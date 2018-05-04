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

import com.chrisrm.idea.MTConfig;
import com.intellij.ide.ui.laf.darcula.ui.DarculaProgressBarUI;
import com.intellij.openapi.progress.util.ColorProgressBar;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * @author Konstantin Bulenkov
 */
public final class MTProgressBarUI extends DarculaProgressBarUI {

  private static final Color REMAINDER_COLOR = new JBColor(() -> (Color) (UIUtil.isUnderWin10LookAndFeel() ? Gray.xCC : new JBColor(Gray
      .xC4, Gray.x69)));
  private static final Color RED = new JBColor(0xE53935, 0xFF5370);
  private static final Color RED_LIGHT = new JBColor(0xFF5370, 0xf07178);

  private static final Color GREEN = new JBColor(0x91B859, 0xC3E88D);
  private static final Color GREEN_LIGHT = new JBColor(0xFFB62C, 0xFFCB6B);

  public static ComponentUI createUI(final JComponent c) {
    return new MTProgressBarUI();
  }

  private Shape getShapedRect(final float x, final float y, final float w, final float h, final float ar) {
    final boolean flatEnds = UIUtil.isUnderWin10LookAndFeel() || progressBar.getClientProperty("ProgressBar.flatEnds") == Boolean.TRUE;
    return flatEnds ? new Rectangle2D.Float(x, y, w, h) : new RoundRectangle2D.Float(x, y, w, h, ar, ar);
  }

  private static boolean isSimplified() {
    // TODO improve user experience based on System.properties
    // Avoid using Services directly to make UI code independent.
    return false;
  }

  @Override
  protected void paintIndeterminate(final Graphics g, final JComponent c) {
    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      final Rectangle r = new Rectangle(progressBar.getSize());
      if (c.isOpaque()) {
        g2.setColor(c.getParent().getBackground());
        g2.fill(r);
      }

      final Insets i = progressBar.getInsets();
      JBInsets.removeFrom(r, i);
      final int orientation = progressBar.getOrientation();

      // Use foreground color as a reference, don't use it directly. This is done for compatibility reason.
      // Colors are hardcoded in UI delegates by design. If more colors are needed contact designers.
      final Color startColor;
      final Color endColor;
      final Color foreground = progressBar.getForeground();
      if (foreground == ColorProgressBar.RED) {
        startColor = RED;
        endColor = RED_LIGHT;
      } else if (foreground == ColorProgressBar.GREEN) {
        startColor = GREEN;
        endColor = GREEN_LIGHT;
      } else {
        startColor = getStartColor();
        endColor = getEndColor();
      }

      final int pHeight = progressBar.getPreferredSize().height;
      final int pWidth = progressBar.getPreferredSize().width;

      float yOffset = r.y + (r.height - pHeight) / 2.0f;
      float xOffset = r.x + (r.width - pWidth) / 2.0f;

      if (isSimplified()) {
        final Color[] ca = {startColor,
            endColor};
        int idx = 0;
        final int delta = JBUI.scale(10);
        if (orientation == SwingConstants.HORIZONTAL) {
          for (float offset = r.x; offset - r.x < r.width; offset += delta) {
            g2.setPaint(ca[(getAnimationIndex() + idx++) % 2]);
            g2.fill(new Rectangle2D.Float(offset, yOffset, delta, pHeight));
          }
        } else {
          for (float offset = r.y; offset - r.y < r.height; offset += delta) {
            g2.setPaint(ca[(getAnimationIndex() + idx++) % 2]);
            g2.fill(new Rectangle2D.Float(xOffset, offset, delta, pWidth));
          }
        }
      } else {
        final Shape shape;
        final int step = JBUI.scale(6);
        if (orientation == SwingConstants.HORIZONTAL) {
          shape = getShapedRect(r.x, yOffset, r.width, pHeight, pHeight);
          yOffset = r.y + pHeight / 2.0f;
          g2.setPaint(new GradientPaint(r.x + getAnimationIndex() * step * 2, yOffset, startColor,
              r.x + getFrameCount() * step + getAnimationIndex() * step * 2, yOffset, endColor, true));
        } else {
          shape = getShapedRect(xOffset, r.y, pWidth, r.height, pWidth);
          xOffset = r.x + pWidth / 2.0f;
          g2.setPaint(new GradientPaint(xOffset, r.y + getAnimationIndex() * step * 2, startColor,
              xOffset, r.y + getFrameCount() * step + getAnimationIndex() * step * 2, endColor, true));
        }
        g2.fill(shape);
      }

      // Paint text
      if (progressBar.isStringPainted()) {
        if (progressBar.getOrientation() == SwingConstants.HORIZONTAL) {
          paintString(g, i.left, i.top, r.width, r.height, boxRect.x, boxRect.width);
        } else {
          paintString(g, i.left, i.top, r.width, r.height, boxRect.y, boxRect.height);
        }
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  protected void paintDeterminate(final Graphics g, final JComponent c) {
    final Graphics2D g2 = (Graphics2D) g.create();

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      final Rectangle r = new Rectangle(progressBar.getSize());
      if (c.isOpaque()) {
        g2.setColor(c.getParent().getBackground());
        g2.fill(r);
      }

      final Insets i = progressBar.getInsets();
      JBInsets.removeFrom(r, i);
      final int amountFull = getAmountFull(i, r.width, r.height);

      final Shape fullShape;
      final Shape coloredShape;
      final int orientation = progressBar.getOrientation();
      if (orientation == SwingConstants.HORIZONTAL) {
        final int pHeight = progressBar.getPreferredSize().height;
        final float yOffset = r.y + (r.height - pHeight) / 2.0f;

        fullShape = getShapedRect(r.x, yOffset, r.width, pHeight, pHeight);
        coloredShape = getShapedRect(r.x, yOffset, amountFull, pHeight, pHeight);
      } else {
        final int pWidth = progressBar.getPreferredSize().width;
        final float xOffset = r.x + (r.width - pWidth) / 2.0f;

        fullShape = getShapedRect(xOffset, r.y, pWidth, r.height, pWidth);
        coloredShape = getShapedRect(xOffset, r.y, pWidth, amountFull, pWidth);
      }
      g2.setColor(getRemainderColor());
      g2.fill(fullShape);

      // Use foreground color as a reference, don't use it directly. This is done for compatibility reason.
      // Colors are hardcoded in UI delegates by design. If more colors are needed contact designers.
      final Color foreground = progressBar.getForeground();
      if (foreground == ColorProgressBar.RED) {
        g2.setColor(RED);
      } else if (foreground == ColorProgressBar.GREEN) {
        g2.setColor(GREEN);
      } else {
        g2.setColor(getFinishedColor());
      }
      g2.fill(coloredShape);

      // Paint text
      if (progressBar.isStringPainted()) {
        paintString(g, i.left, i.top, r.width, r.height, amountFull, i);
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  protected Color getStartColor() {
    return ColorUtil.fromHex(MTConfig.getInstance().getAccentColor()).brighter().brighter();
  }

  @Override
  protected Color getEndColor() {
    return ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
  }

  @Override
  protected Color getFinishedColor() {
    return ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
  }

  @Override
  protected Color getRemainderColor() {
    return REMAINDER_COLOR;
  }

  private void paintString(final Graphics g,
                           final int x,
                           final int y,
                           final int w,
                           final int h,
                           final int fillStart,
                           final int amountFull) {
    if (!(g instanceof Graphics2D)) {
      return;
    }

    final Graphics2D g2 = (Graphics2D) g;
    final String progressString = progressBar.getString();
    g2.setFont(progressBar.getFont());
    Point renderLocation = getStringPlacement(g2, progressString,
        x, y, w, h);
    final Rectangle oldClip = g2.getClipBounds();

    if (progressBar.getOrientation() == SwingConstants.HORIZONTAL) {
      g2.setColor(getSelectionBackground());
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
      g2.setColor(getSelectionForeground());
      g2.clipRect(fillStart, y, amountFull, h);
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
    } else { // VERTICAL
      g2.setColor(getSelectionBackground());
      final AffineTransform rotate =
          AffineTransform.getRotateInstance(Math.PI / 2);
      g2.setFont(progressBar.getFont().deriveFont(rotate));
      renderLocation = getStringPlacement(g2, progressString,
          x, y, w, h);
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
      g2.setColor(getSelectionForeground());
      g2.clipRect(x, fillStart, w, amountFull);
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
    }
    g2.setClip(oldClip);
  }
}
