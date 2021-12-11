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
import java.awt.Color
import javax.swing.plaf.ColorUIResource

/**
 * Night owl theme
 *
 */
class NightOwlTheme : MTAbstractTheme() {
  @NonNls
  override var themeId: String = "NIGHT_OWL"

  override var isThemeDark: Boolean = true

  override val order: Int
    get() = 11

  override val backgroundImage: String
    @NonNls get() = "walls/nightowl.svg"

  @NonNls
  override var themeColorScheme: String? = "Night Owl (Material)"

  @NonNls
  override var themeName: String = "Night Owl (Material)"

  override val themeIcon: String?
    get() = MTUiUtils.iconPrefix("nightowl")

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x011627)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0xd6deeb)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x5f7e97)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x084d81)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0x0b253a)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0x0b2942)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x697098)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0x010e1a)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0x13344f)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0x122d42)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0x084d81)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(0x5013344f, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0x01111d)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0x7e57c2)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0x0e293f)
}