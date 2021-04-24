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

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.IdeRootPane;
import com.intellij.openapi.wm.impl.ToolwindowToolbar;
import com.intellij.openapi.wm.impl.WindowManagerImpl;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

/**
 * Temporary fix for the stripes feature to avoid having duplicates
 */
@SuppressWarnings("UnstableApiUsage")
public final class MTFixStripes implements ProjectManagerListener {

  @Override
  public void projectClosing(@NotNull final Project project) {
    if (!MTConfig.getInstance().isStripedToolWindowsEnabled()) {
      return;
    }
    final IdeRootPane ideRootPane = ((WindowManagerImpl) WindowManager.getInstance()).getProjectFrameRootPane(project);
    final java.util.List<ToolwindowToolbar> toolbars = UIUtil.findComponentsOfType(ideRootPane, ToolwindowToolbar.class);

    for (final ToolwindowToolbar toolbar : toolbars) {
      final List<JPanel> panes = UIUtil.findComponentsOfType(toolbar, JPanel.class);
      for (final JPanel pane : panes) {
        if (pane.getLayout().toString().contains("VerticalFlowLayout")) {
          pane.removeAll();
        }
      }
    }
  }

}
