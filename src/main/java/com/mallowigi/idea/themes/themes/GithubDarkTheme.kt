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
import javax.swing.plaf.ColorUIResource

/**
 * Github dark theme
 *
 */
class GithubDarkTheme : MTAbstractTheme() {
  override val themeName: String
    get() = "GitHub Dark (Material)"

  override val themeIcon: String
    get() = iconPrefix("githubdark")

  override val backgroundImage: @NonNls String
    get() = "walls/github_dark.svg"

  override val themeColorScheme: String
    get() = "GitHub Dark (Material)"

  override val themeId: String
    get() = "github_dark"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 6

  override fun getBackgroundColorResource(): ColorUIResource = ColorUIResource(0x24292e)

  override fun getForegroundColorResource(): ColorUIResource = ColorUIResource(0xe1e4e8)

  override fun getTextColorResource(): ColorUIResource = ColorUIResource(0x959da5)

  override fun getSelectionBackgroundColorResource(): ColorUIResource = ColorUIResource(0x3392FF)

  override fun getSelectionForegroundColorResource(): ColorUIResource = ColorUIResource(0xFFFFFF)

  override fun getButtonColorResource(): ColorUIResource = ColorUIResource(0x39414a)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = ColorUIResource(0x2f363d)

  override fun getDisabledColorResource(): ColorUIResource = ColorUIResource(0x6a737d)

  override fun getContrastColorResource(): ColorUIResource = ColorUIResource(0x1e2428)

  override fun getTableSelectedColorResource(): ColorUIResource = ColorUIResource(0x2b3036)

  override fun getSecondBorderColorResource(): ColorUIResource = ColorUIResource(0x1b1f23)

  override fun getHighlightColorResource(): ColorUIResource = ColorUIResource(0x444d56)

  override fun getTreeSelectionColorResource(): ColorUIResource = ColorUIResource(0x39414a)

  override fun getNotificationsColorResource(): ColorUIResource = ColorUIResource(0x2f363d)

  override fun getAccentColorResource(): ColorUIResource = ColorUIResource(0xf9826c)

  override fun getExcludedColorResource(): ColorUIResource = ColorUIResource(0x2f363d)
}
