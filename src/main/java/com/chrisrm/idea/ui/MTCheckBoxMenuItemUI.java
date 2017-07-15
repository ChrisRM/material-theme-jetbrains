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
import com.intellij.ui.Gray;
import com.intellij.util.ui.UIUtil;
import sun.swing.MenuItemLayoutHelper;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTCheckBoxMenuItemUI extends MTMenuItemUIBase {

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(final JComponent c) {
        return new MTCheckBoxMenuItemUI();
    }

    @Override
    public Dimension getPreferredSize(final JComponent c) {
        return super.getPreferredSize(c);
    }

    protected String getPropertyPrefix() {
        return "CheckBoxMenuItem";
    }

    @Override
    protected void paintCheckIcon(final Graphics g2,
                                  final MenuItemLayoutHelper lh,
                                  final MenuItemLayoutHelper.LayoutResult lr,
                                  final Color holdc,
                                  final Color foreground) {
        final Graphics2D g = (Graphics2D) g2;
        final GraphicsConfig config = new GraphicsConfig(g);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

        g.translate(lr.getCheckRect().x - 2, lr.getCheckRect().y);

        final int sz = 13;
        g.setPaint(new GradientPaint(sz / 2, 1, Gray._110, sz / 2, sz, Gray._95));
        g.fillRoundRect(0, 0, sz, sz - 1, 4, 4);

        g.setPaint(new GradientPaint(sz / 2, 1, Gray._120.withAlpha(0x5a), sz / 2, sz, Gray._105.withAlpha(90)));
        g.drawRoundRect(0, (UIUtil.isUnderDarcula() ? 1 : 0), sz, sz - 1, 4, 4);

        g.setPaint(Gray._40.withAlpha(180));
        g.drawRoundRect(0, 0, sz, sz - 1, 4, 4);


        if (lh.getMenuItem().isSelected()) {
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.setPaint(Gray._30);
            g.drawLine(4, 7, 7, 10);
            g.drawLine(7, 10, sz, 2);
            g.setPaint(Gray._170);
            g.drawLine(4, 5, 7, 8);
            g.drawLine(7, 8, sz, 0);
        }

        g.translate(-lr.getCheckRect().x + 2, -lr.getCheckRect().y);
        config.restore();
        g.setColor(foreground);
    }
}
