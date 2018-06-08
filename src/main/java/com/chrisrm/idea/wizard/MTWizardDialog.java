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

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTThemeManager;
import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import com.intellij.ide.customize.CustomizeIDEWizardDialog;
import com.intellij.openapi.ui.popup.util.PopupUtil;
import com.intellij.ui.JBCardLayout;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class MTWizardDialog extends CustomizeIDEWizardDialog implements ActionListener {

  private Field myNextButtonField;
  private Field myBackButtonField;
  private Field mySkipButtonField;
  private Field myIndexField;
  private Field myStepsField;
  private Field myCardLayoutField;
  private Field myContentPanelField;
  private final MTConfig configCopy;

  public MTWizardDialog(final MTWizardStepsProvider stepsProvider) {
    super(stepsProvider);
    setTitle("Material Theme Wizard");
    getPeer().setAppIcons();
    configCopy = (MTConfig) MTConfig.getInstance().clone();

    getPrivateFields();
    initCurrentStep();
  }

  @Override
  public void actionPerformed(@NotNull final ActionEvent e) {
    super.actionPerformed(e);
    initCurrentStep();
  }

  @Override
  protected JComponent createCenterPanel() {
    final JComponent centerPanel = super.createCenterPanel();
    centerPanel.setPreferredSize(JBUI.size(1200, 700));
    return centerPanel;
  }

  @Override
  public void doCancelAction() {
    super.doCancelAction();
    MTConfig.getInstance().copyFrom(configCopy);
    MTThemeManager.getInstance().activate();
  }

  private void getPrivateFields() {
    try {
      myNextButtonField = ReflectionUtil.findField(CustomizeIDEWizardDialog.class, JButton.class, "myNextButton");
      myBackButtonField = ReflectionUtil.findField(CustomizeIDEWizardDialog.class, JButton.class, "myBackButton");
      mySkipButtonField = ReflectionUtil.findField(CustomizeIDEWizardDialog.class, JButton.class, "mySkipButton");
      myIndexField = ReflectionUtil.findField(CustomizeIDEWizardDialog.class, int.class, "myIndex");
      myCardLayoutField = ReflectionUtil.findField(CustomizeIDEWizardDialog.class, JBCardLayout.class, "myCardLayout");
      myStepsField = ReflectionUtil.findField(CustomizeIDEWizardDialog.class, List.class, "mySteps");
      myContentPanelField = ReflectionUtil.findField(CustomizeIDEWizardDialog.class, JPanel.class, "myContentPanel");

    } catch (final NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  private void initCurrentStep() {
    try {
      final JButton myNextButton = (JButton) myNextButtonField.get(this);
      //      final JButton myBackButton = (JButton) myBackButtonField.get(this);
      //      final JButton mySkipButton = (JButton) mySkipButtonField.get(this);
      final int myIndex = (int) myIndexField.get(this);
      final List<AbstractCustomizeWizardStep> mySteps = (List<AbstractCustomizeWizardStep>) myStepsField.get(this);

      if (myIndex == mySteps.size() - 1) {
        myNextButton.setText("Finish");
      }
    } catch (final IllegalAccessException e) {
      e.printStackTrace();
    }
  }


  @Nullable
  @Override
  protected ActionListener createCancelAction() {
    return e -> {
      if (!PopupUtil.handleEscKeyEvent()) {
        doCancelAction(e);
      }
    };
  }

}
