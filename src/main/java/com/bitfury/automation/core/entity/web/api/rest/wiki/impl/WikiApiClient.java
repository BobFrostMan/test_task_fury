package com.bitfury.automation.core.entity.web.api.rest.wiki.impl;

import com.bitfury.automation.core.entity.web.DefaultHttpClient;
import com.bitfury.automation.core.entity.web.api.rest.wiki.IWikiApiClient;
import com.bitfury.automation.core.entity.web.api.rest.wiki.bean.WikiExtract;
import com.bitfury.automation.core.utils.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * Created by FOG on 01.04.2018.
 *
 * Basic implementation of WikiApi client.
 * Currently executes only requests, that required for given framework
 */
public class WikiApiClient implements IWikiApiClient {

    private static final String DEFAULT_WIKI_ENDPOINT = "https://en.wikipedia.org/w/api.php";

    private String endpoint;

    private DefaultHttpClient httpClient;

    public WikiApiClient(String endpoint) {
        this.httpClient = new DefaultHttpClient();
        this.endpoint = endpoint;
    }

    public WikiApiClient() {
        this.httpClient = new DefaultHttpClient();
        this.endpoint = DEFAULT_WIKI_ENDPOINT;
    }

    /**
     * Raw request looks like this
     * https://en.wikipedia.org/w/api.php?
     * format=json
     * &action=query
     * &prop=extracts
     * &exintro=
     * &explaintext=
     * &titles=Donald%20Trump
     */
    @Override
    public String getIntro(String searchText) {
        URI uri = null;
        try {
            uri = new URIBuilder(endpoint)
                    .setParameter("format", "json")
                    .setParameter("action", "query")
                    .setParameter("prop", "extracts")
                    .setParameter("exintro", "")
                    .setParameter("explaintext", "")
                    .setParameter("titles", searchText)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return httpClient.get(uri);
    }

    @Override
    public String getShortIntroContent(String searchText) {
        WikiExtract extract = new WikiExtract(getIntro(searchText));
        return extract.getExtract()[0] + ".";
    }
}
