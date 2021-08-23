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

package com.mallowigi.idea.messages;

import com.intellij.AbstractBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

/**
 * Messages Bundle for Material Theme
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
public final class MaterialThemeBundle extends AbstractBundle {
  @NonNls
  public static final String BUNDLE = "messages.MaterialThemeBundle";
  public static final MaterialThemeBundle INSTANCE = new MaterialThemeBundle();

  private MaterialThemeBundle() {
    super(BUNDLE);
  }

  public static String message(@NonNls @NotNull @PropertyKey(resourceBundle = BUNDLE) final String key, @NotNull final Object... params) {
    return INSTANCE.getMessage(key, params);
  }

  @Override
  public String messageOrDefault(@NotNull @PropertyKey(resourceBundle = BUNDLE) final String key,
                                 @Nullable final String defaultValue,
                                 @NotNull final Object... params) {
    return AbstractBundle.messageOrDefault(ResourceBundle.getBundle(BUNDLE), key, defaultValue, params);
  }

}
