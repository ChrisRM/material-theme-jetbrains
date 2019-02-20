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
import com.chrisrm.idea.config.ui.MTCustomThemeForm;
import com.chrisrm.idea.config.ui.MTForm;
import com.chrisrm.idea.listeners.CustomConfigNotifier;
import com.chrisrm.idea.themes.models.MTBundledTheme;
import com.chrisrm.idea.themes.models.MTDarkBundledTheme;
import com.chrisrm.idea.themes.models.MTThemeable;
import com.chrisrm.idea.utils.MTColorUtils;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Property;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Objects;

@SuppressWarnings({"ClassWithTooManyFields",
    "ClassWithTooManyMethods",
    "WeakerAccess",
    "PackageVisibleField",
    "PublicMethodNotExposedInInterface"})
@State(
    name = "MaterialCustomThemeConfig", //NON-NLS
    storages = @Storage("material_custom_theme.xml") //NON-NLS
)
public final class MTCustomThemeConfig implements PersistentStateComponent<MTCustomThemeConfig>,
                                                  MTBaseConfig<MTCustomThemeForm, MTCustomThemeConfig> {
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
    return ObjectUtils.assertNotNull(ServiceManager.getService(MTCustomThemeConfig.class));
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

  @Override
  public void fireBeforeChanged(final MTCustomThemeForm form) {
    //    ApplicationManager.getApplication().getMessageBus()
    //                      .syncPublisher(CustomConfigNotifier.CONFIG_TOPIC)
    //                      .customConfigChanged(this);
  }

  @Override
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(CustomConfigNotifier.CONFIG_TOPIC)
                      .customConfigChanged(this);
  }

  @NotNull
  public Color getExcludedColor() {
    return MTColorUtils.parseColor(excludedColor);
  }

  @NotNull
  public Color getAccentColor() {
    return MTColorUtils.parseColor(accentColor);
  }

  @NotNull
  public Color getNotificationsColor() {
    return MTColorUtils.parseColor(notificationsColor);
  }

  @NotNull
  public Color getTreeSelectionColor() {
    return MTColorUtils.parseColor(treeSelectionColor);
  }

  @NotNull
  public Color getHighlightColor() {
    return MTColorUtils.parseColor(highlightColor);
  }

  @NotNull
  public Color getSecondBorderColor() {
    return MTColorUtils.parseColor(secondBorderColor);
  }

  @NotNull
  public Color getTableSelectedColor() {
    return MTColorUtils.parseColor(tableSelectedColor);
  }

  @NotNull
  public Color getContrastColor() {
    return MTColorUtils.parseColor(contrastColor);
  }

  @NotNull
  public Color getDisabledColor() {
    return MTColorUtils.parseColor(disabledColor);
  }

  @NotNull
  public Color getSecondaryBackgroundColor() {
    return MTColorUtils.parseColor(secondaryBackgroundColor);
  }

  @NotNull
  public Color getButtonColor() {
    return MTColorUtils.parseColor(buttonColor);
  }

  @NotNull
  public Color getSelectionForegroundColor() {
    return MTColorUtils.parseColor(selectionForegroundColor);
  }

  @NotNull
  public Color getSelectionBackgroundColor() {
    return MTColorUtils.parseColor(selectionBackgroundColor);
  }

  @NotNull
  public Color getTextColor() {
    return MTColorUtils.parseColor(textColor);
  }

  @NotNull
  public Color getForegroundColor() {
    return MTColorUtils.parseColor(foregroundColor);
  }

  @NotNull
  public Color getBackgroundColor() {
    return MTColorUtils.parseColor(backgroundColor);
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

  public void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor = ColorUtil.toHex(backgroundColor, true);
  }

  public void setForegroundColor(final Color foregroundColor) {
    this.foregroundColor = ColorUtil.toHex(foregroundColor, true);
  }

  public void setTextColor(final Color textColor) {
    this.textColor = ColorUtil.toHex(textColor, true);
  }

  public void setSelectionBackgroundColor(final Color selectionBackgroundColor) {
    this.selectionBackgroundColor = ColorUtil.toHex(selectionBackgroundColor, true);
  }

  public void setSelectionForegroundColor(final Color selectionForegroundColor) {
    this.selectionForegroundColor = ColorUtil.toHex(selectionForegroundColor, true);
  }

  public void setButtonColor(final Color buttonColor) {
    this.buttonColor = ColorUtil.toHex(buttonColor, true);
  }

  public void setSecondaryBackgroundColor(final Color secondaryBackgroundColor) {
    this.secondaryBackgroundColor = ColorUtil.toHex(secondaryBackgroundColor, true);
  }

  public void setDisabledColor(final Color disabledColor) {
    this.disabledColor = ColorUtil.toHex(disabledColor, true);
  }

  public void setContrastColor(final Color contrastColor) {
    this.contrastColor = ColorUtil.toHex(contrastColor, true);
  }

  public void setTableSelectedColor(final Color tableSelectedColor) {
    this.tableSelectedColor = ColorUtil.toHex(tableSelectedColor, true);
  }

  public void setSecondBorderColor(final Color secondBorderColor) {
    this.secondBorderColor = ColorUtil.toHex(secondBorderColor, true);
  }

  public void setHighlightColor(final Color highlightColor) {
    this.highlightColor = ColorUtil.toHex(highlightColor, true);
  }

  public void setTreeSelectionColor(final Color treeSelectionColor) {
    this.treeSelectionColor = ColorUtil.toHex(treeSelectionColor, true);
  }

  public void setNotificationsColor(final Color notificationsColor) {
    this.notificationsColor = ColorUtil.toHex(notificationsColor, true);
  }

  public void setAccentColor(final Color accentColor) {
    this.accentColor = ColorUtil.toHex(accentColor, true);
  }

  public void setExcludedColor(final Color excludedColor) {
    this.excludedColor = ColorUtil.toHex(excludedColor, true);
  }

  public boolean isBackgroundColorChanged(final Color backgroundColor) {
    return !Objects.equals(this.backgroundColor, ColorUtil.toHex(backgroundColor, true));
  }

  public boolean isForegroundColorChanged(final Color foregroundColor) {
    return !Objects.equals(this.foregroundColor, ColorUtil.toHex(foregroundColor, true));
  }

  public boolean isTextColorChanged(final Color textColor) {
    return !Objects.equals(this.textColor, ColorUtil.toHex(textColor, true));
  }

  public boolean isSelectionBackgroundColorChanged(final Color selectionBackgroundColor) {
    return !Objects.equals(this.selectionBackgroundColor, ColorUtil.toHex(selectionBackgroundColor, true));
  }

  public boolean isSelectionForegroundColorChanged(final Color selectionForegroundColor) {
    return !Objects.equals(this.selectionForegroundColor, ColorUtil.toHex(selectionForegroundColor, true));
  }

  public boolean isButtonColorChanged(final Color buttonColor) {
    return !Objects.equals(this.buttonColor, ColorUtil.toHex(buttonColor, true));
  }

  public boolean isSecondaryBackgroundColorChanged(final Color secondaryBackgroundColor) {
    return !Objects.equals(this.secondaryBackgroundColor, ColorUtil.toHex(secondaryBackgroundColor, true));
  }

  public boolean isDisabledColorChanged(final Color disabledColor) {
    return !Objects.equals(this.disabledColor, ColorUtil.toHex(disabledColor, true));
  }

  public boolean isContrastColorChanged(final Color contrastColor) {
    return !Objects.equals(this.contrastColor, ColorUtil.toHex(contrastColor, true));
  }

  public boolean isTableSelectionColorChanged(final Color tableSelectedColor) {
    return !Objects.equals(this.tableSelectedColor, ColorUtil.toHex(tableSelectedColor, true));
  }

  public boolean isSecondBorderColorChanged(final Color secondBorderColor) {
    return !Objects.equals(this.secondBorderColor, ColorUtil.toHex(secondBorderColor, true));
  }

  public boolean isHighlightColorChanged(final Color highlightColor) {
    return !Objects.equals(this.highlightColor, ColorUtil.toHex(highlightColor, true));
  }

  public boolean isTreeSelectionColorChanged(final Color treeSelectionColor) {
    return !Objects.equals(this.treeSelectionColor, ColorUtil.toHex(treeSelectionColor, true));
  }

  public boolean isNotificationsColorChanged(final Color notificationsColor) {
    return !Objects.equals(this.notificationsColor, ColorUtil.toHex(notificationsColor, true));
  }

  public boolean isAccentColorChanged(final Color accentColor) {
    return !Objects.equals(this.accentColor, ColorUtil.toHex(accentColor, true));
  }

  public boolean isExcludedColorChanged(final Color excludedColor) {
    return !Objects.equals(this.excludedColor, ColorUtil.toHex(excludedColor, true));
  }

  @SuppressWarnings({"FeatureEnvy",
      "Duplicates"})
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
}
