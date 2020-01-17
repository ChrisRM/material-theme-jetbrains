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

package com.mallowigi.idea.ui;

import com.mallowigi.idea.MTConfig;
import com.intellij.util.ui.UIUtil;

import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;
import java.awt.*;

public final class MTStatusBarBorder implements Border, UIResource {

  static final int COMPACT_PADDING = 4;
  public static final int DEFAULT_PADDING = 8;

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    final Graphics2D g2d = (Graphics2D) g.create();
    final Color background = UIUtil.getPanelBackground();

    g2d.setColor(background);
    g2d.fillRect(0, 0, width, height);

    g2d.dispose();
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return getInsets();
  }

  private static Insets getInsets() {
    final boolean compactStatusBar = MTConfig.getInstance().isCompactStatusBar();
    final int padding = compactStatusBar ? COMPACT_PADDING : DEFAULT_PADDING;

    return new InsetsUIResource(padding, 0, padding, 0);
  }

  @Override
  public boolean isBorderOpaque() {
    return true;
  }
}
