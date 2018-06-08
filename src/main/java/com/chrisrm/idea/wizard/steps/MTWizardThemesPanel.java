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
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
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
    scrollPane1 = new JScrollPane();
    oceanicButton = new JRadioButton();
    scrollPane2 = new JScrollPane();
    darkerButton = new JRadioButton();
    scrollPane3 = new JScrollPane();
    palenightButton = new JRadioButton();
    scrollPane4 = new JScrollPane();
    lighterButton = new JRadioButton();

    //======== this ========
    setLayout(new MigLayout(
        "insets 0,hidemode 3,gap 0 0",
        // columns
        "[grow 1,fill]" +
            "[grow 1,fill]",
        // rows
        "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

    //---- selectThemeLabel ----
    selectThemeLabel.setText("Select a theme:");
    add(selectThemeLabel, "cell 0 0 2 1,align left top,grow 0 0");

    //======== scrollPane1 ========
    {

      //---- oceanicButton ----
      oceanicButton.setText("Oceanic");
      oceanicButton.setHorizontalAlignment(SwingConstants.LEFT);
      oceanicButton.setIcon(new ImageIcon(getClass().getResource("/wizard/oceanic.png")));
      oceanicButton.addActionListener(e -> oceanicButtonActionPerformed(e));
      scrollPane1.setViewportView(oceanicButton);
    }
    add(scrollPane1, "cell 0 7");

    //======== scrollPane2 ========
    {

      //---- darkerButton ----
      darkerButton.setText("Darker");
      darkerButton.setHorizontalAlignment(SwingConstants.LEFT);
      darkerButton.setIcon(new ImageIcon(getClass().getResource("/wizard/darker.png")));
      darkerButton.addActionListener(e -> darkerButtonActionPerformed(e));
      scrollPane2.setViewportView(darkerButton);
    }
    add(scrollPane2, "cell 0 8");

    //======== scrollPane3 ========
    {

      //---- palenightButton ----
      palenightButton.setText("Palenight");
      palenightButton.setIcon(new ImageIcon(getClass().getResource("/wizard/palenight.png")));
      palenightButton.addActionListener(e -> palenightButtonActionPerformed(e));
      scrollPane3.setViewportView(palenightButton);
    }
    add(scrollPane3, "cell 0 9");

    //======== scrollPane4 ========
    {

      //---- lighterButton ----
      lighterButton.setText("Lighter");
      lighterButton.setIcon(new ImageIcon(getClass().getResource("/wizard/lighter.png")));
      lighterButton.addActionListener(e -> lighterButtonActionPerformed(e));
      scrollPane4.setViewportView(lighterButton);
    }
    add(scrollPane4, "cell 0 10");

    //---- selectedTheme ----
    final ButtonGroup selectedTheme = new ButtonGroup();
    selectedTheme.add(oceanicButton);
    selectedTheme.add(darkerButton);
    selectedTheme.add(palenightButton);
    selectedTheme.add(lighterButton);
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JLabel selectThemeLabel;
  private JScrollPane scrollPane1;
  private JRadioButton oceanicButton;
  private JScrollPane scrollPane2;
  private JRadioButton darkerButton;
  private JScrollPane scrollPane3;
  private JRadioButton palenightButton;
  private JScrollPane scrollPane4;
  private JRadioButton lighterButton;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

}
