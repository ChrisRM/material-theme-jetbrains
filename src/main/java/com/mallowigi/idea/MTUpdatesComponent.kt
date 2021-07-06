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

import com.intellij.ide.BrowserUtil
import com.intellij.ide.plugins.PluginManager
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.Notification
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.Notifications
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.mallowigi.idea.messages.MaterialThemeBundle
import com.mallowigi.idea.notifications.MTInstallAtomNotification
import com.mallowigi.idea.notifications.MTNotifications
import com.mallowigi.idea.notifications.MTStatisticsNotification
import com.mallowigi.idea.notifications.MTWhatsNewAction
import com.mallowigi.idea.utils.MTUiUtils
import org.json.JSONException
import org.json.JSONObject
import javax.swing.event.HyperlinkEvent

/**
 * Component for showing update notification
 */
class MTUpdatesComponent : StartupActivity {
  private var config: MTConfig? = null
  private var myProject: Project? = null

  override fun runActivity(project: Project) {
    myProject = project
    config = MTConfig.getInstance()
    projectOpened()
  }

  private fun projectOpened() {
    // Show new version notification
    val pluginVersion = MTUiUtils.getVersion()
    val updated = pluginVersion != config!!.getVersion()
    config!!.setVersion(pluginVersion)

    // Show notification update
    if (updated && MTConfig.getInstance().isShowWhatsNew) {
      ApplicationManager.getApplication().invokeLater {
        MTWhatsNewAction.openWhatsNewFile(myProject!!, MTWhatsNewAction.WHATS_NEW_URL, null)
      }
    }

    // Show agreement
    if (!isAgreementShown) {
      val notification = createStatsNotification()
      Notifications.Bus.notify(notification, myProject)
    }
    if (updated && !isInstallAtomShown && !PluginManager.isPluginInstalled(PluginId.getId(MaterialThemeBundle.message("atom.pluginid")))) {
      val notification = createInstallAtomNotification()
      Notifications.Bus.notify(notification, myProject)
    }

    if (updated) {
      MTNotifications.showUpdate(myProject!!, MTUpdatesComponent::onPaypalClick);
    }
  }

  companion object {
    /**
     * Open Paypal/OpenCollective link and add event
     *
     * @param notification The notification
     * @param event        The click to link event
     */
    @JvmStatic
    fun onPaypalClick(notification: Notification, event: HyperlinkEvent) {
      val url = event.url
      try {
        val props = JSONObject()
        props.put("Url", url)
        MTAnalytics.getInstance().trackWithData(MTAnalytics.UPDATE_NOTIFICATION, props)
      } catch (ignored: JSONException) {
      }

      if (url == null) BrowserUtil.browse(event.description)
      else BrowserUtil.browse(url)
      notification.expire()
    }

    /**
     * Create a stats notification.
     *
     * @return the notification
     */
    private fun createStatsNotification(): Notification {
      val group = NotificationGroupManager.getInstance().getNotificationGroup(MTNotifications.CHANNEL)
      val notif = MTStatisticsNotification()
      return group.createNotification(
        notif.title,
        notif.subtitle,
        notif.content,
        notif.type,
        notif.listener
      )
    }

    private fun createInstallAtomNotification(): Notification {
      val group = NotificationGroupManager.getInstance().getNotificationGroup(MTNotifications.CHANNEL)
      val notif = MTInstallAtomNotification()
      return group.createNotification(
        notif.title,
        notif.subtitle,
        notif.content,
        notif.type,
        notif.listener
      )
    }

    private val isAgreementShown: Boolean
      get() = PropertiesComponent.getInstance().isValueSet(MTStatisticsNotification.SHOW_STATISTICS_AGREEMENT)

    private val isInstallAtomShown: Boolean
      get() = PropertiesComponent.getInstance().isValueSet(MTInstallAtomNotification.SHOW_INSTALL_ATOM)
  }
}
