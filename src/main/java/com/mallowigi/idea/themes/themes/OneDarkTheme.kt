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

import java.awt.Color
import javax.swing.plaf.ColorUIResource

/**
 * Atom One dark theme
 *
 */
class OneDarkTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "one.dark"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 7

  override val themeName: String
    get() = "Atom One Dark (Material)"

  override val themeIcon: String?
    get() = iconPrefix("one_dark")

  override val backgroundImage: String
    get() = "walls/onedark.svg"

  override val themeColorScheme: String
    get() = "Atom One Dark (Material)"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x282C34)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0x979FAD)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x979FAD)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x4D515D)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x3A3F4B)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x2F333D)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x6B727D)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x21252B)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x383E49)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x282C34)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x383D48)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(-0x7fc5c0b5, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x282C34)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0x2979ff)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x3c4150)
}
