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

package com.mallowigi.idea.themes.models;

import com.mallowigi.idea.messages.ThemesBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for MTThemes and MTBundledThemes
 */
@SuppressWarnings("ClassWithTooManyMethods")
public interface MTThemeable {

  @NonNls
  String getThemeId();

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

  /**
   * Set the theme name
   */
  MTThemeable setName(String name);

  /**
   * The theme's inherent color scheme
   */
  String getEditorColorsScheme();

  /**
   * Set the editor color scheme
   */
  MTThemeable setEditorColorScheme(String editorColorsScheme);

  /**
   * The theme's unique ID
   */
  @NotNull
  String getId();

  /**
   * Set the theme id
   */
  MTThemeable setId(String id);

  /**
   * Whether the theme is a dark theme
   */
  boolean isDark();

  /**
   * Set the theme dark state
   */
  MTThemeable setIsDark(boolean dark);

  /**
   * The theme icon
   */
  Icon getIcon();

  /**
   * Set the theme icon
   */
  MTThemeable setIcon(String icon);

  /**
   * The theme's background color
   */
  @NotNull
  Color getBackgroundColor();

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
   * Get the text color
   */
  @NotNull
  Color getTextColor();

  /**
   * The theme's selection background color
   */
  @NotNull
  Color getSelectionBackgroundColor();

  /**
   * The theme's selection foreground color
   */
  @NotNull
  Color getSelectionForegroundColor();

  /**
   * Get the button color
   */
  @NotNull
  Color getButtonColor();

  /**
   * Get Secondary background color
   */
  @NotNull
  Color getSecondaryBackgroundColor();

  /**
   * Get disabled color
   */
  @NotNull
  Color getDisabledColor();

  /**
   * The theme's contrast color
   */
  @NotNull
  Color getContrastColor();

  /**
   * Get the table selected color
   */
  @NotNull
  Color getTableSelectedColor();

  /**
   * Get second border color
   */
  @NotNull
  Color getSecondBorderColor();

  /**
   * Get the highlight color
   */
  @NotNull
  Color getHighlightColor();

  /**
   * Get the tree selection color
   */
  @NotNull
  Color getTreeSelectionColor();

  /**
   * Get the notifications color
   */
  @NotNull
  Color getNotificationsColor();

  /**
   * Get the default accent color
   */
  @Nullable
  Color getAccentColor();

  /**
   * Get the excluded files color
   */
  @Nullable
  Color getExcludedColor();

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
   * Clean up all irrelevant properties
   */
  void setPristine();

  /**
   * Apply contrast
   *
   * @param apply apply
   */
  void applyContrast(boolean apply);

  /**
   * Change the theme name
   *
   * @param name name
   */
  void setThemeName(String name);

  /**
   * Whether theme is native
   */
  boolean isNative();

  void applyAccentMode();
}
