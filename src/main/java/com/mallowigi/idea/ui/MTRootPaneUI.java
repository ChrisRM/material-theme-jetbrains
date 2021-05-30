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

import com.intellij.ide.DataManager;
import com.intellij.ide.ui.laf.darcula.ui.DarculaRootPaneUI;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.ui.ColorUtil;
import com.intellij.util.lang.JavaVersion;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTLicenseChecker;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;

import static com.mallowigi.idea.utils.MTUiUtils.stringToARGB;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "SyntheticAccessorCall",
  "StandardVariableNames"})
public final class MTRootPaneUI extends DarculaRootPaneUI {
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

  @Override
  public void installUI(final JComponent c) {
    super.installUI(c);
    //    final boolean darkTitleBar = MTConfig.getInstance().isDarkTitleBar();
    final boolean isPremium = MTLicenseChecker.isLicensed();

    if (!isPremium) {
      return;
    }

    if (SystemInfo.isMac || SystemInfo.isLinux) {
      //      if (darkTitleBar) {

      if (JavaVersion.current().feature >= JDK_VER) {
        final JRootPane rootPane = (JRootPane) c;

        c.addHierarchyListener((event) -> {
          final Window window = UIUtil.getWindow(c);
          final String title = getWindowTitle(window);
          if (title != null && !isDialogWindow(window)) {
            setCustomTitleBar(window, rootPane, (runnable) -> disposer = runnable);
          }
        });
        //        }
      }
    }
  }

  private static int getTransparentTitleBarHeight(final JRootPane rootPane) {
    final Object property = rootPane.getClientProperty("Window.transparentTitleBarHeight");
    if (property instanceof Integer) {
      return (int) property;
    }
    return "small".equals(rootPane.getClientProperty("Window.style")) ? 19 : 24;
  }

  public static @Nullable Project getCurrentProject() {
    return CommonDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
  }

  @NotNull
  private static Color getFrameColor(final String title) {
    final Color projectColor = new Color(stringToARGB(title));

    return ColorUtil.withAlpha(MTUiUtils.darker(projectColor, 2), 0.5);
  }

  private static void setCustomTitleBar(@NotNull final Window window,
                                        @NotNull final JRootPane rootPane,
                                        final java.util.function.Consumer<? super Runnable> onDispose) {
    if (!SystemInfoRt.isMac || !Registry.is("ide.mac.transparentTitleBarAppearance", false)) {
      return;
    }

    final JBInsets topWindowInset = JBUI.insetsTop(getTransparentTitleBarHeight(rootPane));

    rootPane.putClientProperty("apple.awt.fullWindowContent", true);
    rootPane.putClientProperty("apple.awt.transparentTitleBar", true);

    // Use standard properties starting jdk 17
    if (Runtime.version().feature() >= 17) {
      rootPane.putClientProperty("apple.awt.windowTitleVisible", false);
    }

    final Border customDecorationBorder = new AbstractBorder() {
      @Override
      public Insets getBorderInsets(final Component c) {
        return topWindowInset;
      }

      @Override
      public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        final Graphics2D graphics = (Graphics2D) g.create();
        try {
          final Shape headerRectangle = new Rectangle(0, 0, c.getWidth(), topWindowInset.top);
          final Color titleColor = getCurrentProject() == null ? UIUtil.getPanelBackground() : getFrameColor(getCurrentProject().getName());
          graphics.setColor(titleColor);
          graphics.fill(headerRectangle);
          final Color color = window.isActive() ? MTUI.Label.getLabelForeground() : MTUI.Label.getLabelDisabledForeground();
          graphics.setColor(color);
        } finally {
          graphics.dispose();
        }
      }
    };
    rootPane.setBorder(customDecorationBorder);

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
    final PropertyChangeListener propertyChangeListener = e -> rootPane.repaint();
    window.addPropertyChangeListener("title", propertyChangeListener);
    onDispose.accept((Runnable) () -> {
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
           !(window instanceof JFrame) || ((Frame) window).isUndecorated();
  }

}
