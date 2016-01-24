package com.wikisearch.android.utils;

import android.content.Context;
import android.widget.Toast;

import com.wikisearch.android.entity.WikiError;

/**
 * Created by veena.joshi on 1/24/2016.
 */
public class WikiErrorHandler {

    public static void handleWikiError(Context context, WikiError wikiError){
        Toast.makeText(context, wikiError.getErrorTitle(), Toast.LENGTH_SHORT).show();
    }
}
