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
import com.chrisrm.idea.ui.ColorPanelWithOpacity;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.ui.ColorUtil;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import net.miginfocom.swing.MigLayout;

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
    modified = modified || customThemeConfig.isAccentColorChanged(getAccentColor());
    modified = modified || customThemeConfig.isExcludedColorChanged(getExcludedColor());

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
    setAccentColor(customThemeConfig.getAccentColor());
    setExcludedColor(customThemeConfig.getExcludedColor());

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

  private void setAccentColor(final Color accentColor) {
    this.accentColor.setSelectedColor(accentColor);
  }

  private void setExcludedColor(final Color excludedColor) {
    this.excludedColor.setSelectedColor(excludedColor);
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

  public Color getAccentColor() {
    return accentColor.getSelectedColor();
  }

  public Color getExcludedColor() {
    return excludedColor.getSelectedColor();
  }

  @SuppressWarnings({"HardCodedStringLiteral",
      "StringConcatenation"})
  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    final JPanel customThemeForm = new JPanel();
    explLabel = new JLabel();
    expTextArea = new JTextArea();
    separator1 = new JSeparator();
    backgroundColorLabel = new JLabel();
    backgroundColor = new ColorPanelWithOpacity();
    foregroundColorLabel = new JLabel();
    foregroundColor = new ColorPanelWithOpacity();
    labelColorLabel = new JLabel();
    labelColor = new ColorPanelWithOpacity();
    selectionBackgroundColorLabel = new JLabel();
    selectionBackgroundColor = new ColorPanelWithOpacity();
    selectionForegroundColorLabel = new JLabel();
    selectionForegroundColor = new ColorPanelWithOpacity();
    buttonColorLabel = new JLabel();
    buttonColor = new ColorPanelWithOpacity();
    listBackgroundColorLabel = new JLabel();
    listBackgroundColor = new ColorPanelWithOpacity();
    disabledColorLabel = new JLabel();
    disabledColor = new ColorPanelWithOpacity();
    contrastColorLabel = new JLabel();
    contrastColor = new ColorPanelWithOpacity();
    tableSelectionColorLabel = new JLabel();
    tableSelectionColor = new ColorPanelWithOpacity();
    miscColorLabel = new JLabel();
    miscColor1 = new ColorPanelWithOpacity();
    miscColorLabel2 = new JLabel();
    miscColor2 = new ColorPanelWithOpacity();
    treeSelectionLabel = new JLabel();
    treeSelectionColor = new ColorPanelWithOpacity();
    notificationsLabel = new JLabel();
    notificationsColor = new ColorPanelWithOpacity();
    accentLabel = new JLabel();
    accentColor = new ColorPanelWithOpacity();
    excludedLabel = new JLabel();
    excludedColor = new ColorPanelWithOpacity();
    panel1 = new JPanel();

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
            "[grow 1,fill]" +
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

        //---- backgroundColorLabel ----
        backgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.backgroundColorLabel.text"));
        backgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.backgroundColorLabel.toolTipText"));
        customThemeForm.add(backgroundColorLabel, "cell 0 3,align left center,grow 0 0");
        customThemeForm.add(backgroundColor, "cell 1 3,align right center,grow 0 0");

        //---- foregroundColorLabel ----
        foregroundColorLabel.setText(bundle.getString("MTCustomThemeForm.foregroundColorLabel.text"));
        foregroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.foregroundColorLabel.toolTipText"));
        customThemeForm.add(foregroundColorLabel, "cell 0 4,align left center,grow 0 0");
        customThemeForm.add(foregroundColor, "cell 1 4,align right center,grow 0 0");

        //---- labelColorLabel ----
        labelColorLabel.setText(bundle.getString("MTCustomThemeForm.labelColorLabel.text"));
        labelColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.labelColorLabel.toolTipText"));
        customThemeForm.add(labelColorLabel, "cell 0 5,align left center,grow 0 0");
        customThemeForm.add(labelColor, "cell 1 5,align right center,grow 0 0");

        //---- selectionBackgroundColorLabel ----
        selectionBackgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.selectionBackgroundColorLabel.text"));
        selectionBackgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.selectionBackgroundColorLabel.toolTipText"));
        customThemeForm.add(selectionBackgroundColorLabel, "cell 0 6,align left center,grow 0 0");
        customThemeForm.add(selectionBackgroundColor, "cell 1 6,align right center,grow 0 0");

        //---- selectionForegroundColorLabel ----
        selectionForegroundColorLabel.setText(bundle.getString("MTCustomThemeForm.selectionForegroundColorLabel.text"));
        selectionForegroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.selectionForegroundColorLabel.toolTipText"));
        customThemeForm.add(selectionForegroundColorLabel, "cell 0 7,align left center,grow 0 0");
        customThemeForm.add(selectionForegroundColor, "cell 1 7,align right center,grow 0 0");

        //---- buttonColorLabel ----
        buttonColorLabel.setText(bundle.getString("MTCustomThemeForm.buttonColorLabel.text"));
        buttonColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.buttonColorLabel.toolTipText"));
        customThemeForm.add(buttonColorLabel, "cell 0 8,align left center,grow 0 0");
        customThemeForm.add(buttonColor, "cell 1 8,align right center,grow 0 0");

        //---- listBackgroundColorLabel ----
        listBackgroundColorLabel.setText(bundle.getString("MTCustomThemeForm.listBackgroundColorLabel.text"));
        listBackgroundColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.listBackgroundColorLabel.toolTipText"));
        customThemeForm.add(listBackgroundColorLabel, "cell 0 9,align left center,grow 0 0");
        customThemeForm.add(listBackgroundColor, "cell 1 9,align right center,grow 0 0");

        //---- disabledColorLabel ----
        disabledColorLabel.setText(bundle.getString("MTCustomThemeForm.disabledColorLabel.text"));
        disabledColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.disabledColorLabel.toolTipText"));
        customThemeForm.add(disabledColorLabel, "cell 0 10,align left center,grow 0 0");
        customThemeForm.add(disabledColor, "cell 1 10,align right center,grow 0 0");

        //---- contrastColorLabel ----
        contrastColorLabel.setText(bundle.getString("MTCustomThemeForm.contrastColorLabel.text"));
        contrastColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.contrastColorLabel.toolTipText"));
        customThemeForm.add(contrastColorLabel, "cell 0 11,align left center,grow 0 0");
        customThemeForm.add(contrastColor, "cell 1 11,align right center,grow 0 0");

        //---- tableSelectionColorLabel ----
        tableSelectionColorLabel.setText(bundle.getString("MTCustomThemeForm.tableSelectionColorLabel.text"));
        tableSelectionColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.tableSelectionColorLabel.toolTipText"));
        customThemeForm.add(tableSelectionColorLabel, "cell 0 12,align left center,grow 0 0");
        customThemeForm.add(tableSelectionColor, "cell 1 12,align right center,grow 0 0");

        //---- miscColorLabel ----
        miscColorLabel.setText(bundle.getString("MTCustomThemeForm.miscColorLabel.text"));
        miscColorLabel.setToolTipText(bundle.getString("MTCustomThemeForm.miscColorLabel.toolTipText"));
        customThemeForm.add(miscColorLabel, "cell 0 13,align left center,grow 0 0");
        customThemeForm.add(miscColor1, "cell 1 13,align right center,grow 0 0");

        //---- miscColorLabel2 ----
        miscColorLabel2.setText(bundle.getString("MTCustomThemeForm.miscColorLabel2.text"));
        miscColorLabel2.setToolTipText(bundle.getString("MTCustomThemeForm.miscColorLabel2.toolTipText"));
        customThemeForm.add(miscColorLabel2, "cell 0 14,align left center,grow 0 0");
        customThemeForm.add(miscColor2, "cell 1 14,align right center,grow 0 0");

        //---- treeSelectionLabel ----
        treeSelectionLabel.setText(bundle.getString("MTCustomThemeForm.treeSelectionLabel.text"));
        treeSelectionLabel.setToolTipText(bundle.getString("MTCustomThemeForm.treeSelectionLabel.toolTipText"));
        customThemeForm.add(treeSelectionLabel, "cell 0 15,align left center,grow 0 0");
        customThemeForm.add(treeSelectionColor, "cell 1 15,align right center,grow 0 0");

        //---- notificationsLabel ----
        notificationsLabel.setText(bundle.getString("MTCustomThemeForm.notificationsLabel.text"));
        notificationsLabel.setToolTipText(bundle.getString("MTCustomThemeForm.notificationsLabel.toolTipText"));
        customThemeForm.add(notificationsLabel, "cell 0 16,align left center,grow 0 0");
        customThemeForm.add(notificationsColor, "cell 1 16,align right center,grow 0 0");

        //---- accentLabel ----
        accentLabel.setText(bundle.getString("MTForm.accentLabel.text"));
        accentLabel.setToolTipText(bundle.getString("MTForm.accentLabel.toolTipText"));
        customThemeForm.add(accentLabel, "cell 0 17,align left center,grow 0 0");
        customThemeForm.add(accentColor, "cell 1 17,align right center,grow 0 0");

        //---- excludedLabel ----
        excludedLabel.setText(bundle.getString("MTForm.excludedLabel.text"));
        excludedLabel.setToolTipText(bundle.getString("MTForm.excludedLabel.toolTipText"));
        customThemeForm.add(excludedLabel, "cell 0 18,align left center,grow 0 0");
        customThemeForm.add(excludedColor, "cell 1 18,align right center,grow 0 0");

        //======== panel1 ========
        {
          panel1.setLayout(new FlowLayout());
        }
        customThemeForm.add(panel1, "cell 0 19,align center center,grow 0 0");
      }
      content.add(customThemeForm, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    // Load from preset combobox
    addLoadFromPresetComboBox();
  }

  private void addLoadFromPresetComboBox() {
    final DefaultActionGroup actions = new DefaultActionGroup();
    final ComboBoxAction action = new MTLoadCustomThemeComboBoxAction(this);
    actions.addAction(action);

    final ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("inspection.view.quick.fix.preview", actions, true);

    panel1.add(toolbar.getComponent());
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JLabel explLabel;
  private JTextArea expTextArea;
  private JSeparator separator1;
  private JLabel backgroundColorLabel;
  private ColorPanelWithOpacity backgroundColor;
  private JLabel foregroundColorLabel;
  private ColorPanelWithOpacity foregroundColor;
  private JLabel labelColorLabel;
  private ColorPanelWithOpacity labelColor;
  private JLabel selectionBackgroundColorLabel;
  private ColorPanelWithOpacity selectionBackgroundColor;
  private JLabel selectionForegroundColorLabel;
  private ColorPanelWithOpacity selectionForegroundColor;
  private JLabel buttonColorLabel;
  private ColorPanelWithOpacity buttonColor;
  private JLabel listBackgroundColorLabel;
  private ColorPanelWithOpacity listBackgroundColor;
  private JLabel disabledColorLabel;
  private ColorPanelWithOpacity disabledColor;
  private JLabel contrastColorLabel;
  private ColorPanelWithOpacity contrastColor;
  private JLabel tableSelectionColorLabel;
  private ColorPanelWithOpacity tableSelectionColor;
  private JLabel miscColorLabel;
  private ColorPanelWithOpacity miscColor1;
  private JLabel miscColorLabel2;
  private ColorPanelWithOpacity miscColor2;
  private JLabel treeSelectionLabel;
  private ColorPanelWithOpacity treeSelectionColor;
  private JLabel notificationsLabel;
  private ColorPanelWithOpacity notificationsColor;
  private JLabel accentLabel;
  private ColorPanelWithOpacity accentColor;
  private JLabel excludedLabel;
  private ColorPanelWithOpacity excludedColor;
  private JPanel panel1;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

  @SuppressWarnings("FeatureEnvy")
  public MTCustomThemeForm() {
    initComponents();
  }

  /**
   * Default colors for Custom theme
   */
  @SuppressWarnings({
      "PublicInnerClass",
      "ClassWithTooManyFields",
      "FieldNamingConvention"})
  public enum MTCustomDefaults {;
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
      "PublicInnerClass",
      "ClassWithTooManyFields",
      "FieldNamingConvention"})
  public enum MTLightCustomDefaults {;
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
