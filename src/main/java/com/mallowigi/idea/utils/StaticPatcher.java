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

package com.mallowigi.idea.utils;

import org.jetbrains.annotations.NonNls;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Super hacking class to change static fields!
 */
public enum StaticPatcher {
  DEFAULT;

  /**
   * Rewrites a class's static field with a new value by static field name.
   * <p>
   * Note that private fields will have their names changed at compilation.
   *
   * @param cls       the class
   * @param fieldName the name of the static field
   * @param newValue  the new value
   */
  @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
  public static void setFinalStatic(final Class cls, @NonNls final String fieldName, final Object newValue)
    throws NoSuchFieldException, IllegalAccessException {
    final Field[] fields = cls.getDeclaredFields();

    for (final Field field : fields) {
      if (field.getName().equals(fieldName)) {
        setFinalStatic(field, newValue);
        return;
      }
    }
  }

  /**
   * Rewrites a class's static field with a new value by field
   *
   * @param field    the Field to change
   * @param newValue the new value
   */
  public static void setFinalStatic(final Field field, final Object newValue) throws NoSuchFieldException, IllegalAccessException {
    field.setAccessible(true);

    final Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

    field.set(null, newValue);

    modifiersField.setInt(field, field.getModifiers() | Modifier.FINAL);
    modifiersField.setAccessible(false);

    field.setAccessible(false);
  }

}
