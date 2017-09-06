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

import com.chrisrm.idea.config.CustomConfigNotifier;
import com.chrisrm.idea.utils.PropertiesParser;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.ColorUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

@State(
    name = "MaterialCustomThemeConfig",
    storages = @Storage("material_custom_theme.xml")
)
public final class MTCustomThemeConfig implements PersistentStateComponent<MTCustomThemeConfig> {

  public String treeSelectionColor = "546E7A50";
  public String buttonHighlightColor = "304146";
  public String highlightColor = "425B67";
  public String secondBorderColor = "2A373E";
  public String tableSelectedColor = "314549";
  public String contrastColor = "1E272C";
  public String disabledColor = "415967";
  public String secondaryBackgroundColor = "32424A";
  public String caretColor = "FFCC00";
  public String inactiveColor = "2E3C43";
  public String selectionForegroundColor = "FFFFFF";
  public String selectionBackgroundColor = "546E7A";
  public String textColor = "607D8B";
  public String foregroundColor = "B0BEC5";
  public String backgroundColor = "263238";

  public static MTCustomThemeConfig getInstance() {
    return ServiceManager.getService(MTCustomThemeConfig.class);
  }

  @NotNull
  @Override
  public MTCustomThemeConfig getState() {
    return this;
  }

  @Override
  public void loadState(final MTCustomThemeConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(CustomConfigNotifier.CONFIG_TOPIC)
                      .customConfigChanged(this);
  }

  public Color getTreeSelectionColor() {
    return PropertiesParser.parseColor(getTreeSelectionColorString());
  }

  public Color getButtonHighlightColor() {
    return PropertiesParser.parseColor(getButtonHighlightColorString());
  }

  public Color getHighlightColor() {
    return PropertiesParser.parseColor(getHighlightColorString());
  }

  public Color getSecondBorderColor() {
    return PropertiesParser.parseColor(getSecondBorderColorString());
  }

  public Color getTableSelectedColor() {
    return PropertiesParser.parseColor(getTableSelectedColorString());
  }

  public Color getContrastColor() {
    return PropertiesParser.parseColor(getContrastColorString());
  }

  public Color getDisabledColor() {
    return PropertiesParser.parseColor(getDisabledColorString());
  }

  public Color getSecondaryBackgroundColor() {
    return PropertiesParser.parseColor(getSecondaryBackgroundColorString());
  }

  public Color getCaretColor() {
    return PropertiesParser.parseColor(getCaretColorString());
  }

  public Color getInactiveColor() {
    return PropertiesParser.parseColor(getInactiveColorString());
  }

  public Color getSelectionForegroundColor() {
    return PropertiesParser.parseColor(getSelectionForegroundColorString());
  }

  public Color getSelectionBackgroundColor() {
    return PropertiesParser.parseColor(getSelectionBackgroundColorString());
  }

  public Color getTextColor() {
    return PropertiesParser.parseColor(getTextColorString());
  }

  public Color getForegroundColor() {
    return PropertiesParser.parseColor(getForegroundColorString());
  }

  public Color getBackgroundColor() {
    return PropertiesParser.parseColor(getBackgroundColorString());
  }

  public String getTreeSelectionColorString() {
    return treeSelectionColor;
  }

  public String getButtonHighlightColorString() {
    return buttonHighlightColor;
  }

  public String getHighlightColorString() {
    return highlightColor;
  }

  public String getSecondBorderColorString() {
    return secondBorderColor;
  }

  public String getTableSelectedColorString() {
    return tableSelectedColor;
  }

  public String getContrastColorString() {
    return contrastColor;
  }

  public String getDisabledColorString() {
    return disabledColor;
  }

  public String getSecondaryBackgroundColorString() {
    return secondaryBackgroundColor;
  }

  public String getCaretColorString() {
    return caretColor;
  }

  public String getInactiveColorString() {
    return inactiveColor;
  }

  public String getSelectionForegroundColorString() {
    return selectionForegroundColor;
  }

  public String getSelectionBackgroundColorString() {
    return selectionBackgroundColor;
  }

  public String getTextColorString() {
    return textColor;
  }

  public String getForegroundColorString() {
    return foregroundColor;
  }

  public String getBackgroundColorString() {
    return backgroundColor;
  }

  public void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor = ColorUtil.toHex(backgroundColor);
  }

  public void setForegroundColor(final Color foregroundColor) {
    this.foregroundColor = ColorUtil.toHex(foregroundColor);
  }

  public void setTextColor(final Color textColor) {
    this.textColor = ColorUtil.toHex(textColor);
  }

  public void setSelectionBackgroundColor(final Color selectionBackgroundColor) {
    this.selectionBackgroundColor = ColorUtil.toHex(selectionBackgroundColor);
  }

  public void setSelectionForegroundColor(final Color selectionForegroundColor) {
    this.selectionForegroundColor = ColorUtil.toHex(selectionForegroundColor);
  }

  public void setInactiveColor(final Color inactiveColor) {
    this.inactiveColor = ColorUtil.toHex(inactiveColor);
  }

  public void setCaretColor(final Color caretColor) {
    this.caretColor = ColorUtil.toHex(caretColor);
  }

  public void setSecondaryBackgroundColor(final Color secondaryBackgroundColor) {
    this.secondaryBackgroundColor = ColorUtil.toHex(secondaryBackgroundColor);
  }

  public void setDisabledColor(final Color disabledColor) {
    this.disabledColor = ColorUtil.toHex(disabledColor);
  }

  public void setContrastColor(final Color contrastColor) {
    this.contrastColor = ColorUtil.toHex(contrastColor);
  }

  public void setTableSelectedColor(final Color tableSelectedColor) {
    this.tableSelectedColor = ColorUtil.toHex(tableSelectedColor);
  }

  public void setSecondBorderColor(final Color secondBorderColor) {
    this.secondBorderColor = ColorUtil.toHex(secondBorderColor);
  }

  public void setHighlightColor(final Color highlightColor) {
    this.highlightColor = ColorUtil.toHex(highlightColor);
  }

  public void setButtonHighlightColor(final Color buttonHighlightColor) {
    this.buttonHighlightColor = ColorUtil.toHex(buttonHighlightColor);
  }


  public void setTreeSelectionColor(final Color treeSelectionColor) {
    this.treeSelectionColor = ColorUtil.toHex(treeSelectionColor);
  }

  public static final class MTCustomDefaults {
    public static Color treeSelectionColor = ColorUtil.toAlpha(new ColorUIResource(0x546E7A), 50);
    public static ColorUIResource buttonHighlightColor = new ColorUIResource(0x304146);
    public static ColorUIResource highlightColor = new ColorUIResource(0x425B67);
    public static ColorUIResource secondBorderColor = new ColorUIResource(0x2A373E);
    public static ColorUIResource tableSelectedColor = new ColorUIResource(0x314549);
    public static ColorUIResource contrastColor = new ColorUIResource(0x1E272C);
    public static ColorUIResource disabledColor = new ColorUIResource(0x415967);
    public static ColorUIResource secondaryBackgroundColor = new ColorUIResource(0x32424A);
    public static ColorUIResource caretColor = new ColorUIResource(0xFFCC00);
    public static ColorUIResource inactiveColor = new ColorUIResource(0x2E3C43);
    public static ColorUIResource selectionForegroundColor = new ColorUIResource(0xFFFFFF);
    public static ColorUIResource selectionBackgroundColor = new ColorUIResource(0x546E7A);
    public static ColorUIResource textColor = new ColorUIResource(0x607D8B);
    public static ColorUIResource foregroundColor = new ColorUIResource(0xB0BEC5);
    public static ColorUIResource backgroundColor = new ColorUIResource(0x263238);
  }

}
