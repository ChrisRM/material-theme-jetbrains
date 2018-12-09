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

package com.chrisrm.idea.themes.lists;

import com.chrisrm.idea.utils.MTUI;
import com.google.common.collect.Sets;
import org.jetbrains.annotations.NonNls;

import java.util.Collections;
import java.util.Set;

public enum ContrastResources {
  DEFAULT;

  @SuppressWarnings("DuplicateStringLiteralInspection")
  @NonNls
  public static final Set<String> CONTRASTED_RESOURCES = Collections.unmodifiableSet(
      Sets.newHashSet(
          "ActionToolbar.background",
          "activeCaption",
          MTUI.Spinner.COMBO_BOX_ARROW_BACKGROUND,
          MTUI.Spinner.COMBO_BOX_DISABLED_ARROW_BACKGROUND,
          MTUI.Spinner.COMBO_BOX_EDITABLE_ARROW_BACKGROUND,
          "ComboBox.arrowFillColor",
          "ComboBox.background",
          "ComboBox.ArrowButton.nonEditableBackground",
          "desktop",
          "Editor.background",
          "EditorPane.background",
          "FormattedTextField.background",
          "MenuBar.background",
          "MenuBar.shadow",
          "PasswordField.background",
          "Plugins.SearchField.background",
          "SidePanel.background",
          "SplitPane.highlight",
          "TabbedPane.borderColor",
          "TabbedPane.darkShadow",
          "TabbedPane.mt.tab.background",
          "TabbedPane.shadow",
          "TextArea.background",
          "TextField.background",
          "TextPane.background",
          "ToolBar.background",
          "ToolBar.background",
          "Tree.background",
          "Tree.textBackground",
          "Viewport.background",
          "window"
      ));
}
