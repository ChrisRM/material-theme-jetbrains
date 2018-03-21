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

package com.chrisrm.idea.config;

import com.chrisrm.idea.MTCustomThemeConfig;
import com.chrisrm.idea.config.ui.MTCustomThemeForm;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import static com.chrisrm.idea.utils.MTUiUtils.HELP_PREFIX;

public final class MTCustomThemeConfigurable extends MTConfigurableBase<MTCustomThemeForm, MTCustomThemeConfig>
    implements SearchableConfigurable {

  public static final String ID = "com.chrisrm.idea.config.custom";
  public static final String HELP_ID = "MTCustomThemeConfig";

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.customTheme");
  }

  @NotNull
  @Override
  public String getHelpTopic() {
    return HELP_PREFIX + "." + HELP_ID;
  }

  @Override
  protected void setFormState(final MTCustomThemeForm mtForm, final MTCustomThemeConfig mtConfig) {
    mtForm.setBackgroundColor(mtConfig.getBackgroundColor());
    mtForm.setForegroundColor(mtConfig.getForegroundColor());
    mtForm.setTextColor(mtConfig.getTextColor());
    mtForm.setContrastColor(mtConfig.getContrastColor());
    mtForm.setDisabledColor(mtConfig.getDisabledColor());
    mtForm.setButtonColor(mtConfig.getButtonColor());
    mtForm.setSecondaryBackgroundColor(mtConfig.getSecondaryBackgroundColor());
    mtForm.setSecondBorderColor(mtConfig.getSecondBorderColor());
    mtForm.setSelectionBackgroundColor(mtConfig.getSelectionBackgroundColor());
    mtForm.setSelectionForegroundColor(mtConfig.getSelectionForegroundColor());
    mtForm.setTableSelectedColor(mtConfig.getTableSelectedColor());
    mtForm.setTreeSelectionColor(mtConfig.getTreeSelectionColor());
    mtForm.setHighlightColor(mtConfig.getHighlightColor());
    mtForm.setNotificationsColor(mtConfig.getNotificationsColor());

    mtForm.afterStateSet();
  }

  @Override
  protected MTCustomThemeConfig getConfig() {
    return MTCustomThemeConfig.getInstance();
  }

  @Override
  protected MTCustomThemeForm createForm() {
    return new MTCustomThemeForm();
  }

  @Override
  protected void doApply(final MTCustomThemeForm mtForm, final MTCustomThemeConfig mtConfig) {
    mtConfig.setBackgroundColor(mtForm.getBackgroundColor());
    mtConfig.setForegroundColor(mtForm.getForegroundColor());
    mtConfig.setTextColor(mtForm.getTextColor());
    mtConfig.setSelectionBackgroundColor(mtForm.getSelectionBackgroundColor());
    mtConfig.setSelectionForegroundColor(mtForm.getSelectionForegroundColor());
    mtConfig.setButtonColor(mtForm.getButtonColor());
    mtConfig.setSecondaryBackgroundColor(mtForm.getSecondaryBackgroundColor());
    mtConfig.setDisabledColor(mtForm.getDisabledColor());
    mtConfig.setContrastColor(mtForm.getContrastColor());
    mtConfig.setTableSelectedColor(mtForm.getTableSelectedColor());
    mtConfig.setSecondBorderColor(mtForm.getSecondBorderColor());
    mtConfig.setHighlightColor(mtForm.getHighlightColor());
    mtConfig.setTreeSelectionColor(mtForm.getTreeSelectionColor());
    mtConfig.setNotificationsColor(mtForm.getNotificationsColor());

    mtConfig.fireChanged();
  }

  @Override
  protected boolean checkModified(final MTCustomThemeForm mtForm, final MTCustomThemeConfig mtConfig) {
    boolean modified = mtConfig.isBackgroundColorChanged(getForm().getBackgroundColor());
    modified = modified || mtConfig.isForegroundColorChanged(getForm().getForegroundColor());
    modified = modified || mtConfig.isTextColorChanged(getForm().getTextColor());
    modified = modified || mtConfig.isSelectionBackgroundColorChanged(getForm().getSelectionForegroundColor());
    modified = modified || mtConfig.isSelectionForegroundColorChanged(getForm().getSelectionForegroundColor());
    modified = modified || mtConfig.isButtonColorChanged(getForm().getButtonColor());
    modified = modified || mtConfig.isSecondaryBackgrouncColorChanged(getForm().getSecondaryBackgroundColor());
    modified = modified || mtConfig.isDisabledColorChanged(getForm().getDisabledColor());
    modified = modified || mtConfig.isContrastColorChanged(getForm().getContrastColor());
    modified = modified || mtConfig.isTableSelectionColorChanged(getForm().getTableSelectedColor());
    modified = modified || mtConfig.isSecondBorderColorChanged(getForm().getSecondBorderColor());
    modified = modified || mtConfig.isHighlightColorChanged(getForm().getHighlightColor());
    modified = modified || mtConfig.isTreeSelectionColorChanged(getForm().getTreeSelectionColor());
    modified = modified || mtConfig.isNotificationsColorChanged(getForm().getNotificationsColor());

    return modified;
  }
}
