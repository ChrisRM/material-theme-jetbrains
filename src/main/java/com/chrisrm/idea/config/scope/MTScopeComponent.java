package com.chrisrm.idea.config.scope;

import com.chrisrm.idea.MTConfig;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.scope.NonProjectFilesScope;
import com.intellij.psi.search.scope.packageSet.NamedScope;
import com.intellij.psi.search.scope.packageSet.NamedScopesHolder;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.FileColorManager;
import com.intellij.ui.tabs.FileColorManagerImpl;

import java.awt.*;

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
    lafManagerListener = source -> addDisabledFileColors();
  }

  @Override
  public void projectOpened() {
    addDisabledFileColors();
    lafManager.addLafManagerListener(lafManagerListener);
  }

  @Override
  public void projectClosed() {
    addDisabledFileColors();
    lafManager.removeLafManagerListener(lafManagerListener);
  }

  private void addDisabledFileColors() {
    Color disabledColor = MTConfig.getInstance().getSelectedTheme().getDisabledColor();
    FileColorManager manager = FileColorManager.getInstance(myProject);
    manager.addScopeColor(MTNonProjectScope.NAME, ColorUtil.toHex(disabledColor), false);
  }

}
