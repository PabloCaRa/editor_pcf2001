package com.pcfutbolmania.pcf2001.service.fdi.coach;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.model.search.CoachFilter;

public class CoachSearchService {

	public List<Coach> searchCoaches(CoachFilter filter, Map<Integer, Coach> coaches) {

		List<Coach> filteredCoaches = coaches.values().stream()
				.filter(coach -> (StringUtils.containsIgnoreCase(coach.getShortName(), filter.getName())
						|| StringUtils.containsIgnoreCase(coach.getName(), filter.getName()))
						&& (filter.isFree() ? CollectionUtils.isEmpty(coach.getTeams())
								: (filter.getTeamId() != null
										? CollectionUtils.containsAny(coach.getTeams(), filter.getTeamId())
										: true)))
				.sorted((coach1, coach2) -> StringUtils.compareIgnoreCase(
						StringUtils.defaultIfBlank(coach1.getName(), coach1.getShortName()),
						StringUtils.defaultIfBlank(coach2.getName(), coach2.getShortName())))
				.collect(Collectors.toList());

		return filteredCoaches;
	}

}
