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

import com.intellij.ide.ui.laf.darcula.ui.DarculaCheckBoxUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.Gray;
import com.intellij.util.ui.EmptyIcon;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTCheckBoxUI extends DarculaCheckBoxUI {
  public static ComponentUI createUI(final JComponent c) {
    if (UIUtil.getParentOfType(CellRendererPane.class, c) != null) {
      c.setBorder(null);
    }
    return new MTCheckBoxUI();
  }

  protected static Color getColor(final String shortPropertyName, final Color defaultValue) {
    final Color color = UIManager.getColor("CheckBox.darcula." + shortPropertyName);
    return color == null ? defaultValue : color;
  }

  protected static Color getColor(final String shortPropertyName, final Color defaultValue, final boolean selected) {
    if (selected) {
      final Color color = getColor(shortPropertyName + ".selected", null);
      if (color != null) {
        return color;
      }
    }
    return getColor(shortPropertyName, defaultValue);
  }

  @Override
  public synchronized void paint(final Graphics g2d, final JComponent c) {
    final Graphics2D g = (Graphics2D) g2d;
    final JCheckBox b = (JCheckBox) c;
    final Dimension size = c.getSize();
    final Font font = c.getFont();

    g.setFont(font);
    final FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, font);

    final Rectangle viewRect = new Rectangle(size);
    final Rectangle iconRect = new Rectangle();
    final Rectangle textRect = new Rectangle();

    JBInsets.removeFrom(viewRect, c.getInsets());

    final String text = SwingUtilities.layoutCompoundLabel(c, fm, b.getText(), getDefaultIcon(),
        b.getVerticalAlignment(), b.getHorizontalAlignment(),
        b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
        viewRect, iconRect, textRect, b.getIconTextGap());

    //background
    if (c.isOpaque()) {
      g.setColor(b.getBackground());
      g.fillRect(0, 0, size.width, size.height);
    }

    final boolean selected = b.isSelected();
    final boolean enabled = b.isEnabled();
    drawCheckIcon(c, g, b, iconRect, selected, enabled);
    drawText(c, g, b, fm, textRect, text);
  }

  @Override
  public Icon getDefaultIcon() {
    return EmptyIcon.create(JBUI.scale(20)).asUIResource();
  }

  @Override
  protected void drawCheckIcon(final JComponent c,
                               final Graphics2D g,
                               final JCheckBox b,
                               final Rectangle iconRect,
                               final boolean selected,
                               final boolean enabled) {
    if (selected && b.getSelectedIcon() != null) {
      b.getSelectedIcon().paintIcon(b, g, iconRect.x + JBUI.scale(4), iconRect.y + JBUI.scale(2));
    } else if (!selected && b.getIcon() != null) {
      b.getIcon().paintIcon(b, g, iconRect.x + JBUI.scale(4), iconRect.y + JBUI.scale(2));
    } else {
      final int off = JBUI.scale(3);
      final int x = iconRect.x + off;
      final int y = iconRect.y + off;
      final int w = iconRect.width - 2 * off;
      final int h = iconRect.height - 2 * off;

      g.translate(x, y);
      final Paint paint = UIUtil.getGradientPaint(w / 2, 0, b.getBackground().brighter(),
          w / 2, h, b.getBackground());
      g.setPaint(paint);
      final int fillOffset = JBUI.scale(1);
      g.fillRect(fillOffset, fillOffset, w - 2 * fillOffset, h - 2 * fillOffset);

      //setup AA for lines
      final GraphicsConfig config = new GraphicsConfig(g);
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

      final boolean armed = b.getModel().isArmed();

      final int rad = JBUI.scale(2);
      if (c.hasFocus()) {
        paintOvalRing(g, w, h);

        g.setPaint(getFocusedBackgroundColor1(armed, selected));
        g.fillRoundRect(0, 0, w, h, rad, rad);

      } else {
        g.setPaint(getBackgroundColor1(enabled, selected));
        g.fillRoundRect(0, 0, w, h, rad, rad);

        final Color borderColor1 = getBorderColor1(enabled, selected);
        g.setPaint(borderColor1);
        g.drawRoundRect(0, 0, w, h - 1, rad, rad);

        g.setPaint(getInactiveFillColor());
        g.drawRoundRect(0, 0, w, h - 1, rad, rad);
      }

      if (b.getModel().isSelected()) {
        paintCheckSign(g, enabled, w, h);
      }
      g.translate(-x, -y);
      config.restore();
    }
  }

  private void paintOvalRing(final Graphics2D g, final int w, final int h) {
    g.setColor(UIManager.getColor("Focus.color"));
    g.fillOval(-5, -5, w + 10, h + 10);
  }

  @Override
  protected void drawText(final JComponent c,
                          final Graphics2D g,
                          final JCheckBox b,
                          final FontMetrics fm,
                          final Rectangle textRect,
                          final String text) {
    //text
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

  @Override
  protected void paintCheckSign(final Graphics2D g, final boolean enabled, final int w, final int h) {
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    g.setStroke(new BasicStroke(1 * JBUI.scale(2.0f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    g.setPaint(getCheckSignColor(enabled, true));
    final int x1 = JBUI.scale(3);
    final int y1 = JBUI.scale(7);
    final int x2 = JBUI.scale(7);
    final int y2 = JBUI.scale(10);
    final int x3 = w - JBUI.scale(2);
    final int y3 = JBUI.scale(3);

    g.drawLine(x1, y1, x2, y2);
    g.drawLine(x2, y2, x3, y3);
    g.setPaint(getCheckSignColor(enabled, true));
  }

  @Override
  protected Color getInactiveFillColor() {
    return getColor("inactiveFillColor", Gray._40.withAlpha(180));
  }

  @Override
  protected Color getBorderColor1(final boolean enabled, final boolean selected) {
    return enabled ? getColor("borderColor1", Gray._120.withAlpha(0x5a), selected)
                   : getColor("disabledBorderColor1", Gray._120.withAlpha(90), selected);
  }

  @Override
  protected Color getBorderColor2(final boolean enabled, final boolean selected) {
    return enabled ? getColor("borderColor2", Gray._105.withAlpha(90), selected)
                   : getColor("disabledBorderColor2", Gray._105.withAlpha(90), selected);
  }

  @Override
  protected Color getBackgroundColor1(final boolean enabled, final boolean selected) {
    return getColor("backgroundColor1", Gray._110, selected);
  }

  @Override
  protected Color getBackgroundColor2(final boolean enabled, final boolean selected) {
    return getColor("backgroundColor2", Gray._95, selected);
  }

  @Override
  protected Color getCheckSignColor(final boolean enabled, final boolean selected) {
    return enabled ? getColor("checkSignColor", Gray._170, selected)
                   : getColor("checkSignColorDisabled", Gray._120, selected);
  }

  @Override
  protected Color getShadowColor(final boolean enabled, final boolean selected) {
    return enabled ? getColor("shadowColor", Gray._30, selected)
                   : getColor("shadowColorDisabled", Gray._60, selected);
  }

  @Override
  protected Color getFocusedBackgroundColor1(final boolean armed, final boolean selected) {
    return armed ? getColor("focusedArmed.backgroundColor1", Gray._100, selected)
                 : getColor("focused.backgroundColor1", Gray._120, selected);
  }

  @Override
  protected Color getFocusedBackgroundColor2(final boolean armed, final boolean selected) {
    return armed ? getColor("focusedArmed.backgroundColor2", Gray._55, selected)
                 : getColor("focused.backgroundColor2", Gray._75, selected);
  }
}
