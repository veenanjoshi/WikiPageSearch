package com.wikisearch.android.wikiservice.pagesearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiThumbnail {

    public String getSource() {
        return source;
    }

    @SerializedName("source")
    private String source;

}
