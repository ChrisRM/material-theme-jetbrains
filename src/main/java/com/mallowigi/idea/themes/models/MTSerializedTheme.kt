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

import javax.swing.plaf.ColorUIResource

/**
 * Interface for themes serializable from xml
 */
interface MTSerializedTheme {
  /**
   * Get the hex code for the background color
   */
  val backgroundColorResource: ColorUIResource

  /**
   * Get the hex code for the foreground color
   */
  val foregroundColorResource: ColorUIResource

  /**
   * Get the hex code for the text color
   */
  val textColorResource: ColorUIResource

  /**
   * Get the hex code for the selection background color
   */
  val selectionBackgroundColorResource: ColorUIResource

  /**
   * Get the hex code for the selection foreground color
   */
  val selectionForegroundColorResource: ColorUIResource

  /**
   * Allow themes to specify a second selection foreground
   * Default primary selection foreground
   */
  val secondSelectionForegroundColorResource: ColorUIResource
    get() = selectionForegroundColorResource

  /**
   * Get the hex code for the button color
   */
  val buttonColorResource: ColorUIResource

  /**
   * Get the hex code for the secondary background color
   */
  val secondaryBackgroundColorResource: ColorUIResource

  /**
   * Get the hex code for the disabled color
   */
  val disabledColorResource: ColorUIResource

  /**
   * Get the hex code for the contrast color
   */
  val contrastColorResource: ColorUIResource

  /**
   * Get the hex code for the table selected color
   */
  val tableSelectedColorResource: ColorUIResource

  /**
   * Get the hex code for the second border color
   */
  val secondBorderColorResource: ColorUIResource

  /**
   * Get the hex code for the highlight color
   */
  val highlightColorResource: ColorUIResource

  /**
   * Get the hex code for the tree selection color
   */
  val treeSelectionColorResource: ColorUIResource

  /**
   * Get the hex code for the notifications color
   */
  val notificationsColorResource: ColorUIResource

  /**
   * The accent color string
   */
  val accentColorResource: ColorUIResource

  /**
   * The excluded files color string
   */
  val excludedColorResource: ColorUIResource
}
