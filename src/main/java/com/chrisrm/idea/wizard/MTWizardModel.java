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

package com.chrisrm.idea.wizard;

import com.chrisrm.idea.MaterialThemeInfo;
import com.intellij.ui.wizard.WizardModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTWizardModel extends WizardModel {
  private final Map<String, MTWizardStep> myStepMap = new HashMap<>();
  private MTWizardStep myOtherStep;

  public MTWizardModel(final List<MaterialThemeInfo.WizardPage> pages) {
    super("Material Theme Wizard");

    for (final MaterialThemeInfo.WizardPage page: pages) {
      if (page.getCategory() == null) {
        myOtherStep = new MTWizardStep(page.getTitle(), this);
      } else {
        addStep(page.getCategory(), page.getTitle());
      }
    }
    if (myOtherStep != null) {
      add(myOtherStep);
    }
  }

  private MTWizardStep addStep(final String category, final String title) {
    final MTWizardStep step = new MTWizardStep(title, this);
    add(step);
    myStepMap.put(category, step);
    return step;
  }
}
