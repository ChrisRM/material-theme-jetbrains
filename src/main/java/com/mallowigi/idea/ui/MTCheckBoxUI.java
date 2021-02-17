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

import com.intellij.ide.ui.laf.darcula.ui.DarculaCheckBoxUI;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.EmptyIcon;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.utils.MTIconLookup;
import com.mallowigi.idea.utils.MTUI;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;
import java.awt.*;

@SuppressWarnings({
  "StandardVariableNames",
  "SynchronizedMethod"})
public final class MTCheckBoxUI extends DarculaCheckBoxUI {
  private static final Icon DEFAULT_ICON = JBUIScale.scaleIcon(EmptyIcon.create(20)).asUIResource();

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTCheckBoxUI();
  }

  private static void paintOvalRing(final Graphics2D g, final int w, final int h) {
    g.setColor(MTUI.ActionButton.getHoverBackground());
    g.fillOval(-3, -3, w + 6, h + 6);
  }

  @Override
  public void installUI(final JComponent c) {
    super.installUI(c);
    c.setBackground(MTUI.Panel.getBackground());
    c.setForeground(MTUI.Panel.getForeground());
    if (UIUtil.getParentOfType(CellRendererPane.class, c) != null) {
      c.setBorder(null);
    }
  }

  @Override
  protected int textIconGap() {
    return JBUIScale.scale(4);
  }

  @Override
  public Icon getDefaultIcon() {
    return DEFAULT_ICON;
  }

  @Override
  protected void drawCheckIcon(final JComponent c,
                               final Graphics2D g,
                               final AbstractButton b,
                               final Rectangle iconRect,
                               final boolean selected,
                               final boolean enabled) {
    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      final String iconName = isIndeterminate(b) ? "checkBoxIndeterminate" : "checkBox";
      final boolean hasFocus = b.hasFocus();

      // get the relevant icon
      final Icon icon = MTIconLookup.getIcon("checkboxes/" + iconName, selected || isIndeterminate(b), hasFocus, b.isEnabled());
      icon.paintIcon(b, g2, iconRect.x, iconRect.y);

    } finally {
      g2.dispose();
    }
  }

  @Override
  protected void drawText(final JComponent c,
                          final Graphics2D g,
                          final AbstractButton b,
                          final FontMetrics fm,
                          final Rectangle textRect,
                          final String text) {
    if (text != null) {
      final View view = (View) c.getClientProperty(BasicHTML.propertyKey);
      if (view != null) {
        view.paint(g, textRect);
      } else {
        g.setColor(b.isEnabled() ? b.getForeground() : getDisabledTextColor());
        SwingUtilities2.drawStringUnderlineCharAt(c, g, text,
          b.getDisplayedMnemonicIndex(),
          textRect.x,
          textRect.y + fm.getAscent());
      }
    }
  }

}
