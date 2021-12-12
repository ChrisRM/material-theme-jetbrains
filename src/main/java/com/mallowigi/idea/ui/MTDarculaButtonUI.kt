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

import com.intellij.ide.ui.laf.darcula.DarculaLaf
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI
import com.intellij.util.ui.UIUtil
import com.intellij.util.ui.UIUtilities
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.utils.MTUI.Button.disabledColor
import java.awt.FontMetrics
import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.AbstractButton
import javax.swing.JComponent
import javax.swing.plaf.ComponentUI

/**
 * Improve default darcula buttons with MT Features
 *
 */
class MTDarculaButtonUI : DarculaButtonUI() {
  /**
   * Paint the text of the button
   */
  override fun paintText(g: Graphics, c: JComponent, textRect: Rectangle, text: String) {
    if (UIUtil.isHelpButton(c)) return

    val button = c as AbstractButton
    val model = button.model
    val metrics = UIUtilities.getFontMetrics(c, g)
    g.color = getButtonTextColor(button)

    val textToPrint = if (MTConfig.getInstance().isUpperCaseButtons) text.uppercase() else text
    val textWidth = metrics.stringWidth(textToPrint)
    val x = (c.getWidth() - textShiftOffset - textWidth) / 2
    val y = textRect.y + metrics.ascent
    val mnemonicIndex = if (DarculaLaf.isAltPressed()) button.displayedMnemonicIndex else -1

    if (model.isEnabled) {
      UIUtilities.drawStringUnderlineCharAt(c, g, textToPrint, mnemonicIndex, x, y)
    } else {
      paintDisabledText(g, text, c, textRect, metrics)
    }
  }

  /**
   * Paint disabled text
   */
  override fun paintDisabledText(
    g: Graphics,
    text: String,
    c: JComponent,
    textRect: Rectangle,
    metrics: FontMetrics,
  ) {
    val textToPrint = if (MTConfig.getInstance().isUpperCaseButtons) text.uppercase() else text
    val x = (c.width - textShiftOffset - metrics.stringWidth(textToPrint)) / 2
    g.color = disabledColor

    UIUtilities.drawStringUnderlineCharAt(c, g, textToPrint, -1, x + 1, textRect.y + metrics.ascent + 1)
  }

  companion object {
    /**
     * Create Material Button UI
     *
     */
    @Suppress("UNUSED_PARAMETER", "HardCodedStringLiteral")
    fun createUI(component: JComponent?): ComponentUI = MTDarculaButtonUI()
  }
}
