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
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.ide.ui.laf.IntelliJLookAndFeelInfo
import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.ui.Messages
import com.mallowigi.idea.config.MTBaseConfig
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.config.ui.MTForm
import com.mallowigi.idea.lafs.MTLafInstaller
import com.mallowigi.idea.listeners.ConfigNotifier
import com.mallowigi.idea.listeners.CustomConfigNotifier
import com.mallowigi.idea.listeners.MTTopics
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.themes.MTTheme
import com.mallowigi.idea.ui.MTButtonUI
import com.mallowigi.idea.ui.indicators.MTSelectedTreeIndicatorImpl
import com.mallowigi.idea.utils.MTUiUtils
import javax.swing.UIManager

/**
 * Component for working on the Material Look And Feel
 */
class MTLafComponent : AppLifecycleListener {
  private var activeLookAndFeel: UIManager.LookAndFeelInfo? = null
  private var willRestartIde = false
  private var shouldChangeTheme = false

  /**
   * Run on App frame created
   *
   */
  override fun appFrameCreated(commandLineArgs: List<String>): Unit = initComponent()

  /**
   * Run on App frame started temporarily
   * TODO Remove once code with license checker is fixed
   */
  @Suppress("UnstableApiUsage")
  override fun appStarted() {
    ApplicationManager.getApplication().invokeAndWait(fun() {
      MTThemeManager.resetColorScheme()
      activateLaf(activeLookAndFeel)
    }, ModalityState.NON_MODAL)
  }

  /**
   * Activate theme when Look and feel changed
   *
   * @param source the source look and feel
   */
  private fun lookAndFeelChanged(source: LafManager) {
    val currentLookAndFeel = source.currentLookAndFeel
    patchTree()
    MTButtonUI.resetCache()

    // Prevent infinite loop
    if (currentLookAndFeel === activeLookAndFeel) return
    // Save instance of current laf
    activeLookAndFeel = currentLookAndFeel
    MTThemeManager.resetColorScheme()
    activateLaf(currentLookAndFeel)
  }

  /**
   * Activate the relevant look and feel (theme, darcula or light)
   *
   * @param currentLookAndFeel
   */
  @Suppress("HardCodedStringLiteral")
  private fun activateLaf(currentLookAndFeel: UIManager.LookAndFeelInfo?) {
    val oldLaf = LafManager.getInstance().currentLookAndFeel
    if (oldLaf is UIThemeBasedLookAndFeelInfo) {
      oldLaf.dispose()
    }

    val mtThemeManager = MTThemeManager.instance
    when {
      currentLookAndFeel is UIThemeBasedLookAndFeelInfo -> mtThemeManager.activateLAF(currentLookAndFeel.theme)
      activeLookAndFeel is DarculaLookAndFeelInfo       -> mtThemeManager.activateLAF(
        themeId = "darcula",
        isDark = true,
        name = MTUiUtils.DARCULA
      )
      activeLookAndFeel is IntelliJLookAndFeelInfo      -> mtThemeManager.activateLAF(
        themeId = "default",
        isDark = false,
        name = "Light"
      )
    }
  }

  /**
   * Listen for settings change to reload the theme and trigger restart if necessary
   */
  private fun initComponent() {
    activeLookAndFeel = LafManager.getInstance().currentLookAndFeel

    // Activate the theme
    ApplicationManager.getApplication().invokeAndWait({ activateLaf(activeLookAndFeel) }, ModalityState.NON_MODAL)

    // Listen for changes on the settings
    val connect = ApplicationManager.getApplication().messageBus.connect()

    connect.subscribe(
      MTTopics.CONFIG,
      object : ConfigNotifier {
        override fun configChanged(mtConfig: MTConfig) = onSettingsChanged()

        override fun beforeConfigChanged(mtConfig: MTConfig, form: MTForm) = onBeforeSettingsChanged(mtConfig, form)
      }
    )

    connect.subscribe(MTTopics.CUSTOM_THEME, CustomConfigNotifier { activateCustomTheme() })
    connect.subscribe(LafManagerListener.TOPIC, LafManagerListener(::lookAndFeelChanged))

    patchTree()
  }

  /**
   * Called before Material Settings are changed
   *
   * @param mtConfig of type MTConfig
   * @param form     of type MTForm
   */
  fun onBeforeSettingsChanged(mtConfig: MTConfig, form: MTForm) {
    shouldChangeTheme = MTConfig.getInstance().isSelectedThemeChanged(form.theme)
    // Force restart if material design is switched
    restartIdeIfNecessary(mtConfig, form)
  }

  /**
   * Restart IDE if necessary (ex: material design components)
   *
   * @param mtConfig of type MTConfig
   * @param form     of type MTForm
   */
  private fun restartIdeIfNecessary(mtConfig: MTBaseConfig<MTForm, MTConfig>, form: MTForm) {
    // Restart the IDE if changed
    if (mtConfig.needsRestart(form)) {
      val title = message("MTForm.restartDialog.title")
      val message = message("MTForm.restartDialog.content")
      val answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon())
      if (answer == Messages.YES) {
        willRestartIde = true
      }
    }
  }

  /**
   * Called when Material Theme settings are changed
   */
  fun onSettingsChanged() {
    MTSelectedTreeIndicatorImpl.resetCache()
    MTButtonUI.resetCache()

    val currentLookAndFeel = LafManager.getInstance().currentLookAndFeel
    if (shouldChangeTheme) {
      MTThemeManager.instance.setLookAndFeel(MTConfig.getInstance().selectedTheme)
    }

    // if theme hasnt changed, force reactivation
    if (currentLookAndFeel === activeLookAndFeel) {
      activateTheme()
    }

    ApplicationManager.getApplication().invokeAndWait({ UIReplacer.patchUI() }, ModalityState.NON_MODAL)

    if (willRestartIde) {
      MTUiUtils.restartIde()
    }
  }

  /**
   * Activate custom theme
   *
   */
  private fun activateCustomTheme() {
    val mtConfig = MTConfig.getInstance()
    val isNotCustom = !mtConfig.selectedTheme.isCustom
    if (isNotCustom) {
      val okCancelDialog = Messages.showOkCancelDialog(
        message("MTThemes.activate.custom.theme"),
        message("MTThemesComponent.activate.custom.theme"),
        message("common.ok"),
        message("common.cancel"),
        Messages.getQuestionIcon()
      )
      if (okCancelDialog == Messages.OK) {
        mtConfig.selectedTheme = MTTheme.CUSTOM
        MTThemeManager.instance.setLookAndFeel(MTTheme.CUSTOM)
      }
    }
    activateTheme()
  }

  /**
   * Activate current theme
   *
   */
  private fun activateTheme(): Unit = MTThemeManager.instance.activate()

  /**
   * Patch trees
   *
   */
  private fun patchTree() =
    ApplicationManager.getApplication().invokeLater { MTLafInstaller.replaceTree() }
}
