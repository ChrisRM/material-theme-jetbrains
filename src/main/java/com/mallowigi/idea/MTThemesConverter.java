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

package com.mallowigi.idea;

import com.mallowigi.idea.themes.models.MTBundledTheme;
import com.mallowigi.idea.themes.models.MTDarkBundledTheme;
import com.mallowigi.idea.themes.models.MTLightBundledTheme;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.jetbrains.annotations.NonNls;

/**
 * Converts the object read from XML into a MTBundledTheme
 */
@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter")
final class MTThemesConverter implements Converter {
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
    // todo convert this polymorphism into a strategy pattern or something
    final boolean dark = Boolean.parseBoolean(reader.getAttribute("dark"));
    final Class<? extends MTBundledTheme> themeClass = dark ? MTLightBundledTheme.class : MTDarkBundledTheme.class;
    final Object result = reflectionProvider.newInstance(themeClass);
    return context.convertAnother(result, themeClass, defaultConverter);
  }

  @Override
  public boolean canConvert(final Class type) {
    return MTBundledTheme.class.isAssignableFrom(type);
  }
}
