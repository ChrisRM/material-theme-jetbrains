package com.chrisrm.idea.ui

import com.intellij.ide.ui.laf.darcula.DarculaOptionButtonUI
import java.awt.Graphics2D
import javax.swing.JComponent


open class MTOptionButtonUI : DarculaOptionButtonUI() {
    override val clipXOffset: Int
        get() = 0

    override fun paintSeparator(g: Graphics2D, c: JComponent) {

    }

    companion object {
        @Suppress("UNUSED_PARAMETER")
        @JvmStatic
        fun createUI(c: JComponent) = MTOptionButtonUI()
    }
}
