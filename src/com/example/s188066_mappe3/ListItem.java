package com.example.s188066_mappe3;

public class ListItem {
	int id;
	String listitemname;
	int price;
	int productid;
	

	public ListItem() {
	}
	
	public ListItem(String listitemName, int Price, int productID) {
		this.listitemname = listitemName;
		this.price = Price;
		this.productid = productID;
	}

	public ListItem(int iD, String listitemName, int Price, int productID) {
		this.id = iD;
		this.listitemname = listitemName;
		this.price = Price;
		this.productid = productID;
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
	
	int getProductID(){
		return productid;
	}
	
	void setProductID(int pID){
		productid = pID;
	}
	
	@Override
	public String toString() {
	    return this.id + ". " + this.listitemname + " [" + this.price + "kr]";
	}

}
