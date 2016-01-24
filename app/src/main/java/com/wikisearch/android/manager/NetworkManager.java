package com.wikisearch.android.manager;

import com.wikisearch.android.entity.WikiError;
import com.wikisearch.android.entity.WikiRequest;
import com.wikisearch.android.entity.WikiResponse;
import com.wikisearch.android.interfaces.NetworkMangerListener;
import com.wikisearch.android.wikiservice.WikiService;
import com.wikisearch.android.wikiservice.WikiServiceFactory;
import com.wikisearch.android.wikiservice.WikiServiceListener;

import java.util.HashMap;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public class NetworkManager implements WikiServiceListener {
    private NetworkMangerListener networkMangerListener;
    private HashMap<Integer,WikiService> wikiServiceRequestMap;

    public NetworkManager(NetworkMangerListener listener) {
        this.networkMangerListener = listener;
        wikiServiceRequestMap = new HashMap<Integer,WikiService>();
    }

    public void executeNetworkService(WikiRequest wikiRequest) {
        WikiService wikiService = WikiServiceFactory.getWebService(wikiRequest.getRequestCode());
        wikiService.setListener(this);
        wikiServiceRequestMap.put(wikiRequest.getRequestCode(), wikiService);//Add this in queue
        wikiService.execute(wikiRequest);

    }

    public void cancelNetworkService(int serviceCode) {
        WikiService cancelWebService = wikiServiceRequestMap.get(serviceCode);
        if(cancelWebService != null) {
            cancelWebService.cancel();
            wikiServiceRequestMap.remove(serviceCode);//remove from Queue
        }
    }

    @Override
    public void onResponseReceived(WikiResponse wikiResponse) {
        networkMangerListener.onResponseReceived(wikiResponse);
    }

    @Override
    public void onErrorReceived(WikiError wikiError) {
        networkMangerListener.onErrorReceived(wikiError);
    }
}

