package com.jpmc.service;

/**
 * 
 * @author Smriti Pathak
 * @since 18-05-2022 Class Name: MessageProcesser This class process the 
 * message based on its type and will do the parsing
 *
 */

public class MessageProcessor {
	// Holds the productQuantity number
	int productQuantiy;
	// Holds the product type name
	String productType;
	// Holds the single product price info
	double productPrice;
	// Holds the adjustment operator name
	String operationType;

	// Constructor
	public MessageProcessor() {
		productQuantiy = 0;
		productType = "";
		productPrice = 0.0;
		operationType = "";

	}

	// Reads each message processes it and identifies its message format
	public boolean processMessage(String msg) {

		String[] wordsAsArray = msg.trim().split(" ");
		System.out.println("Message : "+msg);

		if (wordsAsArray.length == 7 || wordsAsArray[1].equalsIgnoreCase("at")) {
			
			processMessageType1And2(wordsAsArray);
		} else if (wordsAsArray[0].equalsIgnoreCase("Add") 
				|| wordsAsArray[0].equalsIgnoreCase("Multiply")
				|| wordsAsArray[0].equalsIgnoreCase("Subtract")) {
			
			processMessageType3(wordsAsArray);

		} else {
			System.out.println("Message Format is not valid");
			return false;
		}
		return true;
	}

	// Processes message type 1 and 2
	public void processMessageType1And2(String[] wordsAsArray) {

		if (wordsAsArray.length == 3) { // message type 1
			if (wordsAsArray[1].equalsIgnoreCase("at")) {
				productType = parseType(wordsAsArray[0]);
				productPrice = Float.parseFloat(wordsAsArray[2].replaceAll("£|p", ""));
				productQuantiy = 1;
			}
		} else if (wordsAsArray.length == 7) { // message type 2
			productType = parseType(wordsAsArray[3]);
			productPrice = Float.parseFloat(wordsAsArray[5].replaceAll("£|p", ""));
			productQuantiy = (Integer.parseInt(wordsAsArray[0]));

		}

	}

	// Processes message type 3
	public void processMessageType3(String[] wordsAsArray) {
		if (wordsAsArray.length == 3) { // message type 2
			operationType = wordsAsArray[0];
			 // assuming ProductType will always be plural in case of message type 3
			productType = wordsAsArray[2];
			productQuantiy = 0;
			productPrice = Float.parseFloat(wordsAsArray[1].replaceAll("£|p", ""));;
		}
	}

	// Method to convert the price
	public float parsePrice(String rawPrice) {
		float price = Float.parseFloat(rawPrice.replaceAll("£|p", ""));
		if (!rawPrice.contains(".")) {
			price = Float.valueOf(Float.valueOf(price) / Float.valueOf("100"));
		}
		return price;
	}

	// Method to convert the product name from singular to plural
	public String parseType(String rawType) {
		String parsedType = "";
		String typeWithoutLastChar = rawType.substring(0, rawType.length() - 1);
		if (rawType.endsWith("o")) {
			parsedType = String.format("%soes", typeWithoutLastChar);
		} else if (rawType.endsWith("y")) {
			parsedType = String.format("%sies", typeWithoutLastChar);
		} else if (rawType.endsWith("h")) {
			parsedType = String.format("%shes", typeWithoutLastChar);
		} else if (!rawType.endsWith("s")) {
			parsedType = String.format("%ss", rawType);
		} else {
			parsedType = String.format("%s", rawType);
		}
		return parsedType.toLowerCase();
	}

	public int getProductQuantiy() {
		return productQuantiy;
	}

	public void setProductQuantiy(int productQuantiy) {
		this.productQuantiy = productQuantiy;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setAdjustmentOpType(String operationType) {
		this.operationType = operationType;
	}

}
