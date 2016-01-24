package com.wikisearch.android.wikiservice.pagesearch;

import android.content.ContentValues;

import com.wikisearch.android.entity.WikiRequest;
import com.wikisearch.android.entity.WikiServiceCodes;
import com.wikisearch.android.wikiapplication.WikiSearchApplication;
import com.wikisearch.android.wikiservice.WikiConstants;

/**
 * Created by veena.joshi on 1/24/2016.
 */
public class WikiPageSearchRequest extends WikiRequest {

    public WikiPageSearchRequest() {
        ContentValues values = new ContentValues();
        values.put(WikiConstants.WIKI_PARAM_KEY_ACTION,WikiConstants.WIKI_PARAM_VALUE_QUERY);
        values.put(WikiConstants.WIKI_PARAM_KEY_PROP,WikiConstants.WIKI_PARAM_VALUE_PROP_PAGE_IMAGES);
        values.put(WikiConstants.WIKI_PARAM_KEY_FORMAT,WikiConstants.WIKI_PARAM_VALUE_JSON);
        values.put(WikiConstants.WIKI_PARAM_KEY_PIPROP,WikiConstants.WIKI_PARAM_VALUE_THUMBNAIL);
        values.put(WikiConstants.WIKI_PARAM_KEY_PITHUMBSIZE, WikiSearchApplication.getInstance().getImageWidth());
        values.put(WikiConstants.WIKI_PARAM_KEY_PILIMIT,WikiConstants.WIKI_PARAM_VALUE_PILIMIT);
        values.put(WikiConstants.WIKI_PARAM_KEY_GENERATOR, WikiConstants.WIKI_PARAM_VALUE_PREFIXSEARCH);
        setQueryParams(values);
        setRequestCode(WikiServiceCodes.WIKI_PAGE_SEARCH_SERVICE);
    }
}
