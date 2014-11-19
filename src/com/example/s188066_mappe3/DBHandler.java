package com.example.s188066_mappe3;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "skruevelgernDB.db";

	public static final String WALL_TABLE_NAME = "walls";
	public static final String WALL_COLUMN_ID = "_id";
	public static final String WALL_COLUMN_WALLTYPE = "walltype";

	private static final String ITEM_TABLE_NAME = "items";
	private static final String ITEM_TABLE_COLUMN_ID = "_id";
	private static final String ITEM_TABLE_COLUMN_TYPE = "itemtype";

	public static final String PRODUCT_TABLE_NAME = "products";
	public static final String PRODUCT_TABLE_COLUMN_ID = "_id";
	public static final String PRODUCT_TABLE_COLUMN_NAME = "productname";
	public static final String PRODUCT_TABLE_COLUMN_INFO = "productinfo";
	public static final String PRODUCT_TABLE_COLUMN_PRICE = "price";
	public static final String PRODUCT_TABLE_COLUMN_WID = "wall_id";
	public static final String PRODUCT_TABLE_COLUMN_TID = "thing_id";

	public static final String LIST_TABLE_NAME = "list";
	public static final String LIST_TABLE_COLUMN_ID = "_id";
	public static final String LIST_TABLE_COLUMN_NAME = "productname";
	public static final String LIST_TABLE_COLUMN_PRICE = "price";
	private SQLiteDatabase db;

	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public DBHandler open() throws SQLException{
		db = this.getWritableDatabase();
		return this;
	}

	public void addWall(Wall wall) {
		open();
		ContentValues values = new ContentValues();
		values.put(WALL_COLUMN_WALLTYPE, wall.getWalltype());
		db.insert(WALL_TABLE_NAME, null, values);
		db.close();
	}

	public List<Wall> findAllWalls() {
		List<Wall> wallList = new ArrayList<Wall>();
		String selectQuery = "SELECT * FROM " + WALL_TABLE_NAME;
		open();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Wall wall = new Wall();
				wall.setId(Integer.parseInt(cursor.getString(0)));
				wall.setWalltype(cursor.getString(1));
				wallList.add(wall);
			} while (cursor.moveToNext());
		}
		db.close();
		return wallList;
	}

	public void addItem(Item item) {
		open();
		ContentValues values = new ContentValues();
		values.put(ITEM_TABLE_COLUMN_TYPE, item.getItemtype());
		db.insert(ITEM_TABLE_NAME, null, values);
		db.close();
	}
	
	public List<Item> findAllItems() {
		List<Item> itemList = new ArrayList<Item>();
		String selectQuery = "SELECT * FROM " + ITEM_TABLE_NAME;
		open();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Item item = new Item();
				item.setId(Integer.parseInt(cursor.getString(0)));
				item.setItemtype(cursor.getString(1));
				itemList.add(item);
			} while (cursor.moveToNext());
		}
		db.close();
		return itemList;
	}

	public void addProduct(Product product) {
		open();
		ContentValues values = new ContentValues();
		values.put(PRODUCT_TABLE_COLUMN_INFO, product.getProductinfo());
		values.put(PRODUCT_TABLE_COLUMN_NAME, product.getProductname());
		values.put(PRODUCT_TABLE_COLUMN_PRICE, product.getPrice());
		values.put(PRODUCT_TABLE_COLUMN_WID, product.getWallid());
		values.put(PRODUCT_TABLE_COLUMN_TID, product.getThingid());
		db.insert(PRODUCT_TABLE_NAME, null, values);
		db.close();
	}

	public void addListItem(ListItem listitem) {
		open();
		ContentValues values = new ContentValues();
		values.put(LIST_TABLE_COLUMN_NAME, listitem.getListItemname());
		values.put(LIST_TABLE_COLUMN_PRICE, listitem.getPrice());
		db.insert(LIST_TABLE_NAME, null, values);
		db.close();
	}
	
	public List<ListItem> findAllListItems() {
		List<ListItem> listItemList = new ArrayList<ListItem>();
		String selectQuery = "SELECT * FROM " + LIST_TABLE_NAME;
		open();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				ListItem listitem = new ListItem();
				listitem.setId(Integer.parseInt(cursor.getString(0)));
				listitem.setListItemname(cursor.getString(1));
				listitem.setPrice(Integer.parseInt(cursor.getString(2)));
				listItemList.add(listitem);
			} while (cursor.moveToNext());
		}
		db.close();
		return listItemList;
	}
	
	public int checkWallDB(){
		String count = "SELECT count(*) FROM " + WALL_TABLE_NAME;
		open();

		Cursor c = db.rawQuery(count, null);
		c.moveToFirst();
		int icount = c.getInt(0);
		if(icount > 0)
			return 1;
		else
		return 0;
	}
	
	public int checkItemDB(){
		String count = "SELECT count(*) FROM " + ITEM_TABLE_NAME;
		open();
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
		open();
		Cursor c = db.rawQuery(count, null);
		c.moveToFirst();
		int icount = c.getInt(0);
		if(icount > 0)
			return 1;
		else
		return 0;
	}
	
	public Product findProduct(long wID, long tID){
		open();
		Cursor cursor = db.query(PRODUCT_TABLE_NAME, new String[] { PRODUCT_TABLE_COLUMN_ID, PRODUCT_TABLE_COLUMN_NAME,
				PRODUCT_TABLE_COLUMN_INFO, PRODUCT_TABLE_COLUMN_PRICE, PRODUCT_TABLE_COLUMN_WID,
				PRODUCT_TABLE_COLUMN_TID}, PRODUCT_TABLE_COLUMN_WID + "=?" + " AND " +
				PRODUCT_TABLE_COLUMN_TID + "=?", new String[]{String.valueOf(wID), String.valueOf(tID)}, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		Product product = new Product(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), 
				Integer.parseInt(cursor.getString(3)), 
				Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
	
		return product;
	
	}
	
	public int countListItems(){
		String count = "SELECT count(*) FROM " + LIST_TABLE_NAME;
		open();
		Cursor c = db.rawQuery(count, null);
		int counter = 0;
		if (c.moveToFirst())
		    counter = c.getInt(0);	
		return counter;
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_WALLS_TABLE = "CREATE TABLE " + WALL_TABLE_NAME + "("
				+ WALL_COLUMN_ID + " INTEGER PRIMARY KEY,"
				+ WALL_COLUMN_WALLTYPE + " TEXT" + ")";

		String CREATE_ITEMS_TABLE = "CREATE TABLE " + ITEM_TABLE_NAME + "("
				+ ITEM_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY,"
				+ ITEM_TABLE_COLUMN_TYPE + " TEXT" + ")";

		String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + PRODUCT_TABLE_NAME
				+ "( " + PRODUCT_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, "
				+ PRODUCT_TABLE_COLUMN_NAME + " TEXT, "
				+ PRODUCT_TABLE_COLUMN_INFO + " TEXT, "
				+ PRODUCT_TABLE_COLUMN_PRICE + " INTEGER, "
				+ PRODUCT_TABLE_COLUMN_WID + " INTEGER, "
				+ PRODUCT_TABLE_COLUMN_TID + " INTEGER )";

		String CREATE_LIST_TABLE = "CREATE TABLE " + LIST_TABLE_NAME + "( "
				+ LIST_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, "
				+ LIST_TABLE_COLUMN_NAME + " TEXT, " + LIST_TABLE_COLUMN_PRICE
				+ " INTEGER )";

		Log.d("SQL", CREATE_PRODUCTS_TABLE);
		Log.d("SQL", CREATE_WALLS_TABLE);
		Log.d("SQL", CREATE_ITEMS_TABLE);
		Log.d("SQL", CREATE_LIST_TABLE);
		db.execSQL(CREATE_PRODUCTS_TABLE);
		db.execSQL(CREATE_ITEMS_TABLE);
		db.execSQL(CREATE_WALLS_TABLE);
		db.execSQL(CREATE_LIST_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + WALL_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE_NAME);

		onCreate(db);
	}
}