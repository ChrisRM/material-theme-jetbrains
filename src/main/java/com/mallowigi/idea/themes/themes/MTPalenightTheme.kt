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
 * Material palenight theme
 *
 */
class MTPalenightTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "mt.palenight"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 2

  override val themeName: String
    get() = "Material Palenight"

  override val themeIcon: String?
    get() = iconPrefix("palenight")

  override val backgroundImage: String
    get() = "walls/palenight.svg"

  override val themeColorScheme: String
    get() = "Material Palenight"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x292D3E)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0xA6ACCD)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x676E95)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x444267)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x303348)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x34324a)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x515772)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x202331)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x414863)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x2b2a3e)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x444267)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(0x50676E95, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x202331)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0xab47bc)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x2f2e43)

}
