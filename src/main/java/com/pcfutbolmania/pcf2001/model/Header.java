package com.pcfutbolmania.pcf2001.model;

public class Header {

	public static final int INIT_FILE_LENGTH = 16;
	public static final Double INIT_FILE_CONTENT = 1.0800405746942644E21;

	private int id; // Player's id
	private int init; // The offset where the player is located in the file
	private int length; // Length of bytes for the player
	private int end;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Header [id=" + id + ", init=" + init + ", length=" + length + "]";
	}

}
