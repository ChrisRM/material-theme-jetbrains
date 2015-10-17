package com.chrisrm.idea.icons;

import com.google.common.annotations.VisibleForTesting;
import com.thoughtworks.xstream.XStream;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class Associations implements Serializable {

    public static class AssociationsFactory {
        public static Associations create() {
            final URL associationsXml = AssociationsFactory.class.getResource("/icon_associations.xml");
            final XStream xStream = new XStream();
            xStream.alias("associations", Associations.class);
            xStream.alias("regex", RegexAssociation.class);
            xStream.alias("type", TypeAssociation.class);
            xStream.alias("psi", PsiElementAssociation.class);

            xStream.useAttributeFor(Association.class, "icon");
            xStream.useAttributeFor(Association.class, "name");
            xStream.useAttributeFor(RegexAssociation.class, "pattern");
            xStream.useAttributeFor(TypeAssociation.class, "type");
            xStream.useAttributeFor(PsiElementAssociation.class, "type");

            return (Associations) xStream.fromXML(associationsXml);
        }
    }

    private List<Association> associations;

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
        return result;
    }

}