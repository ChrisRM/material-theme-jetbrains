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
package com.mallowigi.idea.config.application

import com.intellij.openapi.options.SearchableConfigurable
import com.mallowigi.idea.config.MTBaseConfig
import com.mallowigi.idea.config.MTConfigurableBase
import com.mallowigi.idea.config.ui.MTForm
import com.mallowigi.idea.messages.MaterialThemeBundle
import com.mallowigi.idea.utils.MTUiUtils

/**
 * Service used to load and save settings from MTConfig
 */
class MTConfigurable : MTConfigurableBase<MTForm?, MTConfig?>(), SearchableConfigurable {
  override fun getDisplayName(): String = MaterialThemeBundle.message("mt.settings.titles.materialTheme")

  override fun getHelpTopic(): String = "${MTUiUtils.HELP_PREFIX}.$HELP_ID"

  override fun getId(): String = ID

  override fun setFormState(form: MTForm?, config: MTConfig): Unit = form!!.setFormState(config)

  override fun getConfig(): MTConfig = MTConfig.getInstance()

  override fun createForm(): MTForm = MTForm()

  override fun doApply(form: MTForm?, config: MTConfig?): Unit = config!!.applySettings(form)

  override fun checkModified(form: MTForm?, config: MTConfig?): Boolean = checkFormModified(config)

  private fun checkFormModified(config: MTBaseConfig<MTForm, MTConfig>?): Boolean = form!!.isModified(config)

  companion object {
    const val ID: String = "MTConfigurable"

    const val HELP_ID: String = "MTConfig"
  }
}
