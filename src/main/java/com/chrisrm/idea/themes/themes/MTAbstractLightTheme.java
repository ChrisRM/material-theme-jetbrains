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

package com.chrisrm.idea.themes.themes;

import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

abstract class MTAbstractLightTheme extends MTAbstractTheme {
  @Override
  public void buildAllResources() {
    super.buildAllResources();
    buildResources(getSelectionForegroundResources(), getSelectionForegroundColorResource());
    buildResources(getSecondSelectionForegroundResources(), getSecondSelectionForegroundColorResource());
  }

  private static Set<String> getSelectionForegroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Button.highlight",
            "Button.mt.selectedForeground",
            "CompletionPopup.selectedForeground", //deprecated
            "CompletionPopup.selectionForeground",
            "CompletionPopup.selectedGrayedForeground", //deprecated
            "CompletionPopup.selectionGrayForeground", // deprecated
            "CompletionPopup.selectionInfoForeground",
            "Counter.foreground",
            "Github.List.tallRow.selectionForeground", // deprecated
            "Github.List.tallRow.selectionForeground.unfocused", //deprecated
            "List.selectionForeground",
            "Menu.acceleratorSelectionForeground",
            "Menu.selectionForeground",
            "MenuItem.acceleratorSelectionForeground",
            "MenuItem.selectionForeground",
            "Plugins.selectionForeground", // deprecated
            "SearchEverywhere.Tab.active.foreground", // deprecated
            "SearchEverywhere.Tab.selectedForeground",
            "SearchEverywhere.Tab.selected.foreground", // deprecated
            "Tree.selectionForeground",
            "Tree.selectionInactiveForeground",
            "material.selectionForeground"
        ));

  }

  private static Set<String> getSecondSelectionForegroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Button.darcula.selectedButtonForeground", // deprecated
            "Button.default.foreground",
            "CheckBox.darcula.borderColor1", // deprecated
            "CheckBox.foreground",
            "CheckBoxMenuItem.foreground",
            "CheckBoxMenuItem.selectionForeground",
            "EditorPane.selectionForeground",
            "FormattedTextField.selectionForeground",
            "Label.selectedForeground",
            "List.selectionInactiveForeground",
            "PasswordField.selectionForeground",
            "Plugins.Tab.active.foreground", // deprecated
            "Plugins.Tab.selectedForeground",
            "Spinner.selectionForeground",
            "TabbedPane.selectedForeground", // deprecated
            "Table.focusCellForeground",
            "Table.lightSelectionForeground",
            "Table.selectionForeground",
            "TableHeader.focusCellForeground",
            "TextArea.selectionForeground",
            "TextField.selectionForeground",
            "TextPane.selectionForeground",
            "ToolWindow.Button.selectedForeground",
            "VersionControl.Ref.foreground", //deprecated
            "VersionControl.RefLabel.foreground",
            "VersionControl.HgLog.bookmarkIconColor"
        ));
  }
}
