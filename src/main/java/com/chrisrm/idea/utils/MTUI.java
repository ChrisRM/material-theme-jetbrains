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
}
