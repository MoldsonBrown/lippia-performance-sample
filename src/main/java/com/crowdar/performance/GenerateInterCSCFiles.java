package com.crowdar.performance;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.*;

import com.crowdar.performance.model.CsvFileGeneratorConfig;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class GenerateInterCSCFiles {
	static int highTagNumber = 16777215;

	/**
	 *
	 * @param 	path
	 * 			to store the zip, relative to the project folder root location
	 * @param 	filename
	 * 			name of the file to compress
	 * @param 	zipName
	 * 			output name of the zip generated
	 *
	 * @throws 	FileNotFoundException
	 * 			if the file does not exist in the path
	 * @throws 	IOException
	 * 			if fails during reading the file o writing the zip file
	 */
	public static void writeToZipFile(String path, String filename, String zipName) throws FileNotFoundException, IOException {
		Map<String, String> env = new HashMap<>();
		env.put("create", "true");

		File aFile = new File(path+filename);
		String filePAth = aFile.getAbsoluteFile().getParent();

		//URI uri = URI.create("jar:file:"+filePAth+""+zipName);

		Path pathUri = Paths.get(filePAth+"/"+zipName);
		URI uri = URI.create("jar:"+pathUri.toUri());
		System.out.println("URI:"+uri);


		try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
			Path externalTxtFile = Paths.get(filePAth+"/"+filename);
			Path pathInZipfile = zipfs.getPath(filename);
			// copy a file into the zip file
			Files.copy(externalTxtFile, pathInZipfile, StandardCopyOption.REPLACE_EXISTING);
		}

		//elimino el file
		//aFile.delete();
	}

	/**
	 *
	 * @param 	localPath
	 * 			path of the file to upload, relative to root
	 * @param 	filename
	 * 			name of the file to upload
	 * @param 	config
	 * 			CsvFileGeneratorConfig object that contains the necessary config
	 *
	 * @throws 	IOException
	 * 			if fails during writing the file
	 */
	public static void uploadToFtp(String localPath, String filename, CsvFileGeneratorConfig config) throws IOException {
		FTPClient client = new FTPClient();
		FileInputStream fis = null;

		try {
            if (config != null) {
                client.connect(config.getFtpDomain(), Integer.parseInt(config.getFtpPort()));
                client.login(config.getFtpUser(), config.getFtpPass());
                client.enterLocalPassiveMode();
            }else{
                client.connect("10.0.1.7", 2121);
                client.login("iag", "admin");
                client.enterLocalPassiveMode();
            }
            client.setFileType(FTP.BINARY_FILE_TYPE);


            // Create an InputStream of the file to be uploaded
			fis = new FileInputStream(localPath);

			// Store file to server
			client.storeFile(filename, fis);
			client.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
