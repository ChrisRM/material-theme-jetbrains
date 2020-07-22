/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 - 2020 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.UITheme;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.util.Couple;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.GuiUtils;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.listeners.MTTopics;
import com.mallowigi.idea.themes.MTThemeFacade;
import com.mallowigi.idea.themes.MTThemes;
import com.mallowigi.idea.themes.lists.AccentResources;
import com.mallowigi.idea.themes.lists.FontResources;
import com.mallowigi.idea.themes.models.MTThemeable;
import com.mallowigi.idea.utils.MTChangeLAFAnimator;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import sun.awt.AppContext;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Locale;

/**
 * Manages appearance settings
 */
@SuppressWarnings({"ClassWithTooManyMethods",
  "DuplicateStringLiteralInspection",
  "UtilityClassCanBeEnum",
  "UtilityClass"})
public final class MTThemeManager implements Disposable {

  /**
   * The constant DEFAULT_FONT.
   */
  @NonNls
  public static final String DEFAULT_FONT = "Roboto";
  /**
   * The constant DEFAULT_SIDEBAR_HEIGHT.
   */
  private static final int DEFAULT_SIDEBAR_HEIGHT = 28;
  /**
   * The constant DEFAULT_INDENT.
   */
  private static final int DEFAULT_INDENT = 6;
  /**
   * The constant DEFAULT_FONT_SIZE.
   */
  private static final int DEFAULT_FONT_SIZE = JBUI.scale(13);
  /**
   * The constant DEFAULT_MONO_FONT.
   */
  @NonNls
  private static final String DEFAULT_MONO_FONT = "Fira Code";
  @NonNls
  private static final String RETINA = "@2x.css";
  @NonNls
  private static final String NON_RETINA = ".css";
  @NonNls
  private static final String DARCULA = "darcula";
  private static final MTConfig CONFIG = MTConfig.getInstance();

  /**
   * Instantiates a new Mt theme manager.
   */
  private MTThemeManager() {
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static MTThemeManager getInstance() {
    return ServiceManager.getService(MTThemeManager.class);
  }

  //region Action Toggles

  public static void toggleColoredDirs() {
    CONFIG.setUseColoredDirectories(!CONFIG.isUseColoredDirectories());
    updateFileIcons();
  }

  /**
   * Set contrast and reactivate theme
   */
  public static void toggleContrast() {
    CONFIG.setContrastMode(!CONFIG.isContrastMode());

    applyContrast(true);
  }

  /**
   * Toggle high contrast.
   */
  public static void toggleHighContrast() {
    CONFIG.setHighContrast(!CONFIG.isHighContrast());
    activate();
  }

  /**
   * Toggle compact status bar.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactStatusBar() {
    final boolean compactStatusBar = CONFIG.isCompactStatusBar();
    CONFIG.setCompactStatusBar(!compactStatusBar);

    applyCompactToolWindowHeaders();
  }

  private static void applyCompactToolWindowHeaders() {
    if (CONFIG.isCompactStatusBar()) {
      UIManager.put("ToolWindow.tab.verticalPadding", JBUI.scale(0));
    } else {
      UIManager.put("ToolWindow.tab.verticalPadding", JBUI.scale(5));
    }
  }

  /**
   * Toggle compact sidebar.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactSidebar() {
    final boolean isCompactSidebar = CONFIG.isCompactSidebar();
    CONFIG.setCompactSidebar(!isCompactSidebar);

    applyCompactSidebar(true);
  }

  /**
   * Toggle compact dropdowns.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactDropdowns() {
    final boolean isCompactDropdowns = CONFIG.isCompactDropdowns();
    CONFIG.setCompactDropdowns(!isCompactDropdowns);

    UIReplacer.patchUI();
  }

  /**
   * Toggle compact menus.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactMenus() {
    final boolean isCompact = CONFIG.isCompactMenus();
    CONFIG.setCompactMenus(!isCompact);

    applyMenusHeight();
    UIReplacer.patchUI();
  }

  /**
   * Compact table cells
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactTableCells() {
    final boolean isCompact = CONFIG.isCompactTables();
    CONFIG.setCompactTables(!isCompact);

    reloadUI();
  }

  /**
   * Toggle material fonts.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleMaterialFonts() {
    final boolean useMaterialFonts = CONFIG.isUseMaterialFont2();
    CONFIG.setUseMaterialFont2(!useMaterialFonts);

    applyFonts();
  }

  /**
   * Toggle material fonts.
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleMaterialWallpapers() {
    CONFIG.setUseMaterialWallpapers(!CONFIG.isUseMaterialWallpapers());
    CONFIG.fireChanged();
  }

  /**
   * Toggle upper case tabs.
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleUpperCaseTabs() {
    CONFIG.setUpperCaseTabs(!CONFIG.isUpperCaseTabs());
    CONFIG.fireChanged();
  }

  /**
   * Toggle status bar indicator.
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleStatusBarIndicator() {
    CONFIG.setStatusBarTheme(!CONFIG.isStatusBarTheme());
    CONFIG.fireChanged();
  }

  /**
   * Toggle override accent color
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleOverrideAccent() {
    CONFIG.setOverrideAccentColor(!CONFIG.isOverrideAccentColor());
    CONFIG.fireChanged();
  }
  //endregion

  /**
   * Toggle project frame
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleProjectFrame() {
    CONFIG.setUseProjectFrame(!CONFIG.isUseProjectFrame());
    CONFIG.fireChanged();
  }

  /**
   * Toggle accent mode
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleAccentMode() {
    CONFIG.setAccentMode(!CONFIG.isAccentMode());
    CONFIG.fireChanged();
  }
  //endregion

  //region File Icons support

  /**
   * Update file icons.
   */
  private static void updateFileIcons() {
    GuiUtils.invokeLaterIfNeeded(() -> {
      final Application app = ApplicationManager.getApplication();
      app.runWriteAction(() -> FileTypeManagerEx.getInstanceEx().fireFileTypesChanged());
      app.runWriteAction(ActionToolbarImpl::updateAllToolbarsImmediately);
    }, ModalityState.NON_MODAL);
  }
  //endregion

  //region Theme activation and deactivation

  /**
   * Activate selected theme or deactivate current
   */
  public static void activate() {
    final MTThemeFacade mtTheme = CONFIG.getSelectedTheme();
    activate(mtTheme, true);
  }

  /**
   * Specify whether to activate with color scheme
   */
  @SuppressWarnings("SameParameterValue")
  static void activateWithColorScheme(final boolean withColorScheme) {
    final MTThemeFacade mtTheme = CONFIG.getSelectedTheme();
    activate(mtTheme, withColorScheme);
  }

  /**
   * Activate a theme
   *
   * @param themeId theme id
   * @param isDark  dark
   * @param name    name
   */
  static void activateLAF(@NonNls final String themeId, final boolean isDark, @NonNls final String name, final boolean switchColorScheme) {
    final MTThemeFacade themeFor = MTThemes.getThemeFor(themeId);
    if (themeFor != null) {
      activate(themeFor, switchColorScheme);
    } else {
      final MTThemeFacade mtTheme = MTThemes.NATIVE;
      mtTheme.setIsDark(isDark);
      mtTheme.setThemeName(name);
      activate(mtTheme, switchColorScheme);
    }
  }

  /**
   * Activate a Look and Feel
   *
   * @param theme UITheme
   */
  static void activateLAF(final UITheme theme, final boolean switchColorScheme) {
    activateLAF(theme.getId(), theme.isDark(), theme.getName(), switchColorScheme);
  }

  /**
   * Activate theme and switch color scheme
   *
   * @param mtTheme           the mt theme
   * @param switchColorScheme whether to switch color scheme
   */
  public static void activate(final MTThemeFacade mtTheme, final boolean switchColorScheme) {
    MTThemeFacade newTheme = mtTheme;
    if (newTheme == null) {
      newTheme = MTThemes.OCEANIC;
    }

    CONFIG.setSelectedTheme(newTheme);

    newTheme.getTheme().activate();

    // Change color scheme to the theme's
    switchScheme(newTheme, switchColorScheme);

    // Save a reference to the theme
    IconLoader.clearCache();

    // apply different settings
    applyContrast(false);
    applyCompactSidebar(false);
    applyCustomTreeIndent();
    applyMenusHeight();
    applyAccents(false);
    applyFonts();
    applyCompactToolWindowHeaders();

    // Documentation styles
    patchStyledEditorKit();

    // Monochrome filter and co
    LafManager.getInstance().updateUI();

    // Custom UI Patches
    UIReplacer.patchUI();

    fireThemeChanged(newTheme);
  }

  /**
   * New way of switching themes
   */
  @SuppressWarnings("CallToSuspiciousStringMethod")
  public static void setLookAndFeel(final MTThemeFacade selectedTheme) {
    // Find LAF theme and trigger a theme change
    final LafManager lafManager = LafManager.getInstance();
    final UIManager.LookAndFeelInfo lafInfo = ContainerUtil.find(lafManager.getInstalledLookAndFeels(),
      lookAndFeelInfo -> lookAndFeelInfo.getName().equals(selectedTheme.getThemeName()));

    MTChangeLAFAnimator.showSnapshot();
    if (lafInfo != null) {
      lafManager.setCurrentLookAndFeel(lafInfo);
    } else {
      // good ol' shit
      activate(selectedTheme, true);
    }
    SwingUtilities.invokeLater(MTChangeLAFAnimator::hideSnapshotWithAnimation);

  }

  /**
   * Switch the color scheme to the current theme's
   *
   * @param mtTheme           the current theme
   * @param switchColorScheme whether to switch color scheme
   */
  private static void switchScheme(final MTThemeFacade mtTheme, final boolean switchColorScheme) {
    final EditorColorsManager editorColorsManager = EditorColorsManager.getInstance();
    if (switchColorScheme) {
      final EditorColorsScheme themeScheme = editorColorsManager.getScheme(mtTheme.getThemeColorScheme());
      if (themeScheme != null) {
        editorColorsManager.setGlobalScheme(themeScheme);
      }
    }
    // Need to trigger a change otherwise the ui will get stuck. Yes this sucks
    final EditorColorsScheme globalScheme = editorColorsManager.getGlobalScheme();
    editorColorsManager.setGlobalScheme(editorColorsManager.getScheme(MTUiUtils.DARCULA));
    editorColorsManager.setGlobalScheme(globalScheme);
  }

  /**
   * Apply accents.
   */
  @SuppressWarnings("MethodWithMultipleLoops")
  public static void applyAccents(final boolean fireEvent) {
    final Color accentColor = ColorUtil.fromHex(CONFIG.getAccentColor());
    final Color transparentAccentColor = ColorUtil.toAlpha(accentColor, 70);

    for (final String resource : AccentResources.ACCENT_RESOURCES) {
      UIManager.put(resource, accentColor);
    }

    for (final String resource : AccentResources.ACCENT_TRANSPARENT_RESOURCES) {
      UIManager.put(resource, transparentAccentColor);
    }

    // Accent mode
    CONFIG.getSelectedTheme().applyAccentMode();

    // Scrollbars management
    applyScrollbars(accentColor);

    patchStyledEditorKit();

    if (fireEvent) {
      fireAccentChanged(accentColor);
    }
  }

  @SuppressWarnings("MethodWithMultipleLoops")
  private static void applyScrollbars(final Color accentColor) {
    final Color transColor = ColorUtil.toAlpha(accentColor, 50);
    final Color hoverColor = ColorUtil.toAlpha(accentColor, 75);

    // IDE scrollbars
    final Couple<Color> scrollbarColors = getScrollbarColors(accentColor, transColor, hoverColor);
    if (scrollbarColors != null) { //null unless accent scrollbars is on
      final Color scrollbarColor = scrollbarColors.getFirst();
      final Color scrollbarHoverColor = scrollbarColors.getSecond();

      for (final String resource : AccentResources.SCROLLBAR_RESOURCES) {
        UIManager.put(resource, scrollbarColor);
      }
      for (final String resource : AccentResources.SCROLLBAR_HOVER_RESOURCES) {
        UIManager.put(resource, scrollbarHoverColor);
      }
    }
  }

  @Nullable
  private static Couple<Color> getScrollbarColors(final Color accentColor, final Color transColor, final Color hoverColor) {
    // Scrollbars
    if (CONFIG.isAccentScrollbars()) {
      return CONFIG.isThemedScrollbars() ?
             new Couple<>(transColor, hoverColor) :
             new Couple<>(hoverColor, accentColor);
    }
    return null;
  }

  private static void fireThemeChanged(final MTThemeFacade newTheme) {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(MTTopics.THEMES)
                      .themeChanged(newTheme);
  }

  private static void fireAccentChanged(final Color accentColorColor) {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(MTTopics.ACCENTS)
                      .accentChanged(accentColorColor);
  }

  //endregion

  // region Fonts

  /**
   * Apply custom fonts
   *
   * @param uiDefaults the defaults to override
   * @param fontFace   the font face
   * @param fontSize   the font size
   */
  @SuppressWarnings("Duplicates")
  private static void applySettingsFont(@NonNls final UIDefaults uiDefaults, final String fontFace, final int fontSize) {
    uiDefaults.put("Tree.ancestorInputMap", null);
    final FontUIResource font = UIUtil.getFontWithFallback(fontFace, Font.PLAIN, fontSize);

    final String editorFontName = AppEditorFontOptions.getInstance().getFontPreferences().getFontFamily();
    final String monospaceFont = ObjectUtils.notNull(editorFontName, DEFAULT_MONO_FONT);
    final FontUIResource monoFont = new FontUIResource(monospaceFont, Font.PLAIN, fontSize);

    // Keep old style and size
    for (final String fontResource : FontResources.FONT_RESOURCES) {
      final Font curFont = ObjectUtils.notNull(uiDefaults.getFont(fontResource), font);
      uiDefaults.put(fontResource, font.deriveFont(curFont.getStyle(), curFont.getSize()));
    }

    uiDefaults.put("PasswordField.font", monoFont);
    uiDefaults.put("TextArea.font", monoFont);
    uiDefaults.put("TextPane.font", font);
    uiDefaults.put("EditorPane.font", font);
  }

  @SuppressWarnings("Duplicates")
  private static void applyMaterialFonts(@NonNls final UIDefaults uiDefaults) {
    uiDefaults.put("Tree.ancestorInputMap", null);

    @NonNls final String language = Locale.getDefault().getLanguage();
    final boolean cjkLocale =
      (Locale.CHINESE.getLanguage().equals(language) ||
        Locale.JAPANESE.getLanguage().equals(language) ||
        Locale.KOREAN.getLanguage().equals(language));

    FontUIResource font = UIUtil.getFontWithFallback(DEFAULT_FONT, Font.PLAIN, DEFAULT_FONT_SIZE);
    if (cjkLocale) {
      font = UIUtil.getFontWithFallback(MTUiUtils.NOTO_SANS, Font.PLAIN, DEFAULT_FONT_SIZE);
    }

    final FontUIResource uiFont = font;
    final FontUIResource textFont = font;

    final String editorFontName = AppEditorFontOptions.getInstance().getFontPreferences().getFontFamily();
    final String monospaceFont = ObjectUtils.notNull(editorFontName, DEFAULT_MONO_FONT);
    final FontUIResource monoFont = new FontUIResource(monospaceFont, Font.PLAIN, DEFAULT_FONT_SIZE);

    // Keep old style and size
    for (final String fontResource : FontResources.FONT_RESOURCES) {
      final Font curFont = ObjectUtils.notNull(uiDefaults.getFont(fontResource), uiFont);
      uiDefaults.put(fontResource, uiFont.deriveFont(curFont.getStyle(), curFont.getSize()));
    }

    uiDefaults.put("PasswordField.font", monoFont);
    uiDefaults.put("TextArea.font", monoFont);
    uiDefaults.put("TextPane.font", textFont);
    uiDefaults.put("EditorPane.font", textFont);
  }

  /**
   * Apply fonts according to settings
   */
  @SuppressWarnings("FeatureEnvy")
  private static void applyFonts() {
    final UISettings uiSettings = UISettings.getInstance();
    @NonNls final UIDefaults lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();
    final int treeFontSize = JBUI.scale(CONFIG.getTreeFontSize());

    final boolean useMaterialFont = CONFIG.isUseMaterialFont2();

    if (uiSettings.getOverrideLafFonts()) {
      applySettingsFont(lookAndFeelDefaults, uiSettings.getFontFace(), uiSettings.getFontSize());
    } else if (useMaterialFont) {
      applyMaterialFonts(lookAndFeelDefaults);
    } else {
      if (SystemInfo.isMacOSYosemite) {
        LafManagerImpl.installMacOSXFonts(lookAndFeelDefaults);
      }
    }

    if (CONFIG.isTreeFontSizeEnabled()) {
      // Tree font size
      final Font font = lookAndFeelDefaults.getFont("Tree.font");
      lookAndFeelDefaults.put("Tree.font", font.deriveFont((float) treeFontSize));
      LafManager.getInstance().updateUI();
    }
  }
  //endregion

  //region Contrast support

  /**
   * Apply contrast
   *
   * @param reloadUI if true, reload the ui
   */
  private static void applyContrast(final boolean reloadUI) {
    final boolean apply = CONFIG.isContrastMode();
    final MTThemeable mtTheme = CONFIG.getSelectedTheme().getTheme();
    mtTheme.applyContrast(apply);

    if (reloadUI) {
      reloadUI();
    }
  }

  //endregion

  //region Custom tree indents support

  /**
   * Apply custom tree indent
   */
  @SuppressWarnings("FeatureEnvy")
  private static void applyCustomTreeIndent() {

    if (CONFIG.isCustomTreeIndentEnabled()) {
      UIManager.put("Tree.leftChildIndent", CONFIG.getLeftTreeIndent());
      UIManager.put("Tree.rightChildIndent", CONFIG.getRightTreeIndent());
    } else {
      UIManager.put("Tree.leftChildIndent", (DEFAULT_INDENT / 2) + JBUI.scale(7));
      UIManager.put("Tree.rightChildIndent", (DEFAULT_INDENT / 2) + JBUI.scale(4));
    }
  }
  //endregion

  //region Compact Menus support

  /**
   * Apply custom tree indent
   */
  private static void applyMenusHeight() {
    if (CONFIG.isCompactMenus()) {
      UIManager.put("PopupMenuSeparator.height", 3);
      UIManager.put("PopupMenuSeparator.stripeIndent", 1);
    } else {
      UIManager.put("PopupMenuSeparator.height", 10);
      UIManager.put("PopupMenuSeparator.stripeIndent", 5);
    }
  }
  //endregion

  //region Compact Sidebar support

  /**
   * Use compact sidebar option
   */
  private static void applyCompactSidebar(final boolean reloadUI) {
    final boolean isCustomSidebarHeight = CONFIG.isCompactSidebar();
    final int customSidebarHeight = CONFIG.getCustomSidebarHeight();
    final int rowHeight = isCustomSidebarHeight ? JBUI.scale(customSidebarHeight) : JBUI.scale(DEFAULT_SIDEBAR_HEIGHT);
    UIManager.put("Tree.rowHeight", rowHeight);

    if (reloadUI) {
      reloadUI();
    }
  }

  //endregion

  //region Accents supports

  /**
   * Override patch style editor kit for custom accent support
   */
  @SuppressWarnings({
    "StringConcatenation",
    "OverlyBroadCatchBlock"})
  private static void patchStyledEditorKit() {
    @NonNls final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    final MTThemeable selectedTheme = CONFIG.getSelectedTheme().getTheme();

    // Load css
    final URL url = selectedTheme.getClass().getResource(selectedTheme.getId() + (JBUIScale.isUsrHiDPI() ? RETINA : NON_RETINA));
    StyleSheet styleSheet = UIUtil.loadStyleSheet(url);
    if (styleSheet == null) {
      final URL fallbackUrl = DarculaLaf.class.getResource(DARCULA + (JBUIScale.isUsrHiDPI() ? RETINA : NON_RETINA));
      styleSheet = UIUtil.loadStyleSheet(fallbackUrl);
    }

    // Add custom accent color
    assert styleSheet != null;
    final String accentColor = ColorUtil.toHex(MTUI.Panel.getLinkForeground());

    @NonNls final String css = "a, address, b { color: #%s; }";
    styleSheet.addRule(String.format(css, accentColor));
    UIManager.put("StyledEditorKit.JBDefaultStyle", styleSheet);
    defaults.put("StyledEditorKit.JBDefaultStyle", styleSheet);

    try {
      final Field keyField = HTMLEditorKit.class.getDeclaredField("DEFAULT_STYLES_KEY");
      keyField.setAccessible(true);
      AppContext.getAppContext().put(keyField.get(null), styleSheet);
    } catch (final Exception ignored) {
    }
  }
  //endregion

  //region Tabs Height support

  /**
   * Sets tabs height.
   *
   * @param newTabsHeight the new tabs height
   */
  public static void setTabsHeight(final int newTabsHeight) {
    CONFIG.setTabsHeight(newTabsHeight);
  }
  //endregion

  /**
   * Trigger a reloadUI event
   */
  private static void reloadUI() {
    applyFonts();

    DarculaInstaller.uninstall();
    if (UIUtil.isUnderDarcula()) {
      DarculaInstaller.install();
    }
    LafManager.getInstance().updateUI();
  }

  @Override
  public void dispose() {

  }
}
