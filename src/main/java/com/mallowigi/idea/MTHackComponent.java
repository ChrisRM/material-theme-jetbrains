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

package com.mallowigi.idea;

import com.intellij.execution.runners.ProcessProxy;
import com.intellij.openapi.editor.toolbar.floating.DefaultFloatingToolbarProvider;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.intellij.openapi.wm.impl.SquareStripeButtonLook;
import com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrameProvider;
import com.intellij.ui.CaptionPanel;
import com.intellij.ui.components.MultiColumnList;
import com.intellij.ui.tabs.FileColorsConfigurable;
import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;
import org.jetbrains.annotations.NonNls;

@SuppressWarnings({
  "CallToSuspiciousStringMethod",
  "DuplicateStringLiteralInspection",
  "OverlyBroadCatchBlock",
  "KotlinInternalInJava",
  "HardCodedStringLiteral",
  "SpellCheckingInspection"})
public final class MTHackComponent {

  static {
    hackTabs();
    hackBackgroundFrame();
    hackTitleLabel();
    hackFab();
    hackNewScreenHardcodedColor();
    hackScrollbars();
    hackLiveIndicator();
    hackFileColors();
    hackEapAgreement();
    hackExperimentalUI();
    hackTagButton();
    hackWelcomeScreen();
    hackLargeStripeLooks();
  }

  private MTHackComponent() {
  }

  private static void hackFileColors() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(FileColorsConfigurable.class));
      final CtClass fileColorsClass = cp.get("com.intellij.ui.tabs.FileColorManagerImpl");

      final CtMethod getColorName = fileColorsClass.getDeclaredMethod("getColorName");
      getColorName.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("message".equals(m.getMethodName())) {
            m.replace("{ $_ = id; }");
          }
        }
      });

      final CtMethod getColorNames = fileColorsClass.getDeclaredMethod("getColorNames");
      getColorNames.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("message".equals(m.getMethodName())) {
            m.replace("{ $_ = key; }");
          }
        }
      });

      fileColorsClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackBackgroundFrame() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(IdeBackgroundUtil.class));
      final CtClass ideRootPaneClass = cp.get("com.intellij.openapi.wm.impl.IdeRootPane");

      final CtMethod createContentPane = ideRootPaneClass.getDeclaredMethod("createContentPane");
      createContentPane.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("setBackground".equals(m.getMethodName())) {
            m.replace("{ $1 = javax.swing.UIManager.getColor(\"Viewport.background\"); }");
          }
        }
      });
      ideRootPaneClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  private static void hackNewScreenHardcodedColor() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(FlatWelcomeFrameProvider.class));
      final CtClass welcomeScreenClass = cp.get("com.intellij.openapi.wm.impl.welcomeScreen.WelcomeScreenUIManager");

      final CtMethod getActionLinkSelectionColor = welcomeScreenClass.getDeclaredMethod("getActionLinkSelectionColor");
      getActionLinkSelectionColor.instrument(new ExprEditor() {
        @Override
        public void edit(final NewExpr e) throws CannotCompileException {
          final String bgColor = "javax.swing.UIManager.getColor(\"MenuItem.selectionBackground\")";
          e.replace(String.format("{ $_ = %s; $proceed($$); }", bgColor));
        }
      });

      final CtMethod getLinkNormalColor = welcomeScreenClass.getDeclaredMethod("getLinkNormalColor");
      getLinkNormalColor.instrument(new ExprEditor() {
        @Override
        public void edit(final NewExpr e) throws CannotCompileException {
          final String bgColor = "javax.swing.UIManager.getColor(\"MenuItem.selectionForeground\")";
          e.replace(String.format("{ $_ = %s; $proceed($$); }", bgColor));
        }
      });
      welcomeScreenClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackTabs() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(FlatWelcomeFrameProvider.class));
      final CtClass singleHeightLabelClass = cp.get("com.intellij.ui.tabs.impl.SingleHeightTabs$SingleHeightLabel");

      final CtMethod getPreferredHeight = singleHeightLabelClass.getDeclaredMethod("getPreferredHeight");
      getPreferredHeight.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("scale".equals(m.getMethodName())) {
            m.replace("{ $1 = javax.swing.UIManager.getInt(\"TabbedPane.tabHeight\"); $_ = $proceed($$); }");
          }
        }
      });
      singleHeightLabelClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackScrollbars() {
    try {
      if (SystemInfoRt.isMac) {
        return;
      }
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(MultiColumnList.class));

      final CtClass scrollBarThumbClass = cp.get("com.intellij.ui.components.ScrollBarPainter$Thumb");
      final CtMethod paint = scrollBarThumbClass.getDeclaredMethod("paint");
      paint.instrument(new ExprEditor() {
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
      scrollBarThumbClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  /**
   * For better dialog titles (since I have no idea how to know when dialogs appear, I can't attach events so I'm directly hacking
   * the source code). I hate doing this.
   */
  private static void hackTitleLabel() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(CaptionPanel.class));
      final CtClass titlePanelClass = cp.get("com.intellij.ui.TitlePanel");

      final CtConstructor declaredConstructor = titlePanelClass.getDeclaredConstructor(new CtClass[]{
        cp.get("javax.swing.Icon"),
        cp.get("javax.swing.Icon")});
      declaredConstructor.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          final String methodName = m.getMethodName();
          if ("setHorizontalAlignment".equals(methodName)) {
            // Set title at the left
            m.replace("{ $1 = javax.swing.SwingConstants.LEFT; $_ = $proceed($$); }");
          } else if ("setBorder".equals(methodName)) {
            // Bigger heading
            m.replace("{ $_ = $proceed($$); myLabel.setFont(myLabel.getFont().deriveFont(1, com.intellij.util.ui.JBUI.scale(16.0f))); }");
          }
        }
      });

      final CtMethod getPreferredSize = titlePanelClass.getDeclaredMethod("getPreferredSize");
      getPreferredSize.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("headerHeight".equals(m.getMethodName())) {
            // Set title at the left
            m.replace("{ $_ = 40; }");
          }
        }
      });

      titlePanelClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackFab() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(DefaultFloatingToolbarProvider.class));
      final CtClass floatingToolbarClass = cp.get("com.intellij.openapi.editor.toolbar.floating.FloatingToolbarComponentImpl");

      final CtMethod paintComponent = floatingToolbarClass.getDeclaredMethod("paintComponent");
      paintComponent.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("fillRoundRect".equals(m.getMethodName())) {
            m.replace("$5 = 26; $6 = 26; $_ = $proceed($$);");
          }
        }
      });

      // Double up the fab buttons
      //      final CtConstructor declaredConstructor = ctClass2.getDeclaredConstructors()[0];
      //      declaredConstructor.instrument(new ExprEditor() {
      //        @Override
      //        public void edit(final MethodCall m) throws CannotCompileException {
      //          final String s = m.getMethodName();
      //          if ("setMinimumButtonSize".equals(s)) {
      //            m.replace("{ $1 = new java.awt.Dimension(44, 44); $_ = $proceed($$); }");
      //          }
      //        }
      //      });

      floatingToolbarClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  /**
   * Enables the ability to tint the live activity
   * indicator to the current accent color.
   * The small dot next to an icon that indicates
   * a process is running.
   */
  private static void hackLiveIndicator() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(ProcessProxy.class));
      final CtClass executionUtilClass = cp.get("com.intellij.execution.runners.ExecutionUtil");

      final CtMethod getLiveIndicatorMethod = executionUtilClass.getDeclaredMethods(
        "getLiveIndicator"
      )[1];
      getLiveIndicatorMethod.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("getIndicator".equals(m.getMethodName())) {
            m.replace("{ $4 = com.intellij.ui.JBColor.namedColor(\"LiveIndicator.color\", java.awt.Color.GREEN); $_ = $proceed($$); }");
          }
        }
      });
      executionUtilClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackEapAgreement() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      final CtClass agreementClass = cp.get("com.intellij.ide.gdpr.AgreementUi");

      //      final CtMethod createHtmlEditorPane = agreementClass.getDeclaredMethod("createHtmlEditorPane");
      //      createHtmlEditorPane.instrument(new ExprEditor() {
      //        @Override
      //        public void edit(final MethodCall m) throws CannotCompileException {
      //          if ("setBackground".equals(m.getMethodName())) {
      //            m.replace("{ $1 = javax.swing.UIManager.getColor(\"Panel.background\"); $proceed($$); }");
      //          }
      //        }
      //      });

      final CtMethod addEapPanel = agreementClass.getDeclaredMethod("addEapPanel");
      addEapPanel.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("setBackground".equals(m.getMethodName())) {
            m.replace("{ $1 = javax.swing.UIManager.getColor(\"Panel.background\"); $proceed($$); }");
          }
        }
      });
      agreementClass.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  private static void hackExperimentalUI() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      final CtClass experimentalUIClass = cp.get("com.intellij.ui.ExperimentalUI");

      final CtMethod isNewToolWindowsStripes = experimentalUIClass.getDeclaredMethod("isNewToolWindowsStripes");
      isNewToolWindowsStripes.setBody(String.format("{ return com.intellij.openapi.util.registry.Registry.is(\"%s\"); }",
        MTThemeManagerKt.NEW_STRIPES_UI));
      experimentalUIClass.toClass();
    } catch (final Throwable e) {
      // do nothing
    }
  }

  private static void hackTagButton() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      final CtClass tagButtonClass = cp.get("com.intellij.execution.ui.TagButton");

      final CtMethod getBackgroundColor = tagButtonClass.getDeclaredMethod("getBackgroundColor");
      getBackgroundColor.setBody("{ return javax.swing.UIManager.getColor(\"Button.background\"); }");
      //      getBackgroundColor.instrument(new ExprEditor() {
      //        @Override
      //        public void edit(final MethodCall m) throws CannotCompileException {
      //          if ("hoverBackground".equals(m.getMethodName())) {
      //            m.replace("{ $_ = javax.swing.UIManager.getColor(\"Button.startBackground\"); $proceed($$); }");
      //          }
      //        }
      //      });
      tagButtonClass.toClass();
    } catch (final Throwable e) {
      // do nothing
    }
  }

  private static void hackWelcomeScreen() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(CaptionPanel.class));
      final CtClass ctClass = cp.get("com.intellij.openapi.wm.impl.welcomeScreen.TabbedWelcomeScreen");

      final CtConstructor declaredConstructor = ctClass.getDeclaredConstructors()[0];
      declaredConstructor.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          final String methodName = m.getMethodName();
          if ("setBorder".equals(methodName) && "javax.swing.JTree".equals(m.getClassName())) {
            m.replace("{ $1 = com.intellij.util.ui.JBUI.Borders.emptyLeft(1); $_ = $proceed($$); }");
          }
        }
      });

      final CtClass ctClass2 = cp.get("com.intellij.openapi.wm.impl.welcomeScreen.TabbedWelcomeScreen$MyCellRenderer");
      final CtMethod getTreeCellRendererComponent = ctClass2.getDeclaredMethod("getTreeCellRendererComponent");
      getTreeCellRendererComponent.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if ("setBackgroundRecursively".equals(m.getMethodName())) {
            m.replace("{ $2 = com.intellij.ui.Gray.TRANSPARENT; $proceed($$); }");
          }
        }
      });

      ctClass.toClass();
      ctClass2.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings({"DuplicatedCode",
    "LocalVariableNamingConvention"})
  private static void hackLargeStripeLooks() {
    try {
      @NonNls final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(SquareStripeButtonLook.class));
      final CtClass squareStripeButton = cp.get("com.intellij.openapi.wm.impl.SquareStripeButton");

      final CtConstructor squareStripeButtonDeclaredConstructor = squareStripeButton.getDeclaredConstructors()[0];
      squareStripeButtonDeclaredConstructor.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          final String methodName = m.getMethodName();
          if ("setLook".equals(methodName)) {
            m.replace("{  }");
          }
        }
      });
      squareStripeButton.toClass();

      final CtClass moreButton = cp.get("com.intellij.openapi.wm.impl.MoreSquareStripeButton");

      final CtConstructor moreButtonDeclaredConstructor = moreButton.getDeclaredConstructors()[0];
      moreButtonDeclaredConstructor.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          final String methodName = m.getMethodName();
          if ("setLook".equals(methodName)) {
            m.replace("{  }");
          }
        }
      });
      moreButton.toClass();
    } catch (final Throwable e) {
      e.printStackTrace();
    }
  }

}
