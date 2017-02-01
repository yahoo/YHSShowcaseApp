# YHSSearch
### Android sdk for web,image and video search.

### User Guide:

The yhssdk aar file is in the [yhssdk-release module](./yhssdk-release) module.

#### Initialize SDK:
  ```
   //Set Search settings.
   Ask for your STRING_HSPART, STRING_HSIMP from Yahoo partner managers.
   SearchSDKSettings.Builder builder = new SearchSDKSettings.Builder(STRING_HSPART, STRING_HSIMP);
   builder.setAppId(STRING_APP_ID); // Optional setting to enable Trending Now (search buzz) in Search Assist tray.
   //builder.setCountry("us");//ISO 3166 alpha-2 country code. Optional setting for regional search.
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
Create a custom view class that implements **ISearchViewHolder**.
Refer [CustomSearchBar.java](./app/src/main/java/com/yahoo/search/yhssearch/custom/CustomSearchBar.java) for sample implementation in the demo app. Add this custom view to xml layout resource in your app. Pass this layout to sdk using the **SearchActivity.IntentBuilder** as below.
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
##### Custom SearchAssist:
Refer [custom_search_assist_item.xml](./app/src/main/res/layout/custom_search_assist_item.xml). Create a [Data binding layout](https://developer.android.com/topic/libraries/data-binding/index.html#data_binding_layout_files) file and pass this layout to sdk using the **SearchActivity.IntentBuilder** as below.
The data binding variables used in the layout file are described below.
  ```
    SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
    builder.setCustomSearchAssist(R.layout.custom_search_assist_item);
  ```

###### SearchAssistData
The search assist data object. Below are the list of fields that can be used in layout file.

1. **type** - Integer constant that specifies the suggestion type. Below are the different types of suggestions.

  ```
    SEARCH_HISTORY   - The suggestion is a history item from the user previous query searches.
    SEARCH_APPS      - App suggestion from the phone that matches the user query.
    SEARCH_CONTACTS  - Contact suggestion from the phone that matches the user query.
    SEARCH_SUGGEST   - Typical query suggestion
    LOCAL_WEB        - similar to SEARCH_HISTORY.
    SEARCH_TRENDING  - Trending suggestion.
    SHOW_ALL_HISTORY - Show all history.
    CLEAR_HISTORY    - Clear all history.
    SECTION_DIVIDER  - The section divider between different types of suggestions like Apps,Contacts,history etc.
  ```

2. **label** - The actual search suggestion text.
3. **icon** - The icon to be used for SEARCH_APPS and SEARCH_CONTACTS suggestion.

###### Click Event Handling
Below are the two click handlers that needs to be registered in layout.

*onAppendSuggestionItem* - callback for the query builder click event. SDK uses this callback to build the query.
*onClickAssistItem* - callback for the click of search assist item.

##### International Support:
The default Search results market is US. SDK will not auto detect region so it is the responsibility of the app to set the region using the SearchSDKSettings.Builder with corresponding ISO 3166 alpha-2 country code.
   ```
     SearchSDKSettings.Builder builder = new SearchSDKSettings.Builder(STRING_HSPART, STRING_HSIMP);
   builder.setCountry("us");
   ```