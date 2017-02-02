package com.yahoo.search.yhssdk.showcase.custom;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.yahoo.search.yhssdk.data.SearchAssistData;
import com.yahoo.search.yhssdk.showcase.R;

/**
 * Created by surendar on 12/28/16.
 */

public class CustomSearchAssist {

    @BindingAdapter({"bind:imageDrawable"})
    public static void loadImageDrawable(ImageView view, SearchAssistData data){
        Drawable drawable = null;
        view.setVisibility(View.VISIBLE);
        switch (data.type){
            case SearchAssistData.SEARCH_APPS:
            case SearchAssistData.SEARCH_CONTACTS:
                drawable = data.icon;
                break;
            case SearchAssistData.SEARCH_TRENDING:
                drawable = view.getResources().getDrawable(R.drawable.ic_trending_up_black_24px);
                break;
            case SearchAssistData.SEARCH_HISTORY:
            case SearchAssistData.LOCAL_WEB:
                drawable = view.getResources().getDrawable(R.drawable.ic_history_black_24px);
                break;
            case SearchAssistData.SEARCH_SUGGEST:
            case SearchAssistData.SHOW_ALL_HISTORY:
            case SearchAssistData.CLEAR_HISTORY:
                view.setVisibility(View.GONE);
                break;
        }
        view.setImageDrawable(drawable);
    }
}
