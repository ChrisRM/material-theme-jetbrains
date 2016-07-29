package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.MTThemeUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;


public class MTDefaultTheme extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        MTThemeUtil.setTheme(MTTheme.DEFAULT);
    }
}
