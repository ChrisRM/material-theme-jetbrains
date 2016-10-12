package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaInternalBorder;
import com.intellij.ui.JBColor;

import javax.swing.plaf.InsetsUIResource;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class MTInternalBorder extends DarculaInternalBorder {

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        g.setColor(JBColor.RED);
        g.drawRect(x, y, w - 1, h - 1);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new InsetsUIResource(1, 1, 1, 1);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
