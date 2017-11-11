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

import com.chrisrm.idea.MTCustomThemeConfig;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.MTThemes;

public final class MTLightCustomTheme extends MTTheme implements LafTheme {

  public MTLightCustomTheme() {
    super("mt.light_custom", "Material Light Custom", false, MTThemes.LIGHT_CUSTOM);
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
        "CheckBox.darcula.inactiveFillColor"
    };
  }

  @Override
  protected String[] getSecondBorderResources() {
    return new String[] {
        "TabbedPane.highlight",
        "TabbedPane.selected",
        "TabbedPane.selectHighlight"
    };
  }

  @Override
  protected String[] getTableSelectedResources() {
    return new String[] {
        "ProgressBar.halfColor",
        "MemoryIndicator.unusedColor"
    };
  }

  @Override
  protected String[] getContrastResources() {
    return new String[] {
        "Table.stripedBackground",
        "ScrollBar.thumb",
        "EditorPane.background",
        "ToolBar.background",
        "Table.focusCellBackground",
        "material.contrast"
    };
  }

  @Override
  protected String[] getDisabledResources() {
    return new String[] {
    };
  }

  @Override
  protected String[] getSecondaryBackgroundResources() {
    return new String[] {
        "Separator.foreground",
        "TextField.separatorColorDisabled",
        "TextField.inactiveForeground",
        "PasswordField.inactiveForeground",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "Button.mt.selection.color1",
        "List.background",
        "material.disabled",
        "material.mergeCommits"
    };
  }

  @Override
  protected String[] getCaretResources() {
    return new String[] {
        "mt.light_custom.caretForeground"
    };
  }

  @Override
  protected String[] getInactiveResources() {
    return new String[] {
        "Table.gridColor",
        "MenuBar.darcula.borderColor",
        "MenuBar.darcula.borderShadowColor",
        "CheckBox.darcula.disabledBorderColor1",
        "CheckBox.darcula.disabledBorderColor2"
    };
  }

  @Override
  protected String[] getSelectionForegroundResources() {
    return new String[] {
        "mt.light_custom.selectionForeground",
        "Menu.selectionForeground",
        "Menu.acceleratorSelectionForeground",
        "MenuItem.selectionForeground",
        "MenuItem.acceleratorSelectionForeground",
        "Table.selectionForeground",
        "TextField.selectionForeground",
        "PasswordField.selectionForeground",
        "Button.mt.selectedForeground",
        "TextArea.selectionForeground",
        "List.selectionForeground",
        "ComboBox.selectionForeground",
        "FormattedTextField.selectionForeground",
        "CheckBoxMenuItem.selectionForeground",
        "TextPane.selectionForeground",
        "EditorPane.selectionForeground",
        "Tree.selectionForeground",
        "Button.darcula.selectedButtonForeground"
    };
  }

  @Override
  protected String[] getSelectionBackgroundResources() {
    return new String[] {
        "mt.light_custom.selectionBackgroundInactive",
        "mt.light_custom.selectionInactiveBackground",
        "inactiveCaption",
        "MenuItem.disabledForeground",
        "ComboBox.disabledForeground",
        "Button.disabledText"
    };
  }

  @Override
  protected String[] getTextResources() {
    return new String[] {
        "Menu.acceleratorForeground",
        "MenuItem.acceleratorForeground",
        "Tree.foreground"
    };
  }

  @Override
  protected String[] getBackgroundResources() {
    return new String[] {
        "mt.light_custom.background",
        "mt.light_custom.textBackground",
        "mt.light_custom.inactiveBackground",
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
        "Separator.background", "MenuBar.background",
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
        "Label.background",
        "Panel.background",
        "SidePanel.background",
        "DialogWrapper.southPanelDivider",
        "OnePixelDivider.background",
        "Dialog.titleColor",
        "RadioButton.background",
        "CheckBoxMenuItem.background",
        "MenuItem.background",
        "RadioButtonMenuItem.background",
        "CheckBox.background",
        "ColorChooser.background",
        "Slider.background",
        "TabbedPane.background",
        "Menu.background",
        "OptionPane.background",
        "material.tab.backgroundColor"
    };
  }

  @Override
  protected String[] getForegroundResources() {
    return new String[] {
        "mt.light_custom.foreground",
        "mt.light_custom.textForeground",
        "mt.light_custom.selectionForegroundInactive",
        "mt.light_custom.selectionInactiveForeground",
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
        "Panel.foreground",
        "Label.foreground",
        "TitledBorder.titleColor",
        "TextField.selectionBackground",
        "PasswordField.selectionBackground"
    };
  }

  @Override
  protected String getTreeSelectionColorString() {
    return MTCustomThemeConfig.getInstance().getTreeSelectionColorString();
  }

  @Override
  protected String getButtonHighlightColorString() {
    return MTCustomThemeConfig.getInstance().getButtonHighlightColorString();
  }

  @Override
  protected String getHighlightColorString() {
    return MTCustomThemeConfig.getInstance().getHighlightColorString();
  }

  @Override
  protected String getSecondBorderColorString() {
    return MTCustomThemeConfig.getInstance().getSecondBorderColorString();
  }

  @Override
  protected String getTableSelectedColorString() {
    return MTCustomThemeConfig.getInstance().getTableSelectedColorString();
  }

  @Override
  protected String getContrastColorString() {
    return MTCustomThemeConfig.getInstance().getContrastColorString();
  }

  @Override
  protected String getDisabledColorString() {
    return MTCustomThemeConfig.getInstance().getDisabledColorString();
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return MTCustomThemeConfig.getInstance().getSecondaryBackgroundColorString();
  }

  @Override
  protected String getCaretColorString() {
    return MTCustomThemeConfig.getInstance().getCaretColorString();
  }

  @Override
  protected String getInactiveColorString() {
    return MTCustomThemeConfig.getInstance().getInactiveColorString();
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return MTCustomThemeConfig.getInstance().getSelectionForegroundColorString();
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return MTCustomThemeConfig.getInstance().getSelectionBackgroundColorString();
  }

  @Override
  protected String getTextColorString() {
    return MTCustomThemeConfig.getInstance().getTextColorString();
  }

  @Override
  protected String getForegroundColorString() {
    return MTCustomThemeConfig.getInstance().getForegroundColorString();
  }

  @Override
  protected String getBackgroundColorString() {
    return MTCustomThemeConfig.getInstance().getBackgroundColorString();
  }

}
