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
package com.chrisrm.idea.actions.themes;

import com.chrisrm.idea.MTBundledThemesManager;
import com.chrisrm.idea.MTThemeManager;
import com.chrisrm.idea.MTThemes;
import com.chrisrm.idea.themes.MTThemeable;
import com.chrisrm.idea.themes.models.MTBundledTheme;
import com.intellij.ide.actions.QuickSwitchSchemeAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author max
 */
public class MTQuickChangeThemeAction extends QuickSwitchSchemeAction {
  @Override
  protected void fillActions(final Project project, @NotNull final DefaultActionGroup group, @NotNull final DataContext dataContext) {
    final MTBundledThemesManager manager = MTBundledThemesManager.getInstance();
    final List<MTBundledTheme> bundledThemes = manager.getBundledThemes();
    final MTThemeable current = manager.getActiveTheme();
    for (final MTBundledTheme bundledTheme : bundledThemes) {
      addBundledTheme(group, bundledTheme, current);
    }
  }

  private static void addBundledTheme(final DefaultActionGroup group,
                                      final MTThemeable theme,
                                      final MTThemeable current) {
    group.add(new DumbAwareAction(theme.getName(), theme.getEditorColorsScheme(),
        theme == current ? ourCurrentAction : ourNotCurrentAction) {
      @Override
      public void actionPerformed(final AnActionEvent e) {
        if (MTThemes.getThemeFor(theme.getId()) == null) {
          MTThemes.addTheme(MTThemes.fromTheme(theme));
        }

        MTThemeManager.getInstance().activate(MTThemes.getThemeFor(theme.getId()));
      }
    });
  }

  @Override
  protected boolean isEnabled() {
    return MTBundledThemesManager.getInstance().getBundledThemes().size() >= 1;
  }
}
