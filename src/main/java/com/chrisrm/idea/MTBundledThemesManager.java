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

import com.chrisrm.idea.themes.BundledThemeEP;
import com.chrisrm.idea.themes.MTThemeable;
import com.chrisrm.idea.themes.models.MTBundledTheme;
import com.chrisrm.idea.themes.models.MTDarkBundledTheme;
import com.chrisrm.idea.themes.models.MTLightBundledTheme;
import com.chrisrm.idea.themes.models.MTThemeColor;
import com.chrisrm.idea.ui.MTTreeUI;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.DumbAwareAction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the Bundled themes (external themes)
 */
@SuppressWarnings("OverlyCoupledClass")
public final class MTBundledThemesManager {
  private final Map<String, MTBundledTheme> bundledThemes = new HashMap<>();

  public static MTBundledThemesManager getInstance() {
    return ServiceManager.getService(MTBundledThemesManager.class);
  }

  /**
   * Returns the installed bundled (external) themes
   *
   * @return the list of installed external themes
   */
  public Map<String, MTBundledTheme> getBundledThemes() {
    return Collections.unmodifiableMap(bundledThemes);
  }

  /**
   * Load external themes defined in other plugins' plugin.xml <bundledTheme> extension point
   *
   * @throws IOException the exception
   */
  public void loadBundledThemes() throws IOException {
    for (final BundledThemeEP ep : BundledThemeEP.EP_NAME.getExtensions()) {
      final MTBundledTheme mtBundledTheme = loadBundledTheme(ep.path + ".xml", ep);
      mtBundledTheme.setName(ep.name);
      bundledThemes.put(mtBundledTheme.getThemeId(), mtBundledTheme);

      MTThemes.installTheme(mtBundledTheme);
    }
  }

  /**
   * Gets active theme.
   *
   * @return the active theme
   */
  private static MTThemeable getActiveTheme() {
    return MTConfig.getInstance().getSelectedTheme().getTheme();
  }

  /**
   * Load an external theme from the XML
   *
   * @param resource xml file
   * @param ep       The Bundled Theme extension point
   * @return a new instance of MTBundledTheme
   */
  private static MTBundledTheme loadBundledTheme(@NonNls final String resource, final BundledThemeEP ep) throws IOException {
    final URL url = ep.getLoaderForClass().getResource(resource);
    if (url == null) {
      throw new IOException("Cannot read theme from " + resource);
    }

    @NonNls final XStream xStream = new XStream(new DomDriver());
    xStream.alias("mtTheme", MTDarkBundledTheme.class);
    xStream.alias("color", MTThemeColor.class);

    xStream.useAttributeFor(MTThemeColor.class, "id");
    xStream.useAttributeFor(MTThemeColor.class, "value");

    // Use a converter to create a MTDarkBundledTheme or MTLightBundledTheme according to the "dark" property
    xStream.registerConverter(new MTThemesConverter(
        xStream.getConverterLookup().lookupConverterForType(MTBundledTheme.class),
        xStream.getReflectionProvider()
    ));

    xStream.addDefaultImplementation(MTDarkBundledTheme.class, MTBundledTheme.class);
    xStream.addDefaultImplementation(MTLightBundledTheme.class, MTBundledTheme.class);

    try {
      return (MTBundledTheme) xStream.fromXML(url);
    } catch (final RuntimeException e) {
      return new MTDarkBundledTheme();
    }
  }

  /**
   * Add bundled themes
   *
   * @param group               quick switch group
   * @param ourCurrentAction    default current icon (check mark)
   * @param ourNotCurrentAction default not current icon (empty)
   */
  public void addBundledThemes(@NotNull final DefaultActionGroup group, final Icon ourCurrentAction, final Icon ourNotCurrentAction) {
    final Map<String, MTBundledTheme> bundledThemes = getBundledThemes();
    final MTThemeable current = getActiveTheme();

    for (final MTBundledTheme bundledTheme : bundledThemes.values()) {
      addBundledTheme(group, bundledTheme, current, ourCurrentAction, ourNotCurrentAction);
    }
  }

  /**
   * Adds a bundled theme to the list, and mark it if it's the current theme
   *
   * @param group               switch scheme group
   * @param theme               theme
   * @param current             current theme
   * @param ourCurrentAction    default current icon (check mark)
   * @param ourNotCurrentAction default not current icon (empty)
   */
  @SuppressWarnings("FeatureEnvy")
  private static void addBundledTheme(final DefaultActionGroup group,
                                      final MTThemeable theme,
                                      final MTThemeable current,
                                      final Icon ourCurrentAction,
                                      final Icon ourNotCurrentAction) {
    final Icon themeIcon = theme.getIcon() != null ? theme.getIcon() : ourNotCurrentAction;

    group.add(new DumbAwareAction(theme.getName(),
                                  theme.getEditorColorsScheme(),
                                  theme == current ? ourCurrentAction : themeIcon) {
      @Override
      public void actionPerformed(@NotNull final AnActionEvent e) {
        //         Install theme if not installed
        final MTThemeFacade externalTheme = MTThemes.installTheme(theme);

        MTTreeUI.resetIcons();
        MTThemeManager.getInstance().activate(externalTheme, true);
        MTAnalytics.getInstance().track(MTAnalytics.SELECT_THEME, externalTheme);
      }
    });
  }

  /**
   * Converts the object read from XML into a MTBundledTheme
   */
  private static final class MTThemesConverter implements Converter {
    private final Converter defaultConverter;
    private final ReflectionProvider reflectionProvider;

    /**
     * Instantiates a new Mt themes converter.
     *
     * @param defaultConverter   the default converter
     * @param reflectionProvider the reflection provider
     */
    MTThemesConverter(final Converter defaultConverter, final ReflectionProvider reflectionProvider) {
      this.defaultConverter = defaultConverter;
      this.reflectionProvider = reflectionProvider;
    }

    @Override
    public void marshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context) {
      defaultConverter.marshal(source, writer, context);
    }

    @Override
    public Object unmarshal(@NonNls final HierarchicalStreamReader reader, final UnmarshallingContext context) {
      final boolean dark = Boolean.parseBoolean(reader.getAttribute("dark"));
      final Class<? extends MTBundledTheme> themeClass = dark ? MTDarkBundledTheme.class : MTLightBundledTheme.class;
      final Object result = reflectionProvider.newInstance(themeClass);
      return context.convertAnother(result, themeClass, defaultConverter);
    }

    @Override
    public boolean canConvert(final Class type) {
      return MTBundledTheme.class.isAssignableFrom(type);
    }
  }
}
