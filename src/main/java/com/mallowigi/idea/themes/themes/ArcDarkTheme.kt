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

import org.jetbrains.annotations.NonNls
import java.awt.Color
import javax.swing.plaf.ColorUIResource

/**
 * Arc dark theme
 *
 */
class ArcDarkTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "arc.dark"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 5

  override val themeColorScheme: String
    get() = "Arc Dark (Material)"

  override val themeName: String
    get() = "Arc Dark (Material)"

  override val themeIcon: String
    get() = iconPrefix("arc_dark")

  override val backgroundImage: String
    get() = "walls/arcdark.svg"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x2f343f)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0xD3DAE3)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x8b9eb5)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x414181)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x383C4A)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x393f4c)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0xa2a2a2)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x262b33)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x41416A)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x404552)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x444A58)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x70094771, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x262a33)

  override fun getAccentColorResource(): @NonNls ColorUIResource = ColorUIResource(0x5294E2)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x37373d)
}
