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
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.CommonBundle;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorPanel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class MTForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel tabsHeightSpinnerModel;
  private SpinnerModel customTreeIndentModel;
  private SpinnerModel customSidebarHeightModel;

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JPanel mainSettingsPanel;
  private JComponent settingsSep;
  private JLabel selectedThemeLabel;
  private JComboBox themeComboBox;
  private JCheckBox isContrastModeCheckbox;
  private JLabel customAccentColorLabel;
  private ColorPanel customAccentColorChooser;
  private JComponent tabSep;
  private JTabbedPane tabbedPane1;
  private JPanel tabPanel;
  private JCheckBox activeTabHighlightCheckbox;
  private ColorPanel activeTabHighlightColor;
  private JSpinner highlightSpinner;
  private JCheckBox isUpperCaseTabsCheckbox;
  private JSpinner tabHeightSpinner;
  private JLabel opacityLabel;
  private JSlider tabOpacitySlider;
  private JPanel iconsPanel;
  private JCheckBox monochromeCheckbox;
  private JCheckBox hideFileIconsCheckbox;
  private JPanel projectViewPanel;
  private JCheckBox isCompactSidebarCheckbox;
  private JSpinner customSidebarSpinner;
  private JCheckBox customTreeIndentCheckbox;
  private JSpinner customIndentSpinner;
  private JCheckBox decoratedFoldersCheckbox;
  private JLabel arrowsStyleLabel;
  private ComboBox<ArrowsStyles> arrowsStyleComboBox;
  private JCheckBox boldTabs;
  private JPanel compactPanel;
  private JCheckBox isCompactStatusbarCheckbox;
  private JCheckBox isCompactTablesCheckbox;
  private JCheckBox compactDropdownsCheckbox;
  private JPanel featuresPanel;
  private JCheckBox materialThemeCheckbox;
  private JCheckBox isMaterialIconsCheckbox;
  private JCheckBox useMaterialFontCheckbox;
  private JCheckBox isMaterialDesignCheckbox;
  private JPanel componentsPanel;
  private JCheckBox upperCaseButtonsCheckbox;
  private JCheckBox accentScrollbarsCheckbox;
  private JCheckBox themedScrollbarsCheckbox;
  private JPanel otherTweaksPanel;
  private JCheckBox darkTitleBarCheckbox;
  private JCheckBox isProjectViewDecoratorsCheckbox;
  private JCheckBox isThemeInStatusCheckbox;
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

  //region [[Tab Options]]
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
  //endregion

  //region [[Panel Options]]
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
  public Integer getCustomTreeIndent() {
    return (Integer) customTreeIndentModel.getValue();
  }

  public void setCustomTreeIndent(final Integer customTreeIndent) {
    customTreeIndentModel.setValue(customTreeIndent);
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
  //endregion

  //region [[Component Options]]
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
  //endregion

  //region Enabled listeners
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

  //endregion

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
    content = new JPanel();
    mainSettingsPanel = new JPanel();
    settingsSep = compFactory.createSeparator(bundle.getString("MTForm.settingsSep.text"));
    selectedThemeLabel = new JLabel();
    themeComboBox = new JComboBox();
    isContrastModeCheckbox = new JCheckBox();
    customAccentColorLabel = new JLabel();
    customAccentColorChooser = new ColorPanel();
    tabSep = compFactory.createSeparator(bundle.getString("MTForm.tabSep.text"));
    tabbedPane1 = new JTabbedPane();
    JPanel tabOptions = new JPanel();
    tabPanel = new JPanel();
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    JLabel thicknessLabel = new JLabel();
    highlightSpinner = new JSpinner();
    isUpperCaseTabsCheckbox = new JCheckBox();
    JLabel tabHeight = new JLabel();
    tabHeightSpinner = new JSpinner();
    opacityLabel = new JLabel();
    tabOpacitySlider = new JSlider();
    JPanel panelOptions = new JPanel();
    iconsPanel = new JPanel();
    monochromeCheckbox = new JCheckBox();
    hideFileIconsCheckbox = new JCheckBox();
    projectViewPanel = new JPanel();
    isCompactSidebarCheckbox = new JCheckBox();
    customSidebarSpinner = new JSpinner();
    customTreeIndentCheckbox = new JCheckBox();
    customIndentSpinner = new JSpinner();
    decoratedFoldersCheckbox = new JCheckBox();
    arrowsStyleLabel = new JLabel();
    arrowsStyleComboBox = new ComboBox<>();
    boldTabs = new JCheckBox();
    compactPanel = new JPanel();
    isCompactStatusbarCheckbox = new JCheckBox();
    isCompactTablesCheckbox = new JCheckBox();
    compactDropdownsCheckbox = new JCheckBox();
    JPanel componentOptions = new JPanel();
    featuresPanel = new JPanel();
    materialThemeCheckbox = new JCheckBox();
    isMaterialIconsCheckbox = new JCheckBox();
    useMaterialFontCheckbox = new JCheckBox();
    isMaterialDesignCheckbox = new JCheckBox();
    componentsPanel = new JPanel();
    upperCaseButtonsCheckbox = new JCheckBox();
    accentScrollbarsCheckbox = new JCheckBox();
    themedScrollbarsCheckbox = new JCheckBox();
    otherTweaksPanel = new JPanel();
    darkTitleBarCheckbox = new JCheckBox();
    isProjectViewDecoratorsCheckbox = new JCheckBox();
    isThemeInStatusCheckbox = new JCheckBox();
    resetDefaultsButton = new JButton();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);
      content.setPreferredSize(new Dimension(399, 778));
      content.setLayout(new MigLayout(
          "insets 0,hidemode 3,gap 0 0",
          // columns
          "[grow,fill]",
          // rows
          "[fill]" +
              "[fill]" +
              "[grow,fill]" +
              "[]"));

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
                "[]" +
                "[grow]"));
        mainSettingsPanel.add(settingsSep, "cell 0 0");

        //---- selectedThemeLabel ----
        selectedThemeLabel.setText(bundle.getString("MTForm.selectedThemeLabel.text"));
        selectedThemeLabel.setIcon(new ImageIcon(getClass().getResource("/icons/actions/material-theme.png")));
        selectedThemeLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        selectedThemeLabel.setIconTextGap(8);
        mainSettingsPanel.add(selectedThemeLabel, "pad 0 2 0 0,cell 0 1,growx");
        mainSettingsPanel.add(themeComboBox, "cell 1 1");

        //---- isContrastModeCheckbox ----
        isContrastModeCheckbox.setLabel(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setText(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setToolTipText(bundle.getString("mt.contrast.tooltip"));
        mainSettingsPanel.add(isContrastModeCheckbox, "cell 0 2,align leading center,grow 0 0");

        //---- customAccentColorLabel ----
        customAccentColorLabel.setText(bundle.getString("MTForm.customAccentColorLabel.text"));
        customAccentColorLabel.setToolTipText(bundle.getString("MTForm.customAccentColorLabel.toolTipText"));
        customAccentColorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        customAccentColorLabel.setIcon(new ImageIcon(getClass().getResource("/icons/actions/accents/customAccent.png")));
        customAccentColorLabel.setIconTextGap(8);
        mainSettingsPanel.add(customAccentColorLabel, "pad 0 2 0 0,cell 0 3,growx");

        //---- customAccentColorChooser ----
        customAccentColorChooser.setMinimumSize(new Dimension(10, 18));
        customAccentColorChooser.setPreferredSize(new Dimension(61, 26));
        mainSettingsPanel.add(customAccentColorChooser, "cell 1 3,alignx right,growx 0");
      }
      content.add(mainSettingsPanel, "cell 0 0");
      content.add(tabSep, "cell 0 1,aligny center,growy 0,gapx 16,gapy 10 10");

      //======== tabbedPane1 ========
      {
        tabbedPane1.setMinimumSize(new Dimension(400, 330));
        tabbedPane1.setPreferredSize(null);

        //======== tabOptions ========
        {
          tabOptions.setBorder(null);
          tabOptions.setPreferredSize(null);
          tabOptions.setMinimumSize(null);
          tabOptions.setLayout(new MigLayout(
              "insets 4 4 0 0,hidemode 3,gap 10 5",
              // columns
              "[grow1, fill]",
              // rows
              "[]"));

          //======== tabPanel ========
          {
            tabPanel.setLayout(new MigLayout(
                "fill,hidemode 3,align left top",
                // columns
                "[fill]" +
                    "[grow1, fill]",
                // rows
                "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

            //---- activeTabHighlightCheckbox ----
            activeTabHighlightCheckbox.setText(bundle.getString("MTForm.activeTabHighlightCheckbox.text"));
            activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
            tabPanel.add(activeTabHighlightCheckbox, "cell 0 0,align left center,grow 0 0");
            tabPanel.add(activeTabHighlightColor, "cell 1 0,align right center,grow 0 0");

            //---- thicknessLabel ----
            thicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
            thicknessLabel.setLabelFor(highlightSpinner);
            thicknessLabel.setText(bundle.getString("mt.border.thickness"));
            thicknessLabel.setToolTipText(bundle.getString("mt.border.thickness.tooltip"));
            tabPanel.add(thicknessLabel, "pad 0,cell 0 1,aligny center,grow 100 0");
            tabPanel.add(highlightSpinner, "cell 1 1,align right center,grow 0 0,width 80:80:80");

            //---- isUpperCaseTabsCheckbox ----
            isUpperCaseTabsCheckbox.setText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.text"));
            isUpperCaseTabsCheckbox.setToolTipText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.toolTipText"));
            tabPanel.add(isUpperCaseTabsCheckbox, "cell 0 2,align left center,grow 0 0");

            //---- tabHeight ----
            tabHeight.setHorizontalTextPosition(SwingConstants.LEADING);
            tabHeight.setLabelFor(highlightSpinner);
            tabHeight.setText(bundle.getString("MTForm.tabHeight"));
            tabHeight.setToolTipText(bundle.getString("MTForm.tabHeight.toolTipText"));
            tabPanel.add(tabHeight, "pad 0,cell 0 3,aligny center,grow 100 0");
            tabPanel.add(tabHeightSpinner, "cell 1 3,align right center,grow 0 0,width 80:80:80");

            //---- opacityLabel ----
            opacityLabel.setText(bundle.getString("MTForm.opacityLabel.text"));
            opacityLabel.setToolTipText(bundle.getString("MTForm.opacityLabel.toolTipText"));
            tabPanel.add(opacityLabel, "cell 0 4,aligny center,growy 0");
            tabPanel.add(tabOpacitySlider, "cell 1 4");
          }
          tabOptions.add(tabPanel, "cell 0 0");
        }
        tabbedPane1.addTab(bundle.getString("mt.activetab"), tabOptions);

        //======== panelOptions ========
        {
          panelOptions.setBorder(null);
          panelOptions.setMinimumSize(null);
          panelOptions.setPreferredSize(null);
          panelOptions.setLayout(new MigLayout(
              "insets 4 4 0 0,hidemode 3,gap 10 5",
              // columns
              "[grow, fill]",
              // rows
              "[fill]" +
                  "[fill]" +
                  "[fill]"));

          //======== iconsPanel ========
          {
            iconsPanel.setBorder(new TitledBorder(bundle.getString("MTForm.iconsPanel.border")));
            iconsPanel.setLayout(new MigLayout(
                "fillx,align left center",
                // columns
                "[fill]" +
                    "[fill]",
                // rows
                "[]" +
                    "[]"));

            //---- monochromeCheckbox ----
            monochromeCheckbox.setText(bundle.getString("MTForm.monochromeCheckbox.text"));
            monochromeCheckbox.setToolTipText(bundle.getString("MTForm.monochromeCheckbox.toolTipText"));
            iconsPanel.add(monochromeCheckbox, "cell 0 0");

            //---- hideFileIconsCheckbox ----
            hideFileIconsCheckbox.setText(bundle.getString("MTForm.hideFileIcons"));
            hideFileIconsCheckbox.setToolTipText(bundle.getString("MTForm.hideFileIcons.tooltip"));
            iconsPanel.add(hideFileIconsCheckbox, "cell 0 1,align left center,grow 0 0");
          }
          panelOptions.add(iconsPanel, "cell 0 0");

          //======== projectViewPanel ========
          {
            projectViewPanel.setBorder(new TitledBorder(bundle.getString("MTForm.projectViewPanel.border")));
            projectViewPanel.setLayout(new MigLayout(
                "fillx,hidemode 3,align left center",
                // columns
                "[fill]" +
                    "[fill]",
                // rows
                "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

            //---- isCompactSidebarCheckbox ----
            isCompactSidebarCheckbox.setText(bundle.getString("MTForm.isCompactSidebarCheckbox.text"));
            isCompactSidebarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactSidebarCheckbox.toolTipText"));
            isCompactSidebarCheckbox.addActionListener(e -> isCompactSidebarCheckboxActionPerformed(e));
            projectViewPanel.add(isCompactSidebarCheckbox, "cell 0 0,align left center,grow 0 0");

            //---- customSidebarSpinner ----
            customSidebarSpinner.setToolTipText(bundle.getString("MTForm.customSidebarSpinner.toolTipText"));
            projectViewPanel.add(customSidebarSpinner, "cell 1 0,align right center,grow 0 0,width 80:80:80");

            //---- customTreeIndentCheckbox ----
            customTreeIndentCheckbox.setText(bundle.getString("MTForm.customTreeIndentCheckbox.text"));
            customTreeIndentCheckbox.setToolTipText(bundle.getString("MTForm.customTreeIndentCheckbox.toolTipText"));
            customTreeIndentCheckbox.addActionListener(e -> customTreeIndentCheckboxActionPerformed(e));
            projectViewPanel.add(customTreeIndentCheckbox, "cell 0 1,align left center,grow 0 0");

            //---- customIndentSpinner ----
            customIndentSpinner.setToolTipText(bundle.getString("MTForm.customIndentSpinner.toolTipText"));
            projectViewPanel.add(customIndentSpinner, "cell 1 1,align right center,grow 0 0,width 80:80:80");

            //---- decoratedFoldersCheckbox ----
            decoratedFoldersCheckbox.setText(bundle.getString("MTForm.decoratedFoldersCheckbox.text"));
            decoratedFoldersCheckbox.setToolTipText(bundle.getString("MTForm.decoratedFoldersCheckbox.toolTipText"));
            projectViewPanel.add(decoratedFoldersCheckbox, "cell 0 4");

            //---- arrowsStyleLabel ----
            arrowsStyleLabel.setText(bundle.getString("MTForm.arrowsStyleLabel.text"));
            arrowsStyleLabel.setToolTipText(bundle.getString("MTForm.arrowsStyleLabel.toolTipText"));
            projectViewPanel.add(arrowsStyleLabel, "pad 0,cell 0 2,aligny center,grow 100 0");

            //---- arrowsStyleComboBox ----
            arrowsStyleComboBox.setToolTipText(bundle.getString("MTForm.arrowsStyleLabel.toolTipText"));
            projectViewPanel.add(arrowsStyleComboBox, "cell 1 2,align right center,grow 0 0,width 120:120:120");

            //---- boldTabs ----
            boldTabs.setLabel(bundle.getString("mt.boldtabs"));
            boldTabs.setText(bundle.getString("mt.boldtabs"));
            boldTabs.setToolTipText(bundle.getString("mt.boldtabs.tooltip"));
            projectViewPanel.add(boldTabs, "cell 0 3,align left center,grow 0 0");
          }
          panelOptions.add(projectViewPanel, "cell 0 1");

          //======== compactPanel ========
          {
            compactPanel.setBorder(new TitledBorder("Compact Panels"));
            compactPanel.setLayout(new MigLayout(
                "fillx,hidemode 3,align left center",
                // columns
                "[fill]" +
                    "[fill]",
                // rows
                "[]" +
                    "[]" +
                    "[]"));

            //---- isCompactStatusbarCheckbox ----
            isCompactStatusbarCheckbox.setText(bundle.getString("MTForm.isCompactStatusbarCheckbox.text"));
            isCompactStatusbarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactStatusBar.tooltip"));
            compactPanel.add(isCompactStatusbarCheckbox, "cell 0 0,align left center,grow 0 0");

            //---- isCompactTablesCheckbox ----
            isCompactTablesCheckbox.setText(bundle.getString("MTForm.isCompactTablesCheckbox.text"));
            isCompactTablesCheckbox.setToolTipText(bundle.getString("MTForm.isCompactTables.tooltip"));
            compactPanel.add(isCompactTablesCheckbox, "cell 0 1,align left center,grow 0 0");

            //---- compactDropdownsCheckbox ----
            compactDropdownsCheckbox.setText(bundle.getString("MTForm.compactDropdownsCheckbox.text"));
            compactDropdownsCheckbox.setToolTipText(bundle.getString("MTForm.compactDropdownsCheckbox.toolTipText"));
            compactPanel.add(compactDropdownsCheckbox, "cell 0 2");
          }
          panelOptions.add(compactPanel, "cell 0 2");
        }
        tabbedPane1.addTab(bundle.getString("mt.panels.section"), panelOptions);

        //======== componentOptions ========
        {
          componentOptions.setBorder(new EmptyBorder(5, 5, 5, 5));
          componentOptions.setPreferredSize(null);
          componentOptions.setMinimumSize(null);
          componentOptions.setLayout(new MigLayout(
              "insets 4 4 0 0,hidemode 3,gap 10 5",
              // columns
              "[grow 1,fill]",
              // rows
              "[fill]" +
                  "[fill]" +
                  "[fill]"));

          //======== featuresPanel ========
          {
            featuresPanel.setBorder(new TitledBorder(bundle.getString("MTForm.featuresPanel.border")));
            featuresPanel.setLayout(new MigLayout(
                "fillx,hidemode 3,align left center",
                // columns
                "[fill]" +
                    "[fill]",
                // rows
                "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

            //---- materialThemeCheckbox ----
            materialThemeCheckbox.setText(bundle.getString("MTForm.materialThemeCheckbox.text"));
            materialThemeCheckbox.setToolTipText(bundle.getString("MTForm.materialThemeCheckbox.toolTipText"));
            featuresPanel.add(materialThemeCheckbox, "cell 0 2 2 1,align left center,grow 0 0");

            //---- isMaterialIconsCheckbox ----
            isMaterialIconsCheckbox.setText(bundle.getString("MTForm.isMaterialIconsCheckbox.text"));
            isMaterialIconsCheckbox.setToolTipText(bundle.getString("MTForm.materialIcons.tooltip"));
            isMaterialIconsCheckbox.addActionListener(e -> isMaterialIconsCheckboxActionPerformed(e));
            featuresPanel.add(isMaterialIconsCheckbox, "cell 0 0 2 1,align left center,grow 0 0");

            //---- useMaterialFontCheckbox ----
            useMaterialFontCheckbox.setText(bundle.getString("MTForm.useMaterialFontCheckbox.text"));
            useMaterialFontCheckbox.setToolTipText(bundle.getString("MTForm.useMaterialFontCheckbox.tooltipText"));
            featuresPanel.add(useMaterialFontCheckbox, "cell 0 1 2 1,align left center,grow 0 0");

            //---- isMaterialDesignCheckbox ----
            isMaterialDesignCheckbox.setLabel(bundle.getString("MTForm.isMaterialDesignCheckbox.label"));
            isMaterialDesignCheckbox.setText(bundle.getString("MTForm.isMaterialDesignCheckbox.text"));
            isMaterialDesignCheckbox.setToolTipText(bundle.getString("MTForm.isMaterialDesignCheckbox.toolTipText"));
            featuresPanel.add(isMaterialDesignCheckbox, "cell 0 3 2 1,align left center,grow 0 0");
          }
          componentOptions.add(featuresPanel, "cell 0 0");

          //======== componentsPanel ========
          {
            componentsPanel.setBorder(new TitledBorder(bundle.getString("MTForm.componentsPanel.border")));
            componentsPanel.setLayout(new MigLayout(
                "fillx,hidemode 3,align left center",
                // columns
                "[fill]" +
                    "[fill]",
                // rows
                "[]" +
                    "[]" +
                    "[]"));

            //---- upperCaseButtonsCheckbox ----
            upperCaseButtonsCheckbox.setText(bundle.getString("MTForm.upperCaseButtonsCheckbox.text"));
            upperCaseButtonsCheckbox.setToolTipText(bundle.getString("MTForm.upperCaseButtonsCheckbox.toolTipText"));
            componentsPanel.add(upperCaseButtonsCheckbox, "cell 0 0 2 1");

            //---- accentScrollbarsCheckbox ----
            accentScrollbarsCheckbox.setText(bundle.getString("MTForm.accentScrollbarsCheckbox.text"));
            accentScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.accentScrollbarsCheckbox.toolTipText"));
            componentsPanel.add(accentScrollbarsCheckbox, "cell 0 1 2 1,align left center,grow 0 0");

            //---- themedScrollbarsCheckbox ----
            themedScrollbarsCheckbox.setText(bundle.getString("MTForm.themedScrollbarsCheckbox.text"));
            themedScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.themedScrollbarsCheckbox.toolTipText"));
            componentsPanel.add(themedScrollbarsCheckbox, "cell 0 2");
          }
          componentOptions.add(componentsPanel, "cell 0 1");

          //======== otherTweaksPanel ========
          {
            otherTweaksPanel.setBorder(new TitledBorder(bundle.getString("MTForm.otherTweaksPanel.border")));
            otherTweaksPanel.setLayout(new MigLayout(
                "fillx,hidemode 3,align left center",
                // columns
                "[fill]" +
                    "[fill]",
                // rows
                "[]" +
                    "[]" +
                    "[]"));

            //---- darkTitleBarCheckbox ----
            darkTitleBarCheckbox.setText(bundle.getString("MTForm.darkTitleBarCheckbox.text"));
            darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.darkTitleBarCheckbox.toolTipText"));
            darkTitleBarCheckbox.addActionListener(e -> isDarkTitleBarActionPerformed(e));
            otherTweaksPanel.add(darkTitleBarCheckbox, "cell 0 0 2 1,align left center,grow 0 0");

            //---- isProjectViewDecoratorsCheckbox ----
            isProjectViewDecoratorsCheckbox.setText(bundle.getString("MTForm.projectViewDecorators"));
            isProjectViewDecoratorsCheckbox.setToolTipText(bundle.getString("MTForm.projectViewDecorators.tooltip"));
            isProjectViewDecoratorsCheckbox.addActionListener(e -> isProjectViewDecoratorsCheckboxActionPerformed(e));
            otherTweaksPanel.add(isProjectViewDecoratorsCheckbox, "cell 0 1 2 1,align left center,grow 0 0");

            //---- isThemeInStatusCheckbox ----
            isThemeInStatusCheckbox.setText(bundle.getString("MTForm.themeStatus"));
            isThemeInStatusCheckbox.setToolTipText(bundle.getString("MTForm.themeStatus.tooltip"));
            otherTweaksPanel.add(isThemeInStatusCheckbox, "cell 0 2 2 1,align left center,grow 0 0");
          }
          componentOptions.add(otherTweaksPanel, "cell 0 2");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.componentOptions.border"), componentOptions);
      }
      content.add(tabbedPane1, "cell 0 2");

      //---- resetDefaultsButton ----
      resetDefaultsButton.setText(bundle.getString("mt.resetdefaults"));
      resetDefaultsButton.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
      content.add(resetDefaultsButton, "cell 0 3,align left center,grow 0 0");
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

    arrowsStyleComboBox.setModel(new DefaultComboBoxModel<>(ArrowsStyles.values()));
  }

  private int valueInRange(final int value, final int min, final int max) {
    return Integer.min(max, Integer.max(value, min));
  }
}
