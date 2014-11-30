package com.example.s188066_mappe3.objects;

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

	public int getId() {
		return id;
	}

	public void setId(int productid) {
		id = productid;
	}

	public String getListItemname() {
		return listitemname;
	}

	public void setListItemname(String listitemn) {
		listitemname = listitemn;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int pprice) {
		price = pprice;
	}

	public int getProductID() {
		return productid;
	}

	public void setProductID(int pID) {
		productid = pID;
	}

	@Override
	public String toString() {
		return " - " + this.listitemname + " [" + this.price + "kr]";
	}

}
