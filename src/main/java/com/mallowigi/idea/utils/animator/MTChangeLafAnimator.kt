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

import com.intellij.openapi.util.Disposer
import com.intellij.util.ui.Animator
import com.intellij.util.ui.GraphicsUtil
import com.intellij.util.ui.ImageUtil
import com.intellij.util.ui.StartupUiUtil
import java.awt.AlphaComposite
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.Window
import java.awt.image.BufferedImage
import javax.swing.JComponent
import javax.swing.JLayeredPane
import javax.swing.RootPaneContainer

class MTChangeLAFAnimator {
  // The current alpha, changed by the fade in animator
  private var myAlpha = 1f

  // List of windows to animate
  private val windowLayers: MutableMap<JLayeredPane, JComponent> = LinkedHashMap(10)

  // The animator
  internal val fadeInAnimator: Animator = FadeInAnimator()

  /**
   * Paint our images on each opened window
   */
  private fun doPaint() {
    for ((window, image) in windowLayers) {
      if (window.isShowing) {
        image.revalidate()
        image.repaint()
      }
    }
  }

  init {
    // When this constructor is invoked (e.g. during showSnapshot), we gather all open windows
    // and create a snapshot of the window before the switch. We then attach this snapshot to the windows'
    // root layer and progressively reduce it's opacity to simulate a "fading in"
    val windows = Window.getWindows()
    for (window in windows) {
      if (window is RootPaneContainer && window.isShowing) {
        val bounds = window.bounds
        val rootPaneContainer = window as RootPaneContainer
        val layeredPane = rootPaneContainer.layeredPane

        // Create the snapshot
        val image =
          ImageUtil.createImage(window.graphicsConfiguration, bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB)
        val imageGraphics = image.graphics
        GraphicsUtil.setupAntialiasing(imageGraphics)

        // Paint the image on top of the root pane
        (window as RootPaneContainer).rootPane.paint(imageGraphics)

        // Then we create the image layers and store them in our window layers map
        val imageLayer: JComponent = object : JComponent() {
          override fun updateUI() = Unit

          // When printing the image, we add the composite with the current alpha
          override fun paint(g: Graphics) {
            (g as Graphics2D).composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, myAlpha)
            StartupUiUtil.drawImage(g, image, 0, 0, this)
          }

          override fun getBounds(): Rectangle = layeredPane.bounds
        }
        imageLayer.size = layeredPane.size
        layeredPane.add(imageLayer, JLayeredPane.DRAG_LAYER)
        windowLayers[layeredPane] = imageLayer
      }
    }

    doPaint()
  }

  /**
   * This animator will modify the alpha between each frame, thus creating a fade in effect
   *
   */
  inner class FadeInAnimator : Animator("MTChangeLAF", 60, 1200, false) {
    /**
     * when the animation starts/resume
     *
     */
    override fun resume() {
      doPaint()
      super.resume()
    }

    /**
     * Before repainting, modify the alpha according to the current frame within the total frames
     *
     * @param frame the current frame
     * @param totalFrames total frames
     * @param cycle should the animation cycle
     */
    override fun paintNow(frame: Int, totalFrames: Int, cycle: Int) {
      myAlpha = 1 - (1 - StrictMath.cos(Math.PI * frame / totalFrames.toFloat())).toFloat() / 2
      doPaint()
    }

    /**
     * When the animation finishes, dispose it
     *
     */
    override fun paintCycleEnd() {
      if (!isDisposed) Disposer.dispose(this)
    }

    /**
     * when disposing, we remove all layers to remove the screenshot from the root pane
     *
     */
    override fun dispose(): Unit = try {
      super.dispose()
      for ((layeredPane, image) in windowLayers) {
        layeredPane.remove(image)
        layeredPane.revalidate()
        layeredPane.repaint()
      }
    } finally {
      windowLayers.clear()
    }
  }
}
