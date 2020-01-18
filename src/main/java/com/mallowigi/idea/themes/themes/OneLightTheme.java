/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

@SuppressWarnings("MagicNumber")
public final class OneLightTheme extends MTAbstractLightTheme {
  @Override
  public String getThemeId() {
    return "one.light";
  }

  @Override
  public ColorUIResource getBackgroundColorResource() {
    return new ColorUIResource(0xF4F4F4);
  }

  @Override
  public ColorUIResource getForegroundColorResource() {
    return new ColorUIResource(0x232324);
  }

  @Override
  public ColorUIResource getTextColorResource() {
    return new ColorUIResource(0x7f7f7f);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorResource() {
    return new ColorUIResource(0xFFFFFF);
  }

  @Override
  public ColorUIResource getSelectionForegroundColorResource() {
    return new ColorUIResource(0x232324);
  }

  @Override
  public ColorUIResource getButtonColorResource() {
    return new ColorUIResource(0xDBDBDC);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorResource() {
    return new ColorUIResource(0xEAEAEB);
  }

  @Override
  public ColorUIResource getDisabledColorResource() {
    return new ColorUIResource(0xb8b8b9);
  }

  @Override
  public ColorUIResource getContrastColorResource() {
    return new ColorUIResource(0xeaeaeb);
  }

  @Override
  public ColorUIResource getTableSelectedColorResource() {
    return new ColorUIResource(0xDBDBDC);
  }

  @Override
  public ColorUIResource getSecondBorderColorResource() {
    return new ColorUIResource(0xDBDBDC);
  }

  @Override
  public ColorUIResource getHighlightColorResource() {
    return new ColorUIResource(0xFFFFFF);
  }

  @Override
  public ColorUIResource getTreeSelectionColorResource() {
    return new ColorUIResource(new Color(0x80DBDBDC, true));
  }

  @Override
  public ColorUIResource getNotificationsColorResource() {
    return new ColorUIResource(0xF2F2F2);
  }

  @Override
  public ColorUIResource getAccentColorResource() {
    return new ColorUIResource(0x2979ff);
  }

  @Override
  public ColorUIResource getExcludedColorResource() {
    return new ColorUIResource(0xCACACB);
  }

  @Override
  public ColorUIResource getSecondSelectionForegroundColorResource() {
    return new ColorUIResource(0x232324);
  }
}
