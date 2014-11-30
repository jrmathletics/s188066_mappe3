package com.example.s188066_mappe3;

import java.util.List;

import com.example.s188066_mappe3.database.DBHandler;
import com.example.s188066_mappe3.objects.ListItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowShoppingList extends Activity {

	public List<ListItem> listitems;
	public DBHandler dBHandler;
	public TextView emptyListTV, totalTV, totalTextTV, countTV;
	private AlertDialog messageDialog;
	public int total;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_shopping_list);
		total = 0;

		dBHandler = new DBHandler(this);
		emptyListTV = (TextView) findViewById(R.id.emptyListTV);
		countTV = (TextView) findViewById(R.id.countItemsTV);
		final ListView shoppingList = (ListView) findViewById(R.id.shoppingListLV);
		shoppingList.setVisibility(View.INVISIBLE);
		countTV.setVisibility(View.INVISIBLE);
		totalTV = (TextView) findViewById(R.id.totalTV);
		totalTextTV = (TextView) findViewById(R.id.totalTextTV);
		totalTextTV.setVisibility(View.INVISIBLE);
		totalTV.setVisibility(View.INVISIBLE);

		if (dBHandler.countListItems() != 0) {
			emptyListTV.setVisibility(View.INVISIBLE);
			totalTV.setVisibility(View.VISIBLE);
			countTV.setVisibility(View.VISIBLE);
			shoppingList.setVisibility(View.VISIBLE);
			totalTextTV.setVisibility(View.VISIBLE);
		}

		listitems = dBHandler.findAllListItems();

		ArrayAdapter<ListItem> adapter = new ArrayAdapter<ListItem>(this,
				R.layout.trow, listitems);
		shoppingList.setAdapter(adapter);

		for (ListItem item : listitems) {
			total += item.getPrice();
		}

		totalTV.setText(" " + String.valueOf(total)
				+ getResources().getString(R.string.currencyText));

		if (shoppingList.getCount() != 1) {
			countTV.setText(String.valueOf(shoppingList.getCount() + " "
					+ getResources().getString(R.string.countText)));
		} else
			countTV.setText(String.valueOf(shoppingList.getCount() + " "
					+ getResources().getString(R.string.countOneText)));

		shoppingList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				long productid, listitemid;
				ListItem li = (ListItem) shoppingList.getItemAtPosition(position);
				productid = li.getProductID();
				listitemid = li.getId();
				Intent intent = new Intent(ShowShoppingList.this,
						SeeProductActivity.class);
				intent.putExtra("product_id", productid);
				intent.putExtra("listitem_id", listitemid);
				startActivityForResult(intent, 1);
			}

		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1) {
			setResult(1);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.show_shopping_list, menu);
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
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
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
			internetIntent.setComponent(new ComponentName("com.android.browser",
					"com.android.browser.BrowserActivity"));
			internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(internetIntent);
		case R.id.exitApp:
			setResult(1);
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}

	}
}
