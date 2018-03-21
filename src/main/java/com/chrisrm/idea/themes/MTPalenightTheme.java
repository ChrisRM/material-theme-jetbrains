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

public final class MTPalenightTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "292D3E"; // 41, 45, 62
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String TEXT = "676E95";  // 103, 110, 149
  public static final String SELECTION_BACKGROUND = "676E95"; // 103, 110, 149
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "2f2e43";

  public MTPalenightTheme() {
    super("mt.palenight", "Material Palenight", true, "Material Palenight");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return MTPalenightTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return MTPalenightTheme.DISABLED;
  }

  @Override
  protected String getNotificationsColorString() {
    return "202331";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "676E9550";
  }

  @Override
  protected String getHighlightColorString() {
    return "444267";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "2b2a3e";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "414863";
  }

  @Override
  protected String getContrastColorString() {
    return "202331";
  }

  @Override
  protected String getDisabledColorString() {
    return "515772";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "34324a";
  }

  @Override
  protected String getButtonColorString() {
    return "303348";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "3C435E";
  }

  @Override
  protected String getTextColorString() {
    return "676E95";
  }

  @Override
  protected String getForegroundColorString() {
    return "A6ACCD";
  }

  @Override
  protected String getBackgroundColorString() {
    return "292D3E";
  }
}
