package com.chrisrm.idea.config.scope;

import com.chrisrm.idea.actions.MTDarkerTheme;
import com.chrisrm.idea.actions.MTDefaultTheme;
import com.chrisrm.idea.actions.MTLighterTheme;
import com.chrisrm.idea.actions.MTPalenightTheme;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.FileColorManager;

public class MTScopeComponent extends AbstractProjectComponent implements ProjectComponent {
  private final LafManager lafManager;
  private LafManagerListener lafManagerListener;

  protected MTScopeComponent(Project project, LafManager lafManager) {
    super(project);
    this.lafManager = lafManager;
  }

  @Override
  public void initComponent() {
    addDisabledFileColors();
    //    lafManagerListener = source -> addDisabledFileColors();
  }

  //  @Override
  //  public void projectOpened() {
  //    addDisabledFileColors();
  //    lafManager.addLafManagerListener(lafManagerListener);
  //  }
  //
  //  @Override
  //  public void projectClosed() {
  //    addDisabledFileColors();
  //    lafManager.removeLafManagerListener(lafManagerListener);
  //  }

  private void addDisabledFileColors() {
    FileColorManager manager = FileColorManager.getInstance(myProject);
    manager.addScopeColor(MTDefaultNonProjectScope.NAME, MTDefaultTheme.DISABLED, false);
    manager.addScopeColor(MTDarkerNonProjectScope.NAME, MTDarkerTheme.DISABLED, false);
    manager.addScopeColor(MTLighterNonProjectScope.NAME, MTLighterTheme.DISABLED, false);
    manager.addScopeColor(MTPalenightNonProjectScope.NAME, MTPalenightTheme.DISABLED, false);
  }

}
