package com.chrisrm.idea.utils;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
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
                      StaticPatcher.setFieldValue(value, "myCallerClass", IconReplacer.class);
                      StaticPatcher.setFieldValue(value, "myWasComputed", Boolean.FALSE);
                      StaticPatcher.setFieldValue(value, "myIcon", null);
                    } else if (byClass.getName().endsWith("$CachedImageIcon")) {
                        String newPath = patchUrlIfNeeded(value, iconsRootPath);
                        if (newPath != null) {
                            Icon newIcon = IconLoader.getIcon(newPath);
                          StaticPatcher.setFinalStatic(field, newIcon);
                        }
                    }
                } catch (IllegalAccessException e) {
                    // Suppress
                }
                catch (Exception e) {
                    // suppress
                }
            }
        }

        // Recurse into nested classes
        for (Class subClass : iconsClass.getDeclaredClasses()) {
            replaceIcons(subClass, iconsRootPath);
        }

//        try {
//            Icon collapsed = IconLoader.getIcon("/icons/nodes/homeFolder.png");
//            Icon expanded = IconLoader.getIcon("/icons/nodes/folder.png");
//
//            UIManager.put("Tree.collapsedIcon", collapsed);
//            setFinalStatic(AllIcons.Mac.class, "Tree_right_down_arrow", collapsed);
//
//            UIManager.put("Tree.expandedIcon", expanded);
//            setFinalStatic(AllIcons.Mac.class, "Tree_white_down_arrow", expanded);
//        } catch (Exception e) {
//            ;
//        }
    }

    private static String patchUrlIfNeeded(Object icon, String iconsRootPath) {
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
                    return path;
                }
                return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return iconsRootPath;
    }

}
