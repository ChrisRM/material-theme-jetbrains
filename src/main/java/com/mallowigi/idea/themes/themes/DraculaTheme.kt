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
 * Dracula theme
 *
 */
class DraculaTheme : MTAbstractTheme() {
  override var themeId: String = "DRACULA"

  override var isThemeDark: Boolean = true

  override val order: Int
    get() = 7

  override var themeName: String = "Dracula (Material)"

  override val themeIcon: String
    get() = MTUiUtils.iconPrefix("dracula")

  override val backgroundImage: String
    get() = "walls/dracula.svg"

  override var themeColorScheme: String? = "Dracula (Material)"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x282A36)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0xF8F8F2)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x6272A4)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x44475A)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0x8BE9FD)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x393C4B)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x282A36)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x6272A4)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x191A21)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x44475A)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x21222C)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x44475a)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(0x5044475A, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x1D2228)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0xFF79C5)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x313341)
}
