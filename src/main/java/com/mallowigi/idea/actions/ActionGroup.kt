package com.mallowigi.idea.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;

public class ActionGroup extends DefaultActionGroup {
  @Override
  public void update(final AnActionEvent event) {
    final Presentation p = event.getPresentation();
    final boolean hasProject = event.getData(CommonDataKeys.PROJECT) != null;
    p.setVisible(hasProject);
  }
}
