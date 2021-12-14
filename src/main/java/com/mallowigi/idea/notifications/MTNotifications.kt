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
package com.mallowigi.idea.notifications

import com.intellij.notification.Notification
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.notification.impl.NotificationsManagerImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.BalloonLayoutData
import com.intellij.ui.awt.RelativePoint
import com.mallowigi.idea.MTThemeManager
import com.mallowigi.idea.messages.MaterialThemeBundle
import com.mallowigi.idea.utils.MTUiUtils
import java.awt.Point
import java.util.Objects

/**
 * Service for sending notifications
 *
 */
@Suppress("UnstableApiUsage")
object MTNotifications {
  private const val fromTop = 20

  /**
   * Notification channel ID
   */
  const val CHANNEL: String = "Material Theme Notifications"

  /**
   * Show the update notification
   *
   * @param project  the project to display in
   */
  @Suppress("DialogTitleCapitalization")
  @JvmStatic
  fun showUpdate(project: Project) {
    val notification = createNotification(
      MaterialThemeBundle.message("notification.update.title", MTUiUtils.getVersion()),
      MaterialThemeBundle.message("notification.update.content"),
      NotificationType.INFORMATION
    )
    showFullNotification(project, notification)
  }

  /**
   * Show a simple notification
   *
   * @param project the project concerned
   * @param content the content text
   */
  @JvmStatic
  fun showSimple(
    project: Project,
    @NlsContexts.NotificationContent content: String,
  ) {
    val notification = createNotification("", content, NotificationType.INFORMATION)
    Notifications.Bus.notify(notification, project)
  }

  /**
   * Shows [Notification] in [MTNotifications.CHANNEL] group.
   *
   * @param project  current project
   * @param title    notification title
   * @param content  notification text
   * @param type     notification type
   * @param listener optional listener
   */
  @JvmStatic
  fun showWithListener(
    project: Project,
    @NlsContexts.NotificationTitle title: String,
    @NlsContexts.NotificationContent content: String,
    type: NotificationType,
    listener: NotificationListener?,
  ) {
    val notification = createNotification(title, content, type, listener)
    Notifications.Bus.notify(notification, project)
  }

  /**
   * Create a notification
   *
   * @param title    notification title
   * @param content  the content
   * @param type     the type (sticky...)
   * @return new notification to be displayed
   */
  private fun createNotification(
    @NlsContexts.NotificationTitle title: String,
    @NlsContexts.NotificationContent content: String,
    type: NotificationType,
  ): Notification {
    val group = NotificationGroupManager.getInstance().getNotificationGroup(CHANNEL)
    return group.createNotification(title, content, type)
  }

  /**
   * Create a notification
   *
   * @param title    notification title
   * @param content  the content
   * @param type     the type (sticky…)
   * @param listener listener
   * @return new notification to be displayed
   */
  private fun createNotification(
    @NlsContexts.NotificationTitle title: String,
    @NlsContexts.NotificationContent content: String,
    type: NotificationType,
    listener: NotificationListener?,
  ): Notification {
    assert(listener != null)
    return createNotification(title, content, type).setListener(listener!!)
  }

  /**
   * Show a notification using the Balloon API instead of the bus
   * Credit to @vladsch
   *
   * @param project      the project to display into
   * @param notification the notification to display
   */
  private fun showFullNotification(project: Project, notification: Notification) {
    run {
      val frame = WindowManager.getInstance().getIdeFrame(project) ?: return
      val bounds = Objects.requireNonNull(frame).component.bounds
      val target = RelativePoint(frame.component, Point(bounds.x + bounds.width, fromTop))
      try {
        // Create a notification balloon using the manager
        val balloon = NotificationsManagerImpl.createBalloon(
          frame,
          notification,
          true,
          true,
          BalloonLayoutData.fullContent(),
          MTThemeManager.instance
        )
        // Display the balloon at the top right
        balloon.show(target, Balloon.Position.atLeft)
      } catch (e: NoSuchMethodError) {
        notification.notify(project)
      } catch (e: NoClassDefFoundError) {
        notification.notify(project)
      } catch (e: NoSuchFieldError) {
        notification.notify(project)
      }
    }
  }
}
