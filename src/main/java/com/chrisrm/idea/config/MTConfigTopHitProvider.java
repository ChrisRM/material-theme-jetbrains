/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.config;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.ui.OptionsTopHitProvider;
import com.intellij.ide.ui.PublicMethodBasedOptionDescription;
import com.intellij.ide.ui.search.BooleanOptionDescription;
import com.intellij.ide.ui.search.OptionDescription;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Provide commands for Search Everything Top Hit commands
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
public final class MTConfigTopHitProvider extends OptionsTopHitProvider {

  @NonNls
  private static final Collection<OptionDescription> OPTION_DESCRIPTIONS = Collections.unmodifiableCollection(Arrays.asList(
      option(getText("mt.contrast"), "getIsContrastMode", "setIsContrastMode"),
      option(getText("mt.materialdesign"), "getIsMaterialDesign", "setIsMaterialDesign"),
      option(getText("mt.boldtabs"), "getIsStyledDirectories", "setIsStyledDirectories"),
      option(getText("MTForm.isUpperCaseTabsCheckbox.text"), "isUpperCaseTabs", "setIsUpperCaseTabs"),

      option(getText("MTForm.customTreeIndentCheckbox.text"), "isCustomTreeIndent", "setIsCustomTreeIndent"),

      option(getText("MTForm.isMaterialIconsCheckbox.text"), "isUseMaterialIcons", "setUseMaterialIcons"),
      option(getText("MTForm.projectViewDecorators"), "isUseProjectViewDecorators", "setUseProjectViewDecorators"),
      option(getText("MTForm.monochromeCheckbox.text"), "isMonochromeIcons", "setMonochromeIcons"),
      option(getText("MTForm.hideFileIcons"), "getHideFileIcons", "setHideFileIcons"),
      option(getText("MTForm.isCompactSidebarCheckbox.text"), "isCompactSidebar", "setCompactSidebar"),
      option(getText("MTForm.isCompactStatusbarCheckbox.text"), "isCompactStatusBar", "setIsCompactStatusBar"),
      option(getText("MTForm.compactDropdownsCheckbox.text"), "isCompactDropdowns", "setCompactDropdowns"),
      option(getText("MTForm.upperCaseButtonsCheckbox.text"), "isUpperCaseButtons", "setUpperCaseButtons"),

      option(getText("MTForm.isCompactTablesCheckbox.text"), "isCompactTables", "setIsCompactTables"),
      option(getText("MTForm.isCompactMenusCheckbox.text"), "isCompactMenus", "setIsCompactMenus"),

      option(getText("MTForm.themeStatus"), "isStatusBarTheme", "setIsStatusBarTheme"),
      option(getText("MTForm.materialThemeCheckbox.text"), "isMaterialTheme", "setIsMaterialTheme"),
      option(getText("MTForm.decoratedFoldersCheckbox.text"), "isDecoratedFolders", "setIsDecoratedFolders"),
      option(getText("MTForm.isFileIconsCheckbox.text"), "isFileIcons", "setIsFileIcons"),
      option(getText("MTForm.psiIconsCheckbox.text"), "isPsiIcons", "setIsPsiIcons"),

      option(getText("MTForm.themedScrollbarsCheckbox.text"), "isThemedScrollbars", "setThemedScrollbars"),
      option(getText("MTForm.accentScrollbarsCheckbox.text"), "isAccentScrollbars", "setAccentScrollbars"),
      option(getText("MTForm.fontSizeCheckbox.text"), "isTreeFontSizeEnabled", "setTreeFontSizeEnabled"),
      option(getText("MTForm.fileColorsCheckbox.text"), "isFileStatusColorsEnabled", "setFileStatusColorsEnabled"),

      option(getText("MTForm.highContrastCheckbox.text"), "isHighContrast", "setIsHighContrast"),
      option(getText("MTForm.tabShadowCheckbox.text"), "isTabsShadow", "setIsTabsShadow"),

      option(getText("MTForm.darkTitleBarCheckbox.text"), "isDarkTitleBar", "setDarkTitleBar")

  ));

  private static String getText(final String property) {
    return StringUtil.stripHtml(MaterialThemeBundle.message(property), false);
  }

  @SuppressWarnings("FeatureEnvy")
  private static BooleanOptionDescription option(@NonNls final String option, final String getter, final String setter) {
    return new PublicMethodBasedOptionDescription(MaterialThemeBundle.message("material.theme") + option,
        MTConfigurable.ID, getter, setter) {
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

  @NotNull
  @Override
  public Collection<OptionDescription> getOptions(@Nullable final Project project) {
    return OPTION_DESCRIPTIONS;
  }

  @NonNls
  @Override
  public String getId() {
    return "mtconfig";
  }
}
