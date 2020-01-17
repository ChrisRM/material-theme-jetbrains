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

package com.mallowigi.idea.themes.themes;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public final class SolarizedLightTheme extends MTAbstractTheme {
  @Override
  public String getThemeId() {
    return "solarized.light";
  }

  @Override
  public ColorUIResource getBackgroundColorResource() {
    return new ColorUIResource(0xfdf6e3);
  }

  @Override
  public ColorUIResource getForegroundColorResource() {
    return new ColorUIResource(0x586e75);
  }

  @Override
  public ColorUIResource getTextColorResource() {
    return new ColorUIResource(0x93a1a1);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorResource() {
    return new ColorUIResource(0xe8dcb6);
  }

  @Override
  public ColorUIResource getSelectionForegroundColorResource() {
    return new ColorUIResource(0x002b36);
  }

  @Override
  public ColorUIResource getButtonColorResource() {
    return new ColorUIResource(0xd8d4c4);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorResource() {
    return new ColorUIResource(0xF6F0DE);
  }

  @Override
  public ColorUIResource getDisabledColorResource() {
    return new ColorUIResource(0xC9CCC3);
  }

  @Override
  public ColorUIResource getContrastColorResource() {
    return new ColorUIResource(0xeee8d5);
  }

  @Override
  public ColorUIResource getTableSelectedColorResource() {
    return new ColorUIResource(0xd1cbb8);
  }

  @Override
  public ColorUIResource getSecondBorderColorResource() {
    return new ColorUIResource(0xedead9);
  }

  @Override
  public ColorUIResource getHighlightColorResource() {
    return new ColorUIResource(0xd1cbb8);
  }

  @Override
  public ColorUIResource getTreeSelectionColorResource() {
    return new ColorUIResource(new Color(0xb0e8dcb6, true));
  }

  @Override
  public ColorUIResource getNotificationsColorResource() {
    return new ColorUIResource(0xEDE8D4);
  }

  @Override
  public ColorUIResource getAccentColorResource() {
    return new ColorUIResource(0xd33682);
  }

  @Override
  public ColorUIResource getExcludedColorResource() {
    return new ColorUIResource(0xE3DCC9);
  }
}
