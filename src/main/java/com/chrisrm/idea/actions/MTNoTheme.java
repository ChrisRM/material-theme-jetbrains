package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class MTNoTheme extends MTAbstractTheme {
  @Override
  public void actionPerformed(AnActionEvent e) {
    MTThemeManager.getInstance().activate(MTTheme.NONE);
  }
}
