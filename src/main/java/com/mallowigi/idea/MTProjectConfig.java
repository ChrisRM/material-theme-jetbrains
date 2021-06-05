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

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.ui.ColorUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Transient;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.config.enums.IndicatorStyles;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.mallowigi.idea.config.ui.MTForm;
import com.mallowigi.idea.config.ui.MTProjectForm;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;

@SuppressWarnings({"ClassWithTooManyFields",
  "ClassWithTooManyMethods",
  "WeakerAccess",
  "PackageVisibleField",
  "MethodParameterOfConcreteClass",
  "MethodReturnOfConcreteClass",
  "PublicMethodNotExposedInInterface",
  "StaticMethodOnlyUsedInOneClass",
  "ParameterHidesMemberVariable",
  "TransientFieldInNonSerializableClass",
  "UnusedAssignment"})
@State(
  name = "MaterialThemeProjectConfig", //NON-NLS
  storages = @Storage("material_theme_project.xml") //NON-NLS
)
public final class MTProjectConfig implements PersistentStateComponent<MTProjectConfig>,
                                              MTBaseConfig<MTProjectForm, MTProjectConfig>, Cloneable {
  @Property
  boolean isActive = false;
  @Property
  boolean borderedButtons = false;
  @Property
  boolean isHighlightColorEnabled = false;
  @Property
  boolean upperCaseButtons = true;
  @Property
  boolean upperCaseTabs = false;
  @Property
  boolean useProjectFrame = false;
  @Property
  IndicatorStyles indicatorStyle = IndicatorStyles.BORDER;
  @Property
  Integer highlightThickness = MTConfig.DEFAULT_THICKNESS;
  @Property
  Integer indicatorThickness = MTConfig.DEFAULT_THICKNESS;
  @Property
  Integer settingsSelectedTab = 0;
  @Property
  String highlightColor = MTConfig.ACCENT_COLOR;
  @Property
  String projectFrameColor = MTConfig.ACCENT_COLOR;
  @Property
  boolean useProjectTitle = true;
  @Property
  boolean useCustomTitle = false;
  @NonNls
  String customTitle = MTConfig.DEFAULT_TITLE;
  @Property
  TabHighlightPositions tabHighlightPosition = TabHighlightPositions.DEFAULT;
  @Transient
  private transient boolean isReset = false;
  @Transient
  private transient boolean isPremium = false;

  @SuppressWarnings({
    "ImplicitCallToSuper",
    "PublicConstructor"})
  public MTProjectConfig() {
    isPremium = MTLicenseChecker.isLicensed();
  }

  public static MTProjectConfig getInstance(final Project project) {
    return project.getService(MTProjectConfig.class);
  }

  @SuppressWarnings("MethodDoesntCallSuperMethod")
  @Override
  public MTProjectConfig clone() {
    return XmlSerializerUtil.createCopy(this);
  }

  @Override
  public @Nullable MTProjectConfig getState() {
    isPremium = true;
    final MTProjectConfig clone = clone();
    isPremium = MTLicenseChecker.isLicensed();
    return clone;
  }

  @Override
  public void loadState(@NotNull final MTProjectConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  @Override
  public void fireBeforeChanged(final MTProjectForm form) {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
                      .beforeProjectConfigChanged(this, form);
  }

  @Override
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
                      .projectConfigChanged(this);
  }

  @SuppressWarnings({"CallToSimpleSetterFromWithinClass",
    "FeatureEnvy",
    "Duplicates"})
  @Override
  public void applySettings(final MTProjectForm form) {
    // First fire before change
    fireBeforeChanged(form);
    isReset = false;

    setIsActive(form.isActive());
    setSettingsSelectedTab(form.getSelectedTabIndex());
    setHighlightColor(form.getHighlightColor());
    setHighlightColorEnabled(form.isHighlightColorEnabled());
    setHighlightThickness(form.getHighlightThickness());
    setUpperCaseTabs(form.isUpperCaseTabs());
    setTabHighlightPosition(form.getTabHighlightPosition());
    setUseProjectFrame(form.isUseProjectFrame());
    setProjectFrameColor(form.getProjectFrameColor());
    setUseProjectTitle(form.isUseProjectTitle());
    setUseCustomTitle(form.isUseCustomTitle());
    setCustomTitle(form.getCustomTitle());

    // Then fire changed
    fireChanged();
  }

  @Override
  public void resetSettings() {
    isReset = true;
    isActive = false;
    borderedButtons = false;
    highlightColor = MTConfig.ACCENT_COLOR;
    isHighlightColorEnabled = false;
    highlightThickness = MTConfig.DEFAULT_THICKNESS;
    indicatorStyle = IndicatorStyles.BORDER;
    indicatorThickness = MTConfig.DEFAULT_THICKNESS;
    projectFrameColor = MTConfig.ACCENT_COLOR;
    tabHighlightPosition = TabHighlightPositions.DEFAULT;
    upperCaseButtons = true;
    upperCaseTabs = false;
    useProjectFrame = false;
    useProjectTitle = true;
    useCustomTitle = false;
    customTitle = MTConfig.DEFAULT_TITLE;
  }

  @Override
  public boolean needsRestart(final MTForm form) {
    return false;
  }

  //region Is Project Settings Active
  public boolean isActive() {
    return isPremium && isActive;
  }

  public void setIsActive(final boolean active) {
    isActive = active;
  }

  public boolean isActiveChanged(final boolean active) {
    return isActive != active;
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
    if (MTConfig.MIN_HIGHLIGHT_THICKNESS > thickness || thickness > MTConfig.MAX_HIGHLIGHT_THICKNESS) {
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

  //region Project Frame color

  public void setProjectFrameColor(@NotNull final Color color) {
    projectFrameColor = ColorUtil.toHex(color);
  }

  public boolean isProjectFrameColorChanged(@NotNull final Color color) {
    final Color current = getProjectFrameColor();
    return !Objects.equals(current, color);
  }

  /**
   * Get the tab highlight color
   *
   * @return the highlight color
   */
  public Color getProjectFrameColor() {
    return ColorUtil.fromHex(projectFrameColor);
  }

  //endregion

  //region Project Frame Title
  public boolean isUseProjectTitle() {
    return useProjectTitle;
  }

  private void setUseProjectTitle(final boolean useProjectTitle) {
    this.useProjectTitle = useProjectTitle;
  }

  public boolean isUseProjectTitleChanged(final boolean useProjectTitle) {
    return this.useProjectTitle != useProjectTitle;
  }

  //endregion

  //region Customize Project Frame Title
  public boolean isUseCustomTitle() {
    return useCustomTitle;
  }

  private void setUseCustomTitle(final boolean useCustomTitle) {
    this.useCustomTitle = useCustomTitle;
  }

  public boolean isUseCustomTitleChanged(final boolean useCustomTitle) {
    return this.useCustomTitle != useCustomTitle;
  }

  //endregion

  //region Custom Title
  public String getCustomTitle() {
    return customTitle;
  }

  private void setCustomTitle(final String customTitle) {
    this.customTitle = customTitle;
  }

  public boolean isCustomTitleChanged(@NonNls final String customTitle) {
    return !Objects.equals(this.customTitle, customTitle);
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
    return MTUiUtils.valueInRange(settingsSelectedTab, 0, MTConfig.MAX_TAB_INDEX);
  }

  /**
   * Sets the settingsSelectedTab of this MTConfig object.
   *
   * @param settingsSelectedTab the settingsSelectedTab of this MTConfig object.
   */
  public void setSettingsSelectedTab(final Integer settingsSelectedTab) {
    this.settingsSelectedTab = settingsSelectedTab;
  }

  public void setPremium(final boolean premium) {
    isPremium = premium;
  }

  //endregion

}
