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

import com.intellij.ide.RecentProjectsManagerBase;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.config.project.MTProjectConfig;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.mallowigi.idea.utils.MTUiUtils.stringToARGB;

final class MTProjectTitlePanel extends JPanel {
  private final Pattern projectPattern = Pattern.compile("\\{project\\}");
  private final Pattern modulePattern = Pattern.compile("\\{module\\}");
  private final Pattern filePattern = Pattern.compile("\\{file\\}");

  private final Project myProject;

  MTProjectTitlePanel(final Project project) {
    super(new BorderLayout());
    myProject = project;
  }

  @SuppressWarnings("MultiplyOrDivideByPowerOfTwo")
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
    if (shouldPaintIcon()) {
      final RecentProjectsManagerBase recentProjectsManage = RecentProjectsManagerBase.getInstanceEx();
      final Icon recentIcon = recentProjectsManage.getProjectIcon(Objects.requireNonNull(myProject.getBasePath()), false);
      recentIcon.paintIcon(this,
        g,
        x - recentIcon.getIconWidth() - padding * 2,
        JBUI.scale(padding) / 2);
    }

    if (shouldDrawText()) {
      g.setColor(MTUI.Panel.getBackground());
      g.fillRoundRect(x - padding, padding, textWidth + padding * 2, rect.height - padding * 2, padding, padding);

      g.setColor(MTUI.Panel.getForeground());
      g.drawString(str, x, y);
      g.setClip(oldClip);
    }
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

      final String textToDraw = getTextToDraw();
      // Draw the title
      drawCenteredString(graphics, headerRectangle, textToDraw);
    } finally {
      graphics.dispose();
    }
  }

  @SuppressWarnings({"FeatureEnvy",
    "MagicNumber"})
  @NotNull
  private Color getFrameColor() {
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject);
    if (projectConfig != null && projectConfig.isUseProjectFrame()) {
      return projectConfig.getProjectFrameColor();
    }

    final Color projectColor = new Color(stringToARGB(myProject.getName()));
    return ColorUtil.withAlpha(MTUiUtils.darker(projectColor, 2), 0.5);
  }

  private boolean shouldDrawText() {
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject);
    if (projectConfig != null) {
      return projectConfig.isUseProjectTitle();
    }

    return MTConfig.getInstance().isUseProjectTitle();
  }

  private boolean shouldPaintIcon() {
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject);
    if (projectConfig != null) {
      return projectConfig.isUseProjectIcon();
    }

    return MTConfig.getInstance().isUseProjectIcon();
  }

  @SuppressWarnings("FeatureEnvy")
  private String getTextToDraw() {
    String textToDraw = MTConfig.DEFAULT_TITLE;
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject);
    final MTConfig mtConfig = MTConfig.getInstance();

    if (projectConfig != null && projectConfig.isUseCustomTitle()) {
      textToDraw = projectConfig.getCustomTitle();
    } else if (mtConfig.isUseCustomTitle()) {
      textToDraw = mtConfig.getCustomTitle();
    }
    return replacePatterns(textToDraw);
  }

  private String replacePatterns(final String textToDraw) {
    String result = textToDraw;
    result = projectPattern.matcher(result).replaceAll(myProject.getName());
    if (textToDraw.contains("{module}")) {
      result = replaceModule(result);
    }
    if (textToDraw.contains("{file}")) {
      result = replaceFile(result);
    }
    return result;
  }

  private String replaceModule(final String result) {
    String newResult = result;
    final VirtualFile virtualFile = FileEditorManagerEx.getInstanceEx(myProject).getCurrentFile();
    if (virtualFile != null) {
      final Module moduleForFile = ProjectRootManager.getInstance(myProject).getFileIndex().getModuleForFile(virtualFile);
      if (moduleForFile != null) {
        newResult = modulePattern.matcher(newResult).replaceAll(moduleForFile.getName());
      }
    }
    return newResult;
  }

  private String replaceFile(final String result) {
    String newResult = result;
    final VirtualFile virtualFile = FileEditorManagerEx.getInstanceEx(myProject).getCurrentFile();
    if (virtualFile != null) {
      newResult = filePattern.matcher(newResult).replaceAll(virtualFile.getName());
    }
    return newResult;
  }

  @Override
  public void updateUI() {
    super.updateUI();
    setOpaque(false);
  }
}
