/**
 * Created by chris on 27/09/15.
 *
 * http://alvinalexander.com/java/java-uimanager-color-keys-list
 */

package com.chrisrm.idea;

import com.chrisrm.idea.utils.IconReplacer;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ApplicationComponent;

import org.jetbrains.annotations.NotNull;

public class MTIconReplacer implements ApplicationComponent {


    public MTIconReplacer() {
    }

    public void initComponent() {
        IconReplacer replacer = new IconReplacer();

        replacer.replaceIcons(AllIcons.class, "/icons");
    }

    public void disposeComponent() {

    }

    @NotNull
    public String getComponentName() {
        return "com.chrisrm.idea.MTIconReplacer";
    }
}
