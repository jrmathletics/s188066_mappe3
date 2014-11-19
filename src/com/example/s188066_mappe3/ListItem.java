package com.example.s188066_mappe3;

public class ListItem {
	int id;
	String listitemname;
	int price;
	

	public ListItem() {
	}
	
	public ListItem(String listitemName, int Price) {
		this.listitemname = listitemName;
		this.price = Price;
	}

	public ListItem(int iD, String listitemName, int Price) {
		this.id = iD;
		this.listitemname = listitemName;
		this.price = Price;
	}

	int getId() {
		return id;
	}

	void setId(int productid) {
		id = productid;
	}

	String getListItemname() {
		return listitemname;
	}

	void setListItemname(String listitemn) {
		listitemname = listitemn;
	}
		
	int getPrice(){
		return price;
	}
	
	void setPrice(int pprice){
		price = pprice;
	}
	
	@Override
	public String toString() {
	    return this.id + ". " + this.listitemname + "[" + this.price + "kr]";
	}

}
