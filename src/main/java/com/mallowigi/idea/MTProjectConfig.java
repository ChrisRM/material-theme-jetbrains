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
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
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

  public static MTProjectConfig getInstance() {
    return ServiceManager.getService(MTProjectConfig.class);
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

    setSettingsSelectedTab(form.getSelectedTabIndex());
    setBorderedButtons(form.isBorderedButtons());
    setHighlightColor(form.getHighlightColor());
    setHighlightColorEnabled(form.isHighlightColorEnabled());
    setHighlightThickness(form.getHighlightThickness());
    setIndicatorStyle(form.getIndicatorStyle());
    setIndicatorThickness(form.getIndicatorThickness());
    setUpperCaseTabs(form.isUpperCaseTabs());
    setTabHighlightPosition(form.getTabHighlightPosition());
    setUpperCaseButtons(form.isUpperCaseButtons());
    setUseProjectFrame(form.isUseProjectFrame());

    // Then fire changed
    fireChanged();
  }

  @Override
  public void resetSettings() {
    isReset = true;
    borderedButtons = false;
    highlightColor = MTConfig.ACCENT_COLOR;
    isHighlightColorEnabled = false;
    highlightThickness = MTConfig.DEFAULT_THICKNESS;
    indicatorStyle = IndicatorStyles.BORDER;
    indicatorThickness = MTConfig.DEFAULT_THICKNESS;
    tabHighlightPosition = TabHighlightPositions.DEFAULT;
    upperCaseButtons = true;
    upperCaseTabs = false;
    useProjectFrame = false;
  }

  @Override
  public boolean needsRestart(final MTForm form) {
    return false;
  }

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
