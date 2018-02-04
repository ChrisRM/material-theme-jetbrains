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

package com.chrisrm.idea.utils;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

import java.awt.*;

import static com.sun.jna.platform.win32.WinReg.HKEY_CURRENT_USER;

public class WinRegistry {

  public static final String DWM_PATH = "Software\\Microsoft\\Windows\\DWM";
  public static final String KEY = "AccentColor";
  public static final String ORIG_KEY = "OrigAccentColor";

  public static void writeStringValue(final WinReg.HKEY hkey, final String path, final String key, final int value) {
    Advapi32Util.registrySetIntValue(hkey, path, key, value);
  }

  public static int getStringValue(final WinReg.HKEY hkey, final String path, final String key) {
    return Advapi32Util.registryGetIntValue(hkey, path, key);
  }

  public static void writeOriginalTitleColor() {
    final int backgroundColor = getTitleColor();
    WinRegistry.writeStringValue(HKEY_CURRENT_USER, WinRegistry.DWM_PATH, WinRegistry.ORIG_KEY, backgroundColor);
  }

  public static void writeTitleColor(final Color backgroundColor) {
    WinRegistry.writeStringValue(HKEY_CURRENT_USER, WinRegistry.DWM_PATH, WinRegistry.KEY, MTUiUtils.colorToDword(backgroundColor));
  }

  public static void writeTitleColor(final int backgroundColorDword) {
    WinRegistry.writeStringValue(HKEY_CURRENT_USER, WinRegistry.DWM_PATH, WinRegistry.KEY, backgroundColorDword);
  }

  public static int getTitleColor() {
    return WinRegistry.getStringValue(HKEY_CURRENT_USER, WinRegistry.DWM_PATH, WinRegistry.KEY);
  }

  public static int getOriginalTitleColor() {
    return WinRegistry.getStringValue(HKEY_CURRENT_USER, WinRegistry.DWM_PATH, WinRegistry.ORIG_KEY);
  }
}
