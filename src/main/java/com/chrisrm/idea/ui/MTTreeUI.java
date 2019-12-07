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
import com.intellij.icons.AllIcons;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.tree.AsyncTreeModel;
import com.intellij.ui.tree.ui.Control;
import com.intellij.ui.tree.ui.DefaultControl;
import com.intellij.util.ui.CenteredIcon;
import com.intellij.util.ui.MouseEventAdapter;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.ui.tree.TreeUtil;
import com.intellij.util.ui.tree.WideSelectionTreeUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.AbstractLayoutCache;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.intellij.openapi.application.ApplicationManager.getApplication;

@SuppressWarnings({"StaticVariableMayNotBeInitialized",
  "StaticVariableUsedBeforeInitialization",
  "NonThreadSafeLazyInitialization"})
public final class MTTreeUI extends BasicTreeUI {
  private static final Border LIST_PAINTER = MTUI.List.getListSelectionPainter();
  private static final Border LIST_FOCUSED_PAINTER = MTUI.List.getListFocusedSelectionPainter();
  private static final MTConfig CONFIG = MTConfig.getInstance();
  private static final Icon TRANSPARENT_ICON = IconLoader.getTransparentIcon(AllIcons.General.ArrowDown, 0);
  @Nullable
  private static Icon treeCollapsedIcon;
  @Nullable
  private static Icon treeExpandedIcon;
  @Nullable
  private static Icon treeSelectedCollapsedIcon;
  @Nullable
  private static Icon treeSelectedExpandedIcon;

  private final Control control = new DefaultControl();
  private final DispatchThreadValidator validator = new DispatchThreadValidator();

  private MTTreeUI() {
  }

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTTreeUI();
  }

  public static void resetIcons() {
    treeCollapsedIcon = null;
    treeExpandedIcon = null;
    treeSelectedCollapsedIcon = null;
    treeSelectedExpandedIcon = null;
  }

  @Nullable
  private static Color getSelectionBackgroundColor(@NotNull final JTree tree, final boolean checkProperty) {
    final Object property = tree.getClientProperty(WideSelectionTreeUI.TREE_TABLE_TREE_KEY);
    if (property instanceof JTable) {
      return ((JTable) property).getSelectionBackground();
    }
    boolean selection = tree.hasFocus();
    if (!selection && checkProperty) {
      selection = Boolean.TRUE.equals(property);
    }
    return selection ? UIUtil.getTreeSelectionBackground(true) : MTUI.Tree.getSelectionBackground();
  }

  private static Icon getTreeNodeIcon(final boolean expanded, final boolean selected, final boolean focused) {
    final boolean selectedFocused = selected && focused;

    final Icon selectedIcon = getTreeSelectedExpandedIcon();
    final Icon notSelectedIcon = getTreeExpandedIcon();

    final int width = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());
    final int height = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());

    //noinspection NestedConditionalExpression
    return new CenteredIcon(expanded ? (selectedFocused ? getTreeSelectedExpandedIcon() : getTreeExpandedIcon())
                                     : (selectedFocused ? getTreeSelectedCollapsedIcon() : getTreeCollapsedIcon()),
      width, height, false);
  }

  private static Icon getTreeCollapsedIcon() {
    if (treeCollapsedIcon == null) {
      final Icon icon = CONFIG.getArrowsStyle().getExpandIcon();
      treeCollapsedIcon = icon == null ? TRANSPARENT_ICON : icon;
    }
    return treeCollapsedIcon;
  }

  private static Icon getTreeExpandedIcon() {
    if (treeExpandedIcon == null) {
      final Icon icon = CONFIG.getArrowsStyle().getCollapseIcon();
      treeExpandedIcon = icon == null ? TRANSPARENT_ICON : icon;
    }
    return treeExpandedIcon;
  }

  private static Icon getTreeSelectedCollapsedIcon() {
    if (treeSelectedCollapsedIcon == null) {
      final Icon icon = CONFIG.getArrowsStyle().getSelectedExpandIcon();
      treeSelectedCollapsedIcon = icon == null ? TRANSPARENT_ICON : icon;
    }
    return treeSelectedCollapsedIcon;
  }

  private static Icon getTreeSelectedExpandedIcon() {
    if (treeSelectedExpandedIcon == null) {
      final Icon icon = CONFIG.getArrowsStyle().getSelectedCollapseIcon();
      treeSelectedExpandedIcon = icon == null ? TRANSPARENT_ICON : icon;
    }
    return treeSelectedExpandedIcon;
  }

  //region EDT Improvements methods
  @Override
  protected void setRootVisible(final boolean newValue) {
    if (treeModel instanceof AsyncTreeModel) {
      // this method must be called on EDT to be consistent with ATM,
      // because it modifies a list of visible nodes in the layout cache
      UIUtil.invokeLaterIfNeeded(() -> super.setRootVisible(newValue));
    } else {
      super.setRootVisible(newValue);
    }
  }

  @Override
  public Rectangle getPathBounds(final JTree tree, final TreePath path) {
    if (path == null || !isValid(tree)) {
      return null;
    }
    return super.getPathBounds(tree, path);
  }

  @Override
  public TreePath getPathForRow(final JTree tree, final int row) {
    if (!isValid(tree)) {
      return null;
    }
    return super.getPathForRow(tree, row);
  }

  @Override
  public int getRowForPath(final JTree tree, final TreePath path) {
    if (path == null || !isValid(tree)) {
      return -1;
    }
    return super.getRowForPath(tree, path);
  }

  @Override
  public int getRowCount(final JTree tree) {
    if (!isValid(tree)) {
      return 0;
    }
    return super.getRowCount(tree);
  }

  @Override
  public TreePath getClosestPathForLocation(final JTree tree, final int x, final int y) {
    if (!isValid(tree)) {
      return null;
    }
    return super.getClosestPathForLocation(tree, x, y);
  }

  @Override
  public boolean isEditing(final JTree tree) {
    if (!isValid(tree)) {
      return false;
    }
    return super.isEditing(tree);
  }

  @Override
  public boolean stopEditing(final JTree tree) {
    if (!isValid(tree)) {
      return false;
    }
    return super.stopEditing(tree);
  }

  @Override
  public void cancelEditing(final JTree tree) {
    if (!isValid(tree)) {
      return;
    }
    super.cancelEditing(tree);
  }

  @Override
  public void startEditingAtPath(final JTree tree, final TreePath path) {
    if (path == null || !isValid(tree)) {
      return;
    }
    super.startEditingAtPath(tree, path);
  }

  @Override
  public TreePath getEditingPath(final JTree tree) {
    if (!isValid(tree)) {
      return null;
    }
    return super.getEditingPath(tree);
  }
  //endregion

  @Override
  protected void installDefaults() {
    super.installDefaults();
    final JTree tree = getTree();
    if (tree != null) {
      LookAndFeel.installBorder(tree, "Tree.border");
      if (tree.isForegroundSet()) {
        tree.setForeground(null);
      }
      //      if (UIManager.get("Tree.showsRootHandles") == null) {
      //        LookAndFeel.installProperty(tree, JTree.SHOWS_ROOT_HANDLES_PROPERTY, Boolean.TRUE);
      //      }
    }
  }

  @Override
  protected AbstractLayoutCache.NodeDimensions createNodeDimensions() {
    return new AbstractLayoutCache.NodeDimensions() {
      @Override
      public Rectangle getNodeDimensions(final Object value,
                                         final int row,
                                         final int depth,
                                         final boolean expanded,
                                         final Rectangle bounds) {
        final JTree tree = getTree();
        if (tree == null) {
          return null;
        }

        final boolean leaf = isLeaf(value);
        Dimension size = null;

        // Get node dimensions
        if (editingComponent != null && editingRow == row) {
          size = editingComponent.getPreferredSize();
        } else {
          final boolean selected = tree.isRowSelected(row);
          final Component component = getRenderer(tree, value, selected, expanded, leaf, row, false);
          if (component != null) {
            component.validate();
            size = component.getPreferredSize();
          }
        }

        if (size == null) {
          return null;
        }

        // Compute indent
        final int x = getPainter(tree).getRendererOffset(control, depth + TreeUtil.getDepthOffset(tree), leaf);
        int height = getRowHeight();
        if (height <= 0) {
          height = size.height;
        }
        if (bounds == null) {
          return new Rectangle(x, 0, size.width, height);
        }

        bounds.x = x;
        bounds.y = 0;
        bounds.width = size.width;
        bounds.height = height;
        return bounds;
      }
    };
  }

  @Override
  protected MouseListener createMouseListener() {
    return new MouseEventAdapter<MouseListener>(super.createMouseListener()) {
      @Override
      public void mouseDragged(final MouseEvent event) {
        final Object property = UIUtil.getClientProperty(event.getSource(), "DnD Source"); // DnDManagerImpl.SOURCE_KEY
        if (property == null) {
          super.mouseDragged(event);
        }
      }

      @NotNull
      @Override
      protected MouseEvent convert(@NotNull final MouseEvent event) {
        final JTree tree = getTree();
        MouseEvent newEvent = event;

        if (tree != null && tree == event.getSource() && tree.isEnabled()) {
          if (!event.isConsumed() && SwingUtilities.isLeftMouseButton(event)) {
            int x = event.getX();
            final int y = event.getY();
            final TreePath path = getClosestPathForLocation(tree, x, y);
            if (path != null && !isLocationInExpandControl(path, x, y)) {
              final Rectangle bounds = getPathBounds(tree, path);
              if (bounds != null && bounds.y <= y && y <= (bounds.y + bounds.height)) {
                x = Math.max(bounds.x, Math.min(x, bounds.x + bounds.width - 1));
                if (x != event.getX()) {
                  newEvent = convert(event, tree, x, y);
                }
              }
            }
          }
        }
        return newEvent;
      }
    };
  }

  @Override
  protected void paintExpandControl(final Graphics g,
                                    final Rectangle clipBounds,
                                    final Insets insets,
                                    final Rectangle bounds,
                                    final TreePath path,
                                    final int row,
                                    final boolean isExpanded,
                                    final boolean hasBeenExpanded,
                                    final boolean isLeaf) {
    final boolean isPathSelected = tree.getSelectionModel().isPathSelected(path);
    if (!isLeaf(row)) {
      setExpandedIcon(getTreeNodeIcon(true, isPathSelected, tree.hasFocus()));
      setCollapsedIcon(getTreeNodeIcon(false, isPathSelected, tree.hasFocus()));
    }

    overridePaintExpandControl(g, bounds, path, isExpanded, hasBeenExpanded, isLeaf);
  }

  @Override
  protected void paintRow(final Graphics g,
                          final Rectangle clipBounds,
                          final Insets insets,
                          final Rectangle bounds,
                          final TreePath path,
                          final int row,
                          final boolean isExpanded,
                          final boolean hasBeenExpanded,
                          final boolean isLeaf) {
    final int containerWidth = tree.getParent() instanceof JViewport ? tree.getParent().getWidth() : tree.getWidth();
    final int xOffset = tree.getParent() instanceof JViewport ? ((JViewport) tree.getParent()).getViewPosition().x : 0;

    if (path != null) {
      final boolean selected = tree.isPathSelected(path);
      final Graphics2D rowGraphics = (Graphics2D) g.create();
      rowGraphics.setClip(clipBounds);

      if (selected) {
        final Color bg = getSelectionBackgroundColor(tree, true);
        rowGraphics.setColor(bg);
        rowGraphics.fillRect(xOffset, bounds.y, containerWidth, bounds.height);
      }

      if (selected) {
        if (tree.hasFocus()) {
          LIST_FOCUSED_PAINTER.paintBorder(tree, rowGraphics, xOffset, bounds.y, containerWidth, bounds.height);
        } else {
          LIST_PAINTER.paintBorder(tree, rowGraphics, xOffset, bounds.y, containerWidth, bounds.height);
        }
      }
      super.paintRow(rowGraphics, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
      rowGraphics.dispose();
    } else {
      super.paintRow(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
    }
  }

  @Nullable
  private JTree getTree() {
    return super.tree;
  }

  @Override
  protected boolean isLocationInExpandControl(final TreePath path, final int mouseX, final int mouseY) {
    final JTree tree = getTree();
    if (tree == null) {
      return false;
    }
    final Rectangle bounds = getPathBounds(tree, path);
    if (bounds == null) {
      return false;
    }
    bounds.x = getPainter(tree).getControlOffset(control, TreeUtil.getNodeDepth(tree, path), isLeaf(path.getLastPathComponent()));
    if (bounds.x < 0) {
      return false; // does not paint an icon to expand or collapse path
    }
    final Insets insets = tree.getInsets();
    bounds.x += insets.left;
    bounds.width = control.getWidth();
    final int height = 2 + control.getHeight();
    if (height < bounds.height) {
      bounds.y += (bounds.height - height) / 2;
      bounds.height = height;
    }
    return bounds.contains(mouseX, mouseY);
  }

  @Override
  protected int getRowX(final int row, final int depth) {
    final JTree tree = getTree();
    if (tree == null) {
      return 0;
    }
    final TreePath path = getPathForRow(tree, row);
    if (path == null) {
      return 0;
    }
    return getPainter(tree).getRendererOffset(control, TreeUtil.getNodeDepth(tree, path), isLeaf(path.getLastPathComponent()));
  }

  @NotNull
  private static Control.Painter getPainter(@NotNull final JTree tree) {
    Control.Painter painter = UIUtil.getClientProperty(tree, Control.Painter.KEY);
    if (painter != null) {
      return painter;
    }
    // painter is not specified for the given tree
    final Application application = getApplication();
    if (application != null) {
      painter = application.getUserData(Control.Painter.KEY);
      if (painter != null) {
        return painter;
      }
      // painter is not specified for the whole application
    }
    return new MTRowPainter();
  }

  @Nullable
  private Component getRenderer(final JTree tree,
                                final Object value,
                                final boolean selected,
                                final boolean expanded,
                                final boolean leaf,
                                final int row,
                                final boolean focused) {
    final TreeCellRenderer renderer = currentCellRenderer;
    if (renderer == null) {
      return null;
    }
    final Component component = renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, focused);
    if (component == null) {
      return null;
    }

    final CellRendererPane pane = rendererPane;
    if (pane != null && pane != component.getParent()) {
      pane.add(component);
    }

    return component;
  }

  @SuppressWarnings("MethodWithMoreThanThreeNegations")
  private void overridePaintExpandControl(final Graphics g,
                                          final Rectangle bounds,
                                          final TreePath path,
                                          final boolean isExpanded,
                                          final boolean hasBeenExpanded,
                                          final boolean isLeaf) {
    final Object value = path.getLastPathComponent();

    // Draw icons if not a leaf and either hasn't been loaded,
    // or the model child count is > 0.
    if (!isLeaf && (!hasBeenExpanded || treeModel.getChildCount(value) > 0)) {
      final int middleXOfKnob;
      middleXOfKnob = bounds.x - getRightChildIndent() + 1;
      final int middleYOfKnob = bounds.y + (bounds.height / 2);

      if (isExpanded) {
        final Icon icon = getExpandedIcon();
        if (icon != null) {
          drawCentered(tree, g, icon, middleXOfKnob, middleYOfKnob);
        }
      } else {
        final Icon icon = getCollapsedIcon();
        if (icon != null) {
          drawCentered(tree, g, icon, middleXOfKnob, middleYOfKnob);
        }
      }
    }
  }

  private boolean isValid(@Nullable final JTree tree) {
    if (!validator.isValidThread()) {
      return false;
    }
    return tree != null && tree == getTree();
  }

  private boolean isLeaf(@Nullable final Object value) {
    return value == null || super.treeModel.isLeaf(value);
  }

}
