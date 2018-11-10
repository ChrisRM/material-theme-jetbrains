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

package com.chrisrm.idea;

import com.chrisrm.idea.themes.models.MTSerializedTheme;
import com.chrisrm.idea.themes.models.MTThemeable;
import com.chrisrm.idea.utils.MTColorUtils;
import com.intellij.ide.ui.laf.IntelliJLookAndFeelInfo;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.IconUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.Serializable;

public abstract class MTAbstractTheme implements Serializable, MTThemeable, MTSerializedTheme {
  private static final int HC_FG_TONES = 4;
  private static final int HC_BG_TONES = 2;

  private String id;
  private String editorColorsScheme;
  private boolean dark;
  private String name;
  private String icon;

  private void init() {
    setId(getThemeId())
        .setIsDark(isThemeDark())
        .setEditorColorScheme(getThemeColorScheme())
        .setIcon(getThemeIcon())
        .setName(getThemeName());
  }

  protected MTAbstractTheme() {
    init();
  }

  /**
   * Get the theme id
   */
  @Override
  public final String toString() {
    return getId();
  }

  /**
   * Activate the theme by overriding UIManager with the theme resources and by setting the relevant Look and feel
   */
  @Override
  public final void activate() {
    try {
      if (dark) {
        LafManagerImpl.getTestInstance().setCurrentLookAndFeel(new DarculaLookAndFeelInfo());
      } else {
        LafManagerImpl.getTestInstance().setCurrentLookAndFeel(new IntelliJLookAndFeelInfo());
      }
      JBColor.setDark(dark);
      IconLoader.setUseDarkIcons(dark);
      buildResources(getBackgroundResources(), contrastifyBackground(getBackgroundColorString()));
      buildResources(getForegroundResources(), getForegroundColorString());
      buildResources(getTextResources(), contrastifyForeground(getTextColorString()));
      buildResources(getSelectionBackgroundResources(), getSelectionBackgroundColorString());
      buildResources(getSelectionForegroundResources(), getSelectionForegroundColorString());
      buildResources(getButtonColorResource(), getButtonColorString());
      buildResources(getSecondaryBackgroundResources(), getSecondaryBackgroundColorString());
      buildResources(getDisabledResources(), getDisabledColorString());
      buildResources(getContrastResources(), contrastifyBackground(getContrastColorString()));
      buildResources(getTableSelectedResources(), getTableSelectedColorString());
      buildResources(getSecondBorderResources(), getSecondBorderColorString());
      buildResources(getHighlightResources(), getHighlightColorString());

      buildResources(getTreeSelectionResources(), getTreeSelectionColorString());
      buildResources(getNotificationsResources(), getNotificationsColorString());
      buildNotificationsColors();

      // Apply theme accent color if said so
      if (MTConfig.getInstance().isOverrideAccentColor()) {
        MTConfig.getInstance().setAccentColor(getAccentColorString());
        MTThemeManager.getInstance().applyAccents(true);
      }

      if (isDark()) {
        UIManager.setLookAndFeel(new MTDarkLaf(this));
      } else {
        UIManager.setLookAndFeel(new MTLightLaf(this));
      }
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  //region Getters/Setters

  /**
   * The theme name
   */
  @NotNull
  @Override
  public final String getName() {
    return name;
  }

  /**
   * Set the theme name
   */
  @Override
  public final MTAbstractTheme setName(final String name) {
    this.name = name;
    return this;
  }

  /**
   * Get the editor color scheme
   */
  @Override
  public final String getEditorColorsScheme() {
    return editorColorsScheme;
  }

  @Override
  public final MTAbstractTheme setEditorColorScheme(final String editorColorsScheme) {
    this.editorColorsScheme = editorColorsScheme;
    return this;
  }

  /**
   * The theme id
   */
  @Override
  @NotNull
  public String getId() {
    return id;
  }

  @Override
  public final MTAbstractTheme setId(final String id) {
    this.id = id;
    return this;
  }

  /**
   * Whether the theme is a dark one
   */
  @Override
  public boolean isDark() {
    return dark;
  }

  @Override
  public final MTAbstractTheme setIsDark(final boolean dark) {
    this.dark = dark;
    return this;
  }

  @NotNull
  @Override
  public final Icon getIcon() {
    return icon != null ? IconLoader.getIcon(icon) : IconUtil.getEmptyIcon(true);
  }

  @Override
  public final MTAbstractTheme setIcon(final String icon) {
    this.icon = icon;
    return this;
  }

  /**
   * Whether the theme is a custom or external one
   */
  @Override
  public boolean isCustom() {
    return false;
  }
  //endregion

  //region Theme methods

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public final Color getBackgroundColor() {
    return ColorUtil.fromHex(getBackgroundColorString());
  }

  /**
   * Get contrast color custom property
   */
  @Override
  @NotNull
  public final Color getContrastColor() {
    return ColorUtil.fromHex(getContrastColorString());
  }

  /**
   * Get foreground color custom property
   */
  @Override
  @NotNull
  public final Color getForegroundColor() {
    return ColorUtil.fromHex(getForegroundColorString());
  }

  @Override
  public final Color getSelectionBackgroundColor() {
    return ColorUtil.fromHex(getSecondaryBackgroundColorString());

  }

  @Override
  public final Color getSelectionForegroundColor() {
    return ColorUtil.fromHex(getSelectionForegroundColorString());
  }

  @Override
  public final Color getExcludedColor() {
    return ColorUtil.fromHex(getExcludedColorString());
  }

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public final Color getPrimaryColor() {
    return ColorUtil.fromHex(getTextColorString());
  }

  private String contrastifyForeground(final String colorString) {
    final boolean isNotHighContrast = !MTConfig.getInstance().isHighContrast();
    if (isNotHighContrast) {
      return colorString;
    }

    return dark ?
           ColorUtil.toHex(ColorUtil.brighter(ColorUtil.fromHex(colorString), HC_FG_TONES)) :
           ColorUtil.toHex(ColorUtil.darker(ColorUtil.fromHex(colorString), HC_FG_TONES));
  }

  @SuppressWarnings("unused")
  private Color contrastifyForeground(final Color color) {
    final boolean isNotHighContrast = !MTConfig.getInstance().isHighContrast();
    if (isNotHighContrast) {
      return color;
    }

    return dark ?
           ColorUtil.brighter(color, HC_FG_TONES) :
           ColorUtil.darker(color, HC_FG_TONES);
  }

  private String contrastifyBackground(final String colorString) {
    final boolean isNotHighContrast = !MTConfig.getInstance().isHighContrast();
    if (isNotHighContrast) {
      return colorString;
    }

    return dark ?
           ColorUtil.toHex(ColorUtil.darker(ColorUtil.fromHex(colorString), HC_BG_TONES)) :
           ColorUtil.toHex(ColorUtil.brighter(ColorUtil.fromHex(colorString), HC_BG_TONES));
  }

  @SuppressWarnings("unused")
  private Color contrastifyBackground(final Color color) {
    final boolean isNotHighContrast = !MTConfig.getInstance().isHighContrast();
    if (isNotHighContrast) {
      return color;
    }

    return dark ?
           ColorUtil.darker(color, HC_BG_TONES) :
           ColorUtil.brighter(color, HC_BG_TONES);
  }

  //endregion

  //region MTThemeable methods

  /**
   * Get resources using the background color
   */
  protected static String[] getBackgroundResources() {
    return new String[]{
        //        "Menu.background",
        "activeCaption",
        "Borders.color",
        "Borders.ContrastBorderColor",
        "Button.background",
        "Button.darcula.color1",
        "Button.darcula.color2",
        "Button.darcula.disabledText.shadow",
        "CheckBox.background",
        "CheckBox.darcula.backgroundColor1",
        "CheckBox.darcula.backgroundColor2",
        "CheckBox.darcula.checkSignColor",
        "CheckBox.darcula.focused.backgroundColor1",
        "CheckBox.darcula.focused.backgroundColor2",
        "CheckBox.darcula.focusedArmed.backgroundColor1",
        "CheckBox.darcula.focusedArmed.backgroundColor2",
        "CheckBox.darcula.shadowColor",
        "CheckBox.darcula.shadowColorDisabled",
        "CheckBoxMenuItem.background",
        "CheckBoxMenuItem.disabledBackground",
        "ColorChooser.background",
        "ComboBox.arrowFillColor",
        "ComboBox.background",
        "ComboBox.darcula.arrowButtonBackground",
        "ComboBox.darcula.arrowButtonBackground",
        "ComboBox.darcula.nonEditableBackground",
        "ComboBox.disabledBackground",
        "control",
        "darcula.background",
        "Desktop.background",
        "Dialog.titleColor",
        "DialogWrapper.southPanelBackground",
        "DialogWrapper.southPanelDivider",
        "DragAndDrop.backgroundBorderColor",
        "DragAndDrop.backgroundColor",
        "Editor.background",
        "EditorPane.inactiveBackground",
        "EditorTabs.inactive.maskColor",
        "FlameGraph.JVMBackground",
        "FlameGraph.JVMFocusBackground",
        "FlameGraph.nativeBackground",
        "FlameGraph.nativeFocusBackground",
        "FlameGraph.parentBackground",
        "FlameGraph.parentFocusBackground",
        "FormattedTextField.background",
        "HeaderColor.active",
        "HelpTooltip.backgroundColor",
        "inactiveCaptionBorder",
        "intellijlaf.background",
        "InternalFrame.inactiveTitleBackground",
        "material.background",
        "material.tab.backgroundColor",
        "MenuBar.background",
        "MenuBar.borderColor",
        "MenuBar.disabledBackground",
        "MenuBar.highlight",
        "MenuBar.shadow",
        "NavBar.borderColor",
        "OptionPane.background",
        "PasswordField.background",
        "Plugins.SearchField.background",
        "Popup.Advertiser.background",
        "Popup.Border.inactiveColor",
        "Popup.Header.activeBackground",
        "Popup.inactiveBorderColor",
        "Popup.preferences.background",
        "Popup.preferences.borderColor",
        "PopupMenu.background",
        "PopupMenu.translucentBackground",
        "RadioButton.background",
        "RadioButton.darcula.selectionDisabledColor",
        "SearchEverywhere.background",
        "SearchEverywhere.Dialog.background",
        "SearchEverywhere.Header.background",
        "SearchEverywhere.SearchField.Border.color",
        "SearchEverywhere.SearchField.borderColor",
        "SidePanel.background",
        "Slider.background",
        "Spinner.background",
        "SplitPane.highlight",
        "StatusBar.background",
        "StatusBar.borderColor",
        "StatusBar.bottomColor",
        "StatusBar.top2Color",
        "StatusBar.topColor",
        "TabbedPane.background",
        "TabbedPane.borderColor",
        "TabbedPane.mt.tab.background",
        "Table.background",
        "Table.gridColor",
        "TextArea.background",
        "TextField.background",
        "TextField.borderColor",
        "TextField.focusedBorderColor",
        "TextField.hoverBorderColor",
        "TextPane.background",
        "ToolTip.actions.background",
        "ToolTip.Actions.background",
        "ToolTip.background",
        "tooltips.actions.settings.icon.background.color",
        "ToolWindow.header.background",
        "ToolWindow.header.closeButton.background",
        "ToolWindow.HeaderCloseButton.background",
        "ToolWindow.inactive.Header.background",
        "Tree.background",
        "VersionControl.FileHistory.Commit.otherBranchBackground",
        "VersionControl.FileHistory.Commit.selectedBranchBackground",
        "WelcomeScreen.background",
        "WelcomeScreen.borderColor",
        "WelcomeScreen.headerBackground",
        "window",
    };
  }

  /**
   * Get resources using the foreground color
   */
  protected static String[] getForegroundResources() {
    return new String[]{
        "CheckBox.darcula.borderColor1",
        "CheckBox.foreground",
        "CheckBoxMenuItem.foreground",
        "ColorChooser.foreground",
        "ComboBox.darcula.arrowButtonForeground",
        "ComboBox.foreground",
        "CompletionPopup.foreground",
        "darcula.foreground",
        "DragAndDrop.foregroundColor",
        "EditorPane.foreground",
        "EditorTabs.active.foreground",
        "FormattedTextField.foreground",
        "Git.Log.Ref.RemoteBranch",
        "Github.List.tallRow.foreground",
        "Group.separatorColor",
        "HelpTooltip.textColor",
        "Hg.Log.Ref.ClosedBranch",
        "intellijlaf.foreground",
        "Label.foreground",
        "Label.selectedDisabledForeground",
        "List.foreground",
        "material.branchColor",
        "material.foreground",
        "Menu.foreground",
        "MenuBar.foreground",
        "MenuItem.foreground",
        "MenuItem.foreground",
        "Notification.foreground",
        "Notification.MoreButton.foreground",
        "OptionPane.messageForeground",
        "ParameterInfo.foreground",
        "PasswordField.foreground",
        "Plugins.Button.installForeground",
        "Plugins.Button.updateForeground",
        "Plugins.SectionHeader.foreground",
        "Popup.Separator.foreground",
        "PopupMenu.foreground",
        "RadioButton.darcula.borderColor1",
        "RadioButton.foreground",
        "RadioButtonMenuItem.foreground",
        "SearchEverywhere.foreground",
        "Spinner.foreground",
        "TabbedPane.foreground",
        "Table.foreground",
        "Table.sortIconColor",
        "TableHeader.foreground",
        "TextArea.foreground",
        "TextField.foreground",
        "TextPane.foreground",
        "TitledBorder.titleColor",
        "ToggleButton.foreground",
        "ToolBar.foreground",
        "ToolTip.foreground",
        "tooltips.description.title.text.color",
        "WelcomeScreen.captionForeground",
        "WelcomeScreen.footerForeground",
        "WelcomeScreen.headerForeground",
    };
  }

  /**
   * Get resources using the label color
   */
  protected static String[] getTextResources() {
    return new String[]{
        "Button.foreground",
        "Button.mt.foreground",
        "CheckBoxMenuItem.acceleratorForeground",
        "CheckBoxMenuItem.acceleratorSelectionForeground",
        "CompletionPopup.grayedForeground",
        "CompletionPopup.grayForeground",
        "Component.grayForeground",
        "controlText",
        "Editor.shortcutForeground",
        "Git.Log.Ref.Other",
        "Git.Log.Ref.Tag",
        "Github.List.tallRow.secondary.foreground",
        "HelpTooltip.shortcutTextColor",
        "Hg.Log.Ref.LocalTag",
        "Hg.Log.Ref.MqTag",
        "Hg.Log.Ref.Tag",
        "inactiveCaptionText",
        "infoText",
        "InternalFrame.inactiveTitleForeground",
        "Label.grayForeground",
        "Label.textForeground",
        "material.primaryColor",
        "material.tagColor",
        "Menu.acceleratorForeground",
        "MenuItem.acceleratorForeground",
        "ParameterInfo.ContextHelp.foreground",
        "Plugins.Button.installFillForeground",
        "RadioButtonMenuItem.acceleratorForeground",
        "RadioButtonMenuItem.acceleratorSelectionForeground",
        "SearchEverywhere.shortcutForeground",
        "text",
        "TextField.separatorColorDisabled",
        "textInactiveText",
        "textText",
        "ToolBar.borderHandleColor",
        "ToolBar.floatingForeground",
        "ToolTip.Actions.grayForeground",
        "tooltips.actions.keymap.text.color",
        "Tree.foreground",
        "VersionControl.Log.Commit.unmatchedForeground",
    };
  }

  /**
   * Get resources using the selection background color
   */
  protected static String[] getSelectionBackgroundResources() {
    return new String[]{
        "Autocomplete.selectionBackground",
        "CheckBoxMenuItem.selectionBackground",
        "CompletionPopup.selectionBackground",
        "EditorPane.selectionBackground",
        "Github.List.tallRow.selectionBackground",
        "List.selectionBackground",
        "Menu.selectionBackground",
        "MenuItem.selectionBackground",
        "Plugins.selectionBackground",
        "RadioButtonMenuItem.selectionBackground",
        "TabbedPane.selected",
        "WelcomeScreen.Projects.selectionBackground",
        "material.selectionBackground"
    };
  }

  /**
   * Get resources using the selection foreground color
   */
  protected static String[] getSelectionForegroundResources() {
    return new String[]{
        "Button.darcula.selectedButtonForeground",
        "Button.default.foreground",
        "Button.mt.selectedForeground",
        "CheckBoxMenuItem.selectionForeground",
        "ComboBox.selectionForeground",
        "CompletionPopup.selectedForeground",
        "CompletionPopup.selectionForeground",
        "CompletionPopup.selectedGrayedForeground",
        "CompletionPopup.selectionGrayForeground",
        "EditorPane.selectionForeground",
        "FormattedTextField.selectionForeground",
        "Github.List.tallRow.selectionForeground",
        "Github.List.tallRow.selectionForeground.unfocused",
        "List.selectionForeground",
        "List.selectionInactiveForeground",
        "Menu.acceleratorSelectionForeground",
        "Menu.selectionForeground",
        "MenuItem.acceleratorSelectionForeground",
        "MenuItem.selectionForeground",
        "PasswordField.selectionForeground",
        "Plugins.selectionForeground",
        "Plugins.Tab.active.foreground",
        "SearchEverywhere.Tab.active.foreground",
        "SearchEverywhere.Tab.selected.foreground",
        "TabbedPane.selectedForeground",
        "Table.focusCellForeground",
        "Table.selectionForeground",
        "TableHeader.focusCellForeground",
        "TextArea.selectionForeground",
        "TextField.selectionForeground",
        "TextPane.selectionForeground",
        "ToolWindow.Button.selectedForeground",
        "Tree.selectionForeground",
        "Tree.selectionInactiveForeground",
        "VersionControl.Ref.foreground",
        "material.selectionForeground"
    };
  }

  /**
   * Get resources using the button color
   */
  protected static String[] getButtonColorResource() {
    return new String[]{
        "Button.darcula.borderColor",
        "Button.darcula.defaultBorderColor",
        "Button.darcula.defaultEndColor",
        "Button.darcula.defaultOutlineColor",
        "Button.darcula.defaultStartColor",
        "Button.darcula.disabledBorderColor",
        "Button.darcula.endColor",
        "Button.darcula.outlineColor",
        "Button.darcula.smallComboButtonBackground",
        "Button.darcula.startColor",
        "Button.default.borderColor",
        "Button.default.startBackground",
        "Button.default.endBackground",
        "Button.endBackground",
        "Button.mt.background",
        "Button.mt.color1",
        "Button.mt.color2",
        "Button.startBackground",
        "ComboBox.buttonBackground",
        "ComboBox.darcula.editable.arrowButtonBackground",
        "Component.borderColor",
        "material.mergeCommits",
        "Notification.MoreButton.background",
        "Notification.MoreButton.innerBorderColor",
        "Outline.color",
        "Plugins.Button.installBackground",
        "Plugins.Button.installBorderColor",
        "Plugins.Button.installFillBackground",
        "Plugins.Button.updateBackground",
        "Plugins.Button.updateBorderColor",
        "ToolBar.comboBoxButtonBackground",
        "WelcomeScreen.groupIconBorderColor",
    };
  }

  /**
   * Get resources using the secondary background color
   */
  protected static String[] getSecondaryBackgroundResources() {
    return new String[]{
        "CompletionPopup.background",
        "EditorTabs.borderColor",
        "inactiveCaption",
        "List.background",
        "MemoryIndicator.unusedColor",
        "Menu.borderColor",
        "ParameterInfo.background",
        "ParameterInfo.borderColor",
        "Plugins.SectionHeader.background",
        "Plugins.background",
        "Popup.separatorColor",
        "ProgressBar.trackColor",
        "Separator.background",
        "Separator.foreground",
        "Slider.tickColor",
        "ToolWindow.active.Header.background",
        "ToolWindow.header.active.background",
        "ToolWindow.header.border.background",
        "ToolWindow.Header.borderColor",
        "VersionControl.Log.Commit.currentBranchBackground",
        "WelcomeScreen.Projects.background",
        "WelcomeScreen.Projects.selectionInactiveBackground"
    };
  }

  /**
   * Get resources using the disabled color
   */
  protected static String[] getDisabledResources() {
    return new String[]{
        "Button.disabledText",
        "CheckBox.darcula.checkSignColorDisabled",
        "CheckBox.darcula.disabledBorderColor1",
        "CheckBox.darcula.disabledBorderColor2",
        "CheckBox.disabledText",
        "CheckBoxMenuItem.disabledForeground",
        "ComboBox.darcula.arrowButtonDisabledForeground",
        "ComboBox.disabledForeground",
        "Component.disabledBorderColor",
        "EditorPane.inactiveForeground",
        "FormattedTextField.inactiveForeground",
        "Label.disabledForeground",
        "Label.disabledShadow",
        "Menu.disabledForeground",
        "MenuBar.disabledForeground",
        "MenuItem.disabledForeground",
        "Outline.disabledColor",
        "ParameterInfo.disabledColor",
        "PasswordField.inactiveForeground",
        "Plugins.disabledForeground",
        "RadioButton.disabledText",
        "RadioButtonMenuItem.disabledForeground",
        "SearchEverywhere.SearchField.grayForeground",
        "TabbedPane.disabledForeground",
        "TabbedPane.disabledText",
        "TabbedPane.disabledUnderlineColor",
        "TabbedPane.selectedDisabledColor",
        "TextArea.inactiveForeground",
        "TextField.inactiveForeground",
        "TextPane.inactiveForeground",
        "ToggleButton.disabledText"
    };
  }

  /**
   * Get resources using the contrast color
   */
  protected static String[] getContrastResources() {
    return new String[]{
        "EditorPane.background",
        "HeaderColor.inactive",
        "material.contrast",
        "Popup.Border.color",
        "Popup.borderColor",
        "Popup.Header.inactiveBackground",
        "Popup.Toolbar.background",
        "Popup.Toolbar.Border.color",
        "Popup.Toolbar.borderColor",
        "ScrollBar.thumb",
        "SearchEverywhere.Advertiser.background",
        "SearchEverywhere.SearchField.background",
        "Table.stripeColor",
        "Table.stripedBackground",
        "TitlePane.background",
        "ToolBar.background",
        "ToolWindow.Button.selectedBackground",
        "ToolWindow.header.tab.selected.active.background",
        "ToolWindow.header.tab.selected.background",
        "ToolWindow.inactive.HeaderTab.background",
        "ToolWindow.active.HeaderTab.background",
        "WelcomeScreen.captionBackground",
        "WelcomeScreen.footerBackground"
    };
  }

  /**
   * Get resources using the table/button selection color
   */
  protected static String[] getTableSelectedResources() {
    return new String[]{
        "Button.darcula.defaultFocusedBorderColor",
        "Button.darcula.focusedBorderColor",
        "Button.darcula.selection.color1",
        "Button.darcula.selection.color2",
        "Button.mt.selection.color1",
        "Button.mt.selection.color2",
        "ComboBox.selectionBackground",
        "EditorTabs.active.background",
        "FormattedTextField.selectionBackground",
        "ParameterInfo.borderColor",
        "PasswordField.selectionBackground",
        "Plugins.Tab.active.background",
        "Plugins.Tab.hover.background",
        "Table.focusCellBackground",
        "Table.selectionBackground",
        "TextArea.selectionBackground",
        "TextField.selectionBackground",
        "TextPane.selectionBackground",
        "ToolWindow.Button.hoverBackground"
    };
  }

  /**
   * Get resources using the second border color
   */
  protected static String[] getSecondBorderResources() {
    return new String[]{
        "Borders.color",
        "Button.darcula.disabledOutlineColor",
        "Button.darcula.shadowColor",
        "Button.disabledBorderColor",
        "Button.shadowColor",
        "HelpTooltip.borderColor",
        "Menu.separatorColor",
        "OnePixelDivider.background",
        "Plugins.SearchField.borderColor",
        "Popup.Separator.color",
        "SearchEverywhere.List.Separator.Color",
        "SearchEverywhere.List.Separator.foreground",
        "SearchEverywhere.List.separatorColor",
        "TabbedPane.darkShadow",
        "TabbedPane.highlight",
        "TabbedPane.shadow",
        "WelcomeScreen.separatorColor",
        "windowBorder"
    };
  }

  /**
   * Get resources using the highlight color
   */
  protected static String[] getHighlightResources() {
    return new String[]{
        "ActionButton.pressedBackground",
        "ActionButton.pressedBorderColor",
        "Autocomplete.selectionUnfocus",
        "CheckBox.darcula.inactiveFillColor",
        "CompletionPopup.selectionInactiveBackground",
        "Component.focusedBorderColor",
        "DebuggerTabs.active.background",
        "Focus.color",
        "Github.List.tallRow.selectionBackground.unfocused",
        "MemoryIndicator.usedColor",
        "Outline.focusedColor",
        "Plugins.Button.installFocusedBackground",
        "Plugins.eapTagBackground",
        "Plugins.tagBackground",
        "ProgressBar.halfColor",
        "ProgressBar.selectionBackground",
        "SearchEverywhere.Tab.active.background",
        "SearchEverywhere.Tab.selected.background",
        "TabbedPane.contentAreaColor",
        "TabbedPane.hoverColor",
        "TabbedPane.selectedColor",
        "TabbedPane.selectHighlight",
        "TabbedPane.underlineColor",
        "TableHeader.borderColor",
        "TextField.separatorColor",
        "VersionControl.Ref.backgroundBase",
    };
  }

  /**
   * Get resources using the tree selected row color
   */
  protected static String[] getTreeSelectionResources() {
    return new String[]{
        "CompletionPopup.nonFocusedState",
        "List.selectionInactiveBackground",
        "Table.selectionInactiveBackground",
        "Tree.selectionBackground",
        "Tree.selectionInactiveBackground"
    };
  }

  /**
   * Get notifications colors resources
   */
  protected static String[] getNotificationsResources() {
    return new String[]{
        "Notification.background",
        "Notification.borderColor",
        "Notifications.background",
        "Notifications.borderColor",
        "ValidationTooltip.errorBackgroundColor",
        "ValidationTooltip.errorBackground",
        "ValidationTooltip.errorBorderColor",
        "ValidationTooltip.warningBackgroundColor",
        "ValidationTooltip.warningBackground",
        "ValidationTooltip.warningBorderColor",
    };
  }
  //endregion

  /**
   * Iterate over theme resources and fill up the UIManager
   *
   * @param resources
   * @param color
   */
  private static void buildResources(final String[] resources, final String color) {
    for (final String resource : resources) {
      UIManager.getDefaults().put(resource, MTColorUtils.parseColor(color));
    }
  }

  private static void buildNotificationsColors() {
    final JBColor errorColor = new JBColor(new ColorUIResource(0xef5350), new ColorUIResource(0xb71c1c));
    UIManager.put("Notification.ToolWindowError.background", errorColor);
    UIManager.put("Notification.ToolWindowError.borderColor", errorColor);

    final JBColor warnColor = new JBColor(new ColorUIResource(0xFFD54F), new ColorUIResource(0x5D4037));
    UIManager.put("Notification.ToolWindowWarning.background", warnColor);
    UIManager.put("Notification.ToolWindowWarning.borderColor", warnColor);

    final JBColor infoColor = new JBColor(new ColorUIResource(0x66BB6A), new ColorUIResource(0x1B5E20));
    UIManager.put("Notification.ToolWindowInfo.borderColor", infoColor);
    UIManager.put("Notification.ToolWindowInfo.background", infoColor);
  }
}
