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
 * Atom One light theme
 *
 */
class OneLightTheme : MTAbstractLightTheme() {
  override var themeId: String = "one.light"

  override var isThemeDark: Boolean = false

  override val order: Int
    get() = 8

  override var themeName: String = "Atom One Light (Material)"

  override val themeIcon: String?
    get() = MTUiUtils.iconPrefix("one_light")

  override val backgroundImage: String
    get() = "walls/onelight.svg"

  override var themeColorScheme: String? = "Atom One Light (Material)"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xF4F4F4)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0x232324)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x7f7f7f)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0x232324)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0xDBDBDC)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xEAEAEB)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0xb8b8b9)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0xeaeaeb)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0xDBDBDC)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0xDBDBDC)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(-0x7f242424, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0xF2F2F2)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0x2979ff)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0xCACACB)

  override val secondSelectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0x232324)
}
