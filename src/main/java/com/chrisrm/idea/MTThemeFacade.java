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

package com.chrisrm.idea;

import com.chrisrm.idea.themes.MTThemeable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Interface Facade for themes.
 *
 * <p>
 * Not to be confused with MTThemeable. MTThemeable is an interface to design themes by defining their resources, such as
 * getBackgroundColor, getSelectionColor, activate, etc, whereas MTThemeFacade represents themes that can be serialized, containing
 * methods such as getThemeId, getThemeIsDark, getAuthor etc.
 * </p>
 * <p>
 * A MTConfig contains a MTThemeFacade which contains itself a MTThemeable.
 * </p>
 */
public interface MTThemeFacade {
  /**
   * The internal theme's color scheme
   *
   * @return The color scheme
   */
  @NotNull
  String getThemeColorScheme();

  /**
   * The internal theme
   *
   * @return the themeable
   */
  @NotNull
  MTThemeable getTheme();

  /**
   * Whether a theme is dark
   *
   * @return if the theme is dark
   */
  boolean isDark();

  /**
   * The theme name
   *
   * @return the theme name
   */
  @NotNull
  String getName();

  /**
   * The internal theme name, e.g. the name to display in dropdowns
   *
   * @return the internal theme name
   */
  @Nullable
  String getThemeName();

  /**
   * The internal theme id (OCEANIC, DARKER, PALENIGHT...)
   *
   * @return the unique identifier for themes
   */
  @NotNull
  String getThemeId();

  /**
   * The theme icon
   *
   * @return the theme's icon
   * @todo Implement icon loading for extenral themes
   */
  Icon getIcon();

  /**
   * The predefined accent color to set if "override accent color" is set
   *
   * @return the predefined accent color
   */
  String getAccentColor();

  /**
   * The external/excluded files color
   *
   * @return the excluded files color
   */
  String getExcludedColor();

  /**
   * The order in the list
   *
   * @return the order
   */
  int getOrder();

  /**
   * If the theme is a premium theme
   *
   * @return true if premium
   */
  boolean isPremium();
  /**
   * Is the theme custom
   */
  boolean isCustom();
}
