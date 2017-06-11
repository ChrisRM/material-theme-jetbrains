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

    super.paintExpandControl(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
  }

  public Icon getTreeNodeIcon(boolean expanded, boolean selected, boolean focused) {
    boolean white = selected && focused;

    Icon selectedIcon = getTreeSelectedExpandedIcon();
    Icon notSelectedIcon = getTreeExpandedIcon();

    int width = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());
    int height = Math.max(selectedIcon.getIconWidth(), notSelectedIcon.getIconWidth());

    return new CenteredIcon(expanded ? (white ? getTreeSelectedExpandedIcon() : getTreeExpandedIcon())
                                     : (white ? getTreeSelectedCollapsedIcon() : getTreeCollapsedIcon()),
                            width, height, false);
  }

  public Icon getTreeCollapsedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_right_arrow.png");
  }

  public Icon getTreeExpandedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_down_arrow.png");
  }

  public Icon getTreeSelectedCollapsedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_right_arrow_selected.png");
  }

  public Icon getTreeSelectedExpandedIcon() {
    return IconLoader.findIcon("/icons/mac/tree_white_down_arrow_selected.png");
  }
}
