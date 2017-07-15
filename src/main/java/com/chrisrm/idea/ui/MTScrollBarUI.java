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
package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaScrollBarUI;
import com.intellij.openapi.util.SystemInfo;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;

import static com.intellij.util.ReflectionUtil.newInstance;

/**
 * @author Konstantin Bulenkov
 */
@Deprecated
public final class MTScrollBarUI extends DarculaScrollBarUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(final JComponent c) {
    try {
      return (ComponentUI) newInstance(Class.forName(SystemInfo.isMac
                                                     ? "com.intellij.ui.components.MacScrollBarUI"
                                                     : "com.intellij.ui.components.DefaultScrollBarUI"));
    } catch (Exception ignore) {
    }
    return new MTScrollBarUI();
  }
}
