/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.ui.ColorPanel;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ResourceBundle;

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

  public void setBackgroundColor(final Color backgroundColor) {
    this.backgroundColor.setSelectedColor(backgroundColor);
  }

  public void setForegroundColor(final Color foregroundColor) {
    this.foregroundColor.setSelectedColor(foregroundColor);
  }

  public void setTextColor(final Color labelColor) {
    this.labelColor.setSelectedColor(labelColor);
  }

  public void setSelectionBackgroundColor(final Color selectionBackgroundColor) {
    this.selectionBackgroundColor.setSelectedColor(selectionBackgroundColor);
  }

  public void setSelectionForegroundColor(final Color selectionForegroundColor) {
    this.selectionForegroundColor.setSelectedColor(selectionForegroundColor);
  }

  public void setInactiveColor(final Color inactiveColor) {
    this.inactiveColor.setSelectedColor(inactiveColor);
  }

  public void setCaretColor(final Color caretColor) {
    this.caretColor.setSelectedColor(caretColor);
  }

  public void setSecondaryBackgroundColor(final Color listBackgroundColor) {
    this.listBackgroundColor.setSelectedColor(listBackgroundColor);
  }

  public void setDisabledColor(final Color disabledColor) {
    this.disabledColor.setSelectedColor(disabledColor);
  }

  public void setContrastColor(final Color contrastColor) {
    this.contrastColor.setSelectedColor(contrastColor);
  }

  public void setTableSelectedColor(final Color tableSelectionColor) {
    this.tableSelectionColor.setSelectedColor(tableSelectionColor);
  }

  public void setSecondBorderColor(final Color miscColor1) {
    this.miscColor1.setSelectedColor(miscColor1);
  }

  public void setHighlightColor(final Color miscColor2) {
    this.miscColor2.setSelectedColor(miscColor2);
  }

  public void setButtonHighlightColor(final Color buttonHighlightColor) {
    this.buttonHighlightColor.setSelectedColor(buttonHighlightColor);
  }

  public void setTreeSelectionColor(final Color treeSelectionColor) {
    this.treeSelectionColor.setSelectedColor(treeSelectionColor);
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


  public Color getInactiveColor() {
    return inactiveColor.getSelectedColor();
  }


  public Color getCaretColor() {
    return caretColor.getSelectedColor();
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


  public Color getButtonHighlightColor() {
    return buttonHighlightColor.getSelectedColor();
  }


  public Color getHighlightColor() {
    return miscColor2.getSelectedColor();
  }


  public Color getTreeSelectionColor() {
    return treeSelectionColor.getSelectedColor();
  }


  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Mario Smilax
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    final JPanel panel1 = new JPanel();
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
    inactiveColorLabel = new JLabel();
    inactiveColor = new ColorPanel();
    caretColorLabel = new JLabel();
    caretColor = new ColorPanel();
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
    buttonHighlightLabel = new JLabel();
    buttonHighlightColor = new ColorPanel();
    treeSelectionLabel = new JLabel();
    treeSelectionColor = new ColorPanel();
    resetTabDefaultsBtn = new JButton();

    final Spacer vSpacer1 = new Spacer();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);

      // JFormDesigner evaluation mark
      content.setBorder(new javax.swing.border.CompoundBorder(
          new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
              "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
              javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
              java.awt.Color.red), content.getBorder()));

      content.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== panel1 ========
      {
        panel1.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.customColorsTitle")));
        panel1.setLayout(new GridLayoutManager(17, 2, new Insets(0, 3, 0, 0), -1, -1));

        //---- backgroundColorLabel ----
        backgroundColorLabel.setText(bundle.getString("MTColorForm.background"));
        backgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.backgroundColor.toolTipText"));
        panel1.add(backgroundColorLabel, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(backgroundColor, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- foregroundColorLabel ----
        foregroundColorLabel.setText(bundle.getString("MTForm.foregroundColorLabel.text"));
        foregroundColorLabel.setToolTipText(bundle.getString("MTForm.foregroundColorLabel.toolTipText"));
        panel1.add(foregroundColorLabel, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(foregroundColor, new GridConstraints(1, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- labelColorLabel ----
        labelColorLabel.setText(bundle.getString("MTForm.labelColorLabel.text"));
        labelColorLabel.setToolTipText(bundle.getString("MTForm.labelColorLabel.toolTipText"));
        panel1.add(labelColorLabel, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(labelColor, new GridConstraints(2, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- selectionBackgroundColorLabel ----
        selectionBackgroundColorLabel.setText(bundle.getString("MTForm.selectionBackgroundColorLabel.text"));
        selectionBackgroundColorLabel.setToolTipText(bundle.getString("MTForm.selectionBackgroundColorLabel.toolTipText"));
        panel1.add(selectionBackgroundColorLabel, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(selectionBackgroundColor, new GridConstraints(3, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- selectionForegroundColorLabel ----
        selectionForegroundColorLabel.setText(bundle.getString("MTForm.selectionForegroundColorLabel.text"));
        selectionForegroundColorLabel.setToolTipText(bundle.getString("MTForm.selectionForegroundColorLabel.toolTipText"));
        panel1.add(selectionForegroundColorLabel, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(selectionForegroundColor, new GridConstraints(4, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- inactiveColorLabel ----
        inactiveColorLabel.setText(bundle.getString("MTForm.inactiveColorLabel.text"));
        inactiveColorLabel.setToolTipText(bundle.getString("MTForm.inactiveColorLabel.toolTipText"));
        panel1.add(inactiveColorLabel, new GridConstraints(5, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(inactiveColor, new GridConstraints(5, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- caretColorLabel ----
        caretColorLabel.setText(bundle.getString("MTForm.caretColorLabel.text"));
        caretColorLabel.setToolTipText(bundle.getString("MTForm.caretColorLabel.toolTipText"));
        panel1.add(caretColorLabel, new GridConstraints(6, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(caretColor, new GridConstraints(6, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- listBackgroundColorLabel ----
        listBackgroundColorLabel.setText(bundle.getString("MTForm.listBackgroundColorLabel.text"));
        listBackgroundColorLabel.setToolTipText(bundle.getString("MTForm.listBackgroundColorLabel.toolTipText"));
        panel1.add(listBackgroundColorLabel, new GridConstraints(7, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(listBackgroundColor, new GridConstraints(7, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- disabledColorLabel ----
        disabledColorLabel.setText(bundle.getString("MTForm.disabledColorLabel.text"));
        disabledColorLabel.setToolTipText(bundle.getString("MTForm.disabledColorLabel.toolTipText"));
        panel1.add(disabledColorLabel, new GridConstraints(8, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(disabledColor, new GridConstraints(8, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- contrastColorLabel ----
        contrastColorLabel.setText(bundle.getString("MTForm.contrastColorLabel.text"));
        contrastColorLabel.setToolTipText(bundle.getString("MTForm.contrastColorLabel.toolTipText"));
        panel1.add(contrastColorLabel, new GridConstraints(9, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(contrastColor, new GridConstraints(9, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- tableSelectionColorLabel ----
        tableSelectionColorLabel.setText(bundle.getString("MTForm.tableSelectionColorLabel.text"));
        tableSelectionColorLabel.setToolTipText(bundle.getString("MTForm.tableSelectionColorLabel.toolTipText"));
        panel1.add(tableSelectionColorLabel, new GridConstraints(10, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(tableSelectionColor, new GridConstraints(10, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- miscColorLabel ----
        miscColorLabel.setText(bundle.getString("MTForm.miscColorLabel.text"));
        miscColorLabel.setToolTipText(bundle.getString("MTForm.miscColorLabel.toolTipText"));
        panel1.add(miscColorLabel, new GridConstraints(11, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(miscColor1, new GridConstraints(11, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- miscColorLabel2 ----
        miscColorLabel2.setText(bundle.getString("MTForm.miscColorLabel2.text"));
        miscColorLabel2.setToolTipText(bundle.getString("MTForm.miscColorLabel2.toolTipText"));
        panel1.add(miscColorLabel2, new GridConstraints(12, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(miscColor2, new GridConstraints(12, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- buttonHighlightLabel ----
        buttonHighlightLabel.setText(bundle.getString("MTForm.buttonHighlightLabel.text"));
        buttonHighlightLabel.setToolTipText(bundle.getString("MTForm.buttonHighlightLabel.toolTipText"));
        panel1.add(buttonHighlightLabel, new GridConstraints(13, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(buttonHighlightColor, new GridConstraints(13, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- treeSelectionLabel ----
        treeSelectionLabel.setText(bundle.getString("MTForm.treeSelectionLabel.text"));
        treeSelectionLabel.setToolTipText(bundle.getString("MTForm.treeSelectionLabel.toolTipText"));
        panel1.add(treeSelectionLabel, new GridConstraints(14, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(treeSelectionColor, new GridConstraints(14, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- resetTabDefaultsBtn ----
        resetTabDefaultsBtn.setText(bundle.getString("mt.resetdefaults"));
        resetTabDefaultsBtn.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
        panel1.add(resetTabDefaultsBtn, new GridConstraints(15, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));
      }
      content.add(panel1, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
      content.add(vSpacer1, new GridConstraints(1, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - Mario Smilax
  private JPanel content;
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
  private JLabel inactiveColorLabel;
  private ColorPanel inactiveColor;
  private JLabel caretColorLabel;
  private ColorPanel caretColor;
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
  private JLabel buttonHighlightLabel;
  private ColorPanel buttonHighlightColor;
  private JLabel treeSelectionLabel;
  private ColorPanel treeSelectionColor;
  private JButton resetTabDefaultsBtn;

  // JFormDesigner - End of variables declaration  //GEN-END:variables

  public MTCustomThemeForm() {
    initComponents();

    // Reset tab defaults
    resetTabDefaultsBtn.addActionListener(e -> {
      setButtonHighlightColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.buttonHighlightColor,
          MTCustomThemeConfig.MTLightCustomDefaults.buttonHighlightColor));
      setSecondBorderColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.secondBorderColor,
          MTCustomThemeConfig.MTLightCustomDefaults.secondBorderColor));
      setContrastColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.contrastColor,
          MTCustomThemeConfig.MTLightCustomDefaults.contrastColor));
      setDisabledColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.disabledColor,
          MTCustomThemeConfig.MTLightCustomDefaults.disabledColor));
      setCaretColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.caretColor,
          MTCustomThemeConfig.MTLightCustomDefaults.caretColor));
      setSecondaryBackgroundColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.secondaryBackgroundColor,
          MTCustomThemeConfig.MTLightCustomDefaults.secondaryBackgroundColor));
      setInactiveColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.inactiveColor,
          MTCustomThemeConfig.MTLightCustomDefaults.inactiveColor));
      setSelectionBackgroundColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.selectionBackgroundColor,
          MTCustomThemeConfig.MTLightCustomDefaults.selectionBackgroundColor));
      setSelectionForegroundColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.selectionForegroundColor,
          MTCustomThemeConfig.MTLightCustomDefaults.selectionForegroundColor));
      setTableSelectedColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.tableSelectedColor,
          MTCustomThemeConfig.MTLightCustomDefaults.tableSelectedColor));
      setTextColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.textColor,
          MTCustomThemeConfig.MTLightCustomDefaults.textColor));
      setTreeSelectionColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.treeSelectionColor,
          MTCustomThemeConfig.MTLightCustomDefaults.treeSelectionColor));
      setHighlightColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.highlightColor,
          MTCustomThemeConfig.MTLightCustomDefaults.highlightColor));
      setForegroundColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.foregroundColor,
          MTCustomThemeConfig.MTLightCustomDefaults.foregroundColor));
      setBackgroundColor(MTUiUtils.lightOrDark(
          MTCustomThemeConfig.MTCustomDefaults.backgroundColor,
          MTCustomThemeConfig.MTLightCustomDefaults.backgroundColor));

    });
  }
}
