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

import com.intellij.util.ui.TimerUtil
import org.jetbrains.annotations.NonNls
import java.awt.Color
import java.awt.Component
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.Deque
import javax.swing.Timer

/**
 * Button background timer used for animating button highlights
 *
 * @property fps
 */
class ButtonBackgroundTimer(private val fps: Int) {
  private val baseline = 1000

  /**
   * Start animation
   *
   * @param name component name
   * @param component component
   * @param colors list of colors
   */
  fun start(@NonNls name: String, component: Component, colors: Deque<out Color>) {
    val timer = TimerUtil.createNamedTimer(name, baseline / fps)
    timer.addActionListener(getActionListener(timer, component, colors))
    timer.start()
  }

  private fun getActionListener(timer: Timer, component: Component, colors: Deque<out Color>): ActionListener =
    object : ActionListener {
      override fun actionPerformed(it: ActionEvent) {
        val color = colors.poll()
        if (color == null) {
          timer.stop()
          return
        }

        try {
          component.background = color
          component.repaint()
        } catch (exception: Exception) {
          // do nothing
        }
      }
    }
}
