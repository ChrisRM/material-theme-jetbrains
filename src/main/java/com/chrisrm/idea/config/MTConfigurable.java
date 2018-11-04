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
import com.chrisrm.idea.config.ui.MTForm;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import static com.chrisrm.idea.utils.MTUiUtils.HELP_PREFIX;

/**
 * Service used to load and save settings from MTConfig
 */
public final class MTConfigurable extends MTConfigurableBase<MTForm, MTConfig> implements SearchableConfigurable {

  public static final String ID = "com.chrisrm.idea.config";
  public static final String HELP_ID = "MTConfig";

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.title");
  }

  @NonNls
  @NotNull
  @Override
  public String getHelpTopic() {
    return HELP_PREFIX + "." + HELP_ID;
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @NotNull
  @Override
  protected MTConfig getConfig() {
    return MTConfig.getInstance();
  }

  @NotNull
  @Override
  protected MTForm createForm() {
    return new MTForm();
  }

  @Override
  protected void setFormState(final MTForm mtForm, @NotNull final MTConfig mtConfig) {
    getForm().setFormState(mtConfig);
  }

  @Override
  protected void doApply(final MTForm mtForm, final MTConfig mtConfig) {
    mtConfig.fireBeforeChanged(getForm());
    mtConfig.setSettingsSelectedTab(getForm().getSelectedTabIndex());

    mtConfig.setSelectedTheme(getForm().getTheme());
    mtConfig.setHighlightColor(getForm().getHighlightColor());
    mtConfig.setHighlightColorEnabled(getForm().getHighlightColorEnabled());
    mtConfig.setHighlightThickness(getForm().getHighlightThickness());
    mtConfig.setIsContrastMode(getForm().getIsContrastMode());
    mtConfig.setIsMaterialDesign(getForm().getIsMaterialDesign());
    mtConfig.setIsStyledDirectories(getForm().isStyledDirectories());
    mtConfig.setTabsHeight(getForm().getTabsHeight());
    mtConfig.setIsCustomTreeIndent(getForm().isCustomTreeIndent());
    mtConfig.setRightTreeIndent(getForm().getRightTreeIndent());
    mtConfig.setLeftTreeIndent(getForm().getLeftTreeIndent());
    mtConfig.setTreeFontSize(getForm().getTreeFontSize());
    mtConfig.setTreeFontSizeEnabled(getForm().isTreeFontSizeEnabled());
    mtConfig.setIsUpperCaseTabs(getForm().isUpperCaseTabs());

    mtConfig.setUseMaterialIcons(getForm().isUseMaterialIcons());
    mtConfig.setUseProjectViewDecorators(getForm().getUseProjectViewDecorators());
    mtConfig.setHideFileIcons(getForm().getHideFileIcons());
    mtConfig.setCompactSidebar(getForm().isCompactSidebar());
    mtConfig.setIsStatusBarTheme(getForm().isStatusBarTheme());
    mtConfig.setIsCompactStatusBar(getForm().isCompactStatusBar());
    mtConfig.setIsCompactTables(getForm().isCompactTables());
    mtConfig.setIsCompactMenus(getForm().getIsCompactMenus());
    mtConfig.setFileIcons(getForm().isFileIcons());
    mtConfig.setIsDecoratedFolders(getForm().isDecoratedFolders());

    mtConfig.setIsMaterialTheme(getForm().getIsMaterialTheme());
    mtConfig.setCustomSidebarHeight(getForm().getCustomSidebarHeight());
    mtConfig.setArrowsStyle(getForm().getArrowsStyle());
    mtConfig.setIndicatorStyle(getForm().getIndicatorStyle());
    mtConfig.setIndicatorThickness(getForm().getIndicatorThickness());
    mtConfig.setUseMaterialFont(getForm().getUseMaterialFont());

    mtConfig.setThemedScrollbars(getForm().isThemedScrollbars());
    mtConfig.setAccentScrollbars(getForm().isAccentScrollbars());
    mtConfig.setFileStatusColorsEnabled(getForm().isFileStatusColors());
    mtConfig.setDarkTitleBar(getForm().isDarkTitleBar());

    mtConfig.setTabOpacity(getForm().getTabOpacity());

    mtConfig.setCompactDropdowns(getForm().getIsCompactDropdowns());
    mtConfig.setMonochromeIcons(getForm().getIsMonochromeIcons());
    mtConfig.setUpperCaseButtons(getForm().getIsUpperCaseButtons());

    mtConfig.setAccentColor(ColorUtil.toHex(getForm().getCustomAccentColor()));
    mtConfig.setIsHighContrast(getForm().isHighContrast());
    mtConfig.setOverrideAccentColor(getForm().isOverrideAccents());
    mtConfig.setIsTabsShadow(getForm().isTabsShadow());

    mtConfig.fireChanged();
  }

  @Override
  protected boolean checkModified(final MTForm mtForm, final MTConfig mtConfig) {
    boolean modified = mtConfig.isHighlightColorChanged(getForm().getHighlightColor());
    modified = modified || mtConfig.isSelectedThemeChanged(getForm().getTheme());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(getForm().getHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(getForm().getHighlightThickness());
    modified = modified || mtConfig.isContrastModeChanged(getForm().getIsContrastMode());
    modified = modified || mtConfig.isMaterialDesignChanged(getForm().getIsMaterialDesign());
    modified = modified || mtConfig.isStyledDirectoriesChanged(getForm().isStyledDirectories());
    modified = modified || mtConfig.isTabsHeightChanged(getForm().getTabsHeight());

    modified = modified || mtConfig.isCustomTreeIndentChanged(getForm().isCustomTreeIndent());
    modified = modified || mtConfig.isRightTreeIndentChanged(getForm().getRightTreeIndent());
    modified = modified || mtConfig.isLeftTreeIndentChanged(getForm().getLeftTreeIndent());
    modified = modified || mtConfig.isTreeFontSizeChanged(getForm().getTreeFontSize());
    modified = modified || mtConfig.isTreeFontSizeEnabledChanged(getForm().isTreeFontSizeEnabled());
    modified = modified || mtConfig.isUpperCaseTabsChanged(getForm().isUpperCaseTabs());

    modified = modified || mtConfig.isMaterialIconsChanged(getForm().isUseMaterialIcons());
    modified = modified || mtConfig.isUseProjectViewDecoratorsChanged(getForm().getUseProjectViewDecorators());
    modified = modified || mtConfig.isHideFileIconsChanged(getForm().getHideFileIcons());
    modified = modified || mtConfig.isCompactSidebarChanged(getForm().isCompactSidebar());
    modified = modified || mtConfig.isCompactStatusBarChanged(getForm().isCompactStatusBar());
    modified = modified || mtConfig.isCompactTablesChanged(getForm().isCompactTables());
    modified = modified || mtConfig.isCompactMenusChanged(getForm().getIsCompactMenus());

    modified = modified || mtConfig.isStatusBarThemeChanged(getForm().isStatusBarTheme());
    modified = modified || mtConfig.isMaterialThemeChanged(getForm().getIsMaterialTheme());
    modified = modified || mtConfig.isCustomSidebarHeightChanged(getForm().getCustomSidebarHeight());

    modified = modified || mtConfig.isThemedScrollbarsChanged(getForm().isThemedScrollbars());
    modified = modified || mtConfig.isAccentScrollbarsChanged(getForm().isAccentScrollbars());
    modified = modified || mtConfig.isFileStatusColorsEnabledChanged(getForm().isFileStatusColors());
    modified = modified || mtConfig.isDarkTitleBarChanged(getForm().isDarkTitleBar());
    modified = modified || mtConfig.isFileIconsChanged(getForm().isFileIcons());
    modified = modified || mtConfig.isDecoratedFoldersChanged(getForm().isDecoratedFolders());

    modified = modified || mtConfig.isAccentColorChanged(getForm().getCustomAccentColor());
    modified = modified || mtConfig.isArrowsStyleChanged(getForm().getArrowsStyle());
    modified = modified || mtConfig.isIndicatorStyleChanged(getForm().getIndicatorStyle());
    modified = modified || mtConfig.isIndicatorThicknessChanged(getForm().getIndicatorThickness());
    modified = modified || mtConfig.isUseMaterialFontChanged(getForm().getUseMaterialFont());

    modified = modified || mtConfig.isTabOpacityChanged(getForm().getTabOpacity());
    modified = modified || mtConfig.isCompactDropdownsChanged(getForm().getIsCompactDropdowns());
    modified = modified || mtConfig.isMonochromeIconsChanged(getForm().getIsMonochromeIcons());
    modified = modified || mtConfig.isUpperCaseButtonsChanged(getForm().getIsUpperCaseButtons());
    modified = modified || mtConfig.isHighContrastChanged(getForm().isHighContrast());

    modified = modified || mtConfig.isOverrideAccentColorChanged(getForm().isOverrideAccents());
    modified = modified || mtConfig.isTabsShadowChanged(getForm().isTabsShadow());

    return modified;
  }
}
