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
package com.mallowigi.idea.tabs.highlights

import java.awt.Graphics2D
import java.awt.Rectangle
import javax.swing.SwingConstants

/**
 * Right highlight tab painter
 *
 * @constructor Create empty Right highlight tab painter
 */
class RightHighlightTabPainter : HighlightTabPainter() {
  override fun paintBottom(borderThickness: Int, g2d: Graphics2D, rect: Rectangle): Unit = Unit

  override fun paintTop(borderThickness: Int, g2d: Graphics2D, rect: Rectangle): Unit = Unit

  override fun paintLeft(borderThickness: Int, g2d: Graphics2D, rect: Rectangle): Unit = Unit

  override fun paintRight(borderThickness: Int, g2d: Graphics2D, rect: Rectangle) {
    val positionFromPlacement = editorTabPlacement
    // Paint on left if tabs at right, otherwise paint on right
    if (positionFromPlacement == SwingConstants.RIGHT) {
      paintOnLeft(borderThickness, g2d, rect)
    } else {
      paintOnRight(borderThickness, g2d, rect)
    }
  }
}
