package com.crowdar.performance.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class XMLUtils {
	
//	Paso 1: Enviar configuracion de jenkins al proyecto Maven
//	Paso 2: El proyecto Maven ejecuta Java
//	Paso 2: Java edita el template de JMeter a correr.
//	Paso 3: Java genera el CSV
//	Paso 4: El proyecto Maven corre el JMeter.
	
	public static Document openXML(String filePath) throws Exception{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
		Document document = domBuilder.parse(filePath);
		return document;
	}
	
	public static void edit(Document document, String path, String atributeValue, String atributeName, String value) throws Exception {
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		NodeList nodes = (NodeList)xPath.evaluate(path,document, XPathConstants.NODESET);
		for (int i = 0; i < nodes.getLength(); ++i) {
		    Element e = (Element) nodes.item(i);
		    e.getFirstChild().setNodeValue("macmacmac");
		}
	}
	
	public static void saveXML(String filePath, Document document) throws Exception{
		// Save XML to file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(new DOMSource(document),new StreamResult(filePath));

	}

	public static void removeDub(String filePath, Integer size) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		Set<String> lines = new HashSet<String>(size+1); // maybe should be bigger
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		for (String unique : lines) {
			writer.write(unique);
			writer.newLine();
		}
		writer.close();
	}

}
