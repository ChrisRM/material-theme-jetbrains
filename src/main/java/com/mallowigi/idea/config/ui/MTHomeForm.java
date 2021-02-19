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

/**
 * @author Elior Boukhobza
 */
@SuppressWarnings({"ConstantConditions",
  "FieldCanBeLocal",
  "DuplicateStringLiteralInspection"})
public class MTHomeForm implements MTFormUI {
  public MTHomeForm() {
    initComponents();
  }

  @Override
  public void init() {
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

  @SuppressWarnings("AnonymousInnerClassMayBeStatic")
  @Override
  public final void setupComponents() {
    logo.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(final MouseEvent e) {
        try {
          Desktop.getDesktop().browse(new URI(MaterialThemeBundle.message("plugin.website")));
        } catch (final IOException | URISyntaxException ioException) {
          //
        }
      }
    });

    final boolean isPremium = MTLicenseChecker.isLicensed();
    if (isPremium) {
      licensedLabel.setText(MTLicenseChecker.getLicensedInfo());
      content.remove(buyLicenseButton);
      content.remove(activateLicenseButton);
    }
  }

  private static void buyLicenseButtonActionPerformed(final ActionEvent e) {
    try {
      Desktop.getDesktop().browse(new URI(MaterialThemeBundle.message("plugin.buyLink")));
    } catch (final IOException | URISyntaxException ioException) {
      ioException.printStackTrace();
    }
  }

  private static void activateLicenseButtonActionPerformed(final ActionEvent e) {
    MTLicenseChecker.requestLicense("Activate License");
  }

  @Override
  public final void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    logo = new JLabel();
    licensedLabel = new JLabel();
    buyLicenseButton = new JButton();
    activateLicenseButton = new JButton();

    //======== content ========
    {
      content.setLayout(new MigLayout(
        "hidemode 3",
        // columns
        "[357,left]",
        // rows
        "[304]" +
          "[23]" +
          "[]"));

      //---- logo ----
      logo.setIcon(new ImageIcon(getClass().getResource("/wizard/logo.png")));
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
  // JFormDesigner - End of variables declaration  //GEN-END:variables

  public void setFormState(final MTConfig config) {
  }

  public static boolean isModified(final MTBaseConfig<MTHomeForm, MTConfig> config) {
    return false;
  }
}
