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

import com.intellij.openapi.actionSystem.ActionButtonComponent;
import com.intellij.openapi.actionSystem.impl.IdeaActionButtonLook;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.impl.SquareStripeButton;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.utils.MTUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public final class MTActionButtonLook extends IdeaActionButtonLook {

  private static final int INDICATOR_THICKNESS = 3;

  @Override
  public void paintBackground(final Graphics g, final JComponent component, final int state) {
    if (state != ActionButtonComponent.NORMAL) {
      final Rectangle rect = new Rectangle(component.getSize());
      final Insets insets = component.getInsets();
      JBInsets.removeFrom(rect, insets);

      final Color color = MTUI.ActionButton.getHoverBackground();
      if (component instanceof SquareStripeButton) {
        paintStripeButtonBackground(g, (SquareStripeButton) component, rect, color, state);
      } else {
        paintLookBackground(g, rect, color);
      }
    }
  }

  @Override
  public void paintBorder(final Graphics g, final JComponent component, final int state) {
    if (state != ActionButtonComponent.NORMAL) {
      final Rectangle rect = new Rectangle(component.getSize());
      final Insets insets = component.getInsets();
      JBInsets.removeFrom(rect, insets);

      final Color color = MTUI.ActionButton.getHoverBorderColor();
      if (component instanceof SquareStripeButton) {
        paintStripeButtonBorder(g, (SquareStripeButton) component, rect, color, state);
      } else {
        paintLookBorder(g, rect, color);
      }
    }
  }

  private static void paintStripeButtonBackground(@NotNull final Graphics g,
                                                  final SquareStripeButton component,
                                                  @NotNull final Rectangle rect,
                                                  @NotNull final Color color,
                                                  final int state) {
    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      g2.translate(rect.x, rect.y);
      g2.setColor(color);

      if (state == ActionButtonComponent.POPPED) {
        g2.fillOval(0, 0, rect.width, rect.height);
      } else if (state == ActionButtonComponent.PUSHED) {
        g2.fillRect(0, 0, rect.width, rect.height);
      }
    } finally {
      g2.dispose();
    }
  }

  private static void paintStripeButtonBorder(@NotNull final Graphics g,
                                              final SquareStripeButton component,
                                              @NotNull final Rectangle rect,
                                              @NotNull final Color color,
                                              final int state) {
    if (state == ActionButtonComponent.PUSHED) {
      final Graphics2D g2 = (Graphics2D) g.create();
      try {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2.setColor(MTUI.Panel.getAccentColor());

        final ToolWindowAnchor anchor = component.getButton().getToolWindow().getLargeStripeAnchor();
        if (anchor == ToolWindowAnchor.LEFT || anchor == ToolWindowAnchor.BOTTOM) {
          g2.fillRect(0, 0, INDICATOR_THICKNESS, rect.height);
        } else if (anchor == ToolWindowAnchor.RIGHT) {
          g2.fillRect(rect.width - INDICATOR_THICKNESS, 0, INDICATOR_THICKNESS, rect.height);
        }

      } finally {
        g2.dispose();
      }
    }
  }

  @Override
  public void paintLookBackground(@NotNull final Graphics g, @NotNull final Rectangle rect, @NotNull final Color color) {
    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      g2.translate(rect.x, rect.y);
      g2.setColor(color);

      if (rect.width == rect.height) {
        g2.fillOval(0, 0, rect.width, rect.height);
      } else {
        g2.fillRoundRect(0, 0, rect.width, rect.height, JBUI.scale(4), JBUI.scale(4));
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  public void paintLookBorder(@NotNull final Graphics g, @NotNull final Rectangle rect, @NotNull final Color color) {
    final Graphics2D g2 = (Graphics2D) g.create();
    try {

      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      g2.translate(rect.x, rect.y);
      g2.setColor(color);

      if (rect.width == rect.height) {
        g2.fillOval(0, 0, rect.height - JBUI.scale(1), rect.height - JBUI.scale(1));
      } else {
        g2.fillRoundRect(0, 0, rect.width, rect.height, JBUI.scale(4), JBUI.scale(4));
      }
    } finally {
      g2.dispose();
    }
  }
}
