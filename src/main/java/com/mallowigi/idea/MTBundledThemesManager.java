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

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.extensions.ExtensionPointListener;
import com.intellij.openapi.extensions.PluginDescriptor;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.util.ThrowableRunnable;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.BundledThemeEP;
import com.mallowigi.idea.themes.MTThemes;
import com.mallowigi.idea.themes.models.MTBundledTheme;
import com.mallowigi.idea.themes.models.MTDarkBundledTheme;
import com.mallowigi.idea.themes.models.MTLightBundledTheme;
import com.mallowigi.idea.themes.models.MTThemeColor;
import com.thoughtworks.xstream.XStream;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Manages the Bundled themes (external themes)
 */
@SuppressWarnings("WeakerAccess")
public final class MTBundledThemesManager implements Disposable {
  MTBundledThemesManager() {
    BundledThemeEP.EP_NAME.addExtensionPointListener(new BundledThemeEPExtensionPointListener(), this);
  }

  private final Map<String, MTBundledTheme> bundledThemes = new HashMap<>(10);

  public static MTBundledThemesManager getInstance() {
    return ApplicationManager.getApplication().getService(MTBundledThemesManager.class);
  }

  @Override
  public void dispose() {
    // do nothing
  }

  void installBundledTheme(final BundledThemeEP ep) throws IOException {
    final MTBundledTheme mtBundledTheme = loadBundledTheme(ep.path + ".xml", ep);

    Objects.requireNonNull(mtBundledTheme).setThemeName(ep.name);
    bundledThemes.put(mtBundledTheme.getThemeId(), mtBundledTheme);

    MTThemes.installTheme(mtBundledTheme);
  }

  void removeBundledTheme(final BundledThemeEP ep) throws IOException {
    final MTBundledTheme mtBundledTheme = loadBundledTheme(ep.path + ".xml", ep);
    if (mtBundledTheme != null) {
      bundledThemes.remove(mtBundledTheme.getThemeId());
    }
  }

  public static MTBundledTheme loadBundledTheme(final VirtualFile file) {
    final File url = new File(file.getPath());
    return loadFromXml(null, url);
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

    return loadFromXml(url, null);
  }

  @SuppressWarnings("MethodWithMultipleReturnPoints")
  @Nullable
  private static MTBundledTheme loadFromXml(@Nullable final URL url, @Nullable final File file) {
    @NonNls final XStream xStream = configureXStream();

    try {
      if (url != null) {
        return (MTBundledTheme) xStream.fromXML(url);
      } else if (file != null) {
        return (MTBundledTheme) xStream.fromXML(file);
      }
      return null;
    } catch (final RuntimeException e) {
      return null;
    }
  }

  @SuppressWarnings("FeatureEnvy")
  public static void saveTheme(final MTBundledTheme customTheme) {
    @NonNls final FileSaverDialog saveFileDialog = FileChooserFactory.getInstance().createSaveFileDialog(
      new FileSaverDescriptor(
        MaterialThemeBundle.message("SaveThemeDialog.placeholder"),
        MaterialThemeBundle.message("SaveThemeDialog.title"),
        "xml"),
      (Project) null);
    final VirtualFileWrapper target = saveFileDialog.save(customTheme.getThemeId() + ".xml");

    if (target != null) {
      final VirtualFile targetFile = target.getVirtualFile(true);
      final String message;

      message = targetFile != null ?
                generateMessage(customTheme, targetFile) :
                ApplicationBundle.message("scheme.exporter.ui.cannot.write.message");

      Messages.showDialog(message,
        MaterialThemeBundle.message("common.status"),
        new String[]{MaterialThemeBundle.message("common.ok")},
        0,
        null);

    }

  }

  private static String generateMessage(final MTBundledTheme customTheme, final VirtualFile targetFile) {
    String message;
    try {
      WriteAction.run(new ThrowableRunnable<Throwable>() {
        @SuppressWarnings("SyntheticAccessorCall")
        @Override
        public void run() throws IOException {
          final OutputStream outputStream = targetFile.getOutputStream(this);
          exportTheme(customTheme, outputStream);
        }
      });
      message = ApplicationBundle
        .message("scheme.exporter.ui.scheme.exported.message",
          customTheme.getThemeName(),
          customTheme.getThemeName(),
          targetFile.getPresentableUrl());
    } catch (final Throwable e) {
      message = ApplicationBundle.message("scheme.exporter.ui.export.failed", e.getMessage());
    }
    return message;
  }

  @SuppressWarnings({"CheckStyle",
    "MethodOnlyUsedFromInnerClass"})
  private static void exportTheme(final MTBundledTheme customTheme, final OutputStream outputStream) {
    final OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
    @NonNls final XStream xStream = configureXStream();
    final String xml = xStream.toXML(customTheme);

    try (final PrintWriter printWriter = new PrintWriter(writer)) {
      printWriter.write(xml);
    }
  }

  @NotNull
  private static XStream configureXStream() {
    @NonNls final XStream xStream = new XStream();
    xStream.allowTypesByWildcard(new String[]{"com.mallowigi.idea.themes.models.*"});
    xStream.processAnnotations(MTBundledTheme.class);
    xStream.processAnnotations(MTDarkBundledTheme.class);
    xStream.processAnnotations(MTLightBundledTheme.class);
    xStream.processAnnotations(MTThemeColor.class);

    // Use a converter to create a MTDarkBundledTheme or MTLightBundledTheme according to the "dark" property
    xStream.registerConverter(new MTThemesConverter(
      xStream.getConverterLookup().lookupConverterForType(MTBundledTheme.class),
      xStream.getReflectionProvider()
    ));

    xStream.addDefaultImplementation(MTDarkBundledTheme.class, MTBundledTheme.class);
    xStream.addDefaultImplementation(MTLightBundledTheme.class, MTBundledTheme.class);
    return xStream;
  }

  private class BundledThemeEPExtensionPointListener implements ExtensionPointListener<BundledThemeEP> {
    BundledThemeEPExtensionPointListener() {
    }

    @Override
    public final void extensionAdded(@NotNull final BundledThemeEP theme, @NotNull final PluginDescriptor pluginDescriptor) {
      try {
        installBundledTheme(theme);
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public final void extensionRemoved(@NotNull final BundledThemeEP theme, @NotNull final PluginDescriptor pluginDescriptor) {
      try {
        removeBundledTheme(theme);
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }
}
