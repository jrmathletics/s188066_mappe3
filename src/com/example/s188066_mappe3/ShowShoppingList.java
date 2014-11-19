package com.example.s188066_mappe3;

import java.util.List;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowShoppingList extends Activity{
	
	public List<ListItem> listitems;
	public DBHandler dBHandler;
	public ShoppingListCursorAdapter sCursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_shopping_list);
		
		dBHandler = new DBHandler(this);
		
		listitems = dBHandler.findAllListItems();
		final ListView shoppingList = (ListView)findViewById(R.id.shoppingListLV);
		ArrayAdapter<ListItem> adapter = new ArrayAdapter<ListItem>(this, android.R.layout.simple_list_item_1, listitems);
		shoppingList.setAdapter(adapter);
		shoppingList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				long productid;
				ListItem li = (ListItem) shoppingList.getItemAtPosition(position);
				productid = li.getProductID();
				System.out.println(productid);
				Intent intent = new Intent(ShowShoppingList.this, SeeProductActivity.class);
				intent.putExtra("product_id", productid);
				startActivityForResult(intent, 1);
			}
			
		});
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==1){
			setResult(1);
			finish();
		}
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
		switch (item.getItemId()) {
    // Respond to the action bar's Up/Home button
    case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    case R.id.exitApp:
    		setResult(1);
    		finish();
    default:
    		return super.onOptionsItemSelected(item);
    }
		
	}
}
