package com.chrisrm.idea.tree;

import com.chrisrm.idea.MTConfig;
import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.ProjectRootsUtil;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.ColoredTreeCellRenderer;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eliorb on 09/04/2017.
 */
public class MTProjectViewNodeDecorator implements ProjectViewNodeDecorator {

  private final Map<FileStatus, Color> fileStatusColorMap;

  public MTProjectViewNodeDecorator() {
    fileStatusColorMap = new HashMap<>(18);
    // TODO move into a properties file ?
    fileStatusColorMap.put(FileStatus.NOT_CHANGED_IMMEDIATE, ColorUtil.fromHex("#80CBC4"));
    fileStatusColorMap.put(FileStatus.NOT_CHANGED_RECURSIVE, ColorUtil.fromHex("#80CBC4"));
    fileStatusColorMap.put(FileStatus.DELETED, ColorUtil.fromHex("#F77669"));
    fileStatusColorMap.put(FileStatus.MODIFIED, ColorUtil.fromHex("#80CBC4"));
    fileStatusColorMap.put(FileStatus.ADDED, ColorUtil.fromHex("#C3E887"));
    fileStatusColorMap.put(FileStatus.MERGE, ColorUtil.fromHex("#C792EA"));
    fileStatusColorMap.put(FileStatus.UNKNOWN, ColorUtil.fromHex("#F77669"));
    fileStatusColorMap.put(FileStatus.IGNORED, ColorUtil.fromHex("#515D5D"));
    fileStatusColorMap.put(FileStatus.HIJACKED, ColorUtil.fromHex("#FFCB6B"));
    fileStatusColorMap.put(FileStatus.MERGED_WITH_CONFLICTS, ColorUtil.fromHex("#BC3F3C"));
    fileStatusColorMap.put(FileStatus.MERGED_WITH_BOTH_CONFLICTS, ColorUtil.fromHex("#BC3F3C"));
    fileStatusColorMap.put(FileStatus.MERGED_WITH_PROPERTY_CONFLICTS, ColorUtil.fromHex("#BC3F3C"));
    fileStatusColorMap.put(FileStatus.DELETED_FROM_FS, ColorUtil.fromHex("#626669"));
    fileStatusColorMap.put(FileStatus.SWITCHED, ColorUtil.fromHex("#F77669"));
    fileStatusColorMap.put(FileStatus.OBSOLETE, ColorUtil.fromHex("#FFCB6B"));
    fileStatusColorMap.put(FileStatus.SUPPRESSED, ColorUtil.fromHex("#3C3F41"));
  }

  @Override
  public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {

  }

  @Override
  public void decorate(ProjectViewNode node, PresentationData data) {
    if (!MTConfig.getInstance().isUseProjectViewDecorators()) {
      return;
    }

    VirtualFile file = node.getVirtualFile();
    if (file == null) {
      return;
    }
    Project project = node.getProject();

    // Color file status
    colorFileStatus(data, file, project);

    // Fix open/closed icons (TODO USE SETTING FOR THIS)
    setOpenOrClosedIcon(data, file, project);
  }

  /**
   * Try to mimic the "open or closed"  folder feature
   * TODO: listen to tab select changes
   */
  private void setOpenOrClosedIcon(PresentationData data, VirtualFile file, Project project) {
    if (!file.isDirectory()) {
      return;
    }

    final FileEditorManagerEx manager = FileEditorManagerEx.getInstanceEx(project);
    for (EditorWindow editorWindow : manager.getWindows()) {
      VirtualFile[] files = editorWindow.getFiles();
      for (VirtualFile leaf : files) {
        if (leaf.getPath().contains(file.getPath())) {
          setDirectoryIcon(data, file, project);
        }
      }
    }
  }

  private void setDirectoryIcon(PresentationData data, VirtualFile file, Project project) {
    if (ProjectRootManager.getInstance(project).getFileIndex().isExcluded(file)) {
      data.setIcon(IconLoader.findIcon("/icons/modules/ExcludedTreeOpen.png"));
    }
    else if (ProjectRootsUtil.isModuleContentRoot(file, project)) {
      data.setIcon(IconLoader.findIcon("/icons/nodes/ModuleOpen.png"));
    }
    else if (ProjectRootsUtil.isInSource(file, project)) {
      data.setIcon(IconLoader.findIcon("/icons/modules/sourceRootOpen.png"));
    }
    else if (ProjectRootsUtil.isInTestSource(file, project)) {
      data.setIcon(IconLoader.findIcon("/icons/modules/testRootOpen.png"));
    }
    else {
      data.setIcon(AllIcons.Nodes.TreeOpen);
    }
  }

  private void colorFileStatus(PresentationData data, VirtualFile file, Project project) {
    FileStatus status = FileStatusManager.getInstance(project).getStatus(file);
    Color colorFromStatus = getColorFromStatus(status);
    if (colorFromStatus != null) {
      data.setForcedTextForeground(colorFromStatus);
    }
  }

  private Color getColorFromStatus(FileStatus status) {
    return fileStatusColorMap.get(status);
  }
}
