/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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
package com.mallowigi.idea.themes.models

import java.awt.Color
import javax.swing.Icon

/**
 * Interface for MTThemes and MTBundledThemes
 */
@Suppress("ClassOrdering")
interface MTThemeable {
  val themeId: String

  /**
   * Whether this is a custom theme
   */
  val isCustom: Boolean

  /**
   * Return the theme's name
   */
  var name: String

  /**
   * The theme's inherent color scheme
   */
  val editorColorsScheme: String?

  /**
   * The theme icon
   */
  val icon: Icon?

  /**
   * The theme's background color
   */
  val backgroundColor: Color

  /**
   * The theme's foreground color
   */
  val foregroundColor: Color

  /**
   * The theme's primary color (when not foreground color)
   */
  val primaryColor: Color

  /**
   * Get the text color
   */
  val textColor: Color

  /**
   * The theme's selection background color
   */
  val selectionBackgroundColor: Color

  /**
   * The theme's selection foreground color
   */
  val selectionForegroundColor: Color

  /**
   * Get the button color
   */
  val buttonColor: Color

  /**
   * Get Secondary background color
   */
  val secondaryBackgroundColor: Color

  /**
   * Get disabled color
   */
  val disabledColor: Color

  /**
   * The theme's contrast color
   */
  val contrastColor: Color

  /**
   * Get the table selected color
   */
  val tableSelectedColor: Color

  /**
   * Get second border color
   */
  val secondBorderColor: Color

  /**
   * Get the highlight color
   */
  val highlightColor: Color

  /**
   * Get the tree selection color
   */
  val treeSelectionColor: Color

  /**
   * Get the notifications color
   */
  val notificationsColor: Color

  /**
   * Get the default accent color
   */
  val accentColor: Color

  /**
   * Get the excluded files color
   */
  val excludedColor: Color

  /**
   * Theme name
   */
  val themeName: String

  /**
   * Theme icon (optional)
   */
  val themeIcon: String?

  /**
   * Theme color scheme (optional)
   */
  val themeColorScheme: String?

  /**
   * Is theme dark?
   */
  var isThemeDark: Boolean

  /**
   * Theme Order in the dropdown
   */
  val order: Int

  /**
   * Activate the theme
   */
  fun activate()

  /**
   * Clean up all irrelevant properties
   */
  fun setPristine()

  /**
   * Apply contrast
   *
   * @param apply apply
   */
  fun applyContrast(apply: Boolean)

  /**
   * Whether theme is native
   */
  val isNative: Boolean

  /**
   * Apply accent mode for the theme
   */
  fun applyAccentMode()

  /**
   * Theme background image
   *
   */
  val backgroundImage: String?
}
