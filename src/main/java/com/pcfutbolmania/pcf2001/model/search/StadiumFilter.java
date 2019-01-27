package com.pcfutbolmania.pcf2001.model.search;

public class StadiumFilter {

	private String name;
	private Integer country;

	private Integer constructionYearMin;
	private Integer constructionYearMax;

	private Integer widthMin;
	private Integer widthMax;

	private Integer lengthMin;
	private Integer lengthMax;

	private Integer sittingCapacityMin;
	private Integer sittingCapacityMax;

	private Integer standingCapacityMin;
	private Integer standingCapacityMax;

	private Integer teamId;

	private boolean free;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getConstructionYearMin() {
		return constructionYearMin;
	}

	public void setConstructionYearMin(Integer constructionYearMin) {
		this.constructionYearMin = constructionYearMin;
	}

	public Integer getConstructionYearMax() {
		return constructionYearMax;
	}

	public void setConstructionYearMax(Integer constructionYearMax) {
		this.constructionYearMax = constructionYearMax;
	}

	public Integer getWidthMin() {
		return widthMin;
	}

	public void setWidthMin(Integer widthMin) {
		this.widthMin = widthMin;
	}

	public Integer getWidthMax() {
		return widthMax;
	}

	public void setWidthMax(Integer widthMax) {
		this.widthMax = widthMax;
	}

	public Integer getLengthMin() {
		return lengthMin;
	}

	public void setLengthMin(Integer lengthMin) {
		this.lengthMin = lengthMin;
	}

	public Integer getLengthMax() {
		return lengthMax;
	}

	public void setLengthMax(Integer lengthMax) {
		this.lengthMax = lengthMax;
	}

	public Integer getSittingCapacityMin() {
		return sittingCapacityMin;
	}

	public void setSittingCapacityMin(Integer sittingCapacityMin) {
		this.sittingCapacityMin = sittingCapacityMin;
	}

	public Integer getSittingCapacityMax() {
		return sittingCapacityMax;
	}

	public void setSittingCapacityMax(Integer sittingCapacityMax) {
		this.sittingCapacityMax = sittingCapacityMax;
	}

	public Integer getStandingCapacityMin() {
		return standingCapacityMin;
	}

	public void setStandingCapacityMin(Integer standingCapacityMin) {
		this.standingCapacityMin = standingCapacityMin;
	}

	public Integer getStandingCapacityMax() {
		return standingCapacityMax;
	}

	public void setStandingCapacityMax(Integer standingCapacityMax) {
		this.standingCapacityMax = standingCapacityMax;
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
