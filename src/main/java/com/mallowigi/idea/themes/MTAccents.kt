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

/**
 * List of available accent colors
 *
 * @property colorUIResource the color as a [ColorUIResource]
 */
enum class MTAccents(private val colorUIResource: ColorUIResource) {
  /**
   * [<span style="color:c6ff00">ACID_LIME</span>]
   *
   */
  ACID_LIME(ColorUIResource(0xc6ff00)),

  /**
   * [<span style="color:ab47bc">AMETHYST</span>]
   *
   */
  AMETHYST(ColorUIResource(0xab47bc)),

  /**
   * [<span style="color:FFC107">AMBER</span>]
   *
   */
  AMBER(ColorUIResource(0xFFC107)),

  /**
   * [<span style="color:64ffda">AQUAMARINE</span>]
   *
   */
  AQUAMARINE(ColorUIResource(0x64ffda)),

  /**
   * [<span style="color:0F111A">ABYSS</span>]
   *
   */
  ABYSS(ColorUIResource(0x0F111A)),

  /**
   * [<span style="color:388e3c">BREAKING_BAD</span>]
   *
   */
  BREAKING_BAD(ColorUIResource(0x388e3c)),

  /**
   * [<span style="color:e57373">BRICK</span>]
   *
   */
  BRICK(ColorUIResource(0xe57373)),

  /**
   * [<span style="color:424242">CARBON</span>]
   *
   */
  CARBON(ColorUIResource(0x424242)),

  /**
   * [<span style="color:795548">COFFEE</span>]
   *
   */
  COFFEE(ColorUIResource(0x795548)),

  /**
   * [<span style="color:00bcd4">CYAN</span>]
   *
   */
  CYAN(ColorUIResource(0x00bcd4)),

  /**
   * [<span style="color:FFEB3B">DAISY</span>]
   *
   */
  DAISY(ColorUIResource(0xFFEB3B)),

  /**
   * [<span style="color:2979ff">DODGER_BLUE</span>]
   *
   */
  DODGER_BLUE(ColorUIResource(0x2979ff)),

  /**
   * [<span style="color:E91E63">FUCHSIA</span>]
   *
   */
  FUCHSIA(ColorUIResource(0xE91E63)),

  /**
   * [<span style="color:616161">GRAPHITE</span>]
   *
   */
  GRAPHITE(ColorUIResource(0x616161)),

  /**
   * [<span style="color:3F51B5">INDIGO</span>]
   *
   */
  INDIGO(ColorUIResource(0x3F51B5)),

  /**
   * [<span style="color:7E57C2">LAVENDER</span>]
   *
   */
  LAVENDER(ColorUIResource(0x7E57C2)),

  /**
   * [<span style="color:FAFAFA">LIGHT</span>]
   *
   */
  LIGHT(ColorUIResource(0xFAFAFA)),

  /**
   * [<span style="color:7CB342">LIME</span>]
   *
   */
  LIME(ColorUIResource(0x7CB342)),

  /**
   * [<span style="color:1DE9B6">NEON</span>]
   *
   */
  NEON(ColorUIResource(0x1DE9B6)),

  /**
   * [<span style="color:546E7A">OCEANIC</span>]
   *
   */
  OCEANIC(ColorUIResource(0x546E7A)),

  /**
   * [<span style="color:FF9800">ORANGE</span>]
   *
   */
  ORANGE(ColorUIResource(0xFF9800)),

  /**
   * [<span style="color:676E95">PALENIGHT</span>]
   *
   */
  PALENIGHT(ColorUIResource(0x676E95)),

  /**
   * [<span style="color:81C784">PLANT</span>]
   *
   */
  PLANT(ColorUIResource(0x81C784)),

  /**
   * [<span style="color:B71C1C">POMEGRANATE</span>]
   *
   */
  POMEGRANATE(ColorUIResource(0xB71C1C)),

  /**
   * [<span style="color:9E9E9E">SILVER</span>]
   *
   */
  SILVER(ColorUIResource(0x9E9E9E)),

  /**
   * [<span style="color:84ffff">SKY</span>]
   *
   */
  SKY(ColorUIResource(0x84ffff)),

  /**
   * [<span style="color:607D8B">SLATE</span>]
   *
   */
  SLATE(ColorUIResource(0x607D8B)),

  /**
   * [<span style="color:ff4081">STRAWBERRY</span>]
   *
   */
  STRAWBERRY(ColorUIResource(0xff4081)),

  /**
   * [<span style="color:009688">TEAL</span>]
   *
   */
  TEAL(ColorUIResource(0x009688)),

  /**
   * [<span style="color:F44336">TOMATO</span>]
   *
   */
  TOMATO(ColorUIResource(0xF44336)),

  /**
   * [<span style="color:80CBC4">TURQUOISE</span>]
   *
   */
  TURQUOISE(ColorUIResource(0x80CBC4)),

  /**
   * [<span style="color:42A5F5">WATER</span>]
   *
   */
  WATER(ColorUIResource(0x42A5F5)),

  /**
   * [<span style="color:651FFF">ATOMIC_PURPLE</span>]
   *
   */
  ATOMIC_PURPLE(ColorUIResource(0x651FFF));

  /**
   * Converts to hex
   */
  val hexColor: String
    get() = ColorUtil.toHex(colorUIResource)

  /**
   * Color
   */
  val color: Color
    get() = colorUIResource
}
