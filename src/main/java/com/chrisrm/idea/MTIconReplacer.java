package com.chrisrm.idea;

import com.chrisrm.idea.utils.IconReplacer;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ApplicationComponent;

import org.jetbrains.annotations.NotNull;

public class MTIconReplacer implements ApplicationComponent {

    public void initComponent() {
        IconReplacer.replaceIcons(AllIcons.class, "/icons");
    }

    public void disposeComponent() {

    }

    @NotNull
    public String getComponentName() {
        return "com.chrisrm.idea.MTIconReplacer";
    }
}
