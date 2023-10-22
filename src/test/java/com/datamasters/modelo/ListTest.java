package com.datamasters.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {

    private List<String> stringList;

    @Before
    public void setUp() {
        stringList = new List<>();
    }

    @Test
    public void testGetSize() {
        assertEquals(0, stringList.getSize());
        stringList.add("One");
        assertEquals(1, stringList.getSize());
        stringList.add("Two");
        assertEquals(2, stringList.getSize());
    }

    @Test
    public void testAddAndDelete() {
        stringList.add("One");
        stringList.add("Two");
        stringList.add("Three");

        assertEquals(3, stringList.getSize());
        assertTrue(stringList.contains("Two"));

        stringList.delete("Two");
        assertEquals(2, stringList.getSize());
        assertFalse(stringList.contains("Two"));
    }

    @Test
    public void testGetAt() {
        stringList.add("One");
        stringList.add("Two");
        stringList.add("Three");

        assertEquals("One", stringList.getAt(0));
        assertEquals("Two", stringList.getAt(1));
        assertEquals("Three", stringList.getAt(2));

        assertThrows(IndexOutOfBoundsException.class, () -> stringList.getAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> stringList.getAt(3));
    }

    @Test
    public void testSetAt() {
        stringList.add("One");
        stringList.add("Two");

        stringList.setAt(1, "NewValue");
        assertEquals("NewValue", stringList.getAt(1));

        assertThrows(IndexOutOfBoundsException.class, () -> stringList.setAt(-1, "Invalid"));
        assertThrows(IndexOutOfBoundsException.class, () -> stringList.setAt(2, "Invalid"));
    }

    @Test
    public void testClear() {
        stringList.add("One");
        stringList.add("Two");
        stringList.add("Three");

        stringList.clear();
        assertEquals(0, stringList.getSize());
        assertTrue(stringList.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stringList.isEmpty());
        stringList.add("One");
        assertFalse(stringList.isEmpty());
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }
}
