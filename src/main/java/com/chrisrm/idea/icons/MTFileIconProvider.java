package com.chrisrm.idea.icons;

import com.chrisrm.idea.MTConfig;
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
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MTFileIconProvider extends IconProvider {

  private final Associations associations = Associations.AssociationsFactory.create();

  @Nullable
  @Override
  public Icon getIcon(@NotNull PsiElement psiElement, int i) {
    Icon icon = null;

    if (!MTConfig.getInstance().isUseMaterialIcons()) {
      return null;
    }

    // Only replace icons on elements representing a file
    // Prevents file icons from being assigned to classes, methods, fields, etc.
    if (psiElement instanceof PsiFile) {
      VirtualFile virtualFile = PsiUtilCore.getVirtualFile(psiElement);
      if (virtualFile != null) {
        FileInfo file = new VirtualFileInfo(psiElement, virtualFile);
        icon = getIconForAssociation(file, associations.findAssociationForFile(file));
      }
    } else if (psiElement instanceof PsiDirectory) {
      icon = getDirectoryIcon((PsiDirectory) psiElement);
    }

    return icon;
  }

  private Icon getTransparentIcon(Icon icon) {
    boolean noIcon = MTConfig.getInstance().getHideFileIcons();
    return noIcon && icon != null ? IconLoader.getTransparentIcon(icon, 0) : icon;
  }

  /**
   * Return correct instance of directory icon (taken straight from the source code)
   *
   * @param element
   * @return
   */
  private Icon getDirectoryIcon(PsiDirectory element) {
    final VirtualFile vFile = element.getVirtualFile();
    final Project project = element.getProject();

    SourceFolder sourceFolder;
    Icon symbolIcon;

    boolean hasJFS;
    try {
      Class.forName("com.intellij.openapi.vfs.jrt.JrtFileSystem");
      hasJFS = true;
    }
    catch (final ClassNotFoundException e) {
      hasJFS = false;
    }

    boolean hasJDS;
    try {
      Class.forName("com.intellij.psi.JavaDirectoryService");
      hasJDS = true;
    }
    catch (final ClassNotFoundException e) {
      hasJDS = false;
    }

    if (vFile.getParent() == null && vFile.getFileSystem() instanceof ArchiveFileSystem) {
      symbolIcon = PlatformIcons.JAR_ICON;
    } else if (ProjectRootsUtil.isModuleContentRoot(vFile, project)) {
      Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(vFile);
      symbolIcon = module != null ? ModuleType.get(module).getIcon() : PlatformIcons.CONTENT_ROOT_ICON_CLOSED;
    } else if ((sourceFolder = ProjectRootsUtil.getModuleSourceRoot(vFile, project)) != null) {
      symbolIcon = SourceRootPresentation.getSourceRootIcon(sourceFolder);
    } else if (hasJFS && JrtFileSystem.isModuleRoot(vFile)) {
      symbolIcon = AllIcons.Nodes.JavaModuleRoot;
    } else if (hasJDS && JavaDirectoryService.getInstance().getPackage(element) != null) {
      symbolIcon = PlatformIcons.PACKAGE_ICON;
    } else if (!Registry.is("ide.hide.excluded.files") && ProjectRootManager.getInstance(project).getFileIndex().isExcluded(vFile)) {
      symbolIcon = AllIcons.Modules.ExcludeRoot;
    } else {
      symbolIcon = AllIcons.Nodes.TreeClosed;
    }

    return ElementBase.createLayeredIcon(element, symbolIcon, 0);
  }

  private Icon getIconForAssociation(FileInfo file, Association association) {
    final boolean isInputInvalid = association == null || association.getIcon() == null;
    return isInputInvalid ? null : getTransparentIcon(loadIcon(file, association));
  }

  private Icon loadIcon(FileInfo file, Association association) {
    Icon icon = null;

    try {
      if (association instanceof PsiElementAssociation) {
        icon = ((PsiElementAssociation) association).getIconForFile(file);
      } else {
        icon = IconLoader.getIcon(association.getIcon());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return icon;
  }
}
