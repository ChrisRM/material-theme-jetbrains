package com.chrisrm.idea;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MTThemeTest {
    @Test
    public void testValueOfIgnoreCase() {
        assertEquals(MTTheme.DARKER, MTTheme.valueOfIgnoreCase("darker"));
        assertEquals(MTTheme.DARKER, MTTheme.valueOfIgnoreCase("Darker"));
        assertEquals(MTTheme.DARKER, MTTheme.valueOfIgnoreCase("DARKER"));
        assertEquals(MTTheme.DEFAULT, MTTheme.valueOfIgnoreCase("default"));
        assertEquals(MTTheme.DEFAULT, MTTheme.valueOfIgnoreCase("Default"));
        assertEquals(MTTheme.DEFAULT, MTTheme.valueOfIgnoreCase("DEFAULT"));
        assertEquals(MTTheme.LIGHTER, MTTheme.valueOfIgnoreCase("lighter"));
        assertEquals(MTTheme.LIGHTER, MTTheme.valueOfIgnoreCase("Lighter"));
        assertEquals(MTTheme.LIGHTER, MTTheme.valueOfIgnoreCase("LIGHTER"));
        assertNull(MTTheme.valueOfIgnoreCase("Does not exist"));
    }
}
