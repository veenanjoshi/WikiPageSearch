package com.wikisearch.android.manager;

import com.wikisearch.android.entity.WikiError;
import com.wikisearch.android.entity.WikiRequest;
import com.wikisearch.android.entity.WikiResponse;
import com.wikisearch.android.interfaces.DataManagerListener;
import com.wikisearch.android.interfaces.NetworkMangerListener;

import java.util.HashMap;

/**
 * Created by veena.joshi on 1/22/2016.
 */
public class DataManager implements NetworkMangerListener{

    private static DataManager dataManager = new DataManager();
    public static DataManager getInstance() {
        return dataManager;
    }

    private NetworkManager networkManager;

    private HashMap<Integer,DataManagerListener> dataManagerListenerHashMap;

    private DataManager() {
        networkManager = new NetworkManager(this);
        dataManagerListenerHashMap = new HashMap<Integer,DataManagerListener>();
    }
    public void executeNetworkService(WikiRequest request, DataManagerListener listener) {

        //cancel existing service
        cancelExistingPendingRequest(request);

        dataManagerListenerHashMap.put(request.getRequestCode(), listener);
        networkManager.executeNetworkService(request);
    }
    public void cancelExistingPendingRequest(WikiRequest request) {
        cancelNetworkService(request.getRequestCode());

    }
    private void cancelNetworkService(int serviceCode) {
        networkManager.cancelNetworkService(serviceCode);
        dataManagerListenerHashMap.remove(serviceCode);//Remove from Queue
    }

    @Override
    public void onResponseReceived(WikiResponse wikiResponse) {
        DataManagerListener listener = dataManagerListenerHashMap.get(wikiResponse.getRequestCode());
        listener.onResponseReceived(wikiResponse);
    }

    @Override
    public void onErrorReceived(WikiError wikiError) {

        DataManagerListener listener = dataManagerListenerHashMap.get(wikiError.getRequestCode());
        listener.onErrorReceived(wikiError);
    }
}
