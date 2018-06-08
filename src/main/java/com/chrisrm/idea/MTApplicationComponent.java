/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.chrisrm.idea.utils.MTUiUtils;
import com.chrisrm.idea.wizard.MTWizardDialog;
import com.chrisrm.idea.wizard.MTWizardStepsProvider;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import static com.chrisrm.idea.wizard.MTWizardDialog.MT_IS_SHOWN_WIZARD;

public final class MTApplicationComponent implements ApplicationComponent {
  public static final String SHOW_STATISTICS_AGREEMENT = "mt.showStatisticsAgreement";
  private boolean updated;

  @Override
  public void initComponent() {
    updated = !MTUiUtils.getVersion().equals(MTConfig.getInstance().getVersion());
    if (updated) {
      MTConfig.getInstance().setVersion(MTUiUtils.getVersion());
    }

    checkWizard();

    initAnalytics();
  }

  private void initAnalytics() {
    MTAnalytics.getInstance().identify();
    MTAnalytics.getInstance().track("Config", MTConfig.getInstance().asProperties());
  }

  private void checkWizard() {
    final boolean isWizardShown = PropertiesComponent.getInstance().getBoolean(MT_IS_SHOWN_WIZARD);
    if (!isWizardShown) {
      new MTWizardDialog(new MTWizardStepsProvider()).show();
      PropertiesComponent.getInstance().setValue(MT_IS_SHOWN_WIZARD, true);
    }
  }

  /**
   * Component dispose method.
   */
  @Override
  public void disposeComponent() {
  }

  /**
   * Returns component's name.
   *
   * @return component's name
   */
  @NotNull
  @Override
  public String getComponentName() {
    return "MTApplicationComponent";
  }

  public static MTApplicationComponent getInstance() {
    return ApplicationManager.getApplication().getComponent(MTApplicationComponent.class);
  }

  public boolean isUpdated() {
    return updated;
  }

  public boolean isAgreementShown() {
    return PropertiesComponent.getInstance().isValueSet(SHOW_STATISTICS_AGREEMENT);
  }
}
