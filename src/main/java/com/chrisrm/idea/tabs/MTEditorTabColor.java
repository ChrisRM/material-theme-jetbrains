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

package com.chrisrm.idea.tabs;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public final class MTEditorTabColor implements EditorTabColorProvider {

  private static final Color COLOR_DARKER = new JBColor(new Color(33, 33, 33, 255), new Color(33, 33, 33, 255));
  private static final Color COLOR_DEFAULT = new JBColor(new Color(38, 50, 56, 255), new Color(38, 50, 56, 255));
  private static final Color COLOR_PALENIGHT = new JBColor(new Color(41, 45, 62, 255), new Color(41, 45, 62, 255));
  private static final Color COLOR_LIGHTER = new JBColor(new Color(250, 250, 250, 255), new Color(250, 250, 250, 255));

  @Nullable
  @Override
  public Color getEditorTabColor(@NotNull final Project project, @NotNull final VirtualFile virtualFile) {
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();

    switch (mtTheme) {
      case DARKER:
        return COLOR_DARKER;
      case PALENIGHT:
        return COLOR_PALENIGHT;
      case LIGHTER:
        return COLOR_LIGHTER;
      default:
        return COLOR_DEFAULT;
    }
  }
}
