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

/*
 * Created by JFormDesigner on Fri Jun 29 18:52:29 IDT 2018
 */

package com.chrisrm.idea.wizard.steps;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTThemeFacade;
import com.chrisrm.idea.MTThemeManager;
import com.chrisrm.idea.MTThemes;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Elior Boukhobza
 */
public class MTWizardThemesPanel extends JPanel {
  public MTWizardThemesPanel() {
    initComponents();
  }

  private void selectTheme(final MTThemeFacade theme) {
    MTConfig.getInstance().setSelectedTheme(theme);
    MTThemeManager.getInstance().activate(theme, true);
  }

  private void oceanicButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.OCEANIC);
  }

  private void darkerButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.DARKER);
  }

  private void palenightButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.PALENIGHT);
  }

  private void lighterButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.LIGHTER);
  }

  private void deepoceanButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.DEEPOCEAN);
  }

  private void monokaiButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.MONOKAI);
  }

  private void arcdarkButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.ARC_DARK);
  }

  private void onedarkButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.ONE_DARK);
  }

  private void onelightButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.ONE_LIGHT);
  }

  private void solarizedDarkButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.SOLARIZED_DARK);
  }

  private void solarizedLightButtonActionPerformed(final ActionEvent e) {
    selectTheme(MTThemes.SOLARIZED_LIGHT);
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    selectThemeLabel = new JLabel();
    oceanicButton = new JRadioButton();
    darkerButton = new JRadioButton();
    palenightButton = new JRadioButton();
    lighterButton = new JRadioButton();
    deepoceanButton = new JRadioButton();
    monokaiButton = new JRadioButton();
    arcdarkButton = new JRadioButton();
    onedarkButton = new JRadioButton();
    onelightButton = new JRadioButton();
    solarizedDarkButton = new JRadioButton();
    solarizedLightButton = new JRadioButton();

    //======== this ========
    setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), 0, 0));

    //---- selectThemeLabel ----
    selectThemeLabel.setText("Select a theme:");
    add(selectThemeLabel, new GridConstraints(0, 0, 1, 2,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null, 2));

    //---- oceanicButton ----
    oceanicButton.setText("Oceanic");
    oceanicButton.setHorizontalAlignment(SwingConstants.LEFT);
    oceanicButton.setIcon(new ImageIcon(getClass().getResource("/wizard/oceanic.png")));
    oceanicButton.addActionListener(e -> oceanicButtonActionPerformed(e));
    add(oceanicButton, new GridConstraints(1, 0, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- darkerButton ----
    darkerButton.setText("Darker");
    darkerButton.setHorizontalAlignment(SwingConstants.LEFT);
    darkerButton.setIcon(new ImageIcon(getClass().getResource("/wizard/darker.png")));
    darkerButton.addActionListener(e -> darkerButtonActionPerformed(e));
    add(darkerButton, new GridConstraints(1, 1, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- palenightButton ----
    palenightButton.setText("Palenight");
    palenightButton.setIcon(new ImageIcon(getClass().getResource("/wizard/palenight.png")));
    palenightButton.addActionListener(e -> palenightButtonActionPerformed(e));
    add(palenightButton, new GridConstraints(2, 0, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- lighterButton ----
    lighterButton.setText("Lighter");
    lighterButton.setIcon(new ImageIcon(getClass().getResource("/wizard/lighter.png")));
    lighterButton.addActionListener(e -> lighterButtonActionPerformed(e));
    add(lighterButton, new GridConstraints(2, 1, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- deepoceanButton ----
    deepoceanButton.setText("Deep Ocean");
    deepoceanButton.setIcon(new ImageIcon(getClass().getResource("/wizard/deepocean.png")));
    deepoceanButton.addActionListener(e -> deepoceanButtonActionPerformed(e));
    add(deepoceanButton, new GridConstraints(3, 0, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- monokaiButton ----
    monokaiButton.setText("Monokai");
    monokaiButton.setIcon(new ImageIcon(getClass().getResource("/wizard/monokai.png")));
    monokaiButton.addActionListener(e -> monokaiButtonActionPerformed(e));
    add(monokaiButton, new GridConstraints(3, 1, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- arcdarkButton ----
    arcdarkButton.setText("Arc Dark");
    arcdarkButton.setIcon(new ImageIcon(getClass().getResource("/wizard/arcdark.png")));
    arcdarkButton.addActionListener(e -> arcdarkButtonActionPerformed(e));
    add(arcdarkButton, new GridConstraints(4, 0, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- onedarkButton ----
    onedarkButton.setText("One Dark");
    onedarkButton.setIcon(new ImageIcon(getClass().getResource("/wizard/onedark.png")));
    onedarkButton.addActionListener(e -> onedarkButtonActionPerformed(e));
    add(onedarkButton, new GridConstraints(4, 1, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- onelightButton ----
    onelightButton.setText("One Light");
    onelightButton.setIcon(new ImageIcon(getClass().getResource("/wizard/onelight.png")));
    onelightButton.addActionListener(e -> onelightButtonActionPerformed(e));
    add(onelightButton, new GridConstraints(5, 0, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- solarizedDarkButton ----
    solarizedDarkButton.setText("Solarized Dark");
    solarizedDarkButton.setHorizontalAlignment(SwingConstants.LEFT);
    solarizedDarkButton.setIcon(new ImageIcon(getClass().getResource("/wizard/solarizeddark.png")));
    solarizedDarkButton.addActionListener(e -> solarizedDarkButtonActionPerformed(e));
    add(solarizedDarkButton, new GridConstraints(5, 1, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- solarizedLightButton ----
    solarizedLightButton.setText("Solarized Light");
    solarizedLightButton.setIcon(new ImageIcon(getClass().getResource("/wizard/solarizedlight.png")));
    solarizedLightButton.addActionListener(e -> solarizedLightButtonActionPerformed(e));
    add(solarizedLightButton, new GridConstraints(6, 0, 1, 1,
        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

    //---- selectedTheme ----
    final ButtonGroup selectedTheme = new ButtonGroup();
    selectedTheme.add(oceanicButton);
    selectedTheme.add(darkerButton);
    selectedTheme.add(palenightButton);
    selectedTheme.add(lighterButton);
    selectedTheme.add(deepoceanButton);
    selectedTheme.add(monokaiButton);
    selectedTheme.add(arcdarkButton);
    selectedTheme.add(onedarkButton);
    selectedTheme.add(onelightButton);
    selectedTheme.add(solarizedDarkButton);
    selectedTheme.add(solarizedLightButton);
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JLabel selectThemeLabel;
  private JRadioButton oceanicButton;
  private JRadioButton darkerButton;
  private JRadioButton palenightButton;
  private JRadioButton lighterButton;
  private JRadioButton deepoceanButton;
  private JRadioButton monokaiButton;
  private JRadioButton arcdarkButton;
  private JRadioButton onedarkButton;
  private JRadioButton onelightButton;
  private JRadioButton solarizedDarkButton;
  private JRadioButton solarizedLightButton;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

}
