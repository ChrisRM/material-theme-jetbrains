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

public final class SolarizedLightTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "FDF4DF"; // 38, 50, 56
  public static final String FOREGROUND = "586e75"; // 176, 190, 197
  public static final String TEXT = "93a1a1"; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = "93a1a1"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "002b36";
  public static final String DISABLED = "E3DCC9"; // 65, 89, 103

  public SolarizedLightTheme() {
    super("solarized.light", "Solarized Light", false, "Solarized Light");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return SolarizedLightTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return SolarizedLightTheme.DISABLED;
  }

  @Override
  protected String getNotificationsColorString() {
    return "002b36";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "93a1a180";
  }

  @Override
  protected String getHighlightColorString() {
    return "F6F0DE";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "edead9";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "F6F0DE";
  }

  @Override
  protected String getContrastColorString() {
    return "eee8d5";
  }

  @Override
  protected String getDisabledColorString() {
    return "C9CCC3";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "F6F0DE";
  }

  @Override
  protected String getButtonColorString() {
    return "FEFBF1";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "002b36";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "93a1a1";
  }

  @Override
  protected String getTextColorString() {
    return "93a1a1";
  }

  @Override
  protected String getForegroundColorString() {
    return "586e75";
  }

  @Override
  protected String getBackgroundColorString() {
    return "fdf6e3";
  }
}
