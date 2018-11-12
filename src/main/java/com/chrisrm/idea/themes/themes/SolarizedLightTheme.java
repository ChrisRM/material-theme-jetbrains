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

package com.chrisrm.idea.themes.themes;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public final class SolarizedLightTheme extends MTAbstractTheme {
  @Override
  public String getThemeId() {
    return "solarized.light";
  }

  @Override
  public ColorUIResource getBackgroundColorString() {
    return new ColorUIResource(0xfdf6e3);
  }

  @Override
  public ColorUIResource getForegroundColorString() {
    return new ColorUIResource(0x586e75);
  }

  @Override
  public ColorUIResource getTextColorString() {
    return new ColorUIResource(0x93a1a1);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorString() {
    return new ColorUIResource(0x93a1a1);
  }

  @Override
  public ColorUIResource getSelectionForegroundColorString() {
    return new ColorUIResource(0x002b36);
  }

  @Override
  public ColorUIResource getButtonColorString() {
    return new ColorUIResource(0xFEFBF1);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorString() {
    return new ColorUIResource(0xF6F0DE);
  }

  @Override
  public ColorUIResource getDisabledColorString() {
    return new ColorUIResource(0xC9CCC3);
  }

  @Override
  public ColorUIResource getContrastColorString() {
    return new ColorUIResource(0xeee8d5);
  }

  @Override
  public ColorUIResource getTableSelectedColorString() {
    return new ColorUIResource(0xF6F0DE);
  }

  @Override
  public ColorUIResource getSecondBorderColorString() {
    return new ColorUIResource(0xedead9);
  }

  @Override
  public ColorUIResource getHighlightColorString() {
    return new ColorUIResource(0xF6F0DE);
  }

  @Override
  public ColorUIResource getTreeSelectionColorString() {
    return new ColorUIResource(new Color(0x8093a1a1, true));
  }

  @Override
  public ColorUIResource getNotificationsColorString() {
    return new ColorUIResource(0xEDE8D4);
  }

  @Override
  public ColorUIResource getAccentColorString() {
    return new ColorUIResource(0xd33682);
  }

  @Override
  public ColorUIResource getExcludedColorString() {
    return new ColorUIResource(0xE3DCC9);
  }
}
