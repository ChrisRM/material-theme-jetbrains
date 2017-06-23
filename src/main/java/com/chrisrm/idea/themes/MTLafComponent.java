package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.ui.*;
import com.intellij.ide.ui.LafManager;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBPanel;
import javassist.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MTLafComponent extends JBPanel implements ApplicationComponent {

  private boolean isMaterialDesign;

  static {
    try {
      hackTabsSDK();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public MTLafComponent(LafManager lafManager) {
    lafManager.addLafManagerListener(source -> installMaterialComponents());
  }

  @Override
  public void initComponent() {
    installMaterialComponents();

    // Listen for changes on the settings
    ApplicationManager.getApplication().getMessageBus().connect()
                      .subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> this.onSettingsChanged());
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return this.getClass().getName();
  }

  /**
   * Called when MT Config settings are changeds
   */
  private void onSettingsChanged() {
    // Force restart if material design is switched
    restartIdeIfNecessary();

    // Trigger file icons and statuses update
    ApplicationManager.getApplication().runWriteAction(() -> {
      FileTypeManagerEx instanceEx = FileTypeManagerEx.getInstanceEx();
      instanceEx.fireFileTypesChanged();
      ActionToolbarImpl.updateAllToolbarsImmediately();
    });
  }

  /**
   * Restart IDE if necessary (ex: material design components)
   */
  private void restartIdeIfNecessary() {
    MTConfig mtConfig = MTConfig.getInstance();

    // Restart the IDE if changed
    if (mtConfig.isMaterialDesignChanged(this.isMaterialDesign)
        ) {
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

  /**
   * Install Material Design components
   */
  private void installMaterialComponents() {
    MTConfig mtConfig = MTConfig.getInstance();
    this.isMaterialDesign = mtConfig.getIsMaterialDesign();

    if (mtConfig.getIsMaterialDesign()) {
      replaceButtons();
      replaceTextFields();
      replaceProgressBar();
      replaceTree();
      replaceTableHeaders();
      //      try {
      //        hackTabsSDK();
      //      }
      //      catch (Exception e) {
      //        e.printStackTrace();
      //      }
    }
  }

  private void replaceTableHeaders() {
    UIManager.put("TableHeaderUI", MTTableHeaderUI.class.getName());
    UIManager.getDefaults().put(MTTableHeaderUI.class.getName(), MTTableHeaderUI.class);

    UIManager.put("TableHeader.border", new MTTableHeaderBorder());

  }

  /**
   * Replace progress bar (TODO: Material Progress bar)
   */
  private void replaceProgressBar() {
    UIManager.put("ProgressBarUI", MTProgressBarUI.class.getName());
    UIManager.getDefaults().put(MTProgressBarUI.class.getName(), MTProgressBarUI.class);

    UIManager.put("ProgressBar.border", new MTProgressBarBorder());
  }

  /**
   * Replace text fields (TODO: replace password fields)
   */
  private void replaceTextFields() {
    UIManager.put("TextFieldUI", MTTextFieldUI.class.getName());
    UIManager.getDefaults().put(MTTextFieldUI.class.getName(), MTTextFieldUI.class);

    UIManager.put("PasswordFieldUI", MTPasswordFieldUI.class.getName());
    UIManager.getDefaults().put(MTPasswordFieldUI.class.getName(), MTPasswordFieldUI.class);

    UIManager.put("TextField.border", new MTTextBorder());
    UIManager.put("PasswordField.border", new MTTextBorder());

  }

  /**
   * Replace buttons
   */
  private void replaceButtons() {
    UIManager.put("ButtonUI", MTButtonUI.class.getName());
    UIManager.getDefaults().put(MTButtonUI.class.getName(), MTButtonUI.class);

    UIManager.put("Button.border", new MTButtonPainter());
  }

  private static void hackCreateUI() throws NotFoundException, CannotCompileException,
      IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
    ClassPool cp = ClassPool.getDefault();
    cp.insertClassPath(new ClassClassPath(MTTextFieldUI.class));

    CtClass darculaClass = cp.get("com.intellij.ide.ui.laf.darcula.ui.DarculaTextFieldUI");
    CtClass componentClass = cp.get("javax.swing.JComponent");
    CtMethod createUI = darculaClass.getDeclaredMethod("createUI", new CtClass[]{componentClass});
    createUI.setBody("{ return com.chrisrm.idea.ui.MTTextFieldFactory.newInstance($1); }");
    darculaClass.toClass();
  }

  private void replaceTree() {
    UIManager.put("TreeUI", MTTreeUI.class.getName());
    UIManager.getDefaults().put(MTTreeUI.class.getName(), MTTreeUI.class);
  }

  private static void hackTabsSDK() throws
      NotFoundException,
      CannotCompileException,
      IOException,
      IllegalAccessException,
      InvocationTargetException,
      ClassNotFoundException {
    //    ClassPool cp = new ClassPool(true);
    //    CtClass ctClass = cp.get("com.intellij.ui.tabs.TabsUtil");
    //    CtMethod ctMethod = ctClass.getDeclaredMethod("getTabsHeight");
    //    ctMethod.setBody("{ return 38; }");
    //    ctClass.toClass();

    hackCreateUI();
  }
}
