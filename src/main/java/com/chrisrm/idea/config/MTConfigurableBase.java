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
