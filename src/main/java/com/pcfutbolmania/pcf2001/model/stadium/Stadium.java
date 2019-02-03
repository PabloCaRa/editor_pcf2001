package com.pcfutbolmania.pcf2001.model.stadium;

import java.util.List;

import com.pcfutbolmania.pcf2001.model.Entity;

public class Stadium extends Entity {

	public static final int STADIUM_IMAGE_WIDTH = 360;
	public static final int STADIUM_IMAGE_HEIGHT = 240;

	private int width;
	private int length;

	private byte unknown;

	private int countryId;

	private short constructionYear;

	private int sittingCapacity;
	private int standingCapacity;

	private List<Integer> teams;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getUnknown() {
		return unknown;
	}

	public void setUnknown(byte unknown) {
		this.unknown = unknown;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public short getConstructionYear() {
		return constructionYear;
	}

	public void setConstructionYear(short constructionYear) {
		this.constructionYear = constructionYear;
	}

	public int getSittingCapacity() {
		return sittingCapacity;
	}

	public void setSittingCapacity(int sittingCapacity) {
		this.sittingCapacity = sittingCapacity;
	}

	public int getStandingCapacity() {
		return standingCapacity;
	}

	public void setStandingCapacity(int standingCapacity) {
		this.standingCapacity = standingCapacity;
	}

	public List<Integer> getTeams() {
		return teams;
	}

	public void setTeams(List<Integer> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Stadium [width=" + width + ", length=" + length + ", countryId=" + countryId + ", constructionYear="
				+ constructionYear + ", sittingCapacity=" + sittingCapacity + ", standingCapacity=" + standingCapacity
				+ ", teams=" + teams + ", getHeader()=" + getHeader() + ", getName()=" + getName() + "]";
	}

}
