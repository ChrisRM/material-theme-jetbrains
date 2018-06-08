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

import com.chrisrm.idea.themes.lists.ContrastResources;
import com.chrisrm.idea.ui.MTStatusBarBorder;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.ui.UIUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.swing.*;
import java.awt.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Messages.class})
public class MTThemeManagerTest extends MTFixtureTestCase {

  private MTThemeManager mtThemeManagerUnderTest;
  @Mock
  private Messages messages;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    mtThemeManagerUnderTest = MTThemeManager.getInstance();
    PowerMockito.mockStatic(Messages.class);
    PowerMockito.when(Messages.showYesNoDialog(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(1);

    MTConfig.getInstance().resetSettings();
  }

  @Test
  public void testToggleMaterialDesign() {
    // Run the test
    mtThemeManagerUnderTest.toggleMaterialDesign();

    // Verify the results
    assertFalse("It should disable Material Design", MTConfig.getInstance().getIsMaterialDesign());
  }

  @Test
  public void testNotChangeUntilRestart() {
    // Run the test
    mtThemeManagerUnderTest.toggleMaterialDesign();

    final String o = (String) UIManager.get("ButtonUI");
    assertEquals("It should not change back UI Elements until next restart", o, "com.chrisrm.idea.ui.MTButtonUI");
  }

  @Test
  public void testToggleProjectViewDecorators() {
    // Run the test
    mtThemeManagerUnderTest.toggleProjectViewDecorators();

    // Verify the results
    assertFalse("It should disable Project View Decorators", MTConfig.getInstance().isUseProjectViewDecorators());
  }

  @Test
  public void testToggleMaterialTheme() {
    // Run the test
    mtThemeManagerUnderTest.toggleMaterialTheme();

    // Verify the results
    assertFalse("It should disable Material Theme", MTConfig.getInstance().isMaterialTheme());
  }

  @Test
  public void testDisableMaterialTheme() {
    // Run the test
    mtThemeManagerUnderTest.toggleMaterialTheme();

    // Verify the results
    assertFalse("It should disable Material Theme", MTConfig.getInstance().isMaterialTheme());

    assertEquals("It should set the default darcula background",
        UIUtil.getPanelBackground(),
        UIManager.getColor("Panel.background")
    );
    assertEquals("It should set the default darcula foreground",
        UIUtil.getLabelForeground(),
        UIManager.getColor("Label.foreground"));
  }

  @Test
  public void testToggleContrast() {
    // Run the test
    mtThemeManagerUnderTest.toggleContrast();

    // Verify the results
    assertTrue("It should enable contrast", MTConfig.getInstance().getIsContrastMode());
  }

  @Test
  public void testApplyContrast() {
    // Run the test
    mtThemeManagerUnderTest.toggleContrast();
    final Color contrastColor = MTConfig.getInstance().getSelectedTheme().getTheme().getContrastColor();

    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
      Assert.assertEquals("Should set the contrast color to resource: " + resource,
          UIManager.getColor(resource),
          contrastColor);
    }
  }

  @Test
  public void testToggleCompactStatusBar() {
    // Run the test
    mtThemeManagerUnderTest.toggleCompactStatusBar();

    // Verify the results
    assertTrue("It should set compact status bars", MTConfig.getInstance().isCompactStatusBar());
  }

  @Test
  public void testApplyCompactStatusBar() {
    final MTStatusBarBorder border = (MTStatusBarBorder) UIManager.get("IdeStatusBar.border");
    assertTrue("It should not be compact borders", border.getInsets().top == MTStatusBarBorder.DEFAULT_PADDING);

    mtThemeManagerUnderTest.toggleCompactStatusBar();
    assertTrue("It should set compact borders", border.getInsets().top == 0);
  }

  @Test
  public void testToggleHideFileIcons() {
    // Run the test
    mtThemeManagerUnderTest.toggleHideFileIcons();

    // Verify the results
    assertTrue("It should set hidden file icons", MTConfig.getInstance().getHideFileIcons());
  }

  @Test
  public void testToggleMonochromeIcons() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleMonochromeIcons();

    // Verify the results
  }

  @Test
  public void testToggleCompactSidebar() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleCompactSidebar();

    // Verify the results
  }

  @Test
  public void testToggleCompactDropdowns() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleCompactDropdowns();

    // Verify the results
  }

  @Test
  public void testToggleMaterialIcons() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleMaterialIcons();

    // Verify the results
  }

  @Test
  public void testToggleMaterialFonts() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleMaterialFonts();

    // Verify the results
  }

  @Test
  public void testToggleUpperCaseTabs() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleUpperCaseTabs();

    // Verify the results
  }

  @Test
  public void testToggleStatusBarIndicator() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleStatusBarIndicator();

    // Verify the results
  }

  @Test
  public void testToggleDarkTitleBar() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.toggleDarkTitleBar();

    // Verify the results
  }

  @Test
  public void testUpdateFileIcons() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.updateFileIcons();

    // Verify the results
  }

  @Test
  public void testActivate() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.activate();

    // Verify the results
  }

  @Test
  public void testActivate1() {
    // Setup
    final MTThemeFacade mtTheme = null;

    // Run the test
    mtThemeManagerUnderTest.activate(mtTheme);

    // Verify the results
  }

  @Test
  public void testActivate2() {
    // Setup
    final MTThemeFacade mtTheme = null;
    final boolean switchColorScheme = false;

    // Run the test
    mtThemeManagerUnderTest.activate(mtTheme, switchColorScheme);

    // Verify the results
  }

  @Test
  public void testApplyAccents() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.applyAccents();

    // Verify the results
  }

  @Test
  public void testAskForRestart() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.askForRestart();

    // Verify the results
  }


  @Test
  public void testSetTabsHeight1() {
    // Setup
    final int newTabsHeight = 0;

    // Run the test
    mtThemeManagerUnderTest.setTabsHeight(newTabsHeight);

    // Verify the results
  }

  @Test
  public void testThemeTitleBar() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.themeTitleBar();

    // Verify the results
  }

  @Test
  public void testThemeMacTitleBar() {
    // Setup
    final boolean isDarkTitleOn = false;

    // Run the test
    mtThemeManagerUnderTest.themeMacTitleBar(isDarkTitleOn);

    // Verify the results
  }

  @Test
  public void testThemeWindowsTitleBar() {
    // Setup

    // Run the test
    mtThemeManagerUnderTest.themeWindowsTitleBar();

    // Verify the results
  }

  @Test
  public void testGetTitleColor() {
    // Setup
    final int expectedResult = 0;

    // Run the test
    final int result = mtThemeManagerUnderTest.getTitleColor();

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testGetInstance() {
    // Setup
    final MTThemeManager expectedResult = null;

    // Run the test
    final MTThemeManager result = MTThemeManager.getInstance();

    // Verify the results
    assertEquals(expectedResult, result);
  }
}
