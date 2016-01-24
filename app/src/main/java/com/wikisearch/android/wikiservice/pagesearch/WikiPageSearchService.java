package com.wikisearch.android.wikiservice.pagesearch;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wikisearch.android.R;
import com.wikisearch.android.entity.WikiError;
import com.wikisearch.android.entity.WikiRequest;
import com.wikisearch.android.entity.WikiResponse;
import com.wikisearch.android.httpconnection.VolleyConnection;
import com.wikisearch.android.utils.WLogger;
import com.wikisearch.android.wikiapplication.WikiSearchApplication;
import com.wikisearch.android.wikiservice.WikiConstants;
import com.wikisearch.android.wikiservice.WikiParser;
import com.wikisearch.android.wikiservice.WikiParserFactory;
import com.wikisearch.android.wikiservice.WikiService;
import com.wikisearch.android.wikiservice.WikiServiceListener;
import com.wikisearch.android.utils.WikiUtils;

import org.json.JSONObject;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public class WikiPageSearchService extends WikiService {

    private WikiServiceListener wikiServiceListener;

    private JsonObjectRequest request;

    public WikiPageSearchService() {
    }

    @Override
    public void setListener(WikiServiceListener listener) {
        wikiServiceListener = listener;
    }

    public void execute(final WikiRequest wikiRequest) {

        if(WikiUtils.isNetworkConnected(WikiSearchApplication.getInstance().getApplicationContext())) {

            request = new JsonObjectRequest(
                    WikiUtils.createUrlFromRequest(wikiRequest),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            WLogger.d(response.toString());
                            WikiParser parser = WikiParserFactory.getParser(wikiRequest.getRequestCode());
                            WikiResponse wikiResponse = parser.parseResponse(response);
                            if (wikiResponse == null) {
                                WikiError error = WikiUtils.createWikiError(WikiConstants.WIKI_ERROR_NO_IMAGES, wikiRequest.getRequestCode());
                                wikiServiceListener.onErrorReceived(error);
                            } else {
                                wikiServiceListener.onResponseReceived(wikiResponse);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            wikiServiceListener.onErrorReceived(WikiUtils.createWikiError(WikiConstants.WIKI_ERROR_NO_IMAGES, wikiRequest.getRequestCode()));
                        }
                    }

            );

            VolleyConnection.getInstance().addToRequestQueue(request);

        }
        else{
            wikiServiceListener.onErrorReceived(WikiUtils.createWikiError(WikiConstants.WIKI_NETWORK_ERROR, wikiRequest.getRequestCode()));
        }

    }

    @Override
    public void cancel() {

        if(request != null && !request.isCanceled()){
            WLogger.d(WikiSearchApplication.getInstance().getString(R.string.wiki_request_canceled));
            request.cancel();
        }
    }
}
