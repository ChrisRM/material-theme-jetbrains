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

import com.intellij.ide.ui.laf.darcula.ui.DarculaRadioButtonUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.Gray;
import com.intellij.util.ui.EmptyIcon;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTRadioButtonUI extends DarculaRadioButtonUI {
  public static ComponentUI createUI(final JComponent c) {
    return new MTRadioButtonUI();
  }

  @Override
  public synchronized void paint(final Graphics g2d, final JComponent c) {
    final Graphics2D g = (Graphics2D) g2d;

    final Dimension size = c.getSize();

    final Rectangle viewRect = new Rectangle(size);
    final Rectangle iconRect = new Rectangle();
    final Rectangle textRect = new Rectangle();
    final AbstractButton b = (AbstractButton) c;
    //ButtonModel model = b.getModel();
    final Font f = c.getFont();
    g.setFont(f);
    final FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, f);

    final String text = SwingUtilities.layoutCompoundLabel(
        c, fm, b.getText(), getDefaultIcon(),
        b.getVerticalAlignment(), b.getHorizontalAlignment(),
        b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
        viewRect, iconRect, textRect, b.getIconTextGap());

    // fill background
    if (c.isOpaque()) {
      g.setColor(c.getBackground());
      g.fillRect(0, 0, size.width, size.height);
    }

    paintIcon(c, g, viewRect, iconRect);
    drawText(b, g, text, textRect, fm);
  }

  @Override
  public Icon getDefaultIcon() {
    return JBUI.scale(EmptyIcon.create(20)).asUIResource();
  }

  @Override
  protected void paintIcon(final JComponent c,
                           final Graphics2D g,
                           final Rectangle viewRect,
                           final Rectangle iconRect) {
    final Insets i = c.getInsets();
    viewRect.x += i.left;
    viewRect.y += i.top;
    viewRect.width -= (i.right + viewRect.x);
    viewRect.height -= (i.bottom + viewRect.y);

    final int rad = JBUI.scale(5);

    // Paint the radio button
    final int x = iconRect.x + (rad - (rad % 2 == 1 ? 1 : 0)) / 2;
    final int y = iconRect.y + (rad - (rad % 2 == 1 ? 1 : 0)) / 2;
    final int w = iconRect.width - rad;
    final int h = iconRect.height - rad;

    g.translate(x, y);

    //setup AA for lines
    final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
    final boolean focus = c.hasFocus();
    final boolean selected = ((AbstractButton) c).isSelected();

    // paint focus oval ripple
    if (focus) {
      paintOvalRing(g, w, h);
    }

    // paint border
    g.setPaint(Gray._160.withAlpha(90));
    g.drawOval(JBUI.scale(2), JBUI.scale(1) + 1, w - 1, h - 1);
    g.setPaint(Gray._40.withAlpha(200));
    g.drawOval(JBUI.scale(2), JBUI.scale(1), w - 1, h - 1);

    if (selected) {
      final boolean enabled = c.isEnabled();
      g.setColor(UIManager.getColor(enabled ?
                                    "RadioButton.darcula.selectionEnabledShadowColor" :
                                    "RadioButton.darcula.selectionDisabledShadowColor")); // ? Gray._30 : Gray._60);

      // draw outer border
      g.drawOval(JBUI.scale(2), JBUI.scale(1), w - 1, h - 1);

      // draw dot
      final int xOff = JBUI.scale(2);
      final int yOff = JBUI.scale(1);
      g.fillOval(w / 2 - rad / 2, h / 2 - rad / 2 - yOff, rad + JBUI.scale(4), rad + JBUI.scale(4));

      //      g.setColor(UIManager.getColor(enabled ?
      //                                    "RadioButton.darcula.selectionEnabledColor" :
      //                                    "RadioButton.darcula.selectionDisabledColor")); //Gray._170 : Gray._120);
      //      g.fillOval(w / 2 - rad / 2, h / 2 - rad / 2 - 1 + yOff, rad, rad);
    }
    config.restore();
    g.translate(-x, -y);
  }

  @Override
  protected Color getFocusColor() {
    return UIManager.getColor("Focus.color");
  }

  private void paintOvalRing(final Graphics2D g, final int w, final int h) {
    g.setColor(UIManager.getColor("Focus.color"));
    g.fillOval(-JBUI.scale(2), -3, w + 8, h + 8);
  }

  @Override
  protected void drawText(final AbstractButton b, final Graphics2D g, final String text, final Rectangle textRect, final FontMetrics fm) {
    // Draw the Text
    if (text != null) {
      final View v = (View) b.getClientProperty(BasicHTML.propertyKey);
      if (v != null) {
        v.paint(g, textRect);
      } else {
        final int mnemIndex = b.getDisplayedMnemonicIndex();
        if (b.isEnabled()) {
          // *** paint the text normally
          g.setColor(b.getForeground());
        } else {
          // *** paint the text disabled
          g.setColor(getDisabledTextColor());
        }
        SwingUtilities2.drawStringUnderlineCharAt(b, g, text,
            mnemIndex, textRect.x, textRect.y + fm.getAscent());
      }
    }

    if (b.hasFocus() && b.isFocusPainted() &&
        textRect.width > 0 && textRect.height > 0) {
      paintFocus(g, textRect, b.getSize());
    }
  }

  @Override
  protected void paintFocus(final Graphics g, final Rectangle t, final Dimension d) {

  }
}
