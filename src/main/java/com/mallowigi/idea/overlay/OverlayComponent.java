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

package com.mallowigi.idea.overlay;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.wm.ex.ToolWindowEx;
import com.intellij.ui.components.panels.NonOpaquePanel;
import com.intellij.util.ui.accessibility.ScreenReader;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class OverlayComponent implements Disposable {
  //  private final OverlayPainter myPainter;
  //  private Disposable myGlassPaneListenersDisposable = Disposer.newDisposable();

  OverlayComponent() {
    //    final NonOpaquePanel myComponent = new MyComponent();
    //
    //    myPainter = new OverlayPainter(myComponent);
    //    myGlassPaneListenersDisposable = Disposer.newDisposable("GlassPaneListeners");
    //    Disposer.register(this, myGlassPaneListenersDisposable);
    //    IdeGlassPaneUtil.find(myComponent).addPainter(myComponent, myPainter, myGlassPaneListenersDisposable);
  }

  @Override
  public void dispose() {

  }

  private class MyComponent extends NonOpaquePanel {
    MyComponent() {
      super(new BorderLayout());
      setOpaque(true);
      setFocusCycleRoot(!ScreenReader.isActive());
      setBorder(new ToolWindowEx.Border(false, false, false, false));
    }

    @Override
    protected void paintComponent(final Graphics g) {
      super.paintComponent(g);
    }

    @Override
    public @NotNull String getName() {
      return "OverlayComponent";
    }

    @Override
    public void addNotify() {
      super.addNotify();
    }

    @Override
    public void removeNotify() {
      super.removeNotify();
    }
  }

}
