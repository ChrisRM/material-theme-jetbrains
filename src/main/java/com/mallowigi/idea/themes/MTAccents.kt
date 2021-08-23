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
package com.mallowigi.idea.themes

import com.intellij.ui.ColorUtil
import java.awt.Color
import javax.swing.plaf.ColorUIResource

enum class MTAccents(private val colorUIResource: ColorUIResource) {
  ACID_LIME(ColorUIResource(0xc6ff00)),
  AMETHYST(ColorUIResource(0xab47bc)),
  AMBER(ColorUIResource(0xFFC107)),
  AQUAMARINE(ColorUIResource(0x64ffda)),
  ABYSS(ColorUIResource(0x0F111A)),
  BREAKING_BAD(ColorUIResource(0x388e3c)),
  BRICK(ColorUIResource(0xe57373)),
  CARBON(ColorUIResource(0x424242)),
  COFFEE(ColorUIResource(0x795548)),
  CYAN(ColorUIResource(0x00bcd4)),
  DAISY(ColorUIResource(0xFFEB3B)),
  DODGER_BLUE(ColorUIResource(0x2979ff)),
  FUCHSIA(ColorUIResource(0xE91E63)),
  GRAPHITE(ColorUIResource(0x616161)),
  INDIGO(ColorUIResource(0x3F51B5)),
  LAVENDER(ColorUIResource(0x7E57C2)),
  LIGHT(ColorUIResource(0xFAFAFA)),
  LIME(ColorUIResource(0x7CB342)),
  NEON(ColorUIResource(0x1DE9B6)),
  OCEANIC(ColorUIResource(0x546E7A)),
  ORANGE(ColorUIResource(0xFF9800)),
  PALENIGHT(ColorUIResource(0x676E95)),
  PLANT(ColorUIResource(0x81C784)),
  POMEGRANATE(ColorUIResource(0xB71C1C)),
  SILVER(ColorUIResource(0x9E9E9E)),
  SKY(ColorUIResource(0x84ffff)),
  SLATE(ColorUIResource(0x607D8B)),
  STRAWBERRY(ColorUIResource(0xff4081)),
  TEAL(ColorUIResource(0x009688)),
  TOMATO(ColorUIResource(0xF44336)),
  TURQUOISE(ColorUIResource(0x80CBC4)),
  WATER(ColorUIResource(0x42A5F5)),
  ATOMIC_PURPLE(ColorUIResource(0x651FFF));

  val hexColor: String
    get() = ColorUtil.toHex(colorUIResource)
  
  val color: Color
    get() = colorUIResource
}
