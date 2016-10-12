package com.chrisrm.idea.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

public class IconReplacer {
    private IconReplacer() {
        // prevent outside instantiation
    }

    public static void replaceIcons(Class iconsClass, String iconsRootPath) {
        // Iterate all fields (which hold icon locations) and patch them if necessary
        for (Field field : iconsClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    // Object should be some kind of javax.swing.Icon
                    Object value = field.get(null);
                    Class byClass = value.getClass();

                    if (byClass.getName().endsWith("$ByClass")) {
                        setFieldValue(value, "myCallerClass", IconReplacer.class);
                        setFieldValue(value, "myWasComputed", Boolean.FALSE);
                        setFieldValue(value, "myIcon", null);
                    } else if (byClass.getName().endsWith("$CachedImageIcon")) {
                        patchUrlIfNeeded(value, iconsRootPath);
                    }
                } catch (IllegalAccessException e) {
                    // Suppress
                }
            }
        }

        // Recurse into nested classes
        for (Class subClass : iconsClass.getDeclaredClasses()) {
            replaceIcons(subClass, iconsRootPath);
        }
    }

    private static void patchUrlIfNeeded(Object icon, String iconsRootPath) {
        try {
            Field urlField = icon.getClass().getDeclaredField("myUrl");
            Field iconField = icon.getClass().getDeclaredField("myRealIcon");
            urlField.setAccessible(true);
            iconField.setAccessible(true);

            Object url = urlField.get(icon);
            if (url instanceof URL) {
                String path = ((URL) url).getPath();
                if (path != null && path.contains("!")) {
                    path = path.substring(path.lastIndexOf(33) + 1);

                    if (iconsRootPath.contains("plugins")) {
                        path = path.replace("/icons/", "");
                        path = path.substring(path.lastIndexOf('/') + 1);
                    }

                    path = iconsRootPath + path;
                }

                URL newUrl = IconReplacer.class.getResource(path);
                if (newUrl != null && path != null) {
                    iconField.set(icon, null);
                    urlField.set(icon, newUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void setFieldValue(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
