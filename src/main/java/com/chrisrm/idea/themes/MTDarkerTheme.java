/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.MTThemes;

public final class MTDarkerTheme extends MTTheme implements LafTheme {
  public static final String BACKGROUND = "212121"; // 33, 33, 33
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String CARET = "FFCC00"; // 255, 204, 0
  public static final String BORDER = "1B1B1B"; // 27, 27, 27
  public static final String TEXT = "616161"; // 97, 97, 97
  public static final String SELECTION_BACKGROUND = "424242"; // 66, 66, 66
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "B0BEC5"; // 176, 190, 197
  public static final String SUB_LABEL = "616161"; // 97, 97, 97
  public static final String DISABLED = "323232"; // 65, 89, 103

  public static final String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static final String STATUS_LABEL = "616161"; // 97, 97, 97
  public static final String INPUT_BORDER = "484848"; //72, 72, 72

  public static final String BUTTON_BACKGROUND = "2B2B2B"; // 43, 43, 43
  public static final String BUTTON_FOREGROUND = "616161"; // 97, 97, 97
  public static final String BUTTON_SELECTED = "383838"; // 56,56,56

  public static final String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  public MTDarkerTheme() {
    super("mt.darker", "Material Darker", true, MTThemes.DARKER);
  }

  @Override
  public String getDisabled() {
    return DISABLED;
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "42424250";
  }

  @Override
  protected String getButtonHighlightColorString() {
    return "2E2E2E";
  }

  @Override
  protected String getHighlightColorString() {
    return "3F3F3F";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "2C2C2C";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "2A2A2A";
  }

  @Override
  protected String getContrastColorString() {
    return "1A1A1A";
  }

  @Override
  protected String getDisabledColorString() {
    return "474747";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "323232";
  }

  @Override
  protected String getCaretColorString() {
    return "FFCC00";
  }

  @Override
  protected String getInactiveColorString() {
    return "424242";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "424242";
  }

  @Override
  protected String getTextColorString() {
    return "616161";
  }

  @Override
  protected String getForegroundColorString() {
    return "B0BEC5";
  }

  @Override
  protected String getBackgroundColorString() {
    return "212121";
  }

  @Override
  protected String[] getTreeSelectionResources() {
    return new String[]{
        "Tree.selectionBackground"
    };
  }

  @Override
  protected String[] getButtonHighlightResources() {
    return new String[]{
        "Button.mt.color2",
        "Button.mt.selection.color2"
    };
  }

  @Override
  protected String[] getHighlightResources() {
    return new String[]{
        "Focus.color",
        "TextField.separatorColor",
        "ProgressBar.halfColor",
        "MemoryIndicator.usedColor"
    };
  }

  @Override
  protected String[] getSecondBorderResources() {
    return new String[]{
        "MenuBar.darcula.borderColor",
        "MenuBar.darcula.borderShadowColor",
        "TabbedPane.highlight",
        "TabbedPane.selected",
        "TabbedPane.selectHighlight",
        "CheckBox.darcula.disabledBorderColor1",
        "CheckBox.darcula.disabledBorderColor2"
    };
  }

  @Override
  protected String[] getTableSelectedResources() {
    return new String[]{
        "Table.selectionBackground",
        "Button.mt.background",
        "MemoryIndicator.unusedColor"
    };
  }

  @Override
  protected String[] getContrastResources() {
    return new String[]{
        "Table.stripedBackground",
        "material.contrast"
    };
  }

  @Override
  protected String[] getDisabledResources() {
    return new String[]{
        "MenuItem.disabledForeground",
        "Button.disabledText",
        "CheckBox.darcula.checkSignColorDisabled"
    };
  }

  @Override
  protected String[] getSecondaryBackgroundResources() {
    return new String[]{
        "inactiveCaption",
        "ScrollBar.thumb",
        "Separator.foreground",
        "TextField.inactiveForeground",
        "PasswordField.inactiveForeground",
        "TextArea.selectionForeground",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "Button.mt.color1",
        "Button.mt.selection.color1",
        "List.background",
        "material.disabled"
    };
  }

  @Override
  protected String[] getCaretResources() {
    return new String[]{
        "mt.darker.caretForeground"
    };
  }

  @Override
  protected String[] getInactiveResources() {
    return new String[]{

    };
  }

  @Override
  protected String[] getSelectionForegroundResources() {
    return new String[]{
        "mt.darker.selectionForeground",
        "Menu.selectionForeground",
        "Menu.acceleratorSelectionForeground",
        "MenuItem.selectionForeground",
        "MenuItem.acceleratorSelectionForeground",
        "Table.selectionForeground",
        "TextField.selectionForeground",
        "PasswordField.selectionForeground",
        "Button.mt.selectedForeground",
        "TextArea.selectionForeground",
        "Button.darcula.selectedButtonForeground"
    };
  }


  @Override
  protected String[] getSelectionBackgroundResources() {
    return new String[]{
        "mt.darker.selectionBackgroundInactive",
        "mt.darker.selectionInactiveBackground",
        "Menu.selectionBackground",
        "MenuItem.selectionBackground",
        "Autocomplete.selectionbackground",
        "TextField.selectionBackground",
        "PasswordField.selectionBackground",
        "ComboBox.disabledForeground"
    };
  }

  @Override
  protected String[] getTextResources() {
    return new String[]{
        "text",
        "textText",
        "textInactiveText",
        "infoText",
        "controlText",
        "OptionPane.messageForeground",
        "Menu.acceleratorForeground",
        "MenuItem.acceleratorForeground",
        "TextField.separatorColorDisabled",
        "Tree.foreground",
        "Button.foreground",
        "Button.mt.foreground"
    };
  }

  @Override
  protected String[] getBackgroundResources() {
    return new String[]{
        "mt.darker.background",
        "mt.darker.textBackground",
        "mt.darker.inactiveBackground",
        "window",
        "activeCaption",
        "control",
        "PopupMenu.translucentBackground",
        "EditorPane.inactiveBackground",
        "Table.background",
        "Table.gridColor",
        "MenuBar.disabledBackground",
        "MenuBar.shadow",
        "TabbedPane.highlight",
        "TabbedPane.darkShadow",
        "TabbedPane.shadow",
        "TabbedPane.borderColor",
        "TextField.background",
        "PasswordField.background",
        "Desktop.background",
        "PopupMenu.background",
        "Separator.background",
        "MenuBar.background",
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
        "ComboBox.arrowFillColor",
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
        //        "Panel.background",
        "SidePanel.background",
        "DialogWrapper.southPanelDivider",
        "OnePixelDivider.background",
        "Dialog.titleColor",
        "material.tab.backgroundColor"
    };
  }

  @Override
  protected String[] getForegroundResources() {
    return new String[]{
        "mt.darker.foreground",
        "mt.darker.textForeground",
        "mt.darker.selectionForegroundInactive",
        "mt.darker.selectionInactiveForeground",
        "Menu.foreground",
        "MenuItem.foreground",
        "EditorPane.inactiveForeground",
        "Table.sortIconColor",
        "TitledBorder.titleColor"
    };
  }
}
