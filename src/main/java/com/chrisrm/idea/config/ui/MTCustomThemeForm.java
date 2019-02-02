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

package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.MTCustomThemeConfig;
import com.chrisrm.idea.config.MTBaseConfig;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.ColorUtil;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ResourceBundle;

@SuppressWarnings({"OverlyLongMethod",
    "UseDPIAwareInsets",
    "MagicNumber",
    "DuplicateStringLiteralInspection",
    "FieldCanBeLocal",
    "OverlyLongLambda",
    "ClassWithTooManyFields",
    "PublicMethodNotExposedInInterface",
    "Duplicates",
    "ClassWithTooManyMethods"})
public final class MTCustomThemeForm implements MTFormUI {
  @Override
  public void init() {

  }

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void afterStateSet() {

  }

  @Override
  public void dispose() {

  }

  @SuppressWarnings({"OverlyComplexMethod",
      "FeatureEnvy"})
  @Override
  public boolean isModified(final MTBaseConfig config) {
    final MTCustomThemeConfig customThemeConfig = (MTCustomThemeConfig) config;
    boolean modified = customThemeConfig.isBackgroundColorChanged(getBackgroundColor());
    modified = modified || customThemeConfig.isForegroundColorChanged(getForegroundColor());
    modified = modified || customThemeConfig.isTextColorChanged(getTextColor());
    modified = modified || customThemeConfig.isSelectionBackgroundColorChanged(getSelectionForegroundColor());
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

    return modified;
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
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

    afterStateSet();
  }

  private void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor.setSelectedColor(backgroundColor);
  }

  private void setForegroundColor(final Color foregroundColor) {
    this.foregroundColor.setSelectedColor(foregroundColor);
  }

  private void setTextColor(final Color labelColor) {
    this.labelColor.setSelectedColor(labelColor);
  }

  private void setSelectionBackgroundColor(final Color selectionBackgroundColor) {
    this.selectionBackgroundColor.setSelectedColor(selectionBackgroundColor);
  }

  private void setSelectionForegroundColor(final Color selectionForegroundColor) {
    this.selectionForegroundColor.setSelectedColor(selectionForegroundColor);
  }

  private void setButtonColor(final Color buttonColor) {
    this.buttonColor.setSelectedColor(buttonColor);
  }

  private void setSecondaryBackgroundColor(final Color listBackgroundColor) {
    this.listBackgroundColor.setSelectedColor(listBackgroundColor);
  }

  private void setDisabledColor(final Color disabledColor) {
    this.disabledColor.setSelectedColor(disabledColor);
  }

  private void setContrastColor(final Color contrastColor) {
    this.contrastColor.setSelectedColor(contrastColor);
  }

  private void setTableSelectedColor(final Color tableSelectionColor) {
    this.tableSelectionColor.setSelectedColor(tableSelectionColor);
  }

  private void setSecondBorderColor(final Color miscColor1) {
    this.miscColor1.setSelectedColor(miscColor1);
  }

  private void setHighlightColor(final Color miscColor2) {
    this.miscColor2.setSelectedColor(miscColor2);
  }

  private void setTreeSelectionColor(final Color treeSelectionColor) {
    this.treeSelectionColor.setSelectedColor(treeSelectionColor);
  }

  private void setNotificationsColor(final Color notificationsColor) {
    this.notificationsColor.setSelectedColor(notificationsColor);
  }

  public Color getBackgroundColor() {
    return backgroundColor.getSelectedColor();
  }

  public Color getForegroundColor() {
    return foregroundColor.getSelectedColor();
  }

  public Color getTextColor() {
    return labelColor.getSelectedColor();
  }

  public Color getSelectionBackgroundColor() {
    return selectionBackgroundColor.getSelectedColor();
  }

  public Color getSelectionForegroundColor() {
    return selectionForegroundColor.getSelectedColor();
  }

  public Color getButtonColor() {
    return buttonColor.getSelectedColor();
  }

  public Color getSecondaryBackgroundColor() {
    return listBackgroundColor.getSelectedColor();
  }

  public Color getDisabledColor() {
    return disabledColor.getSelectedColor();
  }

  public Color getContrastColor() {
    return contrastColor.getSelectedColor();
  }

  public Color getTableSelectedColor() {
    return tableSelectionColor.getSelectedColor();
  }

  public Color getSecondBorderColor() {
    return miscColor1.getSelectedColor();
  }

  public Color getHighlightColor() {
    return miscColor2.getSelectedColor();
  }

  public Color getTreeSelectionColor() {
    return treeSelectionColor.getSelectedColor();
  }

  public Color getNotificationsColor() {
    return notificationsColor.getSelectedColor();
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    final JPanel panel1 = new JPanel();
    explLabel = new JLabel();
    expTextArea = new JTextArea();
    separator1 = new JSeparator();
    backgroundColorLabel = new JLabel();
    backgroundColor = new ColorPanel();
    foregroundColorLabel = new JLabel();
    foregroundColor = new ColorPanel();
    labelColorLabel = new JLabel();
    labelColor = new ColorPanel();
    selectionBackgroundColorLabel = new JLabel();
    selectionBackgroundColor = new ColorPanel();
    selectionForegroundColorLabel = new JLabel();
    selectionForegroundColor = new ColorPanel();
    buttonColorLabel = new JLabel();
    buttonColor = new ColorPanel();
    listBackgroundColorLabel = new JLabel();
    listBackgroundColor = new ColorPanel();
    disabledColorLabel = new JLabel();
    disabledColor = new ColorPanel();
    contrastColorLabel = new JLabel();
    contrastColor = new ColorPanel();
    tableSelectionColorLabel = new JLabel();
    tableSelectionColor = new ColorPanel();
    miscColorLabel = new JLabel();
    miscColor1 = new ColorPanel();
    miscColorLabel2 = new JLabel();
    miscColor2 = new ColorPanel();
    treeSelectionLabel = new JLabel();
    treeSelectionColor = new ColorPanel();
    notificationsLabel = new JLabel();
    notificationsColor = new ColorPanel();
    resetTabDefaultsBtn = new JButton();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);
      content.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== panel1 ========
      {
        panel1.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.customColorsTitle")));
        panel1.setLayout(new GridLayoutManager(18, 2, new Insets(0, 3, 0, 0), -1, -1));

        //---- explLabel ----
        explLabel.setText(bundle.getString("MTCustomThemeForm.expLabel.text"));
        explLabel.setForeground(UIManager.getColor("Button.disabledText"));
        panel1.add(explLabel, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- expTextArea ----
        expTextArea.setBackground(UIManager.getColor("Panel.background"));
        expTextArea.setFont(UIManager.getFont("Panel.font"));
        expTextArea.setText(bundle.getString("MTCustomThemeForm.expTextArea.text"));
        expTextArea.setRows(2);
        expTextArea.setWrapStyleWord(true);
        expTextArea.setEditable(false);
        expTextArea.setBorder(null);
        panel1.add(expTextArea, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(separator1, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- backgroundColorLabel ----
        backgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.backgroundColorLabel.text"));
        backgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.backgroundColorLabel.toolTipText"));
        panel1.add(backgroundColorLabel, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(backgroundColor, new GridConstraints(3, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- foregroundColorLabel ----
        foregroundColorLabel.setText(bundle.getString("MTCustomThemeForm.foregroundColorLabel.text"));
        foregroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.foregroundColorLabel.toolTipText"));
        panel1.add(foregroundColorLabel, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(foregroundColor, new GridConstraints(4, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- labelColorLabel ----
        labelColorLabel.setText(bundle.getString("MTCustomThemeForm.labelColorLabel.text"));
        labelColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.labelColorLabel.toolTipText"));
        panel1.add(labelColorLabel, new GridConstraints(5, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(labelColor, new GridConstraints(5, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- selectionBackgroundColorLabel ----
        selectionBackgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.selectionBackgroundColorLabel.text"));
        selectionBackgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.selectionBackgroundColorLabel.toolTipText"));
        panel1.add(selectionBackgroundColorLabel, new GridConstraints(6, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(selectionBackgroundColor, new GridConstraints(6, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- selectionForegroundColorLabel ----
        selectionForegroundColorLabel.setText(bundle.getString("MTCustomThemeForm.selectionForegroundColorLabel.text"));
        selectionForegroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.selectionForegroundColorLabel.toolTipText"));
        panel1.add(selectionForegroundColorLabel, new GridConstraints(7, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(selectionForegroundColor, new GridConstraints(7, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- buttonColorLabel ----
        buttonColorLabel.setText(bundle.getString("MTCustomThemeForm.buttonColorLabel.text"));
        buttonColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.buttonColorLabel.toolTipText"));
        panel1.add(buttonColorLabel, new GridConstraints(8, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(buttonColor, new GridConstraints(8, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- listBackgroundColorLabel ----
        listBackgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.listBackgroundColorLabel.text"));
        listBackgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.listBackgroundColorLabel.toolTipText"));
        panel1.add(listBackgroundColorLabel, new GridConstraints(9, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(listBackgroundColor, new GridConstraints(9, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- disabledColorLabel ----
        disabledColorLabel.setText(bundle.getString("MTCustomThemeForm.disabledColorLabel.text"));
        disabledColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.disabledColorLabel.toolTipText"));
        panel1.add(disabledColorLabel, new GridConstraints(10, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(disabledColor, new GridConstraints(10, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- contrastColorLabel ----
        contrastColorLabel.setText(bundle.getString("MTCustomThemeForm.contrastColorLabel.text"));
        contrastColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.contrastColorLabel.toolTipText"));
        panel1.add(contrastColorLabel, new GridConstraints(11, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(contrastColor, new GridConstraints(11, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- tableSelectionColorLabel ----
        tableSelectionColorLabel.setText(bundle.getString("MTCustomThemeForm.tableSelectionColorLabel.text"));
        tableSelectionColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.tableSelectionColorLabel.toolTipText"));
        panel1.add(tableSelectionColorLabel, new GridConstraints(12, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(tableSelectionColor, new GridConstraints(12, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- miscColorLabel ----
        miscColorLabel.setText(bundle.getString("MTCustomThemeForm.miscColorLabel.text"));
        miscColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.miscColorLabel.toolTipText"));
        panel1.add(miscColorLabel, new GridConstraints(13, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(miscColor1, new GridConstraints(13, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- miscColorLabel2 ----
        miscColorLabel2.setText(bundle.getString("MTCustomThemeForm.miscColorLabel2.text"));
        miscColorLabel2.setToolTipText(bundle.getString("MTCustomThemeForm.miscColorLabel2.toolTipText"));
        panel1.add(miscColorLabel2, new GridConstraints(14, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(miscColor2, new GridConstraints(14, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- treeSelectionLabel ----
        treeSelectionLabel.setText(bundle.getString("MTCustomThemeForm.treeSelectionLabel.text"));
        treeSelectionLabel.setToolTipText(bundle.getString("MTCustomThemeForm.treeSelectionLabel.toolTipText"));
        panel1.add(treeSelectionLabel, new GridConstraints(15, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(treeSelectionColor, new GridConstraints(15, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- notificationsLabel ----
        notificationsLabel.setText(bundle.getString("MTCustomThemeForm.notificationsLabel.text"));
        notificationsLabel.setToolTipText(bundle.getString("MTCustomThemeForm.notificationsLabel.toolTipText"));
        panel1.add(notificationsLabel, new GridConstraints(16, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(notificationsColor, new GridConstraints(16, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- resetTabDefaultsBtn ----
        resetTabDefaultsBtn.setText(bundle.getString("MTCustomThemeForm.resetDefaultColorsButton.text"));
        resetTabDefaultsBtn.setToolTipText(bundle.getString("MTCustomThemeForm.resetDefaultColorsButton.toolTipText"));
        panel1.add(resetTabDefaultsBtn, new GridConstraints(17, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
      }
      content.add(panel1, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JLabel explLabel;
  private JTextArea expTextArea;
  private JSeparator separator1;
  private JLabel backgroundColorLabel;
  private ColorPanel backgroundColor;
  private JLabel foregroundColorLabel;
  private ColorPanel foregroundColor;
  private JLabel labelColorLabel;
  private ColorPanel labelColor;
  private JLabel selectionBackgroundColorLabel;
  private ColorPanel selectionBackgroundColor;
  private JLabel selectionForegroundColorLabel;
  private ColorPanel selectionForegroundColor;
  private JLabel buttonColorLabel;
  private ColorPanel buttonColor;
  private JLabel listBackgroundColorLabel;
  private ColorPanel listBackgroundColor;
  private JLabel disabledColorLabel;
  private ColorPanel disabledColor;
  private JLabel contrastColorLabel;
  private ColorPanel contrastColor;
  private JLabel tableSelectionColorLabel;
  private ColorPanel tableSelectionColor;
  private JLabel miscColorLabel;
  private ColorPanel miscColor1;
  private JLabel miscColorLabel2;
  private ColorPanel miscColor2;
  private JLabel treeSelectionLabel;
  private ColorPanel treeSelectionColor;
  private JLabel notificationsLabel;
  private ColorPanel notificationsColor;
  private JButton resetTabDefaultsBtn;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

  @SuppressWarnings("FeatureEnvy")
  public MTCustomThemeForm() {
    initComponents();

    // Reset tab defaults
    resetTabDefaultsBtn.addActionListener(e -> {
      setNotificationsColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.notificationsColor,
          MTLightCustomDefaults.notificationsColor));
      setSecondBorderColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.secondBorderColor,
          MTLightCustomDefaults.secondBorderColor));
      setContrastColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.contrastColor,
          MTLightCustomDefaults.contrastColor));
      setDisabledColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.disabledColor,
          MTLightCustomDefaults.disabledColor));
      setSecondaryBackgroundColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.secondaryBackgroundColor,
          MTLightCustomDefaults.secondaryBackgroundColor));
      setButtonColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.buttonColor,
          MTLightCustomDefaults.buttonColor));
      setSelectionBackgroundColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.selectionBackgroundColor,
          MTLightCustomDefaults.selectionBackgroundColor));
      setSelectionForegroundColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.selectionForegroundColor,
          MTLightCustomDefaults.selectionForegroundColor));
      setTableSelectedColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.tableSelectedColor,
          MTLightCustomDefaults.tableSelectedColor));
      setTextColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.textColor,
          MTLightCustomDefaults.textColor));
      setTreeSelectionColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.treeSelectionColor,
          MTLightCustomDefaults.treeSelectionColor));
      setHighlightColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.highlightColor,
          MTLightCustomDefaults.highlightColor));
      setForegroundColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.foregroundColor,
          MTLightCustomDefaults.foregroundColor));
      setBackgroundColor(MTUiUtils.lightOrDark(
          MTCustomDefaults.backgroundColor,
          MTLightCustomDefaults.backgroundColor));
    });
  }

  /**
   * Default colors for Custom theme
   */
  @SuppressWarnings({
      "PublicInnerClass",
      "ClassWithTooManyFields"})
  public enum MTCustomDefaults {;
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
      "PublicInnerClass",
      "ClassWithTooManyFields"})
  public enum MTLightCustomDefaults {;
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
