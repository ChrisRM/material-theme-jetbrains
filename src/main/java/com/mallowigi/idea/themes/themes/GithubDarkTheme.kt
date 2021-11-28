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

import com.mallowigi.idea.utils.MTUiUtils
import org.jetbrains.annotations.NonNls
import javax.swing.plaf.ColorUIResource

/**
 * Github dark theme
 *
 */
class GithubDarkTheme : MTAbstractTheme() {
  override var themeName: String = "GitHub Dark (Material)"

  override val themeIcon: String
    get() = MTUiUtils.iconPrefix("githubdark")

  override val backgroundImage: @NonNls String
    get() = "walls/github_dark.svg"

  override var themeColorScheme: String? = "GitHub Dark (Material)"

  override var themeId: String = "github_dark"

  override var isThemeDark: Boolean = true

  override val order: Int
    get() = 6

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x24292e)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0xe1e4e8)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x959da5)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x3392FF)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x39414a)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x2f363d)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x6a737d)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x1e2428)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x2b3036)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x1b1f23)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x444d56)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(0x39414a)

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x2f363d)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0xf9826c)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x2f363d)
}
