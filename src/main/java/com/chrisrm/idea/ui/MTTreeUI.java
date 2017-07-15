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

import com.intellij.ide.ui.laf.darcula.ui.DarculaTreeUI;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.CenteredIcon;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTTreeUI extends DarculaTreeUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(final JComponent c) {
    return new MTTreeUI();
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
    boolean isPathSelected = tree.getSelectionModel().isPathSelected(path);
    if (!isLeaf(row)) {
      setExpandedIcon(getTreeNodeIcon(true, isPathSelected, tree.hasFocus()));
      setCollapsedIcon(getTreeNodeIcon(false, isPathSelected, tree.hasFocus()));
    }

    this.overridePaintExpandControl(g, bounds, path, isExpanded, hasBeenExpanded, isLeaf);
  }

  private void overridePaintExpandControl(final Graphics g,
                                          final Rectangle bounds, final TreePath path,
                                          final boolean isExpanded,
                                          final boolean hasBeenExpanded,
                                          final boolean isLeaf) {
    Object value = path.getLastPathComponent();

    // Draw icons if not a leaf and either hasn't been loaded,
    // or the model child count is > 0.
    if (!isLeaf && (!hasBeenExpanded ||
        treeModel.getChildCount(value) > 0)) {
      int middleXOfKnob;
      middleXOfKnob = bounds.x - getRightChildIndent() + 1;
      int middleYOfKnob = bounds.y + (bounds.height / 2);

      if (isExpanded) {
        Icon expandedIcon = getExpandedIcon();
        if (expandedIcon != null) {
          drawCentered(tree, g, expandedIcon, middleXOfKnob,
              middleYOfKnob);
        }
      } else {
        Icon collapsedIcon = getCollapsedIcon();
        if (collapsedIcon != null) {
          drawCentered(tree, g, collapsedIcon, middleXOfKnob,
              middleYOfKnob);
        }
      }
    }
  }

  private Icon getTreeNodeIcon(final boolean expanded, final boolean selected, final boolean focused) {
    boolean white = selected && focused;

    Icon selectedIcon = getTreeSelectedExpandedIcon();
    Icon notSelectedIcon = getTreeExpandedIcon();

    int width = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());
    int height = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());

    return new CenteredIcon(expanded ? (white ? getTreeSelectedExpandedIcon() : getTreeExpandedIcon())
                                     : (white ? getTreeSelectedCollapsedIcon() : getTreeCollapsedIcon()),
        width, height, false);
  }

  private Icon getTreeCollapsedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_right_arrow.png");
  }

  private Icon getTreeExpandedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_down_arrow.png");
  }

  private Icon getTreeSelectedCollapsedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_right_arrow_selected.png");
  }

  private Icon getTreeSelectedExpandedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_down_arrow_selected.png");
  }
}
