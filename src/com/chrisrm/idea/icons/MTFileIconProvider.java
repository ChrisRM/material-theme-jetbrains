package com.chrisrm.idea.icons;

import com.bulenkov.iconloader.IconLoader;
import com.intellij.ide.IconProvider;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MTFileIconProvider extends IconProvider {

    private final Associations associations = Associations.AssociationsFactory.create();

    @Nullable
    @Override
    public Icon getIcon(@NotNull PsiElement psiElement, int i) {
        PsiFile containingFile = psiElement.getContainingFile();

        if (containingFile != null) {
            VirtualFile vFile = containingFile.getVirtualFile();
            final FileInfo file = convertToFileInfo(vFile, psiElement);
            return getIconForAssociation(file, associations.findAssociationForFile(file));
        }

        return null;
    }

    private FileInfo convertToFileInfo(VirtualFile vFile, PsiElement psiElement) {
        return new VirtualFileInfo(psiElement, vFile);
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
