package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;


public class MTToggleProjectViewDecoratorsAction extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    // TODO: insert action logic here
    MTThemeManager.getInstance().toggleProjectViewDecorators();
  }

  @Override
  public void update(AnActionEvent e) {
    e.getPresentation().setEnabled(MTConfig.getInstance().isUseMaterialIcons());
  }
}
