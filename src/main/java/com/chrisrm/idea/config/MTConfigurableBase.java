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

package com.chrisrm.idea.config;

import com.chrisrm.idea.config.ui.MTFormUI;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by helio on 24/03/2017.
 */
public abstract class MTConfigurableBase<FORM extends MTFormUI, CONFIG extends PersistentStateComponent> extends BaseConfigurable {
  protected volatile FORM form;

  /**
   * Link a form to a config
   *
   * @param form   the form
   * @param config the config
   */
  protected abstract void setFormState(FORM form, CONFIG config);

  /**
   * Returns the config object for this setting
   */
  protected abstract CONFIG getConfig();

  /**
   * Create the Form UI for the settings
   */
  protected abstract FORM createForm();

  /**
   * Called when applying settings
   *
   * @param form   the form
   * @param config the config
   */
  protected abstract void doApply(FORM form, CONFIG config) throws ConfigurationException;

  /**
   * Checks whether the user has modified the settings
   *
   * @param form   the form
   * @param config the config
   * @return true if modified
   */
  protected abstract boolean checkModified(FORM form, CONFIG config);

  /**
   * Used to disable the apply button
   *
   * @return true if modified
   */
  @Override
  public final boolean isModified() {
    setModified(checkModified(getForm(), getConfig()));
    return super.isModified();
  }

  /**
   * Creates the component and link it to a config
   *
   * @return the created component
   */
  @Nullable
  @Override
  public JComponent createComponent() {
    initComponent();
    setFormState(getForm(), getConfig());
    return form.getContent();
  }

  /**
   * Apply settings
   *
   * @throws ConfigurationException
   */
  @Override
  public void apply() throws ConfigurationException {
    initComponent();
    doApply(getForm(), getConfig());
  }

  /**
   * Reset settings
   */
  @Override
  public void reset() {
    initComponent();
    setFormState(getForm(), getConfig());
  }

  @Override
  public synchronized void disposeUIResources() {
    dispose();
    if (form != null) {
      form.dispose();
    }
    form = null;
  }

  /**
   * Dispose resources
   */
  protected void dispose() {
  }

  /**
   * Return the created form
   */
  protected final FORM getForm() {
    return form;
  }

  /**
   * Creates the component with Swing
   */
  private synchronized void initComponent() {
    if (form == null) {
      form = UIUtil.invokeAndWaitIfNeeded(() -> {
        FORM form = createForm();
        form.init();
        return form;
      });
    }
  }
}
