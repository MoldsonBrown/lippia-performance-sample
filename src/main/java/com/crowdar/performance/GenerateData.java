package com.crowdar.performance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.crowdar.core.PropertyManager;
import com.crowdar.performance.model.CsvFileGeneratorConfig;
import com.crowdar.performance.utils.DatabaseUtils;
import com.crowdar.performance.utils.FakeDataGenerator;
import com.crowdar.performance.utils.FileUtils;
import com.crowdar.performance.utils.Queries;

public class GenerateData {
	
	
/*
 *  Example Code  *
 *  

	private static final String QUERY_GET_USER_P2P = "";
	private static final String QUERY_GET_ACCOUNT_P2P = "";
	private static final String QUERY_GET_PATH = "";
		
	public static void generatep2pData(String pathFile, int cantidadGenerar) throws Exception {
		File file = new File(Paths.get(pathFile).toAbsolutePath().toString());
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		
		String query_user = String.format(QUERY_GET_USER_P2P, "jmeter%",Integer.toString(cantidadGenerar + 1) );
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathFile));
		CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.INFORMIX_UNLOAD.withHeader("PIN","F_USER","ACCOUNT","EMAIL","ID","S_USER","F_ID","PATH").withDelimiter(','));

		List<Map<String, Object>> data = DatabaseUtils.executeQueryWithHost(query_user,"users");		  
		Map<String, Object> lastPerson = null;
		for (Map<String, Object> person : data) {
			if(lastPerson == null) {
				String query_account = String.format(QUERY_GET_ACCOUNT_P2P,person.get("id"));
				List<Map<String, Object>> useri_id = DatabaseUtils.executeQueryWithHost(query_account,"accounts");	
				if(useri_id.size() == 0) {
					continue;
				}
				person.put("user_id", useri_id.get(0).get("user_id"));
				
				lastPerson = person;
			}else {		
				
				String query_account = String.format(QUERY_GET_ACCOUNT_P2P,person.get("id"));
				List<Map<String, Object>> account = DatabaseUtils.executeQueryWithHost(query_account,"accounts");	
				
				if(account.size() == 0) {
					continue;
				}
				
				String query_path = String.format(QUERY_GET_PATH,person.get("status"));
				List<Map<String, Object>> status = DatabaseUtils.executeQueryWithHost(query_path,"p2p");
				
				csvPrinter.printRecord(person.get("pin"),person.get("username"),account.get(0).get("account_number"),person.get("email"),lastPerson.get("user_id"),lastPerson.get("username"),person.get("id"),status.get(0).get("status"));

				person.put("account_number", account.get(0).get("account_number"));
				lastPerson = null;
				//lastPerson = person;
			}
		}
		
		csvPrinter.flush();
		csvPrinter.close();
	}
	
	
	public static void generateFromFile(String inPathFile, String pathFile, int cantidadGenerar) throws Exception {
		File file = new File(Paths.get(pathFile).toAbsolutePath().toString());
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathFile));
		CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.INFORMIX_UNLOAD.withHeader("account_number","last_four_digits").withDelimiter(','));

		List<String> rows = FileUtils.getLinesFromResource(inPathFile, false);
		int catRows = 0;
		for (String row : rows) {
			
			String[] splited = row.split(",");
			csvPrinter.printRecord(splited[0],splited[1]);

			catRows++;
			if(catRows == cantidadGenerar) {
				break;
			}
		}
		
		csvPrinter.flush();
		csvPrinter.close();
	}
	
	public static void copyFromFile(String inPathFile, String pathFile, int cantidadGenerar) throws Exception {	
		FileUtils.copyFile(inPathFile,pathFile);
	}
	

	public static void staticData(String pathFile, int cantidadGenerar) throws Exception {
		generateFromFile("staticData.csv", pathFile, cantidadGenerar);
	}
	
	public static void loginData(String pathFile, int cantidadGenerar) throws Exception {
		copyFromFile("UserData.csv", pathFile, cantidadGenerar);
	}
	
	public static void p2pData(String pathFile, int cantidadGenerar) throws Exception {
		generatep2pData(pathFile, cantidadGenerar);
	}
*/	

}