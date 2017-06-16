/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaTreeUI;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.CenteredIcon;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.tree.*;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class MTTreeUI extends DarculaTreeUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(JComponent c) {
    return new MTTreeUI();
  }

  @Override
  protected void paintExpandControl(Graphics g,
                                    Rectangle clipBounds,
                                    Insets insets,
                                    Rectangle bounds,
                                    TreePath path,
                                    int row,
                                    boolean isExpanded,
                                    boolean hasBeenExpanded,
                                    boolean isLeaf) {
    boolean isPathSelected = tree.getSelectionModel().isPathSelected(path);
    if (!isLeaf(row)) {
      setExpandedIcon(getTreeNodeIcon(true, isPathSelected, tree.hasFocus()));
      setCollapsedIcon(getTreeNodeIcon(false, isPathSelected, tree.hasFocus()));
    }

    this.overridePaintExpandControl(g, bounds, path, isExpanded, hasBeenExpanded, isLeaf);
  }

  private void overridePaintExpandControl(Graphics g,
                                          Rectangle bounds, TreePath path,
                                          boolean isExpanded,
                                          boolean hasBeenExpanded,
                                          boolean isLeaf) {
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

  private Icon getTreeNodeIcon(boolean expanded, boolean selected, boolean focused) {
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
