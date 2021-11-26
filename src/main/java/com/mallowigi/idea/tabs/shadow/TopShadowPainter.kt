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
package com.mallowigi.idea.tabs.shadow

import com.intellij.ui.ColorUtil
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class TopShadowPainter : ShadowPainter() {
  override fun drawShadow(g2d: Graphics2D, from: Point?, to: Point) {
    val w = to.getX().toInt()
    val h = to.getY().toInt()
    if (h == 0) return

    val bg: Color = shadowColor
    g2d.color = ColorUtil.toAlpha(bg, 50)
    g2d.drawLine(0, h - 1, w, h - 1)

    // draw the drop-shadow
    val mid = ColorUtil.toAlpha(bg, 40)
    g2d.color = mid
    g2d.drawLine(0, h - 2, w, h - 2)

    // draw the drop-shadow
    val mid2 = ColorUtil.toAlpha(bg, 30)
    g2d.color = mid2
    g2d.drawLine(0, h - 3, w, h - 3)
    g2d.drawLine(0, h - 4, w, h - 4)

    val edge = ColorUtil.toAlpha(bg, 25)
    g2d.color = edge
    g2d.drawLine(0, h - 5, w, h - 5)
  }
}
