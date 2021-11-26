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
 * Material lighter theme
 *
 */
class MTLighterTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "mt.lighter"

  override val isThemeDark: Boolean
    get() = false

  override val order: Int
    get() = 3

  override val themeName: String
    get() = "Material Lighter"

  override val themeIcon: String?
    get() = iconPrefix("lighter")

  override val backgroundImage: String
    get() = "walls/lighter.svg"

  override val themeColorScheme: String?
    get() = "Material Lighter"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0xFAFAFA)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0x546E7A)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x94A7B0)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0xCCD7DA)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0x546e7a)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0xF3F4F5)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0xD2D4D5)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0xEEEEEE)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0xE7E7E8)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0xd3e1e8)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0xE7E7E8)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x4080CBC4, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0xeae8e8)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0x00BCD4)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0xd3e1e8)

}
