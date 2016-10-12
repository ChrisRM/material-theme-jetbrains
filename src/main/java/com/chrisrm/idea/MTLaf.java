package com.chrisrm.idea;

import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import org.jetbrains.annotations.NotNull;

public class MTLaf extends DarculaLaf {

    private MTTheme theme;

    public MTLaf(@NotNull MTTheme theme) {
        super();
        this.theme = theme;
    }

    @Override
    protected String getPrefix() {
        return theme.getId();
    }
}
