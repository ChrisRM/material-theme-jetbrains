/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.MTThemeManager;
import com.intellij.openapi.actionSystem.AnActionEvent;

public final class MTDarkerThemeAction extends MTAbstractThemeAction {
  public static final String BACKGROUND = "212121"; // 33, 33, 33
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String CARET = "FFCC00"; // 255, 204, 0
  public static final String BORDER = "1B1B1B"; // 27, 27, 27
  public static final String TEXT = "616161"; // 97, 97, 97
  public static final String SELECTION_BACKGROUND = "424242"; // 66, 66, 66
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "B0BEC5"; // 176, 190, 197
  public static final String SUB_LABEL = "616161"; // 97, 97, 97
  public static final String DISABLED = "323232"; // 65, 89, 103

  public static final String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static final String STATUS_LABEL = "616161"; // 97, 97, 97
  public static final String INPUT_BORDER = "484848"; //72, 72, 72

  public static final String BUTTON_BACKGROUND = "2B2B2B"; // 43, 43, 43
  public static final String BUTTON_FOREGROUND = "616161"; // 97, 97, 97
  public static final String BUTTON_SELECTED = "383838"; // 56,56,56

  public static final String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  @Override
  public boolean isSelected(final AnActionEvent e) {
    return MTConfig.getInstance().getSelectedTheme() == MTTheme.DARKER;
  }

  @Override
  public void setSelected(final AnActionEvent e, final boolean state) {
    MTThemeManager.getInstance().activate(MTTheme.DARKER);
  }
}
