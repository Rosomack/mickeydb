/*
 * Generated by Mickey DB
 */
package com.example.mickeydbexample.data;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import java.util.Set;
import com.justeat.mickeydb.MickeyContentProvider;
import com.justeat.mickeydb.MickeyOpenHelper;
import com.justeat.mickeydb.DefaultContentProviderActions;
import com.justeat.mickeydb.ContentProviderActions;
import com.example.mickeydbexample.data.AbstractTakeawaysDBOpenHelper.Sources;
import com.example.mickeydbexample.data.RestaurantsRecord;

public abstract class AbstractTakeawaysDBContentProvider extends MickeyContentProvider {

	public static final int RESTAURANTS = 0;
	public static final int RESTAURANTS_ID = 1;

	
	public static final int ALL_RESTAURANTS = 2;
	public static final int ALL_RESTAURANTS_ID = 3;
	public static final int NUM_URI_MATCHERS = 4;

	@Override
    protected UriMatcher createUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = TakeawaysDBContract.CONTENT_AUTHORITY;

		matcher.addURI(authority, "restaurants", RESTAURANTS);
		matcher.addURI(authority, "restaurants/#", RESTAURANTS_ID);
		matcher.addURI(authority, "all_restaurants", ALL_RESTAURANTS);
		matcher.addURI(authority, "all_restaurants/#", ALL_RESTAURANTS_ID);

		// User Actions
        return matcher;
    }
    
    @Override
    protected String[] createContentTypes() {
		String[] contentTypes = new String[NUM_URI_MATCHERS];

		contentTypes[RESTAURANTS] = TakeawaysDBContract.Restaurants.CONTENT_TYPE;
		contentTypes[RESTAURANTS_ID] = TakeawaysDBContract.Restaurants.ITEM_CONTENT_TYPE;
		contentTypes[ALL_RESTAURANTS] = TakeawaysDBContract.AllRestaurants.CONTENT_TYPE;
		contentTypes[ALL_RESTAURANTS_ID] = TakeawaysDBContract.AllRestaurants.ITEM_CONTENT_TYPE;
		
		return contentTypes;
    }

	@Override
	protected MickeyOpenHelper createOpenHelper(Context context) {
        return new TakeawaysDBOpenHelper(context);
	}
	
	@Override
	protected Set<Uri> getRelatedUris(Uri uri) {
		return TakeawaysDBContract.REFERENCING_VIEWS.get(uri);
	}
    
    @Override
    protected ContentProviderActions createActions(int id) {
    	switch(id) {
			case RESTAURANTS: 
				return createRestaurantsActions();
			case RESTAURANTS_ID:
				return createRestaurantsByIdActions();
			case ALL_RESTAURANTS:
				return createAllRestaurantsActions();
			case ALL_RESTAURANTS_ID: 
				return createAllRestaurantsByIdActions();
			default:
				throw new UnsupportedOperationException("Unknown id: " + id);
    	}
    }
    
    protected ContentProviderActions createRestaurantsByIdActions() {
    	return new DefaultContentProviderActions(Sources.RESTAURANTS, true, RestaurantsRecord.getFactory());
    }
    
    protected ContentProviderActions createRestaurantsActions() {
    	return new DefaultContentProviderActions(Sources.RESTAURANTS, false, RestaurantsRecord.getFactory());
    }
    
    protected ContentProviderActions createAllRestaurantsByIdActions() {
    	return new DefaultContentProviderActions(Sources.ALL_RESTAURANTS, true, AllRestaurantsRecord.getFactory());
    }
    
    protected ContentProviderActions createAllRestaurantsActions() {
    	return new DefaultContentProviderActions(Sources.ALL_RESTAURANTS, false, AllRestaurantsRecord.getFactory());
    }
    
}
