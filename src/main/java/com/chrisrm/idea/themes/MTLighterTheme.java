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

public final class MTLighterTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "FAFAFA"; // 250, 250, 250
  public static final String FOREGROUND = "A7ADB0"; // 167, 173, 176
  public static final String TEXT = "A7ADB0"; // 167, 173, 176
  public static final String SELECTION_BACKGROUND = "546E7A"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "eae8e8";

  public MTLighterTheme() {
    super("mt.lighter", "Material Lighter", false);
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return MTLighterTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return MTLighterTheme.DISABLED;
  }

  @Override
  protected String getNotificationsColorString() {
    return "C3E88D";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "546E7A50";
  }

  @Override
  protected String getHighlightColorString() {
    return "425B67";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "d3e1e8";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "BDE3DF";
  }

  @Override
  protected String getContrastColorString() {
    return "F4F4F4";
  }

  @Override
  protected String getDisabledColorString() {
    return "D2D4D5";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "eae8e8";
  }

  @Override
  protected String getCaretColorString() {
    return "FFCC00";
  }

  @Override
  protected String getInactiveColorString() {
    return "FAFAFA";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "D2D4D5";
  }

  @Override
  protected String getTextColorString() {
    return "7E939E";
  }

  @Override
  protected String getForegroundColorString() {
    return "546E7A";
  }

  @Override
  protected String getBackgroundColorString() {
    return "FAFAFA";
  }

  @Override
  protected String[] getInactiveResources() {
    return new String[]{
        "Table.gridColor",
        "MenuBar.darcula.borderColor",
        "MenuBar.darcula.borderShadowColor",
        "CheckBox.darcula.disabledBorderColor1",
        "CheckBox.darcula.disabledBorderColor2"
    };
  }

  @Override
  protected String[] getSelectionForegroundResources() {
    return new String[]{
        "mt.lighter.selectionForeground",
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
        "mt.lighter.selectionBackgroundInactive",
        "mt.lighter.selectionInactiveBackground",
        "inactiveCaption",
        "Button.disabledText"
    };
  }

  @Override
  protected String[] getTextResources() {
    return new String[]{
        "Menu.acceleratorForeground",
        "MenuItem.acceleratorForeground",
        "material.tagColor",
        "material.primaryColor",
        "SearchEverywhere.shortcutForeground",
        "Tree.foreground"
    };
  }

  @Override
  protected String[] getBackgroundResources() {
    return new String[]{
        "mt.lighter.background",
        "mt.lighter.textBackground",
        "mt.lighter.inactiveBackground",
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
        "Desktop.background",
        "PopupMenu.background",
        "Separator.background",
        "MenuBar.background",
        "Separator.foreground",
        "TextField.background",
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
        "Button.mt.color1",
        "Button.mt.background",
        "ToolTip.background",
        "Spinner.background",
        "SplitPane.highlight",
        "SearchEverywhere.background",
        //        "Panel.background",
        "SidePanel.background",
        "DialogWrapper.southPanelDivider",
        "OnePixelDivider.background",
        "Dialog.titleColor",
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
        "OptionPane.background",
        "ToolWindow.header.background",
        "ToolWindow.header.closeButton.background",
        "material.tab.backgroundColor",
        "material.background"
    };
  }

  @Override
  protected String[] getForegroundResources() {
    return new String[]{
        "mt.lighter.foreground",
        "mt.lighter.textForeground",
        "mt.lighter.selectionForegroundInactive",
        "mt.lighter.selectionInactiveForeground",
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
        // wtf
    };
  }
}
