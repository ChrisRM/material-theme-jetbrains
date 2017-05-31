package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class MTForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;

  // Generated using JFormDesigner Evaluation license - Mario Smilax
  private JPanel content;

  @Override
  public void init() {
    MTConfig config = MTConfig.getInstance();
    highlightSpinnerModel = new SpinnerNumberModel(config.getHighlightThickness(), 1, 5, 1);
    highlightSpinner.setModel(highlightSpinnerModel);
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
    checkBoxWithColorChooserImpl.dispose();
  }

  public Color getHighlightColor() {
    return checkBoxWithColorChooserImpl.getColor();
  }

  public void setHighlightColor(@NotNull Color highlightColor) {
    checkBoxWithColorChooserImpl.setColor(highlightColor);
  }

  public boolean getHighlightColorEnabled() {
    return checkBoxWithColorChooserImpl.isSelected();
  }

  public void setHighlightColorEnabled(boolean enabled) {
    checkBoxWithColorChooserImpl.setSelected(enabled);
  }

  public Integer getHighlightThickness() {
    return (Integer) highlightSpinnerModel.getValue();
  }

  public void setHighlightThickness(Integer highlightThickness) {
    highlightSpinnerModel.setValue(highlightThickness);
  }

  public boolean getIsContrastMode() {
    return isContrastModeCheckbox.isSelected();
  }

  public void setIsContrastMode(boolean isContrastMode) {
    isContrastModeCheckbox.setSelected(isContrastMode);
  }

  public boolean getIsMaterialDesign() {
    return isMaterialDesignCheckbox.isSelected();
  }

  public void setIsMaterialDesign(boolean isMaterialDesign) {
    this.isMaterialDesignCheckbox.setSelected(isMaterialDesign);
  }

  public boolean getIsBoldTabs() {
    return this.boldTabs.isSelected();
  }

  public void setIsBoldTabs(boolean isBold) {
    this.boldTabs.setSelected(isBold);
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - Mario Smilax
  private JPanel content;
  private CheckBoxWithColorChooserImpl checkBoxWithColorChooserImpl;
  private JSpinner highlightSpinner;
  private JButton reset;
  private JCheckBox boldTabs;
  private JCheckBox isContrastModeCheckbox;
  private JCheckBox isWallpaperSetCheckbox;
  private JPanel customBgPanel;
  private JTextField customBgTextField;
  private JButton bgChooser;
  private JLabel customBgLabel;
  private JCheckBox isMaterialDesignCheckbox;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

  public MTForm() {

    initComponents();
    reset.addActionListener(e -> {
      final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();
      Color borderColor = mtTheme.getBorderColor();
      int thickness = mtTheme.getBorderThickness();

      this.setHighlightColor(borderColor);
      this.setHighlightColorEnabled(false);
      this.setHighlightThickness(thickness);
      this.setIsBoldTabs(false);
    });
  }

  public boolean getIsWallpaperSet() {
    return this.isWallpaperSetCheckbox.isSelected();
  }

  public void setIsWallpaperSet(boolean isWallpaperSet) {
    this.isWallpaperSetCheckbox.setSelected(isWallpaperSet);
    enableDisableCustomBg(isWallpaperSet);
  }

  public void setCustomWallpaper(String wallpaper) {
    this.customBgTextField.setText(wallpaper);
  }

  public String getWallpaper() {
    return this.customBgTextField.getText();
  }

  private void enableDisableCustomBg(boolean isWallpaperSet) {
    this.customBgLabel.setEnabled(isWallpaperSet);
    this.customBgTextField.setEnabled(isWallpaperSet);
    this.bgChooser.setEnabled(isWallpaperSet);
  }

  private void createUIComponents() {
    checkBoxWithColorChooserImpl = new CheckBoxWithColorChooserImpl(MaterialThemeBundle.message("mt.activetab.text"));
  }

  private void bgChooserActionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes()));
    int bgImage = fileChooser.showOpenDialog(null);

    if (bgImage == JFileChooser.APPROVE_OPTION) {
      this.customBgTextField.setText(fileChooser.getSelectedFile().toString());
    }
  }

  private void isWallpaperSetCheckboxActionPerformed(ActionEvent e) {
    this.enableDisableCustomBg(this.isWallpaperSetCheckbox.isSelected());
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Mario Smilax
    createUIComponents();

    ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    JPanel panel1 = new JPanel();
    Spacer hSpacer1 = new Spacer();
    JLabel label1 = new JLabel();
    highlightSpinner = new JSpinner();
    Spacer vSpacer1 = new Spacer();
    reset = new JButton();
    boldTabs = new JCheckBox();
    Spacer vSpacer2 = new Spacer();
    JPanel panel2 = new JPanel();
    isContrastModeCheckbox = new JCheckBox();
    Spacer hSpacer2 = new Spacer();
    Spacer vSpacer3 = new Spacer();
    JPanel panel3 = new JPanel();
    isWallpaperSetCheckbox = new JCheckBox();
    customBgPanel = new JPanel();
    customBgTextField = new JTextField();
    bgChooser = new JButton();
    customBgLabel = new JLabel();
    isMaterialDesignCheckbox = new JCheckBox();

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
          java.awt.Color.red), content.getBorder())); content.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

      content.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== panel1 ========
      {
        panel1.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.activetab")));
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));

        //---- checkBoxWithColorChooserImpl ----
        checkBoxWithColorChooserImpl.setDoubleBuffered(true);
        checkBoxWithColorChooserImpl.setToolTipText(bundle.getString("mt.activetab.highlight.tooltip"));
        panel1.add(checkBoxWithColorChooserImpl, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          null, new Dimension(204, 18), null));
        panel1.add(hSpacer1, new GridConstraints(0, 1, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          null, new Dimension(14, 28), null));

        //---- label1 ----
        label1.setHorizontalTextPosition(SwingConstants.LEADING);
        label1.setLabelFor(highlightSpinner);
        label1.setText(bundle.getString("mt.border.thickness"));
        label1.setToolTipText(bundle.getString("mt.border.thickness.tooltip"));
        panel1.add(label1, new GridConstraints(1, 0, 1, 1,
          GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
          GridConstraints.SIZEPOLICY_FIXED,
          GridConstraints.SIZEPOLICY_FIXED,
          null, new Dimension(204, 18), null));
        panel1.add(highlightSpinner, new GridConstraints(1, 1, 1, 1,
          GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, new Dimension(89, 29), null));
        panel1.add(vSpacer1, new GridConstraints(4, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          null, null, null));

        //---- reset ----
        reset.setText(bundle.getString("mt.resetdefaults"));
        reset.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
        panel1.add(reset, new GridConstraints(3, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_FIXED,
          null, null, null));

        //---- boldTabs ----
        boldTabs.setLabel(bundle.getString("mt.boldtabs"));
        boldTabs.setText(bundle.getString("mt.boldtabs"));
        boldTabs.setToolTipText(bundle.getString("mt.boldtabs.tooltip"));
        panel1.add(boldTabs, new GridConstraints(2, 0, 1, 1,
          GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_FIXED,
          null, null, null));
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
        panel2.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));

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
        panel2.add(vSpacer3, new GridConstraints(2, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          null, null, null));
      }
      content.add(panel2, new GridConstraints(1, 0, 1, 1,
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
        null, null, null));

      //======== panel3 ========
      {
        panel3.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.panel3.border")));
        panel3.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));

        //---- isWallpaperSetCheckbox ----
        isWallpaperSetCheckbox.setLabel(bundle.getString("MTForm.isWallpaperSetCheckbox.label"));
        isWallpaperSetCheckbox.setText(bundle.getString("MTForm.isWallpaperSetCheckbox.text"));
        isWallpaperSetCheckbox.setToolTipText("Whether to allow setting a custom wallpaper for the IDE");
        isWallpaperSetCheckbox.addActionListener(e -> isWallpaperSetCheckboxActionPerformed(e));
        panel3.add(isWallpaperSetCheckbox, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_FIXED,
          null, null, null));

        //======== customBgPanel ========
        {
          customBgPanel.setAlignmentX(0.0F);
          customBgPanel.setAlignmentY(0.0F);
          customBgPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), 5, -1));

          //---- customBgTextField ----
          customBgTextField.setHorizontalAlignment(SwingConstants.LEFT);
          customBgPanel.add(customBgTextField, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_WANT_GROW,
            new Dimension(0, 0), new Dimension(300, 26), null));

          //---- bgChooser ----
          bgChooser.setText(bundle.getString("MTForm.bgChooser.text"));
          bgChooser.addActionListener(e -> bgChooserActionPerformed(e));
          customBgPanel.add(bgChooser, new GridConstraints(0, 2, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

          //---- customBgLabel ----
          customBgLabel.setText(bundle.getString("MTForm.customBgLabel.text"));
          customBgPanel.add(customBgLabel, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null, 2));
        }
        panel3.add(customBgPanel, new GridConstraints(1, 0, 1, 1,
          GridConstraints.ANCHOR_EAST, GridConstraints.FILL_HORIZONTAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));

        //---- isMaterialDesignCheckbox ----
        isMaterialDesignCheckbox.setLabel(bundle.getString("MTForm.isMaterialDesignCheckbox.label"));
        isMaterialDesignCheckbox.setText(bundle.getString("MTForm.isMaterialDesignCheckbox.text"));
        isMaterialDesignCheckbox.setToolTipText(bundle.getString("MTForm.isMaterialDesignCheckbox.toolTipText"));
        panel3.add(isMaterialDesignCheckbox, new GridConstraints(2, 0, 1, 1,
          GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_FIXED,
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
