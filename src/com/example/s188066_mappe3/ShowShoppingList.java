package com.example.s188066_mappe3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ShowShoppingList extends Activity {
	
	ListView shoppingList;
	DBHelper dBHelper;
	ShoppingListCursorAdapter sCursorAdapter;
	private AlertDialog deleteDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_shopping_list);
		
		dBHelper = new DBHelper(this);
		
		shoppingList = (ListView)findViewById(R.id.shoppingListLV);
		
		run();
	}
	
	public void run(){
		new Handler().post(new Runnable(){			
			@Override
			public void run(){
				sCursorAdapter = new ShoppingListCursorAdapter(ShowShoppingList.this, dBHelper.listAllList());
				shoppingList.setAdapter(sCursorAdapter);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_shopping_list, menu);
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
