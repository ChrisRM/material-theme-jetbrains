package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTLaf;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.utils.UIReplacer;
import com.google.common.collect.ImmutableList;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.laf.IntelliJLaf;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
      "Table.background",
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
    Collection<String> schemes = new ArrayList<String>();
    for (MTTheme theme : MTTheme.values()) {
      schemes.add(theme.getEditorColorsScheme());
    }
    EDITOR_COLORS_SCHEMES = ImmutableList.copyOf(schemes);
  }

  public static MTThemeManager getInstance() {
    return ServiceManager.getService(MTThemeManager.class);
  }

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

  /**
   * Apply contrast
   *
   * @param apply
   */
  private void applyContrast(boolean apply) {
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();
    for (String resource : contrastedResources) {
      Color contrastedColor = apply ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
      UIManager.put(resource, contrastedColor);
    }
  }

  private void resetContrast() {
    for (String resource : contrastedResources) {
      UIManager.put(resource, null);
    }
  }

  private static String getSettingsPrefix() {
    PluginId pluginId = PluginManager.getPluginByClassName(MTTheme.class.getName());
    return pluginId == null ? "com.chrisrm.idea.MaterialThemeUI" : pluginId.getIdString();
  }

  public void activate() {
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();
    if (!MTConfig.getInstance().isMaterialTheme()) {
      removeTheme(mtTheme);
      return;
    }

    this.activate(mtTheme);
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

  public void activate(MTTheme mtTheme) {
    MTConfig.getInstance().setSelectedTheme(mtTheme);

    try {
      UIManager.setLookAndFeel(new MTLaf(mtTheme));
      JBColor.setDark(mtTheme.isDark());
      IconLoader.setUseDarkIcons(mtTheme.isDark());

      PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", mtTheme.name());
      applyContrast(MTConfig.getInstance().getIsContrastMode());
      applyCompactSidebar(MTConfig.getInstance().isCompactSidebar());
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

    UISettings uiSettings = UISettings.getInstance();
    UIDefaults lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();

    if (uiSettings.getOverrideLafFonts()) {
      applyCustomFonts(lookAndFeelDefaults, uiSettings.getFontFace(), uiSettings.getFontSize());
    } else {
      applyCustomFonts(lookAndFeelDefaults, "Roboto", JBUI.scale(12));

    }

    UIReplacer.patchUI();
  }

  /**
   * Use compact sidebar option
   *
   * @param compactSidebar
   */
  private void applyCompactSidebar(boolean compactSidebar) {
    int rowHeight = compactSidebar ? JBUI.scale(18) : JBUI.scale(24);
    UIManager.put("Tree.rowHeight", rowHeight);
  }

  /**
   * Set contrast and reactivate theme
   */
  public void toggleContrast() {
    MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsContrastMode(!mtConfig.getIsContrastMode());

    this.activate(mtConfig.getSelectedTheme());
  }
}
