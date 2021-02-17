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

package com.mallowigi.idea;

import com.intellij.ide.RecentProjectsManagerBase;
import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeRootPaneNorthExtension;
import com.intellij.ui.ColorUtil;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.*;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static com.mallowigi.idea.utils.MTUiUtils.stringToARGB;

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
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        addFrame(shouldShowProjectFrame());
      }
    });
  }

  private static boolean shouldShowProjectFrame() {
    final UISettings uiSettings = UISettings.getInstance();
    return !uiSettings.getPresentationMode() && MTConfig.getInstance().isUseProjectFrame();
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

  private static final class MTProjectFrameWrapperPanel extends JPanel {
    @SuppressWarnings("HardCodedStringLiteral")
    private MTProjectFrameWrapperPanel(final LayoutManager layout) {
      super(layout);
      setName("mtRootPane");
    }

    @Override
    protected Graphics getComponentGraphics(final Graphics g) {
      return JBSwingUtilities.runGlobalCGTransform(this, super.getComponentGraphics(g));
    }
  }

  private static final class MTProjectTitlePanel extends JPanel {
    private final Project myProject;

    private MTProjectTitlePanel(final Project project) {
      super(new BorderLayout());
      myProject = project;
    }

    private void drawCenteredString(@NotNull final Graphics2D g,
                                    @NotNull final Rectangle rect,
                                    @NotNull final String str) {
      final FontMetrics fm = g.getFontMetrics(g.getFont());
      final int textWidth = fm.stringWidth(str) - 1;
      final int x = Math.max(rect.x, rect.x + (rect.width - textWidth) / 2);
      final int y = Math.max(rect.y, rect.y + rect.height / 2 + fm.getAscent() * 2 / 5);
      final int padding = JBUI.scale(4);
      final Shape oldClip = g.getClip();

      // Draw icon
      final RecentProjectsManagerBase recentProjectsManage = RecentProjectsManagerBase.getInstanceEx();
      Icon recentIcon = recentProjectsManage.getProjectIcon(Objects.requireNonNull(myProject.getBasePath()),
        StartupUiUtil.isUnderDarcula());
      if (recentIcon == null) {
        recentIcon = EmptyIcon.ICON_16;
      }

      //      IconUtil.paintInCenterOf(this, g, recentIcon);

      g.setColor(MTUI.Panel.getBackground());
      g.fillRoundRect(x - padding, padding, textWidth + padding * 2, rect.height - padding * 2, padding, padding);

      g.clip(rect);
      recentIcon.paintIcon(this, g, x, y);

      g.setColor(MTUI.Panel.getForeground());
      g.drawString(str, x, y);
      g.setClip(oldClip);
    }

    @Override
    protected void paintComponent(final Graphics g) {
      super.paintComponent(g);

      final Graphics2D graphics = (Graphics2D) g.create();
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      try {
        final Rectangle headerRectangle = new Rectangle(0, 0, getWidth(), JBUI.insetsTop(24).top);
        graphics.setColor(getFrameColor());
        graphics.fill(headerRectangle);
        graphics.setFont(MTUI.Panel.getFont());

        final String textToDraw = myProject.getName();

        // Draw the title
        drawCenteredString(graphics, headerRectangle, textToDraw);
      } finally {
        graphics.dispose();
      }
    }

    @NotNull
    private Color getFrameColor() {
      final Color projectColor = new Color(stringToARGB(myProject.getName()));

      return ColorUtil.withAlpha(MTUiUtils.darker(projectColor, 2), 0.5);
    }
  }
}
