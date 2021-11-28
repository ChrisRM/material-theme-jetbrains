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
 * Material lighter theme
 *
 */
class MTLighterTheme : MTAbstractTheme() {
  override var themeId: String = "mt.lighter"

  override var isThemeDark: Boolean = false

  override val order: Int
    get() = 3

  override var themeName: String = "Material Lighter"

  override val themeIcon: String?
    get() = MTUiUtils.iconPrefix("lighter")

  override val backgroundImage: String
    get() = "walls/lighter.svg"

  override var themeColorScheme: String? = "Material Lighter"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFAFAFA)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0x546E7A)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x94A7B0)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xCCD7DA)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0x546e7a)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0xF3F4F5)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0xD2D4D5)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0xEEEEEE)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0xE7E7E8)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0xd3e1e8)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0xE7E7E8)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(0x4080CBC4, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0xeae8e8)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0x00BCD4)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0xd3e1e8)
}
