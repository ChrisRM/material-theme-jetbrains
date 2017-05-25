package com.chrisrm.idea.messages;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

public class MaterialThemeBundle {

    @NonNls
    public static final String PATH_TO_BUNDLE = "messages.MaterialThemeBundle";

    @NotNull
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(PATH_TO_BUNDLE);

    /**
     * Prevent instantiation
     */
    private MaterialThemeBundle() {

    }

    /**
     * Get a message from the resource bundle
     *
     * @param key
     * @param params
     * @return the message
     */
    public static String message(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, @NotNull Object... params) {
        return CommonBundle.message(BUNDLE, key, params);
    }

    /**
     * Get a message from the resource bundle or return a default message
     *
     * @param key
     * @param defaultValue
     * @param params
     * @return the message or default
     */
    public static String messageOrDefault(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, String defaultValue,
                                          Object... params) {
        return CommonBundle.messageOrDefault(BUNDLE, key, defaultValue, params);
    }


}
