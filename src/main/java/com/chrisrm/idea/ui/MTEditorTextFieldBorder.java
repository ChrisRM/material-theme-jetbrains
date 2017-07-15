/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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
package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaEditorTextFieldBorder;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class MTEditorTextFieldBorder extends DarculaEditorTextFieldBorder implements Border {
    public static boolean isComboBoxEditor(Component c) {
        return UIUtil.getParentOfType(JComboBox.class, c) != null;
    }

    public static boolean isCellEditor(Component c) {
        return UIUtil.getParentOfType(JTable.class, c) != null;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (isComboBoxEditor(c) /*|| isCellEditor(c)*/) {
            g.setColor(c.getBackground());
            g.fillRect(x, y, width, height);
            return;
        }
        final EditorTextField textField = UIUtil.getParentOfType(EditorTextField.class, c);
        if (textField == null) {
            return;
        }

        final Rectangle r = new Rectangle(x + 1, y + 1, width - 2, height - 2);

        if (c.isOpaque()) {
            g.setColor(UIUtil.getPanelBackground());
            g.fillRect(x, y, width, height);
        }

        g.setColor(c.getBackground());
        g.fillRect(r.x, r.y, r.width, r.height);

        if (!textField.isEnabled()) {
            ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        if (textField.isEnabled() && textField.isVisible() && textField.getFocusTarget().hasFocus()) {
            DarculaUIUtil.paintFocusRing(g, new Rectangle(r.x + 1, r.y + 1, r.width - 2, r.height - 2));
        } else {
            g.setColor(new JBColor(Gray._150, Gray._100));
            g.drawRect(r.x, r.y, r.width, r.height);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        if (isComboBoxEditor(c) /*|| isCellEditor(c)*/) {
            return new InsetsUIResource(2, 3, 2, 3);
        }
        return new InsetsUIResource(4, 7, 4, 7);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
