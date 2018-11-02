/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.utils;

import com.intellij.ui.ColorUtil;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * Color Utils!
 */
public final class MTColorUtils {

  private MTColorUtils() {
  }

  /**
   * Parse a color from a value
   *
   * @param value string color
   * @return Color
   */
  public static Color parseColor(final String value) {
    if (value != null && value.length() == 8) {
      final Color color = ColorUtil.fromHex(value.substring(0, 6));
      try {
        final int alpha = Integer.parseInt(value.substring(6, 8), 16);
        return new ColorUIResource(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
      } catch (final Exception ignore) {
      }
      return null;
    }
    return ColorUtil.fromHex(value, null);
  }

}
