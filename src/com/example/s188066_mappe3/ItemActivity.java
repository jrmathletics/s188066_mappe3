package com.example.s188066_mappe3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ItemActivity extends Activity {

	private TextView wallIDTV;
	private TextView thingIDTV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		wallIDTV = (TextView)findViewById(R.id.wallIdTV);
		thingIDTV = (TextView)findViewById(R.id.thingIdTV);
		
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		final long thingId = b.getLong("thing_id");
		final long wallId = b.getLong("wall_id");
		
		String wallIdText = Long.toString(wallId);
		String thingIdText = Long.toString(thingId);
		
		
		wallIDTV.setText(wallIdText);
		thingIDTV.setText(thingIdText);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item, menu);
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
