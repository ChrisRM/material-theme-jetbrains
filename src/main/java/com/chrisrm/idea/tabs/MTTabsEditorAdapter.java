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

package com.chrisrm.idea.tabs;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorTabbedContainer;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.fileEditor.impl.EditorWithProviderComposite;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.FileColorManager;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public final class MTTabsEditorAdapter implements FileEditorManagerListener {

  @Override
  public void selectionChanged(@NotNull final FileEditorManagerEvent event) {
    final Project project = event.getManager().getProject();
    final FileEditorManagerEx manager = FileEditorManagerEx.getInstanceEx(project);
    final FileColorManager fileColorManager = FileColorManager.getInstance(project);
    final FileStatusManager fileStatusManager = FileStatusManager.getInstance(project);

    final VirtualFile oldFile = event.getOldFile();
    final VirtualFile newFile = event.getNewFile();

    for (final EditorWindow editorWindow : manager.getWindows()) {
      if (oldFile != null) {
        processOldTab(fileStatusManager, fileColorManager, oldFile, editorWindow);
      }

      if (newFile != null) {
        processActiveTab(fileStatusManager, newFile, editorWindow);
      }
    }
  }

  private void processActiveTab(@NotNull final FileStatusManager fileStatusManager,
                                @NotNull final VirtualFile file,
                                @NotNull final EditorWindow editorWindow) {
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();
    final MTConfig mtConfig = MTConfig.getInstance();

    final Color backgroundColor = mtTheme.getBackgroundColor();
    FileStatus status = fileStatusManager.getStatus(file);

    // Set tab color
    setTabColor(backgroundColor, file, editorWindow, status);

    // bold tabs
    if (mtConfig.getIsBoldTabs()) {
      setBoldTabs(file, editorWindow);
    }
  }

  private void setBoldTabs(@NotNull final VirtualFile file,
                           @NotNull final EditorWindow editorWindow) {
    final EditorWithProviderComposite fileComposite = editorWindow.findFileComposite(file);
    final boolean isBoldTabs = MTConfig.getInstance().getIsBoldTabs();

    // Find the tab of the selected file
    final int editorIndex = getEditorIndex(editorWindow, fileComposite);
    if (editorIndex >= 0) {
      final EditorTabbedContainer tabbedPane = editorWindow.getTabbedPane();

      if (tabbedPane != null) {
        try {
          tabbedPane.getTabs()
              .getTabAt(editorIndex)
              .setDefaultStyle(isBoldTabs ? SimpleTextAttributes.STYLE_BOLD : SimpleTextAttributes.STYLE_PLAIN);
        }
        catch (IndexOutOfBoundsException ignored) {
        }
      }
    }
  }

  private void processOldTab(@NotNull final FileStatusManager fileStatusManager,
                             @NotNull final FileColorManager fileColorManager,
                             @NotNull final VirtualFile file,
                             @NotNull final EditorWindow editorWindow) {

    FileStatus status = fileStatusManager.getStatus(file);
    setTabColor(fileColorManager.getFileColor(file), file, editorWindow, status);
  }

  private void setTabColor(final Color fileColor,
                           @NotNull final VirtualFile file,
                           @NotNull final EditorWindow editorWindow,
                           FileStatus status) {
    final EditorWithProviderComposite fileComposite = editorWindow.findFileComposite(file);

    // Find the tab of the selected file
    final int editorIndex = getEditorIndex(editorWindow, fileComposite);

    if (editorIndex >= 0) {
      final EditorTabbedContainer tabbedPane = editorWindow.getTabbedPane();

      if (tabbedPane != null) {
        tabbedPane.getTabs()
            .getPresentation()
            .setActiveTabFillIn(fileColor);

        //        try {
        //          if (statusColor != null) {
        //            tabbedPane.getTabs()
        //                .getTabAt(editorIndex)
        //                .setDefaultForeground(statusColor);
        //          }
        //        }
        //        catch (IndexOutOfBoundsException ignored) {
        //
        //        }
      }
    }
  }

  private int getEditorIndex(@NotNull final EditorWindow editorWindow, final EditorWithProviderComposite fileComposite) {
    int index = 0;
    for (final EditorWithProviderComposite editorWithProviderComposite : editorWindow.getEditors()) {
      if (editorWithProviderComposite.equals(fileComposite)) {
        break;
      }
      index++;
    }

    return index;
  }
}
