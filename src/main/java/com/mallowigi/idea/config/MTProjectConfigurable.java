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

package com.mallowigi.idea.config;

import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.mallowigi.idea.MTProjectConfig;
import com.mallowigi.idea.config.ui.MTProjectForm;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Service used to load and save settings from MTProjectConfig
 */
public final class MTProjectConfigurable extends MTConfigurableBase<MTProjectForm, MTProjectConfig> implements SearchableConfigurable {

  public static final String ID = "MTProjectConfigurable";
  @NonNls
  public static final String HELP_ID = "MTProjectConfig";
  private final Project project;

  MTProjectConfigurable(final Project project) {
    this.project = project;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.titles.project.materialTheme");
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
  protected void setFormState(final MTProjectForm form, @NotNull final MTProjectConfig config) {
    Objects.requireNonNull(getForm()).setFormState(config);
  }

  @NotNull
  @Override
  protected MTProjectConfig getConfig() {
    return MTProjectConfig.getInstance(project);
  }

  @NotNull
  @Override
  protected MTProjectForm createForm() {
    return new MTProjectForm(project);
  }

  @Override
  protected void doApply(final MTProjectForm form, final MTProjectConfig config) {
    Objects.requireNonNull(config).applySettings(form);
  }

  @Override
  protected boolean checkModified(final MTProjectForm form, final MTProjectConfig config) {
    return checkFormModified(config);
  }

  /**
   * Checks whether the form is modified by comparing to the config
   *
   * @param config the config
   * @return true if changed
   */
  private boolean checkFormModified(final MTBaseConfig<MTProjectForm, MTProjectConfig> config) {
    return Objects.requireNonNull(getForm()).isModified(config);
  }
}
