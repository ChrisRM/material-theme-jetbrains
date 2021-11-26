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
 * Dracula theme
 *
 */
class DraculaTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "dracula"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 7

  override val themeName: String
    get() = "Dracula (Material)"

  override val themeIcon: String
    get() = iconPrefix("dracula")

  override val backgroundImage: String
    get() = "walls/dracula.svg"

  override val themeColorScheme: String
    get() = "Dracula (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x282A36)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0xF8F8F2)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x6272A4)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x44475A)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0x8BE9FD)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x393C4B)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x282A36)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x6272A4)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x191A21)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x44475A)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x21222C)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x44475a)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x5044475A, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x1D2228)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0xFF79C5)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x313341)
}
