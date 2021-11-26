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
 * Light owl theme
 *
 */
class LightOwlTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "lightowl"

  override val isThemeDark: Boolean
    get() = false

  override val order: Int
    get() = 12

  override val themeName: String
    get() = "Light Owl (Material)"

  override val themeIcon: String
    get() = iconPrefix("lightowl")

  override val backgroundImage: String
    get() = "walls/lightowl.svg"

  override val themeColorScheme: String
    get() = "Light Owl (Material)"

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0xF0F0F0)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0x403f53)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x90A7B2)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0xd3e8f8)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0x403f53)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0xd9d9d9)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0xFBFBFB)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x93A1A1)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0xf0f0f0)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0xd3e8f8)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0xd9d9d9)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0xCCCCCC)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(Color(0x7cd3e8f8, true))

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0xF0F0F0)

  override fun getAccentColorResource(): @NonNls ColorUIResource = ColorUIResource(0x2AA298)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0xE0E7EA)
}
