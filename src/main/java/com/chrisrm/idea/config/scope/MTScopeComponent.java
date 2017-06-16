package com.chrisrm.idea.config.scope;

import com.chrisrm.idea.actions.MTDarkerTheme;
import com.chrisrm.idea.actions.MTDefaultTheme;
import com.chrisrm.idea.actions.MTLighterTheme;
import com.chrisrm.idea.actions.MTPalenightTheme;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.FileColorManager;

public class MTScopeComponent extends AbstractProjectComponent implements ProjectComponent {

  protected MTScopeComponent(Project project) {
    super(project);
  }

  @Override
  public void initComponent() {
    addDisabledFileColors();
  }

  private void addDisabledFileColors() {
    FileColorManager manager = FileColorManager.getInstance(myProject);
    manager.addScopeColor(MTDefaultNonProjectScope.NAME, MTDefaultTheme.DISABLED, false);
    manager.addScopeColor(MTDarkerNonProjectScope.NAME, MTDarkerTheme.DISABLED, false);
    manager.addScopeColor(MTLighterNonProjectScope.NAME, MTLighterTheme.DISABLED, false);
    manager.addScopeColor(MTPalenightNonProjectScope.NAME, MTPalenightTheme.DISABLED, false);
  }

}
