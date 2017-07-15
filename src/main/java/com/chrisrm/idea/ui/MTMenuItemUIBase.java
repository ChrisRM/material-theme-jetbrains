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

import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.laf.darcula.ui.DarculaMenuItemUIBase;
import com.intellij.util.ui.JBInsets;
import sun.swing.MenuItemLayoutHelper;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Konstantin Bulenkov
 */
public class MTMenuItemUIBase extends DarculaMenuItemUIBase {
  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(final JComponent c) {
    return new MTMenuItemUIBase();
  }

  public final void processMouseEvent(final JMenuItem item,
                                      final MouseEvent e,
                                      final MenuElement[] path,
                                      final MenuSelectionManager manager) {
    Point p = e.getPoint();
    if (p.x >= 0 && p.x < item.getWidth() &&
        p.y >= 0 && p.y < item.getHeight()) {
      if (e.getID() == MouseEvent.MOUSE_RELEASED) {
        manager.clearSelectedPath();
        item.doClick(0);
        item.setArmed(false);
      } else {
        manager.setSelectedPath(path);
      }
    } else if (item.getModel().isArmed()) {
      MenuElement[] newPath = new MenuElement[path.length - 1];
      int i, c;
      for (i = 0, c = path.length - 1; i < c; i++) {
        newPath[i] = path[i];
      }
      manager.setSelectedPath(newPath);
    }
  }

  protected final void paintMenuItem(final Graphics g, final JComponent c,
                                     final Icon checkIcon, final Icon arrowIcon,
                                     final Color background, final Color foreground,
                                     final int defaultTextIconGap) {
    // Save original graphics font and color
    Font holdf = g.getFont();
    Color holdc = g.getColor();

    JMenuItem mi = (JMenuItem) c;
    g.setFont(mi.getFont());

    Rectangle viewRect = new Rectangle(0, 0, mi.getWidth(), mi.getHeight());
    JBInsets.removeFrom(viewRect, mi.getInsets());

    MenuItemLayoutHelper lh = new MenuItemLayoutHelper(mi, checkIcon,
        arrowIcon, viewRect, defaultTextIconGap, "-", //todo[kb] use protected field BasicMenuItemUI.acceleratorDelimiter when we
        // move to java 1.7
        mi.getComponentOrientation().isLeftToRight(), mi.getFont(),
        acceleratorFont, MenuItemLayoutHelper.useCheckAndArrow(menuItem),
        getPropertyPrefix());
    MenuItemLayoutHelper.LayoutResult lr = lh.layoutMenuItem();

    paintBackground(g, mi, background);
    paintCheckIcon(g, lh, lr, holdc, foreground);
    paintIcon(g, lh, lr, holdc);
    g.setColor(foreground);
    UISettings.setupAntialiasing(g);
    paintText(g, lh, lr);
    paintAccText(g, lh, lr);
    paintArrowIcon(g, lh, lr, foreground);

    // Restore original graphics font and color
    g.setColor(holdc);
    g.setFont(holdf);
  }

  protected final void paintIcon(final Graphics g, final MenuItemLayoutHelper lh,
                                 final MenuItemLayoutHelper.LayoutResult lr, final Color holdc) {
    if (lh.getIcon() != null) {
      Icon icon;
      ButtonModel model = lh.getMenuItem().getModel();
      if (!model.isEnabled()) {
        icon = lh.getMenuItem().getDisabledIcon();
      } else if (model.isPressed() && model.isArmed()) {
        icon = lh.getMenuItem().getPressedIcon();
        if (icon == null) {
          // Use default icon
          icon = lh.getMenuItem().getIcon();
        }
      } else {
        icon = lh.getMenuItem().getIcon();
      }

      if (icon != null) {
        icon.paintIcon(lh.getMenuItem(), g, lr.getIconRect().x,
            lr.getIconRect().y);
        g.setColor(holdc);
      }
    }
  }

  /**
   * @param g
   * @param lh
   * @param lr
   * @param holdc
   * @param foreground
   */
  protected void paintCheckIcon(final Graphics g, final MenuItemLayoutHelper lh,
                                final MenuItemLayoutHelper.LayoutResult lr,
                                final Color holdc, final Color foreground) {
    if (lh.getCheckIcon() != null) {
      ButtonModel model = lh.getMenuItem().getModel();
      if (model.isArmed() || (lh.getMenuItem() instanceof JMenu
          && model.isSelected())) {
        g.setColor(foreground);
      } else {
        g.setColor(holdc);
      }
      if (lh.useCheckAndArrow()) {
        lh.getCheckIcon().paintIcon(lh.getMenuItem(), g,
            lr.getCheckRect().x, lr.getCheckRect().y);
      }
      g.setColor(holdc);
    }
  }

  protected final void paintAccText(final Graphics g, final MenuItemLayoutHelper lh,
                                    final MenuItemLayoutHelper.LayoutResult lr) {
    if (!lh.getAccText().equals("")) {
      ButtonModel model = lh.getMenuItem().getModel();
      g.setFont(lh.getAccFontMetrics().getFont());
      if (!model.isEnabled()) {
        // *** paint the accText disabled
        if (disabledForeground != null) {
          g.setColor(disabledForeground);
          SwingUtilities2.drawString(lh.getMenuItem(), g,
              lh.getAccText(), lr.getAccRect().x,
              lr.getAccRect().y + lh.getAccFontMetrics().getAscent());
        } else {
          g.setColor(lh.getMenuItem().getBackground().brighter());
          SwingUtilities2.drawString(lh.getMenuItem(), g,
              lh.getAccText(), lr.getAccRect().x,
              lr.getAccRect().y + lh.getAccFontMetrics().getAscent());
          g.setColor(lh.getMenuItem().getBackground().darker());
          SwingUtilities2.drawString(lh.getMenuItem(), g,
              lh.getAccText(), lr.getAccRect().x - 1,
              lr.getAccRect().y + lh.getFontMetrics().getAscent() - 1);
        }
      } else {
        // *** paint the accText normally
        if (model.isArmed()
            || (lh.getMenuItem() instanceof JMenu
            && model.isSelected())) {
          g.setColor(acceleratorSelectionForeground);
        } else {
          g.setColor(acceleratorForeground);
        }
        SwingUtilities2.drawString(lh.getMenuItem(), g, lh.getAccText(),
            lr.getAccRect().x, lr.getAccRect().y +
                lh.getAccFontMetrics().getAscent());
      }
    }
  }

  protected final void paintText(final Graphics g, final MenuItemLayoutHelper lh,
                                 final MenuItemLayoutHelper.LayoutResult lr) {
    if (!lh.getText().equals("")) {
      if (lh.getHtmlView() != null) {
        // Text is HTML
        lh.getHtmlView().paint(g, lr.getTextRect());
      } else {
        // Text isn't HTML
        UISettings.setupAntialiasing(g);
        paintText(g, lh.getMenuItem(), lr.getTextRect(), lh.getText());
      }
    }
  }

  protected final void paintArrowIcon(final Graphics g, final MenuItemLayoutHelper lh,
                                      final MenuItemLayoutHelper.LayoutResult lr,
                                      final Color foreground) {
    if (lh.getArrowIcon() != null) {
      ButtonModel model = lh.getMenuItem().getModel();
      if (model.isArmed() || (lh.getMenuItem() instanceof JMenu
          && model.isSelected())) {
        g.setColor(foreground);
      }
      if (lh.useCheckAndArrow()) {
        lh.getArrowIcon().paintIcon(lh.getMenuItem(), g,
            lr.getArrowRect().x, lr.getArrowRect().y);
      }
    }
  }
}
