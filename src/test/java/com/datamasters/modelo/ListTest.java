package com.datamasters.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {

    private List<Integer> list;

    @Before
    public void setUp() {
        list = new List<>();
    }

    @Test
    public void testAddAndGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.getSize());
        assertEquals(Integer.valueOf(1), list.getAt(0));
        assertEquals(Integer.valueOf(2), list.getAt(1));
        assertEquals(Integer.valueOf(3), list.getAt(2));
    }

    @Test
    public void testDelete() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.delete(2);

        assertEquals(2, list.getSize());
        assertEquals(Integer.valueOf(1), list.getAt(0));
        assertEquals(Integer.valueOf(3), list.getAt(1));
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.clear();

        assertEquals(0, list.getSize());
        assertTrue(list.isEmpty());
    }
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
    }
    @Test
    public void testGetSize() {
        assertEquals(0, list.getSize());
    }
    @Test
    public void testGetAt() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(Integer.valueOf(1), list.getAt(0));
        assertEquals(Integer.valueOf(2), list.getAt(1));
        assertEquals(Integer.valueOf(3), list.getAt(2));
    }
    @Test
public void testSetAt() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.setAt(1, 4);

        assertEquals(Integer.valueOf(1), list.getAt(0));
        assertEquals(Integer.valueOf(4), list.getAt(1));
        assertEquals(Integer.valueOf(3), list.getAt(2));
    }
    @Test
    public void testGetArrayList()
    {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.getSize());
        assertEquals(Integer.valueOf(1), list.getArrayList().getAt(0));
        assertEquals(Integer.valueOf(2), list.getArrayList().getAt(1));
        assertEquals(Integer.valueOf(3), list.getArrayList().getAt(2));
    }

}
