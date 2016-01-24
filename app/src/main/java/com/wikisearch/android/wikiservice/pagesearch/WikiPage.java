package com.wikisearch.android.wikiservice.pagesearch;

import com.google.gson.annotations.SerializedName;
import com.wikisearch.android.entity.WikiBaseObject;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiPage implements WikiBaseObject{

    @SerializedName("pageid")
    private int pageid;

    public String getTitle() {
        return title;
    }

    public WikiThumbnail getThumbnail() {
        return thumbnail;
    }

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    private WikiThumbnail thumbnail;
}
