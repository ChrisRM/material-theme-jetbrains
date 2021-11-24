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

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo
import com.intellij.ui.ColorUtil
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.lafs.MTDarculaLaf
import com.mallowigi.idea.lafs.MTNativeLaf
import com.mallowigi.idea.themes.MTAccentMode
import com.mallowigi.idea.themes.MTAccentMode.selectionColor
import com.mallowigi.idea.themes.lists.ContrastResources
import com.mallowigi.idea.utils.MTColorUtils.contrastifyBackground
import com.mallowigi.idea.utils.MTUI
import com.mallowigi.idea.utils.MTUiUtils
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException
import javax.swing.plaf.ColorUIResource

class MTNativeTheme : MTAbstractTheme() {
  override fun getBackgroundColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.background", MTUI.Panel.background)

  override fun getForegroundColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.foreground", MTUI.Panel.foreground)

  override fun getTextColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.primaryColor", MTUI.Panel.primaryForeground)

  override fun getSelectionBackgroundColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.selectionBackground", MTUI.Panel.selectionBackground)

  override fun getSelectionForegroundColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.selectionForeground", MTUI.Panel.selectionForeground)

  override fun getButtonColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.button", MTUI.Button.backgroundColor)

  override fun getSecondaryBackgroundColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.second", MTUI.Panel.secondaryBackground)

  override fun getDisabledColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.disabled", MTUI.Button.disabledColor)

  override fun getContrastColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.contrast", MTUI.Panel.contrastBackground)

  override fun getTableSelectedColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.active", MTUI.Table.highlightOuterColor)

  override fun getSecondBorderColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.border", MTUI.Separator.separatorColor)

  override fun getHighlightColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.highlight", MTUI.Panel.highlightBackground)

  override fun getTreeSelectionColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.tree", MTUI.Tree.selectionBackground)

  override fun getNotificationsColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.notifications", MTUI.Notification.backgroundColor)

  override fun getAccentColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.accent", ColorUtil.brighter(MTUI.Button.primaryBackgroundColor, 2))

  override fun getExcludedColorResource(): ColorUIResource =
    MTUiUtils.namedColor("material.excluded", MTUI.Panel.excludedBackground)

  override fun getThemeId(): String = "external"

  @Throws(UnsupportedLookAndFeelException::class)
  public override fun setLookAndFeel() {
    val currentLookAndFeel = LafManager.getInstance().currentLookAndFeel
    if (currentLookAndFeel == null) {
      super.setLookAndFeel()
      return
    }
    if (currentLookAndFeel is UIThemeBasedLookAndFeelInfo) {
      UIManager.setLookAndFeel(MTNativeLaf(this, currentLookAndFeel))
    } else if (DarculaLookAndFeelInfo.CLASS_NAME == currentLookAndFeel.className) {
      UIManager.setLookAndFeel(MTDarculaLaf())
    }
  }

  override fun buildAllResources(): Unit = Unit

  override fun applyContrast(apply: Boolean) {
    val dark = isDark
    for (resource in ContrastResources.CONTRASTED_RESOURCES) {
      val color = UIManager.getLookAndFeelDefaults().getColor(resource)
      if (color != null) {
        UIManager.put(resource, if (apply) contrastifyBackground(dark, ColorUIResource(color), false) else color)
      } else {
        UIManager.put(resource, if (apply) MTUI.Panel.contrastBackground else MTUI.Panel.background)
      }
    }
  }

  override fun isNative(): Boolean = true

  override fun getBackgroundImage(): String? = null

  override fun isThemeDark(): Boolean = true

  override fun applyAccentMode() {
    val mtConfig = MTConfig.getInstance()
    val accentColor = ColorUtil.fromHex(mtConfig.accentColor)
    val darkerAccentColor = ColorUtil.darker(accentColor, 2)
    val accentColorTransparent = ColorUtil.withAlpha(accentColor, 0.5)
    val secondAccentColor = ColorUtil.fromHex(mtConfig.secondAccentColor)
    val accentMode = mtConfig.isAccentMode

    // Add accent resources
    MTUiUtils.buildAccentResources(MTAccentMode.ACCENT_EXTRA_RESOURCES, accentColor, accentMode)
    MTUiUtils.buildAccentResources(MTAccentMode.DARKER_ACCENT_RESOURCES, darkerAccentColor, accentMode)
    MTUiUtils.buildAccentResources(MTAccentMode.ACCENT_TRANSPARENT_EXTRA_RESOURCES, accentColorTransparent, accentMode)
    // Add new selection color resources
    MTUiUtils.buildAccentResources(MTAccentMode.SELECTION_RESOURCES, selectionColor, accentMode)
    MTUiUtils.buildAccentResources(MTAccentMode.SECOND_ACCENT_RESOURCES, secondAccentColor, accentMode)
  }
}
