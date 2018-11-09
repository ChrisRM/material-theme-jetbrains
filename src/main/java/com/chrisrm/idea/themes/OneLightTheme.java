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

public final class OneLightTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "FAFAFA"; // 38, 50, 56
  public static final String FOREGROUND = "232324"; // 176, 190, 197
  public static final String TEXT = "9D9D9F"; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = "526FFF"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "232324";
  public static final String DISABLED = "DBDBDC"; // 65, 89, 103

  public OneLightTheme() {
    super("one.light", "Atom One Light", false, "Atom One Light", "/icons/actions/themes/one_light.svg");
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
    return 7;
  }

  @Override
  public String getNotificationsColorString() {
    return "F2F2F2";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "526FFF80";
  }

  @Override
  public String getHighlightColorString() {
    return "EAEAEB";
  }

  @Override
  public String getSecondBorderColorString() {
    return "DBDBDC";
  }

  @Override
  public String getTableSelectedColorString() {
    return "DBDBDC";
  }

  @Override
  public String getContrastColorString() {
    return "D3D4D5";
  }

  @Override
  public String getDisabledColorString() {
    return "424243";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "EAEAEB";
  }

  @Override
  public String getButtonColorString() {
    return "DBDBDC";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "526FFF";
  }

  @Override
  public String getTextColorString() {
    return "9D9D9F";
  }

  @Override
  public String getForegroundColorString() {
    return "232324";
  }

  @Override
  public String getBackgroundColorString() {
    return "FAFAFA";
  }
}
