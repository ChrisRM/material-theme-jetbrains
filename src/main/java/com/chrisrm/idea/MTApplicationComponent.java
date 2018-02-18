/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public final class MTApplicationComponent implements ApplicationComponent {
  private boolean updated;

  @Override
  public void initComponent() {
    updated = !MTUiUtils.getVersion().equals(MTConfig.getInstance().getVersion());
    if (updated) {
      MTConfig.getInstance().setVersion(MTUiUtils.getVersion());
    }
  }

  /** Component dispose method. */
  @Override
  public void disposeComponent() {
  }

  /**
   * Returns component's name.
   *
   * @return component's name
   */
  @NotNull
  @Override
  public String getComponentName() {
    return "MTApplicationComponent";
  }

  public static MTApplicationComponent getInstance() {
    return ApplicationManager.getApplication().getComponent(MTApplicationComponent.class);
  }

  public boolean isUpdated() {
    return updated;
  }

  public void setUpdateNotificationShown(final boolean updateNotificationShown) {
  }
}
