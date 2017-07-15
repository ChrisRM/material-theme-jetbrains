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

import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class MTPalenightTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "292D3E"; // 41, 45, 62
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String CARET = "FFCC00"; // 255, 204, 0
  public static final String BORDER = "202226"; // 32, 34, 38
  public static final String TEXT = "676E95";  // 103, 110, 149
  public static final String SELECTION_BACKGROUND = "676E95"; // 103, 110, 149
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "A6ACCD"; // 166, 172, 205
  public static final String SUB_LABEL = "676E95";  // 103, 110, 149
  public static final String DISABLED = "2f2e43";

  public static final String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static final String STATUS_LABEL = "676E95"; // 103, 110, 149
  public static final String INPUT_BORDER = "373B4D"; // 55, 59, 77

  public static final String BUTTON_BACKGROUND = "2D3144";
  public static final String BUTTON_FOREGROUND = "676E95"; // 103, 110, 149
  public static final String BUTTON_SELECTED = "32374D"; // 50, 55, 77

  public static final String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    MTThemeManager.getInstance().activate(MTTheme.PALENIGHT);
  }
}
