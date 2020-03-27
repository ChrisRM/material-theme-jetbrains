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

package com.mallowigi.idea.ui;

import com.mallowigi.idea.utils.MTUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;

public final class MTSliderUI extends MetalSliderUI {
  private int trackLeft = 0;
  private int trackTop = 0;
  private int trackRight;
  private int trackBottom;
  private int fillTop;
  private int fillLeft;
  private int fillBottom;
  private int fillRight;

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTSliderUI();
  }

  private static boolean isLeftToRight(final Component component) {
    return component.getComponentOrientation().isLeftToRight();
  }

  /**
   * Add Slider properties
   */
  private static void addSliderProperties() {
    UIManager.put("Slider.trackWidth", 7);
    UIManager.put("Slider.majorTickLength", 6);
    UIManager.put("Slider.horizontalThumbIcon", null);
    UIManager.put("Slider.verticalThumbIcon", null);
  }

  @Override
  public void installUI(final JComponent c) {
    addSliderProperties();
    super.installUI(c);
    getTrackCoordinates();
    getThumbAndFillCoordinates();
    // Allow free sliding
    scrollListener.setScrollByBlock(false);
  }

  @Override
  public void paintThumb(final Graphics g) {
    final Rectangle knobBounds = thumbRect;
    final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);

    g.setColor(MTUI.Slider.getThumbColor());
    g.translate(knobBounds.x, knobBounds.y);
    g.fillOval(0, 0, knobBounds.width, knobBounds.height);
    g.translate(-knobBounds.x, -knobBounds.y);
    config.restore();
  }

  @Override
  public void paintTrack(final Graphics g) {
    g.translate(trackRect.x, trackRect.y);
    getTrackCoordinates();
    getThumbAndFillCoordinates();
    drawTrack(g);
    drawFill(g);
    g.translate(-trackRect.x, -trackRect.y);
  }

  @Override
  protected Dimension getThumbSize() {
    return new Dimension(JBUI.scale(16), JBUI.scale(16));
  }

  @Override
  public int getTickLength() {
    return 0;
  }

  private void drawFill(final Graphics g) {
    if (slider.isEnabled()) {
      g.setColor(slider.getBackground());
      g.drawLine(fillLeft, fillTop, fillRight, fillTop);
      g.drawLine(fillLeft, fillTop, fillLeft, fillBottom);

      g.setColor(MTUI.Slider.getThumbColor());
    } else {
      g.setColor(MetalLookAndFeel.getControlShadow());
    }
    g.fillRect(fillLeft, fillTop, fillRight - fillLeft, fillBottom - fillTop);
  }

  private void drawTrack(final Graphics g) {
    if (slider.isEnabled()) {
      g.setColor(MTUI.Slider.getTrackColor());
    } else {
      g.setColor(MTUI.Slider.getTrackDisabledColor());
    }
    g.fillRect(trackLeft, trackTop, (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);
  }

  /**
   * Compute the thumb and fill coordinates
   */
  private void getThumbAndFillCoordinates() {
    int middleOfThumb;

    if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
      middleOfThumb = thumbRect.x + (thumbRect.width / 2);
      middleOfThumb -= trackRect.x; // To compensate for the g.translate()
      fillTop = trackTop;
      fillBottom = trackBottom - 1;

      if (drawInverted()) {
        fillLeft = middleOfThumb;
        fillRight = trackRight - 1;
      } else {
        fillLeft = trackLeft;
        fillRight = middleOfThumb;
      }
    } else {
      middleOfThumb = thumbRect.y + (thumbRect.height / 2);
      middleOfThumb -= trackRect.y; // To compensate for the g.translate()
      fillLeft = trackLeft;
      fillRight = trackRight - 1;

      if (drawInverted()) {
        fillTop = trackTop;
        fillBottom = middleOfThumb;
      } else {
        fillTop = middleOfThumb;
        fillBottom = trackBottom - 1;
      }
    }
  }

  /**
   * Compute the track coordinates
   */
  private void getTrackCoordinates() {
    final boolean leftToRight = isLeftToRight(slider);
    if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
      trackBottom = (trackRect.height - 1) - getThumbOverhang();
      trackTop = trackBottom - (getTrackWidth() - 1);
      trackRight = trackRect.width - 1;
    } else {
      if (leftToRight) {
        trackLeft = (trackRect.width - getThumbOverhang()) - getTrackWidth();
        trackRight = (trackRect.width - getThumbOverhang()) - 1;
      } else {
        trackLeft = getThumbOverhang();
        trackRight = getThumbOverhang() + getTrackWidth() - 1;
      }
      trackBottom = trackRect.height - 1;
    }
  }
}
