package com.justeat.mickeydb.generator

import com.justeat.mickeydb.MickeyDatabaseModel
import static extension com.justeat.mickeydb.Strings.*
import com.justeat.mickeydb.ContentUriInfo
import com.justeat.mickeydb.mickeyLang.ContentUriParamSegment
import com.justeat.mickeydb.mickeyLang.ColumnType
import static extension com.justeat.mickeydb.ModelUtil.*

class CustomActionsGenerator {
	def CharSequence generate(MickeyDatabaseModel model, ContentUriInfo content) '''
	«var snapshot = model.snapshot»

	/*
	 * Generated by Mickey DB
	 */
	package «model.packageName».actions;
	
	import com.justeat.mickeydb.ActiveRecordFactory;
	import android.content.ContentValues;
	import android.database.sqlite.SQLiteDatabase;
	import android.net.Uri;
	import com.justeat.mickeydb.CustomActions;
	import com.justeat.mickeydb.MickeyContentProvider;
	import com.justeat.mickeydb.Query;
	import «model.packageName».«model.databaseName»Contract.«content.type.pascalize»;
	import «model.packageName».Abstract«model.databaseName»OpenHelper.Sources;
	import «model.packageName».«content.type.pascalize»Record;
	import java.util.List;
	
	public class «content.name.pascalize»Actions extends CustomActions {
		
		@Override
		public void addQueryExpressionsFromUriSegmentParams(Uri uri, Query query) {
			List<String> segments = uri.getPathSegments();
			«FOR entry : content.action.uri.segments.indexed»
			«IF entry.value instanceof ContentUriParamSegment»
			«var param = entry.value as ContentUriParamSegment»
			«IF param.param.inferredColumnType != ColumnType::TEXT»
			long «param.param.name»Slug = Long.parseLong(segments.get(«entry.key»));
			«ELSE»
			String «param.param.name»Slug = segments.get(«entry.key»);
			«ENDIF» 
			«ENDIF»
			«ENDFOR»
			
			«FOR entry : content.action.uri.segments.indexed»
			«IF entry.value instanceof ContentUriParamSegment»
			«var param = entry.value as ContentUriParamSegment»
			«IF param.param.inferredColumnType != ColumnType::TEXT»
			query.expr(«content.type.pascalize».«param.param.name.underscore.toUpperCase», Query.Op.EQ, «param.param.name»Slug);
			«ELSE»
			query.expr("cast(" + «content.type.pascalize».«param.param.name.underscore.toUpperCase» + " as text)", Query.Op.EQ, «param.param.name»Slug);
			«ENDIF» 
			
			«ENDIF» 
			«ENDFOR»
		}
		
		@Override
		public String getSourceName() {
			return Sources.«content.type.underscore.toUpperCase»;
		}
		
		@Override
		public ActiveRecordFactory<?> getActiveRecordFactory() {
			return «content.type.pascalize»Record.getFactory();
		}
	}
	'''
}