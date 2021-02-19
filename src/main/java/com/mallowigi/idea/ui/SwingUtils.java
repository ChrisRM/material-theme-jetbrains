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

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.*;

public final class SwingUtils {

  private SwingUtils() {
    throw new Error("SwingUtils is just a container for static methods");
  }

  /**
   * Convenience method for searching below <code>container</code> in the
   * component hierarchy and return nested components that are instances of
   * class <code>clazz</code> it finds. Returns an empty list if no such
   * components exist in the container.
   * <p>
   * Invoking this method with a class parameter of JComponent.class
   * will return all nested components.
   * <p>
   * This method invokes getDescendantsOfType(clazz, container, true)
   *
   * @param clazz     the class of components whose instances are to be found.
   * @param container the container at which to begin the search
   * @return the List of components
   */
  public static <T extends JComponent> List<T> getDescendantsOfType(
    final Class<T> clazz, final Container container) {
    return getDescendantsOfType(clazz, container, true);
  }

  /**
   * Convenience method for searching below <code>container</code> in the
   * component hierarchy and return nested components that are instances of
   * class <code>clazz</code> it finds. Returns an empty list if no such
   * components exist in the container.
   * <p>
   * Invoking this method with a class parameter of JComponent.class
   * will return all nested components.
   *
   * @param clazz     the class of components whose instances are to be found.
   * @param container the container at which to begin the search
   * @param nested    true to list components nested within another listed
   *                  component, false otherwise
   * @return the List of components
   */
  public static <T extends JComponent> List<T> getDescendantsOfType(
    final Class<T> clazz, final Container container, final boolean nested) {
    final List<T> tList = new ArrayList<>();
    for (final Component component : container.getComponents()) {
      if (clazz.isAssignableFrom(component.getClass())) {
        tList.add(clazz.cast(component));
      }
      if (nested || !clazz.isAssignableFrom(component.getClass())) {
        tList.addAll(SwingUtils.<T>getDescendantsOfType(clazz,
          (Container) component, nested));
      }
    }
    return tList;
  }

  /**
   * Convenience method that searches below <code>container</code> in the
   * component hierarchy and returns the first found component that is an
   * instance of class <code>clazz</code> having the bound property value.
   * Returns {@code null} if such component cannot be found.
   * <p>
   * This method invokes getDescendantOfType(clazz, container, property, value,
   * true)
   *
   * @param clazz     the class of component whose instance is to be found.
   * @param container the container at which to begin the search
   * @param property  the className of the bound property, exactly as expressed in
   *                  the accessor e.g. "Text" for getText(), "Value" for getValue().
   * @param value     the value of the bound property
   * @return the component, or null if no such component exists in the
   * container
   * @throws java.lang.IllegalArgumentException if the bound property does
   *                                            not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfType(
    final Class<T> clazz, final Container container, final String property, final Object value)
    throws IllegalArgumentException {
    return getDescendantOfType(clazz, container, property, value, true);
  }

  /**
   * Convenience method that searches below <code>container</code> in the
   * component hierarchy and returns the first found component that is an
   * instance of class <code>clazz</code> and has the bound property value.
   * Returns {@code null} if such component cannot be found.
   *
   * @param clazz     the class of component whose instance to be found.
   * @param container the container at which to begin the search
   * @param property  the className of the bound property, exactly as expressed in
   *                  the accessor e.g. "Text" for getText(), "Value" for getValue().
   * @param value     the value of the bound property
   * @param nested    true to list components nested within another component
   *                  which is also an instance of <code>clazz</code>, false otherwise
   * @return the component, or null if no such component exists in the
   * container
   * @throws java.lang.IllegalArgumentException if the bound property does
   *                                            not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfType(final Class<T> clazz,
                                                             final Container container,
                                                             final String property,
                                                             final Object value,
                                                             final boolean nested)
    throws IllegalArgumentException {
    final List<T> list = getDescendantsOfType(clazz, container, nested);
    return getComponentFromList(clazz, list, property, value);
  }

  /**
   * Convenience method for searching below <code>container</code> in the
   * component hierarchy and return nested components of class
   * <code>clazz</code> it finds.  Returns an empty list if no such
   * components exist in the container.
   * <p>
   * This method invokes getDescendantsOfClass(clazz, container, true)
   *
   * @param clazz     the class of components to be found.
   * @param container the container at which to begin the search
   * @return the List of components
   */
  public static <T extends JComponent> List<T> getDescendantsOfClass(
    final Class<T> clazz, final Container container) {
    return getDescendantsOfClass(clazz, container, true);
  }

  /**
   * Convenience method for searching below <code>container</code> in the
   * component hierarchy and return nested components of class
   * <code>clazz</code> it finds.  Returns an empty list if no such
   * components exist in the container.
   *
   * @param clazz     the class of components to be found.
   * @param container the container at which to begin the search
   * @param nested    true to list components nested within another listed
   *                  component, false otherwise
   * @return the List of components
   */
  public static <T extends JComponent> List<T> getDescendantsOfClass(
    final Class<T> clazz, final Container container, final boolean nested) {
    final List<T> tList = new ArrayList<>();
    for (final Component component : container.getComponents()) {
      if (clazz.equals(component.getClass())) {
        tList.add(clazz.cast(component));
      }
      if (nested || !clazz.equals(component.getClass())) {
        tList.addAll(SwingUtils.<T>getDescendantsOfClass(clazz,
          (Container) component, nested));
      }
    }
    return tList;
  }

  /**
   * Convenience method that searches below <code>container</code> in the
   * component hierarchy in a depth first manner and returns the first
   * found component of class <code>clazz</code> having the bound property
   * value.
   * <p>
   * Returns {@code null} if such component cannot be found.
   * <p>
   * This method invokes getDescendantOfClass(clazz, container, property,
   * value, true)
   *
   * @param clazz     the class of component to be found.
   * @param container the container at which to begin the search
   * @param property  the className of the bound property, exactly as expressed in
   *                  the accessor e.g. "Text" for getText(), "Value" for getValue().
   *                  This parameter is case sensitive.
   * @param value     the value of the bound property
   * @return the component, or null if no such component exists in the
   * container's hierarchy.
   * @throws java.lang.IllegalArgumentException if the bound property does
   *                                            not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfClass(final Class<T> clazz,
                                                              final Container container, final String property, final Object value)
    throws IllegalArgumentException {
    return getDescendantOfClass(clazz, container, property, value, true);
  }

  /**
   * Convenience method that searches below <code>container</code> in the
   * component hierarchy in a depth first manner and returns the first
   * found component of class <code>clazz</code> having the bound property
   * value.
   * <p>
   * Returns {@code null} if such component cannot be found.
   *
   * @param clazz     the class of component to be found.
   * @param container the container at which to begin the search
   * @param property  the className of the bound property, exactly as expressed
   *                  in the accessor e.g. "Text" for getText(), "Value" for getValue().
   *                  This parameter is case sensitive.
   * @param value     the value of the bound property
   * @param nested    true to include components nested within another listed
   *                  component, false otherwise
   * @return the component, or null if no such component exists in the
   * container's hierarchy
   * @throws java.lang.IllegalArgumentException if the bound property does
   *                                            not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfClass(final Class<T> clazz,
                                                              final Container container,
                                                              final String property,
                                                              final Object value,
                                                              final boolean nested)
    throws IllegalArgumentException {
    final List<T> list = getDescendantsOfClass(clazz, container, nested);
    return getComponentFromList(clazz, list, property, value);
  }

  private static <T extends JComponent> T getComponentFromList(final Class<T> clazz,
                                                               final List<T> list, final String property, final Object value)
    throws IllegalArgumentException {
    final T retVal = null;
    Method method = null;
    try {
      method = clazz.getMethod("get" + property);
    } catch (final NoSuchMethodException ex) {
      try {
        method = clazz.getMethod("is" + property);
      } catch (final NoSuchMethodException ex1) {
        throw new IllegalArgumentException("Property " + property +
          " not found in class " + clazz.getName());
      }
    }
    try {
      for (final T t : list) {
        final Object testVal = method.invoke(t);
        if (equals(value, testVal)) {
          return t;
        }
      }
    } catch (final InvocationTargetException ex) {
      throw new IllegalArgumentException(
        "Error accessing property " + property +
          " in class " + clazz.getName());
    } catch (final IllegalAccessException ex) {
      throw new IllegalArgumentException(
        "Property " + property +
          " cannot be accessed in class " + clazz.getName());
    } catch (final SecurityException ex) {
      throw new IllegalArgumentException(
        "Property " + property +
          " cannot be accessed in class " + clazz.getName());
    }
    return retVal;
  }

  /**
   * Convenience method for determining whether two objects are either
   * equal or both null.
   *
   * @param obj1 the first reference object to compare.
   * @param obj2 the second reference object to compare.
   * @return true if obj1 and obj2 are equal or if both are null,
   * false otherwise
   */
  public static boolean equals(final Object obj1, final Object obj2) {
    return obj1 == null ? obj2 == null : obj1.equals(obj2);
  }

  /**
   * Convenience method for mapping a container in the hierarchy to its
   * contained components.  The keys are the containers, and the values
   * are lists of contained components.
   * <p>
   * Implementation note:  The returned value is a HashMap and the values
   * are of type ArrayList.  This is subject to change, so callers should
   * code against the interfaces Map and List.
   *
   * @param container The JComponent to be mapped
   * @param nested    true to drill down to nested containers, false otherwise
   * @return the Map of the UI
   */
  public static Map<JComponent, List<JComponent>> getComponentMap(
    final JComponent container, final boolean nested) {
    final HashMap<JComponent, List<JComponent>> retVal =
      new HashMap<>();
    for (final JComponent component : getDescendantsOfType(JComponent.class,
      container, false)) {
      if (!retVal.containsKey(container)) {
        retVal.put(container,
          new ArrayList<>());
      }
      retVal.get(container).add(component);
      if (nested) {
        retVal.putAll(getComponentMap(component, nested));
      }
    }
    return retVal;
  }

  /**
   * Convenience method for retrieving a subset of the UIDefaults pertaining
   * to a particular class.
   *
   * @param clazz the class of interest
   * @return the UIDefaults of the class
   */
  public static UIDefaults getUIDefaultsOfClass(final Class clazz) {
    String name = clazz.getName();
    name = name.substring(name.lastIndexOf(".") + 2);
    return getUIDefaultsOfClass(name);
  }

  /**
   * Convenience method for retrieving a subset of the UIDefaults pertaining
   * to a particular class.
   *
   * @param className fully qualified name of the class of interest
   * @return the UIDefaults of the class named
   */
  public static UIDefaults getUIDefaultsOfClass(final String className) {
    final UIDefaults retVal = new UIDefaults();
    final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    final List<?> listKeys = Collections.list(defaults.keys());
    for (final Object key : listKeys) {
      if (key instanceof String && ((String) key).startsWith(className)) {
        final String stringKey = (String) key;
        String property = stringKey;
        if (stringKey.contains(".")) {
          property = stringKey.substring(stringKey.indexOf(".") + 1);
        }
        retVal.put(property, defaults.get(key));
      }
    }
    return retVal;
  }

  /**
   * Convenience method for retrieving the UIDefault for a single property
   * of a particular class.
   *
   * @param clazz    the class of interest
   * @param property the property to query
   * @return the UIDefault property, or null if not found
   */
  public static Object getUIDefaultOfClass(final Class clazz, final String property) {
    Object retVal = null;
    final UIDefaults defaults = getUIDefaultsOfClass(clazz);
    final List<Object> listKeys = Collections.list(defaults.keys());
    for (final Object key : listKeys) {
      if (key.equals(property)) {
        return defaults.get(key);
      }
      if (key.toString().equalsIgnoreCase(property)) {
        retVal = defaults.get(key);
      }
    }
    return retVal;
  }

  /**
   * Exclude methods that return values that are meaningless to the user
   */
  static Set<String> setExclude = new HashSet<>();

  static {
    setExclude.add("getFocusCycleRootAncestor");
    setExclude.add("getAccessibleContext");
    setExclude.add("getColorModel");
    setExclude.add("getGraphics");
    setExclude.add("getGraphicsConfiguration");
  }

  /**
   * Convenience method for obtaining most non-null human readable properties
   * of a JComponent.  Array properties are not included.
   * <p>
   * Implementation note:  The returned value is a HashMap.  This is subject
   * to change, so callers should code against the interface Map.
   *
   * @param component the component whose proerties are to be determined
   * @return the class and value of the properties
   */
  public static Map<Object, Object> getProperties(final JComponent component) {
    final Map<Object, Object> retVal = new HashMap<>();
    final Class<?> clazz = component.getClass();
    final Method[] methods = clazz.getMethods();
    Object value = null;
    for (final Method method : methods) {
      if (method.getName().matches("^(is|get).*") &&
        method.getParameterTypes().length == 0) {
        try {
          final Class returnType = method.getReturnType();
          if (returnType != void.class &&
            !returnType.getName().startsWith("[") &&
            !setExclude.contains(method.getName())) {
            final String key = method.getName();
            value = method.invoke(component);
            if (value != null && !(value instanceof Component)) {
              retVal.put(key, value);
            }
          }
          // ignore exceptions that arise if the property could not be accessed
        } catch (final IllegalAccessException ex) {
        } catch (final IllegalArgumentException ex) {
        } catch (final InvocationTargetException ex) {
        }
      }
    }
    return retVal;
  }

  /**
   * Convenience method to obtain the Swing class from which this
   * component was directly or indirectly derived.
   *
   * @param component The component whose Swing superclass is to be
   *                  determined
   * @return The nearest Swing class in the inheritance tree
   */
  public static <T extends JComponent> Class getJClass(final T component) {
    Class<?> clazz = component.getClass();
    while (!clazz.getName().matches("javax.swing.J[^.]*$")) {
      clazz = clazz.getSuperclass();
    }
    return clazz;
  }
}