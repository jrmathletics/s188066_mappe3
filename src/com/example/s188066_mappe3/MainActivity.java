package com.example.s188066_mappe3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RawRes;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView wListView;
	private ListView tListView;
	private DBHelper dbHelper;
	private TextView wTextView;
	private TextView tTextView;
	private CustomCursorAdapter wCursorAdapter;
	private CustomCursorAdapter tCursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		
		run();
		
	}
	
	public void run(){
		new Handler().post(new Runnable(){			
			@Override
			public void run(){
				wCursorAdapter = new CustomCursorAdapter(MainActivity.this, dbHelper.listAllWalls());
				wListView.setAdapter(wCursorAdapter);
				tCursorAdapter = new CustomCursorAdapter(MainActivity.this, dbHelper.listAllThings());
				tListView.setAdapter(tCursorAdapter);
			}
		});
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
}
