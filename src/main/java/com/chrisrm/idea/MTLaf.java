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

package com.chrisrm.idea;

import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.icons.tinted.TintedIconsService;
import com.chrisrm.idea.ui.*;
import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.DarculaTableHeaderBorder;
import com.intellij.ide.ui.laf.darcula.DarculaTableHeaderUI;
import com.intellij.ide.ui.laf.darcula.DarculaTableSelectedCellHighlightBorder;
import com.intellij.ide.ui.laf.darcula.ui.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.ui.components.JBScrollBar;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class MTLaf extends DarculaLaf {
  protected final MTConfig mtConfig;

  public MTLaf() {
    super();
    mtConfig = MTConfig.getInstance();
  }

  protected void installMTDefaults(final UIDefaults defaults) {
    if (mtConfig.getIsMaterialDesign()) {
      replaceButtons();
      replaceTextFields();
      replaceDropdowns();
      replaceProgressBar();
      replaceTree();
      replaceTableHeaders();
      replaceTables();
      replaceStatusBar();
      replaceSpinners();
      replaceCheckboxes();
      replaceRadioButtons();
      replaceSliders();
      replaceTextAreas();
      replaceTabbedPanes();
      replaceIcons();
      modifyRegistry();
    }
  }

  protected void installDefaults(final UIDefaults defaults) {
    defaults.put("Caret.width", 2);
    defaults.put("Border.width", 2);
    defaults.put("Button.arc", 6);
    defaults.put("Component.arc", 0);

    defaults.put("Menu.maxGutterIconWidth", 18);
    defaults.put("MenuItem.maxGutterIconWidth", 18);
    defaults.put("MenuItem.acceleratorDelimiter", "-");
    defaults.put("MenuItem.border", new DarculaMenuItemBorder());
    defaults.put("Menu.border", new DarculaMenuItemBorder());
    defaults.put("TextArea.caretBlinkRate", 500);
    defaults.put("Table.cellNoFocusBorder", JBUI.insets(10, 2, 10, 2));
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

    defaults.put("HelpTooltip.defaultTextBorder", JBUI.insets(10, 10, 10, 16));
    defaults.put("HelpTooltip.fontSizeDelta", 0);
    defaults.put("HelpTooltip.smallTextBorder", JBUI.insets(4, 8, 5, 8));

    defaults.put("Spinner.arrowButtonInsets", JBUI.insets(1, 1, 1, 1));
    defaults.put("Spinner.editorBorderPainted", false);
    defaults.put("ToolWindow.tab.verticalPadding", 5);
    defaults.put("ScrollBarUI", JBScrollBar.class.getName());
    defaults.put(JBScrollBar.class.getName(), JBScrollBar.class);

    defaults.put("Focus.activeErrorBorderColor", new ColorUIResource(0xE53935));
    defaults.put("Focus.inactiveErrorBorderColor", new ColorUIResource(0x743A3A));
    defaults.put("Focus.activeWarningBorderColor", new ColorUIResource(0xFFB62C));
    defaults.put("Focus.inactiveWarningBorderColor", new ColorUIResource(0x7F6C00));

    defaults.put("TabbedPane.selectedLabelShift", 0);
    defaults.put("TabbedPane.labelShift", 0);
    defaults.put("TabbedPane.tabsOverlapBorder", true);
    defaults.put("TabbedPane.tabHeight", 32);
    defaults.put("TabbedPane.tabSelectionHeight", 2);
    defaults.put("TabbedPane.tabFillStyle", "underline");

    installDarculaDefaults(defaults);
  }

  private void installDarculaDefaults(final UIDefaults defaults) {
    defaults.put("darcula.primary", new ColorUIResource(0x3c3f41));
    defaults.put("darcula.contrastColor", new ColorUIResource(0x262626));

    defaults.put("EditorPaneUI", DarculaEditorPaneUI.class.getName());
    defaults.put("TableHeaderUI", DarculaTableHeaderUI.class.getName());
    defaults.put("Table.focusSelectedCellHighlightBorder", new DarculaTableSelectedCellHighlightBorder());
    defaults.put("TableHeader.cellBorder", new DarculaTableHeaderBorder());

    defaults.put("CheckBoxMenuItemUI", DarculaCheckBoxMenuItemUI.class.getName());
    defaults.put("RadioButtonMenuItemUI", DarculaRadioButtonMenuItemUI.class.getName());
    defaults.put("TabbedPaneUI", DarculaTabbedPaneUI.class.getName());

    defaults.put("TextFieldUI", DarculaTextFieldUI.class.getName());
    defaults.put("TextField.border", new DarculaTextBorder());
    //    defaults.put("TextField.darcula.search.icon", "/com/intellij/ide/ui/laf/icons/darcula/search.png");
    //    defaults.put("TextField.darcula.searchWithHistory.icon", "/com/intellij/ide/ui/laf/icons/darcula/searchWithHistory.png");
    //    defaults.put("TextField.darcula.clear.icon", "/com/intellij/ide/ui/laf/icons/darcula/clear.png");

    defaults.put("PasswordFieldUI", DarculaPasswordFieldUI.class.getName());
    defaults.put("PasswordField.border", new DarculaTextBorder());
    defaults.put("ProgressBarUI", DarculaProgressBarUI.class.getName());
    defaults.put("ProgressBar.border", new DarculaProgressBarBorder());
    defaults.put("FormattedTextFieldUI", DarculaTextFieldUI.class.getName());
    defaults.put("FormattedTextField.border", new DarculaTextBorder());

    defaults.put("TextAreaUI", DarculaTextAreaUI.class.getName());
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
    defaults.put("grayFilter", new UIUtil.GrayFilter(-100, -100, 100));
    defaults.put("text.grayFilter", new UIUtil.GrayFilter(-15, -10, 100));
  }

  /**
   * Replace buttons
   */
  private void replaceButtons() {
    UIManager.put("ButtonUI", MTButtonUI.class.getName());
    UIManager.getDefaults().put(MTButtonUI.class.getName(), MTButtonUI.class);

    UIManager.put("Button.border", new MTButtonPainter());

    UIManager.put("OptionButtonUI", MTOptionButtonUI.class.getName());
    UIManager.getDefaults().put(MTOptionButtonUI.class.getName(), MTOptionButtonUI.class);

    UIManager.put("OnOffButtonUI", MTOnOffButtonUI.class.getName());
    UIManager.put(MTOnOffButtonUI.class.getName(), MTOnOffButtonUI.class);
  }

  /**
   * Replace text fields
   */
  private void replaceTextFields() {
    UIManager.put("TextFieldUI", MTTextFieldUI.class.getName());
    UIManager.getDefaults().put(MTTextFieldUI.class.getName(), MTTextFieldUI.class);

    UIManager.put("PasswordFieldUI", MTPasswordFieldUI.class.getName());
    UIManager.getDefaults().put(MTPasswordFieldUI.class.getName(), MTPasswordFieldUI.class);

    UIManager.put("TextField.border", new MTTextBorder());
    UIManager.put("PasswordField.border", new MTTextBorder());
  }

  private void replaceDropdowns() {
    UIManager.put("ComboBoxUI", MTComboBoxUI.class.getName());
    UIManager.getDefaults().put(MTComboBoxUI.class.getName(), MTComboBoxUI.class);
  }

  /**
   * Replace progress bar
   */
  private void replaceProgressBar() {
    UIManager.put("ProgressBarUI", MTProgressBarUI.class.getName());
    UIManager.getDefaults().put(MTProgressBarUI.class.getName(), MTProgressBarUI.class);

    UIManager.put("ProgressBar.border", new MTProgressBarBorder());
  }

  /**
   * Replace trees
   */
  private void replaceTree() {
    UIManager.put("TreeUI", MTTreeUI.class.getName());
    UIManager.getDefaults().put(MTTreeUI.class.getName(), MTTreeUI.class);

    UIManager.put("List.sourceListSelectionBackgroundPainter", new MTSelectedTreePainter());
    UIManager.put("List.sourceListFocusedSelectionBackgroundPainter", new MTSelectedTreePainter());
  }

  /**
   * Replace Table headers
   */
  private void replaceTableHeaders() {
    UIManager.put("TableHeaderUI", MTTableHeaderUI.class.getName());
    UIManager.getDefaults().put(MTTableHeaderUI.class.getName(), MTTableHeaderUI.class);

    UIManager.put("TableHeader.border", new MTTableHeaderBorder());
    UIManager.put("Table.focusSelectedCellHighlightBorder", new MTTableSelectedCellHighlightBorder());
  }

  private void replaceTables() {
    UIManager.put("TableHeader.cellBorder", new MTTableHeaderBorder());
    UIManager.put("Table.cellNoFocusBorder", new MTTableCellNoFocusBorder());
    UIManager.put("Table.focusCellHighlightBorder", new MTTableSelectedCellHighlightBorder());
  }

  private void replaceStatusBar() {
    final MessageBusConnection connect = ApplicationManager.getApplication().getMessageBus().connect();

    // On app init, set the statusbar borders
    connect.subscribe(ProjectManager.TOPIC, new ProjectManagerListener() {
      @Override
      public void projectOpened(@Nullable final Project projectFromCommandLine) {
        MTThemeManager.getInstance().setStatusBarBorders();
      }
    });

    // And also on config change
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> MTThemeManager.getInstance().setStatusBarBorders());
  }

  private void replaceSpinners() {
    UIManager.put("SpinnerUI", MTSpinnerUI.class.getName());
    UIManager.getDefaults().put(MTSpinnerUI.class.getName(), MTSpinnerUI.class);

    UIManager.put("Spinner.border", new MTSpinnerBorder());
  }

  private void replaceCheckboxes() {
    UIManager.put("CheckBoxUI", MTCheckBoxUI.class.getName());
    UIManager.getDefaults().put(MTCheckBoxUI.class.getName(), MTCheckBoxUI.class);

    UIManager.put("CheckBoxMenuItemUI", MTCheckBoxMenuItemUI.class.getName());
    UIManager.getDefaults().put(MTCheckBoxMenuItemUI.class.getName(), MTCheckBoxMenuItemUI.class);

    UIManager.put("CheckBox.border", new MTCheckBoxBorder());
  }

  private void replaceRadioButtons() {
    UIManager.put("RadioButtonUI", MTRadioButtonUI.class.getName());
    UIManager.getDefaults().put(MTRadioButtonUI.class.getName(), MTRadioButtonUI.class);

    UIManager.put("RadioButtonMenuItemUI", MTRadioButtonMenuItemUI.class.getName());
    UIManager.getDefaults().put(MTRadioButtonMenuItemUI.class.getName(), MTRadioButtonMenuItemUI.class);
  }

  private void replaceSliders() {
    UIManager.put("SliderUI", MTSliderUI.class.getName());
    UIManager.getDefaults().put(MTSliderUI.class.getName(), MTSliderUI.class);
  }

  private void replaceTextAreas() {
    UIManager.put("TextAreaUI", MTTextAreaUI.class.getName());
    UIManager.getDefaults().put(MTTextAreaUI.class.getName(), MTTextAreaUI.class);
  }

  private void replaceTabbedPanes() {
    UIManager.put("TabbedPane.tabInsets", JBUI.insets(5, 10, 5, 10));
    UIManager.put("TabbedPane.contentBorderInsets", JBUI.insets(3, 1, 1, 1));

    UIManager.put("TabbedPaneUI", MTTabbedPaneUI.class.getName());
    UIManager.getDefaults().put(MTTabbedPaneUI.class.getName(), MTTabbedPaneUI.class);
  }

  private void replaceIcons() {
    final Icon collapsedIcon = getIcon(MTConfig.getInstance().getArrowsStyle().getCollapsedIcon());
    final Icon expandedIcon = getIcon(MTConfig.getInstance().getArrowsStyle().getExpandedIcon());

    UIManager.put("Tree.collapsedIcon", collapsedIcon);
    UIManager.put("Tree.expandedIcon", expandedIcon);
    UIManager.put("Menu.arrowIcon", collapsedIcon);
    //    UIManager.put("MenuItem.arrowIcon", collapsedIcon);
    UIManager.put("RadioButtonMenuItem.arrowIcon", collapsedIcon);
    UIManager.put("CheckBoxMenuItem.arrowIcon", collapsedIcon);

    UIManager.put("FileView.fileIcon", AllIcons.FileTypes.Unknown);
    UIManager.put("Table.ascendingSortIcon", AllIcons.General.SplitUp);
    UIManager.put("Table.descendingSortIcon", AllIcons.General.SplitDown);

    UIManager.put("TextField.darcula.searchWithHistory.icon", IconLoader.getIcon("/icons/darcula/searchWithHistory.png"));
    UIManager.put("TextField.darcula.search.icon", IconLoader.getIcon("/icons/darcula/search.png"));
    UIManager.put("TextField.darcula.clear.icon", IconLoader.getIcon("/icons/darcula/clear.png"));
  }

  private void modifyRegistry() {
    Registry.get("ide.balloon.shadow.size").setValue(0);
  }

  private Icon getIcon(final String icon) {
    if (icon == null) {
      return IconLoader.getTransparentIcon(AllIcons.Mac.Tree_white_down_arrow, 0);
    }
    return TintedIconsService.getIcon(icon + ".png");
  }
}
