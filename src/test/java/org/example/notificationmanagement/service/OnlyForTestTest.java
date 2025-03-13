package org.example.notificationmanagement.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnlyForTestTest {
    @Test
    public void testAdd() {
        OnlyForTest onlyForTest = new OnlyForTest();
        int result = onlyForTest.add(2, 3);
        assertEquals(5, result, "2 + 3 повинно дорівнювати 5");
    }

    @Test
    public void testSubtract() {
        OnlyForTest onlyForTest = new OnlyForTest();
        int result = onlyForTest.subtract(10, 4);
        assertEquals(6, result, "10 - 4 повинно дорівнювати 6");
    }
}