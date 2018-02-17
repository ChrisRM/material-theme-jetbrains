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

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.themes.MTThemeable;
import com.chrisrm.idea.utils.IconReplacer;
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
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.status.IdeStatusBarImpl;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.awt.AppContext;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.lang.reflect.Field;
import java.net.URL;

import static com.chrisrm.idea.tabs.MTTabsPainterPatcherComponent.BOLD_TABS;
import static com.chrisrm.idea.tabs.MTTabsPainterPatcherComponent.TABS_HEIGHT;

public final class MTThemeManager {

  public static final String[] ACCENT_RESOURCES = new String[] {
      "link.foreground",
      "ProgressBar.foreground",
      "RadioButton.darcula.selectionEnabledColor",
      "RadioButton.darcula.selectionEnabledShadowColor",
      "RadioButton.darcula.selectionDisabledShadowColor",
      "TextField.selectedSeparatorColor",
      "CheckBox.darcula.borderColor1",
      "CheckBox.darcula.borderColor2",
      "CheckBox.darcula.backgroundColor1.selected",
      "CheckBox.darcula.backgroundColor2.selected",
      "CheckBox.darcula.focusedArmed.backgroundColor1.selected",
      "CheckBox.darcula.focusedArmed.backgroundColor2.selected",
      "CheckBox.darcula.focused.backgroundColor1.selected",
      "CheckBox.darcula.focused.backgroundColor2.selected",
      "Hyperlink.linkColor",
      "Focus.color",
      "Slider.thumb",
      "material.tab.borderColor"
  };
  private static final String[] FONT_RESOURCES = new String[] {
      "Button.font",
      "ToggleButton.font",
      "RadioButton.font",
      "CheckBox.font",
      "ColorChooser.font",
      "ComboBox.font",
      "Label.font",
      "List.font",
      "MenuBar.font",
      "MenuItem.font",
      "MenuItem.acceleratorFont",
      "RadioButtonMenuItem.font",
      "CheckBoxMenuItem.font",
      "Menu.font",
      "PopupMenu.font",
      "OptionPane.font",
      "Panel.font",
      "ProgressBar.font",
      "ScrollPane.font",
      "Viewport.font",
      "TabbedPane.font",
      "Table.font",
      "TableHeader.font",
      "TextField.font",
      "FormattedTextField.font",
      "Spinner.font",
      "PasswordField.font",
      "TextArea.font",
      "TextPane.font",
      "EditorPane.font",
      "TitledBorder.font",
      "ToolBar.font",
      "ToolTip.font",
      "Tree.font"};
  private static final String[] CONTRASTED_RESOURCES = new String[] {
      "Tree.background",
      "Tree.textBackground",
      //      "Table.background",
      "Viewport.background",
      "ToolBar.background",
      "SidePanel.background",
      "TabbedPane.background",
      "TextField.background",
      "PasswordField.background",
      "TextArea.background",
      "TextPane.background",
      "EditorPane.background",
      "ToolBar.background",
      "FormattedTextField.background",
      //      "RadioButton.darcula.selectionDisabledColor",
      //      "RadioButton.background",
      //      "Spinner.background",
      //      "CheckBox.background",
      //      "CheckBox.darcula.backgroundColor1",
      //      "CheckBox.darcula.backgroundColor2",
      //      "CheckBox.darcula.shadowColor",
      //      "CheckBox.darcula.shadowColorDisabled",
      //      "CheckBox.darcula.focusedArmed.backgroundColor1",
      //      "CheckBox.darcula.focusedArmed.backgroundColor2",
      //      "CheckBox.darcula.focused.backgroundColor1",
      //      "CheckBox.darcula.focused.backgroundColor2",
      //      "ComboBox.disabledBackground",
      //      "control",
      "ComboBox.background",
      "ComboBox.disabledBackground",
      "ComboBox.arrowFillColor",
      "window",
      "activeCaption",
      "desktop",
      "MenuBar.shadow",
      "MenuBar.background",
      "TabbedPane.darkShadow",
      "TabbedPane.shadow",
      "TabbedPane.borderColor",
      "StatusBar.background",
      "SplitPane.highlight",
      "ActionToolbar.background"
  };
  public static final int DEFAULT_SIDEBAR_HEIGHT = 28;
  public static final int DEFAULT_TAB_HEIGHT = 24;
  public static final boolean DEFAULT_IS_BOLD_TABS = false;
  public static final int DEFAULT_INDENT = 6;
  public static final int DEFAULT_FONT_SIZE = 12;
  public static final String DEFAULT_FONT = "Roboto";
  public static final int DEFAULT_STATUSBAR_PADDING = 8;

  private Color oldWinTitlebarColor;

  public MTThemeManager() {
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

  public void toggleCompactStatusBar() {
    final boolean compactStatusBar = MTConfig.getInstance().isCompactStatusBar();
    MTConfig.getInstance().setIsCompactStatusBar(!compactStatusBar);

    setStatusBarBorders();
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

  //region Status bar support

  /**
   * Change status bar borders
   */
  public void setStatusBarBorders() {
    final boolean compactSidebar = MTConfig.getInstance().isCompactStatusBar();

    ApplicationManager.getApplication().invokeLater(() -> {
      final JFrame visibleFrame = WindowManager.getInstance().findVisibleFrame();
      if (visibleFrame == null) {
        return;
      }
      final JComponent component = visibleFrame.getRootPane();
      if (component != null) {
        final IdeStatusBarImpl ideStatusBar = UIUtil.findComponentOfType(component, IdeStatusBarImpl.class);
        if (ideStatusBar != null) {
          ideStatusBar.setBorder(compactSidebar ? JBUI.Borders.empty() : JBUI.Borders.empty(MTThemeManager.DEFAULT_STATUSBAR_PADDING, 0));
        }
      }
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
      applyAccents(false);
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

    // Because the DarculaInstaller overrides this
    final EditorColorsScheme currentScheme = EditorColorsManager.getInstance().getGlobalScheme();

    PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", newTheme.getThemeId());
    applyContrast(false);
    applyCompactSidebar(false);
    applyCustomTreeIndent();
    applyAccents(false);
    setBoldTabs();

    // We need this to update parts of the UI that do not change
    if (UIUtil.isUnderDarcula()) {
      DarculaInstaller.uninstall();
      DarculaInstaller.install();
    } else {
      DarculaInstaller.uninstall();
      DarculaInstaller.install();
    }
    LafManager.getInstance().updateUI();

    // Because the DarculaInstaller overrides this
    EditorColorsManager.getInstance().setGlobalScheme(currentScheme);

    applyFonts();

    // Documentation styles
    patchStyledEditorKit();

    themeTitleBar();

    UIReplacer.patchUI();
  }

  private void switchScheme(final MTThemeFacade mtTheme, final boolean switchColorScheme) {
    if (switchColorScheme) {
      final EditorColorsScheme themeScheme = EditorColorsManager.getInstance().getScheme(mtTheme.getThemeColorScheme());
      if (themeScheme != null) {
        EditorColorsManager.getInstance().setGlobalScheme(themeScheme);
      }
    }
  }

  public void applyAccents(final boolean reloadUI) {
    final String accentColor = MTConfig.getInstance().getAccentColor();
    final Color accentColorColor = ColorUtil.fromHex(accentColor);
    for (final String resource : MTThemeManager.ACCENT_RESOURCES) {
      UIManager.put(resource, accentColorColor);
    }
    // override for transparency
    UIManager.put("Focus.color", ColorUtil.toAlpha(accentColorColor, 70));

  }

  private void askForRestart() {
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
  private void applyCustomFonts(final UIDefaults uiDefaults, final String fontFace, final int fontSize) {
    uiDefaults.put("Tree.ancestorInputMap", null);
    final FontUIResource uiFont = new FontUIResource(fontFace, Font.PLAIN, fontSize);
    final FontUIResource textFont = new FontUIResource("Serif", Font.PLAIN, fontSize);

    final String monospaceFont = ObjectUtils.notNull(EditorColorsManager.getInstance().getGlobalScheme().getEditorFontName(), "Fira Code");
    final FontUIResource monoFont = new FontUIResource(monospaceFont, Font.PLAIN, fontSize);

    for (final String fontResource : MTThemeManager.FONT_RESOURCES) {
      uiDefaults.put(fontResource, uiFont);
    }

    uiDefaults.put("PasswordField.font", monoFont);
    uiDefaults.put("TextArea.font", monoFont);
    uiDefaults.put("TextPane.font", textFont);
    uiDefaults.put("EditorPane.font", textFont);
  }

  private void applyFonts() {
    final UISettings uiSettings = UISettings.getInstance();
    final UIDefaults lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();

    final boolean useMaterialFont = MTConfig.getInstance().isUseMaterialFont();
    toggleBiggerFont(useMaterialFont);

    if (uiSettings.getOverrideLafFonts()) {
      applyCustomFonts(lookAndFeelDefaults, uiSettings.getFontFace(), uiSettings.getFontSize());
    } else if (useMaterialFont) {
      final Font roboto = MTUiUtils.findFont(MTThemeManager.DEFAULT_FONT);
      if (roboto != null) {
        applyCustomFonts(lookAndFeelDefaults, MTThemeManager.DEFAULT_FONT, JBUI.scale(MTThemeManager.DEFAULT_FONT_SIZE));
      }
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
    for (final String resource : CONTRASTED_RESOURCES) {
      final Color contrastedColor = apply ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
      UIManager.put(resource, contrastedColor);
    }

    if (reloadUI) {
      reloadUI(mtTheme);
    }
  }

  /**
   * Reset contrast
   */
  private void resetContrast() {
    for (final String resource : MTThemeManager.CONTRASTED_RESOURCES) {
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
      UIManager.put("Tree.rightChildIndent", mtConfig.customTreeIndent);
    } else {
      UIManager.put("Tree.rightChildIndent", MTThemeManager.DEFAULT_INDENT);
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
      final MTThemeable mtTheme = MTConfig.getInstance().getSelectedTheme().getTheme();
      reloadUI(mtTheme);
    }
  }

  private void toggleBiggerFont(final boolean isEnabled) {
    Registry.get("bigger.font.in.project.view").setValue(isEnabled);
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
  public void setTabsHeight() {
    PropertiesComponent.getInstance().setValue(TABS_HEIGHT, MTConfig.getInstance().getTabsHeight(), MTThemeManager.DEFAULT_TAB_HEIGHT);
  }

  public void setTabsHeight(final int newTabsHeight) {
    MTConfig.getInstance().setTabsHeight(newTabsHeight);
    setTabsHeight();
  }

  public void setBoldTabs() {
    PropertiesComponent.getInstance().setValue(BOLD_TABS, MTConfig.getInstance().isUpperCaseTabs(), MTThemeManager.DEFAULT_IS_BOLD_TABS);
  }

  //endregion

  /**
   * Trigger a reloadUI event
   *
   * @param mtTheme
   */
  private void reloadUI(final MTThemeable mtTheme) {
    try {
      UIManager.setLookAndFeel(new MTLaf(MTConfig.getInstance().getSelectedTheme().getTheme()));

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
    if (SystemInfo.isMac) {
      themeMacTitleBar(isDarkTitleOn);
    } else if (SystemInfo.isWin10OrNewer && isDarkTitleOn) {
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

  public void restoreTitleBar() {
    if (SystemInfo.isMac) {
      Registry.get("ide.mac.allowDarkWindowDecorations").setValue(false);
    } else if (SystemInfo.isWin10OrNewer) {
      //      restoreWindowsTitleBar();
    }
  }

  public void restoreWindowsTitleBar() {
    final boolean isDarkTitleOn = MTConfig.getInstance().isMaterialTheme() && MTConfig.getInstance().isDarkTitleBar();

    if (isDarkTitleOn && SystemInfo.isWin10OrNewer) {
      final String originalTitleColor = MTConfig.getInstance().getAccentTitleBarColor();
      WinRegistry.writeTitleColor(ColorUtil.fromHex(originalTitleColor));
    }
  }

  public Color getWindowsTitleBarColor() {
    final int windowsColor = getTitleColor();
    return MTUiUtils.dwordToColor(windowsColor);
  }

  public int getTitleColor() {
    return WinRegistry.getTitleColor();
  }

  private void styleWindowsTitleBar() {
    final boolean isDarkTitleOn = MTConfig.getInstance().isMaterialTheme() && MTConfig.getInstance().isDarkTitleBar();

    if (isDarkTitleOn && SystemInfo.isWin10OrNewer) {
      themeWindowsTitleBar();
    }
  }

  //endregion
}
