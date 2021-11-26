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
 * Atom One light theme
 *
 */
class OneLightTheme : MTAbstractLightTheme() {
  override val themeId: String
    get() = "one.light"
  override val isThemeDark: Boolean
    get() = false

  override val order: Int
    get() = 8

  override val themeName: String
    get() = "Atom One Light (Material)"

  override val themeIcon: String?
    get() = iconPrefix("one_light")

  override val backgroundImage: String
    get() = "walls/onelight.svg"

  override val themeColorScheme: String
    get() = "Atom One Light (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0xF4F4F4)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0x232324)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x7f7f7f)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0x232324)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0xDBDBDC)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0xEAEAEB)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0xb8b8b9)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0xeaeaeb)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0xDBDBDC)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0xDBDBDC)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(-0x7f242424, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0xF2F2F2)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0x2979ff)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0xCACACB)

  override fun getSecondSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0x232324)
}
