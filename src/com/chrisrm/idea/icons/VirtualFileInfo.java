package com.chrisrm.idea.icons;

import com.intellij.openapi.vfs.VirtualFile;

import java.util.Objects;

public class VirtualFileInfo implements FileInfo {
    private VirtualFile vFile;

    public VirtualFileInfo(VirtualFile vFile) {
        this.vFile = vFile;
    }

    @Override
    public String getName() {
        return vFile.getName();
    }

    @Override
    public String getFileType() {
        return Objects.toString(vFile.getFileType().getName());
    }
}
