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

import com.chrisrm.idea.themes.MTLightCustomTheme;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a theme that is parsable from XML
 */
public class MTLightBundledTheme extends MTLightCustomTheme implements Serializable, MTBundledTheme {
  private List<MTThemeColor> colors;
  private String themeId;
  private String accentColor;
  private String excludedColor;

  public MTLightBundledTheme() {
    this("mt.light_custom", "External Theme", true);
  }

  protected MTLightBundledTheme(@NotNull final String id,
                                @NotNull final String editorColorsScheme,
                                final boolean dark) {
    super();
  }

  /**
   * The ID of Light bundled theme
   *
   * @return
   */
  @NotNull
  @Override
  public String getId() {
    return "mt.light_custom";
  }

  private static final String NOTIFICATIONS_COLOR = "80cbc4";
  private static final String TREE_SELECTION_COLOR = "546E7A50";
  private static final String HIGHLIGHT_COLOR = "D2D4D5";
  private static final String SECOND_BORDER_COLOR = "d3e1e8";
  private static final String TABLE_SELECTED_COLOR = "D2D4D5";
  private static final String CONTRAST_COLOR = "F4F4F4";
  private static final String DISABLED_COLOR = "D2D4D5";
  private static final String SECONDARY_BACKGROUND_COLOR = "eae8e8";
  private static final String BUTTON_COLOR = "F3F4F5";
  private static final String SELECTION_FOREGROUND_COLOR = "FFFFFF";
  private static final String SELECTION_BACKGROUND_COLOR = "546E7A";
  private static final String TEXT_COLOR = "94A7B0";
  private static final String FOREGROUND_COLOR = "546E7A";
  private static final String BACKGROUND_COLOR = "FAFAFA";

  public List<MTThemeColor> getColors() {
    return colors;
  }

  public void setColors(final List<MTThemeColor> colors) {
    this.colors = colors;
  }

  /**
   * Theme ID
   *
   * @return
   */
  @NotNull
  @Override
  public String getThemeId() {
    return themeId;
  }

  public void setThemeId(final String themeId) {
    this.themeId = themeId;
  }

  @Override
  public String getNotificationsColorString() {
    return ObjectUtils.notNull(findColor("notifications"), NOTIFICATIONS_COLOR);
  }

  @Override
  public String getTreeSelectionColorString() {
    return ObjectUtils.notNull(findColor("treeSelection"), TREE_SELECTION_COLOR);
  }

  @Override
  public String getHighlightColorString() {
    return ObjectUtils.notNull(findColor("highlight"), HIGHLIGHT_COLOR);
  }

  @Override
  public String getSecondBorderColorString() {
    return ObjectUtils.notNull(findColor("secondBorder"), SECOND_BORDER_COLOR);
  }

  @Override
  public String getTableSelectedColorString() {
    return ObjectUtils.notNull(findColor("tableSelected"), TABLE_SELECTED_COLOR);
  }

  @Override
  public String getContrastColorString() {
    return ObjectUtils.notNull(findColor("contrast"), CONTRAST_COLOR);
  }

  @Override
  public String getDisabledColorString() {
    return ObjectUtils.notNull(findColor("disabled"), DISABLED_COLOR);
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return ObjectUtils.notNull(findColor("secondaryBackground"), SECONDARY_BACKGROUND_COLOR);
  }

  @Override
  public String getButtonColorString() {
    return ObjectUtils.notNull(findColor("button"), BUTTON_COLOR);
  }

  @Override
  public String getSelectionForegroundColorString() {
    return ObjectUtils.notNull(findColor("selectionForeground"), SELECTION_FOREGROUND_COLOR);
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return ObjectUtils.notNull(findColor("selectionBackground"), SELECTION_BACKGROUND_COLOR);
  }

  @Override
  public String getTextColorString() {
    return ObjectUtils.notNull(findColor("text"), TEXT_COLOR);
  }

  @Override
  public String getForegroundColorString() {
    return ObjectUtils.notNull(findColor("foreground"), FOREGROUND_COLOR);
  }

  @Override
  public String getBackgroundColorString() {
    return ObjectUtils.notNull(findColor("background"), BACKGROUND_COLOR);
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

  @Override
  public String getAccentColor() {
    return accentColor;
  }


  @Override
  public String getExcludedColor() {
    return excludedColor;
  }


  @Override
  public int getOrder() {
    return 200;
  }

}
