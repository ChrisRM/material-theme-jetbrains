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

public final class MTPalenightTheme extends MTTheme implements LafTheme {
  public static final String BACKGROUND = "292D3E"; // 41, 45, 62
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String CARET = "FFCC00"; // 255, 204, 0
  public static final String BORDER = "202226"; // 32, 34, 38
  public static final String TEXT = "676E95";  // 103, 110, 149
  public static final String SELECTION_BACKGROUND = "676E95"; // 103, 110, 149
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "A6ACCD"; // 166, 172, 205
  public static final String SUB_LABEL = "676E95";  // 103, 110, 149
  public static final String DISABLED = "2f2e43";

  public static final String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static final String STATUS_LABEL = "676E95"; // 103, 110, 149
  public static final String INPUT_BORDER = "373B4D"; // 55, 59, 77

  public static final String BUTTON_BACKGROUND = "2D3144";
  public static final String BUTTON_FOREGROUND = "676E95"; // 103, 110, 149
  public static final String BUTTON_SELECTED = "32374D"; // 50, 55, 77

  public static final String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  public MTPalenightTheme() {
    super("mt.palenight", "Material Palenight", true, MTThemes.PALENIGHT);
  }

  @Override
  public String getDisabled() {
    return DISABLED;
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "676E9550";
  }

  @Override
  protected String getButtonHighlightColorString() {
    return "303146";
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
    return "2D3043";
  }

  @Override
  protected String getContrastColorString() {
    return "202331";
  }

  @Override
  protected String getDisabledColorString() {
    return "2f2e43";
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
    return "4E5579";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "303348";
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
        "CheckBox.darcula.inactiveFillColor"
    };
  }

  @Override
  protected String[] getSecondBorderResources() {
    return new String[]{
        "Table.gridColor",
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
        "ScrollBar.thumb",
        "MenuBar.darcula.borderColor",
        "MenuBar.darcula.borderShadowColor",
        "Separator.foreground",
        "TextField.separatorColorDisabled",
        "TextField.inactiveForeground",
        "PasswordField.inactiveForeground",
        "Button.mt.color1",
        "Button.mt.background",
        "MemoryIndicator.usedColor",
        "material.disabled",
        "material.mergeCommits"
    };
  }

  @Override
  protected String[] getSecondaryBackgroundResources() {
    return new String[]{
        "inactiveCaption",
        "List.background"
    };
  }

  @Override
  protected String[] getCaretResources() {
    return new String[]{
        "mt.palenight.caretForeground"
    };
  }

  @Override
  protected String[] getInactiveResources() {
    return new String[]{
        "mt.palenight.selectionBackgroundInactive",
        "mt.palenight.selectionInactiveBackground",
        "MenuItem.disabledForeground",
        "ComboBox.disabledForeground",
        "Button.disabledText"
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
        "Button.darcula.selectedButtonForeground"
    };
  }


  @Override
  protected String[] getSelectionBackgroundResources() {
    return new String[]{
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
        "ComboBox.disabledForeground"
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
        "mt.palenight.foreground",
        "mt.palenight.textForeground",
        "text",
        "textText",
        "textInactiveText",
        "Menu.foreground",
        "TitledBorder.titleColor"
    };
  }
}
