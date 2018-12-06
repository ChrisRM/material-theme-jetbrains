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

package com.chrisrm.idea.utils;

import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

@SuppressWarnings({"StaticMethodOnlyUsedInOneClass",
    "EmptyClass"})
public final class MTUI {
  public enum Tree {
    DEFAULT;

    @NotNull
    public static Color getSelectionBackground() {
      return ColorUtil.withAlpha(UIManager.getColor("Tree.selectionBackground"), 0.25);
    }
  }

  public enum ActionButton {
    DEFAULT;

    @NonNls
    public static final String HOVER_BACKGROUND = "ActionButton.hoverBackground";
    @NonNls
    public static final String HOVER_BORDER_COLOR = "ActionButton.hoverBorderColor";

    @NotNull
    public static Color hoverBackground() {
      return JBColor.namedColor(HOVER_BACKGROUND, 0x00000000);
    }

    @NotNull
    public static Color hoverBorderColor() {
      return JBColor.namedColor(HOVER_BORDER_COLOR, 0x00000000);
    }
  }

  public enum TextField {
    DEFAULT;
    @NonNls
    public static final String TEXT_FIELD_SEPARATOR_COLOR = "TextField.separatorColor";
    @NonNls
    public static final String TEXT_FIELD_SELECTED_SEPARATOR_COLOR = "TextField.selectedSeparatorColor";


    public static Color getBorderColor(final boolean enabled) {
      return enabled ? UIManager.getColor(TEXT_FIELD_SEPARATOR_COLOR) : UIManager.getColor("TextField.separatorColorDisabled");
    }

    public static Color getSelectedBorderColor() {
      return UIManager.getColor(TEXT_FIELD_SELECTED_SEPARATOR_COLOR);
    }
  }

  public enum List {
    DEFAULT;

    @NonNls
    public static final String LIST_SELECTION_BACKGROUND_PAINTER = "List.sourceListSelectionBackgroundPainter";
    @NonNls
    public static final String LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER = "List.sourceListFocusedSelectionBackgroundPainter";

    public static Border getListSelectionPainter() {
      return UIManager.getBorder(LIST_SELECTION_BACKGROUND_PAINTER);
    }

    public static Border getListFocusedSelectionPainter() {
      return UIManager.getBorder(LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER);
    }
  }
}
