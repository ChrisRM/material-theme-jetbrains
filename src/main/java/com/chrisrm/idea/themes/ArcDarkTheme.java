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
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public final class ArcDarkTheme extends MTAbstractTheme {

  @NotNull
  @Override
  public String getThemeId() {
    return "arc.dark";
  }

  @Override
  public String getBackgroundColorString() {
    return "2f343f";
  }

  @Override
  public String getForegroundColorString() {
    return "D3DAE3";
  }

  @Override
  public String getTextColorString() {
    return "8b9eb5";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "8888FF55";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getButtonColorString() {
    return "383C4A";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "393f4c";
  }

  @Override
  public String getDisabledColorString() {
    return "D3DAE345";
  }

  @Override
  public String getContrastColorString() {
    return "262b33";
  }

  @Override
  public String getTableSelectedColorString() {
    return "444444";
  }

  @Override
  public String getSecondBorderColorString() {
    return "404552";
  }

  @Override
  public String getHighlightColorString() {
    return "3F3F46";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "08507C50";
  }

  @Override
  public String getNotificationsColorString() {
    return "262a33";
  }

  @NonNls
  @Override
  public String getAccentColorString() {
    return "42A5F5";
  }

  @Override
  public String getExcludedColorString() {
    return "474B57";
  }
}
