package com.chrisrm.idea.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;


public class MTDefaultTheme extends AnAction {

    String name = "Default";

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        new MTSwitchTheme().setTheme(this.name);
    }
}
