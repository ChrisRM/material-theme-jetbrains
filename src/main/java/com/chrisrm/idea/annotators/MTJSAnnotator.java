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
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

public class MTJSAnnotator implements Annotator {

  private final TextAttributesKey JSKEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JS.KEYWORD"),
                                                                  DefaultLanguageHighlighterColors.KEYWORD);
  private final TextAttributesKey THIS_SUPER = TextAttributesKey.createTextAttributesKey("JS.THIS_SUPER", JSKEYWORD);
  private final TextAttributesKey MODULE = TextAttributesKey.createTextAttributesKey("JS.MODULE_KEYWORD", JSKEYWORD);
  private final TextAttributesKey DEBUGGER = TextAttributesKey.createTextAttributesKey("JS.DEBUGGER_STMT", JSKEYWORD);
  private final TextAttributesKey NULL = TextAttributesKey.createTextAttributesKey("JS.NULL_UNDEFINED", JSKEYWORD);
  private final TextAttributesKey VAL = TextAttributesKey.createTextAttributesKey("JS.VAR_DEF", JSKEYWORD);
  private final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("JS.FUNCTION", JSKEYWORD);

  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {

    if (element instanceof LeafPsiElement) {
      final TextRange textRange = element.getTextRange();
      final TextRange range = new TextRange(textRange.getStartOffset(), textRange.getEndOffset());
      final Annotation annotation = holder.createAnnotation(HighlightSeverity.INFORMATION, range, null);
      final TextAttributesKey kind = getKeywordKind(element);

      if (kind != null) {
        annotation.setTextAttributes(kind);
      }
    }
  }

  public TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = null;
    switch (element.getText()) {
      case "this":
      case "super":
        kind = THIS_SUPER;
        break;
      case "export":
      case "import":
      case "require":
      case "from":
      case "module":
        kind = MODULE;
        break;
      case "debugger":
        kind = DEBUGGER;
        break;
      case "null":
      case "undefined":
        kind = NULL;
        break;
      case "var":
      case "let":
      case "const":
        kind = VAL;
        break;
      case "function":
        kind = FUNCTION;
        break;
    }
    return kind;
  }
}
