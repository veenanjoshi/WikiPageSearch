package com.wikisearch.android.wikiservice;

import com.wikisearch.android.entity.WikiRequest;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public abstract class WikiService{
    private WikiServiceListener listener;

    public WikiService() {

    }
    public void setListener(WikiServiceListener listener) {
        this.listener = listener;
    }
    public abstract void execute(WikiRequest serviceRequestObject) ;

    public abstract void cancel();

}
