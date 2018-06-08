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

package com.chrisrm.idea.icons.patchers;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.util.IconPathPatcher;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TintedIconsPatcher extends IconPathPatcher {
  private static final Map<String, String> CACHE = new HashMap<>();
  @NonNls
  private static final Map<String, String> REPLACEMENTS = new HashMap<>();

  static {
    REPLACEMENTS.put("/general/modified.png", "MTIcons.Modified");
  }

  private MTConfig instance;

  public static void clearCache() {
    CACHE.clear();
  }

  @Nullable
  @Override
  public String patchPath(final String path, final ClassLoader classLoader) {
    if (getInstance() == null || !getInstance().isUseMaterialIcons()) {
      return null;
    }

    if (CACHE.containsKey(path)) {
      return CACHE.get(path);
    }
    if (REPLACEMENTS.get(path) != null) {
      CACHE.put(path, REPLACEMENTS.get(path));
      return CACHE.get(path);
    }
    return null;
  }

  @Nullable
  @Override
  public ClassLoader getContextClassLoader(final String path, final ClassLoader originalClassLoader) {
    return getClass().getClassLoader();
  }

  public MTConfig getInstance() {
    if (instance == null) {
      instance = MTConfig.getInstance();
    }
    return instance;
  }
}
