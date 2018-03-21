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

import com.intellij.ide.ui.laf.darcula.ui.DarculaOptionButtonUI
import java.awt.Graphics2D
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JComponent


open class MTOptionButtonUI : DarculaOptionButtonUI() {
    override val clipXOffset: Int
        get() = 0

    override fun paintSeparator(g: Graphics2D, c: JComponent) {
    }

    private var mouseListener: MouseListener? = null

    override fun installListeners() {
        super.installListeners()
        mouseListener = createMouseListener()?.apply(optionButton::addMouseListener)
    }

    override fun uninstallListeners() {
        mouseListener = null
    }

    protected open fun createMouseListener(): MouseListener? = object : MouseAdapter() {
        override fun mouseEntered(e: MouseEvent?) {
            super.mouseEntered(e)
            println("entered")
        }

        override fun mouseExited(e: MouseEvent?) {
            super.mouseExited(e)
            println("exited")
        }
    }


    companion object {
        @Suppress("UNUSED_PARAMETER")
        @JvmStatic
        fun createUI(c: JComponent) = MTOptionButtonUI()
    }
}
