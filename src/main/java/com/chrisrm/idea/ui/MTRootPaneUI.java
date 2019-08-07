/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.utils.MTUI;
import com.intellij.ide.ui.laf.darcula.ui.DarculaRootPaneUI;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.wm.ex.IdeFrameEx;
import com.intellij.ui.DoubleClickListener;
import com.intellij.util.Consumer;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;

public final class MTRootPaneUI extends DarculaRootPaneUI {
  @NonNls
  private static final String WINDOW_DARK_APPEARANCE = "jetbrains.awt.windowDarkAppearance";
  @NonNls
  private static final String TRANSPARENT_TITLE_BAR_APPEARANCE = "jetbrains.awt.transparentTitleBarAppearance";

  private Runnable disposer;

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return hasCustomDecoration() ? new MTRootPaneUI() : createWindowsRootPaneUI();
  }

  private static boolean hasCustomDecoration() {
    return SystemInfo.isMac;
  }

  @SuppressWarnings("OverlyBroadCatchBlock")
  private static ComponentUI createWindowsRootPaneUI() {
    try {
      return (ComponentUI) Class.forName("com.sun.java.swing.plaf.windows.WindowsRootPaneUI").getConstructor().newInstance();
    } catch (final Exception e) {
      return new BasicRootPaneUI();
    }
  }

  @Override
  public void uninstallUI(final JComponent c) {
    super.uninstallUI(c);
    if (disposer != null) {
      disposer.run();
    }
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  public void installUI(final JComponent c) {
    super.installUI(c);
    final boolean themeIsDark = MTConfig.getInstance().getSelectedTheme().isDark();
    final boolean darkTitleBar = MTConfig.getInstance().isDarkTitleBar();
    final boolean allowDarkWindowDecorations = Registry.get("ide.mac.allowDarkWindowDecorations").asBoolean();

    if (SystemInfo.isMac || SystemInfo.isLinux) {
      if (darkTitleBar) {
        Registry.get("ide.mac.allowDarkWindowDecorations").setValue(themeIsDark);

        c.putClientProperty(WINDOW_DARK_APPEARANCE, themeIsDark);

        if (!SystemInfo.isJavaVersionAtLeast(11)) {
          c.putClientProperty(TRANSPARENT_TITLE_BAR_APPEARANCE, true);
        } else {
          c.putClientProperty(TRANSPARENT_TITLE_BAR_APPEARANCE, true);
          final JRootPane rootPane = (JRootPane) c;

          c.addHierarchyListener((event) -> {
            final Window window = UIUtil.getWindow(c);
            final String title = getWindowTitle(window);
            if (title != null && !title.equals("") && !title.equals("This should not be shown")) {
              setCustomTitleBar(window, rootPane, (runnable) -> disposer = runnable);
            }
          });
        }
      } else {
        c.putClientProperty(WINDOW_DARK_APPEARANCE, themeIsDark && allowDarkWindowDecorations);
        c.putClientProperty(TRANSPARENT_TITLE_BAR_APPEARANCE, false);
      }
    }
  }

  private static void setCustomTitleBar(final Window window, final JRootPane rootPane, final Consumer<Runnable> onDispose) {
    final JBInsets topWindowInset = JBUI.insetsTop(24);

    // Create the title bar
    final AbstractBorder customDecorationBorder = new AbstractBorder() {
      @Override
      public Insets getBorderInsets(final Component c) {
        if (isInFullScreen(window)) {
          return JBUI.insets(0);
        }
        return topWindowInset;
      }

      @Override
      public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        if (isInFullScreen(window)) {
          return;
        }

        final Graphics2D graphics = (Graphics2D) g.create();
        try {
          final Rectangle headerRectangle = new Rectangle(0, 0, c.getWidth(), topWindowInset.top);
          graphics.setColor(UIUtil.getPanelBackground());
          graphics.fill(headerRectangle);
          graphics.setFont(UIManager.getFont("Panel.font"));

          final Color color = window.isActive() ? MTUI.Label.getLabelForeground() : MTUI.Label.getLabelDisabledForeground();
          graphics.setColor(color);

          final int controlButtonsWidth = 70;
          final String windowTitle = getWindowTitle(window);
          final double widthToFit = (controlButtonsWidth * 2 + GraphicsUtil.stringWidth(windowTitle, g.getFont())) - c.getWidth();

          // Draw the title
          if (widthToFit <= 0) {
            UIUtil.drawCenteredString(graphics, headerRectangle, windowTitle);
          } else {
            final FontMetrics fm = graphics.getFontMetrics();
            final Rectangle2D stringBounds = fm.getStringBounds(windowTitle, graphics);
            final Rectangle bounds =
                AffineTransform.getTranslateInstance(controlButtonsWidth, fm.getAscent() + (
                    headerRectangle.height - stringBounds.getHeight()) / 2).createTransformedShape(stringBounds).getBounds();
            UIUtil.drawCenteredString(graphics, bounds, windowTitle, false, true);
          }
        } finally {
          graphics.dispose();
        }
      }
    };
    rootPane.setBorder(customDecorationBorder);

    // Listen for activations
    final WindowAdapter windowAdapter = new WindowAdapter() {
      @Override
      public void windowActivated(final WindowEvent e) {
        rootPane.repaint();
      }

      @Override
      public void windowDeactivated(final WindowEvent e) {
        rootPane.repaint();
      }
    };

    // Listen for double clicks
    new DoubleClickListener() {
      @Override
      protected boolean onDoubleClick(final MouseEvent e) {
        final Frame f;

        if (window instanceof Frame) {
          f = (Frame) window;
        } else {
          return false;
        }
        final int state = f.getExtendedState();

        if ((e.getClickCount() % 2) == 0 && ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0)) {
          if (f.isResizable()) {
            if ((state & Frame.MAXIMIZED_BOTH) != 0) {
              f.setExtendedState(state & ~Frame.MAXIMIZED_BOTH);
            } else {
              f.setExtendedState(state | Frame.MAXIMIZED_BOTH);
            }
            return true;
          }
        }
        return false;
      }
    }.installOn(rootPane);

    window.addWindowListener(windowAdapter);

    // Listen for title changes
    final PropertyChangeListener propertyChangeListener = evt -> rootPane.repaint();
    window.addPropertyChangeListener("title", propertyChangeListener);
    onDispose.consume(() -> {
      window.removeWindowListener(windowAdapter);
      window.removePropertyChangeListener("title", propertyChangeListener);
    });
  }

  private static String getWindowTitle(final Window window) {
    return window instanceof JDialog ? ((JDialog) window).getTitle() :
           window instanceof JFrame ? ((JFrame) window).getTitle() : null;
  }

  private static boolean isInFullScreen(final Window window) {
    final Component ultimateParent = UIUtil.findUltimateParent(window);
    if (ultimateParent == window && ultimateParent instanceof IdeFrameEx) {
      final IdeFrameEx ultimateParentWindowForEvent = (IdeFrameEx) ultimateParent;
      return ultimateParentWindowForEvent.isInFullScreen();
    }
    return false;
  }
}
