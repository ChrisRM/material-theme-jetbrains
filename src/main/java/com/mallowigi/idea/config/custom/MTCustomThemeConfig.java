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

package com.mallowigi.idea.config.custom;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.ColorUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Property;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.config.ui.MTCustomThemeForm;
import com.mallowigi.idea.config.ui.MTForm;
import com.mallowigi.idea.listeners.MTTopics;
import com.mallowigi.idea.themes.models.MTBundledTheme;
import com.mallowigi.idea.themes.models.MTDarkBundledTheme;
import com.mallowigi.idea.themes.models.MTThemeable;
import com.mallowigi.idea.utils.MTColorUtils;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Objects;

@SuppressWarnings({"ClassWithTooManyFields",
  "ClassWithTooManyMethods",
  "WeakerAccess",
  "PackageVisibleField",
  "PublicMethodNotExposedInInterface",
  "DuplicateStringLiteralInspection"})
@State(
  name = "MaterialCustomThemeConfig", //NON-NLS
  storages = @Storage("material_custom_theme.xml") //NON-NLS
)
public final class MTCustomThemeConfig implements PersistentStateComponent<MTCustomThemeConfig>,
                                                  MTBaseConfig<MTCustomThemeForm, MTCustomThemeConfig>,
                                                  Cloneable {
  @Property
  @NonNls
  String excludedColor = "2E3C43";
  @Property
  @NonNls
  String accentColor = "009688";
  @Property
  @NonNls
  String notificationsColor = "323232";
  @Property
  @NonNls
  String treeSelectionColor = "546E7A50";
  @Property
  @NonNls
  String highlightColor = "425B67";
  @Property
  @NonNls
  String secondBorderColor = "2A373E";
  @Property
  @NonNls
  String tableSelectedColor = "314549";
  @Property
  @NonNls
  String contrastColor = "1E272C";
  @Property
  @NonNls
  String disabledColor = "415967";
  @Property
  @NonNls
  String secondaryBackgroundColor = "32424A";
  @Property
  @NonNls
  String buttonColor = "2E3C43";
  @Property
  @NonNls
  String selectionForegroundColor = "FFFFFF";
  @Property
  @NonNls
  String selectionBackgroundColor = "546E7A";
  @Property
  @NonNls
  String textColor = "607D8B";
  @Property
  @NonNls
  String foregroundColor = "B0BEC5";
  @Property
  @NonNls
  String backgroundColor = "263238";

  @NotNull
  public static MTCustomThemeConfig getInstance() {
    return Objects.requireNonNull(ApplicationManager.getApplication().getService(MTCustomThemeConfig.class));
  }

  @SuppressWarnings({"StaticMethodOnlyUsedInOneClass",
    "FeatureEnvy"})
  public static MTBundledTheme export(final MTCustomThemeForm form) {
    final MTBundledTheme theme = new MTDarkBundledTheme();

    theme.setBackgroundColor(new ColorUIResource(form.getBackgroundColor()));
    theme.setForegroundColor(new ColorUIResource(form.getForegroundColor()));
    theme.setTextColor(new ColorUIResource(form.getTextColor()));
    theme.setHighlightColor(new ColorUIResource(form.getHighlightColor()));
    theme.setSelectionBackgroundColor(new ColorUIResource(form.getSelectionBackgroundColor()));
    theme.setSelectionForegroundColor(new ColorUIResource(form.getSelectionForegroundColor()));
    theme.setButtonColor(new ColorUIResource(form.getButtonColor()));
    theme.setSecondaryBackgroundColor(new ColorUIResource(form.getSecondaryBackgroundColor()));
    theme.setDisabledColor(new ColorUIResource(form.getDisabledColor()));
    theme.setContrastColor(new ColorUIResource(form.getContrastColor()));
    theme.setTableSelectedColor(new ColorUIResource(form.getTableSelectedColor()));
    theme.setSecondBorderColor(new ColorUIResource(form.getSecondBorderColor()));
    theme.setTreeSelectionColor(new ColorUIResource(form.getTreeSelectionColor()));
    theme.setNotificationsColor(new ColorUIResource(form.getNotificationsColor()));
    theme.setAccentColor(new ColorUIResource(form.getAccentColor()));
    theme.setExcludedColor(new ColorUIResource(form.getExcludedColor()));

    return theme;
  }

  /**
   * Clone the current instance
   *
   * @return Object
   */
  @SuppressWarnings("MethodDoesntCallSuperMethod")
  @Override
  public MTCustomThemeConfig clone() {
    return XmlSerializerUtil.createCopy(this);
  }

  @NotNull
  @Override
  public MTCustomThemeConfig getState() {
    return this;
  }

  @Override
  public void loadState(@NotNull final MTCustomThemeConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  @Override
  public void fireBeforeChanged(final MTCustomThemeForm form) {
    //    ApplicationManager.getApplication().getMessageBus()
    //                      .syncPublisher(CustomConfigNotifier.CONFIG_TOPIC)
    //                      .customConfigChanged(this);
  }

  @Override
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(MTTopics.CUSTOM_THEME)
                      .customConfigChanged(this);
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  public void applySettings(final MTCustomThemeForm form) {
    setBackgroundColor(form.getBackgroundColor());
    setForegroundColor(form.getForegroundColor());
    setTextColor(form.getTextColor());
    setSelectionBackgroundColor(form.getSelectionBackgroundColor());
    setSelectionForegroundColor(form.getSelectionForegroundColor());
    setButtonColor(form.getButtonColor());
    setSecondaryBackgroundColor(form.getSecondaryBackgroundColor());
    setDisabledColor(form.getDisabledColor());
    setContrastColor(form.getContrastColor());
    setTableSelectedColor(form.getTableSelectedColor());
    setSecondBorderColor(form.getSecondBorderColor());
    setHighlightColor(form.getHighlightColor());
    setTreeSelectionColor(form.getTreeSelectionColor());
    setNotificationsColor(form.getNotificationsColor());
    setAccentColor(form.getAccentColor());
    setExcludedColor(form.getExcludedColor());

    fireChanged();
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  public void resetSettings() {
    setExcludedColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.excludedColor,
      MTCustomThemeForm.MTLightCustomDefaults.excludedColor));
    setAccentColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.accentColor,
      MTCustomThemeForm.MTLightCustomDefaults.accentColor));
    setNotificationsColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.notificationsColor,
      MTCustomThemeForm.MTLightCustomDefaults.notificationsColor));
    setSecondBorderColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.secondBorderColor,
      MTCustomThemeForm.MTLightCustomDefaults.secondBorderColor));
    setContrastColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.contrastColor,
      MTCustomThemeForm.MTLightCustomDefaults.contrastColor));
    setDisabledColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.disabledColor,
      MTCustomThemeForm.MTLightCustomDefaults.disabledColor));
    setSecondaryBackgroundColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.secondaryBackgroundColor,
      MTCustomThemeForm.MTLightCustomDefaults.secondaryBackgroundColor));
    setButtonColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.buttonColor,
      MTCustomThemeForm.MTLightCustomDefaults.buttonColor));
    setSelectionBackgroundColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.selectionBackgroundColor,
      MTCustomThemeForm.MTLightCustomDefaults.selectionBackgroundColor));
    setSelectionForegroundColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.selectionForegroundColor,
      MTCustomThemeForm.MTLightCustomDefaults.selectionForegroundColor));
    setTableSelectedColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.tableSelectedColor,
      MTCustomThemeForm.MTLightCustomDefaults.tableSelectedColor));
    setTextColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.textColor,
      MTCustomThemeForm.MTLightCustomDefaults.textColor));
    setTreeSelectionColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.treeSelectionColor,
      MTCustomThemeForm.MTLightCustomDefaults.treeSelectionColor));
    setHighlightColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.highlightColor,
      MTCustomThemeForm.MTLightCustomDefaults.highlightColor));
    setForegroundColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.foregroundColor,
      MTCustomThemeForm.MTLightCustomDefaults.foregroundColor));
    setBackgroundColor(MTUiUtils.lightOrDark(
      MTCustomThemeForm.MTCustomDefaults.backgroundColor,
      MTCustomThemeForm.MTLightCustomDefaults.backgroundColor));
  }

  @Override
  public boolean needsRestart(final MTForm form) {
    return false;
  }

  @NotNull
  public Color getExcludedColor() {
    return MTColorUtils.parseColor(excludedColor);
  }

  public void setExcludedColor(final Color excludedColor) {
    this.excludedColor = ColorUtil.toHex(excludedColor, true);
  }

  @NotNull
  public Color getAccentColor() {
    return MTColorUtils.parseColor(accentColor);
  }

  public void setAccentColor(final Color accentColor) {
    this.accentColor = ColorUtil.toHex(accentColor, true);
  }

  @NotNull
  public Color getNotificationsColor() {
    return MTColorUtils.parseColor(notificationsColor);
  }

  public void setNotificationsColor(final Color notificationsColor) {
    this.notificationsColor = ColorUtil.toHex(notificationsColor, true);
  }

  @NotNull
  public Color getTreeSelectionColor() {
    return MTColorUtils.parseColor(treeSelectionColor);
  }

  public void setTreeSelectionColor(final Color treeSelectionColor) {
    this.treeSelectionColor = ColorUtil.toHex(treeSelectionColor, true);
  }

  @NotNull
  public Color getHighlightColor() {
    return MTColorUtils.parseColor(highlightColor);
  }

  public void setHighlightColor(final Color highlightColor) {
    this.highlightColor = ColorUtil.toHex(highlightColor, true);
  }

  @NotNull
  public Color getSecondBorderColor() {
    return MTColorUtils.parseColor(secondBorderColor);
  }

  public void setSecondBorderColor(final Color secondBorderColor) {
    this.secondBorderColor = ColorUtil.toHex(secondBorderColor, true);
  }

  @NotNull
  public Color getTableSelectedColor() {
    return MTColorUtils.parseColor(tableSelectedColor);
  }

  public void setTableSelectedColor(final Color tableSelectedColor) {
    this.tableSelectedColor = ColorUtil.toHex(tableSelectedColor, true);
  }

  @NotNull
  public Color getContrastColor() {
    return MTColorUtils.parseColor(contrastColor);
  }

  public void setContrastColor(final Color contrastColor) {
    this.contrastColor = ColorUtil.toHex(contrastColor, true);
  }

  @NotNull
  public Color getDisabledColor() {
    return MTColorUtils.parseColor(disabledColor);
  }

  public void setDisabledColor(final Color disabledColor) {
    this.disabledColor = ColorUtil.toHex(disabledColor, true);
  }

  @NotNull
  public Color getSecondaryBackgroundColor() {
    return MTColorUtils.parseColor(secondaryBackgroundColor);
  }

  public void setSecondaryBackgroundColor(final Color secondaryBackgroundColor) {
    this.secondaryBackgroundColor = ColorUtil.toHex(secondaryBackgroundColor, true);
  }

  @NotNull
  public Color getButtonColor() {
    return MTColorUtils.parseColor(buttonColor);
  }

  public void setButtonColor(final Color buttonColor) {
    this.buttonColor = ColorUtil.toHex(buttonColor, true);
  }

  @NotNull
  public Color getSelectionForegroundColor() {
    return MTColorUtils.parseColor(selectionForegroundColor);
  }

  public void setSelectionForegroundColor(final Color selectionForegroundColor) {
    this.selectionForegroundColor = ColorUtil.toHex(selectionForegroundColor, true);
  }

  @NotNull
  public Color getSelectionBackgroundColor() {
    return MTColorUtils.parseColor(selectionBackgroundColor);
  }

  public void setSelectionBackgroundColor(final Color selectionBackgroundColor) {
    this.selectionBackgroundColor = ColorUtil.toHex(selectionBackgroundColor, true);
  }

  @NotNull
  public Color getTextColor() {
    return MTColorUtils.parseColor(textColor);
  }

  public void setTextColor(final Color textColor) {
    this.textColor = ColorUtil.toHex(textColor, true);
  }

  @NotNull
  public Color getForegroundColor() {
    return MTColorUtils.parseColor(foregroundColor);
  }

  public void setForegroundColor(final Color foregroundColor) {
    this.foregroundColor = ColorUtil.toHex(foregroundColor, true);
  }

  @NotNull
  public Color getBackgroundColor() {
    return MTColorUtils.parseColor(backgroundColor);
  }

  public void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor = ColorUtil.toHex(backgroundColor, true);
  }

  @NotNull
  public ColorUIResource getExcludedColorString() {
    return MTColorUtils.parseColor(excludedColor);
  }

  @NotNull
  public ColorUIResource getAccentColorString() {
    return MTColorUtils.parseColor(accentColor);
  }

  @NotNull
  public ColorUIResource getNotificationsColorString() {
    return MTColorUtils.parseColor(notificationsColor);
  }

  public ColorUIResource getTreeSelectionColorString() {
    return MTColorUtils.parseColor(treeSelectionColor);
  }

  public ColorUIResource getHighlightColorString() {
    return MTColorUtils.parseColor(highlightColor);
  }

  public ColorUIResource getSecondBorderColorString() {
    return MTColorUtils.parseColor(secondBorderColor);
  }

  public ColorUIResource getTableSelectedColorString() {
    return MTColorUtils.parseColor(tableSelectedColor);
  }

  public ColorUIResource getContrastColorString() {
    return MTColorUtils.parseColor(contrastColor);
  }

  public ColorUIResource getDisabledColorString() {
    return MTColorUtils.parseColor(disabledColor);
  }

  public ColorUIResource getSecondaryBackgroundColorString() {
    return MTColorUtils.parseColor(secondaryBackgroundColor);
  }

  public ColorUIResource getButtonColorString() {
    return MTColorUtils.parseColor(buttonColor);
  }

  public ColorUIResource getSelectionForegroundColorString() {
    return MTColorUtils.parseColor(selectionForegroundColor);
  }

  public ColorUIResource getSelectionBackgroundColorString() {
    return MTColorUtils.parseColor(selectionBackgroundColor);
  }

  public ColorUIResource getTextColorString() {
    return MTColorUtils.parseColor(textColor);
  }

  public ColorUIResource getForegroundColorString() {
    return MTColorUtils.parseColor(foregroundColor);
  }

  public ColorUIResource getBackgroundColorString() {
    return MTColorUtils.parseColor(backgroundColor);
  }

  public boolean isBackgroundColorChanged(final Color newBackgroundColor) {
    return !Objects.equals(MTColorUtils.parseColor(backgroundColor), newBackgroundColor);
  }

  public boolean isForegroundColorChanged(final Color newForegroundColor) {
    return !Objects.equals(MTColorUtils.parseColor(foregroundColor), newForegroundColor);
  }

  public boolean isTextColorChanged(final Color newTextColor) {
    return !Objects.equals(MTColorUtils.parseColor(textColor), newTextColor);
  }

  public boolean isSelectionBackgroundColorChanged(final Color newSelectionBackgroundColor) {
    return !Objects.equals(MTColorUtils.parseColor(selectionBackgroundColor), newSelectionBackgroundColor);
  }

  public boolean isSelectionForegroundColorChanged(final Color newSelectionForegroundColor) {
    return !Objects.equals(MTColorUtils.parseColor(selectionForegroundColor), newSelectionForegroundColor);
  }

  public boolean isButtonColorChanged(final Color newButtonColor) {
    return !Objects.equals(MTColorUtils.parseColor(buttonColor), newButtonColor);
  }

  public boolean isSecondaryBackgroundColorChanged(final Color newSecondaryBackgroundColor) {
    return !Objects.equals(MTColorUtils.parseColor(secondaryBackgroundColor), newSecondaryBackgroundColor);
  }

  public boolean isDisabledColorChanged(final Color newDisabledColor) {
    return !Objects.equals(MTColorUtils.parseColor(disabledColor), newDisabledColor);
  }

  public boolean isContrastColorChanged(final Color newContrastColor) {
    return !Objects.equals(MTColorUtils.parseColor(contrastColor), newContrastColor);
  }

  public boolean isTableSelectionColorChanged(final Color newTableSelectedColor) {
    return !Objects.equals(MTColorUtils.parseColor(tableSelectedColor), newTableSelectedColor);
  }

  public boolean isSecondBorderColorChanged(final Color newSecondBorderColor) {
    return !Objects.equals(MTColorUtils.parseColor(secondBorderColor), newSecondBorderColor);
  }

  public boolean isHighlightColorChanged(final Color newHighlightColor) {
    return !Objects.equals(MTColorUtils.parseColor(highlightColor), newHighlightColor);
  }

  public boolean isTreeSelectionColorChanged(final Color newTreeSelectionColor) {
    return !Objects.equals(MTColorUtils.parseColor(treeSelectionColor), newTreeSelectionColor);
  }

  public boolean isNotificationsColorChanged(final Color newNotificationsColor) {
    return !Objects.equals(MTColorUtils.parseColor(notificationsColor), newNotificationsColor);
  }

  public boolean isAccentColorChanged(final Color newAccentColor) {
    return !Objects.equals(MTColorUtils.parseColor(accentColor), newAccentColor);
  }

  public boolean isExcludedColorChanged(final Color newExcludedColor) {
    return !Objects.equals(MTColorUtils.parseColor(excludedColor), newExcludedColor);
  }

  @SuppressWarnings("FeatureEnvy")
  public void importFrom(final MTThemeable theme) {
    theme.setPristine();

    setBackgroundColor(theme.getBackgroundColor());
    setForegroundColor(theme.getForegroundColor());
    setTextColor(theme.getTextColor());
    setSelectionBackgroundColor(theme.getSelectionBackgroundColor());
    setSelectionForegroundColor(theme.getSelectionForegroundColor());
    setButtonColor(theme.getButtonColor());
    setDisabledColor(theme.getDisabledColor());
    setContrastColor(theme.getContrastColor());
    setSecondaryBackgroundColor(theme.getSecondaryBackgroundColor());
    setSecondBorderColor(theme.getSecondBorderColor());
    setTableSelectedColor(theme.getTableSelectedColor());
    setHighlightColor(theme.getHighlightColor());
    setTreeSelectionColor(theme.getTreeSelectionColor());
    setNotificationsColor(theme.getNotificationsColor());
    setAccentColor(theme.getAccentColor());
    setExcludedColor(theme.getExcludedColor());
  }
}
