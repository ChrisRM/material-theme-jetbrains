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

package com.mallowigi.idea.lafs;

import com.intellij.ide.ui.laf.darcula.ui.DarculaMenuBarBorder;
import com.intellij.ide.ui.laf.darcula.ui.DarculaMenuItemBorder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.ui.components.JBScrollBar;
import com.intellij.ui.tree.ui.Control;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.themes.models.MTThemeable;
import com.mallowigi.idea.ui.*;
import com.mallowigi.idea.ui.indicators.MTSelectedTreePainter;
import com.mallowigi.idea.utils.MTUI;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Service to install Material Theme properties in the UIManager
 */
@SuppressWarnings({"ClassWithTooManyMethods",
  "OverlyLongMethod",
  "DuplicateStringLiteralInspection",
  "OverlyCoupledClass",
  "MagicNumber",
  "java:S109"
})
public class MTLafInstaller {
  /**
   * The Theme
   */
  @Nullable
  private final MTThemeable theme;

  /**
   * Constructor MTLafInstaller creates a new MTLafInstaller instance.
   *
   * @param theme of type MTThemeable
   */
  MTLafInstaller(@Nullable final MTThemeable theme) {
    this.theme = theme;
  }

  /**
   * Install Material Theme UI Components.
   * <p>
   * Some components will only be installed if the Material Components option is set, while others depend on other options, such as
   * Compact Statusbars, Arrow Styles, Title bar and so on.
   *
   * @param defaults the UIManager defaults to install properties into
   */
  static void installMTDefaults(final UIDefaults defaults) {
    replaceStatusBar(defaults);
    replaceTree(defaults);
    replaceSelectedIndicator(defaults);
    replaceDropdowns(defaults);
    replaceTableHeaders(defaults);
    replaceRootPane(defaults);
    replaceMenus(defaults);
    replaceTabbedPanes(defaults);
    replaceLabels(defaults);
    replaceDefaultButtons(defaults);

    replaceButtons(defaults);
    replaceTextFields(defaults);
    replaceProgressBar(defaults);
    replaceTables(defaults);
    replaceSpinners(defaults);
    replaceCheckboxes(defaults);
    replaceRadioButtons(defaults);
    replaceSliders(defaults);
    replaceTextAreas(defaults);
    modifyRegistry();
  }

  /**
   * Install non themeable defaults, such as borders, insets and so on
   *
   * @param defaults of type UIDefaults
   */
  @SuppressWarnings("DuplicateStringLiteralInspection")
  static void installDefaults(@NonNls final UIDefaults defaults) {
    defaults.put("ActionsList.icon.gap", 8);
    defaults.put("ActionsList.mnemonic.icon.gap", 6);
    defaults.put("ActionsList.mnemonics.insets", "0,8,0,8");
    defaults.put("ActionsList.mnemonicsBorderInsets", "0,8,1,6");
    defaults.put("Border.width", 2); // deprecated
    defaults.put("Button.ToolWindow.arc", 0);
    defaults.put("Button.arc", 6);
    defaults.put("Caret.width", 2);
    defaults.put("CellEditor.border.width", 2);
    defaults.put("CheckBox.border.width", 3);
    defaults.put("CheckBoxMenuItem.borderPainted", false);
    defaults.put("ComboBox.padding", JBUI.insets(1, 5, 1, 5));
    defaults.put("ComboBox.squareButton", true);
    defaults.put("CompletionPopup.nonFocusedState", true);
    defaults.put("Component.arc", 0);
    defaults.put("Component.errorFocusColor", new ColorUIResource(0xE53935));
    defaults.put("Component.focusErrorColor", new ColorUIResource(0xE53935));
    defaults.put("Component.focusWarningColor", new ColorUIResource(0xFFB62C));
    defaults.put("Component.focusWidth", 2);
    defaults.put("Component.inactiveErrorFocusColor", new ColorUIResource(0x743A3A));
    defaults.put("Component.inactiveFocusErrorColor", new ColorUIResource(0x743A3A));
    defaults.put("Component.inactiveFocusWarningColor", new ColorUIResource(0x7F6C00));
    defaults.put("Component.inactiveWarningFocusColor", new ColorUIResource(0x7F6C00));
    defaults.put("Component.warningFocusColor", new ColorUIResource(0xFFB62C));
    defaults.put("EditorTabs.underlineHeight", 0);
    defaults.put("Focus.activeErrorBorderColor", new ColorUIResource(0xE53935));
    defaults.put("Focus.activeWarningBorderColor", new ColorUIResource(0xFFB62C));
    defaults.put("Focus.inactiveErrorBorderColor", new ColorUIResource(0x743A3A));
    defaults.put("Focus.inactiveWarningBorderColor", new ColorUIResource(0x7F6C00));
    defaults.put("HelpTooltip.defaultTextBorderInsets", JBUI.insets(10, 10, 10, 16));
    defaults.put("HelpTooltip.fontSizeDelta", 0);
    defaults.put("HelpTooltip.horizontalGap", 10);
    defaults.put("HelpTooltip.maxWidth", 250);
    defaults.put("HelpTooltip.smallTextBorderInsets", JBUI.insets(4, 8, 5, 8));
    defaults.put("HelpTooltip.verticalGap", 4);
    defaults.put("HelpTooltip.xOffset", 1);
    defaults.put("HelpTooltip.yOffset", 1);
    defaults.put("List.rowHeight", 20);
    defaults.put("List.selectedItemAlpha", 100);
    defaults.put("ListUI", "com.intellij.ui.components.WideSelectionListUI");
    defaults.put("Menu.border", new DarculaMenuItemBorder());
    defaults.put("Menu.maxGutterIconWidth", 18);
    defaults.put("MenuBar.border", new DarculaMenuBarBorder());
    defaults.put("MenuItem.acceleratorDelimiter", "-");
    defaults.put("MenuItem.border", new DarculaMenuItemBorder());
    defaults.put("MenuItem.maxGutterIconWidth", 18);
    defaults.put("NewClass.separatorWidth", 10);
    defaults.put("Notification.arc", 4);
    defaults.put("Notification.borderInsets", JBUI.insets(4, 6, 4, 6));
    defaults.put("Popup.Advertiser.borderInsets", JBUI.insets(10, 10, 10, 15));
    defaults.put("RadioButton.border.width", 3);
    defaults.put("RadioButtonMenuItem.borderPainted", false);
    defaults.put("ScrollBarUI", JBScrollBar.class.getName());
    defaults.put("SearchEverywhere.Advertiser.borderInsets", JBUI.insets(10, 10, 10, 15));
    defaults.put("Spinner.arrowButtonInsets", JBUI.insets(1, 1, 1, 1));
    defaults.put("Spinner.editorBorderPainted", false);
    defaults.put("TabbedPane.fontSizeOffset", 0);
    defaults.put("TabbedPane.labelShift", 0);
    defaults.put("TabbedPane.selectedLabelShift", 0);
    defaults.put("TabbedPane.tabAreaInsets", JBUI.insets(0));
    defaults.put("TabbedPane.tabFillStyle", "underline");
    defaults.put("TabbedPane.tabHeight", 32);
    defaults.put("TabbedPane.tabSelectionHeight", 2);
    defaults.put("TabbedPane.tabsOverlapBorder", true);
    defaults.put("Table.cellNoFocusBorder", JBUI.insets(4, 4, 4, 4));
    defaults.put("Table.rowHeight", 20);
    defaults.put("TableHeader.height", 25);
    defaults.put("TextArea.caretBlinkRate", 500);
    defaults.put("ToolWindow.Header.padding", 6); // todo verify once experimental ui is merged
    defaults.put("ToolWindow.HeaderTab.padding", 6); // todo verify once experimental ui is merged
    defaults.put("Tree.border", "1,1,1,1");
    defaults.put("Tree.collapsedIcon", "/com/intellij/ide/ui/laf/icons/darcula/treeCollapsed.svg");
    defaults.put("Tree.collapsedSelectedIcon", "/com/intellij/ide/ui/laf/icons/darcula/treeCollapsedSelected.svg");
    defaults.put("Tree.expandedIcon", "/com/intellij/ide/ui/laf/icons/darcula/treeExpanded.svg");
    defaults.put("Tree.expandedSelectedIcon", "/com/intellij/ide/ui/laf/icons/darcula/treeExpandedSelected.svg");
    defaults.put("Tree.paintLines", false);
    defaults.put("ValidationTooltip.maxWidth", 384);
    defaults.put("Window.border", "1,1,1,1,000000");
    defaults.put(JBScrollBar.class.getName(), JBScrollBar.class);
  }

  private static void replaceDefaultButtons(final UIDefaults defaults) {
    defaults.put("ButtonUI", MTDarculaButtonUI.class.getName());
    defaults.put(MTDarculaButtonUI.class.getName(), MTDarculaButtonUI.class);
  }

  /**
   * Replace buttons
   *
   * @param defaults of type UIDefaults
   */
  @SuppressWarnings("OverlyComplexAnonymousInnerClass")
  private static void replaceButtons(final UIDefaults defaults) {
    defaults.put("ButtonUI", MTButtonUI.class.getName());
    defaults.put(MTButtonUI.class.getName(), MTButtonUI.class);

    defaults.put("Button.border", new MTButtonBorder());

    defaults.put("OptionButtonUI", MTOptionButtonUI.class.getName());
    defaults.put(MTOptionButtonUI.class.getName(), MTOptionButtonUI.class);

    defaults.put("OnOffButtonUI", MTOnOffButtonUI.class.getName());
    defaults.put(MTOnOffButtonUI.class.getName(), MTOnOffButtonUI.class);

    defaults.put("ActionButton.backgroundIcon", new Icon() {
      @Override
      public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        final Graphics2D g2 = (Graphics2D) g.create();
        try {
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

          g2.translate(x, y);
          g2.setColor(JBUI.CurrentTheme.ActionButton.pressedBackground());

          if (getIconWidth() > 28) {
            g2.fill3DRect(0, 0, getIconWidth(), getIconHeight(), true);
          } else {
            g2.fillOval(0, 0, getIconWidth(), getIconHeight());
          }
        } finally {
          g2.dispose();
        }
      }

      @Override
      public int getIconWidth() {
        return JBUI.scale(18);
      }

      @Override
      public int getIconHeight() {
        return JBUI.scale(18);
      }
    });
  }

  /**
   * Replace text fields
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceTextFields(final UIDefaults defaults) {
    defaults.put("TextFieldUI", MTTextFieldUI.class.getName());
    defaults.put(MTTextFieldUI.class.getName(), MTTextFieldUI.class);

    defaults.put("PasswordFieldUI", MTPasswordFieldUI.class.getName());
    defaults.put(MTPasswordFieldUI.class.getName(), MTPasswordFieldUI.class);

    defaults.put("TextField.border", new MTTextBorder());
    defaults.put("PasswordField.border", new MTTextBorder());
    defaults.put("EditorTextField.border", new MTEditorTextFieldBorder());
  }

  /**
   * Replace dropdowns
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceDropdowns(final UIDefaults defaults) {
    defaults.put("ComboBoxUI", MTComboBoxUI.class.getName());
    defaults.put(MTComboBoxUI.class.getName(), MTComboBoxUI.class);
  }

  /**
   * Replace progress bars
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceProgressBar(final UIDefaults defaults) {
    defaults.put("ProgressBarUI", MTProgressBarUI.class.getName());
    defaults.put(MTProgressBarUI.class.getName(), MTProgressBarUI.class);

    defaults.put("ProgressBar.border", new MTProgressBarBorder());
  }

  /**
   * Replace trees with custom trees with arrow styles, padding, etc
   *
   * @param defaults of type UIDefaults
   */
  public static void replaceTree(final UIDefaults defaults) {
    ApplicationManager.getApplication().putUserData(Control.Painter.KEY, new MTRowPainter());
  }

  /**
   * Install the selected item indicator in trees
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceSelectedIndicator(@NonNls final UIDefaults defaults) {
    final MTSelectedTreePainter painter = new MTSelectedTreePainter();
    defaults.put(MTUI.List.getListSelectionPainterKey(), painter);
    defaults.put(MTUI.List.getListFocusedSelectionPainterKey(), painter);
  }

  /**
   * Replace Table headers with padded headers
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceTableHeaders(@NonNls final UIDefaults defaults) {
    defaults.put("TableHeaderUI", MTTableHeaderUI.class.getName());
    defaults.put(MTTableHeaderUI.class.getName(), MTTableHeaderUI.class);

    defaults.put("TableHeader.border", new MTTableHeaderBorder());
    defaults.put("Table.focusSelectedCellHighlightBorder", new MTTableSelectedCellHighlightBorder());
  }

  /**
   * Replace tables with padded tables
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceTables(@NonNls final UIDefaults defaults) {
    defaults.put("TableHeader.cellBorder", new MTTableHeaderBorder());
    defaults.put("Table.cellNoFocusBorder", new MTTableCellNoFocusBorder());
    defaults.put("Table.focusCellHighlightBorder", new MTTableSelectedCellHighlightBorder());
  }

  /**
   * Replace the status bar with padded status bar
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceStatusBar(@NonNls final UIDefaults defaults) {
    defaults.put("IdeStatusBarUI", MTStatusBarUI.class.getName());
    defaults.put(MTStatusBarUI.class.getName(), MTStatusBarUI.class);
    defaults.put(MTUI.StatusBar.getStatusBarBorderKey(), new MTStatusBarBorder());

    defaults.put("SeparatorUI", MTSeparatorUI.class.getName());
    defaults.put(MTSeparatorUI.class.getName(), MTSeparatorUI.class);
  }

  /**
   * Replace the spinners
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceSpinners(final UIDefaults defaults) {
    defaults.put("SpinnerUI", MTSpinnerUI.class.getName());
    defaults.put(MTSpinnerUI.class.getName(), MTSpinnerUI.class);

    defaults.put("Spinner.border", new MTSpinnerBorder());
  }

  /**
   * Replace the checkboxes.
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceCheckboxes(final UIDefaults defaults) {
    defaults.put("CheckBoxUI", MTCheckBoxUI.class.getName());
    defaults.put(MTCheckBoxUI.class.getName(), MTCheckBoxUI.class);

    defaults.put("CheckBoxMenuItemUI", MTCheckBoxMenuItemUI.class.getName());
    defaults.put(MTCheckBoxMenuItemUI.class.getName(), MTCheckBoxMenuItemUI.class);

    defaults.put("CheckBox.border", new MTCheckBoxBorder());
  }

  /**
   * Replace the radio buttons
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceRadioButtons(final UIDefaults defaults) {
    defaults.put("RadioButtonUI", MTRadioButtonUI.class.getName());
    defaults.put(MTRadioButtonUI.class.getName(), MTRadioButtonUI.class);

    defaults.put("RadioButtonMenuItemUI", MTRadioButtonMenuItemUI.class.getName());
    defaults.put(MTRadioButtonMenuItemUI.class.getName(), MTRadioButtonMenuItemUI.class);
  }

  /**
   * Replace the sliders
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceSliders(final UIDefaults defaults) {
    defaults.put("SliderUI", MTSliderUI.class.getName());
    defaults.put(MTSliderUI.class.getName(), MTSliderUI.class);
  }

  /**
   * Replace the root pane to enable the themed title bar
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceRootPane(final UIDefaults defaults) {
    defaults.put("RootPaneUI", MTRootPaneUI.class.getName());
    defaults.put(MTRootPaneUI.class.getName(), MTRootPaneUI.class);
  }

  /**
   * Replace the text areas
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceTextAreas(final UIDefaults defaults) {
    defaults.put("TextAreaUI", MTTextAreaUI.class.getName());
    defaults.put(MTTextAreaUI.class.getName(), MTTextAreaUI.class);
  }

  /**
   * Replace Tabbed Panes with Custom tabbed panes
   *
   * @param defaults of type UIDefaults
   */
  private static void replaceTabbedPanes(final UIDefaults defaults) {
    defaults.put("TabbedPane.tabInsets", JBUI.insets(5, 10, 5, 10));
    defaults.put("TabbedPane.selectedTabPadInsets", JBUI.insets(0));
    defaults.put("TabbedPane.contentBorderInsets", JBUI.insets(3, 1, 1, 1));

    defaults.put("TabbedPaneUI", MTTabbedPaneUI.class.getName());
    defaults.put(MTTabbedPaneUI.class.getName(), MTTabbedPaneUI.class);
  }

  private static void replaceLabels(@NonNls final UIDefaults defaults) {
    defaults.put("LabelUI", MTLabelUI.class.getName());
    defaults.put(MTLabelUI.class.getName(), MTLabelUI.class);
  }

  /**
   * Replace the menus with padded menus
   *
   * @param defaults defaults to fill
   */
  private static void replaceMenus(@NonNls final UIDefaults defaults) {
    defaults.put("PopupMenuUI", MTPopupMenuUI.class.getName());
    defaults.put(MTPopupMenuUI.class.getName(), MTPopupMenuUI.class);

    defaults.put("PopupMenu.border", new MTPopupMenuBorder());
    defaults.put("MenuItem.border", new MTMenuItemBorder());
    defaults.put("Menu.border", new MTMenuItemBorder());
  }

  /**
   * Add registry modifications
   */
  private static void modifyRegistry() {
    Registry.get("ide.balloon.shadow.size").setValue(0);
  }

  /**
   * Install defaults - background, foreground, selection background and foreground, inactive background
   *
   * @param defaults of type UIDefaults the defaults to fill
   */
  @SuppressWarnings({"MagicCharacter",
    "DuplicateStringLiteralInspection",
    "FeatureEnvy"
  })
  static void loadDefaults(final UIDefaults defaults) {
    @NonNls final Map<String, Object> globalProps = new HashMap<>(100);
    final MTThemeable selectedTheme = MTConfig.getInstance().getSelectedTheme().getTheme();

    final Color backgroundColorString = selectedTheme.getBackgroundColor();
    final ColorUIResource backgroundColor = new ColorUIResource(backgroundColorString);
    globalProps.put("background", backgroundColor);
    globalProps.put("textBackground", backgroundColor);
    globalProps.put("inactiveBackground", backgroundColor);

    final Color foregroundColorString = selectedTheme.getForegroundColor();
    final ColorUIResource foregroundColor = new ColorUIResource(foregroundColorString);
    globalProps.put("foreground", foregroundColor);
    globalProps.put("textForeground", foregroundColor);
    globalProps.put("inactiveForeground", foregroundColor);
    globalProps.put("selectionForegroundInactive", foregroundColor);
    globalProps.put("selectionInactiveForeground", foregroundColor);

    final Color selectionBackgroundColorString = selectedTheme.getSelectionBackgroundColor();
    final Color selectionBgColor = new ColorUIResource(selectionBackgroundColorString);
    globalProps.put("selectionBackgroundInactive", selectionBgColor);
    globalProps.put("selectionInactiveBackground", selectionBgColor);

    final Color selectionForegroundColorString = selectedTheme.getSelectionForegroundColor();
    final Color selectionFgColor = new ColorUIResource(selectionForegroundColorString);
    globalProps.put("selectionForeground", selectionFgColor);

    for (final Object key : defaults.keySet()) {
      if (key instanceof String && ((String) key).contains(".")) {
        final String s = (String) key;
        final String property = s.substring(s.lastIndexOf('.') + 1);
        if (globalProps.containsKey(property)) {
          defaults.put(key, globalProps.get(property));
        }
      }
    }
  }

  /**
   * Method getPrefix returns the prefix of the theme in properties
   *
   * @return the prefix (type String) of the theme in properties
   */
  final String getPrefix() {
    return Objects.requireNonNull(theme).getThemeId();
  }
}
