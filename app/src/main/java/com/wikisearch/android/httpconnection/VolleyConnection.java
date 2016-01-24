package com.wikisearch.android.httpconnection;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.wikisearch.android.wikiapplication.WikiSearchApplication;

/**
 * Created by veena.joshi on 1/23/2016.
 */
public class VolleyConnection {

    private RequestQueue requestQueue;

    private static VolleyConnection volleyConnection = new VolleyConnection();

    private VolleyConnection(){
        initRequestQueue();
    }
    public static synchronized VolleyConnection getInstance(){
        if(volleyConnection == null){
            volleyConnection = new VolleyConnection();
        }
        return volleyConnection;
    }
    private RequestQueue getRequestQueue(){
        return requestQueue;
    }

    private void initRequestQueue(){
        requestQueue = Volley.newRequestQueue(WikiSearchApplication.getInstance().getApplicationContext(), new HurlStack(), 1024*1024);
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }




}
