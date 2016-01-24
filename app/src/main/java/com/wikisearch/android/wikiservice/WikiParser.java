package com.wikisearch.android.wikiservice;

import com.wikisearch.android.entity.WikiResponse;

import org.json.JSONObject;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiParser {

    public WikiResponse parseResponse(JSONObject response){
        return new WikiResponse();
    }
}
