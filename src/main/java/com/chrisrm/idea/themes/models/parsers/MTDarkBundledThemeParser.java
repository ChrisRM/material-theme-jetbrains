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

package com.chrisrm.idea.themes.models.parsers;

import com.chrisrm.idea.themes.models.MTBundledTheme;

import javax.swing.plaf.ColorUIResource;

public class MTDarkBundledThemeParser extends MTBundledThemeParser {
  public MTDarkBundledThemeParser(final MTBundledTheme mtBundledTheme) {
    super(mtBundledTheme);
  }

  @Override
  protected final ColorUIResource getDefaultExcludedColor() {
    return new ColorUIResource(0x2E3C43);
  }

  @Override
  protected final ColorUIResource getDefaultAccentColor() {
    return new ColorUIResource(0x009688);
  }

  @Override
  protected final ColorUIResource getDefaultNotificationsColor() {
    return new ColorUIResource(0x323232);
  }

  @Override
  protected final ColorUIResource getDefaultTreeSelectionColor() {
    return new ColorUIResource(0x546E7A);
  }

  @Override
  protected final ColorUIResource getDefaultHighlightColor() {
    return new ColorUIResource(0x425B67);
  }

  @Override
  protected final ColorUIResource getDefaultSecondBorderColor() {
    return new ColorUIResource(0x2A373E);
  }

  @Override
  protected final ColorUIResource getDefaultTableSelectedColor() {
    return new ColorUIResource(0x314549);
  }

  @Override
  protected final ColorUIResource getDefaultContrastColor() {
    return new ColorUIResource(0x1E272C);
  }

  @Override
  protected final ColorUIResource getDefaultDisabledColor() {
    return new ColorUIResource(0x415967);
  }

  @Override
  protected final ColorUIResource getDefaultSecondaryBackgroundColor() {
    return new ColorUIResource(0x32424A);
  }

  @Override
  protected final ColorUIResource getDefaultButtonColor() {
    return new ColorUIResource(0x2E3C43);
  }

  @Override
  protected final ColorUIResource getDefaultSelectionForegroundColor() {
    return new ColorUIResource(0xFFFFFF);
  }

  @Override
  protected final ColorUIResource getDefaultSelectionBackgroundColor() {
    return new ColorUIResource(0x546E7A);
  }

  @Override
  protected final ColorUIResource getDefaultTextColor() {
    return new ColorUIResource(0x607D8B);
  }

  @Override
  protected final ColorUIResource getDefaultForegroundColor() {
    return new ColorUIResource(0xB0BEC5);
  }

  @Override
  protected final ColorUIResource getDefaultBackgroundColor() {
    return new ColorUIResource(0x263238);
  }
}
