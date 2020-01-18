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

package com.mallowigi.idea.themes.models.parsers;

import com.mallowigi.idea.themes.models.MTBundledTheme;

import javax.swing.plaf.ColorUIResource;

@SuppressWarnings("MagicNumber")
public class MTLightBundledThemeParser extends MTBundledThemeParser {
  public MTLightBundledThemeParser(final MTBundledTheme mtBundledTheme) {
    super(mtBundledTheme);
  }

  @Override
  protected final ColorUIResource getDefaultExcludedColor() {
    return new ColorUIResource(0xeae8e8);
  }

  @Override
  protected final ColorUIResource getDefaultAccentColor() {
    return new ColorUIResource(0x80cbc4);
  }

  @Override
  protected final ColorUIResource getDefaultNotificationsColor() {
    return new ColorUIResource(0xb0bec5);
  }

  @Override
  protected final ColorUIResource getDefaultTreeSelectionColor() {
    return new ColorUIResource(0x546E7A);
  }

  @Override
  protected final ColorUIResource getDefaultHighlightColor() {
    return new ColorUIResource(0xD2D4D5);
  }

  @Override
  protected final ColorUIResource getDefaultSecondBorderColor() {
    return new ColorUIResource(0xd3e1e8);

  }

  @Override
  protected final ColorUIResource getDefaultTableSelectedColor() {
    return new ColorUIResource(0xD2D4D5);

  }

  @Override
  protected final ColorUIResource getDefaultContrastColor() {
    return new ColorUIResource(0xF4F4F4);

  }

  @Override
  protected final ColorUIResource getDefaultDisabledColor() {
    return new ColorUIResource(0xD2D4D5);

  }

  @Override
  protected final ColorUIResource getDefaultSecondaryBackgroundColor() {
    return new ColorUIResource(0xeae8e8);

  }

  @Override
  protected final ColorUIResource getDefaultButtonColor() {
    return new ColorUIResource(0xF3F4F5);

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
    return new ColorUIResource(0x94A7B0);

  }

  @Override
  protected final ColorUIResource getDefaultForegroundColor() {
    return new ColorUIResource(0x546E7A);

  }

  @Override
  protected final ColorUIResource getDefaultBackgroundColor() {
    return new ColorUIResource(0xFAFAFA);

  }
}
