package com.crowdar.configuration;

import java.io.File;
import java.io.IOException;

import com.crowdar.core.MyThreadLocal;
import com.crowdar.performance.utils.FileUtils;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
	@Before
    public void beforeScenario() throws IOException{
		org.apache.commons.io.FileUtils.copyFile(new File("src/test/resources/jmeter.properties"),
				new File("src/test/jmeter/jmeter.properties"));
    }

    @After
    public void afterScenario() throws Exception{
    	String scriptName = (String) MyThreadLocal.getData("scriptName");
    	String jmxFineName = scriptName + ".jmx";
		File dest = new File("src/test/jmeter/" + jmxFineName);
		if (dest.exists()) {
			dest.delete();
		}
		FileUtils.writeFile("src/test/jmeter/" + jmxFineName, (String) MyThreadLocal.getData("jmx"));

    }
}
