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

package com.mallowigi.idea.config.ui;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.ColorUtil;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.mallowigi.idea.MTLicenseChecker;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.config.custom.MTCustomThemeConfig;
import com.mallowigi.idea.config.ui.load.MTLoadCustomThemeComboBoxAction;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.ui.ColorPanelWithOpacity;
import com.mallowigi.idea.ui.DisabledPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ResourceBundle;

import static com.mallowigi.idea.utils.MTUiUtils.disablePremium;

@SuppressWarnings({"OverlyLongMethod",
  "UseDPIAwareInsets",
  "MagicNumber",
  "DuplicateStringLiteralInspection",
  "FieldCanBeLocal",
  "OverlyLongLambda",
  "ClassWithTooManyFields",
  "PublicMethodNotExposedInInterface",
  "Duplicates",
  "ClassWithTooManyMethods",
  "UseJBColor",
  "InstanceVariableMayNotBeInitialized"})
public final class MTCustomThemeForm implements MTFormUI {
  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JPanel customThemeForm;
  private JLabel explLabel;
  private JTextArea expTextArea;
  private JSeparator separator1;
  private JPanel panel2;
  private JLabel backgroundColorLabel;
  private ColorPanel backgroundColor;
  private JPanel panel1;
  private JLabel foregroundColorLabel;
  private ColorPanel foregroundColor;
  private JPanel panel3;
  private JLabel labelColorLabel;
  private ColorPanel labelColor;
  private JPanel panel4;
  private JLabel selectionBackgroundColorLabel;
  private ColorPanelWithOpacity selectionBackgroundColor;
  private JPanel panel5;
  private JLabel selectionForegroundColorLabel;
  private ColorPanel selectionForegroundColor;
  private JPanel panel6;
  private JLabel buttonColorLabel;
  private ColorPanelWithOpacity buttonColor;
  private JPanel panel7;
  private JLabel listBackgroundColorLabel;
  private ColorPanelWithOpacity listBackgroundColor;
  private JPanel panel8;
  private JLabel disabledColorLabel;
  private ColorPanel disabledColor;
  private JPanel panel9;
  private JLabel contrastColorLabel;
  private ColorPanel contrastColor;
  private JPanel panel10;
  private JLabel tableSelectionColorLabel;
  private ColorPanelWithOpacity tableSelectionColor;
  private JPanel panel11;
  private JLabel miscColorLabel;
  private ColorPanelWithOpacity miscColor1;
  private JPanel panel12;
  private JLabel miscColorLabel2;
  private ColorPanelWithOpacity miscColor2;
  private JPanel panel13;
  private JLabel treeSelectionLabel;
  private ColorPanelWithOpacity treeSelectionColor;
  private JPanel panel14;
  private JLabel notificationsLabel;
  private ColorPanelWithOpacity notificationsColor;
  private JPanel panel15;
  private JLabel accentLabel;
  private ColorPanel accentColor;
  private JPanel panel16;
  private JLabel excludedLabel;
  private ColorPanel excludedColor;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
  private DisabledPanel disabledPanel;

  @Override
  public void init() {
    initComponents();
    setupComponents();
  }

  @Override
  public JComponent getContent() {
    return disabledPanel;
  }

  @Override
  public void dispose() {

  }

  @SuppressWarnings({"OverlyComplexMethod",
    "FeatureEnvy"})
  public boolean isModified(final MTBaseConfig config) {
    final MTCustomThemeConfig customThemeConfig = (MTCustomThemeConfig) config;
    boolean modified = customThemeConfig.isBackgroundColorChanged(getBackgroundColor());
    modified = modified || customThemeConfig.isForegroundColorChanged(getForegroundColor());
    modified = modified || customThemeConfig.isTextColorChanged(getTextColor());
    modified = modified || customThemeConfig.isSelectionBackgroundColorChanged(getSelectionBackgroundColor());
    modified = modified || customThemeConfig.isSelectionForegroundColorChanged(getSelectionForegroundColor());
    modified = modified || customThemeConfig.isButtonColorChanged(getButtonColor());
    modified = modified || customThemeConfig.isSecondaryBackgroundColorChanged(getSecondaryBackgroundColor());
    modified = modified || customThemeConfig.isDisabledColorChanged(getDisabledColor());
    modified = modified || customThemeConfig.isContrastColorChanged(getContrastColor());
    modified = modified || customThemeConfig.isTableSelectionColorChanged(getTableSelectedColor());
    modified = modified || customThemeConfig.isSecondBorderColorChanged(getSecondBorderColor());
    modified = modified || customThemeConfig.isHighlightColorChanged(getHighlightColor());
    modified = modified || customThemeConfig.isTreeSelectionColorChanged(getTreeSelectionColor());
    modified = modified || customThemeConfig.isNotificationsColorChanged(getNotificationsColor());
    modified = modified || customThemeConfig.isAccentColorChanged(getAccentColor());
    modified = modified || customThemeConfig.isExcludedColorChanged(getExcludedColor());

    return modified;
  }

  @SuppressWarnings("FeatureEnvy")
  public void setFormState(final MTBaseConfig config) {
    final MTCustomThemeConfig customThemeConfig = (MTCustomThemeConfig) config;
    setBackgroundColor(customThemeConfig.getBackgroundColor());
    setForegroundColor(customThemeConfig.getForegroundColor());
    setTextColor(customThemeConfig.getTextColor());
    setContrastColor(customThemeConfig.getContrastColor());
    setDisabledColor(customThemeConfig.getDisabledColor());
    setButtonColor(customThemeConfig.getButtonColor());
    setSecondaryBackgroundColor(customThemeConfig.getSecondaryBackgroundColor());
    setSecondBorderColor(customThemeConfig.getSecondBorderColor());
    setSelectionBackgroundColor(customThemeConfig.getSelectionBackgroundColor());
    setSelectionForegroundColor(customThemeConfig.getSelectionForegroundColor());
    setTableSelectedColor(customThemeConfig.getTableSelectedColor());
    setTreeSelectionColor(customThemeConfig.getTreeSelectionColor());
    setHighlightColor(customThemeConfig.getHighlightColor());
    setNotificationsColor(customThemeConfig.getNotificationsColor());
    setAccentColor(customThemeConfig.getAccentColor());
    setExcludedColor(customThemeConfig.getExcludedColor());

    afterStateSet();
  }

  public Color getBackgroundColor() {
    return backgroundColor.getSelectedColor();
  }

  private void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor.setSelectedColor(backgroundColor);
  }

  public Color getForegroundColor() {
    return foregroundColor.getSelectedColor();
  }

  private void setForegroundColor(final Color foregroundColor) {
    this.foregroundColor.setSelectedColor(foregroundColor);
  }

  public Color getTextColor() {
    return labelColor.getSelectedColor();
  }

  private void setTextColor(final Color labelColor) {
    this.labelColor.setSelectedColor(labelColor);
  }

  public Color getSelectionBackgroundColor() {
    return selectionBackgroundColor.getSelectedColor();
  }

  private void setSelectionBackgroundColor(final Color selectionBackgroundColor) {
    this.selectionBackgroundColor.setSelectedColor(selectionBackgroundColor);
  }

  public Color getSelectionForegroundColor() {
    return selectionForegroundColor.getSelectedColor();
  }

  private void setSelectionForegroundColor(final Color selectionForegroundColor) {
    this.selectionForegroundColor.setSelectedColor(selectionForegroundColor);
  }

  public Color getButtonColor() {
    return buttonColor.getSelectedColor();
  }

  private void setButtonColor(final Color buttonColor) {
    this.buttonColor.setSelectedColor(buttonColor);
  }

  public Color getSecondaryBackgroundColor() {
    return listBackgroundColor.getSelectedColor();
  }

  private void setSecondaryBackgroundColor(final Color listBackgroundColor) {
    this.listBackgroundColor.setSelectedColor(listBackgroundColor);
  }

  public Color getDisabledColor() {
    return disabledColor.getSelectedColor();
  }

  private void setDisabledColor(final Color disabledColor) {
    this.disabledColor.setSelectedColor(disabledColor);
  }

  public Color getContrastColor() {
    return contrastColor.getSelectedColor();
  }

  private void setContrastColor(final Color contrastColor) {
    this.contrastColor.setSelectedColor(contrastColor);
  }

  public Color getTableSelectedColor() {
    return tableSelectionColor.getSelectedColor();
  }

  private void setTableSelectedColor(final Color tableSelectionColor) {
    this.tableSelectionColor.setSelectedColor(tableSelectionColor);
  }

  public Color getSecondBorderColor() {
    return miscColor1.getSelectedColor();
  }

  private void setSecondBorderColor(final Color miscColor1) {
    this.miscColor1.setSelectedColor(miscColor1);
  }

  public Color getHighlightColor() {
    return miscColor2.getSelectedColor();
  }

  private void setHighlightColor(final Color miscColor2) {
    this.miscColor2.setSelectedColor(miscColor2);
  }

  public Color getTreeSelectionColor() {
    return treeSelectionColor.getSelectedColor();
  }

  private void setTreeSelectionColor(final Color treeSelectionColor) {
    this.treeSelectionColor.setSelectedColor(treeSelectionColor);
  }

  public Color getNotificationsColor() {
    return notificationsColor.getSelectedColor();
  }

  private void setNotificationsColor(final Color notificationsColor) {
    this.notificationsColor.setSelectedColor(notificationsColor);
  }

  public Color getAccentColor() {
    return accentColor.getSelectedColor();
  }

  private void setAccentColor(final Color accentColor) {
    this.accentColor.setSelectedColor(accentColor);
  }

  public Color getExcludedColor() {
    return excludedColor.getSelectedColor();
  }

  private void setExcludedColor(final Color excludedColor) {
    this.excludedColor.setSelectedColor(excludedColor);
  }

  private void afterStateSet() {

  }

  @Override
  @SuppressWarnings({"HardCodedStringLiteral",
    "StringConcatenation"})
  public void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    customThemeForm = new JPanel();
    explLabel = new JLabel();
    expTextArea = new JTextArea();
    separator1 = new JSeparator();
    panel2 = new JPanel();
    backgroundColorLabel = new JLabel();
    backgroundColor = new ColorPanel();
    panel1 = new JPanel();
    foregroundColorLabel = new JLabel();
    foregroundColor = new ColorPanel();
    panel3 = new JPanel();
    labelColorLabel = new JLabel();
    labelColor = new ColorPanel();
    panel4 = new JPanel();
    selectionBackgroundColorLabel = new JLabel();
    selectionBackgroundColor = new ColorPanelWithOpacity();
    panel5 = new JPanel();
    selectionForegroundColorLabel = new JLabel();
    selectionForegroundColor = new ColorPanel();
    panel6 = new JPanel();
    buttonColorLabel = new JLabel();
    buttonColor = new ColorPanelWithOpacity();
    panel7 = new JPanel();
    listBackgroundColorLabel = new JLabel();
    listBackgroundColor = new ColorPanelWithOpacity();
    panel8 = new JPanel();
    disabledColorLabel = new JLabel();
    disabledColor = new ColorPanel();
    panel9 = new JPanel();
    contrastColorLabel = new JLabel();
    contrastColor = new ColorPanel();
    panel10 = new JPanel();
    tableSelectionColorLabel = new JLabel();
    tableSelectionColor = new ColorPanelWithOpacity();
    panel11 = new JPanel();
    miscColorLabel = new JLabel();
    miscColor1 = new ColorPanelWithOpacity();
    panel12 = new JPanel();
    miscColorLabel2 = new JLabel();
    miscColor2 = new ColorPanelWithOpacity();
    panel13 = new JPanel();
    treeSelectionLabel = new JLabel();
    treeSelectionColor = new ColorPanelWithOpacity();
    panel14 = new JPanel();
    notificationsLabel = new JLabel();
    notificationsColor = new ColorPanelWithOpacity();
    panel15 = new JPanel();
    accentLabel = new JLabel();
    accentColor = new ColorPanel();
    panel16 = new JPanel();
    excludedLabel = new JLabel();
    excludedColor = new ColorPanel();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);
      content.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== customThemeForm ========
      {
        customThemeForm.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.customColorsTitle")));
        customThemeForm.setAlignmentY(0.0F);
        customThemeForm.setLayout(new MigLayout(
          "insets 0 2 0 2,hidemode 3,gap 10 0",
          // columns
          "[grow 1,fill]",
          // rows
          "[fill]rel" +
            "[fill]rel" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[]" +
            "[]" +
            "[fill]" +
            "[grow,fill]"));

        //---- explLabel ----
        explLabel.setText(bundle.getString("MTCustomThemeForm.explLabel.text"));
        explLabel.setForeground(UIManager.getColor("Button.disabledText"));
        explLabel.setBackground(UIManager.getColor("Tree.textForeground"));
        customThemeForm.add(explLabel, "cell 0 0,aligny center,grow 100 0");

        //---- expTextArea ----
        expTextArea.setBackground(UIManager.getColor("Panel.background"));
        expTextArea.setFont(UIManager.getFont("Panel.font"));
        expTextArea.setText(bundle.getString("MTCustomThemeForm.expTextArea.text"));
        expTextArea.setRows(2);
        expTextArea.setWrapStyleWord(true);
        expTextArea.setEditable(false);
        expTextArea.setBorder(null);
        customThemeForm.add(expTextArea, "cell 0 1,align left center,grow 0 0");
        customThemeForm.add(separator1, "cell 0 2,align center center,grow 0 0");

        //======== panel2 ========
        {
          panel2.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- backgroundColorLabel ----
          backgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.backgroundColorLabel.text"));
          backgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.backgroundColorLabel.toolTipText"));
          panel2.add(backgroundColorLabel, "cell 0 0,align left center,grow 0 0");
          panel2.add(backgroundColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel2, "cell 0 3");

        //======== panel1 ========
        {
          panel1.setBackground(UIManager.getColor("Viewport.background"));
          panel1.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- foregroundColorLabel ----
          foregroundColorLabel.setText(bundle.getString("MTCustomThemeForm.foregroundColorLabel.text"));
          foregroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.foregroundColorLabel.toolTipText"));
          foregroundColorLabel.setBackground(UIManager.getColor("Viewport.background"));
          panel1.add(foregroundColorLabel, "cell 0 0,align left center,grow 0 0");

          //---- foregroundColor ----
          foregroundColor.setBackground(new Color(34, 37, 51));
          panel1.add(foregroundColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel1, "cell 0 4");

        //======== panel3 ========
        {
          panel3.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- labelColorLabel ----
          labelColorLabel.setText(bundle.getString("MTCustomThemeForm.labelColorLabel.text"));
          labelColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.labelColorLabel.toolTipText"));
          labelColorLabel.setBackground(new Color(102, 102, 255));
          panel3.add(labelColorLabel, "cell 0 0,align left center,grow 0 0");
          panel3.add(labelColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel3, "cell 0 5");

        //======== panel4 ========
        {
          panel4.setBackground(UIManager.getColor("Viewport.background"));
          panel4.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- selectionBackgroundColorLabel ----
          selectionBackgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.selectionBackgroundColorLabel.text"));
          selectionBackgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.selectionBackgroundColorLabel.toolTipText"));
          panel4.add(selectionBackgroundColorLabel, "cell 0 0,align left center,grow 0 0");

          //---- selectionBackgroundColor ----
          selectionBackgroundColor.setBackground(new Color(34, 37, 51));
          panel4.add(selectionBackgroundColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel4, "cell 0 6");

        //======== panel5 ========
        {
          panel5.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- selectionForegroundColorLabel ----
          selectionForegroundColorLabel.setText(bundle.getString("MTCustomThemeForm.selectionForegroundColorLabel.text"));
          selectionForegroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.selectionForegroundColorLabel.toolTipText"));
          panel5.add(selectionForegroundColorLabel, "cell 0 0,align left center,grow 0 0");
          panel5.add(selectionForegroundColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel5, "cell 0 7");

        //======== panel6 ========
        {
          panel6.setBackground(UIManager.getColor("Viewport.background"));
          panel6.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- buttonColorLabel ----
          buttonColorLabel.setText(bundle.getString("MTCustomThemeForm.buttonColorLabel.text"));
          buttonColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.buttonColorLabel.toolTipText"));
          panel6.add(buttonColorLabel, "cell 0 0,align left center,grow 0 0");
          panel6.add(buttonColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel6, "cell 0 8");

        //======== panel7 ========
        {
          panel7.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- listBackgroundColorLabel ----
          listBackgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.listBackgroundColorLabel.text"));
          listBackgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.listBackgroundColorLabel.toolTipText"));
          panel7.add(listBackgroundColorLabel, "cell 0 0,align left center,grow 0 0");
          panel7.add(listBackgroundColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel7, "cell 0 9");

        //======== panel8 ========
        {
          panel8.setBackground(UIManager.getColor("Viewport.background"));
          panel8.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- disabledColorLabel ----
          disabledColorLabel.setText(bundle.getString("MTCustomThemeForm.disabledColorLabel.text"));
          disabledColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.disabledColorLabel.toolTipText"));
          panel8.add(disabledColorLabel, "cell 0 0,align left center,grow 0 0");
          panel8.add(disabledColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel8, "cell 0 10");

        //======== panel9 ========
        {
          panel9.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- contrastColorLabel ----
          contrastColorLabel.setText(bundle.getString("MTCustomThemeForm.contrastColorLabel.text"));
          contrastColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.contrastColorLabel.toolTipText"));
          panel9.add(contrastColorLabel, "cell 0 0,align left center,grow 0 0");
          panel9.add(contrastColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel9, "cell 0 11");

        //======== panel10 ========
        {
          panel10.setBackground(UIManager.getColor("Viewport.background"));
          panel10.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- tableSelectionColorLabel ----
          tableSelectionColorLabel.setText(bundle.getString("MTCustomThemeForm.tableSelectionColorLabel.text"));
          tableSelectionColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.tableSelectionColorLabel.toolTipText"));
          panel10.add(tableSelectionColorLabel, "cell 0 0,align left center,grow 0 0");
          panel10.add(tableSelectionColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel10, "cell 0 12");

        //======== panel11 ========
        {
          panel11.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- miscColorLabel ----
          miscColorLabel.setText(bundle.getString("MTCustomThemeForm.miscColorLabel.text"));
          miscColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.miscColorLabel.toolTipText"));
          panel11.add(miscColorLabel, "cell 0 0,align left center,grow 0 0");
          panel11.add(miscColor1, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel11, "cell 0 13");

        //======== panel12 ========
        {
          panel12.setBackground(UIManager.getColor("Viewport.background"));
          panel12.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- miscColorLabel2 ----
          miscColorLabel2.setText(bundle.getString("MTCustomThemeForm.miscColorLabel2.text"));
          miscColorLabel2.setToolTipText(bundle.getString("MTCustomThemeForm.miscColorLabel2.toolTipText"));
          panel12.add(miscColorLabel2, "cell 0 0,align left center,grow 0 0");
          panel12.add(miscColor2, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel12, "cell 0 14");

        //======== panel13 ========
        {
          panel13.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- treeSelectionLabel ----
          treeSelectionLabel.setText(bundle.getString("MTCustomThemeForm.treeSelectionLabel.text"));
          treeSelectionLabel.setToolTipText(bundle.getString("MTCustomThemeForm.treeSelectionLabel.toolTipText"));
          panel13.add(treeSelectionLabel, "cell 0 0,align left center,grow 0 0");
          panel13.add(treeSelectionColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel13, "cell 0 15");

        //======== panel14 ========
        {
          panel14.setBackground(UIManager.getColor("Viewport.background"));
          panel14.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[fill]"));

          //---- notificationsLabel ----
          notificationsLabel.setText(bundle.getString("MTCustomThemeForm.notificationsLabel.text"));
          notificationsLabel.setToolTipText(bundle.getString("MTCustomThemeForm.notificationsLabel.toolTipText"));
          panel14.add(notificationsLabel, "cell 0 0,align left center,grow 0 0");
          panel14.add(notificationsColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel14, "cell 0 16");

        //======== panel15 ========
        {
          panel15.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[]"));

          //---- accentLabel ----
          accentLabel.setText(bundle.getString("MTForm.accentLabel.text"));
          accentLabel.setToolTipText(bundle.getString("MTForm.accentLabel.toolTipText"));
          panel15.add(accentLabel, "cell 0 0,align left center,grow 0 0");
          panel15.add(accentColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel15, "cell 0 17");

        //======== panel16 ========
        {
          panel16.setBackground(UIManager.getColor("Viewport.background"));
          panel16.setLayout(new MigLayout(
            "insets 0 2 0 2,hidemode 3,gap 10 0",
            // columns
            "[grow 1,fill]" +
              "[grow 1,fill]",
            // rows
            "[]"));

          //---- excludedLabel ----
          excludedLabel.setText(bundle.getString("MTForm.excludedLabel.text"));
          excludedLabel.setToolTipText(bundle.getString("MTForm.excludedLabel.toolTipText"));
          panel16.add(excludedLabel, "cell 0 0,align left center,grow 0 0");
          panel16.add(excludedColor, "cell 1 0,align right center,grow 0 0");
        }
        customThemeForm.add(panel16, "cell 0 18");
      }
      content.add(customThemeForm, new GridConstraints(0, 0, 1, 1,
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK,
        null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  @Override
  public void setupComponents() {
    // Load from preset combobox
    final ActionToolbar actionToolbar = addLoadFromPresetComboBox();
    customThemeForm.add(actionToolbar.getComponent(), "cell 1 1, align right center,grow 0 0");
    disabledPanel = new DisabledPanel(content, MaterialThemeBundle.message("plugin.premiumSimple"));

    final boolean isFreeLicense = !MTLicenseChecker.isLicensed();
    if (isFreeLicense) {
      disablePremium(disabledPanel);
    }
  }

  private ActionToolbar addLoadFromPresetComboBox() {
    final DefaultActionGroup actions = new DefaultActionGroup();
    final ComboBoxAction action = new MTLoadCustomThemeComboBoxAction(this);
    actions.addAction(action);

    return ActionManager.getInstance().createActionToolbar("inspection.view.quick.fix.preview", actions, true);
  }

  /**
   * Default colors for Custom theme
   */
  @SuppressWarnings({
    "ClassWithTooManyFields",
    "FieldNamingConvention"})
  public enum MTCustomDefaults {
    ;
    public static final ColorUIResource excludedColor = new ColorUIResource(0x2E3C43);
    public static final ColorUIResource accentColor = new ColorUIResource(0x009688);
    public static final ColorUIResource notificationsColor = new ColorUIResource(0x323232);
    public static final Color treeSelectionColor = ColorUtil.toAlpha(new ColorUIResource(0x546E7A), 50);
    public static final ColorUIResource highlightColor = new ColorUIResource(0x425B67);
    public static final ColorUIResource secondBorderColor = new ColorUIResource(0x2A373E);
    public static final ColorUIResource tableSelectedColor = new ColorUIResource(0x314549);
    public static final ColorUIResource contrastColor = new ColorUIResource(0x1E272C);
    public static final ColorUIResource disabledColor = new ColorUIResource(0x415967);
    public static final ColorUIResource secondaryBackgroundColor = new ColorUIResource(0x32424A);
    public static final ColorUIResource buttonColor = new ColorUIResource(0x2E3C43);
    public static final ColorUIResource selectionForegroundColor = new ColorUIResource(0xFFFFFF);
    public static final ColorUIResource selectionBackgroundColor = new ColorUIResource(0x546E7A);
    public static final ColorUIResource textColor = new ColorUIResource(0x607D8B);
    public static final ColorUIResource foregroundColor = new ColorUIResource(0xB0BEC5);
    public static final ColorUIResource backgroundColor = new ColorUIResource(0x263238);
  }

  /**
   * Default colors for Light custom theme
   */
  @SuppressWarnings({
    "ClassWithTooManyFields",
    "FieldNamingConvention"})
  public enum MTLightCustomDefaults {
    ;
    public static final ColorUIResource excludedColor = new ColorUIResource(0xeae8e8);
    public static final ColorUIResource accentColor = new ColorUIResource(0x80CBC4);
    public static final ColorUIResource notificationsColor = new ColorUIResource(0x80cbc4);
    public static final Color treeSelectionColor = ColorUtil.toAlpha(new ColorUIResource(0x546E7A), 50);
    public static final ColorUIResource highlightColor = new ColorUIResource(0xD2D4D5);
    public static final ColorUIResource secondBorderColor = new ColorUIResource(0xd3e1e8);
    public static final ColorUIResource tableSelectedColor = new ColorUIResource(0xD2D4D5);
    public static final ColorUIResource contrastColor = new ColorUIResource(0xF4F4F4);
    public static final ColorUIResource disabledColor = new ColorUIResource(0xD2D4D5);
    public static final ColorUIResource secondaryBackgroundColor = new ColorUIResource(0xeae8e8);
    public static final ColorUIResource buttonColor = new ColorUIResource(0xF3F4F5);
    public static final ColorUIResource selectionForegroundColor = new ColorUIResource(0xFFFFFF);
    public static final ColorUIResource selectionBackgroundColor = new ColorUIResource(0x546E7A);
    public static final ColorUIResource textColor = new ColorUIResource(0x94A7B0);
    public static final ColorUIResource foregroundColor = new ColorUIResource(0x546E7A);
    public static final ColorUIResource backgroundColor = new ColorUIResource(0xFAFAFA);
  }

}
