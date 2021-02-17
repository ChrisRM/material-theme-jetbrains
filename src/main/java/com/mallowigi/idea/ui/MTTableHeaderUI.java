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

import com.intellij.openapi.ui.GraphicsConfig;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTTableHeaderUI extends BasicTableHeaderUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTTableHeaderUI();
  }

  @Override
  public void paint(final Graphics g, final JComponent c) {
    final Color borderColor = MTUI.Table.getBorderColor();

    final Graphics2D g2d = (Graphics2D) g;
    final GraphicsConfig config = new GraphicsConfig(g2d);
    final Color bg = c.getBackground();
    g2d.setColor(bg);
    final int h = c.getHeight();
    final int w = c.getWidth();
    g2d.fillRect(0, 0, w, h);

    g2d.setColor(borderColor);
    g2d.drawLine(0, h - 1, w, h - 1);

    config.restore();

    super.paint(g2d, c);
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
    if (header.getDefaultRenderer() instanceof DefaultTableCellRenderer) {
      header.setDefaultRenderer(new MTDefaultHeaderRenderer());
    }
    header.setFont(header.getFont().deriveFont(Font.BOLD));
  }

  private static final class MTDefaultHeaderRenderer extends DefaultTableCellRenderer {

    private MTDefaultHeaderRenderer() {
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

      setBorder(MTUI.Table.getCellBorder());
      setFont(getFont().deriveFont(Font.BOLD));
      return this;
    }

  }
}
