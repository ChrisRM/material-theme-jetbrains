/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.lafs;

import com.intellij.ide.ui.UITheme;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * Look and Feel class for Dark Material Themes
 *
 * @author helio
 * Created on 2018-10-29
 */
@SuppressWarnings({"SerializableHasSerializationMethods",
  "MagicNumber",
  "StringConcatenation",
  "DuplicateStringLiteralInspection"})
public final class MTDarculaLaf extends DarculaLaf {

  /**
   * Install additional Darcula defaults
   *
   * @param defaults of type UIDefaults
   */
  @SuppressWarnings("DuplicateStringLiteralInspection")
  private static void installDarculaDefaults(@NonNls final UIDefaults defaults) {
    defaults.put("darcula.primary", new ColorUIResource(0x3c3f41));
    defaults.put("darcula.contrastColor", new ColorUIResource(0x262626));

    defaults.put("grayFilter", new UIUtil.GrayFilter(-100, -100, 100));
    defaults.put("text.grayFilter", new UIUtil.GrayFilter(-15, -10, 100));
  }

  /**
   * Installs and returns the defaults for dark lafs
   *
   * @return the defaults (type UIDefaults) of this MTDarkLaf object.
   */
  @Override
  public UIDefaults getDefaults() {
    final UIDefaults defaults = super.getDefaults();

    MTLafInstaller.installDefaults(defaults);
    // Install darcula defaults
    installDarculaDefaults(defaults);
    // Install material defaults
    MTLafInstaller.installMTDefaults(defaults);

    return defaults;
  }

  @Override
  protected void loadDefaults(final UIDefaults defaults) {
    try {
      try (@NonNls final InputStream stream = DarculaLaf.class.getResourceAsStream(getPrefix() + ".theme.json")) {
        assert stream != null;
        final UITheme theme = UITheme.loadFromJson(stream, "Darcula", getClass().getClassLoader(), Function.identity());
        theme.applyProperties(defaults);
      }
    } catch (final IOException e) {
      log(e);
    }
  }

}
