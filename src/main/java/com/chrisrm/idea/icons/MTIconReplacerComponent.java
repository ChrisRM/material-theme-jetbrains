package com.chrisrm.idea.icons;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.utils.IconReplacer;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTIconReplacerComponent implements ApplicationComponent {

    public void initComponent() {
        if (MTConfig.getInstance().isUseMaterialIcons()) {
            IconReplacer.replaceIcons(AllIcons.class, "/icons");
        }
    }

    public void disposeComponent() {

    }

    @NotNull
    public String getComponentName() {
      return "com.chrisrm.idea.icons.MTIconReplacerComponent";
    }
}
