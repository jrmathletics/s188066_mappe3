package com.example.s188066_mappe3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SeeProductActivity extends Activity implements OnClickListener {
	
	private TextView productNameTV, productInfoTV, productPriceTV;
	private ImageView productImage;
	private Button deleteProduct;
	private DBHandler dBHandler;
	private Product product;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_product);
		
		productNameTV = (TextView)findViewById(R.id.shoppinglistProductNameTV);
		productInfoTV = (TextView)findViewById(R.id.shoppinglistProductInfoTV);
		productPriceTV = (TextView)findViewById(R.id.shoppinglistProductPriceTV);
		productImage = (ImageView)findViewById(R.id.shoppinglistProductImageView);
		
		
		deleteProduct = (Button)findViewById(R.id.deleteProductButton);
		
		dBHandler = new DBHandler(this);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		final long id = b.getLong("product_id");
		System.out.println("ID fra extras:" + id);
		
		
		product = dBHandler.findProduct(id);
		
		productNameTV.setText(product.getProductname());
		System.out.println("SeeProductactivity product.getproductname" + product.getProductname());
		productInfoTV.setText(product.getProductinfo());
		productPriceTV.setText(String.valueOf(product.getPrice()));
		productImage.setImageResource(Integer.parseInt(product.getImage()));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_shopping_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {

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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
