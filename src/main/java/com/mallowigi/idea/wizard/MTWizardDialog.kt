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

package com.mallowigi.idea.wizard;

import com.intellij.ide.customize.CustomizeIDEWizardDialog;
import com.intellij.ide.customize.CustomizeIDEWizardStepsProvider;
import com.intellij.openapi.ui.popup.util.PopupUtil;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.messages.MTWizardBundle;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Arrays;

public final class MTWizardDialog extends CustomizeIDEWizardDialog {

  private final MTConfig configCopy;

  public MTWizardDialog(final CustomizeIDEWizardStepsProvider stepsProvider) {
    super(stepsProvider);
    setTitle(MTWizardBundle.message("mt.wizard.title"));
    getPeer().setAppIcons();
    configCopy = MTConfig.getInstance().clone();

    extractPrivateFields();
    initCurrentStep();
  }

  @Override
  public void actionPerformed(@NotNull final ActionEvent e) {
    super.actionPerformed(e);
    initCurrentStep();
  }

  @SuppressWarnings("MagicNumber")
  @Override
  protected JComponent createCenterPanel() {
    final JComponent centerPanel = super.createCenterPanel();
    if (centerPanel != null) {
      centerPanel.setPreferredSize(JBUI.size(1200, 700));
    }
    return centerPanel;
  }

  @Override
  public void doCancelAction() {
    super.doCancelAction();
    MTConfig.getInstance().loadState(configCopy);
    MTThemeManager.activate();
  }

  private static void extractPrivateFields() {
    final Field[] fields = CustomizeIDEWizardDialog.class.getDeclaredFields();
    final Object[] buttons = Arrays.stream(fields)
                                   .filter(field -> field.getType().equals(JButton.class))
                                   .toArray();

    final Field myNextButtonField = (Field) buttons[0];
    final Field myBackButtonField = (Field) buttons[1];
    final Field mySkipButtonField = (Field) buttons[2];
    final Field myIndexField = fields[7];
    final Field myCardLayoutField = fields[5];
    final Field myStepsField = fields[6];
    final Field myContentPanelField = fields[13];

    myNextButtonField.setAccessible(true);
    myBackButtonField.setAccessible(true);
    mySkipButtonField.setAccessible(true);
    myIndexField.setAccessible(true);
    myCardLayoutField.setAccessible(true);
    myStepsField.setAccessible(true);
    myContentPanelField.setAccessible(true);
  }

  private void initCurrentStep() {
    //      final JButton myNextButton = (JButton) myNextButtonField.get(this);
    //      final int myIndex = (int) myIndexField.get(this);
    //      final Collection<AbstractCustomizeWizardStep> mySteps = (Collection<AbstractCustomizeWizardStep>) myStepsField.get(this);

    if (myIndex == mySteps.size() - 1) {
      myNextButton.setText(MTWizardBundle.message("finish.button"));
    }
  }

  @NotNull
  @Override
  protected ActionListener createCancelAction() {
    return e -> {
      if (!PopupUtil.handleEscKeyEvent()) {
        doCancelAction(e);
      }
    };
  }

}
