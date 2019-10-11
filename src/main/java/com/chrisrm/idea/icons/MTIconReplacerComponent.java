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

package com.chrisrm.idea.icons;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.icons.patchers.IconPathPatchers;
import com.chrisrm.idea.icons.patchers.MTIconPatcher;
import com.chrisrm.idea.listeners.ConfigNotifier;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.fileTypes.FileTypeEvent;
import com.intellij.openapi.fileTypes.FileTypeListener;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.IconPathPatcher;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.xmlb.annotations.Property;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"OverlyCoupledClass",
    "FeatureEnvy"})
public final class MTIconReplacerComponent implements BaseComponent {
  @Property
  private final IconPathPatchers iconPathPatchers = IconPatchersFactory.create();

  private final Set<IconPathPatcher> installedPatchers = new HashSet<>();

  private MessageBusConnection connect;

  @Override
  public void initComponent() {
    updateIcons();
    connect = ApplicationManager.getApplication().getMessageBus().connect();

    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        updateIcons();
      }
    });
    connect.subscribe(FileTypeManager.TOPIC, new FileTypeListener() {
      @Override
      public void fileTypesChanged(@NotNull final FileTypeEvent event) {
        updateIcons();
      }
    });
  }

  @SuppressWarnings("WeakerAccess")
  void updateIcons() {
    MTIconPatcher.clearCache();
    removePathPatchers();

    //    if (MTConfig.getInstance().isUseMaterialIcons()) {
    //      installPathPatchers();
    //    }
    //    if (MTConfig.getInstance().isPsiIcons()) {
    //      installPSIPatchers();
    //    }
    //    if (MTConfig.getInstance().isFileIcons()) {
    //      installFileIconsPatchers();
    //    }
  }

  @SuppressWarnings("OverlyCoupledMethod")
  private void installPathPatchers() {
    for (final IconPathPatcher externalPatcher : iconPathPatchers.getIconPatchers()) {
      installPathPatcher(externalPatcher);
    }
  }

  @SuppressWarnings("OverlyCoupledMethod")
  private void installPSIPatchers() {
    for (final IconPathPatcher externalPatcher : iconPathPatchers.getGlyphPatchers()) {
      installPathPatcher(externalPatcher);
    }
  }

  private void installFileIconsPatchers() {
    for (final IconPathPatcher externalPatcher : iconPathPatchers.getFilePatchers()) {
      installPathPatcher(externalPatcher);
    }
  }

  private void removePathPatchers() {
    for (final IconPathPatcher iconPathPatcher : installedPatchers) {
      removePathPatcher(iconPathPatcher);
    }
    installedPatchers.clear();
  }

  private void installPathPatcher(final IconPathPatcher patcher) {
    installedPatchers.add(patcher);
    IconLoader.installPathPatcher(patcher);
  }

  private static void removePathPatcher(final IconPathPatcher patcher) {
    IconLoader.removePathPatcher(patcher);
  }

  @Override
  public void disposeComponent() {
    MTIconPatcher.clearCache();
    connect.disconnect();
  }

  @Override
  @NotNull
  public String getComponentName() {
    return "com.chrisrm.idea.icons.MTIconReplacerComponent";
  }
}
