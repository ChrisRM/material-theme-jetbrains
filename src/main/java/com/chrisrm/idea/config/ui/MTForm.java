package com.chrisrm.idea.config.ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Created by helio on 24/03/2017.
 */
public class MTForm implements MTFormUI {
  private CheckBoxWithColorChooserImpl checkBoxWithColorChooserImpl;
  private JPanel content;

  private void createUIComponents() {
    checkBoxWithColorChooserImpl = new CheckBoxWithColorChooserImpl("Active Tab Highlight Color  ");
  }


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
    checkBoxWithColorChooserImpl.dispose();
  }

  public void setHighlightColor(@NotNull Color highlightColor) {
    checkBoxWithColorChooserImpl.setColor(highlightColor);
  }

  public Color getHighlightColor() {
    return checkBoxWithColorChooserImpl.getColor();
  }

  public void setHighlightColorEnabled(boolean enabled) {
    checkBoxWithColorChooserImpl.setSelected(enabled);
  }

  public boolean getHighlightColorEnabled() {
    return checkBoxWithColorChooserImpl.isSelected();
  }

}
