/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.wallpaper;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.ide.AppLifecycleListener;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

public final class MTWallpaperComponent implements ApplicationComponent {

  public static final String FRAME_PROP = IdeBackgroundUtil.FRAME_PROP;
  public static final String MT_PROP = FRAME_PROP + ".mt";
  public static final String DEFAULT_PROP = FRAME_PROP + ".default";
  private final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
  private MTConfig mtConfig;

  @Override
  public void initComponent() {
    mtConfig = MTConfig.getInstance();

    /*
     We need to handle the case where the user set a custom background prior to installing the plugin.
     In this case, we save his current value inside the configuration file and we modify another value throughout the plugin lifetime.
     If he deletes the plugin, the configuration file will still be there, however if he deletes the value everything will be alright.
     */
    mtConfig.setDefaultBackground(getIdeBackground());
    propertiesComponent.setValue(DEFAULT_PROP, mtConfig.getDefaultBackground());

    // Loads the component
    this.reloadWallpaper();

    // Subscribe for events
    final MessageBusConnection connect = ApplicationManager.getApplication().getMessageBus().connect();
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> this.reloadWallpaper());
    connect.subscribe(AppLifecycleListener.TOPIC, new AppLifecycleListener() {
      /**
       * Restore original background at evey close
       *
       * @param isRestart
       */
      @Override
      public void appWillBeClosed(final boolean isRestart) {
        updateDefaultBackground();
        final String defaultBackground = mtConfig.getDefaultBackground();

        if (defaultBackground == null) {
          propertiesComponent.unsetValue(FRAME_PROP);
        } else {
          propertiesComponent.unsetValue(FRAME_PROP);
          propertiesComponent.setValue(FRAME_PROP, defaultBackground);
        }
      }
    });
  }

  /**
   * Get the value of the background set while Custom Wallpaper option is on
   *
   * @return
   */
  private String getMTBackground() {
    return propertiesComponent.getValue(MT_PROP);
  }

  /**
   * Get the value of the background set when changed from the IDE
   *
   * @return
   */
  private String getOriginalDefaultBackground() {
    return propertiesComponent.getValue(DEFAULT_PROP);
  }

  /**
   * Get the current value of the IDE background
   *
   * @return
   */
  @NotNull
  private String getIdeBackground() {
    return ObjectUtils.notNull(propertiesComponent.getValue(FRAME_PROP), "");
  }

  /**
   * Before doing anything, update the default background stored in case the user changed it with Set Background Image
   */
  private void updateDefaultBackground() {
    // if the value has changed since the beginning (e.g. user has done "set background image"
    if (!getIdeBackground().equals(getOriginalDefaultBackground())) {
      // But if the value of FRAME is equals to the value of MT (e.g. changed when the checkbox is on), dont update bg
      if (getIdeBackground().equals(getMTBackground())) {
        return;
      }
      // update OCEANIC PROP and defaultBackground
      propertiesComponent.setValue(DEFAULT_PROP, getIdeBackground());
      mtConfig.setDefaultBackground(getOriginalDefaultBackground());
    }
  }

  /**
   * Called when the application loads or when the settings are saved
   */
  private void reloadWallpaper() {
    final String wallpaper = mtConfig.getWallpaper();
    // if the value has changed since the beginning (e.g. user has done "set background image"
    updateDefaultBackground();

    final String defaultBackground = mtConfig.getDefaultBackground();

    // If the wallpaper option is set, change the wallpaper
    if (mtConfig.isWallpaperSet()) {
      // If no value, remove the wallpaper
      if (wallpaper == null) {
        propertiesComponent.unsetValue(FRAME_PROP);
      } else {
        propertiesComponent.unsetValue(FRAME_PROP);
        propertiesComponent.setValue(FRAME_PROP, wallpaper);
      }
      // Keep it in MT PROP
      propertiesComponent.setValue(MT_PROP, mtConfig.getWallpaper());
    } else {
      // Restore the original value
      if (defaultBackground == null) {
        propertiesComponent.unsetValue(FRAME_PROP);
      } else {
        propertiesComponent.unsetValue(FRAME_PROP);
        propertiesComponent.setValue(FRAME_PROP, defaultBackground);
      }
      // Remove MT PROP
      propertiesComponent.unsetValue(MT_PROP);
    }

    IdeBackgroundUtil.repaintAllWindows();
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTWallpaperComponent";
  }
}
