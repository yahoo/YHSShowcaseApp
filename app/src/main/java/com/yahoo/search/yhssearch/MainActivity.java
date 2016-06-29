package com.yahoo.search.yhssearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yahoo.search.yhssdk.Constants;
import com.yahoo.search.yhssdk.ui.view.SearchActivity;

public class MainActivity extends Activity {

    private static  final int REQUEST_CODE_SEARCH = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchYHSSearch();
            }
        });
    }

    private void launchYHSSearch() {
        //Initialize sdk
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        builder.setQueryString("flower");
        builder.setTrendingCategory(Constants.TrendingCategory.CELEBRITY);
        builder.setNumberOfHistoryItems(11);
        builder.showAppSuggestions(true);
        builder.showContactSuggestions(true);
        Intent i = builder.buildIntent(this);
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
}
