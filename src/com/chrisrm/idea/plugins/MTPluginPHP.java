package com.chrisrm.idea.plugins;

import com.chrisrm.idea.utils.IconReplacer;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTPluginPHP implements ApplicationComponent {
    public MTPluginPHP() {
    }

    public void initComponent() {

        try {
            Class.forName("com.jetbrains.php.PhpIcons", false, this.getClass().getClassLoader());

            IconReplacer replacer = new IconReplacer();

            replacer.replaceIcons(com.jetbrains.php.PhpIcons.class, "/icons/plugins/php/");
        } catch (ClassNotFoundException e) {
            return;
        }
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTPluginPHP";
    }
}
