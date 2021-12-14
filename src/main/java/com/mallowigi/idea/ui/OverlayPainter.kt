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
package com.mallowigi.idea.ui

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.ui.paint.LinePainter2D
import com.intellij.ui.paint.RectanglePainter
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.ui.JBUI
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.utils.MTUiUtils
import java.awt.AWTEvent
import java.awt.AlphaComposite
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Insets
import java.awt.Point
import java.awt.Rectangle
import java.awt.Toolkit
import java.awt.Window
import java.awt.event.AWTEventListener
import java.awt.event.WindowEvent
import java.awt.event.WindowEvent.WINDOW_CLOSED
import java.awt.event.WindowEvent.WINDOW_CLOSING
import java.util.ArrayDeque
import java.util.Deque
import javax.swing.JComponent
import javax.swing.JRootPane
import javax.swing.SwingUtilities
import kotlin.math.max

/**
 * Overlay painter
 *
 */
class OverlayPainter : AWTEventListener, Disposable {
  /**
   * The registered root panes
   */
  private val rootPanes: MutableCollection<Component> = ArrayList(2)

  /**
   * The currently overlayed root panes
   */
  private val overlaidRootPanes: MutableCollection<OverlayComponent> = ArrayList(2)

  /**
   * Stack of opened windows
   */
  private val openedWindowsStack: Deque<Any> = ArrayDeque(1)

  init {
    Toolkit.getDefaultToolkit().addAWTEventListener(this, MASK)
  }

  /**
   * Dispose and remove overlays
   *
   */
  override fun dispose() {
    Toolkit.getDefaultToolkit().removeAWTEventListener(this)
    removeOverlays()
  }

  /**
   * Show/Hide the overlay when a window is shown or hidden
   *
   * @param event the event
   */
  @Suppress("CastToNullableType")
  override fun eventDispatched(event: AWTEvent) {
    if (!MTConfig.getInstance().isShowOverlays) {
      if (openedWindowsStack.isEmpty()) return
      openedWindowsStack.clear()
      removeOverlays()
      return
    }

    val source = event.source
    if (source !is Window) return

    // Remove highlights when all windows are closed
    when {
      event.id in listOf(WINDOW_CLOSED, WINDOW_CLOSING) && openedWindowsStack.contains(source) -> {
        openedWindowsStack.remove(source)
        if (openedWindowsStack.isEmpty()) removeOverlays()
      }
      event.id == WindowEvent.WINDOW_OPENED && MTUiUtils.isDialogWindow(source as Window?)     -> {
        openedWindowsStack.push(source)
        updateOverlays()
      }
    }
  }

  private fun updateOverlays() {
    removeOverlays()
    rootPanes.forEach { rootPane -> ContainerUtil.addIfNotNull(overlaidRootPanes, createOverlay(rootPane)) }
  }

  /**
   * Remove all overlays
   */
  private fun removeOverlays() {
    overlaidRootPanes.forEach { overlay ->
      val glassPane = getGlassPane(overlay)
      if (glassPane != null) {
        with(glassPane) {
          remove(overlay)
          revalidate()
          repaint()
        }
      }
    }
    overlaidRootPanes.clear()
  }

  /**
   * Add a root pane to the list of rootpanes
   *
   * @param rootPane the rootpane
   */
  fun addRootPane(rootPane: JRootPane) {
    rootPanes.add(rootPane)
  }

  /**
   * Clean overlays
   *
   */
  fun cleanOverlays(): Unit = removeOverlays()

  /**
   * The Overlay itself
   */
  private class OverlayComponent(private val myInsets: Insets) : JComponent() {
    @Suppress("MagicNumber")
    override fun paintComponent(g: Graphics) {
      val g2d = g as Graphics2D
      val oldColor = g2d.color
      val oldComposite = g2d.composite
      g2d.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f)

      val r = bounds
      val myColor = Color.BLACK
      RectanglePainter.paint(g2d, 0, 0, r.width, r.height, 0, myColor, null)
      g.paint = myColor.darker()

      for (i in 0 until myInsets.left) LinePainter2D.paint(
        g2d,
        i.toDouble(),
        myInsets.top.toDouble(),
        i.toDouble(),
        (r.height - myInsets.bottom - 1).toDouble()
      )
      for (i in 0 until myInsets.right) LinePainter2D.paint(
        g2d,
        (r.width - i - 1).toDouble(),
        myInsets.top.toDouble(),
        (r.width - i - 1).toDouble(),
        (r.height - myInsets.bottom - 1).toDouble()
      )
      for (i in 0 until myInsets.top) LinePainter2D.paint(
        g2d,
        0.0,
        i.toDouble(),
        r.width.toDouble(),
        i.toDouble()
      )
      for (i in 0 until myInsets.bottom) LinePainter2D.paint(
        g2d,
        0.0,
        (r.height - i - 1).toDouble(),
        r.width.toDouble(),
        (r.height - i - 1).toDouble()
      )

      g2d.composite = oldComposite
      g2d.color = oldColor
    }
  }

  companion object {
    /**
     * Listen to window events
     */
    private const val MASK = AWTEvent.WINDOW_EVENT_MASK or AWTEvent.WINDOW_STATE_EVENT_MASK

    /**
     * Service for managing overlays
     */
    @JvmStatic
    val instance: OverlayPainter
      get() = ApplicationManager.getApplication().getService(OverlayPainter::class.java)

    /**
     * Get the IdeGlassPane of a component
     *
     * @param component the component
     * @return the IdeGlassPane
     */
    private fun getGlassPane(component: Component): JComponent? {
      val rootPane = SwingUtilities.getRootPane(component)
      return if (rootPane == null) null else rootPane.glassPane as JComponent
    }

    private fun createOverlay(rootPane: Component): OverlayComponent? {
      val bounds: Rectangle
      val glassPane = getGlassPane(rootPane) ?: return null

      // Get bounds of rootpane
      val pt = SwingUtilities.convertPoint(rootPane, Point(0, 0), glassPane)
      bounds = Rectangle(pt.x, pt.y, rootPane.width, rootPane.height)
      if (bounds.width == 0 || bounds.height == 0) {
        bounds.width = max(bounds.width, 1)
        bounds.height = max(bounds.height, 1)
      }

      val insets = if (rootPane is Container) rootPane.insets else JBUI.emptyInsets()
      val overlayComponent = OverlayComponent(insets)
      overlayComponent.bounds = bounds
      glassPane.add(overlayComponent)
      glassPane.revalidate()
      glassPane.repaint()
      return overlayComponent
    }
  }
}
