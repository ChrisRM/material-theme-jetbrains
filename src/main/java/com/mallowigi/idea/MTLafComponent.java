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

import com.intellij.ide.AppLifecycleListener;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.ide.ui.laf.IntelliJLookAndFeelInfo;
import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.config.ui.MTForm;
import com.mallowigi.idea.lafs.MTLafInstaller;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.listeners.CustomConfigNotifier;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.MTThemes;
import com.mallowigi.idea.ui.indicators.MTSelectedTreeIndicatorImpl;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Component for working on the Material Look And Feel
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
public final class MTLafComponent implements AppLifecycleListener {

  /**
   * Keep instance of the current LAF
   */
  private UIManager.LookAndFeelInfo activeLookAndFeel = null;
  /**
   * Whether to restart the ide
   */
  private boolean willRestartIde = false;

  @Override
  public void appFrameCreated(@NotNull final List<String> commandLineArgs) {
    initComponent();
  }

  private void lookAndFeelChanged(final LafManager source) {
    final UIManager.LookAndFeelInfo currentLookAndFeel = source.getCurrentLookAndFeel();
    patchTree();
    // Prevent infinite loop
    if (currentLookAndFeel == activeLookAndFeel) {
      return;
    }
    // Save instance of current laf
    activeLookAndFeel = currentLookAndFeel;

    activateLaf(currentLookAndFeel);

  }

  @SuppressWarnings({"FeatureEnvy",
    "ChainOfInstanceofChecks"})
  private void activateLaf(final UIManager.LookAndFeelInfo currentLookAndFeel) {
    final UIManager.LookAndFeelInfo oldLaf = LafManager.getInstance().getCurrentLookAndFeel();

    if (oldLaf instanceof UIThemeBasedLookAndFeelInfo) {
      ((UIThemeBasedLookAndFeelInfo) oldLaf).dispose();
    }

    if (currentLookAndFeel instanceof UIThemeBasedLookAndFeelInfo) {
      final UIThemeBasedLookAndFeelInfo lookAndFeel = (UIThemeBasedLookAndFeelInfo) currentLookAndFeel;
      MTThemeManager.activateLAF(lookAndFeel.getTheme());
    } else if (activeLookAndFeel instanceof DarculaLookAndFeelInfo) {
      MTThemeManager.activateLAF("darcula", true, "Darcula");
    } else if (activeLookAndFeel instanceof IntelliJLookAndFeelInfo) {
      MTThemeManager.activateLAF("default", false, "Light");
    }
  }

  /**
   * Listen for settings change to reload the theme and trigger restart if necessary
   */
  private void initComponent() {
    // Load bundled themes
    try {
      MTBundledThemesManager.getInstance().loadBundledThemes();
    } catch (final IOException e) {
      e.printStackTrace();
    }

    activeLookAndFeel = LafManager.getInstance().getCurrentLookAndFeel();

    // Activate the theme
    activateLaf(activeLookAndFeel);

    // Listen for changes on the settings
    final MessageBusConnection connect = ApplicationManager.getApplication().getMessageBus().connect();
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
    connect.subscribe(CustomConfigNotifier.CONFIG_TOPIC, mtCustomThemeConfig -> activateCustomTheme());
    connect.subscribe(LafManagerListener.TOPIC, this::lookAndFeelChanged);

    patchTree();
  }

  @SuppressWarnings({"NegativelyNamedBooleanVariable",
    "FeatureEnvy"})
  private static void activateCustomTheme() {
    final MTConfig mtConfig = MTConfig.getInstance();
    final boolean isNotCustom = !mtConfig.getSelectedTheme().isCustom();
    if (isNotCustom) {
      final int okCancelDialog = Messages.showOkCancelDialog(
        MaterialThemeBundle.message("MTThemes.activate.custom.theme"),
        MaterialThemeBundle.message("MTThemesComponent.activate.custom.theme"),
        MaterialThemeBundle.message("common.ok"),
        MaterialThemeBundle.message("common.cancel"),
        Messages.getQuestionIcon()
      );
      if (okCancelDialog == Messages.OK) {
        mtConfig.setSelectedTheme(MTThemes.CUSTOM);
      }
    }
    activateTheme(false);
  }

  @SuppressWarnings("WeakerAccess")
  static void activateTheme(final boolean withColorScheme) {
    MTThemeManager.activateWithColorScheme(withColorScheme);
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
   * Called before Material Settings are changed
   *
   * @param mtConfig of type MTConfig
   * @param form     of type MTForm
   */
  @SuppressWarnings("WeakerAccess")
  void onBeforeSettingsChanged(final MTBaseConfig<MTForm, MTConfig> mtConfig, final MTForm form) {
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
  private void restartIdeIfNecessary(final MTBaseConfig<MTForm, MTConfig> mtConfig, final MTForm form) {
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
    //    MTThemeManager.updateFileIcons();
    MTSelectedTreeIndicatorImpl.resetCache();

    activateTheme(true);

    ApplicationManager.getApplication().runWriteAction(UIReplacer::patchUI);

    if (willRestartIde) {
      MTUiUtils.restartIde();
    }
  }
}
