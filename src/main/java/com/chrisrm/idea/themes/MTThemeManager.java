package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTLaf;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.MTUiUtils;
import com.chrisrm.idea.utils.UIReplacer;
import com.google.common.collect.ImmutableList;
import com.intellij.ide.plugins.PluginManager;
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
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.status.IdeStatusBarImpl;
import com.intellij.ui.JBColor;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chrisrm.idea.tabs.MTTabsPainterPatcherComponent.TABS_HEIGHT;

public class MTThemeManager {

  private static final String[] ourPatchableFontResources = new String[]{
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

  private static final String[] contrastedResources = new String[]{
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

  private List<String> EDITOR_COLORS_SCHEMES;

  public MTThemeManager() {
    Collection<String> schemes = new ArrayList<>();
    for (MTTheme theme : MTTheme.values()) {
      schemes.add(theme.getEditorColorsScheme());
    }
    EDITOR_COLORS_SCHEMES = ImmutableList.copyOf(schemes);
  }

  public static MTThemeManager getInstance() {
    return ServiceManager.getService(MTThemeManager.class);
  }

  private static String getSettingsPrefix() {
    PluginId pluginId = PluginManager.getPluginByClassName(MTTheme.class.getName());
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
    this.updateFileIcons();
  }

  public void toggleMaterialTheme() {
    MTConfig.getInstance().setIsMaterialTheme(!MTConfig.getInstance().isMaterialTheme());
    getInstance().activate();
  }

  /**
   * Set contrast and reactivate theme
   */
  public void toggleContrast() {
    MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsContrastMode(!mtConfig.getIsContrastMode());

    this.applyContrast(true);
  }

  public void toggleCompactStatusBar() {
    boolean compactStatusBar = MTConfig.getInstance().isCompactStatusBar();
    MTConfig.getInstance().setIsCompactStatusBar(!compactStatusBar);

    this.setStatusBarBorders();
  }

  public void toggleHideFileIcons() {
    boolean hideFileIcons = MTConfig.getInstance().getHideFileIcons();
    MTConfig.getInstance().setHideFileIcons(!hideFileIcons);

    this.updateFileIcons();
  }

  public void toggleCompactSidebar() {
    boolean isCompactSidebar = MTConfig.getInstance().isCompactSidebar();
    MTConfig.getInstance().setCompactSidebar(!isCompactSidebar);

    this.applyCompactSidebar(true);
  }

  public void toggleMaterialIcons() {
    boolean useMaterialIcons = MTConfig.getInstance().isUseMaterialIcons();
    MTConfig.getInstance().setUseMaterialIcons(!useMaterialIcons);

    this.updateFileIcons();
  }

  public void toggleStatusBarIndicator() {
    final MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsStatusBarTheme(!mtConfig.isStatusBarTheme());
    mtConfig.fireChanged();
  }
  //endregion

  //region File Icons support
  public void updateFileIcons() {
    ApplicationManager.getApplication().runWriteAction(() -> {
      FileTypeManagerEx instanceEx = FileTypeManagerEx.getInstanceEx();
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
    boolean compactSidebar = MTConfig.getInstance().isCompactStatusBar();

    ApplicationManager.getApplication().invokeLater(() -> {
      JComponent component = WindowManager.getInstance().findVisibleFrame().getRootPane();
      if (component != null) {
        IdeStatusBarImpl ideStatusBar = UIUtil.findComponentOfType(component, IdeStatusBarImpl.class);
        if (ideStatusBar != null) {
          ideStatusBar.setBorder(compactSidebar ? JBUI.Borders.empty() : JBUI.Borders.empty(8, 0));
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
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();
    if (!MTConfig.getInstance().isMaterialTheme()) {
      removeTheme(mtTheme);
      return;
    }

    this.activate(mtTheme);
  }

  /**
   * Activate theme
   *
   * @param mtTheme
   */
  public void activate(MTTheme mtTheme) {
    if (mtTheme == null) {
      mtTheme = MTTheme.DEFAULT;
    }

    MTConfig.getInstance().setSelectedTheme(mtTheme);

    try {
      UIManager.setLookAndFeel(new MTLaf(mtTheme));
      JBColor.setDark(mtTheme.isDark());
      IconLoader.setUseDarkIcons(mtTheme.isDark());

      PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", mtTheme.name());
      applyContrast(false);
      applyCompactSidebar(false);
      applyCustomTreeIndent(false);
    }
    catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    String currentScheme = EditorColorsManager.getInstance().getGlobalScheme().getName();

    String makeActiveScheme = !EDITOR_COLORS_SCHEMES.contains(currentScheme) ?
                              currentScheme : mtTheme.getEditorColorsScheme();

    EditorColorsScheme scheme = EditorColorsManager.getInstance().getScheme(makeActiveScheme);

    // We need this to update parts of the UI that do not change
    DarculaInstaller.uninstall();
    if (mtTheme.isDark()) {
      DarculaInstaller.install();
    }

    if (scheme != null) {
      EditorColorsManager.getInstance().setGlobalScheme(scheme);
    }

    applyFonts();

    // Documentation styles
    patchStyledEditorKit();

    UIReplacer.patchUI();
  }



  private void askForRestart() {
    String title = MaterialThemeBundle.message("mt.restartDialog.title");
    String message = MaterialThemeBundle.message("mt.restartDialog.content");

    int answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon());
    if (answer == Messages.YES) {
      MTUiUtils.restartIde();
    }
  }


  /**
   * Completely remove theme
   *
   * @param mtTheme
   */
  private void removeTheme(MTTheme mtTheme) {
    try {
      resetContrast();

      if (mtTheme.isDark()) {
        UIManager.setLookAndFeel(new DarculaLaf());
      } else {
        UIManager.setLookAndFeel(new IntelliJLaf());
      }

      JBColor.setDark(mtTheme.isDark());
      IconLoader.setUseDarkIcons(mtTheme.isDark());
      PropertiesComponent.getInstance().unsetValue(getSettingsPrefix() + ".theme");

      // We need this to update parts of the UI that do not change
      DarculaInstaller.uninstall();
      if (mtTheme.isDark()) {
        DarculaInstaller.install();
      }
    }
    catch (UnsupportedLookAndFeelException e) {
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
  private void applyCustomFonts(UIDefaults uiDefaults, String fontFace, int fontSize) {
    uiDefaults.put("Tree.ancestorInputMap", null);
    FontUIResource uiFont = new FontUIResource(fontFace, Font.PLAIN, fontSize);
    FontUIResource textFont = new FontUIResource("Serif", Font.PLAIN, fontSize);
    FontUIResource monoFont = new FontUIResource("Monospaced", Font.PLAIN, fontSize);

    for (String fontResource : ourPatchableFontResources) {
      uiDefaults.put(fontResource, uiFont);
    }

    uiDefaults.put("PasswordField.font", monoFont);
    uiDefaults.put("TextArea.font", monoFont);
    uiDefaults.put("TextPane.font", textFont);
    uiDefaults.put("EditorPane.font", textFont);
  }

  private void applyFonts() {
    UISettings uiSettings = UISettings.getInstance();
    UIDefaults lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();

    if (uiSettings.getOverrideLafFonts()) {
      applyCustomFonts(lookAndFeelDefaults, uiSettings.getFontFace(), uiSettings.getFontSize());
    } else {
      Font roboto = MTUiUtils.findFont("Roboto");
      if (roboto != null) {
        applyCustomFonts(lookAndFeelDefaults, "Roboto", JBUI.scale(12));
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
  private void applyContrast(boolean reloadUI) {
    final boolean apply = MTConfig.getInstance().getIsContrastMode();
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();
    for (String resource : contrastedResources) {
      Color contrastedColor = apply ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
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
    for (String resource : contrastedResources) {
      UIManager.put(resource, null);
    }
  }
  //endregion

  //region Custom tree indents support

  /**
   * Apply custom tree indent
   */
  private void applyCustomTreeIndent(boolean reloadUI) {
    MTConfig mtConfig = MTConfig.getInstance();
    int defaultIndent = mtConfig.getSelectedTheme().getTreeIndent();

    if (mtConfig.isCustomTreeIndentEnabled) {
      UIManager.put("Tree.rightChildIndent", mtConfig.customTreeIndent);
    } else {
      UIManager.put("Tree.rightChildIndent", defaultIndent);
    }

    if (reloadUI) {
      reloadUI();
    }
  }
  //endregion

  //region Compact Sidebar support

  /**
   * Use compact sidebar option
   */
  private void applyCompactSidebar(boolean reloadUI) {
    final boolean compactSidebar = MTConfig.getInstance().isCompactSidebar();
    int rowHeight = compactSidebar ? JBUI.scale(18) : JBUI.scale(24);
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
    MTConfig mtConfig = MTConfig.getInstance();
    MTTheme selectedTheme = mtConfig.getSelectedTheme();

    // Load css
    URL url = selectedTheme.getClass().getResource(selectedTheme.getId() + (JBUI.isUsrHiDPI() ? "@2x.css" : ".css"));
    StyleSheet styleSheet = UIUtil.loadStyleSheet(url);

    // Add custom accent color
    assert styleSheet != null;
    styleSheet.addRule("a, address, b { color: " + mtConfig.getAccentColor() + "; }");
    defaults.put("StyledEditorKit.JBDefaultStyle", styleSheet);

    try {
      Field keyField = HTMLEditorKit.class.getDeclaredField("DEFAULT_STYLES_KEY");
      keyField.setAccessible(true);
      AppContext.getAppContext().put(keyField.get(null), styleSheet);
    }
    catch (Exception e) {
    }
  }
  //endregion

  //region Tabs Height support
  public void setTabsHeight() {
    PropertiesComponent.getInstance().setValue(TABS_HEIGHT, MTConfig.getInstance().getTabsHeight(), 24);
  }

  public void setTabsHeight(int newTabsHeight) {
    MTConfig.getInstance().setTabsHeight(newTabsHeight);
    this.setTabsHeight();
  }
  //endregion

  /**
   * Trigger a reloadUI event
   */
  private void reloadUI() {
    try {
      UIManager.setLookAndFeel(new MTLaf(MTConfig.getInstance().getSelectedTheme()));
      applyFonts();
    }
    catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }
}
