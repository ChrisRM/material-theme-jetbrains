package com.chrisrm.idea;

import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.ColorUtil;
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
  public MTTheme selectedTheme = MTTheme.DEFAULT;
  public String highlightColor;
  public boolean highlightColorEnabled = false;
  public Integer highlightThickness;
  public boolean isContrastMode = false;
  public boolean isMaterialDesign = true;
  public boolean isBoldTabs = false;
  public String accentColor = "80CBC4";
  public String wallpaper = DEFAULT_BG;

  public boolean wallpaperSet = true;
  public boolean useMaterialIcons = true;
  public boolean useProjectViewDecorators = true;
  public boolean hideFileIcons = false;
  public boolean compactSidebar = false;
  public boolean statusBarTheme = true;

  public int tabsHeight = 46;
  private boolean materialTheme;
  private boolean isMaterialTheme;

  public MTConfig() {
    MTTheme theme = this.selectedTheme;

    try {
      InputStream stream = getClass().getResourceAsStream(theme.getId() + ".properties");
      Properties properties = new Properties();
      properties.load(stream);
      stream.close();

      if (this.highlightColor == null) {
        highlightColor = properties.getProperty("material.tab.borderColor");
        highlightColorEnabled = false;
      }

      if (this.highlightThickness == null) {
        highlightThickness = Integer.parseInt(properties.getProperty("material.tab.borderThickness"));
      }
    }
    catch (IOException ignored) {
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

  public MTTheme getSelectedTheme() {
    return selectedTheme;
  }

  public void setSelectedTheme(MTTheme selectedTheme) {
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
  public void loadState(MTConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  /**
   * Get the set highlight color
   *
   * @return the highlight color
   */
  public Color getHighlightColor() {
    return ColorUtil.fromHex(this.highlightColor);
  }

  /**
   * Set a new highlight color
   *
   * @param color the new hightlight color
   */
  public void setHighlightColor(@NotNull Color color) {
    highlightColor = ColorUtil.toHex(color);
  }

  /**
   * Return whether custom highlight is enabled
   *
   * @return true if enabled
   */
  public boolean isHighlightColorEnabled() {
    return this.highlightColorEnabled;
  }

  /**
   * Enable/Disable custom highlight
   *
   * @param enabled state
   */
  public void setHighlightColorEnabled(boolean enabled) {
    this.highlightColorEnabled = enabled;
  }

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
  public void setHighlightThickness(int thickness) {
    this.highlightThickness = thickness;
  }

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
  public void setIsContrastMode(boolean isContrastMode) {
    this.isContrastMode = isContrastMode;
  }

  public boolean getIsMaterialDesign() {
    return isMaterialDesign;
  }

  public void setIsMaterialDesign(boolean materialDesign) {
    isMaterialDesign = materialDesign;
  }

  /**
   * Checks whether the new highlightColor is different from the previous one
   *
   * @param color new highlight color
   * @return true if changed
   */
  public boolean isHighlightColorChanged(@NotNull Color color) {
    Color current = this.getHighlightColor();
    return !Objects.equals(current, color);
  }

  /**
   * Checks whether the highlight color enabled state has changed
   *
   * @param enabled new enabled state
   * @return true if changed
   */
  public boolean isHighlightColorEnabledChanged(boolean enabled) {
    return this.highlightColorEnabled != enabled;
  }

  /**
   * Checks whether the highlight thickness has changed
   *
   * @param thickness new thickness
   * @return true if changed
   */
  public boolean isHighlightThicknessChanged(int thickness) {
    return highlightThickness != thickness;
  }

  public boolean isContrastModeChanged(boolean isContrastMode) {
    return this.isContrastMode != isContrastMode;
  }

  public boolean isMaterialDesignChanged(boolean isMaterialDesign) {
    return this.isMaterialDesign != isMaterialDesign;
  }

  public boolean isBoldTabsChanged(boolean isBoldTabs) {
    return this.isBoldTabs != isBoldTabs;
  }

  /**
   * Fire an event to the application bus that the settings have changed
   */
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
                      .configChanged(this);
  }

  public boolean getIsBoldTabs() {
    return isBoldTabs;
  }

  public void setIsBoldTabs(boolean isBoldTabs) {
    this.isBoldTabs = isBoldTabs;
  }

  public String getAccentColor() {
    return accentColor;
  }

  public void setAccentColor(String accentColor) {
    this.accentColor = accentColor;
  }

  public String getWallpaper() {
    return wallpaper;
  }

  public void setWallpaper(String wallpaper) {
    this.wallpaper = wallpaper;
  }

  public boolean isWallpaperSet() {
    return wallpaperSet;
  }

  public void setIsWallpaperSet(boolean wallpaperSet) {
    this.wallpaperSet = wallpaperSet;
  }

  public boolean isWallpaperSetChanged(boolean isWallpaperSet) {
    return this.wallpaperSet != isWallpaperSet;
  }

  public boolean isWallpaperChanged(String wallpaper) {
    return !Objects.equals(this.wallpaper, wallpaper);
  }

  public boolean isUseMaterialIcons() {
    return useMaterialIcons;
  }

  public void setUseMaterialIcons(boolean useMaterialIcons) {
    this.useMaterialIcons = useMaterialIcons;
  }

  public boolean isMaterialIconsChanged(boolean useMaterialIcons) {
    return this.useMaterialIcons != useMaterialIcons;
  }

  public boolean isUseProjectViewDecorators() {
    return useProjectViewDecorators;
  }

  public void setUseProjectViewDecorators(boolean useProjectViewDecorators) {
    this.useProjectViewDecorators = useProjectViewDecorators;
  }

  public boolean isUseProjectViewDecoratorsChanged(boolean useProjectViewDecorators) {
    return this.useProjectViewDecorators != useProjectViewDecorators;
  }

  public boolean getHideFileIcons() {
    return hideFileIcons;
  }

  public void setHideFileIcons(boolean hideFileIcons) {
    this.hideFileIcons = hideFileIcons;
  }

  public boolean isHideFileIconsChanged(boolean hideFileIcons) {
    return this.hideFileIcons != hideFileIcons;
  }

  public boolean isCompactSidebar() {
    return compactSidebar;
  }

  public void setCompactSidebar(boolean compactSidebar) {
    this.compactSidebar = compactSidebar;
  }

  public boolean isCompactSidebarChanged(boolean compactSidebar) {
    return this.compactSidebar != compactSidebar;
  }

  public boolean isStatusBarTheme() {
    return statusBarTheme;
  }

  public void setIsStatusBarTheme(boolean isStatusBarTheme) {
    this.statusBarTheme = isStatusBarTheme;
  }

  public boolean isStatusBarThemeChanged(boolean statusBarTheme) {
    return this.statusBarTheme != statusBarTheme;
  }

  public int getTabsHeight() {
    return tabsHeight;
  }

  public void setTabsHeight(Integer tabsHeight) {
    this.tabsHeight = tabsHeight;
  }

  public boolean isTabsHeightChanged(Integer tabsHeight) {
    return this.tabsHeight != tabsHeight;
  }

  public boolean isMaterialTheme() {
    return materialTheme;
  }

  public void setIsMaterialTheme(boolean isMaterialTheme) {
    this.isMaterialTheme = isMaterialTheme;
  }

  public boolean isMaterialThemeChanged(boolean isMaterialTheme) {
    return this.isMaterialTheme != isMaterialTheme;
  }
}
