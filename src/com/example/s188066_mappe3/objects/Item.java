package com.example.s188066_mappe3.objects;

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

	public void setId(int itemid) {
		id = itemid;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemt) {
		itemtype = itemt;
	}

	@Override
	public String toString() {
		return this.id + ". " + this.itemtype;
	}

}
