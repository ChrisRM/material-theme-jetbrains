package com.chrisrm.idea.config;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ui.MTForm;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by helio on 24/03/2017.
 */
public class MTConfigurable extends MTConfigurableBase<MTForm, MTConfig> implements SearchableConfigurable {

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
  protected void setFormState(MTForm mtForm, MTConfig mtConfig) {
    form.setHighlightColor(mtConfig.getHighlightColor());
    form.setHighlightColorEnabled(mtConfig.isHighlightColorEnabled());
    form.setHighlightThickness(mtConfig.getHighlightThickness());
    form.setIsContrastMode(mtConfig.getIsContrastMode());
    form.setIsMaterialDesign(mtConfig.getIsMaterialDesign());
    form.setIsBoldTabs(mtConfig.getIsBoldTabs());
    form.setTabsHeight(mtConfig.getTabsHeight());
    form.setIsWallpaperSet(mtConfig.isWallpaperSet());
    form.setCustomWallpaper(mtConfig.getWallpaper());
    form.setIsCustomTreeIndent(mtConfig.isCustomTreeIndent());
    form.setCustomTreeIndent(mtConfig.getCustomTreeIndent());

    form.setIsUseMaterialIcons(mtConfig.isUseMaterialIcons());
    form.setUseProjectViewDecorators(mtConfig.isUseProjectViewDecorators());
    form.setHideFileIcons(mtConfig.getHideFileIcons());
    form.setIsCompactSidebar(mtConfig.isCompactSidebar());
    form.setIsStatusBarTheme(mtConfig.isStatusBarTheme());
    form.setIsMaterialTheme(mtConfig.isMaterialTheme());

    form.setIsThemedScrollbars(mtConfig.isThemedScrollbars());

    form.afterStateSet();
  }

  @Override
  protected void doApply(MTForm mtForm, MTConfig mtConfig) throws ConfigurationException {
    mtConfig.fireBeforeChanged(form);

    mtConfig.setHighlightColor(form.getHighlightColor());
    mtConfig.setHighlightColorEnabled(form.getHighlightColorEnabled());
    mtConfig.setHighlightThickness(form.getHighlightThickness());
    mtConfig.setIsContrastMode(form.getIsContrastMode());
    mtConfig.setIsMaterialDesign(form.getIsMaterialDesign());
    mtConfig.setIsBoldTabs(form.getIsBoldTabs());
    mtConfig.setTabsHeight(form.getTabsHeight());
    mtConfig.setIsWallpaperSet(form.getIsWallpaperSet());
    mtConfig.setWallpaper(form.getWallpaper());
    mtConfig.setIsCustomTreeIndent(form.isCustomTreeIndent());
    mtConfig.setCustomTreeIndent(form.getCustomTreeIndent());

    mtConfig.setUseMaterialIcons(form.isUseMaterialIcons());
    mtConfig.setUseProjectViewDecorators(form.getUseProjectViewDecorators());
    mtConfig.setHideFileIcons(form.getHideFileIcons());
    mtConfig.setCompactSidebar(form.isCompactSidebar());
    mtConfig.setIsStatusBarTheme(form.isStatusBarTheme());
    mtConfig.setIsMaterialTheme(form.getIsMaterialTheme());

    mtConfig.setThemedScrollbars(form.isThemedScrollbars());

    mtConfig.fireChanged();
  }

  @Override
  protected boolean checkModified(MTForm mtForm, MTConfig mtConfig) {
    boolean modified = mtConfig.isHighlightColorChanged(form.getHighlightColor());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(form.getHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(form.getHighlightThickness());
    modified = modified || mtConfig.isContrastModeChanged(form.getIsContrastMode());
    modified = modified || mtConfig.isMaterialDesignChanged(form.getIsMaterialDesign());
    modified = modified || mtConfig.isBoldTabsChanged(form.getIsBoldTabs());
    modified = modified || mtConfig.isTabsHeightChanged(form.getTabsHeight());
    modified = modified || mtConfig.isWallpaperSetChanged(form.getIsWallpaperSet());
    modified = modified || mtConfig.isWallpaperChanged(form.getWallpaper());

    modified = modified || mtConfig.isCustomTreeIndentChanged(form.isCustomTreeIndent());
    modified = modified || mtConfig.customTreeIndentChanged(form.getCustomTreeIndent());

    modified = modified || mtConfig.isMaterialIconsChanged(form.isUseMaterialIcons());
    modified = modified || mtConfig.isUseProjectViewDecoratorsChanged(form.getUseProjectViewDecorators());
    modified = modified || mtConfig.isHideFileIconsChanged(form.getHideFileIcons());
    modified = modified || mtConfig.isCompactSidebarChanged(form.isCompactSidebar());
    modified = modified || mtConfig.isStatusBarThemeChanged(form.isStatusBarTheme());
    modified = modified || mtConfig.isMaterialThemeChanged(form.getIsMaterialTheme());

    modified = modified || mtConfig.isThemedScrollbarsChanged(form.isThemedScrollbars());


    return modified;
  }

}
