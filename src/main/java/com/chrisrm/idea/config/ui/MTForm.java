/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTThemeFacade;
import com.chrisrm.idea.MTThemes;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.CommonBundle;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.ListCellRendererWrapper;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class MTForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel tabsHeightSpinnerModel;
  private SpinnerModel leftTreeIndentModel;
  private SpinnerModel rightTreeIndentModel;
  private SpinnerModel customSidebarHeightModel;
  private SpinnerModel treeFontSizeModel;


  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JComponent settingsSep;
  private JPanel mainSettingsPanel;
  private JLabel selectedThemeLabel;
  private ComboBox<MTThemeFacade> themeComboBox;
  private JCheckBox isContrastModeCheckbox;
  private JLabel customAccentColorLabel;
  private ColorPanel customAccentColorChooser;
  private JComponent tabSep;
  private JTabbedPane tabbedPane1;
  private JPanel tabPanel;
  private JLabel label1;
  private JCheckBox activeTabHighlightCheckbox;
  private ColorPanel activeTabHighlightColor;
  private JSpinner highlightSpinner;
  private JCheckBox isUpperCaseTabsCheckbox;
  private JSpinner tabHeightSpinner;
  private JLabel opacityLabel;
  private JSlider tabOpacitySlider;
  private JLabel panelDesc;
  private JCheckBox isCompactStatusbarCheckbox;
  private JCheckBox isCompactTablesCheckbox;
  private JCheckBox compactDropdownsCheckbox;
  private JPanel iconsPanel;
  private JLabel iconsDesc;
  private JCheckBox isMaterialIconsCheckbox;
  private JCheckBox monochromeCheckbox;
  private JCheckBox hideFileIconsCheckbox;
  private JCheckBox decoratedFoldersCheckbox;
  private JPanel projectViewPanel;
  private JLabel projectViewDesc;
  private JCheckBox isCompactSidebarCheckbox;
  private JSpinner customSidebarSpinner;
  private JCheckBox customTreeIndentCheckbox;
  private JLabel leftLabel;
  private JSpinner leftIndentSpinner;
  private JLabel rightLabel;
  private JSpinner rightSpinner;
  private JLabel arrowsStyleLabel;
  private ComboBox<ArrowsStyles> arrowsStyleComboBox;
  private JCheckBox boldTabs;
  private JLabel fontSizeLabel;
  private JSpinner fontSizeSpinner;
  private JPanel componentsPanel;
  private JLabel componentDesc;
  private JCheckBox upperCaseButtonsCheckbox;
  private JCheckBox accentScrollbarsCheckbox;
  private JCheckBox themedScrollbarsCheckbox;
  private JPanel featuresPanel;
  private JLabel featuresDesc;
  private JCheckBox materialThemeCheckbox;
  private JCheckBox useMaterialFontCheckbox;
  private JCheckBox isMaterialDesignCheckbox;
  private JPanel otherTweaksPanel;
  private JLabel tweaksDesc;
  private JCheckBox darkTitleBarCheckbox;
  private JCheckBox isProjectViewDecoratorsCheckbox;
  private JCheckBox isThemeInStatusCheckbox;
  private JSeparator separator1;
  private JButton resetDefaultsButton;
  // GEN-END:variables

  public MTForm() {

    initComponents();

  }

  @Override
  public void init() {
    final MTConfig config = MTConfig.getInstance();
    final int highlightThickness = valueInRange(config.getHighlightThickness(), MTConfig.MIN_HIGHLIGHT_THICKNESS,
        MTConfig.MAX_HIGHLIGHT_THICKNESS);
    final int tabsHeight = valueInRange(config.getTabsHeight(), MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT);
    final int rightTreeIndent = valueInRange(config.getRightTreeIndent(), MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT);
    final int leftTreeIndent = valueInRange(config.getLeftTreeIndent(), MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT);
    final int customSidebarHeight = valueInRange(config.getCustomSidebarHeight(), MTConfig.MIN_SIDEBAR_HEIGHT, MTConfig.MAX_SIDEBAR_HEIGHT);
    final int treeFontSize = valueInRange(config.getTreeFontSize(), MTConfig.MIN_FONT_SIZE, MTConfig.MAX_FONT_SIZE);


    highlightSpinnerModel = new SpinnerNumberModel(highlightThickness, MTConfig.MIN_HIGHLIGHT_THICKNESS, MTConfig.MAX_HIGHLIGHT_THICKNESS,
        1);
    highlightSpinner.setModel(highlightSpinnerModel);
    tabsHeightSpinnerModel = new SpinnerNumberModel(tabsHeight, MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT, 1);
    tabHeightSpinner.setModel(tabsHeightSpinnerModel);
    leftTreeIndentModel = new SpinnerNumberModel(leftTreeIndent, MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT, 2);
    leftIndentSpinner.setModel(leftTreeIndentModel);
    rightTreeIndentModel = new SpinnerNumberModel(rightTreeIndent, MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT, 2);
    rightSpinner.setModel(rightTreeIndentModel);
    customSidebarHeightModel = new SpinnerNumberModel(customSidebarHeight, MTConfig.MIN_SIDEBAR_HEIGHT, MTConfig.MAX_SIDEBAR_HEIGHT, 2);
    customSidebarSpinner.setModel(customSidebarHeightModel);
    treeFontSizeModel = new SpinnerNumberModel(treeFontSize, MTConfig.MIN_FONT_SIZE, MTConfig.MAX_FONT_SIZE, 1);
    fontSizeSpinner.setModel(treeFontSizeModel);
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

  //region Selected Theme

  public MTThemeFacade getTheme() {
    return (MTThemeFacade) themeComboBox.getSelectedItem();
  }

  public void setTheme(final MTThemeFacade selectedTheme) {
    themeComboBox.setSelectedItem(selectedTheme);
  }
  //endregion

  //region Highlight Color
  public Color getHighlightColor() {
    return activeTabHighlightColor.getSelectedColor();
  }

  public void setHighlightColor(@NotNull final Color highlightColor) {
    activeTabHighlightColor.setSelectedColor(highlightColor);
  }
  //endregion

  //region Highlight color enabled
  public boolean getHighlightColorEnabled() {
    return activeTabHighlightCheckbox.isSelected();
  }

  public void setHighlightColorEnabled(final boolean enabled) {
    activeTabHighlightCheckbox.setSelected(enabled);
    enableDisableActiveTabColor(enabled);
  }
  //endregion

  //region Thickness
  public Integer getHighlightThickness() {
    return (Integer) highlightSpinnerModel.getValue();
  }

  public void setHighlightThickness(final Integer highlightThickness) {
    highlightSpinnerModel.setValue(highlightThickness);
  }
  //endregion

  //region Tabs Height
  public Integer getTabsHeight() {
    return (Integer) tabsHeightSpinnerModel.getValue();
  }

  public void setTabsHeight(final int tabsHeight) {
    tabsHeightSpinnerModel.setValue(tabsHeight);
  }
  //endregion

  //region Uppercase tabs
  public void setIsUpperCaseTabs(final boolean upperCaseTabs) {
    isUpperCaseTabsCheckbox.setSelected(upperCaseTabs);
  }

  public boolean isUpperCaseTabs() {
    return isUpperCaseTabsCheckbox.isSelected();
  }
  //endregion

  //region Tab Opacity
  public void setTabOpacity(final int opacity) {
    tabOpacitySlider.setValue(valueInRange(opacity, 0, 100));
  }

  public int getTabOpacity() {
    return tabOpacitySlider.getValue();
  }
  //endregion

  //region Contrast Mode
  public boolean getIsContrastMode() {
    return isContrastModeCheckbox.isSelected();
  }

  public void setIsContrastMode(final boolean isContrastMode) {
    isContrastModeCheckbox.setSelected(isContrastMode);
  }
  //endregion

  //region Monochrome Icons
  public void setIsMonochromeIcons(final boolean monochromeIcons) {
    monochromeCheckbox.setSelected(monochromeIcons);
  }

  public boolean getIsMonochromeIcons() {
    return monochromeCheckbox.isSelected();
  }
  //endregion

  //region Hide File Icons
  public boolean getHideFileIcons() {
    return hideFileIconsCheckbox.isSelected();
  }

  public void setHideFileIcons(final boolean hideFileIcons) {
    hideFileIconsCheckbox.setSelected(hideFileIcons);
  }
  //endregion

  //region Compact Sidebar
  public void setIsCompactSidebar(final boolean compactSidebar) {
    isCompactSidebarCheckbox.setSelected(compactSidebar);
    enableDisableCustomSidebarHeight(compactSidebar);
  }

  public boolean isCompactSidebar() {
    return isCompactSidebarCheckbox.isSelected();
  }
  //endregion

  //region Custom Sidebar Height
  public Integer getCustomSidebarHeight() {
    return (Integer) customSidebarHeightModel.getValue();
  }

  public void setCustomSidebarHeight(final Integer customSidebarHeight) {
    customSidebarHeightModel.setValue(customSidebarHeight);
  }
  //endregion

  //region Is Custom Tree Indent
  public void setIsCustomTreeIndent(final boolean isCustomTreeIndent) {
    customTreeIndentCheckbox.setSelected(isCustomTreeIndent);
    enableDisableCustomTreeIndent(isCustomTreeIndent);
  }

  public boolean isCustomTreeIndent() {
    return customTreeIndentCheckbox.isSelected();
  }
  //endregion

  //region Custom Tree Indent
  public Integer getRightTreeIndent() {
    return (Integer) rightTreeIndentModel.getValue();
  }

  public void setRightTreeIndent(final Integer rightTreeIndent) {
    rightTreeIndentModel.setValue(rightTreeIndent);
  }

  public Integer getLeftTreeIndent() {
    return (Integer) leftTreeIndentModel.getValue();
  }

  public void setLeftTreeIndent(final Integer leftTreeIndent) {
    leftTreeIndentModel.setValue(leftTreeIndent);
  }
  //endregion

  //region Tree Font Size
  public Integer getTreeFontSize() {
    return (Integer) treeFontSizeModel.getValue();
  }

  public void setTreeFontSize(final int treeFontSize) {
    treeFontSizeModel.setValue(treeFontSize);
  }
  //endregion

  //region Compact Statusbar
  public void setIsCompactStatusBar(final boolean compactStatusBar) {
    isCompactStatusbarCheckbox.setSelected(compactStatusBar);
  }

  public boolean isCompactStatusBar() {
    return isCompactStatusbarCheckbox.isSelected();
  }
  //endregion

  //region Compact Tables
  public void setIsCompactTables(final boolean compactTables) {
    isCompactTablesCheckbox.setSelected(compactTables);
  }

  public boolean isCompactTables() {
    return isCompactTablesCheckbox.isSelected();
  }
  //endregion

  //region Compact Dropdowns
  public void setIsCompactDropdowns(final boolean compactDropdowns) {
    compactDropdownsCheckbox.setSelected(compactDropdowns);
  }

  public boolean getIsCompactDropdowns() {
    return compactDropdownsCheckbox.isSelected();
  }
  //endregion

  //region Bold Directories
  public boolean getIsBoldTabs() {
    return boldTabs.isSelected();
  }

  public void setIsBoldTabs(final boolean isBold) {
    boldTabs.setSelected(isBold);
  }
  //endregion

  //region Accent Color
  public void setCustomAccentColor(final Color customAccentColor) {
    customAccentColorChooser.setSelectedColor(customAccentColor);
  }

  public Color getCustomAccentColor() {
    return customAccentColorChooser.getSelectedColor();
  }
  //endregion

  //region Arrow Styles
  public void setArrowsStyle(final ArrowsStyles arrowsStyle) {
    arrowsStyleComboBox.setSelectedItem(arrowsStyle);
  }

  public ArrowsStyles getArrowsStyle() {
    return (ArrowsStyles) arrowsStyleComboBox.getSelectedItem();
  }
  //endregion

  //region Uppercase buttons
  public void setIsUppercaseButtons(final boolean upperCaseButtons) {
    upperCaseButtonsCheckbox.setSelected(upperCaseButtons);
  }

  public boolean getIsUpperCaseButtons() {
    return upperCaseButtonsCheckbox.isSelected();
  }
  //endregion

  //region Material Components
  public boolean getIsMaterialDesign() {
    return isMaterialDesignCheckbox.isSelected();
  }

  public void setIsMaterialDesign(final boolean isMaterialDesign) {
    isMaterialDesignCheckbox.setSelected(isMaterialDesign);
  }
  //endregion

  //region Material Icons
  public void setIsUseMaterialIcons(final boolean useMaterialIcons) {
    isMaterialIconsCheckbox.setSelected(useMaterialIcons);
    enableDisableFileIcons(useMaterialIcons);
  }

  public boolean isUseMaterialIcons() {
    return isMaterialIconsCheckbox.isSelected();
  }
  //endregion

  //region Material Fonts
  public boolean getUseMaterialFont() {
    return useMaterialFontCheckbox.isSelected();
  }

  public void setUseMaterialFont(final boolean isUseMaterialFont) {
    useMaterialFontCheckbox.setSelected(isUseMaterialFont);
  }
  //endregion

  //region Material Theme
  public boolean getIsMaterialTheme() {
    return materialThemeCheckbox.isSelected();
  }

  public void setIsMaterialTheme(final boolean materialTheme) {
    materialThemeCheckbox.setSelected(materialTheme);
  }
  //endregion

  //region Project View Decorators
  public boolean getUseProjectViewDecorators() {
    return isProjectViewDecoratorsCheckbox.isSelected();
  }

  public void setUseProjectViewDecorators(final boolean useProjectViewDecorators) {
    isProjectViewDecoratorsCheckbox.setSelected(useProjectViewDecorators);
  }
  //endregion

  //region Decorated folders
  public void setDecoratedFolders(final boolean decoratedFolders) {
    decoratedFoldersCheckbox.setSelected(decoratedFolders);
  }

  public boolean isDecoratedFolders() {
    return decoratedFoldersCheckbox.isSelected();
  }
  //endregion

  //region Themed Scrollbars
  public void setIsThemedScrollbars(final boolean isThemedScrollbars) {
    themedScrollbarsCheckbox.setSelected(isThemedScrollbars);
  }

  public boolean isThemedScrollbars() {
    return themedScrollbarsCheckbox.isSelected();
  }
  //endregion

  //region Accent Scrollbars
  public void setIsAccentScrollbars(final boolean isAccentScrollbars) {
    accentScrollbarsCheckbox.setSelected(isAccentScrollbars);
  }

  public boolean isAccentScrollbars() {
    return accentScrollbarsCheckbox.isSelected();
  }
  //endregion

  //region Status Bar
  public void setIsStatusBarTheme(final boolean statusBarTheme) {
    isThemeInStatusCheckbox.setSelected(statusBarTheme);
  }

  public boolean isStatusBarTheme() {
    return isThemeInStatusCheckbox.isSelected();
  }
  //endregion

  //region Title Bar
  public void setIsDarkTitleBar(final boolean darkTitleBar) {
    darkTitleBarCheckbox.setSelected(darkTitleBar);
  }

  public boolean isDarkTitleBar() {
    return darkTitleBarCheckbox.isSelected();
  }
  //endregion

  //region Enabled listeners
  private void enableDisableFileIcons(final boolean isMaterialIconsSet) {
    hideFileIconsCheckbox.setEnabled(isMaterialIconsSet);
  }

  private void enableDisableCustomTreeIndent(final boolean isCustomTreeIndent) {
    leftIndentSpinner.setEnabled(isCustomTreeIndent);
    rightSpinner.setEnabled(isCustomTreeIndent);
  }

  private void enableDisableActiveTabColor(final boolean isCustomTreeIndent) {
    activeTabHighlightColor.setEnabled(isCustomTreeIndent);
  }

  private void enableDisableCustomSidebarHeight(final boolean isCustomSidebarHeight) {
    customSidebarSpinner.setEnabled(isCustomSidebarHeight);
  }

  private void enableDisableDecoratedFolders(final boolean selected) {
    decoratedFoldersCheckbox.setEnabled(selected);
  }
  //endregion

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

  private void isProjectViewDecoratorsCheckboxActionPerformed(final ActionEvent e) {
    enableDisableDecoratedFolders(isProjectViewDecoratorsCheckbox.isSelected());
  }

  private void resetDefaultsButtonActionPerformed(final ActionEvent e) {
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");

    final int answer = Messages.showYesNoDialog(bundle.getString("mt.reserdefaults.consent"),
        bundle.getString("mt.resetdefaults"),
        Messages.getWarningIcon());
    if (answer == Messages.YES) {
      MTConfig.getInstance().resetSettings();
    }
  }

  //endregion

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    final DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
    content = new JPanel();
    settingsSep = compFactory.createSeparator(bundle.getString("MTForm.settingsSep.text"));
    mainSettingsPanel = new JPanel();
    selectedThemeLabel = new JLabel();
    themeComboBox = new ComboBox<>();
    isContrastModeCheckbox = new JCheckBox();
    customAccentColorLabel = new JLabel();
    customAccentColorChooser = new ColorPanel();
    tabSep = compFactory.createSeparator(bundle.getString("MTForm.tabSep.text"));
    tabbedPane1 = new JTabbedPane();
    tabPanel = new JPanel();
    label1 = compFactory.createLabel(bundle.getString("MTForm.label1.textWithMnemonic"));
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    final JLabel thicknessLabel = new JLabel();
    highlightSpinner = new JSpinner();
    isUpperCaseTabsCheckbox = new JCheckBox();
    final JLabel tabHeight = new JLabel();
    tabHeightSpinner = new JSpinner();
    opacityLabel = new JLabel();
    tabOpacitySlider = new JSlider();
    final JPanel panelOptions = new JPanel();
    panelDesc = compFactory.createLabel(bundle.getString("MTForm.panelDesc.textWithMnemonic"));
    isCompactStatusbarCheckbox = new JCheckBox();
    isCompactTablesCheckbox = new JCheckBox();
    compactDropdownsCheckbox = new JCheckBox();
    iconsPanel = new JPanel();
    iconsDesc = compFactory.createLabel(bundle.getString("MTForm.iconsDesc.textWithMnemonic"));
    isMaterialIconsCheckbox = new JCheckBox();
    monochromeCheckbox = new JCheckBox();
    hideFileIconsCheckbox = new JCheckBox();
    decoratedFoldersCheckbox = new JCheckBox();
    projectViewPanel = new JPanel();
    projectViewDesc = compFactory.createLabel(bundle.getString("MTForm.projectViewDesc.textWithMnemonic"));
    isCompactSidebarCheckbox = new JCheckBox();
    customSidebarSpinner = new JSpinner();
    customTreeIndentCheckbox = new JCheckBox();
    leftLabel = new JLabel();
    leftIndentSpinner = new JSpinner();
    rightLabel = new JLabel();
    rightSpinner = new JSpinner();
    arrowsStyleLabel = new JLabel();
    arrowsStyleComboBox = new ComboBox<>();
    boldTabs = new JCheckBox();
    fontSizeLabel = new JLabel();
    fontSizeSpinner = new JSpinner();
    componentsPanel = new JPanel();
    componentDesc = compFactory.createLabel(bundle.getString("MTForm.componentDesc.textWithMnemonic"));
    upperCaseButtonsCheckbox = new JCheckBox();
    accentScrollbarsCheckbox = new JCheckBox();
    themedScrollbarsCheckbox = new JCheckBox();
    featuresPanel = new JPanel();
    featuresDesc = compFactory.createLabel(bundle.getString("MTForm.featuresDesc.textWithMnemonic"));
    materialThemeCheckbox = new JCheckBox();
    useMaterialFontCheckbox = new JCheckBox();
    isMaterialDesignCheckbox = new JCheckBox();
    otherTweaksPanel = new JPanel();
    tweaksDesc = compFactory.createLabel(bundle.getString("MTForm.tweaksDesc.textWithMnemonic"));
    darkTitleBarCheckbox = new JCheckBox();
    isProjectViewDecoratorsCheckbox = new JCheckBox();
    isThemeInStatusCheckbox = new JCheckBox();
    separator1 = new JSeparator();
    resetDefaultsButton = new JButton();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);
      content.setPreferredSize(null);
      content.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      content.setMinimumSize(null);
      content.setLayout(new MigLayout(
          "fillx,insets 0,hidemode 3,aligny top,gap 0 0",
          // columns
          "[grow,fill]",
          // rows
          "[]" +
              "[fill]" +
              "[fill]" +
              "[grow,fill]" +
              "[]" +
              "[]" +
              "[]" +
              "[]"));
      content.add(settingsSep, "cell 0 0,gapx 16,gapy 10 10");

      //======== mainSettingsPanel ========
      {
        mainSettingsPanel.setBorder(null);
        mainSettingsPanel.setLayout(new MigLayout(
            "fillx,align left center",
            // columns
            "[grow 1,shrink 0,fill]",
            // rows
            "[]" +
                "[]" +
                "[grow]"));

        //---- selectedThemeLabel ----
        selectedThemeLabel.setText(bundle.getString("MTForm.selectedThemeLabel.text"));
        selectedThemeLabel.setIcon(new ImageIcon(getClass().getResource("/icons/actions/material-theme.png")));
        selectedThemeLabel.setFont(UIManager.getFont("ComboBox.font"));
        selectedThemeLabel.setIconTextGap(8);
        mainSettingsPanel.add(selectedThemeLabel, "pad 0 2 0 0,cell 0 0,growx");
        mainSettingsPanel.add(themeComboBox, "cell 1 0");

        //---- isContrastModeCheckbox ----
        isContrastModeCheckbox.setLabel(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setText(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setToolTipText(bundle.getString("mt.contrast.tooltip"));
        isContrastModeCheckbox.setIconTextGap(8);
        mainSettingsPanel.add(isContrastModeCheckbox, "cell 0 1,align leading center,grow 0 0");

        //---- customAccentColorLabel ----
        customAccentColorLabel.setText(bundle.getString("MTForm.customAccentColorLabel.text"));
        customAccentColorLabel.setToolTipText(bundle.getString("MTForm.customAccentColorLabel.toolTipText"));
        customAccentColorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        customAccentColorLabel.setIcon(new ImageIcon(getClass().getResource("/icons/actions/accents/customAccent.png")));
        customAccentColorLabel.setIconTextGap(8);
        mainSettingsPanel.add(customAccentColorLabel, "pad 0 2 0 0,cell 0 2,growx");

        //---- customAccentColorChooser ----
        customAccentColorChooser.setMinimumSize(new Dimension(10, 18));
        customAccentColorChooser.setPreferredSize(new Dimension(61, 26));
        mainSettingsPanel.add(customAccentColorChooser, "cell 1 2,alignx right,growx 0");
      }
      content.add(mainSettingsPanel, "cell 0 1");
      content.add(tabSep, "cell 0 2,aligny center,growy 0,gapx 16,gapy 10 10");

      //======== tabbedPane1 ========
      {
        tabbedPane1.setMinimumSize(null);
        tabbedPane1.setPreferredSize(null);

        //======== tabPanel ========
        {
          tabPanel.setLayout(new MigLayout(
              "fillx,hidemode 3,align left top",
              // columns
              "[fill]" +
                  "[grow1, fill]",
              // rows
              "[]" +
                  "[]" +
                  "[]" +
                  "[]" +
                  "[]" +
                  "[]"));

          //---- label1 ----
          label1.setForeground(UIManager.getColor("Label.disabledForeground"));
          tabPanel.add(label1, "cell 0 0 2 1");

          //---- activeTabHighlightCheckbox ----
          activeTabHighlightCheckbox.setText(bundle.getString("MTForm.activeTabHighlightCheckbox.text"));
          activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
          tabPanel.add(activeTabHighlightCheckbox, "cell 0 1,align left center,grow 0 0");
          tabPanel.add(activeTabHighlightColor, "cell 1 1,align right center,grow 0 0");

          //---- thicknessLabel ----
          thicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
          thicknessLabel.setLabelFor(highlightSpinner);
          thicknessLabel.setText(bundle.getString("mt.border.thickness"));
          thicknessLabel.setToolTipText(bundle.getString("mt.border.thickness.tooltip"));
          tabPanel.add(thicknessLabel, "pad 0,cell 0 2,aligny center,grow 100 0");
          tabPanel.add(highlightSpinner, "cell 1 2,align right center,grow 0 0,width 80:80:80");

          //---- isUpperCaseTabsCheckbox ----
          isUpperCaseTabsCheckbox.setText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.text"));
          isUpperCaseTabsCheckbox.setToolTipText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.toolTipText"));
          tabPanel.add(isUpperCaseTabsCheckbox, "cell 0 3,align left center,grow 0 0");

          //---- tabHeight ----
          tabHeight.setHorizontalTextPosition(SwingConstants.LEADING);
          tabHeight.setLabelFor(highlightSpinner);
          tabHeight.setText(bundle.getString("MTForm.tabHeight"));
          tabHeight.setToolTipText(bundle.getString("MTForm.tabHeight.toolTipText"));
          tabPanel.add(tabHeight, "pad 0,cell 0 4,aligny center,grow 100 0");
          tabPanel.add(tabHeightSpinner, "cell 1 4,align right center,grow 0 0,width 80:80:80");

          //---- opacityLabel ----
          opacityLabel.setText(bundle.getString("MTForm.opacityLabel.text"));
          opacityLabel.setToolTipText(bundle.getString("MTForm.opacityLabel.toolTipText"));
          tabPanel.add(opacityLabel, "cell 0 5,aligny center,growy 0");
          tabPanel.add(tabOpacitySlider, "cell 1 5");
        }
        tabbedPane1.addTab(bundle.getString("mt.activetab"), tabPanel);

        //======== panelOptions ========
        {
          panelOptions.setBorder(null);
          panelOptions.setMinimumSize(null);
          panelOptions.setPreferredSize(null);
          panelOptions.setLayout(new MigLayout(
              "fillx,hidemode 3,gap 10 5",
              // columns
              "[grow, fill]",
              // rows
              "[fill]" +
                  "[]" +
                  "[]" +
                  "[]"));

          //---- panelDesc ----
          panelDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          panelOptions.add(panelDesc, "cell 0 0");

          //---- isCompactStatusbarCheckbox ----
          isCompactStatusbarCheckbox.setText(bundle.getString("MTForm.isCompactStatusbarCheckbox.text"));
          isCompactStatusbarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactStatusBar.tooltip"));
          panelOptions.add(isCompactStatusbarCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- isCompactTablesCheckbox ----
          isCompactTablesCheckbox.setText(bundle.getString("MTForm.isCompactTablesCheckbox.text"));
          isCompactTablesCheckbox.setToolTipText(bundle.getString("MTForm.isCompactTables.tooltip"));
          panelOptions.add(isCompactTablesCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- compactDropdownsCheckbox ----
          compactDropdownsCheckbox.setText(bundle.getString("MTForm.compactDropdownsCheckbox.text"));
          compactDropdownsCheckbox.setToolTipText(bundle.getString("MTForm.compactDropdownsCheckbox.toolTipText"));
          panelOptions.add(compactDropdownsCheckbox, "cell 0 3");
        }
        tabbedPane1.addTab(bundle.getString("mt.panels.section"), panelOptions);

        //======== iconsPanel ========
        {
          iconsPanel.setBorder(null);
          iconsPanel.setLayout(new MigLayout(
              "fillx,align left top",
              // columns
              "[fill]",
              // rows
              "[]" +
                  "[]" +
                  "[]" +
                  "[]" +
                  "[]"));

          //---- iconsDesc ----
          iconsDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          iconsPanel.add(iconsDesc, "cell 0 0");

          //---- isMaterialIconsCheckbox ----
          isMaterialIconsCheckbox.setText(bundle.getString("MTForm.isMaterialIconsCheckbox.text"));
          isMaterialIconsCheckbox.setToolTipText(bundle.getString("MTForm.materialIcons.tooltip"));
          isMaterialIconsCheckbox.addActionListener(e -> isMaterialIconsCheckboxActionPerformed(e));
          iconsPanel.add(isMaterialIconsCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- monochromeCheckbox ----
          monochromeCheckbox.setText(bundle.getString("MTForm.monochromeCheckbox.text"));
          monochromeCheckbox.setToolTipText(bundle.getString("MTForm.monochromeCheckbox.toolTipText"));
          iconsPanel.add(monochromeCheckbox, "cell 0 3");

          //---- hideFileIconsCheckbox ----
          hideFileIconsCheckbox.setText(bundle.getString("MTForm.hideFileIcons"));
          hideFileIconsCheckbox.setToolTipText(bundle.getString("MTForm.hideFileIcons.tooltip"));
          iconsPanel.add(hideFileIconsCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- decoratedFoldersCheckbox ----
          decoratedFoldersCheckbox.setText(bundle.getString("MTForm.decoratedFoldersCheckbox.text"));
          decoratedFoldersCheckbox.setToolTipText(bundle.getString("MTForm.decoratedFoldersCheckbox.toolTipText"));
          iconsPanel.add(decoratedFoldersCheckbox, "cell 0 4");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.iconsPanel.border"), iconsPanel);

        //======== projectViewPanel ========
        {
          projectViewPanel.setBorder(null);
          projectViewPanel.setLayout(new MigLayout(
              "fillx,hidemode 3,align left top",
              // columns
              "[322,fill]" +
                  "[fill]",
              // rows
              "[]" +
                  "[]" +
                  "[]" +
                  "[]" +
                  "[]" +
                  "[]"));

          //---- projectViewDesc ----
          projectViewDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          projectViewPanel.add(projectViewDesc, "cell 0 0 2 1");

          //---- isCompactSidebarCheckbox ----
          isCompactSidebarCheckbox.setText(bundle.getString("MTForm.isCompactSidebarCheckbox.text"));
          isCompactSidebarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactSidebarCheckbox.toolTipText"));
          isCompactSidebarCheckbox.addActionListener(e -> isCompactSidebarCheckboxActionPerformed(e));
          projectViewPanel.add(isCompactSidebarCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- customSidebarSpinner ----
          customSidebarSpinner.setToolTipText(bundle.getString("MTForm.customSidebarSpinner.toolTipText"));
          projectViewPanel.add(customSidebarSpinner, "cell 1 1,align right center,grow 0 0,width 80:80:80");

          //---- customTreeIndentCheckbox ----
          customTreeIndentCheckbox.setText(bundle.getString("MTForm.customTreeIndentCheckbox.text"));
          customTreeIndentCheckbox.setToolTipText(bundle.getString("MTForm.customTreeIndentCheckbox.toolTipText"));
          customTreeIndentCheckbox.addActionListener(e -> customTreeIndentCheckboxActionPerformed(e));
          projectViewPanel.add(customTreeIndentCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- leftLabel ----
          leftLabel.setText(bundle.getString("MTForm.leftLabel.text"));
          leftLabel.setFont(leftLabel.getFont().deriveFont(leftLabel.getFont().getSize() - 2f));
          projectViewPanel.add(leftLabel, "cell 1 2");

          //---- leftIndentSpinner ----
          leftIndentSpinner.setToolTipText(bundle.getString("MTForm.leftIndentSpinner.toolTipText"));
          leftIndentSpinner.setPreferredSize(new Dimension(80, 30));
          leftIndentSpinner.setMinimumSize(new Dimension(80, 30));
          projectViewPanel.add(leftIndentSpinner, "cell 1 2");

          //---- rightLabel ----
          rightLabel.setText(bundle.getString("MTForm.rightLabel.text"));
          rightLabel.setFont(rightLabel.getFont().deriveFont(rightLabel.getFont().getSize() - 2f));
          projectViewPanel.add(rightLabel, "cell 1 2");

          //---- rightSpinner ----
          rightSpinner.setToolTipText(bundle.getString("MTForm.rightSpinner.toolTipText"));
          projectViewPanel.add(rightSpinner, "cell 1 2,align right center,grow 0 0,width 80:80:80");

          //---- arrowsStyleLabel ----
          arrowsStyleLabel.setText(bundle.getString("MTForm.arrowsStyleLabel.text"));
          arrowsStyleLabel.setToolTipText(bundle.getString("MTForm.arrowsStyleLabel.toolTipText"));
          projectViewPanel.add(arrowsStyleLabel, "pad 0,cell 0 3,aligny center,grow 100 0");

          //---- arrowsStyleComboBox ----
          arrowsStyleComboBox.setToolTipText(bundle.getString("MTForm.arrowsStyleLabel.toolTipText"));
          projectViewPanel.add(arrowsStyleComboBox, "cell 1 3,align right center,grow 0 0,width 120:120:120");

          //---- boldTabs ----
          boldTabs.setLabel(bundle.getString("mt.boldtabs"));
          boldTabs.setText(bundle.getString("mt.boldtabs"));
          boldTabs.setToolTipText(bundle.getString("mt.boldtabs.tooltip"));
          projectViewPanel.add(boldTabs, "cell 0 4,align left center,grow 0 0");

          //---- fontSizeLabel ----
          fontSizeLabel.setText(bundle.getString("MTForm.fontSizeLabel.text"));
          fontSizeLabel.setToolTipText("Customize the font size of the project view");
          projectViewPanel.add(fontSizeLabel, "cell 0 5");

          //---- fontSizeSpinner ----
          fontSizeSpinner.setToolTipText(bundle.getString("MTForm.fontSizeSpinner.toolTipText"));
          projectViewPanel.add(fontSizeSpinner, "cell 1 5,align right center,grow 0 0,width 80:80:80");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.projectViewPanel.border"), projectViewPanel);

        //======== componentsPanel ========
        {
          componentsPanel.setBorder(null);
          componentsPanel.setLayout(new MigLayout(
              "fillx,hidemode 3,align left top",
              // columns
              "[fill]",
              // rows
              "[]" +
                  "[]" +
                  "[]" +
                  "[]"));

          //---- componentDesc ----
          componentDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          componentsPanel.add(componentDesc, "cell 0 0");

          //---- upperCaseButtonsCheckbox ----
          upperCaseButtonsCheckbox.setText(bundle.getString("MTForm.upperCaseButtonsCheckbox.text"));
          upperCaseButtonsCheckbox.setToolTipText(bundle.getString("MTForm.upperCaseButtonsCheckbox.toolTipText"));
          componentsPanel.add(upperCaseButtonsCheckbox, "cell 0 1");

          //---- accentScrollbarsCheckbox ----
          accentScrollbarsCheckbox.setText(bundle.getString("MTForm.accentScrollbarsCheckbox.text"));
          accentScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.accentScrollbarsCheckbox.toolTipText"));
          componentsPanel.add(accentScrollbarsCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- themedScrollbarsCheckbox ----
          themedScrollbarsCheckbox.setText(bundle.getString("MTForm.themedScrollbarsCheckbox.text"));
          themedScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.themedScrollbarsCheckbox.toolTipText"));
          componentsPanel.add(themedScrollbarsCheckbox, "cell 0 3");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.componentsPanel.tab.title"), componentsPanel);

        //======== featuresPanel ========
        {
          featuresPanel.setBorder(null);
          featuresPanel.setLayout(new MigLayout(
              "fillx,hidemode 3,align left top",
              // columns
              "[fill]",
              // rows
              "[]" +
                  "[]" +
                  "[]" +
                  "[]"));

          //---- featuresDesc ----
          featuresDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          featuresPanel.add(featuresDesc, "cell 0 0");

          //---- materialThemeCheckbox ----
          materialThemeCheckbox.setText(bundle.getString("MTForm.materialThemeCheckbox.text"));
          materialThemeCheckbox.setToolTipText(bundle.getString("MTForm.materialThemeCheckbox.toolTipText"));
          featuresPanel.add(materialThemeCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- useMaterialFontCheckbox ----
          useMaterialFontCheckbox.setText(bundle.getString("MTForm.useMaterialFontCheckbox.text"));
          useMaterialFontCheckbox.setToolTipText(bundle.getString("MTForm.useMaterialFontCheckbox.tooltipText"));
          featuresPanel.add(useMaterialFontCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- isMaterialDesignCheckbox ----
          isMaterialDesignCheckbox.setLabel(bundle.getString("MTForm.isMaterialDesignCheckbox.label"));
          isMaterialDesignCheckbox.setText(bundle.getString("MTForm.isMaterialDesignCheckbox.text"));
          isMaterialDesignCheckbox.setToolTipText(bundle.getString("MTForm.isMaterialDesignCheckbox.toolTipText"));
          featuresPanel.add(isMaterialDesignCheckbox, "cell 0 3,align left center,grow 0 0");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.featuresPanel.border"), featuresPanel);

        //======== otherTweaksPanel ========
        {
          otherTweaksPanel.setBorder(null);
          otherTweaksPanel.setLayout(new MigLayout(
              "fillx,hidemode 3,align left top",
              // columns
              "[fill]",
              // rows
              "[]" +
                  "[]" +
                  "[]" +
                  "[]"));

          //---- tweaksDesc ----
          tweaksDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          otherTweaksPanel.add(tweaksDesc, "cell 0 0");

          //---- darkTitleBarCheckbox ----
          darkTitleBarCheckbox.setText(bundle.getString("MTForm.darkTitleBarCheckbox.text"));
          darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.darkTitleBarCheckbox.toolTipText"));
          darkTitleBarCheckbox.addActionListener(e -> isDarkTitleBarActionPerformed(e));
          otherTweaksPanel.add(darkTitleBarCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- isProjectViewDecoratorsCheckbox ----
          isProjectViewDecoratorsCheckbox.setText(bundle.getString("MTForm.projectViewDecorators"));
          isProjectViewDecoratorsCheckbox.setToolTipText(bundle.getString("MTForm.projectViewDecorators.tooltip"));
          isProjectViewDecoratorsCheckbox.addActionListener(e -> isProjectViewDecoratorsCheckboxActionPerformed(e));
          otherTweaksPanel.add(isProjectViewDecoratorsCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- isThemeInStatusCheckbox ----
          isThemeInStatusCheckbox.setText(bundle.getString("MTForm.themeStatus"));
          isThemeInStatusCheckbox.setToolTipText(bundle.getString("MTForm.themeStatus.tooltip"));
          otherTweaksPanel.add(isThemeInStatusCheckbox, "cell 0 3,align left center,grow 0 0");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.otherTweaksPanel.border"), otherTweaksPanel);
      }
      content.add(tabbedPane1, "pad 0,cell 0 3,growx,gapx 10 10,gapy 10 10");
      content.add(separator1, "cell 0 4");

      //---- resetDefaultsButton ----
      resetDefaultsButton.setText(bundle.getString("mt.resetdefaults"));
      resetDefaultsButton.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
      resetDefaultsButton.addActionListener(e -> resetDefaultsButtonActionPerformed(e));
      content.add(resetDefaultsButton, "pad 0,cell 0 5,align trailing center,grow 0 0,wmin 200");
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

    // Themes
    themeComboBox.setModel(new DefaultComboBoxModel<>(MTThemes.values()));
    themeComboBox.setRenderer(new ListCellRendererWrapper<MTThemeFacade>() {
      @Override
      public void customize(final JList list, final MTThemeFacade value, final int index, final boolean selected, final boolean hasFocus) {
        final Icon baseIcon;
        if (value == null) {
          return;
        }
        baseIcon = value.getIcon();
        setIcon(baseIcon);
        setText(value.getThemeName());
      }
    });

    // Arrows
    arrowsStyleComboBox.setModel(new DefaultComboBoxModel<>(ArrowsStyles.values()));
    arrowsStyleComboBox.setRenderer(new ListCellRendererWrapper<ArrowsStyles>() {
      @Override
      public void customize(final JList list, final ArrowsStyles value, final int index, final boolean selected, final boolean hasFocus) {
        final Icon baseIcon;
        if (value == null) {
          return;
        }
        baseIcon = value.getIcon();
        setIcon(baseIcon);
      }
    });
  }

  private int valueInRange(final int value, final int min, final int max) {
    return Integer.min(max, Integer.max(value, min));
  }
}
