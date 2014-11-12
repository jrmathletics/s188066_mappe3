package com.example.s188066_mappe3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private ListView wListView,tListView;
	private DBHelper dbHelper;
	private TextView wTextView,tTextView;
	private long wallID, thingID;
	private WallCursorAdapter wCursorAdapter;
	private ThingCursorAdapter tCursorAdapter;
	private Button setChoices, showShoppingList;
	private static final String TAG = MainActivity.class.getSimpleName();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wallID = 0;
		thingID = 0;
		
		setChoices = (Button)findViewById(R.id.setChoicesButton);
		setChoices.setVisibility(View.INVISIBLE);
		setChoices.setOnClickListener(this);
		
		showShoppingList = (Button)findViewById(R.id.showShoppingListButton);
		showShoppingList.setOnClickListener(this);
		
		dbHelper = new DBHelper(this);
		if(dbHelper.checkWallDB() == 0)
		{
			dbHelper.addWall(getResources().getString(R.string.bWallText));
			dbHelper.addWall(getResources().getString(R.string.thWallText));
			dbHelper.addWall(getResources().getString(R.string.llWallText));
			dbHelper.addWall(getResources().getString(R.string.gWallText));
			dbHelper.addWall(getResources().getString(R.string.sWallText));
			
		}
		if(dbHelper.checkProductDB() == 0)
		{
			dbHelper.addProduct(getResources().getString(R.string.productButterflyText), 39, 5, 1);
			dbHelper.addProduct(getResources().getString(R.string.productButterflyText), 39, 4, 1);
			dbHelper.addProduct(getResources().getString(R.string.productDrywallAnchorText), 49, 4, 2);
			dbHelper.addProduct(getResources().getString(R.string.productExpansionText), 59, 4, 4);
			dbHelper.addProduct(getResources().getString(R.string.productExpansionText), 59, 4, 5);
			dbHelper.addProduct(getResources().getString(R.string.productHerculesText), 19, 4, 3);
			dbHelper.addProduct(getResources().getString(R.string.productNylonPCHText), 29, 1, 1);
			dbHelper.addProduct(getResources().getString(R.string.productNylonPSText), 45, 1, 2);
			dbHelper.addProduct(getResources().getString(R.string.productNylonPSText), 45, 1, 4);
			dbHelper.addProduct(getResources().getString(R.string.productNylonPSText), 45, 1, 5);
			dbHelper.addProduct(getResources().getString(R.string.productNylonPSText), 45, 2, 5);
			dbHelper.addProduct(getResources().getString(R.string.productNylonPSText), 45, 3, 5);
			dbHelper.addProduct(getResources().getString(R.string.productPictureHookText), 17, 2, 3);
			dbHelper.addProduct(getResources().getString(R.string.productPictureHookText), 17, 3, 3);
			dbHelper.addProduct(getResources().getString(R.string.productPictureHookText), 17, 5, 3);
			dbHelper.addProduct(getResources().getString(R.string.productPlasticHardWallText), 27, 1, 3);
			dbHelper.addProduct(getResources().getString(R.string.productScrewText), 9, 5, 2);
			dbHelper.addProduct(getResources().getString(R.string.productScrewText), 9, 5, 4);
			dbHelper.addProduct(getResources().getString(R.string.productScrewText), 9, 5, 5);
			dbHelper.addProduct(getResources().getString(R.string.productUniversalPCHText), 29, 2, 1);
			dbHelper.addProduct(getResources().getString(R.string.productUniversalPCHText), 29, 3, 1);
			dbHelper.addProduct(getResources().getString(R.string.productUniversalPSText), 39, 2, 2);			
			dbHelper.addProduct(getResources().getString(R.string.productUniversalPSText), 39, 3, 2);		
			dbHelper.addProduct(getResources().getString(R.string.productUniversalPSText), 39, 2, 4);		
			dbHelper.addProduct(getResources().getString(R.string.productUniversalPSText), 39, 3, 4);	
		}
		if(dbHelper.checkThingDB() == 0)
		{
			dbHelper.addThing(getResources().getString(R.string.lampText));
			dbHelper.addThing(getResources().getString(R.string.curtainText));
			dbHelper.addThing(getResources().getString(R.string.sMirrorFrameText));
			dbHelper.addThing(getResources().getString(R.string.bMirrorFrameText));
			dbHelper.addThing(getResources().getString(R.string.closetText));
		}
		
		wListView = (ListView)findViewById(R.id.wallLV);
		tListView = (ListView)findViewById(R.id.thingLV);
		lockThingListView();
		wListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				wallID = id;
				unlockThingListView();
			}
		});
		tListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				thingID = id;
				setChoices.setVisibility(View.VISIBLE);
			}
		});
		
		run();
		
	}
	
	public void run(){
		new Handler().post(new Runnable(){			
			@Override
			public void run(){
				wCursorAdapter = new WallCursorAdapter(MainActivity.this, dbHelper.listAllWalls());
				wListView.setAdapter(wCursorAdapter);
				tCursorAdapter = new ThingCursorAdapter(MainActivity.this, dbHelper.listAllThings());
				tListView.setAdapter(tCursorAdapter);
			}
		});
	}
	
	public void lockThingListView(){
		tListView.setAlpha(75);
		tListView.setBackgroundColor(Color.GRAY);
		tListView.setEnabled(false);
	}
	
	public void unlockThingListView(){		
		tListView.setAlpha(255); 
		tListView.setBackgroundColor(this.getTitleColor());
		tListView.setEnabled(true);

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==1){
			finish();
		}
	}
	
	public void onClick(View v){
		if(v.getId() == R.id.setChoicesButton)
		{
			Intent startIntent = new Intent(this, ItemActivity.class);
			startIntent.putExtra("thing_id", thingID);
			startIntent.putExtra("wall_id", wallID);
			this.startActivityForResult(startIntent, 1);
		}
		else if (v.getId() == R.id.showShoppingListButton)
		{
			Intent startIntent = new Intent(this, ShowShoppingList.class);
			this.startActivityForResult(startIntent, 1);
		}
	}
	
}
