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

package com.chrisrm.idea.config.scope;

import com.chrisrm.idea.themes.MTDarkerTheme;
import com.chrisrm.idea.themes.MTLighterTheme;
import com.chrisrm.idea.themes.MTOceanicTheme;
import com.chrisrm.idea.themes.MTPalenightTheme;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.FileColorManager;

/**
 * Component for importing Material Theme custom scopes.
 * <p>
 * Check in "Appearance > File Colors" and set your preferred Scope at the top.
 */
public final class MTScopeComponent extends AbstractProjectComponent implements ProjectComponent {

  protected MTScopeComponent(final Project project) {
    super(project);
  }

  @Override
  public void initComponent() {
    addDisabledFileColors();
  }

  /**
   * At start, check if Material Theme File Colors are added, otherwise add them
   * Note: If the scope has changed somehow (by changing the name or the color), it will add a duplicate.
   */
  private void addDisabledFileColors() {
    final FileColorManager manager = FileColorManager.getInstance(myProject);
    manager.addScopeColor(MTOceanicNonProjectScope.NAME, MTOceanicTheme.DISABLED, false);
    manager.addScopeColor(MTDarkerNonProjectScope.NAME, MTDarkerTheme.DISABLED, false);
    manager.addScopeColor(MTLighterNonProjectScope.NAME, MTLighterTheme.DISABLED, false);
    manager.addScopeColor(MTPalenightNonProjectScope.NAME, MTPalenightTheme.DISABLED, false);
  }

}
