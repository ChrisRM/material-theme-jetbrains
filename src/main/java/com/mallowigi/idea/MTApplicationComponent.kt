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

import com.intellij.ide.AppLifecycleListener
import com.intellij.openapi.application.ApplicationManager
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.wizard.MTWizardDialog
import com.mallowigi.idea.wizard.MTWizardStepsProvider

/**
 * Component for Material Theme plugin initializations
 */
class MTApplicationComponent : AppLifecycleListener {
  val instance: MTApplicationComponent
    get() = ApplicationManager.getApplication().getComponent(MTApplicationComponent::class.java)

  override fun welcomeScreenDisplayed(): Unit = initComponent()

  override fun appClosing(): Unit = disposeComponent()

  /**
   * Initializes the MTAnalytics
   */
  private fun initAnalytics() =
    ApplicationManager.getApplication().invokeLater { MTAnalytics.instance.initAnalytics() }

  /**
   * Display wizard for new users
   */
  private fun initWizard() {
    val firstRun = !MTConfig.getInstance().isWizardShown
    if (firstRun) {
      ApplicationManager.getApplication().invokeLater { MTWizardDialog(MTWizardStepsProvider(), true).show() }
      MTConfig.getInstance().setIsWizardShown(true)
    }
  }

  private fun initComponent() {
    // Show the wizard
    initWizard()

    // Init analytics
    initAnalytics()
  }

  private fun disposeComponent() = cleanRegistry()

  private fun cleanRegistry() = MTThemeManager.instance.cleanRegistry()
}
