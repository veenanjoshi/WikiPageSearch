package com.wikisearch.android.wikiservice;

import com.wikisearch.android.entity.WikiRequest;
import com.wikisearch.android.entity.WikiServiceCodes;
import com.wikisearch.android.wikiservice.pagesearch.WikiPageSearchRequest;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiRequestFactory {
    public static WikiRequest getWikiRequest(int serviceCode) {

        switch (serviceCode){
            case WikiServiceCodes.WIKI_PAGE_SEARCH_SERVICE:
                return new WikiPageSearchRequest();

            default:
                return new WikiRequest();
        }
    }
}
