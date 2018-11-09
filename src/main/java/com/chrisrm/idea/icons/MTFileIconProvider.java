/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea.icons;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.icons.associations.Association;
import com.chrisrm.idea.icons.associations.Associations;
import com.chrisrm.idea.icons.associations.AssociationsFactory;
import com.intellij.ide.IconProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilCore;
import icons.MTIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provider for file icons
 */
@SuppressWarnings("LocalVariableOfConcreteClass")
public final class MTFileIconProvider extends IconProvider implements DumbAware {
  private final Associations associations = AssociationsFactory.create("/icon_associations.xml");
  private final Associations dirAssociations = AssociationsFactory.create("/folder_associations.xml");

  @Nullable
  @Override
  public Icon getIcon(@NotNull final PsiElement element, final int flags) {
    Icon icon = null;

    if (element instanceof PsiDirectory) {
      icon = getDirectoryIcon(element);
    } else if (element instanceof PsiFile) {
      icon = getFileIcon(element);
    }

    return icon;
  }

  private Icon getFileIcon(final PsiElement psiElement) {
    Icon icon = null;
    if (!MTConfig.getInstance().isFileIcons()) {
      return null;
    }

    final VirtualFile virtualFile = PsiUtilCore.getVirtualFile(psiElement);
    if (virtualFile != null) {
      final FileInfo file = new VirtualFileInfo(psiElement, virtualFile);
      icon = getIconForAssociation(file, associations.findAssociationForFile(file));
    }
    return icon;
  }

  private DirIcon getDirectoryIcon(final PsiElement psiElement) {
    DirIcon icon = null;
    if (!MTConfig.getInstance().isDecoratedFolders()) {
      return null;
    }

    final VirtualFile virtualFile = PsiUtilCore.getVirtualFile(psiElement);
    if (virtualFile != null) {
      final FileInfo file = new VirtualFileInfo(psiElement, virtualFile);
      icon = getDirectoryIconForAssociation(dirAssociations.findAssociationForFile(file));
    }
    return icon;
  }

  /**
   * Get the relevant icon for association
   */
  private static Icon getIconForAssociation(final FileInfo file, final Association association) {
    final boolean isInputInvalid = association == null || association.getIcon() == null;
    return isInputInvalid ? null : loadIcon(file, association);
  }

  private static DirIcon getDirectoryIconForAssociation(final Association association) {
    final boolean isInputInvalid = association == null || association.getIcon() == null;
    return isInputInvalid ? null : loadDirIcon(association);
  }

  /**
   * Load the association's icon
   */
  private static Icon loadIcon(final FileInfo file, final Association association) {
    Icon icon = null;

    try {
      icon = association.getIconForFile(file);
    } catch (final RuntimeException e) {
      e.printStackTrace();
    }
    return icon;
  }

  /**
   * Load the association's icon
   */
  private static DirIcon loadDirIcon(final Association association) {
    DirIcon icon = null;

    try {
      final String iconPath = association.getIcon();
      icon = MTIcons.getFolderIcon(iconPath);
    } catch (final RuntimeException e) {
      e.printStackTrace();
    }
    return icon;
  }
}
