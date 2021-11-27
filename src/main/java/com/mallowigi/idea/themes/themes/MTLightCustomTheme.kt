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
 * Material Light Custom theme
 *
 */
class MTLightCustomTheme : MTAbstractTheme() {
  override val themeId: String
    get() = "mt.light_custom"

  override val isThemeDark: Boolean
    get() = false

  override val order: Int
    get() = 101

  override val themeName: String
    get() = "Light Custom Theme (Material)"

  override val backgroundImage: String?
    get() = null

  override val themeColorScheme: String?
    get() = null

  override val isCustom: Boolean
    get() = true

  override val themeIcon: String?
    get() = MTUiUtils.iconPrefix("light_custom")

  private val config = MTCustomThemeConfig.getInstance()

  override val backgroundColorResource: ColorUIResource
    get() = config.backgroundColorString

  override val foregroundColorResource: ColorUIResource
    get() = config.foregroundColorString

  override val textColorResource: ColorUIResource
    get() = config.textColorString

  override val selectionBackgroundColorResource: ColorUIResource
    get() = config.selectionBackgroundColorString

  override val selectionForegroundColorResource: ColorUIResource
    get() = config.selectionForegroundColorString

  override val buttonColorResource: ColorUIResource
    get() = config.buttonColorString

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = config.secondaryBackgroundColorString

  override val disabledColorResource: ColorUIResource
    get() = config.disabledColorString

  override val contrastColorResource: ColorUIResource
    get() = config.contrastColorString

  override val tableSelectedColorResource: ColorUIResource
    get() = config.tableSelectedColorString

  override val secondBorderColorResource: ColorUIResource
    get() = config.secondBorderColorString

  override val highlightColorResource: ColorUIResource
    get() = config.highlightColorString

  override val treeSelectionColorResource: ColorUIResource
    get() = config.treeSelectionColorString

  override val notificationsColorResource: ColorUIResource
    get() = config.notificationsColorString

  override val accentColorResource: ColorUIResource
    get() = config.accentColorString

  override val excludedColorResource: ColorUIResource
    get() = config.excludedColorString
}
