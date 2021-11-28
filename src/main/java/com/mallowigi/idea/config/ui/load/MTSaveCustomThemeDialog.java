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
 * Created by JFormDesigner on Sun Feb 17 20:27:29 IST 2019
 */

package com.mallowigi.idea.config.ui.load;

import com.intellij.openapi.ui.ComponentValidator;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.mallowigi.idea.MTBundledThemesManager;
import com.mallowigi.idea.config.custom.MTCustomThemeConfig;
import com.mallowigi.idea.config.ui.MTCustomThemeForm;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.models.MTBundledTheme;
import com.twelvemonkeys.lang.StringUtil;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.ResourceBundle;

@SuppressWarnings({"CheckStyle",
  "Duplicates",
  "FieldCanBeLocal",
  "UseDPIAwareInsets",
  "InstanceVariableMayNotBeInitialized",
  "ClassWithTooManyFields",
  "unused",
  "OverlyLongMethod",
  "HardCodedStringLiteral",
  "StringConcatenation",
  "DuplicateStringLiteralInspection",
  "SyntheticAccessorCall",
  "NonBooleanMethodNameMayNotStartWithQuestion"})
public final class MTSaveCustomThemeDialog extends DialogWrapper {
  private final MTCustomThemeForm form;
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
  private JPanel darkCheckBoxPanel;
  private JLabel darkLabel;
  private JCheckBox darkThemeCheckbox;

  MTSaveCustomThemeDialog(final MTCustomThemeForm mtCustomThemeForm) {
    super(null, true, IdeModalityType.IDE);
    form = mtCustomThemeForm;

    init();

    getOKAction().setEnabled(false);
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    initComponents();

    return dialogPane;
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  protected void doOKAction() {
    final MTBundledTheme customTheme = MTCustomThemeConfig.export(form);

    customTheme.setName(nameField.getText());
    customTheme.setThemeId(idField.getText());
    customTheme.setId(idField.getText());
    customTheme.setEditorColorsScheme(colorField.getText());
    customTheme.setDark(darkThemeCheckbox.isSelected());

    MTBundledThemesManager.saveTheme(customTheme);
    close(0, true);
  }

  private void checkFields() {
    getOKAction().setEnabled(!Objects.equals(nameField.getText(), "") && !Objects.equals(idField.getText(), ""));
  }

  private void checkFields(final FocusEvent e) {
    checkFields();
  }

  private void checkFields(final KeyEvent e) {
    checkFields();
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
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
    darkCheckBoxPanel = new JPanel();
    darkLabel = new JLabel();
    darkThemeCheckbox = new JCheckBox();

    //======== dialogPane ========
    {
      dialogPane.setLayout(new BorderLayout());

      //======== contentPanel ========
      {
        contentPanel.setLayout(new MigLayout(
          "fillx,insets dialog,hidemode 3,align center top",
          // columns
          "[fill]",
          // rows
          "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- pleaseFillLabel ----
        pleaseFillLabel.setText(bundle.getString("MTSaveDialog.pleaseFillLabel.text"));
        contentPanel.add(pleaseFillLabel, "cell 0 0");

        //======== namePanel ========
        {
          namePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

          //---- nameLabel ----
          nameLabel.setText(bundle.getString("MTSaveDialog.nameLabel.text"));
          nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
          nameLabel.setVerticalTextPosition(SwingConstants.TOP);
          nameLabel.setVerticalAlignment(SwingConstants.TOP);
          nameLabel.setFont(UIManager.getFont("TableHeader.font"));
          nameLabel.setForeground(UIManager.getColor("controlText"));
          namePanel.add(nameLabel, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

          //---- nameField ----
          nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent e) {
              checkFields(e);
            }
          });
          nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
              checkFields(e);
            }
          });
          namePanel.add(nameField, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        }
        contentPanel.add(namePanel, "cell 0 1,aligny top,grow 100 0");

        //======== idPanel ========
        {
          idPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

          //---- idLabel ----
          idLabel.setText(bundle.getString("MTSaveDialog.idLabel.text"));
          idLabel.setHorizontalAlignment(SwingConstants.LEFT);
          idLabel.setVerticalTextPosition(SwingConstants.TOP);
          idLabel.setVerticalAlignment(SwingConstants.TOP);
          idLabel.setFont(UIManager.getFont("TableHeader.font"));
          idLabel.setForeground(UIManager.getColor("controlText"));
          idPanel.add(idLabel, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

          //---- idField ----
          idField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent e) {
              checkFields(e);
            }
          });
          idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
              checkFields(e);
            }
          });
          idPanel.add(idField, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        }
        contentPanel.add(idPanel, "cell 0 2,aligny top,grow 100 0");

        //======== colorSchemePanel ========
        {
          colorSchemePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

          //---- colorLabel ----
          colorLabel.setText(bundle.getString("MTSaveDialog.colorLabel.text"));
          colorLabel.setHorizontalAlignment(SwingConstants.LEFT);
          colorLabel.setVerticalTextPosition(SwingConstants.TOP);
          colorLabel.setVerticalAlignment(SwingConstants.TOP);
          colorLabel.setFont(UIManager.getFont("TableHeader.font"));
          colorLabel.setForeground(UIManager.getColor("controlText"));
          colorSchemePanel.add(colorLabel, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
          colorSchemePanel.add(colorField, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        }
        contentPanel.add(colorSchemePanel, "cell 0 3,aligny top,grow 100 0");

        //======== darkCheckBoxPanel ========
        {
          darkCheckBoxPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

          //---- darkLabel ----
          darkLabel.setText(bundle.getString("MTSaveDialog.darkLabel.text"));
          darkLabel.setHorizontalAlignment(SwingConstants.LEFT);
          darkLabel.setVerticalTextPosition(SwingConstants.TOP);
          darkLabel.setVerticalAlignment(SwingConstants.TOP);
          darkLabel.setFont(UIManager.getFont("TableHeader.font"));
          darkLabel.setForeground(UIManager.getColor("controlText"));
          darkCheckBoxPanel.add(darkLabel, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

          //---- darkThemeCheckbox ----
          darkThemeCheckbox.setText(bundle.getString("MTSaveDialog.darkThemeCheckbox.text"));
          darkThemeCheckbox.setSelected(true);
          darkCheckBoxPanel.add(darkThemeCheckbox, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        }
        contentPanel.add(darkCheckBoxPanel, "cell 0 4,aligny top,grow 100 0");
      }
      dialogPane.add(contentPanel, BorderLayout.CENTER);
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    addValidators();
  }

  @SuppressWarnings("ReturnOfNull")
  private void addValidators() {
    new ComponentValidator(getDisposable())
      .withValidator(() -> {
        final String tt = nameField.getText();
        if (StringUtil.isEmpty(tt)) {
          return new ValidationInfo(MaterialThemeBundle.message("MTSaveDialog.nameMissing"), nameField).asWarning();
        }
        return null;
      })
      .andRegisterOnDocumentListener(nameField)
      .installOn(nameField);

    new ComponentValidator(getDisposable())
      .withValidator(() -> {
        final String tt = idField.getText();
        if (StringUtil.isEmpty(tt)) {
          return new ValidationInfo(MaterialThemeBundle.message("MTSaveDialog.themeIdMissing"), idField).asWarning();
        }
        return null;
      })
      .andRegisterOnDocumentListener(idField)
      .installOn(idField);
  }
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
