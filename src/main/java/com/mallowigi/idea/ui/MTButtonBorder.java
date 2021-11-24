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

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonPainter;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.util.ui.*;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

@SuppressWarnings("OverlyComplexMethod")
public final class MTButtonBorder extends DarculaButtonPainter {
  private static final JBValue HELP_BUTTON_DIAMETER = new JBValue.Float(22);

  @SuppressWarnings("StandardVariableNames")
  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    if (MTConfig.getInstance().isBorderedButtons()) {
      if (((JComponent) c).getClientProperty(MTUI.Button.getNoBorder()) == Boolean.TRUE) {
        return;
      }
      paintOutlinedBorder(c, g, x, y, width, height);
    }
  }

  public static void paintFocusOval(final Graphics2D g, final float x, final float y, final float width, final float height) {
    DarculaUIUtil.Outline.focus.setGraphicsColor(g, true);
    final float borderWidth = JBUI.scale(1);
    final float padding = 0;

    final float blw = borderWidth + padding;
    final Path2D shape = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    shape.append(new Ellipse2D.Float(x - blw, y - blw, width + blw, height + blw), false);
    shape.append(new Ellipse2D.Float(x, y, width, height), false);
    g.fill(shape);
  }

  @SuppressWarnings({
    "MagicNumber",
    "MethodWithTooManyParameters",
    "OverlyLongMethod"})
  private void paintOutlinedBorder(final Component component,
                                   final Graphics g,
                                   final int x,
                                   final int y,
                                   final int width,
                                   final int height) {
    final Graphics2D g2 = (Graphics2D) g.create();

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
        MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

      final boolean isSmallComboButton = DarculaButtonUI.isSmallVariant(component);
      final int helpButtonDiameter = HELP_BUTTON_DIAMETER.get();
      final float borderWidth = JBUI.scale(1);
      final float padding = 0;
      float arc = DarculaButtonUI.isTag(component) ?
                  height - padding * 2 - borderWidth * 2 :
                  DarculaUIUtil.BUTTON_ARC.getFloat();

      final Rectangle r = new Rectangle(x, y, width, height);
      final boolean paintComboFocus = isSmallComboButton && component.isFocusable() && component.hasFocus();

      // Paint combobox buttons
      if (paintComboFocus) { // a11y support
        g2.setColor(JBUI.CurrentTheme.Focus.focusColor());

        final Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);
        border.append(new RoundRectangle2D.Float(r.x, r.y, r.width, r.height, arc + borderWidth, arc + borderWidth), false);
        border.append(new RoundRectangle2D.Float(
          r.x + borderWidth * 2,
          r.y + borderWidth * 2,
          r.width - borderWidth * 4,
          r.height - borderWidth * 4,
          arc,
          arc
        ), false);
        g2.fill(border);
      }
      if (!DarculaButtonUI.isGotItButton(component)) {
        JBInsets.removeFrom(r, JBUI.insets(1));
      }

      g2.translate(r.x, r.y);

      // Paint focused borders
      if (!isSmallComboButton) {
        if (component.hasFocus()) {
          if (UIUtil.isHelpButton(component)) {
            paintFocusOval(
              g2,
              (r.width - helpButtonDiameter) / 2.0f,
              (r.height - helpButtonDiameter) / 2.0f,
              helpButtonDiameter,
              helpButtonDiameter
            );
          } else if (DarculaButtonUI.isTag(component)) {
            DarculaUIUtil.paintTag(g2, r.width, r.height, component.hasFocus(), DarculaUIUtil.computeOutlineFor(component));
          } else {
            final DarculaUIUtil.Outline type = DarculaButtonUI.isDefaultButton((JComponent) component) ?
                                               DarculaUIUtil.Outline.defaultButton :
                                               DarculaUIUtil.Outline.focus;
            DarculaUIUtil.paintOutlineBorder(g2, r.width, r.height, arc, true, true, type);
          }
        } else if (DarculaButtonUI.isTag(component)) {
          DarculaUIUtil.paintTag(g2, r.width, r.height, component.hasFocus(), DarculaUIUtil.computeOutlineFor(component));
        }
      }

      g2.setPaint(getBorderPaint(component));

      if (UIUtil.isHelpButton(component)) {
        g2.draw(new Ellipse2D.Float(
          (r.width - helpButtonDiameter) / 2.0f,
          (r.height - helpButtonDiameter) / 2.0f,
          helpButtonDiameter,
          helpButtonDiameter)
        );
      } else if (!paintComboFocus) {
        // Paint padding and border
        final Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);
        border.append(new RoundRectangle2D.Float(
          padding,
          padding,
          r.width - padding * 2,
          r.height - padding * 2,
          arc,
          arc
        ), false);

        arc = arc > borderWidth ? arc - borderWidth : 0.0f;
        border.append(new RoundRectangle2D.Float(
          padding + borderWidth,
          padding + borderWidth,
          r.width - (padding + borderWidth) * 2,
          r.height - (padding + borderWidth) * 2,
          arc,
          arc
        ), false);

        g2.fill(border);
      }
    } finally {
      g2.dispose();
    }
  }

  @SuppressWarnings("MagicNumber")
  @Override
  protected int getOffset() {
    return 16;
  }
}
