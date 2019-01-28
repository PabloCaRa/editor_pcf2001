package com.pcfutbolmania.pcf2001.service.fdi.player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.player.Player;
import com.pcfutbolmania.pcf2001.model.search.PlayerFilter;

public class PlayerSearchService {

	public List<Player> searchPlayers(PlayerFilter filter, Map<Integer, Player> players) {
		List<Player> playersFiltered = players.values().stream()
				.filter(player -> (StringUtils.containsIgnoreCase(player.getShortName(), filter.getName())
						|| StringUtils.containsIgnoreCase(player.getName(), filter.getName()))
						&& player.getBirthCountry() == (filter.getBirthCountry() != null
								? filter.getBirthCountry().intValue()
								: player.getBirthCountry())
						&& player.getNationality() == (filter.getNationality() != null
								? filter.getNationality().intValue()
								: player.getNationality())
						&& (filter.getPosition() != null
								? CollectionUtils.containsAny(player.getPositions(), filter.getPosition())
								: true)
						&& player.getDemarcation() == (filter.getDemarcation() != null ? filter.getDemarcation()
								: player.getDemarcation())
						&& (player.getBirthYear() >= filter.getBirthYearMin().shortValue()
								&& player.getBirthYear() <= filter.getBirthYearMax().shortValue())
						&& (player.getHeight() >= filter.getHeightMin().intValue()
								&& player.getHeight() <= filter.getHeightMax().intValue())
						&& (player.getWeight() >= filter.getWeightMin().intValue()
								&& player.getWeight() <= filter.getWeightMax().intValue())
						&& player.getParameters()
								.getFoot() == (filter.getFoot() != null ? filter.getFoot().byteValue()
										: player.getParameters().getFoot())
						&& (player.getParameters().getSpeed() >= filter.getSpeedMin().intValue()
								&& player.getParameters().getSpeed() <= filter.getSpeedMax().intValue())
						&& (player.getParameters().getStamina() >= filter.getStaminaMin().intValue()
								&& player.getParameters().getStamina() <= filter.getStaminaMax().intValue())
						&& (player.getParameters().getAggressiveness() >= filter.getAggressivenessMin().intValue()
								&& player.getParameters().getAggressiveness() <= filter.getAggressivenessMax()
										.intValue())
						&& (player.getParameters().getQuality() >= filter.getQualityMin().intValue()
								&& player.getParameters().getQuality() <= filter.getQualityMax().intValue())
						&& (player.getParameters().getGoalkeeper() >= filter.getGoalkeeperMin().intValue()
								&& player.getParameters().getGoalkeeper() <= filter.getGoalkeeperMax().intValue())
						&& (player.getParameters().getTackling() >= filter.getTacklingMin().intValue()
								&& player.getParameters().getTackling() <= filter.getTacklingMax().intValue())
						&& (player.getParameters().getPass() >= filter.getPassMin().intValue()
								&& player.getParameters().getPass() <= filter.getPassMax().intValue())
						&& (player.getParameters().getDribbling() >= filter.getDribblingMin().intValue()
								&& player.getParameters().getDribbling() <= filter.getDribblingMax().intValue())
						&& (player.getParameters().getShot() >= filter.getShotMin().intValue()
								&& player.getParameters().getShot() <= filter.getShotMax().intValue())
						&& (player.getParameters().getHeadshot() >= filter.getHeadshotMin().intValue()
								&& player.getParameters().getHeadshot() <= filter.getHeadshotMax().intValue())
						&& (player.getParameters().getPenalty() >= filter.getPenaltyMin().intValue()
								&& player.getParameters().getPenalty() <= filter.getPenaltyMax().intValue())
						&& (player.getParameters().getLeftCorner() >= filter.getLeftCornerMin().intValue()
								&& player.getParameters().getLeftCorner() <= filter.getLeftCornerMax().intValue())
						&& (player.getParameters().getRightCorner() >= filter.getRightCornerMin().intValue()
								&& player.getParameters().getRightCorner() <= filter.getRightCornerMax().intValue())
						&& (player.getParameters().getLeftFreeKick() >= filter.getLeftFreeKickMin().intValue()
								&& player.getParameters().getLeftFreeKick() <= filter.getLeftFreeKickMax().intValue())
						&& (player.getParameters().getRightFreeKick() >= filter.getRightFreeKickMin().intValue()
								&& player.getParameters().getRightFreeKick() <= filter.getRightFreeKickMax().intValue())
						&& (filter.isFree() ? CollectionUtils.isEmpty(player.getTeams())
								: (filter.getTeamId() != null
										? CollectionUtils.containsAny(player.getTeams(), filter.getTeamId())
										: true)))
				.sorted((player1, player2) -> StringUtils.compareIgnoreCase(player1.getName(), player2.getName()))
				.collect(Collectors.toList());

		List<Player> again = playersFiltered.stream()
				.filter(player -> filter.isFree() ? CollectionUtils.isEmpty(player.getTeams())
						: filter.getTeamId() != null
								? CollectionUtils.containsAny(player.getTeams(), filter.getTeamId())
								: true)
				.collect(Collectors.toList());
		return again;
	}

}
