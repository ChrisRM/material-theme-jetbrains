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

package com.chrisrm.idea.icons.patchers;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.util.IconPathPatcher;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ThemedTintedIconsPatcher extends IconPathPatcher {
  private static final Map<String, String> CACHE = new HashMap<>();
  @NonNls
  private static final Map<String, String> REPLACEMENTS = new HashMap<>();

  static {
    replaceSVGs();
  }

  private MTConfig instance;

  private static synchronized void replaceSVGs() {
    REPLACEMENTS.put("/nodes/folder", "MTIcons.Nodes2.Folder");
    REPLACEMENTS.put("/nodes/TreeClosed", "MTIcons.Nodes.TreeClosed");
    REPLACEMENTS.put("/nodes/folderClosed", "MTIcons.Nodes.FolderClosed");
    REPLACEMENTS.put("/nodes/folderOpen", "MTIcons.Nodes.FolderOpen");

    REPLACEMENTS.put("/mac/tree_white_down_arrow", "MTIcons.Arrows.MaterialDown");
    REPLACEMENTS.put("/mac/tree_white_right_arrow", "MTIcons.Arrows.MaterialRight");
    REPLACEMENTS.put("/mac/darcula/tree_white_down_arrow", "MTIcons.Arrows.DarculaDown");
    REPLACEMENTS.put("/mac/darcula/tree_white_right_arrow", "MTIcons.Arrows.DarculaRight");
    REPLACEMENTS.put("/mac/plusminus/plus", "MTIcons.Arrows.Plus");
    REPLACEMENTS.put("/mac/plusminus/minus", "MTIcons.Arrows.Minus");

    REPLACEMENTS.put("/icons/plugins/datagrip/objectGroup", "MTIcons.DataGrip.ObjectGroup");
    REPLACEMENTS.put("/icons/plugins/datagrip/table", "MTIcons.DataGrip.Table");
  }

  public static void clearCache() {
    CACHE.clear();
  }

  @Nullable
  @Override
  public String patchPath(final String path, final ClassLoader classLoader) {
    if (getInstance() == null || !getInstance().isUseMaterialIcons()) {
      return null;
    }
    final String vPath = path.replace(".svg", "").replace(".png", "");

    if (CACHE.containsKey(vPath)) {
      return CACHE.get(vPath);
    }
    if (REPLACEMENTS.get(vPath) != null) {
      CACHE.put(vPath, REPLACEMENTS.get(vPath));
      return CACHE.get(vPath);
    }
    return null;
  }

  @Nullable
  @Override
  public ClassLoader getContextClassLoader(final String path, final ClassLoader originalClassLoader) {
    return getClass().getClassLoader();
  }

  public MTConfig getInstance() {
    if (instance == null) {
      instance = MTConfig.getInstance();
    }
    return instance;
  }
}
