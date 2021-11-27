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

import com.intellij.util.ui.JBUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

final class OverlapLayout implements LayoutManager2, java.io.Serializable {
  public static final float PAD = 0.5F;

  private static final int PREFERRED = 0;
  private static final int MINIMUM = 1;

  //  Indicates how a component is painted
  private final boolean overlapAbove;

  private final Point overlapPosition;

  //  Reserve extra space so a component can "popup"
  private final Insets popupInsets = JBUI.emptyInsets();

  //  Track original order in which the components where added
  private final List<Component> components = new ArrayList<>(10);

  //  Track a constraint added to a component
  private final HashMap<Component, Boolean> constraints = new HashMap<>(10);

  /**
   * Convenience constructor to provide for "stacking" of components. Each
   * component will be stacked above the previous component and sized to
   * fill the space of the parent container.
   */
  OverlapLayout() {
    this(new Point(0, 0));
  }

  /**
   * Convenience constructor. Each component will overlap above the previous
   * component.
   *
   * @param overlapPosition a Point defining the relative amount of overlap
   */
  private OverlapLayout(final Point overlapPosition) {
    this(overlapPosition, true);
  }

  /**
   * Create an overlapping layout.
   *
   * @param overlapPosition a Point defining the relative amount of overlap
   * @param overlapAbove    when true components are painted above the previous
   *                        component, otherwise they are painted below.
   */
  private OverlapLayout(final Point overlapPosition, final boolean overlapAbove) {
    this.overlapPosition = overlapPosition;
    this.overlapAbove = overlapAbove;
  }

  /**
   * Adds the specified component with the specified name to the layout.
   *
   * @param name the name of the component
   * @param comp the component to be added
   */
  @Override
  public void addLayoutComponent(final String name, final Component comp) {
    // nothing
  }

  /*
   *	Keep track of any specified constraint for the component.
   */
  @SuppressWarnings("BreakStatement")
  @Override
  public void addLayoutComponent(final Component comp, final Object constraints) {
    //  Support simple Boolean constraint for painting a Component in
    //  its "popped up" position
    if (constraints == null) {
      this.constraints.remove(comp);
    } else if (constraints instanceof Boolean) {
      this.constraints.put(comp, (Boolean) constraints);
    } else {
      final String message = "Constraint parameter must be of type Boolean";
      throw new IllegalArgumentException(message);
    }
    //  Keep a separate List of components in the order in which they where
    //  added to the Container. This makes layout easier. First we need
    //  to find the position the component was added at. We can't depend
    //  on the component order in the parent Container as changing the
    //  Z-Order also changes the order in the Container
    if (!components.contains(comp)) {
      final Container parent = comp.getParent();
      final int size = parent.getComponentCount();

      for (int i = 0; i < size; i++) {
        final Component component = parent.getComponent(i);

        if (component == comp) {
          components.add(i, comp);

          //  Need to change Z-Order so added components are painted
          //  above the previously added component.
          if (overlapAbove) {
            parent.setComponentZOrder(comp, size - i - 1);
          }

          break;
        }
      }
    }
  }

  /**
   * Removes the specified component from the layout.
   *
   * @param comp the component to be removed
   */
  @Override
  public void removeLayoutComponent(final Component comp) {
    components.remove(comp);
    constraints.remove(comp);
  }

  /**
   * Determine the minimum size on the Container
   *
   * @param parent the container in which to do the layout
   * @return the minimum dimensions needed to lay out the
   * subcomponents of the specified container
   */
  @Override
  public Dimension minimumLayoutSize(final Container parent) {
    synchronized (parent.getTreeLock()) {
      return getLayoutSize(parent, MINIMUM);
    }
  }

  /**
   * Determine the preferred size on the Container
   *
   * @param parent the container in which to do the layout
   * @return the preferred dimensions to lay out the
   * subcomponents of the specified container
   */
  @Override
  public Dimension preferredLayoutSize(final Container parent) {
    synchronized (parent.getTreeLock()) {
      return getLayoutSize(parent, PREFERRED);
    }
  }

  /*
   *  The calculation for minimum/preferred size it the same. The only
   *  difference is the need to use the minimum or preferred size of the
   *  component in the calculation.
   *
   *  @param	 parent  the container in which to do the layout
   *  @param	 type    either MINIMUM or PREFERRED
   */
  private Dimension getLayoutSize(final Container parent, final int type) {
    int visibleComponents = 0;
    int width = 0;
    int height = 0;

    //  All components will be resized to the maximum dimension
    for (final Component component : components) {
      component.isVisible();
      final Dimension size = getDimension(component, type);
      width = Math.max(size.width, width);
      height = Math.max(size.height, height);
      visibleComponents++;
    }

    if (visibleComponents == 0) {
      return new Dimension(0, 0);
    }

    //  Adjust size for each overlapping component
    visibleComponents--;
    width += visibleComponents * Math.abs(overlapPosition.x);
    height += visibleComponents * Math.abs(overlapPosition.y);

    //  Adjust for parent Container and popup insets
    final Insets parentInsets = parent.getInsets();
    width += parentInsets.left + parentInsets.right;
    height += parentInsets.top + parentInsets.bottom;

    width += popupInsets.left + popupInsets.right;
    height += popupInsets.top + popupInsets.bottom;

    return new Dimension(width, height);
  }

  private static Dimension getDimension(final Component component, final int type) {
    switch (type) {
      case PREFERRED:
        return component.getPreferredSize();
      case MINIMUM:
        return component.getMinimumSize();
      default:
        return new Dimension(0, 0);
    }
  }

  /**
   * Lays out the specified container using this layout.
   * <p>
   *
   * @param parent the container in which to do the layout
   */
  @SuppressWarnings("OverlyLongMethod")
  @Override
  public void layoutContainer(final Container parent) {
    synchronized (parent.getTreeLock()) {
      final int size = components.size();
      if (size == 0) {
        return;
      }

      //  Determine the maximum size of the components
      final Dimension maximumSize = new Dimension();

      for (final Component component : components) {
        component.isVisible();
        final Dimension preferred = component.getPreferredSize();
        maximumSize.width = Math.max(preferred.width, maximumSize.width);
        maximumSize.height = Math.max(preferred.height, maximumSize.height);
      }

      //  Determine location of first component
      final Point location = new Point(0, 0);
      final Insets parentInsets = parent.getInsets();

      //  Layout right-to-left, else left-to-right
      if (overlapPosition.x < 0) {
        location.x = parent.getWidth() - maximumSize.width - parentInsets.right - popupInsets.right;
      } else {
        location.x = parentInsets.left + popupInsets.left;
      }

      //  Layout bottom-to-top, else top-to-bottom
      if (overlapPosition.y < 0) {
        location.y = parent.getHeight() - maximumSize.height - parentInsets.bottom - popupInsets.bottom;
      } else {
        location.y = parentInsets.top + popupInsets.top;
      }

      //  Set the size and location for each component
      for (final Component component : components) {
        component.isVisible();
        //  When components are "stacked" resize each component to fill
        //  the size of the parent container

        if (overlapPosition.x == 0 && overlapPosition.y == 0) {
          final int width = parent.getWidth() - parentInsets.left - parentInsets.right;
          final int height = parent.getHeight() - parentInsets.top - parentInsets.bottom;
          component.setSize(width, height);
        } else {
          //  resize each component to be the same size
          component.setSize(maximumSize);
        }

        //  Set location of the component
        int x = location.x;
        int y = location.y;

        //  Adjust location when component is "popped up"
        final Boolean constraint = constraints.get(component);

        if (constraint == Boolean.TRUE) {
          x += popupInsets.right - popupInsets.left;
          y += popupInsets.bottom - popupInsets.top;
        }

        component.setLocation(x, y);

        //  Calculate location of next component using the overlap offsets
        location.x += overlapPosition.x;
        location.y += overlapPosition.y;
      }
    }
  }

  /**
   * There is no maximum.
   */
  @Override
  public Dimension maximumLayoutSize(final Container target) {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }

  /**
   * Returns the alignment along the x axis.  Use center alignment.
   */
  @Override
  public float getLayoutAlignmentX(final Container target) {
    return PAD;
  }

  /**
   * Returns the alignment along the y axis.  Use center alignment.
   */
  @Override
  public float getLayoutAlignmentY(final Container target) {
    return PAD;
  }

  /**
   * Invalidates the layout, indicating that if the layout manager
   * has cached information it should be discarded.
   */
  @Override
  public void invalidateLayout(final Container target) {
    // remove constraints here?
  }
}
