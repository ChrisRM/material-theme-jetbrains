/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea.themes.lists;

import com.google.common.collect.Sets;
import org.jetbrains.annotations.NonNls;

import java.util.Collections;
import java.util.Set;

public enum MTThemeResources {
  THEMEY;

  /**
   * Get resources using the background color
   */
  @NonNls
  public static Set<String> getBackgroundResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "activeCaption", // deprecated
        "Borders.ContrastBorderColor",
        "Button.darcula.disabledText.shadow", // deprecated
        "Button.default.shadowColor",
        "CheckBox.background",
        "CheckBox.darcula.backgroundColor1", //deprecated
        "CheckBox.darcula.backgroundColor2", // deprecated
        "CheckBox.darcula.checkSignColor", // deprecated
        "CheckBox.darcula.checkSignColor.selected", // deprecated
        "CheckBox.darcula.focused.backgroundColor1", // deprecated
        "CheckBox.darcula.focused.backgroundColor2", // deprecated
        "CheckBox.darcula.focusedArmed.backgroundColor1", // deprecated
        "CheckBox.darcula.focusedArmed.backgroundColor2", // deprecated
        "CheckBox.darcula.shadowColor", // deprecated
        "CheckBox.darcula.shadowColorDisabled", // deprecated
        "CheckBoxMenuItem.background",
        "CheckBoxMenuItem.disabledBackground",
        "ColorChooser.background",
        "ComboBox.ArrowButton.nonEditableBackground",
        "ComboBox.arrowFillColor",
        "ComboBox.background",
        "ComboBox.darcula.arrowButtonBackground", // deprecated
        "ComboBox.darcula.disabledArrowButtonBackground", // deprecated
        "ComboBox.darcula.editable.arrowButtonBackground", // deprecated
        "ComboBox.darcula.nonEditableBackground", // deprecated
        "ComboBox.disabledBackground",
        "ComboBox.nonEditableBackground",
        "control",
        "darcula.background",
        "DebuggerPopup.borderColor",
        "DefaultTabs.background",
        "DefaultTabs.borderColor",
        "Desktop.background",
        "Dialog.titleColor", // deprecated
        "DialogWrapper.southPanelBackground",
        "DialogWrapper.southPanelDivider",
        "DragAndDrop.areaBorderColor",
        "DragAndDrop.backgroundBorderColor", // deprecated
        "DragAndDrop.backgroundColor", //deprecated
        "DragAndDrop.areaBackground",
        "Editor.background",
        "EditorGroupsTabs.background",
        "EditorPane.inactiveBackground",
        "EditorTabs.background",
        //            "EditorTabs.inactiveColoredFileBackground",
        "EditorTabs.inactive.maskColor", // deprecated
        "EditorTabs.inactiveMaskColor",
        "FormattedTextField.background",
        "GutterTooltip.borderColor", // deprecated
        "GutterTooltip.lineSeparatorColor",
        "HeaderColor.active", // deprecated
        "HelpTooltip.background",
        "HelpTooltip.backgroundColor", // deprecated
        "inactiveCaptionBorder",
        "intellijlaf.background", // deprecated
        "InplaceRefactoringPopup.borderColor",
        "InternalFrame.inactiveTitleBackground",
        "material.background",
        "material.tab.backgroundColor",
        "MenuBar.background",
        "MenuBar.borderColor",
        "MenuBar.disabledBackground",
        "MenuBar.highlight",
        "MenuBar.shadow",
        "NavBar.borderColor",
        "NewClass.Panel.background",
        "OptionPane.background",
        "PasswordField.background",
        "Plugins.background",
        "Plugins.SearchField.background",
        "Popup.Advertiser.background",
        "Popup.Border.inactiveColor", // deprecated
        "Popup.Header.activeBackground",
        "Popup.inactiveBorderColor",
        "Popup.preferences.background", // deprecated
        "Popup.preferences.borderColor", // deprecated
        "Popup.Toolbar.borderColor",
        "PopupMenu.background",
        "PopupMenu.translucentBackground",
        "RadioButton.background",
        "RadioButton.darcula.selectionDisabledColor", // deprecated
        "RadioButton.selectionDisabledColor", // deprecated
        "ScrollBar.background",
        "SearchEverywhere.background", //deprecated
        "SearchEverywhere.Dialog.background", //deprecated
        "SearchEverywhere.Header.background",
        "SearchEverywhere.SearchField.Border.color", //deprecated
        "SearchEverywhere.SearchField.borderColor",
        "SidePanel.background",
        "Slider.background",
        "Spinner.background",
        "SplitPane.highlight",
        "StatusBar.background", //deprecated
        "StatusBar.borderColor",
        "StatusBar.bottomColor", //deprecated
        "StatusBar.hoverBackground",
        "StatusBar.top2Color", //deprecated
        "StatusBar.topColor", //deprecated
        "TabbedPane.background",
        "TabbedPane.borderColor", // deprecated
        "TabbedPane.mt.tab.background",
        "Table.background",
        "Table.gridColor",
        "TableHeader.background",
        "TextField.background",
        "TextField.borderColor", // deprecated
        "TextField.focusedBorderColor", // deprecated
        "TextField.hoverBorderColor", //deprecated
        "TextField.separatorColorDisabled", // deprecated
        "TextPane.background",
        "TitlePane.inactiveBackground",
        "ToolWindow.HeaderTab.underlinedTabBackground",
        "ToolTip.actions.background", // deprecated
        "ToolTip.Actions.background",
        "ToolTip.background",
        "tooltips.actions.settings.icon.background.color", // deprecated
        "ToolWindow.header.background", // deprecated
        "ToolWindow.header.closeButton.background", // deprecated
        "ToolWindow.Header.inactiveBackground",
        "ToolWindow.HeaderCloseButton.background",
        "ToolWindow.inactive.Header.background", // deprecated
        "Tree.background",
        "UiDesigner.Panel.background",
        "UiDesigner.Preview.background",
        "VersionControl.FileHistory.Commit.otherBranchBackground", // deprecated
        "VersionControl.FileHistory.Commit.selectedBranchBackground",
        "WelcomeScreen.background",
        "WelcomeScreen.borderColor",
        "WelcomeScreen.headerBackground",
        "window"
      ));
  }

  /**
   * Get resources using the foreground color
   */
  public static Set<String> getForegroundResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "CheckBox.darcula.borderColor1", // deprecated
        "CheckBox.foreground",
        "CheckBoxMenuItem.foreground",
        "ColorChooser.foreground",
        "ComboBox.darcula.arrowButtonForeground", // deprecated
        "ComboBox.ArrowButton.iconColor",
        "ComboBox.foreground",
        "CompletionPopup.foreground",
        "darcula.foreground", // deprecated
        "DragAndDrop.areaForeground",
        "DragAndDrop.foregroundColor", // deprecated
        "Editor.foreground",
        "EditorPane.foreground",
        "EditorGroupsTabs.underlinedTabForeground",
        "EditorTabs.active.foreground", // deprecated
        "EditorTabs.selectedForeground",
        "FormattedTextField.foreground",
        "Git.Log.Ref.RemoteBranch", // deprecated
        "Github.List.tallRow.foreground", // deprecated
        "GutterTooltip.infoForeground",
        "Group.separatorColor",
        "HelpTooltip.foreground",
        "HelpTooltip.textColor", // deprecated
        "Hg.Log.Ref.ClosedBranch", //deprecated
        "intellijlaf.foreground", // deprecated
        "InternalFrame.activeTitleForeground",
        "Label.foreground",
        "Label.selectedDisabledForeground", // deprecated
        "List.foreground",
        "material.branchColor",
        "material.foreground",
        "Menu.foreground",
        "MenuBar.foreground",
        "MenuItem.foreground",
        "NavBar.arrowColor",
        "Notification.foreground",
        "Notification.MoreButton.foreground",
        "Notification.ToolWindow.errorForeground",
        "Notification.ToolWindow.infoForeground", // deprecated
        "Notification.ToolWindow.informativeForeground",
        "Notification.ToolWindow.warningForeground",
        "Notification.ToolWindowError.foreground", // deprecated
        "Notification.ToolWindowInfo.foreground", // deprecated
        "Notification.ToolWindowWarning.foreground", // deprecated
        "OptionPane.messageForeground",
        "ParameterInfo.foreground",
        "PasswordField.foreground",
        "Plugins.Button.installForeground",
        "Plugins.Button.updateForeground",
        "Plugins.SectionHeader.foreground",
        "Popup.separatorForeground",
        "Popup.Separator.foreground", // deprecated
        "PopupMenu.foreground",
        "RadioButton.darcula.borderColor1", // deprecated
        "RadioButton.foreground",
        "RadioButtonMenuItem.foreground",
        "SearchEverywhere.foreground", // deprecated
        "SpeedSearch.foreground",
        "SpeedSearch.errorForeground",
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
        "ToggleButton.off.foreground", // deprecated
        "ToggleButton.off.background", // deprecated
        "ToggleButton.offBackground",
        "ToggleButton.offForeground",
        "ToolBar.foreground",
        "ToolTip.foreground",
        "tooltips.description.title.text.color", // deprecated
        "VersionControl.GitLog.remoteBranchIconColor",
        "VersionControl.HgLog.closedBranchIconColor",
        "WelcomeScreen.captionForeground",
        "WelcomeScreen.footerForeground",
        "WelcomeScreen.headerForeground"
      ));
  }

  /**
   * Get resources using the label color
   */
  public static Set<String> getTextResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Button.foreground",
        "Button.mt.foreground",
        "CheckBoxMenuItem.acceleratorForeground",
        "CheckBoxMenuItem.acceleratorSelectionForeground",
        "CompletionPopup.grayedForeground", //deprecated
        "CompletionPopup.grayForeground", //deprcated
        "CompletionPopup.infoForeground",
        "CompletionPopup.selectionInactiveForeground", // deprecated
        "CompletionPopup.selectionInactiveInfoForeground",
        "Component.infoForeground",
        "Component.grayForeground", // deprecated
        "controlText",
        "Editor.shortcutForeground",
        "Git.Log.Ref.Other", //deprecated
        "Git.Log.Ref.Tag", // deprecated
        "Github.List.tallRow.secondary.foreground",
        "HelpTooltip.infoForeground",
        "HelpTooltip.shortcutForeground",
        "HelpTooltip.shortcutTextColor", // deprecated
        "Hg.Log.Ref.LocalTag", //deprecated
        "Hg.Log.Ref.MqTag", //deprecated
        "Hg.Log.Ref.Tag", //deprecated
        "inactiveCaptionText", // deprecated
        "infoText", // deprecated
        "InternalFrame.inactiveTitleForeground", // deprecated
        "Label.grayForeground", // deprecated
        "Label.infoForeground",
        "Label.textForeground", // deprecated
        "Link.secondaryForeground",
        "material.primaryColor",
        "material.tagColor",
        "Menu.acceleratorForeground",
        "MenuItem.acceleratorForeground",
        "ParameterInfo.ContextHelp.foreground", // deprecated
        "ParameterInfo.infoForeground",
        "Plugins.Button.installFillForeground",
        "RadioButtonMenuItem.acceleratorForeground",
        "RadioButtonMenuItem.acceleratorSelectionForeground",
        "SearchEverywhere.shortcutForeground", // deprecated
        "SearchEverywhere.List.separatorForeground",
        "Table.lightSelectionInactiveForeground",
        "text",
        "textInactiveText",
        "textText",
        "TitlePane.infoForeground",
        "ToolBar.borderHandleColor",
        "ToolBar.floatingForeground",
        "ToolTip.Actions.grayForeground", // deprecated
        "ToolTip.Actions.infoForeground",
        "ToolTip.infoForeground",
        "tooltips.actions.keymap.text.color", //deprecated
        "Tree.foreground",
        "VersionControl.GitLog.otherIconColor",
        "VersionControl.GitLog.tagIconColor",
        "VersionControl.HgLog.localTagIconColor",
        "VersionControl.HgLog.mqTagIconColor",
        "VersionControl.HgLog.tagIconColor",
        "VersionControl.HgLog.tipIconColor",
        "VersionControl.Log.Commit.unmatchedForeground"
      ));
  }

  /**
   * Get resources using the selection background color
   */
  public static Set<String> getSelectionBackgroundResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Autocomplete.selectionBackground", // deprecated
        "CheckBoxMenuItem.selectionBackground",
        "CompletionPopup.selectionBackground",
        "EditorPane.selectionBackground",
        "Github.List.tallRow.selectionBackground",
        "List.selectionBackground",
        "Menu.selectionBackground",
        "MenuItem.selectionBackground",
        "Plugins.selectionBackground", // deprecated
        "Plugins.lightSelectionBackground",
        "RadioButtonMenuItem.selectionBackground",
        "TabbedPane.selected", // deprecated
        "WelcomeScreen.Projects.selectionBackground",
        "material.selectionBackground"
      ));
  }

  public static Set<String> getSelectionTransparentBackgroundResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "List.selectionInactiveBackground",
        "TitlePane.Button.hoverBackground"
      ));
  }

  /**
   * Get resources using the selection foreground color
   */
  public static Set<String> getSelectionForegroundResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Button.darcula.selectedButtonForeground", // deprecated
        "Button.default.foreground",
        "Button.highlight",
        "Button.mt.selectedForeground",
        "CheckBoxMenuItem.selectionForeground",
        "ComboBox.selectionForeground",
        "CompletionPopup.selectedForeground", //deprecated
        "CompletionPopup.selectionForeground", // deprecated
        "CompletionPopup.selectedGrayedForeground", //deprecated
        "CompletionPopup.selectionGrayForeground", // deprecated
        "CompletionPopup.selectionInfoForeground", // deprecated
        "Counter.foreground",
        "EditorPane.selectionForeground",
        "FormattedTextField.selectionForeground",
        "Github.List.tallRow.selectionForeground", // deprecated
        "Github.List.tallRow.selectionForeground.unfocused", //deprecated
        "Label.selectedForeground",
        "List.selectionForeground",
        "List.selectionInactiveForeground",
        "Menu.acceleratorSelectionForeground",
        "Menu.selectionForeground",
        "MenuItem.acceleratorSelectionForeground",
        "MenuItem.selectionForeground",
        "PasswordField.selectionForeground",
        "Plugins.selectionForeground", // deprecated
        "Plugins.Tab.active.foreground", // deprecated
        "Plugins.Tab.selectedForeground",
        "SearchEverywhere.Tab.active.foreground", // deprecated
        "SearchEverywhere.Tab.selectedForeground",
        "SearchEverywhere.Tab.selected.foreground", // deprecated
        "TabbedPane.selectedForeground", // deprecated
        "Table.focusCellForeground",
        "Table.lightSelectionForeground",
        "Table.selectionForeground",
        "TableHeader.focusCellForeground",
        "TextArea.selectionForeground",
        "TextField.selectionForeground",
        "TextPane.selectionForeground",
        "ToolWindow.Button.selectedForeground",
        "Tree.selectionForeground",
        "Tree.selectionInactiveForeground",
        "VersionControl.Ref.foreground", //deprecated
        "VersionControl.RefLabel.foreground",
        "VersionControl.HgLog.bookmarkIconColor",
        "material.selectionForeground"
      ));
  }

  /**
   * Get resources using the button color
   */
  public static Set<String> getButtonColorResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Button.background", // deprecated
        "Button.darcula.borderColor", // deprecated
        "Button.darcula.defaultBorderColor", // deprecated
        "Button.darcula.defaultEndColor", // deprecated
        "Button.darcula.defaultOutlineColor", // deprecated
        "Button.darcula.defaultStartColor", // deprecated
        "Button.darcula.disabledBorderColor", // deprecated
        "Button.darcula.endColor", // deprecated
        "Button.darcula.outlineColor", // deprecated
        "Button.darcula.smallComboButtonBackground", // deprecated
        "Button.darcula.startColor", // deprecated
        "Button.default.borderColor", // deprecated
        "Button.endBackground",
        "Button.mt.background",
        "Button.mt.color1", // deprecated
        "Button.mt.color2", // deprecated
        "Button.select", // deprecated
        "Button.startBackground",
        "ComboBoxButton.background",
        "ComboBox.ArrowButton.background",
        "ComboBox.buttonBackground",
        "material.mergeCommits",
        "Notification.MoreButton.background",
        "Notification.MoreButton.innerBorderColor",
        "Outline.color", // deprecated
        "Plugins.Button.installBackground",
        "Plugins.Button.installBorderColor",
        "Plugins.Button.installFillBackground",
        "Plugins.Button.updateBackground",
        "Plugins.Button.updateBorderColor",
        "ToggleButton.borderColor",
        "ToggleButton.buttonColor",
        "ToolBar.comboBoxButtonBackground", // deprecated
        "WelcomeScreen.groupIconBorderColor"
      ));
  }

  /**
   * Get resources using the secondary background color
   */
  public static Set<String> getSecondaryBackgroundResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "CompletionPopup.background",
        "EditorGroupsTabs.borderColor",
        "EditorTabs.borderColor",
        "inactiveCaption",
        "List.background",
        "MemoryIndicator.allocatedBackground",
        "MemoryIndicator.unusedColor", // deprecated
        "Menu.borderColor",
        "ParameterInfo.background",
        "Plugins.SectionHeader.background",
        "Popup.separatorColor",
        "Separator.background",
        "Separator.foreground",
        "Separator.separatorColor",
        "Slider.tickColor",
        "Table.lightSelectionInactiveBackground",
        "TextArea.background",
        "ToolWindow.active.Header.background",
        "ToolWindow.Header.background",
        "ToolWindow.header.active.background", //deprecated
        "ToolWindow.header.border.background", //deprecated
        "ToolWindow.Header.borderColor",
        "WelcomeScreen.Projects.background",
        "WelcomeScreen.Projects.selectionInactiveBackground"
      ));
  }

  /**
   * Get resources using the disabled color
   */
  public static Set<String> getDisabledResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Button.disabledText",
        "CheckBox.darcula.checkSignColorDisabled", // deprecated
        "CheckBox.darcula.disabledBorderColor1", // deprecated
        "CheckBox.darcula.disabledBorderColor2", // deprecated
        "CheckBox.disabledText",
        "CheckBoxMenuItem.disabledForeground",
        "ComboBox.ArrowButton.disabledIconColor",
        "ComboBox.darcula.arrowButtonDisabledForeground", // deprecated
        "ComboBox.disabledForeground",
        "Component.disabledBorderColor",
        "EditorPane.inactiveForeground",
        "FormattedTextField.inactiveForeground",
        "Label.disabledForeground",
        "Label.disabledForegroundColor", // deprecated
        "Label.disabledShadow", // deprecated
        "Label.disabledText",
        "Menu.disabledForeground",
        "MenuBar.disabledForeground",
        "MenuItem.disabledForeground",
        "Outline.disabledColor", // deprecated
        "ParameterInfo.disabledColor", //deprecated
        "ParameterInfo.disabledForeground",
        "PasswordField.inactiveForeground",
        "Plugins.disabledForeground",
        "RadioButton.disabledText",
        "RadioButtonMenuItem.disabledForeground",
        "SearchEverywhere.SearchField.grayForeground", // deprecated
        "SearchEverywhere.SearchField.infoForeground",
        "TabbedPane.disabledForeground",
        "TabbedPane.disabledText", // deprecated
        "TabbedPane.disabledUnderlineColor",
        "TabbedPane.selectedDisabledColor",
        "TextArea.inactiveForeground",
        "TextField.inactiveForeground",
        "TextPane.inactiveForeground",
        "TitlePane.inactiveInfoForeground",
        "ToggleButton.disabledText",
        "VersionControl.HgLog.closedBranchIconColor"
      ));
  }

  /**
   * Get resources using the contrast color
   */
  public static Set<String> getContrastResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "DefaultTabs.hoverColor",
        "DefaultTabs.inactiveMaskColor",
        "EditorPane.background",
        "HeaderColor.inactive", // deprecated
        "material.contrast",
        "NewClass.SearchField.background",
        "Popup.Border.color", // deprecated
        "Popup.borderColor",
        "Popup.Header.inactiveBackground",
        "Popup.Toolbar.background",
        "Popup.Toolbar.Border.color", // deprecated
        "Popup.Toolbar.borderColor",
        "ScrollBar.thumb",
        "SearchEverywhere.Advertiser.background",
        "SearchEverywhere.SearchField.background",
        "Table.stripeColor",
        "Table.stripedBackground", // deprecated
        "TitlePane.background",
        "ToolBar.background",
        "ToolWindow.Button.selectedBackground",
        "ToolWindow.header.tab.selected.active.background", // deprecated
        "ToolWindow.header.tab.selected.background", // deprecated
        "ToolWindow.HeaderTab.selectedInactiveBackground",
        "ToolWindow.HeaderTab.selectedBackground",
        "ToolWindow.inactive.HeaderTab.background", // deprecated
        "ToolWindow.active.HeaderTab.background", // deprecated
        "ToolWindow.HeaderTab.underlinedTabInactiveBackground",
        "VersionControl.Log.Commit.currentBranchBackground",
        "WelcomeScreen.captionBackground",
        "WelcomeScreen.footerBackground"
      ));
  }

  /**
   * Get resources using the table/button selection color
   */
  public static Set<String> getTableSelectedResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Button.darcula.defaultFocusedBorderColor", // deprecated
        "Button.darcula.focusedBorderColor", // deprecated
        "Button.darcula.selection.color1", // deprecated
        "Button.darcula.selection.color2", // deprecated
        "Button.mt.selection.color1",
        "Button.mt.selection.color2",
        "Button.default.startBackground",
        "Button.default.endBackground",
        "Button.focus", // deprecated
        "ComboBox.selectionBackground",
        "DebuggerTabs.underlinedTabBackground",
        "DefaultTabs.hoverBackground",
        "DefaultTabs.underlinedTabBackground",
        "EditorGroupsTabs.underlinedTabBackground",
        "EditorTabs.underlinedTabBackground",
        "EditorTabs.active.background", // deprecated
        "EditorTabs.selectedBackground",
        "FormattedTextField.selectionBackground",
        "ParameterInfo.borderColor",
        "ParameterInfo.lineSeparatorColor",
        "ParameterInfo.currentOverloadBackground",
        "PasswordField.selectionBackground",
        "Plugins.Tab.active.background", // deprecated
        "Plugins.Tab.selectedBackground",
        "Plugins.Tab.hover.background", // deprecated
        "Plugins.Tab.hoverBackground",
        "Slider.track", // deprecated
        "Slider.trackColor",
        "TabbedPane.focusColor",
        "Table.highlightOuter",
        "Table.focusCellBackground",
        "Table.lightSelectionBackground", // deprecated
        "Table.selectionBackground",
        "TextArea.selectionBackground",
        "TextField.selectionBackground",
        "TextPane.selectionBackground",
        "Tree.selectionInactiveBackground",
        "Table.selectionInactiveBackground",
        "ToolWindow.Button.hoverBackground"
      ));
  }

  /**
   * Get resources using the second border color
   */
  public static Set<String> getSecondBorderResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Borders.color",
        "Button.darcula.disabledOutlineColor", // deprecated
        "Button.darcula.shadowColor", // deprecated
        "Button.disabledBorderColor",
        "Button.shadowColor",
        "ComboPopup.border",
        "Group.disabledSeparatorColor",
        "HelpTooltip.borderColor",
        "InformationHint.borderColor",
        "Menu.separatorColor",
        "OnePixelDivider.background", // deprecated
        "Plugins.SearchField.borderColor",
        "Popup.Separator.color", // deprecated
        "SearchEverywhere.List.Separator.Color", // deprecated
        "SearchEverywhere.List.Separator.foreground", // deprecated
        "SearchEverywhere.List.separatorColor",
        "SpeedSearch.borderColor",
        "TabbedPane.darkShadow", // deprecated
        "TabbedPane.highlight", // deprecated
        "TabbedPane.shadow", // deprecated
        "TableHeader.bottomSeparatorColor",
        "TableHeader.separatorColor",
        "Tooltip.separatorColor",
        "Tree.hash",
        "WelcomeScreen.separatorColor",
        "windowBorder"
      ));
  }

  /**
   * Get resources using the highlight color
   */
  public static Set<String> getHighlightResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Autocomplete.selectionUnfocus", // deprecated
        "CheckBox.darcula.inactiveFillColor", // deprecated
        "CompletionPopup.selectionInactiveBackground",
        "Component.focusedBorderColor",
        "Component.borderColor",
        "DefaultTabs.hoverColor", // not implemented
        "DefaultTabs.hoverMaskColor", // not implemented
        "DebuggerTabs.active.background", // deprecated
        "DebuggerTabs.selectedBackground", // deprecated
        "EditorGroupsTabs.hoverBackground",
        "EditorGroupsTabs.hoverColor",
        "EditorTabs.hoverColor",
        "EditorTabs.hoverMaskColor",
        "Focus.color", // deprecated
        "Github.List.tallRow.selectionBackground.unfocused", // deprecated
        "MemoryIndicator.usedColor", // deprecated
        "MemoryIndicator.usedBackground",
        "Outline.focusedColor", // deprecated
        "Plugins.Button.installFocusedBackground",
        "Plugins.eapTagBackground",
        "Plugins.tagBackground",
        "ProgressBar.halfColor", // deprecated
        "ProgressBar.trackColor",
        "ProgressBar.selectionBackground",
        "SearchEverywhere.Tab.active.background", // deprecated
        "SearchEverywhere.Tab.selectedBackground",
        "SearchEverywhere.Tab.selected.background", // deprecated
        "SpeedSearch.background",
        "Slider.trackDisabled", // deprecated
        "TabbedPane.contentAreaColor",
        "TabbedPane.hoverColor",
        "TabbedPane.selectHighlight", // deprecated
        "TabbedPane.selectedColor", // deprecated
        "TableHeader.borderColor", // deprecated
        "TextField.separatorColor", // deprecated
        "ToolWindow.HeaderTab.hoverBackground",
        "VersionControl.Ref.backgroundBase", //deprecated
        "VersionControl.RefLabel.backgroundBase"
      ));
  }

  /**
   * Get resources using the tree selected row color
   */
  public static Set<String> getTreeSelectionResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Tree.selectionBackground"
      ));
  }

  /**
   * Get notifications colors resources
   */
  public static Set<String> getNotificationsResources() {
    return Collections.unmodifiableSet(
      Sets.newHashSet(
        "Notification.background",
        "Notification.borderColor",
        "Notifications.background", // deprecated
        "Notifications.borderColor", // deprecated
        "ValidationTooltip.errorBackground",
        "ValidationTooltip.errorBackgroundColor", // deprecated
        "ValidationTooltip.errorBorderColor",
        "ValidationTooltip.warningBackground",
        "ValidationTooltip.warningBackgroundColor", // deprecated
        "ValidationTooltip.warningBorderColor"
      ));
  }
}
