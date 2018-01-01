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

package com.chrisrm.idea.tree;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.icons.tinted.TintedIconsService;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.ProjectRootsUtil;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.util.PlatformIcons;

/**
 * Created by eliorb on 09/04/2017.
 */
public final class MTProjectViewNodeDecorator implements ProjectViewNodeDecorator {

  public MTProjectViewNodeDecorator() {
  }

  @Override
  public void decorate(final PackageDependenciesNode node, final ColoredTreeCellRenderer cellRenderer) {

  }

  @Override
  public void decorate(final ProjectViewNode node, final PresentationData data) {
    final VirtualFile file = node.getVirtualFile();
    final Project project = node.getProject();

    // Color file status
    if (file != null) {
      if (MTConfig.getInstance().getIsBoldTabs()) {
        // Color file status
        applyBoldTabs(data, file);
      }

      if (MTConfig.getInstance().isUseProjectViewDecorators()) {
        setOpenOrClosedIcon(data, file, project);
      }
    }
  }

  /**
   * Try to mimic the "open or closed"  folder feature
   */
  private void setOpenOrClosedIcon(final PresentationData data, final VirtualFile file, final Project project) {
    if (!file.isDirectory()) {
      return;
    }
    data.setIcon(TintedIconsService.getIcon("/icons/nodes/folderClosed.png", "ff00cc"));

    final FileEditorManagerEx manager = FileEditorManagerEx.getInstanceEx(project);
    for (final EditorWindow editorWindow : manager.getWindows()) {
      final VirtualFile[] files = editorWindow.getFiles();
      for (final VirtualFile leaf : files) {
        if (leaf.getPath().contains(file.getPath())) {
          setDirectoryIcon(data, file, project);
          colorOpenDirectories(data);
        }
      }
    }
  }

  private void colorOpenDirectories(final PresentationData data) {
    final String accentColor = MTConfig.getInstance().getAccentColor();
    data.setForcedTextForeground(ColorUtil.fromHex(accentColor));
  }

  private void setDirectoryIcon(final PresentationData data, final VirtualFile file, final Project project) {
    if (ProjectRootManager.getInstance(project).getFileIndex().isExcluded(file)) {
      data.setIcon(IconLoader.findIcon("/icons/modules/ExcludedTreeOpen.png"));
    } else if (ProjectRootsUtil.isModuleContentRoot(file, project)) {
      data.setIcon(IconLoader.findIcon("/icons/nodes/ModuleOpen.png"));
    } else if (ProjectRootsUtil.isInSource(file, project)) {
      data.setIcon(IconLoader.findIcon("/icons/modules/sourceRootOpen.png"));
    } else if (ProjectRootsUtil.isInTestSource(file, project)) {
      data.setIcon(IconLoader.findIcon("/icons/modules/testRootOpen.png"));
    } else if (data.getIcon(false).equals(PlatformIcons.PACKAGE_ICON)) {
      //      Looks like an open directory anyway
      data.setIcon(PlatformIcons.PACKAGE_ICON);
    } else {
      data.setIcon(TintedIconsService.getIcon("/icons/nodes/folderOpen.png", "ff00cc"));
    }
  }

  private void applyBoldTabs(final PresentationData data, final VirtualFile file) {
    if (file.isDirectory()) {
      data.setAttributesKey(CodeInsightColors.BOOKMARKS_ATTRIBUTES);
    }
  }

}
