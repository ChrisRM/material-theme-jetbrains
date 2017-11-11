package com.chrisrm.idea.utils;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Notify {
  /**
   * Shows {@link Notification} in IGNORE_GROUP_UPDATE group.
   *
   * @param project  current project
   */
  public final static String CHANNEL = "MATERIAL_THEME";

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
