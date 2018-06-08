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
  public static final String BACKGROUND = "0F111A"; // 38, 50, 56
  public static final String FOREGROUND = "8F93A2"; // 176, 190, 197
  public static final String TEXT = "4B526D"; // 96, 125, 139
  public static final String SELECTION_BACKGROUND = "717CB4"; // 84, 110, 122
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "232324"; // 65, 89, 103

  public MTDeepOceanTheme() {
    super("mt.deepocean", "Material Deep Ocean", true, "Material Deep Ocean", "/icons/actions/themes/deepocean.svg");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return MTDeepOceanTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return MTDeepOceanTheme.DISABLED;
  }

  @Override
  public String getAccentColor() {
    return "84ffff";
  }

  @Override
  public String getExcludedColor() {
    return DISABLED;
  }

  @Override
  public int getOrder() {
    return 4;
  }

  @Override
  public String getNotificationsColorString() {
    return "090B10";
  }

  @Override
  public String getTreeSelectionColorString() {
    return "717CB450";
  }

  @Override
  public String getHighlightColorString() {
    return "4B526D";
  }

  @Override
  public String getSecondBorderColorString() {
    return "3B3F51";
  }

  @Override
  public String getTableSelectedColorString() {
    return "3B3F51";
  }

  @Override
  public String getContrastColorString() {
    return "090B10";
  }

  @Override
  public String getDisabledColorString() {
    return "464B5D";
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return "292D3E";
  }

  @Override
  public String getButtonColorString() {
    return "3B3F51";
  }

  @Override
  public String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return "717CB4";
  }

  @Override
  public String getTextColorString() {
    return "4B526D";
  }

  @Override
  public String getForegroundColorString() {
    return "8F93A2";
  }

  @Override
  public String getBackgroundColorString() {
    return "0F111A";
  }
}
