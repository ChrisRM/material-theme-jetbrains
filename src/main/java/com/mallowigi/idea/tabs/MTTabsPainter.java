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

package com.mallowigi.idea.tabs;

import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.project.Project;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.paint.LinePainter2D;
import com.intellij.ui.paint.RectanglePainter2D;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.impl.JBDefaultTabPainter;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.config.project.MTProjectConfig;
import com.mallowigi.idea.tabs.highlights.MTTabsHighlightPainter;
import com.mallowigi.idea.tabs.shadow.*;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@SuppressWarnings({"WeakerAccess",
  "CheckStyle",
  "DesignForExtension"})
public class MTTabsPainter extends JBDefaultTabPainter {
  private final MTConfig mtConfig = MTConfig.getInstance();
  private Project project = null;
  private JBEditorTabs tabs = null;

  @SuppressWarnings("unused")
  public MTTabsPainter() {

  }

  public MTTabsPainter(final JBEditorTabs component) {
    tabs = component;
  }

  public MTTabsPainter(final JBEditorTabs component, final Project project) {
    this(component);
    this.project = project;
  }

  private static Color getBorderColor() {
    return ColorUtil.withAlpha(Color.BLACK, 0);
  }

  @Override
  public void paintSelectedTab(@NotNull final JBTabsPosition position,
                               @NotNull final Graphics2D g,
                               @NotNull final Rectangle rect,
                               final int borderThickness,
                               @Nullable final Color tabColor,
                               final boolean active,
                               final boolean hovered) {
    g.setColor(hovered ? MTUI.TabbedPane.getHoveredBackground() : MTUI.TabbedPane.getBackground());
    if (tabColor != null) {
      g.setColor(tabColor);
    }
    RectanglePainter2D.FILL.paint(g, rect.x, rect.y, rect.width, rect.height);

    final int configThickness = getHighlightThickness() + 1;
    final Color underlineColor = getIndicatorColor(active);
    // Finally paint the active tab highlighter
    g.setColor(underlineColor);
    MTTabsHighlightPainter.paintHighlight(project, configThickness, g, rect);
  }

  @NotNull
  private Color getIndicatorColor(final boolean active) {
    return getTabUnderlineColor(active);
  }

  @Override
  public void paintUnderline(@NotNull final JBTabsPosition position,
                             @NotNull final Rectangle rect,
                             final int borderThickness,
                             @NotNull final Graphics2D g,
                             final boolean active) {
    final int thickness = getHighlightThickness();
    final Color underlineColor = getIndicatorColor(active);
    // Finally paint the active tab highlighter
    g.setColor(underlineColor);
    MTTabsHighlightPainter.paintHighlight(project, thickness, g, rect);
  }

  private int getHighlightThickness() {
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(project);
    if (projectConfig != null) {
      return projectConfig.getHighlightThickness();
    }
    return mtConfig.getHighlightThickness();
  }

  @SuppressWarnings("MethodWithMultipleReturnPoints")
  public static ShadowPainter getShadowPainter(final JBTabsPosition position) {
    switch (position) {
      case top:
        return new BottomShadowPainter();
      case bottom:
        return new TopShadowPainter();
      case left:
        return new RightShadowPainter();
      case right:
        return new LeftShadowPainter();
      default:
        return new NoneShadowPainter();
    }
  }

  @Override
  public void paintBorderLine(@NotNull final Graphics2D g,
                              final int thickness,
                              @NotNull final Point from,
                              @NotNull final Point to) {
    if (MTConfig.getInstance().isTabsShadow()) {
      final ShadowPainter shadowPainter = getShadowPainter(tabs != null ? tabs.getTabsPosition() : JBTabsPosition.bottom);
      shadowPainter.drawShadow(g, from, to);
    }
    g.setColor(getBorderColor());
    LinePainter2D.paint(g, from.getX(), from.getY(), to.getX(), to.getY(), LinePainter2D.StrokeType.INSIDE, thickness);
  }

  public Color getTabUnderlineColor(final boolean active) {
    final Color accentColor = MTUI.Panel.getLinkForeground();
    final Color highlightColor = mtConfig.getHighlightColor();

    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(project);
    if (projectConfig != null) {
      final Color projectHlColor = projectConfig.getHighlightColor();
      if (projectConfig.isHighlightColorEnabled()) {
        return projectHlColor;
      }
    } else if (mtConfig.isHighlightColorEnabled()) {
      return highlightColor;
    }

    final EditorColorsManager editorColorsManager = EditorColorsManager.getInstance();
    final EditorColorsScheme currentScheme = editorColorsManager.getGlobalScheme();
    final Color tabUnderline = currentScheme.getColor(ColorKey.find(MTUI.Tabs.getTabUnderlineKey()));
    final Color tabUnderlineInactive = currentScheme.getColor(ColorKey.find(MTUI.Tabs.getTabUnderlineInactiveKey()));

    if (active && tabUnderline != null) {
      return tabUnderline;
    } else if (!active && tabUnderlineInactive != null) {
      return tabUnderlineInactive;
    }

    return accentColor;
  }

}
