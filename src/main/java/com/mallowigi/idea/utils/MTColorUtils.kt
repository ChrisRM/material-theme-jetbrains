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

package com.mallowigi.idea.utils;

import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * Color Utils!
 */
public enum MTColorUtils {
  DEFAULT;

  private static final int HC_FG_TONES = 4;
  private static final int HC_BG_TONES = 2;

  @SuppressWarnings({"MethodWithMultipleReturnPoints",
      "OverlyBroadCatchBlock"})
  public static ColorUIResource parseColor(@Nullable final String value) {
    if (value != null && value.length() == 8) {
      final Color color = ColorUtil.fromHex(value.substring(0, 6));

      try {
        final int alpha = Integer.parseInt(value.substring(6, 8), 16);
        return new ColorUIResource(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
      } catch (final Exception ignore) {
      }

      return new ColorUIResource(new Color(color.getRed(), color.getGreen(), color.getBlue(), 1));
    }

    final Color color = ColorUtil.fromHex(value, Color.GRAY);
    return new ColorUIResource(new Color(color.getRed(), color.getGreen(), color.getBlue()));
  }

  @SuppressWarnings("unused")
  private static String contrastifyForeground(final boolean dark, final String colorString, final boolean isNotHighContrast) {
    if (isNotHighContrast) {
      return colorString;
    }

    return dark ?
           ColorUtil.toHex(ColorUtil.brighter(ColorUtil.fromHex(colorString), HC_FG_TONES)) :
           ColorUtil.toHex(ColorUtil.darker(ColorUtil.fromHex(colorString), HC_FG_TONES));
  }

  @SuppressWarnings("unused")
  public static Color contrastifyForeground(final boolean isDark, final Color color, final boolean isNotHighContrast) {
    if (isNotHighContrast) {
      return color;
    }
    final int alpha = color.getAlpha() / 255;

    return ColorUtil.withAlpha(new ColorUIResource(isDark ?
                                                   ColorUtil.brighter(color, HC_FG_TONES) :
                                                   ColorUtil.darker(color, HC_FG_TONES)), alpha);
  }

  @SuppressWarnings("unused")
  public static String contrastifyBackground(final boolean isDark, final String colorString, final boolean isNotHighContrast) {
    if (isNotHighContrast) {
      return colorString;
    }

    return isDark ?
           ColorUtil.toHex(ColorUtil.darker(ColorUtil.fromHex(colorString), HC_BG_TONES)) :
           ColorUtil.toHex(ColorUtil.brighter(ColorUtil.fromHex(colorString), HC_BG_TONES));
  }

  @SuppressWarnings("unused")
  public static Color contrastifyBackground(final boolean isDark, final ColorUIResource color, final boolean isNotHighContrast) {
    if (isNotHighContrast) {
      return color;
    }
    final int alpha = color.getAlpha() / 255;

    return ColorUtil.withAlpha(new ColorUIResource(isDark ?
                                                   ColorUtil.darker(color, HC_BG_TONES) :
                                                   ColorUtil.brighter(color, HC_BG_TONES)), alpha);
  }
}
