package com.example.s188066_mappe3;

public class Product {
	int id;
	String productname;
	String productinfo;
	int price;
	int wallID;
	int thingID;
	

	public Product() {
	}
	
	public Product(String productName, String productInfo, int Price, int wallid, int thingid) {
		this.productname = productName;
		this.productinfo = productInfo;
		this.price = Price;
		this.wallID = wallid;
		this.thingID = thingid;
	}

	public Product(int iD, String productName, String productInfo, int Price, int wallid, int thingid) {
		this.id = iD;
		this.productname = productName;
		this.productinfo = productInfo;
		this.price = Price;
		this.wallID = wallid;
		this.thingID = thingid;
	}

	int getId() {
		return id;
	}

	void setId(int productid) {
		id = productid;
	}

	String getProductname() {
		return productname;
	}

	void setProductname(String productn) {
		productname = productn;
	}
	
	String getProductinfo(){
		return productinfo;
	}
	
	void setProductinfo(String producti){
		productinfo = producti;
	}
	
	int getPrice(){
		return price;
	}
	
	void setPrice(int pprice){
		price = pprice;
	}
	
	int getWallid(){
		return wallID;
	}
	
	void setWallid(int wallid){
		wallID = wallid;
	}
	
	int getThingid(){
		return thingID;
	}
	
	void setThingid(int thingid){
		thingID = thingid;
	}
		

}
