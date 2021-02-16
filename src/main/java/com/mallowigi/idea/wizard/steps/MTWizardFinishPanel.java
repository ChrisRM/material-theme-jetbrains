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

/*
 * Created by JFormDesigner on Wed Jul 25 00:33:59 IDT 2018
 */

package com.mallowigi.idea.wizard.steps;

import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.labels.LinkLabel;
import com.mallowigi.idea.messages.MTWizardBundle;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

/**
 * @author Elior Boukhobza
 */
@SuppressWarnings({"FieldCanBeLocal",
  "ClassWithTooManyFields",
  "CheckStyle",
  "unchecked",
  "rawtypes"})
public final class MTWizardFinishPanel extends AbstractCustomizeWizardStep {
  public MTWizardFinishPanel() {
    initComponents();
  }

  @Override
  public String getTitle() {
    return MTWizardBundle.message("finish.panel.title");
  }

  @Override
  public String getHTMLHeader() {
    return null;
  }

  @NotNull
  @Override
  public String getHTMLFooter() {
    return MTWizardBundle.message("finish.panel.footer");
  }

  @SuppressWarnings({"OverlyLongMethod",
    "DuplicateStringLiteralInspection",
    "ConfusingFloatingPointLiteral",
    "OverlyLongLambda",
    "AbsoluteAlignmentInUserInterface",
    "StringConcatenation",
    "HardCodedStringLiteral",
    "MagicNumber"})
  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MTWizardBundle");
    scrollPane = new JBScrollPane();
    content = new JPanel();
    summary = new JTextPane();
    summaryLabel = new JLabel();
    docLink = new LinkLabel();
    paypalLabel = new JLabel();
    paypalLink = new LinkLabel();
    label1 = new JLabel();
    openCollLink = new LinkLabel();
    pluginsLabel = new JLabel();
    pluginsLink = new LinkLabel();
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
          "hidemode 3,align center top",
          // columns
          "[]",
          // rows
          "[62,top]para" +
            "[]0" +
            "[]para" +
            "[]para" +
            "[]para" +
            "[]" +
            "[]"));

        //---- summary ----
        summary.setText(bundle.getString("MTWizardFinishPanel.summary.text"));
        summary.setFont(new Font("Roboto", summary.getFont().getStyle(), summary.getFont().getSize() + 3));
        summary.setBackground(UIManager.getColor("Panel.background"));
        summary.setEditable(false);
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
        paypalLink.setText(bundle.getString("MTWizardFinishPanel.paypalLink.text"));
        paypalLink.setIcon(null);
        content.add(paypalLink, "cell 0 2");

        //---- label1 ----
        label1.setText(bundle.getString("MTWizardFinishPanel.label1.text"));
        content.add(label1, "cell 0 2");

        //---- openCollLink ----
        openCollLink.setText(bundle.getString("MTWizardFinishPanel.openCollLink.text"));
        openCollLink.setIcon(null);
        content.add(openCollLink, "cell 0 2");

        //---- pluginsLabel ----
        pluginsLabel.setText(bundle.getString("MTWizardFinishPanel.pluginsLabel.text"));
        content.add(pluginsLabel, "cell 0 3");

        //---- pluginsLink ----
        pluginsLink.setText(bundle.getString("MTWizardFinishPanel.pluginsLink.text"));
        pluginsLink.setIcon(null);
        content.add(pluginsLink, "cell 0 3");
        content.add(vSpacer1, "cell 0 4");

        //---- summarySummary ----
        summarySummary.setText(bundle.getString("MTWizardFinishPanel.summarySummary.text"));
        summarySummary.setFont(summarySummary.getFont().deriveFont(summarySummary.getFont().getSize() + 5f));
        content.add(summarySummary, "cell 0 5,alignx center,growx 0");
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
          Desktop.getDesktop().browse(new URI("https://opencollective.com/atom-material-themes-and-plugins"));
        } catch (final IOException | URISyntaxException e) {
          e.printStackTrace();
        }
      }
    }, null);

    pluginsLink.setListener((aSource, aLinkData) -> {
      if (Desktop.isDesktopSupported()) {
        try {
          Desktop.getDesktop().browse(new URI("https://plugins.jetbrains.com/search?search=mallowigi"));
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
  private JTextPane summary;
  private JLabel summaryLabel;
  private LinkLabel docLink;
  private JLabel paypalLabel;
  private LinkLabel paypalLink;
  private JLabel label1;
  private LinkLabel openCollLink;
  private JLabel pluginsLabel;
  private LinkLabel pluginsLink;
  private JPanel vSpacer1;
  private JLabel summarySummary;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
