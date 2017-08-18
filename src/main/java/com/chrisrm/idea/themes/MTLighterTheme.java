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

public final class MTLighterTheme extends MTTheme implements LafTheme {
  public static final String BACKGROUND = "FAFAFA"; // 250, 250, 250
  public static final String FOREGROUND = "A7ADB0"; // 167, 173, 176
  public static final String CARET = "FFCC00"; // 255, 204, 0
  public static final String BORDER = "E6E6E6"; // 230, 230, 230
  public static final String TEXT = "A7ADB0"; // 167, 173, 176
  public static final String SELECTION_BACKGROUND = "546E7A"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "546E7A"; // 84, 110, 122
  public static final String SUB_LABEL = "B0BEC5"; // 176, 190, 197
  public static final String DISABLED = "eae8e8";

  public static final String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static final String STATUS_LABEL = "90A4AE"; // 144, 164, 174
  public static final String INPUT_BORDER = "CFD8DC"; // 207, 216, 220

  public static final String BUTTON_BACKGROUND = "EAF3F2"; // 234, 243, 242
  public static final String BUTTON_FOREGROUND = "676E95"; // 103, 110, 149
  public static final String BUTTON_SELECTED = "CCEAE7"; // 204, 234, 231

  public static final String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  public MTLighterTheme() {
    super("mt.lighter", "Material Theme - Lighter", false);
  }

  @Override
  public String getDisabled() {
    return DISABLED;
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
        "Panel.background",
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
        "mt.lighter.foreground",
        "mt.lighter.textForeground",
        "mt.lighter.selectionForegroundInactive",
        "mt.lighter.selectionInactiveForeground",
        "text",
        "textText",
        "textInactiveText",
        "controlText",
        "OptionPane.messageForeground",
        "Menu.foreground",
        "Menu.selectionBackground",
        "MenuItem.selectionBackground",
        "EditorPane.inactiveForeground",
        "Table.sortIconColor",
        "Table.selectionBackground",
        "TitledBorder.titleColor",
        "TextField.selectionBackground",
        "PasswordField.selectionBackground"
    };
  }
}
