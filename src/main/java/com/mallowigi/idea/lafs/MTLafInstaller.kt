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
package com.mallowigi.idea.lafs

import com.intellij.ide.ui.laf.darcula.ui.DarculaMenuBarBorder
import com.intellij.ide.ui.laf.darcula.ui.DarculaMenuItemBorder
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.registry.Registry
import com.intellij.ui.components.JBScrollBar
import com.intellij.ui.tree.ui.Control
import com.intellij.util.ui.JBUI
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.ui.MTButtonBorder
import com.mallowigi.idea.ui.MTButtonUI
import com.mallowigi.idea.ui.MTCheckBoxBorder
import com.mallowigi.idea.ui.MTCheckBoxMenuItemUI
import com.mallowigi.idea.ui.MTCheckBoxUI
import com.mallowigi.idea.ui.MTComboBoxUI
import com.mallowigi.idea.ui.MTDarculaButtonUI
import com.mallowigi.idea.ui.MTEditorTextFieldBorder
import com.mallowigi.idea.ui.MTLabelUI
import com.mallowigi.idea.ui.MTMenuItemBorder
import com.mallowigi.idea.ui.MTOnOffButtonUI
import com.mallowigi.idea.ui.MTOptionButtonUI
import com.mallowigi.idea.ui.MTPasswordFieldUI
import com.mallowigi.idea.ui.MTPopupMenuBorder
import com.mallowigi.idea.ui.MTPopupMenuUI
import com.mallowigi.idea.ui.MTProgressBarBorder
import com.mallowigi.idea.ui.MTProgressBarUI
import com.mallowigi.idea.ui.MTRadioButtonMenuItemUI
import com.mallowigi.idea.ui.MTRadioButtonUI
import com.mallowigi.idea.ui.MTRootPaneUI
import com.mallowigi.idea.ui.MTRowPainter
import com.mallowigi.idea.ui.MTSeparatorUI
import com.mallowigi.idea.ui.MTSliderUI
import com.mallowigi.idea.ui.MTSpinnerBorder
import com.mallowigi.idea.ui.MTSpinnerUI
import com.mallowigi.idea.ui.MTStatusBarBorder
import com.mallowigi.idea.ui.MTStatusBarUI
import com.mallowigi.idea.ui.MTTabbedPaneUI
import com.mallowigi.idea.ui.MTTableCellNoFocusBorder
import com.mallowigi.idea.ui.MTTableHeaderBorder
import com.mallowigi.idea.ui.MTTableHeaderUI
import com.mallowigi.idea.ui.MTTableSelectedCellHighlightBorder
import com.mallowigi.idea.ui.MTTextAreaUI
import com.mallowigi.idea.ui.MTTextBorder
import com.mallowigi.idea.ui.MTTextFieldUI
import com.mallowigi.idea.ui.indicators.MTSelectedTreePainter
import com.mallowigi.idea.utils.MTUI.List.listFocusedSelectionPainterKey
import com.mallowigi.idea.utils.MTUI.List.listSelectionPainterKey
import com.mallowigi.idea.utils.MTUI.StatusBar.statusBarBorderKey
import java.awt.Color
import java.awt.Component
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.Icon
import javax.swing.UIDefaults
import javax.swing.plaf.ColorUIResource

/**
 * Service to install Material Theme properties in the UIManager
 */
@Suppress("MagicNumber")
object MTLafInstaller {
  /**
   * Install Material Theme UI Components.
   *
   *
   * Some components will only be installed if the Material Components option is set, while others depend on other
   * options, such as Compact Statusbars, Arrow Styles, Title bar and so on.
   *
   * @param defaults the UIManager defaults to install properties into
   */
  fun installMTDefaults(defaults: UIDefaults) {
    replaceStatusBar(defaults)
    replaceTree()
    replaceSelectedIndicator(defaults)
    replaceDropdowns(defaults)
    replaceTableHeaders(defaults)
    replaceRootPane(defaults)
    replaceMenus(defaults)
    replaceTabbedPanes(defaults)
    replaceLabels(defaults)
    replaceDefaultButtons(defaults)
    replaceButtons(defaults)
    replaceTextFields(defaults)
    replaceProgressBar(defaults)
    replaceTables(defaults)
    replaceSpinners(defaults)
    replaceCheckboxes(defaults)
    replaceRadioButtons(defaults)
    replaceSliders(defaults)
    replaceTextAreas(defaults)
    modifyRegistry()
  }

  /**
   * Install non themeable defaults, such as borders, insets and so on
   *
   * @param defaults of type UIDefaults
   */
  @Suppress("HardCodedStringLiteral")
  fun installDefaults(defaults: UIDefaults) {
    defaults["ActionsList.icon.gap"] = 8
    defaults["ActionsList.mnemonic.icon.gap"] = 6
    defaults["ActionsList.mnemonics.insets"] = "0,8,0,8"
    defaults["ActionsList.mnemonicsBorderInsets"] = "0,8,1,6"
    defaults["Border.width"] = 2 // deprecated
    defaults["Button.ToolWindow.arc"] = 0
    defaults["Button.arc"] = 6
    defaults["Caret.width"] = 2
    defaults["CellEditor.border.width"] = 2
    defaults["CheckBox.border.width"] = 3
    defaults["CheckBoxMenuItem.borderPainted"] = false
    defaults["ComboBox.padding"] = JBUI.insets(1, 5, 1, 5)
    defaults["ComboBox.squareButton"] = true
    defaults["CompletionPopup.nonFocusedState"] = true
    defaults["Component.arc"] = 0
    defaults["Component.errorFocusColor"] = ColorUIResource(0xE53935)
    defaults["Component.focusErrorColor"] = ColorUIResource(0xE53935)
    defaults["Component.focusWarningColor"] = ColorUIResource(0xFFB62C)
    defaults["Component.focusWidth"] = 2
    defaults["Component.inactiveErrorFocusColor"] = ColorUIResource(0x743A3A)
    defaults["Component.inactiveFocusErrorColor"] = ColorUIResource(0x743A3A)
    defaults["Component.inactiveFocusWarningColor"] = ColorUIResource(0x7F6C00)
    defaults["Component.inactiveWarningFocusColor"] = ColorUIResource(0x7F6C00)
    defaults["Component.warningFocusColor"] = ColorUIResource(0xFFB62C)
    defaults["EditorTabs.underlineHeight"] = 0
    defaults["Focus.activeErrorBorderColor"] = ColorUIResource(0xE53935)
    defaults["Focus.activeWarningBorderColor"] = ColorUIResource(0xFFB62C)
    defaults["Focus.inactiveErrorBorderColor"] = ColorUIResource(0x743A3A)
    defaults["Focus.inactiveWarningBorderColor"] = ColorUIResource(0x7F6C00)
    defaults["HelpTooltip.defaultTextBorderInsets"] = JBUI.insets(10, 10, 10, 16)
    defaults["HelpTooltip.fontSizeDelta"] = 0
    defaults["HelpTooltip.horizontalGap"] = 10
    defaults["HelpTooltip.maxWidth"] = 250
    defaults["HelpTooltip.smallTextBorderInsets"] = JBUI.insets(4, 8, 5, 8)
    defaults["HelpTooltip.verticalGap"] = 4
    defaults["HelpTooltip.xOffset"] = 1
    defaults["HelpTooltip.yOffset"] = 1
    defaults["List.rowHeight"] = 20
    defaults["List.selectedItemAlpha"] = 100
    defaults["ListUI"] = "com.intellij.ui.components.WideSelectionListUI"
    defaults["Menu.border"] = DarculaMenuItemBorder()
    defaults["Menu.maxGutterIconWidth"] = 18
    defaults["MenuBar.border"] = DarculaMenuBarBorder()
    defaults["MenuItem.acceleratorDelimiter"] = "-"
    defaults["MenuItem.border"] = DarculaMenuItemBorder()
    defaults["MenuItem.maxGutterIconWidth"] = 18
    defaults["NewClass.separatorWidth"] = 10
    defaults["Notification.arc"] = 4
    defaults["Notification.borderInsets"] = JBUI.insets(4, 6, 4, 6)
    defaults["Popup.Advertiser.borderInsets"] = JBUI.insets(10, 10, 10, 15)
    defaults["RadioButton.border.width"] = 3
    defaults["RadioButtonMenuItem.borderPainted"] = false
    defaults["ScrollBarUI"] = JBScrollBar::class.java.name
    defaults["SearchEverywhere.Advertiser.borderInsets"] = JBUI.insets(10, 10, 10, 15)
    defaults["Spinner.arrowButtonInsets"] = JBUI.insets(1, 1, 1, 1)
    defaults["Spinner.editorBorderPainted"] = false
    defaults["TabbedPane.fontSizeOffset"] = 0
    defaults["TabbedPane.labelShift"] = 0
    defaults["TabbedPane.selectedLabelShift"] = 0
    defaults["TabbedPane.tabAreaInsets"] = JBUI.insets(0)
    defaults["TabbedPane.tabFillStyle"] = "underline"
    defaults["TabbedPane.tabHeight"] = 32
    defaults["TabbedPane.tabSelectionHeight"] = 2
    defaults["TabbedPane.tabsOverlapBorder"] = true
    defaults["Table.cellNoFocusBorder"] = JBUI.insets(4, 4, 4, 4)
    defaults["Table.rowHeight"] = 20
    defaults["TableHeader.height"] = 25
    defaults["TextArea.caretBlinkRate"] = 500
    defaults["ToolWindow.Header.padding"] = 6 // todo verify once experimental ui is merged
    defaults["ToolWindow.HeaderTab.padding"] = 6 // todo verify once experimental ui is merged
    defaults["Tree.border"] = "1,1,1,1"
    defaults["Tree.collapsedIcon"] = "/com/intellij/ide/ui/laf/icons/darcula/treeCollapsed.svg"
    defaults["Tree.collapsedSelectedIcon"] = "/com/intellij/ide/ui/laf/icons/darcula/treeCollapsedSelected.svg"
    defaults["Tree.expandedIcon"] = "/com/intellij/ide/ui/laf/icons/darcula/treeExpanded.svg"
    defaults["Tree.expandedSelectedIcon"] = "/com/intellij/ide/ui/laf/icons/darcula/treeExpandedSelected.svg"
    defaults["Tree.paintLines"] = false
    defaults["ValidationTooltip.maxWidth"] = 384
    defaults["Window.border"] = "1,1,1,1,000000"
    defaults[JBScrollBar::class.java.name] = JBScrollBar::class.java
  }

  /**
   * Replace default buttons
   *
   * @param defaults
   */
  private fun replaceDefaultButtons(defaults: UIDefaults) {
    defaults["ButtonUI"] = MTDarculaButtonUI::class.java.name
    defaults[MTDarculaButtonUI::class.java.name] = MTDarculaButtonUI::class.java
  }

  /**
   * Replace buttons
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceButtons(defaults: UIDefaults) {
    defaults["ButtonUI"] = MTButtonUI::class.java.name
    defaults[MTButtonUI::class.java.name] = MTButtonUI::class.java
    defaults["Button.border"] = MTButtonBorder()

    defaults["OptionButtonUI"] = MTOptionButtonUI::class.java.name
    defaults[MTOptionButtonUI::class.java.name] = MTOptionButtonUI::class.java

    defaults["OnOffButtonUI"] = MTOnOffButtonUI::class.java.name
    defaults[MTOnOffButtonUI::class.java.name] = MTOnOffButtonUI::class.java

    defaults["ActionButton.backgroundIcon"] = object : Icon {
      override fun paintIcon(c: Component, g: Graphics, x: Int, y: Int) {
        val g2 = g.create() as Graphics2D
        try {
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
          g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
          g2.translate(x, y)
          g2.color = JBUI.CurrentTheme.ActionButton.pressedBackground()
          if (iconWidth > 28) {
            g2.fill3DRect(0, 0, iconWidth, iconHeight, true)
          } else {
            g2.fillOval(0, 0, iconWidth, iconHeight)
          }
        } finally {
          g2.dispose()
        }
      }

      override fun getIconWidth(): Int = JBUI.scale(18)

      override fun getIconHeight(): Int = JBUI.scale(18)
    }
  }

  /**
   * Replace text fields
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceTextFields(defaults: UIDefaults) {
    defaults["TextFieldUI"] = MTTextFieldUI::class.java.name
    defaults[MTTextFieldUI::class.java.name] = MTTextFieldUI::class.java

    defaults["PasswordFieldUI"] = MTPasswordFieldUI::class.java.name
    defaults[MTPasswordFieldUI::class.java.name] = MTPasswordFieldUI::class.java

    defaults["TextField.border"] = MTTextBorder()
    defaults["PasswordField.border"] = MTTextBorder()
    defaults["EditorTextField.border"] = MTEditorTextFieldBorder()
  }

  /**
   * Replace dropdowns
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceDropdowns(defaults: UIDefaults) {
    defaults["ComboBoxUI"] = MTComboBoxUI::class.java.name
    defaults[MTComboBoxUI::class.java.name] = MTComboBoxUI::class.java
  }

  /**
   * Replace progress bars
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceProgressBar(defaults: UIDefaults) {
    defaults["ProgressBarUI"] = MTProgressBarUI::class.java.name
    defaults[MTProgressBarUI::class.java.name] = MTProgressBarUI::class.java
    defaults["ProgressBar.border"] = MTProgressBarBorder()
  }

  /**
   * Replace trees with custom trees with arrow styles, padding, etc
   *
   */
  fun replaceTree(): Unit =
    ApplicationManager.getApplication().putUserData(Control.Painter.KEY, MTRowPainter())

  /**
   * Install the selected item indicator in trees
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceSelectedIndicator(defaults: UIDefaults) {
    val painter = MTSelectedTreePainter()
    defaults[listSelectionPainterKey] = painter
    defaults[listFocusedSelectionPainterKey] = painter
  }

  /**
   * Replace Table headers with padded headers
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceTableHeaders(defaults: UIDefaults) {
    defaults["TableHeaderUI"] = MTTableHeaderUI::class.java.name
    defaults[MTTableHeaderUI::class.java.name] = MTTableHeaderUI::class.java

    defaults["TableHeader.border"] = MTTableHeaderBorder()
    defaults["Table.focusSelectedCellHighlightBorder"] = MTTableSelectedCellHighlightBorder()
  }

  /**
   * Replace tables with padded tables
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceTables(defaults: UIDefaults) {
    defaults["TableHeader.cellBorder"] = MTTableHeaderBorder()
    defaults["Table.cellNoFocusBorder"] = MTTableCellNoFocusBorder()
    defaults["Table.focusCellHighlightBorder"] = MTTableSelectedCellHighlightBorder()
  }

  /**
   * Replace the status bar with padded status bar
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceStatusBar(defaults: UIDefaults) {
    defaults["IdeStatusBarUI"] = MTStatusBarUI::class.java.name
    defaults[MTStatusBarUI::class.java.name] = MTStatusBarUI::class.java

    defaults[statusBarBorderKey] = MTStatusBarBorder()
    defaults["SeparatorUI"] = MTSeparatorUI::class.java.name
    defaults[MTSeparatorUI::class.java.name] = MTSeparatorUI::class.java
  }

  /**
   * Replace the spinners
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceSpinners(defaults: UIDefaults) {
    defaults["SpinnerUI"] = MTSpinnerUI::class.java.name
    defaults[MTSpinnerUI::class.java.name] = MTSpinnerUI::class.java

    defaults["Spinner.border"] = MTSpinnerBorder()
  }

  /**
   * Replace the checkboxes.
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceCheckboxes(defaults: UIDefaults) {
    defaults["CheckBoxUI"] = MTCheckBoxUI::class.java.name
    defaults[MTCheckBoxUI::class.java.name] = MTCheckBoxUI::class.java

    defaults["CheckBoxMenuItemUI"] = MTCheckBoxMenuItemUI::class.java.name
    defaults[MTCheckBoxMenuItemUI::class.java.name] = MTCheckBoxMenuItemUI::class.java

    defaults["CheckBox.border"] = MTCheckBoxBorder()
  }

  /**
   * Replace the radio buttons
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceRadioButtons(defaults: UIDefaults) {
    defaults["RadioButtonUI"] = MTRadioButtonUI::class.java.name
    defaults[MTRadioButtonUI::class.java.name] = MTRadioButtonUI::class.java

    defaults["RadioButtonMenuItemUI"] = MTRadioButtonMenuItemUI::class.java.name
    defaults[MTRadioButtonMenuItemUI::class.java.name] = MTRadioButtonMenuItemUI::class.java
  }

  /**
   * Replace the sliders
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceSliders(defaults: UIDefaults) {
    defaults["SliderUI"] = MTSliderUI::class.java.name
    defaults[MTSliderUI::class.java.name] = MTSliderUI::class.java
  }

  /**
   * Replace the root pane to enable the themed title bar
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceRootPane(defaults: UIDefaults) {
    defaults["RootPaneUI"] = MTRootPaneUI::class.java.name
    defaults[MTRootPaneUI::class.java.name] = MTRootPaneUI::class.java
  }

  /**
   * Replace the text areas
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceTextAreas(defaults: UIDefaults) {
    defaults["TextAreaUI"] = MTTextAreaUI::class.java.name
    defaults[MTTextAreaUI::class.java.name] = MTTextAreaUI::class.java
  }

  /**
   * Replace Tabbed Panes with Custom tabbed panes
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceTabbedPanes(defaults: UIDefaults) {
    defaults["TabbedPane.tabInsets"] = JBUI.insets(5, 10, 5, 10)
    defaults["TabbedPane.selectedTabPadInsets"] = JBUI.insets(0)
    defaults["TabbedPane.contentBorderInsets"] = JBUI.insets(3, 1, 1, 1)

    defaults["TabbedPaneUI"] = MTTabbedPaneUI::class.java.name
    defaults[MTTabbedPaneUI::class.java.name] = MTTabbedPaneUI::class.java
  }

  /**
   * Replace labels
   *
   * @param defaults of type UIDefaults
   */
  private fun replaceLabels(defaults: UIDefaults) {
    defaults["LabelUI"] = MTLabelUI::class.java.name
    defaults[MTLabelUI::class.java.name] = MTLabelUI::class.java
  }

  /**
   * Replace the menus with padded menus
   *
   * @param defaults defaults to fill
   */
  private fun replaceMenus(defaults: UIDefaults) {
    defaults["PopupMenuUI"] = MTPopupMenuUI::class.java.name
    defaults[MTPopupMenuUI::class.java.name] = MTPopupMenuUI::class.java

    defaults["PopupMenu.border"] = MTPopupMenuBorder()
    defaults["MenuItem.border"] = MTMenuItemBorder()
    defaults["Menu.border"] = MTMenuItemBorder()
  }

  /**
   * Add registry modifications
   */
  private fun modifyRegistry() {
    Registry.get("ide.balloon.shadow.size").setValue(0)
  }

  /**
   * Install defaults - background, foreground, selection background and foreground, inactive background
   *
   * @param defaults of type UIDefaults the defaults to fill
   */
  @Suppress("HardCodedStringLiteral")
  fun loadDefaults(defaults: UIDefaults) {
    val globalProps: MutableMap<String, Any> = HashMap(100)
    val selectedTheme = MTConfig.getInstance().selectedTheme.theme
    val backgroundColorString = selectedTheme.backgroundColor
    val backgroundColor = ColorUIResource(backgroundColorString)

    globalProps["background"] = backgroundColor
    globalProps["textBackground"] = backgroundColor
    globalProps["inactiveBackground"] = backgroundColor

    val foregroundColorString = selectedTheme.foregroundColor
    val foregroundColor = ColorUIResource(foregroundColorString)
    globalProps["foreground"] = foregroundColor
    globalProps["textForeground"] = foregroundColor
    globalProps["inactiveForeground"] = foregroundColor
    globalProps["selectionForegroundInactive"] = foregroundColor
    globalProps["selectionInactiveForeground"] = foregroundColor

    val selectionBackgroundColorString = selectedTheme.selectionBackgroundColor
    val selectionBgColor: Color = ColorUIResource(selectionBackgroundColorString)
    globalProps["selectionBackgroundInactive"] = selectionBgColor
    globalProps["selectionInactiveBackground"] = selectionBgColor

    val selectionForegroundColorString = selectedTheme.selectionForegroundColor
    val selectionFgColor: Color = ColorUIResource(selectionForegroundColorString)
    globalProps["selectionForeground"] = selectionFgColor

    for (key in defaults.keys) {
      if (key is String && key.contains(".")) {
        val property = key.substring(key.lastIndexOf('.') + 1)
        if (globalProps.containsKey(property)) {
          defaults[key] = globalProps[property]
        }
      }
    }
  }
}

