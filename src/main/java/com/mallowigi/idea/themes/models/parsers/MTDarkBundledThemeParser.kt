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
package com.mallowigi.idea.themes.models.parsers

import com.mallowigi.idea.themes.models.MTBundledTheme
import javax.swing.plaf.ColorUIResource

/**
 * Dark Theme parser from XML
 *
 * @param mtBundledTheme the theme to fill
 */
class MTDarkBundledThemeParser(mtBundledTheme: MTBundledTheme?) : MTBundledThemeParser(mtBundledTheme!!) {
  override val defaultExcludedColor: ColorUIResource
    get() = ColorUIResource(0x2E3C43)
  override val defaultAccentColor: ColorUIResource
    get() = ColorUIResource(0x009688)
  override val defaultNotificationsColor: ColorUIResource
    get() = ColorUIResource(0x323232)
  override val defaultTreeSelectionColor: ColorUIResource
    get() = ColorUIResource(0x546E7A)
  override val defaultHighlightColor: ColorUIResource
    get() = ColorUIResource(0x425B67)
  override val defaultSecondBorderColor: ColorUIResource
    get() = ColorUIResource(0x2A373E)
  override val defaultTableSelectedColor: ColorUIResource
    get() = ColorUIResource(0x314549)
  override val defaultContrastColor: ColorUIResource
    get() = ColorUIResource(0x1E272C)
  override val defaultDisabledColor: ColorUIResource
    get() = ColorUIResource(0x415967)
  override val defaultSecondaryBackgroundColor: ColorUIResource
    get() = ColorUIResource(0x32424A)
  override val defaultButtonColor: ColorUIResource
    get() = ColorUIResource(0x2E3C43)
  override val defaultSelectionForegroundColor: ColorUIResource
    get() = ColorUIResource(0xFFFFFF)
  override val defaultSelectionBackgroundColor: ColorUIResource
    get() = ColorUIResource(0x546E7A)
  override val defaultTextColor: ColorUIResource
    get() = ColorUIResource(0x607D8B)
  override val defaultForegroundColor: ColorUIResource
    get() = ColorUIResource(0xB0BEC5)
  override val defaultBackgroundColor: ColorUIResource
    get() = ColorUIResource(0x263238)
}
