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
import com.intellij.openapi.ui.AbstractPainter;
import com.intellij.openapi.wm.impl.IdeGlassPaneImpl;
import com.intellij.ui.JBColor;
import com.intellij.ui.paint.LinePainter2D;
import com.intellij.ui.paint.RectanglePainter;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.util.ArrayList;
import java.util.List;

public class OverlayPainter extends AbstractPainter implements AWTEventListener, Disposable {
  private static final long MASK = AWTEvent.WINDOW_EVENT_MASK | AWTEvent.WINDOW_STATE_EVENT_MASK | AWTEvent.COMPONENT_EVENT_MASK;
  @NotNull
  private final List<Component> myComponents = new ArrayList<>();
  @NotNull
  private final Component myInitialComponent;
  @NotNull
  private final List<HighlightComponent> myHighlightComponents = new ArrayList<>();
  private final boolean myIsHighlighted = true;
  private final Disposable overlayDisposable;

  public OverlayPainter(final JComponent rootPane, final Disposable overlayDisposable) {
    this.overlayDisposable = overlayDisposable;
    myComponents.add(rootPane);
    myInitialComponent = rootPane;

    //    Toolkit.getDefaultToolkit().addAWTEventListener(this, MASK);

    rootPane.addHierarchyListener((event) -> {
      final Window window = UIUtil.getWindow(rootPane);
      if (MTUiUtils.isDialogWindow(window)) {
        updateHighlighting();
      }

    });
  }

  @Nullable
  private static JComponent getGlassPane(@NotNull final Component component) {
    final JRootPane rootPane = SwingUtilities.getRootPane(component);
    return rootPane == null ? null : (JComponent) rootPane.getGlassPane();
  }

  @Nullable
  private static HighlightComponent createHighlighter(@NotNull final Component component, @Nullable final Rectangle bounds) {
    Rectangle rectangle = bounds;
    final JComponent glassPane = getGlassPane(component);
    if (glassPane == null) {
      return null;
    }

    if (rectangle != null) {
      rectangle = SwingUtilities.convertRectangle(component, rectangle, glassPane);
    } else {
      final Point pt = SwingUtilities.convertPoint(component, new Point(0, 0), glassPane);
      rectangle = new Rectangle(pt.x, pt.y, component.getWidth(), component.getHeight());
    }

    JBColor color = new JBColor(JBColor.GREEN, JBColor.RED);
    if (rectangle.width == 0 || rectangle.height == 0) {
      rectangle.width = Math.max(rectangle.width, 1);
      rectangle.height = Math.max(rectangle.height, 1);
      color = JBColor.BLUE;
    }

    final Insets insets = component instanceof JComponent ? ((JComponent) component).getInsets() : JBUI.emptyInsets();
    final HighlightComponent highlightComponent = new HighlightComponent(color, insets);
    highlightComponent.setBounds(rectangle);

    glassPane.add(highlightComponent);
    glassPane.revalidate();
    glassPane.repaint();

    return highlightComponent;
  }

  @Override
  public boolean needsRepaint() {
    return true;
  }

  @Override
  public void executePaint(final Component component, final Graphics2D g) {
    updateHighlighting();
  }

  @Override
  public void dispose() {
    //    myInitialComponent.removeHierarchyListener();
    overlayDisposable.dispose();
    Toolkit.getDefaultToolkit().removeAWTEventListener(this);

  }

  @Override
  public void eventDispatched(final AWTEvent event) {
    final Object source = event == null ? null : event.getSource();
    if (source instanceof Window) {
      for (Container container = (Window) source; container instanceof Window && container instanceof RootPaneContainer; container =
        container.getParent()) {
        final JRootPane root = ((RootPaneContainer) container).getRootPane();
        if (root != null) {
          final JComponent pane = (JComponent) root.getGlassPane();
          if (pane instanceof IdeGlassPaneImpl) {

          }
        }
      }
    }
  }

  private void updateHighlighting() {
    for (final HighlightComponent component : myHighlightComponents) {
      final JComponent glassPane = getGlassPane(component);
      if (glassPane != null) {
        glassPane.remove(component);
        glassPane.revalidate();
        glassPane.repaint();
      }
    }
    myHighlightComponents.clear();

    if (myIsHighlighted) {
      for (final Component component : myComponents) {
        ContainerUtil.addIfNotNull(myHighlightComponents, createHighlighter(component, null));
      }
    }
  }

  private static final class HighlightComponent extends JComponent {
    @NotNull
    private final Color myColor;
    @NotNull
    private final Insets myInsets;

    private HighlightComponent(@NotNull final Color color, @NotNull final Insets insets) {
      myColor = color;
      myInsets = insets;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    protected void paintComponent(final Graphics g) {
      final Graphics2D g2d = (Graphics2D) g;

      final Color oldColor = g2d.getColor();
      final Composite old = g2d.getComposite();
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

      final Rectangle r = getBounds();
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

      g2d.setComposite(old);
      g2d.setColor(oldColor);
    }
  }

}
