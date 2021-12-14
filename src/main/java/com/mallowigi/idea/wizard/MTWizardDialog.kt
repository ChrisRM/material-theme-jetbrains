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
package com.mallowigi.idea.wizard

import com.intellij.ide.customize.CustomizeIDEWizardDialog
import com.intellij.ide.customize.CustomizeIDEWizardStepsProvider
import com.intellij.openapi.ui.popup.util.PopupUtil
import com.intellij.util.ui.JBUI
import com.mallowigi.idea.MTThemeManager
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.messages.MTWizardBundle
import com.mallowigi.idea.themes.MTTheme
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComponent

/**
 * Represents the wizard main panel
 */
class MTWizardDialog(stepsProvider: CustomizeIDEWizardStepsProvider?, firstRun: Boolean) :
  CustomizeIDEWizardDialog(stepsProvider!!) {
  private val configCopy: MTConfig

  private val width = 1200
  private val height = 800

  init {
    title = MTWizardBundle.message("mt.wizard.title")
    peer.setAppIcons()
    configCopy = MTConfig.getInstance().clone()
    setSize(width, height)

    if (firstRun) {
      MTThemeManager.instance.setLookAndFeel(MTTheme.OCEANIC)
      MTThemeManager.instance.activate()
    }

    initCurrentStep()
  }

  /**
   * Open Wizard
   *
   */
  override fun actionPerformed(e: ActionEvent) {
    super.actionPerformed(e)
    initCurrentStep()
  }

  /**
   * Create center panel
   *
   */
  override fun createCenterPanel(): JComponent? {
    val centerPanel = super.createCenterPanel()
    if (centerPanel != null) centerPanel.preferredSize = JBUI.size(width, height)
    return centerPanel
  }

  /**
   * On cancel action
   *
   */
  override fun doCancelAction() {
    super.doCancelAction()
    MTConfig.getInstance().loadState(configCopy)
    MTThemeManager.instance.activate()
  }

  private fun initCurrentStep() {
    if (myIndex == mySteps.size - 1) myNextButton.text = MTWizardBundle.message("finish.button")
  }

  /**
   * Create cancel action
   *
   */
  override fun createCancelAction(): ActionListener = ActionListener {
    if (!PopupUtil.handleEscKeyEvent()) doCancelAction(it)
  }
}
