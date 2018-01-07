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
import com.chrisrm.idea.themes.models.MTThemeColor;
import com.intellij.openapi.components.ServiceManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.chrisrm.idea.themes.BundledThemeEP.EP_NAME;

public final class MTBundledThemesManager {
  private final List<MTBundledTheme> bundledThemes = new ArrayList<>();

  public MTBundledThemesManager() {

  }

  public static MTBundledThemesManager getInstance() {
    return ServiceManager.getService(MTBundledThemesManager.class);
  }

  public List<MTBundledTheme> getBundledThemes() {
    return bundledThemes;
  }

  public void loadBundledThemes() throws Exception {
    for (final BundledThemeEP ep : EP_NAME.getExtensions()) {
      final MTBundledTheme mtBundledTheme = loadBundledTheme(ep.path + ".xml", ep);
      mtBundledTheme.setName(ep.name);
      getBundledThemes().add(mtBundledTheme);
    }
  }

  public MTThemeable getActiveTheme() {
    final MTThemeFacade selectedTheme = MTConfig.getInstance().getSelectedTheme();
    return selectedTheme.getTheme();
  }

  private MTBundledTheme loadBundledTheme(final String resource, final BundledThemeEP ep) throws Exception {
    final URL url = ep.getClass().getResource(resource);
    if (url == null) {
      throw new Exception("Cannot read theme from " + resource);
    }

    final XStream xStream = new XStream(new DomDriver());
    xStream.alias("mtTheme", MTBundledTheme.class);
    xStream.alias("color", MTThemeColor.class);

    xStream.useAttributeFor(MTBundledTheme.class, "id");
    xStream.useAttributeFor(MTBundledTheme.class, "editorColorsScheme");
    xStream.useAttributeFor(MTBundledTheme.class, "dark");

    xStream.useAttributeFor(MTThemeColor.class, "id");
    xStream.useAttributeFor(MTThemeColor.class, "value");

    try {
      return (MTBundledTheme) xStream.fromXML(url);
    } catch (final Exception e) {
      return new MTBundledTheme();
    }
  }

  public static class MTThemesConverter implements SingleValueConverter {

    @Override
    public String toString(final Object obj) {
      return ((MTThemes) obj).toString();
    }

    @Override
    public Object fromString(final String str) {
      return MTThemes.getThemeFor(str);
    }

    @Override
    public boolean canConvert(final Class type) {
      return type.equals(MTThemeFacade.class);
    }
  }
}
