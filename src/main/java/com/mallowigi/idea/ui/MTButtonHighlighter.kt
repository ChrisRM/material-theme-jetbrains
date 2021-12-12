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

import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI
import com.intellij.ui.ColorUtil
import com.mallowigi.idea.utils.ButtonBackgroundTimer
import java.awt.Color
import java.util.ArrayDeque
import java.util.Deque
import javax.swing.AbstractButton
import javax.swing.JButton
import javax.swing.event.ChangeEvent
import javax.swing.plaf.basic.BasicButtonListener

class MTButtonHighlighter(button: AbstractButton?) : BasicButtonListener(button) {

  override fun stateChanged(e: ChangeEvent) {
    val button = e.source as JButton
    val model = button.model
    val rollover = model.isRollover

    if (rollover) {
      highlightButton(button)
    } else {
      removeHighlight(button)
    }
  }

  private fun highlightButton(jButton: JButton) {
    val hoverColor = hoverColor(jButton)
    val preHoverColor = preHoverColor(jButton)
    val buttonBackgroundTimer = ButtonBackgroundTimer(/* fps = */ 20)

    val colors: Deque<Color> = ArrayDeque(ANIM_STEPS)
    val textColor = textColor(highlight = true)

    // Build the colors from non-highlighted to highlighted
    for (i in 0 until ANIM_STEPS) {
      colors.add(ColorUtil.mix(preHoverColor, hoverColor, i * BALANCE))
    }
    jButton.foreground = textColor
    buttonBackgroundTimer.start("Highlight", jButton, colors)
  }

  private fun removeHighlight(jButton: JButton) {
    val hoverColor = hoverColor(jButton)
    val preHoverColor = preHoverColor(jButton)
    val buttonBackgroundTimer = ButtonBackgroundTimer(/* fps = */ 20)

    val colors: Deque<Color> = ArrayDeque(ANIM_STEPS)
    val textColor = textColor(highlight = false)

    // Build the colors from highlighted to non-highlighted
    for (i in 0 until ANIM_STEPS) {
      colors.addFirst(ColorUtil.mix(preHoverColor, hoverColor, i * BALANCE))
    }
    jButton.foreground = textColor
    buttonBackgroundTimer.start("Remove Highlight", jButton, colors)
  }

  private fun preHoverColor(jButton: JButton): Color = try {
    if (DarculaButtonUI.isDefaultButton(jButton)) MTButtonUI.primaryButtonBg() else MTButtonUI.buttonBg()
  } catch (e: Exception) {
    // swallow exception
    MTButtonUI.buttonBg()
  }

  private fun hoverColor(jButton: JButton): Color = try {
    if (DarculaButtonUI.isDefaultButton(jButton)) MTButtonUI.primaryButtonHoverColor() else MTButtonUI.buttonHoverColor()
  } catch (e: Exception) {
    // swallow exception
    MTButtonUI.buttonHoverColor()
  }

  private fun textColor(highlight: Boolean) =
    if (highlight) MTButtonUI.selectedButtonFg() else MTButtonUI.buttonFg()

  companion object {
    private const val ANIM_STEPS = 5
    private const val BALANCE = 0.2
  }
}
