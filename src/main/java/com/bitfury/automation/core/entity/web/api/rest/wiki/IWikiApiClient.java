package com.bitfury.automation.core.entity.web.api.rest.wiki;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Interface for Wikipedia client API
 */
public interface IWikiApiClient {

    /**
     * Returns article intro content as string
     *
     * @param searchText - article name to search
     * @return String representation of article intro content
     */
    public String getIntro(String searchText);

    /**
     * Returns first sentence from Wiki intro
     *
     * @param searchText - article name to search
     * @return - first sentence from article intro
     */
    public String getShortIntroContent(String searchText);

}
