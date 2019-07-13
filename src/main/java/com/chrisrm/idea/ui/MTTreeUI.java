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
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.CenteredIcon;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.ui.tree.WideSelectionTreeUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.tree.TreePath;
import java.awt.*;

@SuppressWarnings({"StaticVariableMayNotBeInitialized",
    "StaticVariableUsedBeforeInitialization",
    "NonThreadSafeLazyInitialization"})
public final class MTTreeUI extends WideSelectionTreeUI {
  private static final Border LIST_PAINTER = MTUI.List.getListSelectionPainter();
  private static final Border LIST_FOCUSED_PAINTER = MTUI.List.getListFocusedSelectionPainter();
  private static final MTConfig CONFIG = MTConfig.getInstance();
  private static final Icon TRANSPARENT_ICON = IconLoader.getTransparentIcon(AllIcons.Mac.Tree_white_down_arrow, 0);

  @Nullable
  private static Icon treeCollapsedIcon;
  @Nullable
  private static Icon treeExpandedIcon;
  @Nullable
  private static Icon treeSelectedCollapsedIcon;
  @Nullable
  private static Icon treeSelectedExpandedIcon;

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
    final Object property = tree.getClientProperty(TREE_TABLE_TREE_KEY);
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
}
