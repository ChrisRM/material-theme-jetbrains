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
package com.mallowigi.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaEditorTextFieldBorder;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.MacUIUtil;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.intellij.ide.ui.laf.darcula.DarculaUIUtil.isCompact;
import static com.intellij.ide.ui.laf.darcula.DarculaUIUtil.isTableCellEditor;

public final class MTEditorTextFieldBorder extends DarculaEditorTextFieldBorder {

  @Override
  public Insets getBorderInsets(final Component c) {
    return JBUI.insets(isTableCellEditor(c) || isCompact(c) ? 0 : 3).asUIResource();
  }

  @SuppressWarnings({"FeatureEnvy",
    "MethodWithMoreThanThreeNegations"})
  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle r = new Rectangle(x, y, width, height);
    final boolean isFocused = isFocused(c);
    final boolean isDisabled = !c.isEnabled();
    final boolean isEditable = !(c instanceof JTextComponent) || ((JTextComponent) c).isEditable();

    if (!(c.getParent() instanceof JComboBox)) {
      try {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
          MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

        JBInsets.removeFrom(r, JBUI.insets(1));
        g2.translate(x, y);

        if (isFocused) {
          // Draw accented bold border
          g2.setColor(MTUI.TextField.getSelectedBorderColor());
          g2.fillRect(JBUI.scale(1), height - JBUI.scale(2), width - JBUI.scale(2), JBUI.scale(2));
        } else if (isDisabled && !isEditable) {
          // Draw dash border
          g.setColor(MTUI.TextField.getBorderColor(false));
          g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{1,
            2}, 0));
          g2.draw(new Rectangle2D.Double(JBUI.scale(1), height - JBUI.scale(1), width - JBUI.scale(2), JBUI.scale(2)));
        } else {
          // Draw small border
          g2.setColor(MTUI.TextField.getBorderColor(isEditable));
          g2.fillRect(JBUI.scale(1), height - JBUI.scale(1), width - JBUI.scale(2), JBUI.scale(2));
        }
      } finally {
        g2.dispose();
      }
    }
  }
}
