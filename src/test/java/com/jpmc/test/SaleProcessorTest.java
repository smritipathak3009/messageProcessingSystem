package com.jpmc.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.jpmc.model.Product;
import com.jpmc.service.SaleProcessor;

public class SaleProcessorTest {

	@Test
	public void testProcessSale() {
		SaleProcessor saleProc = new SaleProcessor();
		Product product = new Product();
		
		// Test With new Product Type
		String msg = "apple at 10p";
		saleProc.processSale(msg);
		assertEquals(true, saleProc.getSaleInfo().containsKey("apples"));
		product = saleProc.getSaleInfo().get("apples");
		assertEquals("apples", product.getProductType());
		assertEquals(0.10000000149011612, product.getProductUnitPrice());
		assertEquals(1, product.getProductQuantity());

		// Test updating existing Product Type
		msg = "20 sales of apples at 10p each";
		saleProc.processSale(msg);
		assertEquals(true, saleProc.getSaleInfo().containsKey("apples"));
		product = saleProc.getSaleInfo().get("apples");
		assertEquals("apples", product.getProductType());
		assertEquals(0.10000000149011612, product.getProductUnitPrice());
		assertEquals(20, product.getProductQuantity());

		// Test with adjustment message
		msg = "Add 20p apples";
		saleProc.processSale(msg);
		assertEquals(true, saleProc.getSaleInfo().containsKey("apples"));
		product = saleProc.getSaleInfo().get("apples");
		assertEquals("apples", product.getProductType());
		assertEquals("Add", product.getOperatorType());
	}

}
