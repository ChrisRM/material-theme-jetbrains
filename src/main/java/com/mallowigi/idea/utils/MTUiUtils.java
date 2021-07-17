/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.utils;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInsight.hint.HintUtil;
import com.intellij.ide.actions.BigPopupUI;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.newItemPopup.NewItemSimplePopupPanel;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.ex.ApplicationEx;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.ui.LightweightHint;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTProjectConfig;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * All kinds of utils and constants
 */
@SuppressWarnings({"unused",
  "StaticMethodOnlyUsedInOneClass",
  "ClassWithTooManyMethods",
  "HardcodedFileSeparator",
  "OverlyComplexClass"})
public enum MTUiUtils {
  DEFAULT;

  @NonNls
  public static final String MATERIAL_FONT = MTThemeManager.DEFAULT_FONT;
  @NonNls
  public static final String HELP_PREFIX = "com.mallowigi.idea.help";
  @NonNls
  public static final String DOCS_URL = "https://www.material-theme.com/";
  @NonNls
  public static final String PLUGIN_ID = "com.chrisrm.idea.MaterialThemeUI";
  @NonNls
  public static final String APPEARANCE_SECTION = "Appearance";
  @NonNls
  public static final String DARCULA = "Darcula";
  @NonNls
  public static final String PLUGIN_NAME = "MaterialThemeUI";

  @SuppressWarnings("HardcodedFileSeparator")
  @NonNls
  public static final @Nullable Icon LINK_ICON = IconLoader.findIcon("icons/mt/link2.svg");

  private static final RenderingHints RENDERING_HINTS;
  @NonNls
  public static final String NOTO_SANS = "Noto Sans";
  @NonNls
  public static final String PROJECT_PATTERN = "\\{project\\}";

  static {
    RENDERING_HINTS = new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
    RENDERING_HINTS.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    RENDERING_HINTS.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    RENDERING_HINTS.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    RENDERING_HINTS.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
  }

  public static Map getHints() {
    return Collections.unmodifiableMap(RENDERING_HINTS);
  }

  /**
   * Find a font
   *
   * @param name font name
   * @return font if found
   */
  @Nullable
  public static Font findFont(@NonNls final String name) {
    for (final Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
      if (font.getFamily().equals(name)) {
        return font;
      }
    }
    return null;
  }

  /**
   * Return a color between dark and light colors according to the current look and feel
   *
   * @param darkColor  the color to return if dark
   * @param lightColor the color to return if light
   * @return the color
   */
  @SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
  public static Color lightOrDark(final ColorUIResource darkColor, final ColorUIResource lightColor) {
    return UIUtil.isUnderDarcula() ? darkColor : lightColor;
  }

  /**
   * Return a color between dark and light colors according to the current look and feel
   *
   * @param darkColor  the color to return if dark
   * @param lightColor the color to return if light
   * @return the color
   */
  @SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
  public static Color lightOrDark(final Color darkColor, final Color lightColor) {
    return UIUtil.isUnderDarcula() ? darkColor : lightColor;
  }

  /**
   * Brightens a color by tones if the current laf is dark, otherwise darkens it
   *
   * @param color the color to brighten/darken
   * @param tones number of tones to darken
   * @return new color
   */
  public static Color brighter(final Color color, final int tones) {
    return UIUtil.isUnderDarcula() ? ColorUtil.brighter(color, tones) : ColorUtil.darker(color, tones);
  }

  /**
   * Darkens a color by tones if the current laf is dark, otherwise brightens it
   *
   * @param color the color to brighten/darken
   * @param tones number of tones to darken
   * @return new color
   */
  public static Color darker(final Color color, final int tones) {
    return UIUtil.isUnderDarcula() ? ColorUtil.darker(color, tones) : ColorUtil.brighter(color, tones);
  }

  /**
   * Returns a color according to specific conditions:
   * If Material Theme is enabled, returns the mtColor
   * Otherwise if LAF is dark returns the darkColor
   * Otherwise if LAF is light returns the lightColor
   *
   * @param mtColor    material theme color
   * @param darkColor  darcula color
   * @param lightColor light color
   * @return color
   */
  public static Color getColor(final Color mtColor, @NotNull final Color darkColor, @NotNull final Color lightColor) {
    final Color defaultColor = UIUtil.isUnderDarcula() ? darkColor : lightColor;
    return ObjectUtils.notNull(mtColor, defaultColor);
  }

  /**
   * Checks if current LAF is Darcula
   *
   * @return true if darcula
   */
  public static boolean isDarcula() {
    return Objects.equals(Objects.requireNonNull(LafManager.getInstance().getCurrentLookAndFeel()).toString(), DARCULA);
  }

  /**
   * Restarts the IDE :-)
   */
  public static void restartIde() {
    final Application application = ApplicationManager.getApplication();
    if (application instanceof ApplicationImpl) {
      ((ApplicationEx) application).restart(true);
    } else {
      application.restart();
    }
  }

  /**
   * Get plugin version
   *
   * @return Plugin version
   */
  public static String getVersion() {
    final IdeaPluginDescriptor plugin = getPlugin();
    if (plugin != null) {
      return plugin.getVersion();
    }
    return MTConfig.getInstance().getVersion();
  }

  /**
   * Plugin name
   *
   * @return plugin name
   */
  public static Object getPluginName() {
    return MaterialThemeBundle.message("plugin.name");
  }

  /**
   * Get plugin descriptor
   *
   * @return the plugin
   */
  private static IdeaPluginDescriptor getPlugin() {
    return PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID));
  }

  public static boolean hasAtomPluginInstalled() {
    return PluginManager.isPluginInstalled(PluginId.getId(MaterialThemeBundle.message("atom.pluginid")));
  }

  /**
   * Convert a color to dword
   *
   * @param color color
   * @return dword
   */
  public static int colorToDword(final Color color) {
    final Color result = new Color(color.getBlue(), color.getGreen(), color.getRed());
    return result.getRGB();
  }

  /**
   * Convert a dword to color
   *
   * @param dword dword
   * @return color
   */
  public static Color dwordToColor(final int dword) {
    final Color color = new Color(dword);
    return new Color(color.getBlue(), color.getGreen(), color.getRed());
  }

  public static NotificationListener.Adapter openAppearanceSettings(final Project project) {
    return new NotificationListener.Adapter() {
      @Override
      protected void hyperlinkActivated(@NotNull final Notification notification, @NotNull final HyperlinkEvent e) {
        ApplicationManager.getApplication().invokeLater(() -> ShowSettingsUtil.getInstance().showSettingsDialog(
          project,
          APPEARANCE_SECTION), ModalityState.NON_MODAL);
      }
    };
  }

  /**
   * Iterate over theme resources and fill up the UIManager
   */
  public static void buildResources(final Iterable<String> resources, final Color color) {
    for (final String resource : resources) {
      UIManager.getDefaults().put(resource, color);
    }
  }

  /**
   * Build accent resources and put them in the UI Manager
   */
  public static void buildAccentResources(final Iterable<String> resources, final Color color, final boolean isAccentMode) {
    for (final String resource : resources) {
      if (isAccentMode) {
        UIManager.put(resource, color);
      } else {
        final Color defaultColor = UIManager.getLookAndFeelDefaults().getColor(resource);
        if (defaultColor != null) {
          UIManager.put(resource, defaultColor);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends Enum<T>> String parseEnumValue(final Object value, final T defaultValue) {
    if (value instanceof String) {
      @NonNls final String name = StringUtil.toUpperCase((String) value);
      for (final T t : ((Class<T>) defaultValue.getClass()).getEnumConstants()) {
        if (t.name().equals(name)) {
          return StringUtil.toLowerCase(value.toString());
        }
      }
    }
    return StringUtil.toLowerCase(defaultValue.name());
  }

  /**
   * Returns whether the user has a frame wallpaper set
   */
  public static boolean hasFrameWallpaper() {
    return PropertiesComponent.getInstance().getValue("old.mt." + IdeBackgroundUtil.FRAME_PROP) != null ||
      PropertiesComponent.getInstance().getValue(IdeBackgroundUtil.FRAME_PROP) != null;
  }

  public static LightweightHint createHintTooltip(final String message, final Dimension preferredSize) {
    // Create a tooltip
    final JComponent informationLabel = HintUtil.createInformationLabel(message);
    informationLabel.setBorder(JBUI.Borders.empty(6, 6, 5, 6));
    informationLabel.setBackground(MTUI.Panel.getContrastBackground());
    informationLabel.setOpaque(true);
    informationLabel.setPreferredSize(preferredSize);

    return new LightweightHint(informationLabel);
  }

  public static LightweightHint createLinkHintTooltip(final String message, final String linkUrl, final Dimension preferredSize) {
    // Create a tooltip
    final JComponent informationLabel = HintUtil.createInformationLabel(
      message,
      e -> {
        try {
          if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            Desktop.getDesktop().browse(new URI(linkUrl));
          }
        } catch (final IOException | URISyntaxException exception) {
          // do nothing
        }
      },
      null,
      null
    );
    informationLabel.setBorder(JBUI.Borders.empty(6, 6, 5, 6));
    informationLabel.setBackground(MTUI.Panel.getContrastBackground());
    informationLabel.setOpaque(true);
    informationLabel.setPreferredSize(preferredSize);

    return new LightweightHint(informationLabel);
  }

  public static void disablePremium(final JComponent component) {
    component.setEnabled(false);
    component.setToolTipText(null);
    final AtomicBoolean isHintHidden = new AtomicBoolean(false);

    // Create a tooltip
    final LightweightHint hint = createLinkHintTooltip(
      MaterialThemeBundle.message("plugin.premium"),
      MaterialThemeBundle.message("plugin.buyLink"),
      new Dimension(500, 32)
    );

    component.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(final MouseEvent e) {
        isHintHidden.set(showHint(component, hint, isHintHidden).get());
      }
    });
  }

  public static void disableEnable(final JComponent component, final boolean state) {
    component.setEnabled(state);
  }

  @SuppressWarnings("MagicNumber")
  static AtomicBoolean showHint(final JComponent component, final LightweightHint hint, final AtomicBoolean isHintHidden) {
    final AtomicBoolean newIsHintHidden = new AtomicBoolean(isHintHidden.get());
    if (isHintHidden.get()) {
      ApplicationManager.getApplication().invokeLater(() -> {
        HintManager.getInstance().hideAllHints();
        HintManager.getInstance().showHint(
          hint.getComponent(),
          RelativePoint.getSouthWestOf(component),
          HintManager.HIDE_BY_ANY_KEY | HintManager.HIDE_BY_SCROLLING,
          3000,
          () -> newIsHintHidden.set(true)
        );
        newIsHintHidden.set(false);
      });
    } else {
      HintManager.getInstance().hideHints(HintManager.HIDE_BY_ANY_KEY, true, false);
      newIsHintHidden.set(true);
      return showHint(component, hint, newIsHintHidden);
    }
    return newIsHintHidden;
  }

  /**
   * Returns whether the value is between min and max
   *
   * @param value value to check
   * @param min   min bound
   * @param max   max bound
   */
  public static int valueInRange(final int value, final int min, final int max) {
    return Integer.min(max, Integer.max(value, min));
  }

  public static JBColor toJBColor(final Color color) {
    return new JBColor(color, color);
  }

  /**
   * Generate a random color
   */
  @SuppressWarnings("UnsecureRandomNumberGeneration")
  public static Color getRandomColor() {
    final Random random = new Random();
    final float hue = random.nextFloat();
    // Saturation between 0.1 and 0.3
    final float saturation = (random.nextInt(2000) + 1000) / 10000.0f;
    final float luminance = 0.4f;
    return Color.getHSBColor(hue, saturation, luminance);
  }

  /**
   * Converts a string to a color
   */
  public static int stringToARGB(final CharSequence charSequence) {
    return hashCode(charSequence);
  }

  @SuppressWarnings("CharUsedInArithmeticContext")
  private static int hashCode(final CharSequence charSequence) {
    int hash = 0;
    final int length = charSequence.length();
    for (int i = 0; i < length; i++) {
      hash = charSequence.charAt(i) + ((hash << 5) - hash);
    }
    return hash;
  }

  public static @NotNull EditorColorsScheme getCurrentColorScheme() {
    return EditorColorsManager.getInstance().getGlobalScheme();
  }

  public static @Nullable MTProjectConfig getProjectConfigIfEnabled(final Project project) {
    if (project != null && !project.isDisposed()) {
      final MTProjectConfig projectConfig = MTProjectConfig.getInstance(project);
      if (projectConfig.isActive()) {
        return projectConfig;
      }
      return null;
    }
    return null;
  }

  public static Field findField(final Stream<Field> fieldStream, final @NonNls String name) {
    return fieldStream.filter(field -> name.equals(field.getName()))
                      .findFirst()
                      .orElse(null);
  }

  public static boolean isFrameWindow(final Window window) {
    return window instanceof JFrame && !isHeavyWeightWindow(window);
  }

  private static boolean isHeavyWeightWindow(final Window window) {
    return window != null && window.getClass().getName().contains("HeavyWeightWindow");
  }

  public static boolean isDialogWindow(final Window window) {
    if (window == null) {
      return false;
    }

    if (window instanceof JDialog) {
      return true;
    } else if (!(window instanceof JFrame)) {
      return isBigPopup(window);
    }
    return false;
  }

  public static boolean isContextMenu(final Window window) {
    if (window instanceof JWindow) {
      final JLayeredPane layeredPane = ((RootPaneContainer) window).getLayeredPane();
      for (final Component component : layeredPane.getComponents()) {
        if (component instanceof JPanel) {
          final Component[] children = ((Container) component).getComponents();
          if (ContainerUtil.findInstance(children, JPopupMenu.class) != null) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean isBigPopup(final Window window) {
    if (window instanceof JWindow) {
      final JLayeredPane layeredPane = ((RootPaneContainer) window).getLayeredPane();
      return hasComponentOfTypes(layeredPane, BigPopupUI.class, NewItemSimplePopupPanel.class);
    }
    return false;
  }

  public static boolean hasComponentOfTypes(final Component parent, final Class... types) {
    if (parent instanceof Container) {
      final Component[] children = ((Container) parent).getComponents();
      if (ContainerUtil.find(children, child -> isInstanceOfTypes(child, types)) != null) {
        return true;
      } else if (children.length > 0) {
        return ContainerUtil.find(children, child -> hasComponentOfTypes(child, types)) != null;
      }
    }
    return false;
  }

  public static boolean isInstanceOfTypes(final Object object, final Class... types) {
    return !ContainerUtil.all(Arrays.asList(types), type -> !type.isInstance(object));
  }

  private static @Nullable String getWindowTitle(final Window window) {
    if (window instanceof JDialog) {
      return ((Dialog) window).getTitle();
    } else if (window instanceof JFrame) {
      return ((Frame) window).getTitle();
    }
    return null;
  }
}
