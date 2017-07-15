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
import com.intellij.ide.ui.laf.darcula.ui.DarculaTextAreaUI;
import com.intellij.openapi.util.SystemInfo;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import java.awt.event.KeyEvent;

public final class MTTextAreaUI extends DarculaTextAreaUI {
  @SuppressWarnings("MethodOverridesStaticMethodOfSuperclass")
  public static ComponentUI createUI(final JComponent c) {
    return new MTTextAreaUI();
  }

  @Override
  public int getNextVisualPositionFrom(final JTextComponent t,
                                       final int pos,
                                       final Position.Bias b,
                                       final int direction,
                                       final Position.Bias[] biasRet)
      throws BadLocationException {
    int position = DarculaUIUtil.getPatchedNextVisualPositionFrom(t, pos, direction);
    return position != -1 ? position : super.getNextVisualPositionFrom(t, pos, b, direction, biasRet);
  }

  @Override
  protected void installKeyboardActions() {
    super.installKeyboardActions();
    if (SystemInfo.isMac) {
      InputMap inputMap = getComponent().getInputMap();
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), DefaultEditorKit.upAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DefaultEditorKit.downAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), DefaultEditorKit.pageUpAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0), DefaultEditorKit.pageDownAction);
    }
  }
}
