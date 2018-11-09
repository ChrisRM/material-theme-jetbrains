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

package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTAbstractTheme;

public final class OneDarkTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "383C4A"; // 38, 50, 56
  public static final String FOREGROUND = "D3DAE3"; // 176, 190, 197
  public static final String TEXT = "607D8B"; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = "D3DAE3"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "3c4150"; // 65, 89, 103

  public OneDarkTheme() {
    super("one.dark", "Atom One Dark", true, "Atom One Dark", "/icons/actions/themes/one_dark.svg");
  }

  @Override
  public String getAccentColorString() {
    return "2979ff";
  }

  @Override
  public String getExcludedColorString() {
    return DISABLED;
  }

  @Override
  public int getOrder() {
    return 6;
  }

  @Override
  public String getNotificationsColorString() {
    return "282C34";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "3A3F4B80";
  }

  @Override
  public String getHighlightColorString() {
    return "383D48";
  }

  @Override
  public String getSecondBorderColorString() {
    return "282C34";
  }

  @Override
  public String getTableSelectedColorString() {
    return "383E49";
  }

  @Override
  public String getContrastColorString() {
    return "21252B";
  }

  @Override
  public String getDisabledColorString() {
    return "6B727D";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "2F333D";
  }

  @Override
  public String getButtonColorString() {
    return "3A3F4B";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "3A3F4B";
  }

  @Override
  public String getTextColorString() {
    return "979FAD";
  }

  @Override
  public String getForegroundColorString() {
    return "979FAD";
  }

  @Override
  public String getBackgroundColorString() {
    return "282C34";
  }
}
