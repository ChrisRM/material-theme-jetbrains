package com.chrisrm.idea.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class StaticPatcher {
  public static void setFieldValue(Object object, String fieldName, Object value) {
    try {
      Field field = object.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(object, value);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void setFinalStatic(Class cls, String fieldName, Object newValue) throws Exception {
    Field[] fields = cls.getDeclaredFields();

    for (Field field : fields) {
      if (field.getName().equals(fieldName)) {
        setFinalStatic(field, newValue);
        return;
      }
    }
  }

  public static void setFinalStatic(Field field, Object newValue) throws Exception {
    field.setAccessible(true);

    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

    field.set(null, newValue);

    modifiersField.setInt(field, field.getModifiers() | Modifier.FINAL);
    modifiersField.setAccessible(false);

    field.setAccessible(false);
  }
}