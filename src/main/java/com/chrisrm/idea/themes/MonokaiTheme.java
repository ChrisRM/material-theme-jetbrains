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

public final class MonokaiTheme extends MTTheme implements LafTheme {
  public static final String BACKGROUND = "263238"; // 38, 50, 56
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String CARET = "FFCC00"; // 255, 204, 0
  public static final String BORDER = "222D33"; // 34, 45, 51
  public static final String TEXT = "607D8B"; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = "546E7A"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "B0BEC5"; // 176, 190, 197
  public static final String SUB_LABEL = "546E7A"; // 84, 110, 122
  public static final String DISABLED = "2E3C43"; // 65, 89, 103
  public static final String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static final String STATUS_LABEL = "78909C"; // 120, 144, 156
  public static final String INPUT_BORDER = "37474F"; //55, 71, 79
  public static final String BUTTON_BACKGROUND = "2C3C41"; // 44, 60, 65
  public static final String BUTTON_FOREGROUND = "607D8B"; // 96, 125, 139
  public static final String BUTTON_SELECTED = "314549"; // 49, 69, 73
  public static final String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  public MonokaiTheme() {
    super("monokai", "Material Monokai", true, MTThemes.MONOKAI);
  }

  @Override
  public String getDisabled() {
    return DISABLED;
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "49483E50";
  }

  @Override
  protected String getButtonHighlightColorString() {
    return "34332A";
  }

  @Override
  protected String getHighlightColorString() {
    return "49483E";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "49483E";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "49483E";
  }

  @Override
  protected String getContrastColorString() {
    return "181912";
  }

  @Override
  protected String getDisabledColorString() {
    return "888888";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "363731";
  }

  @Override
  protected String getCaretColorString() {
    return "F92672";
  }

  @Override
  protected String getInactiveColorString() {
    return "363731";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "49483E";
  }

  @Override
  protected String getTextColorString() {
    return "C5C8C6";
  }

  @Override
  protected String getForegroundColorString() {
    return "F8F8F2";
  }

  @Override
  protected String getBackgroundColorString() {
    return "272822";
  }

  @Override
  protected String[] getTreeSelectionResources() {
    return new String[] {
        "Tree.selectionBackground"
    };
  }

  @Override
  protected String[] getButtonHighlightResources() {
    return new String[] {
        "Button.mt.color2",
        "Button.mt.selection.color2"
    };
  }

  @Override
  protected String[] getHighlightResources() {
    return new String[] {
        "Focus.color",
        "TextField.separatorColor",
        "ProgressBar.halfColor",
        "MemoryIndicator.usedColor"
    };
  }

  @Override
  protected String[] getSecondBorderResources() {
    return new String[] {
        "MenuBar.darcula.borderColor",
        "MenuBar.darcula.borderShadowColor",
        "TabbedPane.selected",
        "TabbedPane.selectHighlight",
        "CheckBox.darcula.disabledBorderColor1",
        "CheckBox.darcula.disabledBorderColor2"
    };
  }

  @Override
  protected String[] getTableSelectedResources() {
    return new String[] {
        "Table.selectionBackground",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "Button.mt.selection.color1",
        "MemoryIndicator.unusedColor"
    };
  }

  @Override
  protected String[] getContrastResources() {
    return new String[] {
        "Table.stripedBackground",
        "material.contrast"
    };
  }

  @Override
  protected String[] getDisabledResources() {
    return new String[] {
        "MenuItem.disabledForeground",
        "Button.disabledText",
        "CheckBox.darcula.checkSignColorDisabled"
    };
  }

  @Override
  protected String[] getSecondaryBackgroundResources() {
    return new String[] {
        "inactiveCaption",
        "ScrollBar.thumb",
        "Separator.foreground",
        "TextField.inactiveForeground",
        "PasswordField.inactiveForeground",
        "TextArea.selectionForeground",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "List.background",
        "material.mergeCommits",
        "material.disabled"
    };
  }

  protected String[] getCaretResources() {
    return new String[] {
        "monokai.caretForeground"
    };
  }

  @Override
  protected String[] getInactiveResources() {
    return new String[] {
        "Button.mt.background",
        "Button.mt.color1",
    };
  }

  @Override
  protected String[] getSelectionForegroundResources() {
    return new String[] {
        "monokai.selectionForeground",
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
    return new String[] {
        "monokai.selectionBackgroundInactive",
        "monokai.selectionInactiveBackground",
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
    return new String[] {
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
        "material.tagColor",
        "Button.mt.foreground"
    };
  }

  @Override
  protected String[] getBackgroundResources() {
    return new String[] {
        "monokai.background",
        "monokai.textBackground",
        "monokai.inactiveBackground",
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
        "Desktop.background",
        "PopupMenu.background",
        "Separator.background",
        "MenuBar.background",
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
        "Tree.background",
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
    return new String[] {
        "monokai.foreground",
        "monokai.textForeground",
        "monokai.selectionForegroundInactive",
        "monokai.selectionInactiveForeground",
        "Menu.foreground",
        "MenuItem.foreground",
        "EditorPane.inactiveForeground",
        "Table.sortIconColor",
        "material.branchColor",
        "TitledBorder.titleColor"
    };
  }
}
