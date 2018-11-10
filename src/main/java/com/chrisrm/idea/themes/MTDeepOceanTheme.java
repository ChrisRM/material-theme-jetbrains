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

public final class MTDeepOceanTheme extends MTAbstractTheme {
  @NotNull
  @Override
  public String getThemeId() {
    return "mt.deepocean";
  }

  @Override
  public String getBackgroundColorString() {
    return "0F111A";
  }

  @Override
  public String getForegroundColorString() {
    return "8F93A2";
  }

  @Override
  public String getTextColorString() {
    return "4B526D";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "717CB450";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getButtonColorString() {
    return "191A21";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "181A1F";
  }

  @Override
  public String getDisabledColorString() {
    return "464B5D";
  }

  @Override
  public String getContrastColorString() {
    return "090B10";
  }

  @Override
  public String getTableSelectedColorString() {
    return "3B3F51";
  }

  @Override
  public String getSecondBorderColorString() {
    return "0F111A";
  }

  @Override
  public String getHighlightColorString() {
    return "4B526D";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "717CB450";
  }

  @Override
  public String getNotificationsColorString() {
    return "090B10";
  }

  @Override
  public String getAccentColorString() {
    return "84ffff";
  }

  @Override
  public String getExcludedColorString() {
    return "292D3E";
  }
}
