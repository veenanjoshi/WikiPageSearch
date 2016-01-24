package com.wikisearch.android.wikiservice.pagesearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wikisearch.android.wikiservice.WikiConstants;
import com.wikisearch.android.wikiservice.WikiParser;

import org.json.JSONObject;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiPageParser extends WikiParser {

    public WikiPageSearchResponse parseResponse(JSONObject response) {

        JSONObject query = response.optJSONObject(WikiConstants.WIKI_RESPONSE_OBJECT_QUERY);
        if(query != null && query.has(WikiConstants.WIKI_RESPONSE_OBJECT_PAGES)) {
            String pages = query.optJSONObject(WikiConstants.WIKI_RESPONSE_OBJECT_PAGES).toString();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(WikiPageSearchResponse.class, new WikiPagesAdapter());
            Gson gson = builder.create();

            return (gson.fromJson(pages, WikiPageSearchResponse.class));
        }
        else{
            return null;
        }
    }
}
