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

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.mallowigi.idea.messages.MaterialThemeBundle.message

/**
 * Builds the MT Status Bar
 *
 */
class MTStatusBarFactory : StatusBarWidgetFactory {
  /**
   * Material Status Bar Factory ID
   */
  override fun getId(): String = "mtStatusBar"

  /**
   * Status Bar Widget Name
   */
  override fun getDisplayName(): String = message("mt.settings.statusbar")

  /**
   * Make widget available everywhere
   */
  override fun isAvailable(project: Project): Boolean = true

  /**
   * Make a new [MTStatusWidget]
   */
  override fun createWidget(project: Project): StatusBarWidget = MTStatusWidget()

  /**
   * Dispose widget
   */
  override fun disposeWidget(widget: StatusBarWidget): Unit = Disposer.dispose(widget)

  /**
   * Check when widget can be enabled
   */
  override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true

  /**
   * Whether widget is enabled by default
   */
  override fun isEnabledByDefault(): Boolean = true

  /**
   * Whether widget is configurable
   */
  override fun isConfigurable(): Boolean = true
}
