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

package com.chrisrm.idea.lafs;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.themes.models.MTThemeable;
import com.intellij.ide.ui.laf.IntelliJLaf;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.xmlb.annotations.Transient;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

/**
 * Look and Feel class for Material Light themes
 *
 * @author helio
 * Created on 2018-10-29
 */
@SuppressWarnings("SerializableHasSerializationMethods")
public final class MTLightLaf extends IntelliJLaf implements MTLaf {

  /**
   * Service to install properties in UIManager
   */
  @Transient
  private final MTLafInstaller mtLafInstaller;

  /**
   * Represents a Material Light look and feel
   *
   * @param theme of type MTThemeable
   */
  public MTLightLaf(@NotNull final MTThemeable theme) {
    mtLafInstaller = new MTLafInstaller(theme);
  }

  /**
   * Install additional light theme defaults
   *
   * @param defaults of type UIDefaults
   */
  @SuppressWarnings("DuplicateStringLiteralInspection")
  private static void installLightDefaults(@NonNls final UIDefaults defaults) {
    defaults.put("intellijlaf.primary", new ColorUIResource(0xe8e8e8));
    defaults.put("intellijlaf.contrastColor", new ColorUIResource(0xEEEEEE));

    defaults.put("grayFilter", new UIUtil.GrayFilter(80, -35, 100));
    defaults.put("text.grayFilter", new UIUtil.GrayFilter(20, 0, 100));
  }

  /**
   * Install and returns the defaults for light lafs
   *
   * @return the defaults (type UIDefaults) of this MTLightLaf object.
   */
  @Override
  public UIDefaults getDefaults() {
    final UIDefaults defaults = super.getDefaults();

    mtLafInstaller.installDefaults(defaults);
    // Install darcula defaults
    installLightDefaults(defaults);
    // Install material defaults
    mtLafInstaller.installMTDefaults(defaults);

    return defaults;
  }

  @Override
  public String getDescription() {
    return MaterialThemeBundle.message("light.material");
  }


  @NotNull
  @Override
  public String getPrefix() {
    return mtLafInstaller.getPrefix();
  }

  @Override
  public void loadDefaults(final UIDefaults defaults) {
    MTLafInstaller.loadDefaults(defaults);
  }
}
