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
 * Created by JFormDesigner on Tue Jul 24 22:51:19 IDT 2018
 */

package com.mallowigi.idea.wizard.steps;

import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBScrollPane;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTLicenseChecker;
import com.mallowigi.idea.config.enums.IndicatorStyles;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.mallowigi.idea.messages.MTWizardBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import static com.mallowigi.idea.utils.MTUiUtils.disablePremium;

/**
 * @author Elior Boukhobza
 */
@SuppressWarnings({"FieldCanBeLocal",
  "ClassWithTooManyFields",
  "CheckStyle",
  "Duplicates",
  "OverlyLongLambda",
  "unused",
  "DuplicateStringLiteralInspection",
  "HardCodedStringLiteral",
  "FeatureEnvy",
  "MagicNumber"})
public final class MTWizardOtherOptionsPanel extends AbstractCustomizeWizardStep {
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel tabsHeightSpinnerModel;
  private SpinnerModel customSidebarHeightModel;
  private SpinnerModel projectViewSpinnerModel;
  private final MTConfig config;

  public MTWizardOtherOptionsPanel() {
    config = MTConfig.getInstance();

    initComponents();
    initSpinners();
    initCheckboxes();
    initComboboxes();
    setupComponents();
  }

  private void setupComponents() {
    final boolean isFreeLicense = !MTLicenseChecker.isLicensed();
    if (isFreeLicense) {
      disablePremium(highlightSpinner);
      disablePremium(indicatorStyleComboBox);
      disablePremium(languageAdditionsCheckbox);
      disablePremium(languageAdditionsLabel);
      disablePremium(materialFontsCheckBox);
      disablePremium(materialFontsLabel);
      disablePremium(materialWallpapersCheckbox);
      disablePremium(materialWallpapersLabel);
      disablePremium(projectFrameCheckbox);
      disablePremium(projectFrameLabel);
      disablePremium(projectViewFontSizeDesc);
      disablePremium(projectViewFontSizeLabel);
      disablePremium(projectViewFontSizeSpinner);
      disablePremium(selectedIndicatorLabel);
      disablePremium(tabHighlightCombobox);
      disablePremium(tabHighlightDesc);
      disablePremium(tabHighlightLabel);
      disablePremium(thicknessDesc);
      disablePremium(thicknessLabel);
      disablePremium(uppercaseTabsCheckbox);
      disablePremium(uppercaseTabsDesc);
    }
  }

  private void initCheckboxes() {
    // compact stat
    compactStatusCheckbox.setSelected(config.isCompactStatusBar());

    // compact drop
    compactDropdownCheckbox.setSelected(config.isCompactDropdowns());

    // compact table
    compactTableCheckbox.setSelected(config.isCompactTables());

    // file colors
    fileColorsCheckbox.setSelected(config.isFileStatusColorsEnabled());

    // indicator
    indicatorStyleComboBox.setSelectedItem(config.getIndicatorStyle());

    // tab highlight pos
    tabHighlightCombobox.setSelectedItem(config.getTabHighlightPosition());

    // Wallpapers
    materialWallpapersCheckbox.setSelected(config.isUseMaterialWallpapers());

    // Project Frame
    projectFrameCheckbox.setSelected(config.isUseProjectFrame());

    // material fonts
    materialFontsCheckBox.setSelected(config.isUseMaterialFont2());

    // show overlays
    overlayCheckbox.setSelected(config.isShowOverlays());

    // language additions
    languageAdditionsCheckbox.setSelected(config.isCodeAdditionsEnabled());
  }

  @SuppressWarnings({"Duplicates",
    "FeatureEnvy"})
  private void initSpinners() {
    final int highlightThickness = MTUiUtils.valueInRange(config.getHighlightThickness(),
      MTConfig.MIN_HIGHLIGHT_THICKNESS,
      MTConfig.MAX_HIGHLIGHT_THICKNESS);
    final int tabsHeight = MTUiUtils.valueInRange(config.getTabsHeight(),
      MTConfig.MIN_TABS_HEIGHT,
      MTConfig.MAX_TABS_HEIGHT);
    final int customSidebarHeight = MTUiUtils.valueInRange(config.getCustomSidebarHeight(),
      MTConfig.MIN_SIDEBAR_HEIGHT,
      MTConfig.MAX_SIDEBAR_HEIGHT);
    final int projectViewFontSize = MTUiUtils.valueInRange(config.getTreeFontSize(),
      MTConfig.MIN_FONT_SIZE,
      MTConfig.MAX_FONT_SIZE);

    highlightSpinnerModel = new SpinnerNumberModel(highlightThickness,
      MTConfig.MIN_HIGHLIGHT_THICKNESS,
      MTConfig.MAX_HIGHLIGHT_THICKNESS,
      1);
    highlightSpinner.setModel(highlightSpinnerModel);
    tabsHeightSpinnerModel = new SpinnerNumberModel(tabsHeight,
      MTConfig.MIN_TABS_HEIGHT,
      MTConfig.MAX_TABS_HEIGHT,
      1);
    tabHeightSpinner.setModel(tabsHeightSpinnerModel);
    projectViewSpinnerModel = new SpinnerNumberModel(projectViewFontSize,
      MTConfig.MIN_TABS_HEIGHT,
      MTConfig.MAX_TABS_HEIGHT,
      1);
    projectViewFontSizeSpinner.setModel(projectViewSpinnerModel);
    customSidebarHeightModel = new SpinnerNumberModel(customSidebarHeight,
      MTConfig.MIN_SIDEBAR_HEIGHT,
      MTConfig.MAX_SIDEBAR_HEIGHT,
      2);
    sidebarHeightSpinner.setModel(customSidebarHeightModel);
  }

  @Override
  public String getTitle() {
    return MTWizardBundle.message("other.options.panel.title");
  }

  @Override
  public String getHTMLHeader() {
    return MTWizardBundle.message("other.options.panel.body");
  }

  @NotNull
  @Override
  public String getHTMLFooter() {
    return MTWizardBundle.message("other.options.panel.footer");
  }

  private void tabHeightSpinnerStateChanged(final ChangeEvent e) {
    config.setTabsHeight((Integer) tabHeightSpinner.getModel().getValue());
  }

  private void highlightSpinnerStateChanged(final ChangeEvent e) {
    config.setHighlightThickness((Integer) highlightSpinner.getModel().getValue());
  }

  private void compactStatusCheckboxActionPerformed(final ActionEvent e) {
    config.setCompactStatusBar(compactStatusCheckbox.isSelected());
  }

  private void uppercaseTabsCheckboxActionPerformed(final ActionEvent e) {
    config.setUpperCaseButtons(uppercaseTabsCheckbox.isSelected());
  }

  private void compactTableCheckboxActionPerformed(final ActionEvent e) {
    config.setCompactTables(compactTableCheckbox.isSelected());
  }

  private void compactDropdownCheckboxActionPerformed(final ActionEvent e) {
    config.setCompactDropdowns(compactDropdownCheckbox.isSelected());
  }

  private void sidebarHeightSpinnerStateChanged(final ChangeEvent e) {
    config.setCompactSidebar(true);
    config.setCustomSidebarHeight((Integer) sidebarHeightSpinner.getModel().getValue());
  }

  private void indicatorStyleComboBoxActionPerformed(final ActionEvent e) {
    config.setIndicatorStyle((IndicatorStyles) indicatorStyleComboBox.getSelectedItem());
  }

  private void mtWallpapersCheckboxActionPerformed(final ActionEvent e) {
    config.setUseMaterialWallpapers(materialWallpapersCheckbox.isSelected());
  }

  private void compactMenusCheckboxActionPerformed(final ActionEvent e) {
    config.setCompactMenus(compactMenusCheckbox.isSelected());
  }

  private void fileColorsCheckboxActionPerformed(final ActionEvent e) {
    config.setFileStatusColorsEnabled(fileColorsCheckbox.isSelected());
  }

  private void materialFontsChanged(final ActionEvent e) {
    config.setUseMaterialFont2(materialFontsCheckBox.isSelected());
  }

  private void projectFrameCheckboxActionPerformed(final ActionEvent e) {
    config.setUseProjectFrame(projectFrameCheckbox.isSelected());
    config.fireChanged();
  }

  private void overlayCheckboxActionPerformed(final ActionEvent e) {
    config.setShowOverlays(overlayCheckbox.isSelected());
  }

  private void tabHighlightComboboxActionPerformed(final ActionEvent e) {
    config.setTabHighlightPosition((TabHighlightPositions) tabHighlightCombobox.getSelectedItem());
    config.fireChanged();
  }

  private void projectViewFontSizeSpinnerStateChanged(final ChangeEvent e) {
    config.setTreeFontSizeEnabled(true);
    config.setTreeFontSize((Integer) projectViewFontSizeSpinner.getModel().getValue());
  }

  @SuppressWarnings({"OverlyLongMethod",
    "StringConcatenation",
    "Convert2MethodRef"})
  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MTWizardBundle");
    scrollPane = new JBScrollPane();
    content = new JPanel();
    tabsPanel = new JPanel();
    final JLabel tabHeight = new JLabel();
    tabHeightSpinner = new JSpinner();
    tabHeightDesc = new JTextPane();
    thicknessLabel = new JLabel();
    highlightSpinner = new JSpinner();
    thicknessDesc = new JTextPane();
    uppercaseTabsCheckbox = new JCheckBox();
    uppercaseTabsDesc = new JTextPane();
    tabHighlightLabel = new JLabel();
    tabHighlightCombobox = new ComboBox<>();
    tabHighlightDesc = new JTextPane();
    featuresPanel = new JPanel();
    fileColorsCheckbox = new JCheckBox();
    fileColorsDesc = new JTextPane();
    materialWallpapersCheckbox = new JCheckBox();
    materialWallpapersLabel = new JTextPane();
    materialFontsCheckBox = new JCheckBox();
    materialFontsLabel = new JTextPane();
    overlayCheckbox = new JCheckBox();
    overlayLabel = new JTextPane();
    panelPanel = new JPanel();
    compactStatusCheckbox = new JCheckBox();
    compactStatusDesc = new JTextPane();
    compactTableCheckbox = new JCheckBox();
    compactTableDesc = new JTextPane();
    compactDropdownCheckbox = new JCheckBox();
    compactStatusDesc2 = new JTextPane();
    compactMenusCheckbox = new JCheckBox();
    compactMenusDesc3 = new JTextPane();
    otherPanel = new JPanel();
    languageAdditionsCheckbox = new JCheckBox();
    languageAdditionsLabel = new JTextPane();
    projectFrameCheckbox = new JCheckBox();
    projectFrameLabel = new JTextPane();
    projectPanel = new JPanel();
    final JLabel sidebarHeight = new JLabel();
    sidebarHeightSpinner = new JSpinner();
    sidebarHeightDesc = new JTextPane();
    selectedIndicatorLabel = new JLabel();
    indicatorStyleComboBox = new ComboBox<>();
    indicatorStyleDesc = new JTextPane();
    projectViewFontSizeLabel = new JLabel();
    projectViewFontSizeSpinner = new JSpinner();
    projectViewFontSizeDesc = new JTextPane();
    label1 = new JLabel();

    //======== this ========
    setLayout(new BorderLayout());

    //======== scrollPane ========
    {
      scrollPane.setBorder(null);

      //======== content ========
      {
        content.setBorder(null);
        content.setLayout(new MigLayout(
          "fillx,novisualpadding,hidemode 3,align left top",
          // columns
          "[325,grow,fill]" +
            "[grow,fill]",
          // rows
          "[]" +
            "[]" +
            "[]"));

        //======== tabsPanel ========
        {
          tabsPanel.setBorder(new TitledBorder(bundle.getString("MTWizardOtherOptionsPanel.tabsPanel.border")));
          tabsPanel.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,left]",
            // rows
            "[20,fill]0" +
              "[]" +
              "[]0" +
              "[]" +
              "[]0" +
              "[]" +
              "[]0" +
              "[]0"));

          //---- tabHeight ----
          tabHeight.setHorizontalTextPosition(SwingConstants.LEADING);
          tabHeight.setText(bundle.getString("MTWizardOtherOptionsPanel.tabHeight.text"));
          tabHeight.setToolTipText(bundle.getString("MTWizardOtherOptionsPanel.tabHeight.toolTipText"));
          tabsPanel.add(tabHeight, "pad 0 4 0 0,cell 0 0,aligny center,grow 100 0");

          //---- tabHeightSpinner ----
          tabHeightSpinner.addChangeListener(e -> tabHeightSpinnerStateChanged(e));
          tabsPanel.add(tabHeightSpinner, "cell 0 0,wmax 60");

          //---- tabHeightDesc ----
          tabHeightDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.tabHeightDesc.text"));
          tabHeightDesc.setFont(UIManager.getFont("Label.font"));
          tabHeightDesc.setBackground(UIManager.getColor("Panel.background"));
          tabHeightDesc.setEnabled(false);
          tabsPanel.add(tabHeightDesc, "pad 0 10 0 10,cell 0 1");

          //---- thicknessLabel ----
          thicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
          thicknessLabel.setLabelFor(highlightSpinner);
          thicknessLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.thicknessLabel.text"));
          thicknessLabel.setToolTipText(bundle.getString("MTWizardOtherOptionsPanel.thicknessLabel.toolTipText"));
          tabsPanel.add(thicknessLabel, "pad 0 4 0 0,cell 0 2,aligny center,grow 100 0");

          //---- highlightSpinner ----
          highlightSpinner.addChangeListener(e -> highlightSpinnerStateChanged(e));
          tabsPanel.add(highlightSpinner, "cell 0 2,wmax 60");

          //---- thicknessDesc ----
          thicknessDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.thicknessDesc.text"));
          thicknessDesc.setFont(UIManager.getFont("Label.font"));
          thicknessDesc.setBackground(UIManager.getColor("Panel.background"));
          thicknessDesc.setEnabled(false);
          tabsPanel.add(thicknessDesc, "pad 0 10 0 10,cell 0 3");

          //---- uppercaseTabsCheckbox ----
          uppercaseTabsCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.uppercaseTabsCheckbox.text"));
          uppercaseTabsCheckbox.addActionListener(e -> uppercaseTabsCheckboxActionPerformed(e));
          tabsPanel.add(uppercaseTabsCheckbox, "cell 0 4");

          //---- uppercaseTabsDesc ----
          uppercaseTabsDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.uppercaseTabsDesc.text"));
          uppercaseTabsDesc.setFont(UIManager.getFont("Label.font"));
          uppercaseTabsDesc.setBackground(UIManager.getColor("Panel.background"));
          uppercaseTabsDesc.setEnabled(false);
          tabsPanel.add(uppercaseTabsDesc, "pad 0 10 0 10,cell 0 5");

          //---- tabHighlightLabel ----
          tabHighlightLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.tabHighlightLabel.text"));
          tabHighlightLabel.setToolTipText(bundle.getString("MTWizardOtherOptionsPanel.tabHighlightLabel.toolTipText"));
          tabsPanel.add(tabHighlightLabel, "pad 0 4 0 0,cell 0 6,growx");

          //---- tabHighlightCombobox ----
          tabHighlightCombobox.setToolTipText(bundle.getString("MTWizardOtherOptionsPanel.tabHighlightCombobox.toolTipText"));
          tabHighlightCombobox.addActionListener(e -> tabHighlightComboboxActionPerformed(e));
          tabsPanel.add(tabHighlightCombobox, "cell 0 6");

          //---- tabHighlightDesc ----
          tabHighlightDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.tabHighlightDesc.text"));
          tabHighlightDesc.setFont(UIManager.getFont("Label.font"));
          tabHighlightDesc.setBackground(UIManager.getColor("Panel.background"));
          tabHighlightDesc.setEnabled(false);
          tabsPanel.add(tabHighlightDesc, "pad 0 10 0 10,cell 0 7");
        }
        content.add(tabsPanel, "cell 0 0,aligny top,growy 0");

        //======== featuresPanel ========
        {
          featuresPanel.setBorder(new TitledBorder(bundle.getString("MTWizardOtherOptionsPanel.featuresPanel.border")));
          featuresPanel.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,left]",
            // rows
            "0[18,fill]0" +
              "[]" +
              "[]0" +
              "[]" +
              "[17]0" +
              "[]" +
              "[]0" +
              "[]0"));

          //---- fileColorsCheckbox ----
          fileColorsCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.fileColorsCheckbox.text"));
          fileColorsCheckbox.addActionListener(e -> fileColorsCheckboxActionPerformed(e));
          featuresPanel.add(fileColorsCheckbox, "cell 0 0");

          //---- fileColorsDesc ----
          fileColorsDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.fileColorsDesc.text"));
          fileColorsDesc.setFont(UIManager.getFont("Label.font"));
          fileColorsDesc.setBackground(UIManager.getColor("Panel.background"));
          fileColorsDesc.setEnabled(false);
          featuresPanel.add(fileColorsDesc, "pad 0 10 0 10,cell 0 1");

          //---- materialWallpapersCheckbox ----
          materialWallpapersCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.materialWallpapersCheckbox.text"));
          materialWallpapersCheckbox.addActionListener(e -> mtWallpapersCheckboxActionPerformed(e));
          featuresPanel.add(materialWallpapersCheckbox, "cell 0 2");

          //---- materialWallpapersLabel ----
          materialWallpapersLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.materialWallpapersLabel.text"));
          materialWallpapersLabel.setFont(UIManager.getFont("Label.font"));
          materialWallpapersLabel.setBackground(UIManager.getColor("Panel.background"));
          materialWallpapersLabel.setEnabled(false);
          featuresPanel.add(materialWallpapersLabel, "pad 0 10 0 10,cell 0 3");

          //---- materialFontsCheckBox ----
          materialFontsCheckBox.setText(bundle.getString("MTWizardOtherOptionsPanel.materialFontsCheckBox.text"));
          materialFontsCheckBox.addActionListener(e -> materialFontsChanged(e));
          featuresPanel.add(materialFontsCheckBox, "cell 0 4");

          //---- materialFontsLabel ----
          materialFontsLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.materialFontsLabel.text"));
          materialFontsLabel.setFont(UIManager.getFont("Label.font"));
          materialFontsLabel.setBackground(UIManager.getColor("Panel.background"));
          materialFontsLabel.setEnabled(false);
          featuresPanel.add(materialFontsLabel, "pad 0 10 0 10,cell 0 5");

          //---- overlayCheckbox ----
          overlayCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.overlayCheckbox.text"));
          overlayCheckbox.addActionListener(e -> overlayCheckboxActionPerformed(e));
          featuresPanel.add(overlayCheckbox, "cell 0 6");

          //---- overlayLabel ----
          overlayLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.overlayLabel.text"));
          overlayLabel.setFont(UIManager.getFont("Label.font"));
          overlayLabel.setBackground(UIManager.getColor("Panel.background"));
          overlayLabel.setEnabled(false);
          featuresPanel.add(overlayLabel, "pad 0 10 0 10,cell 0 7");
        }
        content.add(featuresPanel, "cell 1 0,aligny top,growy 0");

        //======== panelPanel ========
        {
          panelPanel.setBorder(new TitledBorder(bundle.getString("MTWizardOtherOptionsPanel.panelPanel.border")));
          panelPanel.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,left]",
            // rows
            "0[18,fill]0" +
              "[]" +
              "[]0" +
              "[]" +
              "[]0" +
              "[]" +
              "[]0" +
              "[]"));

          //---- compactStatusCheckbox ----
          compactStatusCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.compactStatusCheckbox.text"));
          compactStatusCheckbox.addActionListener(e -> compactStatusCheckboxActionPerformed(e));
          panelPanel.add(compactStatusCheckbox, "cell 0 0");

          //---- compactStatusDesc ----
          compactStatusDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.compactStatusDesc.text"));
          compactStatusDesc.setFont(UIManager.getFont("Label.font"));
          compactStatusDesc.setBackground(UIManager.getColor("Panel.background"));
          compactStatusDesc.setEnabled(false);
          panelPanel.add(compactStatusDesc, "pad 0 10 0 10,cell 0 1");

          //---- compactTableCheckbox ----
          compactTableCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.compactTableCheckbox.text"));
          compactTableCheckbox.addActionListener(e -> compactTableCheckboxActionPerformed(e));
          panelPanel.add(compactTableCheckbox, "cell 0 2");

          //---- compactTableDesc ----
          compactTableDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.compactTableDesc.text"));
          compactTableDesc.setFont(UIManager.getFont("Label.font"));
          compactTableDesc.setBackground(UIManager.getColor("Panel.background"));
          compactTableDesc.setEnabled(false);
          panelPanel.add(compactTableDesc, "pad 0 10 0 10,cell 0 3");

          //---- compactDropdownCheckbox ----
          compactDropdownCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.compactDropdownCheckbox.text"));
          compactDropdownCheckbox.addActionListener(e -> compactDropdownCheckboxActionPerformed(e));
          panelPanel.add(compactDropdownCheckbox, "cell 0 4");

          //---- compactStatusDesc2 ----
          compactStatusDesc2.setText(bundle.getString("MTWizardOtherOptionsPanel.compactStatusDesc2.text"));
          compactStatusDesc2.setFont(UIManager.getFont("Label.font"));
          compactStatusDesc2.setBackground(UIManager.getColor("Panel.background"));
          compactStatusDesc2.setEnabled(false);
          panelPanel.add(compactStatusDesc2, "pad 0 10 0 10,cell 0 5");

          //---- compactMenusCheckbox ----
          compactMenusCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.compactMenusCheckbox.text"));
          compactMenusCheckbox.addActionListener(e -> compactMenusCheckboxActionPerformed(e));
          panelPanel.add(compactMenusCheckbox, "cell 0 6");

          //---- compactMenusDesc3 ----
          compactMenusDesc3.setText(bundle.getString("MTWizardOtherOptionsPanel.compactMenusDesc3.text"));
          compactMenusDesc3.setFont(UIManager.getFont("Label.font"));
          compactMenusDesc3.setBackground(UIManager.getColor("Panel.background"));
          compactMenusDesc3.setEnabled(false);
          panelPanel.add(compactMenusDesc3, "pad 0 10 0 10,cell 0 7");
        }
        content.add(panelPanel, "cell 0 1,aligny top,growy 0");

        //======== otherPanel ========
        {
          otherPanel.setBorder(new TitledBorder("Other Settings"));
          otherPanel.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,left]",
            // rows
            "0[18,fill]0" +
              "[17]" +
              "[]0" +
              "[]"));

          //---- languageAdditionsCheckbox ----
          languageAdditionsCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.languageAdditionsCheckbox.text"));
          languageAdditionsCheckbox.addActionListener(e -> fileColorsCheckboxActionPerformed(e));
          otherPanel.add(languageAdditionsCheckbox, "cell 0 0");

          //---- languageAdditionsLabel ----
          languageAdditionsLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.languageAdditionsLabel.text"));
          languageAdditionsLabel.setFont(UIManager.getFont("Label.font"));
          languageAdditionsLabel.setBackground(UIManager.getColor("Panel.background"));
          languageAdditionsLabel.setEnabled(false);
          otherPanel.add(languageAdditionsLabel, "pad 0 10 0 10,cell 0 1");

          //---- projectFrameCheckbox ----
          projectFrameCheckbox.setText(bundle.getString("MTWizardOtherOptionsPanel.projectFrameCheckbox.text"));
          projectFrameCheckbox.addActionListener(e -> projectFrameCheckboxActionPerformed(e));
          otherPanel.add(projectFrameCheckbox, "cell 0 2");

          //---- projectFrameLabel ----
          projectFrameLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.projectFrameLabel.text"));
          projectFrameLabel.setFont(UIManager.getFont("Label.font"));
          projectFrameLabel.setBackground(UIManager.getColor("Panel.background"));
          projectFrameLabel.setEnabled(false);
          otherPanel.add(projectFrameLabel, "pad 0 10 0 10,cell 0 3");
        }
        content.add(otherPanel, "cell 1 1,aligny top,growy 0");

        //======== projectPanel ========
        {
          projectPanel.setBorder(new TitledBorder(bundle.getString("MTWizardOtherOptionsPanel.projectPanel.border")));
          projectPanel.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,left]",
            // rows
            "[20,fill]0" +
              "[]0" +
              "[]0" +
              "[]" +
              "[]0" +
              "[]"));

          //---- sidebarHeight ----
          sidebarHeight.setHorizontalTextPosition(SwingConstants.LEADING);
          sidebarHeight.setText(bundle.getString("MTWizardOtherOptionsPanel.sidebarHeight.text"));
          sidebarHeight.setToolTipText(bundle.getString("MTWizardOtherOptionsPanel.sidebarHeight.toolTipText"));
          projectPanel.add(sidebarHeight, "pad 0 4 0 0,cell 0 0,aligny center,grow 100 0");

          //---- sidebarHeightSpinner ----
          sidebarHeightSpinner.addChangeListener(e -> sidebarHeightSpinnerStateChanged(e));
          projectPanel.add(sidebarHeightSpinner, "cell 0 0,wmax 60");

          //---- sidebarHeightDesc ----
          sidebarHeightDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.sidebarHeightDesc.text"));
          sidebarHeightDesc.setFont(UIManager.getFont("Label.font"));
          sidebarHeightDesc.setBackground(UIManager.getColor("Panel.background"));
          sidebarHeightDesc.setEnabled(false);
          projectPanel.add(sidebarHeightDesc, "pad 0 10 0 10,cell 0 1");

          //---- selectedIndicatorLabel ----
          selectedIndicatorLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.selectedIndicatorLabel.text"));
          selectedIndicatorLabel.setToolTipText(bundle.getString("MTWizardOtherOptionsPanel.selectedIndicatorLabel.toolTipText"));
          projectPanel.add(selectedIndicatorLabel, "pad 0 4 0 0,cell 0 2,growx");

          //---- indicatorStyleComboBox ----
          indicatorStyleComboBox.setToolTipText(bundle.getString("MTWizardOtherOptionsPanel.indicatorStyleComboBox.toolTipText"));
          indicatorStyleComboBox.addActionListener(e -> indicatorStyleComboBoxActionPerformed(e));
          projectPanel.add(indicatorStyleComboBox, "cell 0 2");

          //---- indicatorStyleDesc ----
          indicatorStyleDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.indicatorStyleDesc.text"));
          indicatorStyleDesc.setFont(UIManager.getFont("Label.font"));
          indicatorStyleDesc.setBackground(UIManager.getColor("Panel.background"));
          indicatorStyleDesc.setEnabled(false);
          projectPanel.add(indicatorStyleDesc, "pad 0 10 0 10,cell 0 3");

          //---- projectViewFontSizeLabel ----
          projectViewFontSizeLabel.setHorizontalTextPosition(SwingConstants.LEADING);
          projectViewFontSizeLabel.setText(bundle.getString("MTWizardOtherOptionsPanel.projectViewFontSizeLabel.text"));
          projectPanel.add(projectViewFontSizeLabel, "pad 0 4 0 0,cell 0 4,aligny center,grow 100 0");

          //---- projectViewFontSizeSpinner ----
          projectViewFontSizeSpinner.addChangeListener(e -> projectViewFontSizeSpinnerStateChanged(e));
          projectPanel.add(projectViewFontSizeSpinner, "cell 0 4,wmax 60");

          //---- projectViewFontSizeDesc ----
          projectViewFontSizeDesc.setText(bundle.getString("MTWizardOtherOptionsPanel.projectViewFontSizeDesc.text"));
          projectViewFontSizeDesc.setFont(UIManager.getFont("Label.font"));
          projectViewFontSizeDesc.setBackground(UIManager.getColor("Panel.background"));
          projectViewFontSizeDesc.setEnabled(false);
          projectPanel.add(projectViewFontSizeDesc, "pad 0 10 0 10,cell 0 5");
        }
        content.add(projectPanel, "cell 0 2,aligny top,growy 0");

        //---- label1 ----
        label1.setText(bundle.getString("MTWizardOtherOptionsPanel.label1.text"));
        label1.setFont(new Font("Roboto", Font.PLAIN, 26));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(label1, "cell 1 2");
      }
      scrollPane.setViewportView(content);
    }
    add(scrollPane, BorderLayout.CENTER);
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

  }

  private void initComboboxes() {
    // Indicator
    indicatorStyleComboBox.setModel(new DefaultComboBoxModel<>(IndicatorStyles.values()));
    // Tab Highlight
    tabHighlightCombobox.setModel(new DefaultComboBoxModel<>(TabHighlightPositions.values()));
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JBScrollPane scrollPane;
  private JPanel content;
  private JPanel tabsPanel;
  private JSpinner tabHeightSpinner;
  private JTextPane tabHeightDesc;
  private JLabel thicknessLabel;
  private JSpinner highlightSpinner;
  private JTextPane thicknessDesc;
  private JCheckBox uppercaseTabsCheckbox;
  private JTextPane uppercaseTabsDesc;
  private JLabel tabHighlightLabel;
  private ComboBox<TabHighlightPositions> tabHighlightCombobox;
  private JTextPane tabHighlightDesc;
  private JPanel featuresPanel;
  private JCheckBox fileColorsCheckbox;
  private JTextPane fileColorsDesc;
  private JCheckBox materialWallpapersCheckbox;
  private JTextPane materialWallpapersLabel;
  private JCheckBox materialFontsCheckBox;
  private JTextPane materialFontsLabel;
  private JCheckBox overlayCheckbox;
  private JTextPane overlayLabel;
  private JPanel panelPanel;
  private JCheckBox compactStatusCheckbox;
  private JTextPane compactStatusDesc;
  private JCheckBox compactTableCheckbox;
  private JTextPane compactTableDesc;
  private JCheckBox compactDropdownCheckbox;
  private JTextPane compactStatusDesc2;
  private JCheckBox compactMenusCheckbox;
  private JTextPane compactMenusDesc3;
  private JPanel otherPanel;
  private JCheckBox languageAdditionsCheckbox;
  private JTextPane languageAdditionsLabel;
  private JCheckBox projectFrameCheckbox;
  private JTextPane projectFrameLabel;
  private JPanel projectPanel;
  private JSpinner sidebarHeightSpinner;
  private JTextPane sidebarHeightDesc;
  private JLabel selectedIndicatorLabel;
  private ComboBox<IndicatorStyles> indicatorStyleComboBox;
  private JTextPane indicatorStyleDesc;
  private JLabel projectViewFontSizeLabel;
  private JSpinner projectViewFontSizeSpinner;
  private JTextPane projectViewFontSizeDesc;
  private JLabel label1;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

}
