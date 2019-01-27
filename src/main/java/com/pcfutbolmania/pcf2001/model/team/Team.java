package com.pcfutbolmania.pcf2001.model.team;

import java.util.List;

import com.pcfutbolmania.pcf2001.model.Entity;

public class Team extends Entity {

	public final static int COMPETITIONS_MULTIPLIER = 3;
	public final static int LEAGUE_STATISTICS_LENGTH = 81;
	public final static int TACTIC_LENGTH = 1760;
	public final static int UNKNOWN_2_LENGTH = 7;
	public final static int DINAMIC_COPYRIGHT_LENGTH = 36;
	public final static String DINAMIC_COPYRIGHT_CONTENT = "Copyright (c)2000 Dinamic Multimedia";
	public final static int START_TEAM_LENGTH = 3;

	private short lengthSum;
	private boolean basic;

	private short shortNameLength;
	private String shortName;

	private short stadiumId;
	private int countryId;

	private short nameLength;
	private String name;

	private short foundationYear;
	private int unknown;
	private int supporters;

	private short presidentNameLength;
	private String presidentName;

	private int budget;
	private int defaultBudget;

	private short sponsorNameLength;
	private String sponsorName;

	private short clothingProviderNameLength;
	private String clothingProviderName;

	private short filialTeamId;
	private int segundaBGroup;
	private int terceraGroup;

	private byte[] leagueStatistics;

	// private int season9091position; // 2 bytes
	// private int season9192position; // 2 bytes
	// private int season9293position; // 2 bytes
	// private int season9394position; // 2 bytes
	// private int season9495position; // 2 bytes
	// private int season9596position; // 2 bytes
	// private int season9697position; // 2 bytes
	// private int season9798position; // 2 bytes
	// private int season9899position; // 2 bytes
	// private int season9900position; // 2 bytes
	// 2 x 10 = 20 bytes

	// private byte leaguesPlayed; // 1 byte
	// private int leagueMatchesPlayed; // 2 byte
	// private int leagueMatchesWon; // 2 byte
	// private int leagueMatchesDrawed; // 2 byte
	// private int leagueGoals; // 2 byte
	// private int leagueAgainstGoals; // 2 byte
	// private int leaguePoints; // 2 byte
	// private byte leaguesWon; // 1 byte
	// private byte leagueSecondPositions; // 1 byte
	// 15 bytes

	// private byte[] lastSeasonPositions; // 46 bytes

	private int competitionsLength; // 1 byte
	private byte[] competitions; // competitionsLengt * COMPETITIONS_MULTIPLIER
	// byte idUEFA
	// byte uefas jugadas
	// byte uefas ganadas
	// byte id copa del rey
	// byte copas del rey jugadas
	// byte copas del rey ganadas
	// byte id copa de europa
	// byte copas de europa jugadas
	// byte copas de europa ganadas
	// byte id recopa
	// byte recopas jugadas
	// byte recopas ganadas
	// byte id supercopa de españa
	// byte supercopas de españa jugadas
	// byte supercopas de españa ganadas
	// byte id copa intercontinental
	// byte intercontinentales jugadas
	// byte intercontinentales ganadas
	// byte id supercopa europa
	// byte supercopa europa jugadas
	// byte supercopa europa ganadas

	private byte[] tactic; // TACTIC_LENGTH
	private byte[] unknown2; // UNKNOWN_2_LENGTH

	private int numberOfCoaches;
	private List<Integer> coaches;

	private short numberOfPlayers;

	private List<Integer> registeredPlayers;
	private List<Integer> unregisteredPlayers;

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

	public short getStadiumId() {
		return stadiumId;
	}

	public void setStadiumId(short stadiumId) {
		this.stadiumId = stadiumId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
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

	public short getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(short foundationYear) {
		this.foundationYear = foundationYear;
	}

	public int getUnknown() {
		return unknown;
	}

	public void setUnknown(int unknown) {
		this.unknown = unknown;
	}

	public int getSupporters() {
		return supporters;
	}

	public void setSupporters(int supporters) {
		this.supporters = supporters;
	}

	public short getPresidentNameLength() {
		return presidentNameLength;
	}

	public void setPresidentNameLength(short presidentNameLength) {
		this.presidentNameLength = presidentNameLength;
	}

	public String getPresidentName() {
		return presidentName;
	}

	public void setPresidentName(String presidentName) {
		this.presidentName = presidentName;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getDefaultBudget() {
		return defaultBudget;
	}

	public void setDefaultBudget(int defaultBudget) {
		this.defaultBudget = defaultBudget;
	}

	public short getSponsorNameLength() {
		return sponsorNameLength;
	}

	public void setSponsorNameLength(short sponsorNameLength) {
		this.sponsorNameLength = sponsorNameLength;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public short getClothingProviderNameLength() {
		return clothingProviderNameLength;
	}

	public void setClothingProviderNameLength(short clothingProviderNameLength) {
		this.clothingProviderNameLength = clothingProviderNameLength;
	}

	public String getClothingProviderName() {
		return clothingProviderName;
	}

	public void setClothingProviderName(String clothingProviderName) {
		this.clothingProviderName = clothingProviderName;
	}

	public short getFilialTeamId() {
		return filialTeamId;
	}

	public void setFilialTeamId(short filialTeamId) {
		this.filialTeamId = filialTeamId;
	}

	public int getSegundaBGroup() {
		return segundaBGroup;
	}

	public void setSegundaBGroup(int segundaBGroup) {
		this.segundaBGroup = segundaBGroup;
	}

	public int getTerceraGroup() {
		return terceraGroup;
	}

	public void setTerceraGroup(int terceraGroup) {
		this.terceraGroup = terceraGroup;
	}

	public byte[] getLeagueStatistics() {
		return leagueStatistics;
	}

	public void setLeagueStatistics(byte[] leagueStatistics) {
		this.leagueStatistics = leagueStatistics;
	}

	public int getCompetitionsLength() {
		return competitionsLength;
	}

	public void setCompetitionsLength(int competitionsLength) {
		this.competitionsLength = competitionsLength;
	}

	public byte[] getCompetitions() {
		return competitions;
	}

	public void setCompetitions(byte[] competitions) {
		this.competitions = competitions;
	}

	public byte[] getTactic() {
		return tactic;
	}

	public void setTactic(byte[] tactic) {
		this.tactic = tactic;
	}

	public byte[] getUnknown2() {
		return unknown2;
	}

	public void setUnknown2(byte[] unknown2) {
		this.unknown2 = unknown2;
	}

	public int getNumberOfCoaches() {
		return numberOfCoaches;
	}

	public void setNumberOfCoaches(int numberOfCoaches) {
		this.numberOfCoaches = numberOfCoaches;
	}

	public List<Integer> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<Integer> coaches) {
		this.coaches = coaches;
	}

	public short getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(short numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public List<Integer> getRegisteredPlayers() {
		return registeredPlayers;
	}

	public void setRegisteredPlayers(List<Integer> registeredPlayers) {
		this.registeredPlayers = registeredPlayers;
	}

	public List<Integer> getUnregisteredPlayers() {
		return unregisteredPlayers;
	}

	public void setUnregisteredPlayers(List<Integer> unregisteredPlayers) {
		this.unregisteredPlayers = unregisteredPlayers;
	}

}