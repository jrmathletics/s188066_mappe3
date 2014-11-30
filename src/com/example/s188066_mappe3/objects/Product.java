package com.example.s188066_mappe3.objects;

public class Product {
	int id;
	String productname;
	String productinfo;
	int price;
	int wallID;
	int thingID;
	String image;

	public Product() {
	}

	public Product(String productName, String productInfo, int Price, int wallid,
			int thingid, String Image) {
		this.productname = productName;
		this.productinfo = productInfo;
		this.price = Price;
		this.wallID = wallid;
		this.thingID = thingid;
		this.image = Image;
	}

	public Product(int iD, String productName, String productInfo, int Price,
			int wallid, int thingid, String Image) {
		this.id = iD;
		this.productname = productName;
		this.productinfo = productInfo;
		this.price = Price;
		this.wallID = wallid;
		this.thingID = thingid;
		this.image = Image;
	}

	public int getId() {
		return id;
	}

	public void setId(int productid) {
		id = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productn) {
		productname = productn;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String producti) {
		productinfo = producti;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int pprice) {
		price = pprice;
	}

	public int getWallid() {
		return wallID;
	}

	public void setWallid(int wallid) {
		wallID = wallid;
	}

	public int getThingid() {
		return thingID;
	}

	public void setThingid(int thingid) {
		thingID = thingid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String img) {
		image = img;
	}

}
