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
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.FileColorManager;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MTFileEditorListener implements FileEditorManagerListener {

  @Override
  public void selectionChanged(@NotNull FileEditorManagerEvent event) {
    final Project project = event.getManager().getProject();
    final FileEditorManagerEx manager = FileEditorManagerEx.getInstanceEx(project);
    final FileColorManager fileColorManager = FileColorManager.getInstance(project);

    VirtualFile oldFile = event.getOldFile();
    VirtualFile newFile = event.getNewFile();

    final MTConfig config = MTConfig.getInstance();

    for (EditorWindow editorWindow : manager.getWindows()) {
      processOldTab(config, fileColorManager, oldFile, editorWindow);
      processActiveTab(config, fileColorManager, newFile, editorWindow);
    }
  }

  private void processActiveTab(@NotNull MTConfig config,
                                @NotNull FileColorManager fileColorManager,
                                VirtualFile file,
                                EditorWindow editorWindow) {
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();

    final Color highlightColor = mtTheme.getBorderColor();
    final Color backgroundColor = mtTheme.getBackgroundColor();
    if (file != null) {
      //            setTabHighlightColor(highlightColor, file, editorWindow);
      setTabColor(backgroundColor, file, editorWindow);
      if (config.getIsBoldTabs()) {
        setBoldTabs(config, file, editorWindow);
      }
    }
  }

  private void setBoldTabs(@NotNull MTConfig config,
                           VirtualFile file, EditorWindow editorWindow) {
    EditorWithProviderComposite fileComposite = editorWindow.findFileComposite(file);

    // Find the tab of the selected file
    int editorIndex = getEditorIndex(editorWindow, fileComposite);
    if (editorIndex >= 0) {
      EditorTabbedContainer tabbedPane = editorWindow.getTabbedPane();

      if (tabbedPane != null) {
        try {
          tabbedPane.getTabs()
                    .getTabAt(editorIndex)
                    .setDefaultStyle(SimpleTextAttributes.STYLE_BOLD);
        }
        catch (IndexOutOfBoundsException e) {
          ;
        }
      }
    }
  }

  private void processOldTab(@NotNull MTConfig config,
                             @NotNull FileColorManager fileColorManager,
                             VirtualFile file,
                             EditorWindow editorWindow) {

    if (file != null) {
      // Keep file colors
      //            setTabHighlightColor(fileColorManager.getFileColor(file), file, editorWindow);
      setTabColor(fileColorManager.getFileColor(file), file, editorWindow);
    }
  }

  /**
   * Set a tab's color
   *
   * @param fileColor    the color to set
   * @param file         fhe active file
   * @param editorWindow the editor
   */
  private void setTabColor(Color fileColor, VirtualFile file, EditorWindow editorWindow) {
    EditorWithProviderComposite fileComposite = editorWindow.findFileComposite(file);

    // Find the tab of the selected file
    int editorIndex = getEditorIndex(editorWindow, fileComposite);
    if (editorIndex >= 0) {
      EditorTabbedContainer tabbedPane = editorWindow.getTabbedPane();

      if (tabbedPane != null) {
        tabbedPane.getTabs()
                  .getPresentation()
                  .setActiveTabFillIn(fileColor);
      }
    }
  }

  /**
   * Set a tab's color
   *
   * @param fgColor      the color to set
   * @param file         fhe active file
   * @param editorWindow the editor
   */
  private void setTabHighlightColor(Color fgColor, VirtualFile file, EditorWindow editorWindow) {
    EditorWithProviderComposite fileComposite = editorWindow.findFileComposite(file);

    // Find the tab of the selected file
    int editorIndex = getEditorIndex(editorWindow, fileComposite);
    if (editorIndex >= 0) {
      EditorTabbedContainer tabbedPane = editorWindow.getTabbedPane();

      if (tabbedPane != null) {
        tabbedPane.getTabs()
                  .getTabAt(editorIndex)
                  .setDefaultForeground(fgColor);
      }
    }
  }

  /**
   * Get the index of the selected editor
   *
   * @param editorWindow  the editor window
   * @param fileComposite the file
   * @return the editor index
   */
  private int getEditorIndex(@NotNull EditorWindow editorWindow, EditorWithProviderComposite fileComposite) {
    int index = 0;
    for (EditorWithProviderComposite editorWithProviderComposite : editorWindow.getEditors()) {
      if (editorWithProviderComposite.equals(fileComposite)) {
        break;
      }
      index++;
    }

    return index;
  }
}
