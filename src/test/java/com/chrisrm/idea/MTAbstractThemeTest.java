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
import com.chrisrm.idea.themes.MTLightCustomTheme;
import com.intellij.ide.ui.laf.LafManagerImpl;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;


public class MTAbstractThemeTest extends MTFixtureTestCase {

  private final MTAbstractTheme darkAbstractTheme = new MTAbstractTheme(true) {
    @Override
    protected String getBackgroundColorString() {
      return "000000";
    }

    @Override
    protected String getForegroundColorString() {
      return "000000";
    }

    @Override
    protected String getTextColorString() {
      return "000000";
    }

    @Override
    protected String getSelectionBackgroundColorString() {
      return "000000";
    }

    @Override
    protected String getSelectionForegroundColorString() {
      return "000000";
    }

    @Override
    protected String getButtonColorString() {
      return "000000";
    }

    @Override
    protected String getSecondaryBackgroundColorString() {
      return "000000";
    }

    @Override
    protected String getDisabledColorString() {
      return "000000";
    }

    @Override
    protected String getContrastColorString() {
      return "000000";
    }

    @Override
    protected String getTableSelectedColorString() {
      return "000000";
    }

    @Override
    protected String getSecondBorderColorString() {
      return "000000";
    }

    @Override
    protected String getHighlightColorString() {
      return "000000";
    }

    @Override
    protected String getTreeSelectionColorString() {
      return "000000";
    }

    @Override
    protected String getNotificationsColorString() {
      return "000000";
    }
  };
  private final MTAbstractTheme lightAbstractTheme = new MTAbstractTheme(false) {
    @Override
    protected String getBackgroundColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getForegroundColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getTextColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getSelectionBackgroundColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getSelectionForegroundColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getButtonColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getSecondaryBackgroundColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getDisabledColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getContrastColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getTableSelectedColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getSecondBorderColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getHighlightColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getTreeSelectionColorString() {
      return "FFFFFF";
    }

    @Override
    protected String getNotificationsColorString() {
      return "FFFFFF";
    }

    @Override
    public boolean isCustom() {
      return true;
    }
  };

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();

    // Because of the annoying defaults clear
    UIManager.put("darcula.background", new ColorUIResource(0x3c3f41));
    UIManager.put("darcula.foreground", new ColorUIResource(0xbbbbbb));
    UIManager.put("darcula.primary", new ColorUIResource(0x3c3f41));
    UIManager.put("darcula.contrastColor", new ColorUIResource(0x262626));
    UIManager.put("intellijlaf.background", new ColorUIResource(0xf2f2f2));
    UIManager.put("intellijlaf.foreground", new ColorUIResource(0x000000));
    UIManager.put("intellijlaf.primary", new ColorUIResource(0xe8e8e8));
    UIManager.put("intellijlaf.contrastColor", new ColorUIResource(0xEEEEEE));
  }

  @Override
  public void tearDown() throws Exception {
    MTConfig.getInstance().resetSettings();
    MTThemeManager.getInstance().activate();
    super.tearDown();
  }

  @Test
  public void testGetSelectionBackground() {
    // Verify the results
    assertEquals("should have custom theme selection background", MTCustomTheme.SELECTION_BACKGROUND,
        darkAbstractTheme.getSelectionBackground());
    assertEquals("should have light custom theme selection background", MTLightCustomTheme.SELECTION_BACKGROUND,
        lightAbstractTheme.getSelectionBackground());
  }

  @Test
  public void testGetDisabled() {
    // Verify the results
    assertEquals("should have custom theme disabled color", MTCustomTheme.DISABLED, darkAbstractTheme.getDisabled());
    assertEquals("should have light custom theme disabled color", MTLightCustomTheme.DISABLED, lightAbstractTheme.getDisabled());
  }

  @Test
  public void testActivateDarkLaf() {
    darkAbstractTheme.activate();
    assertTrue(LafManagerImpl.getTestInstance().getCurrentLookAndFeel().getClassName().contains("DarculaLaf"));
  }

  @Test
  public void testActivateLightLaf() {
    lightAbstractTheme.activate();
    assertTrue(LafManagerImpl.getTestInstance().getCurrentLookAndFeel().getClassName().contains("IntelliJLaf"));
  }

  @Test
  public void testIsCustom() {
    assertFalse("should not be custom", darkAbstractTheme.isCustom());
    assertTrue("should be custom", lightAbstractTheme.isCustom());
  }

  @Test
  public void testGetPrimaryColor() {
    assertEquals("should return material primary color", UIManager.getColor("material.primaryColor"), darkAbstractTheme.getPrimaryColor());
    assertEquals("should return material primary color", UIManager.getColor("material.primaryColor"), lightAbstractTheme.getPrimaryColor());
  }

  @Test
  public void testDarculaGetPrimaryColor() {
    // Setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    // Run the test
    final Color result = darkAbstractTheme.getPrimaryColor();

    // Verify the results
    assertEquals("should return darcula primary color", UIManager.getColor("darcula.primary"), result);
  }

  @Test
  public void testLightGetPrimaryColor() {
    // Setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    // Run the test
    final Color result = lightAbstractTheme.getPrimaryColor();

    // Verify the results
    assertEquals("should return light primary color", UIManager.getColor("intellijlaf.primary"), result);
  }

  @Test
  public void testGetBackgroundColor() {
    // Verify the results
    assertEquals("should return material background color", UIManager.getColor("material.background"),
        darkAbstractTheme.getBackgroundColor());
    assertEquals("should return material background color", UIManager.getColor("material.background"),
        lightAbstractTheme.getBackgroundColor());
  }

  @Test
  public void testDarculaGetBackgroundColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    // Run the test
    final Color result = darkAbstractTheme.getBackgroundColor();

    // Verify the results
    assertEquals("should return darcula background color", UIManager.getColor("darcula.background"), result);
  }

  @Test
  public void testLightGetBackgroundColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    // Run the test
    final Color result = lightAbstractTheme.getBackgroundColor();

    // Verify the results
    assertEquals("should return darcula background color", UIManager.getColor("intellijlaf.background"), result);
  }

  @Test
  public void testGetForegroundColor() {
    assertEquals("should return material foreground", UIManager.getColor("material.foreground"), darkAbstractTheme.getForegroundColor());
    assertEquals("should return material foreground", UIManager.getColor("material.foreground"), lightAbstractTheme.getForegroundColor());
  }

  @Test
  public void testDarculaGetForegroundColor() {
    // Run the test
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    final Color result = darkAbstractTheme.getForegroundColor();

    // Verify the results
    assertEquals("should return darcula foreground color", UIManager.getColor("darcula.foreground"), result);
  }

  @Test
  public void testLightGetForegroundColor() {
    // Run the test
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    final Color result = lightAbstractTheme.getForegroundColor();

    // Verify the results
    assertEquals("should return darcula foreground color", UIManager.getColor("intellijlaf.foreground"), result);
  }

  @Test
  public void testGetContrastColor() {
    assertEquals("should return material contrast", UIManager.getColor("material.contrast"), darkAbstractTheme.getContrastColor());
    assertEquals("should return material contrast", UIManager.getColor("material.contrast"), lightAbstractTheme.getContrastColor());
  }

  @Test
  public void testDarculaGetContrastColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    // Run the test
    final Color result = darkAbstractTheme.getContrastColor();

    // Verify the results
    assertEquals("should return default contrast", UIManager.getColor("darcula.contrastColor"), result);
  }

  @Test
  public void testLightGetContrastColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    // Run the test
    final Color result = lightAbstractTheme.getContrastColor();

    // Verify the results
    assertEquals("should return default contrast", UIManager.getColor("intellijlaf.contrastColor"), result);
  }
}
