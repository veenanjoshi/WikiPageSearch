package com.wikisearch.android.interfaces;

import com.wikisearch.android.entity.WikiError;
import com.wikisearch.android.entity.WikiResponse;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public interface WikiListener {
    void onResponseReceived( WikiResponse wikiResponse);
    void onErrorReceived(WikiError wikiError);
}
