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

import com.intellij.openapi.application.ApplicationBundle
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.fileChooser.FileChooserFactory
import com.intellij.openapi.fileChooser.FileSaverDescriptor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.ThrowableRunnable
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.themes.models.MTBundledTheme
import com.mallowigi.idea.themes.models.MTDarkBundledTheme
import com.mallowigi.idea.themes.models.MTLightBundledTheme
import com.mallowigi.idea.themes.models.MTThemeColor
import com.thoughtworks.xstream.XStream
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.URL
import java.nio.charset.StandardCharsets

/**
 * Service for bundled themes such as custom themes and old external themes
 */
object MTBundledThemesManager {
  /**
   * Instance
   */
  val instance: MTBundledThemesManager
    get() = ApplicationManager.getApplication().getService(MTBundledThemesManager::class.java)

  /**
   * Load bundled theme from xml
   *
   * @param file xml file
   * @return the bundled theme
   */
  fun loadBundledTheme(file: VirtualFile): MTBundledTheme? {
    val url = File(file.path)
    return loadFromXml(null, url)
  }

  /**
   * Load from xml
   *
   * @param url if defined, load from that url
   * @param file if defined, load from that file
   * @return the parsed bundled theme, or null if failed
   */
  private fun loadFromXml(url: URL?, file: File?): MTBundledTheme? {
    val xStream = configureXStream()
    return try {
      when {
        url != null  -> return xStream.fromXML(url) as MTBundledTheme
        file != null -> return xStream.fromXML(file) as MTBundledTheme
        else         -> null
      }
    } catch (e: RuntimeException) {
      null
    }
  }

  /**
   * Save custom theme to xml
   *
   * @param customTheme the custom theme
   */
  fun saveTheme(customTheme: MTBundledTheme) {
    val saveFileDialog = FileChooserFactory.getInstance().createSaveFileDialog(
      FileSaverDescriptor(message("SaveThemeDialog.placeholder"), message("SaveThemeDialog.title"), "xml"),
      null
    )

    val target = saveFileDialog.save("${customTheme.themeId}.xml")
    if (target != null) {
      val targetFile = target.getVirtualFile(true)
      val message: String = if (targetFile != null) {
        generateMessage(customTheme, targetFile)
      } else {
        ApplicationBundle.message("scheme.exporter.ui.cannot.write.message")
      }

      Messages.showDialog(
        message,
        message("common.status"),
        arrayOf(message("common.ok")),
        0,
        null
      )
    }
  }

  /**
   * Save to disk and generate the message to display upon saving
   *
   * @param customTheme the theme to save
   * @param targetFile the xml file
   * @return the message
   */
  @Suppress("UnstableApiUsage")
  @NlsContexts.DialogMessage
  private fun generateMessage(customTheme: MTBundledTheme, targetFile: VirtualFile): String {
    val message: String = try {
      // Save to disk
      WriteAction.run(object : ThrowableRunnable<Throwable?> {
        @Throws(IOException::class)
        override fun run() {
          val outputStream = targetFile.getOutputStream(this)
          exportTheme(customTheme, outputStream)
        }
      })
      // Return the "successfully saved" message
      ApplicationBundle.message(
        "scheme.exporter.ui.scheme.exported.message",
        customTheme.themeName,
        "(${customTheme.themeId})",
        targetFile.presentableUrl
      )
    } catch (e: Throwable) {
      ApplicationBundle.message("scheme.exporter.ui.export.failed", e.message)
    }
    return message
  }

  /**
   * Write the xml file to disk at the given location
   *
   * @param customTheme the theme
   * @param outputStream output stream
   */
  private fun exportTheme(customTheme: MTBundledTheme, outputStream: OutputStream) {
    val writer = OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
    val xStream = configureXStream()
    val xml = xStream.toXML(customTheme)
    PrintWriter(writer).use { printWriter -> printWriter.write(xml) }
  }

  /**
   * Configure the xstream theme parser
   *
   * @return the configured xstream
   */
  @Suppress("HardCodedStringLiteral")
  private fun configureXStream(): XStream {
    val xStream = XStream()
    xStream.allowTypesByWildcard(arrayOf("com.mallowigi.idea.themes.models.*"))
    xStream.processAnnotations(MTBundledTheme::class.java)
    xStream.processAnnotations(MTDarkBundledTheme::class.java)
    xStream.processAnnotations(MTLightBundledTheme::class.java)
    xStream.processAnnotations(MTThemeColor::class.java)

    // Use a converter to create a MTDarkBundledTheme or MTLightBundledTheme according to the "dark" property
    xStream.registerConverter(
      MTThemesConverter(
        xStream.converterLookup.lookupConverterForType(MTBundledTheme::class.java),
        xStream.reflectionProvider
      )
    )
    xStream.addDefaultImplementation(MTDarkBundledTheme::class.java, MTBundledTheme::class.java)
    xStream.addDefaultImplementation(MTLightBundledTheme::class.java, MTBundledTheme::class.java)
    return xStream
  }
}
