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

import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public final class MTOceanicTheme extends MTAbstractTheme {
  @NotNull
  @Override
  public String getThemeId() {
    return "mt.oceanic";
  }

  @Override
  public ColorUIResource getBackgroundColorString() {
    return new ColorUIResource(0x263238);
  }

  @Override
  public ColorUIResource getForegroundColorString() {
    return new ColorUIResource(0xB0BEC5);
  }

  @Override
  public ColorUIResource getTextColorString() {
    return new ColorUIResource(0x607D8B);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorString() {
    return new ColorUIResource(0x546E7A);
  }

  @Override
  public ColorUIResource getSelectionForegroundColorString() {
    return new ColorUIResource(0xFFFFFF);
  }

  @Override
  public ColorUIResource getButtonColorString() {
    return new ColorUIResource(0x2E3C43);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorString() {
    return new ColorUIResource(0x32424A);
  }

  @Override
  public ColorUIResource getDisabledColorString() {
    return new ColorUIResource(0x415967);
  }

  @Override
  public ColorUIResource getContrastColorString() {
    return new ColorUIResource(0x1E272C);
  }

  @Override
  public ColorUIResource getTableSelectedColorString() {
    return new ColorUIResource(0x314549);
  }

  @Override
  public ColorUIResource getSecondBorderColorString() {
    return new ColorUIResource(0x2A373E);
  }

  @Override
  public ColorUIResource getHighlightColorString() {
    return new ColorUIResource(0x425B67);
  }

  @Override
  public ColorUIResource getTreeSelectionColorString() {
    return new ColorUIResource(new Color(0x50546E7A, true));
  }

  @Override
  public ColorUIResource getNotificationsColorString() {
    return new ColorUIResource(0x1E272C);
  }

  @Override
  public ColorUIResource getAccentColorString() {
    return new ColorUIResource(0x009688);
  }

  @Override
  public ColorUIResource getExcludedColorString() {
    return new ColorUIResource(0x2E3C43);
  }
}
