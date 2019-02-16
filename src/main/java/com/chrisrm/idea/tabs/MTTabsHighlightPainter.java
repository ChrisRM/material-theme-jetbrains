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
import com.chrisrm.idea.config.enums.TabHighlightPositions;
import com.intellij.ide.ui.UISettings;

import javax.swing.*;
import java.awt.*;

public enum MTTabsHighlightPainter {
  DEFAULT;

  private static TabHighlightPositions getDefaultTabHighlightPosition() {
    final int editorTabPlacement = UISettings.getInstance().getEditorTabPlacement();
    TabHighlightPositions result = TabHighlightPositions.BOTTOM;

    switch (editorTabPlacement) {
      case SwingConstants.BOTTOM:
        result = TabHighlightPositions.TOP;
        break;
      case SwingConstants.LEFT:
        result = TabHighlightPositions.RIGHT;
        break;
      case SwingConstants.RIGHT:
        result = TabHighlightPositions.LEFT;
        break;
      case SwingConstants.TOP:
      default:
        break;
    }
    return result;
  }

  @SuppressWarnings({"OverlyComplexMethod",
      "OverlyComplexBooleanExpression"})
  static void paintHighlight(final int borderThickness,
                             final Graphics2D g2d,
                             final Rectangle rect) {
    TabHighlightPositions tabHighlightPosition = MTConfig.getInstance().getTabHighlightPosition();
    //    final HighlightAnimation animation = new HighlightAnimation(10, 20, rect);

    // If default, use the default according to the tab position
    if (tabHighlightPosition == TabHighlightPositions.DEFAULT) {
      tabHighlightPosition = getDefaultTabHighlightPosition();
    }

    // Builder design pattern
    if (tabHighlightPosition == TabHighlightPositions.FULL ||
        tabHighlightPosition == TabHighlightPositions.BOTTOM ||
        tabHighlightPosition == TabHighlightPositions.TOP_BOTTOM) {
      paintOnBottom(borderThickness, g2d, rect, rect.width);
      //      animation.start((w) -> paintOnBottom(borderThickness, g2d, rect, w));
      //      new Animator("e", 10, 20, false) {
      //        @Override
      //        public void paintNow(final int frame, final int totalFrames, final int cycle) {
      //          paintOnBottom(borderThickness, g2d, rect, (rect.width / totalFrames) * frame);
      //        }
      //      }.resume();
    }
    if (tabHighlightPosition == TabHighlightPositions.FULL ||
        tabHighlightPosition == TabHighlightPositions.TOP ||
        tabHighlightPosition == TabHighlightPositions.BOTTOMLESS ||
        tabHighlightPosition == TabHighlightPositions.TOP_BOTTOM) {
      paintOnTop(borderThickness, g2d, rect);
    }
    if (tabHighlightPosition == TabHighlightPositions.FULL ||
        tabHighlightPosition == TabHighlightPositions.LEFT ||
        tabHighlightPosition == TabHighlightPositions.BOTTOMLESS ||
        tabHighlightPosition == TabHighlightPositions.LEFT_RIGHT) {
      paintOnRight(borderThickness, g2d, rect);
    }
    if (tabHighlightPosition == TabHighlightPositions.FULL ||
        tabHighlightPosition == TabHighlightPositions.RIGHT ||
        tabHighlightPosition == TabHighlightPositions.BOTTOMLESS ||
        tabHighlightPosition == TabHighlightPositions.LEFT_RIGHT) {
      paintOnLeft(borderThickness, g2d, rect);
    }
  }

  private static void paintOnLeft(final int borderThickness, final Graphics2D g2d, final Rectangle rect) {
    g2d.fillRect(rect.x + rect.width - borderThickness + 1, rect.y, borderThickness, rect.height);
  }

  private static void paintOnRight(final int borderThickness, final Graphics2D g2d, final Rectangle rect) {
    g2d.fillRect(rect.x, rect.y, borderThickness, rect.height);
  }

  private static void paintOnBottom(final int borderThickness, final Graphics2D g2d, final Rectangle rect, final int w) {
    g2d.fillRect(rect.x, rect.y + rect.height - borderThickness + 1, w, borderThickness);
  }

  private static void paintOnTop(final int borderThickness, final Graphics2D g2d, final Rectangle rect) {
    g2d.fillRect(rect.x, rect.y - 1, rect.width, borderThickness);
  }
}
