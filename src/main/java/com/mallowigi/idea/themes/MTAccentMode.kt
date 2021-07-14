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

package com.mallowigi.idea.themes;

import com.google.common.collect.Sets;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NonNls;

import java.awt.*;
import java.util.Collections;
import java.util.Set;

@SuppressWarnings({"HardCodedStringLiteral",
  "DuplicateStringLiteralInspection",
  "MagicNumber"})
public enum MTAccentMode {
  ACCENTS;

  public static final Set<String> SECOND_ACCENT_RESOURCES = Collections.unmodifiableSet(
    Sets.newHashSet(
      "CompletionPopup.matchForeground",
      "CompletionPopup.matchSelectedForeground", // deprecated
      "CompletionPopup.matchSelectionForeground",
      "EditorTabs.active.underlineColor", // deprecated
      "EditorTabs.inactiveUnderlineColor",
      "EditorTabs.underlineColor",
      "link.foreground",
      "Link.foreground",
      "Link.activeForeground",
      "Link.hoverForeground",
      "Link.pressedForeground",
      "Link.visitedForeground",
      "Notification.MoreButton.foreground",
      "Notification.linkForeground", // deprecated
      "Notification.Link.foreground", //deprecated
      "TabbedPane.underlineColor"
    )
  );
  public static final Set<String> SELECTION_RESOURCES = Collections.unmodifiableSet(
    Sets.newHashSet(
      "DefaultTabs.underlinedTabForeground",
      "EditorTabs.active.foreground", // deprecated
      "EditorTabs.selectedForeground",
      "EditorTabs.underlinedTabForeground",
      "Notification.foreground",
      "Tree.modifiedItemForeground",
      "UIDesigner.motion.CSPanel.SelectedFocusBackground",
      "WelcomeScreen.Projects.actions.selectionBackground"
    )
  );
  @NonNls
  public static final Set<String> ACCENT_EXTRA_RESOURCES = Collections.unmodifiableSet(
    Sets.newHashSet(
      "Autocomplete.selectionBackground",
      "Button.default.endBackground",
      "Button.default.startBackground",
      "DebuggerTabs.underlinedTabBackground",
      "DefaultTabs.hoverBackground",
      "DefaultTabs.underlinedTabBackground",
      "Dialog.titleColor",
      "EditorTabs.active.background", // deprecated
      "EditorTabs.hoverColor",
      "EditorTabs.hoverMaskColor",
      "EditorTabs.selectedBackground",
      "EditorTabs.underlinedTabBackground",
      "Github.List.tallRow.selectionBackground",
      "Outline.focusedColor", // deprecated
      "Plugins.Button.installFillBackground",
      "Table.focusCellBackground",
      "Table.highlightOuter",
      "Table.lightSelectionBackground", // deprecated
      "Table.selectionBackground",
      "WelcomeScreen.Projects.selectionBackground"
    ));

  @NonNls
  public static final Set<String> ACCENT_TRANSPARENT_EXTRA_RESOURCES = Collections.unmodifiableSet(
    Sets.newHashSet(
      "CompletionPopup.selectionBackground",
      "List.selectionBackground",
      "Menu.selectionBackground",
      "MenuItem.selectionBackground",
      "Tree.selectionBackground",
      "Toolbar.Floating.background"
    ));
  @NonNls
  public static final Set<String> DARKER_ACCENT_RESOURCES = Collections.unmodifiableSet(
    Sets.newHashSet(
      "EditorTabs.background",
      "EditorTabs.borderColor",
      "EditorTabs.inactiveColoredFileBackground",
      "DefaultTabs.background",
      "DefaultTabs.borderColor",
      "Notification.background",
      "Notification.borderColor"
    ));

  public static MTAccentMode getInstance() {
    return ServiceManager.getService(MTAccentMode.class);
  }

  public static Color getSelectionColor() {
    return new JBColor(0x111111, 0xFFFFFF);
  }

}
