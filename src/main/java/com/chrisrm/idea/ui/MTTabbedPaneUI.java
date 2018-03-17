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

import com.intellij.util.ObjectUtils;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class MTTabbedPaneUI extends BasicTabbedPaneUI {
  public static ComponentUI createUI(final JComponent c) {
    return new MTTabbedPaneUI();
  }

  @Override
  protected void paintTabBackground(final Graphics g,
                                    final int tabPlacement,
                                    final int tabIndex,
                                    final int x,
                                    final int y,
                                    final int w,
                                    final int h,
                                    final boolean isSelected) {
    if (isSelected) {
      super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, true);
      return;
    }
    final Color color = ObjectUtils.notNull(UIManager.getColor("TabbedPane.mt.tab.background"), tabPane.getBackground());
    g.setColor(color);
    switch (tabPlacement) {
      case LEFT:
        g.fillRect(x + 1, y + 1, w - 1, h - 3);
        break;
      case RIGHT:
        g.fillRect(x, y + 1, w - 2, h - 3);
        break;
      case BOTTOM:
        g.fillRect(x + 1, y, w - 3, h - 1);
        break;
      case TOP:
      default:
        g.fillRect(x + 1, y + 1, w - 3, h - 1);
    }
  }
}
