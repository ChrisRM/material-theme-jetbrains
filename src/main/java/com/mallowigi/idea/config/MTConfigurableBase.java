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

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.config.ui.MTFormUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

@SuppressWarnings({"SynchronizedMethod",
  "rawtypes"})
public abstract class MTConfigurableBase<F extends MTFormUI, C extends PersistentStateComponent> extends BaseConfigurable {
  @Nullable
  private volatile F form;

  /**
   * Link a form to a config
   *
   * @param form   the form
   * @param config the config
   */
  protected abstract void setFormState(F form, @NotNull C config);

  /**
   * Returns the config object for this setting
   */
  @NotNull
  protected abstract C getConfig();

  /**
   * Create the Form UI for the settings
   */
  @NotNull
  protected abstract F createForm();

  /**
   * Called when applying settings
   *
   * @param form   the form
   * @param config the config
   */
  protected abstract void doApply(F form, @Nullable C config) throws ConfigurationException;

  /**
   * Checks whether the user has modified the settings
   *
   * @param form   the form
   * @param config the config
   * @return true if modified
   */
  protected abstract boolean checkModified(F form, @Nullable C config);

  /**
   * Used to disable the apply button
   *
   * @return true if modified
   */
  @Override
  public final boolean isModified() {
    setModified(checkModified(form, getConfig()));
    return super.isModified();
  }

  /**
   * Creates the component and link it to a config
   *
   * @return the created component
   */
  @Nullable
  @Override
  public final JComponent createComponent() {
    initComponent();
    setFormState(form, getConfig());
    return Objects.requireNonNull(form).getContent();
  }

  /**
   * Apply settings
   */
  @Override
  public final void apply() throws ConfigurationException {
    initComponent();
    doApply(form, getConfig());
  }

  /**
   * Reset settings
   */
  @Override
  public final void reset() {
    initComponent();
    setFormState(form, getConfig());
  }

  /**
   * Dispose the F on dispose
   */
  @Override
  public final synchronized void disposeUIResources() {
    dispose();
    if (form != null) {
      Objects.requireNonNull(form).dispose();
    }
    form = null;
  }

  /**
   * Dispose resources
   */
  @SuppressWarnings("NoopMethodInAbstractClass")
  private void dispose() {
  }

  /**
   * Return the created form
   */
  @Nullable
  protected final F getForm() {
    return form;
  }

  /**
   * Creates the component with Swing
   */
  private synchronized void initComponent() {
    if (form == null) {
      form = UIUtil.invokeAndWaitIfNeeded(() -> {
        final F myForm = createForm();
        myForm.init();
        return myForm;
      });
    }
  }
}
