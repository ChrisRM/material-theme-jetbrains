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
 * Created by JFormDesigner on Wed Jul 25 00:33:59 IDT 2018
 */

package com.chrisrm.idea.wizard.steps;

import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.labels.LinkLabel;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

/**
 * @author Elior Boukhobza
 */
public class MTWizardFinishPanel extends AbstractCustomizeWizardStep {
  public MTWizardFinishPanel() {
    initComponents();
  }

  @Override
  protected String getTitle() {
    return "Finish";
  }

  @Override
  protected String getHTMLHeader() {
    return null;
  }

  @Nullable
  @Override
  protected String getHTMLFooter() {
    return "You can always change your settings at Settings | Appearance | Material Theme";
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MTWizardBundle");
    scrollPane = new JBScrollPane();
    content = new JPanel();
    summary = new JTextArea();
    summaryLabel = new JLabel();
    docLink = new LinkLabel();
    paypalLabel = new JLabel();
    paypalLink = new LinkLabel();
    label1 = new JLabel();
    openCollLink = new LinkLabel();
    vSpacer1 = new JPanel(null);
    summarySummary = new JLabel();

    //======== this ========
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    //======== scrollPane ========
    {

      //======== content ========
      {
        content.setBorder(null);
        content.setLayout(new MigLayout(
            "hidemode 3,align left top",
            // columns
            "[]",
            // rows
            "[62,top]para" +
                "[]0" +
                "[]para" +
                "[]para" +
                "[]"));

        //---- summary ----
        summary.setText(bundle.getString("MTWizardFinishPanel.summary.text"));
        summary.setFont(new Font("Roboto", summary.getFont().getStyle(), summary.getFont().getSize() + 3));
        summary.setBackground(UIManager.getColor("Panel.background"));
        summary.setEditable(false);
        summary.setWrapStyleWord(true);
        content.add(summary, "cell 0 0");

        //---- summaryLabel ----
        summaryLabel.setText(bundle.getString("MTWizardFinishPanel.summaryLabel.text"));
        content.add(summaryLabel, "cell 0 1");

        //---- docLink ----
        docLink.setText(bundle.getString("MTWizardFinishPanel.docLink.text"));
        docLink.setIcon(null);
        content.add(docLink, "cell 0 1");

        //---- paypalLabel ----
        paypalLabel.setText(bundle.getString("MTWizardFinishPanel.paypalLabel.text"));
        content.add(paypalLabel, "cell 0 2");

        //---- paypalLink ----
        paypalLink.setText("Paypal");
        paypalLink.setIcon(null);
        content.add(paypalLink, "cell 0 2");

        //---- label1 ----
        label1.setText("or");
        content.add(label1, "cell 0 2");

        //---- openCollLink ----
        openCollLink.setText("OpenCollective");
        openCollLink.setIcon(null);
        content.add(openCollLink, "cell 0 2");
        content.add(vSpacer1, "cell 0 3");

        //---- summarySummary ----
        summarySummary.setText(bundle.getString("MTWizardFinishPanel.summarySummary.text"));
        summarySummary.setFont(summarySummary.getFont().deriveFont(summarySummary.getFont().getSize() + 5f));
        content.add(summarySummary, "cell 0 4,alignx center,growx 0");
      }
      scrollPane.setViewportView(content);
    }
    add(scrollPane);
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    docLink.setListener((aSource, aLinkData) -> {
      if (Desktop.isDesktopSupported()) {
        try {
          Desktop.getDesktop().browse(new URI("https://www.material-theme.com"));
        } catch (final IOException | URISyntaxException e) {
          e.printStackTrace();
        }
      }
    }, null);

    paypalLink.setListener((aSource, aLinkData) -> {
      if (Desktop.isDesktopSupported()) {
        try {
          Desktop.getDesktop().browse(new URI("https://paypal.me/mallowigi"));
        } catch (final IOException | URISyntaxException e) {
          e.printStackTrace();
        }
      }
    }, null);

    openCollLink.setListener((aSource, aLinkData) -> {
      if (Desktop.isDesktopSupported()) {
        try {
          Desktop.getDesktop().browse(new URI("https://opencollective.com/material-theme-jetbrains"));
        } catch (final IOException | URISyntaxException e) {
          e.printStackTrace();
        }
      }
    }, null);

  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JBScrollPane scrollPane;
  private JPanel content;
  private JTextArea summary;
  private JLabel summaryLabel;
  private LinkLabel docLink;
  private JLabel paypalLabel;
  private LinkLabel paypalLink;
  private JLabel label1;
  private LinkLabel openCollLink;
  private JPanel vSpacer1;
  private JLabel summarySummary;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
