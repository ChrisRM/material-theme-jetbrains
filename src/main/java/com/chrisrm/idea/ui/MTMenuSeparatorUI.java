/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaMenuSeparatorUI;
import com.intellij.util.ui.JBValue;

import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;

public class MTMenuSeparatorUI extends DarculaMenuSeparatorUI {
  private static final JBValue SEPARATOR_HEIGHT = new JBValue.UIInteger("PopupMenuSeparator.height", 10);
  private static final JBValue STRIPE_WIDTH = new JBValue.UIInteger("PopupMenuSeparator.stripeWidth", 10);
  private static final JBValue STRIPE_INDENT = new JBValue.UIInteger("PopupMenuSeparator.stripeIndent", 10);

  public static ComponentUI createUI(final JComponent c) {
    return new MTMenuSeparatorUI();
  }

  @Override
  protected String getColorResourceName() {
    return "Menu.separatorColor";
  }

  @Override
  protected int getStripeIndent() {
    return STRIPE_INDENT.get();
  }

  @Override
  protected int getStripeWidth() {
    return STRIPE_WIDTH.get();
  }

  @Override
  public Dimension getPreferredSize(final JComponent c) {
    return ((JSeparator) c).getOrientation() == SwingConstants.VERTICAL ?
           new Dimension(SEPARATOR_HEIGHT.get(), 0) :
           new Dimension(0, SEPARATOR_HEIGHT.get()); // height is prescaled, so use Dimension here.
  }
}
