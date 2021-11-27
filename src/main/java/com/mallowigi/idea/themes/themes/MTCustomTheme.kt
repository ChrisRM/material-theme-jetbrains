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

import com.mallowigi.idea.config.custom.MTCustomThemeConfig
import com.mallowigi.idea.utils.MTUiUtils
import javax.swing.plaf.ColorUIResource

/**
 * Material Custom Theme
 *
 */
class MTCustomTheme : MTAbstractTheme() {

  private val customThemeConfig = MTCustomThemeConfig.getInstance()

  override val themeName: String
    get() = "Custom Theme (Material)"

  override val backgroundImage: String?
    get() = null

  override val themeColorScheme: String?
    get() = null

  override val themeId: String
    get() = "mt.custom"

  override val isThemeDark: Boolean
    get() = true

  override val order: Int
    get() = 100

  override val isCustom: Boolean
    get() = true

  override val themeIcon: String
    get() = MTUiUtils.iconPrefix("custom")

  override val backgroundColorResource: ColorUIResource
    get() = customThemeConfig.backgroundColorString

  override val foregroundColorResource: ColorUIResource
    get() = customThemeConfig.foregroundColorString

  override val textColorResource: ColorUIResource
    get() = customThemeConfig.textColorString

  override val selectionBackgroundColorResource: ColorUIResource
    get() = customThemeConfig.selectionBackgroundColorString

  override val selectionForegroundColorResource: ColorUIResource
    get() = customThemeConfig.selectionForegroundColorString

  override val buttonColorResource: ColorUIResource
    get() = customThemeConfig.buttonColorString

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = customThemeConfig.secondaryBackgroundColorString

  override val disabledColorResource: ColorUIResource
    get() = customThemeConfig.disabledColorString

  override val contrastColorResource: ColorUIResource
    get() = customThemeConfig.contrastColorString

  override val tableSelectedColorResource: ColorUIResource
    get() = customThemeConfig.tableSelectedColorString

  override val secondBorderColorResource: ColorUIResource
    get() = customThemeConfig.secondBorderColorString

  override val highlightColorResource: ColorUIResource
    get() = customThemeConfig.highlightColorString

  override val treeSelectionColorResource: ColorUIResource
    get() = customThemeConfig.treeSelectionColorString

  override val notificationsColorResource: ColorUIResource
    get() = customThemeConfig.notificationsColorString

  override val accentColorResource: ColorUIResource
    get() = customThemeConfig.accentColorString

  override val excludedColorResource: ColorUIResource
    get() = customThemeConfig.excludedColorString
}
