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

public final class MTDarkerTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "212121"; // 33, 33, 33
  public static final String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static final String TEXT = "616161"; // 97, 97, 97
  public static final String SELECTION_BACKGROUND = "424242"; // 66, 66, 66
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "323232"; // 65, 89, 103

  public MTDarkerTheme() {
    super("mt.darker", "Material Darker", true, "Material Darker");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return MTDarkerTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return MTDarkerTheme.DISABLED;
  }

  @Override
  protected String getNotificationsColorString() {
    return "1A1A1A";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "323232C0";
  }

  @Override
  protected String getHighlightColorString() {
    return "3F3F3F";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "292929";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "323232";
  }

  @Override
  protected String getContrastColorString() {
    return "1A1A1A";
  }

  @Override
  protected String getDisabledColorString() {
    return "474747";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "292929";
  }

  @Override
  protected String getButtonColorString() {
    return "2A2A2A";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "323232";
  }

  @Override
  protected String getTextColorString() {
    return "616161";
  }

  @Override
  protected String getForegroundColorString() {
    return "B0BEC5";
  }

  @Override
  protected String getBackgroundColorString() {
    return "212121";
  }
}
