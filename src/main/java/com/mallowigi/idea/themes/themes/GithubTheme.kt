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
 * Github theme
 *
 */
class GithubTheme : MTAbstractLightTheme() {
  override val themeName: String
    get() = "GitHub (Material)"

  override val themeIcon: String
    get() = MTUiUtils.iconPrefix("github")

  override val backgroundImage: @NonNls String
    get() = "walls/github.svg"

  override val themeColorScheme: String
    get() = "GitHub (Material)"

  override val themeId: String
    get() = "github"

  override var isThemeDark: Boolean = false

  override val order: Int
    get() = 5

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xF7F8FA)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0x5B6168)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x292D31)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xc6e5ff)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0x111111)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0xedf1f5)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xf3f3f3)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x9ba0a3)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0xfafbfc)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0xcce5ff)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0xDFE1E4)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0xCCE5FF)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(0xe1e4e8)

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0xDFECFE)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0x79CB60)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0xdcdcdc)

  override val secondSelectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0x111111)
}
