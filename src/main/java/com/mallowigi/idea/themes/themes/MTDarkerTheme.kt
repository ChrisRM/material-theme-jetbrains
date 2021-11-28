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
 * Material Darker theme
 *
 */
class MTDarkerTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "mt.darker"

  override var isThemeDark: Boolean = true

  override val order: Int
    get() = 1

  override val themeName: String
    get() = "Material Darker"

  override val themeIcon: String?
    get() = MTUiUtils.iconPrefix("darker")

  override val backgroundImage: String
    get() = "walls/darker.svg"

  override val themeColorScheme: String
    get() = "Material Darker"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x212121)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0xB0BEC5)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x727272)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x404040)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x2A2A2A)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x292929)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x474747)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x1A1A1A)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x323232)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x292929)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x3F3F3F)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(-0x3fcdcdce, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x1A1A1A)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0xFF9800)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x323232)
}
