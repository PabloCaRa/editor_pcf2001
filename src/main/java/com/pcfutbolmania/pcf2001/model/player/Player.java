package com.pcfutbolmania.pcf2001.model.player;

import java.util.List;

import com.pcfutbolmania.pcf2001.model.Entity;

public class Player extends Entity {

	public static final byte PARAMETERS_LENGTH = 16;

	private short lengthSum;
	private boolean basic;
	private int photoId;
	private int number;

	private short shortNameLength;
	private String shortName;
	private short nameLength;
	private String name;

	private int genericNumber;
	private int unknown;

	private List<Position> positions;

	private int nationality;
	private SkinColor skinColor;
	private HairColor hairColor;
	private Demarcation demarcation;
	private HairType hairType;
	private FacialHair facialHair;
	private boolean nationalized;

	private int birthDay;
	private int birthMonth;
	private short birthYear;

	private int height;
	private int weight;
	private int birthCountry;

	private short birthCityLength;
	private String birthCity;

	private short formerTeamLength;
	private String formerTeam;

	private short internationalTimesLength;
	private String internationalTimes;

	private byte[] ddbb;

	private Parameters parameters;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public int getGenericNumber() {
		return genericNumber;
	}

	public void setGenericNumber(int genericNumber) {
		this.genericNumber = genericNumber;
	}

	public int getUnknown() {
		return unknown;
	}

	public void setUnknown(int unknown) {
		this.unknown = unknown;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public int getNationality() {
		return nationality;
	}

	public void setNationality(int nationality) {
		this.nationality = nationality;
	}

	public SkinColor getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(SkinColor skinColor) {
		this.skinColor = skinColor;
	}

	public HairColor getHairColor() {
		return hairColor;
	}

	public void setHairColor(HairColor hairColor) {
		this.hairColor = hairColor;
	}

	public Demarcation getDemarcation() {
		return demarcation;
	}

	public void setDemarcation(Demarcation demarcation) {
		this.demarcation = demarcation;
	}

	public HairType getHairType() {
		return hairType;
	}

	public void setHairType(HairType hairType) {
		this.hairType = hairType;
	}

	public FacialHair getFacialHair() {
		return facialHair;
	}

	public void setFacialHair(FacialHair facialHair) {
		this.facialHair = facialHair;
	}

	public boolean isNationalized() {
		return nationalized;
	}

	public void setNationalized(boolean nationalized) {
		this.nationalized = nationalized;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public short getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(short birthYear) {
		this.birthYear = birthYear;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(int birthCountry) {
		this.birthCountry = birthCountry;
	}

	public short getBirthCityLength() {
		return birthCityLength;
	}

	public void setBirthCityLength(short birthCityLength) {
		this.birthCityLength = birthCityLength;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public short getFormerTeamLength() {
		return formerTeamLength;
	}

	public void setFormerTeamLength(short formerTeamLength) {
		this.formerTeamLength = formerTeamLength;
	}

	public String getFormerTeam() {
		return formerTeam;
	}

	public void setFormerTeam(String formerTeam) {
		this.formerTeam = formerTeam;
	}

	public short getInternationalTimesLength() {
		return internationalTimesLength;
	}

	public void setInternationalTimesLength(short internationalTimesLength) {
		this.internationalTimesLength = internationalTimesLength;
	}

	public String getInternationalTimes() {
		return internationalTimes;
	}

	public void setInternationalTimes(String internationalTimes) {
		this.internationalTimes = internationalTimes;
	}

	public byte[] getDdbb() {
		return ddbb;
	}

	public void setDdbb(byte[] ddbb) {
		this.ddbb = ddbb;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public List<Integer> getTeams() {
		return teams;
	}

	public void setTeams(List<Integer> teams) {
		this.teams = teams;
	}
}
