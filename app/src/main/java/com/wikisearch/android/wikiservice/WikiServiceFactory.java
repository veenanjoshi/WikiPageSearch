package com.wikisearch.android.wikiservice;

import com.wikisearch.android.entity.WikiServiceCodes;
import com.wikisearch.android.wikiservice.pagesearch.WikiPageSearchService;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public class WikiServiceFactory {
    public static WikiService getWebService(int serviceCode) {

        switch (serviceCode){
            case WikiServiceCodes.WIKI_PAGE_SEARCH_SERVICE:
                return new WikiPageSearchService();

            default:
                return null;
        }
    }
}
