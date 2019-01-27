package com.pcfutbolmania.pcf2001.service.fdi.team;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.team.Team;

public class TeamSearchService {

	public List<Team> searchTeams(Map<Integer, Team> teams, String toSearch) {
		List<Team> filteredTeams = teams.values().stream()
				.filter(team -> StringUtils.containsIgnoreCase(team.getName(), toSearch)
						|| StringUtils.containsIgnoreCase(team.getShortName(), toSearch))
				.collect(Collectors.toList());
		return filteredTeams;
	}

}
