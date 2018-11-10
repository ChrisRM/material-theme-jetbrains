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

import javax.swing.plaf.ColorUIResource;

/**
 * Interface for themes serializable from xml
 */
public interface MTSerializedTheme {
  /**
   * Get the hex code for the background color
   */
  @NonNls
  ColorUIResource getBackgroundColorString();

  /**
   * Get the hex code for the foreground color
   */
  @NonNls
  ColorUIResource getForegroundColorString();

  /**
   * Get the hex code for the text color
   */
  @NonNls
  ColorUIResource getTextColorString();

  /**
   * Get the hex code for the selection background color
   */
  @NonNls
  ColorUIResource getSelectionBackgroundColorString();

  /**
   * Get the hex code for the selection foreground color
   */
  @NonNls
  ColorUIResource getSelectionForegroundColorString();

  /**
   * Get the hex code for the button color
   */
  @NonNls
  ColorUIResource getButtonColorString();

  /**
   * Get the hex code for the secondary background color
   */
  @NonNls
  ColorUIResource getSecondaryBackgroundColorString();

  /**
   * Get the hex code for the disabled color
   */
  @NonNls
  ColorUIResource getDisabledColorString();

  /**
   * Get the hex code for the contrast color
   */
  @NonNls
  ColorUIResource getContrastColorString();

  /**
   * Get the hex code for the table selected color
   */
  @NonNls
  ColorUIResource getTableSelectedColorString();

  /**
   * Get the hex code for the second border color
   */
  @NonNls
  ColorUIResource getSecondBorderColorString();

  /**
   * Get the hex code for the highlight color
   */
  @NonNls
  ColorUIResource getHighlightColorString();

  /**
   * Get the hex code for the tree selection color
   */
  @NonNls
  ColorUIResource getTreeSelectionColorString();

  /**
   * Get the hex code for the notifications color
   */
  @NonNls
  ColorUIResource getNotificationsColorString();

  /**
   * The accent color string
   */
  @NonNls
  ColorUIResource getAccentColorString();

  /**
   * The excluded files color string
   */
  @NonNls
  ColorUIResource getExcludedColorString();
}
