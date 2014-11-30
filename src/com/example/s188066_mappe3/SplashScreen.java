package com.example.s188066_mappe3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends Activity {
	private static int SPLASH_TIME_OUT = 3000;
	TextView app_nameTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		app_nameTV = (TextView) findViewById(R.id.splashTV);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/Helvetica-Bold.ttf");
		app_nameTV.setTypeface(typeFace);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}
