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

public final class DraculaTheme extends MTAbstractTheme {
  @NotNull
  @Override
  public String getThemeId() {
    return "dracula";
  }

  @Override
  public ColorUIResource getBackgroundColorResource() {
    return new ColorUIResource(0x282A36);
  }

  @Override
  public ColorUIResource getForegroundColorResource() {
    return new ColorUIResource(0xF8F8F2);
  }

  @Override
  public ColorUIResource getTextColorResource() {
    return new ColorUIResource(0x6272A4);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorResource() {
    return new ColorUIResource(0x44475A);
  }

  @Override
  public ColorUIResource getSelectionForegroundColorResource() {
    return new ColorUIResource(0x8BE9FD);
  }

  @Override
  public ColorUIResource getButtonColorResource() {
    return new ColorUIResource(0x393C4B);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorResource() {
    return new ColorUIResource(0x282A36);
  }

  @Override
  public ColorUIResource getDisabledColorResource() {
    return new ColorUIResource(0x6272A4);
  }

  @Override
  public ColorUIResource getContrastColorResource() {
    return new ColorUIResource(0x191A21);
  }

  @Override
  public ColorUIResource getTableSelectedColorResource() {
    return new ColorUIResource(0x44475A);
  }

  @Override
  public ColorUIResource getSecondBorderColorResource() {
    return new ColorUIResource(0x21222C);
  }

  @Override
  public ColorUIResource getHighlightColorResource() {
    return new ColorUIResource(0x6272A4);
  }

  @Override
  public ColorUIResource getTreeSelectionColorResource() {
    return new ColorUIResource(new Color(0x5044475A, true));
  }

  @Override
  public ColorUIResource getNotificationsColorResource() {
    return new ColorUIResource(0x1D2228);
  }

  @Override
  public ColorUIResource getAccentColorResource() {
    return new ColorUIResource(0xBD93F9);
  }

  @Override
  public ColorUIResource getExcludedColorResource() {
    return new ColorUIResource(0x34353D);
  }
}
