package com.pcfutbolmania.pcf2001.model.coach;

import java.util.List;

import com.pcfutbolmania.pcf2001.model.Entity;

public class Coach extends Entity {

	private short lengthSum; // Name length + short name length + number of names. if basic 1, otherwise 2.
	private boolean basic;
	private int photoId;

	private short shortNameLength;
	private String shortName;

	private short hiddenInfoLength;
	private String hiddenInfo;

	private short playSistemLength;
	private String playSistem;

	private short palmaresLength;
	private String palmares;

	private short anecdoteLength;
	private String anecdote;

	private short lastSeasonLength;
	private String lastSeason;

	private short coachTrajectoryLength;
	private String coachTrajectory;

	private int wasPlayer; // 0 no player info. 3 has player info

	private short playerTrajectoryLength;
	private String playerTrajectory;

	// This fields are in the file only if was player is 3
	private short declarationsLength;
	private String declarations;

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

	public short getHiddenInfoLength() {
		return hiddenInfoLength;
	}

	public void setHiddenInfoLength(short hiddenInfoLength) {
		this.hiddenInfoLength = hiddenInfoLength;
	}

	public String getHiddenInfo() {
		return hiddenInfo;
	}

	public void setHiddenInfo(String hiddenInfo) {
		this.hiddenInfo = hiddenInfo;
	}

	public short getPlaySistemLength() {
		return playSistemLength;
	}

	public void setPlaySistemLength(short playSistemLength) {
		this.playSistemLength = playSistemLength;
	}

	public String getPlaySistem() {
		return playSistem;
	}

	public void setPlaySistem(String playSistem) {
		this.playSistem = playSistem;
	}

	public short getPalmaresLength() {
		return palmaresLength;
	}

	public void setPalmaresLength(short palmaresLength) {
		this.palmaresLength = palmaresLength;
	}

	public String getPalmares() {
		return palmares;
	}

	public void setPalmares(String palmares) {
		this.palmares = palmares;
	}

	public short getAnecdoteLength() {
		return anecdoteLength;
	}

	public void setAnecdoteLength(short anecdoteLength) {
		this.anecdoteLength = anecdoteLength;
	}

	public String getAnecdote() {
		return anecdote;
	}

	public void setAnecdote(String anecdote) {
		this.anecdote = anecdote;
	}

	public short getLastSeasonLength() {
		return lastSeasonLength;
	}

	public void setLastSeasonLength(short lastSeasonLength) {
		this.lastSeasonLength = lastSeasonLength;
	}

	public String getLastSeason() {
		return lastSeason;
	}

	public void setLastSeason(String lastSeason) {
		this.lastSeason = lastSeason;
	}

	public short getCoachTrajectoryLength() {
		return coachTrajectoryLength;
	}

	public void setCoachTrajectoryLength(short coachTrajectoryLength) {
		this.coachTrajectoryLength = coachTrajectoryLength;
	}

	public String getCoachTrajectory() {
		return coachTrajectory;
	}

	public void setCoachTrajectory(String coachTrajectory) {
		this.coachTrajectory = coachTrajectory;
	}

	public int getWasPlayer() {
		return wasPlayer;
	}

	public void setWasPlayer(int wasPlayer) {
		this.wasPlayer = wasPlayer;
	}

	public short getPlayerTrajectoryLength() {
		return playerTrajectoryLength;
	}

	public void setPlayerTrajectoryLength(short playerTrajectoryLength) {
		this.playerTrajectoryLength = playerTrajectoryLength;
	}

	public String getPlayerTrajectory() {
		return playerTrajectory;
	}

	public void setPlayerTrajectory(String playerTrajectory) {
		this.playerTrajectory = playerTrajectory;
	}

	public short getDeclarationsLength() {
		return declarationsLength;
	}

	public void setDeclarationsLength(short declarationsLength) {
		this.declarationsLength = declarationsLength;
	}

	public String getDeclarations() {
		return declarations;
	}

	public void setDeclarations(String declarations) {
		this.declarations = declarations;
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
