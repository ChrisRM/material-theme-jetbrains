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

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.paint.LinePainter2D;
import com.intellij.ui.paint.RectanglePainter;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

public final class OverlayPainter implements AWTEventListener, Disposable {
  /**
   * Listen to window events
   */
  private static final long MASK = AWTEvent.WINDOW_EVENT_MASK | AWTEvent.WINDOW_STATE_EVENT_MASK;
  /**
   * The registered root panes
   */
  @NotNull
  private final Collection<Component> rootPanes = new ArrayList<>(2);
  /**
   * The currently overlayed root panes
   */
  @NotNull
  private final Collection<OverlayComponent> overlaidRootPanes = new ArrayList<>(2);

  /**
   * Stack of opened windows
   */
  private final Deque<Object> openedWindowsStack = new ArrayDeque<>(1);

  public static OverlayPainter getInstance() {
    return ApplicationManager.getApplication().getService(OverlayPainter.class);
  }

  public OverlayPainter() {
    Toolkit.getDefaultToolkit().addAWTEventListener(this, MASK);
  }

  @Override
  public void dispose() {
    Toolkit.getDefaultToolkit().removeAWTEventListener(this);
    removeOverlays();
  }

  /**
   * Get the IdeGlassPane of a component
   *
   * @param component the component
   * @return the IdeGlassPane
   */
  @Nullable
  private static JComponent getGlassPane(@NotNull final Component component) {
    final JRootPane rootPane = SwingUtilities.getRootPane(component);
    return rootPane == null ? null : (JComponent) rootPane.getGlassPane();
  }

  @Nullable
  private static OverlayComponent createOverlay(@NotNull final Component rootPane) {
    final Rectangle bounds;
    final JComponent glassPane = getGlassPane(rootPane);
    if (glassPane == null) {
      return null;
    }

    // Get bounds of rootpane
    final Point pt = SwingUtilities.convertPoint(rootPane, new Point(0, 0), glassPane);
    bounds = new Rectangle(pt.x, pt.y, rootPane.getWidth(), rootPane.getHeight());
    if (bounds.width == 0 || bounds.height == 0) {
      bounds.width = Math.max(bounds.width, 1);
      bounds.height = Math.max(bounds.height, 1);
    }

    final Insets insets = rootPane instanceof Container ? ((Container) rootPane).getInsets() : JBUI.emptyInsets();
    final OverlayComponent overlayComponent = new OverlayComponent(insets);
    overlayComponent.setBounds(bounds);

    glassPane.add(overlayComponent);
    glassPane.revalidate();
    glassPane.repaint();

    return overlayComponent;
  }

  /**
   * Show/Hide the overlay when a window is shown or hidden
   *
   * @param event the event
   */
  @Override
  public void eventDispatched(final AWTEvent event) {
    if (!MTConfig.getInstance().isShowOverlays()) {
      if (!openedWindowsStack.isEmpty()) {
        openedWindowsStack.clear();
        removeOverlays();
      }
      return;
    }

    final Object source = event == null ? null : event.getSource();
    if (source instanceof Window) {
      // Remove highlights when all windows are closed
      if (event.getID() == WindowEvent.WINDOW_CLOSED && openedWindowsStack.contains(source)) {
        openedWindowsStack.pop();
        if (openedWindowsStack.isEmpty()) {
          removeOverlays();
        }
      }
      // If a dialog window is opened, show the overlay
      else if (event.getID() == WindowEvent.WINDOW_OPENED && MTUiUtils.isDialogWindow((Window) source)) {
        openedWindowsStack.push(source);
        updateOverlays();
      }
    }
  }

  private void updateOverlays() {
    removeOverlays();

    for (final Component rootPane : rootPanes) {
      ContainerUtil.addIfNotNull(overlaidRootPanes, createOverlay(rootPane));
    }
  }

  /**
   * Remove all overlays
   */
  private void removeOverlays() {
    for (final OverlayComponent overlay : overlaidRootPanes) {
      final JComponent glassPane = getGlassPane(overlay);
      if (glassPane != null) {
        glassPane.remove(overlay);
        glassPane.revalidate();
        glassPane.repaint();
      }
    }
    overlaidRootPanes.clear();
  }

  /**
   * Add a root pane to the list of rootpanes
   *
   * @param rootPane the rootpane
   */
  void addRootPane(final JRootPane rootPane) {
    rootPanes.add(rootPane);
  }

  /**
   * The Overlay itself
   */
  private static final class OverlayComponent extends JComponent {
    @NotNull
    private final Insets myInsets;

    OverlayComponent(@NotNull final Insets insets) {
      myInsets = insets;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    protected void paintComponent(final Graphics g) {
      final Graphics2D g2d = (Graphics2D) g;

      final Color oldColor = g2d.getColor();
      final Composite oldComposite = g2d.getComposite();
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

      final Rectangle r = getBounds();
      final Color myColor = Color.BLACK;
      RectanglePainter.paint(g2d, 0, 0, r.width, r.height, 0, myColor, null);

      ((Graphics2D) g).setPaint(myColor.darker());
      for (int i = 0; i < myInsets.left; i++) {
        LinePainter2D.paint(g2d, i, myInsets.top, i, r.height - myInsets.bottom - 1);
      }
      for (int i = 0; i < myInsets.right; i++) {
        LinePainter2D.paint(g2d, r.width - i - 1, myInsets.top, r.width - i - 1, r.height - myInsets.bottom - 1);
      }
      for (int i = 0; i < myInsets.top; i++) {
        LinePainter2D.paint(g2d, 0, i, r.width, i);
      }
      for (int i = 0; i < myInsets.bottom; i++) {
        LinePainter2D.paint(g2d, 0, r.height - i - 1, r.width, r.height - i - 1);
      }

      g2d.setComposite(oldComposite);
      g2d.setColor(oldColor);
    }
  }

}
