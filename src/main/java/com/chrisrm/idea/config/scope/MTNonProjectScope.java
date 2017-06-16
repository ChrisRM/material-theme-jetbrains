package com.chrisrm.idea.config.scope;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.NonPhysicalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.search.scope.NonProjectFilesScope;
import com.intellij.psi.search.scope.packageSet.AbstractPackageSet;
import com.intellij.psi.search.scope.packageSet.NamedScope;
import com.intellij.psi.search.scope.packageSet.NamedScopesHolder;
import com.intellij.ui.Colored;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MTNonProjectScope extends NamedScope {
  public static final String NAME = "Non-Project Files (Material)";

  public MTNonProjectScope() {
    super(NAME, new AbstractPackageSet("NonProject", 0) {
      @Override
      public boolean contains(VirtualFile file, NamedScopesHolder holder) {
        return contains(file, holder.getProject(), holder);
      }

      @Override
      public boolean contains(VirtualFile file, @NotNull Project project, @Nullable NamedScopesHolder holder) {
        // do not include fake-files e.g. fragment-editors, database consoles, etc.
        if (file == null || file.getFileSystem() instanceof NonPhysicalFileSystem) return false;
        if (!file.isInLocalFileSystem()) return true;
        if (isInsideProjectContent(project, file)) return false;
        return !ProjectScope.getProjectScope(project).contains(file);
      }
    });
  }

  private static boolean isInsideProjectContent(@NotNull Project project, @NotNull VirtualFile file) {
    if (!file.isInLocalFileSystem()) {
      final String projectBaseDir = project.getBasePath();
      if (projectBaseDir != null) {
        return FileUtil.isAncestor(projectBaseDir, file.getPath(), false);
      }
    }
    return false;
  }
}
