/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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
import com.chrisrm.idea.icons.tinted.TintedIconsService;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.ui.ColorUtil;
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

public final class MTTreeUI extends WideSelectionTreeUI {
  private static final Border LIST_SELECTION_BACKGROUND_PAINTER = UIManager.getBorder("List.sourceListSelectionBackgroundPainter");
  private static final Border LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER = UIManager.getBorder("List" +
      ".sourceListFocusedSelectionBackgroundPainter");

  @NotNull
  private final Condition<Integer> myWideSelectionCondition;
  private final boolean myWideSelection;

  public static Icon treeCollapsedIcon;
  public static Icon treeExpandedIcon;
  public static Icon treeSelectedCollapsedIcon;
  public static Icon treeSelectedExpandedIcon;

  public MTTreeUI() {
    this(true, Conditions.alwaysTrue());
  }

  public MTTreeUI(final boolean wideSelection, @NotNull final Condition<Integer> wideSelectionCondition) {
    myWideSelection = wideSelection;
    myWideSelectionCondition = wideSelectionCondition;
  }

  public static ComponentUI createUI(final JComponent c) {
    return new MTTreeUI();
  }

  public static void resetIcons() {
    treeCollapsedIcon = null;
    treeExpandedIcon = null;
    treeSelectedCollapsedIcon = null;
    treeSelectedExpandedIcon = null;
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

    if (path != null && myWideSelection) {
      final boolean selected = tree.isPathSelected(path);
      final Graphics2D rowGraphics = (Graphics2D) g.create();
      rowGraphics.setClip(clipBounds);

      final Object sourceList = tree.getClientProperty(SOURCE_LIST_CLIENT_PROPERTY);
      Color background = tree.getBackground();

      if ((row % 2) == 0 && Boolean.TRUE.equals(tree.getClientProperty(STRIPED_CLIENT_PROPERTY))) {
        background = UIUtil.getDecoratedRowColor();
      }

      if (sourceList != null && (Boolean) sourceList) {
        if (selected) {
          if (tree.hasFocus()) {
            LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER.paintBorder(tree, rowGraphics, xOffset, bounds.y, containerWidth, bounds.height);
          } else {
            LIST_SELECTION_BACKGROUND_PAINTER.paintBorder(tree, rowGraphics, xOffset, bounds.y, containerWidth, bounds.height);
          }
        } else if (myWideSelectionCondition.value(row)) {
          rowGraphics.setColor(background);
          rowGraphics.fillRect(xOffset, bounds.y, containerWidth, bounds.height);
        }
      } else {
        if (selected && (UIUtil.isUnderAquaBasedLookAndFeel() || UIUtil.isUnderDarcula() || UIUtil.isUnderIntelliJLaF())) {
          final Color bg = MTTreeUI.getSelectionBackgroundColor(tree, true);
          final int thickness = MTConfig.getInstance().getHighlightThickness();

          if (myWideSelectionCondition.value(row)) {
            rowGraphics.setColor(bg);
            rowGraphics.fillRect(xOffset + thickness, bounds.y, containerWidth, bounds.height);
          }
        }
      }

      if (shouldPaintExpandControl(path, row, isExpanded, hasBeenExpanded, isLeaf)) {
        paintExpandControl(rowGraphics, bounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
      }

      super.paintRow(rowGraphics, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
      rowGraphics.dispose();
    } else {
      super.paintRow(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
    }
  }

  @Override
  protected void paintSelectedRows(final Graphics g, final JTree tr) {
    final Rectangle rect = tr.getVisibleRect();
    final int firstVisibleRow = tr.getClosestRowForLocation(rect.x, rect.y);
    final int lastVisibleRow = tr.getClosestRowForLocation(rect.x, rect.y + rect.height);

    for (int row = firstVisibleRow; row <= lastVisibleRow; row++) {
      if (tr.getSelectionModel().isRowSelected(row)) {
        final Rectangle bounds = tr.getRowBounds(row);
        final Color color = getSelectionBackgroundColor(tr, false);
        if (color != null) {
          g.setColor(color);
          g.fillRect(0, bounds.y, tr.getWidth(), bounds.height);
        }
      }
    }
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
    if (selection) {
      return UIUtil.getTreeSelectionBackground(true);
    } else {
      return ColorUtil.withAlpha(UIManager.getColor("Tree.selectionBackground"), 0.25);
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

  private void overridePaintExpandControl(final Graphics g,
                                          final Rectangle bounds, final TreePath path,
                                          final boolean isExpanded,
                                          final boolean hasBeenExpanded,
                                          final boolean isLeaf) {
    final Object value = path.getLastPathComponent();

    // Draw icons if not a leaf and either hasn't been loaded,
    // or the model child count is > 0.
    if (!isLeaf && (!hasBeenExpanded ||
        treeModel.getChildCount(value) > 0)) {
      final int middleXOfKnob;
      middleXOfKnob = bounds.x - getRightChildIndent() + 1;
      final int middleYOfKnob = bounds.y + (bounds.height / 2);

      if (isExpanded) {
        final Icon expandedIcon = getExpandedIcon();
        if (expandedIcon != null) {
          drawCentered(tree, g, expandedIcon, middleXOfKnob, middleYOfKnob);
        }
      } else {
        final Icon collapsedIcon = getCollapsedIcon();
        if (collapsedIcon != null) {
          drawCentered(tree, g, collapsedIcon, middleXOfKnob, middleYOfKnob);
        }
      }
    }
  }

  private Icon getTreeNodeIcon(final boolean expanded, final boolean selected, final boolean focused) {
    final boolean white = selected && focused;

    final Icon selectedIcon = getTreeSelectedExpandedIcon();
    final Icon notSelectedIcon = getTreeExpandedIcon();

    final int width = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());
    final int height = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());

    return new CenteredIcon(expanded ? (white ? getTreeSelectedExpandedIcon() : getTreeExpandedIcon())
                                     : (white ? getTreeSelectedCollapsedIcon() : getTreeCollapsedIcon()),
        width, height, false);
  }

  private String getAccentColor() {
    return MTConfig.getInstance().getAccentColor();
  }

  private Icon getTreeCollapsedIcon() {
    if (treeCollapsedIcon == null) {
      final String newPath = MTConfig.getInstance().getArrowsStyle().getCollapsedIcon();
      treeCollapsedIcon = TintedIconsService.getIcon(newPath + ".png", getAccentColor());
    }
    return treeCollapsedIcon;
  }

  private Icon getTreeExpandedIcon() {
    if (treeExpandedIcon == null) {
      final String newPath = MTConfig.getInstance().getArrowsStyle().getExpandedIcon();
      treeExpandedIcon = TintedIconsService.getIcon(newPath + ".png", getAccentColor());
    }
    return treeExpandedIcon;
  }

  private Icon getTreeSelectedCollapsedIcon() {
    if (treeSelectedCollapsedIcon == null) {
      final String newPath = MTConfig.getInstance().getArrowsStyle().getCollapsedIcon();
      treeSelectedCollapsedIcon = TintedIconsService.getIcon(newPath + "_selected.png", getAccentColor());
    }
    return treeSelectedCollapsedIcon;
  }

  private Icon getTreeSelectedExpandedIcon() {
    if (treeSelectedExpandedIcon == null) {
      final String newPath = MTConfig.getInstance().getArrowsStyle().getExpandedIcon();
      treeSelectedExpandedIcon = TintedIconsService.getIcon(newPath + "_selected.png", getAccentColor());
    }
    return treeSelectedExpandedIcon;
  }
}
