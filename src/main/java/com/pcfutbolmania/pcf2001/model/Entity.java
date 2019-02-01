package com.pcfutbolmania.pcf2001.model;

public class Entity {

	public static final Short ENTITY_START_CONTENT = 800;

	private Header header;

	private Short nameLength;
	private String name;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Short getNameLength() {
		return nameLength;
	}

	public void setNameLength(Short nameLength) {
		this.nameLength = nameLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
