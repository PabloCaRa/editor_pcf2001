package com.pcfutbolmania.pcf2001.model.coach;

import java.util.List;

import com.pcfutbolmania.pcf2001.model.Entity;

public class Coach extends Entity {

	private short lengthSum;
	private boolean basic;
	private int photoId;

	private short shortNameLength;
	private String shortName;

	private byte[] ddbb;

	private List<Integer> teams;

	public short getLengthSum() {
		return lengthSum;
	}

	public void setLengthSum(short lengthSum) {
		this.lengthSum = lengthSum;
	}

	public boolean isBasic() {
		return basic;
	}

	public void setBasic(boolean basic) {
		this.basic = basic;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public short getShortNameLength() {
		return shortNameLength;
	}

	public void setShortNameLength(short shortNameLength) {
		this.shortNameLength = shortNameLength;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public byte[] getDdbb() {
		return ddbb;
	}

	public void setDdbb(byte[] ddbb) {
		this.ddbb = ddbb;
	}

	public List<Integer> getTeams() {
		return teams;
	}

	public void setTeams(List<Integer> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Coach [basic=" + basic + ", photoId=" + photoId + ", shortName=" + shortName + ", teams=" + teams
				+ ", getHeader()=" + getHeader() + ", getName()=" + getName() + "]";
	}

}
