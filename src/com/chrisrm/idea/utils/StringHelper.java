package com.chrisrm.idea.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * String related helper methods
 */
public class StringHelper {
    /**
     * Replace last occurrence of given sub string
     *
     * @param s
     * @param search
     * @return
     */
    public static String removeLast(String s, String search) {
        int pos = s.lastIndexOf(search);

        if( pos > -1 ) {
            return s.substring(0, pos) + s.substring(pos + search.length(), s.length());
        }

        return s;
    }

}