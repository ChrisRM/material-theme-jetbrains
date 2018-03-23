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

import com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.MacUIUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.intellij.util.ui.JBUI.scale;

/**
 * @author Konstantin Bulenkov
 */
public final class MTTextFieldUI extends TextFieldWithPopupHandlerUI {

  public MTTextFieldUI(final JTextField textField) {
    super();
  }

  public MTTextFieldUI(final JComponent c) {
    this((JTextField) c);
  }

  public static ComponentUI createUI(final JComponent c) {
    return new MTTextFieldUI((JTextField) c);
  }

  @Override
  protected void paintBackground(final Graphics g) {
    final JTextComponent component = getComponent();
    if (component != null) {
      final Container parent = component.getParent();
      if (parent != null && component.isOpaque()) {
        g.setColor(parent.getBackground());
        g.fillRect(0, 0, component.getWidth(), component.getHeight());
      }

      if (component.getBorder() instanceof MTTextBorder) {
        paintDarculaBackground(g, component, component.getBorder());
      } else if (component.isOpaque()) {
        super.paintBackground(g);
      }
    }
  }

  protected void paintDarculaBackground(final Graphics g, final JTextComponent component, final Border border) {
    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle r = new Rectangle(component.getSize());
    JBInsets.removeFrom(r, JBUI.insets(1));

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
          MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

      g2.translate(r.x, r.y);

      if (component.isEnabled() && component.isEditable()) {
        g2.setColor(component.getBackground());
      }
      g2.fill(new Rectangle2D.Float(0, 0, r.width, r.height));
    } finally {
      g2.dispose();
    }
  }

  @Override
  protected int getSearchIconPreferredSpace() {
    final Icon icon = getSearchIcon(true, true);
    return icon == null ? 0 : icon.getIconWidth() + getSearchIconGap();
  }

  /**
   * @return a gap between the search icon and the editable area
   */
  @Override
  protected int getSearchIconGap() {
    return scale(6);
  }

  @Override
  protected int getClearIconPreferredSpace() {
    return super.getClearIconPreferredSpace() - getClearIconGap();
  }

  @Override
  protected int getClearIconGap() {
    return scale(6);
  }

}
