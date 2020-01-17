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
package com.mallowigi.idea.ui;

import com.mallowigi.idea.utils.MTUI;
import com.intellij.ide.ui.laf.darcula.ui.DarculaSpinnerBorder;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.MacUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Konstantin Bulenkov
 */
public final class MTSpinnerBorder extends DarculaSpinnerBorder {

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
          MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

      if (isFocused(c)) {
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
    } finally {
      g2.dispose();
    }
  }

  private static Color getBorderColor(final boolean enabled) {
    return enabled ? MTUI.TextField.getBorderColor(true) : UIManager.getColor(MTUI.TextField.TEXT_FIELD_SEPARATOR_COLOR_DISABLED);
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return JBUI.insets(3).asUIResource();
  }

  private static Color getSelectedBorderColor() {
    return MTUI.TextField.getSelectedBorderColor();
  }

  @Override
  public boolean isBorderOpaque() {
    return false;
  }

}
