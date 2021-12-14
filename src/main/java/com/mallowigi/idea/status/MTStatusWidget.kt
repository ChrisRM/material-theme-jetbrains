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
package com.mallowigi.idea.status

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBar
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.listeners.AccentsListener
import com.mallowigi.idea.listeners.ConfigNotifier
import com.mallowigi.idea.listeners.MTTopics
import com.mallowigi.idea.listeners.ThemeListener
import javax.swing.JComponent

internal class MTStatusWidget : CustomStatusBarWidget {
  private val mtWidget: MTWidget = MTWidget()
  private val connect = ApplicationManager.getApplication().messageBus.connect(this)

  override fun ID(): String = "MTStatusBarWidget"

  override fun install(statusBar: StatusBar) {
    connect.run {
      subscribe(MTTopics.THEMES, ThemeListener { refresh() })
      subscribe(MTTopics.ACCENTS, AccentsListener { refresh() })
      subscribe(
        MTTopics.CONFIG,
        object : ConfigNotifier {
          override fun configChanged(mtConfig: MTConfig) = refresh()
        }
      )
    }
  }

  override fun dispose() {
    Disposer.dispose(this)
    mtWidget.myBufferedImage = null
    connect.disconnect()
  }

  private fun refresh() {
    mtWidget.myBufferedImage = null
    mtWidget.isVisible = true
    mtWidget.repaint()
    mtWidget.updateUI()
    mtWidget.toolTipText = MTConfig.getInstance().tooltip
  }

  override fun getComponent(): JComponent = mtWidget
}
