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

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class NameUtil {
  private NameUtil() {
  }

  private static final Function<String, String> LOWERCASE_MAPPING = String::toLowerCase;
  private static final int MAX_LENGTH = 40;

  @NotNull
  public static String[] nameToWords(@NotNull final String name) {
    final List<String> array = new ArrayList<>();
    int index = 0;

    while (index < name.length()) {
      final int wordStart = index;
      int upperCaseCount = 0;
      int lowerCaseCount = 0;
      int digitCount = 0;
      int specialCount = 0;
      while (index < name.length()) {
        final char c = name.charAt(index);
        if (Character.isDigit(c)) {
          if (upperCaseCount > 0 || lowerCaseCount > 0 || specialCount > 0) {
            break;
          }
          digitCount++;
        } else if (Character.isUpperCase(c)) {
          if (lowerCaseCount > 0 || digitCount > 0 || specialCount > 0) {
            break;
          }
          upperCaseCount++;
        } else if (Character.isLowerCase(c)) {
          if (digitCount > 0 || specialCount > 0) {
            break;
          }
          if (upperCaseCount > 1) {
            index--;
            break;
          }
          lowerCaseCount++;
        } else {
          if (upperCaseCount > 0 || lowerCaseCount > 0 || digitCount > 0) {
            break;
          }
          specialCount++;
        }
        index++;
      }
      final String word = name.substring(wordStart, index);
      if (!StringUtil.isEmptyOrSpaces(word)) {
        array.add(word);
      }
    }
    return ArrayUtil.toStringArray(array);
  }

  @NotNull
  public static String splitWords(@NotNull final String text, final char separator, @NotNull final Function<String, String> transformWord) {
    final String[] words = nameToWords(text);
    boolean insertSeparator = false;
    final StringBuilder buf = new StringBuilder();
    for (final String word : words) {
      // { "common", "/__", "init", "__.", "py" }
      // if the character is not a java part nor a []()., replace with separator
      final boolean isSpecialChar = "[(.)/]".contains(word.substring(0, 1));
      if (!Character.isLetterOrDigit(word.charAt(0)) && !isSpecialChar) {
        buf.append(separator);
        insertSeparator = false;
        continue;
      }

      // if insert separator and word length > 1, insert separator
      if (insertSeparator && word.length() > 1) {
        buf.append(separator);
      } else {
        insertSeparator = !isSpecialChar && word.length() > 1;
        // set up insertseparator if word length > 1
      }
      buf.append(transformWord.fun(word.replace("_", "")));
    }
    return buf.toString();

  }
}
