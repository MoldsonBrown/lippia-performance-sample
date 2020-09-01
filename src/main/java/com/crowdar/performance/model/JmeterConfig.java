package com.crowdar.performance.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JmeterConfig {
	
    @JsonProperty("file_name")
	private String fileName;
    @JsonProperty("case_name")
	private String caseName;
    @JsonProperty("method_generate_data")
	private String MethodGenerateData;
    @JsonProperty("config")
	private Map<String, String> config;
    @JsonProperty("threads")
	private Integer threads;
    @JsonProperty("ramp_time")
	private Integer rampTime;
    @JsonProperty("loops")
	private Integer loops;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getMethodGenerateData() {
		return MethodGenerateData;
	}

	public void setMethodGenerateData(String methodGenerateData) {
		MethodGenerateData = methodGenerateData;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

	public Integer getThreads() {
		return threads;
	}

	public void setThreads(Integer threads) {
		this.threads = threads;
	}

	public Integer getRampTime() {
		return rampTime;
	}

	public void setRampTime(Integer rampTime) {
		this.rampTime = rampTime;
	}

	public Integer getLoops() {
		return loops;
	}

	public void setLoops(Integer loops) {
		this.loops = loops;
	}

	
	
	
}
