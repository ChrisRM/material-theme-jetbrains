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
package com.mallowigi.idea.themes.themes

import java.awt.Color
import javax.swing.plaf.ColorUIResource

/**
 * Solarized light theme
 *
 */
class SolarizedLightTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "solarized.light"

  override val isThemeDark: Boolean
    get() = false

  override val order: Int
    get() = 10

  override val themeName: String
    get() = "Solarized Light (Material)"

  override val themeIcon: String?
    get() = iconPrefix("solarized_light")

  override val backgroundImage: String
    get() = "walls/solarlight.svg"

  override val themeColorScheme: String
    get() = "Solarized Light (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0xfdf6e3)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0x586e75)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x93a1a1)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0xe8dcb6)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0x002b36)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0xd8d4c4)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0xF6F0DE)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0xC9CCC3)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0xeee8d5)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0xd1cbb8)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0xedead9)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0xd1cbb8)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(-0x4f17234a, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0xEDE8D4)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0xd33682)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0xE3DCC9)

}
