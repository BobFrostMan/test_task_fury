package com.bitfury.automation.core.entity.web.api.rest.wiki.bean;

import com.bitfury.automation.core.utils.json.JsonUtil;

/**
 * Created by FOG on 01.04.2018.
 *
 * Entity that represents article intro content and stores as String array.
 * Each array element is - one single sentence.
 */
public class WikiExtract {

    private static final String EXTRACT_FIELD_NAME = "extract";
    private static final String REGEX_SPLITTER_OF_LINKS = "[.]";

    private String[] extract = new String[]{};
    
    public WikiExtract(String json) {
        init(json);
    }

    private void init(String json) {
        String extractField = JsonUtil.findValue(EXTRACT_FIELD_NAME, json);
        if (extractField != null) {
            extract = extractField.split(REGEX_SPLITTER_OF_LINKS);
        }
    }

    public String[] getExtract() {
        return extract;
    }
}