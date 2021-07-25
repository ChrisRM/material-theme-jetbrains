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

package com.mallowigi.idea.tabs;

import com.intellij.openapi.fileEditor.UniqueVFilePathBuilder;
import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Function;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.config.project.MTProjectConfig;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"BreakStatement",
  "ContinueStatement"})
public final class MTEditorUpperTabs implements EditorTabTitleProvider {

  private static final char SPACE = ' ';

  @NotNull
  private static String[] nameToWords(@NotNull final String name) {
    final Collection<String> array;
    final int index = 0;
    final int length = name.length();

    array = toWords(index, length, name);

    return ArrayUtil.toStringArray(array);
  }

  @SuppressWarnings({"MethodWithMultipleLoops",
    "OverlyComplexMethod",
    "OverlyLongMethod",
    "IfStatementWithTooManyBranches",
    "AssignmentToMethodParameter",
    "SameParameterValue"})
  private static Collection<String> toWords(int index, final int length, final String name) {
    final Collection<String> array = new ArrayList<>(10);
    while (index < length) {
      final int wordStart = index;
      int upperCaseCount = 0;
      int lowerCaseCount = 0;
      int digitCount = 0;
      int specialCount = 0;

      while (index < length) {
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
    return array;
  }

  @SuppressWarnings({"BooleanVariableAlwaysNegated",
    "DynamicRegexReplaceableByCompiledPattern",
    "HardcodedFileSeparator"})
  @NotNull
  private static String splitWords(@NotNull final String text,
                                   @NotNull final Function<? super String, String> transformWord) {
    final String[] words = nameToWords(text);
    boolean insertSeparator = false;
    final StringBuilder buf = new StringBuilder(10);

    for (final String word : words) {
      // { "common", "/__", "init", "__.", "py" }
      // if the character is not a java part nor a []()., replace with separator
      final boolean isSpecialChar = "[(.)/]" .contains(word.substring(0, 1));
      if (!Character.isLetterOrDigit(word.charAt(0)) && !isSpecialChar) {
        buf.append(SPACE);
        insertSeparator = false;
        continue;
      }

      // if insert separator and word length > 1, insert separator
      if (insertSeparator && word.length() > 1) {
        buf.append(SPACE);
      } else {
        insertSeparator = !isSpecialChar && word.length() > 1;
      }
      buf.append(transformWord.fun(word.replace("_", "")));
    }
    return buf.toString();
  }

  @SuppressWarnings("StringToUpperCaseOrToLowerCaseWithoutLocale")
  @Nullable
  @Override
  public String getEditorTabTitle(@NotNull final Project project, @NotNull final VirtualFile file) {
    boolean upperCaseTabs = MTConfig.getInstance().isUpperCaseTabs();
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(project);
    if (projectConfig != null) {
      upperCaseTabs = projectConfig.isUpperCaseTabs();
    }

    final String uniqueVirtualFilePath = UniqueVFilePathBuilder.getInstance().getUniqueVirtualFilePath(project, file);
    if (upperCaseTabs) {
      return splitWords(uniqueVirtualFilePath, String::toUpperCase);
    }
    return null;
  }
}
