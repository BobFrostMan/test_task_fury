package com.bitfury.automation.core.entity.web.api.rest.guerilla.impl;

import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.ApiFunctions;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.IGuerillaApiClient;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.Email;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.Session;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.UserSession;
import com.bitfury.automation.core.utils.json.JsonUtil;
import com.bitfury.automation.core.utils.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Simple implementation of Guerilla API client to provide mail reading
 */
public class GuerrillaClient implements IGuerillaApiClient {

    private static final String API_URL = "https://api.guerrillamail.com/ajax.php";
    private static final String FUNCTION_NAME_PARAM = "f";

    @Override
    public UserSession createTempMailbox(String username) {
        String site = "guerrillamail.com";
        Session session = getEmailAddress("en", site, "Mozilla_foo_bar");
        UserSession userSession = setEmailUser(username, "en", site, session);
        getEmailList(userSession, 0, 0);
        return userSession;
    }

    public Session getEmailAddress(String lang, String site, String agent) {
        Map<String, Object> params = new HashMap<>();
        params.put("lang", lang);
        params.put("site", site);
        params.put("agent", agent);

        JsonNode responseJson = sendGetRequest(API_URL, ApiFunctions.GET_EMAIL_ADDRESS, params);
        if (responseJson == null) {
            return null;
        }

        return new Session(getText(responseJson, "email_addr"),
                getLong(responseJson, "email_timestamp"),
                getText(responseJson, "alias"),
                getText(responseJson, "sid_token"));
    }

    public UserSession setEmailUser(String emailUser, String lang, String site, Session session) {
        Map<String, Object> params = new HashMap<>();
        params.put("email_user", emailUser);
        params.put("lang", lang);
        params.put("sid_token", session.getSidToken());
        params.put("site", site);

        JsonNode responseJson = sendGetRequest(API_URL, ApiFunctions.SET_EMAIL_USER, params);
        if (responseJson == null) {
            return null;
        }

        return new UserSession(
                getText(responseJson, "alias_error"),
                getText(responseJson, "alias"),
                getText(responseJson, "email_addr"),
                getLong(responseJson, "email_timestamp"),
                getLong(responseJson, "site_id"),
                getText(responseJson, "sid_token"),
                getText(responseJson, "site"),
                getBoolean(responseJson.path("auth"), "success"),
                getText(responseJson.path("auth"), "error_codes")
        );
    }

    public List<Email> checkEmail(UserSession session, long seq) {
        Map<String, Object> params = new HashMap<>();
        params.put("seq", seq);
        params.put("sid_token", session.getSidToken());

        JsonNode responseJson = sendGetRequest(API_URL, ApiFunctions.CHECK_EMAIL, params);
        if (responseJson == null) {
            return null;
        }

        return getEmailList(responseJson);
    }

    public List<Email> getEmailList(UserSession session, long offset, long seq) {
        Map<String, Object> params = new HashMap<>();
        params.put("seq", seq);
        params.put("offset", offset);
        params.put("sid_token", session.getSidToken());

        JsonNode responseJson = sendGetRequest(API_URL, ApiFunctions.GET_EMAIL_LIST, params);
        if (responseJson == null) {
            return null;
        }

        return getEmailList(responseJson);
    }

    public boolean forgetMe(UserSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("email_addr", session.getEmailAddr());
        params.put("sid_token", session.getSidToken());

        JsonNode responseJson = sendGetRequest(API_URL, ApiFunctions.FORGET_ME, params);
        if (responseJson == null) {
            return false;
        }

        return responseJson.asBoolean();
    }

    private String getText(JsonNode jsonNode, String fieldName) {
        if (jsonNode.has(fieldName)) {
            return jsonNode.get(fieldName).textValue();
        }
        return null;
    }

    private boolean getBoolean(JsonNode jsonNode, String fieldName) {
        if (jsonNode.has(fieldName)) {
            return jsonNode.get(fieldName).booleanValue();
        }
        return false;
    }

    private long getLong(JsonNode jsonNode, String fieldName) {
        if (jsonNode.has(fieldName)) {
            return jsonNode.get(fieldName).longValue();
        }
        return 0;
    }

    private JsonNode sendGetRequest(String url, ApiFunctions function, Map<String, Object> params) {
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameter(FUNCTION_NAME_PARAM, function.getName());
            for (Map.Entry<String, Object> param : params.entrySet()) {
                builder.setParameter(param.getKey(), String.valueOf(param.getValue()));
            }

            HttpGet request = new HttpGet(builder.build());
            return sendGetRequest(request);
        } catch (IOException | URISyntaxException e) {
            Logger.error("Error occured during get request execution. Message: " + e.getMessage(), e);
        }
        return null;
    }

    private JsonNode sendGetRequest(HttpGet request) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        if (response == null) {
            return null;
        }

        return JsonUtil.httpResponseToJson(response);
    }

    private List<Email> getEmailList(JsonNode responseJson) {
        JsonNode emailListNode = responseJson.get("list");
        List<Email> emailList = new ArrayList<>();

        if (emailListNode == null) {
            return Collections.emptyList();
        }

        for (JsonNode emailNode : emailListNode) {
            final Email email = new Email(
                    getLong(emailNode, "mail_id"),
                    getText(emailNode, "mail_from"),
                    getText(emailNode, "mail_subject"),
                    getText(emailNode, "mail_excerpt"),
                    getLong(emailNode, "mail_timestamp"),
                    getLong(emailNode, "mail_read"),
                    getText(emailNode, "mail_date"),
                    getLong(emailNode, "att"),
                    getLong(emailNode, "mail_size")
            );
            emailList.add(email);
        }
        return emailList;
    }
}
