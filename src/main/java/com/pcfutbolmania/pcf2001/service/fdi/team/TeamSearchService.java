package com.pcfutbolmania.pcf2001.service.fdi.team;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.search.TeamFilter;
import com.pcfutbolmania.pcf2001.model.team.Team;

public class TeamSearchService {

	public List<Team> searchTeams(TeamFilter filter, Map<Integer, Team> teams) {

		List<Team> filteredTeams = teams.values().stream()
				.filter(team -> (StringUtils.containsIgnoreCase(team.getName(), filter.getName())
						|| StringUtils.containsIgnoreCase(team.getShortName(), filter.getName()))
						&& team.getCountryId() == (filter.getCountryId() != null ? filter.getCountryId().intValue()
								: team.getCountryId())
						&& (team.getFoundationYear() >= filter.getFoundationYearMin().intValue()
								&& team.getFoundationYear() <= filter.getFoundationYearMax().intValue())
						&& (team.getSupporters() >= filter.getSupportersMin().intValue()
								&& team.getSupporters() <= filter.getSupportersMax().intValue())
						&& (team.getBudget() >= filter.getBudgetMin().intValue()
								&& team.getBudget() <= filter.getBudgetMax().intValue()))
				.collect(Collectors.toList());

		return filteredTeams;
	}

	public List<Team> searchTeams(Map<Integer, Team> teams, String toSearch) {
		List<Team> filteredTeams = teams.values().stream()
				.filter(team -> StringUtils.containsIgnoreCase(team.getName(), toSearch)
						|| StringUtils.containsIgnoreCase(team.getShortName(), toSearch))
				.collect(Collectors.toList());
		return filteredTeams;
	}

}
