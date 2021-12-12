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
import com.intellij.ide.ui.laf.darcula.ui.DarculaOptionButtonUI
import com.intellij.ui.ComponentUtil
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.utils.MTUI
import java.awt.Color
import java.awt.Graphics2D
import javax.swing.JComponent

/**
 * Material Option Button
 *
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
open class MTOptionButtonUI : DarculaOptionButtonUI() {
  /**
   * Clip x offset
   */
  override val clipXOffset: Int
    get() = 0

  /**
   * Buttons background
   *
   */
  private val buttonBg: Color
    get() = if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.background else MTUI.Button.backgroundColor

  /**
   * Primary buttons background
   */
  private val primaryButtonBg: Color
    get() = if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.background else MTUI.Button.primaryBackgroundColor

  /**
   * Primary buttons background
   */
  private val disabledButtonBg: Color
    get() = if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.background else MTUI.Button.disabledColor

  /**
   * Install UI
   */
  override fun installUI(c: JComponent) {
    super.installUI(c)
    c.background = buttonBackground(c)
  }

  /**
   * Button background
   *
   */
  private fun buttonBackground(c: JComponent): Color {
    if (!c.isEnabled) return disabledButtonBg
    return if (isDefaultButton(c)) primaryButtonBg else buttonBg
  }

  private fun isDefaultButton(c: JComponent): Boolean =
    ComponentUtil.getClientProperty(c, DarculaButtonUI.DEFAULT_STYLE_KEY) === java.lang.Boolean.TRUE

  /**
   * Do not paint separator
   *
   */
  override fun paintSeparator(g: Graphics2D, c: JComponent) {
    // do not paint
  }

  companion object {
    /**
     * Create a new MTOptionButtonUI
     *
     */
    @Suppress("UNUSED_PARAMETER", "HardCodedStringLiteral")
    @JvmStatic
    fun createUI(c: JComponent): MTOptionButtonUI = MTOptionButtonUI()
  }
}
