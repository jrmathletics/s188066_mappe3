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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends Activity implements OnClickListener{

	private TextView wallIDTV, itemIDTV, productNameTV, productInfoTV, productPriceTV;
	private DBHandler dBHandler;
	private String productName, productPrice, productInfo;
	private Button addProduct;
	private ImageView productImage;
	public long thingId, wallId;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		
		wallIDTV = (TextView)findViewById(R.id.wallIdTV);
		itemIDTV = (TextView)findViewById(R.id.itemIdTV);
		productNameTV = (TextView)findViewById(R.id.productNameTV);
		productInfoTV = (TextView)findViewById(R.id.productInfoTV);
		productPriceTV = (TextView)findViewById(R.id.productPriceTV);
		productImage = (ImageView)findViewById(R.id.productImageView);
		dBHandler = new DBHandler(this);
		
		addProduct = (Button)findViewById(R.id.addProductButton);
		addProduct.setOnClickListener(this);
		
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		thingId = b.getLong("item_id");
		wallId = b.getLong("wall_id");
		
		String wallIdText = Long.toString(wallId);
		String thingIdText = Long.toString(thingId);
		
		wallIDTV.setText(wallIdText);
		itemIDTV.setText(thingIdText);
		
		Product p = dBHandler.findProduct(wallId, thingId);
		productNameTV.setText(p.getProductname());
		productInfoTV.setText(p.getProductinfo());
		productPriceTV.setText(String.valueOf(p.getPrice()));
		productImage.setImageResource(Integer.parseInt(p.getImage()));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_bar, menu);
		return true;
	}
	
	public void addProductToList(){
		Product p = dBHandler.findProduct(wallId, thingId);
		dBHandler.addListItem(new ListItem(p.getProductname(), p.getPrice(), p.getId()));
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
    case R.id.shopping_list:
      	startActivityForResult(new Intent(this, ShowShoppingList.class), 1);	
          return true;
    case R.id.shop_locator:
    		startActivityForResult(new Intent(this, ShowShoppingList.class), 1);
    		return true;
    case R.id.exitApp:
    	setResult(1);
    	finish();
    default:
          return super.onOptionsItemSelected(item);
    }
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
	}
}
