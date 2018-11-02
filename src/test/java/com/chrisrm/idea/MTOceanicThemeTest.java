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
import com.chrisrm.idea.utils.MTColorUtils;
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
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getBackgroundColorString());

    for (final String resource : MTAbstractTheme.getBackgroundResources()) {
      assertEquals("bgColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testForegroundResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getForegroundColorString());

    for (final String resource : MTAbstractTheme.getForegroundResources()) {
      assertEquals("fgColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testTextResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getTextColorString());

    for (final String resource : MTAbstractTheme.getTextResources()) {
      assertEquals("textColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testSelectionBackgroundResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getSelectionBackgroundColorString());

    for (final String resource : MTAbstractTheme.getSelectionBackgroundResources()) {
      assertEquals("selectBgColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testSelectionForegroundResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getSelectionForegroundColorString());

    for (final String resource : MTAbstractTheme.getSelectionForegroundResources()) {
      assertEquals("selectFgColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testButtonResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getButtonColorString());

    for (final String resource : MTAbstractTheme.getButtonColorResource()) {
      assertEquals("buttonColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testSecondaryBackgroundResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getSecondaryBackgroundColorString());

    for (final String resource : MTAbstractTheme.getSecondaryBackgroundResources()) {
      assertEquals("secondBgColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testDisabledResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getDisabledColorString());

    for (final String resource : MTAbstractTheme.getDisabledResources()) {
      assertEquals("disabledColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testContrastResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getContrastColorString());

    for (final String resource : MTAbstractTheme.getContrastResources()) {
      assertEquals("contrastColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testTableSelectedResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getTableSelectedColorString());

    for (final String resource : MTAbstractTheme.getTableSelectedResources()) {
      assertEquals("tableSelectedColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testSecondBorderResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getSecondBorderColorString());

    for (final String resource : MTAbstractTheme.getSecondBorderResources()) {
      assertEquals("borderColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testHighlightResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getHighlightColorString());

    for (final String resource : MTAbstractTheme.getHighlightResources()) {
      assertEquals("highlightColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testTreeSelectionResources() {
    mtOceanicTheme.activate();
    final Color color = MTColorUtils.parseColor(mtOceanicTheme.getTreeSelectionColorString());

    for (final String resource : MTAbstractTheme.getTreeSelectionResources()) {
      assertEquals("treeSelectColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

  @Test
  public void testNotificationsResources() {
    mtOceanicTheme.activate();
    final Color color = ColorUtil.fromHex(mtOceanicTheme.getNotificationsColorString());

    for (final String resource : MTAbstractTheme.getNotificationsResources()) {
      assertEquals("notifColor should be the oceanic theme's " + resource, UIManager.getColor(resource), color);
    }
  }

}
