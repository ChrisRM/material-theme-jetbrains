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
package com.mallowigi.idea

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.wm.WindowManager
import com.intellij.openapi.wm.impl.ToolwindowToolbar
import com.intellij.openapi.wm.impl.WindowManagerImpl
import com.intellij.util.ui.UIUtil
import com.mallowigi.idea.config.application.MTConfig
import javax.swing.JPanel

/**
 * Temporary fix for the stripes feature to avoid having duplicates
 */
class MTFixStripes : ProjectManagerListener {
  override fun projectClosing(project: Project) {
    if (!MTConfig.getInstance().isStripedToolWindowsEnabled) return

    val ideRootPane = (WindowManager.getInstance() as WindowManagerImpl).getProjectFrameRootPane(project)
    val toolbars = UIUtil.findComponentsOfType(ideRootPane, ToolwindowToolbar::class.java)
    for (toolbar in toolbars) {
      val panes = UIUtil.findComponentsOfType(toolbar, JPanel::class.java)
      for (pane in panes) {
        if (pane.layout.toString().contains("VerticalFlowLayout")) pane.removeAll()
      }
    }
  }
}
