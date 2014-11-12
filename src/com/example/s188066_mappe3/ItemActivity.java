package com.example.s188066_mappe3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends Activity implements OnClickListener{

	private TextView wallIDTV, thingIDTV, productNameTV, productPriceTV;
	private DBHelper dBHelper;
	private String productName, productPrice;
	private Button addProduct, showShoppingList;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		
		wallIDTV = (TextView)findViewById(R.id.wallIdTV);
		thingIDTV = (TextView)findViewById(R.id.thingIdTV);
		productNameTV = (TextView)findViewById(R.id.productNameTV);
		productPriceTV = (TextView)findViewById(R.id.productPriceTV);
		dBHelper = new DBHelper(this);
		
		addProduct = (Button)findViewById(R.id.addProductButton);
		addProduct.setOnClickListener(this);
		showShoppingList = (Button)findViewById(R.id.showShoppingListButton);
		showShoppingList.setOnClickListener(this);
		
		productName = "";
		productPrice = "";

		
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		long thingId = b.getLong("thing_id");
		long wallId = b.getLong("wall_id");
		

		
		String wallIdText = Long.toString(wallId);
		String thingIdText = Long.toString(thingId);
		
		wallIDTV.setText(wallIdText);
		thingIDTV.setText(thingIdText);
		
		Cursor c = dBHelper.listOne(wallId, thingId);
		if(c.moveToFirst()){
			do{
				productNameTV.setText(c.getString(1));
				productPriceTV.setText(c.getString(2));
				productName = c.getString(1);
				productPrice = c.getString(2);
			}while(c.moveToNext());
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item, menu);
		return true;
	}
	
	public void addProductToList(){
		
		int productPriceInt = Integer.parseInt(productPrice);
		dBHelper.addListItem(productName, productPriceInt);
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

	@Override
	public void onClick(View v){
		Context context = getApplicationContext();

		CharSequence productAdded = getString(R.string.productAddedText);
		int duration = Toast.LENGTH_SHORT;
		Toast productToast = Toast.makeText(context, productAdded, duration);

		if(v.getId() == R.id.addProductButton)
		{
			addProductToList();
			productToast.show();
		}
		else if(v.getId() == R.id.showShoppingListButton)
		{
			Intent startIntent = new Intent(this, ShowShoppingList.class);
			this.startActivityForResult(startIntent, 1);

		}
	}
}
