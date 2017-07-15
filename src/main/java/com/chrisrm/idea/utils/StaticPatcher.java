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

package com.chrisrm.idea.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class StaticPatcher {

  private StaticPatcher() {
  }

  public static void setFieldValue(final Object object, final String fieldName, final Object value) {
    try {
      final Field field = object.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(object, value);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public static void setFinalStatic(final Class cls, final String fieldName, final Object newValue) throws Exception {
    final Field[] fields = cls.getDeclaredFields();

    for (final Field field : fields) {
      if (field.getName().equals(fieldName)) {
        setFinalStatic(field, newValue);
        return;
      }
    }
  }

  public static void setFinalStatic(final Field field, final Object newValue) throws Exception {
    field.setAccessible(true);

    final Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

    field.set(null, newValue);

    modifiersField.setInt(field, field.getModifiers() | Modifier.FINAL);
    modifiersField.setAccessible(false);

    field.setAccessible(false);
  }

  public static boolean isClass(final String className) {
    try {
      Class.forName(className);
      return true;
    } catch (final ClassNotFoundException e) {
      return false;
    }
  }
}
