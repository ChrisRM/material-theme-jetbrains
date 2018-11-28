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

package com.chrisrm.idea.annotators;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.javadoc.PsiDocTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class BaseAnnotator implements Annotator {
  @Override
  public final void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {

    if (element instanceof LeafPsiElement && !(element.getParent() instanceof PsiComment) && !(element.getParent() instanceof PsiDocTag)) {
      final TextAttributesKey kind = getKeywordKind(element);
      if (kind == null) {
        return;
      }
      final TextRange textRange = element.getTextRange();
      final TextRange range = new TextRange(textRange.getStartOffset(), textRange.getEndOffset());
      final Annotation annotation = holder.createAnnotation(HighlightSeverity.INFORMATION, range, null);

      annotation.setTextAttributes(kind);
    }
  }

  @Nullable
  protected abstract TextAttributesKey getKeywordKind(@NotNull PsiElement element);
}
