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

package com.chrisrm.idea.ui;

import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WelcomeScreen;
import com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrame;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBList;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.ui.UIUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public final class MTFlatWelcomeFrame extends FlatWelcomeFrame implements IdeFrame {

  MTFlatWelcomeFrame() {
    super();
    final Color color = UIManager.getColor("Panel.background");
    final Color listColor = UIManager.getColor("List.background");

    // Set welcome frame bg
    WelcomeScreen myScreen = ReflectionUtil.getField(FlatWelcomeFrame.class, this, WelcomeScreen.class, "myScreen");
    // Fuck I dunno why sometimes it is compiled (Phpstorm, rubymine) and sometimes not...
    if (myScreen == null) {
      myScreen = ReflectionUtil.getField(FlatWelcomeFrame.class, this, WelcomeScreen.class, "a");
    }
    if (myScreen == null) {
      myScreen = ReflectionUtil.getField(FlatWelcomeFrame.class, this, WelcomeScreen.class, "b");
    }
    if (myScreen == null) {
      myScreen = ReflectionUtil.getField(FlatWelcomeFrame.class, this, WelcomeScreen.class, "c");
    }
    if (myScreen == null) {
      myScreen = ReflectionUtil.getField(FlatWelcomeFrame.class, this, WelcomeScreen.class, "d");
    }
    if (myScreen == null) {
      return;
    }

    final JPanel welcomeScreen = (JPanel) myScreen;
    welcomeScreen.setBackground(color);

    // Set projects bg
    final JBList list = UIUtil.findComponentOfType(welcomeScreen, JBList.class);
    if (list != null) {
      list.setBackground(new JBColor(listColor, listColor));

      final ListCellRenderer renderer = list.getCellRenderer();
      final MTProjectsRenderer mtProjectsRenderer = new MTProjectsRenderer();
      // All this crap just because Jetbrains didnt' bother using UIManager for their colors -_-
      final ListCellRenderer proxy = (ListCellRenderer) Enhancer.create(ListCellRenderer.class, (MethodInterceptor) (o, method, objects,
                                                                                                                     methodProxy) -> {
        final Object result = method.invoke(renderer, objects);

        if (result instanceof JPanel && "getListCellRendererComponent".equals(method.getName())) {
          mtProjectsRenderer.getListCellRenderedComponent((JPanel) result, method, objects);
        }

        return result;
      });

      list.setCellRenderer(proxy);
    }
  }

  public static class MTProjectsRenderer extends DefaultListCellRenderer {

    final void getListCellRenderedComponent(final JPanel result, final Method method, final Object[] objects) {
      final JList list = (JList) objects[0];
      final boolean isSelected = (boolean) objects[3];

      result.setBackground(getListBackground(isSelected, list.hasFocus()));
    }

    final Color getListBackground(final boolean isSelected, final boolean hasFocus) {
      return UIUtil.getListBackground(isSelected);
    }

    protected final Color getListForeground(final boolean isSelected, final boolean hasFocus) {
      return UIUtil.getListForeground(isSelected);
    }

  }
}
