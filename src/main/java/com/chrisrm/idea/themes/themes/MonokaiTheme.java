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

public final class MonokaiTheme extends MTAbstractTheme {
  @NotNull
  @Override
  public String getThemeId() {
    return "monokai";
  }

  @Override
  public ColorUIResource getBackgroundColorResource() {
    return new ColorUIResource(0x2D2A2E);
  }

  @Override
  public ColorUIResource getForegroundColorResource() {
    return new ColorUIResource(0xfcfcfa);
  }

  @Override
  public ColorUIResource getTextColorResource() {
    return new ColorUIResource(0x939293);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorResource() {
    return new ColorUIResource(0x6E6C6F);
  }

  @Override
  public ColorUIResource getSelectionForegroundColorResource() {
    return new ColorUIResource(0xffd866);
  }

  @Override
  public ColorUIResource getButtonColorResource() {
    return new ColorUIResource(0x403e41);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorResource() {
    return new ColorUIResource(0x403E41);
  }

  @Override
  public ColorUIResource getDisabledColorResource() {
    return new ColorUIResource(0x5b595c);
  }

  @Override
  public ColorUIResource getContrastColorResource() {
    return new ColorUIResource(0x221F22);
  }

  @Override
  public ColorUIResource getTableSelectedColorResource() {
    return new ColorUIResource(0x4A474B);
  }

  @Override
  public ColorUIResource getSecondBorderColorResource() {
    return new ColorUIResource(0x2d2a2e);
  }

  @Override
  public ColorUIResource getHighlightColorResource() {
    return new ColorUIResource(0x5b595c);
  }

  @Override
  public ColorUIResource getTreeSelectionColorResource() {
    return new ColorUIResource(0x403E41);
  }

  @Override
  public ColorUIResource getNotificationsColorResource() {
    return new ColorUIResource(0x363437);
  }

  @Override
  public ColorUIResource getAccentColorResource() {
    return new ColorUIResource(0xffd866);
  }

  @Override
  public ColorUIResource getExcludedColorResource() {
    return new ColorUIResource(0x3a3a3c);
  }
}
