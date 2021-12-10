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
package com.mallowigi.idea.utils

import com.intellij.ui.ColorUtil
import com.intellij.util.ui.UIUtil
import com.mallowigi.idea.utils.MTUI.Panel.linkForeground
import javax.swing.UIManager

/**
 * Add custom CSS for Editor Kits
 *
 */
object MTStyledKitPatcher {

  /**
   * The UI key
   */
  private const val STYLED_EDITOR_KIT = "StyledEditorKit.JBDefaultStyle"

  /**
   * Patch the Styled Editor Kit for the doc comments
   */
  fun patchStyledEditorKit() {
    val defaults = UIManager.getLookAndFeelDefaults()
    val styleSheet = UIUtil.getHTMLEditorKit().styleSheet ?: return
    val accentColor = ColorUtil.toHex(linkForeground)
    val css = "a, address, b { color: #%s; }"

    styleSheet.addRule(String.format(css, accentColor))
    UIManager.put(STYLED_EDITOR_KIT, styleSheet)
    defaults[STYLED_EDITOR_KIT] = styleSheet
  }
}
