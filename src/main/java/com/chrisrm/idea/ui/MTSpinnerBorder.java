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

import com.chrisrm.idea.utils.MTUI;
import com.intellij.ide.ui.laf.darcula.ui.DarculaSpinnerBorder;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

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
    final Graphics2D g2 = (Graphics2D) g.create();

    if (c.isOpaque()) {
      g.setColor(UIUtil.getPanelBackground());
      g.fillRect(x, y, width, height);
    }

    g.setColor(UIUtil.getTextFieldBackground());
    g.fillRect(x1, y1, width1, height1);
    g.setColor(UIManager.getColor(spinner.isEnabled() ? "Spinner.darcula.enabledButtonColor" : "Spinner.darcula.disabledButtonColor"));
    if (editor != null) {
      final int off = editor.getBounds().x + editor.getWidth() + ((JSpinner) c).getInsets().left + 1;
      final Area rect = new Area(new Rectangle2D.Double(x1, y1, width1, height1));
      final Area blueRect = new Area(new Rectangle(off, y1, 22, height1));
      rect.intersect(blueRect);
      ((Graphics2D) g).fill(rect);
    }

    if (!c.isEnabled()) {
      ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
    }

    if (focused) {
      g.setColor(getSelectedBorderColor());
      g.fillRect(JBUI.scale(1), height - JBUI.scale(2), width - JBUI.scale(2), JBUI.scale(2));
    } else if (!c.isEnabled()) {
      g.setColor(getBorderColor(c.isEnabled()));
      g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{1,
          2}, 0));
      g2.draw(new Rectangle2D.Double(JBUI.scale(1), height - JBUI.scale(1), width - JBUI.scale(2), JBUI.scale(2)));
    } else {
      g.setColor(getBorderColor(c.isEnabled()));
      g.fillRect(JBUI.scale(1), height - JBUI.scale(1), width - JBUI.scale(2), JBUI.scale(2));
    }
    g2.dispose();
    config.restore();
  }

  private static Color getBorderColor(final boolean enabled) {
    return enabled ? MTUI.TextField.getBorderColor(true) : UIManager.getColor("TextField.separatorColorDisabled");
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return new InsetsUIResource(6, 7, 6, 7);
  }

  private Color getSelectedBorderColor() {
    return MTUI.TextField.getSelectedBorderColor();
  }

  @Override
  public boolean isBorderOpaque() {
    return false;
  }
}
