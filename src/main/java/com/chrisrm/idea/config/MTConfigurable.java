package com.chrisrm.idea.config;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ui.MTForm;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by helio on 24/03/2017.
 */
public class MTConfigurable extends MTConfigurableBase<MTForm, MTConfig> implements SearchableConfigurable {
  @Nls
  @Override
  public String getDisplayName() {
    return "Material Theme";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }


  @Override
  protected MTConfig getConfig() {
    return MTConfig.getInstance();
  }

  @Override
  protected MTForm createForm() {
    return new MTForm();
  }

  @Override
  protected void setFormState(MTForm mtForm, MTConfig mtConfig) {
    form.setHighlightColor(mtConfig.getHighlightColor());
    form.setHighlightColorEnabled(mtConfig.isHighlightColorEnabled());
    form.setHighlightThickness(mtConfig.getHighlightThickness());
    form.afterStateSet();
  }

  @Override
  protected void doApply(MTForm mtForm, MTConfig mtConfig) throws ConfigurationException {
    mtConfig.setHighlightColor(form.getHighlightColor());
    mtConfig.setHighlightColorEnabled(form.getHighlightColorEnabled());
    mtConfig.setHighlightThickness(form.getHighlightThickness());
    mtConfig.fireChanged();
  }

  @Override
  protected boolean checkModified(MTForm mtForm, MTConfig mtConfig) {
    boolean modified = mtConfig.isHighlightColorChanged(form.getHighlightColor());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(form.getHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(form.getHighlightThickness());
    return modified;
  }

  @NotNull
  @Override
  public String getId() {
    return "com.chrisrm.idea.config";
  }

}
