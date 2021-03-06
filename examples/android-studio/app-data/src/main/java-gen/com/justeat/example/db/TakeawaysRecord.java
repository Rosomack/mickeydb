/*
 * Generated by Mickey DB
 */
package com.justeat.example.db;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.net.Uri;
import com.justeat.example.db.TakeawaysDBContract.Takeaways;
import com.justeat.example.db.TakeawaysDBContract.Takeaways.Builder;
import com.justeat.mickeydb.util.Closeables;
import com.justeat.mickeydb.ActiveRecord;
import com.justeat.mickeydb.ActiveRecordFactory;
import com.justeat.mickeydb.Mickey;
import com.justeat.mickeydb.AbstractValuesBuilder;

public class TakeawaysRecord extends ActiveRecord implements Parcelable {

	private static ActiveRecordFactory<TakeawaysRecord> sFactory = new ActiveRecordFactory<TakeawaysRecord>() {
		@Override
		public TakeawaysRecord create(Cursor c) {
			return fromCursor(c);
		}
		
		@Override
		public String[] getProjection() {
			return PROJECTION;
		}

        @Override
		public Uri getContentUri() {
		    return Takeaways.CONTENT_URI;
		}
	};

public static ActiveRecordFactory<TakeawaysRecord> getFactory() {
		return sFactory;
	}

    public static final Parcelable.Creator<TakeawaysRecord> CREATOR 
    	= new Parcelable.Creator<TakeawaysRecord>() {
        public TakeawaysRecord createFromParcel(Parcel in) {
            return new TakeawaysRecord(in);
        }

        public TakeawaysRecord[] newArray(int size) {
            return new TakeawaysRecord[size];
        }
    };
    
    public static String[] PROJECTION = {
    	Takeaways._ID,
    	Takeaways.NAME
    };
    
    public interface Indices {
    	int _ID = 0;
    	int NAME = 1;
    }
    
    private String mName;
    private boolean mNameDirty;
    
    @Override
    protected String[] _getProjection() {
    	return PROJECTION;
    }
    
    public void setName(String name) {
    	mName = name;
    	mNameDirty = true;
    }
    
    public String getName() {
    	return mName;
    }
    
    
    public TakeawaysRecord() {
    	super(Takeaways.CONTENT_URI);
	}
	
	private TakeawaysRecord(Parcel in) {
    	super(Takeaways.CONTENT_URI);
    	
		setId(in.readLong());
		
		mName = in.readString();
		
		boolean[] dirtyFlags = new boolean[1];
		in.readBooleanArray(dirtyFlags);
		mNameDirty = dirtyFlags[0];
	}
	
	@Override
	public int describeContents() {
	    return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(mName);
		dest.writeBooleanArray(new boolean[] {
			mNameDirty
		});
	}
	
	@Override
	protected AbstractValuesBuilder createBuilder() {
		Builder builder = Takeaways.newBuilder();

		if(mNameDirty) {
			builder.setName(mName);
		}
		
		return builder;
	}
	
    @Override
	public void makeDirty(boolean dirty){
		mNameDirty = dirty;
	}

	@Override
	protected void setPropertiesFromCursor(Cursor c) {
		setId(c.getLong(Indices._ID));
		setName(c.getString(Indices.NAME));
	}
	
	public static TakeawaysRecord fromCursor(Cursor c) {
	    TakeawaysRecord item = new TakeawaysRecord();
	    
		item.setPropertiesFromCursor(c);
		
		item.makeDirty(false);
		
	    return item;
	}
	
	public static TakeawaysRecord fromBundle(Bundle bundle, String key) {
		bundle.setClassLoader(TakeawaysRecord.class.getClassLoader());
		return bundle.getParcelable(key);
	}
	
	public static TakeawaysRecord get(long id) {
	    Cursor c = null;
	    
	    ContentResolver resolver = Mickey.getContentResolver();
	    
	    try {
	        c = resolver.query(Takeaways.CONTENT_URI.buildUpon()
			.appendPath(String.valueOf(id)).build(), PROJECTION, null, null, null);
	        
	        if(!c.moveToFirst()) {
	            return null;
	        }
	        
	        return fromCursor(c);
	    } finally {
	        Closeables.closeSilently(c);
	    }
	}
}
