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

import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import com.intellij.ide.customize.CustomizeIDEWizardDialog;
import com.intellij.ui.JBCardLayout;
import com.intellij.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;

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

  public MTWizardDialog(final MTWizardStepsProvider stepsProvider) {
    super(stepsProvider);
    setTitle("Material Theme Wizard");
    getPeer().setAppIcons();

    getPrivateFields();
    initCurrentStep();
  }

  @Override
  public void actionPerformed(@NotNull final ActionEvent e) {
    super.actionPerformed(e);
    initCurrentStep();
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

      //      myNextButton = (JButton) myNextButtonField.get(this);
      //      myBackButton = (JButton) myBackButtonField.get(this);
      //      mySkipButton = (JButton) mySkipButtonField.get(this);
      //      myIndex = (int) myIndexField.get(this);
      //      myCardLayout = (JBCardLayout) myCardLayoutField.get(this);
      //      mySteps = (List<AbstractCustomizeWizardStep>) myStepsField.get(this);
      //      myContentPanel = (JPanel) myContentPanelField.get(this);
    } catch (final NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  private void initCurrentStep() {
    try {
      final JButton myNextButton = (JButton) myNextButtonField.get(this);
      final JButton myBackButton = (JButton) myBackButtonField.get(this);
      final JButton mySkipButton = (JButton) mySkipButtonField.get(this);
      final int myIndex = (int) myIndexField.get(this);
      final List<AbstractCustomizeWizardStep> mySteps = (List<AbstractCustomizeWizardStep>) myStepsField.get(this);

      mySkipButton.setVisible(false);
      if (myIndex > 0) {
        myBackButton.setText(myBackButton.getText().replace("Back to ", "< "));
      }
      if (myIndex == mySteps.size() - 1) {
        myNextButton.setText("Finish");
      }
    } catch (final IllegalAccessException e) {
      e.printStackTrace();
    }
  }

}
