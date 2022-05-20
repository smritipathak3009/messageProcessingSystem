package com.jpmc.main;

import java.io.BufferedReader;
import java.io.FileReader;

import com.jpmc.service.SaleProcessor;
/**
 * 
 * @author Smriti Pathak
 * @since 18-05-2022
 * Class Name:MainMessageProcessor
 * This is the main class to run the application.
 * Requires a test input file containing sales message details (already present in src/test/resources)
 *
 */
public class MainMessageProcessor {

	public static void main(String[] args) {

		SaleProcessor saleProcessor = new SaleProcessor();

		try {
			String salesMsg;
			//Assuming input file is available within application
			@SuppressWarnings("resource")
			BufferedReader inputFile = new BufferedReader(new FileReader("src/test/resources/salesMessages.txt"));

			
			while (null != (salesMsg = inputFile.readLine())) {
				

				//This method will process the message to store Product details in 
				//HashMap with Key as ProductType and Value as Product Object 
				saleProcessor.processSale(salesMsg);
				
				
				//This method generates logs after each 10 messages and stops after 50 messages
				saleProcessor.processSalesLog();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
