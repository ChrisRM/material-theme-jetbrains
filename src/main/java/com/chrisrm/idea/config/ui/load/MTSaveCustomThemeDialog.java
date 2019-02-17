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
 * Created by JFormDesigner on Sun Feb 17 20:27:29 IST 2019
 */

package com.chrisrm.idea.config.ui.load;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.ui.ComponentValidator;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author Elior Boukhobza
 */
@SuppressWarnings({"CheckStyle",
    "FieldCanBeLocal",
    "Duplicates",
    "UseDPIAwareInsets"})
public final class MTSaveCustomThemeDialog extends JDialog {
  @NotNull
  private final Disposable myDisposable = new Disposable() {
    @Override
    public String toString() {
      return MTSaveCustomThemeDialog.this.toString();
    }

    @Override
    public void dispose() {
      MTSaveCustomThemeDialog.this.dispose();
    }
  };

  public MTSaveCustomThemeDialog(final Window owner) {
    super(owner);
    initComponents();
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle"); //NON-NLS
    dialogPane = new JPanel();
    contentPanel = new JPanel();
    pleaseFillLabel = new JLabel();
    namePanel = new JPanel();
    nameLabel = new JLabel();
    nameField = new JTextField();
    idPanel = new JPanel();
    idLabel = new JLabel();
    idField = new JTextField();
    colorSchemePanel = new JPanel();
    colorLabel = new JLabel();
    colorField = new JTextField();
    buttonBar = new JPanel();
    okButton = new JButton();
    cancelButton = new JButton();

    //======== this ========
    setName("this"); //NON-NLS
    final Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    //======== dialogPane ========
    {
      dialogPane.setName("dialogPane"); //NON-NLS
      dialogPane.setLayout(new BorderLayout());

      //======== contentPanel ========
      {
        contentPanel.setName("contentPanel"); //NON-NLS
        contentPanel.setLayout(new MigLayout(
            "fillx,insets dialog,hidemode 3,align center top", //NON-NLS
            // columns
            "[fill]", //NON-NLS
            // rows
            "[]" + //NON-NLS
                "[]" + //NON-NLS
                "[]" + //NON-NLS
                "[]")); //NON-NLS

        //---- pleaseFillLabel ----
        pleaseFillLabel.setText(bundle.getString("MTSaveDialog.pleaseFillLabel.text")); //NON-NLS
        pleaseFillLabel.setName("pleaseFillLabel"); //NON-NLS
        contentPanel.add(pleaseFillLabel, "cell 0 0"); //NON-NLS

        //======== namePanel ========
        {
          namePanel.setName("namePanel"); //NON-NLS
          namePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

          //---- nameLabel ----
          nameLabel.setText(bundle.getString("MTSaveDialog.nameLabel.text")); //NON-NLS
          nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
          nameLabel.setVerticalTextPosition(SwingConstants.TOP);
          nameLabel.setVerticalAlignment(SwingConstants.TOP);
          nameLabel.setFont(UIManager.getFont("TableHeader.font")); //NON-NLS
          nameLabel.setForeground(UIManager.getColor("controlText")); //NON-NLS
          nameLabel.setName("nameLabel"); //NON-NLS
          namePanel.add(nameLabel, new GridConstraints(0, 0, 1, 1,
              GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              null, null, null));

          //---- nameField ----
          nameField.setName("nameField"); //NON-NLS
          namePanel.add(nameField, new GridConstraints(1, 0, 1, 1,
              GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              null, null, null));
        }
        contentPanel.add(namePanel, "cell 0 1,aligny top,grow 100 0"); //NON-NLS

        //======== idPanel ========
        {
          idPanel.setName("idPanel"); //NON-NLS
          idPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

          //---- idLabel ----
          idLabel.setText(bundle.getString("MTSaveDialog.idLabel.text")); //NON-NLS
          idLabel.setHorizontalAlignment(SwingConstants.LEFT);
          idLabel.setVerticalTextPosition(SwingConstants.TOP);
          idLabel.setVerticalAlignment(SwingConstants.TOP);
          idLabel.setFont(UIManager.getFont("TableHeader.font")); //NON-NLS
          idLabel.setForeground(UIManager.getColor("controlText")); //NON-NLS
          idLabel.setName("idLabel"); //NON-NLS
          idPanel.add(idLabel, new GridConstraints(0, 0, 1, 1,
              GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              null, null, null));

          //---- idField ----
          idField.setName("idField"); //NON-NLS
          idPanel.add(idField, new GridConstraints(1, 0, 1, 1,
              GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              null, null, null));
        }
        contentPanel.add(idPanel, "cell 0 2,aligny top,grow 100 0"); //NON-NLS

        //======== colorSchemePanel ========
        {
          colorSchemePanel.setName("colorSchemePanel"); //NON-NLS
          colorSchemePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

          //---- colorLabel ----
          colorLabel.setText(bundle.getString("MTSaveDialog.colorLabel.text")); //NON-NLS
          colorLabel.setHorizontalAlignment(SwingConstants.LEFT);
          colorLabel.setVerticalTextPosition(SwingConstants.TOP);
          colorLabel.setVerticalAlignment(SwingConstants.TOP);
          colorLabel.setFont(UIManager.getFont("TableHeader.font")); //NON-NLS
          colorLabel.setForeground(UIManager.getColor("controlText")); //NON-NLS
          colorLabel.setName("colorLabel"); //NON-NLS
          colorSchemePanel.add(colorLabel, new GridConstraints(0, 0, 1, 1,
              GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              null, null, null));

          //---- colorField ----
          colorField.setName("colorField"); //NON-NLS
          colorSchemePanel.add(colorField, new GridConstraints(1, 0, 1, 1,
              GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
              null, null, null));
        }
        contentPanel.add(colorSchemePanel, "cell 0 3,aligny top,grow 100 0"); //NON-NLS
      }
      dialogPane.add(contentPanel, BorderLayout.CENTER);

      //======== buttonBar ========
      {
        buttonBar.setName("buttonBar"); //NON-NLS
        buttonBar.setLayout(new MigLayout(
            "insets dialog,alignx right", //NON-NLS
            // columns
            "[button,fill]" + //NON-NLS
                "[button,fill]", //NON-NLS
            // rows
            null));

        //---- okButton ----
        okButton.setText(bundle.getString("MTSaveDialog.okButton.text")); //NON-NLS
        okButton.setName("okButton"); //NON-NLS
        buttonBar.add(okButton, "cell 0 0"); //NON-NLS

        //---- cancelButton ----
        cancelButton.setText(bundle.getString("MTSaveDialog.cancelButton.text")); //NON-NLS
        cancelButton.setName("cancelButton"); //NON-NLS
        buttonBar.add(cancelButton, "cell 1 0"); //NON-NLS
      }
      dialogPane.add(buttonBar, BorderLayout.SOUTH);
    }
    contentPane.add(dialogPane, BorderLayout.CENTER);
    pack();
    setLocationRelativeTo(getOwner());
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    new ComponentValidator(myDisposable).withValidator(() -> {
      final String tt = nameField.getText();
      return StringUtil.isEmpty(tt) ? new ValidationInfo("You must enter a name") : null;
    })
                                        .andStartOnFocusLost()
                                        .andRegisterOnDocumentListener(nameField)
                                        .installOn(nameField);
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel dialogPane;
  private JPanel contentPanel;
  private JLabel pleaseFillLabel;
  private JPanel namePanel;
  private JLabel nameLabel;
  private JTextField nameField;
  private JPanel idPanel;
  private JLabel idLabel;
  private JTextField idField;
  private JPanel colorSchemePanel;
  private JLabel colorLabel;
  private JTextField colorField;
  private JPanel buttonBar;
  private JButton okButton;
  private JButton cancelButton;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

}
