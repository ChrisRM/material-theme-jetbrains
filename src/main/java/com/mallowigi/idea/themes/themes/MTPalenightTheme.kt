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
 * Material palenight theme
 *
 */
class MTPalenightTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "mt.palenight"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 2

  override val themeName: String
    get() = "Material Palenight"

  override val themeIcon: String?
    get() = iconPrefix("palenight")

  override val backgroundImage: String
    get() = "walls/palenight.svg"

  override val themeColorScheme: String
    get() = "Material Palenight"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x292D3E)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0xA6ACCD)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x676E95)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x444267)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x303348)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x34324a)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x515772)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x202331)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x414863)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x2b2a3e)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x444267)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x50676E95, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x202331)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0xab47bc)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x2f2e43)

}
