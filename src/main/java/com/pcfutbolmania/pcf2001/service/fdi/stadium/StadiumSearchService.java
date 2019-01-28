package com.pcfutbolmania.pcf2001.service.fdi.stadium;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.search.StadiumFilter;
import com.pcfutbolmania.pcf2001.model.stadium.Stadium;

public class StadiumSearchService {

	public List<Stadium> searchStadiums(StadiumFilter filter, Map<Integer, Stadium> stadiums) {

		List<Stadium> filteredStadiums = stadiums.values().stream()
				.filter(stadium -> StringUtils.containsIgnoreCase(stadium.getName(), filter.getName())
						&& stadium.getCountryId() == (filter.getCountry() != null ? filter.getCountry().intValue()
								: stadium.getCountryId())
						&& (stadium.getConstructionYear() >= filter.getConstructionYearMin().shortValue()
								&& stadium.getConstructionYear() <= filter.getConstructionYearMax().shortValue())
						&& (stadium.getWidth() >= filter.getWidthMin().shortValue()
								&& stadium.getWidth() <= filter.getWidthMax().shortValue())
						&& (stadium.getLength() >= filter.getLengthMin().shortValue()
								&& stadium.getLength() <= filter.getLengthMax())
						&& (stadium.getSittingCapacity() >= filter.getSittingCapacityMin().intValue()
								&& stadium.getSittingCapacity() <= filter.getSittingCapacityMax().intValue())
						&& (stadium.getStandingCapacity() >= filter.getStandingCapacityMin().intValue()
								&& stadium.getStandingCapacity() <= filter.getStandingCapacityMax().intValue())
						&& (filter.isFree() ? CollectionUtils.isEmpty(stadium.getTeams())
								: (filter.getTeamId() != null
										? CollectionUtils.containsAny(stadium.getTeams(), filter.getTeamId())
										: true)))
				.sorted((stadium1, stadium2) -> StringUtils.compareIgnoreCase(stadium1.getName(), stadium2.getName()))
				.collect(Collectors.toList());

		return filteredStadiums;
	}

}
