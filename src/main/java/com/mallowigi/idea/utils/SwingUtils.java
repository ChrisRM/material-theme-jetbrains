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

package com.mallowigi.idea.utils;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressWarnings({"unused",
  "ThrowsRuntimeException",
  "DuplicateStringLiteralInspection",
  "ThrowInsideCatchBlockWhichIgnoresCaughtException"})
public enum SwingUtils {
  ;

  public static final char DOT = '.';

  /**
   * Exclude methods that return values that are meaningless to the user
   */
  @SuppressWarnings("StaticCollection")
  static final Set<String> EXCLUDED_METHODS = new HashSet<>(10);
  private static final Pattern GETTER_REGEX = Pattern.compile("^(is|get).*");
  private static final Pattern JAVAX_SWING = Pattern.compile("javax.swing.J[^.]*$");

  static {
    EXCLUDED_METHODS.add("getFocusCycleRootAncestor");
    EXCLUDED_METHODS.add("getAccessibleContext");
    EXCLUDED_METHODS.add("getColorModel");
    EXCLUDED_METHODS.add("getGraphics");
    EXCLUDED_METHODS.add("getGraphicsConfiguration");
  }

  /**
   * Convenience method for searching below {@code container} in the
   * component hierarchy and return nested components that are instances of
   * class {@code clazz} it finds. Returns an empty list if no such
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
  public static <T extends JComponent> List<T> getDescendantsOfType(final Class<? extends T> clazz, final Container container) {
    return getDescendantsOfType(clazz, container, true);
  }

  /**
   * Convenience method for searching below {@code container} in the
   * component hierarchy and return nested components that are instances of
   * class {@code clazz} it finds. Returns an empty list if no such
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
  public static <T extends JComponent> List<T> getDescendantsOfType(final Class<? extends T> clazz,
                                                                    final Container container,
                                                                    final boolean nested) {
    final List<T> tList = new ArrayList<>(10);

    for (final Component component : container.getComponents()) {
      if (clazz.isAssignableFrom(component.getClass())) {
        tList.add(clazz.cast(component));
      }

      if (nested || !clazz.isAssignableFrom(component.getClass())) {
        tList.addAll(getDescendantsOfType(clazz, (Container) component, nested));
      }
    }
    return tList;
  }

  /**
   * Convenience method that searches below {@code container} in the
   * component hierarchy and returns the first found component that is an
   * instance of class {@code clazz} having the bound property value.
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
   * @throws IllegalArgumentException if the bound property does
   *                                  not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfType(final Class<T> clazz,
                                                             final Container container,
                                                             final String property,
                                                             final Object value) throws IllegalArgumentException {
    return getDescendantOfType(clazz, container, property, value, true);
  }

  /**
   * Convenience method that searches below {@code container} in the
   * component hierarchy and returns the first found component that is an
   * instance of class {@code clazz} and has the bound property value.
   * Returns {@code null} if such component cannot be found.
   *
   * @param clazz     the class of component whose instance to be found.
   * @param container the container at which to begin the search
   * @param property  the className of the bound property, exactly as expressed in
   *                  the accessor e.g. "Text" for getText(), "Value" for getValue().
   * @param value     the value of the bound property
   * @param nested    true to list components nested within another component
   *                  which is also an instance of {@code clazz}, false otherwise
   * @return the component, or null if no such component exists in the
   * container
   * @throws IllegalArgumentException if the bound property does
   *                                  not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfType(final Class<T> clazz,
                                                             final Container container,
                                                             final String property,
                                                             final Object value,
                                                             final boolean nested) throws IllegalArgumentException {
    final List<T> list = getDescendantsOfType(clazz, container, nested);
    return getComponentFromList(clazz, list, property, value);
  }

  /**
   * Convenience method for searching below {@code container} in the
   * component hierarchy and return nested components of class
   * {@code clazz} it finds.  Returns an empty list if no such
   * components exist in the container.
   * <p>
   * This method invokes getDescendantsOfClass(clazz, container, true)
   *
   * @param clazz     the class of components to be found.
   * @param container the container at which to begin the search
   * @return the List of components
   */
  public static <T extends JComponent> List<T> getDescendantsOfClass(final Class<? extends T> clazz, final Container container) {
    return getDescendantsOfClass(clazz, container, true);
  }

  /**
   * Convenience method for searching below {@code container} in the
   * component hierarchy and return nested components of class
   * {@code clazz} it finds.  Returns an empty list if no such
   * components exist in the container.
   *
   * @param clazz     the class of components to be found.
   * @param container the container at which to begin the search
   * @param nested    true to list components nested within another listed
   *                  component, false otherwise
   * @return the List of components
   */
  public static <T extends JComponent> List<T> getDescendantsOfClass(final Class<? extends T> clazz,
                                                                     final Container container,
                                                                     final boolean nested) {
    final List<T> compList = new ArrayList<>(10);
    for (final Component component : container.getComponents()) {
      if (clazz.equals(component.getClass())) {
        compList.add(clazz.cast(component));
      }

      if (nested || !clazz.equals(component.getClass())) {
        compList.addAll(getDescendantsOfClass(clazz, (Container) component, nested));
      }
    }
    return compList;
  }

  /**
   * Convenience method that searches below {@code container} in the
   * component hierarchy in a depth first manner and returns the first
   * found component of class {@code clazz} having the bound property
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
   * @throws IllegalArgumentException if the bound property does
   *                                  not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfClass(final Class<T> clazz,
                                                              final Container container,
                                                              final String property,
                                                              final Object value) throws IllegalArgumentException {
    return getDescendantOfClass(clazz, container, property, value, true);
  }

  /**
   * Convenience method that searches below {@code container} in the
   * component hierarchy in a depth first manner and returns the first
   * found component of class {@code clazz} having the bound property
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
   * @throws IllegalArgumentException if the bound property does
   *                                  not exist for the class or cannot be accessed
   */
  public static <T extends JComponent> T getDescendantOfClass(final Class<T> clazz,
                                                              final Container container,
                                                              final String property,
                                                              final Object value,
                                                              final boolean nested) throws IllegalArgumentException {
    final List<T> list = getDescendantsOfClass(clazz, container, nested);
    return getComponentFromList(clazz, list, property, value);
  }

  @SuppressWarnings("ThrowInsideCatchBlockWhichIgnoresCaughtException")
  private static <T extends JComponent> @Nullable T getComponentFromList(final Class<T> clazz,
                                                                         final Iterable<? extends T> list,
                                                                         final String property,
                                                                         final Object value) throws IllegalArgumentException {
    Method method;

    // First find a getter of the property
    try {
      method = clazz.getMethod("get" + property);
    } catch (final NoSuchMethodException ex) {
      // Then search for a predicate
      try {
        method = clazz.getMethod("is" + property);
      } catch (final NoSuchMethodException ex1) {
        throw new IllegalArgumentException("Property " + property + " not found in class " + clazz.getName());
      }
    }

    try {
      // Then for each component of the list, invoke the method and checks if it matches the value
      for (final T component : list) {
        final Object testVal = method.invoke(component);
        if (equals(value, testVal)) {
          return component;
        }
      }
    } catch (final InvocationTargetException ex) {
      throw new IllegalArgumentException("Error accessing property " + property + " in class " + clazz.getName());
    } catch (final IllegalAccessException | SecurityException ex) {
      throw new IllegalArgumentException("Property " + property + " cannot be accessed in class " + clazz.getName());
    }
    return null;
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
    return Objects.equals(obj1, obj2);
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
  @SuppressWarnings("ObjectAllocationInLoop")
  public static Map<JComponent, List<JComponent>> getComponentMap(final JComponent container, final boolean nested) {
    final Map<JComponent, List<JComponent>> componentMap = new HashMap<>(10);

    for (final JComponent component : getDescendantsOfType(JComponent.class, container, false)) {
      componentMap.computeIfAbsent(container, list -> new ArrayList<>(10));
      componentMap.get(container).add(component);

      if (nested) {
        componentMap.putAll(getComponentMap(component, true));
      }
    }
    return componentMap;
  }

  /**
   * Convenience method for retrieving a subset of the UIDefaults pertaining
   * to a particular class.
   *
   * @param clazz the class of interest
   * @return the UIDefaults of the class
   */
  public static UIDefaults getUIDefaultsOfClass(final Class<?> clazz) {
    String name = clazz.getName();
    name = name.substring(name.lastIndexOf(DOT) + 2);
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
    final UIDefaults classUiDefaults = new UIDefaults();
    final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    final List<?> listKeys = Collections.list(defaults.keys());

    for (final Object key : listKeys) {
      if (key instanceof String && ((String) key).startsWith(className)) {
        final String stringKey = (String) key;
        String property = stringKey;
        if (stringKey.contains(".")) {
          property = stringKey.substring(stringKey.indexOf(DOT) + 1);
        }
        classUiDefaults.put(property, defaults.get(key));
      }
    }
    return classUiDefaults;
  }

  /**
   * Convenience method for retrieving the UIDefault for a single property
   * of a particular class.
   *
   * @param clazz    the class of interest
   * @param property the property to query
   * @return the UIDefault property, or null if not found
   */
  public static Object getUIDefaultOfClass(final Class<?> clazz, final @NonNls String property) {
    Object uiDefault = null;
    final UIDefaults defaults = getUIDefaultsOfClass(clazz);
    final List<Object> listKeys = Collections.list(defaults.keys());

    for (final Object key : listKeys) {
      if (key.equals(property)) {
        return defaults.get(key);
      }
      if (key.toString().equalsIgnoreCase(property)) {
        uiDefault = defaults.get(key);
      }
    }
    return uiDefault;
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
  @SuppressWarnings({"SingleCharacterStartsWith",
    "MethodWithMoreThanThreeNegations",
    "ObjectAllocationInLoop"})
  public static Map<Object, Object> getProperties(final JComponent component) {
    final Map<Object, Object> propMap = new HashMap<>(10);
    final Class<?> clazz = component.getClass();
    final Method[] methods = clazz.getMethods();
    Object value;

    for (final Method method : methods) {
      if (GETTER_REGEX.matcher(method.getName()).matches() && method.getParameterTypes().length == 0) {
        try {
          final Class<?> returnType = method.getReturnType();

          if (returnType != void.class && !returnType.getName().startsWith("[") && !EXCLUDED_METHODS.contains(method.getName())) {
            final String key = method.getName();
            value = method.invoke(component);

            if (value != null && !(value instanceof Component)) {
              propMap.put(key, value);
            }
          }
          // ignore exceptions that arise if the property could not be accessed
        } catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
          // do nothing
        }
      }
    }
    return propMap;
  }

  /**
   * Convenience method to obtain the Swing class from which this
   * component was directly or indirectly derived.
   *
   * @param component The component whose Swing superclass is to be
   *                  determined
   * @return The nearest Swing class in the inheritance tree
   */
  @SuppressWarnings("MethodCallInLoopCondition")
  public static <T extends JComponent> Class<?> getJClass(final T component) {
    Class<?> clazz = component.getClass();
    while (!JAVAX_SWING.matcher(clazz.getName()).matches()) {
      clazz = clazz.getSuperclass();
    }
    return clazz;
  }
}
