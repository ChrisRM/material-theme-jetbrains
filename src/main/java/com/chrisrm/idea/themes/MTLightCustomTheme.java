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
import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTCustomThemeConfig;

public class MTLightCustomTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "FAFAFA"; // 250, 250, 250
  public static final String FOREGROUND = "A7ADB0"; // 167, 173, 176
  public static final String TEXT = "A7ADB0"; // 167, 173, 176
  public static final String SELECTION_BACKGROUND = "80CBC4";
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String LABEL = "546E7A"; // 84, 110, 122
  public static final String DISABLED = "eae8e8";

  public MTLightCustomTheme() {
    super(LIGHT_CUSTOM_THEME_ID, "Material Light Custom", false, "Light Custom", "/icons/actions/themes/light_custom.svg");
  }

  @Override
  public String getNotificationsColorString() {
    return MTCustomThemeConfig.getInstance().getNotificationsColorString();
  }

  @Override
  public String getTreeSelectionColorString() {
    return MTCustomThemeConfig.getInstance().getTreeSelectionColorString();
  }

  @Override
  public String getHighlightColorString() {
    return MTCustomThemeConfig.getInstance().getHighlightColorString();
  }

  @Override
  public String getSecondBorderColorString() {
    return MTCustomThemeConfig.getInstance().getSecondBorderColorString();
  }

  @Override
  public String getTableSelectedColorString() {
    return MTCustomThemeConfig.getInstance().getTableSelectedColorString();
  }

  @Override
  public String getContrastColorString() {
    return MTCustomThemeConfig.getInstance().getContrastColorString();
  }

  @Override
  public String getDisabledColorString() {
    return MTCustomThemeConfig.getInstance().getDisabledColorString();
  }

  @Override
  public String getSecondaryBackgroundColorString() {
    return MTCustomThemeConfig.getInstance().getSecondaryBackgroundColorString();
  }

  @Override
  public String getButtonColorString() {
    return MTCustomThemeConfig.getInstance().getButtonColorString();
  }

  @Override
  public String getSelectionForegroundColorString() {
    return MTCustomThemeConfig.getInstance().getSelectionForegroundColorString();
  }

  @Override
  public String getSelectionBackgroundColorString() {
    return MTCustomThemeConfig.getInstance().getSelectionBackgroundColorString();
  }

  @Override
  public String getTextColorString() {
    return MTCustomThemeConfig.getInstance().getTextColorString();
  }

  @Override
  public String getForegroundColorString() {
    return MTCustomThemeConfig.getInstance().getForegroundColorString();
  }

  @Override
  public String getBackgroundColorString() {
    return MTCustomThemeConfig.getInstance().getBackgroundColorString();
  }

  @Override
  public boolean isCustom() {
    return true;
  }

  @Override
  public String getAccentColorString() {
    return MTConfig.getInstance().getAccentColor();
  }

  @Override
  public String getExcludedColorString() {
    return DISABLED;
  }

  @Override
  public int getOrder() {
    return 10;
  }
}
