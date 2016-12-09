// Copyright 2016, Yahoo Inc
// Licensed under the terms of the BSD license. Please see LICENSE file associated with this project for terms.
package com.yahoo.search.yhssearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.yahoo.search.yhssdk.TrendingCategory;
import com.yahoo.search.yhssdk.data.ImageSearchResult;
import com.yahoo.search.yhssdk.data.VideoSearchResult;
import com.yahoo.search.yhssdk.data.WebSearchResult;
import com.yahoo.search.yhssdk.interfaces.ISearchResultClickListener;
import com.yahoo.search.yhssdk.settings.SearchSDKSettings;
import com.yahoo.search.yhssdk.ui.view.SearchActivity;

public class MainActivity extends Activity implements ISearchResultClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_SEARCH = 1001;
    public static final int LOCAL_HISTORY_NUM = 11;
    SearchActivity.IntentBuilder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtons();
    }

    private void setUpButtons() {
        final Button btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute(btnSearch.getId());
            }
        });

        final Button btnNoImage = (Button) findViewById(R.id.btn_noimage);
        btnNoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute(btnNoImage.getId());
            }
        });

        final Button btnNoVideo = (Button) findViewById(R.id.btn_novideo);
        btnNoVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute(btnNoVideo.getId());
            }
        });
    }

    /**
     * Init and launch the sdk in background task.
     */
    public class BackgroundTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            initSearchSDK(MainActivity.this, "searchexternaldemo", "1.0.0", "solo", "yhsm-solo_001");
            InitializeSearchBuilder();
            switch ((int)params[0]) {
                case R.id.btn_search : launchYHSSearch(); break;
                case R.id.btn_noimage : mBuilder.enableImageSearch(false);
                    launchYHSSearch(); break;
                case R.id.btn_novideo : mBuilder.enableVideoSearch(false);
                    launchYHSSearch(); break;
                default: break;
            }
            return null;
        }
    }

    private void initSearchSDK(Context context,
                               String appName, String appVersion, String hspart, String hsimp) {
        SearchSDKSettings.Builder builder = new SearchSDKSettings.Builder(hspart, hsimp);
        builder.setAppId("bossValid");
    }

    private void InitializeSearchBuilder() {
        mBuilder = new SearchActivity.IntentBuilder();
        mBuilder.setQueryString("flower");//optional
        mBuilder.setTrendingCategory(TrendingCategory.CELEBRITY);
        mBuilder.setNumberOfHistoryItems(LOCAL_HISTORY_NUM);
        mBuilder.showAppSuggestions(true);
        mBuilder.showContactSuggestions(true);
//        mBuilder.setCustomSearchBar(R.layout.custom_search_bar);
        mBuilder.setSearchResultClickListener(this);//If developers want to handle search result click.
    }

    private void launchYHSSearch() {
        /**
         * To disable image/video verticals. Web is mandatory for now.
         */
        //mBuilder.enableImageSearch(false);
        //mBuilder.enableVideoSearch(false);
        Intent i = mBuilder.buildIntent(this);
        startActivityForResult(i, REQUEST_CODE_SEARCH);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWebResultClicked(WebSearchResult webData) {
        Log.d(TAG, "Search share data returned:"  + webData.getUrl());
    }

    @Override
    public void onImageResultClicked(ImageSearchResult imageData) {

    }

    @Override
    public void onVideoResultClicked(VideoSearchResult videoData) {
        Log.d(TAG, "Search share data returned:"  + videoData.getUrl());
    }
}
