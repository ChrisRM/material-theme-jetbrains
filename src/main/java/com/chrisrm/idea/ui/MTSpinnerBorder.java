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

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaSpinnerBorder;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

/**
 * @author Konstantin Bulenkov
 */
public final class MTSpinnerBorder extends DarculaSpinnerBorder implements Border, UIResource {

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    final JSpinner spinner = (JSpinner) c;
    final JFormattedTextField editor = UIUtil.findComponentOfType(spinner, JFormattedTextField.class);
    final int x1 = x + 1;
    final int y1 = y + 3;
    final int width1 = width - 2;
    final int height1 = height - 6;
    final boolean focused = c.isEnabled() && c.isVisible() && editor != null && editor.hasFocus();
    final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);

    if (c.isOpaque()) {
      g.setColor(UIUtil.getPanelBackground());
      g.fillRect(x, y, width, height);
    }

    g.setColor(UIUtil.getTextFieldBackground());
    g.fillRoundRect(x1, y1, width1, height1, 5, 5);
    g.setColor(UIManager.getColor(spinner.isEnabled() ? "Spinner.darcula.enabledButtonColor" : "Spinner.darcula.disabledButtonColor"));
    if (editor != null) {
      final int off = editor.getBounds().x + editor.getWidth() + ((JSpinner) c).getInsets().left + 1;
      final Area rect = new Area(new RoundRectangle2D.Double(x1, y1, width1, height1, 5, 5));
      final Area blueRect = new Area(new Rectangle(off, y1, 22, height1));
      rect.intersect(blueRect);
      ((Graphics2D) g).fill(rect);
      if (UIUtil.isUnderDarcula()) {
        g.setColor(Gray._100);
        g.drawLine(off, y1, off, height1 + 2);
      }
    }

    if (!c.isEnabled()) {
      ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
    }

    if (focused) {
      DarculaUIUtil.paintFocusRing(g, new Rectangle(x1 + 2, y1, width1 - 3, height1));
    } else {
      g.setColor(new JBColor(Gray._149, Gray._100));
      g.drawRoundRect(x1, y1, width1, height1, 5, 5);
    }
    config.restore();
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return new InsetsUIResource(5, 7, 5, 7);
  }

  @Override
  public boolean isBorderOpaque() {
    return true;
  }
}
