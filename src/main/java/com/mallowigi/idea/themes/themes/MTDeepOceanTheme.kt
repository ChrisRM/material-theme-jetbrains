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
 * Material Deep Ocean theme
 *
 */
class MTDeepOceanTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "mt.deepocean"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 4

  override val themeName: String
    get() = "Material Deep Ocean"

  override val themeIcon: String?
    get() = iconPrefix("deepocean")

  override val backgroundImage: String
    get() = "walls/deepocean.svg"

  override val themeColorScheme: String?
    get() = "Material Deep Ocean"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x0F111A)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0x8F93A2)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x4B526D)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x222533)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x191A21)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x181A1F)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x464B5D)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x090B10)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x1A1C25)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x0F111A)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x1F2233)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x30717CB4, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x090B10)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0x84ffff)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x292D3E)

}
