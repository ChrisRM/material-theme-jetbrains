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
package com.chrisrm.idea.notifications;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.notification.impl.NotificationActionProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkListener;

/**
 * @author Sergey.Malenkov
 */
public final class MTStatisticsNotification extends Notification implements NotificationActionProvider {

  @NonNls
  public static final String ALLOW = "allow";
  @NonNls
  private static final String DECLINE = "decline";

  @SuppressWarnings("FeatureEnvy")
  public MTStatisticsNotification(final NotificationListener listener) {
    super(Notify.CHANNEL,
        MaterialThemeBundle.message("mt.stats.notification.title", MTUiUtils.getPluginName()),
        MaterialThemeBundle.message("mt.stats.config.details", MaterialThemeBundle.message("mt.stats.plugin.team")),
        NotificationType.INFORMATION, listener);
  }

  @NonNls
  @Override
  @NotNull
  public Action[] getActions(final HyperlinkListener listener) {
    return new Action[]{
        new Action(listener, ALLOW, MaterialThemeBundle.message("mt.stats.notification.button.allow")),
        new Action(listener, DECLINE, MaterialThemeBundle.message("mt.stats.notification.button.decline")),
    };
  }
}
