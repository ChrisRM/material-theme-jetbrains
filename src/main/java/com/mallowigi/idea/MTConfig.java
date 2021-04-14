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

package com.mallowigi.idea;

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Transient;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.config.enums.IndicatorStyles;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.mallowigi.idea.config.ui.MTForm;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.MTThemeFacade;
import com.mallowigi.idea.themes.MTThemes;
import com.mallowigi.idea.utils.MTAccents;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.rmi.server.UID;
import java.util.Objects;

@SuppressWarnings({"ClassWithTooManyFields",
  "ClassWithTooManyMethods",
  "OverlyComplexClass",
  "WeakerAccess",
  "PackageVisibleField",
  "MethodParameterOfConcreteClass",
  "MethodReturnOfConcreteClass",
  "OverlyLongMethod",
  "PublicMethodNotExposedInInterface",
  "StaticMethodOnlyUsedInOneClass",
  "ParameterHidesMemberVariable",
  "TransientFieldInNonSerializableClass"})
@State(
  name = "MaterialThemeConfig", //NON-NLS
  storages = @Storage("material_theme.xml") //NON-NLS
)
public final class MTConfig implements PersistentStateComponent<MTConfig>,
                                       MTBaseConfig<MTForm, MTConfig>, Cloneable {
  //region ~~~~~~~~~~~~~ CONSTANTS ~~~~~~~~~~~~~
  @NonNls
  public static final String DEFAULT_BG =
    "https://raw.githubusercontent.com/ChrisRM/material-theme-jetbrains/master/src/main/resources/themes/wall.jpg,60";
  public static final int MAX_HIGHLIGHT_THICKNESS = 5;
  public static final int MIN_HIGHLIGHT_THICKNESS = 1;
  public static final int MAX_INDICATOR_THICKNESS = 5;
  public static final int MIN_INDICATOR_THICKNESS = 1;
  public static final int MAX_TABS_HEIGHT = 60;
  public static final int DEFAULT_LINE_HEIGHT = 18;
  public static final int MIN_TABS_HEIGHT = 10;
  public static final int MAX_TREE_INDENT = 40;
  public static final int MIN_TREE_INDENT = 0;
  public static final int MAX_SIDEBAR_HEIGHT = 36;
  public static final int MIN_SIDEBAR_HEIGHT = DEFAULT_LINE_HEIGHT;
  public static final int MIN_FONT_SIZE = 6;
  public static final int MAX_FONT_SIZE = 24;
  public static final int MAX_TAB_INDEX = 6;
  public static final int DEFAULT_TAB_OPACITY = 50;
  public static final int DEFAULT_TAB_FONT_SIZE = 12;
  public static final int DEFAULT_TREE_FONT_SIZE = 12;
  public static final int DEFAULT_THICKNESS = 2;
  public static final int DEFAULT_LEFT_INDENT = 6;
  public static final int DEFAULT_RIGHT_INDENT = 10;
  public static final int DEFAULT_TAB_HEIGHT = 32;
  public static final int NATIVE_TAB_HEIGHT = 18;
  static final String ACCENT_COLOR = MTAccents.FUCHSIA.getHexColor();
  static final String SECOND_ACCENT_COLOR = MTAccents.TURQUOISE.getHexColor();
  //endregion
  @Property
  boolean accentMode = false;
  @Property
  boolean accentScrollbars = true;
  @SuppressWarnings("FieldHasSetterButNoGetter")
  @Property
  boolean allowDataCollection = false;
  @Property
  boolean borderedButtons = false;
  @Property
  boolean compactDropdowns = false;
  @Property
  boolean compactSidebar = false;
  @Property
  boolean darkTitleBar = false;
  @Property
  boolean fileStatusColorsEnabled = true;
  @Property
  boolean isHighlightColorEnabled = false;
  @Property
  boolean isCompactMenus = false;
  @Property
  boolean isCompactStatusBar = false;
  @Property
  boolean isCompactTables = false;
  @Property
  boolean isContrastMode = false;
  @Property
  boolean isCustomTreeIndentEnabled = false;
  @Property
  boolean isHighContrast = false;
  @Property
  boolean isMaterialDesign = true;
  @Property
  boolean isMaterialTheme = true;
  @Property
  boolean isStyledDirectories = false;
  @Property
  boolean isTabsShadow = true;
  @Property
  boolean isWizardShown = false;
  @Property
  boolean overrideAccentColor = true;
  @Property
  boolean pristineConfig = true;
  @Property
  boolean statusBarTheme = true;
  @Property
  boolean themedScrollbars = true;
  @Property
  boolean treeFontSizeEnabled = false;
  @Property
  boolean tabFontSizeEnabled = false;
  @Property
  boolean upperCaseButtons = true;
  @Property
  boolean upperCaseTabs = false;
  @Property
  @Deprecated
  boolean useMaterialFont = true;
  @Property
  boolean useMaterialFont2 = false;
  @Property
  boolean useMaterialWallpapers = false;
  @Property
  boolean useColoredDirectories = true;
  @Property
  boolean useProjectFrame = false;
  @Property
  IndicatorStyles indicatorStyle = IndicatorStyles.BORDER;
  @Property
  int customSidebarHeight = DEFAULT_LINE_HEIGHT;
  @Property
  int tabOpacity = DEFAULT_TAB_OPACITY;
  @Property
  int treeFontSize = DEFAULT_TREE_FONT_SIZE;
  @Property
  int tabFontSize = DEFAULT_TAB_FONT_SIZE;
  @Property
  Integer highlightThickness = DEFAULT_THICKNESS;
  @Property
  Integer indicatorThickness = DEFAULT_THICKNESS;
  @Property
  Integer leftTreeIndent = DEFAULT_LEFT_INDENT;
  @Property
  Integer rightTreeIndent = DEFAULT_RIGHT_INDENT;
  @Property
  Integer settingsSelectedTab = 0;
  @Property
  Integer tabsHeight = DEFAULT_TAB_HEIGHT;
  @Property
  String accentColor = ACCENT_COLOR;
  @Property
  String secondAccentColor = SECOND_ACCENT_COLOR;
  @Property
  String highlightColor = ACCENT_COLOR;
  @NonNls
  @Property
  String selectedTheme = MTThemes.OCEANIC.getName();
  @Property
  String userId = new UID().toString();
  @Property
  String version = "6.3.0";
  @Property
  TabHighlightPositions tabHighlightPosition = TabHighlightPositions.DEFAULT;
  @Property
  private boolean codeAdditionsEnabled = true;
  @Property
  private boolean enforcedLanguageAdditions = false;
  @Property
  private boolean stripedToolWindowsEnabled = true;

  @Transient
  private transient boolean isReset = false;

  @Transient
  private transient boolean isPremium = false;

  //endregion

  /**
   * Represents an instance of the configuration
   */
  @SuppressWarnings({
    "ImplicitCallToSuper",
    "PublicConstructor"})
  public MTConfig() {
    isPremium = MTLicenseChecker.isLicensed();
  }

  /**
   * Get instance of the config from the ServiceManager
   *
   * @return the MTConfig instance
   */
  public static MTConfig getInstance() {
    return ServiceManager.getService(MTConfig.class);
  }

  /**
   * Clone the current instance
   *
   * @return Object
   */
  @SuppressWarnings("MethodDoesntCallSuperMethod")
  @Override
  public MTConfig clone() {
    return XmlSerializerUtil.createCopy(this);
  }

  /**
   * Get the state of MTConfig
   */
  @NotNull
  @Override
  public MTConfig getState() {
    isPremium = true;
    final MTConfig clone = clone();
    isPremium = MTLicenseChecker.isLicensed();
    return clone;
  }

  /**
   * Load the state from XML
   *
   * @param state the MTConfig instance
   */
  @Override
  public void loadState(@NotNull final MTConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  @Override
  public void fireBeforeChanged(final MTForm form) {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
                      .beforeConfigChanged(this, form);
  }

  /**
   * Fire an event to the application bus that the settings have changed
   */
  @Override
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
                      .configChanged(this);
  }

  /**
   * Apply settings according to the form
   *
   * @param form form to read
   */
  @Override
  @SuppressWarnings({"CallToSimpleSetterFromWithinClass",
    "FeatureEnvy",
    "Duplicates"})
  public void applySettings(final MTForm form) {
    // First fire before change
    fireBeforeChanged(form);
    isReset = false;

    setSettingsSelectedTab(form.getSelectedTabIndex());

    setAccentColor(ColorUtil.toHex(form.getCustomAccentColor()));
    setAccentMode(form.isAccentMode());
    setAccentScrollbars(form.isAccentScrollbars());
    setBorderedButtons(form.isBorderedButtons());
    setCodeAdditionsEnabled(form.isCodeAdditionsEnabled());
    setCompactDropdowns(form.isCompactDropdowns());
    setCompactSidebar(form.isCompactSidebar());
    setCustomSidebarHeight(form.getCustomSidebarHeight());
    setDarkTitleBar(form.isDarkTitleBar());
    setFileStatusColorsEnabled(form.isFileStatusColors());
    setHighlightColor(form.getHighlightColor());
    setHighlightColorEnabled(form.isHighlightColorEnabled());
    setHighlightThickness(form.getHighlightThickness());
    setIndicatorStyle(form.getIndicatorStyle());
    setIndicatorThickness(form.getIndicatorThickness());
    setEnforcedLanguageAdditions(form.isEnforcedLanguageAdditions());
    setCompactMenus(form.isCompactMenus());
    setCompactStatusBar(form.isCompactStatusBar());
    setCompactTables(form.isCompactTables());
    setContrastMode(form.isContrastMode());
    setCustomTreeIndentEnabled(form.isCustomTreeIndent());
    setHighContrast(form.isHighContrast());
    setStyledDirectories(form.isStyledDirectories());
    setIsTabsShadow(form.isTabsShadow());
    setUpperCaseTabs(form.isUpperCaseTabs());
    setLeftTreeIndent(form.getLeftTreeIndent());
    setOverrideAccentColor(form.isOverrideAccents());
    pristineConfig = false;
    setRightTreeIndent(form.getRightTreeIndent());
    setSelectedTheme(form.getTheme());
    setSecondAccentColor(ColorUtil.toHex(form.getSecondAccentColor()));
    setStripedToolWindowsEnabled(form.isStripedToolWindowsEnabled());
    setTabHighlightPosition(form.getTabHighlightPosition());
    setTabsHeight(form.getTabsHeight());
    setThemedScrollbars(form.isThemedScrollbars());
    setTabFontSize(form.getTabFontSize());
    setTabFontSizeEnabled(form.isTabFontSizeEnabled());
    setTreeFontSize(form.getTreeFontSize());
    setTreeFontSizeEnabled(form.isTreeFontSizeEnabled());
    setUpperCaseButtons(form.isUpperCaseButtons());
    setUseMaterialFont2(form.isUseMaterialFonts());
    setUseMaterialWallpapers(form.isUseMaterialWallpapers());
    setUseColoredDirectories(form.isUseColoredDirectories());
    setUseProjectFrame(form.isUseProjectFrame());

    // Then fire changed
    fireChanged();
  }

  @Override
  public void resetSettings() {
    isReset = true;
    accentColor = ACCENT_COLOR;
    accentMode = false;
    accentScrollbars = true;
    borderedButtons = false;
    codeAdditionsEnabled = true;
    enforcedLanguageAdditions = false;
    compactDropdowns = false;
    compactSidebar = false;
    customSidebarHeight = DEFAULT_LINE_HEIGHT;
    darkTitleBar = false;
    fileStatusColorsEnabled = true;
    highlightColor = ACCENT_COLOR;
    isHighlightColorEnabled = false;
    highlightThickness = DEFAULT_THICKNESS;
    indicatorStyle = IndicatorStyles.BORDER;
    indicatorThickness = DEFAULT_THICKNESS;
    isCompactMenus = false;
    isCompactStatusBar = false;
    isCompactTables = false;
    isContrastMode = false;
    isCustomTreeIndentEnabled = false;
    isHighContrast = false;
    isMaterialDesign = true;
    isMaterialTheme = true;
    isStyledDirectories = false;
    isTabsShadow = true;
    leftTreeIndent = 6;
    overrideAccentColor = true;
    pristineConfig = true;
    rightTreeIndent = 6;
    secondAccentColor = SECOND_ACCENT_COLOR;
    selectedTheme = MTThemes.OCEANIC.getName();
    statusBarTheme = true;
    stripedToolWindowsEnabled = true;
    tabHighlightPosition = TabHighlightPositions.DEFAULT;
    tabOpacity = DEFAULT_TAB_OPACITY;
    tabsHeight = DEFAULT_TAB_HEIGHT;
    themedScrollbars = true;
    tabFontSize = DEFAULT_TAB_FONT_SIZE;
    tabFontSizeEnabled = false;
    treeFontSize = DEFAULT_TREE_FONT_SIZE;
    treeFontSizeEnabled = false;
    upperCaseButtons = true;
    upperCaseTabs = false;
    useMaterialFont = true;
    useMaterialFont2 = false;
    useMaterialWallpapers = false;
    useColoredDirectories = true;
    useProjectFrame = false;
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  public boolean needsRestart(final MTForm form) {
    boolean modified = isTreeFontSizeEnabledChanged(form.isTreeFontSizeEnabled());
    modified = modified || isTreeFontSizeChanged(form.getTreeFontSize());
    modified = modified || isDarkTitleBarChanged(form.isDarkTitleBar());
    modified = modified || isStripedToolWindowsChanged(form.isStripedToolWindowsEnabled());

    return modified;
  }

  /**
   * Alias for @getNativePropertiesAsJson
   *
   * @return JSONObject
   * @throws JSONException when
   */
  public JSONObject asJson() throws JSONException {
    return getNativePropertiesAsJson();
  }

  //region Selected theme

  /**
   * Sets the selectedTheme
   *
   * @param selectedTheme the selectedTheme of this MTConfig object.
   */
  public void setSelectedTheme(final MTThemeFacade selectedTheme) {
    this.selectedTheme = selectedTheme.getThemeId();
  }

  /**
   * Checks if the theme has changed
   *
   * @param theme of type MTThemeFacade
   * @return boolean
   */
  public boolean isSelectedThemeChanged(final MTThemeFacade theme) {
    return !selectedTheme.equals(theme.getName());
  }

  /**
   * Return the selected theme
   */
  public MTThemeFacade getSelectedTheme() {
    final MTThemeFacade themeFor = MTThemes.getThemeFor(selectedTheme);
    if (!isPremium && themeFor != null && themeFor.isPremium()) {
      return MTThemes.OCEANIC;
    }

    return ObjectUtils.notNull(themeFor, MTThemes.OCEANIC);
  }
  //endregion

  //region Version

  /**
   * Sets the version of the plugin
   *
   * @param version the version of this MTConfig object.
   */
  public void setVersion(final String version) {
    this.version = version;
  }

  /**
   * Returns the version of the plugin
   *
   * @return the version (type String) of this MTConfig object.
   */
  public String getVersion() {
    return version;
  }

  //endregion

  //region Tabs Highlight

  /**
   * Set a new tab highlight color
   *
   * @param color the new highlight color
   */
  public void setHighlightColor(@NotNull final Color color) {
    highlightColor = ColorUtil.toHex(color);
  }

  /**
   * Checks whether the tab highlightColor is different from the previous one
   *
   * @param color new highlight color
   * @return true if changed
   */
  public boolean isHighlightColorChanged(@NotNull final Color color) {
    // Old code is old
    final Color current = getHighlightColor();
    return !Objects.equals(current, color);
  }

  /**
   * Return whether custom highlight is enabled
   *
   * @return true if enabled
   */
  public boolean isHighlightColorEnabled() {
    return isPremium && isHighlightColorEnabled;
  }

  /**
   * Enable/Disable custom highlight
   *
   * @param enabled state
   */
  public void setHighlightColorEnabled(final boolean enabled) {
    isHighlightColorEnabled = enabled;
  }

  /**
   * Checks whether the highlight color enabled state has changed
   *
   * @param enabled new enabled state
   * @return true if changed
   */
  public boolean isHighlightColorEnabledChanged(final boolean enabled) {
    return isHighlightColorEnabled != enabled;
  }

  /**
   * Get the tab highlight color
   *
   * @return the highlight color
   */
  public Color getHighlightColor() {
    return ColorUtil.fromHex(highlightColor);
  }

  //endregion

  //region Tab highlight thickness

  /**
   * Set highlight thickness
   *
   * @param thickness thickness value
   */
  public void setHighlightThickness(final int thickness) {
    if (MIN_HIGHLIGHT_THICKNESS > thickness || thickness > MAX_HIGHLIGHT_THICKNESS) {
      return;
    }
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

  /**
   * Get user's highlight thickness
   *
   * @return highlight thickness
   */
  public int getHighlightThickness() {
    return isPremium ? highlightThickness : 2;
  }
  // endregion

  //region Tab Placement
  public TabHighlightPositions getTabHighlightPosition() {
    return isPremium ? tabHighlightPosition : TabHighlightPositions.BOTTOM;
  }

  public void setTabHighlightPosition(final TabHighlightPositions tabHighlightPosition) {
    this.tabHighlightPosition = tabHighlightPosition;
  }

  public boolean isTabHighlightPositionChanged(final TabHighlightPositions tabHighlightPosition) {
    return this.tabHighlightPosition != tabHighlightPosition;
  }
  //endregion

  //region Contrast mode

  /**
   * Enable/disable contrast mode
   *
   * @param isContrastMode contrast mode value
   */
  public void setContrastMode(final boolean isContrastMode) {
    this.isContrastMode = isContrastMode;
  }

  /**
   * Checks whether the contrast mode has changed
   *
   * @param isContrastMode of type boolean
   * @return boolean
   */
  public boolean isContrastModeChanged(final boolean isContrastMode) {
    return this.isContrastMode != isContrastMode;
  }

  /**
   * Checks whether we are in contrast mode
   *
   * @return true if contrast mode
   */
  public boolean isContrastMode() {
    return isContrastMode;
  }

  //endregion

  //region Styled Directories

  /**
   * Enable/Disable the styled directories
   *
   * @param isStyledDirectories the isStyledDirectories of this MTConfig object.
   */
  public void setStyledDirectories(final boolean isStyledDirectories) {
    this.isStyledDirectories = isStyledDirectories;
  }

  /**
   * ...
   *
   * @param isStyledDirectories of type boolean
   * @return boolean
   */
  public boolean isStyledDirectoriesChanged(final boolean isStyledDirectories) {
    return this.isStyledDirectories != isStyledDirectories;
  }

  /**
   * Returns whether the styled directories are enabled
   *
   * @return the isStyledDirectories (type boolean) of this MTConfig object.
   */
  public boolean isStyledDirectories() {
    return isPremium && isStyledDirectories;
  }

  //endregion

  //region Accent Color

  /**
   * Sets the accentColor of this MTConfig object.
   *
   * @param accentColor the accentColor of this MTConfig object.
   */
  public void setAccentColor(final String accentColor) {
    this.accentColor = accentColor;
  }

  /**
   * ...
   *
   * @param customAccentColor of type Color
   * @return boolean
   */
  public boolean isAccentColorChanged(final Color customAccentColor) {
    return !Objects.equals(accentColor, ColorUtil.toHex(customAccentColor));
  }

  /**
   * Returns the accentColor of this MTConfig object.
   *
   * @return the accentColor (type String) of this MTConfig object.
   */
  public String getAccentColor() {
    return accentColor;
  }

  //endregion

  //region Override Accent Color

  /**
   * Sets the overrideAccentColor of this MTConfig object.
   *
   * @param overrideAccentColor the overrideAccentColor of this MTConfig object.
   */
  public void setOverrideAccentColor(final boolean overrideAccentColor) {
    this.overrideAccentColor = overrideAccentColor;
  }

  /**
   * ...
   *
   * @param overrideAccents of type boolean
   * @return boolean
   */
  public boolean isOverrideAccentColorChanged(final boolean overrideAccents) {
    return overrideAccentColor != overrideAccents;
  }

  /**
   * Returns the overrideAccentColor of this MTConfig object.
   *
   * @return the overrideAccentColor (type boolean) of this MTConfig object.
   */
  public boolean isOverrideAccentColor() {
    return !isPremium || overrideAccentColor;
  }

  //endregion

  //region Second Accent Color

  /**
   * Sets the accentColor of this MTConfig object.
   *
   * @param accentColor the accentColor of this MTConfig object.
   */
  public void setSecondAccentColor(final String accentColor) {
    secondAccentColor = accentColor;
  }

  /**
   * ...
   *
   * @param color of type Color
   * @return boolean
   */
  public boolean isSecondAccentColorChanged(final Color color) {
    return !Objects.equals(secondAccentColor, ColorUtil.toHex(color));
  }

  /**
   * Returns the accentColor of this MTConfig object.
   *
   * @return the accentColor (type String) of this MTConfig object.
   */
  public String getSecondAccentColor() {
    return secondAccentColor;
  }

  //endregion

  //region Compact Sidebar

  /**
   * Sets the compactSidebar of this MTConfig object.
   *
   * @param compactSidebar the compactSidebar of this MTConfig object.
   */
  public void setCompactSidebar(final boolean compactSidebar) {
    this.compactSidebar = compactSidebar;
  }

  /**
   * ...
   *
   * @param compactSidebar of type boolean
   * @return boolean
   */
  public boolean isCompactSidebarChanged(final boolean compactSidebar) {
    return this.compactSidebar != compactSidebar;
  }

  /**
   * Returns the compactSidebar of this MTConfig object.
   *
   * @return the compactSidebar (type boolean) of this MTConfig object.
   */
  public boolean isCompactSidebar() {
    return !isPremium || compactSidebar;
  }

  //endregion

  //region Custom Sidebar Height

  /**
   * Sets the customSidebarHeight of this MTConfig object.
   *
   * @param customSidebarHeight the customSidebarHeight of this MTConfig object.
   */
  public void setCustomSidebarHeight(final Integer customSidebarHeight) {
    if (MIN_SIDEBAR_HEIGHT > customSidebarHeight || customSidebarHeight > MAX_SIDEBAR_HEIGHT) {
      return;
    }
    this.customSidebarHeight = customSidebarHeight;
  }

  /**
   * ...
   *
   * @param customSidebarHeight of type Integer
   * @return boolean
   */
  public boolean isCustomSidebarHeightChanged(final Integer customSidebarHeight) {
    return this.customSidebarHeight != customSidebarHeight;
  }

  /**
   * Returns the customSidebarHeight of this MTConfig object.
   *
   * @return the customSidebarHeight (type int) of this MTConfig object.
   */
  public int getCustomSidebarHeight() {
    return customSidebarHeight;
  }

  //endregion

  //region Tabs Height

  /**
   * Sets the tabsHeight of this MTConfig object.
   *
   * @param tabsHeight the tabsHeight of this MTConfig object.
   */
  public void setTabsHeight(final Integer tabsHeight) {
    this.tabsHeight = tabsHeight;
  }

  /**
   * ...
   *
   * @param tabsHeight of type Integer
   * @return boolean
   */
  public boolean isTabsHeightChanged(final Integer tabsHeight) {
    return !Objects.equals(this.tabsHeight, tabsHeight);
  }

  /**
   * Returns the tabsHeight of this MTConfig object.
   *
   * @return the tabsHeight (type int) of this MTConfig object.
   */
  public int getTabsHeight() {
    return tabsHeight;
  }

  //endregion

  //region Custom Tree Indents

  /**
   * Sets the rightTreeIndent of this MTConfig object.
   *
   * @param rightTreeIndent the rightTreeIndent of this MTConfig object.
   */
  public void setRightTreeIndent(final Integer rightTreeIndent) {
    this.rightTreeIndent = rightTreeIndent;
  }

  /**
   * Returns the leftTreeIndent of this MTConfig object.
   *
   * @return the leftTreeIndent (type int) of this MTConfig object.
   */
  public int getLeftTreeIndent() {
    return leftTreeIndent;
  }

  /**
   * Sets the leftTreeIndent of this MTConfig object.
   *
   * @param leftTreeIndent the leftTreeIndent of this MTConfig object.
   */
  public void setLeftTreeIndent(final Integer leftTreeIndent) {
    this.leftTreeIndent = leftTreeIndent;
  }

  /**
   * Returns the customTreeIndent of this MTConfig object.
   *
   * @return the customTreeIndent (type boolean) of this MTConfig object.
   */
  public boolean isCustomTreeIndentEnabled() {
    return isPremium && isCustomTreeIndentEnabled;
  }

  /**
   * Sets the isCustomTreeIndent of this MTConfig object.
   *
   * @param isCustomTreeIndent the isCustomTreeIndent of this MTConfig object.
   */
  public void setCustomTreeIndentEnabled(final boolean isCustomTreeIndent) {
    isCustomTreeIndentEnabled = isCustomTreeIndent;
  }

  /**
   * ...
   *
   * @param rightTreeIndent of type int
   * @return boolean
   */
  public boolean isRightTreeIndentChanged(final int rightTreeIndent) {
    return this.rightTreeIndent != rightTreeIndent;
  }

  /**
   * ...
   *
   * @param leftTreeIndent of type int
   * @return boolean
   */
  public boolean isLeftTreeIndentChanged(final int leftTreeIndent) {
    return this.leftTreeIndent != leftTreeIndent;
  }

  /**
   * ...
   *
   * @param customTreeIndentEnabled of type boolean
   * @return boolean
   */
  public boolean isCustomTreeIndentChanged(final boolean customTreeIndentEnabled) {
    return isCustomTreeIndentEnabled != customTreeIndentEnabled;
  }

  /**
   * Returns the rightTreeIndent of this MTConfig object.
   *
   * @return the rightTreeIndent (type int) of this MTConfig object.
   */
  public int getRightTreeIndent() {
    return rightTreeIndent;
  }

  //endregion

  //region Themed Scrollbars

  /**
   * Sets the themedScrollbars of this MTConfig object.
   *
   * @param themedScrollbars the themedScrollbars of this MTConfig object.
   */
  public void setThemedScrollbars(final boolean themedScrollbars) {
    this.themedScrollbars = themedScrollbars;
  }

  /**
   * ...
   *
   * @param themedScrollbars of type boolean
   * @return boolean
   */
  public boolean isThemedScrollbarsChanged(final boolean themedScrollbars) {
    return this.themedScrollbars != themedScrollbars;
  }

  /**
   * Returns the accentScrollbars of this MTConfig object.
   *
   * @return the accentScrollbars (type boolean) of this MTConfig object.
   */
  public boolean isAccentScrollbars() {
    return accentScrollbars;
  }

  /**
   * Sets the accentScrollbars of this MTConfig object.
   *
   * @param accentScrollbars the accentScrollbars of this MTConfig object.
   */
  public void setAccentScrollbars(final boolean accentScrollbars) {
    this.accentScrollbars = accentScrollbars;
  }

  /**
   * ...
   *
   * @param accentScrollbars of type boolean
   * @return boolean
   */
  public boolean isAccentScrollbarsChanged(final boolean accentScrollbars) {
    return this.accentScrollbars != accentScrollbars;
  }

  /**
   * Returns the themedScrollbars of this MTConfig object.
   *
   * @return the themedScrollbars (type boolean) of this MTConfig object.
   */
  public boolean isThemedScrollbars() {
    return themedScrollbars;
  }

  //endregion

  //region Compact Status Bar

  /**
   * Sets the isCompactStatusBar of this MTConfig object.
   *
   * @param isCompactStatusBar the isCompactStatusBar of this MTConfig object.
   */
  public void setCompactStatusBar(final boolean isCompactStatusBar) {
    this.isCompactStatusBar = isCompactStatusBar;
  }

  /**
   * ...
   *
   * @param compactStatusBar of type boolean
   * @return boolean
   */
  public boolean isCompactStatusBarChanged(final boolean compactStatusBar) {
    return isCompactStatusBar != compactStatusBar;
  }

  /**
   * Returns the compactStatusBar of this MTConfig object.
   *
   * @return the compactStatusBar (type boolean) of this MTConfig object.
   */
  public boolean isCompactStatusBar() {
    return isCompactStatusBar;
  }

  //endregion

  //region Compact Tables

  /**
   * Sets the isCompactTables of this MTConfig object.
   *
   * @param isCompactTables the isCompactTables of this MTConfig object.
   */
  public void setCompactTables(final boolean isCompactTables) {
    this.isCompactTables = isCompactTables;
  }

  /**
   * ...
   *
   * @param compactTables of type boolean
   * @return boolean
   */
  public boolean isCompactTablesChanged(final boolean compactTables) {
    return isCompactTables != compactTables;
  }

  /**
   * Returns the compactTables of this MTConfig object.
   *
   * @return the compactTables (type boolean) of this MTConfig object.
   */
  public boolean isCompactTables() {
    return isCompactTables;
  }

  //endregion

  //region Compact Menus

  /**
   * Sets the isCompactMenus of this MTConfig object.
   *
   * @param compactMenus the isCompactMenus of this MTConfig object.
   */
  public void setCompactMenus(final boolean compactMenus) {
    isCompactMenus = compactMenus;
  }

  /**
   * ...
   *
   * @param compactMenus of type boolean
   * @return boolean
   */
  public boolean isCompactMenusChanged(final boolean compactMenus) {
    return isCompactMenus != compactMenus;
  }

  /**
   * Returns the compactMenus of this MTConfig object.
   *
   * @return the compactMenus (type boolean) of this MTConfig object.
   */
  public boolean isCompactMenus() {
    return isCompactMenus;
  }

  // endregion

  //region Uppercase tabs

  /**
   * Sets the isUpperCaseTabs of this MTConfig object.
   *
   * @param isUpperCaseTabs the isUpperCaseTabs of this MTConfig object.
   */
  public void setUpperCaseTabs(final boolean isUpperCaseTabs) {
    upperCaseTabs = isUpperCaseTabs;
  }

  /**
   * ...
   *
   * @param upperCaseTabs of type boolean
   * @return boolean
   */
  public boolean isUpperCaseTabsChanged(final boolean upperCaseTabs) {
    return this.upperCaseTabs != upperCaseTabs;
  }

  /**
   * Returns the upperCaseTabs of this MTConfig object.
   *
   * @return the upperCaseTabs (type boolean) of this MTConfig object.
   */
  public boolean isUpperCaseTabs() {
    return isPremium && upperCaseTabs;
  }

  // endregion

  //region Dark titlebar

  /**
   * Sets the darkTitleBar of this MTConfig object.
   *
   * @param darkTitleBar the darkTitleBar of this MTConfig object.
   */
  public void setDarkTitleBar(final boolean darkTitleBar) {
    this.darkTitleBar = darkTitleBar;
  }

  /**
   * ...
   *
   * @param darkTitleBar of type boolean
   * @return boolean
   */
  public boolean isDarkTitleBarChanged(final boolean darkTitleBar) {
    return this.darkTitleBar != darkTitleBar;
  }

  /**
   * Returns the darkTitleBar of this MTConfig object.
   *
   * @return the darkTitleBar (type boolean) of this MTConfig object.
   */
  public boolean isDarkTitleBar() {
    return isPremium && darkTitleBar;
  }

  //endregion

  //region Indicator Styles

  /**
   * Sets the indicatorStyle of this MTConfig object.
   *
   * @param indicatorStyle the indicatorStyle of this MTConfig object.
   */
  public void setIndicatorStyle(final IndicatorStyles indicatorStyle) {
    this.indicatorStyle = indicatorStyle;
  }

  /**
   * ...
   *
   * @param indicatorStyle of type IndicatorStyles
   * @return boolean
   */
  public boolean isIndicatorStyleChanged(final IndicatorStyles indicatorStyle) {
    return this.indicatorStyle != indicatorStyle;
  }

  /**
   * Returns the indicatorStyle of this MTConfig object.
   *
   * @return the indicatorStyle (type IndicatorStyles) of this MTConfig object.
   */
  public IndicatorStyles getIndicatorStyle() {
    return isPremium ? indicatorStyle : IndicatorStyles.NONE;
  }

  // endregion

  // region indicator thickness

  /**
   * Sets the indicatorThickness of this MTConfig object.
   *
   * @param indicatorThickness the indicatorThickness of this MTConfig object.
   */
  public void setIndicatorThickness(final int indicatorThickness) {
    this.indicatorThickness = indicatorThickness;
  }

  /**
   * ...
   *
   * @param indicatorThickness of type int
   * @return boolean
   */
  public boolean isIndicatorThicknessChanged(final int indicatorThickness) {
    return this.indicatorThickness != indicatorThickness;
  }

  /**
   * Returns the indicatorThickness of this MTConfig object.
   *
   * @return the indicatorThickness (type Integer) of this MTConfig object.
   */
  public Integer getIndicatorThickness() {
    return indicatorThickness;
  }

  // endregion

  // region Material fonts

  /**
   * Sets the useMaterialFont of this MTConfig object.
   *
   * @param useMaterialFont the useMaterialFont of this MTConfig object.
   */
  public void setUseMaterialFont2(final boolean useMaterialFont) {
    useMaterialFont2 = useMaterialFont;
  }

  /**
   * ...
   *
   * @param useMaterialFont of type boolean
   * @return boolean
   */
  public boolean isUseMaterialFontChanged(final boolean useMaterialFont) {
    return useMaterialFont2 != useMaterialFont;
  }

  /**
   * Returns the useMaterialFont of this MTConfig object.
   *
   * @return the useMaterialFont (type boolean) of this MTConfig object.
   */
  public boolean isUseMaterialFont2() {
    return isPremium && useMaterialFont2;
  }

  //endregion

  // region Tab Font Size

  /**
   * Sets the tabFontSize of this MTConfig object.
   *
   * @param tabFontSize the tabFontSize of this MTConfig object.
   */
  public void setTabFontSize(final int tabFontSize) {
    this.tabFontSize = tabFontSize;
  }

  /**
   * ...
   *
   * @param tabFontSize of type Integer
   * @return boolean
   */
  public boolean isTabFontSizeChanged(final Integer tabFontSize) {
    return this.tabFontSize != tabFontSize;
  }

  /**
   * Returns the tabFontSizeEnabled of this MTConfig object.
   *
   * @return the tabFontSizeEnabled (type boolean) of this MTConfig object.
   */
  public boolean isTabFontSizeEnabled() {
    return isPremium && tabFontSizeEnabled;
  }

  /**
   * Sets the tabFontSizeEnabled of this MTConfig object.
   *
   * @param tabFontSizeEnabled the tabFontSizeEnabled of this MTConfig object.
   */
  public void setTabFontSizeEnabled(final boolean tabFontSizeEnabled) {
    this.tabFontSizeEnabled = tabFontSizeEnabled;
  }

  /**
   * ...
   *
   * @param tabFontSizeEnabled of type boolean
   * @return boolean
   */
  public boolean isTabFontSizeEnabledChanged(final boolean tabFontSizeEnabled) {
    return this.tabFontSizeEnabled != tabFontSizeEnabled;
  }

  /**
   * Returns the treeFontSize of this MTConfig object.
   *
   * @return the treeFontSize (type int) of this MTConfig object.
   */
  public int getTabFontSize() {
    return tabFontSize;
  }

  // endregion

  //region Compact dropdowns

  /**
   * Sets the compactDropdowns of this MTConfig object.
   *
   * @param compactDropdowns the compactDropdowns of this MTConfig object.
   */
  public void setCompactDropdowns(final boolean compactDropdowns) {
    this.compactDropdowns = compactDropdowns;
  }

  /**
   * ...
   *
   * @param isCompactDropdowns of type boolean
   * @return boolean
   */
  public boolean isCompactDropdownsChanged(final boolean isCompactDropdowns) {
    return compactDropdowns != isCompactDropdowns;
  }

  /**
   * Returns the compactDropdowns of this MTConfig object.
   *
   * @return the compactDropdowns (type boolean) of this MTConfig object.
   */
  public boolean isCompactDropdowns() {
    return compactDropdowns;
  }

  //endregion

  //region UpperCase Buttons

  /**
   * Sets the upperCaseButtons of this MTConfig object.
   *
   * @param upperCaseButtons the upperCaseButtons of this MTConfig object.
   */
  public void setUpperCaseButtons(final boolean upperCaseButtons) {
    this.upperCaseButtons = upperCaseButtons;
  }

  /**
   * ...
   *
   * @param isUppercaseButtons of type boolean
   * @return boolean
   */
  public boolean isUpperCaseButtonsChanged(final boolean isUppercaseButtons) {
    return upperCaseButtons != isUppercaseButtons;
  }

  /**
   * Returns the upperCaseButtons of this MTConfig object.
   *
   * @return the upperCaseButtons (type boolean) of this MTConfig object.
   */
  public boolean isUpperCaseButtons() {
    return upperCaseButtons;
  }

  //endregion

  //region Bordered Buttons

  /**
   * Sets the borderedButtons of this MTConfig object.
   *
   * @param borderedButtons the borderedButtons of this MTConfig object.
   */
  public void setBorderedButtons(final boolean borderedButtons) {
    this.borderedButtons = borderedButtons;
  }

  public boolean isBorderedButtonsChanged(final boolean isBorderedButtons) {
    return borderedButtons != isBorderedButtons;
  }

  /**
   * Returns the borderedButtons of this MTConfig object.
   *
   * @return the borderedButtons (type boolean) of this MTConfig object.
   */
  public boolean isBorderedButtons() {
    return isPremium && borderedButtons;
  }

  //endregion

  // region Tree Font Size

  /**
   * Sets the treeFontSize of this MTConfig object.
   *
   * @param treeFontSize the treeFontSize of this MTConfig object.
   */
  public void setTreeFontSize(final int treeFontSize) {
    this.treeFontSize = treeFontSize;
  }

  /**
   * ...
   *
   * @param treeFontSize of type Integer
   * @return boolean
   */
  public boolean isTreeFontSizeChanged(final Integer treeFontSize) {
    return this.treeFontSize != treeFontSize;
  }

  /**
   * Returns the treeFontSizeEnabled of this MTConfig object.
   *
   * @return the treeFontSizeEnabled (type boolean) of this MTConfig object.
   */
  public boolean isTreeFontSizeEnabled() {
    return isPremium && treeFontSizeEnabled;
  }

  /**
   * Sets the treeFontSizeEnabled of this MTConfig object.
   *
   * @param treeFontSizeEnabled the treeFontSizeEnabled of this MTConfig object.
   */
  public void setTreeFontSizeEnabled(final boolean treeFontSizeEnabled) {
    this.treeFontSizeEnabled = treeFontSizeEnabled;
  }

  /**
   * ...
   *
   * @param treeFontSizeEnabled of type boolean
   * @return boolean
   */
  public boolean isTreeFontSizeEnabledChanged(final boolean treeFontSizeEnabled) {
    return this.treeFontSizeEnabled != treeFontSizeEnabled;
  }

  /**
   * Returns the treeFontSize of this MTConfig object.
   *
   * @return the treeFontSize (type int) of this MTConfig object.
   */
  public int getTreeFontSize() {
    return treeFontSize;
  }

  // endregion

  //region File Status Colors

  /**
   * Sets the fileStatusColorsEnabled of this MTConfig object.
   *
   * @param enabled the fileStatusColorsEnabled of this MTConfig object.
   */
  public void setFileStatusColorsEnabled(final boolean enabled) {
    fileStatusColorsEnabled = enabled;
  }

  /**
   * ...
   *
   * @param fileStatusColors of type boolean
   * @return boolean
   */
  public boolean isFileStatusColorsEnabledChanged(final boolean fileStatusColors) {
    return fileStatusColorsEnabled != fileStatusColors;
  }

  /**
   * Returns the fileStatusColorsEnabled of this MTConfig object.
   *
   * @return the fileStatusColorsEnabled (type boolean) of this MTConfig object.
   */
  public boolean isFileStatusColorsEnabled() {
    return isPremium && fileStatusColorsEnabled;
  }

  //endregion

  //region High Contrast

  /**
   * Sets the isHighContrast of this MTConfig object.
   *
   * @param isHighContrast the isHighContrast of this MTConfig object.
   */
  public void setHighContrast(final boolean isHighContrast) {
    this.isHighContrast = isHighContrast;
  }

  /**
   * ...
   *
   * @param isHighContrast of type boolean
   * @return boolean
   */
  public boolean isHighContrastChanged(final boolean isHighContrast) {
    return this.isHighContrast != isHighContrast;
  }

  /**
   * Returns the isHighContrast of this MTConfig object.
   *
   * @return the isHighContrast (type boolean) of this MTConfig object.
   */
  public boolean isHighContrast() {
    return isPremium && isHighContrast;
  }

  //endregion

  //region Tabs Shadow

  /**
   * Returns the tabsShadow of this MTConfig object.
   *
   * @return the tabsShadow (type boolean) of this MTConfig object.
   */
  public boolean isTabsShadow() {
    return isPremium && isTabsShadow;
  }

  /**
   * ...
   *
   * @param tabsShadow of type boolean
   * @return boolean
   */
  public boolean isTabsShadowChanged(final boolean tabsShadow) {
    return isTabsShadow != tabsShadow;
  }

  /**
   * Sets the isTabsShadow of this MTConfig object.
   *
   * @param isTabsShadow the isTabsShadow of this MTConfig object.
   */
  public void setIsTabsShadow(final boolean isTabsShadow) {
    this.isTabsShadow = isTabsShadow;
  }
  // endregion

  //region Code Additions
  public boolean isCodeAdditionsEnabled() {
    return isPremium && codeAdditionsEnabled;
  }

  public void setCodeAdditionsEnabled(final boolean codeAdditionsEnabled) {
    this.codeAdditionsEnabled = codeAdditionsEnabled;
  }

  public boolean isCodeAdditionsEnabledChanged(final boolean codeAdditionsEnabled) {
    return this.codeAdditionsEnabled != codeAdditionsEnabled;
  }

  public boolean isEnforcedLanguageAdditions() {
    return enforcedLanguageAdditions;
  }

  private void setEnforcedLanguageAdditions(final boolean enforcedLanguageAdditions) {
    this.enforcedLanguageAdditions = enforcedLanguageAdditions;
  }

  public boolean isEnforcedLanguageAdditionsChanged(final boolean enforcedLanguageAdditions) {
    return enforcedLanguageAdditions != this.enforcedLanguageAdditions;
  }
  //endregion

  //region Colored Directories
  public boolean isUseColoredDirectories() {
    return isPremium && useColoredDirectories;
  }

  public void setUseColoredDirectories(final boolean useColoredDirectories) {
    this.useColoredDirectories = useColoredDirectories;
  }

  public boolean isUseColoredDirectoriesChanged(final boolean useColoredDirectories) {
    return this.useColoredDirectories != useColoredDirectories;
  }
  // endregion

  //region Accent Mode
  public boolean isAccentMode() {
    return isPremium && accentMode;
  }

  public void setAccentMode(final boolean accentMode) {
    this.accentMode = accentMode;
  }

  public boolean isAccentModeChanged(final boolean accentMode) {
    return this.accentMode != accentMode;
  }
  //endregion

  //region Material Wallpapers
  public boolean isUseMaterialWallpapers() {
    return isPremium && useMaterialWallpapers;
  }

  public void setUseMaterialWallpapers(final boolean useMaterialWallpapers) {
    this.useMaterialWallpapers = useMaterialWallpapers;
  }

  public boolean isUseMaterialWallpapersChanged(final boolean useMaterialWallpapers) {
    return this.useMaterialWallpapers != useMaterialWallpapers;
  }
  //endregion

  //region Striped Tool Windows
  public boolean isStripedToolWindowsEnabled() {
    return stripedToolWindowsEnabled;
  }

  private void setStripedToolWindowsEnabled(final boolean stripedToolWindowsEnabled) {
    this.stripedToolWindowsEnabled = stripedToolWindowsEnabled;
  }

  public boolean isStripedToolWindowsChanged(final boolean stripedToolWindows) {
    return stripedToolWindowsEnabled != stripedToolWindows;
  }
  //endregion

  //region Project Frame
  public boolean isUseProjectFrame() {
    return isPremium && useProjectFrame;
  }

  public void setUseProjectFrame(final boolean useProjectFrame) {
    this.useProjectFrame = useProjectFrame;
  }

  public boolean isUseProjectFrameChanged(final boolean useProjectFrame) {
    return this.useProjectFrame != useProjectFrame;
  }
  //endregion

  //region other data
  public boolean isReset() {
    return isReset;
  }

  /**
   * Returns the settingsSelectedTab of this MTConfig object.
   *
   * @return the settingsSelectedTab (type Integer) of this MTConfig object.
   */
  public Integer getSettingsSelectedTab() {
    return MTUiUtils.valueInRange(settingsSelectedTab, 0, MAX_TAB_INDEX);
  }

  /**
   * Sets the settingsSelectedTab of this MTConfig object.
   *
   * @param settingsSelectedTab the settingsSelectedTab of this MTConfig object.
   */
  public void setSettingsSelectedTab(final Integer settingsSelectedTab) {
    this.settingsSelectedTab = settingsSelectedTab;
  }

  /**
   * Returns the userId of this MTConfig object.
   *
   * @return the userId (type String) of this MTConfig object.
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Returns the disallowDataCollection of this MTConfig object.
   *
   * @return the disallowDataCollection (type boolean) of this MTConfig object.
   */
  public boolean isDisallowDataCollection() {
    return !allowDataCollection;
  }

  /**
   * Sets the allowDataCollection of this MTConfig object.
   *
   * @param allowDataCollection the allowDataCollection of this MTConfig object.
   */
  public void setAllowDataCollection(final boolean allowDataCollection) {
    this.allowDataCollection = allowDataCollection;
  }

  /**
   * Returns the isWizardShown of this MTConfig object.
   *
   * @return the isWizardShown (type boolean) of this MTConfig object.
   */
  public boolean isWizardShown() {
    return isWizardShown;
  }

  /**
   * Sets the isWizardShown of this MTConfig object.
   *
   * @param isWizardShown the isWizardShown of this MTConfig object.
   */
  public void setIsWizardShown(final boolean isWizardShown) {
    this.isWizardShown = isWizardShown;
  }

  public boolean isPremium() {
    return isPremium;
  }

  public void setPremium(final boolean premium) {
    isPremium = premium;
  }

  /**
   * Returns this MTConfig as a json
   *
   * @return the nativePropertiesAsJson (type JSONObject) of this MTConfig object.
   * @throws JSONException when
   */
  @SuppressWarnings("DuplicateStringLiteralInspection")
  private JSONObject getNativePropertiesAsJson() throws JSONException {
    @NonNls final JSONObject hashMap = new JSONObject();
    hashMap.put("accentColor", accentColor);
    hashMap.put("accentMode", accentMode);
    hashMap.put("accentScrollbars", accentScrollbars);
    hashMap.put("codeAdditions", codeAdditionsEnabled);
    hashMap.put("enforceLanguageAdditions", enforcedLanguageAdditions);
    hashMap.put("compactDropdowns", compactDropdowns);
    hashMap.put("compactSidebar", compactSidebar);
    hashMap.put("customSidebarHeight", customSidebarHeight);
    hashMap.put("darkTitleBar", darkTitleBar);
    hashMap.put("fileStatusColorsEnabled", fileStatusColorsEnabled);
    hashMap.put("highlightColor", highlightColor);
    hashMap.put("highlightThickness", highlightThickness);
    hashMap.put("IDE", ApplicationNamesInfo.getInstance().getFullProductName());
    hashMap.put("IDEVersion", ApplicationInfo.getInstance().getBuild().getBaselineVersion());
    hashMap.put("indicatorStyles", indicatorStyle);
    hashMap.put("indicatorThickness", indicatorThickness);
    hashMap.put("isCompactMenus", isCompactMenus);
    hashMap.put("isPremium", isPremium);
    hashMap.put("isCompactStatusBar", isCompactStatusBar);
    hashMap.put("isCompactTables", isCompactTables);
    hashMap.put("isContrastMode", isContrastMode);
    hashMap.put("isCustomTreeIndentEnabled", isCustomTreeIndentEnabled);
    hashMap.put("isHighContrast", isHighContrast);
    hashMap.put("isMaterialDesign", isMaterialDesign);
    hashMap.put("isMaterialTheme", isMaterialTheme);
    hashMap.put("isStyledDirectories", isStyledDirectories);
    hashMap.put("isTabsShadow", isTabsShadow);
    hashMap.put("leftTreeIndent", leftTreeIndent);
    hashMap.put("overrideAccentColor", overrideAccentColor);
    hashMap.put("pristineConfig", pristineConfig);
    hashMap.put("rightTreeIndent", rightTreeIndent);
    hashMap.put("secondAccentMode", secondAccentColor);
    hashMap.put("selectedTheme", selectedTheme);
    hashMap.put("statusBarTheme", statusBarTheme);
    hashMap.put("stripedToolWindowsEnabled", stripedToolWindowsEnabled);
    hashMap.put("tabHighlightPosition", tabHighlightPosition);
    hashMap.put("tabOpacity", tabOpacity);
    hashMap.put("tabsHeight", tabsHeight);
    hashMap.put("themedScrollbars", themedScrollbars);
    hashMap.put("tabFontSize", tabFontSize);
    hashMap.put("tabFontSizeEnabled", tabFontSizeEnabled);
    hashMap.put("treeFontSize", treeFontSize);
    hashMap.put("treeFontSizeEnabled", treeFontSizeEnabled);
    hashMap.put("upperCaseButtons", upperCaseButtons);
    hashMap.put("upperCaseTabs", upperCaseTabs);
    hashMap.put("useMaterialFont", useMaterialFont2);
    hashMap.put("useMaterialWallpapers", useMaterialWallpapers);
    hashMap.put("useProjectFrame", useProjectFrame);
    hashMap.put("userId", userId);
    hashMap.put("version", version);

    return hashMap;
  }

  public String getTooltip() {
    return MaterialThemeBundle.message("MTConfig.about",
      Objects.requireNonNull(getSelectedTheme().getThemeName()),
      isContrastMode ? "Contrast" : "Normal");
  }

  //endregion
}
