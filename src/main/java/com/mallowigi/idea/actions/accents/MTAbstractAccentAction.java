/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.actions.accents;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.IconLoader;
import com.mallowigi.idea.MTAnalytics;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.UIReplacer;
import com.mallowigi.idea.actions.MTToggleAction;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.ui.indicators.MTSelectedTreeIndicatorImpl;
import com.mallowigi.idea.utils.MTAccents;
import org.jetbrains.annotations.NotNull;

public abstract class MTAbstractAccentAction extends MTToggleAction implements DumbAware {

  @Override
  public final void setSelected(@NotNull final AnActionEvent e, final boolean state) {
    MTSelectedTreeIndicatorImpl.resetCache();
    IconLoader.clearCache();

    final String accentColor = getAccent().getHexColor();
    MTConfig.getInstance().setAccentColor(accentColor);

    MTThemeManager.applyAccents(true);
    UIReplacer.patchUI();

    ActionToolbarImpl.updateAllToolbarsImmediately();
    MTAnalytics.getInstance().trackValue(MTAnalytics.ACCENT, accentColor);
    super.setSelected(e, state);
  }

  @SuppressWarnings("CallToSuspiciousStringMethod")
  @Override
  public final boolean isSelected(@NotNull final AnActionEvent e) {
    return MTConfig.getInstance().getAccentColor().equals(getAccent().getHexColor());
  }

  @Override
  public final void update(@NotNull final AnActionEvent e) {
    e.getPresentation().setEnabled(!MTConfig.getInstance().isOverrideAccentColor());
  }

  /**
   * The Accent Color String
   */
  protected abstract MTAccents getAccent();

  @Override
  protected final void checkLicense(final @NotNull AnActionEvent e) {
    e.getPresentation().setEnabled(true);
  }
}
