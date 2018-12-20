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

import com.chrisrm.idea.themes.themes.MTAbstractTheme;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.ui.JBColor;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Objects;


@SuppressWarnings({"HardCodedStringLiteral",
    "DuplicateStringLiteralInspection",
    "AnonymousInnerClassMayBeStatic",
    "AnonymousInnerClassWithTooManyMethods",
    "OverlyComplexAnonymousInnerClass"})
public class MTAbstractThemeTest extends MTFixtureTestCase {

  private final MTAbstractTheme darkAbstractTheme = new MTAbstractTheme() {
    private static final long serialVersionUID = 4461161871442463957L;

    @Override
    public ColorUIResource getBackgroundColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getForegroundColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getTextColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getSelectionBackgroundColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getSelectionForegroundColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getButtonColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getSecondaryBackgroundColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getDisabledColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getContrastColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getTableSelectedColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getSecondBorderColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getHighlightColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getTreeSelectionColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getNotificationsColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getAccentColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public ColorUIResource getExcludedColorResource() {
      return new ColorUIResource(0x000000);
    }

    @Override
    public String getThemeId() {
      return "test";
    }

    @Override
    public int getOrder() {
      return 0;
    }
  };
  private final MTAbstractTheme lightAbstractTheme = new MTAbstractTheme() {
    private static final long serialVersionUID = 257303057135163919L;

    @Override
    public ColorUIResource getBackgroundColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getForegroundColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getTextColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getSelectionBackgroundColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getSelectionForegroundColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getButtonColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getSecondaryBackgroundColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getDisabledColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getContrastColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getTableSelectedColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getSecondBorderColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getHighlightColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getTreeSelectionColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getNotificationsColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public boolean isCustom() {
      return true;
    }

    @Override
    public ColorUIResource getAccentColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public ColorUIResource getExcludedColorResource() {
      return new ColorUIResource(0xFFFFFF);
    }

    @Override
    public String getThemeId() {
      return "test";
    }

    @Override
    public int getOrder() {
      return 0;
    }
  };

  @Override
  @Before
  public final void setUp() throws Exception {
    super.setUp();
  }

  @Override
  public final void tearDown() throws Exception {
    MTConfig.getInstance().resetSettings();
    MTThemeManager.activate();
    super.tearDown();
  }

  @Test
  public final void testGetSelectionBackground() {
    // Verify the results
    Assert.assertThat("should have custom theme selection background", darkAbstractTheme.getSelectionBackgroundColorResource(),
        Matchers.is(darkAbstractTheme.getSelectionBackgroundColorResource()));
    Assert.assertThat("should have light custom theme selection background", lightAbstractTheme.getSelectionBackgroundColorResource(),
        Matchers.is(lightAbstractTheme.getSelectionBackgroundColorResource()));
  }

  @Test
  public final void testGetDisabled() {
    // Verify the results
    Assert.assertThat("should have custom theme disabled color", darkAbstractTheme.getDisabledColorResource(), Matchers.is("2E3C43"));
    Assert.assertThat("should have light custom theme disabled color", lightAbstractTheme.getDisabledColorResource(), Matchers.is("eae8e8"
    ));
  }

  @Test
  public final void testActivateDarkLaf() {
    darkAbstractTheme.activate();
    Assert.assertThat("Should activate Dark LAF",
        Objects.requireNonNull(LafManagerImpl.getTestInstance().getCurrentLookAndFeel()).getClassName(),
        Matchers.containsString("DarculaLaf"));
    Assert.assertThat("Should not be bright", JBColor.isBright(), Matchers.is(false));
    Assert.assertThat("Should be wearing the Dark Laf", UIManager.getLookAndFeel().getDescription(), Matchers.containsString("Dark " +
        "Material"));
  }

  @Test
  public final void testActivateLightLaf() {
    lightAbstractTheme.activate();
    Assert.assertThat("Should activate Light LAF",
        Objects.requireNonNull(LafManagerImpl.getTestInstance().getCurrentLookAndFeel()).getClassName(),
        Matchers.containsString("IntelliJLaf"));
    Assert.assertThat("Should be bright", JBColor.isBright(), Matchers.is(true));
    Assert.assertThat("Should be wearing the Light Laf", UIManager.getLookAndFeel().getDescription(), Matchers.containsString("Light " +
        "Material"));
  }

  @Test
  public final void testActivate() {
    final Object oldColor = UIManager.get("material.background");
    darkAbstractTheme.activate();
    final Color newColor = UIManager.getColor("material.background");
    Assert.assertThat("It should have activated the theme resources", oldColor, Matchers.not(Matchers.sameInstance(newColor)));
  }

  @Test
  public final void testIsCustom() {
    Assert.assertThat("dark theme should not be custom", darkAbstractTheme.isCustom(), Matchers.is(false));
    Assert.assertThat("light theme should be custom", lightAbstractTheme.isCustom(), Matchers.is(true));
  }

  @Test
  public final void testGetPrimaryColor() {
    Assert.assertThat("should return dark material primary color", darkAbstractTheme.getPrimaryColor(), Matchers.is(UIManager.getColor(
        "material" +
            ".primaryColor")));
    Assert.assertThat("should return light material primary color", lightAbstractTheme.getPrimaryColor(), Matchers.is(UIManager.getColor(
        "material" +
            ".primaryColor")));
  }

  @Test
  public final void testDarculaGetPrimaryColor() {
    // Setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    // Run the test
    final Color result = darkAbstractTheme.getPrimaryColor();

    // Verify the results
    Assert.assertThat("should return darcula primary color", result, Matchers.is(UIManager.getColor("darcula.primary")));
  }

  @Test
  public final void testLightGetPrimaryColor() {
    // Setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    // Run the test
    final Color result = lightAbstractTheme.getPrimaryColor();

    // Verify the results
    Assert.assertThat("should return light primary color", result, Matchers.is(UIManager.getColor("intellijlaf.primary")));
  }

  @Test
  public final void testGetBackgroundColor() {
    // Verify the results
    Assert.assertThat("should return dark material background color", darkAbstractTheme.getBackgroundColor(),
        Matchers.is(UIManager.getColor(
            "material.background")));
    Assert.assertThat("should return light material background color", lightAbstractTheme.getBackgroundColor(),
        Matchers.is(UIManager.getColor(
            "material.background")));
  }

  @Test
  public final void testDarculaGetBackgroundColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    // Run the test
    final Color result = darkAbstractTheme.getBackgroundColor();

    // Verify the results
    Assert.assertThat("should return darcula background color", result, Matchers.is(UIManager.getColor("darcula.background")));
  }

  @Test
  public final void testLightGetBackgroundColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    // Run the test
    final Color result = lightAbstractTheme.getBackgroundColor();

    // Verify the results
    Assert.assertThat("should return darcula background color", result, Matchers.is(UIManager.getColor("intellijlaf.background")));
  }

  @Test
  public final void testGetForegroundColor() {
    Assert.assertThat("should return dark material foreground", darkAbstractTheme.getForegroundColor(), Matchers.is(UIManager.getColor(
        "material" +
            ".foreground")));
    Assert.assertThat("should return light material foreground", lightAbstractTheme.getForegroundColor(), Matchers.is(UIManager.getColor(
        "material" +
            ".foreground")));
  }

  @Test
  public final void testDarculaGetForegroundColor() {
    // Run the test
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    final Color result = darkAbstractTheme.getForegroundColor();

    // Verify the results
    Assert.assertThat("should return darcula foreground color", result, Matchers.is(UIManager.getColor("darcula.foreground")));
  }

  @Test
  public final void testLightGetForegroundColor() {
    // Run the test
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    final Color result = lightAbstractTheme.getForegroundColor();

    // Verify the results
    Assert.assertThat("should return darcula foreground color", result, Matchers.is(UIManager.getColor("intellijlaf.foreground")));
  }

  @Test
  public final void testGetContrastColor() {
    Assert.assertThat("should return dark material contrast", darkAbstractTheme.getContrastColor(), Matchers.is(UIManager.getColor(
        "material" +
            ".contrast")));
    Assert.assertThat("should return light material contrast", lightAbstractTheme.getContrastColor(), Matchers.is(UIManager.getColor(
        "material" +
            ".contrast")));
  }

  @Test
  public final void testDarculaGetContrastColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    darkAbstractTheme.activate();

    // Run the test
    final Color result = darkAbstractTheme.getContrastColor();

    // Verify the results
    Assert.assertThat("should return default contrast", result, Matchers.is(UIManager.getColor("darcula.contrastColor")));
  }

  @Test
  public final void testLightGetContrastColor() {
    // setup
    MTConfig.getInstance().setIsMaterialTheme(false);
    lightAbstractTheme.activate();

    // Run the test
    final Color result = lightAbstractTheme.getContrastColor();

    // Verify the results
    Assert.assertThat("should return default contrast", result, Matchers.is(UIManager.getColor("intellijlaf.contrastColor")));
  }
}
