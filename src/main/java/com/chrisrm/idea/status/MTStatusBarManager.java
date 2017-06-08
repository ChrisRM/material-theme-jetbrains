package com.chrisrm.idea.status;

import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

class MTStatusBarManager implements Disposable, DumbAware {

  private final Project project;
  private MTStatusWidget mtStatusWidget;
  private final MessageBusConnection connect;

  MTStatusBarManager(@NotNull Project project) {
    this.project = project;
    this.mtStatusWidget = new MTStatusWidget();

    connect = project.getMessageBus().connect();
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> refreshWidget());
  }

  public static MTStatusBarManager create(@NotNull Project project) {
    return new MTStatusBarManager(project);
  }

  private void refreshWidget() {
    mtStatusWidget.refresh();
  }

  public void install() {
    StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
    if (statusBar != null) {
      statusBar.addWidget(mtStatusWidget, "before Position", project);
    }
  }

  public void uninstall() {
    StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
    if (statusBar != null) {
      statusBar.removeWidget(mtStatusWidget.ID());
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
