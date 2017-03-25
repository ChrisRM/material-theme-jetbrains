package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MTForm implements MTFormUI {
  private CheckBoxWithColorChooserImpl checkBoxWithColorChooserImpl;
  private JPanel content;
  private JSpinner highlightSpinner;
  private SpinnerModel highlightSpinnerModel;

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

  private void createUIComponents() {
    checkBoxWithColorChooserImpl = new CheckBoxWithColorChooserImpl("Active Tab Highlight Color  ");
  }
}
