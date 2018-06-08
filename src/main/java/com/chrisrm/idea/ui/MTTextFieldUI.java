/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */
package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;
import java.awt.*;

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

  protected Rectangle getDrawingRect() {
    final JTextComponent c = getComponent();
    final JBInsets i = JBInsets.create(c.getInsets());
    final int x = i.right - JBUI.scale(4) - JBUI.scale(16);
    final int y = i.top - JBUI.scale(3);
    final int w = c.getWidth() - i.width() + JBUI.scale(16 * 2 + 7 * 2 - 5);
    int h = c.getBounds().height - i.height() + JBUI.scale(4 * 2 - 3);
    if (h % 2 == 1) {
      h++;
    }
    return new Rectangle(x, y, w, h);
  }

  @Override
  protected void paintBackground(final Graphics graphics) {
    final Graphics2D g = (Graphics2D) graphics;
    final JTextComponent c = getComponent();
    final Container parent = c.getParent();
    final Rectangle r = getDrawingRect();
    if (c.isOpaque() && parent != null) {
      g.setColor(parent.getBackground());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final GraphicsConfig config = new GraphicsConfig(g);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

    final Border border = c.getBorder();
    if (border instanceof MTTextBorder) {
      paintDarculaBackground(g, c, border);
    } else {
      super.paintBackground(g);
    }
    config.restore();
  }

  protected void paintDarculaBackground(final Graphics2D g, final JTextComponent component, final Border border) {
    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle r = new Rectangle(component.getSize());
    JBInsets.removeFrom(r, JBUI.insets(1));

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
          MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

      g2.translate(r.x, r.y);

      final float arc = isSearchField(component) ? JBUI.scale(6f) : 0.0f;
      final float bw = 0f;

      if (component.isEnabled() && component.isEditable()) {
        g2.setColor(component.getBackground());
      }

      g2.fill(new RoundRectangle2D.Float(bw, bw, r.width - bw * 2, r.height - bw * 2, arc, arc));
    } finally {
      g2.dispose();
    }
  }

  @Override
  protected Icon getSearchIcon(final boolean hovered, final boolean clickable) {
    return IconCache.getIcon(clickable ? "searchWithHistory" : "search");
  }

  /**
   * @return a gap between the search icon and the editable area
   */
  @Override
  protected int getSearchIconGap() {
    return scale(2);
  }

  @Override
  protected Icon getClearIcon(final boolean hovered, final boolean clickable) {
    return !clickable ? null : IconCache.getIcon("clear");
  }

  @Override
  protected int getClearIconPreferredSpace() {
    return super.getClearIconPreferredSpace() - getClearIconGap();
  }

  @Override
  protected int getClearIconGap() {
    return scale(2);
  }

  @Override
  protected void paintSafely(final Graphics g) {
    paintBackground(g);
    super.paintSafely(g);
  }
}
