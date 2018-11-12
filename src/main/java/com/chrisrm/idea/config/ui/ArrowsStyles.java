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

package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.config.ui.arrows.*;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

@SuppressWarnings({"DuplicateStringLiteralInspection",
    "NonSerializableFieldInSerializableClass"})
public enum ArrowsStyles {
  MATERIAL("Material", new MaterialArrowsStyle()),
  DARCULA(MTUiUtils.DARCULA, new DarculaArrowsStyle()),
  PLUSMINUS("Plus-Minus", new PlusMinusArrowsStyle()),
  ARROWS("Arrows", new ArrowsArrowsStyle()),
  NONE("None", new NoneArrowsStyle());


  private final String type;
  private final ArrowsStyle arrowsStyle;

  ArrowsStyles(@NonNls final String type, final ArrowsStyle arrowsStyle) {
    this.type = type;
    this.arrowsStyle = arrowsStyle;
  }

  @Override
  public String toString() {
    return type;
  }

  public Icon getExpandIcon() {
    return arrowsStyle.getExpandIcon();
  }

  public Icon getCollapseIcon() {
    return arrowsStyle.getCollapseIcon();
  }

  public Icon getSelectedExpandIcon() {
    return arrowsStyle.getSelectedExpandIcon();
  }

  public Icon getSelectedCollapseIcon() {
    return arrowsStyle.getSelectedCollapseIcon();
  }

  public Icon getIcon() {
    final Icon icon = getExpandIcon();
    if (icon == null) {
      return IconLoader.getTransparentIcon(AllIcons.Mac.Tree_white_down_arrow, 0.0f);
    }
    return icon;
  }
}
