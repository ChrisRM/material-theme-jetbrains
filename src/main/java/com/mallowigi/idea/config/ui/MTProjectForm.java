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

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.components.OnOffButton;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mallowigi.idea.config.enums.IndicatorStyles;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

/**
 * @author Elior Boukhobza
 */
public class MTProjectForm implements MTFormUI {
  public MTProjectForm() {
    initComponents();
  }

  private void activeTabHighlightCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void fontSizeCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void tabFontSizeCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void isCompactSidebarCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void customTreeIndentCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void useMaterialFontCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void accentModeCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void useMaterialWallpapersCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void codeAdditionsCheckBoxActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  private void enforceLanguageOnOffActionPerformed(final ActionEvent e) {
    // TODO add your code here
  }

  @Override
  public void init() {

  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    final DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
    tabbedPane1 = new JTabbedPane();
    tabPanel = new JPanel();
    tabsDesc = compFactory.createLabel("Customize your editor tabs appearance.");
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    thicknessLabel = new JLabel();
    highlightSpinner = new JSpinner();
    isUpperCaseTabsCheckbox = new JCheckBox();
    positionLabel = new JLabel();
    tabHighlightPositionComboBox = new ComboBox<>();
    projectViewPanel = new JPanel();
    projectViewDesc = compFactory.createLabel("Personalize your Project View and other similar trees' appearance");
    selectedIndicatorLabel = new JLabel();
    indicatorStyleComboBox = new ComboBox<>();
    indicatorThicknessLabel = new JLabel();
    indicatorThicknessSpinner = new JSpinner();
    componentsPanel = new JPanel();
    componentDesc = compFactory.createLabel("Further customize some IDE components' appearance.");
    upperCaseButtonsCheckbox = new JCheckBox();
    borderedButtonsCheckbox = new JCheckBox();
    tabShadowCheckbox = new JCheckBox();
    featuresPanel = new JPanel();
    featuresDesc = compFactory.createLabel("Enable/Disable the plugin's main features");
    accentModeCheckbox = new JCheckBox();
    secondAccentLabel = new JLabel();
    secondAccentColorChooser = new ColorPanel();
    useProjectFrameCheckbox = new JCheckBox();
    otherTweaksPanel = new JPanel();
    tweaksDesc = compFactory.createLabel("Other useful tweaks provided by the plugin");
    codeAdditionsCheckBox = new JCheckBox();
    enforceHighlightingLabel = new JLabel();
    enforceLanguageOnOff = new OnOffButton();

    //======== this ========
    setLayout(new MigLayout(
      "hidemode 3",
      // columns
      "[fill]" +
        "[fill]",
      // rows
      "[]"));

    //======== tabbedPane1 ========
    {
      tabbedPane1.setMinimumSize(null);
      tabbedPane1.setPreferredSize(null);
      tabbedPane1.setBorder(new TitledBorder(bundle.getString("MTProjectForm.tabbedPane1.border")));

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
        tabPanel.add(tabsDesc, "cell 0 0 2 1");

        //---- activeTabHighlightCheckbox ----
        activeTabHighlightCheckbox.setText("Active Tab Highlight Color");
        activeTabHighlightCheckbox.setToolTipText("Override Tab Underline color");
        activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
        tabPanel.add(activeTabHighlightCheckbox, "cell 0 1,align left center,grow 0 0");
        tabPanel.add(activeTabHighlightColor, "cell 1 1,align right center,grow 0 0");

        //---- thicknessLabel ----
        thicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        thicknessLabel.setLabelFor(highlightSpinner);
        thicknessLabel.setText("Thickness");
        thicknessLabel.setToolTipText("Specify the thickness of the indicator");
        tabPanel.add(thicknessLabel, "pad 0,cell 0 2,aligny center,grow 100 0");

        //---- highlightSpinner ----
        highlightSpinner.setToolTipText("Select a value between 1 and 5");
        tabPanel.add(highlightSpinner, "cell 1 2,align right center,grow 0 0,width 80:80:80");

        //---- isUpperCaseTabsCheckbox ----
        isUpperCaseTabsCheckbox.setText("Uppercase Bold Tabs");
        isUpperCaseTabsCheckbox.setToolTipText("Set tabs in Uppercase Bold");
        tabPanel.add(isUpperCaseTabsCheckbox, "cell 0 3,align left center,grow 0 0");

        //---- positionLabel ----
        positionLabel.setText("Tab Highlight Position");
        positionLabel.setToolTipText("Specify the position of the highlight in tabs");
        tabPanel.add(positionLabel, "cell 0 4,aligny center,growy 0");

        //---- tabHighlightPositionComboBox ----
        tabHighlightPositionComboBox.setToolTipText("Specify the position of the highlight in tabs");
        tabPanel.add(tabHighlightPositionComboBox, "cell 1 4,align right center,grow 0 0,width 120:120:120");
      }
      tabbedPane1.addTab("Tabs", null, tabPanel, "Customize your tabs");

      //======== projectViewPanel ========
      {
        projectViewPanel.setBorder(null);
        projectViewPanel.setLayout(new MigLayout(
          "fillx,hidemode 3,align left top",
          // columns
          "[189,left]" +
            "[right]",
          // rows
          "[]" +
            "[]" +
            "[]"));

        //---- projectViewDesc ----
        projectViewDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
        projectViewPanel.add(projectViewDesc, "cell 0 0 2 1");

        //---- selectedIndicatorLabel ----
        selectedIndicatorLabel.setText("Selected Indicator Style");
        selectedIndicatorLabel.setToolTipText("Choose a style for the selected item in trees");
        projectViewPanel.add(selectedIndicatorLabel, "cell 0 1");

        //---- indicatorStyleComboBox ----
        indicatorStyleComboBox.setToolTipText("Change the style of the selected line indicator in trees");
        projectViewPanel.add(indicatorStyleComboBox, "cell 1 1,align right center,grow 0 0,width 120:120:120");

        //---- indicatorThicknessLabel ----
        indicatorThicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        indicatorThicknessLabel.setLabelFor(highlightSpinner);
        indicatorThicknessLabel.setText("Thickness");
        indicatorThicknessLabel.setToolTipText("Specify the thickness of the indicator");
        projectViewPanel.add(indicatorThicknessLabel, "pad 0 16 0 0,cell 0 2,growx");

        //---- indicatorThicknessSpinner ----
        indicatorThicknessSpinner.setToolTipText("Control the thickness of the indicator");
        projectViewPanel.add(indicatorThicknessSpinner, "cell 1 2,alignx right,growx 0");
      }
      tabbedPane1.addTab("Project View", null, projectViewPanel, "Customize your project view");

      //======== componentsPanel ========
      {
        componentsPanel.setBorder(null);
        componentsPanel.setLayout(new MigLayout(
          "fillx,hidemode 3,align left top",
          // columns
          "[208,left]" +
            "[right]",
          // rows
          "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- componentDesc ----
        componentDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
        componentsPanel.add(componentDesc, "cell 0 0 2 1");

        //---- upperCaseButtonsCheckbox ----
        upperCaseButtonsCheckbox.setText("Uppercase buttons");
        upperCaseButtonsCheckbox.setToolTipText("Set buttons in upper case");
        componentsPanel.add(upperCaseButtonsCheckbox, "cell 0 1,alignx left,growx 0");

        //---- borderedButtonsCheckbox ----
        borderedButtonsCheckbox.setText("Outlined Buttons");
        borderedButtonsCheckbox.setToolTipText("When enabled, buttons will have an outline style");
        componentsPanel.add(borderedButtonsCheckbox, "cell 0 2");

        //---- tabShadowCheckbox ----
        tabShadowCheckbox.setText("Tabs Shadow");
        tabShadowCheckbox.setToolTipText("Enable or disable the shadow under the tabs");
        componentsPanel.add(tabShadowCheckbox, "cell 0 3");
      }
      tabbedPane1.addTab("Components", null, componentsPanel, "Customize some of the components");

      //======== featuresPanel ========
      {
        featuresPanel.setBorder(null);
        featuresPanel.setLayout(new MigLayout(
          "fillx,hidemode 3,align left top",
          // columns
          "[left]" +
            "[right]",
          // rows
          "[]" +
            "[]" +
            "[]"));

        //---- featuresDesc ----
        featuresDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
        featuresPanel.add(featuresDesc, "cell 0 0 2 1");

        //---- accentModeCheckbox ----
        accentModeCheckbox.setText("Accent Mode");
        accentModeCheckbox.setToolTipText("Make components stand out by setting their color to the accent color");
        accentModeCheckbox.addActionListener(e -> accentModeCheckboxActionPerformed(e));
        featuresPanel.add(accentModeCheckbox, "cell 0 2");

        //---- secondAccentLabel ----
        secondAccentLabel.setText("Second Accent Color");
        secondAccentLabel.setToolTipText("Add a second accent color (for links and stuff)");
        featuresPanel.add(secondAccentLabel, "cell 1 2");

        //---- secondAccentColorChooser ----
        secondAccentColorChooser.setMinimumSize(new Dimension(10, 18));
        secondAccentColorChooser.setPreferredSize(new Dimension(61, 26));
        featuresPanel.add(secondAccentColorChooser, "cell 1 2");

        //---- useProjectFrameCheckbox ----
        useProjectFrameCheckbox.setText("Project Frame Colors");
        useProjectFrameCheckbox.setToolTipText("Automatically adds a unique color to each window's project frame for better visualization");
        featuresPanel.add(useProjectFrameCheckbox, "cell 0 1,align left center,grow 0 0");
      }
      tabbedPane1.addTab("Features", null, featuresPanel, "Disable specific features of the plugin");

      //======== otherTweaksPanel ========
      {
        otherTweaksPanel.setBorder(null);
        otherTweaksPanel.setLayout(new MigLayout(
          "fillx,hidemode 3,align left top",
          // columns
          "[left]" +
            "[fill]",
          // rows
          "[]" +
            "[31,fill]"));

        //---- tweaksDesc ----
        tweaksDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
        otherTweaksPanel.add(tweaksDesc, "cell 0 0 2 1");

        //---- codeAdditionsCheckBox ----
        codeAdditionsCheckBox.setText("Language Additions");
        codeAdditionsCheckBox.setToolTipText("Add syntax highlighting additions for some languages");
        codeAdditionsCheckBox.addActionListener(e -> codeAdditionsCheckBoxActionPerformed(e));
        otherTweaksPanel.add(codeAdditionsCheckBox, "cell 0 1,align left center,grow 0 0");

        //---- enforceHighlightingLabel ----
        enforceHighlightingLabel.setText("Enforce Highlighting");
        enforceHighlightingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        otherTweaksPanel.add(enforceHighlightingLabel, "cell 1 1,growx");

        //---- enforceLanguageOnOff ----
        enforceLanguageOnOff.setText("Enforce Highlighting");
        enforceLanguageOnOff.setToolTipText("Toggle to make language additions to max priority, but making them appear as \"weak " +
          "warnings\"");
        enforceLanguageOnOff.addActionListener(e -> enforceLanguageOnOffActionPerformed(e));
        otherTweaksPanel.add(enforceLanguageOnOff, "cell 1 1,alignx right,growx 0");
      }
      tabbedPane1.addTab("Other Tweaks", null, otherTweaksPanel, "Other useful tweaks provided by the plugin");
    }
    add(tabbedPane1, "pad 0,cell 0 0,growx,gapx 10 10,gapy 10 10");
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  @Override
  public void setupComponents() {

  }

  @Override
  public JComponent getContent() {
    return null;
  }

  @Override
  public void dispose() {

  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
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
  private JPanel projectViewPanel;
  private JLabel projectViewDesc;
  private JLabel selectedIndicatorLabel;
  private ComboBox<IndicatorStyles> indicatorStyleComboBox;
  private JLabel indicatorThicknessLabel;
  private JSpinner indicatorThicknessSpinner;
  private JPanel componentsPanel;
  private JLabel componentDesc;
  private JCheckBox upperCaseButtonsCheckbox;
  private JCheckBox borderedButtonsCheckbox;
  private JCheckBox tabShadowCheckbox;
  private JPanel featuresPanel;
  private JLabel featuresDesc;
  private JCheckBox accentModeCheckbox;
  private JLabel secondAccentLabel;
  private ColorPanel secondAccentColorChooser;
  private JCheckBox useProjectFrameCheckbox;
  private JPanel otherTweaksPanel;
  private JLabel tweaksDesc;
  private JCheckBox codeAdditionsCheckBox;
  private JLabel enforceHighlightingLabel;
  private OnOffButton enforceLanguageOnOff;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
