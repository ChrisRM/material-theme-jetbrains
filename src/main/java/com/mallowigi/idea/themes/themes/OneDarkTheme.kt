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
 * Atom One dark theme
 *
 */
class OneDarkTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "one.dark"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 7

  override val themeName: String
    get() = "Atom One Dark (Material)"

  override val themeIcon: String?
    get() = iconPrefix("one_dark")

  override val backgroundImage: String
    get() = "walls/onedark.svg"

  override val themeColorScheme: String
    get() = "Atom One Dark (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x282C34)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0x979FAD)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x979FAD)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x4D515D)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x3A3F4B)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x2F333D)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x6B727D)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x21252B)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x383E49)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x282C34)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x383D48)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(-0x7fc5c0b5, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x282C34)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0x2979ff)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x3c4150)

}
