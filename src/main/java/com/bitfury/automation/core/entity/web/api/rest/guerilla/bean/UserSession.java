package com.bitfury.automation.core.entity.web.api.rest.guerilla.bean;

/**
 * Created by FOG on 02.04.2018.
 * <p>
 * Bean class for UserSession entity for guerilla mail client
 */
public class UserSession {

    private String aliasError;
    private String alias;
    private String emailAddr;
    private long emailTimestamp;
    private long siteId;
    private String sidToken;
    private String site;
    private boolean authSuccess;
    private String authErrorCode;

    public UserSession(String aliasError, String alias, String emailAddr, long emailTimestamp, long siteId, String sidToken, String site, boolean authSuccess, String authErrorCode) {
        this.aliasError = aliasError;
        this.alias = alias;
        this.emailAddr = emailAddr;
        this.emailTimestamp = emailTimestamp;
        this.siteId = siteId;
        this.sidToken = sidToken;
        this.site = site;
        this.authSuccess = authSuccess;
        this.authErrorCode = authErrorCode;
    }

    public String getAliasError() {
        return aliasError;
    }

    public String getAlias() {
        return alias;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public long getEmailTimestamp() {
        return emailTimestamp;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSidToken() {
        return sidToken;
    }

    public String getSite() {
        return site;
    }

    public boolean isAuthSuccess() {
        return authSuccess;
    }

    public String getAuthErrorCode() {
        return authErrorCode;
    }
}
