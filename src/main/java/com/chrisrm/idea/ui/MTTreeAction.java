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

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.ui.TreeActions;
import com.intellij.util.ui.tree.TreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.swing.KeyStroke.getKeyStroke;

abstract class MTTreeAction extends AbstractAction implements UIResource {
  private static final List<MTTreeAction> ACTIONS = asList(
    new MTTreeAction(TreeActions.Home.ID, getKeyStroke(KeyEvent.VK_HOME, 0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        scrollAndSetSelection(tree, 0);
      }
    },
    new MTTreeAction(TreeActions.End.ID, getKeyStroke(KeyEvent.VK_END, 0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        scrollAndSetSelection(tree, tree.getRowCount() - 1);
      }
    },
    new MTTreeAction(TreeActions.Up.ID, getKeyStroke(KeyEvent.VK_UP, 0), getKeyStroke(KeyEvent.VK_KP_UP, 0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        int row = tree.getRowForPath(path);
        if (path == null || row < 0) {
          scrollAndSetSelection(tree, 0);
        } else {
          if (row == 0 && isCycleScrollingAllowed()) {
            row = tree.getRowCount();
          }
          row--; // NB!: decrease row after checking for cycle scrolling
          scrollAndSetSelection(tree, row);
        }
      }
    },
    new MTTreeAction(TreeActions.Down.ID, getKeyStroke(KeyEvent.VK_DOWN, 0), getKeyStroke(KeyEvent.VK_KP_DOWN, 0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        int row = tree.getRowForPath(path);
        if (path == null || row < 0) {
          scrollAndSetSelection(tree, 0);
        } else {
          row++; // NB!: increase row before checking for cycle scrolling
          if (isCycleScrollingAllowed() && row == tree.getRowCount()) {
            row = 0;
          }
          scrollAndSetSelection(tree, row);
        }
      }
    },
    new MTTreeAction(TreeActions.Left.ID, getKeyStroke(KeyEvent.VK_LEFT, 0), getKeyStroke(KeyEvent.VK_KP_LEFT, 0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        final int row = tree.getRowForPath(path);
        if (path == null || row < 0) {
          scrollAndSetSelection(tree, 0);
        } else if (tree.isExpanded(path)) {
          tree.collapsePath(path);
        } else {
          final TreePath parent = path.getParentPath();
          if (parent != null) {
            if (tree.isRootVisible() || null != parent.getParentPath()) {
              scrollAndSetSelection(tree, parent);
            } else if (row > 0) {
              scrollAndSetSelection(tree, row - 1);
            }
          }
        }
      }
    },
    new MTTreeAction(TreeActions.Right.ID, getKeyStroke(KeyEvent.VK_RIGHT, 0), getKeyStroke(KeyEvent.VK_KP_RIGHT,
      0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        final int row = tree.getRowForPath(path);
        if (path == null || row < 0) {
          scrollAndSetSelection(tree, 0);
        } else if (tree.isExpanded(path) || tree.getModel().isLeaf(path.getLastPathComponent())) {
          scrollAndSetSelection(tree, row + 1);
        } else {
          tree.expandPath(path);
        }
      }
    },
    new MTTreeAction(TreeActions.PageUp.ID, getKeyStroke(KeyEvent.VK_PAGE_UP, 0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        final Rectangle bounds = tree.getPathBounds(path);
        if (path == null || bounds == null) {
          scrollAndSetSelection(tree, 0);
        } else {
          final int height = Math.max(tree.getVisibleRect().height - bounds.height, 1);
          final TreePath next = tree.getClosestPathForLocation(bounds.x, bounds.y - height);
          if (next != null && !next.equals(path)) {
            scrollAndSetSelection(tree, next);
          }
        }
      }
    },
    new MTTreeAction(TreeActions.PageDown.ID, getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0)) {
      @Override
      void actionPerformed(@NotNull final JTree tree, @Nullable final TreePath path) {
        final Rectangle bounds = tree.getPathBounds(path);
        if (path == null || bounds == null) {
          scrollAndSetSelection(tree, tree.getRowCount() - 1);
        } else {
          final int height = Math.max(tree.getVisibleRect().height - bounds.height, 1);
          final TreePath next = tree.getClosestPathForLocation(bounds.x, bounds.y + bounds.height + height);
          if (next != null && !next.equals(path)) {
            scrollAndSetSelection(tree, next);
          }
        }
      }
    }
  );
  private final String name;
  private final List<KeyStroke> keys;

  MTTreeAction(@NotNull final String name, @NotNull final KeyStroke... keys) {
    this.name = name;
    this.keys = asList(keys);
  }

  abstract void actionPerformed(@NotNull JTree tree, @Nullable TreePath path);

  @Override
  public final void actionPerformed(final ActionEvent event) {
    final Object source = event.getSource();
    if (source instanceof JTree) {
      final JTree tree = (JTree) source;
      actionPerformed(tree, tree.getLeadSelectionPath());
    }
  }

  static void installTo(@NotNull final ActionMap map) {
    final Object[] keys = map.keys();
    if (keys != null && keys.length != 0) {
      return; // actions are already installed
    }
    for (final MTTreeAction action : ACTIONS) {
      map.put(action.name, action);
    }
  }

  static void installTo(@NotNull final InputMap map) {
    final Object[] keys = map.keys();
    if (keys != null && keys.length != 0) {
      return; // keys for actions are already installed
    }
    for (final MTTreeAction action : ACTIONS) {
      for (final KeyStroke key : action.keys) {
        map.put(key, action.name);
      }
    }
  }

  static void scrollAndSetSelection(@NotNull final JTree tree, final int row) {
    scrollAndSetSelection(tree, tree.getPathForRow(row));
  }

  static void scrollAndSetSelection(@NotNull final JTree tree, @Nullable final TreePath path) {
    if (path != null && TreeUtil.scrollToVisible(tree, path, false)) {
      tree.setSelectionPath(path);
    }
  }

  static boolean isCycleScrollingAllowed() {
    if (!Registry.is("ide.tree.ui.cyclic.scrolling.allowed")) {
      return false;
    }
    final UISettings settings = UISettings.getInstanceOrNull();
    return settings != null && settings.getCycleScrolling();
  }
}

