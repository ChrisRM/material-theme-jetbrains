/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea;

import com.intellij.ide.BrowserUtil;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.mallowigi.idea.notifications.MTStatisticsNotification;
import com.mallowigi.idea.notifications.Notify;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.event.HyperlinkEvent;
import java.net.URL;

/**
 * Component for showing update notification
 */
public final class MTUpdatesComponent implements ProjectComponent {
  @NonNls
  private MTConfig config = null;

  @NotNull
  private final Project myProject;

  /**
   * Instantiates a new Mt updates component.
   *
   * @param project the project
   */
  private MTUpdatesComponent(@NotNull final Project project) {
    myProject = project;
  }

  /**
   * Open Paypal/OpenCollective link and add event
   *
   * @param notification The notification
   * @param event        The click to link event
   */
  private static void onPaypalClick(final Notification notification, final HyperlinkEvent event) {
    final URL url = event.getURL();

    try {
      @NonNls final JSONObject props = new JSONObject();
      props.put("Url", url);

      MTAnalytics.getInstance().trackWithData(MTAnalytics.UPDATE_NOTIFICATION, props);
    } catch (final JSONException ignored) {
    }

    if (url == null) {
      BrowserUtil.browse(event.getDescription());
    } else {
      BrowserUtil.browse(url);
    }

    notification.expire();
  }

  @Override
  public void initComponent() {
    config = MTConfig.getInstance();
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  public void projectOpened() {
    // Show new version notification
    @NonNls final String pluginVersion = MTUiUtils.getVersion();
    final boolean updated = !pluginVersion.equals(config.getVersion());

    // Show notification update
    if (updated) {
      config.setVersion(pluginVersion);
      Notify.showUpdate(myProject, MTUpdatesComponent::onPaypalClick);
    }

    // Show agreement
    if (!isAgreementShown()) {
      final Notification notification = createStatsNotification();

      Notifications.Bus.notify(notification, myProject);
    }
  }

  /**
   * Create a stats notification.
   *
   * @return the notification
   */
  private static Notification createStatsNotification() {
    final NotificationGroup group = new NotificationGroup(
      Notify.CHANNEL,
      NotificationDisplayType.STICKY_BALLOON,
      true
    );
    final MTStatisticsNotification notif = new MTStatisticsNotification();
    return group.createNotification(
      notif.getTitle(),
      notif.getSubtitle(),
      notif.getContent(),
      notif.getType(),
      notif.getListener()
    );
  }

  @Override
  public void disposeComponent() {
  }

  @NonNls
  @NotNull
  @Override
  public String getComponentName() {
    return "MTUpdatesComponent";
  }

  /**
   * Checks that the statistics agreement popup has been displayed
   *
   * @return true if displayed
   */
  private static boolean isAgreementShown() {
    return PropertiesComponent.getInstance().isValueSet(MTStatisticsNotification.SHOW_STATISTICS_AGREEMENT);
  }
}
