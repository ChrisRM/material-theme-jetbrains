package com.chrisrm.idea.icons;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;

public class VirtualFileInfo implements FileInfo {
    private VirtualFile vFile;
    private PsiElement psiElement;

    public VirtualFileInfo(PsiElement psiElement, VirtualFile vFile) {
        this.psiElement = psiElement;
        this.vFile = vFile;
    }

    @Override
    public String getName() {
        return vFile.getName();
    }

    @Override
    public String getFileType() {
        return String.valueOf(vFile.getFileType().getName());
    }

    @Override
    public PsiElement getPsiElement() {
        return psiElement;
    }
}