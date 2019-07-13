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
import com.chrisrm.idea.themes.models.MTThemeable;
import com.chrisrm.idea.utils.MTUI;
import com.intellij.ui.paint.RectanglePainter2D;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.newImpl.JBDefaultTabPainter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@SuppressWarnings({"WeakerAccess",
    "CheckStyle"})
public class MTTabsPainter extends JBDefaultTabPainter {
  private final MTConfig mtConfig = MTConfig.getInstance();

  public MTTabsPainter() {
  }

  @Override
  public final Color getBackgroundColor() {
    final MTThemeable mtTheme = mtConfig.getSelectedTheme().getTheme();
    return mtTheme.getBackgroundColor();
  }

  @Override
  public void paintTab(@NotNull final JBTabsPosition position,
                       @NotNull final Graphics2D g,
                       @NotNull final Rectangle rect,
                       final int borderThickness,
                       @Nullable final Color tabColor,
                       final boolean hovered) {
    final Color inactiveBackground = getInactiveBackground();
    final Color hoveredBackground = MTUI.TabbedPane.getHoveredBackground();

    g.setColor(hovered ? hoveredBackground : inactiveBackground);

    RectanglePainter2D.FILL.paint(g, rect.x, rect.y, rect.width, rect.height);
  }

  private Color getInactiveBackground() {
    final boolean isContrast = MTConfig.getInstance().isContrastMode();
    return MTUI.TabbedPane.getInactiveBackground(isContrast);
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
    RectanglePainter2D.FILL.paint(g, rect.x, rect.y, rect.width, rect.height);

    final int borderThickness = mtConfig.getHighlightThickness() + 1;
    final Color underlineColor = getIndicatorColor();
    // Finally paint the active tab highlighter
    g.setColor(underlineColor);
    MTTabsHighlightPainter.paintHighlight(borderThickness, g, rect);
  }

  @NotNull
  private Color getIndicatorColor() {
    final Color accentColor = MTUI.TabbedPane.getHighlightColor();
    final Color highlightColor = mtConfig.getHighlightColor();

    // Color to set
    return mtConfig.isHighlightColorEnabled() ? highlightColor : accentColor;
  }

}
