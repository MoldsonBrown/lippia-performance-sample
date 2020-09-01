package com.crowdar.performance.model;

public class CsvFileGeneratorConfig {
    private String filePath;
    private String methodGenerateData;
    private Integer numRecords;
    private String from;
    private String to;
    private String ftpDomain;
    private String ftpPort;
    private String ftpUploadPath;
    private String ftpUser;
    private String ftpPass;

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getMethodGenerateData() {
        return methodGenerateData;
    }
    public void setMethodGenerateGata(String methodGenerateData) {
        this.methodGenerateData = methodGenerateData;
    }
    public Integer getNumRecords() { return numRecords; }
    public void setNumRecords(Integer numRecords) { this.numRecords = numRecords; }
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getFtpDomain() { return ftpDomain; }
    public void setFtpDomain(String ftpDomain) { this.ftpDomain = ftpDomain; }
    public String getFtpPort() { return ftpPort; }
    public void setFtpPort(String ftpPort) { this.ftpPort = ftpPort; }
    public String getFtpUploadPath() { return ftpUploadPath; }
    public void setFtpUploadPath(String ftpUploadPath) { this.ftpUploadPath = ftpUploadPath; }
    public String getFtpUser() { return ftpUser; }
    public void setFtpUser(String ftpUser) { this.ftpUser = ftpUser; }
    public String getFtpPass() { return ftpPass; }
    public void setFtpPass(String ftpPass) { this.ftpPass = ftpPass; }
}
