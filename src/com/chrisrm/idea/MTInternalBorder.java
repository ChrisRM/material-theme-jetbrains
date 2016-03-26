package com.chrisrm.idea;

import com.intellij.ide.ui.laf.darcula.ui.DarculaInternalBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class MTInternalBorder extends DarculaInternalBorder {

    public MTInternalFrameBorder() {
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        g.setColor(UIManager.getColor("InternalFrame.material.borderColor"));
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
