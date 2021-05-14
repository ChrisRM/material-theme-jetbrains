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

package com.mallowigi.idea.actions;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.mallowigi.idea.MTAnalytics;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.notifications.MTNotifications;
import com.mallowigi.idea.utils.MTUiUtils;

public class MTRemoveWallpaperAction extends AnAction {
  private static final String FRAME_PROP = IdeBackgroundUtil.FRAME_PROP;

  @Override
  public final void actionPerformed(final AnActionEvent e) {
    installWallpaper(e.getProject());
  }

  private static void installWallpaper(final Project project) {
    final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
    propertiesComponent.unsetValue(FRAME_PROP);

    IdeBackgroundUtil.repaintAllWindows();

    MTNotifications.show(project,
      "",
      MaterialThemeBundle.message("mt.actions.wallpaperRemoved"),
      NotificationType.INFORMATION,
      MTUiUtils.openAppearanceSettings(project));

    MTAnalytics.getInstance().track(MTAnalytics.REMOVE_WALLPAPER);
  }
}
