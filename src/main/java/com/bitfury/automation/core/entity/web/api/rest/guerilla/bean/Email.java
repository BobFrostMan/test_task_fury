package com.bitfury.automation.core.entity.web.api.rest.guerilla.bean;

/**
 * Created by FOG on 02.04.2018.
 * <p>
 * Bean class for Email entity, in terms of guerilla mail client
 * (Probably it was better to make a builder instead of complex constructor)
 */
public class Email {

    private long mailId;
    private String mailFrom;
    private String mailSubject;
    private String mailExcerpt;
    private long mailTimestamp;
    private long mailRead;
    private String mailDate;
    private long att;
    private long mailSize;

    public Email(){}

    public Email(long mailId, String mailFrom, String mailSubject, String mailExcerpt,
                 long mailTimestamp, long mailRead, String mailDate, long att, long mailSize) {
        this.mailId = mailId;
        this.mailFrom = mailFrom;
        this.mailSubject = mailSubject;
        this.mailExcerpt = mailExcerpt;
        this.mailTimestamp = mailTimestamp;
        this.mailRead = mailRead;
        this.mailDate = mailDate;
        this.att = att;
        this.mailSize = mailSize;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public void setMailExcerpt(String mailExcerpt) {
        this.mailExcerpt = mailExcerpt;
    }

    public long getMailId() {
        return mailId;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public String getMailExcerpt() {
        return mailExcerpt;
    }

    public long getMailTimestamp() {
        return mailTimestamp;
    }

    public long getMailRead() {
        return mailRead;
    }

    public String getMailDate() {
        return mailDate;
    }

    public long getAtt() {
        return att;
    }

    public long getMailSize() {
        return mailSize;
    }
}
