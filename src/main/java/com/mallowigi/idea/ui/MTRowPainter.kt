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
package com.mallowigi.idea.ui

import com.intellij.ide.ui.UISettings
import com.intellij.ui.paint.LinePainter2D
import com.intellij.ui.paint.PaintUtil
import com.intellij.ui.scale.JBUIScale
import com.intellij.ui.tree.ui.Control
import com.mallowigi.idea.utils.MTUI.List.listFocusedSelectionPainter
import com.mallowigi.idea.utils.MTUI.Tree.selectionBackground
import java.awt.Component
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.UIManager
import kotlin.math.max

/**
 * Painter for the selected row with indent support and indicator
 *
 */
class MTRowPainter : Control.Painter {
  /**
   * Indent for leaves. We don't use that.
   */
  private val leafIndent: Int
    get() = -1

  /**
   * Right indent taken from the settings
   */
  private val rightIndent: Int
    get() = max(0, UIManager.getInt("Tree.rightChildIndent"))

  /**
   * Gets the renderer offset
   *
   * @param control the tree
   * @param depth the depth of the tree row
   * @param leaf whether it's a leaf
   * @return the offset (indent)
   */
  override fun getRendererOffset(control: Control, depth: Int, leaf: Boolean): Int {
    if (depth < 0) {
      // do not paint row
      return -1
    }
    if (depth == 0) {
      return 0
    }

    val controlWidth = control.width
    val left = getLeftIndent(controlWidth / 2)
    val right = rightIndent
    var offset = leafIndent

    if (offset < 0) {
      offset = max(controlWidth + left - controlWidth / 2 + JBUIScale.scale(2), left + right)
    }
    return if (depth > 1) (depth - 1) * (left + right) + offset else offset
  }

  /**
   * Get control offset
   *
   * @param control the tree
   * @param depth the depth of the tree row
   * @param leaf whether it's a leaf
   * @return the control offset
   */
  override fun getControlOffset(control: Control, depth: Int, leaf: Boolean): Int {
    if (depth <= 0 || leaf) {
      // do not paint control
      return -1
    }
    val controlWidth = control.width
    val left = getLeftIndent(controlWidth / 2)
    val offset = left - controlWidth / 2

    return if (depth > 1) (depth - 1) * (left + rightIndent) + offset else offset
  }

  /**
   * Paint component
   *
   */
  @Suppress("kotlin:S3776")
  override fun paint(
    c: Component,
    g: Graphics,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    control: Control,
    depth: Int,
    leaf: Boolean,
    expanded: Boolean,
    selected: Boolean,
  ) {
    // List indicators
    if (selected) {
      val listFocusedSelectionPainter = listFocusedSelectionPainter
      listFocusedSelectionPainter.paintBorder(c, g, x, y, width, height)
    }
    if (depth <= 0) {
      return
    }

    // Should we paint indent lines?
    val paintLines = shouldPaintLines()
    if (!paintLines && leaf) {
      return
    }

    // Compute the position of the paint lines
    val controlWidth = control.width
    val left = getLeftIndent(controlWidth / 2)
    val indent = left + rightIndent
    var lineX = x + left - controlWidth / 2
    val controlX = if (!leaf && depth > 1) (depth - 1) * indent + lineX else lineX
    var d = depth

    // paint the lines
    if (paintLines && (depth > 1 || !leaf && expanded)) {
      g.color = selectionBackground
      while (--d > 0) {
        paintLine(g, lineX, y, controlWidth, height)
        lineX += indent
      }
      if (!leaf && expanded) {
        val offset = (height - control.height) / 2
        if (offset > 0) {
          paintLine(g, lineX, y + height - offset, controlWidth, offset)
        }
      }
    }
    if (leaf) {
      // do not paint control for a leaf node
      return
    }
    control.paint(c, g, controlX, y, controlWidth, height, expanded, selected)
  }

  /**
   * Returns the coerced left indent from the left indent settings and a minumum
   *
   * @param min minimum indent
   * @return the left indent
   */
  private fun getLeftIndent(min: Int): Int = max(min, UIManager.getInt("Tree.leftChildIndent"))

  /**
   * Paint indent line
   */
  private fun paintLine(g: Graphics, x: Int, y: Int, width: Int, height: Int) {
    if (g is Graphics2D) {
      val dx = x + width / 2.0 - PaintUtil.devPixel(g)

      LinePainter2D.paint(
        g,
        dx,
        y.toDouble(),
        dx,
        (y + height).toDouble(),
        LinePainter2D.StrokeType.CENTERED,
        1.0,
        RenderingHints.VALUE_ANTIALIAS_ON
      )
    } else {
      val newX = x + width / 2
      g.drawLine(newX, y, x, y + height)
    }
  }

  private fun shouldPaintLines(): Boolean {
    if (UIManager.getBoolean("Tree.paintLines")) return true

    val settings: UISettings = UISettings.instanceOrNull!!
    return settings.showTreeIndentGuides
  }

}
