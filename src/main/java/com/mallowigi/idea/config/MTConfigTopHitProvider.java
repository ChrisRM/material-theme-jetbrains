/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea.config;

import com.intellij.ide.ui.OptionsSearchTopHitProvider;
import com.intellij.ide.ui.PublicMethodBasedOptionDescription;
import com.intellij.ide.ui.search.BooleanOptionDescription;
import com.intellij.ide.ui.search.OptionDescription;
import com.intellij.openapi.util.text.StringUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Provide commands for Search Everything Top Hit commands
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
public final class MTConfigTopHitProvider implements OptionsSearchTopHitProvider.ApplicationLevelProvider {

  @NonNls
  private static final Collection<OptionDescription> OPTION_DESCRIPTIONS = Collections.unmodifiableCollection(Arrays.asList(
    option(getText("MTForm.accentScrollbarsCheckbox.text"), "isAccentScrollbars", "setAccentScrollbars"),
    option(getText("MTForm.codeAdditionsCheckBox.text"), "isCodeAdditionsEnabled", "setCodeAdditionsEnabled"),
    option(getText("MTForm.compactDropdownsCheckbox.text"), "isCompactDropdowns", "setCompactDropdowns"),
    option(getText("MTForm.contrastCheckBox.text"), "isContrastMode", "setIsContrastMode"),
    option(getText("MTForm.customTreeIndentCheckbox.text"), "isCustomTreeIndent", "setIsCustomTreeIndent"),

    option(getText("MTForm.fileColorsCheckbox.text"), "isFileStatusColorsEnabled", "setFileStatusColorsEnabled"),
    option(getText("MTForm.fontSizeCheckbox.text"), "isTreeFontSizeEnabled", "setTreeFontSizeEnabled"),

    option(getText("MTForm.highContrastCheckbox.text"), "isHighContrast", "setIsHighContrast"),

    option(getText("MTForm.isCompactMenusCheckbox.text"), "isCompactMenus", "setIsCompactMenus"),
    option(getText("MTForm.isCompactSidebarCheckbox.text"), "isCompactSidebar", "setCompactSidebar"),
    option(getText("MTForm.isCompactStatusbarCheckbox.text"), "isCompactStatusBar", "setIsCompactStatusBar"),
    option(getText("MTForm.isCompactTablesCheckbox.text"), "isCompactTables", "setIsCompactTables"),
    option(getText("MTForm.isUpperCaseTabsCheckbox.text"), "isUpperCaseTabs", "setIsUpperCaseTabs"),

    option(getText("MTForm.overrideAccentCheckbox.text"), "isOverrideAccentColor", "setOverrideAccentColor"),
    option(getText("MTForm.isColoredOpenedDirsCheckbox.text"), "isUseColoredDirectories", "setUseColoredDirectories"),

    option(getText("MTForm.styledDirectoriesCheckbox.text"), "isStyledDirectories", "setIsStyledDirectories"),
    option(getText("MTForm.themedScrollbarsCheckbox.text"), "isThemedScrollbars", "setThemedScrollbars"),
    option(getText("MTForm.themedTitleBarCheckbox.text"), "isDarkTitleBar", "setDarkTitleBar"),
    option(getText("MTForm.themeStatusBar.text"), "isStatusBarTheme", "setIsStatusBarTheme"),
    option(getText("MTForm.upperCaseButtonsCheckbox.text"), "isUpperCaseButtons", "setUpperCaseButtons"),
    option(getText("MTForm.accentModeCheckbox.text"), "isAccentMode", "setAccentMode"),
    option(getText("MTForm.useMaterialWallpapersCheckbox.text"), "isUseMaterialWallpapers", "setUseMaterialWallpapers"),
    option(getText("MTForm.useProjectFrameCheckbox.text"), "isUseProjectFrame", "setUseProjectFrame")

  ));

  private static String getText(final String property) {
    return StringUtil.stripHtml(MaterialThemeBundle.message(property), false);
  }

  @SuppressWarnings("FeatureEnvy")
  private static BooleanOptionDescription option(@NonNls final String option, final String getter, final String setter) {
    return new PublicMethodBasedOptionDescription(MaterialThemeBundle.message("option.prefix") + option,
      MTConfigurable.ID, getter, setter) {
      @NotNull
      @Override
      public Object getInstance() {
        return MTConfig.getInstance();
      }

      @Override
      protected void fireUpdated() {
        MTConfig.getInstance().fireChanged();
      }
    };
  }

  @NonNls
  @Override
  public String getId() {
    return "mtconfig";
  }

  @NotNull
  @Override
  public Collection<OptionDescription> getOptions() {
    return OPTION_DESCRIPTIONS;
  }
}
