package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;


public class MTLighterTheme extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        MTTheme.LIGHTER.activate();
    }
}
