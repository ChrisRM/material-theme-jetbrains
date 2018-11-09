/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea.status;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.listeners.ConfigNotifier;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Manages the Status bar widget
 */
public final class MTStatusBarManager implements Disposable, DumbAware {
  /**
   * Current project
   */
  private final Project project;
  /**
   * Whether the status bar widget is enabled
   */
  private boolean statusEnabled;
  /**
   * Status bar widget
   */
  @Nullable
  private MTStatusWidget mtStatusWidget;
  /**
   * Bus
   */
  private final MessageBusConnection connect;

  private MTStatusBarManager(@NotNull final Project project) {
    this.project = project;
    mtStatusWidget = new MTStatusWidget(project);
    statusEnabled = MTConfig.getInstance().isStatusBarTheme();

    connect = project.getMessageBus().connect();

    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        refreshWidget(mtConfig);
      }
    });
  }

  /**
   * Create mt status bar manager.
   *
   * @param project the project
   * @return the mt status bar manager
   */
  public static MTStatusBarManager create(@NotNull final Project project) {
    return new MTStatusBarManager(project);
  }

  /**
   * Reinstall/Uninstall the widget according to settings changes
   *
   * @param mtConfig new config
   */
  @SuppressWarnings("WeakerAccess")
  void refreshWidget(final MTConfig mtConfig) {
    if (mtConfig.isStatusBarThemeChanged(statusEnabled)) {
      statusEnabled = mtConfig.isStatusBarTheme();

      if (statusEnabled) {
        install();
      } else {
        uninstall();
      }
    }

    Objects.requireNonNull(mtStatusWidget).refresh();
  }

  /**
   * Install the status bar.
   */
  void install() {
    @NonNls final StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
    if (statusBar != null && mtStatusWidget != null) {
      statusBar.addWidget(mtStatusWidget, "before Position", project);
    }
  }

  /**
   * Uninstall the status bar.
   */
  void uninstall() {
    final StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
    if (statusBar != null) {
      statusBar.removeWidget(Objects.requireNonNull(mtStatusWidget).ID());
    }
  }

  @Override
  public void dispose() {
    if (!ApplicationManager.getApplication().isHeadlessEnvironment()) {
      if (mtStatusWidget != null) {
        uninstall();
        mtStatusWidget = null;
      }
    }
    connect.disconnect();
  }
}
