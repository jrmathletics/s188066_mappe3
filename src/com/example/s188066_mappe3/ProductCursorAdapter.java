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

public class ProductCursorAdapter extends CursorAdapter{
	
	@SuppressWarnings("deprecation")
	public ProductCursorAdapter(Context context, Cursor c){
		super(context, c);
	}
	
	@Override
	public View newView(Context context, Cursor c, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View returnView = inflater.inflate(R.layout.row, parent, false);
		
		return returnView;
	}
	
	public void bindView(View v, Context context, Cursor c){
		TextView firstname, surname, phone, bday, txt;
		/*
		firstname = (TextView) v.findViewById(R.id.firstnameTextView);
		firstname.setText(c.getString(c.getColumnIndex(c.getColumnName(1))));
		
		surname = (TextView) v.findViewById(R.id.surnameTextView);
		firstname.append(" " + c.getString(c.getColumnIndex(c.getColumnName(2))));
*/
		String x = c.getString(c.getColumnIndex(c.getColumnName(4)));
		String y = x.toString();
		
		String dateSplit[] = y.split(" ", 5);
		String monthString = dateSplit[2];
		String yearString = dateSplit[4];
		String dayString = dateSplit[0];
		int a = Integer.parseInt(monthString);
		
        switch (a) {
            case 1:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 2:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 3:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 4:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 5:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 6:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 7:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 8:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 9:  monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 10: monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 11: monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            case 12: monthString = dayString + "/" + monthString + "/" + yearString;
                     break;
            default: monthString = "Invalid month";
                     break;
        }

//		bday = (TextView) v.findViewById(R.id.datepickerTextView);
//		bday.setText("\n" + monthString);		
	}
}
