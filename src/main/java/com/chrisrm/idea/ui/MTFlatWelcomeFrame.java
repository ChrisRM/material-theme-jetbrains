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
import net.sf.cglib.proxy.MethodProxy;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class MTFlatWelcomeFrame extends FlatWelcomeFrame implements IdeFrame {

  MTFlatWelcomeFrame() {
    super();
    Color color = UIManager.getColor("Panel.background");
    Color listColor = UIManager.getColor("List.background");

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

    JPanel welcomeScreen = (JPanel) myScreen;
    welcomeScreen.setBackground(color);

    // Set projects bg
    JBList list = UIUtil.findComponentOfType(welcomeScreen, JBList.class);
    if (list != null) {
      list.setBackground(new JBColor(listColor, listColor));

      ListCellRenderer renderer = list.getCellRenderer();
      MTProjectsRenderer mtProjectsRenderer = new MTProjectsRenderer();
      // All this crap just because Jetbrains didnt' bother using UIManager for their colors -_-
      final ListCellRenderer proxy = (ListCellRenderer) Enhancer.create(ListCellRenderer.class, new MethodInterceptor() {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
          Object result = method.invoke(renderer, objects);

          if (result instanceof JPanel && "getListCellRendererComponent".equals(method.getName())) {
            mtProjectsRenderer.getListCellRenderedComponent((JPanel) result, method, objects);
          }

          return result;
        }
      });

      list.setCellRenderer(proxy);
    }
  }

  public static class MTProjectsRenderer extends DefaultListCellRenderer {

    void getListCellRenderedComponent(JPanel result, Method method, Object[] objects) {
      JList list = (JList) objects[0];
      boolean isSelected = (boolean) objects[3];

      result.setBackground(getListBackground(isSelected, list.hasFocus()));
    }

    Color getListBackground(boolean isSelected, boolean hasFocus) {
      return UIUtil.getListBackground(isSelected);
    }

    protected Color getListForeground(boolean isSelected, boolean hasFocus) {
      return UIUtil.getListForeground(isSelected);
    }

  }
}
