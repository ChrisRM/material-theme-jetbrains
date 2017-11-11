package com.chrisrm.idea;

import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;

public class MTApplicationComponent implements ApplicationComponent {
  private boolean updated;
  private boolean updateNotificationShown;

  @Override
  public void initComponent() {
    updated = !MTUiUtils.getVersion().equals(MTConfig.getInstance().getVersion());
    if (updated) {
      MTConfig.getInstance().setVersion(MTUiUtils.getVersion());
    }
  }

  /** Component dispose method. */
  @Override
  public void disposeComponent() {
  }

  /**
   * Returns component's name.
   *
   * @return component's name
   */
  @NotNull
  @Override
  public String getComponentName() {
    return "MTApplicationComponent";
  }

  public static MTApplicationComponent getInstance() {
    return ServiceManager.getService(MTApplicationComponent.class);
  }

  public boolean isUpdated() {
    return updated;
  }

  public void setUpdateNotificationShown(final boolean updateNotificationShown) {
    this.updateNotificationShown = updateNotificationShown;
  }

  public boolean isUpdateNotificationShown() {
    return updateNotificationShown;
  }
}
