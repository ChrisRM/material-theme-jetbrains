/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.chrisrm.idea.themes.MTThemeable;
import com.chrisrm.idea.ui.*;
import com.chrisrm.idea.ui.indicators.MTSelectedTreePainter;
import com.chrisrm.idea.utils.PropertiesParser;
import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.IntelliJTableSelectedCellHighlightBorder;
import com.intellij.ide.ui.laf.darcula.DarculaTableHeaderBorder;
import com.intellij.ide.ui.laf.darcula.DarculaTableHeaderUI;
import com.intellij.ide.ui.laf.darcula.ui.*;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.ui.components.JBScrollBar;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class MTLafInstaller {
  protected final MTConfig mtConfig;
  protected final MTLaf mtDarkLaf;
  private final MTThemeable theme;

  public MTLafInstaller() {
    mtConfig = MTConfig.getInstance();
    mtDarkLaf = null;
    theme = null;
  }

  public MTLafInstaller(final MTLaf mtDarkLaf, final MTThemeable theme) {
    mtConfig = MTConfig.getInstance();
    this.mtDarkLaf = mtDarkLaf;
    this.theme = theme;
  }

  public void installMTDefaults(final UIDefaults defaults) {
    replaceStatusBar(defaults);
    replaceTree(defaults);
    replaceSelectedIndicator(defaults);
    replaceDropdowns(defaults);
    replaceTableHeaders(defaults);
    replaceIcons(defaults);
    replaceRootPane(defaults);

    if (mtConfig.getIsMaterialDesign()) {
      replaceButtons(defaults);
      replaceTextFields(defaults);
      replaceProgressBar(defaults);
      replaceTables(defaults);
      replaceSpinners(defaults);
      replaceCheckboxes(defaults);
      replaceRadioButtons(defaults);
      replaceSliders(defaults);
      replaceTextAreas(defaults);
      replaceTabbedPanes(defaults);
      modifyRegistry(defaults);
    }
  }

  public void installDefaults(final UIDefaults defaults) {
    defaults.put("Caret.width", 2);
    defaults.put("Border.width", 2);
    defaults.put("CellEditor.border.width", 2);

    defaults.put("Button.arc", 6);
    defaults.put("Component.arc", 0);

    defaults.put("Menu.maxGutterIconWidth", 18);
    defaults.put("MenuItem.maxGutterIconWidth", 18);
    defaults.put("MenuItem.acceleratorDelimiter", "-");
    defaults.put("MenuItem.border", new DarculaMenuItemBorder());
    defaults.put("Menu.border", new DarculaMenuItemBorder());
    defaults.put("TextArea.caretBlinkRate", 500);
    defaults.put("Table.cellNoFocusBorder", JBUI.insets(4, 4, 4, 4));
    defaults.put("CheckBoxMenuItem.borderPainted", false);
    defaults.put("RadioButtonMenuItem.borderPainted", false);
    defaults.put("ComboBox.squareButton", true);
    defaults.put("ComboBox.padding", JBUI.insets(1, 5, 1, 5));
    defaults.put("CheckBox.border.width", 3);
    defaults.put("RadioButton.border.width", 3);

    defaults.put("HelpTooltip.verticalGap", 4);
    defaults.put("HelpTooltip.horizontalGap", 10);
    defaults.put("HelpTooltip.maxWidth", 250);
    defaults.put("HelpTooltip.xOffset", 1);
    defaults.put("HelpTooltip.yOffset", 1);

    defaults.put("HelpTooltip.defaultTextBorderInsets", JBUI.insets(10, 10, 10, 16));
    defaults.put("HelpTooltip.fontSizeDelta", 0);
    defaults.put("HelpTooltip.smallTextBorderInsets", JBUI.insets(4, 8, 5, 8));
    defaults.put("ValidationTooltip.maxWidth", 384);

    defaults.put("Spinner.arrowButtonInsets", JBUI.insets(1, 1, 1, 1));
    defaults.put("Spinner.editorBorderPainted", false);
    defaults.put("ToolWindow.tab.verticalPadding", 5);
    defaults.put("ScrollBarUI", JBScrollBar.class.getName());
    defaults.put(JBScrollBar.class.getName(), JBScrollBar.class);

    defaults.put("Focus.activeErrorBorderColor", new ColorUIResource(0xE53935));
    defaults.put("Focus.inactiveErrorBorderColor", new ColorUIResource(0x743A3A));
    defaults.put("Focus.activeWarningBorderColor", new ColorUIResource(0xFFB62C));
    defaults.put("Focus.inactiveWarningBorderColor", new ColorUIResource(0x7F6C00));

    defaults.put("TabbedPane.tabAreaInsets", JBUI.insets(0));
    defaults.put("TabbedPane.selectedLabelShift", 0);
    defaults.put("TabbedPane.labelShift", 0);
    defaults.put("TabbedPane.tabsOverlapBorder", true);
    defaults.put("TabbedPane.tabHeight", 32);
    defaults.put("TabbedPane.tabSelectionHeight", 2);
    defaults.put("TabbedPane.tabFillStyle", "underline");
  }

  public void installDarculaDefaults(final UIDefaults defaults) {
    defaults.put("darcula.primary", new ColorUIResource(0x3c3f41));
    defaults.put("darcula.contrastColor", new ColorUIResource(0x262626));

    //    defaults.put("EditorPaneUI", DarculaEditorPaneUI.class.getName());
    //    defaults.put("TableHeaderUI", DarculaTableHeaderUI.class.getName());
    //    defaults.put("Table.focusSelectedCellHighlightBorder", new DarculaTableSelectedCellHighlightBorder());
    //    defaults.put("TableHeader.cellBorder", new DarculaTableHeaderBorder());

    //    defaults.put("CheckBoxMenuItemUI", DarculaCheckBoxMenuItemUI.class.getName());
    //    defaults.put("RadioButtonMenuItemUI", DarculaRadioButtonMenuItemUI.class.getName());
    //    defaults.put("TabbedPaneUI", DarculaTabbedPaneUI.class.getName());
    //
    //    defaults.put("TextFieldUI", DarculaTextFieldUI.class.getName());
    //    defaults.put("TextField.border", new DarculaTextBorder());

    //    defaults.put("PasswordFieldUI", DarculaPasswordFieldUI.class.getName());
    //    defaults.put("PasswordField.border", new DarculaTextBorder());
    //    defaults.put("ProgressBarUI", DarculaProgressBarUI.class.getName());
    //    defaults.put("ProgressBar.border", new DarculaProgressBarBorder());
    //    defaults.put("FormattedTextFieldUI", DarculaTextFieldUI.class.getName());
    //    defaults.put("FormattedTextField.border", new DarculaTextBorder());
    //
    //    defaults.put("TextAreaUI", DarculaTextAreaUI.class.getName());
    //    defaults.put("CheckBoxUI", DarculaCheckBoxUI.class.getName());
    //
    //    defaults.put("CheckBox.border", new DarculaCheckBoxBorder());
    //    defaults.put("ComboBoxUI", DarculaComboBoxUI.class.getName());
    //    defaults.put("RadioButtonUI", DarculaRadioButtonUI.class.getName());
    //    defaults.put("RadioButton.border", new DarculaCheckBoxBorder());
    //
    //    defaults.put("Button.border", new DarculaButtonPainter());
    //    defaults.put("ButtonUI", DarculaButtonUI.class.getName());
    //
    //    defaults.put("ToggleButton.border", new DarculaButtonPainter());
    //    defaults.put("ToggleButtonUI", DarculaButtonUI.class.getName());
    //
    //    defaults.put("SpinnerUI", DarculaSpinnerUI.class.getName());
    //    defaults.put("Spinner.border", new DarculaSpinnerBorder());
    //
    //    defaults.put("TreeUI", DarculaTreeUI.class.getName());
    //    defaults.put("OptionButtonUI", DarculaOptionButtonUI.class.getName());
    defaults.put("grayFilter", new UIUtil.GrayFilter(-100, -100, 100));
    defaults.put("text.grayFilter", new UIUtil.GrayFilter(-15, -10, 100));

    //    defaults.put("RootPaneUI", DarculaRootPaneUI.class.getName());
  }

  protected void installLightDefaults(final UIDefaults defaults) {
    defaults.put("intellijlaf.primary", new ColorUIResource(0xe8e8e8));
    defaults.put("intellijlaf.contrastColor", new ColorUIResource(0xEEEEEE));

    defaults.put("EditorPaneUI", DarculaEditorPaneUI.class.getName());
    defaults.put("TableHeaderUI", DarculaTableHeaderUI.class.getName());
    defaults.put("Table.focusSelectedCellHighlightBorder", new IntelliJTableSelectedCellHighlightBorder());
    defaults.put("TableHeader.cellBorder", new DarculaTableHeaderBorder());

    defaults.put("CheckBoxMenuItemUI", DarculaCheckBoxMenuItemUI.class.getName());
    defaults.put("RadioButtonMenuItemUI", DarculaRadioButtonMenuItemUI.class.getName());
    defaults.put("TabbedPaneUI", DarculaTabbedPaneUI.class.getName());

    defaults.put("TextFieldUI", DarculaTextFieldUI.class.getName());
    defaults.put("TextField.border", new DarculaTextBorder());
    //    defaults.put("TextField.darcula.search.icon", "/com/intellij/ide/ui/laf/icons/search.png");
    //    defaults.put("TextField.darcula.searchWithHistory.icon", "/com/intellij/ide/ui/laf/icons/searchWithHistory.png");
    //    defaults.put("TextField.darcula.clear.icon", "/com/intellij/ide/ui/laf/icons/clear.png");

    defaults.put("PasswordFieldUI", DarculaPasswordFieldUI.class.getName());
    defaults.put("PasswordField.border", new DarculaTextBorder());
    defaults.put("ProgressBarUI", DarculaProgressBarUI.class.getName());
    defaults.put("ProgressBar.border", new DarculaProgressBarBorder());
    defaults.put("FormattedTextFieldUI", DarculaTextFieldUI.class.getName());
    defaults.put("FormattedTextField.border", new DarculaTextBorder());

    defaults.put("TextAreaUI", DarculaTextAreaUI.class.getName());
    defaults.put("Tree.paintLines", false);

    defaults.put("CheckBoxUI", DarculaCheckBoxUI.class.getName());
    defaults.put("CheckBox.border", new DarculaCheckBoxBorder());
    defaults.put("ComboBoxUI", DarculaComboBoxUI.class.getName());
    defaults.put("RadioButtonUI", DarculaRadioButtonUI.class.getName());
    defaults.put("RadioButton.border", new DarculaCheckBoxBorder());

    defaults.put("Button.border", new DarculaButtonPainter());
    defaults.put("ButtonUI", DarculaButtonUI.class.getName());

    defaults.put("ToggleButton.border", new DarculaButtonPainter());
    defaults.put("ToggleButtonUI", DarculaButtonUI.class.getName());

    defaults.put("SpinnerUI", DarculaSpinnerUI.class.getName());
    defaults.put("Spinner.border", new DarculaSpinnerBorder());

    defaults.put("TreeUI", DarculaTreeUI.class.getName());
    defaults.put("OptionButtonUI", DarculaOptionButtonUI.class.getName());
    defaults.put("InternalFrameUI", DarculaInternalFrameUI.class.getName());
    defaults.put("RootPaneUI", DarculaRootPaneUI.class.getName());
    defaults.put("grayFilter", new UIUtil.GrayFilter(80, -35, 100));
    defaults.put("text.grayFilter", new UIUtil.GrayFilter(20, 0, 100));
  }

  /**
   * Replace buttons
   *
   * @param defaults
   */
  private void replaceButtons(final UIDefaults defaults) {
    defaults.put("ButtonUI", MTButtonUI.class.getName());
    defaults.put(MTButtonUI.class.getName(), MTButtonUI.class);

    defaults.put("Button.border", new MTButtonPainter());

    defaults.put("OptionButtonUI", MTOptionButtonUI.class.getName());
    defaults.put(MTOptionButtonUI.class.getName(), MTOptionButtonUI.class);

    defaults.put("OnOffButtonUI", MTOnOffButtonUI.class.getName());
    defaults.put(MTOnOffButtonUI.class.getName(), MTOnOffButtonUI.class);
  }

  /**
   * Replace text fields
   *
   * @param defaults
   */
  private void replaceTextFields(final UIDefaults defaults) {
    defaults.put("TextFieldUI", MTTextFieldUI.class.getName());
    defaults.put(MTTextFieldUI.class.getName(), MTTextFieldUI.class);

    defaults.put("PasswordFieldUI", MTPasswordFieldUI.class.getName());
    defaults.put(MTPasswordFieldUI.class.getName(), MTPasswordFieldUI.class);

    defaults.put("TextField.border", new MTTextBorder());
    defaults.put("PasswordField.border", new MTTextBorder());
  }

  private void replaceDropdowns(final UIDefaults defaults) {
    defaults.put("ComboBoxUI", MTComboBoxUI.class.getName());
    defaults.put(MTComboBoxUI.class.getName(), MTComboBoxUI.class);
  }

  /**
   * Replace progress bar
   *
   * @param defaults
   */
  private void replaceProgressBar(final UIDefaults defaults) {
    defaults.put("ProgressBarUI", MTProgressBarUI.class.getName());
    defaults.put(MTProgressBarUI.class.getName(), MTProgressBarUI.class);

    defaults.put("ProgressBar.border", new MTProgressBarBorder());
  }

  /**
   * Replace trees
   *
   * @param defaults
   */
  private void replaceTree(final UIDefaults defaults) {
    defaults.put("TreeUI", MTTreeUI.class.getName());
    defaults.put(MTTreeUI.class.getName(), MTTreeUI.class);
  }

  private void replaceSelectedIndicator(final UIDefaults defaults) {
    final MTSelectedTreePainter painter = new MTSelectedTreePainter();
    defaults.put("List.sourceListSelectionBackgroundPainter", painter);
    defaults.put("List.sourceListFocusedSelectionBackgroundPainter", painter);
  }

  /**
   * Replace Table headers
   *
   * @param defaults
   */
  private void replaceTableHeaders(final UIDefaults defaults) {
    defaults.put("TableHeaderUI", MTTableHeaderUI.class.getName());
    defaults.put(MTTableHeaderUI.class.getName(), MTTableHeaderUI.class);

    defaults.put("TableHeader.border", new MTTableHeaderBorder());
    defaults.put("Table.focusSelectedCellHighlightBorder", new MTTableSelectedCellHighlightBorder());
  }

  private void replaceTables(final UIDefaults defaults) {
    defaults.put("TableHeader.cellBorder", new MTTableHeaderBorder());
    defaults.put("Table.cellNoFocusBorder", new MTTableCellNoFocusBorder());
    defaults.put("Table.focusCellHighlightBorder", new MTTableSelectedCellHighlightBorder());
  }

  private void replaceStatusBar(final UIDefaults defaults) {
    defaults.put("IdeStatusBarUI", MTStatusBarUI.class.getName());
    defaults.put(MTStatusBarUI.class.getName(), MTStatusBarUI.class);
    defaults.put("IdeStatusBar.border", new MTStatusBarBorder());
  }

  private void replaceSpinners(final UIDefaults defaults) {
    defaults.put("SpinnerUI", MTSpinnerUI.class.getName());
    defaults.put(MTSpinnerUI.class.getName(), MTSpinnerUI.class);

    defaults.put("Spinner.border", new MTSpinnerBorder());
  }

  private void replaceCheckboxes(final UIDefaults defaults) {
    defaults.put("CheckBoxUI", MTCheckBoxUI.class.getName());
    defaults.put(MTCheckBoxUI.class.getName(), MTCheckBoxUI.class);

    defaults.put("CheckBoxMenuItemUI", MTCheckBoxMenuItemUI.class.getName());
    defaults.put(MTCheckBoxMenuItemUI.class.getName(), MTCheckBoxMenuItemUI.class);

    defaults.put("CheckBox.border", new MTCheckBoxBorder());
  }

  private void replaceRadioButtons(final UIDefaults defaults) {
    defaults.put("RadioButtonUI", MTRadioButtonUI.class.getName());
    defaults.put(MTRadioButtonUI.class.getName(), MTRadioButtonUI.class);

    defaults.put("RadioButtonMenuItemUI", MTRadioButtonMenuItemUI.class.getName());
    defaults.put(MTRadioButtonMenuItemUI.class.getName(), MTRadioButtonMenuItemUI.class);
  }

  private void replaceSliders(final UIDefaults defaults) {
    defaults.put("SliderUI", MTSliderUI.class.getName());
    defaults.put(MTSliderUI.class.getName(), MTSliderUI.class);
  }

  private void replaceRootPane(final UIDefaults defaults) {
    defaults.put("RootPaneUI", MTRootPaneUI.class.getName());
    defaults.put(MTRootPaneUI.class.getName(), MTRootPaneUI.class);
  }

  private void replaceTextAreas(final UIDefaults defaults) {
    defaults.put("TextAreaUI", MTTextAreaUI.class.getName());
    defaults.put(MTTextAreaUI.class.getName(), MTTextAreaUI.class);
  }

  private void replaceTabbedPanes(final UIDefaults defaults) {
    defaults.put("TabbedPane.tabInsets", JBUI.insets(5, 10, 5, 10));
    defaults.put("TabbedPane.selectedTabPadInsets", JBUI.insets(0));
    defaults.put("TabbedPane.contentBorderInsets", JBUI.insets(3, 1, 1, 1));

    defaults.put("TabbedPaneUI", MTTabbedPaneUI.class.getName());
    defaults.put(MTTabbedPaneUI.class.getName(), MTTabbedPaneUI.class);
  }

  private void replaceIcons(final UIDefaults defaults) {
    final Icon expandIcon = MTConfig.getInstance().getArrowsStyle().getExpandIcon();
    final Icon collapseIcon = MTConfig.getInstance().getArrowsStyle().getCollapseIcon();

    defaults.put("Tree.collapsedIcon", expandIcon);
    defaults.put("Tree.expandedIcon", collapseIcon);
    defaults.put("Menu.arrowIcon", expandIcon);
    defaults.put("RadioButtonMenuItem.arrowIcon", expandIcon);
    defaults.put("CheckBoxMenuItem.arrowIcon", expandIcon);

    defaults.put("FileView.fileIcon", AllIcons.FileTypes.Unknown);
    defaults.put("Table.ascendingSortIcon", AllIcons.General.SplitUp);
    defaults.put("Table.descendingSortIcon", AllIcons.General.SplitDown);

    defaults.put("TextField.darcula.searchWithHistory.icon", IconLoader.getIcon("/icons/darcula/searchWithHistory.png"));
    defaults.put("TextField.darcula.search.icon", IconLoader.getIcon("/icons/darcula/search.png"));
    defaults.put("TextField.darcula.clear.icon", IconLoader.getIcon("/icons/darcula/clear.png"));
  }

  private void modifyRegistry(final UIDefaults defaults) {
    Registry.get("ide.balloon.shadow.size").setValue(0);
  }

  public String getPrefix() {
    return theme.getId();
  }

  public void loadDefaults(final UIDefaults defaults) {
    final Properties properties = new Properties();
    final String osSuffix = SystemInfo.isMac ? "mac" : SystemInfo.isWindows ? "windows" : "linux";
    try {
      InputStream stream = getClass().getResourceAsStream(getPrefix() + ".properties");
      properties.load(stream);
      stream.close();

      stream = getClass().getResourceAsStream(getPrefix() + "_" + osSuffix + ".properties");
      properties.load(stream);
      stream.close();

      final HashMap<String, Object> darculaGlobalSettings = new HashMap<>();
      final String prefix = getPrefix() + ".";
      for (final String key : properties.stringPropertyNames()) {
        if (key.startsWith(prefix)) {
          final Object value = parseValue(key, properties.getProperty(key));
          final String darculaKey = key.substring(prefix.length());
          if (value == "system") {
            darculaGlobalSettings.remove(darculaKey);
          } else {
            darculaGlobalSettings.put(darculaKey, value);
          }
        }
      }

      // Replace global settings in custom themes
      final MTThemeable selectedTheme = MTConfig.getInstance().getSelectedTheme().getTheme();
      //      if (selectedTheme.isCustom()) {
      // todo replace other properties
      final Color backgroundColorString = selectedTheme.getBackgroundColor();
      final ColorUIResource backgroundColor = new ColorUIResource(backgroundColorString);
      darculaGlobalSettings.put("background", backgroundColor);
      darculaGlobalSettings.put("textBackground", backgroundColor);
      darculaGlobalSettings.put("inactiveBackground", backgroundColor);

      final Color foregroundColorString = selectedTheme.getForegroundColor();
      final ColorUIResource foregroundColor = new ColorUIResource(foregroundColorString);
      darculaGlobalSettings.put("foreground", foregroundColor);
      darculaGlobalSettings.put("textForeground", foregroundColor);
      darculaGlobalSettings.put("inactiveForeground", foregroundColor);
      //      }

      for (final Object key : defaults.keySet()) {
        if (key instanceof String && ((String) key).contains(".")) {
          final String s = (String) key;
          final String darculaKey = s.substring(s.lastIndexOf('.') + 1);
          if (darculaGlobalSettings.containsKey(darculaKey)) {
            defaults.put(key, darculaGlobalSettings.get(darculaKey));
          }
        }
      }

      for (final String key : properties.stringPropertyNames()) {
        final String value = properties.getProperty(key);
        defaults.put(key, parseValue(key, value));
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public Object parseValue(final String key, @NotNull final String value) {
    return PropertiesParser.parseValue(key, value);
  }
}
