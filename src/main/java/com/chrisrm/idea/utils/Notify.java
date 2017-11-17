/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.utils;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Notify {

  private Notify() {
  }

  /**
   * Shows {@link Notification} in IGNORE_GROUP_UPDATE group.
   *
   * @param project  current project
   */
  private static final String CHANNEL = "MATERIAL_THEME";

  public static void showUpdate(@NotNull final Project project) {
    show(
        project,
        MaterialThemeBundle.message("notification.update.title", MTUiUtils.getVersion()),
        MaterialThemeBundle.message("notification.update.content"),
        CHANNEL + "_UPDATE",
        NotificationType.INFORMATION,
        NotificationListener.URL_OPENING_LISTENER
    );
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
  public static void show(@NotNull final Project project, @NotNull final String title, @NotNull final String content,
                          @NotNull final String displayId, @NotNull final NotificationType type,
                          @Nullable final NotificationListener listener) {
    final NotificationGroup group = new NotificationGroup(
        displayId,
        NotificationDisplayType.STICKY_BALLOON,
        true
    );
    final Notification notification = group.createNotification(title, content, type, listener);
    Notifications.Bus.notify(notification, project);
  }
}
