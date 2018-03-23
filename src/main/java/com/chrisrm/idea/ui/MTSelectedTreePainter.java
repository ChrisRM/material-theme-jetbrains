/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

import com.chrisrm.idea.MTConfig;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ui.JBUI;

import javax.swing.border.Border;
import java.awt.*;

public final class MTSelectedTreePainter implements Border {
  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    final Color oldColor = g.getColor();
    int i;

    final int thickness = getThickness();
    for (i = 0; i < thickness; i++) {
      g.setColor(getHighlightColor());
      g.drawLine(x + i, y, x + i, height + y);
    }
    g.setColor(oldColor);
  }

  private Color getHighlightColor() {
    return ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
  }

  private int getThickness() {
    return MTConfig.getInstance().getHighlightThickness();
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return JBUI.insetsLeft(getThickness());
  }

  @Override
  public boolean isBorderOpaque() {
    return true;
  }
}
