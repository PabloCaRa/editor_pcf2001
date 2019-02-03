package com.pcfutbolmania.pcf2001.service.fdi.team;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;

import com.pcfutbolmania.pcf2001.exception.EpcfException;
import com.pcfutbolmania.pcf2001.exception.team.TeamLoadException;
import com.pcfutbolmania.pcf2001.exception.team.TeamSaveException;
import com.pcfutbolmania.pcf2001.helper.PcfStringHelper;
import com.pcfutbolmania.pcf2001.model.Entity;
import com.pcfutbolmania.pcf2001.model.Header;
import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.model.player.Player;
import com.pcfutbolmania.pcf2001.model.stadium.Stadium;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.AbstractEntityService;

public class TeamService extends AbstractEntityService {

	@Override
	protected Map<Integer, Team> load(RandomAccessFile file, Map<Integer, Header> headers) throws EpcfException {

		Map<Integer, Team> teams = new TreeMap<>();

		headers.values().stream().forEach(header -> teams.put(header.getId(), loadTeam(file, header)));

		return teams;
	}

	private Team loadTeam(RandomAccessFile file, Header header) {

		Team team = new Team();

		try {
			file.seek(header.getInit());
			team.setHeader(header);
			file.skipBytes(Team.DINAMIC_COPYRIGHT_LENGTH);
			team.setLengthSum(Short.reverseBytes(file.readShort()));
			file.skipBytes(Team.START_TEAM_LENGTH);
			team.setBasic(file.readBoolean());

			team.setShortNameLength(Short.reverseBytes(file.readShort()));
			team.setShortName(PcfStringHelper.read(team.getShortNameLength(), file));

			team.setStadiumId(Short.reverseBytes(file.readShort()));
			team.setCountryId(file.read());

			team.setNameLength(Short.reverseBytes(file.readShort()));
			team.setName(PcfStringHelper.read(team.getNameLength(), file));

			team.setFoundationYear(Short.reverseBytes(file.readShort()));
			team.setUnknown(file.read());

			if (!team.isBasic()) {
				team.setSupporters(Integer.reverseBytes(file.readInt()));

				team.setPresidentNameLength(Short.reverseBytes(file.readShort()));
				team.setPresidentName(PcfStringHelper.read(team.getPresidentNameLength(), file));

				team.setBudget(Integer.reverseBytes(file.readInt()));
				team.setDefaultBudget(Integer.reverseBytes(file.readInt()));

				team.setSponsorNameLength(Short.reverseBytes(file.readShort()));
				team.setSponsorName(PcfStringHelper.read(team.getSponsorNameLength(), file));

				team.setClothingProviderNameLength(Short.reverseBytes(file.readShort()));
				team.setClothingProviderName(PcfStringHelper.read(team.getClothingProviderNameLength(), file));

				team.setFilialTeamId(Short.reverseBytes(file.readShort()));

				team.setSegundaBGroup(file.read());
				team.setTerceraGroup(file.read());

				byte[] leagueStatistics = new byte[Team.LEAGUE_STATISTICS_LENGTH];
				file.read(leagueStatistics, 0, Team.LEAGUE_STATISTICS_LENGTH);
				team.setLeagueStatistics(leagueStatistics);

				team.setCompetitionsLength(file.read());
				byte[] competitions = new byte[team.getCompetitionsLength() * Team.COMPETITIONS_MULTIPLIER];
				file.read(competitions, 0, team.getCompetitionsLength() * Team.COMPETITIONS_MULTIPLIER);
				team.setCompetitions(competitions);
			}

			byte[] tactic = new byte[Team.TACTIC_LENGTH];
			file.read(tactic, 0, Team.TACTIC_LENGTH);
			team.setTactic(tactic);

			byte[] unknown = new byte[Team.UNKNOWN_2_LENGTH];
			file.read(unknown, 0, Team.UNKNOWN_2_LENGTH);
			team.setUnknown2(unknown);

			team.setNumberOfCoaches(file.read());

			List<Integer> coachIds = new ArrayList<>();
			for (int i = 0; i <= team.getNumberOfCoaches() - 1; i++) {
				coachIds.add(Integer.reverseBytes(file.readInt()));
			}
			team.setCoaches(coachIds);

			team.setNumberOfPlayers(Short.reverseBytes(file.readShort()));

			List<Integer> registeredPlayers = new ArrayList<>();
			List<Integer> unregisteredPlayers = new ArrayList<>();

			for (int i = 0; i < team.getNumberOfPlayers(); i++) {
				if (!file.readBoolean()) {
					registeredPlayers.add(Integer.reverseBytes(file.readInt()));
				} else {
					unregisteredPlayers.add(Integer.reverseBytes(file.readInt()));
				}
			}

			team.setRegisteredPlayers(registeredPlayers);
			team.setUnregisteredPlayers(unregisteredPlayers);

		} catch (IOException e) {
			throw new TeamLoadException(e);
		}

		return team;
	}

	@Override
	protected void save(RandomAccessFile file, Map<Integer, ? extends Entity> entities) throws EpcfException {
		entities.values().stream().forEach(entity -> {
			Team team = (Team) entity;
			saveTeam(file, team);
		});
	}

	private void saveTeam(RandomAccessFile file, Team team) {
		try {
			file.write(Team.DINAMIC_COPYRIGHT_CONTENT.getBytes());
			file.writeShort(Short.reverseBytes(team.getLengthSum()));
			file.writeShort(Short.reverseBytes(Team.ENTITY_START_CONTENT));
			file.write(0);

			file.writeBoolean(team.isBasic());
			file.writeShort(Short.reverseBytes(team.getShortNameLength()));
			PcfStringHelper.write(team.getShortName(), team.getShortNameLength(), file);

			file.writeShort(Short.reverseBytes(team.getStadiumId()));
			file.write(team.getCountryId());

			file.writeShort(Short.reverseBytes(team.getNameLength()));
			PcfStringHelper.write(team.getName(), team.getNameLength(), file);

			file.writeShort(Short.reverseBytes(team.getFoundationYear()));
			file.write(team.getUnknown());

			if (!team.isBasic()) {
				file.writeInt(Integer.reverseBytes(team.getSupporters()));

				file.writeShort(Short.reverseBytes(team.getPresidentNameLength()));
				PcfStringHelper.write(team.getPresidentName(), team.getPresidentNameLength(), file);

				file.writeInt(Integer.reverseBytes(team.getBudget()));
				file.writeInt(Integer.reverseBytes(team.getDefaultBudget()));

				file.writeShort(Short.reverseBytes(team.getSponsorNameLength()));
				PcfStringHelper.write(team.getSponsorName(), team.getSponsorNameLength(), file);

				file.writeShort(Short.reverseBytes(team.getClothingProviderNameLength()));
				PcfStringHelper.write(team.getClothingProviderName(), team.getClothingProviderNameLength(), file);

				file.writeShort(Short.reverseBytes(team.getFilialTeamId()));
				file.write(team.getSegundaBGroup());
				file.write(team.getTerceraGroup());

				file.write(team.getLeagueStatistics());
				file.write(team.getCompetitionsLength());
				file.write(team.getCompetitions());
			}

			file.write(team.getTactic());
			file.write(team.getUnknown2());

			file.write(team.getNumberOfCoaches());

			team.getCoaches().stream().forEach(coachId -> {
				try {
					file.writeInt(Integer.reverseBytes(coachId));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});

			file.writeShort(Short.reverseBytes(team.getNumberOfPlayers()));

			team.getRegisteredPlayers().stream().forEach(registeredPlayer -> {
				writePlayerId(false, registeredPlayer, file);
			});

			team.getUnregisteredPlayers().stream().forEach(unregisteredPlayer -> {
				writePlayerId(true, unregisteredPlayer, file);
			});

		} catch (IOException | RuntimeException e) {
			throw new TeamSaveException(e, team.getHeader().getId());
		}
	}

	private void writePlayerId(boolean unregisteredPlayer, int playerId, RandomAccessFile file)
			throws RuntimeException {
		try {
			file.writeBoolean(unregisteredPlayer);
			file.writeInt(Integer.reverseBytes(playerId));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void setTeams(Map<Integer, Team> teams, Map<Integer, Coach> coaches, Map<Integer, Stadium> stadiums,
			Map<Integer, Player> players) {
		teams.values().parallelStream().forEach(team -> {

			int teamId = team.getHeader().getId();

			short stadiumId = team.getStadiumId();
			List<Integer> coachIds = new ArrayList<>();
			CollectionUtils.addAll(coachIds, team.getCoaches());
			List<Integer> playerIds = new ArrayList<>();
			CollectionUtils.addAll(playerIds, team.getRegisteredPlayers());
			CollectionUtils.addAll(playerIds, team.getUnregisteredPlayers());

			stadiums.get(Short.valueOf(stadiumId).intValue()).getTeams().add(teamId);

			coachIds.parallelStream().forEach(coachId -> {
				coaches.get(coachId).getTeams().add(teamId);
			});

			team.getRegisteredPlayers().parallelStream().forEach(registeredPlayerId -> {
				players.get(registeredPlayerId).getTeamsRegistered().add(teamId);
			});

			team.getUnregisteredPlayers().parallelStream().forEach(unregisteredPlayerId -> {
				players.get(unregisteredPlayerId).getTeamsUnregistered().add(teamId);
			});

		});
	}

}
