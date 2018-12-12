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
import com.chrisrm.idea.listeners.ConfigNotifier;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.IconPathPatcher;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("OverlyCoupledClass")
public final class MTIconReplacerComponent implements BaseComponent {
  private final Set<IconPathPatcher> CACHE = ContainerUtil.newHashSet();
  private MessageBusConnection connect;

  @Override
  public void initComponent() {
    installPathPatchers();
    connect = ApplicationManager.getApplication().getMessageBus().connect();

    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        updateIcons();
      }
    });
  }

  private void updateIcons() {
    if (MTConfig.getInstance().isUseMaterialIcons()) {
      installPathPatchers();
    } else {
      removePathPatchers();
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
    installPathPatcher(new CythonIconsPatcher());
    installPathPatcher(new MakoIconsPatcher());
    installPathPatcher(new JinjaIconsPatcher());
    installPathPatcher(new FlaskIconsPatcher());
    installPathPatcher(new DjangoIconsPatcher());
    installPathPatcher(new ChameleonIconsPatcher());

    installPathPatcher(new RubyIconsPatcher());

    installPathPatcher(new GolandIconsPatcher());
    installPathPatcher(new DataGripIconsPatcher());
    installPathPatcher(new CLionIconsPatcher());
    installPathPatcher(new AppCodeIconsPatcher());
    installPathPatcher(new RestClientIconsPatcher());

    installPathPatcher(new RiderIconsPatcher());
    installPathPatcher(new ResharperIconsPatcher());
  }

  private void removePathPatchers() {
    for (final IconPathPatcher iconPathPatcher : CACHE) {
      removePathPatcher(iconPathPatcher);
    }
    CACHE.clear();
  }

  private void installPathPatcher(final IconPathPatcher patcher) {
    CACHE.add(patcher);
    IconLoader.installPathPatcher(patcher);
  }

  private void removePathPatcher(final IconPathPatcher patcher) {
    //    CACHE.add(patcher);
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
