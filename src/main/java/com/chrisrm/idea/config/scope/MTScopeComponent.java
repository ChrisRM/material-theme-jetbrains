package com.chrisrm.idea.config.scope;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.ApplicationComponent;
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
  protected MTScopeComponent(Project project) {
    super(project);
  }

  @Override
  public void projectOpened() {
    NonProjectFilesScope.removeFromList(new NamedScope[]{NamedScopesHolder.getScope(myProject, NonProjectFilesScope.NAME)});

    Color disabledColor = MTConfig.getInstance().getSelectedTheme().getDisabledColor();
    // todo create an association file or a color schemes
    FileColorManagerImpl manager = (FileColorManagerImpl) FileColorManager.getInstance(myProject);
    manager.addScopeColor(MTNonProjectScope.NAME, ColorUtil.toHex(disabledColor), false);
  }

}
