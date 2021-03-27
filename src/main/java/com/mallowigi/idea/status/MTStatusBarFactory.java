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

package com.mallowigi.idea.status;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public final class MTStatusBarFactory implements StatusBarWidgetFactory {
  @NotNull
  @Override
  public String getId() {
    return "mtStatusBar";
  }

  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.statusbar");
  }

  @Override
  public boolean isAvailable(@NotNull final Project project) {
    return MTConfig.getInstance().isStatusBarTheme();
  }

  @NotNull
  @Override
  public StatusBarWidget createWidget(@NotNull final Project project) {
    return new MTStatusWidget();
  }

  @Override
  public void disposeWidget(@NotNull final StatusBarWidget widget) {

  }

  @Override
  public boolean canBeEnabledOn(@NotNull final StatusBar statusBar) {
    return true;
  }

  @Override
  public boolean isEnabledByDefault() {
    return true;
  }

  @Override
  public boolean isConfigurable() {
    return true;
  }
}
