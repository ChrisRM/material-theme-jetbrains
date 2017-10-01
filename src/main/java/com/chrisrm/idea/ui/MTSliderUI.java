package com.chrisrm.idea.ui;

import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import java.awt.*;

public final class MTSliderUI extends MetalSliderUI {

  protected final int TICK_BUFFER = 4;
  protected boolean filledSlider = false;
  // NOTE: these next five variables are currently unused.
  protected static Color thumbColor;
  protected static Color highlightColor;
  protected static Color darkShadowColor;
  protected static int trackWidth;
  protected static int tickLength;
  private int safeLength;

  public static ComponentUI createUI(JComponent c) {
    return new MTSliderUI();
  }

  public void installUI(JComponent c) {
    fixMacSlider();
    super.installUI(c);

    trackWidth = (Integer) ObjectUtils.notNull(UIManager.get("Slider.trackWidth"), 7);
    tickLength = safeLength = (Integer) ObjectUtils.notNull(UIManager.get("Slider.majorTickLength"), 6);

    thumbColor = getThumbColor();
    highlightColor = getSliderHighlightColor();
    darkShadowColor = getDarkShadowColor();

    scrollListener.setScrollByBlock(false);

    filledSlider = true;
  }

  private void fixMacSlider() {
    UIManager.put("Slider.trackWidth", 7);
    UIManager.put("Slider.majorTickLength", 6);
    UIManager.put("Slider.horizontalThumbIcon", null);
    UIManager.put("Slider.verticalThumbIcon", null);
  }

  public void paintThumb(Graphics g) {
    Rectangle knobBounds = thumbRect;
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

  private Color getSliderHighlightColor() {
    return MTUiUtils.getColor(UIManager.getColor("Slider.highlight"),
                              MetalLookAndFeel.getControlHighlight(),
                              MetalLookAndFeel.getControlHighlight());
  }

  private Color getDarkShadowColor() {
    return MTUiUtils.getColor(UIManager.getColor("Slider.darkShadow"),
                              MetalLookAndFeel.getControlDarkShadow(),
                              MetalLookAndFeel.getControlDarkShadow());
  }

  /**
   * Returns a rectangle enclosing the track that will be painted.
   */
  private Rectangle getPaintTrackRect() {
    int trackLeft = 0,
        trackRight,
        trackTop = 0,
        trackBottom;

    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      trackBottom = (trackRect.height - 1) - getThumbOverhang();
      trackTop = trackBottom - (getTrackWidth() - 1);
      trackRight = trackRect.width - 1;
    }
    else {
      if (isLeftToRight(slider)) {
        trackLeft = (trackRect.width - getThumbOverhang()) -
                    getTrackWidth();
        trackRight = (trackRect.width - getThumbOverhang()) - 1;
      }
      else {
        trackLeft = getThumbOverhang();
        trackRight = getThumbOverhang() + getTrackWidth() - 1;
      }
      trackBottom = trackRect.height - 1;
    }
    return new Rectangle(trackRect.x + trackLeft, trackRect.y + trackTop,
                         trackRight - trackLeft, trackBottom - trackTop);
  }

  private boolean isLeftToRight(Component c) {
    return c.getComponentOrientation().isLeftToRight();
  }

  public void paintTrack(Graphics g) {
    boolean leftToRight = isLeftToRight(slider);

    g.translate(trackRect.x, trackRect.y);

    int trackLeft = 0;
    int trackTop = 0;
    int trackRight;
    int trackBottom;

    // Draw the track
    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      trackBottom = (trackRect.height - 1) - getThumbOverhang();
      trackTop = trackBottom - (getTrackWidth() - 1);
      trackRight = trackRect.width - 1;
    }
    else {
      if (leftToRight) {
        trackLeft = (trackRect.width - getThumbOverhang()) -
                    getTrackWidth();
        trackRight = (trackRect.width - getThumbOverhang()) - 1;
      }
      else {
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
    }
    else {
      g.setColor(getTrackDisabledColor());
      g.fillRect(trackLeft, trackTop,
                 (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);
    }

    // Draw the fill
    if (filledSlider) {
      int middleOfThumb;
      int fillTop;
      int fillLeft;
      int fillBottom;
      int fillRight;

      if (slider.getOrientation() == JSlider.HORIZONTAL) {
        middleOfThumb = thumbRect.x + (thumbRect.width / 2);
        middleOfThumb -= trackRect.x; // To compensate for the g.translate()
        fillTop = !slider.isEnabled() ? trackTop : trackTop;
        fillBottom = !slider.isEnabled() ? trackBottom - 1 : trackBottom - 1;

        if (!drawInverted()) {
          fillLeft = !slider.isEnabled() ? trackLeft : trackLeft;
          fillRight = middleOfThumb;
        }
        else {
          fillLeft = middleOfThumb;
          fillRight = !slider.isEnabled() ? trackRight - 1 : trackRight - 1;
        }
      }
      else {
        middleOfThumb = thumbRect.y + (thumbRect.height / 2);
        middleOfThumb -= trackRect.y; // To compensate for the g.translate()
        fillLeft = !slider.isEnabled() ? trackLeft : trackLeft;
        fillRight = !slider.isEnabled() ? trackRight - 1 : trackRight - 1;

        if (!drawInverted()) {
          fillTop = middleOfThumb;
          fillBottom = !slider.isEnabled() ? trackBottom - 1 : trackBottom - 1;
        }
        else {
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
      }
      else {
        g.setColor(MetalLookAndFeel.getControlShadow());
        g.fillRect(fillLeft, fillTop, fillRight - fillLeft, fillBottom - fillTop);
      }
    }

    g.translate(-trackRect.x, -trackRect.y);
  }

  private void oceanPaintTrack(Graphics g) {
    boolean leftToRight = isLeftToRight(slider);
    boolean drawInverted = drawInverted();
    Color sliderAltTrackColor = (Color) UIManager.get(
        "Slider.altTrackColor");

    // Translate to the origin of the painting rectangle
    Rectangle paintRect = getPaintTrackRect();
    g.translate(paintRect.x, paintRect.y);

    // Width and height of the painting rectangle.
    int w = paintRect.width;
    int h = paintRect.height;

    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      int middleOfThumb = thumbRect.x + thumbRect.width / 2 - paintRect.x;

      if (slider.isEnabled()) {
        int fillMinX;
        int fillMaxX;

        if (middleOfThumb > 0) {
          g.setColor(drawInverted ? MetalLookAndFeel.getControlDarkShadow() :
                     MetalLookAndFeel.getPrimaryControlDarkShadow());

          g.drawRect(0, 0, middleOfThumb - 1, h - 1);
        }

        if (middleOfThumb < w) {
          g.setColor(drawInverted ? MetalLookAndFeel.getPrimaryControlDarkShadow() :
                     MetalLookAndFeel.getControlDarkShadow());

          g.drawRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
        }

        if (filledSlider) {
          g.setColor(MetalLookAndFeel.getPrimaryControlShadow());
          if (drawInverted) {
            fillMinX = middleOfThumb;
            fillMaxX = w - 2;
            g.drawLine(1, 1, middleOfThumb, 1);
          }
          else {
            fillMinX = 1;
            fillMaxX = middleOfThumb;
            g.drawLine(middleOfThumb, 1, w - 1, 1);
          }
          if (h == 6) {
            g.setColor(MetalLookAndFeel.getWhite());
            g.drawLine(fillMinX, 1, fillMaxX, 1);
            g.setColor(sliderAltTrackColor);
            g.drawLine(fillMinX, 2, fillMaxX, 2);
            g.setColor(MetalLookAndFeel.getControlShadow());
            g.drawLine(fillMinX, 3, fillMaxX, 3);
            g.setColor(MetalLookAndFeel.getPrimaryControlShadow());
            g.drawLine(fillMinX, 4, fillMaxX, 4);
          }
        }
      }
      else {
        g.setColor(MetalLookAndFeel.getControlShadow());

        if (middleOfThumb > 0) {
          if (!drawInverted && filledSlider) {
            g.fillRect(0, 0, middleOfThumb - 1, h - 1);
          }
          else {
            g.drawRect(0, 0, middleOfThumb - 1, h - 1);
          }
        }

        if (middleOfThumb < w) {
          if (drawInverted && filledSlider) {
            g.fillRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
          }
          else {
            g.drawRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
          }
        }
      }
    }
    else {
      int middleOfThumb = thumbRect.y + (thumbRect.height / 2) - paintRect.y;

      if (slider.isEnabled()) {
        int fillMinY;
        int fillMaxY;

        if (middleOfThumb > 0) {
          g.setColor(drawInverted ? MetalLookAndFeel.getPrimaryControlDarkShadow() :
                     MetalLookAndFeel.getControlDarkShadow());

          g.drawRect(0, 0, w - 1, middleOfThumb - 1);
        }

        if (middleOfThumb < h) {
          g.setColor(drawInverted ? MetalLookAndFeel.getControlDarkShadow() :
                     MetalLookAndFeel.getPrimaryControlDarkShadow());

          g.drawRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
        }

        if (filledSlider) {
          g.setColor(MetalLookAndFeel.getPrimaryControlShadow());
          if (drawInverted()) {
            fillMinY = 1;
            fillMaxY = middleOfThumb;
            if (leftToRight) {
              g.drawLine(1, middleOfThumb, 1, h - 1);
            }
            else {
              g.drawLine(w - 2, middleOfThumb, w - 2, h - 1);
            }
          }
          else {
            fillMinY = middleOfThumb;
            fillMaxY = h - 2;
            if (leftToRight) {
              g.drawLine(1, 1, 1, middleOfThumb);
            }
            else {
              g.drawLine(w - 2, 1, w - 2, middleOfThumb);
            }
          }
          if (w == 6) {
            g.setColor(leftToRight ? MetalLookAndFeel.getWhite() : MetalLookAndFeel.getPrimaryControlShadow());
            g.drawLine(1, fillMinY, 1, fillMaxY);
            g.setColor(leftToRight ? sliderAltTrackColor : MetalLookAndFeel.getControlShadow());
            g.drawLine(2, fillMinY, 2, fillMaxY);
            g.setColor(leftToRight ? MetalLookAndFeel.getControlShadow() : sliderAltTrackColor);
            g.drawLine(3, fillMinY, 3, fillMaxY);
            g.setColor(leftToRight ? MetalLookAndFeel.getPrimaryControlShadow() : MetalLookAndFeel.getWhite());
            g.drawLine(4, fillMinY, 4, fillMaxY);
          }
        }
      }
      else {
        g.setColor(MetalLookAndFeel.getControlShadow());

        if (middleOfThumb > 0) {
          if (drawInverted && filledSlider) {
            g.fillRect(0, 0, w - 1, middleOfThumb - 1);
          }
          else {
            g.drawRect(0, 0, w - 1, middleOfThumb - 1);
          }
        }

        if (middleOfThumb < h) {
          if (!drawInverted && filledSlider) {
            g.fillRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
          }
          else {
            g.drawRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
          }
        }
      }
    }

    g.translate(-paintRect.x, -paintRect.y);
  }

  protected Dimension getThumbSize() {
    Dimension size = new Dimension();

    if (slider.getOrientation() == JSlider.VERTICAL) {
      size.width = JBUI.scale(16);
      size.height = JBUI.scale(16);
    }
    else {
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
  public int getTickLength() {
    return 0;
  }

  /**
   * Returns the shorter dimension of the track.
   */
  protected int getTrackWidth() {
    // This strange calculation is here to keep the
    // track in proportion to the thumb.
    final double kIdealTrackWidth = 7.0;
    final double kIdealThumbHeight = 16.0;
    final double kWidthScalar = kIdealTrackWidth / kIdealThumbHeight;

    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      return (int) (kWidthScalar * thumbRect.height);
    }
    else {
      return (int) (kWidthScalar * thumbRect.width);
    }
  }

  /**
   * Returns the longer dimension of the slide bar.  (The slide bar is only the
   * part that runs directly under the thumb)
   */
  protected int getTrackLength() {
    if (slider.getOrientation() == JSlider.HORIZONTAL) {
      return trackRect.width;
    }
    return trackRect.height;
  }

  /**
   * Returns the amount that the thumb goes past the slide bar.
   */
  protected int getThumbOverhang() {
    return (int) (getThumbSize().getHeight() - getTrackWidth()) / 2;
  }

  protected void scrollDueToClickInTrack(int dir) {
    scrollByUnit(dir);
  }

  protected void paintMinorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());
    g.drawLine(x, TICK_BUFFER, x, TICK_BUFFER + (safeLength / 2));
  }

  protected void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());
    g.drawLine(x, TICK_BUFFER, x, TICK_BUFFER + (safeLength - 1));
  }

  protected void paintMinorTickForVertSlider(Graphics g, Rectangle tickBounds, int y) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());

    if (isLeftToRight(slider)) {
      g.drawLine(TICK_BUFFER, y, TICK_BUFFER + (safeLength / 2), y);
    }
    else {
      g.drawLine(0, y, safeLength / 2, y);
    }
  }

  protected void paintMajorTickForVertSlider(Graphics g, Rectangle tickBounds, int y) {
    g.setColor(slider.isEnabled() ? slider.getForeground() : MetalLookAndFeel.getControlShadow());

    if (isLeftToRight(slider)) {
      g.drawLine(TICK_BUFFER, y, TICK_BUFFER + safeLength, y);
    }
    else {
      g.drawLine(0, y, safeLength, y);
    }
  }
}
