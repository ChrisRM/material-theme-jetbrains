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

import com.chrisrm.idea.config.ui.ArrowsStyles;
import com.chrisrm.idea.config.ui.MTForm;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.chrisrm.idea.MTConfig.ACCENT_COLOR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MTConfigTest extends MTFixtureTestCase {

  @Test
  public void testNeedsRestart() {
    final MTForm form = defaultState();
    final boolean result = mtConfig.needsRestart(form);

    assertFalse(result);
    when(form.getIsMaterialDesign()).thenReturn(false);
    assertTrue(mtConfig.needsRestart(form));
  }

  @NotNull
  private MTForm defaultState() {
    // Setup
    final MTForm form = mock(MTForm.class);
    // default state
    when(form.getIsMaterialDesign()).thenReturn(true);
    when(form.getIsUpperCaseButtons()).thenReturn(true);
    when(form.isThemedScrollbars()).thenReturn(true);
    when(form.isUseMaterialIcons()).thenReturn(true);
    when(form.getIsMaterialTheme()).thenReturn(true);
    when(form.isAccentScrollbars()).thenReturn(true);
    return form;
  }

  @Test
  public void testGetSelectedTheme() {
    mtConfig.setSelectedTheme(MTThemes.DARKER);
    // Setup
    final MTThemeFacade expectedResult = MTThemes.DARKER;

    // Run the test
    final MTThemeFacade result = mtConfig.getSelectedTheme();

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testSetSelectedTheme() {
    // Setup
    final MTThemeFacade selectedTheme = MTThemes.PALENIGHT;

    // Run the test
    mtConfig.setSelectedTheme(selectedTheme);

    // Verify the results
    Assert.assertEquals(mtConfig.getSelectedTheme(), selectedTheme);
  }

  @Test
  public void testGetState() {
    // Setup
    final MTConfig expectedResult = mtConfig;

    // Run the test
    final MTConfig result = mtConfig.getState();

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testGetHighlightColor() {
    // Setup
    final Color expectedResult = ColorUtil.fromHex(ACCENT_COLOR);

    // Run the test
    final Color result = mtConfig.getHighlightColor();

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testSetHighlightColor() {
    // Setup
    final Color color = ColorUtil.fromHex("AAFFCC");

    // Run the test
    mtConfig.setHighlightColor(color);

    // Verify the results
    assertEquals(mtConfig.getHighlightColor(), color);
  }

  @Test
  public void testIsHighlightColorChanged() {
    // Setup
    final Color color = ColorUtil.fromHex("AAFFCC");
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isHighlightColorChanged(color);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testGetHighlightThickness() {
    // Setup
    final int expectedResult = 2;

    // Run the test
    final int result = mtConfig.getHighlightThickness();

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsHighlightColorEnabledChanged() {
    // Setup
    final boolean enabled = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isHighlightColorEnabledChanged(enabled);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testSetHighlightThickness() {
    // Setup
    int thickness = 2;

    // Run the test
    mtConfig.setHighlightThickness(thickness);

    // Verify the results
    assertEquals(mtConfig.getHighlightThickness(), thickness);

    thickness = 8;
    mtConfig.setHighlightThickness(thickness);
    assertThat(mtConfig.getHighlightThickness(), is(not(thickness)));
  }

  @Test
  public void testIsHighlightThicknessChanged() {
    // Setup
    final int thickness = 3;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isHighlightThicknessChanged(thickness);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsContrastModeChanged() {
    // Setup
    final boolean isContrastMode = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isContrastModeChanged(isContrastMode);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsMaterialDesignChanged() {
    // Setup
    final boolean isMaterialDesign = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isMaterialDesignChanged(isMaterialDesign);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsBoldTabsChanged() {
    // Setup
    final boolean isBoldTabs = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isBoldTabsChanged(isBoldTabs);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsAccentColorChanged() {
    // Setup
    final Color customAccentColor = ColorUtil.fromHex("AABBCC");
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isAccentColorChanged(customAccentColor);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsMaterialIconsChanged() {
    // Setup
    final boolean useMaterialIcons = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isMaterialIconsChanged(useMaterialIcons);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsUseProjectViewDecoratorsChanged() {
    // Setup
    final boolean useProjectViewDecorators = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isUseProjectViewDecoratorsChanged(useProjectViewDecorators);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsHideFileIconsChanged() {
    // Setup
    final boolean hideFileIcons = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isHideFileIconsChanged(hideFileIcons);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsCompactSidebarChanged() {
    // Setup
    final boolean compactSidebar = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isCompactSidebarChanged(compactSidebar);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testCustomSidebarHeightChanged() {
    // Setup
    final int customSidebarHeight = 20;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.customSidebarHeightChanged(customSidebarHeight);

    // Verify the results
    assertEquals(expectedResult, result);

  }

  @Test
  public void testSetCustomSidebarHeight() {
    // Setup
    int customSidebarHeight = 20;

    // Run the test
    mtConfig.setCustomSidebarHeight(customSidebarHeight);

    // Verify the results
    assertEquals(mtConfig.getCustomSidebarHeight(), customSidebarHeight);
    customSidebarHeight = 10;
    mtConfig.setCustomSidebarHeight(customSidebarHeight);

    // Verify the results
    assertThat(mtConfig.getCustomSidebarHeight(), not(is(customSidebarHeight)));
  }

  @Test
  public void testIsStatusBarThemeChanged() {
    // Setup
    final boolean statusBarTheme = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isStatusBarThemeChanged(statusBarTheme);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testGetTabsHeight() {
    // Setup
    final int expectedResult = 42;

    // Run the test
    final int result = mtConfig.getTabsHeight();

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsTabsHeightChanged() {
    // Setup
    final Integer tabsHeight = 34;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isTabsHeightChanged(tabsHeight);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsMaterialThemeChanged() {
    // Setup
    final boolean isMaterialTheme = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isMaterialThemeChanged(isMaterialTheme);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testGetCustomTreeIndent() {
    // Setup
    final int expectedResult = 6;

    // Run the test
    final int result = mtConfig.getCustomTreeIndent();

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testCustomTreeIndentChanged() {
    // Setup
    final int customTreeIndent = 2;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.customTreeIndentChanged(customTreeIndent);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsCustomTreeIndentChanged() {
    // Setup
    final boolean customTreeIndentEnabled = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isCustomTreeIndentChanged(customTreeIndentEnabled);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsThemedScrollbarsChanged() {
    // Setup
    final boolean themedScrollbars = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isThemedScrollbarsChanged(themedScrollbars);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsAccentScrollbarsChanged() {
    // Setup
    final boolean accentScrollbars = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isAccentScrollbarsChanged(accentScrollbars);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsCompactStatusBarChanged() {
    // Setup
    final boolean compactStatusBar = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isCompactStatusBarChanged(compactStatusBar);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsCompactTablesChanged() {
    // Setup
    final boolean compactTables = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isCompactTablesChanged(compactTables);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsUpperCaseTabsChanged() {
    // Setup
    final boolean upperCaseTabs = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isUpperCaseTabsChanged(upperCaseTabs);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsDarkTitleBarChanged() {
    // Setup
    final boolean darkTitleBar = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isDarkTitleBarChanged(darkTitleBar);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsArrowsStyleChanged() {
    // Setup
    final ArrowsStyles arrowsStyle = ArrowsStyles.PLUSMINUS;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isArrowsStyleChanged(arrowsStyle);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsUseMaterialFontChanged() {
    // Setup
    final boolean useMaterialFont = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isUseMaterialFontChanged(useMaterialFont);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsTabOpacityChanged() {
    // Setup
    final int tabOpacity = 100;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isTabOpacityChanged(tabOpacity);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsCompactDropdownsChanged() {
    // Setup
    final boolean isCompactDropdowns = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isCompactDropdownsChanged(isCompactDropdowns);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsMonochromeIconsChanged() {
    // Setup
    final boolean isMonochromeIcons = true;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isMonochromeIconsChanged(isMonochromeIcons);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsUpperCaseButtonsChanged() {
    // Setup
    final boolean isUppercaseButtons = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isUpperCaseButtonsChanged(isUppercaseButtons);

    // Verify the results
    assertEquals(expectedResult, result);
  }

  @Test
  public void testIsDecoratedFoldersChanged() {
    // Setup
    final boolean decoratedFolders = false;
    final boolean expectedResult = true;

    // Run the test
    final boolean result = mtConfig.isDecoratedFoldersChanged(decoratedFolders);

    // Verify the results
    assertEquals(expectedResult, result);
  }

}
