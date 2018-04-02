package com.bitfury.automation.core.entity.web;

import com.bitfury.automation.core.utils.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * DefaultHttpClient to be used by framework, basically it's wrapper for apache
 * CloserableClient
 */
public class DefaultHttpClient {

    private CloseableHttpClient client;

    public DefaultHttpClient() {
        client = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
    }


    public HttpResponse get(HttpGet request) throws IOException {
        return client.execute(request);
    }

    public String get(String url) {
        try {
            HttpResponse response = client.execute(new HttpGet(url));
            return new BasicResponseHandler().handleResponse(response);
        } catch (IOException e) {
            Logger.warn("Cannot get content from url: '" + url + "'");
        }
        return null;
    }

    public String get(URI uri) {
        try {
            if (uri == null) {
                throw new IllegalArgumentException("Uri should not be null!");
            }
            HttpResponse response = client.execute(new HttpGet(uri.toASCIIString()));
            return new BasicResponseHandler().handleResponse(response);
        } catch (IOException e) {
            Logger.warn("Cannot get content from url: '" + uri.toString() + "'");
        }
        return null;
    }

}
