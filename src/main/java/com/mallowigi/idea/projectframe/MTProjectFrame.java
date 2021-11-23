/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.mallowigi.idea.projectframe;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeRootPaneNorthExtension;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.config.project.MTProjectConfig;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.listeners.MTTopics;
import com.mallowigi.idea.listeners.ProjectConfigNotifier;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("SyntheticAccessorCall")
public final class MTProjectFrame extends IdeRootPaneNorthExtension implements Disposable {
  private final Project myProject;
  private final MessageBusConnection connect;
  @Nullable
  private JComponent myWrapperPanel;
  @Nullable
  private JPanel myProjectFramePanel;

  private MTProjectFrame(@NotNull final Project project) {
    myProject = project;

    connect = myProject.getMessageBus().connect();
    connect.subscribe(MTTopics.CONFIG, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        addFrame(shouldShowProjectFrame());
      }
    });
    connect.subscribe(MTTopics.PROJECT_CONFIG, new ProjectConfigNotifier() {
      @Override
      public void projectConfigChanged(final MTProjectConfig mtConfig) {
        addFrame(shouldShowProjectFrame());
      }
    });
  }

  private static boolean shouldShowProjectFrame() {
    final UISettings uiSettings = UISettings.getInstance();
    final boolean useProjectFrame = MTConfig.getInstance().isUseProjectFrame();

    return !uiSettings.getPresentationMode() && useProjectFrame;
  }

  @Override
  public void dispose() {
    connect.disconnect();
  }

  private void addFrame(final boolean show) {
    if (myWrapperPanel == null) {
      return;
    }

    if (show && myProjectFramePanel == null) {
      myProjectFramePanel = buildPanel();
      myWrapperPanel.add(myProjectFramePanel, BorderLayout.CENTER);
    } else if (!show && myProjectFramePanel != null) {
      myWrapperPanel.remove(myProjectFramePanel);
      myProjectFramePanel = null;
    }
    myWrapperPanel.repaint();
  }

  @NonNls
  @NotNull
  @Override
  public String getKey() {
    return "MTProjectFrame";
  }

  @NotNull
  @Override
  public JComponent getComponent() {
    if (myWrapperPanel == null) {
      myWrapperPanel = new MTProjectFrameWrapperPanel(new BorderLayout());
      addFrame(shouldShowProjectFrame());
    }
    return myWrapperPanel;
  }

  private JPanel buildPanel() {
    final MTProjectTitlePanel mtProjectTitlePanel = new MTProjectTitlePanel(myProject);
    final JPanel panel = new JPanel(new BorderLayout()) {

      @Override
      protected void paintComponent(final Graphics g) {
        mtProjectTitlePanel.paintComponent(g);
      }

      @Override
      public Insets getInsets() {
        return JBInsets.create(JBUI.scale(12), 0);
      }
    };
    panel.add(mtProjectTitlePanel, BorderLayout.CENTER);
    panel.updateUI();

    return panel;
  }

  @Override
  public void uiSettingsChanged(final UISettings settings) {
    addFrame(shouldShowProjectFrame());
  }

  @Override
  public IdeRootPaneNorthExtension copy() {
    return new MTProjectFrame(myProject);
  }

}
