package com.chrisrm.idea.schemes;

import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTColorSchemeComponent implements ApplicationComponent {

  @Override
  public void initComponent() {
    activateTheme();

    ApplicationManager.getApplication().getMessageBus().connect()
        .subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> activateTheme());
  }

  public void activateTheme() {
    MTThemeManager.getInstance().activate();
  }

  public void disposeComponent() {
    // TODO: insert component disposal logic here
  }

  @NotNull
  public String getComponentName() {
    return "MTColorSchemeComponent";
  }
}
