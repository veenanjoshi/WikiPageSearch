package com.wikisearch.android.utils;

import android.util.Log;

/**
 * Created by veena.joshi on 1/24/2016.
 */
public class WLogger {

    private static final boolean DEBUG = true;

    private static final String TAG ="wikisearch";

    public static void d(String string){
        if( DEBUG ){
            Log.d(TAG, string);
        }
    }

    public static void v(String string){
        if( DEBUG ){
            Log.v(TAG, string);
        }
    }

    public static void i(String string){
        if( DEBUG ){
            Log.i(TAG, string);
        }
    }

    public static void e(String string){
        if( DEBUG ){
            Log.e(TAG, string);
        }
    }

}
