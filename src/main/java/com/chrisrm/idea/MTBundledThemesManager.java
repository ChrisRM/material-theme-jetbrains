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
import com.intellij.openapi.components.ServiceManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.chrisrm.idea.themes.BundledThemeEP.EP_NAME;

public final class MTBundledThemesManager {
  private final Map<String, MTBundledTheme> bundledThemes = new HashMap<>();

  public MTBundledThemesManager() {

  }

  public static MTBundledThemesManager getInstance() {
    return ServiceManager.getService(MTBundledThemesManager.class);
  }

  public Map<String, MTBundledTheme> getBundledThemes() {
    return bundledThemes;
  }

  public void loadBundledThemes() throws Exception {
    for (final BundledThemeEP ep : EP_NAME.getExtensions()) {
      final MTBundledTheme mtDarkBundledTheme = loadBundledTheme(ep.path + ".xml", ep);
      mtDarkBundledTheme.setName(ep.name);
      getBundledThemes().put(mtDarkBundledTheme.getThemeId(), mtDarkBundledTheme);
    }
  }

  public MTThemeable getActiveTheme() {
    final MTThemeFacade selectedTheme = MTConfig.getInstance().getSelectedTheme();
    return selectedTheme.getTheme();
  }

  public MTBundledTheme findTheme(final String selectedTheme) {
    return bundledThemes.get(selectedTheme);
  }

  private MTBundledTheme loadBundledTheme(final String resource, final BundledThemeEP ep) throws Exception {
    final URL url = ep.getClass().getResource(resource);
    if (url == null) {
      throw new Exception("Cannot read theme from " + resource);
    }

    final XStream xStream = new XStream(new DomDriver());
    xStream.alias("mtTheme", MTDarkBundledTheme.class);
    xStream.alias("color", MTThemeColor.class);

    xStream.useAttributeFor(MTThemeColor.class, "id");
    xStream.useAttributeFor(MTThemeColor.class, "value");

    xStream.registerConverter(new MTThemesConverter(
        xStream.getConverterLookup().lookupConverterForType(MTBundledTheme.class),
        xStream.getReflectionProvider()
    ));

    xStream.addDefaultImplementation(MTDarkBundledTheme.class, MTBundledTheme.class);
    xStream.addDefaultImplementation(MTLightBundledTheme.class, MTBundledTheme.class);

    try {
      return (MTBundledTheme) xStream.fromXML(url);
    } catch (final Exception e) {
      return new MTDarkBundledTheme();
    }
  }

  public final class MTThemesConverter implements Converter {
    private final Converter defaultConverter;
    private final ReflectionProvider reflectionProvider;

    public MTThemesConverter(final Converter defaultConverter, final ReflectionProvider reflectionProvider) {
      this.defaultConverter = defaultConverter;
      this.reflectionProvider = reflectionProvider;
    }

    @Override
    public void marshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context) {
      defaultConverter.marshal(source, writer, context);
    }

    @Override
    public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context) {
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
