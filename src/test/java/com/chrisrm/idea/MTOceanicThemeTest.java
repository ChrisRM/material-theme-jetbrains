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

import com.chrisrm.idea.themes.MTOceanicTheme;
import com.intellij.ui.ColorUtil;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;


public class MTOceanicThemeTest extends MTFixtureTestCase {

  private MTOceanicTheme mtOceanicTheme;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    mtOceanicTheme = new MTOceanicTheme();
  }

  @Override
  public void tearDown() throws Exception {
    MTConfig.getInstance().resetSettings();
    MTThemeManager.getInstance().activate();
    super.tearDown();
  }

  @Test
  public void testBackgroundResources() {
    mtOceanicTheme.activate();
    final Color backgroundColor = ColorUtil.fromHex(mtOceanicTheme.getBackgroundColorString());

    for (final String backgroundResource: mtOceanicTheme.getBackgroundResources()) {
      assertEquals("bgColor should be the oceanic theme's " + backgroundResource, UIManager.getColor(backgroundResource), backgroundColor);
    }
  }

  @Test
  public void testForegroundResources() {
    mtOceanicTheme.activate();
    final Color backgroundColor = ColorUtil.fromHex(mtOceanicTheme.getForegroundColorString());

    for (final String backgroundResource: mtOceanicTheme.getForegroundResources()) {
      assertEquals("bgColor should be the oceanic theme's " + backgroundResource, UIManager.getColor(backgroundResource), backgroundColor);
    }
  }

  @Test
  public void testBackgroundResources() {
    mtOceanicTheme.activate();
    final Color backgroundColor = ColorUtil.fromHex(mtOceanicTheme.getTextColorString());

    for (final String backgroundResource: mtOceanicTheme.getTextResources()) {
      assertEquals("bgColor should be the oceanic theme's " + backgroundResource, UIManager.getColor(backgroundResource), backgroundColor);
    }
  }

}
