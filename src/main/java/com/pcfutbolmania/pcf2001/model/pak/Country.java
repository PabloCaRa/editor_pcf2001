package com.pcfutbolmania.pcf2001.model.pak;

public class Country {

	private int id;
	private short nameLength;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getNameLength() {
		return nameLength;
	}

	public void setNameLength(short nameLength) {
		this.nameLength = nameLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", nameLength=" + nameLength + ", name=" + name + "]";
	}

}
