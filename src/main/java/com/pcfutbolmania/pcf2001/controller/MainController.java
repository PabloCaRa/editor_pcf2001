package com.pcfutbolmania.pcf2001.controller;

import java.util.Map;
import java.util.TreeMap;

import com.pcfutbolmania.pcf2001.Config;
import com.pcfutbolmania.pcf2001.exception.EpcfException;
import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.model.pak.Country;
import com.pcfutbolmania.pcf2001.model.player.Player;
import com.pcfutbolmania.pcf2001.model.stadium.Stadium;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.coach.CoachService;
import com.pcfutbolmania.pcf2001.service.fdi.player.PlayerService;
import com.pcfutbolmania.pcf2001.service.fdi.stadium.StadiumService;
import com.pcfutbolmania.pcf2001.service.fdi.team.TeamService;
import com.pcfutbolmania.pcf2001.service.pak.CountryService;

public class MainController {

	private StadiumService stadiumService;
	private CoachService coachService;
	private PlayerService playerService;
	private TeamService teamService;
	private CountryService countryPakService;

	public MainController() {
		this.stadiumService = new StadiumService();
		this.coachService = new CoachService();
		this.playerService = new PlayerService();
		this.teamService = new TeamService();
		this.countryPakService = new CountryService();
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Stadium> loadStadiums() throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.STADIUMS_FILE);
		Map<Integer, Stadium> stadiums = (TreeMap<Integer, Stadium>) stadiumService.loadEntity(filePath);

		return stadiums;
	}

	public void saveStadiums(Map<Integer, Stadium> stadiums) throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.STADIUMS_FILE);
		stadiumService.saveEntity(filePath, stadiums);
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Coach> loadCoaches() throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.COACHES_FILE);
		Map<Integer, Coach> coaches = (TreeMap<Integer, Coach>) coachService.loadEntity(filePath);

		return coaches;
	}

	public void saveCoaches(Map<Integer, Coach> coaches) throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.COACHES_FILE);
		coachService.saveEntity(filePath, coaches);
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Player> loadPlayers() throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.PLAYERS_FILE);
		Map<Integer, Player> players = (Map<Integer, Player>) playerService.loadEntity(filePath);

		return players;
	}

	public void savePlayers(Map<Integer, Player> players) throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.PLAYERS_FILE);
		playerService.saveEntity(filePath, players);
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Team> loadTeams() throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.TEAMS_FILE);
		Map<Integer, Team> teams = (Map<Integer, Team>) teamService.loadEntity(filePath);

		return teams;
	}

	public void saveTeams(Map<Integer, Team> teams) throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.TEAMS_FILE);
		teamService.saveEntity(filePath, teams);
	}

	public void setTeams(Map<Integer, Team> teams, Map<Integer, Coach> coaches, Map<Integer, Stadium> stadiums,
			Map<Integer, Player> players) {
		teamService.setTeams(teams, coaches, stadiums, players);
	}

	public Map<Integer, Country> loadCountries() throws EpcfException {
		String filePath = Config.getInstance().getProperty(Config.COUNTRIES_FILE);
		Map<Integer, Country> countries = countryPakService.load(filePath);

		return countries;
	}
}
