package com.chrisrm.idea.utils;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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

  public static void writeOriginalTitleColor(final int backgroundColor) {
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
