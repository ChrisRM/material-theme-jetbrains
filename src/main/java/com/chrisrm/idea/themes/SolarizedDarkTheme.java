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

public final class SolarizedDarkTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "002B36"; // 38, 50, 56
  public static final String FOREGROUND = "839496"; // 176, 190, 197
  public static final String TEXT = "586e75"; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = "2E4C52"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "083F4D"; // 65, 89, 103

  public SolarizedDarkTheme() {
    super("solarized.dark", "Material Solarized Dark", true, "Solarized Dark", "/icons/actions/themes/solarized_dark.svg");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return SolarizedDarkTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return SolarizedDarkTheme.DISABLED;
  }

  @Override
  public String getAccentColor() {
    return "d33682";
  }

  @Override
  public String getExcludedColor() {
    return DISABLED;
  }

  @Override
  public int getOrder() {
    return 7;
  }

  @Override
  public String getNotificationsColorString() {
    return "2E4C52";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "2E4C5280";
  }

  @Override
  public String getHighlightColorString() {
    return "11353F";
  }

  @Override
  public String getSecondBorderColorString() {
    return "0D3640";
  }

  @Override
  public String getTableSelectedColorString() {
    return "11353F";
  }

  @Override
  public String getContrastColorString() {
    return "00252E";
  }

  @Override
  public String getDisabledColorString() {
    return "2E5861";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "003745";
  }

  @Override
  public String getButtonColorString() {
    return "073642";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "2E4C52";
  }

  @Override
  public String getTextColorString() {
    return "586e75";
  }

  @Override
  public String getForegroundColorString() {
    return "839496";
  }

  @Override
  public String getBackgroundColorString() {
    return "002B36";
  }
}
