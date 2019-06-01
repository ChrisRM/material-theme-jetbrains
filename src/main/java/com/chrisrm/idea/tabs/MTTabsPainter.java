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
import com.intellij.ui.ColorUtil;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.newImpl.JBDefaultTabPainter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class MTTabsPainter extends JBDefaultTabPainter {
  private static ShadowPainter shadowPainter;

  public MTTabsPainter() {
  }

  @Override
  public final Color getBackgroundColor() {
    final MTConfig config = MTConfig.getInstance();
    final MTThemeable mtTheme = config.getSelectedTheme().getTheme();
    return mtTheme.getBackgroundColor();
  }

  @Override
  public void paintBorderLine(@NotNull final Graphics2D g,
                              final int thickness,
                              @NotNull final Point from,
                              @NotNull final Point to) {
    //    final JBTabsPosition tabsPosition = myTabs.getTabsPosition();
    //    drawTabShadow(g, from, to, tabsPosition);
  }

  @Override
  public void paintSelectedTab(@NotNull final JBTabsPosition position,
                               @NotNull final Graphics2D g,
                               @NotNull final Rectangle rect,
                               @Nullable final Color tabColor,
                               final boolean active,
                               final boolean hovered) {

    final int borderThickness = MTConfig.getInstance().getHighlightThickness() + 1;
    final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());

    g.setColor(tabColor);
    g.fillRect(rect.x, rect.y - 1, rect.width, borderThickness);

    // todo we can try to reproduce the ripple with the hovered boolean here

    // Finally paint the active tab highlighter
    g.setColor(accentColor);
    MTTabsHighlightPainter.paintHighlight(borderThickness, g, rect);
  }

  /**
   * @deprecated
   */
  private static void drawTabShadow(final Graphics2D g2d,
                                    final Point from,
                                    final Point to,
                                    final JBTabsPosition position) {
    if (shadowPainter == null) {
      shadowPainter = getShadowPainter(position);
    }
    shadowPainter.drawShadow(g2d, from, to);
  }

  @SuppressWarnings("MethodWithMultipleReturnPoints")
  private static ShadowPainter getShadowPainter(final JBTabsPosition position) {
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

}
