package com.example.s188066_mappe3;

import java.util.List;
import java.util.Locale;

import com.example.s188066_mappe3.database.DBHandler;
import com.example.s188066_mappe3.objects.Item;
import com.example.s188066_mappe3.objects.Product;
import com.example.s188066_mappe3.objects.Wall;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Spinner wSpinner, iSpinner;
	private DBHandler dbHandler;
	private TextView wTextView, iTextView;
	private long wallID, itemID;
	private Button setChoices, showShoppingList;
	private List<Wall> walls;
	private List<Item> items;
	private Menu menu;
	private AlertDialog messageDialog;
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/Helvetica-Bold.ttf");
		wTextView = (TextView) findViewById(R.id.wallTV);
		wTextView.setTypeface(typeFace);

		iTextView = (TextView) findViewById(R.id.itemTV);
		iTextView.setTypeface(typeFace);

		Locale locale = new Locale("no");
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		onConfigurationChanged(config);

		wallID = 0;
		itemID = 0;

		setChoices = (Button) findViewById(R.id.setChoicesButton);
		setChoices.setTypeface(typeFace);
		setChoices.setOnClickListener(this);

		DBHandler dbHandler = new DBHandler(this);
		if (dbHandler.checkWallDB() == 0) {
			dbHandler.addWall(new Wall(getResources().getString(R.string.bWallText)));
			dbHandler
					.addWall(new Wall(getResources().getString(R.string.thWallText)));
			dbHandler
					.addWall(new Wall(getResources().getString(R.string.llWallText)));
			dbHandler.addWall(new Wall(getResources().getString(R.string.gWallText)));
			dbHandler.addWall(new Wall(getResources().getString(R.string.sWallText)));
		}
		if (dbHandler.checkProductDB() == 0) {
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productButterflyText), getResources().getString(
					R.string.productButterflyTexti), 39, 5, 1, String
					.valueOf(R.drawable.butterflyhook)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productButterflyText), getResources().getString(
					R.string.productButterflyTexti), 39, 4, 1, String
					.valueOf(R.drawable.butterflyhook)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productDrywallAnchorText), getResources().getString(
					R.string.productDrywallAnchorTexti), 49, 4, 2, String
					.valueOf(R.drawable.drywallanchor)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productExpansionText), getResources().getString(
					R.string.productExpansionTexti), 59, 4, 4, String
					.valueOf(R.drawable.expander)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productExpansionText), getResources().getString(
					R.string.productExpansionTexti), 59, 4, 5, String
					.valueOf(R.drawable.expander)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productHerculesText), getResources().getString(
					R.string.productHerculesTexti), 19, 4, 3, String
					.valueOf(R.drawable.hercules)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productNylonPCHText), getResources().getString(
					R.string.productNylonPCHTexti), 29, 1, 1, String
					.valueOf(R.drawable.hook_w_plug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productNylonPSText), getResources().getString(
					R.string.productNylonPSTexti), 45, 1, 2, String
					.valueOf(R.drawable.screw_w_plug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productNylonPSText), getResources().getString(
					R.string.productNylonPSTexti), 45, 1, 4, String
					.valueOf(R.drawable.screw_w_plug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productNylonPSText), getResources().getString(
					R.string.productNylonPSTexti), 45, 1, 5, String
					.valueOf(R.drawable.screw_w_plug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productNylonPSText), getResources().getString(
					R.string.productNylonPSTexti), 45, 2, 5, String
					.valueOf(R.drawable.screw_w_plug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productNylonPSText), getResources().getString(
					R.string.productNylonPSTexti), 45, 3, 5, String
					.valueOf(R.drawable.screw_w_plug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productPictureHookText), getResources().getString(
					R.string.productPictureHookTexti), 17, 2, 3, String
					.valueOf(R.drawable.xhook)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productPictureHookText), getResources().getString(
					R.string.productPictureHookTexti), 17, 3, 3, String
					.valueOf(R.drawable.xhook)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productPictureHookText), getResources().getString(
					R.string.productPictureHookTexti), 17, 5, 3, String
					.valueOf(R.drawable.xhook)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productPlasticHardWallText), getResources().getString(
					R.string.productPlasticHardWallTexti), 27, 1, 3, String
					.valueOf(R.drawable.xbhook)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productScrewText), getResources().getString(
					R.string.productScrewTexti), 9, 5, 2, String
					.valueOf(R.drawable.screw)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productScrewText), getResources().getString(
					R.string.productScrewTexti), 9, 5, 4, String
					.valueOf(R.drawable.screw)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productScrewText), getResources().getString(
					R.string.productScrewTexti), 9, 5, 5, String
					.valueOf(R.drawable.screw)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productUniversalPCHText), getResources().getString(
					R.string.productUniversalPCHTexti), 29, 2, 1, String
					.valueOf(R.drawable.hook_w_universalplug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productUniversalPCHText), getResources().getString(
					R.string.productUniversalPCHTexti), 29, 3, 1, String
					.valueOf(R.drawable.hook_w_universalplug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productUniversalPSText), getResources().getString(
					R.string.productUniversalPSTexti), 39, 2, 2, String
					.valueOf(R.drawable.screw_w_universalplug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productUniversalPSText), getResources().getString(
					R.string.productUniversalPSTexti), 39, 3, 2, String
					.valueOf(R.drawable.screw_w_universalplug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productUniversalPSText), getResources().getString(
					R.string.productUniversalPSTexti), 39, 2, 4, String
					.valueOf(R.drawable.screw_w_universalplug)));
			dbHandler.addProduct(new Product(getResources().getString(
					R.string.productUniversalPSText), getResources().getString(
					R.string.productUniversalPSTexti), 39, 3, 4, String
					.valueOf(R.drawable.screw_w_universalplug)));
		}
		if (dbHandler.checkItemDB() == 0) {
			dbHandler.addItem(new Item(getResources().getString(R.string.lampText)));
			dbHandler
					.addItem(new Item(getResources().getString(R.string.curtainText)));
			dbHandler.addItem(new Item(getResources().getString(
					R.string.sMirrorFrameText)));
			dbHandler.addItem(new Item(getResources().getString(
					R.string.bMirrorFrameText)));
			dbHandler
					.addItem(new Item(getResources().getString(R.string.closetText)));
		}

		wSpinner = (Spinner) findViewById(R.id.wallSpinner);
		iSpinner = (Spinner) findViewById(R.id.itemSpinner);

		walls = dbHandler.findAllWalls();
		ArrayAdapter<Wall> wallArrayAdapter = new ArrayAdapter<Wall>(this,
				android.R.layout.simple_spinner_item, walls);
		wSpinner.setAdapter(wallArrayAdapter);
		wSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				id++;
				wallID = id;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		items = dbHandler.findAllItems();
		ArrayAdapter<Item> itemArrayAdapter = new ArrayAdapter<Item>(this,
				android.R.layout.simple_spinner_item, items);
		iSpinner.setAdapter(itemArrayAdapter);
		iSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				id++;
				itemID = id;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar, menu);
		return super.onCreateOptionsMenu(menu);
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
		case R.id.exitApp:
			finish();
			return true;
		case R.id.openBrowser:
			Intent internetIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://m.clasohlson.com/no/"));
			internetIntent.setComponent(new ComponentName("com.android.browser",
					"com.android.browser.BrowserActivity"));
			internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(internetIntent);
		case R.id.help:
			help();
			return true;
		default:
			return super.onOptionsItemSelected(item);
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

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1) {
			finish();
		}
	}

	public void onClick(View v) {
		if (v.getId() == R.id.setChoicesButton) {
			Intent startIntent = new Intent(this, ItemActivity.class);
			startIntent.putExtra("item_id", itemID);
			startIntent.putExtra("wall_id", wallID);
			this.startActivityForResult(startIntent, 1);
		}
	}

}
