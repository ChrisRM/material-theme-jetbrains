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
import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Elior Boukhobza
 */
public class MTWizardThemesPanel extends AbstractCustomizeWizardStep {
  public MTWizardThemesPanel() {
    initComponents();
  }

  @Override
  protected String getTitle() {
    return "Themes";
  }

  @Override
  protected String getHTMLHeader() {
    return "<html><body><h2>Select a UI theme</h2>&nbsp;</body></html>";
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
    panel2 = new JPanel();
    selectThemeLabel = new JLabel();
    panel1 = new JPanel();
    oceanicButton = new JRadioButton();
    label1 = new JLabel();
    panel11 = new JPanel();
    darkerButton = new JRadioButton();
    label2 = new JLabel();
    panel10 = new JPanel();
    lighterButton = new JRadioButton();
    label11 = new JLabel();
    panel12 = new JPanel();
    palenightButton = new JRadioButton();
    label10 = new JLabel();
    panel3 = new JPanel();
    deepoceanButton = new JRadioButton();
    label9 = new JLabel();
    panel4 = new JPanel();
    monokaiButton = new JRadioButton();
    label8 = new JLabel();
    panel5 = new JPanel();
    arcdarkButton = new JRadioButton();
    label7 = new JLabel();
    panel6 = new JPanel();
    onedarkButton = new JRadioButton();
    label6 = new JLabel();
    panel7 = new JPanel();
    onelightButton = new JRadioButton();
    label5 = new JLabel();
    panel8 = new JPanel();
    solarizedDarkButton = new JRadioButton();
    label4 = new JLabel();
    panel9 = new JPanel();
    solarizedLightButton = new JRadioButton();
    label3 = new JLabel();

    //======== this ========
    setLayout(new BorderLayout());

    //======== panel2 ========
    {
      panel2.setPreferredSize(null);
      panel2.setMinimumSize(null);
      panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      panel2.setInheritsPopupMenu(true);
      panel2.setMaximumSize(new Dimension(2147483647, 2147483647));
      panel2.setAutoscrolls(true);
      panel2.setLayout(new MigLayout(
          "flowy,insets 0,gap 0 0",
          // columns
          "[grow 1,fill]" +
              "[fill]",
          // rows
          "[grow 1,fill]" +
              "[fill]" +
              "[fill]" +
              "[fill]" +
              "[fill]" +
              "[fill]" +
              "[fill]"));

      //---- selectThemeLabel ----
      selectThemeLabel.setText("Select a theme:");
      selectThemeLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
      panel2.add(selectThemeLabel, "cell 0 0 2 1,align left top,grow 0 0");

      //======== panel1 ========
      {
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        //---- oceanicButton ----
        oceanicButton.setText("Oceanic");
        oceanicButton.setHorizontalAlignment(SwingConstants.LEFT);
        oceanicButton.addActionListener(e -> oceanicButtonActionPerformed(e));
        panel1.add(oceanicButton);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/wizard/oceanic.png")));
        panel1.add(label1);
      }
      panel2.add(panel1, "cell 0 1,align center center,grow 0 0");

      //======== panel11 ========
      {
        panel11.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel11.setLayout(new BoxLayout(panel11, BoxLayout.Y_AXIS));

        //---- darkerButton ----
        darkerButton.setText("Darker");
        darkerButton.setHorizontalAlignment(SwingConstants.LEFT);
        darkerButton.addActionListener(e -> darkerButtonActionPerformed(e));
        panel11.add(darkerButton);

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/wizard/darker.png")));
        panel11.add(label2);
      }
      panel2.add(panel11, "cell 1 1,align center center,grow 0 0");

      //======== panel10 ========
      {
        panel10.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));

        //---- lighterButton ----
        lighterButton.setText("Lighter");
        lighterButton.addActionListener(e -> lighterButtonActionPerformed(e));
        panel10.add(lighterButton);

        //---- label11 ----
        label11.setIcon(new ImageIcon(getClass().getResource("/wizard/lighter.png")));
        panel10.add(label11);
      }
      panel2.add(panel10, "cell 1 2,align center center,grow 0 0");

      //======== panel12 ========
      {
        panel12.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel12.setLayout(new BoxLayout(panel12, BoxLayout.Y_AXIS));

        //---- palenightButton ----
        palenightButton.setText("Palenight");
        palenightButton.addActionListener(e -> palenightButtonActionPerformed(e));
        panel12.add(palenightButton);

        //---- label10 ----
        label10.setIcon(new ImageIcon(getClass().getResource("/wizard/palenight.png")));
        panel12.add(label10);
      }
      panel2.add(panel12, "cell 0 2,align center center,grow 0 0");

      //======== panel3 ========
      {
        panel3.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        //---- deepoceanButton ----
        deepoceanButton.setText("Deep Ocean");
        deepoceanButton.addActionListener(e -> deepoceanButtonActionPerformed(e));
        panel3.add(deepoceanButton);

        //---- label9 ----
        label9.setIcon(new ImageIcon(getClass().getResource("/wizard/deepocean.png")));
        panel3.add(label9);
      }
      panel2.add(panel3, "cell 0 3,align center center,grow 0 0");

      //======== panel4 ========
      {
        panel4.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

        //---- monokaiButton ----
        monokaiButton.setText("Monokai");
        monokaiButton.addActionListener(e -> monokaiButtonActionPerformed(e));
        panel4.add(monokaiButton);

        //---- label8 ----
        label8.setIcon(new ImageIcon(getClass().getResource("/wizard/monokai.png")));
        panel4.add(label8);
      }
      panel2.add(panel4, "cell 1 3,align center center,grow 0 0");

      //======== panel5 ========
      {
        panel5.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

        //---- arcdarkButton ----
        arcdarkButton.setText("Arc Dark");
        arcdarkButton.addActionListener(e -> arcdarkButtonActionPerformed(e));
        panel5.add(arcdarkButton);

        //---- label7 ----
        label7.setIcon(new ImageIcon(getClass().getResource("/wizard/arcdark.png")));
        panel5.add(label7);
      }
      panel2.add(panel5, "cell 0 4,align center center,grow 0 0");

      //======== panel6 ========
      {
        panel6.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

        //---- onedarkButton ----
        onedarkButton.setText("One Dark");
        onedarkButton.addActionListener(e -> onedarkButtonActionPerformed(e));
        panel6.add(onedarkButton);

        //---- label6 ----
        label6.setIcon(new ImageIcon(getClass().getResource("/wizard/onedark.png")));
        panel6.add(label6);
      }
      panel2.add(panel6, "cell 1 4,align center center,grow 0 0");

      //======== panel7 ========
      {
        panel7.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));

        //---- onelightButton ----
        onelightButton.setText("One Light");
        onelightButton.addActionListener(e -> onelightButtonActionPerformed(e));
        panel7.add(onelightButton);

        //---- label5 ----
        label5.setIcon(new ImageIcon(getClass().getResource("/wizard/onelight.png")));
        panel7.add(label5);
      }
      panel2.add(panel7, "cell 0 5");

      //======== panel8 ========
      {
        panel8.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.Y_AXIS));

        //---- solarizedDarkButton ----
        solarizedDarkButton.setText("Solarized Dark");
        solarizedDarkButton.setHorizontalAlignment(SwingConstants.LEFT);
        solarizedDarkButton.addActionListener(e -> solarizedDarkButtonActionPerformed(e));
        panel8.add(solarizedDarkButton);

        //---- label4 ----
        label4.setIcon(new ImageIcon(getClass().getResource("/wizard/solarizeddark.png")));
        panel8.add(label4);
      }
      panel2.add(panel8, "cell 1 5");

      //======== panel9 ========
      {
        panel9.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));

        //---- solarizedLightButton ----
        solarizedLightButton.setText("Solarized Light");
        solarizedLightButton.addActionListener(e -> solarizedLightButtonActionPerformed(e));
        panel9.add(solarizedLightButton);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/wizard/solarizedlight.png")));
        panel9.add(label3);
      }
      panel2.add(panel9, "cell 0 6,align center center,grow 0 0");
    }
    add(panel2, BorderLayout.CENTER);

    //---- selectedTheme ----
    final ButtonGroup selectedTheme = new ButtonGroup();
    selectedTheme.add(oceanicButton);
    selectedTheme.add(darkerButton);
    selectedTheme.add(lighterButton);
    selectedTheme.add(palenightButton);
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
  private JPanel panel2;
  private JLabel selectThemeLabel;
  private JPanel panel1;
  private JRadioButton oceanicButton;
  private JLabel label1;
  private JPanel panel11;
  private JRadioButton darkerButton;
  private JLabel label2;
  private JPanel panel10;
  private JRadioButton lighterButton;
  private JLabel label11;
  private JPanel panel12;
  private JRadioButton palenightButton;
  private JLabel label10;
  private JPanel panel3;
  private JRadioButton deepoceanButton;
  private JLabel label9;
  private JPanel panel4;
  private JRadioButton monokaiButton;
  private JLabel label8;
  private JPanel panel5;
  private JRadioButton arcdarkButton;
  private JLabel label7;
  private JPanel panel6;
  private JRadioButton onedarkButton;
  private JLabel label6;
  private JPanel panel7;
  private JRadioButton onelightButton;
  private JLabel label5;
  private JPanel panel8;
  private JRadioButton solarizedDarkButton;
  private JLabel label4;
  private JPanel panel9;
  private JRadioButton solarizedLightButton;
  private JLabel label3;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

}
