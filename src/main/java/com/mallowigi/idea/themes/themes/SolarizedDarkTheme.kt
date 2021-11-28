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
import java.awt.Color
import javax.swing.plaf.ColorUIResource

/**
 * Solarized dark theme
 *
 */
class SolarizedDarkTheme : MTAbstractTheme() {
  override var themeId: String = "solarized.dark"

  override var isThemeDark: Boolean = true

  override val order: Int
    get() = 9

  override var themeName: String = "Solarized Dark (Material)"

  override val themeIcon: String?
    get() = MTUiUtils.iconPrefix("solarized_dark")

  override val backgroundImage: String
    get() = "walls/solardark.svg"

  override var themeColorScheme: String? = "Solarized Dark (Material)"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x002B36)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0x839496)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x586e75)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x2E4C52)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x073642)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x003745)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x2E5861)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x00252E)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x003946)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x0D3640)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x005a6f)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(-0x7fd1b3ae, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x2E4C52)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0xd33682)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x083F4D)
}
