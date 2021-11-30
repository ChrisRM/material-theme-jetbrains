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

package com.mallowigi.idea.utils

import com.intellij.ide.ui.laf.darcula.DarculaLaf
import com.intellij.ide.ui.laf.darcula.DarculaUIUtil
import com.intellij.ui.ColorUtil
import com.intellij.ui.Gray
import com.intellij.ui.JBColor
import com.intellij.util.ui.JBInsets
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import com.intellij.util.ui.UIUtilities
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.utils.MTColorUtils.contrastifyBackground
import java.awt.Color
import java.awt.Component
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.Shape
import java.awt.geom.Path2D
import javax.swing.JLabel
import javax.swing.UIManager
import javax.swing.border.Border
import javax.swing.plaf.ColorUIResource

@Suppress("KDocMissingDocumentation", "MagicNumber", "unused", "HardCodedStringLiteral")
object MTUI {
  object Tree {
    private const val TREE_SELECTION_BACKGROUND: String = "Tree.selectionBackground"
    private const val TREE_SELECTION_INACTIVE_BACKGROUND: String = "Tree.selectionInactiveBackground"

    @JvmStatic
    val selectionBackground: Color
      get() {
        val color: Color = JBColor.namedColor(TREE_SELECTION_BACKGROUND, JBColor(0x27384C, 0x0D293E))
        return ColorUtil.withAlpha(color, 0.25)
      }

    @JvmStatic
    val selectionInactiveBackground: Color
      get() {
        val color: Color = JBColor.namedColor(TREE_SELECTION_INACTIVE_BACKGROUND, JBColor(0x27384C, 0x0D293E))
        return ColorUtil.withAlpha(color, 0.25)
      }
  }

  object ActionButton {
    private const val ACTION_BUTTON_HOVER_BACKGROUND: String = "ActionButton.hoverBackground"
    private const val ACTION_BUTTON_HOVER_BORDER_COLOR: String = "ActionButton.hoverBorderColor"

    @JvmStatic
    val hoverBackground: Color
      get() = JBColor.namedColor(ACTION_BUTTON_HOVER_BACKGROUND, JBColor(0xdfdfdf, 0x4c5052))

    @JvmStatic
    val hoverBorderColor: Color
      get() = JBColor.namedColor(ACTION_BUTTON_HOVER_BORDER_COLOR, JBColor(0xdfdfdf, 0x4c5052))
  }

  object Button {
    private const val BUTTON_DISABLED_TEXT: String = "Button.disabledText"
    private const val BUTTON_BACKGROUND: String = "Button.background"
    private const val BUTTON_FOREGROUND: String = "Button.foreground"
    private const val BUTTON_PRIMARY_BACKGROUND: String = "Button.default.startBackground"
    private const val BUTTON_PRIMARY_FOREGROUND: String = "Button.default.foreground"
    private const val BUTTON_SELECTED_BACKGROUND: String = "Button.focus"
    private const val BUTTON_SELECTED_FOREGROUND: String = "Button.default.foreground"
    private const val BUTTON_DISABLED_TEXT_SHADOW: String = "Button.default.shadowColor"
    private const val NO_BORDER: String = "MTButton.noBorder"

    @JvmStatic
    val backgroundColor: Color
      get() = JBColor.namedColor(BUTTON_BACKGROUND, JBColor(0xf2f2f2, 0x3c3f41))

    @JvmStatic
    val primaryBackgroundColor: Color
      get() = JBColor.namedColor(BUTTON_PRIMARY_BACKGROUND, JBColor(0x4A86C7, 0x365880))

    @JvmStatic
    val primaryForegroundColor: Color
      get() = JBColor.namedColor(BUTTON_PRIMARY_FOREGROUND, JBColor(0xf0f0f0, 0xbbbbbb))

    @JvmStatic
    val foregroundColor: Color
      get() = JBColor.namedColor(BUTTON_FOREGROUND, JBColor(0x000000, 0xbbbbbb))

    @JvmStatic
    val selectedBackgroundColor: Color
      get() = JBColor.namedColor(BUTTON_SELECTED_BACKGROUND, JBColor(0x97c3f3, 0x43688c))

    @JvmStatic
    val selectedForegroundColor: Color
      get() = JBColor.namedColor(BUTTON_SELECTED_FOREGROUND, JBColor(0xf0f0f0, 0xbbbbbb))

    @JvmStatic
    val disabledShadowColor: Color
      get() = JBColor.namedColor(BUTTON_DISABLED_TEXT_SHADOW, JBColor(-0x59595980, 0x36363680))

    @JvmStatic
    val disabledColor: Color
      get() = JBColor.namedColor(BUTTON_DISABLED_TEXT, JBColor(0x999999, 0x777777))

    @JvmStatic
    val noBorder: String
      get() = NO_BORDER
  }

  object TextField {
    private const val TEXT_FIELD_SEPARATOR_COLOR_DISABLED: String = "Component.disabledBorderColor"
    private const val TEXT_FIELD_SEPARATOR_COLOR: String = "Component.borderColor"
    private const val TEXT_FIELD_SELECTED_SEPARATOR_COLOR: String = "Component.focusedBorderColor"

    @JvmStatic
    val selectedBorderColor: Color
      get() = JBColor.namedColor(TEXT_FIELD_SELECTED_SEPARATOR_COLOR, JBColor(0x87AFDA, 0x466D94))

    @JvmStatic
    val separatorColor: Color
      get() = JBColor.namedColor(TEXT_FIELD_SEPARATOR_COLOR, JBColor(0xc4c4c4, 0x646464))

    @JvmStatic
    val disabledSeparatorColor: Color
      get() = JBColor.namedColor(TEXT_FIELD_SEPARATOR_COLOR_DISABLED, JBColor(0xc4c4c4, 0x646464))

    @JvmStatic
    fun getBorderColor(enabled: Boolean): Color = if (enabled) separatorColor else disabledSeparatorColor

  }

  object List {
    private const val LIST_SELECTION_BACKGROUND_PAINTER: String = "List.sourceListSelectionBackgroundPainter"
    private const val LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER: String =
      "List.sourceListFocusedSelectionBackgroundPainter"
    private const val COMPLETION_SELECTION: String = "Autocomplete.selectionUnfocus"

    @JvmStatic
    val listSelectionPainterKey: String
      get() = LIST_SELECTION_BACKGROUND_PAINTER

    @JvmStatic
    val listFocusedSelectionPainterKey: String
      get() = LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER

    @JvmStatic
    val listSelectionPainter: Border
      get() = UIManager.getBorder(LIST_SELECTION_BACKGROUND_PAINTER)

    @JvmStatic
    val listFocusedSelectionPainter: Border
      get() = UIManager.getBorder(LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER)

    @JvmStatic
    val selectedColor: Color
      get() = JBColor.namedColor(COMPLETION_SELECTION, JBColor(0x87AFDA, 0x466D94))
  }

  object Table {
    private const val TABLE_HIGHLIGHT_OUTER: String = "Table.highlightOuter"
    private const val TABLE_HEADER_BORDER_COLOR: String = "TableHeader.borderColor"

    @JvmStatic
    val highlightOuterColor: Color
      get() = JBColor.namedColor(TABLE_HIGHLIGHT_OUTER, JBColor(0x79c0ff, 0x79c0ff))

    @JvmStatic
    val borderColor: Color
      get() = JBColor.namedColor(TABLE_HEADER_BORDER_COLOR, JBColor(0xdddddd, 0x2c2c2c))

    @JvmStatic
    val cellBorder: Border
      get() {
        val compactTables = MTConfig.getInstance().isCompactTables
        return if (compactTables) JBUI.Borders.empty(3) else JBUI.Borders.empty(10, 5)
      }
  }

  object StatusBar {
    private const val IDE_STATUS_BAR_BORDER: String = "IdeStatusBar.border"

    @JvmStatic
    val statusBarBorder: Border?
      get() = UIManager.getBorder(IDE_STATUS_BAR_BORDER)

    @JvmStatic
    val statusBarBorderKey: String
      get() = IDE_STATUS_BAR_BORDER
  }

  object TabbedPane {
    private const val TABBED_PANE_SELECTED_FOREGROUND: String = "TabbedPane.selectedForeground"
    private const val TABBED_PANE_FOREGROUND: String = "TabbedPane.foreground"
    private const val TABBED_PANE_SELECTED: String = "TabbedPane.selectedColor"
    private const val HOVERED_TAB_BACKGROUND: String = "DefaultTabs.hoverBackground"
    private const val TAB_BACKGROUND: String = "DefaultTabs.background"
    private const val INACTIVE_TAB_BACKGROUND: String = "EditorTabs.inactiveColoredFileBackground"
    private const val INACTIVE_TAB_CONTRAST_BACKGROUND: String = "DefaultTabs.inactiveMaskColor"
    private const val TABBED_PANE_SHADOW: String = "TabbedPane.shadow"

    @JvmStatic
    val foreground: Color
      get() = JBColor.namedColor(TABBED_PANE_FOREGROUND, JBColor(0x000000, 0xbbbbbb))

    @JvmStatic
    val selectedForeground: Color
      get() = JBColor.namedColor(TABBED_PANE_SELECTED_FOREGROUND, JBColor(0xffffff, 0xffffff))

    @JvmStatic
    val highlightColor: Color
      get() = JBColor.namedColor(TABBED_PANE_SELECTED, JBColor(0xdae4ed, 0x3d4b5c))

    @JvmStatic
    val hoveredBackground: Color
      get() = JBColor.namedColor(HOVERED_TAB_BACKGROUND, JBColor(0xdae4ed, 0x3d4b5c))

    @JvmStatic
    val background: Color
      get() = JBColor.namedColor(TAB_BACKGROUND, JBColor(0xdae4ed, 0x3d4b5c))

    @JvmStatic
    val shadowColor: Color
      get() = JBColor.namedColor(TABBED_PANE_SHADOW, JBColor(0xdae4ed, 0x3d4b5c))

    @JvmStatic
    fun getInactiveBackground(isContrast: Boolean): Color {
      val inactiveTabBG = JBColor.namedColor(INACTIVE_TAB_BACKGROUND, JBColor(0xdae4ed, 0x3d4b5c))
      val inactiveContrastBG = JBColor.namedColor(INACTIVE_TAB_CONTRAST_BACKGROUND, JBColor(0xdae4ed, 0x3d4b5c))
      return if (isContrast) inactiveContrastBG else inactiveTabBG
    }

  }

  object Slider {
    private const val SLIDER_THUMB: String = "Slider.thumb"
    private const val SLIDER_TRACK: String = "Slider.track"
    private const val SLIDER_TRACK_DISABLED: String = "Slider.trackDisabled"

    @JvmStatic
    val thumbColor: Color
      get() = JBColor.namedColor(SLIDER_THUMB, JBColor(0xc4c4c4, 0x646464))

    @JvmStatic
    val trackColor: Color
      get() = JBColor.namedColor(SLIDER_TRACK, JBColor(0xc4c4c4, 0x646464))

    @JvmStatic
    val trackDisabledColor: Color
      get() = JBColor.namedColor(SLIDER_TRACK_DISABLED, JBColor(0xcfcfcf, 0x646464))
  }

  object Spinner {
    private const val COMBO_BOX_EDITABLE_ARROW_BACKGROUND: String =
      "ComboBox.darcula.editable.arrowButtonBackground"
    private const val COMBO_BOX_ARROW_BACKGROUND: String = "ComboBox.darcula.arrowButtonBackground"
    private const val COMBO_BOX_DISABLED_ARROW_BACKGROUND: String =
      "ComboBox.darcula.disabledArrowButtonBackground"
    private const val COMBO_BOX_ARROW_FOREGROUND: String = "ComboBox.darcula.arrowButtonForeground"
    private const val COMBO_BOX_HOVERED_ARROW_FOREGROUND: String = "ComboBox.darcula.hoveredArrowButtonForeground"
    private const val COMBO_BOX_ARROW_DISABLED_FOREGROUND: String =
      "ComboBox.darcula.arrowButtonDisabledForeground"

    @JvmStatic
    fun getArrowButtonBackgroundColor(enabled: Boolean, editable: Boolean): Color = when {
      enabled -> when {
        editable -> JBColor.namedColor(COMBO_BOX_EDITABLE_ARROW_BACKGROUND, Gray.xFC)
        else     -> JBColor.namedColor(COMBO_BOX_ARROW_BACKGROUND, Gray.xFC)
      }
      else    -> JBColor.namedColor(COMBO_BOX_DISABLED_ARROW_BACKGROUND, Gray.xFC)
    }

    @JvmStatic
    fun getArrowButtonForegroundColor(enabled: Boolean, hovered: Boolean): Color = when {
      enabled -> when {
        hovered -> JBColor.namedColor(COMBO_BOX_HOVERED_ARROW_FOREGROUND, Gray.x66)
        else    -> JBColor.namedColor(COMBO_BOX_ARROW_FOREGROUND, Gray.x66)
      }
      else    -> JBColor.namedColor(COMBO_BOX_ARROW_DISABLED_FOREGROUND, Gray.xAB)
    }
  }

  object MTColor {
    @JvmField
    val PURPLE: Color = ColorUIResource(0xC792EA)

    @JvmField
    val GREEN: Color = ColorUIResource(0xC3E88D)

    @JvmField
    val BLUE: Color = ColorUIResource(0x82AAFF)

    @JvmField
    val CYAN: Color = ColorUIResource(0x89DDF7)

    @JvmField
    val YELLOW: Color = ColorUIResource(0xFFCB6B)

    @JvmField
    val RED: Color = ColorUIResource(0xFF5370)

    @JvmField
    val ORANGE: Color = ColorUIResource(0xF78C6C)

    @JvmField
    val BROWN: Color = ColorUIResource(0xAB7967)

    @JvmField
    val PINK: Color = ColorUIResource(0xBB80B3)

    @JvmField
    val DARK_PURPLE: Color = ColorUIResource(0x1E153D)

    @JvmField
    val DARK_GREEN: Color = ColorUIResource(0x003300)

    @JvmField
    val DARK_BLUE: Color = ColorUIResource(0x002171)

    @JvmField
    val DARK_CYAN: Color = ColorUIResource(0x006064)

    @JvmField
    val DARK_YELLOW: Color = ColorUIResource(0x7a5900)

    @JvmField
    val DARK_RED: Color = ColorUIResource(0x7F0000)

    @JvmField
    val DARK_ORANGE: Color = ColorUIResource(0x894500)

    @JvmField
    val DARK_BROWN: Color = ColorUIResource(0x2F2018)

    @JvmField
    val DARK_PINK: Color = ColorUIResource(0x560027)
  }

  object Separator {
    private const val SEPARATOR_SEPARATOR_COLOR: String = "Separator.separatorColor"

    @JvmStatic
    val separatorColor: Color
      get() = JBColor.namedColor(SEPARATOR_SEPARATOR_COLOR, JBColor(0xcdcdcd, 0x515151))
  }

  object Radio {
    private const val RADIO_BUTTON_SELECTION_ENABLED_COLOR: String = "RadioButton.selectionEnabledShadowColor"
    private const val RADIO_BUTTON_SELECTION_DISABLED_COLOR: String = "RadioButton.selectionDisabledShadowColor"
    private const val RADIO_BUTTON_BORDER_COLOR: String = "RadioButton.darcula.borderColor1"
    private const val RADIO_BUTTON_FOCUS_COLOR: String = "RadioButton.focusColor"

    @JvmStatic
    val selectionEnabledColor: Color
      get() = JBColor.namedColor(RADIO_BUTTON_SELECTION_ENABLED_COLOR, JBColor(0x00000040, 0x1e1e1e))

    @JvmStatic
    val selectionDisabledColor: Color
      get() = JBColor.namedColor(RADIO_BUTTON_SELECTION_DISABLED_COLOR, JBColor(0x00000020, 0x3c3c3c))

    @JvmStatic
    val borderColor: Color
      get() = JBColor.namedColor(RADIO_BUTTON_BORDER_COLOR, JBColor(0xcdcdcd, 0x515151))

    @JvmStatic
    val focusColor: Color
      get() {
        val color: Color = JBColor.namedColor(RADIO_BUTTON_FOCUS_COLOR, JBColor(0xcfcfcf, 0xaaaaaa))
        return ColorUtil.withAlpha(color, 0.5)
      }

    @JvmStatic
    fun getSelectedColor(enabled: Boolean): Color = if (enabled) selectionEnabledColor else selectionDisabledColor

  }

  object ProgressBar {
    private const val PROGRESS_BAR_TRACK_COLOR = "ProgressBar.trackColor"
    private const val PROGRESS_BAR_PROGRESS_COLOR = "ProgressBar.progressColor"
    private const val PROGRESS_BAR_INDETERMINATE_START_COLOR = "ProgressBar.indeterminateStartColor"
    private const val PROGRESS_BAR_INDETERMINATE_END_COLOR = "ProgressBar.indeterminateEndColor"

    @JvmStatic
    val trackColor: JBColor
      get() = JBColor.namedColor(PROGRESS_BAR_TRACK_COLOR, JBColor(Gray.xC4, Gray.x55))

    @JvmStatic
    val progressColor: JBColor
      get() = JBColor.namedColor(PROGRESS_BAR_PROGRESS_COLOR, JBColor(Gray.x80, Gray.xA0))

    @JvmStatic
    val indeterminateStartColor: Color
      get() = JBColor.namedColor(PROGRESS_BAR_INDETERMINATE_START_COLOR, JBColor(Gray.xC4, Gray.x69))
        .brighter().brighter()

    @JvmStatic
    val indeterminateEndColor: JBColor
      get() = JBColor.namedColor(PROGRESS_BAR_INDETERMINATE_END_COLOR, JBColor(Gray.x80, Gray.x83))
  }

  object Switch {
    private const val OFF_THUMB_COLOR: String = "ToggleButton.offForeground"
    private const val ON_THUMB_COLOR: String = "ToggleButton.onForeground"
    private const val OFF_BACKGROUND_COLOR: String = "ToggleButton.offBackground"
    private const val ON_BACKGROUND_COLOR: String = "ToggleButton.onBackground"

    @JvmStatic
    val offThumbColor: Color
      get() = JBColor.namedColor(OFF_THUMB_COLOR, Gray.x77).brighter().brighter()

    @JvmStatic
    val onThumbColor: Color
      get() = JBColor.namedColor(ON_THUMB_COLOR, Gray.xFF)

    @JvmStatic
    val offSwitchColor: Color
      get() = JBColor.namedColor(OFF_BACKGROUND_COLOR, JBColor(0xf2f2f2, 0x3c3f41))

    @JvmStatic
    val onSwitchColor: Color
      get() = JBColor.namedColor(ON_BACKGROUND_COLOR, JBColor(0x4a9249, 0x4d694c))
        .darker().darker()
  }

  object NavBar {
    private const val NAVBAR_ARROW_COLOR: String = "NavBar.arrowColor"
    private const val NAVBAR_HIGHLIGHT_COLOR: String = "NavBar.selectedColor"

    @JvmStatic
    val arrowColor: Color
      get() = JBColor.namedColor(NAVBAR_ARROW_COLOR, Gray._100)

    @JvmStatic
    val highlightColor: Color
      get() = ColorUtil.withAlpha(
        JBColor.namedColor(NAVBAR_HIGHLIGHT_COLOR, UIUtil.getListSelectionBackground(true)),
        0.5
      )

    @JvmStatic
    val decorationOffset: Int
      get() = JBUI.scale(14)

    @JvmStatic
    val decorationHOffset: Int
      get() = JBUI.scale(9)

    @JvmStatic
    val firstElementLeftOffset: Int
      get() = JBUI.scale(6)
  }

  object ComboBox {
    private const val COMBO_BOX_ARROW_BUTTON_NON_EDITABLE_BACKGROUND = "ComboBox.ArrowButton.nonEditableBackground"
    private const val COMBO_BOX_NON_EDITABLE_BACKGROUND = "ComboBox.nonEditableBackground"
    private const val COMBO_BOX_DISABLED_FOREGROUND = "ComboBox.disabledForeground"
    private const val TEXT_FIELD_BACKGROUND = "TextField.background"

    @JvmStatic
    val nonEditableBackground: Color
      get() = JBColor.namedColor(COMBO_BOX_NON_EDITABLE_BACKGROUND, JBColor(0xffffff, 0x3c3f41))

    @JvmStatic
    val disabledForeground: Color
      get() = JBColor.namedColor(COMBO_BOX_DISABLED_FOREGROUND, JBColor(0xb1b1b1, 0xb1b1b1))

    @JvmStatic
    val fallbackBackground: Color
      get() = Button.backgroundColor

    @JvmStatic
    val disabledBackground: Color
      get() = Button.backgroundColor

    @JvmStatic
    val enabledBackground: Color
      get() = JBColor.namedColor(TEXT_FIELD_BACKGROUND, JBColor(0xffffff, 0x45494A))

    @JvmStatic
    fun getArrowShape(button: Component): Shape {
      val r = Rectangle(button.size)
      JBInsets.removeFrom(r, JBUI.insets(1, 0, 1, 1))
      val tW = JBUI.scale(8)
      val tH = JBUI.scale(6)
      val xU = (r.width - tW) / 2 - JBUI.scale(1)
      val yU = (r.height - tH) / 2 + JBUI.scale(1)
      val path: Path2D = Path2D.Float()
      path.moveTo(xU.toDouble(), yU.toDouble())
      path.lineTo((xU + tW).toDouble(), yU.toDouble())
      path.lineTo((xU + tW / 2.0f).toDouble(), (yU + tH).toDouble())
      path.lineTo(xU.toDouble(), yU.toDouble())
      path.closePath()
      return path
    }

    @JvmStatic
    fun getArrowButtonBackgroundColor(enabled: Boolean): Color {
      val color: Color =
        JBColor.namedColor(COMBO_BOX_ARROW_BUTTON_NON_EDITABLE_BACKGROUND, JBColor(0xffffff, 0x3c3f41))
      return if (enabled) color else UIUtil.getPanelBackground()
    }
  }

  object CheckBox {
    @JvmStatic
    val inactiveFillColor: Color
      get() = getColor(
        shortPropertyName = "Background",
        defaultValue = JBColor(Gray._240.withAlpha(180), Gray._110.withAlpha(180)),
        selected = false,
        disabled = true
      )

    private fun getColor(shortPropertyName: String, defaultValue: Color): Color =
      JBColor.namedColor("Checkbox.$shortPropertyName", defaultValue)

    private fun getColor(shortPropertyName: String, defaultValue: Color, selected: Boolean, disabled: Boolean): Color =
      when {
        selected -> getColor("$shortPropertyName.Selected", defaultValue)
        disabled -> getColor("$shortPropertyName.Disabled", defaultValue)
        else     -> getColor("$shortPropertyName.Default", defaultValue)
      }

    @JvmStatic
    fun getBorderColor(enabled: Boolean, selected: Boolean): Color = when {
      enabled -> getColor("Border", Gray._120.withAlpha(0x5a), selected, false)
      else    -> getColor("Border", Gray._120.withAlpha(90), selected, true)
    }

    @JvmStatic
    fun getBackgroundColor(selected: Boolean): Color =
      getColor("Background", JBColor(Gray._240, Gray._110), selected, false)

    @JvmStatic
    fun getCheckSignColor(enabled: Boolean): Color = when {
      enabled -> JBColor.namedColor("CheckBox.checkSignColor", Gray._240)
      else    -> JBColor.namedColor("CheckBox.checkSignColorDisabled", Gray._120)
    }

    @JvmStatic
    fun getShadowColor(enabled: Boolean): Color = when {
      enabled -> JBColor.namedColor("CheckBox.shadowColor", JBColor(Gray._240, Gray._170))
      else    -> JBColor.namedColor("CheckBox.shadowColorDisabled", Gray._120)
    }

    @JvmStatic
    fun getFocusedBackgroundColor(armed: Boolean, selected: Boolean): Color = when {
      armed -> getColor("Focus.Wide", JBColor(Gray._240, Gray._170), selected, false)
      else  -> getColor("Focus.Thin", JBColor(Gray._240, Gray._170), selected, false)
    }
  }

  object Label {
    private const val LABEL_DISABLED_FOREGROUND = "Label.disabledForeground"
    private const val LABEL_INFO_FOREGROUND: String = "Label.infoForeground"
    private const val LABEL_SELECTED_FOREGROUND = "Label.selectedForeground"
    private const val LABEL_FOREGROUND = "Label.foreground"

    @JvmStatic
    val selectedForeground: Color
      get() = JBColor.namedColor(LABEL_SELECTED_FOREGROUND, JBColor(0x11111, 0xFFFFFF))

    @JvmStatic
    val labelForeground: Color
      get() = JBColor.namedColor(LABEL_FOREGROUND, UIUtil.getLabelForeground())

    @JvmStatic
    val labelInfoForeground: Color
      get() = JBColor.namedColor(LABEL_INFO_FOREGROUND, JBColor(0x777777, 0x787878))

    @JvmStatic
    val labelDisabledForeground: Color
      get() = JBColor.namedColor(LABEL_DISABLED_FOREGROUND, JBColor(0x777777, 0x787878))

    @JvmStatic
    fun getLabelInfoForeground(label: JLabel): Color {
      var foreground = label.foreground
      if (foreground === Gray.x78 || foreground === Gray.x80) {
        foreground = JBColor.namedColor(LABEL_INFO_FOREGROUND, JBColor(0x777777, 0x787878))
      }
      return foreground
    }

    @JvmStatic
    fun paintText(label: JLabel, g: Graphics?, s: String?, textX: Int, textY: Int) {
      val mnemIndex = if (DarculaLaf.isAltPressed()) label.displayedMnemonicIndex else -1
      UIUtilities.drawStringUnderlineCharAt(label, g, s, mnemIndex, textX, textY)
    }

  }

  object Panel {
    private const val PANEL_BACKGROUND: String = "Panel.background"
    private const val PANEL_FOREGROUND: String = "Panel.foreground"
    private const val CONTRAST_BACKGROUND: String = "EditorPane.background"
    private const val SELECTION_BACKGROUND: String = "List.selectionBackground"
    private const val SELECTION_FOREGROUND: String = "List.selectionForeground"
    private const val SECONDARY_BACKGROUND: String = "List.background"
    private const val HIGHLIGHT_BACKGROUND: String = "Component.focusedBorderColor"
    private const val EXCLUDED_BACKGROUND: String = "FileColor.excluded"
    private const val PRIMARY_FOREGROUND: String = "Label.infoForeground"
    private const val LINK_FOREGROUND: String = "link.foreground"
    private const val COUNTER_BACKGROUND: String = "Counter.background"
    private const val TOOL_WINDOW_TAB_VERTICAL_PADDING: String = "ToolWindow.tab.verticalPadding"

    @JvmStatic
    val font: Font
      get() = UIManager.getFont("Panel.Font") ?: UIUtil.getLabelFont()

    @JvmStatic
    val background: Color
      get() = JBColor.namedColor(PANEL_BACKGROUND, UIUtil.getPanelBackground())

    @JvmStatic
    val foreground: Color
      get() = JBColor.namedColor(PANEL_FOREGROUND, UIUtil.getLabelForeground())

    @JvmStatic
    val contrastBackground: Color
      get() {
        val color: Color = JBColor.namedColor(PANEL_BACKGROUND, UIUtil.getEditorPaneBackground())
        val isDark = ColorUtil.isDark(color)
        return contrastifyBackground(isDark, ColorUIResource(color), false)
      }

    @JvmStatic
    val editorColor: Color
      get() = JBColor.namedColor("EditorPane.background", contrastBackground)

    @JvmStatic
    val secondaryBackground: Color
      get() = JBColor.namedColor(SECONDARY_BACKGROUND, UIUtil.getListBackground())

    @JvmStatic
    val highlightBackground: Color
      get() = JBColor.namedColor(HIGHLIGHT_BACKGROUND, DarculaUIUtil.getOutlineColor(true, true))

    @JvmStatic
    val transparentSelectionBackground: Color
      get() = ColorUtil.withAlpha(
        JBColor.namedColor(SELECTION_BACKGROUND, UIUtil.getListSelectionBackground(true)),
        0.6
      )

    @JvmStatic
    val transparentBackground: Color
      get() = ColorUtil.withAlpha(JBColor.namedColor(PANEL_BACKGROUND, UIUtil.getPanelBackground()), 0.3)

    @JvmStatic
    val excludedBackground: Color
      get() {
        val color: Color = JBColor.namedColor(PANEL_BACKGROUND, UIUtil.getPanelBackground())
        val isLight = !ColorUtil.isDark(color)
        return contrastifyBackground(isLight, ColorUIResource(color), false)
      }

    @JvmStatic
    val primaryForeground: Color
      get() = JBColor.namedColor(PRIMARY_FOREGROUND, UIUtil.getLabelForeground())

    @JvmStatic
    val selectionBackground: Color
      get() = JBColor.namedColor(SELECTION_BACKGROUND, UIUtil.getListSelectionBackground(true))

    @JvmStatic
    val selectionForeground: Color
      get() = JBColor.namedColor(SELECTION_FOREGROUND, UIUtil.getListSelectionForeground(true))

    @JvmStatic
    val linkForeground: Color
      get() = JBColor.namedColor(LINK_FOREGROUND, JBColor.blue)

    @JvmStatic
    val toolWindowPadding: Int
      get() = UIManager.getInt(TOOL_WINDOW_TAB_VERTICAL_PADDING)

    @JvmStatic
    val toolWindowPaddingKey: String
      get() = TOOL_WINDOW_TAB_VERTICAL_PADDING

    @JvmStatic
    val accentColor: Color
      get() = JBColor.namedColor(COUNTER_BACKGROUND, UIUtil.getOptionPaneBackground())
  }

  object Notification {
    private const val NOTIFICATION_BACKGROUND: String = "Notification.background"

    @JvmStatic
    val backgroundColor: Color
      get() = JBColor.namedColor(NOTIFICATION_BACKGROUND, JBColor(Gray._242, Color(0x4e5052)))
  }

  object Tabs {
    private const val TAB_UNDERLINE: String = "TAB_UNDERLINE"
    private const val TAB_UNDERLINE_INACTIVE: String = "TAB_UNDERLINE_INACTIVE"
    private const val UNDERLINE_COLOR: String = "EditorTabs.underlineColor"
    private const val EDITOR_TABS_INACTIVE: String = "EditorTabs.inactiveColoredFileBackground"

    @JvmStatic
    val tabUnderlineKey: String
      get() = TAB_UNDERLINE

    @JvmStatic
    val tabUnderlineInactiveKey: String
      get() = TAB_UNDERLINE_INACTIVE

    @JvmStatic
    val underlineColor: Color
      get() = JBColor.namedColor(UNDERLINE_COLOR, Color(0x439EB8))

    @JvmStatic
    val selectionInactiveBackground: Color
      get() = JBUI.CurrentTheme.EditorTabs.inactiveColoredFileBackground()
  }
}
