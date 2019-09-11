/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.tabs;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.tabs.shadowPainters.*;
import com.chrisrm.idea.themes.models.MTThemeable;
import com.chrisrm.idea.utils.MTUI;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.paint.RectanglePainter2D;
import com.intellij.ui.tabs.JBTabs;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.impl.JBDefaultTabPainter;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.newImpl.ShapeTransform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@SuppressWarnings({"WeakerAccess",
    "CheckStyle"})
public class MTTabsPainter extends JBDefaultTabPainter {
  private final MTConfig mtConfig = MTConfig.getInstance();
  private JBEditorTabs tabs = null;

  public MTTabsPainter() {

  }

  public MTTabsPainter(final JBEditorTabs component) {
    this.tabs = component;
  }

  @Override
  public final Color getBackgroundColor() {
    final MTThemeable mtTheme = mtConfig.getSelectedTheme().getTheme();
    return mtTheme.getBackgroundColor();
  }

  @Override
  public void paintSelectedTab(@NotNull final JBTabsPosition position,
                               @NotNull final Graphics2D g,
                               @NotNull final Rectangle rect,
                               final int thickness,
                               @Nullable final Color tabColor,
                               final boolean active,
                               final boolean hovered) {
    g.setColor(hovered ? MTUI.TabbedPane.getHoveredBackground() : MTUI.TabbedPane.getBackground());
    if (tabColor != null) {
      g.setColor(tabColor);
    }
    RectanglePainter2D.FILL.paint(g, rect.x, rect.y, rect.width, rect.height);

    final int borderThickness = mtConfig.getHighlightThickness() + 1;
    final Color underlineColor = getIndicatorColor();
    // Finally paint the active tab highlighter
    g.setColor(underlineColor);
    MTTabsHighlightPainter.paintHighlight(borderThickness, g, rect);
  }

  @NotNull
  private Color getIndicatorColor() {
    final Color accentColor = MTUI.Panel.getLinkForeground();
    final Color highlightColor = mtConfig.getHighlightColor();

    // Color to set
    return mtConfig.isHighlightColorEnabled() ? highlightColor : accentColor;
  }

  @Override
  public void paintUnderline(@NotNull final JBTabsPosition position,
                             @NotNull final Rectangle rect,
                             final int borderThickness,
                             @NotNull final Graphics2D g,
                             final boolean active) {
    final int thickness = mtConfig.getHighlightThickness() + 1;
    final Color underlineColor = getIndicatorColor();
    // Finally paint the active tab highlighter
    g.setColor(underlineColor);
    MTTabsHighlightPainter.paintHighlight(thickness, g, rect);
  }

  private static void drawTabShadow(final Graphics2D g2d,
                                    final Rectangle rect,
                                    final ShapeTransform path,
                                    final ShapeTransform labelPath,
                                    final JBTabsPosition position) {
    final ShadowPainter shadowPainter = getShadowPainter(position);
    shadowPainter.drawShadow(g2d, path, labelPath, rect);
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
  public void paintBorderLine(@NotNull final Graphics2D g2d,
                              final int thickness,
                              @NotNull final Point from,
                              @NotNull final Point to) {

    final ShadowPainter shadowPainter = getShadowPainter(tabs != null ? tabs.getTabsPosition() : JBTabsPosition.bottom);
    shadowPainter.drawShadow(g2d, from, to);
  }
}
