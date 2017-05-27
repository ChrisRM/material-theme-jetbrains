package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.ui.*;
import com.intellij.ide.ui.LafManager;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MTLafComponent implements ApplicationComponent {

  private boolean isMaterialDesign;

  public MTLafComponent(LafManager lafManager) {
    lafManager.addLafManagerListener(source -> installTheme());
  }

  @Override
  public void initComponent() {
    installTheme();

    ApplicationManager.getApplication().getMessageBus().connect()
                      .subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> this.restartIdeIfNecessary());
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return this.getClass().getName();
  }

  private void restartIdeIfNecessary() {
    MTConfig mtConfig = MTConfig.getInstance();

    // Restart the IDE if changed
    if (mtConfig.isMaterialDesignChanged(this.isMaterialDesign)) {
      String title = MaterialThemeBundle.message("mt.restartDialog.title");
      String message = MaterialThemeBundle.message("mt.restartDialog.content");

      int answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon());
      if (answer == Messages.YES) {
        Application application = ApplicationManager.getApplication();
        if (application instanceof ApplicationImpl) {
          ((ApplicationImpl) application).restart(true);
        } else {
          application.restart();
        }
      }
    }
  }

  private void installTheme() {
    MTConfig mtConfig = MTConfig.getInstance();
    this.isMaterialDesign = mtConfig.getIsMaterialDesign();

    if (mtConfig.getIsMaterialDesign()) {
      replaceButtons();
      //      replaceTextFields();
      replaceProgressBar();
    }

    //        UIReplacer.patchUI();
  }

  private void replaceProgressBar() {
    UIManager.put("ProgressBarUI", MTProgressBarUI.class.getName());
    UIManager.getDefaults().put(MTProgressBarUI.class.getName(), MTProgressBarUI.class);

    UIManager.put("ProgressBar.border", new MTProgressBarBorder());

    //        UIManager.put("MenuItem.border", new MTMenuItemBorder());
    //        UIManager.put("Menu.border", new MTMenuItemBorder());
    //
    //                UIManager.put("PopupMenu.border", new MTPopupMenuBorder());

  }

  private void replaceTextFields() {
    UIManager.put("TextFieldUI", MTTextFieldUI.class.getName());
    UIManager.getDefaults().put(MTTextFieldUI.class.getName(), MTTextFieldUI.class);
  }

  private void replaceButtons() {
    UIManager.put("ButtonUI", MTButtonUI.class.getName());
    UIManager.getDefaults().put(MTButtonUI.class.getName(), MTButtonUI.class);

    UIManager.put("Button.border", new MTButtonPainter());
  }
}
