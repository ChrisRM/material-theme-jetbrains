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

import com.chrisrm.idea.themes.MTCustomTheme;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class MTAbstractThemeTest extends MTFixtureTestCase {

  private String id;
  private String editorColorsScheme;
  private boolean dark;
  private String name;

  private MTAbstractTheme mtAbstractThemeUnderTest;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    id = "id";
    editorColorsScheme = "editorColorsScheme";
    dark = false;
    name = "name";
    mtAbstractThemeUnderTest = new MTAbstractTheme(id, editorColorsScheme, dark, name) {
      @Override
      protected String getBackgroundColorString() {
        return null;
      }

      @Override
      protected String getForegroundColorString() {
        return null;
      }

      @Override
      protected String getTextColorString() {
        return null;
      }

      @Override
      protected String getSelectionBackgroundColorString() {
        return null;
      }

      @Override
      protected String getSelectionForegroundColorString() {
        return null;
      }

      @Override
      protected String getButtonColorString() {
        return null;
      }

      @Override
      protected String getSecondaryBackgroundColorString() {
        return null;
      }

      @Override
      protected String getDisabledColorString() {
        return null;
      }

      @Override
      protected String getContrastColorString() {
        return null;
      }

      @Override
      protected String getTableSelectedColorString() {
        return null;
      }

      @Override
      protected String getSecondBorderColorString() {
        return null;
      }

      @Override
      protected String getHighlightColorString() {
        return null;
      }

      @Override
      protected String getTreeSelectionColorString() {
        return null;
      }

      @Override
      protected String getNotificationsColorString() {
        return null;
      }
    };
  }

  @Override
  public void tearDown() throws Exception {
    MTConfig.getInstance().resetSettings();
    MTThemeManager.getInstance().activate();
    super.tearDown();
  }

  @Test
  public void testGetSelectionBackground() {
    // Setup
    final String expectedResult = MTCustomTheme.SELECTION_BACKGROUND;

    // Run the test
    final String result = mtAbstractThemeUnderTest.getSelectionBackground();

    // Verify the results
    assertEquals("should have custom theme selection background", expectedResult, result);
  }

  @Test
  public void testGetDisabled() {
    // Setup
    final String expectedResult = MTCustomTheme.DISABLED;

    // Run the test
    final String result = mtAbstractThemeUnderTest.getDisabled();

    // Verify the results
    assertEquals("should have custom theme disabled color", expectedResult, result);
  }

  @Test
  public void testActivate() {
    // Setup

    // Run the test
    mtAbstractThemeUnderTest.activate();

    // Verify the results
  }

  @Test
  public void testIsCustom() {
    // Setup
    final boolean expectedResult = false;

    // Run the test
    final boolean result = mtAbstractThemeUnderTest.isCustom();

    // Verify the results
    assertEquals("should not be custom", expectedResult, result);
  }

  @Test
  public void testGetPrimaryColor() {
    // Setup
    final Color expectedResult = UIManager.getColor("material.primaryColor");

    // Run the test
    final Color result = mtAbstractThemeUnderTest.getPrimaryColor();

    // Verify the results
    assertEquals("should return primary color", expectedResult, result);
  }

  @Test
  public void testNonMaterialGetPrimaryColor() {
    // Setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    MTThemeManager.getInstance().activate();

    // Run the test
    final Color result = mtAbstractThemeUnderTest.getPrimaryColor();

    // Verify the results
    assertEquals("should return darcula background color", UIManager.getColor("darcula.background"), result);
  }

  @Test
  public void testGetBackgroundColor() {
    // Run the test
    final Color result = mtAbstractThemeUnderTest.getBackgroundColor();

    // Verify the results
    assertEquals("should return material background", UIManager.getColor("material.background"), result);
  }

  @Test
  public void testNonMaterialGetBackgroundColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    MTThemeManager.getInstance().activate();

    // Run the test
    final Color result = mtAbstractThemeUnderTest.getBackgroundColor();

    // Verify the results
    assertEquals("should return darcula background color", UIManager.getColor("darcula.background"), result);
  }


  @Test
  public void testGetForegroundColor() {
    // Setup
    final Color expectedResult = UIManager.getColor("material.foreground");

    // Run the test
    final Color result = mtAbstractThemeUnderTest.getForegroundColor();

    // Verify the results
    assertEquals("should return material foreground", expectedResult, result);
  }

  @Test
  public void testNonMaterialGetForegroundColor() {
    // Run the test
    MTConfig.getInstance().setIsMaterialTheme(false);
    MTThemeManager.getInstance().activate();
    final Color result = mtAbstractThemeUnderTest.getForegroundColor();

    // Verify the results
    assertEquals("should return darcula foreground color", UIManager.getColor("darcula.foreground"), result);
  }

  @Test
  public void testGetContrastColor() {
    // Setup
    final Color expectedResult = UIManager.getColor("material.contrast");

    // Run the test
    final Color result = mtAbstractThemeUnderTest.getContrastColor();

    // Verify the results
    assertEquals("should return material contrast", expectedResult, result);
  }

  @Test
  public void testNonMaterialGetContrastColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    MTThemeManager.getInstance().activate();

    // Run the test
    final Color result = mtAbstractThemeUnderTest.getContrastColor();

    // Verify the results
    assertEquals("should return default contrast", new ColorUIResource(0x262626), result);
  }
}
