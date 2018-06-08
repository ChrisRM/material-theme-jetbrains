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

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.util.JDOMUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MaterialThemeInfo {
  private static final String WIZARD_KEY = "wizard-pages";

  private static List<WizardPage> myPages = new ArrayList<>();

  public static MaterialThemeInfo getInstance() {
    return ServiceManager.getService(MaterialThemeInfo.class);
  }

  public MaterialThemeInfo() {
    final String resource = "/materialThemeInfo.xml";
    try {
      final Document doc = JDOMUtil.loadDocument(MaterialThemeInfo.class, resource);
      loadState(doc.getRootElement());
    } catch (final Exception e) {
      throw new RuntimeException("Cannot load resource: " + resource, e);
    }
  }

  public List<WizardPage> getPages() {
    return myPages;
  }

  private void loadState(final Element parentNode) {
    myPages = new ArrayList<>();
    for (final Element child: getChildren(parentNode, WIZARD_KEY)) {
      myPages.add(new WizardPageImpl(child));
    }
  }

  @NotNull
  private static List<Element> getChildren(final Element parentNode, final String name) {
    return parentNode.getChildren(name, parentNode.getNamespace());
  }

  public interface WizardPage {
    @NotNull
    String getTitle();

    @Nullable
    String getCategory();
  }

  private class WizardPageImpl implements WizardPage {
    private final String myTitle;
    private final String myCategory;

    public WizardPageImpl(final Element e) {
      myTitle = e.getAttributeValue("title");
      myCategory = e.getAttributeValue("category");
    }

    @NotNull
    @Override
    public String getTitle() {
      return myTitle;
    }

    @Override
    public String getCategory() {
      return myCategory;
    }
  }
}
