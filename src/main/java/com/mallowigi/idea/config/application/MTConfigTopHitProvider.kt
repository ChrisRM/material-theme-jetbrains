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
package com.mallowigi.idea.config.application

import com.intellij.ide.ui.OptionsSearchTopHitProvider.ApplicationLevelProvider
import com.intellij.ide.ui.PublicMethodBasedOptionDescription
import com.intellij.ide.ui.search.BooleanOptionDescription
import com.intellij.ide.ui.search.OptionDescription
import com.mallowigi.idea.messages.MaterialThemeBundle
import java.util.Collections
import java.util.function.Supplier

/**
 * Allows to get/set Material Theme settings from the command palette
 *
 */
class MTConfigTopHitProvider : ApplicationLevelProvider {
  /**
   * Id
   *
   */
  override fun getId(): String = "mtconfig"

  /**
   * The list of options available
   *
   */
  override fun getOptions(): Collection<OptionDescription> = OPTION_DESCRIPTIONS!!

  companion object {
    private val OPTION_DESCRIPTIONS: Collection<OptionDescription>? = Collections.unmodifiableCollection(
      listOf(
        option(
          MaterialThemeBundle.message("MTForm.accentModeCheckbox.text"),
          "isAccentMode",
          "setAccentMode"
        ),
        option(
          MaterialThemeBundle.message("MTForm.accentScrollbarsCheckbox.text"),
          "isAccentScrollbars",
          "setAccentScrollbars"
        ),
        option(
          MaterialThemeBundle.message("MTForm.activeTabBoldCheckbox.text"),
          "isActiveTabBold",
          "setIsActiveTabBold"
        ),
        option(
          MaterialThemeBundle.message("MTForm.activeTabHighlightCheckbox.text"),
          "isHighlightColorEnabled",
          "setHighlightColorEnabled"
        ),
        option(
          MaterialThemeBundle.message("MTForm.borderedButtonsCheckbox.text"),
          "isBorderedButtons",
          "setBorderedButtons"
        ),
        option(
          MaterialThemeBundle.message("MTForm.codeAdditionsCheckBox.text"),
          "isCodeAdditionsEnabled",
          "setCodeAdditionsEnabled"
        ),
        option(
          MaterialThemeBundle.message("MTForm.compactDropdownsCheckbox.text"),
          "isCompactDropdowns",
          "setCompactDropdowns"
        ),
        option(
          MaterialThemeBundle.message("MTForm.contrastCheckBox.text"),
          "isContrastMode",
          "setIsContrastMode"
        ),
        option(
          MaterialThemeBundle.message("MTForm.customTextCheckbox.text"),
          "isUseCustomTitle",
          "setUseCustomTitle"
        ),
        option(
          MaterialThemeBundle.message("MTForm.customTreeIndentCheckbox.text"),
          "isCustomTreeIndent",
          "setIsCustomTreeIndent"
        ),
        option(
          MaterialThemeBundle.message("MTForm.enforceLanguageOnOff.text"),
          "isEnforcedLanguageAdditions",
          "setEnforcedLanguageAdditions"
        ),
        option(
          MaterialThemeBundle.message("MTForm.fileColorsCheckbox.text"),
          "isFileStatusColorsEnabled",
          "setFileStatusColorsEnabled"
        ),
        option(
          MaterialThemeBundle.message("MTForm.tabFontSizeCheckbox.text"),
          "isTabFontSizeEnabled",
          "setTabFontSize"
        ),
        option(
          MaterialThemeBundle.message("MTForm.fontSizeCheckbox.text"),
          "isTreeFontSizeEnabled",
          "setTreeFontSizeEnabled"
        ),
        option(
          MaterialThemeBundle.message("MTForm.highContrastCheckbox.text"),
          "isHighContrast",
          "setIsHighContrast"
        ),
        option(
          MaterialThemeBundle.message("MTForm.invertedSelectionColorCheckbox.text"),
          "isInvertedSelectionColor",
          "setIsInvertedSelectionColor"
        ),
        option(
          MaterialThemeBundle.message("MTForm.isColoredOpenedDirsCheckbox.text"),
          "isUseColoredDirectories",
          "setUseColoredDirectories"
        ),
        option(
          MaterialThemeBundle.message("MTForm.isCompactMenusCheckbox.text"),
          "isCompactMenus",
          "setIsCompactMenus"
        ),
        option(
          MaterialThemeBundle.message("MTForm.isCompactSidebarCheckbox.text"),
          "isCompactSidebar",
          "setCompactSidebar"
        ),
        option(
          MaterialThemeBundle.message("MTForm.isCompactStatusbarCheckbox.text"),
          "isCompactStatusBar",
          "setIsCompactStatusBar"
        ),
        option(
          MaterialThemeBundle.message("MTForm.isCompactTablesCheckbox.text"),
          "isCompactTables",
          "setIsCompactTables"
        ),
        option(
          MaterialThemeBundle.message("MTForm.isUpperCaseTabsCheckbox.text"),
          "isUpperCaseTabs",
          "setUpperCaseTabs"
        ),
        option(
          MaterialThemeBundle.message("MTForm.showOverlaysCheckbox.text"),
          "isShowOverlays",
          "setShowOverlays"
        ),
        option(
          MaterialThemeBundle.message("MTForm.overrideAccentCheckbox.text"),
          "isOverrideAccentColor",
          "setOverrideAccentColor"
        ),
        option(
          MaterialThemeBundle.message("MTForm.projectTitleCheckbox.text"),
          "isUseProjectTitle",
          "setUseProjectTitle"
        ),
        option(
          MaterialThemeBundle.message("MTForm.showIconCheckbox.text"),
          "isUseProjectIcon",
          "setUseProjectIcon"
        ),
        option(
          MaterialThemeBundle.message("MTForm.showWhatsNewCheckbox.text"),
          "isShowWhatsNew",
          "setShowWhatsNew"
        ),
        option(
          MaterialThemeBundle.message("MTForm.styledDirectoriesCheckbox.text"),
          "isStyledDirectories",
          "setIsStyledDirectories"
        ),
        option(
          MaterialThemeBundle.message("MTForm.tabShadowCheckbox.text"),
          "isTabsShadow",
          "setIsTabsShadow"
        ),
        option(
          MaterialThemeBundle.message("MTForm.themedScrollbarsCheckbox.text"),
          "isThemedScrollbars",
          "setThemedScrollbars"
        ),
        option(
          MaterialThemeBundle.message("MTForm.toolWindowStripeCheckbox.text"),
          "isStripedToolWindowsEnabled",
          "setStripedToolWindowsEnabled"
        ),
        option(
          MaterialThemeBundle.message("MTForm.upperCaseButtonsCheckbox.text"),
          "isUpperCaseButtons",
          "setUpperCaseButtons"
        ),
        option(
          MaterialThemeBundle.message("MTForm.useMaterialFontCheckbox.text"),
          "isUseMaterialFont2",
          "setUseMaterialFont2"
        ),
        option(
          MaterialThemeBundle.message("MTForm.useGlobalFontCheckbox.text"),
          "isUseGlobalFont",
          "setUseGlobalFont"
        ),
        option(
          MaterialThemeBundle.message("MTForm.useMaterialWallpapersCheckbox.text"),
          "isUseMaterialWallpapers",
          "setUseMaterialWallpapers"
        ),
        option(
          MaterialThemeBundle.message("MTForm.useProjectFrameCheckbox.text"),
          "isUseProjectFrame",
          "setUseProjectFrame"
        ),
        option(
          MaterialThemeBundle.message("MTForm.autoResetColorSchemeCheckbox.text"),
          "isAutoResetColorScheme",
          "setAutoResetColorScheme"
        ),
      )
    )

    /**
     * Creates an option description
     *
     * @param text the text of the option
     * @param getter getter for the option
     * @param setter setter for the option
     * @return the option description
     */
    private fun option(text: String, getter: String, setter: String): BooleanOptionDescription {
      return object : PublicMethodBasedOptionDescription(
        MaterialThemeBundle.message("option.prefix") + text,
        MTConfigurable.ID,
        getter,
        setter,
        Supplier { MTConfig.getInstance() }
      ) {
        override fun getInstance(): MTConfig = MTConfig.getInstance()

        override fun fireUpdated() = MTConfig.getInstance().fireChanged()
      }
    }
  }
}
