package com.jpmc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.jpmc.service.AdjustPrice;


public class AdjustPriceTest {

	@Test
	public void testGetAdjustedPrice() {
		AdjustPrice addPrice = new AdjustPrice();
		// Test With ADD adjustment input message
		assertEquals(6.3000000938773155, addPrice.getAdjustedPrice("Add", 2.1000000312924385, 21, 0.20000000298023224));
		// Test With ADD adjustment input message
		assertEquals(6.720000106394291,
				addPrice.getAdjustedPrice("Multiply", 2.1000000312924385, 21, 0.20000000298023224));
		// Test With Subtract adjustment input message
		assertEquals(-2.1000000312924385,
				addPrice.getAdjustedPrice("Subtract", 2.1000000312924385, 21, 0.20000000298023224));
	}

}
