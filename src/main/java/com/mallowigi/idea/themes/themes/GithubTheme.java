/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

import org.jetbrains.annotations.NonNls;

import javax.swing.plaf.ColorUIResource;

@SuppressWarnings("MagicNumber")
public final class GithubTheme extends MTAbstractLightTheme {
  @Override
  public ColorUIResource getBackgroundColorResource() {
    return new ColorUIResource(0xF7F8FA);
  }

  @Override
  public ColorUIResource getForegroundColorResource() {
    return new ColorUIResource(0x5B6168);
  }

  @Override
  public ColorUIResource getTextColorResource() {
    return new ColorUIResource(0x292D31);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorResource() {
    return new ColorUIResource(0xc6e5ff);
  }

  @Override
  public ColorUIResource getSelectionForegroundColorResource() {
    return new ColorUIResource(0x111111);
  }

  @Override
  public ColorUIResource getButtonColorResource() {
    return new ColorUIResource(0xedf1f5);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorResource() {
    return new ColorUIResource(0xf3f3f3);
  }

  @Override
  public ColorUIResource getDisabledColorResource() {
    return new ColorUIResource(0x9ba0a3);
  }

  @Override
  public ColorUIResource getContrastColorResource() {
    return new ColorUIResource(0xfafbfc);
  }

  @Override
  public ColorUIResource getTableSelectedColorResource() {
    return new ColorUIResource(0xcce5ff);
  }

  @Override
  public ColorUIResource getSecondBorderColorResource() {
    return new ColorUIResource(0xDFE1E4);
  }

  @Override
  public ColorUIResource getHighlightColorResource() {
    return new ColorUIResource(0xCCE5FF);
  }

  @Override
  public ColorUIResource getTreeSelectionColorResource() {
    return new ColorUIResource(0xe1e4e8);
  }

  @Override
  public ColorUIResource getNotificationsColorResource() {
    return new ColorUIResource(0xDFECFE);
  }

  @Override
  public ColorUIResource getAccentColorResource() {
    return new ColorUIResource(0x79CB60);
  }

  @Override
  public ColorUIResource getExcludedColorResource() {
    return new ColorUIResource(0xdcdcdc);
  }

  @NonNls
  @Override
  protected String getBackgroundImage() {
    return "walls/github.svg";
  }

  @Override
  public ColorUIResource getSecondSelectionForegroundColorResource() {
    return new ColorUIResource(0x111111);
  }

  @Override
  public String getThemeId() {
    return "github";
  }
}
