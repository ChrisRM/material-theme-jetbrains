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

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.extensions.PluginAware
import com.intellij.openapi.extensions.PluginDescriptor
import com.intellij.openapi.extensions.RequiredElement
import com.intellij.util.KeyedLazyInstance
import com.intellij.util.xmlb.annotations.Attribute

class BundledThemeEP : PluginAware, KeyedLazyInstance<BundledThemeEP> {

  private var pluginDescriptor: PluginDescriptor? = null

  val loaderForClass: ClassLoader
    get() = if (pluginDescriptor == null) javaClass.classLoader else pluginDescriptor!!.pluginClassLoader

  @JvmField
  @Attribute("path")
  @RequiredElement
  var path: String = ""

  @JvmField
  @Attribute("name")
  @RequiredElement
  var name: String = ""

  @JvmField
  @Attribute("icon")
  var icon: String? = null

  fun getPluginDescriptor(): PluginDescriptor? = this.pluginDescriptor

  override fun setPluginDescriptor(pluginDescriptor: PluginDescriptor) {
    this.pluginDescriptor = pluginDescriptor
  }

  companion object {
    @JvmField
    val EP_NAME: ExtensionPointName<BundledThemeEP> =
      ExtensionPointName<BundledThemeEP>("com.chrisrm.idea.MaterialThemeUI.bundledTheme")
  }

  override fun getKey(): String = name

  override fun getInstance(): BundledThemeEP = this
}
