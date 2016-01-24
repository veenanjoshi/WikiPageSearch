package com.wikisearch.android.wikiservice;

import com.wikisearch.android.entity.WikiServiceCodes;
import com.wikisearch.android.wikiservice.pagesearch.WikiPageParser;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiParserFactory {
    public static WikiParser getParser(int serviceCode) {

        switch (serviceCode){
            case WikiServiceCodes.WIKI_PAGE_SEARCH_SERVICE:
                return new WikiPageParser();

            default:
                return null;
        }
    }
}
