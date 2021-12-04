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
package com.mallowigi.idea.themes

import com.mallowigi.idea.themes.models.MTBundledTheme
import com.mallowigi.idea.themes.models.MTDarkBundledTheme
import com.mallowigi.idea.themes.models.MTLightBundledTheme
import com.thoughtworks.xstream.converters.Converter
import com.thoughtworks.xstream.converters.MarshallingContext
import com.thoughtworks.xstream.converters.UnmarshallingContext
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider
import com.thoughtworks.xstream.io.HierarchicalStreamReader
import com.thoughtworks.xstream.io.HierarchicalStreamWriter

/**
 * Converts the object read from XML into a MTBundledTheme
 */
internal class MTThemesConverter(
  private val defaultConverter: Converter,
  private val reflectionProvider: ReflectionProvider,
) : Converter {
  /**
   * Marshals the object into XML
   *
   * @param source the source file
   * @param writer the xstream writer
   * @param context the marshalling context
   */
  override fun marshal(source: Any, writer: HierarchicalStreamWriter, context: MarshallingContext) =
    defaultConverter.marshal(source, writer, context)

  /**
   * Unmarshal the xml element into an object
   *
   * @param reader xstream xml reader
   * @param context the unmarshalling context
   * @return
   */
  @Suppress("HardCodedStringLiteral")
  override fun unmarshal(reader: HierarchicalStreamReader, context: UnmarshallingContext): Any {
    val dark = reader.getAttribute("dark").toBoolean()
    val themeClass = when {
      dark -> MTDarkBundledTheme::class.java
      else -> MTLightBundledTheme::class.java
    }
    val result = reflectionProvider.newInstance(themeClass)
    return context.convertAnother(result, themeClass, defaultConverter)
  }

  /**
   * Can convert?
   *
   * @param type class
   */
  override fun canConvert(type: Class<*>?): Boolean = MTBundledTheme::class.java.isAssignableFrom(type)
}
