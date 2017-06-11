package com.chrisrm.idea.status;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;

public class MTStatusBarComponent extends AbstractProjectComponent {
  private MTStatusBarManager statusBarWidget;

  public MTStatusBarComponent(@NotNull Project project) {
    super(project);
  }

  @Override
  public void initComponent() {
    statusBarWidget = MTStatusBarManager.create(myProject);
  }

  @Override
  public void disposeComponent() {
    statusBarWidget.dispose();
  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTStatusBarComponent";
  }

  @Override
  public void projectOpened() {
    if (MTConfig.getInstance().isStatusBarTheme()) {
      statusBarWidget.install();
    }
  }

  @Override
  public void projectClosed() {
    statusBarWidget.uninstall();
  }
}
