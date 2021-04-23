/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaRootPaneUI;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.ex.IdeFrameEx;
import com.intellij.ui.DoubleClickListener;
import com.intellij.util.Consumer;
import com.intellij.util.lang.JavaVersion;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.utils.MTUI;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "SyntheticAccessorCall",
  "StandardVariableNames"})
public final class MTRootPaneUI extends DarculaRootPaneUI {
  @NonNls
  private static final String WINDOW_DARK_APPEARANCE = "jetbrains.awt.windowDarkAppearance";
  @NonNls
  private static final String REGISTRY_VALUE = "ide.mac.transparentTitleBarAppearance";
  private static final int JDK_VER = 11;

  private Runnable disposer = null;

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

    if (SystemInfo.isMac || SystemInfo.isLinux) {
      c.putClientProperty(WINDOW_DARK_APPEARANCE, themeIsDark);
      if (darkTitleBar) {

        if (JavaVersion.current().feature >= JDK_VER) {
          final JRootPane rootPane = (JRootPane) c;

          c.addHierarchyListener((event) -> {
            final Window window = UIUtil.getWindow(c);
            final String title = getWindowTitle(window);
            if (title != null && !isDialogWindow(window)) {
              Registry.get(REGISTRY_VALUE).setValue(true);
              setCustomTitleBar(window, rootPane, (runnable) -> disposer = runnable);
            } else {
              Registry.get(REGISTRY_VALUE).setValue(false);
            }
          });
        } else {
          Registry.get(REGISTRY_VALUE).setValue(true);
        }
      } else {
        Registry.get(REGISTRY_VALUE).setValue(false);
      }
    }
  }

  @SuppressWarnings({"FeatureEnvy",
    "OverlyComplexAnonymousInnerClass"})
  private static void setCustomTitleBar(final Window window, final JRootPane rootPane, final Consumer<Runnable> onDispose) {
    final JBInsets topWindowInset = JBUI.insetsTop(24);

    // Create the title bar
    final Border customDecorationBorder = new AbstractBorder() {
      @Override
      public Insets getBorderInsets(final Component c) {
        if (isInFullScreen(window) || !MTConfig.getInstance().isDarkTitleBar()) {
          return JBUI.insets(0);
        }
        return topWindowInset;
      }

      @Override
      public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        if (isInFullScreen(window) || !MTConfig.getInstance().isDarkTitleBar()) {
          return;
        }

        final Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        try {
          final Rectangle headerRectangle = new Rectangle(0, 0, c.getWidth(), topWindowInset.top);
          graphics.setColor(UIUtil.getPanelBackground());
          graphics.fill(headerRectangle);
          graphics.setFont(UIManager.getFont("Panel.font"));

          final Color color = window.isActive() ? MTUI.Label.getLabelForeground() : MTUI.Label.getLabelDisabledForeground();
          graphics.setColor(color);

          final int controlButtonsWidth = 70;
          final String windowTitle = getWindowTitle(window);
          final double widthToFit =
            ((controlButtonsWidth << 1) + GraphicsUtil.stringWidth(windowTitle, g.getFont())) - c.getWidth();

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
    final WindowListener windowAdapter = new WindowAdapter() {
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
      protected boolean onDoubleClick(final @NotNull MouseEvent event) {
        final Frame frame;

        if (window instanceof Frame) {
          frame = (Frame) window;
        } else {
          return false;
        }
        final int state = frame.getExtendedState();

        if ((event.getClickCount() % 2) == 0 && ((event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0)) {
          if (frame.isResizable()) {
            if ((state & Frame.MAXIMIZED_BOTH) == 0) {
              frame.setExtendedState(state | Frame.MAXIMIZED_BOTH);
            } else {
              frame.setExtendedState(state & ~Frame.MAXIMIZED_BOTH);
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

  private static @Nullable String getWindowTitle(final Window window) {
    return window instanceof JDialog ? ((Dialog) window).getTitle() :
           window instanceof JFrame ? ((Frame) window).getTitle() : null;
  }

  private static boolean isDialogWindow(final Window window) {
    return window instanceof JDialog ? ((Dialog) window).isModal() :
           window instanceof JFrame ? ((Frame) window).isUndecorated() : true;
  }

  @SuppressWarnings({"UnstableApiUsage",
    "MethodOnlyUsedFromInnerClass"})
  private static boolean isInFullScreen(final Window window) {
    final Component ultimateParent = UIUtil.findUltimateParent(window);
    if (ultimateParent == window && ultimateParent instanceof IdeFrameEx) {
      final IdeFrame ultimateParentWindowForEvent = (IdeFrame) ultimateParent;
      return ultimateParentWindowForEvent.isInFullScreen();
    }
    return false;
  }
}
