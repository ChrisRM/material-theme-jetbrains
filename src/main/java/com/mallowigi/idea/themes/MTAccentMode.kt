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
package com.mallowigi.idea.themes

import com.intellij.openapi.application.ApplicationManager
import com.intellij.ui.JBColor
import java.awt.Color

object MTAccentMode {
  @JvmField
  val SECOND_ACCENT_RESOURCES: Set<String> = setOf(
    "CompletionPopup.matchForeground",
    "CompletionPopup.matchSelectedForeground",  // deprecated
    "CompletionPopup.matchSelectionForeground",
    "EditorTabs.active.underlineColor",  // deprecated
    "EditorTabs.inactiveUnderlineColor",
    "EditorTabs.underlineColor",
    "link.foreground",
    "Link.foreground",
    "Link.activeForeground",
    "Link.hoverForeground",
    "Link.pressedForeground",
    "Link.visitedForeground",
    "Notification.MoreButton.foreground",
    "Notification.linkForeground",  // deprecated
    "Notification.Link.foreground",  //deprecated
    "TabbedPane.underlineColor"

  )

  @JvmField
  val SELECTION_RESOURCES: Set<String> = setOf(
    "DefaultTabs.underlinedTabForeground",
    "EditorTabs.active.foreground",  // deprecated
    "EditorTabs.selectedForeground",
    "EditorTabs.underlinedTabForeground",
    "Notification.foreground",
    "Tree.modifiedItemForeground",
    "UIDesigner.motion.CSPanel.SelectedFocusBackground",
    "WelcomeScreen.Projects.actions.selectionBackground"
  )


  @JvmField
  val ACCENT_EXTRA_RESOURCES: Set<String> = setOf(
    "Autocomplete.selectionBackground",
    "Button.default.endBackground",
    "Button.default.startBackground",
    "DebuggerTabs.underlinedTabBackground",
    "DefaultTabs.hoverBackground",
    "DefaultTabs.underlinedTabBackground",
    "Dialog.titleColor",
    "EditorTabs.active.background",  // deprecated
    "EditorTabs.hoverColor",
    "EditorTabs.hoverMaskColor",
    "EditorTabs.selectedBackground",
    "EditorTabs.underlinedTabBackground",
    "Github.List.tallRow.selectionBackground",
    "Outline.focusedColor",  // deprecated
    "Plugins.Button.installFillBackground",
    "Table.focusCellBackground",
    "Table.highlightOuter",
    "Table.lightSelectionBackground",  // deprecated
    "Table.selectionBackground",
    "WelcomeScreen.Projects.selectionBackground"

  )

  @JvmField
  val ACCENT_TRANSPARENT_EXTRA_RESOURCES: Set<String> = setOf(
    "CompletionPopup.selectionBackground",
    "List.selectionBackground",
    "Menu.selectionBackground",
    "MenuItem.selectionBackground",
    "Tree.selectionBackground",
    "Toolbar.Floating.background"
  )


  @JvmField
  val DARKER_ACCENT_RESOURCES: Set<String> = setOf(
    "EditorTabs.background",
    "EditorTabs.borderColor",
    "EditorTabs.inactiveColoredFileBackground",
    "DefaultTabs.background",
    "DefaultTabs.borderColor",
    "Notification.background",
    "Notification.borderColor"
  )

  val instance: MTAccentMode
    get() = ApplicationManager.getApplication().getService(MTAccentMode::class.java)

  @JvmStatic
  val selectionColor: Color
    get() = JBColor(0x111111, 0xFFFFFF)
}

