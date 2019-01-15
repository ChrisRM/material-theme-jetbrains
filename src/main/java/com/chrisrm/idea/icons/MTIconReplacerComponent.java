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

package com.chrisrm.idea.icons;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.icons.patchers.*;
import com.chrisrm.idea.icons.patchers.glyphs.*;
import com.chrisrm.idea.listeners.ConfigNotifier;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.fileTypes.FileTypeEvent;
import com.intellij.openapi.fileTypes.FileTypeListener;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.IconPathPatcher;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings({"OverlyCoupledClass",
    "FeatureEnvy"})
public final class MTIconReplacerComponent implements BaseComponent {
  private final Set<IconPathPatcher> installedPatchers = ContainerUtil.newHashSet();

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

    if (MTConfig.getInstance().isUseMaterialIcons()) {
      installPathPatchers();
    }
    if (MTConfig.getInstance().isPsiIcons()) {
      installPSIPatchers();
    }
    if (MTConfig.getInstance().isFileIcons()) {
      installFileIconsPatchers();
    }
  }

  @SuppressWarnings("OverlyCoupledMethod")
  private void installPathPatchers() {
    installPathPatcher(new LogPatcher());

    installPathPatcher(new AllIconsPatcher());
    installPathPatcher(new ImagesIconsPatcher());
    installPathPatcher(new VCSIconsPatcher());
    installPathPatcher(new GradleIconsPatcher());
    installPathPatcher(new TasksIconsPatcher());
    installPathPatcher(new MavenIconsPatcher());
    installPathPatcher(new TerminalIconsPatcher());
    installPathPatcher(new BuildToolsIconsPatcher());
    installPathPatcher(new RemoteServersIconsPatcher());
    installPathPatcher(new DatabaseToolsIconsPatcher());

    installPathPatcher(new PHPIconsPatcher());
    installPathPatcher(new PythonIconsPatcher());
    installPathPatcher(new AppEngineIconsPatcher());
    installPathPatcher(new CythonIconsPatcher());
    installPathPatcher(new MakoIconsPatcher());
    installPathPatcher(new JinjaIconsPatcher());
    installPathPatcher(new FlaskIconsPatcher());
    installPathPatcher(new DjangoIconsPatcher());
    installPathPatcher(new ChameleonIconsPatcher());
    installPathPatcher(new PyQtIconsPatcher());
    installPathPatcher(new Web2PythonIconsPatcher());

    installPathPatcher(new RubyIconsPatcher());

    installPathPatcher(new GolandIconsPatcher());
    installPathPatcher(new DataGripIconsPatcher());
    installPathPatcher(new CLionIconsPatcher());
    installPathPatcher(new AppCodeIconsPatcher());
    installPathPatcher(new RestClientIconsPatcher());

    installPathPatcher(new RiderIconsPatcher());
    installPathPatcher(new ResharperIconsPatcher());
  }

  @SuppressWarnings("OverlyCoupledMethod")
  private void installPSIPatchers() {
    installPathPatcher(new GlyphsPatcher());
    installPathPatcher(new ActionsGlyphsPatcher());
    installPathPatcher(new GeneralGlyphsPatcher());
    installPathPatcher(new GutterGlyphsPatcher());

    installPathPatcher(new PHPGlyphsPatcher());
    installPathPatcher(new PythonGlyphsPatcher());
    installPathPatcher(new RubyGlyphsPatcher());
    installPathPatcher(new DataGripGlyphsPatcher());
    installPathPatcher(new AppCodeGlyphsPatcher());
    installPathPatcher(new GolandGlyphsPatcher());
    installPathPatcher(new CLionGlyphsPatcher());
  }

  private void installFileIconsPatchers() {
    installPathPatcher(new PHPFileIconsPatcher());
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
