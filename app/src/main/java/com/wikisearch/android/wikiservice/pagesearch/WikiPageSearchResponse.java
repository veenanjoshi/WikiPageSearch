package com.wikisearch.android.wikiservice.pagesearch;

import com.wikisearch.android.entity.WikiResponse;
import com.wikisearch.android.entity.WikiServiceCodes;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiPageSearchResponse extends WikiResponse {

    public WikiPageSearchResponse(){
        setRequestCode(WikiServiceCodes.WIKI_PAGE_SEARCH_SERVICE);
    }
}
