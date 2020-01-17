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

package com.mallowigi.idea.tabs;

import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.mallowigi.idea.tabs.highlightTabPainters.HighlightTabPainter;

import java.awt.*;

public enum MTTabsHighlightPainter {
  DEFAULT;

  @SuppressWarnings({"OverlyComplexMethod",
      "OverlyComplexBooleanExpression"})
  static void paintHighlight(final int borderThickness,
                             final Graphics2D g2d,
                             final Rectangle rect) {
    final TabHighlightPositions tabHighlightPosition = MTConfig.getInstance().getTabHighlightPosition();
    final HighlightTabPainter tabPainter = HighlightTabPainter.getHighlightTabPainter(tabHighlightPosition);

    tabPainter.paintBottom(borderThickness, g2d, rect, rect.width);
    tabPainter.paintTop(borderThickness, g2d, rect, rect.width);
    tabPainter.paintLeft(borderThickness, g2d, rect, rect.width);
    tabPainter.paintRight(borderThickness, g2d, rect, rect.width);

    //    // If default, use the default according to the tab position
    //    if (tabHighlightPosition == TabHighlightPositions.DEFAULT) {
    //      tabHighlightPosition = getDefaultTabHighlightPosition();
    //    }
    //
    //    // Builder design pattern
    //    if (tabHighlightPosition == TabHighlightPositions.FULL ||
    //        tabHighlightPosition == TabHighlightPositions.BOTTOM ||
    //        tabHighlightPosition == TabHighlightPositions.TOPLESS ||
    //        tabHighlightPosition == TabHighlightPositions.TOP_BOTTOM) {
    //      paintOnBottom(borderThickness, g2d, rect, rect.width);
    //    }
    //    if (tabHighlightPosition == TabHighlightPositions.FULL ||
    //        tabHighlightPosition == TabHighlightPositions.TOP ||
    //        tabHighlightPosition == TabHighlightPositions.BOTTOMLESS ||
    //        tabHighlightPosition == TabHighlightPositions.TOP_BOTTOM) {
    //      paintOnTop(borderThickness, g2d, rect);
    //    }
    //    if (tabHighlightPosition == TabHighlightPositions.FULL ||
    //        tabHighlightPosition == TabHighlightPositions.LEFT ||
    //        tabHighlightPosition == TabHighlightPositions.TOPLESS ||
    //        tabHighlightPosition == TabHighlightPositions.BOTTOMLESS ||
    //        tabHighlightPosition == TabHighlightPositions.LEFT_RIGHT) {
    //      paintOnLeft(borderThickness, g2d, rect);
    //    }
    //    if (tabHighlightPosition == TabHighlightPositions.FULL ||
    //        tabHighlightPosition == TabHighlightPositions.RIGHT ||
    //        tabHighlightPosition == TabHighlightPositions.TOPLESS ||
    //        tabHighlightPosition == TabHighlightPositions.BOTTOMLESS ||
    //        tabHighlightPosition == TabHighlightPositions.LEFT_RIGHT) {
    //      paintOnRight(borderThickness, g2d, rect);
    //    }
  }

}
