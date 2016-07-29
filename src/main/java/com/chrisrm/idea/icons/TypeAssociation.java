package com.chrisrm.idea.icons;

public class TypeAssociation extends Association {

    private String type;

    @Override
    public boolean matches(FileInfo file) {
        return file.getFileType().equals(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
