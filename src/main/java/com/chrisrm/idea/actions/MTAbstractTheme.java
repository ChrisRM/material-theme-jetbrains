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
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;

public abstract class MTAbstractTheme extends ToggleAction {
  public static final String BACKGROUND = ""; // 38, 50, 56
  public static final String FOREGROUND = ""; // 176, 190, 197
  public static final String CARET = ""; // 255, 204, 0
  public static final String BORDER = ""; // 34, 45, 51
  public static final String TEXT = ""; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = ""; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "";
  public static final String LABEL = ""; // 176, 190, 197
  public static final String SUB_LABEL = ""; // 84, 110, 122
  public static final String DISABLED = ""; // 65, 89, 103
  public static final String SIDEBAR_HEADING = ""; // 207, 216, 220
  public static final String STATUS_LABEL = ""; // 120, 144, 156
  public static final String INPUT_BORDER = ""; //55, 71, 79
  public static final String BUTTON_BACKGROUND = ""; // 44, 60, 65
  public static final String BUTTON_FOREGROUND = ""; // 96, 125, 139
  public static final String BUTTON_SELECTED = ""; // 49, 69, 73
  public static final String ACCENT_COLOR = ""; // 128, 203, 196

  /**
   * Set button disabled if material theme is disabled
   *
   * @param e
   */
  @Override
  public void update(final AnActionEvent e) {
    e.getPresentation().setEnabled(MTConfig.getInstance().isMaterialTheme());
  }
}
