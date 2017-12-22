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

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.ColorPanel;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class MTForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel tabsHeightSpinnerModel;
  private SpinnerModel customTreeIndentModel;
  private SpinnerModel customSidebarHeightModel;

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

  public Color getHighlightColor() {
    return activeTabHighlightColor.getSelectedColor();
  }

  public void setHighlightColor(@NotNull final Color highlightColor) {
    activeTabHighlightColor.setSelectedColor(highlightColor);
  }

  public boolean getHighlightColorEnabled() {
    return activeTabHighlightCheckbox.isSelected();
  }

  public void setHighlightColorEnabled(final boolean enabled) {
    activeTabHighlightCheckbox.setSelected(enabled);
    enableDisableActiveTabColor(enabled);
  }

  public Integer getHighlightThickness() {
    return (Integer) highlightSpinnerModel.getValue();
  }

  public void setHighlightThickness(final Integer highlightThickness) {
    highlightSpinnerModel.setValue(highlightThickness);
  }

  public Integer getCustomTreeIndent() {
    return (Integer) customTreeIndentModel.getValue();
  }

  public void setCustomTreeIndent(final Integer customTreeIndent) {
    customTreeIndentModel.setValue(customTreeIndent);
  }

  public Integer getCustomSidebarHeight() {
    return (Integer) customSidebarHeightModel.getValue();
  }

  public void setCustomSidebarHeight(final Integer customSidebarHeight) {
    customSidebarHeightModel.setValue(customSidebarHeight);
  }

  @Override
  public void init() {
    final MTConfig config = MTConfig.getInstance();
    int highlightThickness = valueInRange(config.getHighlightThickness(), MTConfig.MIN_HIGHLIGHT_THICKNESS, MTConfig.MAX_HIGHLIGHT_THICKNESS);
    int tabsHeight = valueInRange(config.getTabsHeight(), MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT);
    int customTreeIndent = valueInRange(config.getCustomTreeIndent(), MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT);
    int customSidebarHeight = valueInRange(config.getCustomTreeIndent(), MTConfig.MIN_SIDEBAR_HEIGHT, MTConfig.MAX_SIDEBAR_HEIGHT);
    highlightSpinnerModel = new SpinnerNumberModel(highlightThickness, MTConfig.MIN_HIGHLIGHT_THICKNESS, MTConfig.MAX_HIGHLIGHT_THICKNESS, 1);
    highlightSpinner.setModel(highlightSpinnerModel);
    tabsHeightSpinnerModel = new SpinnerNumberModel(tabsHeight, MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT, 1);
    tabHeightSpinner.setModel(tabsHeightSpinnerModel);
    customTreeIndentModel = new SpinnerNumberModel(customTreeIndent, MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT, 2);
    customIndentSpinner.setModel(customTreeIndentModel);
    customSidebarHeightModel = new SpinnerNumberModel(customSidebarHeight, MTConfig.MIN_SIDEBAR_HEIGHT, MTConfig.MAX_SIDEBAR_HEIGHT, 2);
    customSidebarSpinner.setModel(customSidebarHeightModel);
  }

  private int valueInRange(final int value, final int min, final int max) {
    return Integer.min(max, Integer.max(value, min));
  }

  public boolean getIsContrastMode() {
    return isContrastModeCheckbox.isSelected();
  }

  public void setIsContrastMode(final boolean isContrastMode) {
    isContrastModeCheckbox.setSelected(isContrastMode);
  }

  public boolean getIsMaterialDesign() {
    return isMaterialDesignCheckbox.isSelected();
  }

  public void setIsMaterialDesign(final boolean isMaterialDesign) {
    isMaterialDesignCheckbox.setSelected(isMaterialDesign);
  }

  public boolean getIsBoldTabs() {
    return boldTabs.isSelected();
  }

  public void setIsBoldTabs(final boolean isBold) {
    boldTabs.setSelected(isBold);
  }

  public void setIsUseMaterialIcons(final boolean useMaterialIcons) {
    isMaterialIconsCheckbox.setSelected(useMaterialIcons);
    enableDisableFileIcons(useMaterialIcons);
  }

  public boolean isUseMaterialIcons() {
    return isMaterialIconsCheckbox.isSelected();
  }

  public void setIsCustomTreeIndent(final boolean isCustomTreeIndent) {
    customTreeIndentCheckbox.setSelected(isCustomTreeIndent);
    enableDisableCustomTreeIndent(isCustomTreeIndent);
  }

  public boolean isCustomTreeIndent() {
    return customTreeIndentCheckbox.isSelected();
  }

  public void setUseProjectViewDecorators(final boolean useProjectViewDecorators) {
    isProjectViewDecoratorsCheckbox.setSelected(useProjectViewDecorators);
  }

  public boolean getUseProjectViewDecorators() {
    return isProjectViewDecoratorsCheckbox.isSelected();
  }

  public void setHideFileIcons(final boolean hideFileIcons) {
    hideFileIconsCheckbox.setSelected(hideFileIcons);
  }

  public boolean getHideFileIcons() {
    return hideFileIconsCheckbox.isSelected();
  }

  public void setIsThemedScrollbars(final boolean isThemedScrollbars) {
    themedScrollbarsCheckbox.setSelected(isThemedScrollbars);
  }

  public boolean isThemedScrollbars() {
    return themedScrollbarsCheckbox.isSelected();
  }

  public void setIsAccentScrollbars(final boolean isAccentScrollbars) {
    accentScrollbarsCheckbox.setSelected(isAccentScrollbars);
  }

  public boolean isAccentScrollbars() {
    return accentScrollbarsCheckbox.isSelected();
  }

  public Integer getTabsHeight() {
    return (Integer) tabsHeightSpinnerModel.getValue();
  }

  public void setTabsHeight(final int tabsHeight) {
    tabsHeightSpinnerModel.setValue(tabsHeight);
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - Mario Smilax
  private JPanel content;
  private JCheckBox activeTabHighlightCheckbox;
  private ColorPanel activeTabHighlightColor;
  private JSpinner highlightSpinner;
  private JButton resetTabDefaultsBtn;
  private JCheckBox isUpperCaseTabsCheckbox;
  private JSpinner tabHeightSpinner;
  private JCheckBox isContrastModeCheckbox;
  private JCheckBox hideFileIconsCheckbox;
  private JCheckBox isCompactSidebarCheckbox;
  private JSpinner customSidebarSpinner;
  private JCheckBox customTreeIndentCheckbox;
  private JSpinner customIndentSpinner;
  private JCheckBox isCompactStatusbarCheckbox;
  private JCheckBox isCompactTablesCheckbox;
  private JCheckBox boldTabs;
  private JLabel arrowsStyleLabel;
  private JComboBox<ArrowsStyles> arrowsStyleComboBox;

  private JLabel customAccentColorLabel;
  private ColorPanel customAccentColorChooser;
  private JCheckBox isMaterialDesignCheckbox;
  private JCheckBox isMaterialIconsCheckbox;
  private JCheckBox isProjectViewDecoratorsCheckbox;
  private JCheckBox materialThemeCheckbox;
  private JCheckBox isThemeInStatusCheckbox;
  private JCheckBox themedScrollbarsCheckbox;
  private JCheckBox accentScrollbarsCheckbox;
  private JCheckBox darkTitleBarCheckbox;
  private JCheckBox useMaterialFontCheckbox;

  // GEN-END:variables

  public MTForm() {

    initComponents();

    // Reset tab defaults
    resetTabDefaultsBtn.addActionListener(e -> {
      final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme().getTheme();
      final Color borderColor = mtTheme.getBorderColor();
      final int thickness = mtTheme.getBorderThickness();

      setHighlightColor(borderColor);
      setHighlightColorEnabled(false);
      setHighlightThickness(thickness);
      setIsBoldTabs(false);
    });
  }

  public void setIsCompactSidebar(final boolean compactSidebar) {
    isCompactSidebarCheckbox.setSelected(compactSidebar);
    enableDisableCustomSidebarHeight(compactSidebar);
  }

  public boolean isCompactSidebar() {
    return isCompactSidebarCheckbox.isSelected();
  }

  public void setIsCompactStatusBar(final boolean compactStatusBar) {
    isCompactStatusbarCheckbox.setSelected(compactStatusBar);
  }

  public boolean isCompactStatusBar() {
    return isCompactStatusbarCheckbox.isSelected();
  }

  public void setIsCompactTables(final boolean compactTables) {
    isCompactTablesCheckbox.setSelected(compactTables);
  }

  public boolean isCompactTables() {
    return isCompactTablesCheckbox.isSelected();
  }

  public void setIsStatusBarTheme(final boolean statusBarTheme) {
    isThemeInStatusCheckbox.setSelected(statusBarTheme);
  }

  public boolean isStatusBarTheme() {
    return isThemeInStatusCheckbox.isSelected();
  }

  public boolean getIsMaterialTheme() {
    return materialThemeCheckbox.isSelected();
  }

  public void setIsMaterialTheme(final boolean materialTheme) {
    materialThemeCheckbox.setSelected(materialTheme);
  }

  public void setIsUpperCaseTabs(final boolean upperCaseTabs) {
    isUpperCaseTabsCheckbox.setSelected(upperCaseTabs);
  }

  public boolean isUpperCaseTabs() {
    return isUpperCaseTabsCheckbox.isSelected();
  }

  public void setCustomAccentColor(final Color customAccentColor) {
    customAccentColorChooser.setSelectedColor(customAccentColor);
  }

  public Color getCustomAccentColor() {
    return customAccentColorChooser.getSelectedColor();
  }

  public void setIsDarkTitleBar(final boolean darkTitleBar) {
    darkTitleBarCheckbox.setSelected(darkTitleBar);
  }

  public boolean isDarkTitleBar() {
    return darkTitleBarCheckbox.isSelected();
  }

  public void setArrowsStyle(final ArrowsStyles arrowsStyle) {
    arrowsStyleComboBox.setSelectedItem(arrowsStyle);
  }

  public ArrowsStyles getArrowsStyle() {
    return (ArrowsStyles) arrowsStyleComboBox.getSelectedItem();
  }

  public boolean getUseMaterialFont() {
    return useMaterialFontCheckbox.isSelected();
  }

  public void setUseMaterialFont(final boolean isUseMaterialFont) {
    useMaterialFontCheckbox.setSelected(isUseMaterialFont);
  }

  private void enableDisableFileIcons(final boolean isMaterialIconsSet) {
    hideFileIconsCheckbox.setEnabled(isMaterialIconsSet);
  }

  private void enableDisableCustomTreeIndent(final boolean isCustomTreeIndent) {
    customIndentSpinner.setEnabled(isCustomTreeIndent);
  }

  private void enableDisableActiveTabColor(final boolean isCustomTreeIndent) {
    activeTabHighlightColor.setEnabled(isCustomTreeIndent);
  }

  private void enableDisableCustomSidebarHeight(final boolean isCustomSidebarHeight) {
    customSidebarSpinner.setEnabled(isCustomSidebarHeight);
  }

  //region Events - Actions Listeners

  private void isMaterialIconsCheckboxActionPerformed(final ActionEvent e) {
    enableDisableFileIcons(isMaterialIconsCheckbox.isSelected());
  }

  private void customTreeIndentCheckboxActionPerformed(final ActionEvent e) {
    enableDisableCustomTreeIndent(customTreeIndentCheckbox.isSelected());
  }

  private void activeTabHighlightCheckboxActionPerformed(final ActionEvent e) {
    enableDisableActiveTabColor(activeTabHighlightCheckbox.isSelected());
  }

  private void isCompactSidebarCheckboxActionPerformed(final ActionEvent e) {
    enableDisableCustomSidebarHeight(isCompactSidebarCheckbox.isSelected());
  }
  //endregion

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Mario Smilax
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    final JPanel panel1 = new JPanel();
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    final JLabel label1 = new JLabel();
    highlightSpinner = new JSpinner();
    resetTabDefaultsBtn = new JButton();
    isUpperCaseTabsCheckbox = new JCheckBox();
    final JLabel tabHeight = new JLabel();
    tabHeightSpinner = new JSpinner();
    final Spacer vSpacer2 = new Spacer();
    final JPanel panel2 = new JPanel();
    isContrastModeCheckbox = new JCheckBox();
    final Spacer hSpacer2 = new Spacer();
    hideFileIconsCheckbox = new JCheckBox();
    isCompactSidebarCheckbox = new JCheckBox();
    customSidebarSpinner = new JSpinner();
    customTreeIndentCheckbox = new JCheckBox();
    customIndentSpinner = new JSpinner();
    isCompactStatusbarCheckbox = new JCheckBox();
    isCompactTablesCheckbox = new JCheckBox();
    boldTabs = new JCheckBox();
    customAccentColorLabel = new JLabel();
    customAccentColorChooser = new ColorPanel();
    arrowsStyleLabel = new JLabel();
    arrowsStyleComboBox = new ComboBox<>();
    arrowsStyleComboBox.setModel(new DefaultComboBoxModel(ArrowsStyles.values()));

    final JPanel panel3 = new JPanel();
    isMaterialDesignCheckbox = new JCheckBox();
    isMaterialIconsCheckbox = new JCheckBox();
    isProjectViewDecoratorsCheckbox = new JCheckBox();
    materialThemeCheckbox = new JCheckBox();
    isThemeInStatusCheckbox = new JCheckBox();
    themedScrollbarsCheckbox = new JCheckBox();
    accentScrollbarsCheckbox = new JCheckBox();
    darkTitleBarCheckbox = new JCheckBox();
    useMaterialFontCheckbox = new JCheckBox();


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

      content.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== panel1 ========
      {
        panel1.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.activetab")));
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));

        //---- activeTabHighlightCheckbox ----
        activeTabHighlightCheckbox.setText(bundle.getString("MTForm.activeTabHighlightCheckbox.text"));
        activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
        panel1.add(activeTabHighlightCheckbox, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(activeTabHighlightColor, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- label1 ----
        label1.setHorizontalTextPosition(SwingConstants.LEADING);
        label1.setLabelFor(highlightSpinner);
        label1.setText(bundle.getString("mt.border.thickness"));
        label1.setToolTipText(bundle.getString("mt.border.thickness.tooltip"));
        panel1.add(label1, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED,
            GridConstraints.SIZEPOLICY_FIXED,
            null, new Dimension(204, 18), null, 2));
        panel1.add(highlightSpinner, new GridConstraints(1, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));

        //---- resetTabDefaultsBtn ----
        resetTabDefaultsBtn.setText(bundle.getString("mt.resetdefaults"));
        resetTabDefaultsBtn.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
        panel1.add(resetTabDefaultsBtn, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- isUpperCaseTabsCheckbox ----
        isUpperCaseTabsCheckbox.setText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.text"));
        isUpperCaseTabsCheckbox.setToolTipText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.toolTipText"));
        panel1.add(isUpperCaseTabsCheckbox, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- tabHeight ----
        tabHeight.setHorizontalTextPosition(SwingConstants.LEADING);
        tabHeight.setLabelFor(highlightSpinner);
        tabHeight.setText(bundle.getString("MTForm.tabHeight"));
        tabHeight.setToolTipText(bundle.getString("MTForm.tabHeight.toolTipText"));
        panel1.add(tabHeight, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED,
            GridConstraints.SIZEPOLICY_FIXED,
            null, new Dimension(204, 18), null, 2));
        panel1.add(tabHeightSpinner, new GridConstraints(4, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));
      }
      content.add(panel1, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
      content.add(vSpacer2, new GridConstraints(3, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          null, null, null));

      //======== panel2 ========
      {
        panel2.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.panels.section")));
        panel2.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));

        //---- isContrastModeCheckbox ----
        isContrastModeCheckbox.setLabel(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setText(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setToolTipText(bundle.getString("mt.contrast.tooltip"));
        panel2.add(isContrastModeCheckbox, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));
        panel2.add(hSpacer2, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            null, null, null));

        //---- hideFileIconsCheckbox ----
        hideFileIconsCheckbox.setText(bundle.getString("MTForm.hideFileIcons"));
        hideFileIconsCheckbox.setToolTipText(bundle.getString("MTForm.hideFileIcons.tooltip"));
        panel2.add(hideFileIconsCheckbox, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isCompactSidebarCheckbox ----
        isCompactSidebarCheckbox.setText(bundle.getString("MTForm.isCompactSidebarCheckbox.text"));
        isCompactSidebarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactSidebarCheckbox.toolTipText"));
        isCompactSidebarCheckbox.addActionListener(e -> isCompactSidebarCheckboxActionPerformed(e));
        panel2.add(isCompactSidebarCheckbox, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- customSidebarSpinner ----
        customSidebarSpinner.setToolTipText(bundle.getString("MTForm.customSidebarSpinner.toolTipText"));
        panel2.add(customSidebarSpinner, new GridConstraints(2, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));

        //---- customTreeIndentCheckbox ----
        customTreeIndentCheckbox.setText(bundle.getString("MTForm.customTreeIndentCheckbox.text"));
        customTreeIndentCheckbox.setToolTipText(bundle.getString("MTForm.customTreeIndentCheckbox.toolTipText"));
        customTreeIndentCheckbox.addActionListener(e -> customTreeIndentCheckboxActionPerformed(e));
        panel2.add(customTreeIndentCheckbox, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- customIndentSpinner ----
        customIndentSpinner.setToolTipText(bundle.getString("MTForm.customIndentSpinner.toolTipText"));
        panel2.add(customIndentSpinner, new GridConstraints(3, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));

        //---- isCompactStatusbarCheckbox ----
        isCompactStatusbarCheckbox.setText(bundle.getString("MTForm.isCompactStatusbarCheckbox.text"));
        isCompactStatusbarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactStatusBar.tooltip"));
        panel2.add(isCompactStatusbarCheckbox, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isCompactTablesCheckbox ----
        isCompactTablesCheckbox.setText(bundle.getString("MTForm.isCompactTablesCheckbox.text"));
        isCompactTablesCheckbox.setToolTipText(bundle.getString("MTForm.isCompactTables.tooltip"));
        panel2.add(isCompactTablesCheckbox, new GridConstraints(5, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- boldTabs ----
        boldTabs.setLabel(bundle.getString("mt.boldtabs"));
        boldTabs.setText(bundle.getString("mt.boldtabs"));
        boldTabs.setToolTipText(bundle.getString("mt.boldtabs.tooltip"));
        panel2.add(boldTabs, new GridConstraints(6, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- customAccentColorLabel ----
        customAccentColorLabel.setText(bundle.getString("MTForm.customAccentColorLabel.text"));
        customAccentColorLabel.setToolTipText(bundle.getString("MTForm.customAccentColorLabel.toolTipText"));
        panel2.add(customAccentColorLabel, new GridConstraints(7, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null, 2));
        panel2.add(customAccentColorChooser, new GridConstraints(7, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- arrowsStyleLabel ----
        arrowsStyleLabel.setText(bundle.getString("MTForm.arrowsStyleLabel.text"));
        arrowsStyleLabel.setToolTipText(bundle.getString("MTForm.arrowsStyleLabel.toolTipText"));
        panel2.add(arrowsStyleLabel, new GridConstraints(8, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null, 2));
        //---- arrowsStyleComboBox ----
        panel2.add(arrowsStyleComboBox, new GridConstraints(8, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(120, 29), null, 2));
      }

      content.add(panel2, new GridConstraints(1, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));

      //======== panel3 ========
      {
        panel3.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.panel3.border")));
        panel3.setLayout(new GridLayoutManager(11, 3, new Insets(0, 0, 0, 0), -1, -1));

        //---- isMaterialDesignCheckbox ----
        isMaterialDesignCheckbox.setLabel(bundle.getString("MTForm.isMaterialDesignCheckbox.label"));
        isMaterialDesignCheckbox.setText(bundle.getString("MTForm.isMaterialDesignCheckbox.text"));
        isMaterialDesignCheckbox.setToolTipText(bundle.getString("MTForm.isMaterialDesignCheckbox.toolTipText"));
        panel3.add(isMaterialDesignCheckbox, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- isMaterialIconsCheckbox ----
        isMaterialIconsCheckbox.setText(bundle.getString("MTForm.isMaterialIconsCheckbox.text"));
        isMaterialIconsCheckbox.setToolTipText(bundle.getString("MTForm.materialIcons.tooltip"));
        isMaterialIconsCheckbox.addActionListener(e -> isMaterialIconsCheckboxActionPerformed(e));
        panel3.add(isMaterialIconsCheckbox, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isMaterialIconsCheckbox ----
        useMaterialFontCheckbox.setText(bundle.getString("MTForm.useMaterialFontCheckbox.text"));
        useMaterialFontCheckbox.setToolTipText(bundle.getString("MTForm.useMaterialFontCheckbox.tooltipText"));
        panel3.add(useMaterialFontCheckbox, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isProjectViewDecoratorsCheckbox ----
        isProjectViewDecoratorsCheckbox.setText(bundle.getString("MTForm.projectViewDecorators"));
        isProjectViewDecoratorsCheckbox.setToolTipText(bundle.getString("MTForm.projectViewDecorators.tooltip"));
        panel3.add(isProjectViewDecoratorsCheckbox, new GridConstraints(5, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- materialThemeCheckbox ----
        materialThemeCheckbox.setText(bundle.getString("MTForm.materialThemeCheckbox.text"));
        materialThemeCheckbox.setToolTipText(bundle.getString("MTForm.materialThemeCheckbox.toolTipText"));
        panel3.add(materialThemeCheckbox, new GridConstraints(6, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isThemeInStatusCheckbox ----
        isThemeInStatusCheckbox.setText(bundle.getString("MTForm.themeStatus"));
        isThemeInStatusCheckbox.setToolTipText(bundle.getString("MTForm.themeStatus.tooltip"));
        panel3.add(isThemeInStatusCheckbox, new GridConstraints(7, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- themedScrollbarsCheckbox ----
        themedScrollbarsCheckbox.setText(bundle.getString("MTForm.themedScrollbarsCheckbox.text"));
        themedScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.themedScrollbarsCheckbox.toolTipText"));
        panel3.add(themedScrollbarsCheckbox, new GridConstraints(8, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- accentScrollbarsCheckbox ----
        accentScrollbarsCheckbox.setText(bundle.getString("MTForm.accentScrollbarsCheckbox.text"));
        accentScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.accentScrollbarsCheckbox.toolTipText"));
        panel3.add(accentScrollbarsCheckbox, new GridConstraints(9, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- darkTitleBarCheckbox ----
        darkTitleBarCheckbox.setText(bundle.getString("MTForm.darkTitleBarCheckbox.text"));
        darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.darkTitleBarCheckbox.toolTipText"));
        panel3.add(darkTitleBarCheckbox, new GridConstraints(10, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
      }
      content.add(panel3, new GridConstraints(2, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }
}
