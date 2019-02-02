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

package com.chrisrm.idea;

import com.chrisrm.idea.config.MTBaseConfig;
import com.chrisrm.idea.config.ui.ArrowsStyles;
import com.chrisrm.idea.config.ui.IndicatorStyles;
import com.chrisrm.idea.config.ui.MTForm;
import com.chrisrm.idea.config.ui.TabHighlightPositions;
import com.chrisrm.idea.listeners.ConfigNotifier;
import com.chrisrm.idea.themes.MTThemeFacade;
import com.chrisrm.idea.themes.MTThemes;
import com.chrisrm.idea.utils.MTAccents;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Property;
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
    "DeprecatedIsStillUsed",
    "deprecation"})
@State(
    name = "MaterialThemeConfig", //NON-NLS
    storages = @Storage("material_theme.xml")
)
public final class MTConfig implements PersistentStateComponent<MTConfig>,
                                       MTBaseConfig<MTForm, MTConfig>, Cloneable {
  //region ~~~~~~~~~~~~~ CONSTANTS ~~~~~~~~~~~~~
  private static final String DEFAULT_BG =
      "https://raw.githubusercontent.com/ChrisRM/material-theme-jetbrains/master/src/main/resources/themes/wall.jpg,60";
  static final String ACCENT_COLOR = MTAccents.FUCHSIA.getHexColor();
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
  public static final int DEFAULT_TAB_OPACITY = 50;
  public static final int DEFAULT_TAB_FONT_SIZE = 12;
  public static final int DEFAULT_TREE_FONT_SIZE = 12;
  public static final int DEFAULT_THICKNESS = 2;
  public static final int DEFAULT_LEFT_INDENT = 6;
  public static final int DEFAULT_RIGHT_INDENT = 10;
  public static final int DEFAULT_TAB_HEIGHT = 42;
  //endregion

  //region ±±±±±±±±±±±±±±±±± Properties ±±±±±±±±±±±±±±±±±
  @Property
  ArrowsStyles arrowsStyle = ArrowsStyles.MATERIAL;
  @Property
  boolean accentScrollbars = true;
  @SuppressWarnings("FieldHasSetterButNoGetter")
  @Property
  boolean allowDataCollection;
  @Property
  private boolean codeAdditionsEnabled = true;
  @Property
  boolean compactDropdowns;
  @Property
  boolean compactSidebar;
  @Property
  boolean darkTitleBar = SystemInfo.isMac;
  @Property
  boolean fileIcons = true;
  @Property
  boolean fileStatusColorsEnabled = true;
  @Property
  boolean hideFileIcons;
  @Property
  boolean highlightColorEnabled;
  @Property
  boolean isCompactMenus;
  @Property
  boolean isCompactStatusBar;
  @Property
  boolean isCompactTables;
  @Property
  boolean isContrastMode;
  @Property
  boolean isCustomTreeIndentEnabled;
  @Property
  boolean isDecoratedFolders = true;
  @Property
  boolean isHighContrast;
  @Property
  boolean isMaterialDesign = true;
  @Property
  boolean isMaterialTheme = true;
  @Property
  boolean isPsiIcons = true;
  @Property
  boolean isStyledDirectories;
  @Property
  boolean isTabsShadow = true;
  @Property
  boolean isWizardShown;
  @Property
  boolean monochromeIcons;
  @Property
  boolean overrideAccentColor;
  @Property
  boolean pristineConfig = true;
  @Property
  boolean statusBarTheme = true;
  @Property
  boolean themedScrollbars = true;
  @Property
  boolean treeFontSizeEnabled;
  @Property
  boolean tabFontSizeEnabled;
  @Property
  boolean upperCaseButtons = true;
  @Property
  boolean upperCaseTabs;
  @Property
  @Deprecated
  boolean useMaterialFont = true;
  @Property
  boolean useMaterialFont2;
  @Property
  boolean useMaterialIcons = true;
  @Property
  boolean useProjectViewDecorators = true;
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
  String highlightColor = ACCENT_COLOR;
  @NonNls
  @Property
  String selectedTheme = MTThemes.OCEANIC.getName();
  @Property
  String userId = new UID().toString();
  @Property
  String version = "3.2.0";
  @Property
  TabHighlightPositions tabHighlightPosition = TabHighlightPositions.DEFAULT;

  //endregion

  /**
   * Represents an instance of the configuration
   */
  @SuppressWarnings("RedundantNoArgConstructor")
  public MTConfig() {
  }

  /**
   * Clone the current instance
   *
   * @return Object
   */
  @Override
  public MTConfig clone() {
    return XmlSerializerUtil.createCopy(this);
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
   * Get the state of MTConfig
   */
  @NotNull
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
   * Returns the defaultBackground of this MTConfig object.
   *
   * @return the defaultBackground (type String) of this MTConfig object.
   */
  public static String getDefaultBackground() {
    return DEFAULT_BG;
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
    hashMap.put("accentScrollbars", accentScrollbars);
    hashMap.put("arrowsStyle", arrowsStyle);
    hashMap.put("codeAdditions", codeAdditionsEnabled);
    hashMap.put("compactDropdowns", compactDropdowns);
    hashMap.put("compactSidebar", compactSidebar);
    hashMap.put("customSidebarHeight", customSidebarHeight);
    hashMap.put("darkTitleBar", darkTitleBar);
    hashMap.put("fileIcons", fileIcons);
    hashMap.put("fileStatusColorsEnabled", fileStatusColorsEnabled);
    hashMap.put("hideFileIcons", hideFileIcons);
    hashMap.put("highlightColor", highlightColor);
    hashMap.put("highlightThickness", highlightThickness);
    hashMap.put("IDE", ApplicationNamesInfo.getInstance().getFullProductName());
    hashMap.put("IDEVersion", ApplicationInfo.getInstance().getBuild().getBaselineVersion());
    hashMap.put("indicatorStyles", indicatorStyle);
    hashMap.put("indicatorThickness", indicatorThickness);
    hashMap.put("isCompactMenus", isCompactMenus);
    hashMap.put("isCompactStatusBar", isCompactStatusBar);
    hashMap.put("isCompactTables", isCompactTables);
    hashMap.put("isContrastMode", isContrastMode);
    hashMap.put("isCustomTreeIndentEnabled", isCustomTreeIndentEnabled);
    hashMap.put("isDecoratedFolders", isDecoratedFolders);
    hashMap.put("isHighContrast", isHighContrast);
    hashMap.put("isMaterialDesign", isMaterialDesign);
    hashMap.put("isMaterialTheme", isMaterialTheme);
    hashMap.put("isPsiIcons", isPsiIcons);
    hashMap.put("isStyledDirectories", isStyledDirectories);
    hashMap.put("isTabsShadow", isTabsShadow);
    hashMap.put("leftTreeIndent", leftTreeIndent);
    hashMap.put("monochromeIcons", monochromeIcons);
    hashMap.put("overrideAccentColor", overrideAccentColor);
    hashMap.put("pristineConfig", pristineConfig);
    hashMap.put("rightTreeIndent", rightTreeIndent);
    hashMap.put("selectedTheme", selectedTheme);
    hashMap.put("statusBarTheme", statusBarTheme);
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
    hashMap.put("useMaterialIcons", useMaterialIcons);
    hashMap.put("useProjectViewDecorators", useProjectViewDecorators);
    hashMap.put("userId", userId);
    hashMap.put("version", version);

    return hashMap;
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
    setSettingsSelectedTab(form.getSelectedTabIndex());

    setAccentColor(ColorUtil.toHex(form.getCustomAccentColor()));
    setAccentScrollbars(form.isAccentScrollbars());
    setArrowsStyle(form.getArrowsStyle());
    setCodeAdditionsEnabled(form.isCodeAdditionsEnabled());
    setCompactDropdowns(form.isCompactDropdowns());
    setCompactSidebar(form.isCompactSidebar());
    setCustomSidebarHeight(form.getCustomSidebarHeight());
    setDarkTitleBar(form.isDarkTitleBar());
    setFileIcons(form.isFileIcons());
    setFileStatusColorsEnabled(form.isFileStatusColors());
    setHideFileIcons(form.isHideFileIcons());
    setHighlightColor(form.getHighlightColor());
    setHighlightColorEnabled(form.isHighlightColorEnabled());
    setHighlightThickness(form.getHighlightThickness());
    setIndicatorStyle(form.getIndicatorStyle());
    setIndicatorThickness(form.getIndicatorThickness());
    setIsCompactMenus(form.isCompactMenus());
    setIsCompactStatusBar(form.isCompactStatusBar());
    setIsCompactTables(form.isCompactTables());
    setIsContrastMode(form.isContrastMode());
    setIsCustomTreeIndent(form.isCustomTreeIndent());
    setIsDecoratedFolders(form.isDecoratedFolders());
    setIsHighContrast(form.isHighContrast());
    setIsMaterialDesign(form.isMaterialDesign());
    setIsMaterialTheme(form.isMaterialTheme());
    setIsPsiIcons(form.isPsiIcons());
    setIsStatusBarTheme(form.isStatusBarTheme());
    setIsStyledDirectories(form.isStyledDirectories());
    setIsTabsShadow(form.isTabsShadow());
    setIsUpperCaseTabs(form.isUpperCaseTabs());
    setLeftTreeIndent(form.getLeftTreeIndent());
    setMonochromeIcons(form.isMonochromeIcons());
    setOverrideAccentColor(form.isOverrideAccents());
    pristineConfig = false;
    setRightTreeIndent(form.getRightTreeIndent());
    setSelectedTheme(form.getTheme());
    setTabHighlightPosition(form.getTabHighlightPosition());
    setTabOpacity(form.getTabOpacity());
    setTabsHeight(form.getTabsHeight());
    setThemedScrollbars(form.isThemedScrollbars());
    setTabFontSize(form.getTabFontSize());
    setTabFontSizeEnabled(form.isTabFontSizeEnabled());
    setTreeFontSize(form.getTreeFontSize());
    setTreeFontSizeEnabled(form.isTreeFontSizeEnabled());
    setUpperCaseButtons(form.isUpperCaseButtons());
    setUseMaterialFont(form.isUseMaterialFonts());
    setUseMaterialIcons(form.isUseMaterialIcons());
    setUseProjectViewDecorators(form.isUseProjectViewDecorators());

    // Then fire changed
    fireChanged();
  }

  @Override
  public void resetSettings() {
    accentColor = ACCENT_COLOR;
    accentScrollbars = true;
    arrowsStyle = ArrowsStyles.MATERIAL;
    codeAdditionsEnabled = true;
    compactDropdowns = false;
    compactSidebar = false;
    customSidebarHeight = DEFAULT_LINE_HEIGHT;
    darkTitleBar = SystemInfo.isMac;
    fileIcons = true;
    fileStatusColorsEnabled = true;
    hideFileIcons = false;
    highlightColor = ACCENT_COLOR;
    highlightColorEnabled = false;
    highlightThickness = DEFAULT_THICKNESS;
    indicatorStyle = IndicatorStyles.BORDER;
    indicatorThickness = DEFAULT_THICKNESS;
    isCompactMenus = false;
    isCompactStatusBar = false;
    isCompactTables = false;
    isContrastMode = false;
    isCustomTreeIndentEnabled = false;
    isDecoratedFolders = true;
    isHighContrast = false;
    isMaterialDesign = true;
    isMaterialTheme = true;
    isPsiIcons = true;
    isStyledDirectories = false;
    isTabsShadow = true;
    leftTreeIndent = 6;
    monochromeIcons = false;
    overrideAccentColor = false;
    pristineConfig = true;
    rightTreeIndent = 6;
    selectedTheme = MTThemes.OCEANIC.getName();
    statusBarTheme = true;
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
    useMaterialIcons = true;
    useProjectViewDecorators = true;
  }

  @Override
  public boolean needsRestart(final MTForm form) {
    boolean modified = isMaterialDesignChanged(form.isMaterialDesign());
    modified = modified || isMaterialThemeChanged(form.isMaterialTheme());

    return modified;
  }

  //region Selected theme

  /**
   * Return the selected theme
   */
  public MTThemeFacade getSelectedTheme() {
    final MTThemeFacade themeFor = MTThemes.getThemeFor(selectedTheme);
    return ObjectUtils.notNull(themeFor, MTThemes.OCEANIC);
  }

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
  //endregion

  //region Version

  /**
   * Returns the version of the plugin
   *
   * @return the version (type String) of this MTConfig object.
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the version of the plugin
   *
   * @param version the version of this MTConfig object.
   */
  public void setVersion(final String version) {
    this.version = version;
  }
  //endregion

  //region Tabs Highlight

  /**
   * Get the tab highlight color
   *
   * @return the highlight color
   */
  public Color getHighlightColor() {
    return ColorUtil.fromHex(highlightColor);
  }

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
  //endregion

  //region Tab Placement
  public TabHighlightPositions getTabHighlightPosition() {
    return tabHighlightPosition;
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
   * Checks whether we are in contrast mode
   *
   * @return true if contrast mode
   */
  public boolean isContrastMode() {
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

  /**
   * Checks whether the contrast mode has changed
   *
   * @param isContrastMode of type boolean
   * @return boolean
   */
  public boolean isContrastModeChanged(final boolean isContrastMode) {
    return this.isContrastMode != isContrastMode;
  }
  //endregion

  //region Material Design Components

  /**
   * Whether Material Design components are enabled
   *
   * @return the isMaterialDesign (type boolean) of this MTConfig object.
   */
  public boolean isMaterialDesign() {
    return isMaterialDesign;
  }

  /**
   * Sets Material Design components
   *
   * @param materialDesign the isMaterialDesign of this MTConfig object.
   */
  public void setIsMaterialDesign(final boolean materialDesign) {
    isMaterialDesign = materialDesign;
  }

  /**
   * ...
   *
   * @param isMaterialDesign of type boolean
   * @return boolean
   */
  public boolean isMaterialDesignChanged(final boolean isMaterialDesign) {
    return this.isMaterialDesign != isMaterialDesign;
  }
  //endregion

  //region Styled Directories

  /**
   * Returns whether the styled directories are enabled
   *
   * @return the isStyledDirectories (type boolean) of this MTConfig object.
   */
  public boolean isStyledDirectories() {
    return isStyledDirectories;
  }

  /**
   * Enable/Disable the styled directories
   *
   * @param isStyledDirectories the isStyledDirectories of this MTConfig object.
   */
  public void setIsStyledDirectories(final boolean isStyledDirectories) {
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
  //endregion

  //region Accent Color

  /**
   * Returns the accentColor of this MTConfig object.
   *
   * @return the accentColor (type String) of this MTConfig object.
   */
  public String getAccentColor() {
    return accentColor;
  }

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
  //endregion

  //region Override Accent Color

  /**
   * Returns the overrideAccentColor of this MTConfig object.
   *
   * @return the overrideAccentColor (type boolean) of this MTConfig object.
   */
  public boolean isOverrideAccentColor() {
    return overrideAccentColor;
  }

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
  //endregion

  //region Material Icons

  /**
   * Returns the useMaterialIcons of this MTConfig object.
   *
   * @return the useMaterialIcons (type boolean) of this MTConfig object.
   */
  public boolean isUseMaterialIcons() {
    return useMaterialIcons;
  }

  /**
   * Sets the useMaterialIcons of this MTConfig object.
   *
   * @param useMaterialIcons the useMaterialIcons of this MTConfig object.
   */
  public void setUseMaterialIcons(final boolean useMaterialIcons) {
    this.useMaterialIcons = useMaterialIcons;
  }

  /**
   * ...
   *
   * @param useMaterialIcons of type boolean
   * @return boolean
   */
  public boolean isMaterialIconsChanged(final boolean useMaterialIcons) {
    return this.useMaterialIcons != useMaterialIcons;
  }
  //endregion

  //region Project View Decorators

  /**
   * Returns the useProjectViewDecorators of this MTConfig object.
   *
   * @return the useProjectViewDecorators (type boolean) of this MTConfig object.
   */
  public boolean isUseProjectViewDecorators() {
    return useProjectViewDecorators;
  }

  /**
   * Sets the useProjectViewDecorators of this MTConfig object.
   *
   * @param useProjectViewDecorators the useProjectViewDecorators of this MTConfig object.
   */
  public void setUseProjectViewDecorators(final boolean useProjectViewDecorators) {
    this.useProjectViewDecorators = useProjectViewDecorators;
  }

  /**
   * ...
   *
   * @param useProjectViewDecorators of type boolean
   * @return boolean
   */
  public boolean isUseProjectViewDecoratorsChanged(final boolean useProjectViewDecorators) {
    return this.useProjectViewDecorators != useProjectViewDecorators;
  }
  //endregion

  //region Hide File Icons

  /**
   * Returns the hideFileIcons of this MTConfig object.
   *
   * @return the hideFileIcons (type boolean) of this MTConfig object.
   */
  public boolean isHideFileIcons() {
    return hideFileIcons;
  }

  /**
   * Sets the hideFileIcons of this MTConfig object.
   *
   * @param hideFileIcons the hideFileIcons of this MTConfig object.
   */
  public void setHideFileIcons(final boolean hideFileIcons) {
    this.hideFileIcons = hideFileIcons;
  }

  /**
   * ...
   *
   * @param hideFileIcons of type boolean
   * @return boolean
   */
  public boolean isHideFileIconsChanged(final boolean hideFileIcons) {
    return this.hideFileIcons != hideFileIcons;
  }
  //endregion

  //region File Icons

  /**
   * Returns the fileIcons of this MTConfig object.
   *
   * @return the fileIcons (type boolean) of this MTConfig object.
   */
  public boolean isFileIcons() {
    return fileIcons;
  }

  /**
   * Sets the fileIcons of this MTConfig object.
   *
   * @param fileIcons the fileIcons of this MTConfig object.
   */
  public void setFileIcons(final boolean fileIcons) {
    this.fileIcons = fileIcons;
  }

  /**
   * ...
   *
   * @param fileIcons of type boolean
   * @return boolean
   */
  public boolean isFileIconsChanged(final boolean fileIcons) {
    return this.fileIcons != fileIcons;
  }
  //endregion

  //region Compact Sidebar

  /**
   * Returns the compactSidebar of this MTConfig object.
   *
   * @return the compactSidebar (type boolean) of this MTConfig object.
   */
  public boolean isCompactSidebar() {
    return compactSidebar;
  }

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
  //endregion

  //region Custom Sidebar Height

  /**
   * Returns the customSidebarHeight of this MTConfig object.
   *
   * @return the customSidebarHeight (type int) of this MTConfig object.
   */
  public int getCustomSidebarHeight() {
    return customSidebarHeight;
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
  //endregion

  //region Statusbar indicator

  /**
   * Returns the statusBarTheme of this MTConfig object.
   *
   * @return the statusBarTheme (type boolean) of this MTConfig object.
   */
  public boolean isStatusBarTheme() {
    return statusBarTheme;
  }

  /**
   * Sets the isStatusBarTheme of this MTConfig object.
   *
   * @param isStatusBarTheme the isStatusBarTheme of this MTConfig object.
   */
  public void setIsStatusBarTheme(final boolean isStatusBarTheme) {
    statusBarTheme = isStatusBarTheme;
  }

  /**
   * ...
   *
   * @param statusBarTheme of type boolean
   * @return boolean
   */
  public boolean isStatusBarThemeChanged(final boolean statusBarTheme) {
    return this.statusBarTheme != statusBarTheme;
  }
  //endregion

  //region Tabs Height

  /**
   * Returns the tabsHeight of this MTConfig object.
   *
   * @return the tabsHeight (type int) of this MTConfig object.
   */
  public int getTabsHeight() {
    return tabsHeight;
  }

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
  //endregion

  //region Material Theme

  /**
   * Returns the materialTheme of this MTConfig object.
   *
   * @return the materialTheme (type boolean) of this MTConfig object.
   */
  public boolean isMaterialTheme() {
    return isMaterialTheme;
  }

  /**
   * Sets the isMaterialTheme of this MTConfig object.
   *
   * @param isMaterialTheme the isMaterialTheme of this MTConfig object.
   */
  public void setIsMaterialTheme(final boolean isMaterialTheme) {
    this.isMaterialTheme = isMaterialTheme;
  }

  /**
   * ...
   *
   * @param isMaterialTheme of type boolean
   * @return boolean
   */
  public boolean isMaterialThemeChanged(final boolean isMaterialTheme) {
    return this.isMaterialTheme != isMaterialTheme;
  }
  //endregion

  //region Custom Tree Indents

  /**
   * Returns the rightTreeIndent of this MTConfig object.
   *
   * @return the rightTreeIndent (type int) of this MTConfig object.
   */
  public int getRightTreeIndent() {
    return rightTreeIndent;
  }

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
  public boolean isCustomTreeIndent() {
    return isCustomTreeIndentEnabled;
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
   * Sets the isCustomTreeIndent of this MTConfig object.
   *
   * @param isCustomTreeIndent the isCustomTreeIndent of this MTConfig object.
   */
  public void setIsCustomTreeIndent(final boolean isCustomTreeIndent) {
    isCustomTreeIndentEnabled = isCustomTreeIndent;
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
  //endregion

  //region Themed Scrollbars

  /**
   * Returns the themedScrollbars of this MTConfig object.
   *
   * @return the themedScrollbars (type boolean) of this MTConfig object.
   */
  public boolean isThemedScrollbars() {
    return themedScrollbars;
  }

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
  //endregion

  //region Compact Status Bar

  /**
   * Returns the compactStatusBar of this MTConfig object.
   *
   * @return the compactStatusBar (type boolean) of this MTConfig object.
   */
  public boolean isCompactStatusBar() {
    return isCompactStatusBar;
  }

  /**
   * Sets the isCompactStatusBar of this MTConfig object.
   *
   * @param isCompactStatusBar the isCompactStatusBar of this MTConfig object.
   */
  public void setIsCompactStatusBar(final boolean isCompactStatusBar) {
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
  //endregion

  //region Compact Tables

  /**
   * Returns the compactTables of this MTConfig object.
   *
   * @return the compactTables (type boolean) of this MTConfig object.
   */
  public boolean isCompactTables() {
    return isCompactTables;
  }

  /**
   * Sets the isCompactTables of this MTConfig object.
   *
   * @param isCompactTables the isCompactTables of this MTConfig object.
   */
  public void setIsCompactTables(final boolean isCompactTables) {
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
  //endregion

  //region Compact Menus

  /**
   * Returns the compactMenus of this MTConfig object.
   *
   * @return the compactMenus (type boolean) of this MTConfig object.
   */
  public boolean isCompactMenus() {
    return isCompactMenus;
  }

  /**
   * Sets the isCompactMenus of this MTConfig object.
   *
   * @param compactMenus the isCompactMenus of this MTConfig object.
   */
  public void setIsCompactMenus(final boolean compactMenus) {
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
  // endregion

  //region Uppercase tabs

  /**
   * Returns the upperCaseTabs of this MTConfig object.
   *
   * @return the upperCaseTabs (type boolean) of this MTConfig object.
   */
  public boolean isUpperCaseTabs() {
    return upperCaseTabs;
  }

  /**
   * Sets the isUpperCaseTabs of this MTConfig object.
   *
   * @param isUpperCaseTabs the isUpperCaseTabs of this MTConfig object.
   */
  public void setIsUpperCaseTabs(final boolean isUpperCaseTabs) {
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
  // endregion

  //region Dark titlebar

  /**
   * Returns the darkTitleBar of this MTConfig object.
   *
   * @return the darkTitleBar (type boolean) of this MTConfig object.
   */
  public boolean isDarkTitleBar() {
    return darkTitleBar;
  }

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
  //endregion

  // region Arrows styles

  /**
   * Returns the arrowsStyle of this MTConfig object.
   *
   * @return the arrowsStyle (type ArrowsStyles) of this MTConfig object.
   */
  public ArrowsStyles getArrowsStyle() {
    return arrowsStyle;
  }

  /**
   * Sets the arrowsStyle of this MTConfig object.
   *
   * @param arrowsStyle the arrowsStyle of this MTConfig object.
   */
  public void setArrowsStyle(final ArrowsStyles arrowsStyle) {
    this.arrowsStyle = arrowsStyle;
  }

  /**
   * ...
   *
   * @param arrowsStyle of type ArrowsStyles
   * @return boolean
   */
  public boolean isArrowsStyleChanged(final ArrowsStyles arrowsStyle) {
    return this.arrowsStyle != arrowsStyle;
  }
  // endregion

  //region Indicator Styles

  /**
   * Returns the indicatorStyle of this MTConfig object.
   *
   * @return the indicatorStyle (type IndicatorStyles) of this MTConfig object.
   */
  public IndicatorStyles getIndicatorStyle() {
    return indicatorStyle;
  }

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
  // endregion

  // region indicator thickness

  /**
   * Returns the indicatorThickness of this MTConfig object.
   *
   * @return the indicatorThickness (type Integer) of this MTConfig object.
   */
  public Integer getIndicatorThickness() {
    return indicatorThickness;
  }

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
  // endregion

  // region Material fonts

  /**
   * Sets the useMaterialFont of this MTConfig object.
   *
   * @param useMaterialFont the useMaterialFont of this MTConfig object.
   */
  public void setUseMaterialFont(final boolean useMaterialFont) {
    useMaterialFont2 = useMaterialFont;
  }

  /**
   * Returns the useMaterialFont of this MTConfig object.
   *
   * @return the useMaterialFont (type boolean) of this MTConfig object.
   */
  public boolean isUseMaterialFont() {
    return useMaterialFont2;
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
  //endregion

  //region Tab Opacity

  /**
   * Returns the tabOpacity of this MTConfig object.
   *
   * @return the tabOpacity (type int) of this MTConfig object.
   */
  public int getTabOpacity() {
    return tabOpacity;
  }

  /**
   * Sets the tabOpacity of this MTConfig object.
   *
   * @param tabOpacity the tabOpacity of this MTConfig object.
   */
  public void setTabOpacity(final int tabOpacity) {
    this.tabOpacity = tabOpacity;
  }

  /**
   * ...
   *
   * @param tabOpacity of type int
   * @return boolean
   */
  public boolean isTabOpacityChanged(final int tabOpacity) {
    return this.tabOpacity != tabOpacity;
  }

  //endregion

  // region Tab Font Size

  /**
   * Returns the treeFontSize of this MTConfig object.
   *
   * @return the treeFontSize (type int) of this MTConfig object.
   */
  public int getTabFontSize() {
    return tabFontSize;
  }

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
    return tabFontSizeEnabled;
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
  // endregion

  //region Compact dropdowns

  /**
   * Returns the compactDropdowns of this MTConfig object.
   *
   * @return the compactDropdowns (type boolean) of this MTConfig object.
   */
  public boolean isCompactDropdowns() {
    return compactDropdowns;
  }

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
  //endregion

  //region Monochrome Icons

  /**
   * Returns the monochromeIcons of this MTConfig object.
   *
   * @return the monochromeIcons (type boolean) of this MTConfig object.
   */
  public boolean isMonochromeIcons() {
    return monochromeIcons;
  }

  /**
   * Sets the monochromeIcons of this MTConfig object.
   *
   * @param monochromeIcons the monochromeIcons of this MTConfig object.
   */
  public void setMonochromeIcons(final boolean monochromeIcons) {
    this.monochromeIcons = monochromeIcons;
  }

  /**
   * ...
   *
   * @param isMonochromeIcons of type boolean
   * @return boolean
   */
  public boolean isMonochromeIconsChanged(final boolean isMonochromeIcons) {
    return monochromeIcons != isMonochromeIcons;
  }

  //endregion

  //region UpperCase Buttons

  /**
   * Returns the upperCaseButtons of this MTConfig object.
   *
   * @return the upperCaseButtons (type boolean) of this MTConfig object.
   */
  public boolean isUpperCaseButtons() {
    return upperCaseButtons;
  }

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
  //endregion

  // region Decorated Folders

  /**
   * Sets the isDecoratedFolders of this MTConfig object.
   *
   * @param isDecoratedFolders the isDecoratedFolders of this MTConfig object.
   */
  public void setIsDecoratedFolders(final boolean isDecoratedFolders) {
    this.isDecoratedFolders = isDecoratedFolders;
  }

  /**
   * Returns the decoratedFolders of this MTConfig object.
   *
   * @return the decoratedFolders (type boolean) of this MTConfig object.
   */
  public boolean isDecoratedFolders() {
    return isDecoratedFolders;
  }

  /**
   * ...
   *
   * @param decoratedFolders of type boolean
   * @return boolean
   */
  public boolean isDecoratedFoldersChanged(final boolean decoratedFolders) {
    return isDecoratedFolders != decoratedFolders;
  }
  //endregion

  // region Tree Font Size

  /**
   * Returns the treeFontSize of this MTConfig object.
   *
   * @return the treeFontSize (type int) of this MTConfig object.
   */
  public int getTreeFontSize() {
    return treeFontSize;
  }

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
    return treeFontSizeEnabled;
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
  // endregion

  //region File Status Colors

  /**
   * Returns the fileStatusColorsEnabled of this MTConfig object.
   *
   * @return the fileStatusColorsEnabled (type boolean) of this MTConfig object.
   */
  public boolean isFileStatusColorsEnabled() {
    return fileStatusColorsEnabled;
  }

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
  //endregion

  //region High Contrast

  /**
   * Sets the isHighContrast of this MTConfig object.
   *
   * @param isHighContrast the isHighContrast of this MTConfig object.
   */
  public void setIsHighContrast(final boolean isHighContrast) {
    this.isHighContrast = isHighContrast;
  }

  /**
   * Returns the isHighContrast of this MTConfig object.
   *
   * @return the isHighContrast (type boolean) of this MTConfig object.
   */
  public boolean isHighContrast() {
    return isHighContrast;
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

  //endregion

  //region Tabs Shadow

  /**
   * Sets the isTabsShadow of this MTConfig object.
   *
   * @param isTabsShadow the isTabsShadow of this MTConfig object.
   */
  public void setIsTabsShadow(final boolean isTabsShadow) {
    this.isTabsShadow = isTabsShadow;
  }

  /**
   * Returns the tabsShadow of this MTConfig object.
   *
   * @return the tabsShadow (type boolean) of this MTConfig object.
   */
  public boolean isTabsShadow() {
    return isTabsShadow;
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
  //endregion

  //region Psi Icons
  public boolean isPsiIcons() {
    return isPsiIcons;
  }

  public void setIsPsiIcons(final boolean psiIcons) {
    isPsiIcons = psiIcons;
  }

  public boolean isPsiIconsChanged(final boolean psiIcons) {
    return isPsiIcons != psiIcons;
  }
  //endregion

  //region Code Additions
  public boolean isCodeAdditionsEnabled() {
    return codeAdditionsEnabled;
  }

  public void setCodeAdditionsEnabled(final boolean codeAdditionsEnabled) {
    this.codeAdditionsEnabled = codeAdditionsEnabled;
  }

  public boolean isCodeAdditionsEnabledChanged(final boolean codeAdditionsEnabled) {
    return this.codeAdditionsEnabled != codeAdditionsEnabled;
  }
  // endregion

  //region other data

  /**
   * Sets the settingsSelectedTab of this MTConfig object.
   *
   * @param settingsSelectedTab the settingsSelectedTab of this MTConfig object.
   */
  public void setSettingsSelectedTab(final Integer settingsSelectedTab) {
    this.settingsSelectedTab = settingsSelectedTab;
  }

  /**
   * Returns the settingsSelectedTab of this MTConfig object.
   *
   * @return the settingsSelectedTab (type Integer) of this MTConfig object.
   */
  public Integer getSettingsSelectedTab() {
    return settingsSelectedTab;
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

  //endregion
}
