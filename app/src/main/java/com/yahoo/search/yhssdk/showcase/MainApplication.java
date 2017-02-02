// Copyright 2016, Yahoo Inc
// Licensed under the terms of the BSD license. Please see LICENSE file associated with this project for terms.
package com.yahoo.search.yhssdk.showcase;

import android.app.Application;
import android.content.Context;

import com.yahoo.search.yhssdk.settings.SearchSDKSettings;


public class MainApplication extends Application {
    public static final String TAG = MainApplication.class.getSimpleName();
    public static String APP_NAME = "searchexternaldemo";
    public static String APP_VERSION = "1.0.0";
    public static String APP_HSPART = "solo";
    public static String APP_HSIMP = "yhsm-solo_001";
    public static String APP_ID = "bossValid";

    @Override
    public void onCreate() {
        super.onCreate();
        //initSearchSDK(this, APP_NAME, APP_VERSION, APP_HSPART, APP_HSIMP);
    }

    /**
     * DEVELOPER: Is important to initialize Search SDK on the Application class. This will prevent
     * Search SDK invalid states (i.e. No Factory available when the search activity is restored)
     *   
     * @param context
     * @param appName
     * @param appVersion
     */
    private void initSearchSDK(Context context,
            String appName, String appVersion, String hspart, String hsimp) {
        SearchSDKSettings.Builder builder = new SearchSDKSettings.Builder(hspart, hsimp);
        builder.setAppId(APP_ID);
    }
}
