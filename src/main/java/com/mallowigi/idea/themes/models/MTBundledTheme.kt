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

import com.intellij.util.xmlb.annotations.Tag
import com.mallowigi.idea.themes.MTBundledThemesManager
import com.mallowigi.idea.themes.models.parsers.MTBundledThemeParser
import com.mallowigi.idea.themes.themes.MTAbstractTheme
import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamOmitField
import org.jetbrains.annotations.NonNls
import javax.swing.plaf.ColorUIResource

/**
 * Model for External and Custom Themes
 *
 */
@XStreamAlias("mtTheme")
abstract class MTBundledTheme : MTAbstractTheme() {

  @XStreamAlias("themeId")
  @NonNls
  override var themeId: String = ""

  /**
   * Theme ID
   * @Deprecated use [.themeId] instead
   */
  @XStreamAlias("id")
  @XStreamOmitField
  var id: String = ""

  @XStreamAlias("name")
  @NonNls
  override var themeName: String = ""

  @XStreamAlias("editorColorsScheme")
  @NonNls
  override var themeColorScheme: String? = null

  /**
   * Is theme dark?
   */
  @Suppress("HardCodedStringLiteral")
  @XStreamAlias("dark")
  override var isThemeDark: Boolean = true

  /**
   * Theme Colors
   */
  @Suppress("HardCodedStringLiteral")
  @Tag
  @XStreamAlias("colors")
  val colors: List<MTThemeColor> = ArrayList(16)

  /**
   * The theme parser, according to the bridge design pattern every subclass must define the parser
   */
  protected abstract val themeParser: MTBundledThemeParser

  //region --------------- Getters ------------------

  override val backgroundColorResource: ColorUIResource
    get() = themeParser.backgroundColorString

  override val foregroundColorResource: ColorUIResource
    get() = themeParser.foregroundColorString

  override val textColorResource: ColorUIResource
    get() = themeParser.textColorString

  override val selectionBackgroundColorResource: ColorUIResource
    get() = themeParser.selectionBackgroundColorString

  override val selectionForegroundColorResource: ColorUIResource
    get() = themeParser.selectionForegroundColorString

  override val buttonColorResource: ColorUIResource
    get() = themeParser.buttonColorString

  override val secondaryBackgroundColorResource: ColorUIResource
    get() = themeParser.secondaryBackgroundColorString

  override val disabledColorResource: ColorUIResource
    get() = themeParser.disabledColorString

  override val contrastColorResource: ColorUIResource
    get() = themeParser.contrastColorString

  override val tableSelectedColorResource: ColorUIResource
    get() = themeParser.tableSelectedColorString

  override val secondBorderColorResource: ColorUIResource
    get() = themeParser.secondBorderColorString

  override val highlightColorResource: ColorUIResource
    get() = themeParser.highlightColorString

  override val treeSelectionColorResource: ColorUIResource
    get() = themeParser.treeSelectionColorString

  override val notificationsColorResource: ColorUIResource
    get() = themeParser.notificationsColorString

  override val accentColorResource: ColorUIResource
    get() = themeParser.accentColorString

  override val excludedColorResource: ColorUIResource
    get() = themeParser.excludedColorString

  abstract override val order: Int

  override val backgroundImage: String?
    get() = null
  //endregion

  //region --------------- Setters ------------------
  /**
   * Excluded Color
   */
  fun setExcludedColor(excludedColor: ColorUIResource): Unit = themeParser.setExcludedColor(excludedColor)

  /**
   * Accent Color
   */
  fun setAccentColor(accentColor: ColorUIResource): Unit = themeParser.setAccentColor(accentColor)

  /**
   * Notifications Color
   */
  fun setNotificationsColor(notificationsColor: ColorUIResource): Unit =
    themeParser.setNotificationsColor(notificationsColor)

  /**
   * Highlight Color
   */
  fun setHighlightColor(highlightColor: ColorUIResource): Unit = themeParser.setHighlightColor(highlightColor)

  /**
   * Tree Selection Color
   */
  fun setTreeSelectionColor(treeSelectionColor: ColorUIResource): Unit =
    themeParser.setTreeSelectionColor(treeSelectionColor)

  /**
   * Second Border Color
   */
  fun setSecondBorderColor(secondBorderColor: ColorUIResource): Unit =
    themeParser.setSecondBorderColor(secondBorderColor)

  /**
   * Table Selected Color
   */
  fun setTableSelectedColor(tableSelectedColor: ColorUIResource): Unit =
    themeParser.setTableSelectedColor(tableSelectedColor)

  /**
   * Contrast Color
   */
  fun setContrastColor(contrastColor: ColorUIResource): Unit = themeParser.setContrastColor(contrastColor)

  /**
   * Disabled Color
   */
  fun setDisabledColor(disabledColor: ColorUIResource): Unit = themeParser.setDisabledColor(disabledColor)

  /**
   * Secondary Background Color
   */
  fun setSecondaryBackgroundColor(secondaryBackgroundColor: ColorUIResource): Unit =
    themeParser.setSecondaryBackgroundColor(secondaryBackgroundColor)

  /**
   * Button Color
   */
  fun setButtonColor(buttonColor: ColorUIResource): Unit = themeParser.setButtonColor(buttonColor)

  /**
   * Selection Foreground Color
   */
  fun setSelectionForegroundColor(selectionForegroundColor: ColorUIResource): Unit =
    themeParser.setSelectionForegroundColor(selectionForegroundColor)

  /**
   * Selection Background Color
   */
  fun setSelectionBackgroundColor(selectionBackgroundColor: ColorUIResource): Unit =
    themeParser.setSelectionBackgroundColor(selectionBackgroundColor)

  /**
   * Text Color
   */
  fun setTextColor(textColor: ColorUIResource): Unit = themeParser.setTextColor(textColor)

  /**
   * Foregroun Color
   */
  fun setForegroundColor(foregroundColor: ColorUIResource): Unit = themeParser.setForegroundColor(foregroundColor)

  /**
   * Background Color
   */
  fun setBackgroundColor(backgroundColor: ColorUIResource): Unit = themeParser.setBackgroundColor(backgroundColor)

  //endregion

  /**
   * Save theme from the form data to a file
   */
  fun saveTheme(name: String, id: String, dark: Boolean, colorScheme: String?) {
    this.themeName = name
    this.themeId = id
    this.themeColorScheme = colorScheme
    this.isThemeDark = dark

    MTBundledThemesManager.saveTheme(this)
  }
}
