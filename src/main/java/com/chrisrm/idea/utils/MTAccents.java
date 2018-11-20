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

package com.chrisrm.idea.utils;

import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NonNls;

import javax.swing.plaf.*;
import java.awt.*;

public enum MTAccents {
  @NonNls
  ACID_LIME(new ColorUIResource(0xc6ff00)),
  AMETHYST(new ColorUIResource(0xab47bc)),
  AMBER(new ColorUIResource(0xFFC107)),
  AQUAMARINE(new ColorUIResource(0x64ffda)),
  ABYSS(new ColorUIResource(0x0F111A)),
  BREAKING_BAD(new ColorUIResource(0x388e3c)),
  BRICK(new ColorUIResource(0xe57373)),
  CARBON(new ColorUIResource(0x424242)),
  COFFEE(new ColorUIResource(0x795548)),
  CYAN(new ColorUIResource(0x00bcd4)),
  DAISY(new ColorUIResource(0xFFEB3B)),
  DODGER_BLUE(new ColorUIResource(0x2979ff)),
  FUCHSIA(new ColorUIResource(0xE91E63)),
  GRAPHITE(new ColorUIResource(0x616161)),
  INDIGO(new ColorUIResource(0x3F51B5)),
  LAVENDER(new ColorUIResource(0x7E57C2)),
  LIGHT(new ColorUIResource(0xFAFAFA)),
  LIME(new ColorUIResource(0x7CB342)),
  NEON(new ColorUIResource(0x1DE9B6)),
  OCEANIC(new ColorUIResource(0x546E7A)),
  ORANGE(new ColorUIResource(0xFF9800)),
  PALENIGHT(new ColorUIResource(0x676E95)),
  PLANT(new ColorUIResource(0x81C784)),
  POMEGRANATE(new ColorUIResource(0xB71C1C)),
  SILVER(new ColorUIResource(0x9E9E9E)),
  SKY(new ColorUIResource(0x84ffff)),
  SLATE(new ColorUIResource(0x607D8B)),
  STRAWBERRY(new ColorUIResource(0xff4081)),
  TEAL(new ColorUIResource(0x009688)),
  TOMATO(new ColorUIResource(0xF44336)),
  TURQUOISE(new ColorUIResource(0x80CBC4)),
  WATER(new ColorUIResource(0x42A5F5)),
  ATOMIC_PURPLE(new ColorUIResource(0x651FFF));

  private final ColorUIResource colorUIResource;

  MTAccents(final ColorUIResource colorUIResource) {
    this.colorUIResource = colorUIResource;
  }

  public String getHexColor() {
    return ColorUtil.toHex(colorUIResource);
  }

  public Color getColor() {
    return colorUIResource;
  }
}
