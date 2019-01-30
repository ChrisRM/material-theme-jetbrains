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

import com.chrisrm.idea.icons.IconManager;
import com.chrisrm.idea.lafs.MTDarkLaf;
import com.chrisrm.idea.lafs.MTLafInstaller;
import com.chrisrm.idea.listeners.MTTopics;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.themes.MTThemeFacade;
import com.chrisrm.idea.themes.MTThemes;
import com.chrisrm.idea.themes.lists.AccentResources;
import com.chrisrm.idea.themes.lists.ContrastResources;
import com.chrisrm.idea.themes.lists.FontResources;
import com.chrisrm.idea.themes.models.MTThemeable;
import com.chrisrm.idea.utils.MTAccents;
import com.chrisrm.idea.utils.MTUI;
import com.chrisrm.idea.utils.MTUiUtils;
import com.chrisrm.idea.utils.WinRegistry;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.laf.IntelliJLaf;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Couple;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;
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
    "OverlyComplexClass",
    "UtilityClass"})
public final class MTThemeManager {

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
   * The constant DEFAULT_FONT.
   */
  private static final String DEFAULT_FONT = "Roboto";
  /**
   * The constant DEFAULT_MONO_FONT.
   */
  private static final String DEFAULT_MONO_FONT = "Fira Code";
  @NonNls
  private static final String RETINA = "@2x.css";
  @NonNls
  private static final String NON_RETINA = ".css";
  @NonNls
  private static final String DARCULA = "darcula";
  private static final MTConfig mtConfig = MTConfig.getInstance();

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

  /**
   * Toggle material design.
   */
  public static void toggleMaterialDesign() {
    mtConfig.setIsMaterialDesign(!mtConfig.isMaterialDesign());

    askForRestart();
  }

  /**
   * Toggle project view decorators.
   */
  public static void toggleProjectViewDecorators() {
    mtConfig.setUseProjectViewDecorators(!mtConfig.isUseProjectViewDecorators());
    updateFileIcons();
  }

  /**
   * Toggle material theme.
   */
  public static void toggleMaterialTheme() {
    mtConfig.setIsMaterialTheme(!mtConfig.isMaterialTheme());
    activate();
  }

  /**
   * Set contrast and reactivate theme
   */
  public static void toggleContrast() {
    mtConfig.setIsContrastMode(!mtConfig.isContrastMode());

    applyContrast(true);
  }

  /**
   * Toggle high contrast.
   */
  public static void toggleHighContrast() {
    mtConfig.setIsHighContrast(!mtConfig.isHighContrast());
    activate();
  }

  /**
   * Toggle compact status bar.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactStatusBar() {
    final boolean compactStatusBar = mtConfig.isCompactStatusBar();
    mtConfig.setIsCompactStatusBar(!compactStatusBar);

    applyCompactToolWindowHeaders();
  }

  private static void applyCompactToolWindowHeaders() {
    if (mtConfig.isCompactStatusBar()) {
      UIManager.put("ToolWindow.tab.verticalPadding", JBUI.scale(0));
    } else {
      UIManager.put("ToolWindow.tab.verticalPadding", JBUI.scale(5));
    }
  }

  /**
   * Toggle hide file icons.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleHideFileIcons() {
    final boolean hideFileIcons = mtConfig.isHideFileIcons();
    mtConfig.setHideFileIcons(!hideFileIcons);

    updateFileIcons();
  }

  /**
   * Toggle monochrome icons.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleMonochromeIcons() {
    final boolean monochromeIcons = mtConfig.isMonochromeIcons();
    mtConfig.setMonochromeIcons(!monochromeIcons);

    IconManager.applyFilter();
    updateFileIcons();
  }

  /**
   * Toggle compact sidebar.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactSidebar() {
    final boolean isCompactSidebar = mtConfig.isCompactSidebar();
    mtConfig.setCompactSidebar(!isCompactSidebar);

    applyCompactSidebar(true);
  }

  /**
   * Toggle compact dropdowns.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactDropdowns() {
    final boolean isCompactDropdowns = mtConfig.isCompactDropdowns();
    mtConfig.setCompactDropdowns(!isCompactDropdowns);

    UIReplacer.patchUI();
  }

  /**
   * Toggle compact menus.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactMenus() {
    final boolean isCompact = mtConfig.isCompactMenus();
    mtConfig.setIsCompactMenus(!isCompact);

    applyMenusHeight();
    UIReplacer.patchUI();
  }

  /**
   * Compact table cells
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleCompactTableCells() {
    final boolean isCompact = mtConfig.isCompactTables();
    mtConfig.setIsCompactTables(!isCompact);

    reloadUI();
  }

  /**
   * Toggle material icons.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleMaterialIcons() {
    final boolean useMaterialIcons = mtConfig.isUseMaterialIcons();
    mtConfig.setUseMaterialIcons(!useMaterialIcons);

    updateFileIcons();
  }

  /**
   * Toggle material file icons.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleMaterialFileIcons() {
    final boolean useMaterialFileIcons = mtConfig.isFileIcons();
    mtConfig.setFileIcons(!useMaterialFileIcons);

    updateFileIcons();
  }

  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleMaterialPsiIcons() {
    final boolean isPsiIcons = mtConfig.isPsiIcons();
    mtConfig.setIsPsiIcons(!isPsiIcons);

    updateFileIcons();
  }

  /**
   * Toggle material fonts.
   */
  @SuppressWarnings("BooleanVariableAlwaysNegated")
  public static void toggleMaterialFonts() {
    final boolean useMaterialFonts = mtConfig.isUseMaterialFont();
    mtConfig.setUseMaterialFont(!useMaterialFonts);

    applyFonts();
  }

  /**
   * Toggle upper case tabs.
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleUpperCaseTabs() {
    mtConfig.setIsUpperCaseTabs(!mtConfig.isUpperCaseTabs());
    mtConfig.fireChanged();
  }

  /**
   * Toggle status bar indicator.
   */
  @SuppressWarnings("FeatureEnvy")
  public static void toggleStatusBarIndicator() {
    mtConfig.setIsStatusBarTheme(!mtConfig.isStatusBarTheme());
    mtConfig.fireChanged();
  }

  /**
   * Toggle dark title bar.
   */
  public static void toggleDarkTitleBar() {
    mtConfig.setDarkTitleBar(!mtConfig.isDarkTitleBar());
    themeTitleBar();
  }
  //endregion

  //region File Icons support

  /**
   * Update file icons.
   */
  static void updateFileIcons() {
    ApplicationManager.getApplication().runWriteAction(() -> {
      final FileTypeManagerEx instanceEx = FileTypeManagerEx.getInstanceEx();
      instanceEx.fireFileTypesChanged();
      ActionToolbarImpl.updateAllToolbarsImmediately();
    });
  }
  //endregion

  //region Theme activation and deactivation

  /**
   * Activate selected theme or deactivate current
   */
  public static void activate() {
    final MTThemeFacade mtTheme = mtConfig.getSelectedTheme();
    if (!mtConfig.isMaterialTheme()) {
      removeTheme(mtTheme);
      return;
    }

    activate(mtTheme, false);
  }

  public static void activate(final String themeId) {
    final MTThemeFacade themeFor = MTThemes.getThemeFor(themeId);
    if (themeFor != null) {
      activate(themeFor, false);
    }
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

    mtConfig.setSelectedTheme(newTheme);

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
    themeTitleBar();
    applyCompactToolWindowHeaders();

    // Documentation styles
    patchStyledEditorKit();

    // Monochrome filter and co
    IconManager.applyFilter();
    LafManager.getInstance().updateUI();

    // Custom UI Patches
    UIReplacer.patchUI();

    fireThemeChanged(newTheme);
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
  @SuppressWarnings("MagicNumber")
  public static void applyAccents(final boolean fireEvent) {
    final Color accentColor = ColorUtil.fromHex(mtConfig.getAccentColor());

    for (final String resource : AccentResources.ACCENT_RESOURCES) {
      UIManager.put(resource, accentColor);
    }

    // Scrollbars management
    applyScrollbars(accentColor);

    // override for transparency
    UIManager.put("Focus.color", ColorUtil.toAlpha(accentColor, 70));
    UIManager.put(MTUI.ActionButton.ACTION_BUTTON_HOVER_BACKGROUND, ColorUtil.toAlpha(accentColor, 70));
    UIManager.put(MTUI.ActionButton.ACTION_BUTTON_HOVER_BORDER_COLOR, ColorUtil.toAlpha(accentColor, 70));

    patchStyledEditorKit();

    if (fireEvent) {
      fireAccentChanged(accentColor);
    }
  }

  private static void applyScrollbars(Color accentColor) {
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

  public static Couple<Color> getScrollbarColors(final Color accentColor, final Color transColor, final Color hoverColor) {
    // Scrollbars
    if (mtConfig.isAccentScrollbars()) {
      return mtConfig.isThemedScrollbars() ?
             new Couple<>(transColor, hoverColor) :
             new Couple<>(hoverColor, accentColor);
    }
    return null;
  }

  /**
   * Ask for restart.
   */
  @SuppressWarnings("Duplicates")
  private static void askForRestart() {
    final String title = MaterialThemeBundle.message("mt.restartDialog.title");
    final String message = MaterialThemeBundle.message("mt.restartDialog.content");

    final int answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon());
    if (answer == Messages.YES) {
      MTUiUtils.restartIde();
    }
  }

  /**
   * Remove the Material Theme and install the native themes
   *
   * @param mtTheme current material theme
   */
  private static void removeTheme(final MTThemeFacade mtTheme) {
    try {
      resetContrast();

      // Still create the MT Look and Feels in order to retrieve some of the components
      if (UIUtil.isUnderDarcula()) {
        UIManager.setLookAndFeel(new DarculaLaf());
      } else {
        UIManager.setLookAndFeel(new IntelliJLaf());
      }
      final MTLafInstaller mtLafInstaller = new MTLafInstaller();
      mtLafInstaller.installMTDefaults(UIManager.getDefaults());

      JBColor.setDark(mtTheme.isDark());
      IconLoader.setUseDarkIcons(mtTheme.isDark());

      // We need this to update parts of the UI that do not change
      if (UIUtil.isUnderDarcula()) {
        DarculaInstaller.uninstall();
        DarculaInstaller.install();
      } else {
        DarculaInstaller.uninstall();
      }

      // Reset custom properties
      UIManager.put("material.primaryColor", null);
      UIManager.put("material.background", null);
      UIManager.put("material.foreground", null);
      UIManager.put("material.tab.borderColor", null);
      UIManager.put("material.tab.borderThickness", null);
      UIManager.put("material.contrast", null);

      // Apply other settings
      themeTitleBar();
      applyCompactSidebar(false);
      applyCustomTreeIndent();
      applyAccents(false);

      // Finally reapply Icon filters and UIReplacer patches
      LafManager.getInstance().updateUI();
      IconManager.applyFilter();
      UIReplacer.patchUI();
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
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
    final int treeFontSize = JBUI.scale(mtConfig.getTreeFontSize());

    final boolean useMaterialFont = mtConfig.isUseMaterialFont();

    if (uiSettings.getOverrideLafFonts()) {
      applySettingsFont(lookAndFeelDefaults, uiSettings.getFontFace(), uiSettings.getFontSize());
    } else if (useMaterialFont) {
      applyMaterialFonts(lookAndFeelDefaults);
    } else {
      if (SystemInfo.isMacOSYosemite) {
        LafManagerImpl.installMacOSXFonts(lookAndFeelDefaults);
      }
    }

    if (mtConfig.isTreeFontSizeEnabled()) {
      // Tree font size
      final Font font = lookAndFeelDefaults.getFont("Tree.font");
      lookAndFeelDefaults.put("Tree.font", font.deriveFont((float) treeFontSize));
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
    final boolean apply = mtConfig.isContrastMode();
    final MTThemeable mtTheme = mtConfig.getSelectedTheme().getTheme();
    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
      final Color contrastedColor = apply ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
      UIManager.put(resource, contrastedColor);
    }

    if (reloadUI) {
      reloadUI();
    }
  }

  /**
   * Remove all contrast properties
   */
  private static void resetContrast() {
    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
      UIManager.put(resource, null);
    }
  }
  //endregion

  //region Custom tree indents support

  /**
   * Apply custom tree indent
   */
  @SuppressWarnings("FeatureEnvy")
  private static void applyCustomTreeIndent() {
    final MTConfig mtConfig = MTThemeManager.mtConfig;

    if (mtConfig.isCustomTreeIndent()) {
      UIManager.put("Tree.leftChildIndent", mtConfig.getLeftTreeIndent());
      UIManager.put("Tree.rightChildIndent", mtConfig.getRightTreeIndent());
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
    final MTConfig mtConfig = MTThemeManager.mtConfig;

    if (mtConfig.isCompactMenus()) {
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
    final boolean isCustomSidebarHeight = mtConfig.isCompactSidebar();
    final int customSidebarHeight = mtConfig.getCustomSidebarHeight();
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
    final MTConfig mtConfig = MTThemeManager.mtConfig;
    final MTThemeable selectedTheme = mtConfig.getSelectedTheme().getTheme();

    // Load css
    final URL url = selectedTheme.getClass().getResource(selectedTheme.getId() + (JBUI.isUsrHiDPI() ? RETINA : NON_RETINA));
    StyleSheet styleSheet = UIUtil.loadStyleSheet(url);
    if (styleSheet == null) {
      final URL fallbackUrl = DarculaLaf.class.getResource(DARCULA + (JBUI.isUsrHiDPI() ? RETINA : NON_RETINA));
      styleSheet = UIUtil.loadStyleSheet(fallbackUrl);
    }

    // Add custom accent color
    assert styleSheet != null;
    final String accentColor = ObjectUtils.notNull(mtConfig.getAccentColor(), MTAccents.TURQUOISE.getHexColor());

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
    mtConfig.setTabsHeight(newTabsHeight);
  }
  //endregion

  /**
   * Trigger a reloadUI event
   */
  private static void reloadUI() {
    try {
      UIManager.setLookAndFeel(new MTDarkLaf(mtConfig.getSelectedTheme().getTheme()));

      applyFonts();

      DarculaInstaller.uninstall();
      if (UIUtil.isUnderDarcula()) {
        DarculaInstaller.install();
      }
      LafManager.getInstance().updateUI();
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  //region Title bar support

  /**
   * Theme title bar.
   */
  static void themeTitleBar() {
    final boolean isDarkTitleOn = mtConfig.isDarkTitleBar();
    if (SystemInfo.isWin10OrNewer && isDarkTitleOn) {
      // Write in the registry
      themeWindowsTitleBar();
    }
  }

  /**
   * Theme windows title bar.
   */
  private static void themeWindowsTitleBar() {
    final Color backgroundColor = mtConfig.getSelectedTheme().getTheme().getBackgroundColor();

    WinRegistry.writeTitleColor(backgroundColor);
  }

  //endregion
}
