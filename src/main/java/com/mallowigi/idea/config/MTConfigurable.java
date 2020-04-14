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

package com.mallowigi.idea.config;

import com.intellij.openapi.options.SearchableConfigurable;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.config.ui.MTForm;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Service used to load and save settings from MTConfig
 */
public final class MTConfigurable extends MTConfigurableBase<MTForm, MTConfig> implements SearchableConfigurable {

  public static final String ID = "com.mallowigi.idea.config";
  public static final String HELP_ID = "MTConfig";

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.titles.materialTheme");
  }

  @NonNls
  @NotNull
  @Override
  public String getHelpTopic() {
    return MTUiUtils.HELP_PREFIX + "." + HELP_ID;
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Override
  protected void setFormState(final MTForm form, @NotNull final MTConfig config) {
    Objects.requireNonNull(getForm()).setFormState(config);
  }

  @NotNull
  @Override
  protected MTConfig getConfig() {
    return MTConfig.getInstance();
  }

  @NotNull
  @Override
  protected MTForm createForm() {
    return new MTForm();
  }

  @Override
  protected void doApply(final MTForm form, final MTConfig config) {
    Objects.requireNonNull(config).applySettings(form);
  }

  @Override
  protected boolean checkModified(final MTForm form, final MTConfig config) {
    return checkFormModified(config);
  }

  /**
   * Checks whether the form is modified by comparing to the config
   *
   * @param config the config
   * @return true if changed
   */
  private boolean checkFormModified(final MTBaseConfig<MTForm, MTConfig> config) {
    return Objects.requireNonNull(getForm()).isModified(config);
  }
}
