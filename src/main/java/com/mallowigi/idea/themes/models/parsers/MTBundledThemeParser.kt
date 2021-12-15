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
package com.mallowigi.idea.themes.models.parsers

import com.intellij.ui.ColorUtil
import com.mallowigi.idea.themes.models.MTBundledTheme
import com.mallowigi.idea.themes.models.MTThemeColor
import com.mallowigi.idea.utils.MTColorUtils.parseColor
import javax.swing.plaf.ColorUIResource

/**
 * Bridge class for Bundled themes for parsing bundled themes xml
 */
@Suppress("TooManyFunctions")
abstract class MTBundledThemeParser internal constructor(private val mtBundledTheme: MTBundledTheme) {
  private val colors: MutableCollection<MTThemeColor>
    get() = mtBundledTheme.colors

  //region ----------- Default colors -------------
  /**
   * Default excluded color
   */
  protected abstract val defaultExcludedColor: ColorUIResource

  /**
   * Default accent color
   */
  protected abstract val defaultAccentColor: ColorUIResource

  /**
   * Default notifications color
   */
  protected abstract val defaultNotificationsColor: ColorUIResource

  /**
   * Default tree selection color
   */
  protected abstract val defaultTreeSelectionColor: ColorUIResource

  /**
   * Default highlight color
   */
  protected abstract val defaultHighlightColor: ColorUIResource

  /**
   * Default second border color
   */
  protected abstract val defaultSecondBorderColor: ColorUIResource

  /**
   * Default table selected color
   */
  protected abstract val defaultTableSelectedColor: ColorUIResource

  /**
   * Default contrast color
   */
  protected abstract val defaultContrastColor: ColorUIResource

  /**
   * Default disabled color
   */
  protected abstract val defaultDisabledColor: ColorUIResource

  /**
   * Default secondary background color
   */
  protected abstract val defaultSecondaryBackgroundColor: ColorUIResource

  /**
   * Default button color
   */
  protected abstract val defaultButtonColor: ColorUIResource

  /**
   * Default selection foreground color
   */
  protected abstract val defaultSelectionForegroundColor: ColorUIResource

  /**
   * Default selection background color
   */
  protected abstract val defaultSelectionBackgroundColor: ColorUIResource

  /**
   * Default text color
   */
  protected abstract val defaultTextColor: ColorUIResource

  /**
   * Default foreground color
   */
  protected abstract val defaultForegroundColor: ColorUIResource

  /**
   * Default background color
   */
  protected abstract val defaultBackgroundColor: ColorUIResource

  //endregion

  //region ------------ Getters ---------------
  /**
   * Excluded color string
   */
  val excludedColorString: ColorUIResource
    get() = getColor(EXCLUDED_TAG, defaultExcludedColor)

  /**
   * Accent color string
   */
  val accentColorString: ColorUIResource
    get() = getColor(ACCENT_TAG, defaultAccentColor)

  /**
   * Notifications color string
   */
  val notificationsColorString: ColorUIResource
    get() = getColor(NOTIFICATIONS_TAG, defaultNotificationsColor)

  /**
   * Tree selection color string
   */
  val treeSelectionColorString: ColorUIResource
    get() = getColor(TREE_SELECTION_TAG, defaultTreeSelectionColor)

  /**
   * Highlight color string
   */
  val highlightColorString: ColorUIResource
    get() = getColor(HIGHLIGHT_TAG, defaultHighlightColor)

  /**
   * Second border color string
   */
  val secondBorderColorString: ColorUIResource
    get() = getColor(SECOND_BORDER_TAG, defaultSecondBorderColor)

  /**
   * Table selected color string
   */
  val tableSelectedColorString: ColorUIResource
    get() = getColor(TABLE_SELECTED_TAG, defaultTableSelectedColor)

  /**
   * Contrast color string
   */
  val contrastColorString: ColorUIResource
    get() = getColor(CONTRAST_TAG, defaultContrastColor)

  /**
   * Disabled color string
   */
  val disabledColorString: ColorUIResource
    get() = getColor(DISABLED_TAG, defaultDisabledColor)

  /**
   * Secondary background color string
   */
  val secondaryBackgroundColorString: ColorUIResource
    get() = getColor(SECONDARY_BACKGROUND_TAG, defaultSecondaryBackgroundColor)

  /**
   * Button color string
   */
  val buttonColorString: ColorUIResource
    get() = getColor(BUTTON_TAG, defaultButtonColor)

  /**
   * Selection foreground color string
   */
  val selectionForegroundColorString: ColorUIResource
    get() = getColor(SELECTION_FOREGROUND_TAG, defaultSelectionForegroundColor)

  /**
   * Selection background color string
   */
  val selectionBackgroundColorString: ColorUIResource
    get() = getColor(SELECTION_BACKGROUND_TAG, defaultSelectionBackgroundColor)

  /**
   * Text color string
   */
  val textColorString: ColorUIResource
    get() = getColor(TEXT_TAG, defaultTextColor)

  /**
   * Foreground color string
   */
  val foregroundColorString: ColorUIResource
    get() = getColor(FOREGROUND_TAG, defaultForegroundColor)

  /**
   * Background color string
   */
  val backgroundColorString: ColorUIResource
    get() = getColor(BACKGROUND_TAG, defaultBackgroundColor)

  //endregion

  //region --------------- Setters -----------------
  /**
   * Set excluded color
   *
   * @param excludedColor
   */
  fun setExcludedColor(excludedColor: ColorUIResource): Unit = setColor(EXCLUDED_TAG, excludedColor)

  /**
   * Set accent color
   *
   * @param accentColor
   */
  fun setAccentColor(accentColor: ColorUIResource): Unit = setColor(ACCENT_TAG, accentColor)

  /**
   * Set notifications color
   *
   * @param notificationsColor
   */
  fun setNotificationsColor(notificationsColor: ColorUIResource): Unit = setColor(NOTIFICATIONS_TAG, notificationsColor)

  /**
   * Set highlight color
   *
   * @param highlightColor
   */
  fun setHighlightColor(highlightColor: ColorUIResource): Unit = setColor(HIGHLIGHT_TAG, highlightColor)

  /**
   * Set tree selection color
   *
   * @param treeSelectionColor
   */
  fun setTreeSelectionColor(treeSelectionColor: ColorUIResource): Unit =
    setColor(TREE_SELECTION_TAG, treeSelectionColor)

  /**
   * Set second border color
   *
   * @param secondBorderColor
   */
  fun setSecondBorderColor(secondBorderColor: ColorUIResource): Unit = setColor(SECOND_BORDER_TAG, secondBorderColor)

  /**
   * Set table selected color
   *
   * @param tableSelectedColor
   */
  fun setTableSelectedColor(tableSelectedColor: ColorUIResource): Unit =
    setColor(TABLE_SELECTED_TAG, tableSelectedColor)

  /**
   * Set contrast color
   *
   * @param contrastColor
   */
  fun setContrastColor(contrastColor: ColorUIResource): Unit = setColor(CONTRAST_TAG, contrastColor)

  /**
   * Set disabled color
   *
   * @param disabledColor
   */
  fun setDisabledColor(disabledColor: ColorUIResource): Unit = setColor(DISABLED_TAG, disabledColor)

  /**
   * Set secondary background color
   *
   * @param secondaryBackgroundColor
   */
  fun setSecondaryBackgroundColor(secondaryBackgroundColor: ColorUIResource): Unit =
    setColor(SECONDARY_BACKGROUND_TAG, secondaryBackgroundColor)

  /**
   * Set button color
   *
   * @param buttonColor
   */
  fun setButtonColor(buttonColor: ColorUIResource): Unit = setColor(BUTTON_TAG, buttonColor)

  /**
   * Set selection foreground color
   *
   * @param selectionForegroundColor
   */
  fun setSelectionForegroundColor(selectionForegroundColor: ColorUIResource): Unit =
    setColor(SELECTION_FOREGROUND_TAG, selectionForegroundColor)

  /**
   * Set selection background color
   *
   * @param selectionBackgroundColor
   */
  fun setSelectionBackgroundColor(selectionBackgroundColor: ColorUIResource): Unit =
    setColor(SELECTION_BACKGROUND_TAG, selectionBackgroundColor)

  /**
   * Set text color
   *
   * @param textColor
   */
  fun setTextColor(textColor: ColorUIResource): Unit = setColor(TEXT_TAG, textColor)

  /**
   * Set foreground color
   *
   * @param foregroundColor
   */
  fun setForegroundColor(foregroundColor: ColorUIResource): Unit = setColor(FOREGROUND_TAG, foregroundColor)

  /**
   * Set background color
   *
   * @param backgroundColor
   */
  fun setBackgroundColor(backgroundColor: ColorUIResource): Unit = setColor(BACKGROUND_TAG, backgroundColor)
  //endregion

  /**
   * Return the color parsed from the XML file, or return the default color if not found
   */
  private fun getColor(tag: String, defaultColor: ColorUIResource): ColorUIResource {
    val color = findColor(tag) ?: return defaultColor
    return ColorUIResource(parseColor(color.value))
  }

  private fun setColor(tag: String, newColor: ColorUIResource) {
    var color = findColor(tag)
    if (color == null) {
      color = MTThemeColor()
      color.id = tag
      colors.add(color)
    }
    color.value = ColorUtil.toHex(newColor, true)
  }

  /**
   * Find a color in the parsed colors
   */
  private fun findColor(id: String): MTThemeColor? = colors.firstOrNull { it.id == id }

  companion object {
    private const val EXCLUDED_TAG: String = "excluded"
    private const val ACCENT_TAG: String = "accent"
    private const val NOTIFICATIONS_TAG: String = "notifications"
    private const val TREE_SELECTION_TAG: String = "treeSelection"
    private const val HIGHLIGHT_TAG: String = "highlight"
    private const val SECOND_BORDER_TAG: String = "secondBorder"
    private const val TABLE_SELECTED_TAG: String = "tableSelected"
    private const val CONTRAST_TAG: String = "contrast"
    private const val DISABLED_TAG: String = "disabled"
    private const val SECONDARY_BACKGROUND_TAG: String = "secondaryBackground"
    private const val BUTTON_TAG: String = "button"
    private const val SELECTION_FOREGROUND_TAG: String = "selectionForeground"
    private const val SELECTION_BACKGROUND_TAG: String = "selectionBackground"
    private const val TEXT_TAG: String = "text"
    private const val FOREGROUND_TAG: String = "foreground"
    private const val BACKGROUND_TAG: String = "background"
  }
}
