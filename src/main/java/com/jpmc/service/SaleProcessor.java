package com.jpmc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jpmc.model.Product;

/**
 * 
 * @author Smriti Pathak
 * @since 18-05-2022 ClassName:SaleProcesser This class processes each message and generates the report
 *
 */
public class SaleProcessor {

	// Product object
	Product product;
	
	// This property will hold the product type and its sales info as key value
	// pairs
	HashMap<String, Product> saleInfo = new HashMap<>();
	
	// MeassageProcesser Object
	MessageProcessor msgProcessor;
	
	// AdjustPrice object
	AdjustPrice adPrice;
	
	// This property will hold the adjusted sales log as an array list
	ArrayList<String> adjustLog = new ArrayList<>();
	
	// This property will hold total generated sales records as an array list
	ArrayList<String> saleLog = new ArrayList<>();
	
	// Total products price value
	double totalProductSaleValue;

	// Read each message then call MessageProcessor class and parse the
	// message.Finally
	// update the product object and related report info
	public void processSale(String message) {

		double adjustPrice;
		msgProcessor = new MessageProcessor();

		// Call to Message Processor
		msgProcessor.processMessage(message);
		
		product = new Product();

		// Create or update the Product object
		product = saleInfo.getOrDefault(msgProcessor.getProductType(), new Product(msgProcessor.getProductType()));
		product.setOperatorType(msgProcessor.getOperationType()); // empty string for message type 1 and 2
		product.setProductQuantity(msgProcessor.getProductQuantiy());
		product.setProductUnitPrice(msgProcessor.getProductPrice());
		product.setTotalProdQuantity(msgProcessor.getProductQuantiy());
		product.setProductSaleValue(msgProcessor.getProductQuantiy(), msgProcessor.getProductPrice());

		// Adjust Price If required in case of Message type 3
		if (product.getOperatorType() != null && !product.getOperatorType().equals("")) {
			adPrice = new AdjustPrice(product);
			adjustPrice = adPrice.getAdjustedPrice(product.getOperatorType(),product.getTotalProductSaleValue(),product.getTotalProdQuantity(),product.getProductUnitPrice());
			adjustLog.add(adPrice.adjustmentReport());
			product.setTotalProductSaleValue(adjustPrice);

		} else {
			product.caluculateTotalPrice(product.getProductSaleValue());
		}

		// Record Sale Info
		saleInfo.put(product.getProductType(), product);

		// Record the sale log
		saleLog.add(message);

	}

	// Generate the reports
	public void processSalesLog() {
		int repFactor = 10;
		int adjustRepFact = 50;

		// Report after 10th iteration
		if ((saleLog.size() % repFactor) == 0 && saleLog.size() != 0) {
			System.out.println("10 sales appended to log");
			System.out.println("*************** Log Report *****************");
			System.out.println("|Product           |Quantity   |Value      |");
			for (Map.Entry<String, Product> type : saleInfo.entrySet()) {
				formatReports( type.getValue());
			}
			System.out.print("Waiting for next 10 messages");
			try {
				// Add 3 second pause
				System.out.print(".");
				Thread.sleep(1000);
				System.out.print(".");
				Thread.sleep(1000);
				System.out.print(".\n\n");
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Report and stop execution after 50th message
		if ((saleLog.size() % adjustRepFact) == 0 && saleLog.size() != 0) {
			System.out.println(
					"Application reached 50 messages and pausing the process. The following are the adjustments made to previous sales;\n");

			// Display all the adjustment reports so far recorded.
			getAdjustLog().forEach(System.out::println);
			System.exit(1);
		}

	}

	// Report format structure generation
	public void formatReports( Product product) {
		String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(),
				product.getTotalProdQuantity(), product.getTotalProductSaleValue());
		System.out.println(lineItem);
	}


	public HashMap<String, Product> getSaleInfo() {
		return saleInfo;
	}

	public void setSaleInfo(HashMap<String, Product> saleInfo) {
		this.saleInfo = saleInfo;
	}

	public MessageProcessor getMsgProc() {
		return msgProcessor;
	}

	public void setMsgProc(MessageProcessor msgProcessor) {
		this.msgProcessor = msgProcessor;
	}

	public AdjustPrice getAdPrice() {
		return adPrice;
	}

	public void setAdPrice(AdjustPrice adPrice) {
		this.adPrice = adPrice;
	}

	public ArrayList<String> getAdjustLog() {
		return adjustLog;
	}

	public void setAdjustLog(ArrayList<String> adjustLog) {
		this.adjustLog = adjustLog;
	}

	public ArrayList<String> getRecordSaleLog() {
		return saleLog;
	}

	public void setRecordSaleLog(ArrayList<String> saleLog) {
		this.saleLog = saleLog;
	}

}
