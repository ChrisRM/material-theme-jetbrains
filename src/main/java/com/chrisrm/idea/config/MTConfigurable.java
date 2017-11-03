/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Service used to load and save settings from MTConfig
 */
public final class MTConfigurable extends MTConfigurableBase<MTForm, MTConfig> implements SearchableConfigurable {

  public static final String ID = "com.chrisrm.idea.config";

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.title");
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Override
  protected MTConfig getConfig() {
    return MTConfig.getInstance();
  }

  @Override
  protected MTForm createForm() {
    return new MTForm();
  }

  @Override
  protected void setFormState(final MTForm mtForm, final MTConfig mtConfig) {
    getForm().setHighlightColor(mtConfig.getHighlightColor());
    getForm().setHighlightColorEnabled(mtConfig.isHighlightColorEnabled());
    getForm().setHighlightThickness(mtConfig.getHighlightThickness());
    getForm().setIsContrastMode(mtConfig.getIsContrastMode());
    getForm().setIsMaterialDesign(mtConfig.getIsMaterialDesign());
    getForm().setIsBoldTabs(mtConfig.getIsBoldTabs());
    getForm().setTabsHeight(mtConfig.getTabsHeight());
    getForm().setIsWallpaperSet(mtConfig.isWallpaperSet());
    getForm().setCustomWallpaper(mtConfig.getWallpaper());
    getForm().setIsCustomTreeIndent(mtConfig.isCustomTreeIndent());
    getForm().setCustomTreeIndent(mtConfig.getCustomTreeIndent());
    getForm().setIsUpperCaseTabs(mtConfig.isUpperCaseTabs());

    getForm().setIsUseMaterialIcons(mtConfig.isUseMaterialIcons());
    getForm().setUseProjectViewDecorators(mtConfig.isUseProjectViewDecorators());
    getForm().setHideFileIcons(mtConfig.getHideFileIcons());
    getForm().setIsCompactSidebar(mtConfig.isCompactSidebar());
    getForm().setIsCompactStatusBar(mtConfig.isCompactStatusBar());
    getForm().setIsStatusBarTheme(mtConfig.isStatusBarTheme());
    getForm().setIsMaterialTheme(mtConfig.isMaterialTheme());
    getForm().setCustomSidebarHeight(mtConfig.getCustomSidebarHeight());

    getForm().setIsThemedScrollbars(mtConfig.isThemedScrollbars());
    getForm().setIsAccentScrollbars(mtConfig.isAccentScrollbars());

    getForm().setCustomAccentColor(ColorUtil.fromHex(mtConfig.getAccentColor()));

    getForm().afterStateSet();
  }

  @Override
  protected void doApply(final MTForm mtForm, final MTConfig mtConfig) {
    mtConfig.fireBeforeChanged(getForm());

    mtConfig.setHighlightColor(getForm().getHighlightColor());
    mtConfig.setHighlightColorEnabled(getForm().getHighlightColorEnabled());
    mtConfig.setHighlightThickness(getForm().getHighlightThickness());
    mtConfig.setIsContrastMode(getForm().getIsContrastMode());
    mtConfig.setIsMaterialDesign(getForm().getIsMaterialDesign());
    mtConfig.setIsBoldTabs(getForm().getIsBoldTabs());
    mtConfig.setTabsHeight(getForm().getTabsHeight());
    mtConfig.setIsWallpaperSet(getForm().getIsWallpaperSet());
    mtConfig.setWallpaper(getForm().getWallpaper());
    mtConfig.setIsCustomTreeIndent(getForm().isCustomTreeIndent());
    mtConfig.setCustomTreeIndent(getForm().getCustomTreeIndent());
    mtConfig.setIsUpperCaseTabs(getForm().isUpperCaseTabs());

    mtConfig.setUseMaterialIcons(getForm().isUseMaterialIcons());
    mtConfig.setUseProjectViewDecorators(getForm().getUseProjectViewDecorators());
    mtConfig.setHideFileIcons(getForm().getHideFileIcons());
    mtConfig.setCompactSidebar(getForm().isCompactSidebar());
    mtConfig.setIsStatusBarTheme(getForm().isStatusBarTheme());
    mtConfig.setIsCompactStatusBar(getForm().isCompactStatusBar());
    mtConfig.setIsMaterialTheme(getForm().getIsMaterialTheme());
    mtConfig.setCustomSidebarHeight(getForm().getCustomSidebarHeight());

    mtConfig.setThemedScrollbars(getForm().isThemedScrollbars());
    mtConfig.setAccentScrollbars(getForm().isAccentScrollbars());
    mtConfig.setAccentColor(ColorUtil.toHex(getForm().getCustomAccentColor()));

    mtConfig.fireChanged();
  }

  @Override
  protected boolean checkModified(final MTForm mtForm, final MTConfig mtConfig) {
    boolean modified = mtConfig.isHighlightColorChanged(getForm().getHighlightColor());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(getForm().getHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(getForm().getHighlightThickness());
    modified = modified || mtConfig.isContrastModeChanged(getForm().getIsContrastMode());
    modified = modified || mtConfig.isMaterialDesignChanged(getForm().getIsMaterialDesign());
    modified = modified || mtConfig.isBoldTabsChanged(getForm().getIsBoldTabs());
    modified = modified || mtConfig.isTabsHeightChanged(getForm().getTabsHeight());
    modified = modified || mtConfig.isWallpaperSetChanged(getForm().getIsWallpaperSet());
    modified = modified || mtConfig.isWallpaperChanged(getForm().getWallpaper());

    modified = modified || mtConfig.isCustomTreeIndentChanged(getForm().isCustomTreeIndent());
    modified = modified || mtConfig.customTreeIndentChanged(getForm().getCustomTreeIndent());
    modified = modified || mtConfig.isUpperCaseTabsChanged(getForm().isUpperCaseTabs());

    modified = modified || mtConfig.isMaterialIconsChanged(getForm().isUseMaterialIcons());
    modified = modified || mtConfig.isUseProjectViewDecoratorsChanged(getForm().getUseProjectViewDecorators());
    modified = modified || mtConfig.isHideFileIconsChanged(getForm().getHideFileIcons());
    modified = modified || mtConfig.isCompactSidebarChanged(getForm().isCompactSidebar());
    modified = modified || mtConfig.isCompactStatusBarChanged(getForm().isCompactStatusBar());
    modified = modified || mtConfig.isStatusBarThemeChanged(getForm().isStatusBarTheme());
    modified = modified || mtConfig.isMaterialThemeChanged(getForm().getIsMaterialTheme());
    modified = modified || mtConfig.customSidebarHeightChanged(getForm().getCustomSidebarHeight());

    modified = modified || mtConfig.isThemedScrollbarsChanged(getForm().isThemedScrollbars());
    modified = modified || mtConfig.isAccentScrollbarsChanged(getForm().isAccentScrollbars());

    modified = modified || mtConfig.isAccentColorChanged(getForm().getCustomAccentColor());

    return modified;
  }
}
