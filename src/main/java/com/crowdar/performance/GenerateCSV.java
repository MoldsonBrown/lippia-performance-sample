package com.crowdar.performance;

import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateCSV {

	/**
	 * Class used for debugging methods
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		GenerateData generator = new GenerateData();
		try {
			//generator.generateBusinessData("src/test/jmeter/Register_business_fleet.csv", 2);
			//generator.generateFleet("src/test/jmeter/Register_business_fleet.csv", 6);
			
			//generator.generateAccountsData("src/test/jmeter/Register_postpaid_account_2-vehicle_0-tags.csv", 10);
			//RandomAccessFile raf = new RandomAccessFile("src/main/start.txt", "rw");
			//raf.writeLong(10000000l);
			//generator.generateAnonymousTransactions("src/test/jmeter/a.csv", 100);

			//Faker faker = new Faker();
			//System.out.println(faker.finance().creditCard(CreditCardType.AMERICAN_EXPRESS));
			//System.out.println(faker.finance().creditCard(CreditCardType.MASTERCARD));

			//GenerateData.generateTransactionFile("src/test/jmeter/registeredPlates.csv",10);
			//GenerateData.generateTagStatusFile("src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateCustomerPlateFile("src/test/jmeter/registeredPlates.csv",10);
			//GenerateData.generateReconciliationFile("src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateTagStatusCtocFiles("incoming","src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateLicensePlateStatusCtocFiles("incoming","src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateTollChargesCtocFiles("incoming","src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generatePayByPlateCtocFiles("incoming","src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateReconciliedTollChargesCtocFiles("incoming","src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateReconciliedPayByPlateCtocFiles("incoming","src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateReconciliedPayByPlateCtocFiles("outgoing","src/test/jmeter/registeredPlates.csv", 10);
			//GenerateData.generateTransactionsBlockSTI("src/test/jmeter/Send_transactions_STI.csv", 20);
			//GenerateData.generateTransactionsBlock("src/test/jmeter/Send_transactions_IOP.csv", 100);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
