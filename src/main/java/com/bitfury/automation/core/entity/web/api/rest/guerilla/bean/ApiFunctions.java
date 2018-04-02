package com.bitfury.automation.core.entity.web.api.rest.guerilla.bean;

/**
 * Created by FOG on 01.04.2018.
 */
public enum ApiFunctions {

    GET_EMAIL_ADDRESS("get_email_address"),
    CHECK_EMAIL("check_email"),
    GET_EMAIL_LIST("get_email_list"),
    FORGET_ME("forget_me"),
    SET_EMAIL_USER("set_email_user");

    private String name;

    ApiFunctions(String methodName) {
        this.name = methodName;
    }

    public String getName() {
        return name;
    }
}
