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

package com.mallowigi.idea.actions;

import com.intellij.application.options.colors.ColorAndFontOptions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.options.ex.Settings;
import com.intellij.openapi.ui.Messages;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MTResetColorScheme extends AnAction {
  @Override
  public void actionPerformed(@NotNull final AnActionEvent e) {
    final DataContext dataContext = e.getDataContext();

    if (Messages.showOkCancelDialog(
      MaterialThemeBundle.message("action.MTResetColorScheme.explanation"),
      MaterialThemeBundle.message("action.MTResetColorScheme.text"),
      MaterialThemeBundle.message("common.ok"),
      MaterialThemeBundle.message("common.cancel"),
      Messages.getQuestionIcon()
    ) == Messages.OK) {
      final EditorColorsScheme scheme = EditorColorsManager.getInstance().getSchemeForCurrentUITheme();
      final ColorAndFontOptions options = getOptions(dataContext);
      if (options == null) {
        return;
      }

      try {
        final Method method = ColorAndFontOptions.class.getDeclaredMethod("resetSchemeToOriginal", String.class);
        method.setAccessible(true);
        method.invoke(options, scheme.getName());
      } catch (final NoSuchMethodException ex) {
        ex.printStackTrace();
      } catch (final InvocationTargetException ex) {
        ex.printStackTrace();
      } catch (final IllegalAccessException ex) {
        ex.printStackTrace();
      }
    }
  }

  private @Nullable ColorAndFontOptions getOptions(final DataContext context) {
    final Settings settings = Settings.KEY.getData(context);
    if (settings == null) {
      return null;
    }
    return settings.find(ColorAndFontOptions.class);
  }

}
