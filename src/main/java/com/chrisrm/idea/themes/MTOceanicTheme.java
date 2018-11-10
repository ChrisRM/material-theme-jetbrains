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

public final class MTOceanicTheme extends MTAbstractTheme {
  @NotNull
  @Override
  public String getThemeId() {
    return "mt.oceanic";
  }

  @Override
  public String getBackgroundColorString() {
    return "263238";
  }

  @Override
  public String getForegroundColorString() {
    return "B0BEC5";
  }

  @Override
  public String getTextColorString() {
    return "607D8B";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "546E7A";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getButtonColorString() {
    return "2E3C43";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "32424A";
  }

  @Override
  public String getDisabledColorString() {
    return "415967";
  }

  @Override
  public String getContrastColorString() {
    return "1E272C";
  }

  @Override
  public String getTableSelectedColorString() {
    return "314549";
  }

  @Override
  public String getSecondBorderColorString() {
    return "2A373E";
  }

  @Override
  public String getHighlightColorString() {
    return "425B67";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "546E7A50";
  }

  @Override
  public String getNotificationsColorString() {
    return "1E272C";
  }

  @Override
  public String getAccentColorString() {
    return "009688";
  }

  @Override
  public String getExcludedColorString() {
    return "2E3C43";
  }
}
