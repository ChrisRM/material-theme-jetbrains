/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("AbstractClassWithOnlyOneDirectInheritor")
public abstract class MTIconPatcher extends IconPathPatcher {
  private static final Map<String, String> CACHE = new HashMap<>(100);
  private static final Pattern PNG = Pattern.compile(".png", Pattern.LITERAL);
  private static final Pattern SVG = Pattern.compile(".svg", Pattern.LITERAL);
  private static final Pattern GIF = Pattern.compile(".gif", Pattern.LITERAL);

  private MTConfig instance = MTConfig.getInstance();

  /**
   * @return The string to append to the final path
   */
  @NonNls
  @NotNull
  protected abstract String getPathToAppend();

  /**
   * @return The string to remove from the original path
   */
  @NonNls
  @NotNull
  protected abstract String getPathToRemove();

  @Nullable
  @Override
  public final ClassLoader getContextClassLoader(@NotNull final String path, final ClassLoader originalClassLoader) {
    return getClass().getClassLoader();
  }

  public static void clearCache() {
    CACHE.clear();
  }

  /**
   * Check whether a svg version of a resource exists
   */
  private URL getSVG(final String path) {
    final String svgFile = PNG.matcher(getReplacement(path)).replaceAll(Matcher.quoteReplacement(".svg")); // NON-NLS
    return getClass().getResource(svgFile);
  }

  /**
   * Check whether a png version of a resource exists
   */
  private URL getPNG(final String path) {
    final String replacement = SVG.matcher(getReplacement(path)).replaceAll(Matcher.quoteReplacement(".png")); // NON-NLS
    return getClass().getResource(replacement);
  }

  @NonNls
  @NotNull
  private String getReplacement(final String path) {
    String finalPath = path;
    if (path.contains(".gif")) { // NON-NLS
      finalPath = GIF.matcher(path).replaceAll(Matcher.quoteReplacement(".png")); // NON-NLS
    }
    return getPathToAppend() + finalPath.replace(getPathToRemove(), "");
  }

  @SuppressWarnings("MethodWithMultipleReturnPoints")
  @Nullable
  @Override
  public final String patchPath(final String path, final ClassLoader classLoader) {
    if (getInstance() == null || !getInstance().isUseMaterialIcons()) {
      return null;
    }

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

  private MTConfig getInstance() {
    if (instance == null) {
      instance = MTConfig.getInstance();
    }
    return instance;
  }
}
