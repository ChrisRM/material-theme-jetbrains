/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.chrisrm.idea.icons;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.icons.tinted.TintedIconsService;
import com.intellij.icons.AllIcons;
import com.intellij.ide.IconProvider;
import com.intellij.ide.projectView.impl.ProjectRootsUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.roots.ui.configuration.SourceRootPresentation;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.jrt.JrtFileSystem;
import com.intellij.openapi.vfs.newvfs.ArchiveFileSystem;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.ElementBase;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.ui.ColorUtil;
import com.intellij.util.IconUtil;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Provider for file icons
 */
public final class MTFileIconProvider extends IconProvider {
  private final Associations associations = Associations.AssociationsFactory.create();
  private boolean hasJFS;
  private boolean hasJDS;

  {
    try {
      Class.forName("com.intellij.openapi.vfs.jrt.JrtFileSystem");
      hasJFS = true;
    } catch (final ClassNotFoundException e) {
      hasJFS = false;
    }
    try {
      Class.forName("com.intellij.psi.JavaDirectoryService");
      hasJDS = true;
    } catch (final ClassNotFoundException e) {
      hasJDS = false;
    }
  }

  @Nullable
  @Override
  public Icon getIcon(@NotNull final PsiElement psiElement, final int i) {
    Icon icon = null;

    if (!MTConfig.getInstance().isUseMaterialIcons()) {
      return null;
    }

    // Only replace icons on elements representing a file
    // Prevents file icons from being assigned to classes, methods, fields, etc.
    if (psiElement instanceof PsiFile) {
      final VirtualFile virtualFile = PsiUtilCore.getVirtualFile(psiElement);
      if (virtualFile != null) {
        final FileInfo file = new VirtualFileInfo(psiElement, virtualFile);
        icon = getIconForAssociation(file, associations.findAssociationForFile(file));
      }
    } else if (psiElement instanceof PsiDirectory) {
      icon = getDirectoryIcon((PsiDirectory) psiElement);
    }

    if (MTConfig.getInstance().isMonochromeIcons() && icon != null) {
      final Color primaryColor = MTConfig.getInstance().getSelectedTheme().getTheme().getPrimaryColor();
      return IconUtil.colorize(icon, ColorUtil.brighter(primaryColor, 4));
    }

    return icon;
  }

  /**
   * Return correct instance of directory icon (taken straight from the source code)
   *
   * @param element
   */
  private Icon getDirectoryIcon(final PsiDirectory element) {
    final VirtualFile vFile = element.getVirtualFile();
    final Project project = element.getProject();

    final SourceFolder sourceFolder;
    Icon symbolIcon = null;

    if (vFile.getParent() == null && vFile.getFileSystem() instanceof ArchiveFileSystem) {
      symbolIcon = PlatformIcons.JAR_ICON;
    } else if (ProjectRootsUtil.isModuleContentRoot(vFile, project)) {
      final Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(vFile);
      symbolIcon = module != null ? ModuleType.get(module).getIcon() : PlatformIcons.CONTENT_ROOT_ICON_CLOSED;
    } else if ((sourceFolder = ProjectRootsUtil.getModuleSourceRoot(vFile, project)) != null) {
      symbolIcon = SourceRootPresentation.getSourceRootIcon(sourceFolder);
    } else if (hasJFS && JrtFileSystem.isModuleRoot(vFile)) {
      symbolIcon = AllIcons.Nodes.JavaModuleRoot;
    } else if (hasJDS && JavaDirectoryService.getInstance().getPackage(element) != null) {
      symbolIcon = PlatformIcons.PACKAGE_ICON;
    } else if (!Registry.is("ide.hide.excluded.files") && ProjectRootManager.getInstance(project).getFileIndex().isExcluded(vFile)) {
      symbolIcon = AllIcons.Modules.ExcludeRoot;
    }

    try {
      if (ProjectRootsUtil.findUnloadedModuleByContentRoot(vFile, project) != null) {
        symbolIcon = AllIcons.Modules.UnloadedModule;
      }
    } catch (final NoSuchMethodError e) {
      // till android studio implements this shit;
    }

    if (symbolIcon != null) {
      return ElementBase.createLayeredIcon(element, symbolIcon, 0);
    } else {
      return TintedIconsService.getIcon("/icons/nodes/folderClosed.png", "ff00cc");
    }
  }

  /**
   * Get the relevant icon for association
   *
   * @param file
   * @param association
   */
  private Icon getIconForAssociation(final FileInfo file, final Association association) {
    final boolean isInputInvalid = association == null || association.getIcon() == null;
    return isInputInvalid ? null : loadIcon(file, association);
  }

  /**
   * Load the association's icon
   *
   * @param file
   * @param association
   */
  private Icon loadIcon(final FileInfo file, final Association association) {
    Icon icon = null;

    try {
      if (association instanceof PsiElementAssociation) {
        icon = ((PsiElementAssociation) association).getIconForFile(file);
      } else {
        icon = IconLoader.getIcon(association.getIcon());
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }
    return icon;
  }
}
