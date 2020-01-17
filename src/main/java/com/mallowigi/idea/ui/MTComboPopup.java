/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.utils.MTUI;
import com.intellij.openapi.ui.ComboBoxWithWidePopup;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.MacUIUtil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboPopup;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import static com.intellij.ide.ui.laf.darcula.DarculaUIUtil.BW;

final class MTComboPopup extends BasicComboPopup {

  MTComboPopup(@SuppressWarnings("unused") final MTComboBoxUI mtComboBoxUI, final JComboBox combo) {
    super(combo);
  }

  @Override
  public void show(final Component invoker, final int x, final int y) {
    if (comboBox instanceof ComboBoxWithWidePopup) {
      final Dimension popupSize = comboBox.getSize();
      final int minPopupWidth = ((ComboBoxWithWidePopup) comboBox).getMinimumPopupWidth();
      final Insets insets = getInsets();

      popupSize.width = Math.max(popupSize.width, minPopupWidth);
      popupSize.setSize(popupSize.width - (insets.right + insets.left), getPopupHeightForRowCount(comboBox.getMaximumRowCount()));

      scroller.setMaximumSize(popupSize);
      scroller.setPreferredSize(popupSize);
      scroller.setMinimumSize(popupSize);

      list.revalidate();
    }

    // Adjust popup location to fit screen - if so we do not translate
    final Point p = adjustPopupLocationToFitScreen(x, y);
    if (p.y != y || MTConfig.getInstance().isCompactDropdowns()) {
      // We also don't translate if option is set
      super.show(invoker, x, y);
    } else {
      // Move popup at the top of the combobox
      super.show(invoker, x, y - comboBox.getHeight());
    }
  }

  private static void doPaint(final Graphics2D g, final int width, final int height) {
    float bw = BW.get();
    final float lw = JBUI.scale(0.5f);

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
        MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

    final float outerArc = bw;
    final Path2D outerRect = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    outerRect.moveTo(width - outerArc, 0);
    outerRect.quadTo(width, 0, width, outerArc);
    outerRect.lineTo(width, height - outerArc);
    outerRect.quadTo(width, height, width - outerArc, height);
    outerRect.lineTo(outerArc, height);
    outerRect.quadTo(0, height, 0, height - outerArc);
    outerRect.lineTo(0, outerArc);
    outerRect.quadTo(0, 0, outerArc, 0);
    outerRect.closePath();

    bw += lw;
    final Path2D innerRect = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    innerRect.moveTo(width - outerArc, bw);
    innerRect.quadTo(width - bw, bw, width - bw, outerArc);
    innerRect.lineTo(width - bw, height - outerArc);
    innerRect.quadTo(width - bw, height - bw, width - outerArc, height - bw);
    innerRect.lineTo(outerArc, height - bw);
    innerRect.quadTo(bw, height - bw, bw, height - outerArc);
    innerRect.lineTo(bw, outerArc);
    innerRect.quadTo(bw, bw, outerArc, bw);
    innerRect.closePath();

    final Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    path.append(outerRect, false);
    path.append(innerRect, false);
    g.fill(path);
  }

  @Override
  protected void paintBorder(final Graphics g) {
    final float bw = 6;
    final boolean isEnabled = comboBox != null && comboBox.isEnabled();
    final Color borderColor = MTUI.TextField.getBorderColor(isEnabled);

    final Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

    final Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    border.append(new RoundRectangle2D.Float(bw, bw, getWidth() - bw * 2, getHeight() - bw * 2, 0, 0), false);

    g2.setColor(borderColor);
    doPaint(g2, getWidth(), getHeight());
  }

  @SuppressWarnings("MethodOverridesInaccessibleMethodOfSuper")
  private Point adjustPopupLocationToFitScreen(final int xPosition, final int yPosition) {
    final Point popupLocation = new Point(xPosition, yPosition);

    // Get screen bounds
    final Rectangle scrBounds;
    final GraphicsConfiguration gc = getCurrentGraphicsConfiguration(popupLocation);
    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    // If we have GraphicsConfiguration use it to get screen bounds
    // If we don't have GraphicsConfiguration use primary screen
    scrBounds = gc != null ? gc.getBounds() : new Rectangle(toolkit.getScreenSize());

    // Calculate the screen size that popup should fit
    final Dimension popupSize = getPreferredSize();
    final long popupRightX = popupLocation.x + (long) popupSize.width;
    final long popupBottomY = popupLocation.y + (long) popupSize.height;
    final int scrWidth = scrBounds.width;
    final int scrHeight = scrBounds.height;

    final int scrRightX = scrBounds.x + scrWidth;
    final int scrBottomY = scrBounds.y + scrHeight;

    // Ensure that popup menu fits the screen
    if (popupRightX > scrRightX) {
      popupLocation.x = scrRightX - popupSize.width;
    }

    if (popupBottomY > scrBottomY) {
      popupLocation.y = scrBottomY - popupSize.height;
    }

    if (popupLocation.x < scrBounds.x) {
      popupLocation.x = scrBounds.x;
    }

    if (popupLocation.y < scrBounds.y) {
      popupLocation.y = scrBounds.y;
    }

    return popupLocation;
  }

  /**
   * Tries to find GraphicsConfiguration
   * that contains the mouse cursor position.
   * Can return null.
   */
  @SuppressWarnings({"MethodOverridesInaccessibleMethodOfSuper",
      "BreakStatement"})
  private GraphicsConfiguration getCurrentGraphicsConfiguration(final Point popupLocation) {
    GraphicsConfiguration gc = null;
    final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final GraphicsDevice[] gd = ge.getScreenDevices();
    for (final GraphicsDevice aGd : gd) {
      if (aGd.getType() == GraphicsDevice.TYPE_RASTER_SCREEN) {
        final GraphicsConfiguration dgc = aGd.getDefaultConfiguration();
        if (dgc.getBounds().contains(popupLocation)) {
          gc = dgc;
          break;
        }
      }
    }
    // If not found and we have invoker, ask invoker about his gc
    if (gc == null && getInvoker() != null) {
      gc = getInvoker().getGraphicsConfiguration();
    }
    return gc;
  }
}
