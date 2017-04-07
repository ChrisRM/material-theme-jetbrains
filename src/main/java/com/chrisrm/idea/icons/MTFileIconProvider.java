package com.chrisrm.idea.icons;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilCore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MTFileIconProvider extends IconProvider {

    private final Associations associations = Associations.AssociationsFactory.create();

    @Nullable
    @Override
    public Icon getIcon(@NotNull PsiElement psiElement, int i) {
        // Only replace icons on elements representing a file
        // Prevents file icons from being assigned to classes, methods, fields, etc.
        if (psiElement instanceof PsiFile) {
            VirtualFile virtualFile = PsiUtilCore.getVirtualFile(psiElement);
            if (virtualFile != null) {
                if (virtualFile.isDirectory()) {
                    return getDirectoryIcon();
                }
                FileInfo file = new VirtualFileInfo(psiElement, virtualFile);
                return getIconForAssociation(file, associations.findAssociationForFile(file));
            }
        } else if (psiElement instanceof PsiDirectory) {
            return getDirectoryIcon();
        }

        return null;
    }

    private Icon getDirectoryIcon() {
        return IconLoader.getIcon("/icons/nodes/folder.png");
    }

    private Icon getIconForAssociation(FileInfo file, Association association) {
        final boolean isInputInvalid = association == null || association.getIcon() == null;
        return isInputInvalid ? null : loadIcon(file, association);
    }

    private Icon loadIcon(FileInfo file, Association association) {
        Icon icon = null;
        try {
            if (association instanceof PsiElementAssociation) {
                icon = ((PsiElementAssociation) association).getIconForFile(file);
            } else {
                icon = IconLoader.getIcon(association.getIcon());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return icon;
    }
}