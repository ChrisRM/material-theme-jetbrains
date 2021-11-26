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
 * Moonlight theme
 *
 */
class MoonlightTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "moonlight"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 13

  override val themeName: String
    get() = "Moonlight (Material)"

  override val themeIcon: String?
    get() = iconPrefix("moonlight")

  override val backgroundImage: String
    get() = "walls/moonlight.svg"

  override val themeColorScheme: String
    get() = "Moonlight (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x222436)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0xc8d3f5)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0xa9b8e8)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x444a73)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x444a73)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x2f334d)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x828bb8)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x191a2a)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x2f334d)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x222436)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x444a73)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x70444a73, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x191a2a)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0x74a0f1)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x2f334d)

}
