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

import com.chrisrm.idea.config.ui.MTForm;
import com.chrisrm.idea.listeners.ConfigNotifier;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.ui.MTTreeUI;
import com.chrisrm.idea.ui.indicators.MTSelectedTreeIndicatorImpl;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.UISettingsListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import static com.chrisrm.idea.icons.IconManager.applyFilter;

/**
 * Component for working on the Material Look And Feel
 */
public final class MTLafComponent implements BaseComponent {

  /**
   * Whether to restart the ide
   */
  private boolean willRestartIde;
  /**
   * Bus connect
   */
  private MessageBusConnection connect;

  /**
   * Listen for settings change to reload the theme and trigger restart if necessary
   */
  @Override
  public void initComponent() {
    // Listen for changes on the settings
    connect = ApplicationManager.getApplication().getMessageBus().connect();
    connect.subscribe(UISettingsListener.TOPIC, MTLafComponent::onUISettingsChanged);
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
  }

  /**
   * When UI settings change, reapply filter
   *
   * @param uiSettings of type UISettings
   */
  private static void onUISettingsChanged(@SuppressWarnings("unused") final UISettings uiSettings) {
    applyFilter();
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
      final String title = MaterialThemeBundle.message("mt.restartDialog.title");
      final String message = MaterialThemeBundle.message("mt.restartDialog.content");

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

    if (willRestartIde) {
      MTUiUtils.restartIde();
    }
  }
}
