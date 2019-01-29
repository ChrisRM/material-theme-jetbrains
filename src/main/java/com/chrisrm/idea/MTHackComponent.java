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

package com.chrisrm.idea;

import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrameProvider;
import com.intellij.ui.CaptionPanel;
import com.intellij.ui.ScrollingUtil;
import com.intellij.ui.components.MultiColumnList;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;
import org.jetbrains.annotations.NonNls;

@SuppressWarnings( {
    "CallToSuspiciousStringMethod",
    "HardCodedStringLiteral",
    "DuplicateStringLiteralInspection", "OverlyBroadCatchBlock"})
public final class MTHackComponent implements BaseComponent {

  static {
    hackTitleLabel();
    hackSearchTextField();
    hackNewScreenHardcodedColor();
    hackScrollbars();
  }

  private static void hackSearchTextField() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(ScrollingUtil.class));
      final CtClass ctClass2 = cp.get("com.intellij.ui.SearchTextField");
      final CtMethod method = ctClass2.getDeclaredMethod("customSetupUIAndTextField");
      method.instrument(new ExprEditor() {
        @Override
        public void edit(final FieldAccess f) throws CannotCompileException {
          if ("isMac".equals(f.getFieldName())) {
            f.replace("{ $_ = false; }");
          }
        }
      });
      ctClass2.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackNewScreenHardcodedColor() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(FlatWelcomeFrameProvider.class));
      final CtClass ctClass2 = cp.get("com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrame");
      final CtMethod method = ctClass2.getDeclaredMethod("getActionLinkSelectionColor");
      method.instrument(new ExprEditor() {
        @Override
        public void edit(final NewExpr e) throws CannotCompileException {
          final String bgColor = "javax.swing.UIManager.getColor(\"MenuItem.selectionBackground\")";
          e.replace(String.format("{ $_ = %s; $proceed($$); }", bgColor));
        }
      });
      ctClass2.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackScrollbars() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(MultiColumnList.class));
      final CtClass ctClass2 = cp.get("com.intellij.ui.components.ScrollBarPainter$Thumb");
      final CtMethod method = ctClass2.getDeclaredMethod("paint");
      if (SystemInfoRt.isMac) {
        return;
      }

      method.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("paint".equals(m.getMethodName())) {
            final String off = "($8 == null ? 2 : 1)";
            final String margin = "($8 == null ? 4 : 2)";

            m.replace(String.format("{ $2 = $2 + %s; $3 = $3 + %s; $4 = $4 - %s; $5 = $5 - %s; $6 = 8; $proceed($$); }",
                                    off, off, margin, margin));
          }
        }
      });
      ctClass2.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  /**
   * For better dialog titles (since I have no idea how to know when dialogs appear, I can't attach events so I'm directly hacking
   * the source code). I hate doing this.
   */
  private static void hackTitleLabel() {
    // Hack method
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(CaptionPanel.class));
      final CtClass ctClass = cp.get("com.intellij.ui.TitlePanel");
      final CtConstructor declaredConstructor = ctClass.getDeclaredConstructor(new CtClass[] {
          cp.get("javax.swing.Icon"),
          cp.get("javax.swing.Icon")});
      declaredConstructor.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          final String s = m.getMethodName();
          if ("setHorizontalAlignment".equals(s)) {
            // Set title at the left
            m.replace("{ $1 = javax.swing.SwingConstants.LEFT; $_ = $proceed($$); }");
          } else if ("setBorder".equals(s)) {
            // Bigger heading
            m.replace("{ $_ = $proceed($$); myLabel.setFont(myLabel.getFont().deriveFont(1, com.intellij.util.ui.JBUI.scale(16.0f))); }");
          }
        }
      });

      final CtMethod getPreferredSize = ctClass.getDeclaredMethod("getPreferredSize");
      getPreferredSize.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("headerHeight".equals(m.getMethodName())) {
            // Set title at the left
            m.replace("{ $_ = 40; }");
          }
        }
      });

      ctClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }
}
