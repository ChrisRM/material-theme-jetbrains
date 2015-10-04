package com.chrisrm.idea.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * String related helper methods
 */
public class StringHelper {

    /**
     * Join the given strings with given glue
     *
     * @param   s
     * @param   glue
     * @return  String
     */
    public static String join(String s[], String glue) {
        StringBuilder buffer	= new StringBuilder();
        List list				= Arrays.asList(s);
        Iterator iterator		= list.iterator();

        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            if (iterator.hasNext())	buffer.append(glue);
        }

        return buffer.toString();
    }



    /**
     * @param   s
     * @param   wrap
     * @return  String
     */
    public static String wrap(String s, String wrap) {
        return wrap + s + wrap;
    }



    /**
     * @param   s
     * @param   wrap
     * @return  String
     */
    public static String wrap(String s, char wrap) {
        return wrap(s, Character.toString(wrap));
    }



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