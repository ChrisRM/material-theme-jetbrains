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

package com.chrisrm.idea.icons.patchers;

import com.intellij.openapi.util.IconPathPatcher;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class CheckStyleIconPatcher extends IconPathPatcher {
  @NonNls
  private static final Map<String, String> REPLACEMENTS = new HashMap<>();

  static {
    REPLACEMENTS.put("/general/autoscrollToSource.png", "AllIcons.General.AutoscrollToSource");
    REPLACEMENTS.put("/actions/expandall.png", "AllIcons.Actions.Expandall");
    REPLACEMENTS.put("/actions/collapseall.png", "AllIcons.Actions.Collapseall");
    REPLACEMENTS.put("/actions/cancel.png", "AllIcons.Actions.Cancel");
    REPLACEMENTS.put("/actions/suspend.png", "AllIcons.Actions.Suspend");
    REPLACEMENTS.put("/actions/execute.png", "AllIcons.Actions.Execute");
    REPLACEMENTS.put("/modules/modulesNode.png", "AllIcons.Nodes.ModulesGroup");
    REPLACEMENTS.put("/general/projectTab.png", "AllIcons.General.ProjectTab");
    REPLACEMENTS.put("/general/toolWindowChanges.png", "AllIcons.Toolwindows.ToolWindowChanges");
    REPLACEMENTS.put("/general/smallConfigurableVcs.png", "AllIcons.Actions.ShowAsTree");

  }

  @Override
  @Nullable
  public String patchPath(@NotNull final String path, final ClassLoader classLoader) {
    return REPLACEMENTS.get(path);
  }
}
