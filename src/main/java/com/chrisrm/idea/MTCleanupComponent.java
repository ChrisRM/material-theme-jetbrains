/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.util.registry.Registry;

public final class MTCleanupComponent implements ApplicationComponent {
  protected MTCleanupComponent() {
  }

  @Override
  public void disposeComponent() {
    // bigger font in project view
    Registry.get("bigger.font.in.project.view").setValue(false);
    // scrollbars
    Registry.get("mac.editor.thumb.default.alpha.base").setValue(75);
    Registry.get("mac.editor.thumb.default.alpha.delta").setValue(120);
    Registry.get("mac.editor.thumb.darcula.alpha.base").setValue(128);
    Registry.get("mac.editor.thumb.darcula.alpha.delta").setValue(127);

    Registry.get("win.editor.thumb.default.alpha.base").setValue(120);
    Registry.get("win.editor.thumb.default.alpha.delta").setValue(135);
    Registry.get("win.editor.thumb.darcula.alpha.base").setValue(128);
    Registry.get("win.editor.thumb.darcula.alpha.delta").setValue(127);

    Registry.get("mac.editor.thumb.default.fill.min").setValue(90);
    Registry.get("mac.editor.thumb.default.fill.max").setValue(50);
    Registry.get("mac.editor.thumb.darcula.fill.min").setValue(133);
    Registry.get("mac.editor.thumb.darcula.fill.max").setValue(150);

    Registry.get("win.editor.thumb.default.fill.min").setValue(193);
    Registry.get("win.editor.thumb.default.fill.max").setValue(163);
    Registry.get("win.editor.thumb.darcula.fill.min").setValue(133);
    Registry.get("win.editor.thumb.darcula.fill.max").setValue(150);

    // dark title bar
    Registry.get("ide.mac.allowDarkWindowDecorations").setValue("false");
  }
}
