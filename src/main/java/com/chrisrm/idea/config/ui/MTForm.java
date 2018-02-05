/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.CommonBundle;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorPanel;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

public class MTForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel tabsHeightSpinnerModel;
  private SpinnerModel customTreeIndentModel;
  private SpinnerModel customSidebarHeightModel;

  @Override
  public void init() {
    final MTConfig config = MTConfig.getInstance();
    final int highlightThickness = valueInRange(config.getHighlightThickness(), MTConfig.MIN_HIGHLIGHT_THICKNESS,
                                                MTConfig.MAX_HIGHLIGHT_THICKNESS);
    final int tabsHeight = valueInRange(config.getTabsHeight(), MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT);
    final int customTreeIndent = valueInRange(config.getCustomTreeIndent(), MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT);
    final int customSidebarHeight = valueInRange(config.getCustomTreeIndent(), MTConfig.MIN_SIDEBAR_HEIGHT, MTConfig.MAX_SIDEBAR_HEIGHT);
    highlightSpinnerModel = new SpinnerNumberModel(highlightThickness, MTConfig.MIN_HIGHLIGHT_THICKNESS, MTConfig.MAX_HIGHLIGHT_THICKNESS,
                                                   1);
    highlightSpinner.setModel(highlightSpinnerModel);
    tabsHeightSpinnerModel = new SpinnerNumberModel(tabsHeight, MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT, 1);
    tabHeightSpinner.setModel(tabsHeightSpinnerModel);
    customTreeIndentModel = new SpinnerNumberModel(customTreeIndent, MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT, 2);
    customIndentSpinner.setModel(customTreeIndentModel);
    customSidebarHeightModel = new SpinnerNumberModel(customSidebarHeight, MTConfig.MIN_SIDEBAR_HEIGHT, MTConfig.MAX_SIDEBAR_HEIGHT, 2);
    customSidebarSpinner.setModel(customSidebarHeightModel);
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

  public void setTabOpacity(final int opacity) {
    tabOpacitySlider.setValue(valueInRange(opacity, 0, 100));
  }

  public int getTabOpacity() {
    return tabOpacitySlider.getValue();
  }

  public void setIsCompactDropdowns(final boolean compactDropdowns) {
    compactDropdownsCheckbox.setSelected(compactDropdowns);
  }

  public boolean getIsCompactDropdowns() {
    return compactDropdownsCheckbox.isSelected();
  }

  public void setIsMonochromeIcons(final boolean monochromeIcons) {
    monochromeCheckbox.setSelected(monochromeIcons);
  }

  public boolean getIsMonochromeIcons() {
    return monochromeCheckbox.isSelected();
  }

  public void setIsUppercaseButtons(final boolean upperCaseButtons) {
    upperCaseButtonsCheckbox.setSelected(upperCaseButtons);
  }

  public boolean getIsUpperCaseButtons() {
    return upperCaseButtonsCheckbox.isSelected();
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

  public boolean getUseProjectViewDecorators() {
    return isProjectViewDecoratorsCheckbox.isSelected();
  }

  public void setUseProjectViewDecorators(final boolean useProjectViewDecorators) {
    isProjectViewDecoratorsCheckbox.setSelected(useProjectViewDecorators);
  }

  public boolean getHideFileIcons() {
    return hideFileIconsCheckbox.isSelected();
  }

  public void setHideFileIcons(final boolean hideFileIcons) {
    hideFileIconsCheckbox.setSelected(hideFileIcons);
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
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JCheckBox activeTabHighlightCheckbox;
  private ColorPanel activeTabHighlightColor;
  private JSpinner highlightSpinner;
  private JButton resetTabDefaultsBtn;
  private JCheckBox isUpperCaseTabsCheckbox;
  private JSpinner tabHeightSpinner;
  private JLabel opacityLabel;
  private JSlider tabOpacitySlider;
  private JCheckBox isContrastModeCheckbox;
  private JCheckBox monochromeCheckbox;
  private JCheckBox hideFileIconsCheckbox;
  private JCheckBox isCompactSidebarCheckbox;
  private JSpinner customSidebarSpinner;
  private JCheckBox customTreeIndentCheckbox;
  private JSpinner customIndentSpinner;
  private JCheckBox isCompactStatusbarCheckbox;
  private JCheckBox isCompactTablesCheckbox;
  private JCheckBox compactDropdownsCheckbox;
  private JCheckBox boldTabs;
  private JLabel customAccentColorLabel;
  private ColorPanel customAccentColorChooser;
  private JLabel arrowsStyleLabel;
  private ComboBox<ArrowsStyles> arrowsStyleComboBox;
  private JCheckBox upperCaseButtonsCheckbox;
  private JCheckBox isMaterialDesignCheckbox;
  private JCheckBox isMaterialIconsCheckbox;
  private JCheckBox useMaterialFontCheckbox;
  private JCheckBox isProjectViewDecoratorsCheckbox;
  private JCheckBox materialThemeCheckbox;
  private JCheckBox isThemeInStatusCheckbox;
  private JCheckBox themedScrollbarsCheckbox;
  private JCheckBox accentScrollbarsCheckbox;
  private JCheckBox darkTitleBarCheckbox;
  private JLabel accentTitleBarLabel;
  private ColorPanel accentTitleBarChooser;
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

  public void setAccentTitleBarColor(final Color accentTitleBar) {
    accentTitleBarChooser.setSelectedColor(accentTitleBar);
  }

  public Color getAccentTitleBarColor() {
    return accentTitleBarChooser.getSelectedColor();
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

  private void isDarkTitleBarActionPerformed(final ActionEvent e) {
    if (SystemInfo.isWin10OrNewer && darkTitleBarCheckbox.isSelected()) {
      final int dialog = Messages.showOkCancelDialog(
          MaterialThemeBundle.message("mt.windowsTitleBar.message"),
          MaterialThemeBundle.message("mt.windowsTitleBar.title"),
          CommonBundle.getOkButtonText(),
          CommonBundle.getCancelButtonText(),
          Messages.getWarningIcon());

      if (dialog == Messages.CANCEL) {
        darkTitleBarCheckbox.setSelected(false);
      }
    }
  }
  //endregion

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    final JPanel panel1 = new JPanel();
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    final JLabel thicknessLabel = new JLabel();
    highlightSpinner = new JSpinner();
    resetTabDefaultsBtn = new JButton();
    isUpperCaseTabsCheckbox = new JCheckBox();
    final JLabel tabHeight = new JLabel();
    tabHeightSpinner = new JSpinner();
    opacityLabel = new JLabel();
    tabOpacitySlider = new JSlider();
    final JPanel panel2 = new JPanel();
    isContrastModeCheckbox = new JCheckBox();
    monochromeCheckbox = new JCheckBox();
    hideFileIconsCheckbox = new JCheckBox();
    isCompactSidebarCheckbox = new JCheckBox();
    customSidebarSpinner = new JSpinner();
    customTreeIndentCheckbox = new JCheckBox();
    customIndentSpinner = new JSpinner();
    isCompactStatusbarCheckbox = new JCheckBox();
    isCompactTablesCheckbox = new JCheckBox();
    compactDropdownsCheckbox = new JCheckBox();
    boldTabs = new JCheckBox();
    customAccentColorLabel = new JLabel();
    customAccentColorChooser = new ColorPanel();
    arrowsStyleLabel = new JLabel();
    arrowsStyleComboBox = new ComboBox<>();
    final JPanel panel3 = new JPanel();
    upperCaseButtonsCheckbox = new JCheckBox();
    isMaterialDesignCheckbox = new JCheckBox();
    isMaterialIconsCheckbox = new JCheckBox();
    useMaterialFontCheckbox = new JCheckBox();
    isProjectViewDecoratorsCheckbox = new JCheckBox();
    materialThemeCheckbox = new JCheckBox();
    isThemeInStatusCheckbox = new JCheckBox();
    themedScrollbarsCheckbox = new JCheckBox();
    accentScrollbarsCheckbox = new JCheckBox();
    darkTitleBarCheckbox = new JCheckBox();
    accentTitleBarLabel = new JLabel();
    accentTitleBarChooser = new ColorPanel();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);
      content.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== panel1 ========
      {
        panel1.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.activetab")));
        panel1.setLayout(new MigLayout(
            "insets 4 4 0 0,hidemode 3,gap 10 5",
            // columns
            "[fill]" +
            "[grow,fill]",
            // rows
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[fill]"));

        //---- activeTabHighlightCheckbox ----
        activeTabHighlightCheckbox.setText(bundle.getString("MTForm.activeTabHighlightCheckbox.text"));
        activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
        panel1.add(activeTabHighlightCheckbox, "cell 0 0,align left center,grow 0 0");
        panel1.add(activeTabHighlightColor, "cell 1 0,align right center,grow 0 0");

        //---- thicknessLabel ----
        thicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        thicknessLabel.setLabelFor(highlightSpinner);
        thicknessLabel.setText(bundle.getString("mt.border.thickness"));
        thicknessLabel.setToolTipText(bundle.getString("mt.border.thickness.tooltip"));
        panel1.add(thicknessLabel, "pad 0,cell 0 1,aligny center,grow 100 0");
        panel1.add(highlightSpinner, "cell 1 1,align right center,grow 0 0,width 80:80:80");

        //---- resetTabDefaultsBtn ----
        resetTabDefaultsBtn.setText(bundle.getString("mt.resetdefaults"));
        resetTabDefaultsBtn.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
        panel1.add(resetTabDefaultsBtn, "cell 0 2,aligny center,grow 100 0");

        //---- isUpperCaseTabsCheckbox ----
        isUpperCaseTabsCheckbox.setText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.text"));
        isUpperCaseTabsCheckbox.setToolTipText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.toolTipText"));
        panel1.add(isUpperCaseTabsCheckbox, "cell 0 3,align left center,grow 0 0");

        //---- tabHeight ----
        tabHeight.setHorizontalTextPosition(SwingConstants.LEADING);
        tabHeight.setLabelFor(highlightSpinner);
        tabHeight.setText(bundle.getString("MTForm.tabHeight"));
        tabHeight.setToolTipText(bundle.getString("MTForm.tabHeight.toolTipText"));
        panel1.add(tabHeight, "pad 0,cell 0 4,aligny center,grow 100 0");
        panel1.add(tabHeightSpinner, "cell 1 4,align right center,grow 0 0,width 80:80:80");

        //---- opacityLabel ----
        opacityLabel.setText(bundle.getString("MTForm.opacityLabel.text"));
        opacityLabel.setToolTipText(bundle.getString("MTForm.opacityLabel.toolTipText"));
        panel1.add(opacityLabel, "cell 0 5");
        panel1.add(tabOpacitySlider, "cell 1 5");
      }
      content.add(panel1, new GridConstraints(0, 0, 1, 1,
                                              GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                              null, null, null));

      //======== panel2 ========
      {
        panel2.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.panels.section")));
        panel2.setLayout(new MigLayout(
            "insets 4 4 0 0,hidemode 3,gap 10 5",
            // columns
            "[fill]" +
            "[grow,fill]",
            // rows
            "[fill]" +
            "[]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[]" +
            "[fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]"));

        //---- isContrastModeCheckbox ----
        isContrastModeCheckbox.setLabel(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setText(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setToolTipText(bundle.getString("mt.contrast.tooltip"));
        panel2.add(isContrastModeCheckbox, "cell 0 0,align left center,grow 0 0");

        //---- monochromeCheckbox ----
        monochromeCheckbox.setText(bundle.getString("MTForm.monochromeCheckbox.text"));
        monochromeCheckbox.setToolTipText(bundle.getString("MTForm.monochromeCheckbox.toolTipText"));
        panel2.add(monochromeCheckbox, "cell 0 1");

        //---- hideFileIconsCheckbox ----
        hideFileIconsCheckbox.setText(bundle.getString("MTForm.hideFileIcons"));
        hideFileIconsCheckbox.setToolTipText(bundle.getString("MTForm.hideFileIcons.tooltip"));
        panel2.add(hideFileIconsCheckbox, "cell 0 2,align left center,grow 0 0");

        //---- isCompactSidebarCheckbox ----
        isCompactSidebarCheckbox.setText(bundle.getString("MTForm.isCompactSidebarCheckbox.text"));
        isCompactSidebarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactSidebarCheckbox.toolTipText"));
        isCompactSidebarCheckbox.addActionListener(e -> isCompactSidebarCheckboxActionPerformed(e));
        panel2.add(isCompactSidebarCheckbox, "cell 0 3,align left center,grow 0 0");

        //---- customSidebarSpinner ----
        customSidebarSpinner.setToolTipText(bundle.getString("MTForm.customSidebarSpinner.toolTipText"));
        panel2.add(customSidebarSpinner, "cell 1 3,align right center,grow 0 0,width 80:80:80");

        //---- customTreeIndentCheckbox ----
        customTreeIndentCheckbox.setText(bundle.getString("MTForm.customTreeIndentCheckbox.text"));
        customTreeIndentCheckbox.setToolTipText(bundle.getString("MTForm.customTreeIndentCheckbox.toolTipText"));
        customTreeIndentCheckbox.addActionListener(e -> customTreeIndentCheckboxActionPerformed(e));
        panel2.add(customTreeIndentCheckbox, "cell 0 4,align left center,grow 0 0");

        //---- customIndentSpinner ----
        customIndentSpinner.setToolTipText(bundle.getString("MTForm.customIndentSpinner.toolTipText"));
        panel2.add(customIndentSpinner, "cell 1 4,align right center,grow 0 0,width 80:80:80");

        //---- isCompactStatusbarCheckbox ----
        isCompactStatusbarCheckbox.setText(bundle.getString("MTForm.isCompactStatusbarCheckbox.text"));
        isCompactStatusbarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactStatusBar.tooltip"));
        panel2.add(isCompactStatusbarCheckbox, "cell 0 5,align left center,grow 0 0");

        //---- isCompactTablesCheckbox ----
        isCompactTablesCheckbox.setText(bundle.getString("MTForm.isCompactTablesCheckbox.text"));
        isCompactTablesCheckbox.setToolTipText(bundle.getString("MTForm.isCompactTables.tooltip"));
        panel2.add(isCompactTablesCheckbox, "cell 0 6,align left center,grow 0 0");

        //---- compactDropdownsCheckbox ----
        compactDropdownsCheckbox.setText(bundle.getString("MTForm.compactDropdownsCheckbox.text"));
        compactDropdownsCheckbox.setToolTipText(bundle.getString("MTForm.compactDropdownsCheckbox.toolTipText"));
        panel2.add(compactDropdownsCheckbox, "cell 0 7");

        //---- boldTabs ----
        boldTabs.setLabel(bundle.getString("mt.boldtabs"));
        boldTabs.setText(bundle.getString("mt.boldtabs"));
        boldTabs.setToolTipText(bundle.getString("mt.boldtabs.tooltip"));
        panel2.add(boldTabs, "cell 0 8,align left center,grow 0 0");

        //---- customAccentColorLabel ----
        customAccentColorLabel.setText(bundle.getString("MTForm.customAccentColorLabel.text"));
        customAccentColorLabel.setToolTipText(bundle.getString("MTForm.customAccentColorLabel.toolTipText"));
        customAccentColorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel2.add(customAccentColorLabel, "pad 0,cell 0 9 2 1,align left center,grow 0 0");
        panel2.add(customAccentColorChooser, "cell 1 9,align right center,grow 0 0");

        //---- arrowsStyleLabel ----
        arrowsStyleLabel.setText(bundle.getString("MTForm.arrowsStyleLabel.text"));
        arrowsStyleLabel.setToolTipText(bundle.getString("MTForm.arrowsStyleLabel.toolTipText"));
        panel2.add(arrowsStyleLabel, "pad 0,cell 0 10,aligny center,grow 100 0");

        //---- arrowsStyleComboBox ----
        arrowsStyleComboBox.setToolTipText(bundle.getString("MTForm.arrowsStyleLabel.toolTipText"));
        panel2.add(arrowsStyleComboBox, "cell 1 10,align right center,grow 0 0,width 120:120:120");
      }
      content.add(panel2, new GridConstraints(1, 0, 1, 1,
                                              GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                              null, null, null));

      //======== panel3 ========
      {
        panel3.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.panel3.border")));
        panel3.setLayout(new MigLayout(
            "insets 4 4 0 0,hidemode 3,gap 10 5",
            // columns
            "[grow 1,fill]",
            // rows
            "[22]" +
            "[fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[grow 1,fill]" +
            "[]" +
            "[grow 1,fill]"));

        //---- upperCaseButtonsCheckbox ----
        upperCaseButtonsCheckbox.setText(bundle.getString("MTForm.upperCaseButtonsCheckbox.text"));
        upperCaseButtonsCheckbox.setToolTipText(bundle.getString("MTForm.upperCaseButtonsCheckbox.toolTipText"));
        panel3.add(upperCaseButtonsCheckbox, "cell 0 0");

        //---- isMaterialDesignCheckbox ----
        isMaterialDesignCheckbox.setLabel(bundle.getString("MTForm.isMaterialDesignCheckbox.label"));
        isMaterialDesignCheckbox.setText(bundle.getString("MTForm.isMaterialDesignCheckbox.text"));
        isMaterialDesignCheckbox.setToolTipText(bundle.getString("MTForm.isMaterialDesignCheckbox.toolTipText"));
        panel3.add(isMaterialDesignCheckbox, "cell 0 1,align left center,grow 0 0");

        //---- isMaterialIconsCheckbox ----
        isMaterialIconsCheckbox.setText(bundle.getString("MTForm.isMaterialIconsCheckbox.text"));
        isMaterialIconsCheckbox.setToolTipText(bundle.getString("MTForm.materialIcons.tooltip"));
        isMaterialIconsCheckbox.addActionListener(e -> isMaterialIconsCheckboxActionPerformed(e));
        panel3.add(isMaterialIconsCheckbox, "cell 0 2,align left center,grow 0 0");

        //---- useMaterialFontCheckbox ----
        useMaterialFontCheckbox.setText(bundle.getString("MTForm.useMaterialFontCheckbox.text"));
        useMaterialFontCheckbox.setToolTipText(bundle.getString("MTForm.useMaterialFontCheckbox.tooltipText"));
        panel3.add(useMaterialFontCheckbox, "cell 0 3,align left center,grow 0 0");

        //---- isProjectViewDecoratorsCheckbox ----
        isProjectViewDecoratorsCheckbox.setText(bundle.getString("MTForm.projectViewDecorators"));
        isProjectViewDecoratorsCheckbox.setToolTipText(bundle.getString("MTForm.projectViewDecorators.tooltip"));
        panel3.add(isProjectViewDecoratorsCheckbox, "cell 0 4,align left center,grow 0 0");

        //---- materialThemeCheckbox ----
        materialThemeCheckbox.setText(bundle.getString("MTForm.materialThemeCheckbox.text"));
        materialThemeCheckbox.setToolTipText(bundle.getString("MTForm.materialThemeCheckbox.toolTipText"));
        panel3.add(materialThemeCheckbox, "cell 0 5,align left center,grow 0 0");

        //---- isThemeInStatusCheckbox ----
        isThemeInStatusCheckbox.setText(bundle.getString("MTForm.themeStatus"));
        isThemeInStatusCheckbox.setToolTipText(bundle.getString("MTForm.themeStatus.tooltip"));
        panel3.add(isThemeInStatusCheckbox, "cell 0 6,align left center,grow 0 0");

        //---- themedScrollbarsCheckbox ----
        themedScrollbarsCheckbox.setText(bundle.getString("MTForm.themedScrollbarsCheckbox.text"));
        themedScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.themedScrollbarsCheckbox.toolTipText"));
        panel3.add(themedScrollbarsCheckbox, "cell 0 7,align left center,grow 0 0");

        //---- accentScrollbarsCheckbox ----
        accentScrollbarsCheckbox.setText(bundle.getString("MTForm.accentScrollbarsCheckbox.text"));
        accentScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.accentScrollbarsCheckbox.toolTipText"));
        panel3.add(accentScrollbarsCheckbox, "cell 0 8,align left center,grow 0 0");

        //---- darkTitleBarCheckbox ----
        darkTitleBarCheckbox.setText(bundle.getString("MTForm.darkTitleBarCheckbox.text"));
        darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.darkTitleBarCheckbox.toolTipText"));
        darkTitleBarCheckbox.addActionListener(e -> isDarkTitleBarActionPerformed(e));
        panel3.add(darkTitleBarCheckbox, "cell 0 9,align left center,grow 0 0");

        //---- accentTitleBarLabel ----
        accentTitleBarLabel.setText(bundle.getString("MTForm.accentTitleBarLabel.text"));
        accentTitleBarLabel.setToolTipText(bundle.getString("MTForm.accentTitleBarLabel.toolTipText"));
        accentTitleBarLabel.setHorizontalAlignment(SwingConstants.LEFT);
        accentTitleBarLabel.setLabelFor(accentTitleBarChooser);
        panel3.add(accentTitleBarLabel, "pad 0,cell 0 10,aligny center,grow 100 0");
        panel3.add(accentTitleBarChooser, "cell 0 10");
      }
      content.add(panel3, new GridConstraints(2, 0, 1, 1,
                                              GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                              GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                              null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    if (SystemInfo.isWin10OrNewer) {
      darkTitleBarCheckbox.setText(bundle.getString("MTForm.darkTitleBarCheckbox.text"));
      darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.darkTitleBarCheckbox.toolTipText"));
    } else if (SystemInfo.isMac) {
      darkTitleBarCheckbox.setText(bundle.getString("MTForm.darkTitleBarCheckbox.textMac"));
      darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.darkTitleBarCheckbox.toolTipTextMac"));
    } else {
      darkTitleBarCheckbox.setText(bundle.getString("MTForm.darkTitleBarCheckbox.text"));
      darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.darkTitleBarCheckbox.toolTipText"));
    }

    //---- accentTitleBar ----
    if (!SystemInfo.isWin10OrNewer) {
      panel3.remove(accentTitleBarLabel);
      panel3.remove(accentTitleBarChooser);
    }

    arrowsStyleComboBox.setModel(new DefaultComboBoxModel<>(ArrowsStyles.values()));
  }
}
