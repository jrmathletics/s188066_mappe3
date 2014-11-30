package com.example.s188066_mappe3;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMap extends Activity implements LocationListener {
	private GoogleMap map;
	private int userIcon, clasIcon;
	private AlertDialog messageDialog;
	private LocationManager locManager;
	
	static final LatLng TORGGATA = new LatLng(59.913650, 10.746969);
	static final LatLng OSLOCITY = new LatLng(59.912534, 10.751859);
	static final LatLng BOGSTADVEIEN = new LatLng(59.925927, 10.721561);
	static final LatLng LAMBERTSETER = new LatLng(59.874412, 10.810874);
	static final LatLng BRYN = new LatLng(59.903877, 10.821842);
	static final LatLng ALNA = new LatLng(59.927123, 10.848070);
	static final LatLng STORO = new LatLng(59.946996, 10.774385);
	static final LatLng STROMMEN = new LatLng(59.947637, 11.005710);
	static final LatLng LORENSKOG = new LatLng(59.918844, 10.952209);
	static final LatLng CCVEST = new LatLng(59.917888, 10.635953);
	static final LatLng SANDVIKA = new LatLng(59.890183, 10.520225);
	static final LatLng ASKER = new LatLng(59.836835, 10.435055);
	static final LatLng SLEPENDEN = new LatLng(59.873669, 10.502341);

	public boolean zoomer;
	private Marker userMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		zoomer = true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_map);
		userIcon = R.drawable.upin;
		clasIcon = R.drawable.cpin;

		if (map == null) {
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
					.getMap();
		}
		if (map != null) {
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		}
		Marker torggata = map.addMarker(new MarkerOptions().position(TORGGATA).title(getResources().getString(R.string.torggataTitle)).snippet(getResources().getString(R.string.torggataSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker oslocity = map.addMarker(new MarkerOptions().position(OSLOCITY).title(getResources().getString(R.string.oslocityTitle)).snippet(getResources().getString(R.string.oslocitySnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker bogstadveien = map.addMarker(new MarkerOptions().position(BOGSTADVEIEN).title(getResources().getString(R.string.bogstadveienTitle)).snippet(getResources().getString(R.string.bogstadveienSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker lambertseter = map.addMarker(new MarkerOptions().position(LAMBERTSETER).title(getResources().getString(R.string.lambertseterTitle)).snippet(getResources().getString(R.string.lambertseterSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker bryn = map.addMarker(new MarkerOptions().position(BRYN).title(getResources().getString(R.string.brynTitle)).snippet(getResources().getString(R.string.brynSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker alna = map.addMarker(new MarkerOptions().position(ALNA).title(getResources().getString(R.string.alnaTitle)).snippet(getResources().getString(R.string.alnaSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker storo = map.addMarker(new MarkerOptions().position(STORO).title(getResources().getString(R.string.storoTitle)).snippet(getResources().getString(R.string.storoSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker strommen = map.addMarker(new MarkerOptions().position(STROMMEN).title(getResources().getString(R.string.strommenTitle)).snippet(getResources().getString(R.string.strommenSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker lorenskog = map.addMarker(new MarkerOptions().position(LORENSKOG).title(getResources().getString(R.string.lorenskogTitle)).snippet(getResources().getString(R.string.lorenskogSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker ccvest = map.addMarker(new MarkerOptions().position(CCVEST).title(getResources().getString(R.string.ccvestTitle)).snippet(getResources().getString(R.string.ccvestSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker sandvika = map.addMarker(new MarkerOptions().position(SANDVIKA).title(getResources().getString(R.string.sandvikaTitle)).snippet(getResources().getString(R.string.sandvikaSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker asker = map.addMarker(new MarkerOptions().position(ASKER).title(getResources().getString(R.string.askerTitle)).snippet(getResources().getString(R.string.askerSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));
		Marker slependen = map.addMarker(new MarkerOptions().position(SLEPENDEN).title(getResources().getString(R.string.slependenTitle)).snippet(getResources().getString(R.string.slependenSnippet)).icon(BitmapDescriptorFactory.fromResource(clasIcon)));

		
		updatePlaces();

	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    if(map != null){
	        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 100, this);
	    }
	}
	 
	@Override
	protected void onPause() {
	    super.onPause();
	    if(map != null){
	        locManager.removeUpdates(this);
	    }
	}

	private void updatePlaces() {

		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location lastLocation = locManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		double lat = lastLocation.getLatitude();
		double lng = lastLocation.getLongitude();

		LatLng lastLatLng = new LatLng(lat, lng);

		if (userMarker != null) {
			userMarker.remove();
		}
		userMarker = map.addMarker(new MarkerOptions().position(lastLatLng)
				.title(getResources().getString(R.string.userIconText))
				.icon(BitmapDescriptorFactory.fromResource(userIcon))
				.snippet(getResources().getString(R.string.userSnippetText)));

		if(zoomer){
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 12), 3000,
				null);
		zoomer = false;
		}
		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000,
				100, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map_menu, menu);
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
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.v("MyMapActivity", "status changed");

	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.v("MyMapActivity", "provider enabled");

	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.v("MyMapActivity", "provider disabled");

	}

	@Override
	public void onLocationChanged(Location location) {
		Log.v("MyMapActivity", "location changed");
		updatePlaces();

	}
}
