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

package com.chrisrm.idea.config;

import com.chrisrm.idea.MTCustomThemeConfig;
import com.chrisrm.idea.config.ui.MTCustomThemeForm;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public final class MTCustomThemeConfigurable extends MTConfigurableBase<MTCustomThemeForm, MTCustomThemeConfig>
    implements SearchableConfigurable {

  private static final String ID = "com.chrisrm.idea.config.custom";
  public static final String HELP_ID = "MTCustomThemeConfig";

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.titles.customTheme");
  }

  @NonNls
  @NotNull
  @Override
  public String getHelpTopic() {
    return MTUiUtils.HELP_PREFIX + "." + HELP_ID;
  }

  @Override
  protected void setFormState(final MTCustomThemeForm form, @NotNull final MTCustomThemeConfig config) {
    form.setFormState(config);
  }

  @NotNull
  @Override
  protected MTCustomThemeConfig getConfig() {
    return MTCustomThemeConfig.getInstance();
  }

  @NotNull
  @Override
  protected MTCustomThemeForm createForm() {
    return new MTCustomThemeForm();
  }

  @Override
  protected void doApply(final MTCustomThemeForm form, final MTCustomThemeConfig config) {
    config.applySettings(form);
  }

  @Override
  protected boolean checkModified(final MTCustomThemeForm form, final MTCustomThemeConfig config) {
    return form.isModified(config);
  }
}
