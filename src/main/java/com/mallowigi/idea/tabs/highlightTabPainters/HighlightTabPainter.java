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

package com.mallowigi.idea.tabs.highlightTabPainters;

import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.intellij.ide.ui.UISettings;

import java.awt.*;

public abstract class HighlightTabPainter {
  /**
   * Factory method to get the HighlightTabPainter from a position
   *
   * @param tabHighlightPosition the config's position
   * @return instance of HighlightTabPainter
   */
  public static HighlightTabPainter getHighlightTabPainter(final TabHighlightPositions tabHighlightPosition) {
    switch (tabHighlightPosition) {
      case TOP:
        return new TopHighlightTabPainter();
      case FULL:
        return new FullHighlightTabPainter();
      case LEFT:
        return new LeftHighlightTabPainter();
      case NONE:
        return new NoneHighlightTabPainter();
      case RIGHT:
        return new RightHighlightTabPainter();
      case BOTTOM:
        return new BottomHighlightTabPainter();
      case TOPLESS:
        return new ToplessHighlightTabPainter();
      case BOTTOMLESS:
        return new BottomlessHighlightTabPainter();
      case LEFT_RIGHT:
        return new LeftRightHighlightTabPainter();
      case TOP_BOTTOM:
        return new TopBottomHighlightTabPainter();
      case DEFAULT:
      default:
        return new DefaultHighlightTabPainter();
    }
  }

  static int getEditorTabPlacement() {
    return UISettings.getInstance().getEditorTabPlacement();
  }

  public abstract void paintBottom(int borderThickness, Graphics2D g2d, Rectangle rect, int width);

  public abstract void paintTop(int borderThickness, Graphics2D g2d, Rectangle rect, int width);

  public abstract void paintLeft(int borderThickness, Graphics2D g2d, Rectangle rect, int width);

  public abstract void paintRight(int borderThickness, Graphics2D g2d, Rectangle rect, int width);

  static void paintOnRight(final int borderThickness, final Graphics2D g2d, final Rectangle rect) {
    g2d.fillRect(rect.x + rect.width - borderThickness + 1, rect.y, borderThickness, rect.height);
  }

  static void paintOnLeft(final int borderThickness, final Graphics2D g2d, final Rectangle rect) {
    g2d.fillRect(rect.x, rect.y, borderThickness, rect.height);
  }

  static void paintOnBottom(final int borderThickness, final Graphics2D g2d, final Rectangle rect, final int w) {
    g2d.fillRect(rect.x, rect.y + rect.height - borderThickness + 1, w, borderThickness);
  }

  static void paintOnTop(final int borderThickness, final Graphics2D g2d, final Rectangle rect) {
    g2d.fillRect(rect.x, rect.y - 1, rect.width, borderThickness);
  }
}
