package com.chrisrm.idea;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorTabbedContainer;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.fileEditor.impl.EditorWithProviderComposite;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.FileColorManager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Created by helio on 25/03/2017.
 */
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
      setUnfocusedTabWithColorManagerDefaultColor(fileColorManager, oldFile, editorWindow);
      setFocusedTabHighlightColor(config, newFile, editorWindow);
    }
  }

  private void setFocusedTabHighlightColor(@NotNull MTConfig config, VirtualFile file, EditorWindow editorWindow) {
    final Color highlightColor = config.getHighlightColor();
    if (file != null) {
      setTabColor(highlightColor, file, editorWindow);
    }
  }

  private void setUnfocusedTabWithColorManagerDefaultColor(@NotNull FileColorManager fileColorManager,
                                                           VirtualFile file,
                                                           EditorWindow editorWindow) {
    if (file != null) {
      // Keep file colors
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
                  .getTabAt(editorIndex)
                  .setTabColor(fileColor);
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
