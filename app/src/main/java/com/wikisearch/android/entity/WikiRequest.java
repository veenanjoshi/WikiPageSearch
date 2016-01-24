package com.wikisearch.android.entity;

import android.content.ContentValues;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public class WikiRequest {

    private String baseUrl ="https://en.wikipedia.org/w/api.php?";

    private int requestCode;

    private ContentValues queryParams;

    public WikiRequest() {
    }

    public ContentValues getQueryParams(){
        return queryParams;
    }
    protected void setQueryParams(ContentValues values){
        queryParams = values;
    }
    public int getRequestCode() {return requestCode;}

    protected void setRequestCode(int requestCode) {this.requestCode = requestCode;}

    public void addParam(String key, String value) {
         queryParams.put(key, value);
    }

    public String getBaseUrl(){
        return baseUrl;
    }

}
