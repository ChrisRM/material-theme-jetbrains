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

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTTableHeaderUI extends BasicTableHeaderUI {

  public static ComponentUI createUI(final JComponent c) {
    return new MTTableHeaderUI();
  }

  @Override
  public void paint(final Graphics g2, final JComponent c) {
    final Color borderColor = UIManager.getColor("TableHeader.borderColor");

    final Graphics2D g = (Graphics2D) g2;
    final GraphicsConfig config = new GraphicsConfig(g);
    final Color bg = c.getBackground();
    g.setColor(bg);
    final int h = c.getHeight();
    final int w = c.getWidth();
    g.fillRect(0, 0, w, h);

    g.setColor(borderColor);
    g.drawLine(0, h - 1, w, h - 1);

    config.restore();

    super.paint(g, c);
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
    header.setDefaultRenderer(new MTDefaultRenderer());
    header.setFont(header.getFont().deriveFont(Font.BOLD));
  }

  private class MTDefaultRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    MTDefaultRenderer() {
      setHorizontalAlignment(SwingConstants.LEFT);
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table,
                                                   final Object value,
                                                   final boolean isSelected,
                                                   final boolean hasFocus,
                                                   final int row,
                                                   final int column) {
      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      final boolean compactTables = MTConfig.getInstance().isCompactTables();
      final Border border;
      border = compactTables ? JBUI.Borders.empty(0, 3) : JBUI.Borders.empty(12, 5);

      setBorder(border);
      setFont(getFont().deriveFont(Font.BOLD));
      return this;
    }
  }
}
