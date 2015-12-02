/**
 * 
 */
package com.patahouse;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author reuben
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_CREATE_TABLES="";
	private static Context context=null;
	private static String name="patahouse.db";
	private static SQLiteDatabase.CursorFactory factory=null;
	private static int version=1;

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	//this is the currently supported constructor
	public DatabaseHelper(Context context) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DatabaseHelper() {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table kim('buildings' integer primary key autoincrement)");
		//db.execSQL("CREATE TABLE IF NOT EXISTS 'buildings' ('_id' int(11) NOT NULL autoincrement PRIMARY KEY,'buildingName' varchar(100) NOT NULL,'owner_id' int(11) NOT NULL,'locationLatitude' double NOT NULL,'locationLongitude' double NOT NULL)" );
		//db.execSQL("CREATE TABLE IF NOT EXISTS 'owner_table' ('_id' int(11) NOT NULL PRIMARY KEY,'firstname' varchar(100) NOT NULL,'lastname' varchar(100) NOT NULL,'phone_number' int(20) NOT NULL)");
		//db.execSQL("CREATE TABLE IF NOT EXISTS 'rooms' ('_id' int(11) NOT NULL PRIMARY KEY,'roomState' tinyint(1) NOT NULL PRIMARY KEY(_id))");
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
