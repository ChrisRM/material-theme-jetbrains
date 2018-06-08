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

import com.intellij.openapi.util.IconPathPatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class MTIconPatcher extends IconPathPatcher {
  private static final Map<String, String> CACHE = new HashMap<>();

  @NotNull
  public abstract String getPathToAppend();

  @NotNull
  public abstract String getPathToRemove();

  @Nullable
  @Override
  public ClassLoader getContextClassLoader(final String path, final ClassLoader originalClassLoader) {
    return getClass().getClassLoader();
  }

  @Nullable
  @Override
  public String patchPath(final String path, final ClassLoader classLoader) {
    if (CACHE.containsKey(path)) {
      return CACHE.get(path);
    }
    // First try the svg version of the resource
    if (getSVG(path) != null) {
      CACHE.put(path, getReplacement(path));
      return CACHE.get(path);
    }
    // Then try the png version
    if (getPNG(path) != null) {
      CACHE.put(path, getReplacement(path));
      return CACHE.get(path);
    }
    return null;
  }

  /**
   * Check whether a svg version of a resource exists
   *
   * @param path
   * @return
   */
  public URL getSVG(final String path) {
    final String svgFile = getReplacement(path).replace(".png", ".svg");
    return getClass().getResource(svgFile);
  }

  /**
   * Check whether a png version of a resource exists
   *
   * @param path
   * @return
   */
  public URL getPNG(final String path) {
    final String replacement = getReplacement(path).replace(".svg", ".png");
    return getClass().getResource(replacement);
  }

  @NotNull
  public String getReplacement(final String path) {
    String finalPath = path;
    if (path.contains(".gif")) {
      finalPath = path.replace(".gif", ".png");
    }
    return getPathToAppend() + finalPath.replace(getPathToRemove(), "");
  }
}
