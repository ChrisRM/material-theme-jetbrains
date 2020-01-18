/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea.wizard.steps;

import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import com.intellij.ui.components.JBScrollPane;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.messages.MTWizardBundle;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

/**
 * @author Elior Boukhobza
 */
@SuppressWarnings({"FieldCanBeLocal",
  "ClassWithTooManyFields",
  "unused"})
public final class MTWizardContrastPanel extends AbstractCustomizeWizardStep {

  private final MTConfig config;

  public MTWizardContrastPanel() {
    config = MTConfig.getInstance();
    initComponents();
  }

  @Override
  protected String getTitle() {
    return MTWizardBundle.message("contrast.panel.title");
  }

  @Override
  protected String getHTMLHeader() {
    return MTWizardBundle.message("contrast.panel.body");
  }

  private void contrastCheckboxActionPerformed(final ActionEvent e) {
    MTThemeManager.toggleContrast();
    config.setContrastMode(contrastCheckbox.isSelected());
  }

  private void highContrastCheckboxActionPerformed(final ActionEvent e) {
    MTThemeManager.toggleHighContrast();
    config.setHighContrast(highContrastCheckbox.isSelected());
  }

  @SuppressWarnings({"CheckStyle",
    "StringConcatenation",
    "OverlyLongMethod",
    "DuplicateStringLiteralInspection",
    "Convert2MethodRef"})
  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MTWizardBundle");
    scrollPane = new JBScrollPane();
    content = new JPanel();
    contrastPanel = new JPanel();
    contrastCheckbox = new JCheckBox();
    contrastImage = new JLabel();
    highContrastPanel = new JPanel();
    highContrastCheckbox = new JCheckBox();
    highContrastImage = new JLabel();
    contrastDesc = new JTextPane();
    highContrastDesc = new JTextPane();

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
          contrastCheckbox.setText(bundle.getString("MTWizardContrastPanel.contrastCheckbox.text"));
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
          highContrastCheckbox.setText(bundle.getString("MTWizardContrastPanel.highContrastCheckbox.text"));
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

        //---- contrastDesc ----
        contrastDesc.setText(bundle.getString("MTWizardContrastPanel.contrastDesc.text"));
        contrastDesc.setFont(UIManager.getFont("Label.font"));
        contrastDesc.setEditable(false);
        contrastDesc.setBackground(UIManager.getColor("Panel.background"));
        content.add(contrastDesc, "cell 0 1,aligny top,growy 0");

        //---- highContrastDesc ----
        highContrastDesc.setText(bundle.getString("MTWizardContrastPanel.highContrastDesc.text"));
        highContrastDesc.setFont(UIManager.getFont("Label.font"));
        highContrastDesc.setEditable(false);
        highContrastDesc.setBackground(UIManager.getColor("Panel.background"));
        content.add(highContrastDesc, "cell 1 1,aligny top,growy 0");
      }
      scrollPane.setViewportView(content);
    }
    add(scrollPane, BorderLayout.CENTER);
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    contrastCheckbox.setSelected(config.isContrastMode());
    highContrastCheckbox.setSelected(config.isHighContrast());
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
  private JTextPane contrastDesc;
  private JTextPane highContrastDesc;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
