/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.notifications;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.notification.*;
import com.intellij.notification.impl.NotificationsManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.BalloonLayoutData;
import com.intellij.ui.awt.RelativePoint;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@SuppressWarnings("MethodWithTooManyParameters")
public enum Notify {
  DEFAULT;

  /**
   * Shows {@link Notification} in IGNORE_GROUP_UPDATE group.
   */
  @NonNls
  static final String CHANNEL = "Material Theme Notifications";

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
        CHANNEL + "_UPDATE",
        NotificationType.INFORMATION,
        listener
    );

    showFullNotification(project, notification);
  }

  /**
   * Shows {@link Notification} in {@link Notify#CHANNEL} group.
   *
   * @param project  current project
   * @param title    notification title
   * @param content  notification text
   * @param type     notification type
   * @param listener optional listener
   */
  public static void show(@NotNull final Project project, @NotNull final String title, @NotNull final String content,
                          @NotNull final NotificationType type, @Nullable final NotificationListener listener) {
    show(project, title, content, CHANNEL, type, listener);
  }

  /**
   * Shows {@link Notification}.
   *
   * @param project   current project
   * @param title     notification title
   * @param displayId notification group
   * @param content   notification text
   * @param type      notification type
   * @param listener  optional listener
   */
  private static void show(@NotNull final Project project, @NotNull final String title, @NotNull final String content,
                           @NotNull final String displayId, @NotNull final NotificationType type,
                           @Nullable final NotificationListener listener) {
    final Notification notification = createNotification(title, content, displayId, type, listener);
    Notifications.Bus.notify(notification, project);
  }

  /**
   * Create a notification
   *
   * @param title     notification title
   * @param content   the content
   * @param displayId the channel id
   * @param type      the type (sticky...)
   * @param listener  optional listener
   * @return new notification to be displayed
   */
  @NotNull
  private static Notification createNotification(@NotNull final String title,
                                                 @NotNull final String content,
                                                 @NonNls @NotNull final String displayId,
                                                 @NotNull final NotificationType type,
                                                 @Nullable final NotificationListener listener) {
    final NotificationGroup group = new NotificationGroup(
        displayId,
        NotificationDisplayType.STICKY_BALLOON,
        true
    );
    return group.createNotification(title, content, type, listener);
  }

  /**
   * Show a notification using the Balloon API instead of the bus
   * Credit to @vladsch
   *
   * @param project      the project to display into
   * @param notification the notification to display
   */
  private static void showFullNotification(final Project project, final Notification notification) {
    {
      final IdeFrame frame = WindowManager.getInstance().getIdeFrame(project);
      final Rectangle bounds = frame.getComponent().getBounds();
      final RelativePoint target = new RelativePoint(frame.getComponent(), new Point(bounds.x + bounds.width, 20));

      try {
        // Create a notification balloon using the manager
        final Balloon balloon = NotificationsManagerImpl.createBalloon(frame,
                                                                       notification,
                                                                       true,
                                                                       true,
                                                                       BalloonLayoutData.fullContent(),
                                                                       () -> {
                                                                       }
        );
        // Display the balloon at the top right
        balloon.show(target, Balloon.Position.atLeft);
      } catch (final NoSuchMethodError | NoClassDefFoundError | NoSuchFieldError e) {
        notification.notify(project);
      }
    }
  }
}
