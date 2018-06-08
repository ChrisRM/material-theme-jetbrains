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

import com.intellij.ide.navigationToolbar.NavBarIdeView;
import com.intellij.ide.plugins.PluginManagerConfigurable;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.impl.ChameleonAction;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.intellij.openapi.wm.impl.IdeFocusManagerImpl;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrameProvider;
import com.intellij.ui.CaptionPanel;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.util.ui.JBSwingUtilities;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;

public class MTHackComponent implements ApplicationComponent {
  public static final String TABS_HEIGHT = "MTTabsHeight";
  public static final String BORDER_POPUP = "MTBorderPopup";

  static {
    hackTitleLabel();
    hackIdeaActionButton();
    hackBackgroundFrame();
    hackTabsGetHeight();
    //    hackToolWindowHeader();
    hackSpeedSearch();
    hackFlatWelcomeFrame();
    hackPopupBorder();
    hackDarculaTabsPainter();
    hackPluginManagerNew();
    hackIntelliJFailures();
    hackProjectViewBorder();
  }

  private static void hackProjectViewBorder() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(IdeFocusManagerImpl.class));
      final CtClass ctClass2 = cp.get("com.intellij.openapi.wm.impl.InternalDecorator$InnerPanelBorder");
      final CtMethod method = ctClass2.getDeclaredMethod("paintBorder");
      method.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("setColor")) {
            m.replace("{ $1 = javax.swing.UIManager.getColor(\"Panel.background\"); $_ = $proceed($$); }");
          }
        }
      });
      ctClass2.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  private static void hackIntelliJFailures() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(JBSwingUtilities.class));
      final CtClass ctClass2 = cp.get("com.intellij.util.IJSwingUtilities");
      final CtMethod method = ctClass2.getDeclaredMethod("updateComponentTreeUI");
      method.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("decorateWindowHeader")) {
            m.replace("{ }");
          }
        }
      });
      ctClass2.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  private static void hackPluginManagerNew() {
    // Hack method
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(PluginManagerConfigurable.class));
      final CtClass ctClass = cp.get("com.intellij.ide.plugins.newui.PluginsGroupComponent");

      final CtMethod addGroup = ctClass.getDeclaredMethod("addGroup", new CtClass[]{
          cp.get("com.intellij.ide.plugins.newui.PluginsGroup"),
          cp.get("int")
      });
      addGroup.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("setForeground")) {
            final String fgColor = "javax.swing.UIManager.getColor(\"List.foreground\")";

            m.replace(String.format("{ $1 = %s; $_ = $proceed($$); }", fgColor));
          }
        }

        @Override
        public void edit(final NewExpr e) throws CannotCompileException {
          if (e.getClassName().contains("OpaquePanel")) {
            final String bgColor = "javax.swing.UIManager.getColor(\"List.background\")";

            e.replace(String.format("{ $2 = %s; $_ = $proceed($$); }", bgColor, bgColor));
          }
        }
      });
      ctClass.toClass();

      final CtClass ctClass2 = cp.get("com.intellij.ide.plugins.newui.TagComponent");
      final CtMethod method = ctClass2.getDeclaredMethod("paintComponent");
      method.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("setColor")) {
            final String bgColor = "javax.swing.UIManager.getColor(\"Button.mt.background\")";

            m.replace(String.format("{ $1 = %s; $proceed($$); }", bgColor));
          }
        }
      });

      ctClass2.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public MTHackComponent() {
    PropertiesComponent.getInstance().setValue(TABS_HEIGHT, 25, 24);
    PropertiesComponent.getInstance().setValue(BORDER_POPUP, true, false);
  }

  private static void hackPopupBorder() {
    try {
      final ClassPool cp = new ClassPool(true);
      final CtClass ctClass2 = cp.get("com.intellij.ui.PopupBorder$Factory");
      cp.insertClassPath(new ClassClassPath(TabInfo.class));
      final CtMethod method = ctClass2.getDeclaredMethod("create");
      method.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("getBorderColor")) {
            final String code = String.format("com.intellij.ide.util.PropertiesComponent.getInstance().getBoolean(\"%s\", true)",
                BORDER_POPUP);
            m.replace(String.format("{ $_ = %s ? javax.swing.UIManager.getColor(\"Separator.foreground\") : $proceed($$); }", code));
          }
        }
      });
      ctClass2.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  private static void hackDarculaTabsPainter() {
    // Hack method
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(JBEditorTabs.class));
      final CtClass ctClass = cp.get("com.intellij.ui.tabs.impl.DarculaEditorTabsPainter");

      final CtMethod defaultTabColor = ctClass.getDeclaredMethod("getDefaultTabColor");
      defaultTabColor.instrument(new ExprEditor() {
        @Override
        public void edit(final FieldAccess f) throws CannotCompileException {
          f.replace("{ $_ = javax.swing.UIManager.getColor(\"TabbedPane.selectHighlight\"); }");
        }
      });
      ctClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  private static void hackBackgroundFrame() {
    // Hack method
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(IdeBackgroundUtil.class));
      final CtClass ctClass = cp.get("com.intellij.openapi.wm.impl.IdePanePanel");

      final CtMethod paintBorder = ctClass.getDeclaredMethod("getBackground");
      paintBorder.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("getIdeBackgroundColor")) {
            m.replace("{ $_ = javax.swing.UIManager.getColor(\"Viewport.background\"); }");
          }
        }
      });
      ctClass.toClass();
    } catch (final Exception e) {
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
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(CaptionPanel.class));
      final CtClass ctClass = cp.get("com.intellij.ui.TitlePanel");
      final CtConstructor declaredConstructor = ctClass.getDeclaredConstructor(new CtClass[]{
          cp.get("javax.swing.Icon"),
          cp.get("javax.swing" +
              ".Icon")});
      declaredConstructor.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          switch (m.getMethodName()) {
            case "setHorizontalAlignment":
              // Set title at the left
              m.replace("{ $1 = javax.swing.SwingConstants.LEFT; $_ = $proceed($$); }");
              break;
            case "setBorder":
              // Bigger heading
              m.replace("{ $_ = $proceed($$); myLabel.setFont(myLabel.getFont().deriveFont(1, com.intellij.util.ui.JBUI.scale(16.0f))); }");
              break;
          }
        }
      });

      final CtMethod getPreferredSize = ctClass.getDeclaredMethod("getPreferredSize");
      getPreferredSize.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          switch (m.getMethodName()) {
            case "headerHeight":
              // Set title at the left
              m.replace("{ $_ = 40; }");
              break;
          }
        }
      });

      ctClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Change Look and feel of Action buttons
   */
  private static void hackIdeaActionButton() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(NavBarIdeView.class));
      cp.insertClassPath(new ClassClassPath(ChameleonAction.class));
      final CtClass ctClass = cp.get("com.intellij.ide.navigationToolbar.NavBarBorder");

      final CtMethod paintBorder = ctClass.getDeclaredMethod("paintBorder");
      paintBorder.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("setColor")) {
            m.replace("{ $1 = javax.swing.UIManager.getColor(\"Panel.background\"); $_ = $proceed($$); }");
          }
        }
      });
      ctClass.toClass();

    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Hack ToolWindowHeight to not take TabsUtil.getHeight
   */
  private static void hackToolWindowHeader() {
    // Hack method
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(ToolWindowImpl.class));
      final CtClass ctClass = cp.get("com.intellij.openapi.wm.impl.ToolWindowHeader");
      final CtMethod ctMethod = ctClass.getDeclaredMethod("getPreferredSize");
      ctMethod.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getClassName().equals("com.intellij.ui.tabs.TabsUtil") && m.getMethodName().equals("getTabsHeight")) {
            m.replace("{ $_ = com.intellij.util.ui.JBUI.scale(25); }");
          }
        }
      });

      ctClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  private static void hackSpeedSearch() {
    // Hack method
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(ToolWindowImpl.class));
      final CtClass ctClass = cp.get("com.intellij.ui.SpeedSearchBase$SearchPopup");
      final CtConstructor declaredConstructor = ctClass.getDeclaredConstructors()[0];
      declaredConstructor.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getMethodName().equals("setBackground")) {
            final String bgColor = "com.intellij.util.ui.UIUtil.getToolTipBackground().brighter();";
            m.replace(String.format("{ $1 = %s; $proceed($$); }", bgColor));
          } else if (m.getMethodName().equals("setBorder")) {
            final String borderColor = "null";
            m.replace(String.format("{ $1 = %s; $proceed($$); }", borderColor));
          }
        }
      });

      ctClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Hack TabsUtil,getHeight to override SDK
   */
  private static void hackTabsGetHeight() {
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(TabInfo.class));
      final CtClass ctClass = cp.get("com.intellij.ui.tabs.impl.TabLabel");
      final CtMethod ctMethod = ctClass.getDeclaredMethod("getPreferredSize");

      ctMethod.instrument(new ExprEditor() {
        @Override
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getClassName().equals("com.intellij.ui.tabs.TabsUtil") && m.getMethodName().equals("getTabsHeight")) {
            final String code = String.format("com.intellij.ide.util.PropertiesComponent.getInstance().getInt(\"%s\", 25)", TABS_HEIGHT);
            m.replace(String.format("{ $_ = com.intellij.util.ui.JBUI.scale(%s); }", code));
          }
        }
      });
      ctClass.toClass();

      // Hack JBRunnerTabs
      //      final CtClass tabLabelClass = cp.get("com.intellij.execution.ui.layout.impl.JBRunnerTabs$MyTabLabel");
      //      final CtMethod ctMethod2 = tabLabelClass.getDeclaredMethod("getPreferredSize");
      //
      //      ctMethod2.instrument(new ExprEditor() {
      //        @Override
      //        public void edit(final FieldAccess f) throws CannotCompileException {
      //          if (f.getFieldName().equals("height") && f.isReader()) {
      //            f.replace("{ $_ = com.intellij.util.ui.JBUI.scale(25); }");
      //          }
      //        }
      //      });
      //      tabLabelClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  private static void hackFlatWelcomeFrame() {
    // Hack method
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(FlatWelcomeFrameProvider.class));
      final CtClass ctClass = cp.get("com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrame");
      final CtMethod ctMethod = ctClass.getDeclaredMethod("getProjectsBackground");
      ctMethod.instrument(new ExprEditor() {
        @Override
        public void edit(final NewExpr e) throws CannotCompileException {
          final String bgColor = "javax.swing.UIManager.getColor(\"List.background\")";

          e.replace(String.format("{ $1 = %s; $2 = %s; $_ = $proceed($$); }", bgColor, bgColor));
        }
      });

      ctClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }
}
