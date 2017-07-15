/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.chrisrm.idea.config.scope;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.NonPhysicalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.search.scope.packageSet.AbstractPackageSet;
import com.intellij.psi.search.scope.packageSet.NamedScope;
import com.intellij.psi.search.scope.packageSet.NamedScopesHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MTPalenightNonProjectScope extends NamedScope {
  public static final String NAME = "Non-Project Files (Material Palenight)";

  public MTPalenightNonProjectScope() {
    super(NAME, new AbstractPackageSet("NonProject", 0) {
      @Override
      public boolean contains(final VirtualFile file, final NamedScopesHolder holder) {
        return contains(file, holder.getProject(), holder);
      }

      @Override
      public boolean contains(final VirtualFile file, @NotNull final Project project, @Nullable final NamedScopesHolder holder) {
        // do not include fake-files e.g. fragment-editors, database consoles, etc.
        if (file == null || file.getFileSystem() instanceof NonPhysicalFileSystem) {
          return false;
        }
        if (!file.isInLocalFileSystem()) {
          return true;
        }
        if (isInsideProjectContent(project, file)) {
          return false;
        }
        return !ProjectScope.getProjectScope(project).contains(file);
      }
    });
  }

  private static boolean isInsideProjectContent(@NotNull final Project project, @NotNull final VirtualFile file) {
    if (!file.isInLocalFileSystem()) {
      final String projectBaseDir = project.getBasePath();
      if (projectBaseDir != null) {
        return FileUtil.isAncestor(projectBaseDir, file.getPath(), false);
      }
    }
    return false;
  }
}
