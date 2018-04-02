package com.bitfury.automation.core.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Created by FOG on 02.04.2018.
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode httpResponseToJson(HttpResponse httpResponse) {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return mapper.readTree(result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findValue(String key, String json) {
        try {
            JsonNode result = findValue(key, mapper.readTree(json));
            if (result != null) {
                return result.textValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JsonNode findValue(String key, JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }

        Iterator<String> fieldNames = jsonNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode node = jsonNode.get(fieldName);
            if (fieldName.equals(key)) {
                return node;
            }
            if (node == null) {
                return null;
            }
            JsonNode nextNode = findValue(key, node);
            if (nextNode != null) {
                return nextNode;
            }
        }
        return null;
    }

}
