/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea.tree;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.icons.DirIcon;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.ProjectRootsUtil;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.util.PlatformIcons;
import icons.MTIcons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

import static com.chrisrm.idea.config.MTFileColorsPage.DIRECTORIES;

/**
 * Created by eliorb on 09/04/2017.
 */
public final class MTProjectViewNodeDecorator implements ProjectViewNodeDecorator {

  @Nullable
  private static volatile Icon directory = MTIcons.Nodes2.FolderOpen;

  public MTProjectViewNodeDecorator() {
  }

  public static void resetCache() {
    directory = null;
  }

  @Override
  public void decorate(final PackageDependenciesNode node, final ColoredTreeCellRenderer cellRenderer) {

  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  public void decorate(final ProjectViewNode node, final PresentationData data) {
    final VirtualFile file = node.getVirtualFile();
    final Project project = node.getProject();

    // Color file status
    if (file != null) {
      if (MTConfig.getInstance().isStyledDirectories()) {
        // Color file status
        applyDirectoriesColor(data, file);
      }

      if (MTConfig.getInstance().isUseProjectViewDecorators()) {
        setOpenOrClosedIcon(data, file, project);
      }
    }
  }

  /**
   * Try to mimic the "open or closed"  folder feature
   */
  @SuppressWarnings("MethodWithMultipleLoops")
  private static void setOpenOrClosedIcon(final PresentationData data, final VirtualFile file, final Project project) {
    if (!file.isDirectory()) {
      return;
    }

    final FileEditorManagerEx manager = FileEditorManagerEx.getInstanceEx(project);
    for (final EditorWindow editorWindow : manager.getWindows()) {
      final VirtualFile[] files = editorWindow.getFiles();
      for (final VirtualFile leaf : files) {
        if (leaf.getPath().contains(file.getPath())) {
          setOpenDirectoryIcon(data, file, project);
          colorOpenDirectories(data);
        }
      }
    }
  }

  private static void colorOpenDirectories(final PresentationData data) {
    final String accentColor = MTConfig.getInstance().getAccentColor();
    data.setForcedTextForeground(ColorUtil.fromHex(accentColor));
  }

  @SuppressWarnings("IfStatementWithTooManyBranches")
  private static void setOpenDirectoryIcon(final PresentationData data, final VirtualFile file, final Project project) {
    if (data.getIcon(true) instanceof DirIcon) {
      final Icon openedIcon = ((DirIcon) Objects.requireNonNull(data.getIcon(true))).getOpenedIcon();
      data.setIcon(openedIcon);
    } else if (ProjectRootManager.getInstance(project).getFileIndex().isExcluded(file)) {
      data.setIcon(MTIcons.EXCLUDED);
    } else if (ProjectRootsUtil.isModuleContentRoot(file, project)) {
      data.setIcon(MTIcons.MODULE);
    } else if (ProjectRootsUtil.isInSource(file, project)) {
      data.setIcon(MTIcons.SOURCE);
    } else if (ProjectRootsUtil.isInTestSource(file, project)) {
      data.setIcon(MTIcons.TEST);
    } else if (Objects.equals(data.getIcon(false), PlatformIcons.PACKAGE_ICON)) {
      //      Looks like an open directory anyway
      data.setIcon(PlatformIcons.PACKAGE_ICON);
    } else {
      data.setIcon(getDirectoryIcon());
    }
  }

  private static Icon getDirectoryIcon() {
    if (directory == null) {
      directory = MTIcons.Nodes2.FolderOpen;
    }

    return directory;
  }

  private static void applyDirectoriesColor(final PresentationData data, final VirtualFile file) {
    if (file.isDirectory()) {
      data.setAttributesKey(DIRECTORIES);
    }
  }
}
