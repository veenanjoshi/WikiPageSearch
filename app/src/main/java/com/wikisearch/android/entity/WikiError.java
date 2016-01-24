package com.wikisearch.android.entity;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public class WikiError {
    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    private int requestCode;

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    private String errorTitle;
}
