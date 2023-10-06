package com.datamasters.vista;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class OnlineStoreTests {

	private OnlineStore onlineStore;

	@Before
	public void setUp() {
		 onlineStore = new OnlineStore();
	}
	@Test
	public void testInstanceOf() {
        assertEquals(true, onlineStore instanceof OnlineStore);
	}
}
