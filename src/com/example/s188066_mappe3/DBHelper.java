package com.example.s188066_mappe3;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class DBHelper{
	
	private static final String TAG = DBHelper.class.getSimpleName();
	
	// Database config
	public static final int DATABASE_VERSION = 3;
	public static final String DATABASE_NAME = "skruevelger.db";
	
	//Wall Table config
	public static final String WALL_TABLE_NAME = "wall_table";
	public static final String WALL_TABLE_COLUMN_ID = "_id";
	public static final String WALL_TABLE_COLUMN_TYPE = "walltype";
	
	//Thing Table config
	private static final String THING_TABLE_NAME = "thing_table";
	private static final String THING_TABLE_COLUMN_ID = "_id";
	private static final String THING_TABLE_COLUMN_TYPE = "thingtype";
	
	//Product Table config
	public static final String PRODUCT_TABLE_NAME = "product_table";
	public static final String PRODUCT_TABLE_COLUMN_ID = "_id";
	public static final String PRODUCT_TABLE_COLUMN_NAME = "productname";
	public static final String PRODUCT_TABLE_COLUMN_PRICE = "price";
	
	//Shoppinglist Table config
	public static final String LIST_TABLE_NAME = "list_table";
	public static final String LIST_TABLE_COLUMN_ID = "_id";
	public static final String LIST_TABLE_COLUMN_PID = "product_id";
	
	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	
	public DBHelper(Context aContext){
		
		dbHelper = new DatabaseHelper(aContext);
		db = dbHelper.getWritableDatabase();
	}
	
	public int countListItems(){
		String count = "SELECT count(*) FROM " + LIST_TABLE_NAME;
		Cursor c = db.rawQuery(count, null);
		int counter = 0;
		if (c.moveToFirst())
		    counter = c.getInt(0);	
		return counter;
	}
	
	public int checkWallDB(){
		String count = "SELECT count(*) FROM " + WALL_TABLE_NAME;
		
		Cursor c = db.rawQuery(count, null);
		c.moveToFirst();
		int icount = c.getInt(0);
		if(icount > 0)
			return 1;
		else
		return 0;
	}
	
	public int checkThingDB(){
		String count = "SELECT count(*) FROM " + THING_TABLE_NAME;
		
		Cursor c = db.rawQuery(count, null);
		c.moveToFirst();
		int icount = c.getInt(0);
		if(icount > 0)
			return 1;
		else
		return 0;
	}
	
	public int checkProductDB(){
		String count = "SELECT count(*) FROM " + PRODUCT_TABLE_NAME;
		
		Cursor c = db.rawQuery(count, null);
		c.moveToFirst();
		int icount = c.getInt(0);
		if(icount > 0)
			return 1;
		else
		return 0;
	}
	
	public void addWall(String wType){	
		ContentValues cv = new ContentValues();
		cv.put(WALL_TABLE_COLUMN_TYPE, wType);
		
		db.insert(WALL_TABLE_NAME, null, cv);
	}
	
	public void addThing(String tType){	
		ContentValues cv = new ContentValues();
		cv.put(THING_TABLE_COLUMN_TYPE, tType);
		
		db.insert(THING_TABLE_NAME, null, cv);
	}
	
	public void addProduct(String pName, int pPrice){	
		ContentValues cv = new ContentValues();
		cv.put(PRODUCT_TABLE_COLUMN_NAME, pName);
		cv.put(PRODUCT_TABLE_COLUMN_PRICE, pPrice);
		
		db.insert(PRODUCT_TABLE_NAME, null, cv);
	}
	
	public void addListItem(int pID){
		ContentValues cv = new ContentValues();
		cv.put(LIST_TABLE_COLUMN_PID, pID);
		
		db.insert(LIST_TABLE_NAME, null, cv);
	}
	
	public Cursor listAllWalls(){
		String buildSQL = "SELECT * FROM " + WALL_TABLE_NAME;
		Log.d(TAG, "listAll SQL: " + buildSQL);
		
		return db.rawQuery(buildSQL, null);
	}
	
	public Cursor listAllThings(){
		String buildSQL = "SELECT * FROM " + THING_TABLE_NAME;
		Log.d(TAG, "listAll SQL: " + buildSQL);
		
		return db.rawQuery(buildSQL, null);
	}
	
	public Cursor listAllProducts(){
		String buildSQL = "SELECT * FROM " + PRODUCT_TABLE_NAME;
		Log.d(TAG, "listAll SQL: " + buildSQL);
		
		return db.rawQuery(buildSQL, null);
	}
	
	public Cursor listAllList(){
		String buildSQL = "SELECT * FROM " + LIST_TABLE_NAME;
		Log.d(TAG, "listAll SQL: " + buildSQL);
		
		return db.rawQuery(buildSQL, null);
	}
	/*
	public Cursor listOne(long id){
		String buildSQL = "SELECT * FROM " + PRODUCT_TABLE_NAME + " WHERE _id" + " = " + id;
		return db.rawQuery(buildSQL, null);
	}*/
	
	public int deleteOne(long id){
		db.execSQL("DELETE FROM "+ LIST_TABLE_NAME +" WHERE product_id='" + id + "'");
		return db.delete(LIST_TABLE_NAME, "_id=?", new String[]{Long.toString(id)});
	}
	/*
	public Cursor listOneText(){
		String buildSQL = "SELECT * FROM " + TEXT_TABLE_NAME;
		return db.rawQuery(buildSQL, null);
	}*/
	
	
	private class DatabaseHelper extends SQLiteOpenHelper{
		public DatabaseHelper(Context aContext){
			super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase sqliteDB){
			String buildSQL = 	"CREATE TABLE " + WALL_TABLE_NAME + "( "
								+ WALL_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, "
								+ WALL_TABLE_COLUMN_TYPE + " TEXT )";
			
			String buildSQL2 = "CREATE TABLE " + THING_TABLE_NAME + "( "
								+ THING_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, "
								+ THING_TABLE_COLUMN_TYPE + " TEXT )";

			
			String buildSQL3 = "CREATE TABLE " + PRODUCT_TABLE_NAME + "( "
								+ PRODUCT_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, "
								+ PRODUCT_TABLE_COLUMN_NAME + " TEXT, "
								+ PRODUCT_TABLE_COLUMN_PRICE + " INTEGER )";


			String buildSQL4 = "CREATE TABLE " + LIST_TABLE_NAME + "( "
								+ LIST_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, "
								+ LIST_TABLE_COLUMN_PID + " INTEGER )";
						
			Log.d(TAG, "onCreate SQL: " + buildSQL);
			Log.d(TAG, "onCreate SQL: " + buildSQL2);
			Log.d(TAG, "onCreate SQL: " + buildSQL3);
			Log.d(TAG, "onCreate SQL: " + buildSQL4);
			
			sqliteDB.execSQL(buildSQL);
			sqliteDB.execSQL(buildSQL2);
			sqliteDB.execSQL(buildSQL3);
			sqliteDB.execSQL(buildSQL4);

		}
		
		@Override
		public void onUpgrade(SQLiteDatabase sqliteDB, int oldVersion, int newVersion){
			String buildSQL = "DROP TABLE IF EXISTS " + WALL_TABLE_NAME;
			String buildSQL2 = "DROP TABLE IF EXISTS " + THING_TABLE_NAME;
			String buildSQL3 = "DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME;
			String buildSQL4 = "DROP TABLE IF EXISTS " + LIST_TABLE_NAME;
			
			
			Log.d(TAG, "onUpgrade SQL: " + buildSQL);
			Log.d(TAG, "onUpgrade SQL: " + buildSQL2);
			Log.d(TAG, "onUpgrade SQL: " + buildSQL3);
			Log.d(TAG, "onUpgrade SQL: " + buildSQL4);
			
			
			sqliteDB.execSQL(buildSQL);
			sqliteDB.execSQL(buildSQL2);
			sqliteDB.execSQL(buildSQL3);
			sqliteDB.execSQL(buildSQL4);
			
			onCreate(sqliteDB);
		}
	}
}
