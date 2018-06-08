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
 * Created by JFormDesigner on Sat Jul 21 22:43:42 IDT 2018
 */

package com.chrisrm.idea.wizard.steps;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTThemeManager;
import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import com.intellij.ui.components.JBScrollPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Elior Boukhobza
 */
public class MTWizardContrastPanel extends AbstractCustomizeWizardStep {

  private final MTConfig config;

  public MTWizardContrastPanel() {
    config = MTConfig.getInstance();
    initComponents();
  }

  @Override
  protected String getTitle() {
    return "Contrast";
  }

  @Override
  protected String getHTMLHeader() {
    return "<html><body><h2>Contrast Modes</h2></body></html>";
  }

  private void contrastCheckboxActionPerformed(final ActionEvent e) {
    MTThemeManager.getInstance().toggleContrast();
    config.setIsContrastMode(contrastCheckbox.isSelected());
  }

  private void highContrastCheckboxActionPerformed(final ActionEvent e) {
    MTThemeManager.getInstance().toggleHighContrast();
    config.setIsHighContrast(highContrastCheckbox.isSelected());
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    scrollPane = new JBScrollPane();
    content = new JPanel();
    contrastPanel = new JPanel();
    contrastCheckbox = new JCheckBox();
    contrastImage = new JLabel();
    highContrastPanel = new JPanel();
    highContrastCheckbox = new JCheckBox();
    highContrastImage = new JLabel();
    textPane1 = new JTextPane();
    textPane2 = new JTextPane();

    //======== this ========
    setLayout(new BorderLayout());

    //======== scrollPane ========
    {
      scrollPane.setBorder(null);

      //======== content ========
      {
        content.setLayout(new MigLayout(
            "fill,hidemode 3,align left top",
            // columns
            "[fill]" +
                "[fill]",
            // rows
            "[384,top]" +
                "[]"));

        //======== contrastPanel ========
        {
          contrastPanel.setLayout(new BoxLayout(contrastPanel, BoxLayout.Y_AXIS));

          //---- contrastCheckbox ----
          contrastCheckbox.setText("Contrast Mode");
          contrastCheckbox.addActionListener(e -> contrastCheckboxActionPerformed(e));
          contrastPanel.add(contrastCheckbox);

          //---- contrastImage ----
          contrastImage.setIcon(new ImageIcon(getClass().getResource("/wizard/withContrast.png")));
          contrastPanel.add(contrastImage);
        }
        content.add(contrastPanel, "cell 0 0,align left top,grow 0 0");

        //======== highContrastPanel ========
        {
          highContrastPanel.setLayout(new BoxLayout(highContrastPanel, BoxLayout.Y_AXIS));

          //---- highContrastCheckbox ----
          highContrastCheckbox.setText("High Contrast");
          highContrastCheckbox.addActionListener(e -> highContrastCheckboxActionPerformed(e));
          highContrastPanel.add(highContrastCheckbox);

          //---- highContrastImage ----
          highContrastImage.setIcon(new ImageIcon(getClass().getResource("/wizard/highContrast.png")));
          highContrastImage.setMaximumSize(new Dimension(561, 360));
          highContrastImage.setMinimumSize(new Dimension(561, 360));
          highContrastImage.setPreferredSize(new Dimension(561, 360));
          highContrastPanel.add(highContrastImage);
        }
        content.add(highContrastPanel, "cell 1 0,align left top,grow 0 0");

        //---- textPane1 ----
        textPane1.setText("Adds contrast between components of the UI such as Trees, Tabs, Input Fields or Lists.");
        textPane1.setFont(UIManager.getFont("Label.font"));
        textPane1.setEditable(false);
        textPane1.setBackground(UIManager.getColor("Panel.background"));
        content.add(textPane1, "cell 0 1,aligny top,growy 0");

        //---- textPane2 ----
        textPane2.setText("Intensifies contrast with the editor by setting the background color darker and texts lighter.");
        textPane2.setFont(UIManager.getFont("Label.font"));
        textPane2.setEditable(false);
        textPane2.setBackground(UIManager.getColor("Panel.background"));
        content.add(textPane2, "cell 1 1,aligny top,growy 0");
      }
      scrollPane.setViewportView(content);
    }
    add(scrollPane, BorderLayout.CENTER);
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    contrastCheckbox.setSelected(config.getIsContrastMode());
    highContrastCheckbox.setSelected(config.getIsHighContrast());
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JBScrollPane scrollPane;
  private JPanel content;
  private JPanel contrastPanel;
  private JCheckBox contrastCheckbox;
  private JLabel contrastImage;
  private JPanel highContrastPanel;
  private JCheckBox highContrastCheckbox;
  private JLabel highContrastImage;
  private JTextPane textPane1;
  private JTextPane textPane2;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
