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

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import sun.swing.MenuItemLayoutHelper;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTRadioButtonMenuItemUI extends MTMenuItemUIBase {
  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(final JComponent c) {
    return new MTRadioButtonMenuItemUI();
  }

  protected String getPropertyPrefix() {
    return "RadioButtonMenuItem";
  }

  @Override
  protected void paintCheckIcon(final Graphics g2,
                                final MenuItemLayoutHelper lh,
                                final MenuItemLayoutHelper.LayoutResult lr,
                                final Color holdc,
                                final Color foreground) {
    Graphics2D g = (Graphics2D) g2;
    final GraphicsConfig config = new GraphicsConfig(g);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

    g.translate(lr.getCheckRect().x - 1, lr.getCheckRect().y - 1);

    int rad = 5;

    final int x = 0;
    final int y = 0;
    final int w = 13;
    final int h = 13;

    g.translate(x, y);

    //setup AA for lines
    Color bg = lh.getMenuItem().getBackground();
    g.setPaint(new GradientPaint(0, 0, ColorUtil.shift(bg, 1.5),
        0, 16, ColorUtil.shift(bg, 1.2)));

    g.fillOval(0, 1, w - 1, h - 1);

    g.setPaint(new GradientPaint(w / 2, 1, Gray._160.withAlpha(90), w / 2, h, Gray._100.withAlpha(90)));
    g.drawOval(0, 2, w - 1, h - 1);

    g.setPaint(Gray._40.withAlpha(200));
    g.drawOval(0, 1, w - 1, h - 1);

    if (lh.getMenuItem().isSelected()) {
      final boolean enabled = lh.getMenuItem().isEnabled();
      g.setColor(UIManager.getColor(enabled ? "RadioButton.darcula.selectionEnabledShadowColor" : "RadioButton.darcula" +
          ".selectionDisabledShadowColor"));
      g.fillOval((w - rad) / 2, h / 2, rad, rad);
      g.setColor(UIManager.getColor(enabled ? "RadioButton.darcula.selectionEnabledColor" : "RadioButton.darcula.selectionDisabledColor"));
      g.fillOval((w - rad) / 2, h / 2 - 1, rad, rad);
    }
    config.restore();
    g.translate(-x, -y);


    g.translate(-lr.getCheckRect().x + 1, -lr.getCheckRect().y + 1);
    config.restore();
  }
}
