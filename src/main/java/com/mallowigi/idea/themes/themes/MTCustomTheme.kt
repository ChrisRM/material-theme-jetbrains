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
    get() = iconPrefix("custom")

  override fun getBackgroundColorResource(): ColorUIResource = customThemeConfig.backgroundColorString

  override fun getForegroundColorResource(): ColorUIResource = customThemeConfig.foregroundColorString

  override fun getTextColorResource(): ColorUIResource = customThemeConfig.textColorString

  override fun getSelectionBackgroundColorResource(): ColorUIResource = customThemeConfig.selectionBackgroundColorString

  override fun getSelectionForegroundColorResource(): ColorUIResource = customThemeConfig.selectionForegroundColorString

  override fun getButtonColorResource(): ColorUIResource = customThemeConfig.buttonColorString

  override fun getSecondaryBackgroundColorResource(): ColorUIResource = customThemeConfig.secondaryBackgroundColorString

  override fun getDisabledColorResource(): ColorUIResource = customThemeConfig.disabledColorString

  override fun getContrastColorResource(): ColorUIResource = customThemeConfig.contrastColorString

  override fun getTableSelectedColorResource(): ColorUIResource = customThemeConfig.tableSelectedColorString

  override fun getSecondBorderColorResource(): ColorUIResource = customThemeConfig.secondBorderColorString

  override fun getHighlightColorResource(): ColorUIResource = customThemeConfig.highlightColorString

  override fun getTreeSelectionColorResource(): ColorUIResource = customThemeConfig.treeSelectionColorString

  override fun getNotificationsColorResource(): ColorUIResource = customThemeConfig.notificationsColorString

  override fun getAccentColorResource(): ColorUIResource = customThemeConfig.accentColorString

  override fun getExcludedColorResource(): ColorUIResource = customThemeConfig.excludedColorString

}
