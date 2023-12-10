package com.datamasters.vista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OnlineStoreTests {

	private OnlineStore onlineStore;

	@BeforeEach
	public void setUp() {
		 onlineStore = new OnlineStore();
	}
	@Test
	public void testInstanceOf() {
        assertEquals(true, onlineStore instanceof OnlineStore);
	}
}
