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

package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTAbstractTheme;
import org.jetbrains.annotations.NotNull;

public final class MTPalenightTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "292D3E"; // 41, 45, 62
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String TEXT = "676E95";  // 103, 110, 149
  public static final String SELECTION_BACKGROUND = "676E95"; // 103, 110, 149
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "2f2e43";

  public MTPalenightTheme() {
    super("mt.palenight", "Material Palenight", true);
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return MTPalenightTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return MTPalenightTheme.DISABLED;
  }

  @Override
  protected String getNotificationsColorString() {
    return "202331";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "676E9550";
  }

  @Override
  protected String getHighlightColorString() {
    return "444267";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "2b2a3e";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "414863";
  }

  @Override
  protected String getContrastColorString() {
    return "202331";
  }

  @Override
  protected String getDisabledColorString() {
    return "515772";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "34324a";
  }

  @Override
  protected String getCaretColorString() {
    return "FFCC00";
  }

  @Override
  protected String getInactiveColorString() {
    return "303348";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "3C435E";
  }

  @Override
  protected String getTextColorString() {
    return "676E95";
  }

  @Override
  protected String getForegroundColorString() {
    return "A6ACCD";
  }

  @Override
  protected String getBackgroundColorString() {
    return "292D3E";
  }

  @Override
  protected String[] getInactiveResources() {
    return new String[]{
        "mt.palenight.selectionBackgroundInactive",
        "mt.palenight.selectionInactiveBackground",
        "MenuBar.darcula.borderColor",
        "MenuBar.darcula.borderShadowColor",
        "Separator.foreground",
        "Button.mt.color1",
        "Button.mt.background",
        "material.disabled",
        "material.mergeCommits"
    };
  }

  @Override
  protected String[] getSelectionForegroundResources() {
    return new String[]{
        "mt.palenight.selectionForeground",
        "Menu.selectionForeground",
        "Menu.acceleratorSelectionForeground",
        "MenuItem.selectionForeground",
        "MenuItem.acceleratorSelectionForeground",
        "Table.selectionForeground",
        "TextField.selectionForeground",
        "PasswordField.selectionForeground",
        "Button.mt.selectedForeground",
        "TextArea.selectionForeground",
        "Label.selectedForeground",
        "Button.darcula.selectedButtonForeground"
    };
  }

  @Override
  protected String[] getSelectionBackgroundResources() {
    return new String[]{
        "mt.palenight.selectionBackgroundInactive",
        "mt.palenight.selectionInactiveBackground",
        "Menu.selectionBackground",
        "MenuItem.selectionBackground",
        "Autocomplete.selectionbackground",
        "EditorPane.inactiveForeground",
        "ScrollBar.thumb",
        "Table.selectionBackground",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "Button.mt.selection.color1"
    };
  }

  @Override
  protected String[] getTextResources() {
    return new String[]{
        "mt.palenight.selectionForegroundInactive",
        "mt.palenight.selectionInactiveForeground",
        "infoText",
        "controlText",
        "OptionPane.messageForeground",
        "Menu.selectionBackground",
        "Menu.acceleratorForeground",
        "MenuItem.selectionBackground",
        "MenuItem.acceleratorForeground",
        "Autocomplete.selectionbackground",
        "Table.sortIconColor",
        "TextField.selectionBackground",
        "PasswordField.selectionBackground",
        "Button.foreground",
        "Button.mt.foreground",
        "Tree.foreground",
        "material.tagColor",
        "material.primaryColor",
        "SearchEverywhere.shortcutForeground"
    };
  }

  @Override
  protected String[] getBackgroundResources() {
    return new String[]{
        "mt.palenight.background",
        "mt.palenight.textBackground",
        "mt.palenight.inactiveBackground",
        "window",
        "activeCaption",
        "control",
        "PopupMenu.translucentBackground",
        "EditorPane.inactiveBackground",
        "Table.background",
        "MenuBar.disabledBackground",
        "MenuBar.shadow",
        "TabbedPane.highlight",
        "TabbedPane.darkShadow",
        "TabbedPane.shadow",
        "TabbedPane.borderColor",
        "TextField.background",
        "Desktop.background",
        "PopupMenu.background",
        "Separator.background",
        "MenuBar.background",
        "PasswordField.background",
        "FormattedTextField.background",
        "TextArea.background",
        "CheckBox.darcula.backgroundColor1",
        "CheckBox.darcula.backgroundColor2",
        "CheckBox.darcula.checkSignColor",
        "CheckBox.darcula.shadowColor",
        "CheckBox.darcula.shadowColorDisabled",
        "CheckBox.darcula.focusedArmed.backgroundColor1",
        "CheckBox.darcula.focusedArmed.backgroundColor2",
        "CheckBox.darcula.focused.backgroundColor1",
        "CheckBox.darcula.focused.backgroundColor2",
        "ComboBox.background",
        "ComboBox.disabledBackground",
        "RadioButton.darcula.selectionDisabledColor",
        "StatusBar.topColor",
        "StatusBar.top2Color",
        "StatusBar.bottomColor",
        "Button.background",
        "Button.darcula.color1",
        "Button.darcula.color2",
        "Button.darcula.disabledText.shadow",
        "ToolTip.background",
        "Spinner.background",
        "SplitPane.highlight",
        "SearchEverywhere.background",
        //        "Panel.background",
        "SidePanel.background",
        "DialogWrapper.southPanelDivider",
        "OnePixelDivider.background",
        "SearchEverywhere.background",
        "RadioButton.background",
        "CheckBoxMenuItem.background",
        //        "MenuItem.background",
        "RadioButtonMenuItem.background",
        "CheckBox.background",
        "ColorChooser.background",
        "Slider.background",
        "TabbedPane.background",
        //        "Menu.background",
        "ToolWindow.header.background",
        "ToolWindow.header.closeButton.background",
        "OptionPane.background",
        "Dialog.titleColor",
        "material.tab.backgroundColor",
        "material.background"
    };
  }

  @Override
  protected String[] getForegroundResources() {
    return new String[]{
        "mt.palenight.foreground",
        "mt.palenight.textForeground",
        "Label.foreground",
        "EditorPane.inactiveForeground",
        "CheckBox.foreground",
        "ComboBox.foreground",
        "RadioButton.foreground",
        "ColorChooser.foreground",
        "MenuBar.foreground",
        "RadioButtonMenuItem.foreground",
        "CheckBoxMenuItem.foreground",
        //        "OptionPane.foreground",
        "MenuItem.foreground",
        "PopupMenu.foreground",
        "Spinner.foreground",
        "TabbedPane.foreground",
        "TextField.foreground",
        "FormattedTextField.foreground",
        "PasswordField.foreground",
        "TextArea.foreground",
        "TextPane.foreground",
        "EditorPane.foreground",
        "ToolBar.foreground",
        "ToolTip.foreground",
        "List.foreground",
        "SearchEverywhere.foreground",
        "Label.foreground",
        "Label.disabledForeground",
        "Label.selectedDisabledForeground",
        "Table.foreground",
        "TableHeader.foreground",
        "ToggleButton.foreground",
        "Table.sortIconColor",
        "material.branchColor",
        "material.foreground",
        "TitledBorder.titleColor"
    };
  }
}
