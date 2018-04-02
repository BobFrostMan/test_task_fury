package com.bitfury.automation.core.entity.web.api.rest.guerilla.bean;

/**
 * Created by FOG on 02.04.2018.
 * <p>
 * Bean class for Session entity for guerilla mail client
 */
public class Session {

    private String emailAddr;
    private long emailTimestamp;
    private String alias;
    private String sidToken;

    public Session(String emailAddr, long emailTimestamp, String alias, String sidToken) {
        this.emailAddr = emailAddr;
        this.emailTimestamp = emailTimestamp;
        this.alias = alias;
        this.sidToken = sidToken;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public long getEmailTimestamp() {
        return emailTimestamp;
    }

    public String getAlias() {
        return alias;
    }

    public String getSidToken() {
        return sidToken;
    }
}
