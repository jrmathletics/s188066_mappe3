package com.example.s188066_mappe3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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

public class ItemActivity extends Activity implements OnClickListener {

	private TextView productNameTV, productInfoTV, productPriceTV;
	private DBHandler dBHandler;
	private String productName, productPrice, productInfo;
	private Button addProduct;
	private ImageView productImage;
	private AlertDialog messageDialog;
	public long thingId, wallId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		productNameTV = (TextView) findViewById(R.id.productNameTV);
		productInfoTV = (TextView) findViewById(R.id.productInfoTV);
		productPriceTV = (TextView) findViewById(R.id.productPriceTV);
		productImage = (ImageView) findViewById(R.id.productImageView);
		dBHandler = new DBHandler(this);

		addProduct = (Button) findViewById(R.id.addProductButton);
		addProduct.setOnClickListener(this);

		Bundle b = new Bundle();
		b = getIntent().getExtras();
		thingId = b.getLong("item_id");
		wallId = b.getLong("wall_id");

		String wallIdText = Long.toString(wallId);
		String thingIdText = Long.toString(thingId);

		Product p = dBHandler.findProduct(wallId, thingId);
		SpannableString content = new SpannableString(p.getProductname());
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		productNameTV.setText(content);
		productInfoTV.setText(p.getProductinfo());
		productPriceTV.setText(String.valueOf(p.getPrice()
				+ getResources().getString(R.string.currencyText)));
		productImage.setImageResource(Integer.parseInt(p.getImage()));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_bar, menu);
		return true;
	}

	public void addProductToList() {
		Product p = dBHandler.findProduct(wallId, thingId);
		dBHandler.addListItem(new ListItem(p.getProductname(), p.getPrice(), p
				.getId()));
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1) {
			setResult(1);
			finish();
		}
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

		CharSequence productAdded = getString(R.string.productAddedText);
		int duration = Toast.LENGTH_SHORT;
		Toast productToast = Toast.makeText(context, productAdded, duration);

		if (v.getId() == R.id.addProductButton) {
			addProductToList();
			productToast.show();
		}
	}
}
