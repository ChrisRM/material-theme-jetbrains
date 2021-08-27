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
package com.mallowigi.idea.actions.themes;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.QuickSwitchSchemeAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.mallowigi.idea.MTBundledThemesManager;
import org.jetbrains.annotations.NotNull;

/**
 * @author max
 */
public final class MTQuickChangeThemeAction extends QuickSwitchSchemeAction {

  private final MTBundledThemesManager manager = MTBundledThemesManager.getInstance();

  /**
   * Add external themes in the list
   *
   * @param project     current project
   * @param group       Quick switch group
   * @param dataContext data context
   */
  @Override
  protected void fillActions(final Project project, @NotNull final DefaultActionGroup group, @NotNull final DataContext dataContext) {
    manager.addBundledThemes(group, AllIcons.Actions.Forward, ourNotCurrentAction);
  }

  @Override
  protected boolean isEnabled() {
    return manager.getBundledThemes().size() >= 1;
  }
}
