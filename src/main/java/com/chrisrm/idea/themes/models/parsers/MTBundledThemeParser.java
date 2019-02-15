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

package com.chrisrm.idea.themes.models.parsers;

import com.chrisrm.idea.themes.models.MTBundledTheme;
import com.chrisrm.idea.themes.models.MTThemeColor;
import com.chrisrm.idea.utils.MTColorUtils;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.ColorUIResource;
import java.util.Collection;

/**
 * Bridge class for Bundled themes for parsing bundled themes xml
 */
@SuppressWarnings({"ClassWithTooManyMethods",
    "DuplicateStringLiteralInspection"})
public abstract class MTBundledThemeParser {
  @NonNls
  private static final String EXCLUDED_TAG = "excluded";
  @NonNls
  private static final String ACCENT_TAG = "accent";
  @NonNls
  private static final String NOTIFICATIONS_TAG = "notifications";
  @NonNls
  private static final String TREE_SELECTION_TAG = "treeSelection";
  @NonNls
  private static final String HIGHLIGHT_TAG = "highlight";
  @NonNls
  private static final String SECOND_BORDER_TAG = "secondBorder";
  @NonNls
  private static final String TABLE_SELECTED_TAG = "tableSelected";
  @NonNls
  private static final String CONTRAST_TAG = "contrast";
  @NonNls
  private static final String DISABLED_TAG = "disabled";
  @NonNls
  private static final String SECONDARY_BACKGROUND_TAG = "secondaryBackground";
  @NonNls
  private static final String BUTTON_TAG = "button";
  @NonNls
  private static final String SELECTION_FOREGROUND_TAG = "selectionForeground";
  @NonNls
  private static final String SELECTION_BACKGROUND_TAG = "selectionBackground";
  @NonNls
  private static final String TEXT_TAG = "text";
  @NonNls
  private static final String FOREGROUND_TAG = "foreground";
  @NonNls
  private static final String BACKGROUND_TAG = "background";

  private final MTBundledTheme mtBundledTheme;

  @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
  MTBundledThemeParser(final MTBundledTheme mtBundledTheme) {
    this.mtBundledTheme = mtBundledTheme;
  }

  //region ----------- Default colors -------------
  protected abstract ColorUIResource getDefaultExcludedColor();

  protected abstract ColorUIResource getDefaultAccentColor();

  protected abstract ColorUIResource getDefaultNotificationsColor();

  protected abstract ColorUIResource getDefaultTreeSelectionColor();

  protected abstract ColorUIResource getDefaultHighlightColor();

  protected abstract ColorUIResource getDefaultSecondBorderColor();

  protected abstract ColorUIResource getDefaultTableSelectedColor();

  protected abstract ColorUIResource getDefaultContrastColor();

  protected abstract ColorUIResource getDefaultDisabledColor();

  protected abstract ColorUIResource getDefaultSecondaryBackgroundColor();

  protected abstract ColorUIResource getDefaultButtonColor();

  protected abstract ColorUIResource getDefaultSelectionForegroundColor();

  protected abstract ColorUIResource getDefaultSelectionBackgroundColor();

  protected abstract ColorUIResource getDefaultTextColor();

  protected abstract ColorUIResource getDefaultForegroundColor();

  protected abstract ColorUIResource getDefaultBackgroundColor();
  //endregion

  //region ------------ Getters ---------------
  public final ColorUIResource getExcludedColorString() {
    return getColor(EXCLUDED_TAG, getDefaultExcludedColor());
  }

  public final ColorUIResource getAccentColorString() {
    return getColor(ACCENT_TAG, getDefaultAccentColor());
  }

  public final ColorUIResource getNotificationsColorString() {
    return getColor(NOTIFICATIONS_TAG, getDefaultNotificationsColor());
  }

  public final ColorUIResource getTreeSelectionColorString() {
    return getColor(TREE_SELECTION_TAG, getDefaultTreeSelectionColor());
  }

  public final ColorUIResource getHighlightColorString() {
    return getColor(HIGHLIGHT_TAG, getDefaultHighlightColor());
  }

  public final ColorUIResource getSecondBorderColorString() {
    return getColor(SECOND_BORDER_TAG, getDefaultSecondBorderColor());
  }

  public final ColorUIResource getTableSelectedColorString() {
    return getColor(TABLE_SELECTED_TAG, getDefaultTableSelectedColor());
  }

  public final ColorUIResource getContrastColorString() {
    return getColor(CONTRAST_TAG, getDefaultContrastColor());
  }

  public final ColorUIResource getDisabledColorString() {
    return getColor(DISABLED_TAG, getDefaultDisabledColor());
  }

  public final ColorUIResource getSecondaryBackgroundColorString() {
    return getColor(SECONDARY_BACKGROUND_TAG, getDefaultSecondaryBackgroundColor());
  }

  public final ColorUIResource getButtonColorString() {
    return getColor(BUTTON_TAG, getDefaultButtonColor());
  }

  public final ColorUIResource getSelectionForegroundColorString() {
    return getColor(SELECTION_FOREGROUND_TAG, getDefaultSelectionForegroundColor());
  }

  public final ColorUIResource getSelectionBackgroundColorString() {
    return getColor(SELECTION_BACKGROUND_TAG, getDefaultSelectionBackgroundColor());
  }

  public final ColorUIResource getTextColorString() {
    return getColor(TEXT_TAG, getDefaultTextColor());
  }

  public final ColorUIResource getForegroundColorString() {
    return getColor(FOREGROUND_TAG, getDefaultForegroundColor());
  }

  public final ColorUIResource getBackgroundColorString() {
    return getColor(BACKGROUND_TAG, getDefaultBackgroundColor());
  }
  //endregion

  //region --------------- Setters -----------------
  public final void setExcludedColor(final ColorUIResource excludedColor) {
    setColor(EXCLUDED_TAG, excludedColor);
  }

  public final void setAccentColor(final ColorUIResource accentColor) {
    setColor(ACCENT_TAG, accentColor);
  }

  public final void setNotificationsColor(final ColorUIResource notificationsColor) {
    setColor(NOTIFICATIONS_TAG, notificationsColor);
  }

  public final void setHighlightColor(final ColorUIResource highlightColor) {
    setColor(HIGHLIGHT_TAG, highlightColor);
  }

  public final void setTreeSelectionColor(final ColorUIResource treeSelectionColor) {
    setColor(TREE_SELECTION_TAG, treeSelectionColor);
  }

  public final void setSecondBorderColor(final ColorUIResource secondBorderColor) {
    setColor(SECOND_BORDER_TAG, secondBorderColor);
  }

  public final void setTableSelectedColor(final ColorUIResource tableSelectedColor) {
    setColor(TABLE_SELECTED_TAG, tableSelectedColor);
  }

  public final void setContrastColor(final ColorUIResource contrastColor) {
    setColor(CONTRAST_TAG, contrastColor);
  }

  public final void setDisabledColor(final ColorUIResource disabledColor) {
    setColor(DISABLED_TAG, disabledColor);
  }

  public final void setSecondaryBackgroundColor(final ColorUIResource secondaryBackgroundColor) {
    setColor(SECONDARY_BACKGROUND_TAG, secondaryBackgroundColor);
  }

  public final void setButtonColor(final ColorUIResource buttonColor) {
    setColor(BUTTON_TAG, buttonColor);
  }

  public final void setSelectionForegroundColor(final ColorUIResource selectionForegroundColor) {
    setColor(SELECTION_FOREGROUND_TAG, selectionForegroundColor);
  }

  public final void setSelectionBackgroundColor(final ColorUIResource selectionBackgroundColor) {
    setColor(SELECTION_BACKGROUND_TAG, selectionBackgroundColor);
  }

  public final void setTextColor(final ColorUIResource textColor) {
    setColor(TEXT_TAG, textColor);
  }

  public final void setForegroundColor(final ColorUIResource foregroundColor) {
    setColor(FOREGROUND_TAG, foregroundColor);
  }

  public final void setBackgroundColor(final ColorUIResource backgroundColor) {
    setColor(BACKGROUND_TAG, backgroundColor);
  }
  //endregion

  /**
   * Return the color parsed from the XML file, or return the default color if not found
   */
  @NotNull
  private ColorUIResource getColor(final String tag, final ColorUIResource defaultColor) {
    final MTThemeColor color = findColor(tag);
    if (color == null) {
      return defaultColor;
    }

    return new ColorUIResource(MTColorUtils.parseColor(color.getValue()));
  }

  private Collection<MTThemeColor> getColors() {
    return mtBundledTheme.getColors();
  }

  private void setColor(final String tag, final ColorUIResource newColor) {
    MTThemeColor color = findColor(tag);
    if (color == null) {
      color = new MTThemeColor();
      color.setId(tag);
      getColors().add(color);
    }

    color.setValue(ColorUtil.toHex(newColor, true));
  }

  /**
   * Find a color in the parsed colors
   * TODO use streams
   */
  @Nullable
  private MTThemeColor findColor(@NonNls final String id) {
    MTThemeColor result = null;
    for (final MTThemeColor color : getColors()) {
      if (color.getId().equals(id)) {
        result = color;
        break;
      }
    }

    return result;
  }
}
