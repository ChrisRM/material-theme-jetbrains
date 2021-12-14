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
package com.mallowigi.idea.wizard

import com.intellij.ide.customize.AbstractCustomizeWizardStep
import com.intellij.ide.customize.CustomizeIDEWizardDialog
import com.intellij.ide.customize.CustomizeIDEWizardStepsProvider
import com.mallowigi.idea.wizard.steps.MTWizardAccentPanel
import com.mallowigi.idea.wizard.steps.MTWizardContrastPanel
import com.mallowigi.idea.wizard.steps.MTWizardFinishPanel
import com.mallowigi.idea.wizard.steps.MTWizardOtherOptionsPanel
import com.mallowigi.idea.wizard.steps.MTWizardThemesPanel
import com.mallowigi.idea.wizard.steps.MTWizardWelcomePanel

/**
 * Wizard Steps
 *
 */
class MTWizardStepsProvider : CustomizeIDEWizardStepsProvider {
  /**
   * Add wizard steps
   *
   */
  override fun initSteps(wizardDialog: CustomizeIDEWizardDialog, steps: MutableList<in AbstractCustomizeWizardStep?>) {
    steps.add(MTWizardWelcomePanel())
    steps.add(MTWizardThemesPanel())
    steps.add(MTWizardContrastPanel())
    steps.add(MTWizardAccentPanel())
    steps.add(MTWizardOtherOptionsPanel())
    steps.add(MTWizardFinishPanel())
  }
}
