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

import javax.swing.plaf.ColorUIResource

/**
 * Monokai Pro theme
 *
 */
class MonokaiTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "monokai"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 4

  override val themeName: String
    get() = "Monokai Pro (Material)"

  override val themeIcon: String?
    get() = iconPrefix("monokai")

  override val backgroundImage: String
    get() = "walls/monokai.svg"

  override val themeColorScheme: String
    get() = "Monokai Pro (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x2D2A2E)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0xfcfcfa)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x939293)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x6E6C6F)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xffd866)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x403e41)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x403E41)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x5b595c)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x221F22)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x4A474B)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x2d2a2e)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x5b595c)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(0x403E41)

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x363437)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0xffd866)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x3a3a3c)
}
