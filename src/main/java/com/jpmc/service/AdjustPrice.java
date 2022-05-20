package com.jpmc.service;

import com.jpmc.model.Product;

/**
 * 
 * @author Smriti Pathak
 * @since 18-05-2022 ClassName: AdjustPrice This class will calculate price 
 * based on the operation Type of message format 3 (e.g Add 20p apples)
 *
 */
public class AdjustPrice {
	
	// adjustedPrice holds the adjusted price value
	private double adjustedPrice;

	// product holds the Product object.
	private Product product;

	// Constructor takes Product as argument.
	public AdjustPrice(Product product) {
		this.product = product;
		this.adjustedPrice = 0.0;
	}

	public AdjustPrice() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Calculates adjustment price based on operator type
	 * 
	 */
	public double getAdjustedPrice(String type, double totalSaleValue, int totalQuantity, double unitPrice) {

		if (type.equalsIgnoreCase("Add")) {
			
			this.adjustedPrice = totalSaleValue + (totalQuantity * unitPrice);
			
		} else if (type.equalsIgnoreCase("Multiply")) {
			
			this.adjustedPrice = totalSaleValue + (totalQuantity * unitPrice) + (totalSaleValue * unitPrice);
			
		} else if (type.equalsIgnoreCase("Subtract")) {
			
			this.adjustedPrice = totalSaleValue - (totalQuantity * unitPrice);
			
		} else {
			
			System.out.println("Invalid Operator in the message");
		}
		return adjustedPrice;
	}

	// @returns [String] e.g "Performed Add 20p to 21 apples and price adjusted from
	// 2.10p to 6.30p"
	public String adjustmentReport() {
		String adjustmentReport = String.format("Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
				this.product.getOperatorType(), this.product.getProductUnitPrice(), this.product.getTotalProdQuantity(),
				this.product.getProductType(), this.product.getTotalProductSaleValue(), this.adjustedPrice);
		return adjustmentReport;
	}

}
