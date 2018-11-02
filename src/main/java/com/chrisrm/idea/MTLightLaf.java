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

import com.chrisrm.idea.themes.MTThemeable;
import com.intellij.ide.ui.laf.IntelliJLaf;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Base class for Material Light look and feels
 *
 * @author helio
 * Created on 2018-10-29
 */
public class MTLightLaf extends IntelliJLaf implements MTLaf {

  /**
   * Field mtLafInstaller
   */
  private final MTLafInstaller mtLafInstaller;

  /**
   * Constructor MTLightLaf creates a new MTLightLaf instance.
   *
   * @param theme of type MTThemeable
   */
  public MTLightLaf(@NotNull final MTThemeable theme) {
    super();
    mtLafInstaller = new MTLafInstaller(this, theme);
  }

  /**
   * Install defaults for light themes
   *
   * @return the defaults (type UIDefaults) of this MTLightLaf object.
   */
  @Override
  public UIDefaults getDefaults() {
    final UIDefaults defaults = super.getDefaults();

    mtLafInstaller.installDefaults(defaults);
    // Install darcula defaults
    mtLafInstaller.installLightDefaults(defaults);
    // Install material defaults
    mtLafInstaller.installMTDefaults(defaults);

    return defaults;
  }

  /**
   * Description of this Look and feel
   *
   * @return the description (type String) of this MTLightLaf object.
   */
  @Override
  public String getDescription() {
    return "Light Material";
  }


  /**
   * Returns the prefix of this MTLightLaf object.
   *
   * @return the prefix (type String) of this MTLightLaf object.
   */
  @NotNull
  @Override
  protected String getPrefix() {
    return mtLafInstaller.getPrefix();
  }

  /**
   * Method loadDefaults ...
   *
   * @param defaults of type UIDefaults
   */
  @Override
  protected void loadDefaults(final UIDefaults defaults) {
    mtLafInstaller.loadDefaults(defaults);
  }
}
