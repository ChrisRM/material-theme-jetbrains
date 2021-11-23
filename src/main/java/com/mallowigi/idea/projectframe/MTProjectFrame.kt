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
package com.mallowigi.idea.projectframe

import com.intellij.ide.ui.UISettings
import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.IdeRootPaneNorthExtension
import com.intellij.util.messages.MessageBusConnection
import com.intellij.util.ui.JBInsets
import com.intellij.util.ui.JBUI
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.config.project.MTProjectConfig
import com.mallowigi.idea.listeners.ConfigNotifier
import com.mallowigi.idea.listeners.MTTopics
import com.mallowigi.idea.listeners.ProjectConfigNotifier
import org.jetbrains.annotations.NonNls
import java.awt.BorderLayout
import java.awt.Graphics
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JPanel

class MTProjectFrame private constructor(private val myProject: Project) : IdeRootPaneNorthExtension(), Disposable {
  private val connect: MessageBusConnection = myProject.messageBus.connect()
  private var myWrapperPanel: JComponent? = null
  private var myProjectFramePanel: JPanel? = null

  init {
    connect.subscribe(MTTopics.CONFIG, object : ConfigNotifier {
      override fun configChanged(mtConfig: MTConfig) = addFrame(shouldShowProjectFrame())
    })
    connect.subscribe(MTTopics.PROJECT_CONFIG, object : ProjectConfigNotifier {
      override fun projectConfigChanged(mtConfig: MTProjectConfig) = addFrame(shouldShowProjectFrame())
    })
  }

  /**
   * Remove listeners on dispose
   *
   */
  override fun dispose(): Unit = connect.disconnect()

  /**
   * Add frame to the top of the window
   *
   * @param enabled
   */
  private fun addFrame(enabled: Boolean) {
    if (myWrapperPanel == null) return

    if (enabled && myProjectFramePanel == null) {
      // when enabling
      myProjectFramePanel = buildPanel()
      myWrapperPanel!!.add(myProjectFramePanel!!, BorderLayout.CENTER)
    } else if (!enabled && myProjectFramePanel != null) {
      // when disabling
      myWrapperPanel!!.remove(myProjectFramePanel)
      myProjectFramePanel = null
    }
    myWrapperPanel!!.repaint()
  }

  /**
   * Key identifying the project frame
   *
   */
  override fun getKey(): @NonNls String = "MTProjectFrame"

  /**
   * IDERootPane component
   *
   */
  override fun getComponent(): JComponent {
    if (myWrapperPanel == null) {
      myWrapperPanel = MTProjectFrameWrapperPanel(BorderLayout())
      addFrame(shouldShowProjectFrame())
    }
    return myWrapperPanel!!
  }

  /**
   * Build the project frame panel
   *
   */
  private fun buildPanel(): JPanel {
    val mtProjectTitlePanel = MTProjectTitlePanel(myProject)
    val container: JPanel = object : JPanel(BorderLayout()) {
      override fun paintComponent(g: Graphics) = mtProjectTitlePanel.paintComponent(g)

      override fun getInsets(): Insets = JBInsets.create(JBUI.scale(12), 0)
    }

    container.add(mtProjectTitlePanel, BorderLayout.CENTER)
    container.updateUI()

    return container
  }

  /**
   * Adds/Removes the project frame when settings are changed
   *
   */
  override fun uiSettingsChanged(settings: UISettings): Unit = addFrame(shouldShowProjectFrame())

  /**
   * Returns a copy of the project frame
   *
   */
  override fun copy(): IdeRootPaneNorthExtension {
    return MTProjectFrame(myProject)
  }

  /**
   * Returns whether the current config/project config should show project frame
   *
   */
  private fun shouldShowProjectFrame(): Boolean {
    val uiSettings: UISettings = UISettings.instance
    val useProjectFrame = MTConfig.getInstance().isUseProjectFrame

    return !uiSettings.presentationMode && useProjectFrame
  }

}
