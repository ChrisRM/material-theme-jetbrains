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
package com.mallowigi.idea.themes.lists

/**
 * Accent resources
 *
 */
object AccentResources {
  /**
   * All accent resources
   */
  @JvmField
  val accentedResources: Set<String> = setOf(
    "ActionButton.pressedBackground",
    "ActionButton.pressedBorderColor",
    "BookmarkIcon.background",
    "Bookmark.iconBackground",
    "BookmarkMnemonicCurrent.background",
    "BookmarkMnemonicCurrent.borderColor",
    "CheckBox.background.selected",
    "CheckBox.borderColor.selected",
    "CheckBox.disabledBorderColor.selected",
    "CheckBox.focused.background.selected",
    "CheckBox.focusedArmed.background.selected",
    "CheckBox.inactiveFillColor.selected",
    "Checkbox.Background.Selected",
    "Checkbox.Background.Selected.Dark",
    "Checkbox.Border.Selected",
    "Checkbox.Border.Selected.Dark",
    "Checkbox.Focus.Thin.Selected",
    "Checkbox.Focus.Thin.Selected.Dark",
    "Checkbox.Focus.Wide.Selected",
    "Checkbox.Focus.Wide.Selected.Dark",
    "CodeWithMe.AccessEnabled.accessDot",
    "ComboBox.darcula.hoveredArrowButtonForeground", // deprecated
    "ComboBox.modifiedItemForeground",
    "CompletionPopup.matchForeground",
    "CompletionPopup.matchSelectedForeground", // deprecated
    "CompletionPopup.matchSelectionForeground",
    "Component.focusColor",
    "Component.focusedBorderColor",
    "Counter.background",
    "CurrentMnemonic.background",
    "CurrentMnemonic.borderColor",
    "Debugger.Variables.changedValueForeground",
    "Debugger.Variables.modifyingValueForeground",
    "Debugger.Variables.valueForeground",
    "DefaultTabs.inactiveUnderlineColor",
    "DefaultTabs.underlineColor",
    "DragAndDrop.borderColor",
    "EditorGroupsTabs.inactiveUnderlineColor",
    "EditorGroupsTabs.underlineColor",
    "EditorPane.caretForeground",
    "EditorTabs.active.underlineColor", // deprecated
    "EditorTabs.inactiveUnderlineColor",
    "EditorTabs.underlineColor",
    "Focus.defaultButtonBorderColor", // deprecated
    "FormattedTextField.caretForeground",
    "Git.Log.Ref.LocalBranch", // deprecated
    "GotItTooltip.linkForeground",
    "Hg.Log.Ref.Branch", // deprecated
    "Hyperlink.linkColor", // deprecated
    "Label.errorForeground",
    "Lesson.Badge.newLessonBackground",
    "Link.activeForeground",
    "Link.hoverForeground",
    "Link.pressedForeground",
    "Link.visitedForeground",
    "LiveIndicator.color",
    "NavBar.selectedColor", // deprecated
    "NewPSD.warning",
    "Notification.Error.foreground", // deprecated
    "Notification.Link.foreground", // deprecated
    "Notification.errorForeground",
    "Notification.linkForeground",
    "Outline.focusedColor",
    "ParameterInfo.currentParameterForeground",
    "ParameterInfo.highlightedColor", // deprecated
    "PasswordField.caretForeground",
    "Plugins.Button.updateBackground",
    "Plugins.tagForeground",
    "ProgressBar.foreground",
    "ProgressBar.indeterminateEndColor",
    "ProgressBar.indeterminateStartColor",
    "ProgressBar.progressColor",
    "PsiViewer.referenceHighlightColor",
    "RadioButton.darcula.selectionDisabledShadowColor", // deprecated
    "RadioButton.darcula.selectionEnabledColor", // deprecated
    "RadioButton.darcula.selectionEnabledShadowColor", // deprecated
    "RadioButton.focusColor", // deprecated
    "RadioButton.selectionDisabledShadowColor", // deprecated
    "RadioButton.selectionEnabledColor", // deprecated
    "RadioButton.selectionEnabledShadowColor", // deprecated
    "ScrollBar.Thumb.Hovered.background", // deprecated
    "ScrollBar.Thumb.NonOpaque.Hovered.background", // deprecated
    "SearchMatch.endBackground",
    "SearchMatch.endColor", // deprecated
    "SearchMatch.startBackground",
    "SearchMatch.startColor", // deprecated
    "Settings.Spotlight.borderColor",
    "Slider.buttonBorderColor",
    "Slider.buttonColor",
    "Slider.thumb",
    "TabbedPane.selectedColor", // deprecated
    "TabbedPane.underlineColor",
    "TextArea.caretForeground",
    "TextField.caretForeground",
    "TextField.selectedSeparatorColor", // deprecated
    "TextPane.caretForeground",
    "ToggleButton.on.background", // deprecated
    "ToggleButton.on.foreground", // deprecated
    "ToggleButton.onBackground",
    "ToggleButton.onForeground",
    "ToolWindow.HeaderTab.inactiveUnderlineColor",
    "ToolWindow.HeaderTab.underlineColor",
    "Tree.modifiedItemForeground",
    "UIDesigner.Panel.lines3d",
    "UIDesigner.motion.AddConstraintColor",
    "UIDesigner.motion.AddConstraintPlus",
    "UIDesigner.motion.Key.selectedForeground",
    "UIDesigner.motion.PositionMarkColor",
    "UIDesigner.motion.TimeCursor.End.selectedForeground",
    "UIDesigner.motion.TimeCursor.Start.selectedForeground",
    "UIDesigner.motion.TimeCursor.selectedForeground",
    "UIDesigner.motion.graphLine.lineSeparatorColor",
    "UIDesigner.motion.ourCS_SelectedFocusBorder.focusedBorderColor",
    "VersionControl.GitLog.localBranchIconColor",
    "VersionControl.HgLog.branchIconColor",
    "dropArea.base",
    "link",
    "link.foreground", // deprecated
    "link.hover.foreground", // deprecated
    "link.pressed.foreground", // deprecated
    "link.visited.foreground", // deprecated
    "material.tab.borderColor"

  )

  /**
   * Accent transparent resources
   */
  @JvmField
  val accentTransparentResources: Set<String> = setOf(
    "ActionButton.focusedBorderColor",
    "ActionButton.hoverBackground",
    "ActionButton.hoverBorderColor",
    "DragAndDrop.rowBackground",
    "Focus.Color", // deprecated
    "Focus.borderColor", // deprecated
    "Focus.color", // deprecated,
  )

  /**
   * Scrollbar resources
   */
  @JvmField
  val scrollbarResources: Set<String> = setOf(
    "ScrollBar.Mac.Transparent.thumbBorderColor",
    "ScrollBar.Mac.Transparent.thumbColor",
    "ScrollBar.Mac.thumbBorderColor",
    "ScrollBar.Mac.thumbColor",
    "ScrollBar.Transparent.thumbBorderColor",
    "ScrollBar.Transparent.thumbColor",
    "ScrollBar.thumbBorderColor",
    "ScrollBar.thumbColor"

  )

  /**
   * Scrollbar hover resources
   */
  @JvmField
  val scrollbarHoverResources: Set<String> = setOf(
    "ScrollBar.Mac.Transparent.hoverThumbBorderColor",
    "ScrollBar.Mac.Transparent.hoverThumbColor",
    "ScrollBar.Mac.hoverThumbBorderColor",
    "ScrollBar.Mac.hoverThumbColor",
    "ScrollBar.Transparent.hoverThumbBorderColor",
    "ScrollBar.Transparent.hoverThumbColor",
    "ScrollBar.hoverThumbBorderColor",
    "ScrollBar.hoverThumbColor"
  )

  /**
   * Outline button resources
   */
  @JvmField
  val outlineButtonResources: Set<String> = setOf(
    "Button.darcula.defaultFocusedOutlineColor", // deprecated
    "Button.darcula.focusedOutlineColor", // deprecated
    "Button.darcula.outlineDefaultEndColor", // deprecated
    "Button.darcula.outlineDefaultStartColor", // deprecated
    "Button.darcula.outlineEndColor", // deprecated
    "Button.darcula.outlineStartColor", // deprecated
    "Button.default.endBorderColor",
    "Button.default.focusColor",
    "Button.default.focusedBorderColor",
    "Button.default.startBorderColor",
    "Button.endBorderColor",
    "Button.focusedBorderColor",
    "Button.startBorderColor",
  )
}
