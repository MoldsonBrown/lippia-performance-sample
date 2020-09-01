package com.crowdar.performance.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
/**
 * Class used to read a jmeter template file,
 * create a jmeter file modified to be executed later,
 * and read a properties json file to create a List<Object>
 *
 *
 */
public class FileUtils {

	public static void copyJMeterFile(String fileName) throws Exception {
		File f = new File(System.getProperty("user.dir") + "/jmeter/" + fileName);
		if (!f.exists()) {
			throw new Exception("El archivo " + fileName + " no existe");
		}

		File destino = new File("src/test/jmeter/" + fileName);
		if (destino.exists()) {
			destino.delete();
		}

		org.apache.commons.io.FileUtils.copyFile(f, destino);
	}

	public static void copyFile(String src,String dest) throws Exception {
			File f = new File(System.getProperty("user.dir") + "/src/test/resources/" + src);
			if (!f.exists()) {
				throw new Exception("El archivo " + src + " no existe");
			}

			File destino = new File(dest);
			if (destino.exists()) {
				destino.delete();
			}

			org.apache.commons.io.FileUtils.copyFile(f, destino);
		}
		
		
	public static String readFile(String fileName) throws Exception {
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		String str = new String(data, "UTF-8");
		return str;
	}

	public static void writeFile(String fileDestination, String content) throws Exception {
		org.apache.commons.io.FileUtils.writeStringToFile(new File(fileDestination), content, "UTF-8");
	}

//	public static List<Object> readJsonObject(String file) throws Exception {
//		List<Object> configList = new ArrayList<>();
//
//		// JSON parser object to parse read file
//		JSONParser jsonParser = new JSONParser();
//		FileReader readerFile = null;
//		InputStream urlFile = null;
//
//		try {
//			System.out.println("Intento abrir como archivo");
//			readerFile = new FileReader(file);
//		} catch (Exception e) {
//			System.out.println("No se encontro el archivo: " + file);
////			e.printStackTrace();
//		}
//
//		try {
//			System.out.println("Intento abrir como URL");
//			URL u = new URL(file);
//			urlFile = u.openStream();
//		} catch (Exception e) {
////			e.printStackTrace();
//			System.out.println("No se encontro la URL: " + file);
//		}
//
//		System.out.println("Continuo luego de abrir archivo");
//
//		try {
//			JSONObject propertiesJson = null;
//			// Read JSON file
//			if (readerFile != null) {
//				propertiesJson = (JSONObject) jsonParser.parse(readerFile);
//			} else {
//				propertiesJson = (JSONObject) jsonParser.parse(new InputStreamReader(urlFile, "UTF-8"));
//			}
//
//			// Files csv generators configurados en el json
//			JSONArray csvFileGenerator = (JSONArray) propertiesJson.get("csvFileGenerator");
//			for (int j = 0; j < csvFileGenerator.size(); j++) {
//				JSONObject csvConfig = (JSONObject) csvFileGenerator.get(j);
//				CsvFileGeneratorConfig config = new CsvFileGeneratorConfig();
//
//				if (csvConfig.containsKey("downloaded_file_path")) {
//					config.setFilePath((String) csvConfig.get("downloaded_file_path"));
//				}
//				
//				config.setMethodGenerateGata((String) csvConfig.get("method_generate_data"));
//				config.setNumRecords(Integer.parseInt((String) csvConfig.get("num_records")));
//				config.setFrom((String) csvConfig.get("from"));
//				config.setTo((String) csvConfig.get("to"));
//				config.setFtpDomain((String) csvConfig.get("ftp_domain"));
//				config.setFtpPort((String) csvConfig.get("ftp_port"));
//				config.setFtpUser((String) csvConfig.get("ftp_user"));
//				config.setFtpPass((String) csvConfig.get("ftp_pass"));
//
//				configList.add(config);
//			}
//
//			// JMeters configurados en el json
//			JSONArray jmeters = (JSONArray) propertiesJson.get("jmeters");
//			for (int i = 0; i < jmeters.size(); i++) {
//				JSONObject jmeterConfig = (JSONObject) jmeters.get(i);
//				JmeterConfig config = new JmeterConfig();
//
//				config.setFileName((String) jmeterConfig.get("file_name"));
//				config.setCaseName((String) jmeterConfig.get("case_name"));
//				String metodos[] = ((String) jmeterConfig.get("method_generate_data")).split(",");
//				String loopsCant[] = ((String) jmeterConfig.get("loops")).split(",");
//				config.setServer((String) jmeterConfig.get("server"));
//				config.setProtocol((String) jmeterConfig.get("protocol"));
//				config.setEndpoint((String) jmeterConfig.get("endpoint"));
//				
//				config.setLoginurl((String) jmeterConfig.get("loginurl"));
//				config.setLoginendpoint((String) jmeterConfig.get("loginendpoint"));
//				config.setClientId((String) jmeterConfig.get("client_id"));
//				config.setClient_secret((String) jmeterConfig.get("client_secret"));
//				config.setAudience((String) jmeterConfig.get("audience"));
//				config.setGrantType((String) jmeterConfig.get("grant_type"));
//
//				int loopcant = 0;
//				for (String metodo : metodos) {
//					Loops loop = new Loops();
//					loop.setMethodsGenerateData(metodo.trim());
//
//					if (loopsCant.length > loopcant) {
//						loop.setLoops(Integer.parseInt(loopsCant[loopcant].trim()));
//					} else {
//						loop.setLoops(1);
//					}
//					config.getLoops().add(loop);
//					loopcant++;
//				}
//
//				String rampTime = (String) jmeterConfig.get("ramp_time");
//				if (!rampTime.equals("")) {
//					config.setRampTime(Integer.parseInt(rampTime));
//				} else {
//					config.setRampTime(1);
//				}
//
//				String qtyThreads = (String) jmeterConfig.get("threads");
//				if (!qtyThreads.equals("")) {
//					config.setThreads(Integer.parseInt(qtyThreads));
//				} else {
//					config.setThreads(1);
//				}
//
//				if (jmeterConfig.containsKey("port")) {
//					config.setPort((String) jmeterConfig.get("port"));
//				}
//
//				configList.add(config);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		return configList;
//	}

	public static void deleteJmeterFolder() throws IOException {
		File testsFolder = new File("src/test/jmeter");
		if (testsFolder.exists()) {
			for (File f : testsFolder.listFiles()) {
				f.delete();
			}
		}
		Files.deleteIfExists(testsFolder.toPath());
		testsFolder.mkdirs();
	}

	public static String getFileNameByType(String listType, String fileType) {
		File testsFolder = new File("src/test/jmeter");
		File[] matchingFiles = testsFolder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains(listType + "." + fileType);
			}
		});
		return matchingFiles[0].getName();
	}
	
	public static List<String> getLinesFromResource(String filename, boolean includeHeader) {
		List<String> lines = new ArrayList<String>();
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classloader.getResourceAsStream(filename);
			InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(streamReader);
			for (String line; (line = reader.readLine()) != null;) {
				lines.add(line);
			}

		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		return includeHeader ? lines : lines.subList(1, lines.size());
	}
	
	public static List<String> getLines(String filename, boolean includeHeader) {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
			for (String line; (line = reader.readLine()) != null;) {
				lines.add(line);
			}

		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		return includeHeader ? lines : lines.subList(1, lines.size());
	}

}
