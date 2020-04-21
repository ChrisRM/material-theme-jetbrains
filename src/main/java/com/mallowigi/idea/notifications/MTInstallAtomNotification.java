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
package com.mallowigi.idea.notifications;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;

@SuppressWarnings("DialogTitleCapitalization")
public final class MTInstallAtomNotification extends Notification {

  @NonNls
  private static final String DECLINE = "decline";
  @NonNls
  public static final String SHOW_INSTALL_ATOM = "mt.showInstallAtom";

  @SuppressWarnings("FeatureEnvy")
  public MTInstallAtomNotification() {
    super(Notify.CHANNEL,
      MaterialThemeBundle.message("atom.plugin.title", MTUiUtils.getPluginName()),
      MaterialThemeBundle.message("atom.plugin.content", MaterialThemeBundle.message("mt.stats.plugin.team")),
      NotificationType.INFORMATION,
      (notification1, event) -> {
        MTConfig.getInstance().setAllowDataCollection(DECLINE.equals(event.getDescription()));
        PropertiesComponent.getInstance().setValue(SHOW_INSTALL_ATOM, true);
        notification1.expire();
      });
  }
}
