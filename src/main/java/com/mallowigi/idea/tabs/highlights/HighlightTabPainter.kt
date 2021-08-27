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

import com.intellij.ide.ui.UISettings
import com.mallowigi.idea.config.enums.TabHighlightPositions
import java.awt.Graphics2D
import java.awt.Rectangle

abstract class HighlightTabPainter {
  abstract fun paintBottom(borderThickness: Int, g2d: Graphics2D?, rect: Rectangle?, width: Int)
  abstract fun paintTop(borderThickness: Int, g2d: Graphics2D?, rect: Rectangle?, width: Int)
  abstract fun paintLeft(borderThickness: Int, g2d: Graphics2D?, rect: Rectangle?, width: Int)
  abstract fun paintRight(borderThickness: Int, g2d: Graphics2D?, rect: Rectangle?, width: Int)

  companion object {
    /**
     * Factory method to get the HighlightTabPainter from a position
     *
     * @param tabHighlightPosition the config's position
     * @return instance of HighlightTabPainter
     */
    @JvmStatic
    fun getHighlightTabPainter(tabHighlightPosition: TabHighlightPositions?): HighlightTabPainter {
      return when (tabHighlightPosition) {
        TabHighlightPositions.TOP -> TopHighlightTabPainter()
        TabHighlightPositions.FULL -> FullHighlightTabPainter()
        TabHighlightPositions.LEFT -> LeftHighlightTabPainter()
        TabHighlightPositions.NONE -> NoneHighlightTabPainter()
        TabHighlightPositions.RIGHT -> RightHighlightTabPainter()
        TabHighlightPositions.BOTTOM -> BottomHighlightTabPainter()
        TabHighlightPositions.TOPLESS -> ToplessHighlightTabPainter()
        TabHighlightPositions.BOTTOMLESS -> BottomlessHighlightTabPainter()
        TabHighlightPositions.LEFT_RIGHT -> LeftRightHighlightTabPainter()
        TabHighlightPositions.TOP_BOTTOM -> TopBottomHighlightTabPainter()
        TabHighlightPositions.DEFAULT -> DefaultHighlightTabPainter()
        else -> DefaultHighlightTabPainter()
      }
    }

    @JvmStatic
    val editorTabPlacement: Int
      get() = UISettings.instance.editorTabPlacement

    @JvmStatic
    fun paintOnRight(borderThickness: Int, g2d: Graphics2D, rect: Rectangle) {
      g2d.fillRect(rect.x + rect.width - borderThickness + 1, rect.y, borderThickness, rect.height)
    }

    @JvmStatic
    fun paintOnLeft(borderThickness: Int, g2d: Graphics2D, rect: Rectangle) {
      g2d.fillRect(rect.x, rect.y, borderThickness, rect.height)
    }

    @JvmStatic
    fun paintOnBottom(borderThickness: Int, g2d: Graphics2D, rect: Rectangle, w: Int) {
      g2d.fillRect(rect.x, rect.y + rect.height - borderThickness + 1, w, borderThickness)
    }

    @JvmStatic
    fun paintOnTop(borderThickness: Int, g2d: Graphics2D, rect: Rectangle) {
      g2d.fillRect(rect.x, rect.y - 1, rect.width, borderThickness)
    }
  }
}