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

public final class MTLighterTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "FAFAFA"; // 250, 250, 250
  public static final String FOREGROUND = "A7ADB0"; // 167, 173, 176
  public static final String TEXT = "A7ADB0"; // 167, 173, 176
  public static final String SELECTION_BACKGROUND = "80CBC4";
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "eae8e8";

  public MTLighterTheme() {
    super("mt.lighter", "Material Lighter", false, "Material Lighter", "/icons/actions/themes/lighter.png");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return MTLighterTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return MTLighterTheme.DISABLED;
  }

  @Override
  public String getAccentColor() {
    return "80CBC4";
  }

  @Override
  public String getExcludedColor() {
    return DISABLED;
  }

  @Override
  public int getOrder() {
    return 3;
  }

  @Override
  public String getNotificationsColorString() {
    return "b0bec5";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "546E7A50";
  }

  @Override
  public String getHighlightColorString() {
    return "D2D4D5";
  }

  @Override
  public String getSecondBorderColorString() {
    return "d3e1e8";
  }

  @Override
  public String getTableSelectedColorString() {
    return "D2D4D5";
  }

  @Override
  public String getContrastColorString() {
    return "F4F4F4";
  }

  @Override
  public String getDisabledColorString() {
    return "D2D4D5";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "eae8e8";
  }

  @Override
  public String getButtonColorString() {
    return "F3F4F5";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "546E7A";
  }

  @Override
  public String getTextColorString() {
    return "94A7B0";
  }

  @Override
  public String getForegroundColorString() {
    return "546E7A";
  }

  @Override
  public String getBackgroundColorString() {
    return "FAFAFA";
  }
}
