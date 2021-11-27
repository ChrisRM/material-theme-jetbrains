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
    get() = MTUiUtils.iconPrefix("arc_dark")

  override val backgroundImage: String
    get() = "walls/arcdark.svg"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x2f343f)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0xD3DAE3)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x8b9eb5)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x414181)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x383C4A)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x393f4c)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0xa2a2a2)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x262b33)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x41416A)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x404552)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x444A58)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(0x70094771, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x262a33)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0x5294E2)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x37373d)
}
