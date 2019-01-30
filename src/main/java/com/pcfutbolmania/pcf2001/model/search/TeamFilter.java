package com.pcfutbolmania.pcf2001.model.search;

public class TeamFilter {

	private String name;

	private Integer countryId;

	private Integer foundationYearMin;
	private Integer foundationYearMax;

	private Integer supportersMin;
	private Integer supportersMax;

	private Integer budgetMin;
	private Integer budgetMax;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getFoundationYearMin() {
		return foundationYearMin;
	}

	public void setFoundationYearMin(Integer foundationYearMin) {
		this.foundationYearMin = foundationYearMin;
	}

	public Integer getFoundationYearMax() {
		return foundationYearMax;
	}

	public void setFoundationYearMax(Integer foundationYearMax) {
		this.foundationYearMax = foundationYearMax;
	}

	public Integer getSupportersMin() {
		return supportersMin;
	}

	public void setSupportersMin(Integer supportersMin) {
		this.supportersMin = supportersMin;
	}

	public Integer getSupportersMax() {
		return supportersMax;
	}

	public void setSupportersMax(Integer supportersMax) {
		this.supportersMax = supportersMax;
	}

	public Integer getBudgetMin() {
		return budgetMin;
	}

	public void setBudgetMin(Integer budgetMin) {
		this.budgetMin = budgetMin;
	}

	public Integer getBudgetMax() {
		return budgetMax;
	}

	public void setBudgetMax(Integer budgetMax) {
		this.budgetMax = budgetMax;
	}

}
