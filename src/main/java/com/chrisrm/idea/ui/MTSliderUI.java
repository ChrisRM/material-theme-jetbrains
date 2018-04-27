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

import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;

public final class MTSliderUI extends MetalSliderUI {

  private final int tickBuffer = 4;
  private boolean filledSlider = false;
  private int safeLength;

  public static ComponentUI createUI(final JComponent c) {
    return new MTSliderUI();
  }

  @Override
  public void installUI(final JComponent c) {
    fixMacSlider();
    super.installUI(c);

    scrollListener.setScrollByBlock(false);

    filledSlider = true;
  }

  private void fixMacSlider() {
    UIManager.put("Slider.trackWidth", 7);
    UIManager.put("Slider.majorTickLength", 6);
    UIManager.put("Slider.horizontalThumbIcon", null);
    UIManager.put("Slider.verticalThumbIcon", null);
  }

  @Override
  public void paintThumb(final Graphics g) {
    final Rectangle knobBounds = thumbRect;
    final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);

    g.setColor(getThumbColor());
    g.translate(knobBounds.x, knobBounds.y);
    //    getVertThumbIcon().paintIcon(slider, g, 0, 0);
    g.fillOval(0, 0, knobBounds.width, knobBounds.height);
    g.translate(-knobBounds.x, -knobBounds.y);
    config.restore();
  }

  private Color getThumbColor() {
    return MTUiUtils.getColor(UIManager.getColor("Slider.thumb"),
        MetalLookAndFeel.getControlShadow(),
        MetalLookAndFeel.getControlShadow());
  }

  private Color getTrackColor() {
    return MTUiUtils.getColor(UIManager.getColor("Slider.track"),
        MetalLookAndFeel.getControlHighlight(),
        MetalLookAndFeel.getControlHighlight());
  }

  private Color getTrackDisabledColor() {
    return MTUiUtils.getColor(UIManager.getColor("Slider.trackDisabled"),
        MetalLookAndFeel.getControlShadow(),
        MetalLookAndFeel.getControlShadow());
  }

  private boolean isLeftToRight(final Component c) {
    return c.getComponentOrientation().isLeftToRight();
  }

  @Override
  public void paintTrack(final Graphics g) {
    final boolean leftToRight = isLeftToRight(slider);

    g.translate(trackRect.x, trackRect.y);

    int trackLeft = 0;
    int trackTop = 0;
    final int trackRight;
    final int trackBottom;

    // Draw the track
    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      trackBottom = (trackRect.height - 1) - getThumbOverhang();
      trackTop = trackBottom - (getTrackWidth() - 1);
      trackRight = trackRect.width - 1;
    } else {
      if (leftToRight) {
        trackLeft = (trackRect.width - getThumbOverhang()) -
            getTrackWidth();
        trackRight = (trackRect.width - getThumbOverhang()) - 1;
      } else {
        trackLeft = getThumbOverhang();
        trackRight = getThumbOverhang() + getTrackWidth() - 1;
      }
      trackBottom = trackRect.height - 1;
    }

    if (slider.isEnabled()) {
      g.setColor(getTrackColor());
      g.fillRect(trackLeft, trackTop,
          (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);

      //      g.setColor(Color.RED);
      //      g.drawLine(trackLeft + 1, trackBottom, trackRight, trackBottom);
      //      g.drawLine(trackRight, trackTop + 1, trackRight, trackBottom);
      //
      //      g.setColor(Color.BLUE);
      //      g.drawLine(trackLeft + 1, trackTop + 1, trackRight - 2, trackTop + 1);
      //      g.drawLine(trackLeft + 1, trackTop + 1, trackLeft + 1, trackBottom - 2);
    } else {
      g.setColor(getTrackDisabledColor());
      g.fillRect(trackLeft, trackTop,
          (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);
    }

    // Draw the fill
    if (filledSlider) {
      int middleOfThumb;
      final int fillTop;
      final int fillLeft;
      final int fillBottom;
      final int fillRight;

      if (slider.getOrientation() == JSlider.HORIZONTAL) {
        middleOfThumb = thumbRect.x + (thumbRect.width / 2);
        middleOfThumb -= trackRect.x; // To compensate for the g.translate()
        fillTop = !slider.isEnabled() ? trackTop : trackTop;
        fillBottom = !slider.isEnabled() ? trackBottom - 1 : trackBottom - 1;

        if (!drawInverted()) {
          fillLeft = !slider.isEnabled() ? trackLeft : trackLeft;
          fillRight = middleOfThumb;
        } else {
          fillLeft = middleOfThumb;
          fillRight = !slider.isEnabled() ? trackRight - 1 : trackRight - 1;
        }
      } else {
        middleOfThumb = thumbRect.y + (thumbRect.height / 2);
        middleOfThumb -= trackRect.y; // To compensate for the g.translate()
        fillLeft = !slider.isEnabled() ? trackLeft : trackLeft;
        fillRight = !slider.isEnabled() ? trackRight - 1 : trackRight - 1;

        if (!drawInverted()) {
          fillTop = middleOfThumb;
          fillBottom = !slider.isEnabled() ? trackBottom - 1 : trackBottom - 1;
        } else {
          fillTop = !slider.isEnabled() ? trackTop : trackTop;
          fillBottom = middleOfThumb;
        }
      }

      if (slider.isEnabled()) {
        g.setColor(slider.getBackground());
        g.drawLine(fillLeft, fillTop, fillRight, fillTop);
        g.drawLine(fillLeft, fillTop, fillLeft, fillBottom);

        g.setColor(getThumbColor());
        g.fillRect(fillLeft, fillTop,
            fillRight - fillLeft, fillBottom - fillTop);
      } else {
        g.setColor(MetalLookAndFeel.getControlShadow());
        g.fillRect(fillLeft, fillTop, fillRight - fillLeft, fillBottom - fillTop);
      }
    }

    g.translate(-trackRect.x, -trackRect.y);
  }

  @Override
  protected Dimension getThumbSize() {
    final Dimension size = new Dimension();

    if (slider.getOrientation() == JSlider.VERTICAL) {
      size.width = JBUI.scale(16);
      size.height = JBUI.scale(16);
    } else {
      size.width = JBUI.scale(16);
      size.height = JBUI.scale(16);
    }

    return size;
  }

  /**
   * Gets the height of the tick area for horizontal sliders and the width of the
   * tick area for vertical sliders.  BasicSliderUI uses the returned value to
   * determine the tick area rectangle.
   */
  @Override
  public int getTickLength() {
    return 0;
  }

  /**
   * Returns the shorter dimension of the track.
   */
  @Override
  protected int getTrackWidth() {
    // This strange calculation is here to keep the
    // track in proportion to the thumb.
    final double kIdealTrackWidth = 7.0;
    final double kIdealThumbHeight = 16.0;
    final double kWidthScalar = kIdealTrackWidth / kIdealThumbHeight;

    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      return (int) (kWidthScalar * thumbRect.height);
    } else {
      return (int) (kWidthScalar * thumbRect.width);
    }
  }

  /**
   * Returns the longer dimension of the slide bar.  (The slide bar is only the
   * part that runs directly under the thumb)
   */
  @Override
  protected int getTrackLength() {
    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      return trackRect.width;
    }
    return trackRect.height;
  }

  /**
   * Returns the amount that the thumb goes past the slide bar.
   */
  @Override
  protected int getThumbOverhang() {
    return (int) (getThumbSize().getHeight() - getTrackWidth()) / 2;
  }

  @Override
  protected void scrollDueToClickInTrack(final int dir) {
    scrollByUnit(dir);
  }

  @Override
  protected void paintMinorTickForHorizSlider(final Graphics g, final Rectangle tickBounds, final int x) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());
    g.drawLine(x, tickBuffer, x, tickBuffer + (safeLength / 2));
  }

  @Override
  protected void paintMajorTickForHorizSlider(final Graphics g, final Rectangle tickBounds, final int x) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());
    g.drawLine(x, tickBuffer, x, tickBuffer + (safeLength - 1));
  }

  @Override
  protected void paintMinorTickForVertSlider(final Graphics g, final Rectangle tickBounds, final int y) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());

    if (isLeftToRight(slider)) {
      g.drawLine(tickBuffer, y, tickBuffer + (safeLength / 2), y);
    } else {
      g.drawLine(0, y, safeLength / 2, y);
    }
  }

  @Override
  protected void paintMajorTickForVertSlider(final Graphics g, final Rectangle tickBounds, final int y) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());

    if (isLeftToRight(slider)) {
      g.drawLine(tickBuffer, y, tickBuffer + safeLength, y);
    } else {
      g.drawLine(0, y, safeLength, y);
    }
  }
}
