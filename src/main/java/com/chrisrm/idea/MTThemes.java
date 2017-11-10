/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea;

import com.chrisrm.idea.themes.*;

public enum MTThemes {
  OCEANIC("OCEANIC", new MTOceanicTheme()),
  DARKER("DARKER", new MTDarkerTheme()),
  LIGHTER("LIGHTER", new MTLighterTheme()),
  PALENIGHT("PALENIGHT", new MTPalenightTheme()),
  CUSTOM("CUSTOM", new MTCustomTheme()),
  LIGHT_CUSTOM("LIGHT_CUSTOM", new MTLightCustomTheme()),
  MONOKAI("MONOKAI", new MonokaiTheme()),
  ARC_DARK("ARC_DARK", new ArcDarkTheme()),
  ONE_DARK("ONE_DARK", new OneDarkTheme());


  private final String name;
  private final MTTheme mtTheme;

  MTThemes(final String name, final MTTheme mtTheme) {
    this.name = name;
    this.mtTheme = mtTheme;
  }

  public String getEditorColorsScheme() {
    return mtTheme.getEditorColorsScheme();
  }

  public MTTheme getTheme() {
    return mtTheme;
  }

  public boolean isDark() {
    return mtTheme.isDark();
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return mtTheme.getId();
  }
}
