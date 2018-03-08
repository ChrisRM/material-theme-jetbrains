/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.ui

import com.chrisrm.idea.utils.ColorCycle
import com.chrisrm.idea.utils.MTUiUtils
import com.intellij.ide.ui.laf.darcula.ui.DarculaOptionButtonUI
import com.intellij.ui.ColorUtil
import com.intellij.util.ObjectUtils
import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import javax.swing.JComponent
import javax.swing.UIManager
import javax.swing.plaf.ColorUIResource


open class MTOptionButtonUI : DarculaOptionButtonUI() {
    override val clipXOffset: Int
        get() = 0

    val colorCycle = ColorCycle(5, 20)

    override fun paintSeparator(g: Graphics2D, c: JComponent) {

    }

    private fun buttonBackground(): Color {
        return MTUiUtils.getColor(UIManager.getColor("Button.mt.background"),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), ColorUIResource(0x555a5c)),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), ColorUIResource(0xeeeeee)))
    }

    private fun buttonColor1(): Color {
        return MTUiUtils.getColor(UIManager.getColor("Button.mt.color1"),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), ColorUIResource(0x555a5c)),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), ColorUIResource(0xeeeeee)))
    }

    private fun buttonFg(): Color {
        return MTUiUtils.getColor(UIManager.getColor("Button.mt.foreground"),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"), ColorUIResource(0xbbbbbb)),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"), ColorUIResource(0x000000)))
    }

    private fun buttonPrimaryFg(): Color {
        return ColorUtil.brighter(MTUiUtils.getColor(UIManager.getColor("Button.mt.foreground"),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"),
                        ColorUIResource(0xbbbbbb)),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"),
                        ColorUIResource(0x000000))), 2)
    }

    private fun buttonSelectFg(): Color {
        return MTUiUtils.getColor(UIManager.getColor("Button.mt.selectedForeground"),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.selectedButtonForeground"),
                        ColorUIResource(0xbbbbbb)),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.selectedButtonForeground"),
                        ColorUIResource(0xf0f0f0)))
    }

    private fun buttonSelectColor1(): Color {
        return MTUiUtils.getColor(UIManager.getColor("Button.mt.selection.color1"),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"), ColorUIResource(0x384f6b)),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"), ColorUIResource(0x4985e4)))
    }

    private fun buttonSelectColor2(): Color {
        val color = MTUiUtils.getColor(UIManager.getColor("Button.mt.selection.color1"),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"),
                        ColorUIResource(0x233143)),
                ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"),
                        ColorUIResource(0x4074c9)))
        return ColorUtil.darker(color, 2)
    }

    fun highlightButton(e: FocusEvent?) {
        colorCycle.stop()

        val component = e!!.component
        colorCycle.setC(component as JComponent)

        val hoverColor = buttonSelectColor1()
        val preHoverColor = buttonBackground()
        val textColor = buttonSelectFg()

        component.setForeground(textColor)
        val colors = arrayOfNulls<Color>(5)
        for (i in 0..4) {
            colors[i] = ColorUtil.mix(preHoverColor, hoverColor, i * 0.2)
        }
        colorCycle.start(*colors)
    }

    fun removeHighlight(e: FocusEvent?) {
        colorCycle.stop()

        val component = e!!.component
        colorCycle.setC(component as JComponent)

        val notHoverColor = buttonColor1()
        val preNotHoverColor = buttonBackground()
        val textColor = buttonFg()

        component.setForeground(textColor)
        val colors = arrayOfNulls<Color>(5)
        for (i in 0..4) {
            colors[i] = ColorUtil.mix(notHoverColor, preNotHoverColor, i * 0.2)
        }
        colorCycle.start(*colors)
    }

    override fun createFocusListener(): FocusListener? = object : FocusAdapter() {
        override fun focusLost(e: FocusEvent?) {
            removeHighlight(e)
        }

        override fun focusGained(e: FocusEvent?) {
            highlightButton(e)
        }
    }

    companion object {
        @Suppress("UNUSED_PARAMETER")
        @JvmStatic
        fun createUI(c: JComponent) = MTOptionButtonUI()
    }
}
