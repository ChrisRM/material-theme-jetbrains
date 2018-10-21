/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.chrisrm.idea.icons.IconReplacer;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.themes.MTThemeable;
import com.chrisrm.idea.themes.lists.AccentResources;
import com.chrisrm.idea.themes.lists.ContrastResources;
import com.chrisrm.idea.themes.lists.FontResources;
import com.chrisrm.idea.utils.MTUiUtils;
import com.chrisrm.idea.utils.UIReplacer;
import com.chrisrm.idea.utils.WinRegistry;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.laf.IntelliJLaf;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.ui.AppUIUtil;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;
import sun.awt.AppContext;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.text.html.*;
import java.awt.*;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Locale;

import static com.intellij.ide.ui.laf.LafManagerImpl.installMacOSXFonts;

public final class MTThemeManager {

  public static final int DEFAULT_SIDEBAR_HEIGHT = 28;
  public static final int DEFAULT_INDENT = 6;
  public static final int DEFAULT_FONT_SIZE = JBUI.scale(12);
  public static final String DEFAULT_FONT = "Roboto";
  public static final String DEFAULT_MONO_FONT = "Fira Code";
  //  private final Font notoFont;
  //  private final Font robotoFont;

  public MTThemeManager() {
    //    notoFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(loader.getResourceAsStream("/fonts/Noto.ttf")));
    //    robotoFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(loader.getResourceAsStream("/fonts/Roboto-Medium.ttf")));
    //    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(robotoFont);
    //    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(notoFont);
    registerFont("/fonts/Roboto-Black.ttf");
    registerFont("/fonts/Roboto-BlackItalic.ttf");
    registerFont("/fonts/Roboto-Bold.ttf");
    registerFont("/fonts/Roboto-BoldItalic.ttf");
    registerFont("/fonts/Roboto-Regular.ttf");
    registerFont("/fonts/Roboto-Italic.ttf");
    registerFont("/fonts/Roboto-Light.ttf");
    registerFont("/fonts/Roboto-LightItalic.ttf");
    registerFont("/fonts/Roboto-Medium.ttf");
    registerFont("/fonts/Roboto-MediumItalic.ttf");
    registerFont("/fonts/Roboto-Thin.ttf");
    registerFont("/fonts/Roboto-ThinItalic.ttf");

    registerFont("/fonts/NotoSans-Black.ttf");
    registerFont("/fonts/NotoSans-BlackItalic.ttf");
    registerFont("/fonts/NotoSans-Bold.ttf");
    registerFont("/fonts/NotoSans-BoldItalic.ttf");
    registerFont("/fonts/NotoSans-Regular.ttf");
    registerFont("/fonts/NotoSans-Italic.ttf");
    registerFont("/fonts/NotoSans-Light.ttf");
    registerFont("/fonts/NotoSans-LightItalic.ttf");
    registerFont("/fonts/NotoSans-Medium.ttf");
    registerFont("/fonts/NotoSans-MediumItalic.ttf");
    registerFont("/fonts/NotoSans-SemiBold.ttf");
    registerFont("/fonts/NotoSans-SemiBoldItalic.ttf");
    registerFont("/fonts/NotoSans-Thin.ttf");
    registerFont("/fonts/NotoSans-ThinItalic.ttf");
  }

  private void registerFont(@NonNls final String name) {
    final ClassLoader loader = getClass().getClassLoader();
    final URL url = loader.getResource(name);
    if (url == null) {
      Logger.getInstance(getClass()).warn("Resource missing: " + name);
      return;
    }

    try {
      try (final InputStream is = url.openStream()) {
        final Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
      }
    } catch (final Throwable t) {
      Logger.getInstance(AppUIUtil.class).warn("Cannot register font: " + url, t);
    }
  }

  public static MTThemeManager getInstance() {
    return ServiceManager.getService(MTThemeManager.class);
  }

  private static String getSettingsPrefix() {
    final PluginId pluginId = PluginManager.getPluginByClassName(MTAbstractTheme.class.getName());
    return pluginId == null ? "com.chrisrm.idea.MaterialThemeUI" : pluginId.getIdString();
  }

  //region Action Toggles

  public void toggleMaterialDesign() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsMaterialDesign(!mtConfig.getIsMaterialDesign());

    askForRestart();
  }

  public void toggleProjectViewDecorators() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setUseProjectViewDecorators(!mtConfig.isUseProjectViewDecorators());
    updateFileIcons();
  }

  public void toggleMaterialTheme() {
    MTConfig.getInstance().setIsMaterialTheme(!MTConfig.getInstance().isMaterialTheme());
    MTThemeManager.getInstance().activate();
  }

  /**
   * Set contrast and reactivate theme
   */
  public void toggleContrast() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsContrastMode(!mtConfig.getIsContrastMode());

    applyContrast(true);
  }

  public void toggleHighContrast() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsHighContrast(!mtConfig.getIsHighContrast());
    MTThemeManager.getInstance().activate();
  }

  public void toggleCompactStatusBar() {
    final boolean compactStatusBar = MTConfig.getInstance().isCompactStatusBar();
    MTConfig.getInstance().setIsCompactStatusBar(!compactStatusBar);
  }

  public void toggleHideFileIcons() {
    final boolean hideFileIcons = MTConfig.getInstance().getHideFileIcons();
    MTConfig.getInstance().setHideFileIcons(!hideFileIcons);

    updateFileIcons();
  }

  public void toggleMonochromeIcons() {
    final boolean monochromeIcons = MTConfig.getInstance().isMonochromeIcons();
    MTConfig.getInstance().setMonochromeIcons(!monochromeIcons);

    IconReplacer.applyFilter();
    updateFileIcons();
  }

  public void toggleCompactSidebar() {
    final boolean isCompactSidebar = MTConfig.getInstance().isCompactSidebar();
    MTConfig.getInstance().setCompactSidebar(!isCompactSidebar);

    applyCompactSidebar(true);
  }

  public void toggleCompactDropdowns() {
    final boolean isCompactDropdowns = MTConfig.getInstance().isCompactDropdowns();
    MTConfig.getInstance().setCompactDropdowns(!isCompactDropdowns);

    UIReplacer.patchUI();
  }

  public void toggleCompactMenus() {
    final boolean isCompact = MTConfig.getInstance().isCompactMenus();
    MTConfig.getInstance().setIsCompactMenus(!isCompact);

    applyMenusHeight();
    UIReplacer.patchUI();
  }

  public void toggleMaterialIcons() {
    final boolean useMaterialIcons = MTConfig.getInstance().isUseMaterialIcons();
    MTConfig.getInstance().setUseMaterialIcons(!useMaterialIcons);

    updateFileIcons();
  }

  public void toggleMaterialFonts() {
    final boolean useMaterialFonts = MTConfig.getInstance().isUseMaterialFont();
    MTConfig.getInstance().setUseMaterialFont(!useMaterialFonts);

    applyFonts();
  }

  public void toggleUpperCaseTabs() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsUpperCaseTabs(!mtConfig.isUpperCaseTabs());
    mtConfig.fireChanged();
  }

  public void toggleStatusBarIndicator() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsStatusBarTheme(!mtConfig.isStatusBarTheme());
    mtConfig.fireChanged();
  }

  public void toggleDarkTitleBar() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setDarkTitleBar(!mtConfig.isDarkTitleBar());
    themeTitleBar();
  }
  //endregion

  //region File Icons support

  public void updateFileIcons() {
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
  public void activate() {
    final MTThemeFacade mtTheme = MTConfig.getInstance().getSelectedTheme();
    if (!MTConfig.getInstance().isMaterialTheme()) {
      removeTheme(mtTheme);
      applyAccents();
      return;
    }

    activate(mtTheme, false);
  }

  public void activate(final MTThemeFacade mtTheme) {
    activate(mtTheme, false);
  }

  /**
   * Activate theme
   *
   * @param mtTheme
   */
  public void activate(final MTThemeFacade mtTheme, final boolean switchColorScheme) {
    MTThemeFacade newTheme = mtTheme;
    if (newTheme == null) {
      newTheme = MTThemes.OCEANIC;
    }

    MTConfig.getInstance().setSelectedTheme(newTheme);

    newTheme.getTheme().activate();
    switchScheme(newTheme, switchColorScheme);

    PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", newTheme.getThemeId());
    applyContrast(false);
    applyCompactSidebar(false);
    applyCustomTreeIndent();
    applyMenusHeight();
    applyAccents();
    // Documentation styles
    //    patchStyledEditorKit();

    LafManager.getInstance().updateUI();

    applyFonts();

    // Documentation styles
    patchStyledEditorKit();

    themeTitleBar();

    IconReplacer.applyFilter();

    UIReplacer.patchUI();
  }

  private void switchScheme(final MTThemeFacade mtTheme, final boolean switchColorScheme) {
    final EditorColorsManager editorColorsManager = EditorColorsManager.getInstance();
    if (switchColorScheme) {
      final EditorColorsScheme themeScheme = editorColorsManager.getScheme(mtTheme.getThemeColorScheme());
      if (themeScheme != null) {
        editorColorsManager.setGlobalScheme(themeScheme);
      }
    }
    // Need to trigger a change otherwise the ui will get stuck. Yes this sucks
    final EditorColorsScheme globalScheme = editorColorsManager.getGlobalScheme();
    editorColorsManager.setGlobalScheme(editorColorsManager.getScheme("Darcula"));
    editorColorsManager.setGlobalScheme(globalScheme);
  }

  public void applyAccents() {
    final String accentColor = MTConfig.getInstance().getAccentColor();
    final Color accentColorColor = ColorUtil.fromHex(accentColor);
    for (final String resource : AccentResources.ACCENT_RESOURCES) {
      UIManager.put(resource, accentColorColor);
    }
    // override for transparency
    UIManager.put("Focus.color", ColorUtil.toAlpha(accentColorColor, 70));
    UIManager.put("ActionButton.hoverBackground", ColorUtil.toAlpha(accentColorColor, 70));
    UIManager.put("ActionButton.hoverBorderColor", ColorUtil.toAlpha(accentColorColor, 70));

    patchStyledEditorKit();
  }

  public void askForRestart() {
    final String title = MaterialThemeBundle.message("mt.restartDialog.title");
    final String message = MaterialThemeBundle.message("mt.restartDialog.content");

    final int answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon());
    if (answer == Messages.YES) {
      MTUiUtils.restartIde();
    }
  }

  /**
   * Completely remove theme
   *
   * @param mtTheme
   */
  private void removeTheme(final MTThemeFacade mtTheme) {
    try {
      resetContrast();

      if (UIUtil.isUnderDarcula()) {
        UIManager.setLookAndFeel(new DarculaLaf());
      } else {
        UIManager.setLookAndFeel(new IntelliJLaf());
      }

      JBColor.setDark(mtTheme.getThemeIsDark());
      IconLoader.setUseDarkIcons(mtTheme.getThemeIsDark());
      PropertiesComponent.getInstance().unsetValue(getSettingsPrefix() + ".theme");

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
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  /**
   * Apply custom fonts
   *
   * @param uiDefaults
   * @param fontFace
   * @param fontSize
   */
  private void applySettingsFont(final UIDefaults uiDefaults, final String fontFace, final int fontSize) {
    uiDefaults.put("Tree.ancestorInputMap", null);
    final FontUIResource font = UIUtil.getFontWithFallback(fontFace, Font.PLAIN, fontSize);
    final FontUIResource uiFont = font;
    final FontUIResource textFont = font;

    final String editorFontName = AppEditorFontOptions.getInstance().getFontPreferences().getFontFamily();
    final String monospaceFont = ObjectUtils.notNull(editorFontName, DEFAULT_MONO_FONT);
    final FontUIResource monoFont = new FontUIResource(monospaceFont, Font.PLAIN, fontSize);

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

  private void applyMaterialFonts(final UIDefaults uiDefaults) {
    uiDefaults.put("Tree.ancestorInputMap", null);

    final String language = Locale.getDefault().getLanguage();
    final boolean cjkLocale =
        (Locale.CHINESE.getLanguage().equals(language) ||
         Locale.JAPANESE.getLanguage().equals(language) ||
         Locale.KOREAN.getLanguage().equals(language));

    FontUIResource font = UIUtil.getFontWithFallback(DEFAULT_FONT, Font.PLAIN, DEFAULT_FONT_SIZE);
    if (cjkLocale) {
      font = UIUtil.getFontWithFallback("Noto Sans", Font.PLAIN, DEFAULT_FONT_SIZE);
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

  private void applyFonts() {
    final UISettings uiSettings = UISettings.getInstance();
    final UIDefaults lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();
    final int treeFontSize = JBUI.scale(MTConfig.getInstance().getTreeFontSize());

    final boolean useMaterialFont = MTConfig.getInstance().isUseMaterialFont();

    if (uiSettings.getOverrideLafFonts()) {
      applySettingsFont(lookAndFeelDefaults, uiSettings.getFontFace(), uiSettings.getFontSize());
    } else if (useMaterialFont) {
      applyMaterialFonts(lookAndFeelDefaults);
    } else {
      if (SystemInfo.isMacOSYosemite) {
        installMacOSXFonts(UIManager.getLookAndFeelDefaults());
      }
    }

    if (MTConfig.getInstance().isTreeFontSizeEnabled()) {
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
   * @param reloadUI
   */
  private void applyContrast(final boolean reloadUI) {
    final boolean apply = MTConfig.getInstance().getIsContrastMode();
    final MTThemeable mtTheme = MTConfig.getInstance().getSelectedTheme().getTheme();
    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
      final Color contrastedColor = apply ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
      UIManager.put(resource, contrastedColor);
    }

    if (reloadUI) {
      reloadUI();
    }
  }

  /**
   * Reset contrast
   */
  private void resetContrast() {
    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
      UIManager.put(resource, null);
    }
  }
  //endregion

  //region Custom tree indents support

  /**
   * Apply custom tree indent
   */
  private void applyCustomTreeIndent() {
    final MTConfig mtConfig = MTConfig.getInstance();

    if (mtConfig.isCustomTreeIndentEnabled) {
      UIManager.put("Tree.leftChildIndent", mtConfig.getLeftTreeIndent());
      UIManager.put("Tree.rightChildIndent", mtConfig.getRightTreeIndent());
    } else {
      UIManager.put("Tree.leftChildIndent", (MTThemeManager.DEFAULT_INDENT / 2) + JBUI.scale(7));
      UIManager.put("Tree.rightChildIndent", (MTThemeManager.DEFAULT_INDENT / 2) + JBUI.scale(4));
    }
  }
  //endregion

  //region Compact Menus support

  /**
   * Apply custom tree indent
   */
  private void applyMenusHeight() {
    final MTConfig mtConfig = MTConfig.getInstance();

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
  private void applyCompactSidebar(final boolean reloadUI) {
    final boolean isCustomSidebarHeight = MTConfig.getInstance().isCompactSidebar();
    final int customSidebarHeight = MTConfig.getInstance().getCustomSidebarHeight();
    final int rowHeight = isCustomSidebarHeight ? JBUI.scale(customSidebarHeight) : JBUI.scale(MTThemeManager.DEFAULT_SIDEBAR_HEIGHT);
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
  private void patchStyledEditorKit() {
    final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    final MTConfig mtConfig = MTConfig.getInstance();
    final MTThemeable selectedTheme = mtConfig.getSelectedTheme().getTheme();

    // Load css
    final URL url = selectedTheme.getClass().getResource(selectedTheme.getId() + (JBUI.isUsrHiDPI() ? "@2x.css" : ".css"));
    StyleSheet styleSheet = UIUtil.loadStyleSheet(url);
    if (styleSheet == null) {
      final URL fallbackUrl = DarculaLaf.class.getResource("darcula" + (JBUI.isUsrHiDPI() ? "@2x.css" : ".css"));
      styleSheet = UIUtil.loadStyleSheet(fallbackUrl);
    }

    // Add custom accent color
    assert styleSheet != null;
    final String accentColor = ObjectUtils.notNull(mtConfig.getAccentColor(), "80CBC4");
    styleSheet.addRule(String.format("a, address, b { color: #%s; }", accentColor));
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

  public void setTabsHeight(final int newTabsHeight) {
    MTConfig.getInstance().setTabsHeight(newTabsHeight);
  }
  //endregion

  /**
   * Trigger a reloadUI event
   */
  private void reloadUI() {
    try {
      UIManager.setLookAndFeel(new MTDarkLaf(MTConfig.getInstance().getSelectedTheme().getTheme()));

      applyFonts();

      DarculaInstaller.uninstall();
      if (UIUtil.isUnderDarcula()) {
        DarculaInstaller.install();
      }
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  //region Title bar support

  public void themeTitleBar() {
    final boolean isDarkTitleOn = MTConfig.getInstance().isMaterialTheme() && MTConfig.getInstance().isDarkTitleBar();
    if (SystemInfo.isWin10OrNewer && isDarkTitleOn) {
      // Write in the registry
      themeWindowsTitleBar();
    }
  }

  public void themeMacTitleBar(final boolean isDarkTitleOn) {
    Registry.get("ide.mac.allowDarkWindowDecorations").setValue(isDarkTitleOn);
  }

  public void themeWindowsTitleBar() {
    final Color backgroundColor = MTConfig.getInstance().getSelectedTheme().getTheme().getBackgroundColor();

    WinRegistry.writeTitleColor(backgroundColor);
  }

  public int getTitleColor() {
    return WinRegistry.getTitleColor();
  }

  //endregion
}
