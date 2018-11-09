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

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface MTBundledTheme extends MTThemeable {

  @NonNls
  String NOTIFICATIONS_TAG = "notifications";
  @NonNls
  String TREE_SELECTION_TAG = "treeSelection";
  @NonNls
  String HIGHLIGHT_TAG = "highlight";
  @NonNls
  String SECOND_BORDER_TAG = "secondBorder";
  @NonNls
  String TABLE_SELECTED_TAG = "tableSelected";
  @NonNls
  String CONTRAST_TAG = "contrast";
  @NonNls
  String DISABLED_TAG = "disabled";
  @NonNls
  String SECONDARY_BACKGROUND_TAG = "secondaryBackground";
  @NonNls
  String BUTTON_TAG = "button";
  @NonNls
  String SELECTION_FOREGROUND_TAG = "selectionForeground";
  @NonNls
  String SELECTION_BACKGROUND_TAG = "selectionBackground";
  @NonNls
  String TEXT_TAG = "text";
  @NonNls
  String FOREGROUND_TAG = "foreground";
  @NonNls
  String BACKGROUND_TAG = "background";

  /**
   * Return the colors
   */
  List<? extends MTThemeColor> getColors();

  /**
   * Set the colors
   */
  //  void setColors(List<? extends MTThemeColor> colors);
  @Nullable
  default String findColor(@NonNls final String id) {
    MTThemeColor result = null;
    for (final MTThemeColor color : getColors()) {
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
