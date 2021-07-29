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

package com.mallowigi.idea.ui.indicators;

import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.config.enums.IndicatorStyles;

import javax.swing.border.Border;
import java.awt.*;

public class MTSelectedTreePainter implements Border {
  private MTSelectedTreeIndicator painter = new MTBorderSelectedTreeIndicator();
  private IndicatorStyles indicatorStyle = IndicatorStyles.BORDER;

  public MTSelectedTreePainter() {
    setSelectedTreePainter();
  }

  @Override
  public final void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    setSelectedTreePainter();
    painter.paintBorder(c, g, x, y, width, height);
  }

  private void setSelectedTreePainter() {
    final IndicatorStyles style = MTConfig.getInstance().getIndicatorStyle();
    if (style != indicatorStyle) {
      indicatorStyle = style;
      switch (style) {
        case NONE:
          painter = new MTNoneSelectedTreeIndicator();
          break;
        case DOT:
          painter = new MTDotSelectedTreeIndicator();
          break;
        case BORDER:
          painter = new MTBorderSelectedTreeIndicator();
          break;
      }
    }
  }

  @Override
  public final Insets getBorderInsets(final Component c) {
    return JBUI.insets(0);
  }

  @Override
  public final boolean isBorderOpaque() {
    return false;
  }
}
