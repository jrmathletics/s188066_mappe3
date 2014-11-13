package com.example.s188066_mappe3;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingListCursorAdapter extends CursorAdapter{
	
	DBHelper dBHelper;
	ShowShoppingList showShoppingList;
	
	
	public ShoppingListCursorAdapter(Context context, Cursor c, int flags){
		super(context, c, flags);
		
		dBHelper = new DBHelper(context);
		showShoppingList = new ShowShoppingList();
	}
	
	@Override
	public View newView(Context context, Cursor c, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View returnView = inflater.inflate(R.layout.srow, parent, false);
		
		return returnView;
	}
	
	public void bindView(View v, Context context, Cursor c){
		TextView productName;
		TextView productPrice;
		TextView currencyTV;
		ImageButton delete;
		
		final long id = c.getLong(0);
		
		productName = (TextView) v.findViewById(R.id.productNameTextView);
		productName.setText(c.getString(c.getColumnIndex(c.getColumnName(1))));
		productPrice = (TextView)v.findViewById(R.id.productPriceTextView);
		productPrice.setText(c.getString(c.getColumnIndex(c.getColumnName(2))));
		currencyTV = (TextView)v.findViewById(R.id.currencyTextView);
		currencyTV.setText(R.string.currencyText);
		delete = (ImageButton)v.findViewById(R.id.deleteRowImageButton);
		
		delete.setOnClickListener(new OnClickListener() {

			   @Override
			   public void onClick(View v) {
			    // TODO Auto-generated method stub
			    Log.i("Delete Button Clicked", "**********");
			    dBHelper.deleteOne(id);
			   }
			  });					
	}
}
