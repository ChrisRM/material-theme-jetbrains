/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.config.ui.MTForm;
import com.mallowigi.idea.lafs.MTLafInstaller;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.ui.MTTreeUI;
import com.mallowigi.idea.ui.indicators.MTSelectedTreeIndicatorImpl;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Component for working on the Material Look And Feel
 */
public final class MTLafComponent implements BaseComponent {

  /**
   * Keep instance of the current LAF
   */
  private UIManager.LookAndFeelInfo activeLookAndFeel;
  /**
   * Whether to restart the ide
   */
  private boolean willRestartIde;
  /**
   * Bus connect
   */
  private MessageBusConnection connect;

  private void lookAndFeelChanged(final LafManager source) {
    final UIManager.LookAndFeelInfo currentLookAndFeel = source.getCurrentLookAndFeel();
    patchTree();
    // Prevent infinite loop
    if (currentLookAndFeel == activeLookAndFeel) {
      return;
    }
    // Save instance of current laf
    activeLookAndFeel = currentLookAndFeel;

    if (currentLookAndFeel instanceof UIThemeBasedLookAndFeelInfo) {
      final UIThemeBasedLookAndFeelInfo lookAndFeel = (UIThemeBasedLookAndFeelInfo) currentLookAndFeel;
      MTThemeManager.activate(lookAndFeel.getTheme().getId());
    }

  }

  /**
   * Listen for settings change to reload the theme and trigger restart if necessary
   */
  @Override
  public void initComponent() {
    activeLookAndFeel = LafManager.getInstance().getCurrentLookAndFeel();

    // Listen for changes on the settings
    connect = ApplicationManager.getApplication().getMessageBus().connect();
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        onSettingsChanged();
      }

      @Override
      public void beforeConfigChanged(final MTConfig mtConfig, final MTForm form) {
        onBeforeSettingsChanged(mtConfig, form);
      }
    });
    connect.subscribe(LafManagerListener.TOPIC, this::lookAndFeelChanged);

    patchTree();
  }

  private static void patchTree() {
    ApplicationManager.getApplication().invokeLater(() -> { // don't do heavy operations right away
      MTLafInstaller.replaceTree(UIManager.getLookAndFeelDefaults());

      if (UIUtil.isUnderDarcula()) {
        DarculaInstaller.uninstall();
        DarculaInstaller.install();
      } else {
        DarculaInstaller.uninstall();
      }
    });
  }

  /**
   * Method disposeComponent ...
   */
  @Override
  public void disposeComponent() {
    connect.disconnect();
  }

  /**
   * Method getComponentName returns the componentName of this MTLafComponent object.
   *
   * @return the componentName (type String) of this MTLafComponent object.
   */
  @NotNull
  @Override
  public String getComponentName() {
    return getClass().getName();
  }

  /**
   * Called before Material Settings are changed
   *
   * @param mtConfig of type MTConfig
   * @param form     of type MTForm
   */
  @SuppressWarnings("WeakerAccess")
  void onBeforeSettingsChanged(final MTConfig mtConfig, final MTForm form) {
    // Force restart if material design is switched
    restartIdeIfNecessary(mtConfig, form);
  }

  /**
   * Restart IDE if necessary (ex: material design components)
   *
   * @param mtConfig of type MTConfig
   * @param form     of type MTForm
   */
  @SuppressWarnings("Duplicates")
  private void restartIdeIfNecessary(final MTConfig mtConfig, final MTForm form) {
    // Restart the IDE if changed
    if (mtConfig.needsRestart(form)) {
      final String title = MaterialThemeBundle.message("MTForm.restartDialog.title");
      final String message = MaterialThemeBundle.message("MTForm.restartDialog.content");

      final int answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon());
      if (answer == Messages.YES) {
        willRestartIde = true;
      }
    }
  }

  /**
   * Called when Material Theme settings are changed
   */
  @SuppressWarnings("WeakerAccess")
  void onSettingsChanged() {
    // Trigger file icons and statuses update
    //    IconReplacer.applyFilter();
    MTThemeManager.updateFileIcons();
    MTTreeUI.resetIcons();
    MTSelectedTreeIndicatorImpl.resetCache();

    ApplicationManager.getApplication().runWriteAction(UIReplacer::patchUI);

    if (willRestartIde) {
      MTUiUtils.restartIde();
    }
  }
}
