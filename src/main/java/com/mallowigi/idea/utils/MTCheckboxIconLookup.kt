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

package com.mallowigi.idea.utils

import com.intellij.icons.AllIcons
import com.intellij.openapi.util.IconLoader
import com.intellij.util.ObjectUtils
import org.jetbrains.annotations.NonNls
import javax.swing.Icon

/**
 * Service for retrieving the different checkbox icons
 *
 */
@Suppress("LongParameterList")
object MTCheckboxIconLookup {
  private const val ICONS_DIR_PREFIX = "/icons/mt/"

  /**
   * Get the icon for the given checkbox state
   *
   *
   * @param name icon name
   * @param selected if selected
   * @param focused if focused
   * @param enabled if enabled
   * @return the icon
   */
  @JvmStatic
  fun getIcon(@NonNls name: String, selected: Boolean, focused: Boolean, enabled: Boolean): Icon =
    getIcon(name, selected, focused, enabled, false)

  /**
   * Get the icon for the given checkbox state
   *
   *
   * @param name icon name
   * @param selected if selected
   * @param focused if focused
   * @param enabled if enabled
   * @param editable if editable
   * @return the icon
   */
  @JvmStatic
  fun getIcon(@NonNls name: String, selected: Boolean, focused: Boolean, enabled: Boolean, editable: Boolean): Icon =
    getIcon(name, selected, focused, enabled, editable, false)

  /**
   * Get the icon for the given checkbox state
   *
   *
   * @param name icon name
   * @param selected if selected
   * @param focused if focused
   * @param enabled if enabled
   * @param editable if editable
   * @param pressed if pressed
   * @return the icon
   */
  @JvmStatic
  fun getIcon(
    @NonNls name: String,
    selected: Boolean,
    focused: Boolean,
    enabled: Boolean,
    editable: Boolean,
    pressed: Boolean,
  ): Icon = findIcon(name, selected, focused, enabled, editable, pressed)

  /**
   * Find icon
   *
   * @param name icon name
   * @param selected if selected
   * @param focused if focused
   * @param enabled if enabled
   * @param editable if editable
   * @param pressed if pressed
   * @return the icon
   */
  private fun findIcon(
    @NonNls name: String,
    selected: Boolean,
    focused: Boolean,
    enabled: Boolean,
    editable: Boolean,
    pressed: Boolean,
  ): Icon {
    @NonNls var key = "checkboxes/$name"
    if (editable) key += "Editable"
    if (selected) key += "Selected"

    when {
      pressed  -> key += "Pressed"
      focused  -> key += "Focused"
      !enabled -> key += "Disabled"
    }

    @NonNls val path = "${ICONS_DIR_PREFIX}$key.svg"
    return ObjectUtils.notNull(
      IconLoader.findIcon(path, MTCheckboxIconLookup::class.java, true, true),
      AllIcons.Actions.Stub
    )
  }
}
