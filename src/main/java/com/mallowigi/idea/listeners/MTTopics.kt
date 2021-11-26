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
package com.mallowigi.idea.listeners

import com.intellij.util.messages.Topic

/**
 * Configuration Save Events
 */
enum class MTTopics {
  DEFAULT;

  companion object {
    @Topic.AppLevel
    @Topic.ProjectLevel
    @JvmField
    val CONFIG: Topic<ConfigNotifier> = Topic.create("Material Theme Config save", ConfigNotifier::class.java)

    @Topic.AppLevel
    @Topic.ProjectLevel
    @JvmField
    val PROJECT_CONFIG: Topic<ProjectConfigNotifier> =
      Topic.create("Material Theme Project Config save", ProjectConfigNotifier::class.java)

    @Topic.AppLevel
    @JvmField
    val CUSTOM_THEME: Topic<CustomConfigNotifier> =
      Topic.create("Material Theme Custom Theme Config save", CustomConfigNotifier::class.java)

    @Topic.AppLevel
    @JvmField
    val ACCENTS: Topic<AccentsListener> = Topic.create("Accents Changes", AccentsListener::class.java)

    @Topic.AppLevel
    @JvmField
    val THEMES: Topic<ThemeListener> = Topic.create("Theme Changes", ThemeListener::class.java)
  }
}
