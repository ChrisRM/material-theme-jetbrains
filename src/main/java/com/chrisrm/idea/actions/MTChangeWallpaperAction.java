package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.Notify;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.*;

public class MTChangeWallpaperAction extends AnAction {
  public static final String FRAME_PROP = IdeBackgroundUtil.FRAME_PROP;

  @Override
  public void actionPerformed(AnActionEvent e) {
    installWallpaper(e.getProject());
  }

  private void installWallpaper(Project project) {
    final String defaultBackground = MTConfig.getInstance().getDefaultBackground();
    final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

    propertiesComponent.unsetValue(FRAME_PROP);
    propertiesComponent.setValue(FRAME_PROP, defaultBackground);

    IdeBackgroundUtil.repaintAllWindows();

    Notify.show(project,
                "",
                MaterialThemeBundle.message("mt.wallpaperInstalled"),
                NotificationType.INFORMATION,
                new NotificationListener.Adapter() {
                  @Override
                  protected void hyperlinkActivated(@NotNull Notification notification, @NotNull HyperlinkEvent e) {
                    ApplicationManager.getApplication().invokeLater(() -> ShowSettingsUtil.getInstance().showSettingsDialog(
                        project,
                        "Appearance"), ModalityState.NON_MODAL);
                  }
                });
  }
}
