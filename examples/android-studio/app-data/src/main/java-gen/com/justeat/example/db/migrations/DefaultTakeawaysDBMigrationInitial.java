/*
 * Generated by Mickey DB
 */
package com.justeat.example.db.migrations;

import android.database.sqlite.SQLiteDatabase;
import com.justeat.mickeydb.Migration;

public class DefaultTakeawaysDBMigrationInitial extends Migration {
	@Override
	public void onBeforeUp(SQLiteDatabase db) {}
	
	@Override
	public void up(SQLiteDatabase db) {
		db.execSQL(
			"create table takeaways ( " +
			"_id integer primary key autoincrement, " +
			"name text " +
			") "
		);	
	}
	
	@Override
	public void onAfterUp(SQLiteDatabase db) {}
}
