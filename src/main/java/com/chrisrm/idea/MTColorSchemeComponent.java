package com.chrisrm.idea;

import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTColorSchemeComponent implements ApplicationComponent {

    @Override
    public void initComponent() {
        MTTheme.getCurrentPreference().activate();

        ApplicationManager.getApplication().getMessageBus().connect()
                          .subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> MTTheme.getCurrentPreference().activate());
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTColorSchemeComponent";
    }
}
