package com.wikisearch.android.wikiapplication;

import android.app.Application;

import com.wikisearch.android.R;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class WikiSearchApplication extends Application {

    private static WikiSearchApplication wikiSearchApplication;

    private static int imageWidth;

    private static int imageHeight;


    @Override
    public void onCreate() {
        super.onCreate();
        wikiSearchApplication = this;

        imageWidth = getResources().getDimensionPixelSize(R.dimen.image_item_width);
        imageHeight = getResources().getDimensionPixelSize(R.dimen.image_item_height);
    }

    public static WikiSearchApplication getInstance(){
        return wikiSearchApplication;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }
}
