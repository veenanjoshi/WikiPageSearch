package com.wikisearch.android.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wikisearch.android.R;
import com.wikisearch.android.entity.WikiError;
import com.wikisearch.android.entity.WikiRequest;
import com.wikisearch.android.wikiapplication.WikiSearchApplication;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiUtils {

    public static String createUrlFromRequest(WikiRequest wikiRequest) {

        StringBuilder buf = null;
        if (wikiRequest != null) {

            String baseUrl = wikiRequest.getBaseUrl();

            ContentValues params = wikiRequest.getQueryParams();

            buf = new StringBuilder(baseUrl);
            for(String key : params.keySet()){
                buf.append("&");

                buf.append(key).append("=").append(params.get(key));
            }

            WLogger.d(WikiSearchApplication.getInstance().getString(R.string.wiki_request_url)+buf.toString());
        }

        return ((buf != null) ? buf.toString() : null);
    }

    public static WikiError createWikiError(String errorMsg, int requestCode){

        WikiError wikiError = new WikiError();

        wikiError.setErrorTitle(errorMsg);
        wikiError.setRequestCode(requestCode);
        return wikiError;
    }

    public static boolean isNetworkConnected(Context context){
        boolean isConnected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                isConnected = true;
            }

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                isConnected = true;
            }
        }
        return isConnected;
    }
}
