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

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.themes.MTThemeable;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.components.OnOffButton;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBDimension;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;

public class MTOnOffButtonUI extends BasicToggleButtonUI {
  private static final Dimension TOGGLE_SIZE = new JBDimension(18, 18);
  private static final Dimension BUTTON_SIZE = new JBDimension(40, 14);
  private static final Border BUTTON_BORDER = JBUI.Borders.empty(1, 6);

  public static ComponentUI createUI(final JComponent c) {
    c.setBorder(BUTTON_BORDER);
    return new MTOnOffButtonUI();
  }

  /**
   * The size of the switch
   *
   * @param c
   */
  @Override
  public Dimension getPreferredSize(final JComponent c) {
    final Dimension size = new Dimension(BUTTON_SIZE);
    JBInsets.addTo(size, BUTTON_BORDER.getBorderInsets(c));
    return size;
  }

  /**
   * Maximum size
   *
   * @param c
   */
  @Override
  public Dimension getMaximumSize(final JComponent c) {
    return getPreferredSize(c);
  }

  /**
   * Minimum size
   *
   * @param c
   */
  @Override
  public Dimension getMinimumSize(final JComponent c) {
    return getPreferredSize(c);
  }

  /**
   * Paint component
   *
   * @param g
   * @param c
   */
  @Override
  public void paint(final Graphics g, final JComponent c) {
    if (!(c instanceof OnOffButton)) {
      return;
    }

    final MTThemeable theme = MTConfig.getInstance().getSelectedTheme().getTheme();
    final Color bgColor = ColorUtil.fromHex(theme.getSelectionBackground());
    final Color thumbColor = bgColor.brighter().brighter();
    final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
    final Color selectedAccentColor = accentColor.darker().darker();

    final OnOffButton b = (OnOffButton) c;
    final Graphics2D g2 = (Graphics2D) g.create();
    final GraphicsConfig config = GraphicsUtil.setupAAPainting(g2);

    try {
      final Insets i = c.getInsets();
      final Point origin = new Point((c.getWidth() - BUTTON_SIZE.width) / 2 + i.left,
                                     (c.getHeight() - BUTTON_SIZE.height) / 2 + i.top);

      // Background
      g2.setColor(b.isSelected() ? selectedAccentColor : bgColor);
      g2.fillRoundRect(origin.x, origin.y, BUTTON_SIZE.width, BUTTON_SIZE.height, 16, 16);

      // Fill
      g2.setColor(b.isSelected() ? accentColor : thumbColor);

      final Point location = new Point(
          (b.isSelected() ? JBUI.scale(24) : JBUI.scale(-2)) + origin.x,
          JBUI.scale(-2) + origin.y);
      g2.fillOval(location.x, location.y, TOGGLE_SIZE.width, TOGGLE_SIZE.height);

      config.restore();
    } finally {
      g2.dispose();
    }
  }
}
