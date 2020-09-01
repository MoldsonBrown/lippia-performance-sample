package com.crowdar.performance;

import java.io.File;
import java.io.IOException;

import com.github.netudima.jmeter.junit.report.JtlToJUnitReportTransformer;

public class TestTransformToJunit {

	public static void main(String[] args) throws IOException {
    	System.out.println("Start transformation");

		String usrDir = System.getProperty("user.dir");

		JtlToJUnitReportTransformer jut = new JtlToJUnitReportTransformer();
    	
    	String basedir = "TestPerformance";
    	
    	String csvPath = usrDir + File.separator + basedir + File.separator + "jmeter" + File.separator + "results" + File.separator + "20200402-clientes_domicilios.csv";
    	System.out.println(csvPath);
    	String junitReportFile = usrDir + File.separator + "junit.xml";
    	System.out.println(junitReportFile);

    	jut.transform(csvPath , junitReportFile); 
    	
    	System.out.println("Finish transformation");


	}

}
