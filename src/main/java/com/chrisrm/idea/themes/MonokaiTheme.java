/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
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
  public static final String DISABLED = "727072";

  public MonokaiTheme() {
    super("monokai", "Material Monokai Pro", true);
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
  protected String getNotificationsColorString() {
    return "363437";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "5B595C50";
  }

  @Override
  protected String getHighlightColorString() {
    return "59575A";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "363437";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "5B595C";
  }

  @Override
  protected String getContrastColorString() {
    return "221F22";
  }

  @Override
  protected String getDisabledColorString() {
    return "727072";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "403E41";
  }

  @Override
  protected String getButtonColorString() {
    return "403E41";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "5B595C";
  }

  @Override
  protected String getTextColorString() {
    return "727072";
  }

  @Override
  protected String getForegroundColorString() {
    return "C1C0C0";
  }

  @Override
  protected String getBackgroundColorString() {
    return "2D2A2E";
  }
}
