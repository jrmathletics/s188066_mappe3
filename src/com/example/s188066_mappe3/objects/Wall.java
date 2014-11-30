package com.example.s188066_mappe3.objects;

public class Wall {
	int id;
	String walltype;

	public Wall() {

	}

	public Wall(String walltype) {
		this.walltype = walltype;
	}

	public Wall(int iD, String wallType) {
		this.id = iD;
		this.walltype = wallType;
	}

	int getId() {
		return id;
	}

	public void setId(int wallid) {
		id = wallid;
	}

	public String getWalltype() {
		return walltype;
	}

	public void setWalltype(String wallt) {
		walltype = wallt;
	}

	@Override
	public String toString() {
		return this.id + ". " + this.walltype;
	}

}
