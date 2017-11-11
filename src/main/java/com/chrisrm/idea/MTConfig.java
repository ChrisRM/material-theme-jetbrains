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

package com.chrisrm.idea;

import com.chrisrm.idea.config.BeforeConfigNotifier;
import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.config.ui.MTForm;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@State(
    name = "MaterialThemeConfig",
    storages = @Storage("material_theme.xml")
)
public class MTConfig implements PersistentStateComponent<MTConfig> {
  public static final String DEFAULT_BG = "https://raw.githubusercontent" +
                                          ".com/mallowigi/material-theme-jetbrains-eap/master/src/main/resources/themes/wall.jpg,60";
  public static final String ACCENT_COLOR = "80CBC4";
  // They are public so they can be serialized
  public String version;

  public MTThemes selectedTheme = MTThemes.OCEANIC;
  public String highlightColor = ACCENT_COLOR;
  public boolean highlightColorEnabled = false;
  public Integer highlightThickness;
  public boolean isContrastMode = false;
  public boolean isMaterialDesign = true;
  public boolean isBoldTabs = false;
  public boolean isCustomTreeIndentEnabled = false;
  public Integer customTreeIndent = 6;

  public String accentColor = ACCENT_COLOR;
  public String wallpaper = DEFAULT_BG;

  public boolean wallpaperSet = true;
  public boolean useMaterialIcons = true;
  public boolean useProjectViewDecorators = true;
  public boolean hideFileIcons = false;
  public boolean compactSidebar = false;
  public boolean statusBarTheme = true;

  public Integer tabsHeight = 42;
  public boolean isMaterialTheme = true;
  public boolean themedScrollbars = true;
  public boolean isCompactStatusBar = false;

  public String defaultBackground;
  public boolean upperCaseTabs = false;
  public int customSidebarHeight = 18;
  public boolean accentScrollbars = true;

  public MTConfig() {
    final MTTheme theme = selectedTheme.getTheme();

    try {
      final InputStream stream = getClass().getResourceAsStream(theme.getId() + ".properties");
      final Properties properties = new Properties();
      properties.load(stream);
      stream.close();

      if (highlightColor == null) {
        highlightColor = properties.getProperty("material.tab.borderColor");
        highlightColorEnabled = false;
      }

      if (highlightThickness == null) {
        highlightThickness = Integer.parseInt(properties.getProperty("material.tab.borderThickness"));
      }
    }
    catch (final IOException ignored) {
    }
  }

  /**
   * Get instance of the config from the ServiceManager
   *
   * @return the MTConfig instance
   */
  public static MTConfig getInstance() {
    return ServiceManager.getService(MTConfig.class);
  }

  public boolean needsRestart(final MTForm form) {
    boolean modified = isMaterialDesignChanged(form.getIsMaterialDesign());
    modified = modified || isThemedScrollbarsChanged(form.isThemedScrollbars());
    modified = modified || isAccentScrollbarsChanged(form.isAccentScrollbars());

    return modified;
  }

  public MTThemes getSelectedTheme() {
    return ObjectUtils.notNull(selectedTheme, MTThemes.OCEANIC);
  }

  public void setSelectedTheme(final MTThemes selectedTheme) {
    this.selectedTheme = selectedTheme;
  }

  /**
   * Get the state of MTConfig
   */
  @Nullable
  @Override
  public MTConfig getState() {
    return this;
  }

  /**
   * Load the state from XML
   *
   * @param state the MTConfig instance
   */
  @Override
  public void loadState(final MTConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  /**
   * Fire an event to the application bus that the settings have changed
   *
   * @param form
   */
  public void fireBeforeChanged(final MTForm form) {
    ApplicationManager.getApplication().getMessageBus()
        .syncPublisher(BeforeConfigNotifier.BEFORE_CONFIG_TOPIC)
        .beforeConfigChanged(this, form);
  }

  /**
   * Fire an event to the application bus that the settings have changed
   */
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
        .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
        .configChanged(this);
  }

  //region Tabs Highlight

  /**
   * Get the set highlight color
   *
   * @return the highlight color
   */
  public Color getHighlightColor() {
    return ColorUtil.fromHex(highlightColor);
  }

  /**
   * Set a new highlight color
   *
   * @param color the new hightlight color
   */
  public void setHighlightColor(@NotNull final Color color) {
    highlightColor = ColorUtil.toHex(color);
  }

  /**
   * Checks whether the new highlightColor is different from the previous one
   *
   * @param color new highlight color
   * @return true if changed
   */
  public boolean isHighlightColorChanged(@NotNull final Color color) {
    final Color current = getHighlightColor();
    return !Objects.equals(current, color);
  }

  /**
   * Return whether custom highlight is enabled
   *
   * @return true if enabled
   */
  public boolean isHighlightColorEnabled() {
    return highlightColorEnabled;
  }

  /**
   * Enable/Disable custom highlight
   *
   * @param enabled state
   */
  public void setHighlightColorEnabled(final boolean enabled) {
    highlightColorEnabled = enabled;
  }

  /**
   * Checks whether the highlight color enabled state has changed
   *
   * @param enabled new enabled state
   * @return true if changed
   */
  public boolean isHighlightColorEnabledChanged(final boolean enabled) {
    return highlightColorEnabled != enabled;
  }
  //endregion

  //region Tab highlight thickness

  /**
   * Get user's highlight thickness
   *
   * @return highlight thickness
   */
  public int getHighlightThickness() {
    return highlightThickness;
  }

  /**
   * Set highlight thickness
   *
   * @param thickness thickness value
   */
  public void setHighlightThickness(final int thickness) {
    highlightThickness = thickness;
  }

  /**
   * Checks whether the highlight thickness has changed
   *
   * @param thickness new thickness
   * @return true if changed
   */
  public boolean isHighlightThicknessChanged(final int thickness) {
    return highlightThickness != thickness;
  }
  //endregion

  //region Contrast mode

  /**
   * Checks whether we are in contrast mode
   *
   * @return true if contrast mode
   */
  public boolean getIsContrastMode() {
    return isContrastMode;
  }

  /**
   * Enable/disable contrast mode
   *
   * @param isContrastMode contrast mode value
   */
  public void setIsContrastMode(final boolean isContrastMode) {
    this.isContrastMode = isContrastMode;
  }

  public boolean isContrastModeChanged(final boolean isContrastMode) {
    return this.isContrastMode != isContrastMode;
  }
  //endregion

  //region Material Design Components
  public boolean getIsMaterialDesign() {
    return isMaterialDesign;
  }

  public void setIsMaterialDesign(final boolean materialDesign) {
    isMaterialDesign = materialDesign;
  }

  public boolean isMaterialDesignChanged(final boolean isMaterialDesign) {
    return this.isMaterialDesign != isMaterialDesign;
  }
  //endregion

  //region Bold Tabs
  public boolean getIsBoldTabs() {
    return isBoldTabs;
  }

  public void setIsBoldTabs(final boolean isBoldTabs) {
    this.isBoldTabs = isBoldTabs;
  }

  public boolean isBoldTabsChanged(final boolean isBoldTabs) {
    return this.isBoldTabs != isBoldTabs;
  }
  //endregion

  //region Accent Color
  public String getAccentColor() {
    return accentColor;
  }

  public void setAccentColor(final String accentColor) {
    this.accentColor = accentColor;
  }

  public boolean isAccentColorChanged(final Color customAccentColor) {
    return !Objects.equals(accentColor, ColorUtil.toHex(customAccentColor));
  }
  //endregion

  //region Wallpapers
  public String getWallpaper() {
    return wallpaper;
  }

  public void setWallpaper(final String wallpaper) {
    this.wallpaper = wallpaper;
  }

  public boolean isWallpaperChanged(final String wallpaper) {
    return !Objects.equals(this.wallpaper, wallpaper);
  }

  public boolean isWallpaperSet() {
    return wallpaperSet;
  }

  public void setIsWallpaperSet(final boolean wallpaperSet) {
    this.wallpaperSet = wallpaperSet;
  }

  public boolean isWallpaperSetChanged(final boolean isWallpaperSet) {
    return wallpaperSet != isWallpaperSet;
  }
  //endregion

  //region Material Icons
  public boolean isUseMaterialIcons() {
    return useMaterialIcons;
  }

  public void setUseMaterialIcons(final boolean useMaterialIcons) {
    this.useMaterialIcons = useMaterialIcons;
  }

  public boolean isMaterialIconsChanged(final boolean useMaterialIcons) {
    return this.useMaterialIcons != useMaterialIcons;
  }
  //endregion

  //region Project View Decorators
  public boolean isUseProjectViewDecorators() {
    return useProjectViewDecorators;
  }

  public void setUseProjectViewDecorators(final boolean useProjectViewDecorators) {
    this.useProjectViewDecorators = useProjectViewDecorators;
  }

  public boolean isUseProjectViewDecoratorsChanged(final boolean useProjectViewDecorators) {
    return this.useProjectViewDecorators != useProjectViewDecorators;
  }
  //endregion

  //region Hide File Icons
  public boolean getHideFileIcons() {
    return hideFileIcons;
  }

  public void setHideFileIcons(final boolean hideFileIcons) {
    this.hideFileIcons = hideFileIcons;
  }

  public boolean isHideFileIconsChanged(final boolean hideFileIcons) {
    return this.hideFileIcons != hideFileIcons;
  }
  //endregion

  //region Compact Sidebar
  public boolean isCompactSidebar() {
    return compactSidebar;
  }

  public void setCompactSidebar(final boolean compactSidebar) {
    this.compactSidebar = compactSidebar;
  }

  public boolean isCompactSidebarChanged(final boolean compactSidebar) {
    return this.compactSidebar != compactSidebar;
  }

  public int getCustomSidebarHeight() {
    return customSidebarHeight;
  }

  public boolean customSidebarHeightChanged(final Integer customSidebarHeight) {
    return this.customSidebarHeight != customSidebarHeight;
  }

  public void setCustomSidebarHeight(final Integer customSidebarHeight) {
    this.customSidebarHeight = customSidebarHeight;
  }
  //endregion

  //region Statusbar indicator
  public boolean isStatusBarTheme() {
    return statusBarTheme;
  }

  public void setIsStatusBarTheme(final boolean isStatusBarTheme) {
    statusBarTheme = isStatusBarTheme;
  }

  public boolean isStatusBarThemeChanged(final boolean statusBarTheme) {
    return this.statusBarTheme != statusBarTheme;
  }
  //endregion

  //region Tabs Height
  public int getTabsHeight() {
    return tabsHeight;
  }

  public void setTabsHeight(final Integer tabsHeight) {
    this.tabsHeight = tabsHeight;
  }

  public boolean isTabsHeightChanged(final Integer tabsHeight) {
    return !Objects.equals(this.tabsHeight, tabsHeight);
  }
  //endregion

  //region Material Theme
  public boolean isMaterialTheme() {
    return isMaterialTheme;
  }

  public void setIsMaterialTheme(final boolean isMaterialTheme) {
    this.isMaterialTheme = isMaterialTheme;
  }

  public boolean isMaterialThemeChanged(final boolean isMaterialTheme) {
    return this.isMaterialTheme != isMaterialTheme;
  }
  //endregion

  //region Custom Tree Indents
  public int getCustomTreeIndent() {
    return customTreeIndent;
  }

  public void setCustomTreeIndent(final Integer customTreeIndent) {
    this.customTreeIndent = customTreeIndent;
  }

  public boolean isCustomTreeIndent() {
    return isCustomTreeIndentEnabled;
  }

  public boolean customTreeIndentChanged(final int customTreeIndent) {
    return this.customTreeIndent != customTreeIndent;
  }

  public void setIsCustomTreeIndent(final boolean isCustomTreeIndent) {
    isCustomTreeIndentEnabled = isCustomTreeIndent;
  }

  public boolean isCustomTreeIndentChanged(final boolean customTreeIndentEnabled) {
    return isCustomTreeIndentEnabled != customTreeIndentEnabled;
  }
  //endregion

  //region Themed Scrollbars
  public boolean isThemedScrollbars() {
    return themedScrollbars;
  }

  public void setThemedScrollbars(final boolean themedScrollbars) {
    this.themedScrollbars = themedScrollbars;
  }

  public boolean isThemedScrollbarsChanged(final boolean themedScrollbars) {
    return this.themedScrollbars != themedScrollbars;
  }

  public boolean isAccentScrollbars() {
    return accentScrollbars;
  }

  public void setAccentScrollbars(final boolean accentScrollbars) {
    this.accentScrollbars = accentScrollbars;
  }

  public boolean isAccentScrollbarsChanged(final boolean accentScrollbars) {
    return this.accentScrollbars != accentScrollbars;
  }
  //endregion

  //region Compact Status Bar
  public boolean isCompactStatusBar() {
    return isCompactStatusBar;
  }

  public void setIsCompactStatusBar(final boolean isCompactStatusBar) {
    this.isCompactStatusBar = isCompactStatusBar;
  }

  public boolean isCompactStatusBarChanged(final boolean compactStatusBar) {
    return isCompactStatusBar != compactStatusBar;
  }
  //endregion

  public String getDefaultBackground() {
    return defaultBackground;
  }

  public void setDefaultBackground(final String defaultBackground) {
    this.defaultBackground = defaultBackground;
  }

  //region Uppercase tabs
  public boolean isUpperCaseTabs() {
    return upperCaseTabs;
  }

  public void setIsUpperCaseTabs(final boolean isUpperCaseTabs) {
    upperCaseTabs = isUpperCaseTabs;
  }

  public boolean isUpperCaseTabsChanged(final boolean upperCaseTabs) {
    return this.upperCaseTabs != upperCaseTabs;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(final String version) {
    this.version = version;
  }

  //endregion
}
