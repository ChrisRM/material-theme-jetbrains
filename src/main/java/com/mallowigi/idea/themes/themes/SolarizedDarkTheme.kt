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
 * Solarized dark theme
 *
 */
class SolarizedDarkTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "solarized.dark"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 9

  override val themeName: String
    get() = "Solarized Dark (Material)"

  override val themeIcon: String?
    get() = iconPrefix("solarized_dark")

  override val backgroundImage: String
    get() = "walls/solardark.svg"

  override val themeColorScheme: String
    get() = "Solarized Dark (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x002B36)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0x839496)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x586e75)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x2E4C52)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x073642)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x003745)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x2E5861)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x00252E)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x003946)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x0D3640)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x005a6f)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(-0x7fd1b3ae, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x2E4C52)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0xd33682)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x083F4D)

}
