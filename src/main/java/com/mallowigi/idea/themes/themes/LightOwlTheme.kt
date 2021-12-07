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
 * Light owl theme
 *
 */
class LightOwlTheme : MTAbstractTheme() {
  @NonNls
  override var themeId: String = "LIGHT_OWL"

  override var isThemeDark: Boolean = false

  override val order: Int
    get() = 12

  @NonNls
  override var themeName: String = "Light Owl (Material)"

  override val themeIcon: String
    get() = MTUiUtils.iconPrefix("lightowl")

  override val backgroundImage: String
    @NonNls get() = "walls/lightowl.svg"

  @NonNls
  override var themeColorScheme: String? = "Light Owl (Material)"

  override val backgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xF0F0F0)

  override val foregroundColorResource: ColorUIResource
    get() = ColorUIResource(0x403f53)

  override val textColorResource: ColorUIResource
    get() = ColorUIResource(0x90A7B2)

  override val selectionBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xd3e8f8)

  override val selectionForegroundColorResource: ColorUIResource
    get() = ColorUIResource(0x403f53)

  override val buttonColorResource: ColorUIResource
    get() = ColorUIResource(0xd9d9d9)

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = ColorUIResource(0xFBFBFB)

  override val disabledColorResource: ColorUIResource
    get() = ColorUIResource(0x93A1A1)

  override val contrastColorResource: ColorUIResource
    get() = ColorUIResource(0xf0f0f0)

  override val tableSelectedColorResource: ColorUIResource
    get() = ColorUIResource(0xd3e8f8)

  override val secondBorderColorResource: ColorUIResource
    get() = ColorUIResource(0xd9d9d9)

  override val highlightColorResource: ColorUIResource
    get() = ColorUIResource(0xCCCCCC)

  override val treeSelectionColorResource: ColorUIResource
    get() = ColorUIResource(Color(0x7cd3e8f8, true))

  override val notificationsColorResource: ColorUIResource
    get() = ColorUIResource(0xF0F0F0)

  override val accentColorResource: ColorUIResource
    get() = ColorUIResource(0x2AA298)

  override val excludedColorResource: ColorUIResource
    get() = ColorUIResource(0xE0E7EA)
}
