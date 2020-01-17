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

package com.mallowigi.idea.ui;

import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.utils.MTUI;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

public final class MTDarculaButtonUI extends DarculaButtonUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTDarculaButtonUI();
  }

  /**
   * Paint the text of the button
   */
  @Override
  protected void paintText(final Graphics g, final JComponent c, final Rectangle textRect, final String text) {
    if (UIUtil.isHelpButton(c)) {
      return;
    }

    final AbstractButton button = (AbstractButton) c;
    final ButtonModel model = button.getModel();
    g.setColor(getButtonTextColor(button));
    final FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g);
    final String textToPrint = MTConfig.getInstance().isUpperCaseButtons() ? text.toUpperCase() : text;
    final int textWidth = metrics.stringWidth(textToPrint);

    final int x = (c.getWidth() - getTextShiftOffset() - textWidth) / 2;
    final int y = textRect.y + metrics.getAscent();

    final int mnemonicIndex = DarculaLaf.isAltPressed() ? button.getDisplayedMnemonicIndex() : -1;
    if (model.isEnabled()) {
      SwingUtilities2.drawStringUnderlineCharAt(c, g, textToPrint, mnemonicIndex, x, y);
    } else {
      paintDisabledText(g, text, c, textRect, metrics);
    }
  }

  /**
   * Paint disabled text
   */
  @Override
  protected void paintDisabledText(final Graphics g,
                                   final String text,
                                   final JComponent c,
                                   final Rectangle textRect,
                                   final FontMetrics metrics) {
    final String textToPrint = MTConfig.getInstance().isUpperCaseButtons() ? text.toUpperCase() : text;
    final int x = (c.getWidth() - getTextShiftOffset() - metrics.stringWidth(textToPrint)) / 2;
    g.setColor(UIManager.getColor(MTUI.Button.BUTTON_DISABLED_TEXT));
    SwingUtilities2.drawStringUnderlineCharAt(c, g, textToPrint, -1, x + 1, textRect.y + metrics.getAscent() + 1);
  }

}
