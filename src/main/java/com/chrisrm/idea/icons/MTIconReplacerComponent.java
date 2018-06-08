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
import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;

public final class MTIconReplacerComponent implements ApplicationComponent {

  @Override
  public void initComponent() {
    if (MTConfig.getInstance().isUseMaterialIcons()) {
      // this is needed for tintedicons
      IconReplacer.replaceIcons(AllIcons.class, "/icons", "");
      IconReplacer.replaceIcons(PlatformIcons.class, "", "");

      IconLoader.installPathPatcher(new AllIconsPatcher());
      IconLoader.installPathPatcher(new ImagesIconsPatcher());
      IconLoader.installPathPatcher(new VCSIconsPatcher());
      IconLoader.installPathPatcher(new GradleIconsPatcher());
      IconLoader.installPathPatcher(new TasksIconsPatcher());
      IconLoader.installPathPatcher(new MavenIconsPatcher());
      IconLoader.installPathPatcher(new TerminalIconsPatcher());
      IconLoader.installPathPatcher(new BuildToolsIconsPatcher());
      IconLoader.installPathPatcher(new RemoteServersIconsPatcher());

    }
  }

  @Override
  public void disposeComponent() {

  }

  @Override
  @NotNull
  public String getComponentName() {
    return "com.chrisrm.idea.icons.MTIconReplacerComponent";
  }
}
