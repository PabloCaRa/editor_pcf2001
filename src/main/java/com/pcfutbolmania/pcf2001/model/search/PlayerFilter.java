package com.pcfutbolmania.pcf2001.model.search;

import com.pcfutbolmania.pcf2001.model.player.Demarcation;
import com.pcfutbolmania.pcf2001.model.player.Position;

public class PlayerFilter {

	private String shortName;
	private String name;

	private Integer birthCountry;
	private Integer nationality;

	private Position position;
	private Demarcation demarcation;

	private Integer birthYearMin;
	private Integer birthYearMax;

	private Integer heightMin;
	private Integer heightMax;

	private Integer weightMin;
	private Integer weightMax;

	private Integer speedMin;
	private Integer speedMax;

	private Integer staminaMin;
	private Integer staminaMax;

	private Integer aggressivenessMin;
	private Integer aggressivenessMax;

	private Integer qualityMin;
	private Integer qualityMax;

	private Integer headshotMin;
	private Integer headshotMax;

	private Integer dribblingMin;
	private Integer dribblingMax;

	private Integer passMin;
	private Integer passMax;

	private Integer shotMin;
	private Integer shotMax;

	private Integer tacklingMin;
	private Integer tacklingMax;

	private Integer goalkeeperMin;
	private Integer goalkeeperMax;

	private Integer foot;

	private Integer penaltyMin;
	private Integer penaltyMax;

	private Integer leftCornerMin;
	private Integer leftCornerMax;

	private Integer rightCornerMin;
	private Integer rightCornerMax;

	private Integer leftFreeKickMin;
	private Integer leftFreeKickMax;

	private Integer rightFreeKickMin;
	private Integer rightFreeKickMax;

	private Integer teamId;

	private boolean free;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(Integer birthCountry) {
		this.birthCountry = birthCountry;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Demarcation getDemarcation() {
		return demarcation;
	}

	public void setDemarcation(Demarcation demarcation) {
		this.demarcation = demarcation;
	}

	public Integer getBirthYearMin() {
		return birthYearMin;
	}

	public void setBirthYearMin(Integer birthYearMin) {
		this.birthYearMin = birthYearMin;
	}

	public Integer getBirthYearMax() {
		return birthYearMax;
	}

	public void setBirthYearMax(Integer birthYearMax) {
		this.birthYearMax = birthYearMax;
	}

	public Integer getHeightMin() {
		return heightMin;
	}

	public void setHeightMin(Integer heightMin) {
		this.heightMin = heightMin;
	}

	public Integer getHeightMax() {
		return heightMax;
	}

	public void setHeightMax(Integer heightMax) {
		this.heightMax = heightMax;
	}

	public Integer getWeightMin() {
		return weightMin;
	}

	public void setWeightMin(Integer weightMin) {
		this.weightMin = weightMin;
	}

	public Integer getWeightMax() {
		return weightMax;
	}

	public void setWeightMax(Integer weightMax) {
		this.weightMax = weightMax;
	}

	public Integer getSpeedMin() {
		return speedMin;
	}

	public void setSpeedMin(Integer speedMin) {
		this.speedMin = speedMin;
	}

	public Integer getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(Integer speedMax) {
		this.speedMax = speedMax;
	}

	public Integer getStaminaMin() {
		return staminaMin;
	}

	public void setStaminaMin(Integer staminaMin) {
		this.staminaMin = staminaMin;
	}

	public Integer getStaminaMax() {
		return staminaMax;
	}

	public void setStaminaMax(Integer staminaMax) {
		this.staminaMax = staminaMax;
	}

	public Integer getAggressivenessMin() {
		return aggressivenessMin;
	}

	public void setAggressivenessMin(Integer aggressivenessMin) {
		this.aggressivenessMin = aggressivenessMin;
	}

	public Integer getAggressivenessMax() {
		return aggressivenessMax;
	}

	public void setAggressivenessMax(Integer aggressivenessMax) {
		this.aggressivenessMax = aggressivenessMax;
	}

	public Integer getQualityMin() {
		return qualityMin;
	}

	public void setQualityMin(Integer qualityMin) {
		this.qualityMin = qualityMin;
	}

	public Integer getQualityMax() {
		return qualityMax;
	}

	public void setQualityMax(Integer qualityMax) {
		this.qualityMax = qualityMax;
	}

	public Integer getHeadshotMin() {
		return headshotMin;
	}

	public void setHeadshotMin(Integer headshotMin) {
		this.headshotMin = headshotMin;
	}

	public Integer getHeadshotMax() {
		return headshotMax;
	}

	public void setHeadshotMax(Integer headshotMax) {
		this.headshotMax = headshotMax;
	}

	public Integer getDribblingMin() {
		return dribblingMin;
	}

	public void setDribblingMin(Integer dribblingMin) {
		this.dribblingMin = dribblingMin;
	}

	public Integer getDribblingMax() {
		return dribblingMax;
	}

	public void setDribblingMax(Integer dribblingMax) {
		this.dribblingMax = dribblingMax;
	}

	public Integer getPassMin() {
		return passMin;
	}

	public void setPassMin(Integer passMin) {
		this.passMin = passMin;
	}

	public Integer getPassMax() {
		return passMax;
	}

	public void setPassMax(Integer passMax) {
		this.passMax = passMax;
	}

	public Integer getShotMin() {
		return shotMin;
	}

	public void setShotMin(Integer shotMin) {
		this.shotMin = shotMin;
	}

	public Integer getShotMax() {
		return shotMax;
	}

	public void setShotMax(Integer shotMax) {
		this.shotMax = shotMax;
	}

	public Integer getTacklingMin() {
		return tacklingMin;
	}

	public void setTacklingMin(Integer tacklingMin) {
		this.tacklingMin = tacklingMin;
	}

	public Integer getTacklingMax() {
		return tacklingMax;
	}

	public void setTacklingMax(Integer tacklingMax) {
		this.tacklingMax = tacklingMax;
	}

	public Integer getGoalkeeperMin() {
		return goalkeeperMin;
	}

	public void setGoalkeeperMin(Integer goalkeeperMin) {
		this.goalkeeperMin = goalkeeperMin;
	}

	public Integer getGoalkeeperMax() {
		return goalkeeperMax;
	}

	public void setGoalkeeperMax(Integer goalkeeperMax) {
		this.goalkeeperMax = goalkeeperMax;
	}

	public Integer getFoot() {
		return foot;
	}

	public void setFoot(Integer foot) {
		this.foot = foot;
	}

	public Integer getPenaltyMin() {
		return penaltyMin;
	}

	public void setPenaltyMin(Integer penaltyMin) {
		this.penaltyMin = penaltyMin;
	}

	public Integer getPenaltyMax() {
		return penaltyMax;
	}

	public void setPenaltyMax(Integer penaltyMax) {
		this.penaltyMax = penaltyMax;
	}

	public Integer getLeftCornerMin() {
		return leftCornerMin;
	}

	public void setLeftCornerMin(Integer leftCornerMin) {
		this.leftCornerMin = leftCornerMin;
	}

	public Integer getLeftCornerMax() {
		return leftCornerMax;
	}

	public void setLeftCornerMax(Integer leftCornerMax) {
		this.leftCornerMax = leftCornerMax;
	}

	public Integer getRightCornerMin() {
		return rightCornerMin;
	}

	public void setRightCornerMin(Integer rightCornerMin) {
		this.rightCornerMin = rightCornerMin;
	}

	public Integer getRightCornerMax() {
		return rightCornerMax;
	}

	public void setRightCornerMax(Integer rightCornerMax) {
		this.rightCornerMax = rightCornerMax;
	}

	public Integer getLeftFreeKickMin() {
		return leftFreeKickMin;
	}

	public void setLeftFreeKickMin(Integer leftFreeKickMin) {
		this.leftFreeKickMin = leftFreeKickMin;
	}

	public Integer getLeftFreeKickMax() {
		return leftFreeKickMax;
	}

	public void setLeftFreeKickMax(Integer leftFreeKickMax) {
		this.leftFreeKickMax = leftFreeKickMax;
	}

	public Integer getRightFreeKickMin() {
		return rightFreeKickMin;
	}

	public void setRightFreeKickMin(Integer rightFreeKickMin) {
		this.rightFreeKickMin = rightFreeKickMin;
	}

	public Integer getRightFreeKickMax() {
		return rightFreeKickMax;
	}

	public void setRightFreeKickMax(Integer rightFreeKickMax) {
		this.rightFreeKickMax = rightFreeKickMax;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
}
