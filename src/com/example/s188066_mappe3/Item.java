package com.example.s188066_mappe3;

public class Item {
	int id;
	String itemtype;

	public Item() {
	}

	public Item(String itemtype) {
		this.itemtype = itemtype;
	}

	public Item(int iD, String itemType) {
		this.id = iD;
		this.itemtype = itemType;
	}

	int getId() {
		return id;
	}

	void setId(int itemid) {
		id = itemid;
	}

	String getItemtype() {
		return itemtype;
	}

	void setItemtype(String itemt) {
		itemtype = itemt;
	}
	
	@Override
	public String toString() {
	    return this.id + ". " + this.itemtype;
	}

}
