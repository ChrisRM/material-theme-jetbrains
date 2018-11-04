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

package com.chrisrm.idea.icons.associations;

import com.chrisrm.idea.icons.FileInfo;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.StaticPatcher;
import com.google.common.collect.ImmutableSet;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Represents a list of association parsed from the XML
 */
@SuppressWarnings("unused")
public final class Associations implements Serializable {

  @NonNls
  private static final Set<String> IMAGE_TYPES = ImmutableSet.of("Images", "SVG");
  /**
   * Parsed associations
   */
  private List<? extends Association> associations;

  public List<Association> getAssociations() {
    return Collections.unmodifiableList(associations);
  }

  @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
  public void setAssociations(final List<? extends Association> associations) {
    this.associations = associations;
  }

  /**
   * Find the Association for the given FileInfo
   *
   * @param file file
   */
  @Nullable
  public Association findAssociationForFile(final FileInfo file) {
    final Association result = associations.stream()
        .filter((Predicate<Association>) association -> association.matches(file))
        .findAny()
        .orElse(null);

    if (result != null && IMAGE_TYPES.contains(result.getName())) {
      try {
        // If those plugins are installed, let them handle the icon
        final IdeaPluginDescriptor plugin = PluginManager.getPlugin(PluginId.getId(MaterialThemeBundle.message("plugins.iconViewer")));
        final IdeaPluginDescriptor plugin2 = PluginManager.getPlugin(
            PluginId.getId(MaterialThemeBundle.message("plugins.imageIconViewer")));

        if (plugin != null || plugin2 != null) {
          return null;
        }
      } catch (final RuntimeException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  @NonNls
  public enum AssociationsFactory {
    ;

    /**
     * Parse icon_associations.xml to build the list of Associations
     */
    @SuppressWarnings("CastToConcreteClass")
    public static Associations create(final String associationsFile) {
      final URL associationsXml = AssociationsFactory.class.getResource(associationsFile);
      @NonNls final XStream xStream = new XStream(new DomDriver());
      xStream.alias("associations", Associations.class);
      xStream.alias("regex", RegexAssociation.class);
      xStream.alias("type", TypeAssociation.class);

      final String psiClass = "com.intellij.psi.PsiClass";
      if (StaticPatcher.isClass(psiClass)) {
        xStream.alias("psi", PsiElementAssociation.class);
      } else {
        xStream.alias("psi", TypeAssociation.class);
      }

      xStream.useAttributeFor(Association.class, "icon");
      xStream.useAttributeFor(Association.class, "name");
      xStream.useAttributeFor(RegexAssociation.class, "pattern");
      xStream.useAttributeFor(TypeAssociation.class, "type");

      if (StaticPatcher.isClass(psiClass)) {
        xStream.useAttributeFor(PsiElementAssociation.class, "type");
      }

      try {
        return (Associations) xStream.fromXML(associationsXml);
      } catch (final RuntimeException e) {
        return new Associations();
      }
    }
  }
}
