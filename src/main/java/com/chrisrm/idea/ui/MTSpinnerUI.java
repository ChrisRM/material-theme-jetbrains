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

import com.intellij.ide.ui.laf.darcula.ui.DarculaSpinnerUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.UIUtil;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Konstantin Bulenkov
 */
public class MTSpinnerUI extends DarculaSpinnerUI {
    protected JButton prevButton;
    protected JButton nextButton;
    private FocusAdapter myFocusListener = new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            spinner.repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            spinner.repaint();
        }
    };

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        return new MTSpinnerUI();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        final Border border = spinner.getBorder();
        if (border != null) {
            border.paintBorder(c, g, 0, 0, spinner.getWidth(), spinner.getHeight());
        }
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        removeEditorFocusListener(spinner.getEditor());
    }

    @Override
    protected void replaceEditor(JComponent oldEditor, JComponent newEditor) {
        super.replaceEditor(oldEditor, newEditor);
        removeEditorFocusListener(oldEditor);
        addEditorFocusListener(newEditor);
    }

    @Override
    protected JComponent createEditor() {
        final JComponent editor = super.createEditor();
        addEditorFocusListener(editor);
        return editor;
    }

    protected JButton createButton(@MagicConstant(intValues = {SwingConstants.NORTH, SwingConstants.SOUTH}) int direction, String name) {
        JButton button = createArrow(direction);
        button.setName(name);
        button.setBorder(new EmptyBorder(1, 1, 1, 1));
        if (direction == SwingConstants.NORTH) {
            installNextButtonListeners(button);
        } else {
            installPreviousButtonListeners(button);
        }
        return button;
    }

    @Override
    protected Component createPreviousButton() {
        return prevButton = createButton(SwingConstants.SOUTH, "Spinner.previousButton");
    }

    @Override
    protected Component createNextButton() {
        return nextButton = createButton(SwingConstants.NORTH, "Spinner.nextButton");
    }

    @Override
    protected LayoutManager createLayout() {
        return new LayoutManagerDelegate(super.createLayout()) {
            @Override
            public void layoutContainer(Container parent) {
                super.layoutContainer(parent);
                JComponent editor = spinner.getEditor();
                if (editor != null) {
                    layoutEditor(editor);
                }
            }
        };
    }

    protected void layoutEditor(@NotNull JComponent editor) {
        if (editor != null) {
            final Rectangle bounds = editor.getBounds();
            editor.setBounds(bounds.x, bounds.y, bounds.width - 6, bounds.height);
        }
    }

    protected void paintArrowButton(Graphics g,
                                    BasicArrowButton button,
                                    @MagicConstant(intValues = {SwingConstants.NORTH, SwingConstants.SOUTH}) int direction) {
        int y = direction == SwingConstants.NORTH ? button.getHeight() - 6 : 2;
        button.paintTriangle(g, (button.getWidth() - 8) / 2 - 1, y, 0, direction, spinner.isEnabled());
    }

    private void addEditorFocusListener(JComponent editor) {
        if (editor != null) {
            editor.getComponents()[0].addFocusListener(myFocusListener);
        }
    }

    private void removeEditorFocusListener(JComponent editor) {
        if (editor != null) {
            editor.getComponents()[0].removeFocusListener(myFocusListener);
        }
    }

    private JButton createArrow(@MagicConstant(intValues = {SwingConstants.NORTH, SwingConstants.SOUTH}) int direction) {
        final Color shadow = UIUtil.getPanelBackground();
        final Color enabledColor = new JBColor(Gray._255, UIUtil.getLabelForeground());
        final Color disabledColor = new JBColor(Gray._200, UIUtil.getLabelForeground().darker());
        BasicArrowButton b = new BasicArrowButton(direction, shadow, shadow, enabledColor, shadow) {
            @Override
            public void paint(Graphics g) {
                paintArrowButton(g, this, direction);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }

            @Override
            public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
                final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
                int mid;
                final int w = 8;
                final int h = 6;
                mid = w / 2;

                g.setColor(isEnabled ? enabledColor : disabledColor);

                g.translate(x, y);
                switch (direction) {
                    case SOUTH:
                        g.fillPolygon(new int[]{0, w, mid}, new int[]{1, 1, h}, 3);
                        break;
                    case NORTH:
                        g.fillPolygon(new int[]{0, w, mid}, new int[]{h - 1, h - 1, 0}, 3);
                        break;
                    case WEST:
                    case EAST:
                }
                g.translate(-x, -y);
                config.restore();
            }
        };
        Border buttonBorder = UIManager.getBorder("Spinner.arrowButtonBorder");
        if (buttonBorder instanceof UIResource) {
            // Wrap the border to avoid having the UIResource be replaced by
            // the ButtonUI. This is the opposite of using BorderUIResource.
            b.setBorder(new CompoundBorder(buttonBorder, null));
        } else {
            b.setBorder(buttonBorder);
        }
        b.setInheritsPopupMenu(true);
        return b;
    }

    protected static class LayoutManagerDelegate implements LayoutManager {
        protected final LayoutManager myDelegate;

        public LayoutManagerDelegate(LayoutManager delegate) {
            myDelegate = delegate;
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
            myDelegate.addLayoutComponent(name, comp);
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            myDelegate.removeLayoutComponent(comp);
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return myDelegate.preferredLayoutSize(parent);
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return myDelegate.minimumLayoutSize(parent);
        }

        @Override
        public void layoutContainer(Container parent) {
            myDelegate.layoutContainer(parent);
        }
    }
}
