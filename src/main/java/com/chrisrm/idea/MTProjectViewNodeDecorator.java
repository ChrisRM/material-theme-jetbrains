package com.chrisrm.idea;

import com.intellij.credentialStore.kdbx.Icon;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusFactory;
import com.intellij.openapi.vcs.FileStatusManager;
import com.intellij.openapi.vcs.VcsBundle;
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
    public void decorate(ProjectViewNode node, PresentationData data) {
        VirtualFile file = node.getVirtualFile();
        if (file == null) {
            return;
        }
        Color highlightColor = MTConfig.getInstance().getHighlightColor();
        FileStatus status = FileStatusManager.getInstance(node.getProject()).getStatus(file);

        Color colorFromStatus = getColorFromStatus(status);
        if (colorFromStatus != null) {
            data.setForcedTextForeground(colorFromStatus);
        }
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {

    }

    private Color getColorFromStatus(FileStatus status) {
        return fileStatusColorMap.get(status);
    }
}
