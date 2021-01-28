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
import java.awt.*;

@SuppressWarnings("MagicNumber")
public final class GithubDarkTheme extends MTAbstractTheme {
  @Override
  public ColorUIResource getBackgroundColorResource() {
    return new ColorUIResource(0x24292e);
  }

  @Override
  public ColorUIResource getForegroundColorResource() {
    return new ColorUIResource(0xe1e4e8);
  }

  @Override
  public ColorUIResource getTextColorResource() {
    return new ColorUIResource(0x959da5);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorResource() {
    return new ColorUIResource(new Color(0x443392FF, true));
  }

  @Override
  public ColorUIResource getSelectionForegroundColorResource() {
    return new ColorUIResource(0xFFFFFF);
  }

  @Override
  public ColorUIResource getButtonColorResource() {
    return new ColorUIResource(0x39414a);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorResource() {
    return new ColorUIResource(0x2f363d);
  }

  @Override
  public ColorUIResource getDisabledColorResource() {
    return new ColorUIResource(0x6a737d);
  }

  @Override
  public ColorUIResource getContrastColorResource() {
    return new ColorUIResource(0x1e2428);
  }

  @Override
  public ColorUIResource getTableSelectedColorResource() {
    return new ColorUIResource(0x2b3036);
  }

  @Override
  public ColorUIResource getSecondBorderColorResource() {
    return new ColorUIResource(0x1b1f23);
  }

  @Override
  public ColorUIResource getHighlightColorResource() {
    return new ColorUIResource(0x444d56);
  }

  @Override
  public ColorUIResource getTreeSelectionColorResource() {
    return new ColorUIResource(0x39414a);
  }

  @Override
  public ColorUIResource getNotificationsColorResource() {
    return new ColorUIResource(0x2f363d);
  }

  @Override
  public ColorUIResource getAccentColorResource() {
    return new ColorUIResource(0xf9826c);
  }

  @Override
  public ColorUIResource getExcludedColorResource() {
    return new ColorUIResource(0x2f363d);
  }

  @NonNls
  @Override
  protected String getBackgroundImage() {
    return "walls/github_dark.svg";
  }

  @Override
  public String getThemeId() {
    return "github_dark";
  }
}
