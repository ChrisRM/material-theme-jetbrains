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
package com.mallowigi.idea.themes.themes

import com.mallowigi.idea.utils.MTUiUtils

/**
 * Abstract theme for light themes
 *
 */
abstract class MTAbstractLightTheme : MTAbstractTheme() {
  private val selectionForegroundResources: Set<String>
    get() = setOf(
      "Button.highlight",
      "Button.mt.selectedForeground",
      "CompletionPopup.selectedForeground",
      "CompletionPopup.selectionForeground",
      "CompletionPopup.selectedGrayedForeground",
      "CompletionPopup.selectionGrayForeground",
      "CompletionPopup.selectionInfoForeground",
      "Counter.foreground",
      "Github.List.tallRow.selectionForeground",
      "Github.List.tallRow.selectionForeground.unfocused",
      "List.selectionForeground",
      "Menu.acceleratorSelectionForeground",
      "Menu.selectionForeground",
      "MenuItem.acceleratorSelectionForeground",
      "MenuItem.selectionForeground",
      "Plugins.selectionForeground",
      "SearchEverywhere.Tab.active.foreground",
      "SearchEverywhere.Tab.selectedForeground",
      "SearchEverywhere.Tab.selected.foreground",
      "Tree.selectionForeground",
      "Tree.selectionInactiveForeground",
      "material.selectionForeground"
    )

  private val secondSelectionForegroundResources: Set<String>
    get() = setOf(
      "Button.darcula.selectedButtonForeground",
      "Button.default.foreground",
      "CheckBox.darcula.borderColor1",
      "CheckBox.foreground",
      "CheckBoxMenuItem.foreground",
      "CheckBoxMenuItem.selectionForeground",
      "ComboBox.selectionForeground",
      "EditorPane.selectionForeground",
      "FormattedTextField.selectionForeground",
      "Label.selectedForeground",
      "List.selectionInactiveForeground",
      "PasswordField.selectionForeground",
      "Plugins.Tab.active.foreground",
      "Plugins.Tab.selectedForeground",
      "Spinner.selectionForeground",
      "TabbedPane.selectedForeground",
      "Table.focusCellForeground",
      "Table.lightSelectionForeground",
      "Table.selectionForeground",
      "TableHeader.focusCellForeground",
      "TextArea.selectionForeground",
      "TextField.selectionForeground",
      "TextPane.selectionForeground",
      "ToolWindow.Button.selectedForeground",
      "VersionControl.Ref.foreground",
      "VersionControl.RefLabel.foreground",
      "VersionControl.HgLog.bookmarkIconColor"
    )

  public override fun buildAllResources() {
    super.buildAllResources()
    MTUiUtils.buildResources(selectionForegroundResources, selectionForegroundColorResource)
    MTUiUtils.buildResources(secondSelectionForegroundResources, secondSelectionForegroundColorResource)
  }

}
