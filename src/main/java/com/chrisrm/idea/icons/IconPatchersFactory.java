/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.icons;

import com.chrisrm.idea.icons.patchers.ExternalIconsPatcher;
import com.chrisrm.idea.icons.patchers.IconPathPatchers;
import com.thoughtworks.xstream.XStream;
import org.jetbrains.annotations.NonNls;

import java.net.URL;
import java.util.Set;

public enum IconPatchersFactory {
  PATCH_PATCH;

  @NonNls
  private static final String ICON_PATCHERS_XML = "/icon_patchers.xml";

  @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
  static IconPathPatchers create() {
    final URL xml = IconPatchersFactory.class.getResource(ICON_PATCHERS_XML);
    @NonNls final XStream xStream = new XStream();
    XStream.setupDefaultSecurity(xStream);
    xStream.allowTypesByWildcard(new String[]{"com.chrisrm.idea.icons.patchers.*"});

    xStream.alias("iconPathPatchers", IconPathPatchers.class);
    xStream.alias("iconPatchers", Set.class);
    xStream.alias("glyphPatchers", Set.class);
    xStream.alias("filePatchers", Set.class);

    xStream.alias("patcher", ExternalIconsPatcher.class);

    xStream.useAttributeFor(ExternalIconsPatcher.class, "append");
    xStream.useAttributeFor(ExternalIconsPatcher.class, "remove");
    xStream.useAttributeFor(ExternalIconsPatcher.class, "name");

    try {
      return (IconPathPatchers) xStream.fromXML(xml);
    } catch (final RuntimeException e) {
      return new IconPathPatchers();
    }
  }
}
