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

package com.mallowigi.idea.ui;

import com.intellij.ui.ColorUtil;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.SwingUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public final class DisabledPanel extends JPanel {
  private static final DisabledEventQueue queue = new DisabledEventQueue();

  private static final Map<Container, List<JComponent>> containers = new HashMap<>(10);
  private final String text;

  private JComponent glassPane;

  public DisabledPanel(final Container container) {
    this(container, null, "");
  }

  /**
   * Create a DisablePanel for the specified Container. The disabled color
   * will be the following color from the UIManager with an alpha value:
   * UIManager.getColor("inactiveCaptionBorder");
   *
   * @param container a Container to be added to this DisabledPanel
   */
  public DisabledPanel(final Container container, final String text) {
    this(container, null, text);
  }

  /**
   * Create a DisablePanel for the specified Container using the specified
   * disabled color.
   *
   * @param disabledColor the background color of the GlassPane
   * @param container     a Container to be added to this DisabledPanel
   */
  public DisabledPanel(final Container container, final Color disabledColor, final String text) {
    setLayout(new OverlapLayout());
    add(container);
    this.text = text;

    glassPane = new GlassPane();
    glassPane.setLayout(new MigLayout(
      "hidemode 3,align center top",
      // columns
      "[]",
      // rows
      "[]para" +
        "[]" +
        "[]"));
    if (text != null) {
      final JTextPane textLabel = new JTextPane();
      textLabel.setText(text);
      textLabel.setFont(new Font("Roboto", textLabel.getFont().getStyle(), textLabel.getFont().getSize() + 3));
      glassPane.add(textLabel, "cell 2 0");
    }

    add(glassPane);

    if (disabledColor != null) {
      glassPane.setBackground(disabledColor);
    }

    setFocusTraversalPolicy(new DefaultFocusTraversalPolicy());
  }

  /**
   * The background color of the glass pane.
   *
   * @return the background color of the glass pane
   */
  public Color getDisabledColor() {
    return glassPane.getBackground();
  }

  /**
   * Set the background color of the glass pane. This color should
   * contain an alpha value to give the glass pane a transparent effect.
   *
   * @param disabledColor the background color of the glass pane
   */
  public void setDisabledColor(final Color disabledColor) {
    glassPane.setBackground(disabledColor);
  }

  /**
   * The glass pane of this DisablePanel. It can be customized by adding
   * components to it.
   *
   * @return the glass pane
   */
  public JComponent getGlassPane() {
    return glassPane;
  }

  /**
   * Use a custom glass pane. You are responsible for adding the
   * appropriate mouse listeners to intercept mouse events.
   *
   * @param glassPane a JComponent to be used as a glass pane
   */
  public void setGlassPane(final JComponent glassPane) {
    this.glassPane = glassPane;
  }

  /**
   * Change the enabled state of the panel.
   */
  @Override
  public void setEnabled(final boolean enabled) {
    super.setEnabled(enabled);

    if (enabled) {
      glassPane.setVisible(false);
      setFocusCycleRoot(false);
      queue.removePanel(this);
    } else {
      glassPane.setVisible(true);
      setFocusCycleRoot(true);  // remove from focus cycle
      queue.addPanel(this);
    }
  }

  /**
   * Because we use layered panels this should be disabled.
   */
  @Override
  public boolean isOptimizedDrawingEnabled() {
    return false;
  }

  /**
   * Convenience static method to disable all components of a given
   * Container, including nested Containers.
   *
   * @param container the Container containing Components to be disabled
   */
  public static void disable(final Container container) {
    final List<JComponent> components = SwingUtils.getDescendantsOfType(JComponent.class, container, true);
    final List<JComponent> enabledComponents = new ArrayList<>(10);
    containers.put(container, enabledComponents);

    for (final JComponent component : components) {
      if (component.isEnabled()) {
        enabledComponents.add(component);
        component.setEnabled(false);
      }
    }
  }

  /**
   * Convenience static method to enable Components disabled by using
   * the disable() method. Only Components disable by the disable()
   * method will be enabled.
   *
   * @param container a Container that has been previously disabled.
   */
  public static void enable(final Container container) {
    final List<JComponent> enabledComponents = containers.get(container);

    if (enabledComponents != null) {
      for (final JComponent component : enabledComponents) {
        component.setEnabled(true);
      }

      containers.remove(container);
    }
  }

  /**
   * A simple "glass pane" that has two functions:
   * <p>
   * a) to paint over top of the Container added to the DisablePanel
   * b) to intercept mouse events when visible
   */
  static final class GlassPane extends JComponent {
    @SuppressWarnings("InnerClassTooDeeplyNested")
    GlassPane() {
      setOpaque(false);
      setVisible(false);
      final Color base = MTUI.Panel.getExcludedBackground();
      final Color background = ColorUtil.toAlpha(base, 212);
      setBackground(background);

      //  Disable Mouse events for the panel
      addMouseListener(new MouseAdapter() {
      });
      addMouseMotionListener(new MouseMotionAdapter() {
      });
    }

    /*
     *  The component is transparent but we want to paint the background
     *  to give it the disabled look.
     */
    @Override
    protected void paintComponent(final Graphics g) {
      g.setColor(getBackground());
      g.fillRect(0, 0, getSize().width, getSize().height);
    }
  }

  /**
   * A custom EventQueue to intercept Key Bindings that are used by any
   * component on a DisabledPanel. When a DisabledPanel is disabled it is
   * registered with the DisabledEventQueue. This class will check if any
   * components on the DisablePanel use KeyBindings. If not then nothing
   * changes. However, if some do, then the DisableEventQueue installs
   * itself as the current EquentQueue. The dispatchEvent() method is
   * overriden to check each KeyEvent. If the KeyEvent is for a Component
   * on a DisablePanel then the event is ignored, otherwise it is
   * dispatched for normal processing.
   */
  static final class DisabledEventQueue extends EventQueue implements WindowListener {
    private final Map<DisabledPanel, Set<KeyStroke>> panels = new HashMap<>(10);

    /**
     * Check if any component on the DisabledPanel is using Key Bindings.
     * If so, then track the bindings and use a custom EventQueue to
     * intercept the KeyStroke before it is passed to the component.
     */
    void addPanel(final DisabledPanel panel) {
      //  Get all the KeyStrokes used by all the components on the panel
      final Set<KeyStroke> keyStrokes = getKeyStrokes(panel);

      if (keyStrokes.isEmpty()) {
        return;
      }

      panels.put(panel, keyStrokes);

      //  More than one panel can be disabled but we only need to install
      //  the custom EventQueue when the first panel is disabled.
      final EventQueue current = Toolkit.getDefaultToolkit().getSystemEventQueue();

      if (current != this) {
        current.push(queue);
      }

      //  We need to track when a Window is closed so we can remove
      //  the references for all the DisabledPanels on that window.
      final Window window = SwingUtilities.windowForComponent(panel);
      window.removeWindowListener(this);
      window.addWindowListener(this);
    }

    /**
     * Check each component to see if its using Key Bindings
     */
    private static Set<KeyStroke> getKeyStrokes(final DisabledPanel panel) {
      final Set<KeyStroke> keyStrokes = new HashSet<>(10);

      //  Only JComponents use Key Bindings
      final Container container = ((Container) panel.getComponent(1));
      final List<JComponent> components = SwingUtils.getDescendantsOfType(JComponent.class, container);

      //  We only care about the WHEN_IN_FOCUSED_WINDOW bindings
      for (final JComponent component : components) {
        final InputMap im = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        if (im != null && im.allKeys() != null) {
          for (final KeyStroke keyStroke : im.allKeys()) {
            keyStrokes.add(keyStroke);
          }
        }
      }

      return keyStrokes;
    }

    /**
     * The panel is no longer disabled so we no longer need to intercept
     * its KeyStrokes. Restore the default EventQueue when all panels
     * using Key Bindings have been enabled.
     */
    void removePanel(final DisabledPanel panel) {
      if (panels.remove(panel) != null && panels.isEmpty()) {
        pop();
      }
    }

    /**
     * Intercept KeyEvents bound to a component on a DisabledPanel.
     */
    @Override
    protected void dispatchEvent(final AWTEvent event) {
      //  Potentially intercept KeyEvents
      if (event.getID() == KeyEvent.KEY_TYPED || event.getID() == KeyEvent.KEY_PRESSED || event.getID() == KeyEvent.KEY_RELEASED) {
        final KeyEvent keyEvent = (KeyEvent) event;
        final KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(keyEvent);

        //  When using Key Bindings, the source of the KeyEvent will be
        //  the Window. Check each panel belonging to the source Window.

        for (final DisabledPanel panel : panels.keySet()) {
          final Window panelWindow = SwingUtilities.windowForComponent(panel);

          //  A binding was found so just return without dispatching it.
          if (panelWindow == keyEvent.getComponent()
            && searchForKeyBinding(panel, keyStroke)) {
            return;
          }
        }
      }

      //  Dispatch normally

      super.dispatchEvent(event);
    }

    /**
     * Check if the KeyStroke is for a Component on the DisablePanel
     */
    private boolean searchForKeyBinding(final DisabledPanel panel, final KeyStroke keyStroke) {
      final Set<KeyStroke> keyStrokes = panels.get(panel);

      return keyStrokes.contains(keyStroke);
    }

    //  Implement WindowListener interface

    /**
     * When a Window containing a DisablePanel that has been disabled is
     * closed, remove the DisablePanel from the DisabledEventQueue. This
     * may result in the DisabledEventQueue deregistering itself as the
     * current EventQueue.
     */
    @Override
    public void windowClosed(final WindowEvent e) {
      final Collection<DisabledPanel> panelsToRemove = new ArrayList<>(10);
      final Window window = e.getWindow();

      //  Create a List of DisabledPanels to remove
      for (final DisabledPanel panel : panels.keySet()) {
        final Window panelWindow = SwingUtilities.windowForComponent(panel);

        if (panelWindow == window) {
          panelsToRemove.add(panel);
        }
      }

      //  Remove panels here to prevent ConcurrentModificationException
      for (final DisabledPanel panel : panelsToRemove) {
        removePanel(panel);
      }

      window.removeWindowListener(this);
    }

    @Override
    public void windowActivated(final WindowEvent e) {
    }

    @Override
    public void windowClosing(final WindowEvent e) {
    }

    @Override
    public void windowDeactivated(final WindowEvent e) {
    }

    @Override
    public void windowDeiconified(final WindowEvent e) {
    }

    @Override
    public void windowIconified(final WindowEvent e) {
    }

    @Override
    public void windowOpened(final WindowEvent e) {
    }
  }
}
