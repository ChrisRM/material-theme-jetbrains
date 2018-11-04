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

package com.chrisrm.idea.messages;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

/**
 * Messages Bundle for Material Theme
 */
@NonNls
public final class FileColorsBundle {

  @NonNls
  public static final String PATH_TO_BUNDLE = "messages.FileColorsBundle";

  @NotNull
  private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(PATH_TO_BUNDLE);

  /**
   * Prevent instantiation
   */
  private FileColorsBundle() {

  }

  /**
   * Get a message from the resource bundle
   *
   * @param key
   * @param params
   * @return the message
   */
  public static String message(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) final String key,
                               @NotNull final Object... params) {
    return CommonBundle.message(BUNDLE, key, params);
  }

  /**
   * Get a message from the resource bundle or return a default message
   *
   * @param key
   * @param defaultValue
   * @param params
   * @return the message or default
   */
  public static String messageOrDefault(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) final String key,
                                        final String defaultValue,
                                        final Object... params) {
    return CommonBundle.messageOrDefault(BUNDLE, key, defaultValue, params);
  }
}
