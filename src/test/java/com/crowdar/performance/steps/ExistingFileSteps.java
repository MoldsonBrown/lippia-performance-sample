package com.crowdar.performance.steps;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.crowdar.core.JsonUtils;
import com.crowdar.core.MyThreadLocal;
import com.crowdar.core.PageSteps;
import com.crowdar.performance.GenerateData;
import com.crowdar.performance.utils.FileUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class ExistingFileSteps extends PageSteps {
	
	@Given("^configure the performance test with '(.*)' iterations '(.*)' users and a ramp time of '(.*)' seconds$")
    public void configThreadGroup (String loops, String threads, String rampTime) {
		MyThreadLocal.setData("LOOPS", loops);
		MyThreadLocal.setData("THREADS", threads);
		MyThreadLocal.setData("RAMP_TIME", rampTime);
		
	}
	
	@When("^run the script '(.*)'$")
    public void runScript (String scriptName) throws Exception {

		MyThreadLocal.setData("scriptName", scriptName);

		String jmxPath = System.getProperty("user.dir") + File.separator + "jmeter" + File.separator;
		
		String jmeterTXT = FileUtils.readFile(jmxPath + scriptName + ".jmx");
		jmeterTXT = jmeterTXT.replace("$THREADS", (String) MyThreadLocal.getData("THREADS"));
		jmeterTXT = jmeterTXT.replace("$RAMP_TIME", (String) MyThreadLocal.getData("RAMP_TIME"));
		jmeterTXT = jmeterTXT.replace("$LOOPS1", (String) MyThreadLocal.getData("LOOPS"));
		
		MyThreadLocal.setData("jmx", jmeterTXT);
		
	}
	
	@When("^use static data$")
    public void staticData() throws Exception{
		String scriptName = (String) MyThreadLocal.getData("scriptName");
		String jmeterTXT = (String) MyThreadLocal.getData("jmx");

		String methodString = "staticData";
		String threads = (String) MyThreadLocal.getData("THREADS");
		String loop = (String) MyThreadLocal.getData("LOOPS");
				
		jmeterTXT = generateData(scriptName, jmeterTXT, methodString, threads, loop);
		
		MyThreadLocal.setData("jmx", jmeterTXT);
		
	}
	
	@When("^use generated data method '(.*)'$")
    public void methodData(String methodString) throws Exception{
		String scriptName = (String) MyThreadLocal.getData("scriptName");
		String jmeterTXT = (String) MyThreadLocal.getData("jmx");

		String threads = (String) MyThreadLocal.getData("THREADS");
		String loop = (String) MyThreadLocal.getData("LOOPS");
				
		jmeterTXT = generateData(scriptName, jmeterTXT, methodString, threads, loop);
		
		MyThreadLocal.setData("jmx", jmeterTXT);
		
	}

	private String generateData(String scriptName, String jmeterTXT, String methodString, String threads, String loop)
			throws IllegalAccessException, InvocationTargetException {
		java.lang.reflect.Method method;
		try {
			
			method = GenerateData.class.getMethod(methodString, String.class, int.class);
			
			String fileNameCSV = scriptName + ".csv";
			
			method.invoke(GenerateData.class, "src/test/jmeter/" + fileNameCSV, Integer.parseInt(loop) * Integer.parseInt(threads));

			jmeterTXT = jmeterTXT.replace("$method_generate_data",
						System.getProperty("user.dir") + "/src/test/jmeter/" + fileNameCSV);
			
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return jmeterTXT;
	}
	
	@When("^remplace variables with '(.*)'$")
    public void replaceVariables (String json) throws Exception {
		String jmeterTXT = (String) MyThreadLocal.getData("jmx");
		
		Map<String, String> configs = getConfigurations(json);
		
		for (String key : configs.keySet()) {
    		jmeterTXT = jmeterTXT.replace("$" + key + "", configs.get(key));
        }
		jmeterTXT = jmeterTXT.replace("$basePath",	System.getProperty("user.dir") + "/jmeter/");

		MyThreadLocal.setData("jmx", jmeterTXT);

	}
	
	protected static Map<String, String> getConfigurations(String jsonFileName) throws IOException {
        String jsonRequest = JsonUtils.getJSONFromFile(jsonFileName);

        return JsonUtils.deserialize(jsonRequest, HashMap.class);
    }
    

}
