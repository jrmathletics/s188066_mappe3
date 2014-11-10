package com.example.s188066_mappe3;

import java.util.Arrays;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class WallCursorAdapter extends CursorAdapter{
	
	@SuppressWarnings("deprecation")
	public WallCursorAdapter(Context context, Cursor c){
		super(context, c);
	}
	
	@Override
	public View newView(Context context, Cursor c, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View returnView = inflater.inflate(R.layout.row, parent, false);
		
		return returnView;
	}
	
	public void bindView(View v, Context context, Cursor c){
		TextView walltype;
		
		walltype = (TextView) v.findViewById(R.id.walltypeTextView);
		walltype.setText(c.getString(c.getColumnIndex(c.getColumnName(1))));
					
	}
}
