# YHSSearch
### Android sdk for web,image and video search.

### User Guide:

Place the yhssdk aar file in the [yhssdk-release module](./yhssdk-release). Refer to [build.gradle](./yhssdk-release/build.gradle)

#### Initialize SDK:
  ```
   //Set Search settings.
   SearchSDKSettings.Builder builder = new SearchSDKSettings.Builder(STRING_HSPART, STRING_HSIMP);
   builder.setAppId(STRING_APP_ID); // Optional setting to enable Trending Now (search buzz) in Search Assist tray.

   static  final int REQUEST_CODE_SEARCH = 1001;
   static final int LOCAL_HISTORY_NUM = 11;

   //Setup SearchActivity Builder.
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        //builder.setQueryString("flower"); //Optional pre-query.
        builder.setTrendingCategory(TrendingCategory.CELEBRITY);
        builder.setNumberOfHistoryItems(LOCAL_HISTORY_NUM);
        builder.showAppSuggestions(true);
        builder.showContactSuggestions(true);
        //builder.setSearchResultClickListener(this);//If developers want to handle search result click.
        /**
         * To disable image/video verticals. Web is mandatory for now.
         */
        //builder.enableImageSearch(false);
        //builder.enableVideoSearch(false);
        Intent i = builder.buildIntent(this);
        startActivityForResult(i, REQUEST_CODE_SEARCH);
  ```
#### Customize:

##### Custom Search Bar:
Set your custom bar layout resource using the **SearchActivity.IntentBuilder** as below. The custom layout should implement **ISearchViewHolder**.
Refer [CustomSearchBar.java](./app/src/main/java/com/yahoo/search/yhssearch/custom/CustomSearchBar.java) for sample implementation in the demo app.

  ```
    SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
    builder.setCustomSearchBar(R.layout.custom_search_bar);
  ```
##### Custom TabLayout style :
The TabLayout for verticals can be customized by overriding the style - **Yhssdk_TabLayout**.
Refer [styles.xml](./app/src/main/res/values/styles.xml) in the demo app. All the xml attributes of [TabLayout](https://developer.android.com/reference/android/support/design/widget/TabLayout.html) are supported.

  ```
    <style name="Yhssdk_TabLayout" parent="Base.Widget.Design.TabLayout">
        <item name="tabIndicatorColor">@color/button_material_light</item>
    </style>
  ```