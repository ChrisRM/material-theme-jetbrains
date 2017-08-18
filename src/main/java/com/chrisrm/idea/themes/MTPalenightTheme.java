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
    super("mt.palenight", "Material Theme - Palenight", true);
  }

  @Override
  public String getDisabled() {
    return DISABLED;
  }

  @Override
  protected String getForegroundColorString() {
    return "292D3E";
  }

  @Override
  protected String getBackgroundColorString() {
    return "A6ACCD";
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
        "mt.palenight.foreground",
        "mt.palenight.textForeground",
        "text",
        "textText",
        "textInactiveText",
        "Menu.foreground"
    };
  }
}
