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

package com.chrisrm.idea.themes.lists;

import com.google.common.collect.Sets;
import org.jetbrains.annotations.NonNls;

import java.util.Collections;
import java.util.Set;

public enum AccentResources {
  DEFAULT;

  @SuppressWarnings("DuplicateStringLiteralInspection")
  @NonNls
  public static final Set<String> ACCENT_RESOURCES = Collections.unmodifiableSet(
      Sets.newHashSet(
          "ActionButton.hoverBackground",
          "ActionButton.hoverBorderColor",
          "Button.darcula.defaultFocusedOutlineColor", // deprecated
          "Button.darcula.focusedOutlineColor", // deprecated
          "Button.darcula.outlineDefaultEndColor", // deprecated
          "Button.darcula.outlineDefaultStartColor", // deprecated
          "Button.darcula.outlineEndColor", // deprecated
          "Button.darcula.outlineStartColor", // deprecated
          "Button.default.focusedBorderColor",
          "Button.default.startBorderColor",
          "Button.default.endBorderColor",
          "Button.default.focusColor",
          "Button.endBorderColor",
          "Button.focusedBorderColor",
          "Button.startBorderColor",
          "CheckBox.darcula.backgroundColor1.selected", // deprecated
          "CheckBox.darcula.backgroundColor2.selected", // deprecated
          "CheckBox.darcula.borderColor.selected", // deprecated
          "CheckBox.darcula.disabledBorderColor.selected", // deprecated
          "CheckBox.darcula.focused.backgroundColor1.selected", // deprecated
          "CheckBox.darcula.focused.backgroundColor2.selected", // deprecated
          "CheckBox.darcula.focusedArmed.backgroundColor1.selected", // deprecated
          "CheckBox.darcula.focusedArmed.backgroundColor2.selected", // deprecated
          "ComboBox.modifiedItemForeground",
          "CompletionPopup.matchForeground",
          "CompletionPopup.matchSelectedForeground", // deprecated
          "CompletionPopup.matchSelectionForeground",
          "Component.focusColor",
          "Component.focusedBorderColor",
          "Counter.background",
          "DefaultTabs.inactiveUnderlineColor", // deprecated
          "DefaultTabs.underlineColor", // deprecated
          "EditorPane.caretForeground",
          "EditorTabs.active.underlineColor", // deprecated
          "EditorTabs.inactiveUnderlineColor", // deprecated
          "Focus.borderColor", // deprecated
          "Focus.color", // deprecated
          "Focus.Color", // deprecated
          "Focus.defaultButtonBorderColor", // deprecated
          "FormattedTextField.caretForeground",
          "Git.Log.Ref.LocalBranch", // deprecated
          "Hg.Log.Ref.Branch", //deprecated
          "Hyperlink.linkColor", // deprecated
          "Label.errorForeground",
          "Link.activeForeground",
          "Link.hoverForeground",
          "Link.pressedForeground",
          "Link.visitedForeground",
          "link.foreground", // deprecated
          "link.hover.foreground", // deprecated
          "link.pressed.foreground", // deprecated
          "link.visited.foreground", // deprecated
          "material.tab.borderColor",
          "NavBar.selectedColor", // deprecated
          "Notification.errorForeground",
          "Notification.Error.foreground", // deprecated
          "Notification.linkForeground", // deprecated
          "Notification.Link.foreground", //deprecated
          "ParameterInfo.highlightedColor", //deprecated
          "ParameterInfo.currentParameterForeground",
          "PasswordField.caretForeground",
          "Plugins.tagForeground",
          "Popup.Advertiser.foreground",
          "ProgressBar.foreground",
          "ProgressBar.indeterminateStartColor",
          "ProgressBar.indeterminateEndColor",
          "ProgressBar.progressColor",
          "RadioButton.darcula.selectionDisabledShadowColor", // deprecated
          "RadioButton.darcula.selectionEnabledColor", // deprecated
          "RadioButton.darcula.selectionEnabledShadowColor", // deprecated
          "RadioButton.selectionDisabledShadowColor", // deprecated
          "RadioButton.selectionEnabledColor", // deprecated
          "RadioButton.selectionEnabledShadowColor", // deprecated
          "RadioButton.focusColor", // deprecated
          "ScrollBar.Thumb.Hovered.background", // deprecated
          "ScrollBar.Thumb.NonOpaque.Hovered.background", // deprecated
          "SearchEverywhere.Advertiser.foreground",
          "SearchMatch.endBackground",
          "SearchMatch.endColor", // deprecated
          "SearchMatch.startBackground",
          "SearchMatch.startColor", // deprecated
          "Slider.thumb",
          "ComboBox.darcula.hoveredArrowButtonForeground", // deprecated
          "TabbedPane.selectedСolor", // deprecated
          "TextArea.caretForeground",
          "TextField.caretForeground",
          "TextField.selectedSeparatorColor", // deprecated
          "TextPane.caretForeground",
          "ToggleButton.on.foreground", // deprecated
          "ToggleButton.on.background", // deprecated
          "ToggleButton.onBackground",
          "ToggleButton.onForeground",
          "Tree.modifiedItemForeground",
          "VersionControl.GitLog.localBranchIconColor",
          "VersionControl.HgLog.branchIconColor"
      ));

  @NonNls
  public static final Set<String> ACCENT_EXTRA_RESOURCES = Collections.unmodifiableSet(
      Sets.newHashSet(
          "Button.focus",
          "Button.default.startBackground",
          "Button.default.endBackground"
      ));

  @NonNls
  public static final Set<String> SCROLLBAR_RESOURCES = Collections.unmodifiableSet(
      Sets.newHashSet(
          "ScrollBar.thumbColor",
          "ScrollBar.thumbBorderColor",
          "ScrollBar.trackColor",
          "ScrollBar.Transparent.thumbColor",
          "ScrollBar.Transparent.thumbBorderColor",
          "ScrollBar.Transparent.trackColor",
          "ScrollBar.Mac.thumbColor",
          "ScrollBar.Mac.thumbBorderColor",
          "ScrollBar.Mac.trackColor",
          "ScrollBar.Mac.Transparent.thumbColor",
          "ScrollBar.Mac.Transparent.thumbBorderColor",
          "ScrollBar.Mac.Transparent.trackColor"
      )
  );

  @NonNls
  public static final Set<String> SCROLLBAR_HOVER_RESOURCES = Collections.unmodifiableSet(
      Sets.newHashSet(
          "ScrollBar.hoverThumbColor",
          "ScrollBar.hoverThumbBorderColor",
          "ScrollBar.hoverTrackColor",
          "ScrollBar.Transparent.hoverThumbColor",
          "ScrollBar.Transparent.hoverThumbBorderColor",
          "ScrollBar.Transparent.hoverTrackColor",
          "ScrollBar.Mac.hoverThumbColor",
          "ScrollBar.Mac.hoverThumbBorderColor",
          "ScrollBar.Mac.hoverTrackColor",
          "ScrollBar.Mac.Transparent.hoverThumbColor",
          "ScrollBar.Mac.Transparent.hoverThumbBorderColor",
          "ScrollBar.Mac.Transparent.hoverTrackColor"
      )
  );
}
