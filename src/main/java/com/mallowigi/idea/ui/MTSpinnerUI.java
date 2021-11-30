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

import com.intellij.ide.ui.laf.darcula.ui.DarculaSpinnerUI;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.JBValue;
import com.intellij.util.ui.MacUIUtil;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

/**
 * @author Konstantin Bulenkov
 */
public final class MTSpinnerUI extends DarculaSpinnerUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTSpinnerUI();
  }

  @Override
  public void paint(final Graphics g, final JComponent c) {
    super.paint(g, c);
    final Border border = spinner.getBorder();
    if (border != null) {
      border.paintBorder(c, g, 0, 0, spinner.getWidth(), spinner.getHeight());
    }
  }

  @Override
  protected JButton createButton(final int direction, final String name) {
    final JButton button = createArrow(direction);
    button.setName(name);
    button.setBorder(JBUI.Borders.empty());
    if (direction == SwingConstants.NORTH) {
      installNextButtonListeners(button);
    } else {
      installPreviousButtonListeners(button);
    }
    return button;
  }

  @Override
  protected void paintArrowButton(final Graphics g,
                                  final BasicArrowButton button,
                                  final int direction) {
    final int y = direction == SwingConstants.NORTH ? button.getHeight() - 4 : 4;
    button.paintTriangle(g, (button.getWidth() - 8) / 2 - 1, y, 0, direction, spinner.isEnabled());
  }

  @SuppressWarnings("MethodOverridesInaccessibleMethodOfSuper")
  private JButton createArrow(final int direction) {
    final MTSpinnerArrowButton arrowButton = new MTSpinnerArrowButton(direction);
    arrowButton.addMouseListener(new SpinnerButtonHighlightedAdapter(arrowButton));
    arrowButton.setInheritsPopupMenu(true);
    return arrowButton;
  }

  @SuppressWarnings("WeakerAccess")
  private static final class SpinnerButtonHighlightedAdapter extends MouseAdapter {
    private final MTSpinnerArrowButton arrowButton;

    SpinnerButtonHighlightedAdapter(final MTSpinnerArrowButton arrowButton) {
      this.arrowButton = arrowButton;
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
      arrowButton.setIsHovered(true);
    }

    @Override
    public void mouseExited(final MouseEvent e) {
      arrowButton.setIsHovered(false);
    }
  }

  @SuppressWarnings("WeakerAccess")
  private final class MTSpinnerArrowButton extends BasicArrowButton {
    private boolean isHovered;

    MTSpinnerArrowButton(final int direction) {
      super(direction);
      isHovered = false;
    }

    void setIsHovered(final boolean hovered) {
      isHovered = hovered;
    }

    @Override
    public void paint(final Graphics g) {
      paintArrowButton(g, this, direction);
    }

    @Override
    public boolean isOpaque() {
      return false;
    }

    /**
     * Paint the rectangle surrounding the arrows
     *
     * @param bw border width
     */
    private Shape getInnerShape(final float bw) {
      final Path2D shape = new Path2D.Float();
      final int w = getWidth() - JBUI.scale(1);
      final int h = getHeight() - JBUI.scale(1);

      switch (direction) {
        case SOUTH:
          shape.moveTo(0, 0);
          shape.lineTo(w - bw, 0);
          shape.lineTo(w - bw, h - bw);
          shape.lineTo(0, h - bw);
          shape.closePath();
          break;

        case NORTH:
          shape.moveTo(0, bw);
          shape.lineTo(w - bw, bw);
          shape.lineTo(w - bw, h);
          shape.lineTo(0, h);
          shape.closePath();
          break;
        default:
          break;
      }
      return shape;
    }

    private Shape getArrowShape() {
      final Path2D arrow = new Path2D.Float();
      final int aw = new JBValue.Float(9).get();
      final int ah = new JBValue.Float(5).get();
      final int bw = JBUI.scale(2);

      switch (direction) {
        case SOUTH:
          paintSouthArrow(arrow, aw, ah, bw);
          break;
        case NORTH:
          paintNorthArrow(arrow, aw, ah, bw);
          break;
        default:
          break;
      }

      return arrow;
    }

    private void paintNorthArrow(final Path2D arrow, final int aw, final int ah, final int bw) {
      arrow.moveTo(0, 0);
      arrow.lineTo(aw / 2.0, -ah);
      arrow.lineTo(aw, 0);
      arrow.quadTo(aw, 0, aw - bw, 0);
      arrow.lineTo(aw / 2.0, -ah + bw);
      arrow.lineTo(bw, 0);
      arrow.quadTo(bw, 0, 0, 0);
      arrow.closePath();
    }

    private void paintSouthArrow(final Path2D arrow, final int aw, final int ah, final int bw) {
      arrow.moveTo(0, 0);
      arrow.lineTo(aw / 2.0, ah);
      arrow.lineTo(aw, 0);
      arrow.quadTo(aw, 0, aw - bw, 0);
      arrow.lineTo(aw / 2.0, ah - bw);
      arrow.lineTo(bw, 0);
      arrow.quadTo(bw, 0, 0, 0);
      arrow.closePath();
    }

    @Override
    public void paintTriangle(final Graphics g,
                              final int x,
                              final int y,
                              final int size,
                              final int direction,
                              final boolean isEnabled) {
      final Graphics2D g2 = (Graphics2D) g.create();
      try {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
          MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

        final int bw = JBUI.scale(2);

        // Paint the arrow buttons background
        g2.setColor(MTUI.Spinner.getArrowButtonBackgroundColor(isEnabled, true));
        g2.fill(getInnerShape(bw));

        // Paint arrow
        g2.translate(x, y);
        g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2.setColor(MTUI.Spinner.getArrowButtonForegroundColor(isEnabled, isHovered));
        g2.fill(getArrowShape());
      } finally {
        g2.dispose();
      }
    }
  }
}
