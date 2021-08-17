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

import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.Notification
import com.intellij.notification.Notifications
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.notifications.MTInstallAtomNotification
import com.mallowigi.idea.notifications.MTNotifications
import com.mallowigi.idea.notifications.MTStatisticsNotification
import com.mallowigi.idea.notifications.MTWhatsNewAction
import com.mallowigi.idea.utils.MTUiUtils

class MTUpdatesComponent : StartupActivity.Background {
  private var config: MTConfig? = MTConfig.getInstance()

  override fun runActivity(project: Project) {
    config = MTConfig.getInstance()
    projectOpened(project)
  }

  private fun projectOpened(project: Project) {
    // Show new version notification
    val pluginVersion = MTUiUtils.getVersion()
    val updated = pluginVersion != config!!.getVersion()
    val showWhatsNew = config!!.isShowWhatsNew
    config!!.setVersion(pluginVersion)

    // Show notification update
    if (updated && showWhatsNew) {
      ApplicationManager.getApplication().invokeLater(
        { MTWhatsNewAction.openWhatsNewFile(project, MTWhatsNewAction.WHATS_NEW_URL, null) },
        project.disposed
      )
    }

    // Show agreement
    if (!isAgreementShown) {
      val notification = createStatsNotification()
      Notifications.Bus.notify(notification, project)
    }

    // Atom plugin
    if (updated && !isInstallAtomShown && !MTUiUtils.hasAtomPluginInstalled()) {
      val notification = createInstallAtomNotification()
      Notifications.Bus.notify(notification, project)
    }

    if (updated) {
      MTNotifications.showUpdate(project)
    }
  }


  /**
   * Create a stats notification.
   *
   * @return the notification
   */
  private fun createStatsNotification(): Notification = MTStatisticsNotification()

  /**
   * Create install atom notification
   *
   * @return: the notification
   */
  private fun createInstallAtomNotification(): Notification = MTInstallAtomNotification()

  private val isAgreementShown: Boolean
    get() = PropertiesComponent.getInstance().isValueSet(MTStatisticsNotification.SHOW_STATISTICS_AGREEMENT)

  private val isInstallAtomShown: Boolean
    get() = PropertiesComponent.getInstance().isValueSet(MTInstallAtomNotification.SHOW_INSTALL_ATOM)
}
