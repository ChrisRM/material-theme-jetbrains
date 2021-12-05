/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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
@file:Suppress("HardCodedStringLiteral")

package com.mallowigi.idea.themes.lists

/**
 * List of UI Resources
 */
object MTThemeResources {

  /**
   * Scrollbar related resources
   */
  @JvmStatic
  val scrollBarResources: Set<String>
    get() = setOf(
      "ScrollBar.Mac.Transparent.hoverTrackColor",
      "ScrollBar.Mac.Transparent.trackColor",
      "ScrollBar.Mac.hoverTrackColor",
      "ScrollBar.Mac.trackColor",
      "ScrollBar.Transparent.hoverTrackColor",
      "ScrollBar.Transparent.trackColor",
      "ScrollBar.hoverTrackColor",
      "ScrollBar.trackColor"
    )

  /**
   * Get resources using the background color
   */
  @JvmStatic
  val backgroundResources: Set<String>
    get() = setOf(
      "BigSpinner.background",
      "Borders.ContrastBorderColor",
      "Button.darcula.disabledText.shadow", // deprecated
      "Button.default.shadowColor",
      "CheckBox.background",
      "CheckBox.borderColor",
      "CheckBox.checkSignColor",
      "CheckBox.checkSignColor.selected",
      "CheckBox.checkSignColorDisabled",
      "CheckBox.checkSignColorDisabled.selected",
      "CheckBox.disabledBorderColor",
      "CheckBox.focused.background",
      "CheckBox.focusedArmed.background",
      "CheckBox.inactiveFillColor",
      "CheckBox.shadowColor",
      "CheckBox.shadowColorDisabled",
      "CheckBoxMenuItem.background",
      "CheckBoxMenuItem.disabledBackground",
      "Checkbox.Background.Default",
      "Checkbox.Background.Default.Dark",
      "Checkbox.Focus.Thin.Default",
      "Checkbox.Focus.Thin.Default.Dark",
      "Checkbox.Focus.Wide.Default",
      "Checkbox.Focus.Wide.Default.Dark",
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
      "DebuggerPopup.borderColor",
      "DefaultTabs.background",
      "DefaultTabs.borderColor",
      "DefaultTabs.inactiveColoredTabBackground",
      "Desktop.background",
      "Dialog.titleColor", // deprecated
      "DialogWrapper.southPanelBackground",
      "DialogWrapper.southPanelDivider",
      "DragAndDrop.areaBorderColor",
      "DragAndDrop.backgroundBorderColor", // deprecated
      "DragAndDrop.backgroundColor", // deprecated
      "Editor.background",
      "EditorGroupsTabs.background",
      "EditorPane.inactiveBackground",
      "EditorTabs.background",
      "EditorTabs.inactive.maskColor", // deprecated
      "EditorTabs.inactiveMaskColor",
      "FormattedTextField.background",
      "GutterTooltip.borderColor", // deprecated
      "GutterTooltip.lineSeparatorColor",
      "HeaderColor.active", // deprecated
      "HelpTooltip.background",
      "HelpTooltip.backgroundColor", // deprecated
      "InplaceRefactoringPopup.borderColor",
      "InternalFrame.inactiveTitleBackground",
      "MenuBar.background",
      "MenuBar.borderColor",
      "MenuBar.disabledBackground",
      "MenuBar.highlight",
      "MenuBar.shadow",
      "MlModelBinding.Viewer.CodeEditor.background",
      "NavBar.borderColor",
      "NewClass.Panel.background",
      "OptionPane.background",
      "Panel.mouseShortcutBackground",
      "PasswordField.background",
      "Plugins.SearchField.background",
      "Plugins.background",
      "Popup.Advertiser.background",
      "Popup.Border.inactiveColor", // deprecated
      "Popup.Header.activeBackground",
      "Popup.inactiveBorderColor",
      "Popup.preferences.background", // deprecated
      "Popup.preferences.borderColor", // deprecated
      "PopupMenu.background",
      "PopupMenu.translucentBackground",
      "RadioButton.background",
      "RadioButton.darcula.selectionDisabledColor", // deprecated
      "RadioButton.selectionDisabledColor", // deprecated
      "ScrollBar.background",
      "SearchEverywhere.Dialog.background", // deprecated
      "SearchEverywhere.Header.background",
      "SearchEverywhere.SearchField.Border.color", // deprecated
      "SearchEverywhere.SearchField.borderColor",
      "SearchEverywhere.background", // deprecated
      "SidePanel.background",
      "Slider.background",
      "Spinner.background",
      "SplitPane.highlight",
      "StatusBar.background", // deprecated
      "StatusBar.borderColor",
      "StatusBar.bottomColor", // deprecated
      "StatusBar.top2Color", // deprecated
      "StatusBar.topColor", // deprecated
      "TabbedPane.background",
      "TabbedPane.borderColor", // deprecated
      "TabbedPane.mt.tab.background",
      "Table.background",
      "Table.gridColor",
      "TableHeader.background",
      "TextField.background",
      "TextField.borderColor", // deprecated
      "TextField.focusedBorderColor", // deprecated
      "TextField.hoverBorderColor", // deprecated
      "TextField.separatorColorDisabled", // deprecated
      "TextPane.background",
      "TitlePane.inactiveBackground",
      "ToolTip.Actions.background",
      "ToolTip.actions.background", // deprecated
      "ToolWindow.Header.inactiveBackground",
      "ToolWindow.HeaderCloseButton.background",
      "ToolWindow.HeaderTab.borderColor",
      "ToolWindow.HeaderTab.underlinedTabBackground",
      "ToolWindow.header.background", // deprecated
      "ToolWindow.header.closeButton.background", // deprecated
      "ToolWindow.inactive.Header.background", // deprecated
      "Tree.background",
      "UIDesigner.Component.background",
      "UIDesigner.Panel.background",
      "UIDesigner.Placeholder.background",
      "UIDesigner.Preview.background",
      "UIDesigner.motion.SecondaryPanel.background",
      "UIDesigner.motion.motionGraph.background",
      "VersionControl.FileHistory.Commit.otherBranchBackground", // deprecated
      "VersionControl.FileHistory.Commit.selectedBranchBackground",
      "WelcomeScreen.AssociatedComponent.background",
      "WelcomeScreen.Details.background",
      "WelcomeScreen.background",
      "WelcomeScreen.borderColor",
      "WelcomeScreen.headerBackground",
      "activeCaption", // deprecated
      "control",
      "darcula.background",
      "inactiveCaptionBorder",
      "intellijlaf.background", // deprecated
      "material.background",
      "material.tab.backgroundColor",
      "tooltips.actions.settings.icon.background.color", // deprecated
      "window"
    )

  /**
   * Get resources using the foreground color
   */
  @JvmStatic
  val foregroundResources: Set<String>
    get() = setOf(
      "AvailableMnemonic.foreground",
      "BookmarkMnemonicAvailable.foreground",
      "CheckBox.darcula.borderColor1", // deprecated
      "CheckBox.foreground",
      "CheckBoxMenuItem.foreground",
      "CodeWithMe.Avatar.foreground",
      "ColorChooser.foreground",
      "ComboBox.ArrowButton.iconColor",
      "ComboBox.darcula.arrowButtonForeground", // deprecated
      "ComboBox.foreground",
      "CompletionPopup.foreground",
      "Content.selectionInactiveBackground",
      "DragAndDrop.areaForeground",
      "DragAndDrop.foregroundColor", // deprecated
      "Editor.foreground",
      "EditorGroupsTabs.underlinedTabForeground",
      "EditorPane.foreground",
      "EditorTabs.active.foreground", // deprecated
      "EditorTabs.selectedForeground",
      "FormattedTextField.foreground",
      "Git.Log.Ref.RemoteBranch", // deprecated
      "Github.List.tallRow.foreground", // deprecated
      "GotItTooltip.foreground",
      "Group.separatorColor",
      "HelpTooltip.foreground",
      "HelpTooltip.textColor", // deprecated
      "Hg.Log.Ref.ClosedBranch", // deprecated
      "InternalFrame.activeTitleForeground",
      "Label.foreground",
      "Label.selectedDisabledForeground",
      "Lesson.Tooltip.foreground",
      "Lesson.Tooltip.spanForeground",
      "List.foreground",
      "Menu.foreground",
      "MenuBar.foreground",
      "MenuItem.foreground",
      "MnemonicIcon.foreground",
      "BookmarkMnemonicIcon.foreground",
      "NavBar.arrowColor",
      "Notification.MoreButton.foreground",
      "Notification.ToolWindow.errorForeground",
      "Notification.ToolWindow.infoForeground", // deprecated
      "Notification.ToolWindow.informativeForeground",
      "Notification.ToolWindow.warningForeground",
      "Notification.ToolWindowError.foreground", // deprecated
      "Notification.ToolWindowInfo.foreground", // deprecated
      "Notification.ToolWindowWarning.foreground", // deprecated
      "Notification.foreground",
      "OptionPane.messageForeground",
      "ParameterInfo.foreground",
      "PasswordField.foreground",
      "Plugins.Button.installForeground",
      "Plugins.Button.updateForeground",
      "Plugins.SectionHeader.foreground",
      "Popup.Separator.foreground", // deprecated
      "Popup.separatorForeground",
      "PopupMenu.foreground",
      "RadioButton.darcula.borderColor1", // deprecated
      "RadioButton.foreground",
      "RadioButtonMenuItem.foreground",
      "SearchEverywhere.foreground", // deprecated
      "SearchResults.Repeated.File.Foreground",
      "SpeedSearch.errorForeground",
      "SpeedSearch.foreground",
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
      "ToggleButton.off.background", // deprecated
      "ToggleButton.off.foreground", // deprecated
      "ToggleButton.offBackground",
      "ToggleButton.offForeground",
      "ToolBar.foreground",
      "ToolTip.foreground",
      "ToolWindow.HeaderTab.underlinedTabInactiveForeground",
      "UIDesigner.ColorPicker.foreground",
      "UIDesigner.Component.foreground",
      "UIDesigner.Placeholder.foreground",
      "UIDesigner.highStroke.foreground",
      "UIDesigner.motion.Component.foreground",
      "UIDesigner.motion.CursorTextColor.foreground",
      "UIDesigner.percent.foreground",
      "VersionControl.GitLog.remoteBranchIconColor",
      "WelcomeScreen.captionForeground",
      "WelcomeScreen.footerForeground",
      "WelcomeScreen.headerForeground",
      "darcula.foreground", // deprecated
      "intellijlaf.foreground", // deprecated
      "material.branchColor",
      "material.foreground",
      "tooltips.description.title.text.color" // deprecated
    )

  /**
   * Get resources using the label color
   */
  @JvmStatic
  val textResources: Set<String>
    get() = setOf(
      "Button.foreground",
      "Button.mt.foreground",
      "CheckBoxMenuItem.acceleratorForeground",
      "CheckBoxMenuItem.acceleratorSelectionForeground",
      "CompletionPopup.grayForeground", // deprcated
      "CompletionPopup.grayedForeground", // deprecated
      "CompletionPopup.infoForeground",
      "CompletionPopup.selectionInactiveForeground", // deprecated
      "CompletionPopup.selectionInactiveInfoForeground",
      "Component.grayForeground", // deprecated
      "Component.infoForeground",
      "Component.iconColor",
      "Debugger.Variables.collectingDataForeground",
      "Debugger.Variables.evaluatingExpressionForeground",
      "Editor.shortcutForeground",
      "Git.Log.Ref.Other", // deprecated
      "Git.Log.Ref.Tag", // deprecated
      "Github.List.tallRow.secondary.foreground",
      "GotItTooltip.shortcutForeground",
      "GutterTooltip.infoForeground",
      "HelpTooltip.infoForeground",
      "HelpTooltip.shortcutForeground",
      "HelpTooltip.shortcutTextColor", // deprecated
      "Hg.Log.Ref.LocalTag", // deprecated
      "Hg.Log.Ref.MqTag", // deprecated
      "Hg.Log.Ref.Tag", // deprecated
      "InternalFrame.inactiveTitleForeground", // deprecated
      "Label.grayForeground", // deprecated
      "Label.infoForeground",
      "Label.textForeground", //
      "Lesson.stepNumberForeground",
      "Lesson.Tooltip.stepNumberForeground",
      "Link.secondaryForeground",
      "Menu.acceleratorForeground",
      "MenuItem.acceleratorForeground",
      "ParameterInfo.ContextHelp.foreground", // deprecated
      "ParameterInfo.infoForeground",
      "Popup.Advertiser.foreground",
      "RadioButtonMenuItem.acceleratorForeground",
      "RadioButtonMenuItem.acceleratorSelectionForeground",
      "SearchEverywhere.Advertiser.foreground",
      "SearchEverywhere.List.separatorForeground",
      "SearchEverywhere.shortcutForeground", // deprecated
      "SearchResults.Ordinal.File.Foreground",
      "Table.lightSelectionInactiveForeground",
      "TitlePane.infoForeground",
      "ToolBar.borderHandleColor",
      "ToolBar.floatingForeground",
      "ToolTip.Actions.grayForeground", // deprecated
      "ToolTip.Actions.infoForeground",
      "ToolTip.infoForeground",
      "ToolTip.shortcutForeground",
      "Tree.foreground",
      "UIDesigner.Label.foreground",
      "UIDesigner.Panel.graphLabel",
      "UIDesigner.motion.ConstraintSetText.foreground",
      "UIDesigner.motion.SecondaryPanel.header.foreground",
      "UIDesigner.motion.cs_FocusText.infoForeground",
      "UIDesigner.motion.ourCS_TextColor.foreground",
      "UIDesigner.stroke.acceleratorForeground",
      "VersionControl.GitLog.otherIconColor",
      "VersionControl.GitLog.tagIconColor",
      "VersionControl.HgLog.localTagIconColor",
      "VersionControl.HgLog.mqTagIconColor",
      "VersionControl.HgLog.tagIconColor",
      "VersionControl.HgLog.tipIconColor",
      "VersionControl.Log.Commit.unmatchedForeground",
      "controlText",
      "inactiveCaptionText", // deprecated
      "infoPanelForeground",
      "infoText", // deprecated
      "material.primaryColor",
      "material.tagColor",
      "text",
      "textInactiveText",
      "textText",
      "tooltips.actions.keymap.text.color" // deprecated
    )

  /**
   * Get resources using the selection background color
   */
  @JvmStatic
  val selectionBackgroundResources: Set<String>
    get() = setOf(
      "AssignedMnemonic.borderColor",
      "Autocomplete.selectionBackground", // deprecated
      "BookmarkMnemonicAssigned.borderColor",
      "CheckBoxMenuItem.selectionBackground",
      "Content.selectionBackground",
      "EditorPane.selectionBackground",
      "Github.List.tallRow.selectionBackground",
      "List.selectionBackground",
      "Menu.selectionBackground",
      "MenuItem.selectionBackground",
      "Plugins.selectionBackground", // deprecated
      "RadioButtonMenuItem.selectionBackground",
      "TabbedPane.selected", // deprecated
      "UIDesigner.motion.CSPanel.SelectedFocusBackground",
      "WelcomeScreen.Projects.selectionBackground",
      "material.selectionBackground"
    )

  /**
   * Selected transparent resources
   */
  @JvmStatic
  val selectionTransparentBackgroundResources: Set<String>
    get() = setOf(
      "List.selectionInactiveBackground",
      "TitlePane.Button.hoverBackground"
    )

  /**
   * Get resources using the selection foreground color
   */
  @JvmStatic
  val selectionForegroundResources: Set<String>
    get() = setOf(
      "AssignedMnemonic.foreground",
      "BookmarkMnemonicAssigned.foreground",
      "Button.darcula.selectedButtonForeground", // deprecated
      "Button.default.foreground",
      "Button.highlight",
      "Button.mt.selectedForeground",
      "CheckBoxMenuItem.selectionForeground",
      "ComboBox.selectionForeground",
      "CompletionPopup.selectedForeground", // deprecated
      "CompletionPopup.selectedGrayedForeground", // deprecated
      "CompletionPopup.selectionForeground", // deprecated
      "CompletionPopup.selectionGrayForeground", // deprecated
      "CompletionPopup.selectionInfoForeground", // deprecated
      "Counter.foreground",
      "CurrentMnemonic.foreground",
      "BookmarkMnemonicCurrent.foreground",
      "DefaultTabs.underlinedTabForeground",
      "EditorPane.selectionForeground",
      "EditorTabs.underlinedTabForeground",
      "FormattedTextField.selectionForeground",
      "Github.List.tallRow.selectionForeground", // deprecated
      "Github.List.tallRow.selectionForeground.unfocused", // deprecated
      "Label.selectedForeground",
      "Lesson.Badge.newLessonForeground",
      "List.selectionForeground",
      "List.selectionInactiveForeground",
      "Menu.acceleratorSelectionForeground",
      "Menu.selectionForeground",
      "MenuItem.acceleratorSelectionForeground",
      "MenuItem.selectionForeground",
      "PasswordField.selectionForeground",
      "Plugins.Tab.active.foreground", // deprecated
      "Plugins.Tab.selectedForeground",
      "Plugins.selectionForeground", // deprecated
      "SearchEverywhere.Tab.active.foreground", // deprecated
      "SearchEverywhere.Tab.selected.foreground", // deprecated
      "SearchEverywhere.Tab.selectedForeground",
      "TabbedPane.selectedForeground", // deprecated
      "Table.focusCellForeground",
      "Table.lightSelectionForeground",
      "Table.selectionForeground",
      "TableHeader.focusCellForeground",
      "TextArea.selectionForeground",
      "TextField.selectionForeground",
      "TextPane.selectionForeground",
      "ToolWindow.Button.selectedForeground",
      "ToolWindow.HeaderTab.underlinedTabForeground",
      "Tree.selectionForeground",
      "Tree.selectionInactiveForeground",
      "UIDesigner.Placeholder.selectedForeground",
      "UIDesigner.motion.ourCS_SelectedFocusBackground.selectionForeground",
      "VersionControl.HgLog.bookmarkIconColor",
      "VersionControl.Ref.foreground", // deprecated
      "VersionControl.RefLabel.foreground",
      "material.selectionForeground"
    )

  /**
   * Get resources using the button color
   */
  @JvmStatic
  val buttonColorResources: Set<String>
    get() = setOf(
      "ActionButton.hoverSeparatorColor",
      "AvailableMnemonic.background",
      "AvailableMnemonic.borderColor",
      "BookmarkMnemonicAvailable.background",
      "BookmarkMnemonicAvailable.borderColor",
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
      "ComboBox.ArrowButton.background",
      "ComboBox.buttonBackground",
      "ComboBoxButton.background",
      "DefaultTabs.inactiveColoredFileBackground",
      "GotItTooltip.endBackground",
      "GotItTooltip.endBorderColor",
      "GotItTooltip.startBackground",
      "GotItTooltip.startBorderColor",
      "Lesson.Tooltip.spanBackground",
      "Notification.MoreButton.background",
      "Notification.MoreButton.innerBorderColor",
      "Outline.color", // deprecated
      "Plugins.Button.installBackground",
      "Plugins.Button.installBorderColor",
      "Plugins.Button.installFillBackground",
      "Plugins.Button.updateBorderColor",
      "StateWidget.activeBackground",
      "ToggleButton.borderColor",
      "ToggleButton.buttonColor",
      "ToolBar.comboBoxButtonBackground", // deprecated
      "WelcomeScreen.groupIconBorderColor",
      "material.mergeCommits"
    )

  /**
   * Get resources using the secondary background color
   */
  @JvmStatic
  val secondaryBackgroundResources: Set<String>
    get() = setOf(
      "Checkbox.Background.Disabled",
      "Checkbox.Background.Disabled.Dark",
      "Checkbox.Border.Disabled",
      "Checkbox.Border.Disabled.Dark",
      "CodeWithMe.AccessEnabled.dropdownBorder",
      "CodeWithMe.AccessEnabled.pillBackground",
      "CompletionPopup.background",
      "EditorGroupsTabs.borderColor",
      "EditorTabs.borderColor",
      "Lesson.shortcutBackground",
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
      "Space.Review.diffAnchorBackground",
      "Table.lightSelectionInactiveBackground",
      "TextArea.background",
      "ToolWindow.Header.background",
      "ToolWindow.Header.borderColor",
      "ToolWindow.active.Header.background",
      "ToolWindow.header.active.background", // deprecated
      "ToolWindow.header.border.background", // deprecated
      "Toolbar.Floating.background",
      "UIDesigner.ColorPicker.background",
      "UIDesigner.motion.ConstraintSet.background",
      "UIDesigner.motion.ourAvg.background",
      "UIDesigner.motion.ourCS.background",
      "WelcomeScreen.Projects.background",
      "WelcomeScreen.Projects.selectionInactiveBackground",
      "WelcomeScreen.SidePanel.background",
      "inactiveCaption"
    )

  /**
   * Get resources using the disabled color
   */
  @JvmStatic
  val disabledResources: Set<String>
    get() = setOf(
      "Button.disabledText",
      "CheckBox.darcula.checkSignColorDisabled", // deprecated
      "CheckBox.darcula.disabledBorderColor1", // deprecated
      "CheckBox.darcula.disabledBorderColor2", // deprecated
      "CheckBox.disabledText",
      "CheckBoxMenuItem.disabledForeground",
      "CodeWithMe.AccessDisabled.accessDot",
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
      "ParameterInfo.disabledColor", // deprecated
      "ParameterInfo.disabledForeground",
      "PasswordField.inactiveForeground",
      "Plugins.Button.installFillForeground",
      "Plugins.disabledForeground",
      "RadioButton.disabledText",
      "RadioButtonMenuItem.disabledForeground",
      "SearchEverywhere.SearchField.grayForeground", // deprecated
      "SearchEverywhere.SearchField.infoForeground",
      "TabbedPane.disabledForeground",
      "TabbedPane.disabledText", // deprecated
      "TabbedPane.disabledUnderlineColor",
      "TabbedPane.selectedDisabledColor",
      "Table.disabledForeground",
      "TableHeader.disabledForeground",
      "TextArea.inactiveForeground",
      "TextField.inactiveForeground",
      "TextPane.inactiveForeground",
      "TitlePane.inactiveInfoForeground",
      "ToggleButton.disabledText",
      "UIDesigner.motion.HoverColor.disabledBackground",
      "VersionControl.HgLog.closedBranchIconColor"
    )

  /**
   * Get resources using the contrast color
   */
  @JvmStatic
  val contrastResources: Set<String>
    get() = setOf(
      "Content.background",
      "DefaultTabs.hoverColor",
      "DefaultTabs.inactiveMaskColor",
      "EditorPane.background",
      "HeaderColor.inactive", // deprecated
      "NewClass.SearchField.background",
      "Popup.Border.color", // deprecated
      "Popup.Header.inactiveBackground",
      "Popup.Toolbar.Border.color", // deprecated
      "Popup.Toolbar.Floating.background",
      "Popup.Toolbar.background",
      "Popup.Toolbar.borderColor",
      "Popup.borderColor",
      "ScrollBar.thumb",
      "SearchEverywhere.Advertiser.background",
      "SearchEverywhere.SearchField.background",
      "Table.alternativeRowBackground",
      "Table.stripeColor",
      "Table.stripedBackground", // deprecated
      "TitlePane.background",
      "ToolBar.background",
      "ToolWindow.Button.selectedBackground",
      "ToolWindow.HeaderTab.selectedBackground",
      "ToolWindow.HeaderTab.selectedInactiveBackground",
      "ToolWindow.HeaderTab.underlinedTabInactiveBackground",
      "ToolWindow.active.HeaderTab.background", // deprecated
      "ToolWindow.header.tab.selected.active.background", // deprecated
      "ToolWindow.header.tab.selected.background", // deprecated
      "ToolWindow.inactive.HeaderTab.background", // deprecated
      "UIDesigner.Canvas.background",
      "UIDesigner.motion.PrimaryPanel.background",
      "UIDesigner.motion.SecondaryPanel.header.background",
      "VersionControl.Log.Commit.currentBranchBackground",
      "WelcomeScreen.List.background",
      "WelcomeScreen.Projects.actions.background",
      "WelcomeScreen.captionBackground",
      "WelcomeScreen.footerBackground",
      "material.contrast"
    )

  /**
   * Get resources using the table/button selection color
   */
  @JvmStatic
  val tableSelectedResources: Set<String>
    get() = setOf(
      "Button.darcula.defaultFocusedBorderColor", // deprecated
      "Button.darcula.focusedBorderColor", // deprecated
      "Button.darcula.selection.color1", // deprecated
      "Button.darcula.selection.color2", // deprecated
      "Button.default.endBackground",
      "Button.default.startBackground",
      "Button.focus", // deprecated
      "Button.mt.selection.color1",
      "Button.mt.selection.color2",
      "ComboBox.selectionBackground",
      "DebuggerTabs.underlinedTabBackground",
      "DefaultTabs.hoverBackground",
      "DefaultTabs.underlinedTabBackground",
      "EditorGroupsTabs.underlinedTabBackground",
      "EditorTabs.active.background", // deprecated
      "EditorTabs.selectedBackground",
      "EditorTabs.underlinedTabBackground",
      "FormattedTextField.selectionBackground",
      "List.hoverInactiveBackground",
      "ParameterInfo.borderColor",
      "ParameterInfo.lineSeparatorColor",
      "PasswordField.selectionBackground",
      "Plugins.Tab.active.background", // deprecated
      "Plugins.Tab.hover.background", // deprecated
      "Plugins.Tab.hoverBackground",
      "Plugins.Tab.selectedBackground",
      "Plugins.lightSelectionBackground",
      "SearchOption.selectedBackground",
      "Slider.track", // deprecated
      "Slider.trackColor",
      "StatusBar.LightEditBackground",
      "TabbedPane.focusColor",
      "Table.focusCellBackground",
      "Table.highlightOuter",
      "Table.hoverInactiveBackground",
      "Table.lightSelectionBackground", // deprecated
      "Table.selectionBackground",
      "Table.selectionInactiveBackground",
      "TextArea.selectionBackground",
      "TextField.selectionBackground",
      "TextPane.selectionBackground",
      "ToolWindow.Button.hoverBackground",
      "ToolWindow.HeaderTab.underlinedTabBackground",
      "Tree.hoverInactiveBackground",
      "Tree.selectionInactiveBackground",
      "UIDesigner.motion.ourCS_SelectedBackground.selectionInactiveBackground"
    )

  /**
   * Get resources using the second border color
   */
  @JvmStatic
  val secondBorderResources: Set<String>
    get() = setOf(
      "Borders.color",
      "Button.darcula.disabledOutlineColor", // deprecated
      "Button.darcula.shadowColor", // deprecated
      "Button.disabledBorderColor",
      "Button.shadowColor",
      "Canvas.Tooltip.borderColor",
      "ComboPopup.border",
      "Group.disabledSeparatorColor",
      "HelpTooltip.borderColor",
      "InformationHint.borderColor",
      "Menu.separatorColor",
      "OnePixelDivider.background", // deprecated
      "Plugins.SearchField.borderColor",
      "Popup.Separator.color", // deprecated
      "ScreenView.borderColor",
      "SearchEverywhere.List.Separator.Color", // deprecated
      "SearchEverywhere.List.Separator.foreground", // deprecated
      "SearchEverywhere.List.separatorColor",
      "SpeedSearch.borderColor",
      "TabbedPane.darkShadow", // deprecated
      "TabbedPane.highlight", // deprecated
      "TabbedPane.shadow", // deprecated
      "TableHeader.bottomSeparatorColor",
      "TableHeader.separatorColor",
      "ToolTip.borderColor",
      "Tooltip.separatorColor",
      "Tree.hash",
      "UIDesigner.Activity.borderColor",
      "UIDesigner.Component.borderColor",
      "UIDesigner.Connector.borderColor",
      "UIDesigner.Panel.borderColor",
      "UIDesigner.Panel.secondaryGraphLines",
      "UIDesigner.Placeholder.borderColor",
      "UIDesigner.motion.borderColor",
      "UIDesigner.motion.ourCS_Border.borderColor",
      "UIDesigner.motion.ourML_BarColor.separatorColor",
      "UIDesigner.motion.timeLine.disabledBorderColor",
      "WelcomeScreen.separatorColor",
      "windowBorder"
    )

  /**
   * Get resources using the highlight color
   */
  @JvmStatic
  val highlightResources: Set<String>
    get() = setOf(
      "AssignedMnemonic.background",
      "BookmarkMnemonicAssigned.background",
      "Autocomplete.selectionUnfocus", // deprecated
      "CheckBox.darcula.inactiveFillColor", // deprecated
      "Checkbox.Border.Default",
      "Checkbox.Border.Default.Dark",
      "Component.borderColor",
      "Component.focusedBorderColor",
      "Component.hoverIconColor",
      "DebuggerTabs.active.background", // deprecated
      "DebuggerTabs.selectedBackground", // deprecated
      "DefaultTabs.hoverColor", // not implemented
      "DefaultTabs.hoverMaskColor", // not implemented
      "EditorGroupsTabs.hoverBackground",
      "EditorGroupsTabs.hoverColor",
      "EditorTabs.hoverBackground",
      "EditorTabs.hoverColor", // deprecated
      "EditorTabs.hoverMaskColor",
      "Focus.color", // deprecated
      "Github.List.tallRow.selectionBackground.unfocused", // deprecated
      "MemoryIndicator.usedBackground",
      "MemoryIndicator.usedColor", // deprecated
      "MnemonicIcon.background",
      "BookmarkMnemonicIcon.background",
      "MnemonicIcon.borderColor",
      "BookmarkMnemonicIcon.borderColor",
      "Outline.focusedColor", // deprecated
      "ParameterInfo.currentOverloadBackground",
      "Plugins.Button.installFocusedBackground",
      "Plugins.eapTagBackground",
      "Plugins.paidTagBackground",
      "Plugins.tagBackground",
      "Plugins.trialTagBackground",
      "ProgressBar.halfColor", // deprecated
      "ProgressBar.selectionBackground",
      "ProgressBar.trackColor",
      "SearchEverywhere.Tab.active.background", // deprecated
      "SearchEverywhere.Tab.selected.background", // deprecated
      "SearchEverywhere.Tab.selectedBackground",
      "Slider.trackDisabled", // deprecated
      "SpeedSearch.background",
      "StatusBar.hoverBackground",
      "TabbedPane.contentAreaColor",
      "TabbedPane.hoverColor",
      "TabbedPane.selectHighlight", // deprecated
      "TabbedPane.selectedColor", // deprecated
      "TableHeader.borderColor", // deprecated
      "TextField.separatorColor", // deprecated
      "ToolWindow.HeaderTab.hoverBackground",
      "ToolWindow.HeaderTab.hoverInactiveBackground",
      "UIDesigner.Component.hoverBorderColor",
      "UIDesigner.Connector.hoverBorderColor",
      "UIDesigner.Panel.graphLines",
      "UIDesigner.motion.hoverBorderColor",
      "UIDesigner.motion.light.borderColor",
      "UIDesigner.motion.ourCS_SelectedBorder.pressedBorderColor",
      "VersionControl.GitCommits.graphColor",
      "VersionControl.Ref.backgroundBase", // deprecated
      "VersionControl.RefLabel.backgroundBase",
      "WelcomeScreen.Projects.actions.selectionBackground"
    )

  /**
   * Get resources using the tree selected row color
   */
  @JvmStatic
  val treeSelectionResources: Set<String>
    get() = setOf(
      "List.hoverBackground",
      "Plugins.hoverBackground",
      "Table.hoverBackground",
      "Tree.hoverBackground",
      "Tree.selectionBackground",
      "UIDesigner.List.selectionBackground",
      "UIDesigner.motion.CSPanel.SelectedBackground",
      "VersionControl.Log.Commit.hoveredBackground"
    )

  /**
   * Get notifications colors resources
   */
  @JvmStatic
  val notificationsResources: Set<String>
    get() = setOf(
      "Canvas.Tooltip.background",
      "GotItTooltip.background",
      "GotItTooltip.borderColor",
      "Lesson.Tooltip.background",
      "Lesson.Tooltip.borderColor",
      "Notification.background",
      "Notification.borderColor",
      "Notifications.background", // deprecated
      "Notifications.borderColor", // deprecated
      "SearchField.errorBackground",
      "ToolTip.background",
      "UIDesigner.motion.Notification.background",
      "ValidationTooltip.errorBackground",
      "ValidationTooltip.errorBackgroundColor", // deprecated
      "ValidationTooltip.errorBorderColor",
      "ValidationTooltip.warningBackground",
      "ValidationTooltip.warningBackgroundColor", // deprecated
      "ValidationTooltip.warningBorderColor"
    )

  /**
   * Excluded resources
   */
  @JvmStatic
  val excludedResources: Set<String>
    get() = setOf(
      "FileColor.excluded"
    )
}
