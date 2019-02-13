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

import com.chrisrm.idea.messages.ThemesBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for MTThemes and MTBundledThemes
 */
public interface MTThemeable {
  @NonNls
  String getThemeId();

  @NonNls
  default String getThemeName() {
    return ThemesBundle.messageWithPrefix("name", getThemeId());
  }

  @NonNls
  default String getThemeIcon() {
    return "/icons/actions/themes/" + ThemesBundle.messageWithPrefix("icon", getThemeId()) + ".svg";
  }

  @NonNls
  default String getThemeColorScheme() {
    return ThemesBundle.messageWithPrefix("scheme", getThemeId());
  }

  @NonNls
  default boolean isThemeDark() {
    return Boolean.parseBoolean(ThemesBundle.messageWithPrefix("dark", getThemeId()));
  }

  @NonNls
  default int getOrder() {
    return Integer.parseInt(ThemesBundle.messageWithPrefix("order", getThemeId()));
  }

  /**
   * Whether this is a custom theme
   */
  boolean isCustom();

  /**
   * Activate the theme
   */
  void activate();

  /**
   * Return the theme's name
   */
  @NotNull
  String getName();

  MTThemeable setName(String name);

  /**
   * The theme's inherent color scheme
   */
  String getEditorColorsScheme();

  MTThemeable setEditorColorScheme(String editorColorsScheme);

  /**
   * The theme's unique ID
   */
  @NotNull
  String getId();

  MTThemeable setId(String id);

  /**
   * Whether the theme is a dark theme
   */
  boolean isDark();

  MTThemeable setIsDark(boolean dark);

  /**
   * The theme icon
   */
  Icon getIcon();

  MTThemeable setIcon(String icon);

  /**
   * The theme's background color
   */
  @NotNull
  Color getBackgroundColor();

  /**
   * The theme's contrast color
   */
  @NotNull
  Color getContrastColor();

  /**
   * The theme's foreground color
   */
  @NotNull
  Color getForegroundColor();

  /**
   * The theme's primary color (when not foreground color)
   */
  @NotNull
  Color getPrimaryColor();

  /**
   * The theme's selection background color
   */
  Color getSelectionBackgroundColor();

  /**
   * The theme's selection foreground color
   */
  Color getSelectionForegroundColor();

  /**
   * Get the excluded files color
   */
  Color getExcludedColor();

  Color getNotificationsColor();

  Color getSecondBorderColor();

  Color getDisabledColor();

  Color getSecondaryBackgroundColor();

  Color getButtonColor();

  Color getTableSelectedColor();

  Color getTextColor();

  Color getTreeSelectionColor();

  Color getHighlightColor();
}
