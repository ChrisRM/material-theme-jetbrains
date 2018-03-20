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

public final class ArcDarkTheme extends MTAbstractTheme {
  public static final String BACKGROUND = "383C4A";
  public static final String FOREGROUND = "D3DAE3";
  public static final String TEXT = "8b9eb5";
  public static final String SELECTION_BACKGROUND = "5294e2";
  public static final String SELECTION_FOREGROUND = "FFFFFF";
  public static final String DISABLED = "3c4150";

  public ArcDarkTheme() {
    super("arc.dark", "Arc Dark", true, "Arc Dark");
  }

  @NotNull
  @Override
  public String getSelectionBackground() {
    return ArcDarkTheme.SELECTION_BACKGROUND;
  }

  @NotNull
  @Override
  public String getDisabled() {
    return ArcDarkTheme.DISABLED;
  }

  @Override
  protected String getNotificationsColorString() {
    return "313541";
  }

  @Override
  protected String getTreeSelectionColorString() {
    return "5294e250";
  }

  @Override
  protected String getHighlightColorString() {
    return "22242d";
  }

  @Override
  protected String getSecondBorderColorString() {
    return "2b2e39";
  }

  @Override
  protected String getTableSelectedColorString() {
    return "22242d";
  }

  @Override
  protected String getContrastColorString() {
    return "2f343f";
  }

  @Override
  protected String getDisabledColorString() {
    return "D3DAE345";
  }

  @Override
  protected String getSecondaryBackgroundColorString() {
    return "404552";
  }

  @Override
  protected String getInactiveColorString() {
    return "3c4150";
  }

  @Override
  protected String getSelectionForegroundColorString() {
    return "FFFFFF";
  }

  @Override
  protected String getSelectionBackgroundColorString() {
    return "5294e2";
  }

  @Override
  protected String getTextColorString() {
    return "8b9eb5";
  }

  @Override
  protected String getForegroundColorString() {
    return "D3DAE3";
  }

  @Override
  protected String getBackgroundColorString() {
    return "383C4A";
  }
}
