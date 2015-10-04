package com.chrisrm.idea.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

public class IconReplacer {

    public void replaceIcons(Class iconsClass, String iconPath) {
        Field[] fields = iconsClass.getDeclaredFields();

        int len$ = fields.length;

        int i$;
        for (i$ = 0; i$ < len$; ++i$) {
            Field subClass = fields[i$];

            if (Modifier.isStatic(subClass.getModifiers())) {
                try {
                    Object ignored = subClass.get(null);
                    Class byClass = ignored.getClass();

                    if (byClass.getName().endsWith("$ByClass")) {
                        this.setFieldValue(ignored, "myCallerClass", IconReplacer.class);
                        this.setFieldValue(ignored, "myWasComputed", Boolean.FALSE);
                        this.setFieldValue(ignored, "myIcon", null);
                    } else if (byClass.getName().endsWith("$CachedImageIcon")) {
                        this.patchUrlIfNeeded(ignored, iconPath);
                    }

                } catch (IllegalAccessException var9) {
                    return;
                }
            }
        }

        Class[] var10 = iconsClass.getDeclaredClasses();
        len$ = var10.length;

        for (i$ = 0; i$ < len$; ++i$) {
            Class var11 = var10[i$];
            this.replaceIcons(var11, iconPath);
        }
    }

    private void patchUrlIfNeeded(Object object, String iconPath) {
        try {
            Field e = object.getClass().getDeclaredField("myUrl");
            Field iconField = object.getClass().getDeclaredField("myRealIcon");
            e.setAccessible(true);
            iconField.setAccessible(true);
            Object url = e.get(object);
            if (url instanceof URL) {
                String path = ((URL) url).getPath();
                if (path != null && path.contains("!")) {
                    path = path.substring(path.lastIndexOf(33) + 1);

                    if (iconPath.contains("plugins")) {
                        path = path.replace("/icons/", "");
                        path = path.substring(path.lastIndexOf('/') + 1);
                    }

                    path = iconPath + path;
                }

                URL newUrl = IconReplacer.class.getResource(path);
                if (newUrl != null && path != null) {
                    iconField.setAccessible(true);
                    iconField.set(object, null);
                    e.set(object, newUrl);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    private void setFieldValue(Object object, String fieldName, Object value) {
        try {
            Field ignore = object.getClass().getDeclaredField(fieldName);
            ignore.setAccessible(true);
            ignore.set(object, value);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

}
