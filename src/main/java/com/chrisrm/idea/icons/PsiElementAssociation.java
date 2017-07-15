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

package com.chrisrm.idea.icons;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.ElementPresentationUtil;

import javax.swing.*;

public class PsiElementAssociation extends Association {
  private Class<? extends PsiElement> type;

  @Override
  public boolean matches(FileInfo file) {
    final PsiElement psiElement = file.getPsiElement();
    return psiElement != null && (type.isAssignableFrom(psiElement.getClass()));
  }

  public Icon getIconForFile(FileInfo file) {
    final PsiElement psiElement = file.getPsiElement();
    return ElementPresentationUtil.getClassIconOfKind((PsiClass) psiElement, ElementPresentationUtil.getBasicClassKind((PsiClass) psiElement));
  }

  public Class<? extends PsiElement> getType() {
    return type;
  }

  public void setType(Class<? extends PsiElement> psiClass) {
    this.type = psiClass;
  }
}