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

package com.chrisrm.idea.themes.models;

import com.chrisrm.idea.themes.models.parsers.MTBundledThemeParser;
import com.chrisrm.idea.themes.themes.MTAbstractTheme;
import com.intellij.util.ObjectUtils;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MTBundledTheme extends MTAbstractTheme {
  @Tag
  private final List<MTThemeColor> colors = new ArrayList<>(16);
  @Property
  protected String themeId;

  @Property
  @Deprecated
  protected String accentColor;
  @Property
  @Deprecated
  protected String excludedColor;

  /**
   * The theme parser, according to the bridge design pattern every subclass must define the parser
   */
  protected abstract MTBundledThemeParser getThemeParser();

  @SuppressWarnings("NoopMethodInAbstractClass")
  @Override
  protected void init() {
  }

  @Override
  public final String getThemeName() {
    return getName();
  }

  //region --------------- Setters ------------------
  public final void setExcludedColor(final ColorUIResource excludedColor) {
    getThemeParser().setExcludedColor(excludedColor);
  }

  public final void setAccentColor(final ColorUIResource accentColor) {
    getThemeParser().setAccentColor(accentColor);
  }

  public final void setNotificationsColor(final ColorUIResource notificationsColor) {
    getThemeParser().setNotificationsColor(notificationsColor);
  }

  public final void setHighlightColor(final ColorUIResource highlightColor) {
    getThemeParser().setHighlightColor(highlightColor);
  }

  public final void setTreeSelectionColor(final ColorUIResource treeSelectionColor) {
    getThemeParser().setTreeSelectionColor(treeSelectionColor);
  }

  public final void setSecondBorderColor(final ColorUIResource secondBorderColor) {
    getThemeParser().setSecondBorderColor(secondBorderColor);
  }

  public final void setTableSelectedColor(final ColorUIResource tableSelectedColor) {
    getThemeParser().setTableSelectedColor(tableSelectedColor);
  }

  public final void setContrastColor(final ColorUIResource contrastColor) {
    getThemeParser().setContrastColor(contrastColor);
  }

  public final void setDisabledColor(final ColorUIResource disabledColor) {
    getThemeParser().setDisabledColor(disabledColor);
  }

  public final void setSecondaryBackgroundColor(final ColorUIResource secondaryBackgroundColor) {
    getThemeParser().setSecondaryBackgroundColor(secondaryBackgroundColor);
  }

  public final void setButtonColor(final ColorUIResource buttonColor) {
    getThemeParser().setButtonColor(buttonColor);
  }

  public final void setSelectionForegroundColor(final ColorUIResource selectionForegroundColor) {
    getThemeParser().setSelectionForegroundColor(selectionForegroundColor);
  }

  public final void setSelectionBackgroundColor(final ColorUIResource selectionBackgroundColor) {
    getThemeParser().setSelectionBackgroundColor(selectionBackgroundColor);
  }

  public final void setTextColor(final ColorUIResource textColor) {
    getThemeParser().setTextColor(textColor);
  }

  public final void setForegroundColor(final ColorUIResource foregroundColor) {
    getThemeParser().setForegroundColor(foregroundColor);
  }

  public final void setBackgroundColor(final ColorUIResource backgroundColor) {
    getThemeParser().setBackgroundColor(backgroundColor);
  }

  public final void setThemeId(final String themeId) {
    this.themeId = themeId;
  }
  //endregion

  //region --------------- Getters ------------------

  /**
   * Get and parse the accent color
   */
  @Override
  public final ColorUIResource getAccentColorResource() {
    return getThemeParser().getAccentColorString();
  }

  /**
   * Get and parse the excluded color
   */
  @Override
  public final ColorUIResource getExcludedColorResource() {
    return getThemeParser().getExcludedColorString();
  }

  @Override
  public final ColorUIResource getBackgroundColorResource() {
    return getThemeParser().getBackgroundColorString();
  }

  @Override
  public final ColorUIResource getForegroundColorResource() {
    return getThemeParser().getForegroundColorString();
  }

  @Override
  public final ColorUIResource getTextColorResource() {
    return getThemeParser().getTextColorString();
  }

  @Override
  public final ColorUIResource getSelectionBackgroundColorResource() {
    return getThemeParser().getSelectionBackgroundColorString();
  }

  @Override
  public final ColorUIResource getSelectionForegroundColorResource() {
    return getThemeParser().getSelectionForegroundColorString();
  }

  @Override
  public final ColorUIResource getButtonColorResource() {
    return getThemeParser().getButtonColorString();
  }

  @Override
  public final ColorUIResource getSecondaryBackgroundColorResource() {
    return getThemeParser().getSecondaryBackgroundColorString();
  }

  @Override
  public final ColorUIResource getDisabledColorResource() {
    return getThemeParser().getDisabledColorString();
  }

  @Override
  public final ColorUIResource getContrastColorResource() {
    return getThemeParser().getContrastColorString();
  }

  @Override
  public final ColorUIResource getTableSelectedColorResource() {
    return getThemeParser().getTableSelectedColorString();
  }

  @Override
  public final ColorUIResource getSecondBorderColorResource() {
    return getThemeParser().getSecondBorderColorString();
  }

  @Override
  public final ColorUIResource getHighlightColorResource() {
    return getThemeParser().getHighlightColorString();
  }

  @Override
  public final ColorUIResource getTreeSelectionColorResource() {
    return getThemeParser().getTreeSelectionColorString();
  }

  @Override
  public final ColorUIResource getNotificationsColorResource() {
    return getThemeParser().getNotificationsColorString();
  }

  @NotNull
  @Override
  public final String getThemeId() {
    return ObjectUtils.notNull(themeId, "");
  }

  /**
   * Add a big number for the order so it appears at the end
   */
  @Override
  public abstract int getOrder();

  public final Collection<MTThemeColor> getColors() {
    return colors;
  }
  //endregion
}
