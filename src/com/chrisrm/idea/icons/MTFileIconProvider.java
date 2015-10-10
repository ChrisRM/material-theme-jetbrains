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
            return getIconForAssociation(associations.findAssociationForFile(convertToFileInfo(vFile)));
        }

        return null;
    }

    private FileInfo convertToFileInfo(VirtualFile vFile) {
        return new VirtualFileInfo(vFile);
    }

    private Icon getIconForAssociation(Association association) {
        final boolean isInputInvalid = association == null || association.getIcon() == null;
        return isInputInvalid ? null : loadIcon(association);
    }

    private Icon loadIcon(Association association) {
        Icon icon = null;
        try {
            icon = IconLoader.getIcon(association.getIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return icon;
    }
}
