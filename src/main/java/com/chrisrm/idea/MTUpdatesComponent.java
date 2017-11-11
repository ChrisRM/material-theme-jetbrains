package com.chrisrm.idea;

import com.chrisrm.idea.utils.Notify;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class MTUpdatesComponent extends AbstractProjectComponent {
  private MTApplicationComponent application;

  protected MTUpdatesComponent(final Project project) {
    super(project);
  }

  @Override
  public void initComponent() {
    application = MTApplicationComponent.getInstance();
  }

  @Override
  public void projectOpened() {
    if (application.isUpdated()) {
      application.setUpdateNotificationShown(true);
      Notify.showUpdate(myProject);
    }
  }

  @Override
  public void disposeComponent() {
    application = null;
  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTUpdatesComponent";
  }
}
