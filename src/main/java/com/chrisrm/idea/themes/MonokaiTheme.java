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

public final class MonokaiTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "2D2A2E";
  public static final String FOREGROUND = "C1C0C0";
  public static final String TEXT = "727072";
  public static final String SELECTION_BACKGROUND = "5B595C";
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "3a3a3c";

  public MonokaiTheme() {
    super("monokai", "Material Monokai Pro", true, "Monokai Pro", "/icons/actions/themes/monokai.png");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return MonokaiTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return MonokaiTheme.DISABLED;
  }

  @Override
  public String getNotificationsColorString() {
    return "363437";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "5B595C50";
  }

  @Override
  public String getHighlightColorString() {
    return "59575A";
  }

  @Override
  public String getSecondBorderColorString() {
    return "363437";
  }

  @Override
  public String getTableSelectedColorString() {
    return "4A474B";
  }

  @Override
  public String getContrastColorString() {
    return "221F22";
  }

  @Override
  public String getDisabledColorString() {
    return "727072";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "403E41";
  }

  @Override
  public String getButtonColorString() {
    return "3E3B3F";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "5B595C";
  }

  @Override
  public String getTextColorString() {
    return "727072";
  }

  @Override
  public String getForegroundColorString() {
    return "C1C0C0";
  }

  @Override
  public String getBackgroundColorString() {
    return "2D2A2E";
  }
}
