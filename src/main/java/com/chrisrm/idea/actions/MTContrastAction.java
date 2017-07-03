package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class MTContrastAction extends AnAction {
  @Override
  public void actionPerformed(AnActionEvent e) {
    MTThemeManager.getInstance().toggleContrast();
  }

  @Override
  public void update(AnActionEvent e) {
    e.getPresentation().setEnabled(MTConfig.getInstance().isMaterialTheme());
  }
}
