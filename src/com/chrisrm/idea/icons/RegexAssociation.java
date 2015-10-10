package com.chrisrm.idea.icons;

import java.util.regex.Pattern;

public class RegexAssociation extends Association {

    private String pattern;
    private transient Pattern compiledPattern;

    @Override
    public boolean matches(FileInfo file) {
        if (compiledPattern == null) {
            compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        }
        return compiledPattern.matcher(file.getName()).matches();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
