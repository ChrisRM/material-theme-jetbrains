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

import com.intellij.ide.ui.laf.darcula.ui.DarculaCheckBoxUI
import com.intellij.ui.scale.JBUIScale
import com.intellij.util.ui.EmptyIcon
import com.intellij.util.ui.UIUtil
import com.intellij.util.ui.UIUtilities
import com.mallowigi.idea.utils.MTCheckboxIconLookup
import com.mallowigi.idea.utils.MTUI
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.Rectangle
import javax.swing.AbstractButton
import javax.swing.CellRendererPane
import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.plaf.basic.BasicHTML
import javax.swing.text.View

class MTCheckBoxUI : DarculaCheckBoxUI() {
  private val defaultIcon: Icon = JBUIScale.scaleIcon(EmptyIcon.create(20)).asUIResource()

  override fun installUI(c: JComponent) {
    super.installUI(c)
    c.background = MTUI.Panel.background
    c.foreground = MTUI.Panel.foreground
    if (UIUtil.getParentOfType(CellRendererPane::class.java, c) != null) {
      c.border = null
    }
  }

  override fun textIconGap(): Int = JBUIScale.scale(4)

  override fun getDefaultIcon(): Icon = defaultIcon

  override fun drawCheckIcon(
    c: JComponent,
    g: Graphics2D,
    b: AbstractButton,
    iconRect: Rectangle,
    selected: Boolean,
    enabled: Boolean,
  ) {
    val g2 = g.create() as Graphics2D
    try {
      val iconName = if (isIndeterminate(b)) "checkBoxIndeterminate" else "checkBox"
      val hasFocus = b.hasFocus()

      // get the relevant icon
      val checkboxIcon = MTCheckboxIconLookup.getIcon("checkboxes/$iconName",
                                                      selected || isIndeterminate(b),
                                                      hasFocus,
                                                      b.isEnabled)
      checkboxIcon.paintIcon(b, g2, iconRect.x, iconRect.y)
    } finally {
      g2.dispose()
    }
  }

  override fun drawText(
    c: JComponent,
    g: Graphics2D,
    b: AbstractButton,
    fm: FontMetrics,
    textRect: Rectangle,
    text: String?,
  ) {
    if (text != null) {
      val view = c.getClientProperty(BasicHTML.propertyKey) as View?
      if (view != null) {
        view.paint(g, textRect)
      } else {
        g.color = if (b.isEnabled) b.foreground else getDisabledTextColor()
        UIUtilities.drawStringUnderlineCharAt(c, g, text,
                                              b.displayedMnemonicIndex,
                                              textRect.x,
                                              textRect.y + fm.ascent)
      }
    }
  }

  companion object {
    @Suppress("UNUSED_PARAMETER")
    @JvmStatic
    fun createUI(c: JComponent): MTCheckBoxUI = MTCheckBoxUI()
  }
}
