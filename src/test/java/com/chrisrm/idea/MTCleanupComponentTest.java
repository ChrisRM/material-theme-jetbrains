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

import com.intellij.openapi.util.registry.Registry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MTCleanupComponentTest extends MTFixtureTestCase {

  private MTCleanupComponent mtCleanupComponentUnderTest;

  @Override
  @BeforeEach
  public void setUp() throws Exception {
    super.setUp();
    mtCleanupComponentUnderTest = new MTCleanupComponent();
  }

  @Test
  public void testDisposeComponent() {
    // Setup
    messRegistry();

    // Run the test
    mtCleanupComponentUnderTest.disposeComponent();

    // Verify the results
    checkRegistry();
  }

  private void messRegistry() {
    // bigger font in project view
    Registry.get("bigger.font.in.project.view").setValue(false);
    // scrollbars
    Registry.get("mac.editor.thumb.default.alpha.base").setValue(0);
    Registry.get("mac.editor.thumb.default.alpha.delta").setValue(0);
    Registry.get("mac.editor.thumb.darcula.alpha.base").setValue(0);
    Registry.get("mac.editor.thumb.darcula.alpha.delta").setValue(0);

    Registry.get("win.editor.thumb.default.alpha.base").setValue(0);
    Registry.get("win.editor.thumb.default.alpha.delta").setValue(0);
    Registry.get("win.editor.thumb.darcula.alpha.base").setValue(0);
    Registry.get("win.editor.thumb.darcula.alpha.delta").setValue(0);

    Registry.get("mac.editor.thumb.default.fill.min").setValue(0);
    Registry.get("mac.editor.thumb.default.fill.max").setValue(0);
    Registry.get("mac.editor.thumb.darcula.fill.min").setValue(0);
    Registry.get("mac.editor.thumb.darcula.fill.max").setValue(0);

    Registry.get("win.editor.thumb.default.fill.min").setValue(0);
    Registry.get("win.editor.thumb.default.fill.max").setValue(0);
    Registry.get("win.editor.thumb.darcula.fill.min").setValue(0);
    Registry.get("win.editor.thumb.darcula.fill.max").setValue(0);

    // dark title bar
    Registry.get("ide.mac.allowDarkWindowDecorations").setValue(true);
  }

  private void checkRegistry() {
    // bigger font in project view
    assertFalse(Registry.get("bigger.font.in.project.view").asBoolean());
    // scrollbars
    assertEquals(Registry.get("mac.editor.thumb.default.alpha.base").asInteger(), 75);
    assertEquals(Registry.get("mac.editor.thumb.default.alpha.delta").asInteger(), 120);
    assertEquals(Registry.get("mac.editor.thumb.darcula.alpha.base").asInteger(), 128);
    assertEquals(Registry.get("mac.editor.thumb.darcula.alpha.delta").asInteger(), 127);

    assertEquals(Registry.get("win.editor.thumb.default.alpha.base").asInteger(), 120);
    assertEquals(Registry.get("win.editor.thumb.default.alpha.delta").asInteger(), 135);
    assertEquals(Registry.get("win.editor.thumb.darcula.alpha.base").asInteger(), 128);
    assertEquals(Registry.get("win.editor.thumb.darcula.alpha.delta").asInteger(), 127);

    assertEquals(Registry.get("mac.editor.thumb.default.fill.min").asInteger(), 90);
    assertEquals(Registry.get("mac.editor.thumb.default.fill.max").asInteger(), 50);
    assertEquals(Registry.get("mac.editor.thumb.darcula.fill.min").asInteger(), 133);
    assertEquals(Registry.get("mac.editor.thumb.darcula.fill.max").asInteger(), 150);

    assertEquals(Registry.get("win.editor.thumb.default.fill.min").asInteger(), 193);
    assertEquals(Registry.get("win.editor.thumb.default.fill.max").asInteger(), 163);
    assertEquals(Registry.get("win.editor.thumb.darcula.fill.min").asInteger(), 133);
    assertEquals(Registry.get("win.editor.thumb.darcula.fill.max").asInteger(), 150);

    // dark title bar
    assertFalse(Registry.get("ide.mac.allowDarkWindowDecorations").asBoolean());
  }
}
