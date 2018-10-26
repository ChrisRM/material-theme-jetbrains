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
import org.jetbrains.annotations.NotNull;

public final class DraculaTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "282A36"; // 38, 50, 56
  public static final String FOREGROUND = "F8F8F2"; // 176, 190, 197
  public static final String TEXT = "6272A4"; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = "44475A"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "8BE9FD";
  public static final String DISABLED = "6272A4"; // 65, 89, 103

  public DraculaTheme() {
    super("mt.custom", "Dracula", true, "Dracula", "/icons/actions/themes/dracula.svg");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return DraculaTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return DraculaTheme.DISABLED;
  }

  @Override
  public String getAccentColor() {
    return "BD93F9";
  }

  @Override
  public String getExcludedColor() {
    return "34353D";
  }

  @Override
  public int getOrder() {
    return 6;
  }

  @Override
  public String getNotificationsColorString() {
    return "1D2228";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "44475A50";
  }

  @Override
  public String getHighlightColorString() {
    return "6272A4";
  }

  @Override
  public String getSecondBorderColorString() {
    return "21222C";
  }

  @Override
  public String getTableSelectedColorString() {
    return "44475A";
  }

  @Override
  public String getContrastColorString() {
    return "191A21";
  }

  @Override
  public String getDisabledColorString() {
    return "6272A4";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "282A36";
  }

  @Override
  public String getButtonColorString() {
    return "393C4B";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "8BE9FD";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "44475A";
  }

  @Override
  public String getTextColorString() {
    return "6272A4";
  }

  @Override
  public String getForegroundColorString() {
    return "F8F8F2";
  }

  @Override
  public String getBackgroundColorString() {
    return "282A36";
  }
}
