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
 * Night owl theme
 *
 */
class NightOwlTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "nightowl"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 11

  override val backgroundImage: String
    get() = "walls/nightowl.svg"

  override val themeColorScheme: String
    get() = "Night Owl (Material)"

  override val themeName: String
    get() = "Night Owl (Material)"

  override val themeIcon: String?
    get() = iconPrefix("nightowl")

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x011627)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0xd6deeb)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x5f7e97)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x084d81)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x0b253a)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x0b2942)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x697098)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x010e1a)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x13344f)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x122d42)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x084d81)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x5013344f, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x01111d)

  override fun getAccentColorResource(): @NonNls ColorUIResource? = ColorUIResource(0x7e57c2)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x0e293f)

}
