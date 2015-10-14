package com.chrisrm.idea.icons;

import com.intellij.psi.PsiElement;

public interface FileInfo {

    String getName();

    String getFileType();

    PsiElement getPsiElement();
}
