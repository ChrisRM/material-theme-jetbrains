package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaRootPaneUI;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;

import javax.swing.*;

/**
 * Created by chris on 26/03/16.
 *
 * @project Material Theme UI
 */
public class MTRootPaneUI extends DarculaRootPaneUI {

    @Override
    public void installBorder(JRootPane root) {
        int style = root.getWindowDecorationStyle();

        if (style == JRootPane.NONE) {
            LookAndFeel.uninstallBorder(root);
        }
        else {
            root.setBorder(JBUI.Borders.customLine(JBColor.WHITE, 1, 1, 1, 1));
            //LookAndFeel.installBorder(root, "RootPane.border");
        }
    }
}
