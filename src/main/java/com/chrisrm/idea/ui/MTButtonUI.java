//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chrisrm.idea.ui;

import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MTButtonUI extends DarculaButtonUI {
    @SuppressWarnings("MethodOverridesStaticMethodOfSuperclass")
    public static ComponentUI createUI(JComponent c) {
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Component component = e.getComponent();
                component.setBackground(Color.red);
                e.getComponent().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Component component = e.getComponent();
                component.setBackground(UIManager.getColor("Button.darcula.color1"));
                e.getComponent().repaint();
            }
        };

        c.addMouseListener(listener);

        return new MTButtonUI();
    }

    public static boolean isHelpButton(JComponent button) {
        return (SystemInfo.isMac || UIUtil.isUnderDarcula() || UIUtil.isUnderWin10LookAndFeel())
                && button instanceof JButton
                && "help".equals(button.getClientProperty("JButton.buttonType"));
    }

    @Override
    public void update(Graphics g, JComponent c) {
        super.update(g, c);

    }

    /**
     * Paints additional buttons decorations
     *
     * @param g Graphics
     * @param c button component
     * @return <code>true</code> if it is allowed to continue painting,
     * <code>false</code> if painting should be stopped
     */
    protected boolean paintDecorations(Graphics2D g, JComponent c) {
        int w = c.getWidth();
        int h = c.getHeight();

        ((JButton) c).setBorderPainted(false);
        ((JButton) c).setFocusPainted(false);
        ((JButton) c).setContentAreaFilled(false);

        if (isHelpButton(c)) {
            g.setPaint(UIUtil.getGradientPaint(0, 0, getButtonColor1(), 0, h, getButtonColor2()));
            int off = JBUI.scale(22);
            int x = (w - off) / 2;
            int y = (h - off) / 2;
            g.fillOval(x, y, off, off);
            AllIcons.Actions.Help.paintIcon(c, g, x + JBUI.scale(3), y + JBUI.scale(3));
            return false;
        } else {
            final Border border = c.getBorder();
            final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
            final boolean square = isSquare(c);
            if (c.isEnabled() && border != null) {
                final Insets ins = border.getBorderInsets(c);
                final int yOff = (ins.top + ins.bottom) / 4;
                g.setPaint(UIUtil.getGradientPaint(0, 0, getButtonColor1(), 0, h, getButtonColor2()));

                int rad = JBUI.scale(2);
                g.fillRoundRect(JBUI.scale(square ? 2 : 4), yOff, w - 2 * JBUI.scale(4), h - 2 * yOff, rad, rad);
            }
            config.restore();
            return true;
        }
    }

    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        if (isHelpButton(c)) {
            return;
        }

        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();
        Color fg = button.getForeground();
        if (fg instanceof UIResource) {
            final Color selectedFg = UIManager.getColor("Button.darcula.selectedButtonForeground");
            if (selectedFg != null) {
                fg = selectedFg;
            }
        }
        g.setColor(fg);

        // Set bold
        Font newFont = g.getFont();
        newFont.deriveFont(Font.BOLD);
        g.setFont(newFont);
        //UISettings.setupAntialiasing(g);

        FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g);
        int mnemonicIndex = DarculaLaf.isAltPressed() ? button.getDisplayedMnemonicIndex() : -1;
        if (model.isEnabled()) {
            SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemonicIndex,
                    textRect.x + getTextShiftOffset(),
                    textRect.y + metrics.getAscent() + getTextShiftOffset());
        } else {
            paintDisabledText(g, text, c, textRect, metrics);
        }
    }

}
