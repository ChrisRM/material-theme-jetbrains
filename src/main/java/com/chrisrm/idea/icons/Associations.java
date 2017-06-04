package com.chrisrm.idea.icons;

import com.google.common.annotations.VisibleForTesting;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;
import com.thoughtworks.xstream.XStream;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class Associations implements Serializable {

  private List<Association> associations;

  private static boolean isClass(String className) {
    try {
      Class.forName(className);
      return true;
    }
    catch (final ClassNotFoundException e) {
      return false;
    }
  }

  public List<Association> getAssociations() {
    return associations;
  }

  public void setAssociations(List<Association> associations) {
    this.associations = associations;
  }

  @VisibleForTesting
  @Nullable
  protected Association findAssociationForFile(FileInfo file) {
    Association result = null;
    for (Association association : associations) {
      if (association.matches(file)) {
        result = association;
        break;
      }
    }

    if (result.getName().equals("Images")) {
      try {
        // Icon viewer plugin
        IdeaPluginDescriptor plugin = PluginManager.getPlugin(PluginId.getId("ch.dasoft.iconviewer"));
        if (plugin != null) {
          return null;
        }
      }
      catch (Exception e) {
      }
    }

    return result;
  }

  public static class AssociationsFactory {
    public static Associations create() {
      final URL associationsXml = AssociationsFactory.class.getResource("/icon_associations.xml");
      final XStream xStream = new XStream();
      xStream.alias("associations", Associations.class);
      xStream.alias("regex", RegexAssociation.class);
      xStream.alias("type", TypeAssociation.class);

      if (isClass("com.intellij.psi.PsiClass")) {
        xStream.alias("psi", PsiElementAssociation.class);
      }
      else {
        xStream.alias("psi", TypeAssociation.class);
      }

      xStream.useAttributeFor(Association.class, "icon");
      xStream.useAttributeFor(Association.class, "name");
      xStream.useAttributeFor(RegexAssociation.class, "pattern");
      xStream.useAttributeFor(TypeAssociation.class, "type");

      if (isClass("com.intellij.psi.PsiClass")) {
        xStream.useAttributeFor(PsiElementAssociation.class, "type");
      }

      return (Associations) xStream.fromXML(associationsXml);
    }
  }
}
