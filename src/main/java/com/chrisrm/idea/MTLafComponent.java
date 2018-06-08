/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea;

import com.chrisrm.idea.config.BeforeConfigNotifier;
import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.config.ui.MTForm;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.ui.MTSelectedTreePainter;
import com.chrisrm.idea.ui.MTTreeUI;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.UISettingsListener;
import com.intellij.ide.ui.laf.IntelliJTableSelectedCellHighlightBorder;
import com.intellij.ide.ui.laf.darcula.DarculaTableHeaderBorder;
import com.intellij.ide.ui.laf.darcula.DarculaTableHeaderUI;
import com.intellij.ide.ui.laf.darcula.DarculaTableSelectedCellHighlightBorder;
import com.intellij.ide.ui.laf.darcula.ui.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBPanel;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import static com.chrisrm.idea.icons.IconReplacer.applyFilter;

/**
 * Component for working on the Material Look And Feel
 */
public final class MTLafComponent extends JBPanel implements ApplicationComponent {

  private boolean willRestartIde = false;
  private MessageBusConnection connect;

  public MTLafComponent(final LafManager lafManager) {
    //    lafManager.addLafManagerListener(source -> installMaterialComponents());
  }


  private void installDarculaDefaults() {
    UIManager.put("darcula.primary", new ColorUIResource(0x3c3f41));
    UIManager.put("darcula.contrastColor", new ColorUIResource(0x262626));

    UIManager.put("EditorPaneUI", DarculaEditorPaneUI.class.getName());
    UIManager.put("TableHeaderUI", DarculaTableHeaderUI.class.getName());
    UIManager.put("Table.focusSelectedCellHighlightBorder", new DarculaTableSelectedCellHighlightBorder());
    UIManager.put("TableHeader.cellBorder", new DarculaTableHeaderBorder());

    UIManager.put("CheckBoxMenuItemUI", DarculaCheckBoxMenuItemUI.class.getName());
    UIManager.put("RadioButtonMenuItemUI", DarculaRadioButtonMenuItemUI.class.getName());
    UIManager.put("TabbedPaneUI", DarculaTabbedPaneUI.class.getName());

    UIManager.put("TextFieldUI", DarculaTextFieldUI.class.getName());
    UIManager.put("TextField.border", new DarculaTextBorder());
    //    UIManager.put("TextField.darcula.search.icon", "/com/intellij/ide/ui/laf/icons/darcula/search.png");
    //    UIManager.put("TextField.darcula.searchWithHistory.icon", "/com/intellij/ide/ui/laf/icons/darcula/searchWithHistory.png");
    //    UIManager.put("TextField.darcula.clear.icon", "/com/intellij/ide/ui/laf/icons/darcula/clear.png");

    UIManager.put("PasswordFieldUI", DarculaPasswordFieldUI.class.getName());
    UIManager.put("PasswordField.border", new DarculaTextBorder());
    UIManager.put("ProgressBarUI", DarculaProgressBarUI.class.getName());
    UIManager.put("ProgressBar.border", new DarculaProgressBarBorder());
    UIManager.put("FormattedTextFieldUI", DarculaTextFieldUI.class.getName());
    UIManager.put("FormattedTextField.border", new DarculaTextBorder());

    UIManager.put("TextAreaUI", DarculaTextAreaUI.class.getName());
    UIManager.put("CheckBoxUI", DarculaCheckBoxUI.class.getName());

    UIManager.put("CheckBox.border", new DarculaCheckBoxBorder());
    UIManager.put("ComboBoxUI", DarculaComboBoxUI.class.getName());
    UIManager.put("RadioButtonUI", DarculaRadioButtonUI.class.getName());
    UIManager.put("RadioButton.border", new DarculaCheckBoxBorder());

    UIManager.put("Button.border", new DarculaButtonPainter());
    UIManager.put("ButtonUI", DarculaButtonUI.class.getName());

    UIManager.put("ToggleButton.border", new DarculaButtonPainter());
    UIManager.put("ToggleButtonUI", DarculaButtonUI.class.getName());

    UIManager.put("SpinnerUI", DarculaSpinnerUI.class.getName());
    UIManager.put("Spinner.border", new DarculaSpinnerBorder());

    UIManager.put("TreeUI", DarculaTreeUI.class.getName());
    UIManager.put("OptionButtonUI", DarculaOptionButtonUI.class.getName());
    UIManager.put("grayFilter", new UIUtil.GrayFilter(-100, -100, 100));
    UIManager.put("text.grayFilter", new UIUtil.GrayFilter(-15, -10, 100));
  }

  private void installLightDefaults() {
    UIManager.put("intellijlaf.primary", new ColorUIResource(0xe8e8e8));
    UIManager.put("intellijlaf.contrastColor", new ColorUIResource(0xEEEEEE));

    UIManager.put("EditorPaneUI", DarculaEditorPaneUI.class.getName());
    UIManager.put("TableHeaderUI", DarculaTableHeaderUI.class.getName());
    UIManager.put("Table.focusSelectedCellHighlightBorder", new IntelliJTableSelectedCellHighlightBorder());
    UIManager.put("TableHeader.cellBorder", new DarculaTableHeaderBorder());

    UIManager.put("CheckBoxMenuItemUI", DarculaCheckBoxMenuItemUI.class.getName());
    UIManager.put("RadioButtonMenuItemUI", DarculaRadioButtonMenuItemUI.class.getName());
    UIManager.put("TabbedPaneUI", DarculaTabbedPaneUI.class.getName());

    UIManager.put("TextFieldUI", DarculaTextFieldUI.class.getName());
    UIManager.put("TextField.border", new DarculaTextBorder());
    //    UIManager.put("TextField.darcula.search.icon", "/com/intellij/ide/ui/laf/icons/search.png");
    //    UIManager.put("TextField.darcula.searchWithHistory.icon", "/com/intellij/ide/ui/laf/icons/searchWithHistory.png");
    //    UIManager.put("TextField.darcula.clear.icon", "/com/intellij/ide/ui/laf/icons/clear.png");

    UIManager.put("PasswordFieldUI", DarculaPasswordFieldUI.class.getName());
    UIManager.put("PasswordField.border", new DarculaTextBorder());
    UIManager.put("ProgressBarUI", DarculaProgressBarUI.class.getName());
    UIManager.put("ProgressBar.border", new DarculaProgressBarBorder());
    UIManager.put("FormattedTextFieldUI", DarculaTextFieldUI.class.getName());
    UIManager.put("FormattedTextField.border", new DarculaTextBorder());

    UIManager.put("TextAreaUI", DarculaTextAreaUI.class.getName());
    UIManager.put("Tree.paintLines", false);

    UIManager.put("CheckBoxUI", DarculaCheckBoxUI.class.getName());
    UIManager.put("CheckBox.border", new DarculaCheckBoxBorder());
    UIManager.put("ComboBoxUI", DarculaComboBoxUI.class.getName());
    UIManager.put("RadioButtonUI", DarculaRadioButtonUI.class.getName());
    UIManager.put("RadioButton.border", new DarculaCheckBoxBorder());

    UIManager.put("Button.border", new DarculaButtonPainter());
    UIManager.put("ButtonUI", DarculaButtonUI.class.getName());

    UIManager.put("ToggleButton.border", new DarculaButtonPainter());
    UIManager.put("ToggleButtonUI", DarculaButtonUI.class.getName());

    UIManager.put("SpinnerUI", DarculaSpinnerUI.class.getName());
    UIManager.put("Spinner.border", new DarculaSpinnerBorder());

    UIManager.put("TreeUI", DarculaTreeUI.class.getName());
    UIManager.put("OptionButtonUI", DarculaOptionButtonUI.class.getName());
    UIManager.put("InternalFrameUI", DarculaInternalFrameUI.class.getName());
    UIManager.put("RootPaneUI", DarculaRootPaneUI.class.getName());
    UIManager.put("grayFilter", new UIUtil.GrayFilter(80, -35, 100));
    UIManager.put("text.grayFilter", new UIUtil.GrayFilter(20, 0, 100));
  }

  @Override
  public void initComponent() {
    //    installMaterialComponents();

    // Patch UI components
    //    UIReplacer.patchUI();

    // Listen for changes on the settings
    connect = ApplicationManager.getApplication().getMessageBus().connect();
    connect.subscribe(UISettingsListener.TOPIC, this::onSettingsChanged);
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, this::onSettingsChanged);
    connect.subscribe(BeforeConfigNotifier.BEFORE_CONFIG_TOPIC, (this::onBeforeSettingsChanged));
  }

  private void onSettingsChanged(final UISettings uiSettings) {
    applyFilter();
  }

  @Override
  public void disposeComponent() {
    connect.disconnect();
  }

  @NotNull
  @Override
  public String getComponentName() {
    return getClass().getName();
  }

  /**
   * Called when MT Config settings are changeds
   *
   * @param mtConfig
   * @param form
   */
  private void onBeforeSettingsChanged(final MTConfig mtConfig, final MTForm form) {
    // Force restart if material design is switched
    restartIdeIfNecessary(mtConfig, form);
  }

  /**
   * Restart IDE if necessary (ex: material design components)
   *
   * @param mtConfig
   * @param form
   */
  private void restartIdeIfNecessary(final MTConfig mtConfig, final MTForm form) {
    // Restart the IDE if changed
    if (mtConfig.needsRestart(form)) {
      final String title = MaterialThemeBundle.message("mt.restartDialog.title");
      final String message = MaterialThemeBundle.message("mt.restartDialog.content");

      final int answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon());
      if (answer == Messages.YES) {
        willRestartIde = true;
      }
    }
  }

  /**
   * Called when MT Config settings are changeds
   *
   * @param mtConfig
   */
  private void onSettingsChanged(final MTConfig mtConfig) {
    // Trigger file icons and statuses update
    //    IconReplacer.applyFilter();
    MTThemeManager.getInstance().updateFileIcons();
    MTTreeUI.resetIcons();
    MTSelectedTreePainter.resetCache();

    if (willRestartIde) {
      MTUiUtils.restartIde();
    }
  }
}
