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

public final class SolarizedLightTheme extends MTAbstractTheme {
  @Override
  public String getThemeId() {
    return "solarized.light";
  }

  @Override
  public String getBackgroundColorString() {
    return "fdf6e3";
  }

  @Override
  public String getForegroundColorString() {
    return "586e75";
  }

  @Override
  public String getTextColorString() {
    return "93a1a1";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "93a1a1";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "002b36";
  }

  @Override
  public String getButtonColorString() {
    return "FEFBF1";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "F6F0DE";
  }

  @Override
  public String getDisabledColorString() {
    return "C9CCC3";
  }

  @Override
  public String getContrastColorString() {
    return "eee8d5";
  }

  @Override
  public String getTableSelectedColorString() {
    return "F6F0DE";
  }

  @Override
  public String getSecondBorderColorString() {
    return "edead9";
  }

  @Override
  public String getHighlightColorString() {
    return "F6F0DE";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "93a1a180";
  }

  @Override
  public String getNotificationsColorString() {
    return "EDE8D4";
  }

  @Override
  public String getAccentColorString() {
    return "d33682";
  }

  @Override
  public String getExcludedColorString() {
    return "E3DCC9";
  }
}
