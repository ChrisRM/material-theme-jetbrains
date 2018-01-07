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

package com.chrisrm.idea.themes;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Interface for MTThemes
 */
public interface MTThemeable {
  /**
   * Activate the theme
   */
  void activate();

  /**
   * Return the theme's name
   *
   * @return
   */
  @NotNull
  String getName();

  /**
   * Change the theme's name
   *
   * @param name
   */
  void setName(String name);

  /**
   * The theme's inherent color scheme
   *
   * @return
   */
  String getEditorColorsScheme();

  /**
   * The theme's unique ID
   *
   * @return
   */
  @NotNull
  String getId();

  /**
   * Whether the theme is a dark theme
   *
   * @return
   */
  boolean isDark();

  @NotNull
  Color getBackgroundColor();

  @NotNull
  Color getBorderColor();

  int getBorderThickness();

  @NotNull
  Color getContrastColor();

  @NotNull
  String getSelectionBackground();

  @NotNull
  String getDisabled();
}
