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

import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.MTThemes;

public final class MonokaiTheme extends MTTheme implements LafTheme {
  public static final String BACKGROUND = "2D2A2E";
  public static final String FOREGROUND = "C1C0C0";
  public static final String CARET = "FCFCFA";
  public static final String BORDER = "59575A";
  public static final String TEXT = "727072";
  public static final String SELECTION_BACKGROUND = "5B595C";
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "C1C0C0";
  public static final String SUB_LABEL = "403E41";
  public static final String DISABLED = "727072";
  public static final String SIDEBAR_HEADING = "CFD8DC";
  public static final String STATUS_LABEL = "727272";
  public static final String INPUT_BORDER = "59575A";
  public static final String BUTTON_BACKGROUND = "403E41";
  public static final String BUTTON_FOREGROUND = "C1C0C0";
  public static final String BUTTON_SELECTED = "59575A";

  public MonokaiTheme() {
    super("monokai", "Material Monokai Pro", true, MTThemes.MONOKAI);
  }

  @Override
  public String getSelectionBackground() {
    return MonokaiTheme.SELECTION_BACKGROUND;
  }

  @Override
  public String getDisabled() {
    return MonokaiTheme.DISABLED;
  }

  @Override
  protected String getNotificationsColorString() {
    return "363437";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "5B595C50";
  }

  @Override
  protected String getButtonHighlightColorString() {
    return "403E41";
  }

  @Override
  protected String getHighlightColorString() {
    return "59575A";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "59575A";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "5B595C";
  }

  @Override
  protected String getContrastColorString() {
    return "221F22";
  }

  @Override
  protected String getDisabledColorString() {
    return "727072";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "403E41";
  }

  @Override
  protected String getCaretColorString() {
    return "FCFCFA";
  }

  @Override
  protected String getInactiveColorString() {
    return "403E41";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "5B595C";
  }

  @Override
  protected String getTextColorString() {
    return "727072";
  }

  @Override
  protected String getForegroundColorString() {
    return "C1C0C0";
  }

  @Override
  protected String getBackgroundColorString() {
    return "2D2A2E";
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
        "TabbedPane.selected",
        "TabbedPane.selectHighlight",
        "CheckBox.darcula.disabledBorderColor1",
        "CheckBox.darcula.disabledBorderColor2",
        "Table.highlightOuter",
        "Table.highlightInner",
        "Table.shadowOuter",
        "Table.shadowInner"
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
        "material.contrast",
        "Table.focusCellBackground"
    };
  }

  @Override
  protected String[] getDisabledResources() {
    return new String[] {
        "MenuItem.disabledForeground",
        "ComboBox.disabledForeground",
        "Button.disabledText",
        "CheckBox.darcula.checkSignColorDisabled"
    };
  }

  @Override
  protected String[] getSecondaryBackgroundResources() {
    return new String[] {
        "inactiveCaption",
        "ScrollBar.thumb",
        "TextField.inactiveForeground",
        "PasswordField.inactiveForeground",
        "TextArea.selectionForeground",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "List.background",
        "Separator.foreground",
        "material.mergeCommits",
        "material.disabled"
    };
  }

  @Override
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
        "PasswordField.selectionBackground"
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
        "SearchEverywhere.shortcutForeground",
        "material.tagColor"
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
        "MenuBar.darcula.borderColor",
        "MenuBar.darcula.borderShadowColor",
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
        "Label.foreground",
        "EditorPane.inactiveForeground",
        "CheckBox.foreground",
        "ComboBox.foreground",
        "RadioButton.foreground",
        "ColorChooser.foreground",
        "MenuBar.foreground",
        "RadioButtonMenuItem.foreground",
        "CheckBoxMenuItem.foreground",
        "MenuItem.foreground",
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
        "Table.foreground",
        "TableHeader.foreground",
        "ToggleButton.foreground",
        "Table.sortIconColor",
        "material.branchColor",
        "TitledBorder.titleColor"
    };
  }
}
