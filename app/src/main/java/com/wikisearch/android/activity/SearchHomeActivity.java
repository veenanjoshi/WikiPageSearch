package com.wikisearch.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.wikisearch.android.R;
import com.wikisearch.android.adapters.WikiImageListRecyclerViewAdapter;
import com.wikisearch.android.entity.WikiError;
import com.wikisearch.android.entity.WikiRequest;
import com.wikisearch.android.entity.WikiResponse;
import com.wikisearch.android.entity.WikiServiceCodes;
import com.wikisearch.android.interfaces.DataManagerListener;
import com.wikisearch.android.manager.DataManager;
import com.wikisearch.android.utils.WLogger;
import com.wikisearch.android.utils.WikiErrorHandler;
import com.wikisearch.android.wikiservice.WikiConstants;
import com.wikisearch.android.wikiservice.WikiRequestFactory;
import com.wikisearch.android.wikiservice.pagesearch.WikiPageSearchResponse;

import java.util.Timer;
import java.util.TimerTask;

public class SearchHomeActivity extends AppCompatActivity implements DataManagerListener {

    private Timer timer = new Timer();

    private final long DELAY = 1000;

    private EditText searchText;

    private String lastSearchToken;

//    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager recyclerLayoutManager;

    private WikiImageListRecyclerViewAdapter recyclerViewAdapter;

    private ProgressBar progressBar;

    private WikiRequest wikiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home);
        setSearchTextListener();
        initRecyclerView();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);

        recyclerViewAdapter = new WikiImageListRecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
        if(wikiRequest != null){
            WLogger.d(getString(R.string.wiki_request_canceled));
            hideProgressBar();
            DataManager.getInstance().cancelExistingPendingRequest(wikiRequest);
        }

    }

    private void executeNetworkService() {
        String searchToken = searchText.getText().toString().trim();
        if (searchToken.length() > 0 && !searchToken.equalsIgnoreCase(lastSearchToken)) {
            startNewSearchRequest(searchToken);
        }
    }

    private void startNewSearchRequest(String searchToken) {

        WLogger.d(getString(R.string.wiki_new_request)+searchToken);
        hideKeyboard();
        showProgressBar();
        recyclerViewAdapter.clearWikiPageList();
        //Execute service if user has typed something and is not same as that of last search
        wikiRequest= WikiRequestFactory.getWikiRequest(WikiServiceCodes.WIKI_PAGE_SEARCH_SERVICE);
        wikiRequest.addParam(WikiConstants.WIKI_PARAM_KEY_GPSSEARCH, searchToken);
        DataManager.getInstance().executeNetworkService(wikiRequest, this);
        lastSearchToken = searchToken;
    }

    private void hideKeyboard(){
        InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
    }
    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResponseReceived(final WikiResponse wikiResponse) {
        runOnUiThread( new Thread(new Runnable() {
            @Override
            public void run() {
                if (wikiResponse != null) {
                    recyclerViewAdapter.setWikiPageList(((WikiPageSearchResponse) wikiResponse).getWikiBaseObjectList());
                    recyclerViewAdapter.notifyDataSetChanged();
                    hideProgressBar();
                }
            }
        }));

    }

    @Override
    public void onErrorReceived(final WikiError wikiError) {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {

                hideProgressBar();
                WikiErrorHandler.handleWikiError(SearchHomeActivity.this, wikiError);
            }
        }));

    }


    private void setSearchTextListener() {
        searchText = (EditText) findViewById(R.id.search_editview);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, int start, int before,
                                          int count) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
                if (timer != null)
                    timer.cancel();
            }

            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is too short
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Thread(new Runnable() {
                            @Override
                            public void run() {
                                executeNetworkService();
                            }
                        }));
                    }

                }, DELAY);
            }

        });
    }

}
