package com.yahoo.search.yhssearch.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yahoo.search.yhssdk.interfaces.ISearchViewHolder;
import com.yahoo.search.yhssearch.R;

/**
 * TODO: document your custom view class.
 */
public class CustomSearchBar extends LinearLayout implements ISearchViewHolder {

    private EditText mSearchBox;
    private ImageView mCancelButton;

    public CustomSearchBar(Context context) {
        super(context);
    }

    public CustomSearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSearchBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mSearchBox = (EditText) this.findViewById(R.id.searchbox);
        mCancelButton = (ImageView) this.findViewById(R.id.cancel);
    }

    @Override
    public EditText getSearchEditText() {
        return mSearchBox;
    }

    @Override
    public View getClearTextButton() {
        return mCancelButton;
    }
}
