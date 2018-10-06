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

import com.intellij.ide.ui.laf.darcula.ui.DarculaPopupMenuBorder;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;

import javax.swing.plaf.UIResource;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

/**
 * @author Konstantin Bulenkov
 */
public final class MTPopupMenuBorder extends DarculaPopupMenuBorder implements UIResource {
  private static Shape getBorderShape(final Component c, final Rectangle rect) {
    final int x = rect.x;
    final int y = rect.y;
    final int width = rect.width;
    final int height = rect.height;
    final int thickness = 1;
    final int doubleThickness = thickness * 2;
    final int arc = 10;
    final Shape outer = new RoundRectangle2D.Float(x, y, width, height, arc, arc);

    if (width <= doubleThickness || height <= doubleThickness) {
      return outer;
    }
    final Shape inner = new RoundRectangle2D.Float(x + thickness, y + thickness, width - doubleThickness, height - doubleThickness, arc,
        arc);

    final Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    path.append(outer, false);
    path.append(inner, false);
    return path;
  }

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.setColor(JBColor.namedColor("Menu.borderColor", new JBColor(Gray.xCD, Gray.x51)));
      final Shape border = getBorderShape(c, new Rectangle(x, y, width, height));
      if (border != null) {
        final Object old = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(border);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, old);
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return JBUI.insets(2).asUIResource();
  }

  @Override
  public boolean isBorderOpaque() {
    return true;
  }
}
