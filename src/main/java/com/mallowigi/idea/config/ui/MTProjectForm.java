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
 * Created by JFormDesigner on Sat May 22 18:31:17 IDT 2021
 */

package com.mallowigi.idea.config.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ColorPanel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTLicenseChecker;
import com.mallowigi.idea.MTProjectConfig;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import static com.mallowigi.idea.utils.MTUiUtils.disableEnable;
import static com.mallowigi.idea.utils.MTUiUtils.disablePremium;

@SuppressWarnings({"ClassWithTooManyFields",
  "ClassWithTooManyMethods",
  "InstanceVariableMayNotBeInitialized",
  "Duplicates",
  "FeatureEnvy",
  "StringConcatenation",
  "FieldCanBeLocal",
  "DuplicateStringLiteralInspection",
  "rawtypes",
  "Convert2MethodRef",
  "AnonymousInnerClassMayBeStatic",
  "unused",
  "PublicMethodNotExposedInInterface",
  "UndesirableClassUsage",
  "OverlyLongMethod"})
public class MTProjectForm implements MTFormUI {
  private final Project project;
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel indicatorThicknessSpinnerModel;

  public MTProjectForm(final Project project) {
    this.project = project;
  }

  private void disablePremiumFeatures() {
    final boolean isFreeLicense = !MTLicenseChecker.isLicensed();
    if (isFreeLicense) {
      disablePremium(isActiveCheckbox);
      disablePremium(activeTabHighlightCheckbox);
      disablePremium(activeTabHighlightColor);
      disablePremium(thicknessLabel);
      disablePremium(highlightSpinner);
      disablePremium(isUpperCaseTabsCheckbox);
      disablePremium(positionLabel);
      disablePremium(tabHighlightPositionComboBox);
      disablePremium(useProjectFrameCheckbox);
      disablePremium(projectFrameColor);
      disablePremium(showProjectTitleCheckbox);
      disablePremium(useCustomTextCheckbox);
      disablePremium(customTextField);
    }
  }

  @Override
  public final void init() {
    initComponents();
    setupComponents();
  }

  @Override
  public final JComponent getContent() {
    return content;
  }

  @SuppressWarnings({
    "OverlyLongMethod",
    "OverlyLongLambda",
    "HardCodedStringLiteral"
  })
  @Override
  public final void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    final DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
    content = new JPanel();
    settings = new JPanel();
    isActiveCheckbox = new JCheckBox();
    tabbedPane1 = new JTabbedPane();
    tabPanel = new JPanel();
    tabsDesc = compFactory.createLabel(bundle.getString("MTProjectForm.tabsDesc.text"));
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    thicknessLabel = new JLabel();
    highlightSpinner = new JSpinner();
    isUpperCaseTabsCheckbox = new JCheckBox();
    positionLabel = new JLabel();
    tabHighlightPositionComboBox = new ComboBox<>();
    projectFramePanel = new JPanel();
    projectFrameDesc = compFactory.createLabel(bundle.getString("MTProjectForm.projectFrameDesc.text"));
    useProjectFrameCheckbox = new JCheckBox();
    projectFrameColor = new ColorPanel();
    showProjectTitleCheckbox = new JCheckBox();
    useCustomTextCheckbox = new JCheckBox();
    customTextField = new JTextField();

    //======== content ========
    {
      content.setLayout(new MigLayout(
        "hidemode 3",
        // columns
        "[grow,fill]",
        // rows
        "[]"));

      //======== settings ========
      {
        settings.setBorder(new TitledBorder(bundle.getString("MTProjectForm.settings.border")));
        settings.setLayout(new MigLayout(
          "",
          // columns
          "ind[grow,fill]0",
          // rows
          "[]" +
            "[]"));

        //---- isActiveCheckbox ----
        isActiveCheckbox.setText(bundle.getString("MTProjectForm.isActiveCheckbox.text"));
        isActiveCheckbox.addActionListener(e -> isActiveCheckboxActionPerformed(e));
        settings.add(isActiveCheckbox, "cell 0 0");

        //======== tabbedPane1 ========
        {
          tabbedPane1.setMinimumSize(null);
          tabbedPane1.setPreferredSize(null);
          tabbedPane1.setBorder(null);

          //======== tabPanel ========
          {
            tabPanel.setLayout(new MigLayout(
              "fillx,hidemode 3,align left top",
              // columns
              "[left]" +
                "[right]",
              // rows
              "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- tabsDesc ----
            tabsDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
            tabsDesc.setText(bundle.getString("MTProjectForm.tabsDesc.text"));
            tabPanel.add(tabsDesc, "cell 0 0 2 1");

            //---- activeTabHighlightCheckbox ----
            activeTabHighlightCheckbox.setText(bundle.getString("MTProjectForm.activeTabHighlightCheckbox.text"));
            activeTabHighlightCheckbox.setToolTipText(bundle.getString("MTProjectForm.activeTabHighlightCheckbox.toolTipText"));
            activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
            tabPanel.add(activeTabHighlightCheckbox, "cell 0 1,align left center,grow 0 0");
            tabPanel.add(activeTabHighlightColor, "cell 1 1,align right center,grow 0 0");

            //---- thicknessLabel ----
            thicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
            thicknessLabel.setLabelFor(highlightSpinner);
            thicknessLabel.setText(bundle.getString("MTProjectForm.thicknessLabel.text"));
            thicknessLabel.setToolTipText(bundle.getString("MTProjectForm.thicknessLabel.toolTipText"));
            tabPanel.add(thicknessLabel, "pad 0,cell 0 2,aligny center,grow 100 0");

            //---- highlightSpinner ----
            highlightSpinner.setToolTipText(bundle.getString("MTProjectForm.highlightSpinner.toolTipText"));
            tabPanel.add(highlightSpinner, "cell 1 2,align right center,grow 0 0,width 80:80:80");

            //---- isUpperCaseTabsCheckbox ----
            isUpperCaseTabsCheckbox.setText(bundle.getString("MTProjectForm.isUpperCaseTabsCheckbox.text"));
            isUpperCaseTabsCheckbox.setToolTipText(bundle.getString("MTProjectForm.isUpperCaseTabsCheckbox.toolTipText"));
            tabPanel.add(isUpperCaseTabsCheckbox, "cell 0 3,align left center,grow 0 0");

            //---- positionLabel ----
            positionLabel.setText(bundle.getString("MTProjectForm.positionLabel.text"));
            positionLabel.setToolTipText(bundle.getString("MTProjectForm.positionLabel.toolTipText"));
            tabPanel.add(positionLabel, "cell 0 4,aligny center,growy 0");

            //---- tabHighlightPositionComboBox ----
            tabHighlightPositionComboBox.setToolTipText(bundle.getString("MTProjectForm.tabHighlightPositionComboBox.toolTipText"));
            tabPanel.add(tabHighlightPositionComboBox, "cell 1 4,align right center,grow 0 0,width 120:120:120");
          }
          tabbedPane1.addTab(bundle.getString("MTProjectForm.tabPanel.tab.title"), null, tabPanel, "Customize your tabs");

          //======== projectFramePanel ========
          {
            projectFramePanel.setBorder(null);
            projectFramePanel.setLayout(new MigLayout(
              "fillx,hidemode 3,align left top",
              // columns
              "[left]" +
                "[right]",
              // rows
              "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- projectFrameDesc ----
            projectFrameDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
            projectFrameDesc.setText(bundle.getString("MTProjectForm.projectFrameDesc.text"));
            projectFramePanel.add(projectFrameDesc, "cell 0 0 2 1");

            //---- useProjectFrameCheckbox ----
            useProjectFrameCheckbox.setText(bundle.getString("MTProjectForm.useProjectFrameCheckbox.text"));
            useProjectFrameCheckbox.setToolTipText(bundle.getString("MTProjectForm.useProjectFrameCheckbox.toolTipText"));
            useProjectFrameCheckbox.addActionListener(e -> useProjectFrameCheckboxActionPerformed(e));
            projectFramePanel.add(useProjectFrameCheckbox, "cell 0 1,align left center,grow 0 0");
            projectFramePanel.add(projectFrameColor, "cell 1 1");

            //---- showProjectTitleCheckbox ----
            showProjectTitleCheckbox.setText(bundle.getString("MTProjectForm.showProjectTitleCheckbox.text"));
            showProjectTitleCheckbox.setToolTipText(bundle.getString("MTProjectForm.showProjectTitleCheckbox.toolTipText"));
            projectFramePanel.add(showProjectTitleCheckbox, "cell 0 2,align left center,grow 0 0");

            //---- useCustomTextCheckbox ----
            useCustomTextCheckbox.setText(bundle.getString("MTProjectForm.useCustomTextCheckbox.text"));
            useCustomTextCheckbox.setToolTipText(bundle.getString("MTProjectForm.useCustomTextCheckbox.toolTipText"));
            useCustomTextCheckbox.addActionListener(e -> useCustomTextCheckboxActionPerformed(e));
            projectFramePanel.add(useCustomTextCheckbox, "cell 0 3,align left center,grow 0 0");
            projectFramePanel.add(customTextField, "cell 1 3,alignx right,growx 0,width 150:150:150");
          }
          tabbedPane1.addTab(bundle.getString("MTProjectForm.projectFramePanel.tab.title"), null, projectFramePanel, bundle.getString(
            "MTProjectForm.projectFramePanel.tab.toolTipText"));
        }
        settings.add(tabbedPane1, "pad 0,cell 0 1,growx,gapx 10 10,gapy 10 10");
      }
      content.add(settings, "cell 0 0");
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  @Override
  public final void setupComponents() {
    final MTProjectConfig config = MTProjectConfig.getInstance(project);
    final int highlightThickness = MTUiUtils.valueInRange(config.getHighlightThickness(), MTConfig.MIN_HIGHLIGHT_THICKNESS,
      MTConfig.MAX_HIGHLIGHT_THICKNESS);
    final int selectedTabIndex = MTUiUtils.valueInRange(config.getSettingsSelectedTab(), 0, MTConfig.MAX_TAB_INDEX);

    highlightSpinnerModel = new SpinnerNumberModel(highlightThickness, MTConfig.MIN_HIGHLIGHT_THICKNESS,
      MTConfig.MAX_HIGHLIGHT_THICKNESS, 1);
    highlightSpinner.setModel(highlightSpinnerModel);
    indicatorThicknessSpinnerModel = new SpinnerNumberModel(highlightThickness, MTConfig.MIN_INDICATOR_THICKNESS,
      MTConfig.MAX_INDICATOR_THICKNESS, 1);

    // Positions
    tabHighlightPositionComboBox.setModel(new DefaultComboBoxModel<>(TabHighlightPositions.values()));

    toggleOptions(isActiveCheckbox.isSelected());
    disablePremiumFeatures();
  }

  private void toggleOptions(final boolean state) {
    disableEnable(tabbedPane1, state);
    disableEnable(activeTabHighlightCheckbox, state);
    disableEnable(activeTabHighlightColor, state);
    disableEnable(thicknessLabel, state);
    disableEnable(highlightSpinner, state);
    disableEnable(isUpperCaseTabsCheckbox, state);
    disableEnable(positionLabel, state);
    disableEnable(tabHighlightPositionComboBox, state);
    disableEnable(useProjectFrameCheckbox, state);
    disableEnable(projectFrameColor, state);
    disableEnable(showProjectTitleCheckbox, state);
    disableEnable(useCustomTextCheckbox, state);
    disableEnable(customTextField, state);

    enableDisableActiveTabColor(activeTabHighlightCheckbox.isSelected());
    enableDisableProjectFrame(useProjectFrameCheckbox.isSelected());
  }

  private void afterStateSet() {
    toggleOptions(isActiveCheckbox.isSelected());
  }

  @Override
  public void dispose() {

  }

  public final void setFormState(final MTBaseConfig config) {
    final MTProjectConfig mtConfig = (MTProjectConfig) config;

    mtConfig.setPremium(true);
    setIsActive(mtConfig.isActive());
    setHighlightColor(mtConfig.getHighlightColor());
    setHighlightColorEnabled(mtConfig.isHighlightColorEnabled());
    setHighlightPosition(mtConfig.getTabHighlightPosition());
    setHighlightThickness(mtConfig.getHighlightThickness());
    setIsUpperCaseTabs(mtConfig.isUpperCaseTabs());
    setSelectedTabIndex(mtConfig.getSettingsSelectedTab());
    setUseProjectFrame(mtConfig.isUseProjectFrame());
    setProjectFrameColor(mtConfig.getProjectFrameColor());
    setUseProjectTitle(mtConfig.isUseProjectTitle());
    setUseCustomTitle(mtConfig.isUseCustomTitle());
    setCustomTitle(mtConfig.getCustomTitle());

    mtConfig.setPremium(MTLicenseChecker.isLicensed());

    afterStateSet();
  }

  public final boolean isModified(final MTBaseConfig config) {
    final MTProjectConfig mtConfig = (MTProjectConfig) config;

    boolean modified = mtConfig.isReset();
    modified = modified || mtConfig.isActiveChanged(isActive());
    modified = modified || mtConfig.isHighlightColorChanged(getHighlightColor());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(isHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(getHighlightThickness());
    modified = modified || mtConfig.isTabHighlightPositionChanged(getTabHighlightPosition());
    modified = modified || mtConfig.isUpperCaseTabsChanged(isUpperCaseTabs());
    modified = modified || mtConfig.isUseProjectFrameChanged(isUseProjectFrame());
    modified = modified || mtConfig.isProjectFrameColorChanged(getProjectFrameColor());
    modified = modified || mtConfig.isUseProjectTitleChanged(isUseProjectTitle());
    modified = modified || mtConfig.isUseCustomTitleChanged(isUseCustomTitle());
    modified = modified || mtConfig.isCustomTitleChanged(getCustomTitle());

    return modified;
  }

  //region ----------------- Is Active ---------------------
  public final boolean isActive() {
    return isActiveCheckbox.isSelected();
  }

  private void setIsActive(final boolean active) {
    isActiveCheckbox.setSelected(active);
  }
  //endregion

  // region ----------- Tab Settings -----------

  //region Highlight Color
  public final Color getHighlightColor() {
    return activeTabHighlightColor.getSelectedColor();
  }

  private void setHighlightColor(@NotNull final Color highlightColor) {
    activeTabHighlightColor.setSelectedColor(highlightColor);
  }
  //endregion

  //region Highlight color enabled
  public final boolean isHighlightColorEnabled() {
    return activeTabHighlightCheckbox.isSelected();
  }

  private void setHighlightColorEnabled(final boolean enabled) {
    activeTabHighlightCheckbox.setSelected(enabled);
    enableDisableActiveTabColor(enabled);
  }
  //endregion

  //region Thickness
  public final Integer getHighlightThickness() {
    return (Integer) highlightSpinnerModel.getValue();
  }

  private void setHighlightThickness(final Integer highlightThickness) {
    highlightSpinnerModel.setValue(highlightThickness);
  }
  //endregion

  //region Uppercase tabs
  public final boolean isUpperCaseTabs() {
    return isUpperCaseTabsCheckbox.isSelected();
  }

  private void setIsUpperCaseTabs(final boolean upperCaseTabs) {
    isUpperCaseTabsCheckbox.setSelected(upperCaseTabs);
  }
  //endregion

  //region Tab Highlight Position
  public final TabHighlightPositions getTabHighlightPosition() {
    return (TabHighlightPositions) tabHighlightPositionComboBox.getSelectedItem();
  }

  private void setHighlightPosition(final TabHighlightPositions position) {
    tabHighlightPositionComboBox.setSelectedItem(position);
  }
  //endregion

  //endregion

  // region ----------- Project Frame Settings -----------

  //region Use Project Frame
  public final boolean isUseProjectFrame() {
    return useProjectFrameCheckbox.isSelected();
  }

  private void setUseProjectFrame(final boolean useProjectFrame) {
    useProjectFrameCheckbox.setSelected(useProjectFrame);
  }
  // endregion

  //region Project Frame Color
  public final Color getProjectFrameColor() {
    return projectFrameColor.getSelectedColor();
  }

  private void setProjectFrameColor(@NotNull final Color projFrameColor) {
    projectFrameColor.setSelectedColor(projFrameColor);
  }
  //endregion

  //region Project Title
  public final boolean isUseProjectTitle() {
    return showProjectTitleCheckbox.isSelected();
  }

  private void setUseProjectTitle(final boolean enabled) {
    showProjectTitleCheckbox.setSelected(enabled);
  }

  //endregion

  //region Use Custom Title
  public final boolean isUseCustomTitle() {
    return useCustomTextCheckbox.isSelected();
  }

  private void setUseCustomTitle(final boolean enabled) {
    useCustomTextCheckbox.setSelected(enabled);
    enableDisableCustomTitle(enabled);
  }
  //endregion

  //region Custom Title
  public final String getCustomTitle() {
    return customTextField.getText();
  }

  private void setCustomTitle(final String text) {
    customTextField.setText(text);
  }
  //endregion

  // endregion

  //region ############### Selected tab #################
  public final Integer getSelectedTabIndex() {
    return tabbedPane1.getSelectedIndex();
  }

  private void setSelectedTabIndex(final Integer settingsSelectedTab) {
    tabbedPane1.setSelectedIndex(settingsSelectedTab);
  }
  //endregion

  //region ~~~~~~~~~~~~ Enabled listeners ~~~~~~~~~~~~~~~~~~

  private void enableDisableActiveTabColor(final boolean isActiveTabColor) {
    activeTabHighlightColor.setEnabled(isActiveTabColor);
  }

  private void enableDisableProjectFrame(final boolean isProjectFrameEnabled) {
    projectFrameColor.setEnabled(isProjectFrameEnabled);
  }

  private void enableDisableCustomTitle(final boolean isCustomTitleEnabled) {
    customTextField.setEnabled(isCustomTitleEnabled);
  }

  private void activeTabHighlightCheckboxActionPerformed(final ActionEvent e) {
    enableDisableActiveTabColor(activeTabHighlightCheckbox.isSelected());
  }

  @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
  private void isActiveCheckboxActionPerformed(final ActionEvent e) {
    toggleOptions(isActiveCheckbox.isSelected());
  }

  private void useProjectFrameCheckboxActionPerformed(final ActionEvent e) {
    enableDisableProjectFrame(useProjectFrameCheckbox.isSelected());
  }

  private void useCustomTextCheckboxActionPerformed(final ActionEvent e) {
    enableDisableCustomTitle(useCustomTextCheckbox.isSelected());
  }

  //endregion

  //region Events - Actions Listeners

  private void resetDefaultsButtonActionPerformed(final ActionEvent e) {
    @NonNls final ResourceBundle bundle = ResourceBundle.getBundle(MaterialThemeBundle.BUNDLE);

    final int answer = Messages.showYesNoDialog(bundle.getString("MTForm.dialog.resetDefaults.consent"),
      bundle.getString("MTForm.resetDefaultsButton.text"),
      Messages.getWarningIcon());
    if (answer == Messages.YES) {
      final MTConfig config = MTConfig.getInstance();
      config.resetSettings();
      setFormState(config);
    }
  }

  //endregion

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JPanel settings;
  private JCheckBox isActiveCheckbox;
  private JTabbedPane tabbedPane1;
  private JPanel tabPanel;
  private JLabel tabsDesc;
  private JCheckBox activeTabHighlightCheckbox;
  private ColorPanel activeTabHighlightColor;
  private JLabel thicknessLabel;
  private JSpinner highlightSpinner;
  private JCheckBox isUpperCaseTabsCheckbox;
  private JLabel positionLabel;
  private ComboBox<TabHighlightPositions> tabHighlightPositionComboBox;
  private JPanel projectFramePanel;
  private JLabel projectFrameDesc;
  private JCheckBox useProjectFrameCheckbox;
  private ColorPanel projectFrameColor;
  private JCheckBox showProjectTitleCheckbox;
  private JCheckBox useCustomTextCheckbox;
  private JTextField customTextField;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
