/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea.tree;

import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.config.MTFileColorsPage;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.ColoredTreeCellRenderer;

public final class MTColoredFoldersNodeDecorator implements ProjectViewNodeDecorator {

  public MTColoredFoldersNodeDecorator() {
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

      if (MTConfig.getInstance().isUseColoredDirectories()) {
        setColoredDirsDecorator(data, file, project);
      }
    }
  }

  /**
   * Try to mimic the "open or closed"  folder feature
   */
  @SuppressWarnings("MethodWithMultipleLoops")
  private static void setColoredDirsDecorator(final PresentationData data, final VirtualFile file, final Project project) {
    //    if (!file.isDirectory()) {
    //      return;
    //    }

    //    final FileEditorManagerEx manager = FileEditorManagerEx.getInstanceEx(project);
    //    for (final EditorWindow editorWindow : manager.getWindows()) {
    //      final VirtualFile[] files = editorWindow.getFiles();
    //      for (final VirtualFile leaf : files) {
    //        if (leaf.getPath().contains(file.getPath())) {
    //          colorOpenDirectories(data);
    //        }
    //      }
    //    }
  }

  private static void colorOpenDirectories(final PresentationData data) {
    final String accentColor = MTConfig.getInstance().getAccentColor();
    data.setForcedTextForeground(ColorUtil.fromHex(accentColor));
  }

  private static void applyDirectoriesColor(final PresentationData data, final VirtualFile file) {
    if (file.isDirectory()) {
      data.setAttributesKey(MTFileColorsPage.DIRECTORIES);
    }
  }
}
