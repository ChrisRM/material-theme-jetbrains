/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.chrisrm.idea.themes.MTCustomTheme;
import com.chrisrm.idea.themes.MTThemeable;
import com.chrisrm.idea.utils.MTUiUtils;
import com.chrisrm.idea.utils.PropertiesParser;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.laf.IntelliJLookAndFeelInfo;
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.IconUtil;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.io.Serializable;

public abstract class MTAbstractTheme implements Serializable, MTThemeable {
  private final String id;
  private final String editorColorsScheme;
  private final boolean dark;
  private String name;
  private String icon;

  @Override
  @Nullable
  public Icon getIcon() {
    return icon != null ? IconLoader.getIcon(icon) : IconUtil.getEmptyIcon(true);
  }

  @Override
  public void setIcon(final String icon) {
    this.icon = icon;
  }

  protected MTAbstractTheme(@NotNull final String id, final String editorColorsScheme, final boolean dark, final String name) {
    this(id, editorColorsScheme, dark);
    this.name = name;
  }

  protected MTAbstractTheme(@NotNull final String id, final String editorColorsScheme, final boolean dark) {
    this.id = id;
    this.editorColorsScheme = editorColorsScheme;
    this.dark = dark;
    name = id;
  }

  //region LafManager methods

  /**
   * Get the default selection background
   */
  @NotNull
  @Override
  public String getSelectionBackground() {
    return MTCustomTheme.SELECTION_BACKGROUND;
  }

  /**
   * Get disabled color
   */
  @NotNull
  @Override
  public String getDisabled() {
    return MTCustomTheme.DISABLED;
  }
  //endregion

  @Override
  public final void activate() {
    try {
      buildResources(getBackgroundResources(), getBackgroundColorString());
      buildResources(getForegroundResources(), getForegroundColorString());
      buildResources(getTextResources(), getTextColorString());
      buildResources(getSelectionBackgroundResources(), getSelectionBackgroundColorString());
      buildResources(getSelectionForegroundResources(), getSelectionForegroundColorString());
      buildResources(getButtonColorResource(), getButtonColorString());
      buildResources(getSecondaryBackgroundResources(), getSecondaryBackgroundColorString());
      buildResources(getDisabledResources(), getDisabledColorString());
      buildResources(getContrastResources(), getContrastColorString());
      buildResources(getTableSelectedResources(), getTableSelectedColorString());
      buildResources(getSecondBorderResources(), getSecondBorderColorString());
      buildResources(getHighlightResources(), getHighlightColorString());

      buildResources(getTreeSelectionResources(), getTreeSelectionColorString());
      buildResources(getNotificationsResources(), getNotificationsColorString());
      if (isDark()) {
        LafManager.getInstance().setCurrentLookAndFeel(new DarculaLookAndFeelInfo());
        UIManager.setLookAndFeel(new MTLaf(this));
      } else {
        LafManager.getInstance().setCurrentLookAndFeel(new IntelliJLookAndFeelInfo());
        UIManager.setLookAndFeel(new MTLightLaf(this));
      }
      JBColor.setDark(isDark());
      IconLoader.setUseDarkIcons(isDark());
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  /**
   * Whether the theme is a custom or external one
   */
  @Override
  public boolean isCustom() {
    return false;
  }

  /**
   * Iterate over theme resources and fill up the UIManager
   *
   * @param resources
   * @param color
   */
  private void buildResources(final String[] resources, final String color) {
    for (final String resource : resources) {
      UIManager.getDefaults().put(resource, PropertiesParser.parseColor(color));
    }
  }

  //region Theme resources and colors

  /**
   * Get resources using the background color
   */
  protected String[] getBackgroundResources() {
    return new String[] {
        //        "Menu.background",
        "window",
        "activeCaption",
        "control",
        "PopupMenu.translucentBackground",
        "EditorPane.inactiveBackground",
        "Table.background",
        "Table.gridColor",
        "Desktop.background",
        "PopupMenu.background",
        "Separator.background",
        "MenuBar.background",
        "MenuBar.disabledBackground",
        "MenuBar.shadow",
        "TabbedPane.background",
        "TabbedPane.borderColor",
        "TextField.background",
        "PasswordField.background",
        "FormattedTextField.background",
        "TextArea.background",
        "CheckBox.background",
        "OptionPane.background",
        "ColorChooser.background",
        "Slider.background",
        "TabbedPane.mt.tab.background",
        "TextPane.background",
        "RadioButton.background",
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
        "Dialog.titleColor",
        "SearchEverywhere.background",
        "CheckBoxMenuItem.background",
        "ToolWindow.header.background",
        "ToolWindow.header.closeButton.background",
        "material.tab.backgroundColor",
        "TextField.borderColor",
        "TextField.hoverBorderColor",
        "TextField.focusedBorderColor",
        "material.background"
    };
  }

  /**
   * Get the hex code for the background color
   */
  protected abstract String getBackgroundColorString();

  /**
   * Get resources using the foreground color
   */
  protected String[] getForegroundResources() {
    return new String[] {
        "text",
        "textText",
        "textInactiveText",
        "infoText",
        "controlText",
        "OptionPane.messageForeground",
        "Menu.foreground",
        "MenuItem.foreground",
        "Label.foreground",
        "Label.selectedDisabledForeground",
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
        "material.foreground",
        "CheckBox.darcula.borderColor1",
        "RadioButton.darcula.borderColor1",
        "TitledBorder.titleColor"
    };
  }

  /**
   * Get the hex code for the foreground color
   */
  protected abstract String getForegroundColorString();

  /**
   * Get resources using the label color
   */
  protected String[] getTextResources() {
    return new String[] {
        "Menu.acceleratorForeground",
        "text",
        "textText",
        "textInactiveText",
        "infoText",
        "controlText",
        "OptionPane.messageForeground",
        "MenuItem.acceleratorForeground",
        "TextField.separatorColorDisabled",
        "Table.sortIconColor",
        "material.tagColor",
        "material.primaryColor",
        "SearchEverywhere.shortcutForeground",
        "Button.foreground",
        "Button.mt.foreground",
        "Tree.foreground"
    };
  }

  /**
   * Get the hex code for the text color
   */
  protected abstract String getTextColorString();

  /**
   * Get resources using the selection background color
   */
  protected String[] getSelectionBackgroundResources() {
    return new String[] {
        "Menu.selectionBackground",
        "MenuItem.selectionBackground",
        "RadioButtonMenuItem.selectionBackground",
        "CheckBoxMenuItem.selectionBackground",
        "EditorPane.selectionBackground",
        "Autocomplete.selectionbackground",
        "TabbedPane.selectHighlight",
        "List.selectionBackground",
        "TabbedPane.selected",
    };
  }

  /**
   * Get the hex code for the selection background color
   */
  protected abstract String getSelectionBackgroundColorString();

  /**
   * Get resources using the selection foreground color
   */
  protected String[] getSelectionForegroundResources() {
    return new String[] {
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
        "TableHeader.focusCellForeground",
        "TabbedPane.selectedForeground",
        //        "Label.selectedForeground",
        "Button.darcula.selectedButtonForeground"
    };
  }

  /**
   * Get the hex code for the selection foreground color
   */
  protected abstract String getSelectionForegroundColorString();

  /**
   * Get resources using the button color
   */
  protected String[] getButtonColorResource() {
    return new String[] {
        "Button.mt.color1",
        "Button.mt.color2",
        "Button.mt.background",
        "Button.darcula.startColor",
        "Button.darcula.endColor",
        "Button.darcula.defaultStartColor",
        "Button.darcula.defaultEndColor",
        "Button.darcula.disabledBorderColor",
        "Button.darcula.borderColor",
        "Button.darcula.defaultBorderColor",
        "material.mergeCommits"
    };
  }

  /**
   * Get the hex code for the button color
   */
  protected abstract String getButtonColorString();

  /**
   * Get resources using the secondary background color
   */
  protected String[] getSecondaryBackgroundResources() {
    return new String[] {
        "inactiveCaption",
        "ToolWindow.header.active.background",
        "ToolWindow.header.border.background",
        "MemoryIndicator.unusedColor",
        "List.background"
    };
  }

  /**
   * Get the hex code for the secondary background color
   */
  protected abstract String getSecondaryBackgroundColorString();

  /**
   * Get resources using the disabled color
   */
  protected String[] getDisabledResources() {
    return new String[] {
        "MenuItem.disabledForeground",
        "ComboBox.disabledForeground",
        "TextField.inactiveForeground",
        "FormattedTextField.inactiveForeground",
        "PasswordField.inactiveForeground",
        "TextArea.inactiveForeground",
        "TextPane.inactiveForeground",
        "EditorPane.inactiveForeground",
        "Button.disabledText",
        "Menu.disabledForeground",
        "Label.disabledForeground",
        "RadioButtonMenuItem.disabledForeground",
        "CheckBoxMenuItem.disabledForeground",
        "CheckBox.darcula.checkSignColorDisabled"
    };
  }

  /**
   * Get the hex code for the disabled color
   */
  protected abstract String getDisabledColorString();

  /**
   * Get resources using the contrast color
   */
  protected String[] getContrastResources() {
    return new String[] {
        "Table.stripedBackground",
        "ToolWindow.header.tab.selected.background",
        "ToolWindow.header.tab.selected.active.background",
        "Table.focusCellBackground",
        "ScrollBar.thumb",
        "EditorPane.background",
        "ToolBar.background",
        "material.contrast"
    };
  }

  /**
   * Get the hex code for the contrast color
   */
  protected abstract String getContrastColorString();

  /**
   * Get resources using the table/button selection color
   */
  protected String[] getTableSelectedResources() {
    return new String[] {
        "Table.selectionBackground",
        "TextField.selectionBackground",
        "PasswordField.selectionBackground",
        "FormattedTextField.selectionBackground",
        "ComboBox.selectionBackground",
        "TextArea.selectionBackground",
        "TextPane.selectionBackground",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "Button.darcula.focusedBorderColor",
        "Button.darcula.defaultFocusedBorderColor",
        "Button.mt.selection.color2",
        "Button.mt.selection.color1"
    };
  }

  /**
   * Get the hex code for the table selected color
   */
  protected abstract String getTableSelectedColorString();

  /**
   * Get resources using the second border color
   */
  protected String[] getSecondBorderResources() {
    return new String[] {
        "CheckBox.darcula.disabledBorderColor1",
        "CheckBox.darcula.disabledBorderColor2",
        "Button.darcula.shadowColor",
        "Separator.foreground",
        "TabbedPane.highlight",
        "TabbedPane.darkShadow",
        "OnePixelDivider.background",
        "TabbedPane.shadow"
    };
  }

  /**
   * Get the hex code for the second border color
   */
  protected abstract String getSecondBorderColorString();

  /**
   * Get resources using the highlight color
   */
  protected String[] getHighlightResources() {
    return new String[] {
        "Focus.color",
        "TextField.separatorColor",
        "ProgressBar.halfColor",
        "CheckBox.darcula.inactiveFillColor",
        "TableHeader.borderColor",
        "MemoryIndicator.usedColor"
    };
  }

  /**
   * Get the hex code for the highlight color
   */
  protected abstract String getHighlightColorString();

  /**
   * Get resources using the tree selected row color
   */
  protected String[] getTreeSelectionResources() {
    return new String[] {
        "Tree.selectionBackground"
    };
  }

  /**
   * Get the hex code for the tree selection color
   */
  protected abstract String getTreeSelectionColorString();

  /**
   * Get notifications colors resources
   */
  protected String[] getNotificationsResources() {
    return new String[] {
        "Notifications.background",
        "Notifications.borderColor"
    };
  }

  /**
   * Get the hex code for the notifications color
   */
  protected abstract String getNotificationsColorString();
  //endregion

  //region MTThemeable methods

  /**
   * The theme name
   */
  @NotNull
  @Override
  public String getName() {
    return name;
  }

  /**
   * Set the theme name
   *
   * @param name
   */
  @Override
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Get the editor color scheme
   */
  @Override
  public String getEditorColorsScheme() {
    return editorColorsScheme;
  }

  /**
   * The theme id
   */
  @Override
  @NotNull
  public String getId() {
    return id;
  }

  /**
   * Whether the theme is a dark one
   */
  @Override
  public boolean isDark() {
    return dark;
  }
  //endregion

  //region Helper methods

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public Color getPrimaryColor() {
    final Color defaultValue = MTUiUtils.getColor(
        new ColorUIResource(0x263238),
        ObjectUtils.notNull(UIManager.getColor("darcula.background"), new ColorUIResource(0x3c3f41)),
        ObjectUtils.notNull(UIManager.getColor("intellijlaf.background"), new ColorUIResource(0xe8e8e8)));
    return ObjectUtils.notNull(UIManager.getColor("material.primaryColor"), defaultValue);
  }

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public Color getBackgroundColor() {
    final Color defaultValue = MTUiUtils.getColor(
        new ColorUIResource(0x263238),
        ObjectUtils.notNull(UIManager.getColor("darcula.background"), new ColorUIResource(0x3c3f41)),
        ObjectUtils.notNull(UIManager.getColor("intellijlaf.background"), new ColorUIResource(0xe8e8e8)));
    return ObjectUtils.notNull(UIManager.getColor("material.background"), defaultValue);
  }

  /**
   * Get foreground color custom property
   */
  @Override
  @NotNull
  public Color getForegroundColor() {
    final Color defaultValue = MTUiUtils.getColor(
        new ColorUIResource(0xB0BEC5),
        ObjectUtils.notNull(UIManager.getColor("darcula.foreground"), new ColorUIResource(0x3c3f41)),
        ObjectUtils.notNull(UIManager.getColor("intellijlaf.foreground"), new ColorUIResource(0xe8e8e8)));
    return ObjectUtils.notNull(UIManager.getColor("material.foreground"), defaultValue);
  }

  /**
   * Get border color custom property
   */
  @Override
  @NotNull
  public Color getBorderColor() {
    return ObjectUtils.notNull(UIManager.getColor("material.tab.borderColor"), new ColorUIResource(0x80cbc4));
  }

  /**
   * Get border thickness custom property
   */
  @Override
  public int getBorderThickness() {
    return ObjectUtils.notNull(UIManager.getInt("material.tab.borderThickness"), 2);
  }

  /**
   * Get contrast color custom property
   */
  @Override
  @NotNull
  public Color getContrastColor() {
    final Color defaultValue = MTUiUtils.getColor(
        new ColorUIResource(0x1E272C),
        ColorUtil.withAlpha(new Color(0x262626), .5),
        ColorUtil.withAlpha(new Color(0x262626), .2));
    return ObjectUtils.notNull(UIManager.getColor("material.contrast"), defaultValue);
  }
  //endregion

  /**
   * Get the theme id
   */
  @Override
  public String toString() {
    return getId();
  }

  @NotNull
  @Override
  public String getThemeId() {
    return getId();
  }
}
