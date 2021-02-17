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

import com.intellij.ide.ui.laf.darcula.ui.DarculaRadioButtonUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.EmptyIcon;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTRadioButtonUI extends DarculaRadioButtonUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTRadioButtonUI();
  }

  @Override
  public Icon getDefaultIcon() {
    return (EmptyIcon.create(20)).asUIResource();
  }

  @SuppressWarnings("BadOddness")
  @Override
  protected void paintIcon(final JComponent c,
                           final Graphics2D g,
                           final Rectangle viewRect,
                           final Rectangle iconRect) {
    final Insets insets = c.getInsets();
    viewRect.x += insets.left;
    viewRect.y += insets.top;
    viewRect.width -= (insets.right + viewRect.x);
    viewRect.height -= (insets.bottom + viewRect.y);

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

    if (selected) {
      final boolean enabled = c.isEnabled();
      g.setColor(MTUI.Radio.getSelectedColor(enabled));

      // draw outer border
      g.drawOval(JBUI.scale(2), JBUI.scale(1), w, h);

      // draw dot
      final int yOff = JBUI.scale(1);
      g.fillOval(w / 2 - rad / 2, h / 2 - rad / 2 - yOff, rad + JBUI.scale(4), rad + JBUI.scale(4));
    } else {
      // paint border
      g.setPaint(MTUI.Radio.getBorderColor());
      g.drawOval(JBUI.scale(2), JBUI.scale(1) + 1, w - 1, h - 1);
    }
    config.restore();
    g.translate(-x, -y);
  }

  @Override
  protected Color getFocusColor() {
    return MTUI.Radio.getFocusColor();
  }

  private static void paintOvalRing(final Graphics2D g, final int w, final int h) {
    g.setColor(MTUI.Radio.getFocusColor());
    g.fillOval(-JBUI.scale(2), -3, w + 8, h + 8);
  }
}
