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

package com.mallowigi.idea.notifications;

import com.intellij.notification.*;
import com.intellij.notification.impl.NotificationsManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.BalloonLayoutData;
import com.intellij.ui.awt.RelativePoint;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;

public enum MTNotifications {
  DEFAULT;

  /**
   * Shows {@link Notification} in IGNORE_GROUP_UPDATE group.
   */
  @NonNls
  public static final String CHANNEL = "Material Theme Notifications";

  /**
   * Show the update notification
   *
   * @param project  the project to display in
   * @param listener optional listener
   */
  public static void showUpdate(@NotNull final Project project, final NotificationListener listener) {
    final Notification notification = createNotification(
      MaterialThemeBundle.message("notification.update.title", MTUiUtils.getVersion()),
      MaterialThemeBundle.message("notification.update.content"),
      NotificationType.INFORMATION,
      listener
    );

    showFullNotification(project, notification);
  }

  public static void showSimple(@NotNull final Project project, @NotNull final String content) {
    final Notification notification = createNotification("", content, NotificationType.INFORMATION);
    Notifications.Bus.notify(notification, project);
  }

  /**
   * Shows {@link Notification} in {@link MTNotifications#CHANNEL} group.
   *
   * @param project  current project
   * @param title    notification title
   * @param content  notification text
   * @param type     notification type
   * @param listener optional listener
   */
  public static void showWithListener(@NotNull final Project project,
                                      @NotNull final String title,
                                      @NotNull final String content,
                                      @NotNull final NotificationType type,
                                      @Nullable final NotificationListener listener) {
    final Notification notification = createNotification(title, content, type, listener);
    Notifications.Bus.notify(notification, project);
  }

  @NotNull
  private static Notification createNotification(@NotNull final String title,
                                                 @NotNull final String content,
                                                 @NotNull final NotificationType type) {
    final NotificationGroup group = NotificationGroupManager.getInstance().getNotificationGroup(CHANNEL);
    return group.createNotification(title, content, type);
  }

  /**
   * Create a notification
   *
   * @param title    notification title
   * @param content  the content
   * @param type     the type (sticky...)
   * @param listener optional listener
   * @return new notification to be displayed
   */
  @NotNull
  private static Notification createNotification(@NotNull final String title,
                                                 @NotNull final String content,
                                                 @NotNull final NotificationType type,
                                                 @Nullable final NotificationListener listener) {
    assert listener != null;
    return createNotification(title, content, type).setListener(listener);
  }

  /**
   * Show a notification using the Balloon API instead of the bus
   * Credit to @vladsch
   *
   * @param project      the project to display into
   * @param notification the notification to display
   */
  @SuppressWarnings("ErrorNotRethrown")
  private static void showFullNotification(final Project project, final Notification notification) {
    {
      final IdeFrame frame = WindowManager.getInstance().getIdeFrame(project);
      final Rectangle bounds = Objects.requireNonNull(frame).getComponent().getBounds();
      final RelativePoint target = new RelativePoint(frame.getComponent(), new Point(bounds.x + bounds.width, 20));

      try {
        // Create a notification balloon using the manager
        final Balloon balloon = NotificationsManagerImpl.createBalloon(frame,
          notification,
          true,
          true,
          BalloonLayoutData.fullContent(),
          MTThemeManager.getInstance()
        );
        // Display the balloon at the top right
        balloon.show(target, Balloon.Position.atLeft);
      } catch (final NoSuchMethodError | NoClassDefFoundError | NoSuchFieldError e) {
        notification.notify(project);
      }
    }
  }
}
