//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chrisrm.idea.ui;

import com.chrisrm.idea.utils.ColorCycle;
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
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MTButtonUI extends DarculaButtonUI {
    @SuppressWarnings("MethodOverridesStaticMethodOfSuperclass")
    public static ComponentUI createUI(JComponent c) {
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

    @Override
    protected void installDefaults(final AbstractButton b) {
        super.installDefaults(b);
        b.setFont(b.getFont().deriveFont(Font.BOLD, 13.0f));
    }

    @Override
    protected BasicButtonListener createButtonListener(AbstractButton b) {
        return new BasicButtonListener(b) {

            private ColorCycle colorCycle = new ColorCycle(2, 20);

            @Override
            public void mouseEntered(MouseEvent e) {
                highlightButton(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeHighlight(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                highlightButton(e);
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                removeHighlight(e);
                super.mouseReleased(e);
            }

            private void highlightButton(MouseEvent e) {
                colorCycle.stop();

                Component component = e.getComponent();
                colorCycle.setC((JComponent) component);
                Color hoverColor = UIManager.getColor("Button.mt.selection.color1");
                Color preHoverColor = UIManager.getColor("Button.mt.selection.color2");

                colorCycle.start(preHoverColor, hoverColor);
            }

            private void removeHighlight(MouseEvent e) {
                colorCycle.stop();

                Component component = e.getComponent();
                colorCycle.setC((JComponent) component);
                Color notHoverColor = UIManager.getColor("Button.mt.color1");
                Color preNotHoverColor = UIManager.getColor("Button.mt.color2");

                colorCycle.start(preNotHoverColor, notHoverColor);
            }
        };
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
        Color background = c.getBackground();

        // Remove decorations
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
            if (c.isEnabled() && border != null) {
                final Insets ins = border.getBorderInsets(c);
                final int xOff = (ins.left + ins.right) / 4;
                final int yOff = (ins.top + ins.bottom) / 4;
                g.setPaint(UIUtil.getGradientPaint(0, 0, background, 0, h, background));
                int rad = JBUI.scale(3);
                g.fillRoundRect(xOff, yOff, w - 2 * xOff, h - 2 * yOff, rad, rad);
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
        if (fg instanceof UIResource && button.isSelected()) {
            final Color selectedFg = UIManager.getColor("Button.mt.selectedButtonForeground");
            if (selectedFg != null) {
                fg = selectedFg;
            }
        } else {
            final Color selectedFg = UIManager.getColor("Button.mt.foreground");
            if (selectedFg != null) {
                fg = selectedFg;
            }
        }
        g.setColor(fg);

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
