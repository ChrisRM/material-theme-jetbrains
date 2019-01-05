/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.ui;

import com.intellij.ui.paint.RectanglePainter;
import com.intellij.util.ui.RegionPainter;

import java.awt.*;

public final class MTScrollUI extends RegionPainter.Alpha {
  private final int myOffset;
  private final float myAlphaBase;
  private final float myAlphaDelta;
  private final Color myFillColor;
  private final Color myDrawColor;

  public MTScrollUI(final int offset, final float base, final float delta, final Color fill, final Color draw) {
    myOffset = offset;
    myAlphaBase = base;
    myAlphaDelta = delta;
    myFillColor = fill;
    myDrawColor = draw;
  }

  @Override
  protected float getAlpha(final Float value) {
    return value != null ? myAlphaBase + myAlphaDelta * value : 0;
  }

  @Override
  protected void paint(final Graphics2D g, final int x, final int y, final int width, final int height) {
    int finalX = x;
    int finalY = y;
    int finalWidth = width;
    int finalHeight = height;

    if (myOffset > 0) {
      finalX += myOffset;
      finalY += myOffset;
      finalWidth -= myOffset + myOffset;
      finalHeight -= myOffset + myOffset;
    }
    if (finalWidth > 0 && finalHeight > 0) {
      if (myFillColor != null) {
        g.setColor(myFillColor);
        fill(g, finalX, finalY, finalWidth, finalHeight, myDrawColor != null);
      }
      if (myDrawColor != null) {
        g.setColor(myDrawColor);
        draw(g, finalX, finalY, finalWidth, finalHeight);
      }
    }
  }

  private static void fill(final Graphics2D g, final int x, final int y, final int width, final int height, final boolean border) {
    if (border) {
      g.fillRect(x + 1, y + 1, width - 2, height - 2);
    } else {
      g.fillRect(x, y, width, height);
    }
  }

  private static void draw(final Graphics2D g, final int x, final int y, final int width, final int height) {
    RectanglePainter.DRAW.paint(g, x, y, width, height, Math.min(width, height));
  }
}
