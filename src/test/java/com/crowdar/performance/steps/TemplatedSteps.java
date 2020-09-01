package com.crowdar.performance.steps;

import com.crowdar.api.rest.Request;
import com.crowdar.core.JsonUtils;
import com.crowdar.core.MyThreadLocal;
import com.crowdar.performance.GenerateData;
import com.crowdar.performance.utils.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.When;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;

public class TemplatedSteps {
	
	private final String REPLACEABLE_FORMAT = "{{%s}}";
	private final String VARIABLE_REPLACEABLE_FORMAT = "${%s}";
	
    
    public void runWith (String threads, String rampTime, String loops) throws Exception {
    	String usrDir = System.getProperty("user.dir");
    	String jmeterTXT = (String) MyThreadLocal.getData("jmeterTXT");
    	String methodName = "staticData";
    	
    	String threadGroup = getTemplate("threadGroup");
    	threadGroup = replaceVariable(threadGroup, getTemplateName("loops"), loops);
    	threadGroup = replaceVariable(threadGroup, getTemplateName("threads"), threads);
    	threadGroup = replaceVariable(threadGroup, getTemplateName("ramp_time"), rampTime);

    	jmeterTXT = replaceVariable(jmeterTXT, getTemplateName("threadGroup"), threadGroup);
    	
    	try {
			Method method = GenerateData.class.getMethod(methodName, String.class, int.class);
			String fileNameCSV =  "jemeterFile.csv";
			int quantity = Integer.parseInt(loops) * Integer.parseInt(threads);
			method.invoke(GenerateData.class, "src/test/jmeter/" + fileNameCSV, quantity);
			String dataSet = getTemplate("csvDataSet");
			
			dataSet = replaceVariable(dataSet, getTemplateName("filePath"), usrDir + "/src/test/jmeter/" + fileNameCSV);
			dataSet = replaceVariable(dataSet, getTemplateName("commaSeparatedVariableNames"),"tipoPersona,tipoDocumento,numeroDocumento");
			
			jmeterTXT = replaceVariable(jmeterTXT,  getTemplateName("dataSet"), dataSet);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    	
    	MyThreadLocal.setData("jmeterTXT", jmeterTXT);

    }
    
    public void configJmx (String method, String entity, String jsonName) throws Exception {

    	Request request = getRequest(jsonName);

    	String jmeterTXT = getTemplate("jmx");
    	String header = getHeader(request);
    	jmeterTXT = replaceVariable(jmeterTXT,  getTemplateName("headerManager"), header);

    	String requestSampler = getRequesSampler(request);
    	requestSampler = replaceVariable(requestSampler, getTemplateName("method"), method);

    	jmeterTXT = replaceVariable(jmeterTXT,  getTemplateName("httpSampler"), requestSampler);
    	
    	String usrDir = System.getProperty("user.dir");
		String jmxPath = usrDir + File.separator + "jmeter" + File.separator;
		
    	String onceOnlyController = getTemplate("onceOnlyController");

    	onceOnlyController = replaceVariable(onceOnlyController, getTemplateName("fragmentPath"), jmxPath);

    	jmeterTXT = replaceVariable(jmeterTXT,  getTemplateName("onceOnlyController"), onceOnlyController);

    	MyThreadLocal.setData("jmeterTXT", jmeterTXT);

		FileUtils.writeFile("src/test/jmeter/" + entity + ".jmx", jmeterTXT);
    	
    }

	
    private String getHeader(Request request) throws Exception {
    	String header = "";
		String parameterHeader = "";

    	if(request.getHeaders() != null){
    		header = getTemplate("header");
    		HashMap<String,String> result = null;
    		try {
    		    ObjectMapper mapper = new ObjectMapper();
                result = mapper.readValue(request.getHeaders().toString(), HashMap.class);
                for (String key : result.keySet()) {
                	String parameterTemplate = getTemplate("headerParameter");
        			parameterTemplate = replaceVariable(parameterTemplate,  getTemplateName("value"), result.get(key));
        			parameterTemplate = replaceVariable(parameterTemplate,  getTemplateName("name"), key);
        			parameterHeader += parameterTemplate;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    		header = replaceVariable(header,  getTemplateName("parameters"), parameterHeader);
    	}
    	
    	return header;
	}
    
    private String getRequesSampler(Request request) throws Exception {
    	
    	String httpSampleProxy = getTemplate("httpSampleProxy");
    	
    	URL url = new URL(request.getUrl());
    	String protocol = url.getProtocol();
    	String host = url.getHost();
    	String path = url.getPath();
    	int port = url.getPort();

    	httpSampleProxy = replaceVariable(httpSampleProxy, getTemplateName("url"), host);
    	httpSampleProxy = replaceVariable(httpSampleProxy, getTemplateName("protocol"), protocol);
    	httpSampleProxy = replaceVariable(httpSampleProxy, getTemplateName("endpoint"), path);
    	
    	if (port == -1) {
        	httpSampleProxy = replaceVariable(httpSampleProxy, getTemplateName("port"), "");
    	} else {
        	httpSampleProxy = replaceVariable(httpSampleProxy, getTemplateName("port"), Integer.toString(url.getPort()));
    	}
    	
    	
    	String parameters = getTemplate("getParametersList");
    	String parameter = "";
    	if(request.getUrlParameters() != null && request.getUrlParameters().size() != 0) {
    		for (String key : request.getUrlParameters().keySet()) {
    			String parameterTemplate = getTemplate("parameter");
    			String variable = getVarName(key);
    			parameterTemplate = replaceVariable(parameterTemplate, variable, String.valueOf(request.getUrlParameters().get(key)));
    			parameter += parameterTemplate;
            }
			parameters = replaceVariable(parameters, getTemplateName("paramters"), parameter);
    	}
    	
    	if(request.getBody() != null) {
    		String parameterTemplate = getTemplate("rawRequest");
    		parameters = replaceVariable(parameterTemplate, getTemplateName("rawParams"), request.getBody().toString());
    	}
    	
    	parameters = replaceVariable(parameters, getTemplateName("paramters"), parameter);
    	
    	httpSampleProxy = replaceVariable(httpSampleProxy, getTemplateName("paramters"), parameters);

		return httpSampleProxy;
	}
    
    private String getVarName(String variableName) {
    	
        return String.format(VARIABLE_REPLACEABLE_FORMAT, variableName);
	}
    
    private String getTemplateName(String variableName) {
    	
        return String.format(REPLACEABLE_FORMAT, variableName);
	}
    
    private String getTemplate(String templateName) throws Exception {
    	
        String path = System.getProperty("user.dir").concat(File.separator).concat("src").concat(File.separator)
        		.concat("test").concat(File.separator).concat("resources").concat(File.separator)
        		.concat("jmx_templates").concat(File.separator).concat(templateName).concat(".template");
       
        try {
        	return FileUtils.readFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            System.out.println("JMX was not found " + path + " " + e.getMessage());
            throw e;
		}
        
	}

	private String replaceVariable(String jmeterTXT, String variable, String value) {
		if(jmeterTXT.indexOf(variable) != -1) {
			jmeterTXT = jmeterTXT.replace(variable, value);
		}
		return jmeterTXT;
	}
	
	protected static Request getRequest(String jsonFileName) throws IOException {
        String jsonRequest = JsonUtils.getJSONFromFile(jsonFileName);

        return JsonUtils.deserialize(jsonRequest, Request.class);
    }
}
