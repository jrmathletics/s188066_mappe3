package com.example.s188066_mappe3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SeeProductActivity extends Activity implements OnClickListener {
	
	private TextView productNameTV, productInfoTV, productPriceTV;
	private ImageView productImage;
	private Button deleteProduct;
	private DBHandler dBHandler;
	private Product product;
	private AlertDialog messageDialog;
	private ListItem listitem;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_product);
		
		productNameTV = (TextView)findViewById(R.id.shoppinglistProductNameTV);
		productInfoTV = (TextView)findViewById(R.id.shoppinglistProductInfoTV);
		productPriceTV = (TextView)findViewById(R.id.shoppinglistProductPriceTV);
		productImage = (ImageView)findViewById(R.id.shoppinglistProductImageView);
		
		
		deleteProduct = (Button)findViewById(R.id.deleteProductButton);
		deleteProduct.setOnClickListener(this);
		
		dBHandler = new DBHandler(this);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		final long id = b.getLong("product_id");
		
		
		product = dBHandler.findProduct(id);
		SpannableString content = new SpannableString(product.getProductname());
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		productNameTV.setText(content);
		productInfoTV.setText(product.getProductinfo());
		productPriceTV.setText(String.valueOf(product.getPrice() + getResources().getString(R.string.currencyText)));
		productImage.setImageResource(Integer.parseInt(product.getImage()));
		
	}
	
	public void delete(){
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		final long id = b.getLong("listitem_id");
		listitem = dBHandler.findListItem(id);
		dBHandler.deleteListItem(listitem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_shopping_list_item, menu);
		return true;
	}
	
	public void help() {
		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		View view = inflater.inflate(R.layout.help_view, null);
		helpBuilder.setView(view);

		helpBuilder.setTitle(R.string.helpDialogTitle);

		helpBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				messageDialog.dismiss();
			}
		});

		messageDialog = helpBuilder.create();
		helpBuilder.show();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Context context = getApplicationContext();
		switch (item.getItemId()) {
		case R.id.shopping_list:
			startActivityForResult(new Intent(this, ShowShoppingList.class), 1);
			return true;
		case R.id.shop_locator:
			startActivityForResult(new Intent(this, ShowMap.class), 1);
			return true;
		case R.id.help:
			help();
			return true;
		case R.id.openBrowser:
			Intent internetIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://m.clasohlson.com/no/"));
					internetIntent.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));
					internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(internetIntent);
		case R.id.exitApp:
			setResult(1);
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		Context context = getApplicationContext();

		CharSequence productDeleted = getString(R.string.productDeletedText);
		int duration = Toast.LENGTH_SHORT;
		Toast productToast = Toast.makeText(context, productDeleted, duration);
		if(v.getId() == R.id.deleteProductButton){
			delete();
			NavUtils.navigateUpFromSameTask(this);
			productToast.show();
		}		
	}
}
