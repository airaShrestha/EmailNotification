package com.eficens.emailapp.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

//This is a POJO class
public class EmailTemplate implements Serializable {
    @JsonProperty("fromAddress")
    private String fromAddress;
    @JsonProperty("toAddress")
    private String toAddress;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("emailBody")
    private String emailBody;
    @JsonProperty("filePath")
    private String filePath;

    @JsonProperty("isAttachmentRequired")
    private boolean isAttachmentRequired;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public boolean isAttachmentRequired() {
        return isAttachmentRequired;
    }

    public void setAttachmentRequired(boolean attachmentRequired) {
        isAttachmentRequired = attachmentRequired;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
