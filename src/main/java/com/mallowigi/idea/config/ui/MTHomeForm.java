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
 * Created by JFormDesigner on Fri Feb 19 17:20:15 IST 2021
 */

package com.mallowigi.idea.config.ui;

import com.intellij.ui.components.labels.LinkLabel;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTLicenseChecker;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

@SuppressWarnings({"ConstantConditions",
  "FieldCanBeLocal",
  "DuplicateStringLiteralInspection",
  "StringConcatenation",
  "MethodOnlyUsedFromInnerClass",
  "AnonymousInnerClassMayBeStatic",
  "SyntheticAccessorCall",
  "unused",
  "ClassWithTooManyFields"})
public final class MTHomeForm implements MTFormUI {
  public MTHomeForm() {
    init();
  }

  @Override
  public void init() {
    MTLicenseChecker.extractLicenseInformation();
    initComponents();
    setupComponents();
  }

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void dispose() {

  }

  @Override
  public void setupComponents() {
    final boolean isPremium = MTLicenseChecker.isLicensed();
    if (isPremium) {
      licensedLabel.setText(MTLicenseChecker.getLicensedInfo());
      content.remove(buyLicenseButton);
      content.remove(activateLicenseButton);
      content.remove(activateLicenseLabel);
    }
  }

  private static void buyLicenseButtonActionPerformed(final ActionEvent e) {
    openWebsite("plugin.buyLink");
  }

  private void activateLicenseButtonActionPerformed(final ActionEvent e) {
    MTLicenseChecker.requestLicense("Activate License");
    activateLicenseButton.setText(MaterialThemeBundle.message("activateLicense.afterSaveButton"));
    activateLicenseButton.setEnabled(false);
    activateLicenseLabel.setVisible(true);
  }

  private static void logoMouseClicked(final MouseEvent e) {
    openWebsite("plugin.website");
  }

  private static void websiteLinkMouseClicked(final MouseEvent e) {
    openWebsite("plugin.website");
  }

  private static void openWebsite(final String s) {
    try {
      Desktop.getDesktop().browse(new URI(MaterialThemeBundle.message(s)));
    } catch (final IOException | URISyntaxException ioException) {
      ioException.printStackTrace();
    }
  }

  @SuppressWarnings({"Convert2MethodRef",
    "MagicNumber",
    "OverlyLongMethod"})
  @Override
  public void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    logo = new JLabel();
    licensedLabel = new JLabel();
    buyLicenseButton = new JButton();
    activateLicenseButton = new JButton();
    separator1 = new JSeparator();
    vSpacer1 = new JPanel(null);
    pluginName = new JLabel();
    pluginVersionLabel = new JLabel();
    pluginVersion = new JLabel();
    copyrightLabel = new JLabel();
    websiteLink = new LinkLabel();
    activateLicenseLabel = new JLabel();

    //======== content ========
    {
      content.setLayout(new MigLayout(
        "hidemode 3",
        // columns
        "[357,left]",
        // rows
        "[304]" +
          "[23]" +
          "[]" +
          "[]" +
          "[]" +
          "[]" +
          "[]"));

      //---- logo ----
      logo.setIcon(new ImageIcon(getClass().getResource("/wizard/logo.png")));
      logo.setMinimumSize(new Dimension(768, 480));
      logo.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(final MouseEvent e) {
          logoMouseClicked(e);
        }
      });
      content.add(logo, "cell 0 0");

      //---- licensedLabel ----
      licensedLabel.setText(bundle.getString("MTHomeForm.licensedLabel.text"));
      content.add(licensedLabel, "cell 0 1");

      //---- buyLicenseButton ----
      buyLicenseButton.setText(bundle.getString("MTHomeForm.buyLicenseButton.text"));
      buyLicenseButton.addActionListener(e -> buyLicenseButtonActionPerformed(e));
      content.add(buyLicenseButton, "cell 0 2");

      //---- activateLicenseButton ----
      activateLicenseButton.setText(bundle.getString("MTHomeForm.activateLicenseButton.text"));
      activateLicenseButton.addActionListener(e -> activateLicenseButtonActionPerformed(e));
      content.add(activateLicenseButton, "cell 0 2");
      content.add(separator1, "cell 0 3");
      content.add(vSpacer1, "cell 0 4");

      //---- pluginName ----
      pluginName.setText(bundle.getString("plugin.name"));
      pluginName.setIconTextGap(0);
      pluginName.setFont(new Font("Roboto", Font.BOLD, 13));
      pluginName.setForeground(UIManager.getColor("Tree.foreground"));
      content.add(pluginName, "cell 0 5");

      //---- pluginVersionLabel ----
      pluginVersionLabel.setText(bundle.getString("MTHomeForm.pluginVersionLabel.text"));
      pluginVersionLabel.setFont(new Font("Roboto", Font.BOLD, 13));
      pluginVersionLabel.setForeground(UIManager.getColor("Tree.foreground"));
      content.add(pluginVersionLabel, "cell 0 5,gapx 2");

      //---- pluginVersion ----
      pluginVersion.setText(bundle.getString("plugin.version"));
      pluginVersion.setFont(new Font("Roboto", Font.BOLD, 13));
      pluginVersion.setForeground(UIManager.getColor("Tree.foreground"));
      content.add(pluginVersion, "cell 0 5,gapx 2");

      //---- copyrightLabel ----
      copyrightLabel.setText(bundle.getString("MTHomeForm.copyrightLabel.text"));
      copyrightLabel.setForeground(UIManager.getColor("Tree.foreground"));
      content.add(copyrightLabel, "cell 0 5,gapx 2");

      //---- websiteLink ----
      websiteLink.setText(bundle.getString("plugin.website"));
      websiteLink.setIcon(null);
      websiteLink.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(final MouseEvent e) {
          websiteLinkMouseClicked(e);
        }
      });
      content.add(websiteLink, "cell 0 6");

      //---- activateLicenseLabel ----
      activateLicenseLabel.setText(bundle.getString("MTHomeForm.activateLicenseLabel.text"));
      activateLicenseLabel.setEnabled(false);
      activateLicenseLabel.setVisible(false);
      content.add(activateLicenseLabel, "cell 0 2");
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JLabel logo;
  private JLabel licensedLabel;
  private JButton buyLicenseButton;
  private JButton activateLicenseButton;
  private JSeparator separator1;
  private JPanel vSpacer1;
  private JLabel pluginName;
  private JLabel pluginVersionLabel;
  private JLabel pluginVersion;
  private JLabel copyrightLabel;
  private LinkLabel websiteLink;
  private JLabel activateLicenseLabel;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

  public void setFormState(final MTConfig config) {
  }

  @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
  public static boolean isModified(final MTBaseConfig<MTHomeForm, MTConfig> config) {
    return false;
  }
}
