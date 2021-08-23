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
package com.mallowigi.idea.utils

import com.intellij.ui.ColorUtil
import java.awt.Color
import javax.swing.plaf.ColorUIResource

/**
 * Color Utils!
 */
object MTColorUtils {
  private const val HC_FG_TONES = 4
  private const val HC_BG_TONES = 2

  @JvmStatic
  fun parseColor(value: String?): ColorUIResource {
    if (value != null && value.length == 8) {
      val color = ColorUtil.fromHex(value.substring(0, 6))
      try {
        val alpha = value.substring(6, 8).toInt(16)
        return ColorUIResource(Color(color.red, color.green, color.blue, alpha))
      } catch (ignore: Exception) {
        // ignored
      }
      return ColorUIResource(Color(color.red, color.green, color.blue, 1))
    }

    val color = ColorUtil.fromHex(value, Color.GRAY)
    return ColorUIResource(Color(color!!.red, color.green, color.blue))
  }

  @JvmStatic
  private fun contrastifyForeground(dark: Boolean, colorString: String, isNotHighContrast: Boolean): String {
    if (isNotHighContrast) return colorString

    return when {
      dark -> ColorUtil.toHex(ColorUtil.brighter(ColorUtil.fromHex(colorString), HC_FG_TONES))
      else -> ColorUtil.toHex(ColorUtil.darker(ColorUtil.fromHex(colorString), HC_FG_TONES))
    }
  }

  @JvmStatic
  fun contrastifyForeground(isDark: Boolean, color: Color, isNotHighContrast: Boolean): Color {
    if (isNotHighContrast) return color

    val alpha = color.alpha / 255
    val contrastColor = when {
      isDark -> ColorUtil.brighter(color, HC_FG_TONES)
      else -> ColorUtil.darker(color, HC_FG_TONES)
    }

    return ColorUtil.withAlpha(ColorUIResource(contrastColor), alpha.toDouble())
  }

  @JvmStatic
  fun contrastifyBackground(isDark: Boolean, colorString: String, isNotHighContrast: Boolean): String {
    if (isNotHighContrast) return colorString
    val contrastColor = when {
      isDark -> ColorUtil.toHex(ColorUtil.darker(ColorUtil.fromHex(colorString), HC_BG_TONES))
      else -> ColorUtil.toHex(ColorUtil.brighter(ColorUtil.fromHex(colorString), HC_BG_TONES))
    }
    return contrastColor
  }

  @JvmStatic
  fun contrastifyBackground(isDark: Boolean, color: ColorUIResource, isNotHighContrast: Boolean): Color {
    if (isNotHighContrast) return color
    val alpha = color.alpha / 255
    val contrastColor = when {
      isDark -> ColorUtil.darker(color, HC_BG_TONES)
      else -> ColorUtil.brighter(color, HC_BG_TONES)
    }

    return ColorUtil.withAlpha(ColorUIResource(contrastColor), alpha.toDouble())
  }
}
