package com.wikisearch.android.entity;

import java.util.List;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public class WikiResponse {

    private int requestCode;

    private List<WikiBaseObject> wikiBaseObjectList;

    public int getRequestCode() {
        return requestCode;
    }

    protected void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public List<WikiBaseObject> getWikiBaseObjectList() {
        return wikiBaseObjectList;
    }

    public void setWikiBaseObjectList(List<WikiBaseObject> wikiBaseObjectList) {
        this.wikiBaseObjectList = wikiBaseObjectList;
    }

}
