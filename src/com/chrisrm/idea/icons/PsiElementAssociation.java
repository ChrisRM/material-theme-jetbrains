package com.chrisrm.idea.icons;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.ElementPresentationUtil;

import javax.swing.*;

public class PsiElementAssociation extends Association {
    private Class<? extends PsiElement> type;

    @Override
    public boolean matches(FileInfo file) {
        final PsiElement psiElement = file.getPsiElement();
        return psiElement != null && (type.isAssignableFrom(psiElement.getClass()));
    }

    public Icon getIconForFile(FileInfo file) {
        final PsiElement psiElement = file.getPsiElement();
        return ElementPresentationUtil.getClassIconOfKind((PsiClass) psiElement, ElementPresentationUtil.getBasicClassKind((PsiClass) psiElement));
    }

    public Class<? extends PsiElement> getType() {
        return type;
    }

    public void setType(Class<? extends PsiElement> psiClass) {
        this.type = psiClass;
    }
}
