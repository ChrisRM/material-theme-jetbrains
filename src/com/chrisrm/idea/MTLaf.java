package com.chrisrm.idea;

import com.intellij.ide.ui.laf.darcula.DarculaLaf;

public class MTLaf extends DarculaLaf {

    private String theme;

    public MTLaf(String theme) {
        super();
        this.theme = theme;
    }

    @Override
    protected String getPrefix() {
        return "mt." + theme;
    }
}
