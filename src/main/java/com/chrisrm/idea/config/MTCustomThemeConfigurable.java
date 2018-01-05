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

public final class MTCustomThemeConfigurable extends MTConfigurableBase<MTCustomThemeForm, MTCustomThemeConfig>
    implements SearchableConfigurable {
  @NotNull
  @Override
  public String getId() {
    return "com.chrisrm.idea.config.custom";
  }

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.customTheme");
  }

  @Override
  protected void setFormState(final MTCustomThemeForm mtForm, final MTCustomThemeConfig mtConfig) {
    mtForm.setBackgroundColor(mtConfig.getBackgroundColor());
    mtForm.setForegroundColor(mtConfig.getForegroundColor());
    mtForm.setTextColor(mtConfig.getTextColor());
    mtForm.setCaretColor(mtConfig.getCaretColor());
    mtForm.setButtonHighlightColor(mtConfig.getButtonHighlightColor());
    mtForm.setContrastColor(mtConfig.getContrastColor());
    mtForm.setDisabledColor(mtConfig.getDisabledColor());
    mtForm.setInactiveColor(mtConfig.getInactiveColor());
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
    mtConfig.setInactiveColor(mtForm.getInactiveColor());
    mtConfig.setCaretColor(mtForm.getCaretColor());
    mtConfig.setSecondaryBackgroundColor(mtForm.getSecondaryBackgroundColor());
    mtConfig.setDisabledColor(mtForm.getDisabledColor());
    mtConfig.setContrastColor(mtForm.getContrastColor());
    mtConfig.setTableSelectedColor(mtForm.getTableSelectedColor());
    mtConfig.setSecondBorderColor(mtForm.getSecondBorderColor());
    mtConfig.setHighlightColor(mtForm.getHighlightColor());
    mtConfig.setButtonHighlightColor(mtForm.getButtonHighlightColor());
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
    modified = modified || mtConfig.isInactiveColorChanged(getForm().getInactiveColor());
    modified = modified || mtConfig.isCaretColorChanged(getForm().getCaretColor());
    modified = modified || mtConfig.isSecondaryBackgrouncColorChanged(getForm().getSecondaryBackgroundColor());
    modified = modified || mtConfig.isDisabledColorChanged(getForm().getDisabledColor());
    modified = modified || mtConfig.isContrastColorChanged(getForm().getContrastColor());
    modified = modified || mtConfig.isTableSelectionColorChanged(getForm().getTableSelectedColor());
    modified = modified || mtConfig.isSecondBorderColorChanged(getForm().getSecondBorderColor());
    modified = modified || mtConfig.isHighlightColorChanged(getForm().getHighlightColor());
    modified = modified || mtConfig.isButtonHighlightColorChanged(getForm().getButtonHighlightColor());
    modified = modified || mtConfig.isTreeSelectionColorChanged(getForm().getTreeSelectionColor());
    modified = modified || mtConfig.isNotificationsColorChanged(getForm().getNotificationsColor());

    return modified;
  }
}
