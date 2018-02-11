/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

import com.chrisrm.idea.MTAbstractTheme;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class MTBundledTheme extends MTAbstractTheme implements Serializable {
  private List<MTThemeColor> colors;

  public MTBundledTheme() {
    this("mt.custom", "External Theme", true);
  }

  protected MTBundledTheme(@NotNull final String id,
                           @NotNull final String editorColorsScheme,
                           final boolean dark) {
    super(id, editorColorsScheme, dark);
  }

  public static String notificationsColor = "323232";
  public static String treeSelectionColor = "546E7A50";
  public static String buttonHighlightColor = "304146";
  public static String highlightColor = "425B67";
  public static String secondBorderColor = "2A373E";
  public static String tableSelectedColor = "314549";
  public static String contrastColor = "1E272C";
  public static String disabledColor = "415967";
  public static String secondaryBackgroundColor = "32424A";
  public static String caretColor = "FFCC00";
  public static String inactiveColor = "2E3C43";
  public static String selectionForegroundColor = "FFFFFF";
  public static String selectionBackgroundColor = "546E7A";
  public static String textColor = "607D8B";
  public static String foregroundColor = "B0BEC5";
  public static String backgroundColor = "263238";

  public List<MTThemeColor> getColors() {
    return colors;
  }

  public void setColors(final List<MTThemeColor> colors) {
    this.colors = colors;
  }

  @Override
  protected String getNotificationsColorString() {
    return ObjectUtils.notNull(findColor("notifications"), notificationsColor);
  }

  @Override
  protected String getTreeSelectionColorString() {
    return ObjectUtils.notNull(findColor("treeSelection"), treeSelectionColor);
  }

  @Override
  protected String getButtonHighlightColorString() {
    return ObjectUtils.notNull(findColor("buttonHighlight"), buttonHighlightColor);
  }

  @Override
  protected String getHighlightColorString() {
    return ObjectUtils.notNull(findColor("highlight"), highlightColor);
  }

  @Override
  protected String getSecondBorderColorString() {
    return ObjectUtils.notNull(findColor("secondBorder"), secondBorderColor);
  }

  @Override
  protected String getTableSelectedColorString() {
    return ObjectUtils.notNull(findColor("tableSelected"), tableSelectedColor);
  }

  @Override
  protected String getContrastColorString() {
    return ObjectUtils.notNull(findColor("contrast"), contrastColor);
  }

  @Override
  protected String getDisabledColorString() {
    return ObjectUtils.notNull(findColor("disabled"), disabledColor);
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return ObjectUtils.notNull(findColor("secondaryBackground"), secondaryBackgroundColor);
  }

  @Override
  protected String getCaretColorString() {
    return ObjectUtils.notNull(findColor("caret"), caretColor);
  }

  @Override
  protected String getInactiveColorString() {
    return ObjectUtils.notNull(findColor("inactive"), inactiveColor);
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return ObjectUtils.notNull(findColor("selectionForeground"), selectionForegroundColor);
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return ObjectUtils.notNull(findColor("selectionBackground"), selectionBackgroundColor);
  }

  @Override
  protected String getTextColorString() {
    return ObjectUtils.notNull(findColor("text"), textColor);
  }

  @Override
  protected String getForegroundColorString() {
    return ObjectUtils.notNull(findColor("foreground"), foregroundColor);
  }

  @Override
  protected String getBackgroundColorString() {
    return ObjectUtils.notNull(findColor("background"), backgroundColor);
  }

  private String findColor(final String id) {
    MTThemeColor result = null;
    for (final MTThemeColor color : colors) {
      if (color.getId().equals(id)) {
        result = color;
        break;
      }
    }

    if (result != null) {
      return result.getValue();
    }
    return null;
  }

}
