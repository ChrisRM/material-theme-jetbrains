package com.chrisrm.idea.status;

import com.chrisrm.idea.MTConfig;
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
  private boolean statusEnabled;
  private MTStatusWidget mtStatusWidget;
  private final MessageBusConnection connect;

  private MTStatusBarManager(@NotNull Project project) {
    this.project = project;
    this.mtStatusWidget = new MTStatusWidget();
    this.statusEnabled = MTConfig.getInstance().isStatusBarTheme();

    connect = project.getMessageBus().connect();
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, this::refreshWidget);
  }

  public static MTStatusBarManager create(@NotNull Project project) {
    return new MTStatusBarManager(project);
  }

  private void refreshWidget(MTConfig mtConfig) {
    if (mtConfig.isStatusBarThemeChanged(this.statusEnabled)) {
      statusEnabled = mtConfig.isStatusBarTheme();

      if (statusEnabled){
        this.install();
      } else {
        this.uninstall();
      }
    }

    mtStatusWidget.refresh();
  }

  void install() {
    StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
    if (statusBar != null) {
      statusBar.addWidget(mtStatusWidget, "before Position", project);
    }
  }


  void uninstall() {
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
