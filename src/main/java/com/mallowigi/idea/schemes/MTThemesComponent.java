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

package com.mallowigi.idea.schemes;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.MessageBusConnection;
import com.mallowigi.idea.MTBundledThemesManager;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.listeners.CustomConfigNotifier;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.MTThemes;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Component for switching Material Themes
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
public final class MTThemesComponent implements BaseComponent {

  private MessageBusConnection connect = null;

  @Override
  public void initComponent() {
    try {
      MTBundledThemesManager.getInstance().loadBundledThemes();
    } catch (final IOException e) {
      e.printStackTrace();
    }

    // Activate the theme
    activateTheme(false);

    connect = ApplicationManager.getApplication().getMessageBus().connect();

    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new MyConfigNotifier());

    connect.subscribe(CustomConfigNotifier.CONFIG_TOPIC, mtCustomThemeConfig -> activateCustomTheme());
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

  @Override
  public void disposeComponent() {
    connect.disconnect();
  }

  @NonNls
  @Override
  @NotNull
  public String getComponentName() {
    return "MTThemesComponent";
  }

  private static class MyConfigNotifier implements ConfigNotifier {
    MyConfigNotifier() {
    }

    @Override
    public final void configChanged(final MTConfig mtConfig) {
      activateTheme(true);
    }
  }
}
